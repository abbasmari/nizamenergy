package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import bal.ApplianceBAL;
import bal.QuickFilterBAL;
import bean.UserBean;

/**
 * Servlet implementation class QuickFilterController
 */
@WebServlet("/QuickFilterController")
public class QuickFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoanBook.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuickFilterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter writer = response.getWriter()) {
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()) {
				if (action.equals("getApplianceByQuickFilters")) {
					Map<String, String[]> parameterMap = request
							.getParameterMap();

					String[] drawString = parameterMap.get("draw");
					int draw = Integer.parseInt(drawString[0]);

					String[] startString = parameterMap.get("start");
					int start = Integer.parseInt(startString[0]);

					String[] lengthString = parameterMap.get("length");
					int length = Integer.parseInt(lengthString[0]);

					String[] orderByString = parameterMap
							.get("order[0][column]");
					int orderBy = Integer.parseInt(orderByString[0]);

					String[] orderDirString = parameterMap.get("order[0][dir]");
					String orderDir = orderDirString[0];

					String[] search = parameterMap.get("search[value]");
					System.out.println("=================== cahed value"
							+ search[0]);
					String applianceStatusString = parameterMap
							.get("applianceStatus")[0];
					System.out.println(applianceStatusString);
					int applianceStatus = Integer
							.parseInt(applianceStatusString);
					String[] soldStatusArrayString = parameterMap
							.get("soldStatus[]");
					String[] healthStatusArrayString = parameterMap.get("healthStatus[]");

					if (soldStatusArrayString == null) {
						soldStatusArrayString = new String[0];
					}if (healthStatusArrayString == null) {
						healthStatusArrayString = new String[0];
					}
					System.out.println(Arrays.toString(soldStatusArrayString));

					JSONObject jsonObject = new JSONObject();
					QuickFilterBAL quickFilterBAL = new QuickFilterBAL();

					ArrayList<HashMap<String, String>> quickedFilteredCustomers = quickFilterBAL
							.getQuickedFilteredAppliances(
									Integer.parseInt(applianceStatusString),
									soldStatusArrayString,healthStatusArrayString, orderBy, orderDir,
									start, length, search[0]);

					JSONArray jsonArray = new JSONArray(
							quickedFilteredCustomers);

					jsonObject.put("draw", draw);
					int count = quickFilterBAL.countQuickedFilteredAppliances(
							applianceStatus, soldStatusArrayString,healthStatusArrayString, search[0]);
					jsonObject.put("recordsTotal", count);
					jsonObject.put("recordsFiltered", count);
					jsonObject.put("applianceCount", count);
					jsonObject.put("data", jsonArray);
					writer.print(jsonObject);

				}
			}
		}
	}

}
