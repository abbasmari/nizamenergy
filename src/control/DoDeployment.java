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

import bal.EligibilityBAL;
import bal.SalesmanBAL;
import bean.UserBean;

/**
 * Servlet implementation class DoDeployment
 */
@WebServlet("/DoDeployment")
public class DoDeployment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoDeployment.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoDeployment() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {
			
			if(request.getParameter("action").equalsIgnoreCase("LatePanalityForAll")){

				int late_panality = Integer.parseInt(request.getParameter("late_panality"));
				int late_days = Integer.parseInt(request.getParameter("late_days"));
				
				SalesmanBAL.addLateCustomersFine(late_panality, late_days);
				response.sendRedirect("SuperAdminDashboard");
			}
			
			if(request.getParameter("action").equalsIgnoreCase("discountForAll")){

				int discount_amount = Integer.parseInt(request.getParameter("discount_amount"));
				String discount_purpose = request.getParameter("discount_purpose");
				
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				int ApplianceId = Integer.parseInt(request.getParameter("ApplianceId"));
				System.err.println("/////////////////////////////addOwnScheme");
				SalesmanBAL.discountForCustomers(discount_amount, discount_purpose);
				response.sendRedirect("SuperAdminDashboard");
			}
			
			
			if(request.getParameter("action").equalsIgnoreCase("fineOne")){

				int fine = Integer.parseInt(request.getParameter("fine"));
				String fine_message = request.getParameter("fine_message");
				
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				int ApplianceId = Integer.parseInt(request.getParameter("ApplianceId"));
				
				SalesmanBAL.customerFine(customerId, ApplianceId, fine, fine_message);
				response.sendRedirect("PaymentLoanServlet?appliace_key="+ApplianceId);
			}
			
			if(request.getParameter("action").equalsIgnoreCase("DiscountOne")){
					System.err.println("DiscountOne");
				int discount = Integer.parseInt(request.getParameter("discount"));
				String discount_message = request.getParameter("discount_message");
				
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				int ApplianceId = Integer.parseInt(request.getParameter("ApplianceId"));
				
				SalesmanBAL.discountCustomer(customerId, ApplianceId, discount, discount_message);
				response.sendRedirect("PaymentLoanServlet?appliace_key="+ApplianceId);
			}
			
			if(request.getParameter("action").equalsIgnoreCase("addOnUpgrateScheme")){
				System.err.println("addOnUpgrateScheme");
			int discount = Integer.parseInt(request.getParameter("discount"));
			String discount_message = request.getParameter("discount_message");
			
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			int ApplianceId = Integer.parseInt(request.getParameter("ApplianceId"));
			
			SalesmanBAL.discountCustomer(customerId, ApplianceId, discount, discount_message);
			response.sendRedirect("PaymentLoanServlet?appliace_key="+ApplianceId);
		}
			
			if(request.getParameter("action").equalsIgnoreCase("add_own_appliance")){

				String customer_appliance_name = request.getParameter("customer_appliance_name");
				int customer_appliance_scheme = Integer.parseInt(request.getParameter("customer_appliance_scheme"));
				if(customer_appliance_scheme == 1){
					customer_appliance_scheme = Integer.parseInt(request.getParameter("customer_appliance_scheme_other"));
				}
				int customer_advance_payment = Integer.parseInt(request.getParameter("customer_advance_payment"));
				int customer_monthly_payment = Integer.parseInt(request.getParameter("customer_monthly_payment"));
				int customer_total_payment = Integer.parseInt(request.getParameter("customer_total_payment"));
				
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				int ApplianceId = Integer.parseInt(request.getParameter("ApplianceId"));
				System.err.println("customerId "+customerId+" ApplianceId "+ApplianceId);
			
			SalesmanBAL.addOwnScheme(customerId, ApplianceId, customer_appliance_name, customer_appliance_scheme, 
					customer_advance_payment, customer_monthly_payment, customer_total_payment);
			response.sendRedirect("doDeployment.jsp");
		}
			
			
			
			if (request.getParameter("action").equalsIgnoreCase("doDeployment")) {
				int userId = ubean.getUserId();
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
				if (!search[0].isEmpty()) {
					int count = SalesmanBAL.getDoDeploymentListSearchCount(
							orderDir, orderBy, userId, search[0]);
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("deploymentCount", count);
					jobject.put("data", SalesmanBAL
							.getDoDeploymentListSearch(start, length, orderDir,
									orderBy, userId, search[0]));
					out.print(jobject);
				} else {
					int count = SalesmanBAL.getDoDeploymentListCount(orderDir,
							orderBy, userId);
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("deploymentCount", count);
					jobject.put("data", SalesmanBAL.getDoDeploymentList(start,
							length, orderDir, orderBy, userId));
					out.print(jobject);
				}
			} else if (request.getParameter("action").equals("countFilters")) {
				HashMap<String, Integer> map = SalesmanBAL
						.countDoPipelineFilters(ubean.getUserId());
				JSONObject jsonObject = new JSONObject(map);
				out.print(jsonObject);
			}

			if (request.getParameter("action").equals("filterPipeline")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

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

				String[] filters = parameterMap.get("filter");
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = SalesmanBAL
						.countDoPipelineFiltersWithSearch(ubean.getUserId(),
								search[0]);

				if (filters[0].equals("pending")) {
					System.out.println("Pending " + map);
					count = map.get("pending");
					loanApplications = SalesmanBAL.getDoDeploymentListPending(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("readyToAssign")) {
					System.out.println("readyToAssign " + map);
					count = map.get("readyToAssign");
					loanApplications = SalesmanBAL
							.getDoDeploymentListRtoAssign(start, length,
									orderDir, orderBy, ubean.getUserId(),
									search[0]);

				} else if (filters[0].equals("awaitingDownpayment")) {
					System.out.println("awaitingDownpayment " + map);
					count = map.get("awaitingDownpayment");
					loanApplications = SalesmanBAL.getDoDeploymentListAdp(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("installed")) {
					System.out.println("installed " + map);
					count = map.get("installed");
					loanApplications = SalesmanBAL
							.getDoDeploymentListInstalled(start, length,
									orderDir, orderBy, ubean.getUserId(),
									search[0]);

				} else if (filters[0].equals("returned")) {
					System.out.println("returned " + map);
					count = map.get("returned");
					loanApplications = SalesmanBAL.getDoDeploymentListReturned(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("rdp")) {
					count = map.get("rdp");
					loanApplications = SalesmanBAL.getDoDeploymentListRdp(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("installed_ns")) {
					count = map.get("installedNoSignal");
					loanApplications = SalesmanBAL
							.getDoDeploymentListInstalledNoSignal(start,
									length, orderDir, orderBy,
									ubean.getUserId(), search[0]);

				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("deploymentCount", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
