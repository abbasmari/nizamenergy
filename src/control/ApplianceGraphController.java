package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import bal.ApplianceStatusBAL;
import bean.UserBean;

/**
 * Servlet implementation class ApplianceGraphController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ApplianceGraphController" })
public class ApplianceGraphController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(ApplianceGraphController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplianceGraphController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : "
				+ bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String applianceID = request.getParameter("applianceId");
		String dates = request.getParameter("dates");
		String dateSolar = request.getParameter("dateSolar");
		String dateSet = request.getParameter("dateSet");

		double solarpower = 0.0;
		double chargingHours = 0.0;
		double chargingMins = 0.0;
		double loadConsumed = 0.0;
		int hours = 0;
		int today=1;

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if (applianceID != null) {
			int appId = Integer.parseInt(applianceID);
			try {
				
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				if(date.equals(dateSet)){
				    today=1;
				    System.err.println("yes it works!!!!!!!"+date+" parameter "+dateSet);
				   }else{
				    today=0;
				   }
				ArrayList<HashMap<String, String>> list = ApplianceBAL
						.getWattageStaticsDateWise(appId, dateSet);

				ArrayList<HashMap<String, Object>> list2 = ApplianceBAL
						.getloadSolarAmpere(appId, dateSet);
				ArrayList<HashMap<String, Object>> list3 = ApplianceBAL
						.getApplianceBatteryPerformance(appId, dateSet);
				solarpower = ApplianceStatusBAL.ApplianceSummaryDateWise(appId,
						dateSet)[0];
				chargingHours = ApplianceStatusBAL.ApplianceSummaryDateWise(
						appId, dateSet)[1];
				chargingMins = ApplianceStatusBAL.ApplianceSummaryDateWise(
						appId, dateSet)[2];
				loadConsumed = ApplianceStatusBAL.ApplianceLoadSummaryDateWise(
						appId, dateSet);
				hours = (int) chargingHours;
				if (list.size() == 0) {
					Date d = new Date();
					HashMap<String, String> map = new HashMap<>();
					map.put("batteryWatt", "0");
					map.put("lodWatt", "0");
					map.put("solarWatt", "0");
					map.put("datetime", d + "");
					map.put("datetime2", d + "");
					list.add(map);
					json.put("dateWise", list);
				}
				if (list2.isEmpty()) {
					System.out
							.println("Empty called *********************************");
					Date d = new Date();
					HashMap<String, Object> map = new HashMap<>();
					map.put("solar", "0");
					map.put("load", "0");
					map.put("datetime2", d + "");
					list2.add(map);
					json.put("solarLoad", list2);
				}
				if (list3.isEmpty()) {
					System.out
							.println("Empty called *********************************");
					Date d = new Date();
					HashMap<String, Object> map = new HashMap<>();
					map.put("batteryVoltage", "0");
					map.put("batteryAmpere", "0");
					map.put("solarVoltage", "0");
					map.put("solarAmpere", "0");
					map.put("applianceTemperature", "0");
					map.put("datetime2", d + "");
					list3.add(map);
					json.put("batteryPerformance", list3);
				}

				json.put("dateWise", list);
				json.put("solarLoad", list2);
				json.put("batteryPerformance", list3);
				json.put("hours", hours);
				json.put("mins", Math.round(chargingMins));
				json.put("loadConsumed", Math.round(loadConsumed));
				json.put("power", Math.round(solarpower));
				json.put("today",today);
				JSONArray JArray = new JSONArray();
				JArray.put(json);
				out.println(JArray);
			} catch (JSONException e) {
				logger.error(e);
				e.printStackTrace();
			}
			
			

			   
			
			
			
		}
	}

}
