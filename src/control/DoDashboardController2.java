package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import bal.ActiveInActiveGraphBAL;
import bal.ApplianceBAL;
import bal.CustomerBAL;
import bal.EligibilityBAL;
import bal.LoanPaymentGraphBAL;
import bal.Message_details;
import bal.MobileMoneyGraphBAL;
import bal.SalesmanBAL;
import bal.soldApplianceGraphBAL;
import bean.ActiveInActiveGraph;
import bean.LoanPaymentGraphBean;
import bean.Message_details_counter;
import bean.MobileMoneyGraphBean;
import bean.UserBean;
import bean.soldApplianceGraphBean;

/**
 * Servlet implementation class DoDashboardController2
 */
@WebServlet("/DoDashboardController2")
public class DoDashboardController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoDashboardController2.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoDashboardController2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		int userid = ubean.getUserId();
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			if (action.equals("getUnseenRequest")) {
				JSONObject json = new JSONObject();
				try {
					json.put("request", EligibilityBAL.getUnseenRequestsByDistrict(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoRecovery")) {
				JSONObject json = null;
				try {
					HashMap<String, String> recovery = ActiveInActiveGraphBAL.getDoRecovery(userid);
					json = new JSONObject(recovery);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoOverduePayments")) {
				JSONObject json = null;
				try {
					HashMap<String, String> overduePayment = ActiveInActiveGraphBAL.getDoOverduePayments(userid);
					json = new JSONObject(overduePayment);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			}

			else if (action.equals("getDoDefaulters")) {
				JSONObject json = null;
				try {
					json = new JSONObject(ActiveInActiveGraphBAL.getDoDefaulters(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoInstallations")) {
				JSONObject json = null;
				try {
					json = new JSONObject(ActiveInActiveGraphBAL.getDoInstallations(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoSales")) {
				JSONObject json = null;
				try {
					json = new JSONObject(ActiveInActiveGraphBAL.getDoSales(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoPerformanceDetails")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
				JSONObject json = null;
				try {
					json = new JSONObject(ActiveInActiveGraphBAL.getDoPerformanceDetails(userid, to, from));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoLoanStatus")) {
				JSONObject json = null;
				try {
					json = new JSONObject(ActiveInActiveGraphBAL.getLoanStatus(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoTopFiveNDs")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
				JSONArray json = null;
				try {
					ArrayList<HashMap<String, String>> ndDetails = ActiveInActiveGraphBAL.getDoTopFiveNds(userid, to,
							from);
					json = new JSONArray(ndDetails);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (action.equals("getTotalCustomersAndAppliances")) {
				try {
					int totalCustomer = CustomerBAL.countCustomersDo(userid);
					int totalAppliance = ApplianceBAL.countAppliancesDo(userid);

					int countPendingCustomersDo = CustomerBAL.countPendingCustomersDo(userid);
					int countAcceptedCustomersDo = CustomerBAL.countAcceptedCustomersDo(userid);
					int countVarifiedCustomersDo = CustomerBAL.countVarifiedCustomersDo(userid);
					HashMap<String, String> countAllStatusAndApplianceStatusDo = ApplianceBAL
							.countAllStatusAndApplianceStatusDo(userid);

					JSONObject jobject = new JSONObject();
					jobject.put("getCustomers", totalCustomer);
					jobject.put("getAppliances", totalAppliance);

					jobject.put("countPendingCustomersDo", countPendingCustomersDo);
					jobject.put("countAcceptedCustomersDo", countAcceptedCustomersDo);
					jobject.put("countVarifiedCustomersDo", countVarifiedCustomersDo);
					jobject.put("countAllStatusAndApplianceStatusDo", countAllStatusAndApplianceStatusDo);

					out.println(jobject);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equalsIgnoreCase("getDonutChartValues")) {

				try {
					JSONObject jobject = new JSONObject();
					jobject.put("data", ApplianceBAL.getSuperAdminDonutChart());
					out.print(jobject);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (action.equals("getTopFos")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
				logger.info(to + " " + from);
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

				JSONObject json = new JSONObject();

				try {
					ArrayList<HashMap<String, String>> topFos = SalesmanBAL.getFosSales(from + "-01", to + "-30", start,
							length, orderBy, orderDir, search[0]);
					int count = SalesmanBAL.countFosSales(search[0]);
					System.out.println("count : " + count);

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("sa_loan_apps_counts", count);
					json.put("data", topFos);

				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (action.equals("getTopNds")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
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

				JSONObject json = new JSONObject();
				System.out.println(to + " " + from);
				try {
					ArrayList<HashMap<String, String>> topFos = SalesmanBAL.getNdsSales(from + "-01", to + "-30", start,
							length, orderBy, orderDir, search[0]);
					int count = SalesmanBAL.countNdsSales(search[0]);
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("sa_loan_apps_counts", count);
					json.put("data", topFos);

				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (action.equals("getTopDos")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
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

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> topFos = SalesmanBAL.getDosSalesHistory(from + "-01", to + "-30",
							start, length, orderBy, orderDir, search[0]);

					int count = SalesmanBAL.countDosSales(from + "-01", to + "-30", search[0]);
					json.put("draw", draw);
					json.put("recordsTotal", topFos.size());
					json.put("recordsFiltered", topFos.size());
					json.put("sa_loan_apps_counts", count);
					json.put("data", topFos);

				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
		}
	}

}
