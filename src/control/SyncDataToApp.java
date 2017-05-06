package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.EligibilityBAL;
import bal.FieldOfficerBAL;
import bal.RecoveryBAL;
import bal.SalesmanBAL;

@WebServlet("/SyncDataToApp")
public class SyncDataToApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SalesmanTableControl.class);
	public SyncDataToApp() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			String action = request.getParameter("action");
			String userIdString = request.getParameter("userId");
			String userType = request.getParameter("userType");

			JSONObject json = new JSONObject();

			String status = "", message = "";

			if (action != null && !action.isEmpty() && action.equals("syncDataToApp")) {

				if (userIdString != null && !userIdString.equals("0")) {
					int userId = Integer.parseInt(userIdString);
					if (userType.equals("FO")) {
						status = "ok";
						// for dashboard data
						json.put("dashboard", EligibilityBAL.getDashboardData(userType, userId));

						// for counts data
						json.put("counts", FieldOfficerBAL.getFOCounters(userId));

						// for payments data
						json.put("payments", EligibilityBAL.getPaymentHistory(userType, userId));

						// for customers data
						json.put("customers", EligibilityBAL.getAllCustomersByFO(userId));

						// for recoveries data
						json.put("recoveries", RecoveryBAL.getRecoveriesByFO(userId));

						// for defaulters data
						json.put("defaulters", FieldOfficerBAL.getDefaultedCustomersByFO(userId));

						// for vles data
						json.put("vles", FieldOfficerBAL.getSalesmen(userId));

						// for verifies data
						json.put("verifies", EligibilityBAL.getAllAcceptedEligibilityByFO(userId));

						// for installs data
						json.put("installs", EligibilityBAL.getAllHandoveredEligibilityByFO(userId));

					} else if (userType.equals("VLE")) {
						status = "ok";

						// for dashboard data
						json.put("dashboard", EligibilityBAL.getDashboardData(userType, userId));

						// for payments data
						json.put("payments", EligibilityBAL.getPaymentHistory(userType, userId));

						// for customers data
						json.put("customers", CustomerBAL.getCustomersDetailBySalesmanId(userId));

						// for recoveries data
						json.put("recoveries", RecoveryBAL.getAllCustomerBySaler(userId));

						// for defaulters data
						json.put("defaulters", SalesmanBAL.getDefaultCustomer(userId));
					} else {
						status = "error";
						message = "userType not defined";
					}
				} else {
					status = "error";
					message = "userId not defined";
				}

			} else {
				status = "error";
				message = "action not defined";
			}
			json.put("status", status);
			json.put("message", message);
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}