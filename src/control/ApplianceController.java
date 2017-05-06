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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceBAL;
import bal.CallingXML;
import bal.telenor;
import bean.UserBean;

@WebServlet("/ApplianceController")
public class ApplianceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ApplianceController.class);

	public ApplianceController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HashMap<String, String> map = new HashMap<>();
		map.put("Name", "Jetander");
		map.put("RollNo", "2k11/CS/34");
		map.put("Percentage", "83 %");

		JSONObject json = null;
		json = new JSONObject(map);
		out.print(json);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equals("getApplianceTable")) {

				HttpSession session = request.getSession();

				UserBean bean = (UserBean) session.getAttribute("email");
				logger.info("User Name : " + bean.getUserName()
						+ " , UserID : " + bean.getUserId() + " , IP : "
						+ request.getSession().getAttribute("ipAddress"));

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

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");
				System.out.println("=================== cahed value"
						+ search[0]);

				JSONObject jobject = new JSONObject();
				try {
					int searchCount = ApplianceBAL.getApplianceSearchCount(search[0]);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", searchCount);
					jobject.put("recordsFiltered", searchCount);
					jobject.put("applianceCount", searchCount);
					jobject.put("data", ApplianceBAL
							.getApplianceByLimitAndRangeAndOrderBy(start,
									length, orderBy, orderDir, search[0]));
				} catch (Exception e) {
					logger.error("", e);
				}
				out.print(jobject);
			}

			if (request.getParameter("action").equals(
					"countApplianceAndSoldStatus")) {
				try {
					HashMap<String, String> countAllStatusAndApplianceStatus = ApplianceBAL
							.countAllStatusAndApplianceStatus();
					JSONObject jsonObject = new JSONObject(
							countAllStatusAndApplianceStatus);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}

			// Jeevan Get all appliance name by Sold to
			if (request.getParameter("action").equals(
					"getAllApplianceNameBySoldTo")) {
				try {
					ArrayList<HashMap<String, String>> allApplianceBySoldTo = new ApplianceBAL()
							.getAllApplianceBySoldTo();
					JSONArray jsonArray = new JSONArray(allApplianceBySoldTo);
					out.print(jsonArray);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
			  if (request.getParameter("action").equals("updateCredentials")) {

	                try {
	                    int applianceId = Integer.parseInt(request
	                            .getParameter("appId"));
	                    String consumer = request.getParameter("consumer");
	                    String imei = request.getParameter("appImei");
	                    String gsm = request.getParameter("appGsm");
	                    System.err.println("updateCredentials imei  " + imei
	                            + "consumer" + consumer + "gsm" + gsm);
	                    ApplianceBAL.EditApplianceCredentials(applianceId, gsm,
	                            imei, consumer);

	                } catch (Exception e) {
	                    logger.error("", e);
	                    e.printStackTrace();
	                }
	            }

			// Ghulam Yaseen update appliance status
			if (request.getParameter("action").equals("updateApplianceStatus")) {
				response.setContentType("application/json;charset=UTF-8");
				int applianceId = Integer.parseInt(request
						.getParameter("appliance_id"));
				int applianceStatus = Integer.parseInt(request
						.getParameter("appliance_status"));

				String data[] = ApplianceBAL.updateApplianceStatus(applianceId,
						applianceStatus).split(":");

				JSONObject obj = new JSONObject();
				if (data[1].equals("1")) {
					obj.put("status", "ok");
					obj.put("appliance_id", applianceId);
				} else {
					obj.put("status", "error");
					obj.put("message", "error in Installing Appliance");
				}
				String str = "Congratulations, your device has been installed, now pay first installment to active the device.";
				try {
					CallingXML.SendMessage(data[0], str);
				} catch (Exception e) {
					logger.error(e);
				}
				out.print(obj);

			}
			
				if (request.getParameter("action").equals("districtSummary")) {
				
				try {
					int applianceId = Integer.parseInt(request
							.getParameter("appId"));
					JSONObject json = new JSONObject(ApplianceBAL.getDistrictSummary(applianceId));
					System.out.print(json);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}

				
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}