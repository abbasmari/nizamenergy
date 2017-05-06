package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import bal.ApplianceBAL;
import bean.UserBean;

/**
 * Servlet implementation class AddApplianceController
 */
@WebServlet("/AddApplianceController")
public class AddApplianceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddApplianceController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddApplianceController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);

	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean bean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + bean.getUserName() + " , UserID : "
					+ bean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));
			if (request.getParameter("action").equalsIgnoreCase("testImei")) {
				JSONArray array = new JSONArray();
				JSONObject obj = new JSONObject();
				System.out
						.println("******************* IMEI *****************");
				String imei = request.getParameter("testImeiValue");
				System.out.println(imei);
				array.add(ApplianceBAL.checkImeiInAppliance(imei));
				out.print(array);
			}

			if (request.getParameter("action")
					.equalsIgnoreCase("testGsmNumber")) {
				JSONArray array = new JSONArray();
				JSONObject obj = new JSONObject();
				System.out.println("******************* GSM *****************");
				String gsmNumber = request.getParameter("testNumber");
				System.out.println(gsmNumber);
				array.add(ApplianceBAL.checkNumberInAppliance(gsmNumber));
				out.print(array);
			}

			// String applianceName = request.getParameter("applianceName");
			// HashMap<String, String> map = new HashMap<String, String>();
			// map.put("applianceGsmNumber", request.getParameter("gsmNumber"));
			// map.put("applianceName", applianceName);
			// map.put("imei", request.getParameter("imeiNumber"));
			// map.put("userId", request.getParameter("userId"));
			// String appliancePrice = "";
			// if (applianceName.equalsIgnoreCase("50 W")) {
			// appliancePrice = "44000";
			// } else if (applianceName.equalsIgnoreCase("80 W")) {
			// appliancePrice = "64000";
			// } else if (applianceName.equalsIgnoreCase("100 W")) {
			// appliancePrice = "84000";
			// }
			// map.put("appliancePrice", appliancePrice);
			//
			// int a = ApplianceBAL.addAppliance(map);
			// if (a > 0) {
			// out.println("Added");
			// } else {
			// out.println("Not Added");
			// }

		}
	}

}
