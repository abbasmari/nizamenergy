package bal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import connection.Connect;

public class AdvancedFilterBAL {

	private final static Logger logger = Logger.getLogger(AdvancedFilterBAL.class);

	public ArrayList<HashMap<String, String>> getFilteredAppliances(HashMap<String, String> whereClause, int start,
			int range, int columnIndex, String dir) {
		System.out.println("AdvancedFilterBAL.getFilteredAppliances()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		String condition = "";

		for (String key : whereClause.keySet()) {
			String t = key;
			String u = whereClause.get(key);
			if (!u.isEmpty()) {
				if (condition.isEmpty()) {
					condition = "where ";
				} else if (!condition.isEmpty()) {
					condition += "\n AND ";
				}
				condition += (t + " = " + u);
				// System.out.println(t + " = " + u);
			}

		}
		// set column name
		String column = "a.appliance_name";
		switch (columnIndex) {
		case 1:
			column = "a.appliance_name";
			break;
		case 2:
			column = "a.appliance_gsmno";
			break;
		case 4:
			column = "d.district_name";
			break;
		case 5:
			column = "c.customer_name";
			break;
		case 6:
			column = "s.salesman_name";
			break;
		default:
			column = "cs.customer_name";
			break;
		}

		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT " + "a.appliance_id, "
					+ "a.appliance_GSMno, " + "a.appliance_name, " + "a.appliance_product_no, " + "a.appliance_price, "
					+ "a.appliance_status, " + "c.customer_name, " + "d.district_name, " + "s.salesman_name, "
					+ "a.status, " + "s.salesman_id, "
					+ "c.customer_phone, isDefaulted(a.appliance_id) AS defaulted,a.imei_number "
					+ "from eligibility e INNER JOIN customer c "
					+ "ON c.customer_id = e.customer_id INNER JOIN appliance a "
					+ "ON a.appliance_id = e.appliance_id INNER JOIN salesman s "
					+ "ON s.salesman_id = e.salesman_id INNER JOIN district d ON d.district_id = s.salesman_district "
					+ "INNER JOIN field_officer fo ON fo.fo_id = s.fo_id INNER JOIN qasolarp_nizamdb_tester.user us ON us.user_id = fo.do_id "
					+ condition + " AND e.status != 3 " + " AND e.status != 2 " + "ORDER BY " + column + " " + dir
					+ " LIMIT " + start + " " + "," + range + " ");
			System.out.println("Advanced Filter getFilteredAppliances() : " + ps.getPreparedSql());
			// ps.setString(1, "60 W");
			ResultSet resultSet = ps.executeQuery();
			System.out.println();
			while (resultSet.next()) {

				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("appliancePrice", resultSet.getString("appliance_price"));
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("applianceGSM", resultSet.getString("appliance_GSMno"));
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("salesmanDistrict", resultSet.getString("district_name"));
				map.put("customerPhone", resultSet.getString("customer_phone"));
				map.put("salesmanName", resultSet.getString("salesman_name"));
				map.put("applianceStatusActive", resultSet.getInt("appliance_status") + "");
				map.put("applianceStatus", resultSet.getInt("a.status") + "");
				map.put("imeiNumber", resultSet.getString("imei_number"));
				map.put("isDefaulted", resultSet.getInt(13) + "");
				maps.add(map);
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println("Ex : ApplianceAdvancedFilterBAL.getFilteredAppliances()");
			// System.err.println();
			e.printStackTrace();
		}
		return maps;
	}

	public int countFilteredAppliances(HashMap<String, String> whereClause) {
		System.out.println("AdvancedFilterBAL.countFilteredAppliances()");
		int count = 0;
		String condition = "";

		for (String key : whereClause.keySet()) {
			String t = key;
			String u = whereClause.get(key);
			if (!u.isEmpty()) {
				if (condition.isEmpty()) {
					condition = "where ";
				} else if (!condition.isEmpty()) {
					condition += "\n AND ";
				}
				condition += (t + " = " + u);
				// System.out.println(t + " = " + u);
			}

		}
		System.out.println(condition);

		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT " + "COUNT(*) "
					+ "FROM eligibility st " + "INNER JOIN customer c " + "ON c.customer_id = st.customer_id "
					+ "INNER JOIN appliance a " + "ON a.appliance_id = st.appliance_id " + "INNER JOIN salesman s "
					+ "ON s.salesman_id = st.salesman_id " + "INNER JOIN district d "
					+ "ON d.district_id = s.salesman_district " + condition + " AND e.status != 3");
			System.out.println("Advanced Filter countFilteredAppliances() : " + ps.getPreparedSql());
			// ps.setString(1, "60 W");
			ResultSet rs = ps.executeQuery();
			System.out.println();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println("Ex : ApplianceAdvancedFilterBAL.countFilteredAppliances()");
			// System.err.println();
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<HashMap<String, String>> getFilteredCustomers(HashMap<String, String> whereClause, int columnIndex,
			String dir, int start, int length) {
		System.out.println("AdvancedFilterBAL.getFilteredCustomers()");
		ArrayList<HashMap<String, String>> listMap = new ArrayList<>();

		// ArrayList<CustomerInfoBean> customerList = new
		// ArrayList<CustomerInfoBean>();
		String condition = "";

		for (String key : whereClause.keySet()) {
			String t = key;
			String u = whereClause.get(key);
			if (!u.isEmpty()) {
				if (condition.isEmpty()) {
					condition = "where ";
				} else if (!condition.isEmpty()) {
					condition += "\n AND ";
				}
				condition += (t + " = " + u);
				// System.out.println(t + " = " + u);
			}

		}
		String column = "cs.customer_name";
		switch (columnIndex) {
		case 1:
			column = "cs.customer_name";
			break;
		case 2:
			column = "d.district_name";
			break;
		case 4:
			column = "sal.salary_range";
			break;
		case 5:
			column = "a.appliance_name";
			break;
		default:
			column = "cs.customer_name";
			break;
		}

		try (Connection con = Connect.getConnection()) {

			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT e.eligibility_id, " + "e.status,"
					+ "cs.customer_id," + "	cs.customer_name," + "	cs.customer_phone," + "	cs.customer_cnic,"
					+ "CONCAT(c.city_name, ', ', d.district_name) AS 'district_name'," + "cs.status,"
					+ "sal.salary_range," + "a.appliance_id,"
					+ "(SELECT COUNT(DISTINCT pl.loan_id) FROM sold_to sld JOIN payment_loan pl ON pl.soldto_id = sld.sold_to_id "
					+ "JOIN loan_payments l ON l.loan_id = pl.loan_id WHERE sld.customer_id = cs.customer_id AND l.Amount_Paid IS NULL) appliances ,"
					+ "a.appliance_status"
					+ "FROM eligibility e INNER JOIN appliance a ON e.appliance_id = a.appliance_id "
					+ "INNER JOIN customer cs ON e.customer_id = cs.customer_id "
					+ "LEFT JOIN city c ON cs.customer_city = c.city_id "
					+ "INNER JOIN salary sal ON cs.customer_monthly_income = sal.salary_id "
					+ "LEFT JOIN city_district cd " + "ON c.city_id = cd.city_id "
					+ "LEFT JOIN district d ON cd.district_id = d.district_id "
					+ "INNER JOIN sold_to st ON st.appliance_id = a.appliance_id "
					+ "INNER JOIN salesman s ON e.salesman_id = s.salesman_id "
					+ "INNER JOIN payment_loan pl ON pl.soldto_id = st.sold_to_id "
					+ "INNER JOIN loan_payments lp ON pl.loan_id = lp.loan_id "
					+ "WHERE lp.Amount_Paid IS NULL OR pl.remaining_balance = 0 " + condition + " order by " + column
					+ " " + dir + " " + " limit " + start + ", " + length);
			System.out.println("Advanced Filter getFilteredCustomers() : " + ps.getPreparedSql());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id") + "");
				map.put("DT_RowId", "row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerCnic", resultSet.getString("customer_cnic"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber", resultSet.getString("customer_phone"));
				map.put("monthlyIncome", resultSet.getString("salary_range"));
				map.put("customerStatus", resultSet.getInt("cs.status") + "");
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status") + "");
				listMap.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			System.out.println("AdvancedFilterBAL.getFilteredCustomers()");
			e.printStackTrace();
		}
		// System.out.println("AdvancedFilterBAL.getFilteredCustomers() :
		// Customer List Size : "+customerList.size());
		return listMap;
	}

	public int countFilteredCustomers(HashMap<String, String> whereClause) {
		System.out.println("AdvancedFilterBAL.countFilteredCustomers()");
		int count = 0;

		String condition = "";

		for (String key : whereClause.keySet()) {
			String t = key;
			String u = whereClause.get(key);
			if (!u.isEmpty()) {
				if (condition.isEmpty()) {
					condition = "where ";
				} else if (!condition.isEmpty()) {
					condition += "\n AND ";
				}
				condition += (t + " = " + u);
				// System.out.println(t + " = " + u);
			}

		}

		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT " + "count(*) "
					+ "FROM eligibility e INNER JOIN appliance a ON e.appliance_id = a.appliance_id "
					+ "INNER JOIN customer cs ON e.customer_id = cs.customer_id "
					+ "LEFT JOIN city c ON cs.customer_city = c.city_id "
					+ "INNER JOIN salary sal ON cs.customer_monthly_income = sal.salary_id "
					+ "LEFT JOIN city_district cd " + "ON c.city_id = cd.city_id "
					+ "LEFT JOIN district d ON cd.district_id = d.district_id "
					+ "INNER JOIN sold_to st ON st.appliance_id = a.appliance_id "
					+ "INNER JOIN salesman s ON e.salesman_id = s.salesman_id "
					+ "INNER JOIN payment_loan pl ON pl.soldto_id = st.sold_to_id "
					+ "INNER JOIN loan_payments lp ON pl.loan_id = lp.loan_id "
					+ "WHERE lp.Amount_Paid IS NULL OR pl.remaining_balance = 0" + condition
					+ " order by cs.customer_name ");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			System.out.println("Filtered " + count);

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
