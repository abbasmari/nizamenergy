package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ActiveInActiveGraphBAL;
import bal.ApplianceBAL;
import bal.Payment_loanNewBAL;
import bal.CustomerBAL;
import bal.EligibilityBAL;
import bal.LoanPaymentGraphBAL;
import bal.Message_details;
import bal.MobileMoneyGraphBAL;
import bal.soldApplianceGraphBAL;
import bean.ActiveInActiveGraph;
import bean.LoanPaymentGraphBean;
import bean.Message_details_counter;
import bean.MobileMoneyGraphBean;
import bean.soldApplianceGraphBean;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(asyncSupported = true, urlPatterns = { "/SuperAdminDashboardController" })
public class SuperAdminDashboardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(SuperAdminDashboardController.class);

	public SuperAdminDashboardController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			if (action.equals("getUnseenRequest")) {
				JSONObject json = new JSONObject();
				try {
					int countRequests = EligibilityBAL.getUnseenRequests();
					json.put("request", countRequests);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getRecovery")) {
				try {
					HashMap<String, String> recovery = ActiveInActiveGraphBAL
							.getRecoveryRateBeforeDueDate();
					JSONObject json = new JSONObject(recovery);
					json.put("getRecoveryRateBeforeDueDate", recovery);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getParTen")) {
				try {
					HashMap<String, String> parTen = ActiveInActiveGraphBAL
							.getRecoveryRateAfterDueDate();
					JSONObject json = new JSONObject(parTen);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getPortfolio")) {
				try {
					HashMap<String, String> getTotalOutstandingLoans = ActiveInActiveGraphBAL
							.getTotalOutstandingLoans();
					JSONObject json = new JSONObject(getTotalOutstandingLoans);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getDoTopFiveNDs")) {
				try {
					JSONArray json = new JSONArray(
							EligibilityBAL.getTopFiveNDs());
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (action.equals("getDoTopFiveFos")) {
				try {
					JSONArray json = new JSONArray(
							EligibilityBAL.getTopFiveFos());
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getDoPerformance")) {
				try {
					JSONArray json = new JSONArray(
							EligibilityBAL.getTopFiveDos());
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getPortfolioHealth")) {
				try {
					HashMap<String, String> map = EligibilityBAL
							.totalPortfolioHealth();
					JSONObject json = new JSONObject(map);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getCreditScore")) {
				try {
					ArrayList<HashMap<String, String>> list = EligibilityBAL
							.getDistrict_wise_rating();
					JSONArray json = new JSONArray(list);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getRecoveryDetails")) {
				try {
					ArrayList<HashMap<String, String>> list = EligibilityBAL
							.monthlyPortfolio();
					JSONArray json = new JSONArray(list);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getTotalCustomersAndAppliances")) {
				try {
					int totalCustomer = CustomerBAL.countCustomers();
					int totalAppliance = ApplianceBAL.countAppliances();

					int countPendingCustomers = CustomerBAL
							.countPendingCustomers();
					int countAcceptedCustomers = CustomerBAL
							.countAcceptedCustomers();
					int countVarifiedCustomers = CustomerBAL
							.countVarifiedCustomers();
					HashMap<String, String> countAllStatusAndApplianceStatus = ApplianceBAL
							.countAllStatusAndApplianceStatus();
					Payment_loanNewBAL payment_loanNewBAL = new Payment_loanNewBAL();
					HashMap<String, String> countLoanBookFilters = payment_loanNewBAL
							.countLoanBookFilters();
					JSONObject jobject = new JSONObject();
					jobject.put("getCustomers", totalCustomer);
					jobject.put("getAppliances", totalAppliance);

					jobject.put("countPendingCustomers", countPendingCustomers);
					jobject.put("countAcceptedCustomers",
							countAcceptedCustomers);
					jobject.put("countVarifiedCustomers",
							countVarifiedCustomers);
					jobject.put("countAllStatusAndApplianceStatus",
							countAllStatusAndApplianceStatus);
					jobject.put("countLoanBookFilters", countLoanBookFilters);
					out.println(jobject);

				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equalsIgnoreCase("getDonutChartValues")) {
				try {
					JSONObject jobject = new JSONObject();
					jobject.put("data", ApplianceBAL.getSuperAdminDonutChart());
					out.print(jobject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getSchemePlansDonutChart")) {
				try {
					JSONObject jobject = new JSONObject();
					jobject.put("scheme", CustomerBAL.getAppliance());
					out.print(jobject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
		}
	}

}