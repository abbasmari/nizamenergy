package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceBAL;
import bal.ApplianceStatusBAL;
import bean.UserBean;

/**
 * Servlet implementation class ApplianceAlarmsControl
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ApplianceAlarmsControl" })
public class ApplianceAlarmsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ApplianceAlarmsControl.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplianceAlarmsControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		JSONObject json = new JSONObject();
		int appId = Integer.parseInt(request.getParameter("appId"));

		System.out.println("**********************" + appId);
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase("getAlarmsTable")) {
				System.out.println("*********************///////////////////////////////");
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);
				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");
				System.out.println("=================== cahed value" + search[0]);

				if (!search[0].isEmpty()) {
					ArrayList<HashMap<String, String>> list = ApplianceStatusBAL
							.getSuperadminApplianceviewAlertsListSearch(start, length, orderDir, orderBy, appId,
									search[0]);
					int count = ApplianceStatusBAL.getSuperadminApplianceviewAlertsListSearchCount(orderDir, orderBy,
							appId, search[0]);
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("salesmansCount", count);
					json.put("data", list);
					out.print(json);

				} else {

					ArrayList<HashMap<String, String>> list = ApplianceStatusBAL
							.getSuperadminApplianceViewAlertList(start, length, orderDir, orderBy, appId);
					int count = ApplianceStatusBAL.getSuperadminApplianceViewAlertListCount(orderDir, orderBy, appId);
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("salesmansCount", count);
					json.put("data", list);
					out.print(json);

				}

			}
			if (request.getParameter("action").equalsIgnoreCase("getsummaryTable")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);
				int count = ApplianceBAL.getApplianceStatusbyId(appId).size();
				json.put("draw", draw);
				json.put("recordsTotal", count);
				json.put("recordsFiltered", count);
				json.put("salesmansCount", count);
				json.put("data", ApplianceBAL.getApplianceStatusbyId(appId));

				out.print(json);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
