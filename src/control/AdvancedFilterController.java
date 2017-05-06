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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.AdvancedFilterBAL;
import bal.CustomerBAL;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/AdvancedFilterController")
public class AdvancedFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AdvancedFilterController.class);

	public AdvancedFilterController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String click = request.getParameter("click");

		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");

		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		if (click != null && !click.isEmpty() && click.equalsIgnoreCase("appliancefilter")) {

			PrintWriter out = response.getWriter();
			Map<String, String[]> parameterMap = request.getParameterMap();
			System.out.println("Appliance Advanced Filter ");
			for (String key : parameterMap.keySet()) {
				System.out.println("Key : " + key);
				String[] values = parameterMap.get(key);
				System.out.println("Values Length " + values.length);
				int i = 0;
				for (String value : values) {
					System.out.println("-------- value [" + i + "] = " + value);
					i++;
				}
			}
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

			String[] customerName = parameterMap.get("customername");
			String[] applianceStatus = parameterMap.get("appliancestatus");
			String[] applianceName = parameterMap.get("appliancename");
			String[] applianceNumber = parameterMap.get("appliancenumber");
			String[] district = parameterMap.get("district");
			String[] salesmanName = parameterMap.get("salesmanname");
			String[] status = parameterMap.get("status");

			HashMap<String, String> map = new HashMap<>();
			map.put("c.customer_name", customerName[0].isEmpty() ? "" : "'" + customerName[0] + "'");
			map.put("a.appliance_status", applianceStatus[0].equals("-1") ? "" : applianceStatus[0]);
			map.put("a.appliance_name", applianceName[0].equals("-1") ? "" : applianceName[0]);
			map.put("a.imei_number", applianceNumber[0].isEmpty() ? "" : "'" + applianceNumber[0] + "'");
			map.put("s.salesman_district", district[0].equals("-1") ? "" : district[0]);
			map.put("s.salesman_id", salesmanName[0].equals("0") ? "" : salesmanName[0]);
			map.put("a.status", status[0].equals("-1") ? "" : status[0]);
			System.out.println("ApplianceAdvancedFilterController : Map " + map);
			AdvancedFilterBAL advancedFilter = new AdvancedFilterBAL();
			ArrayList<HashMap<String, String>> filteredAppliances = advancedFilter.getFilteredAppliances(map, start,
					length, orderBy, orderDir);

			int count = advancedFilter.countFilteredAppliances(map);
			System.out.println(count);
			JSONObject json = new JSONObject();
			try {
				json.put("data", filteredAppliances);
				json.put("draw", draw);
				json.put("recordsTotal", count);
				json.put("recordsFiltered", count);
				json.put("applianceCount", count);
			} catch (JSONException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			out.println(json);
		}
		// End Appliance Filter

		// Customer Filter
		if (click != null && !click.isEmpty() && click.equalsIgnoreCase("customerfilter")) {
			PrintWriter out = response.getWriter();

			Map<String, String[]> parameterMap = request.getParameterMap();
			// extract draw or page no from request parameter map
			String[] drawStringArray = parameterMap.get("draw");
			int draw = Integer.parseInt(drawStringArray[0]); // (Page No/Start
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

			String[] customerName = parameterMap.get("customername");
			String[] customerStatus = parameterMap.get("customerstatus");
			String[] customerMonthlyIncome = parameterMap.get("customermonthlyincome");
			String[] applianceStatus = parameterMap.get("appliancestatus");
			String[] applianceName = parameterMap.get("appliancename");
			String[] applianceNumber = parameterMap.get("appliancenumber");
			String[] district = parameterMap.get("district");
			String[] salesmanName = parameterMap.get("salesmanname");
			String searchValue = parameterMap.get("search[value]")[0];
			HashMap<String, String> map = new HashMap<>();
			map.put("cs.customer_name", customerName[0].isEmpty() ? "" : "'" + customerName[0] + "'");
			map.put("(e.status",
					customerStatus[0].equals("-1") ? ""
							: customerStatus[0].equals("1") ? "'1' or e.status = '6')"
									: customerStatus[0].equals("2") ? "'2' or e.status = '4')"
											: "'" + customerStatus[0] + "')");
			map.put("cs.customer_monthly_income",
					customerMonthlyIncome[0].equals("-1") ? "" : "" + customerMonthlyIncome[0] + "");
			map.put("a.appliance_name", applianceName[0].equals("-1") ? "" : applianceName[0]);
			map.put("a.appliance_gsmno", applianceNumber[0].isEmpty() ? "" : "'" + applianceNumber[0] + "'");
			map.put("a.appliance_status", applianceStatus[0].equals("-1") ? "" : applianceStatus[0]);
			map.put("d.district_id", district[0].equals("-1") ? "" : district[0]);
			map.put("s.salesman_id", salesmanName[0].equals("0") ? "" : salesmanName[0]);
			System.out.println("ApplianceAdvancedFilterController " + map);

			JSONObject json = new JSONObject();
			try {
				if (!searchValue.trim().isEmpty()) {
					// System.out.println("Search Regex "+searchRegex);
					System.out.println("Search Value " + searchValue);
					ArrayList<HashMap<String, String>> customersByRegexSearch = CustomerBAL
							.getCustomersByRegexSearch(searchValue, start, length, orderDir, orderBy);
					int total = CustomerBAL.countCustomersByRegexSearch(searchValue);
					json.put("recordsTotal", total);
					json.put("recordsFiltered", total);
					JSONArray jsonArray = new JSONArray(customersByRegexSearch);
					json.put("data", jsonArray);

				} else {
					AdvancedFilterBAL advancedFilter = new AdvancedFilterBAL();
					ArrayList<HashMap<String, String>> customers = advancedFilter.getFilteredCustomers(map, orderBy,
							orderDir, start, length);
					int count = advancedFilter.countFilteredCustomers(map);
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					JSONArray jsonArray = new JSONArray(customers);
					json.put("data", jsonArray);

				}
			} catch (JSONException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			out.println(json);
		}
	}

}
