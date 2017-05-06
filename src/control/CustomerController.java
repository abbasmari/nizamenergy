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

import org.json.JSONArray;
import org.json.simple.JSONObject;

import bal.CustomerBAL;
import bean.UserBean;

import org.apache.log4j.Logger;

@WebServlet(urlPatterns = { "/CustomerController" })
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(CustomerController.class);

	public CustomerController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + userBean.getUserName() + " , UserID : " + userBean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		if (userBean != null) {
			String action = request.getParameter("action");
			CustomerBAL customerBAL = new CustomerBAL();
			if (action != null && !action.isEmpty()) {
				if (action.equals("getAllCountCustomerTableStatus")) {
					HashMap<String, String> countList = CustomerBAL.getCountAllEligibilityAndApplianceStatus();
					JSONObject jsonObject = new JSONObject(countList);
					out.print(jsonObject);
				}
				if (action.equals("getAllCustomerSalaryRanges")) {
					ArrayList<HashMap<String, String>> allCustomerSalaryRanges = customerBAL
							.getAllCustomerSalaryRanges();
					JSONArray jsonArray = new JSONArray(allCustomerSalaryRanges);
					out.print(jsonArray);
				}
				if (action.equals("getCnic")) {
					JSONObject json = new JSONObject();
					String cnic = request.getParameter("customerCnic");
					String cnicdata = CustomerBAL.getCnic(cnic);
					if (cnic.equals(cnicdata)) {
						json.put("status", "ok");
					}
					out.println(json);
				}
				if (action.equalsIgnoreCase("getCustomersByLimitAndRange")) {
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
					String searchValue = parameterMap.get("search[value]")[0];
					JSONObject jsonObject = new JSONObject();

					if (!searchValue.trim().isEmpty()) {
						System.out.println("Search Value " + searchValue);
						ArrayList<HashMap<String, String>> customersByRegexSearch = CustomerBAL
								.getCustomersByRegexSearch(searchValue, start, length, orderDir, orderBy);
						int total = CustomerBAL.countCustomersByRegexSearch(searchValue);
						jsonObject.put("draw", draw);
						jsonObject.put("recordsTotal", total);
						jsonObject.put("recordsFiltered", total);
						JSONArray jsonArray = new JSONArray(customersByRegexSearch);
						jsonObject.put("data", jsonArray);

					} else {
						ArrayList<HashMap<String, String>> customerList = CustomerBAL
								.getCustomersByLimitAndRangeAndOrderBy(start, length, orderBy, orderDir);
						int total = CustomerBAL.countCustomers();
						jsonObject.put("draw", draw);
						jsonObject.put("recordsTotal", total);
						jsonObject.put("recordsFiltered", total);
						JSONArray jsonArray = new JSONArray(customerList);
						jsonObject.put("data", jsonArray);
					}

					out.println(jsonObject);
				}
				if (action.equalsIgnoreCase("getRejectedCustomers")) {
					Map<String, String[]> parameterMap = request.getParameterMap();
					// extract draw or page no from request parameter map
					String[] drawStringArray = parameterMap.get("draw");
					int draw = Integer.parseInt(drawStringArray[0]); // (Page
																		// No/Start
																		// Position)

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
					String searchValue = parameterMap.get("search[value]")[0];
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("draw", draw);
					if (!searchValue.trim().isEmpty()) {
						System.out.println("Search Value " + searchValue);
						ArrayList<HashMap<String, String>> customersByRegexSearch = CustomerBAL
								.getRejectedCustomersByRegexSearch(searchValue, start, length);
						int total = CustomerBAL.countRejectedCustomersByRegexSearch(searchValue, start, length);
						jsonObject.put("recordsTotal", total);
						jsonObject.put("recordsFiltered", total);
						JSONArray jsonArray = new JSONArray(customersByRegexSearch);
						jsonObject.put("data", jsonArray);

					} else {
						ArrayList<HashMap<String, String>> customerList = CustomerBAL.getRejectedCustomers(start,
								length);
						int total = CustomerBAL.countRejectedCustomers(start, length);
						jsonObject.put("recordsTotal", total);
						jsonObject.put("recordsFiltered", total);
						JSONArray jsonArray = new JSONArray(customerList);
						jsonObject.put("data", jsonArray);
					}

					out.println(jsonObject);
				}
			}
		}

	}

}
