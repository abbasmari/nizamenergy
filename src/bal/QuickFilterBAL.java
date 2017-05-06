package bal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import connection.Connect;

public class QuickFilterBAL {
	final static Logger logger = Logger.getLogger(QuickFilterBAL.class);

	public int countQuickedFilteredCustomers(int applianceStatus,
			String[] customerStatus) {
		int count = 0;
		StringBuilder condition = new StringBuilder();
		if (applianceStatus != -1 || customerStatus.length > 0) {
			condition.append(" WHERE ");
		}

		if (applianceStatus != -1) {
			condition.append("a.appliance_status = " + applianceStatus);
		}
		if (applianceStatus != -1 && customerStatus.length > 0) {
			condition.append(" AND ");
		}

		if (customerStatus.length > 0) {
			condition.append(" e.status IN ");
			for (int i = 0; i < customerStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(customerStatus[i]);

				if (i >= 0 && i < (customerStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (customerStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		condition.append("GROUP BY cs.customer_phone");

		StringBuilder query = new StringBuilder("SELECT " + " COUNT(*) "
				+ " FROM eligibility e " + " INNER JOIN appliance a "
				+ " ON e.appliance_id = a.appliance_id "
				+ " INNER JOIN salesman s "
				+ " ON e.salesman_id = s.salesman_id "
				+ " INNER JOIN customer cs "
				+ " ON e.customer_id = cs.customer_id " + " LEFT JOIN city c "
				+ " ON cs.customer_city = c.city_id "
				+ " INNER JOIN salary sal "
				+ " ON cs.customer_monthly_income = sal.salary_id "
				+ " LEFT JOIN city_district cd "
				+ " ON c.city_id = cd.city_id " + " LEFT JOIN district d "
				+ " ON cd.district_id = d.district_id ");
		query.append(condition);
		try (Connection connection = Connect.getConnection();) {
			System.out.println(query.toString());
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query.toString());
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	public ArrayList<HashMap<String, String>> getQuickedFilteredCustomers(
			int applianceStatus, String[] customerStatus, int columnIndex,
			String dir, int start, int range) {
		System.out.println("QuickFilterBAL.getQuickedFilteredCustomers()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		StringBuilder condition = new StringBuilder();
		if (applianceStatus != -1
				|| (customerStatus != null && customerStatus.length > 0)) {
			condition.append(" WHERE ");
		}

		if (applianceStatus != -1) {
			condition.append("a.appliance_status = " + applianceStatus);
		}

		if (applianceStatus != -1
				&& (customerStatus != null && customerStatus.length > 0)) {
			condition.append(" AND ");
		}
		if ((customerStatus != null && customerStatus.length > 0)) {
			condition.append(" e.status IN ");
			for (int i = 0; i < customerStatus.length; i++) {
				System.out.println(i + " Eligibility Status "
						+ customerStatus[i]);
				if (i == 0) {
					condition.append(" ( ");
				}
				if (customerStatus[i].equals("2")) {
					customerStatus[i] = "2, 4";
				}
				if (customerStatus[i].equals("1")) {
					customerStatus[i] = "1, 6";
				}
				condition.append(customerStatus[i]);

				if (i >= 0 && i < (customerStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (customerStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}

		// set column name
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

		// order by
		condition.append("GROUP BY cs.customer_phone ORDER BY " + column + " "
				+ dir);

		// limit
		condition.append(" LIMIT " + start + ", " + range);

		StringBuilder query = new StringBuilder(
				"SELECT e.eligibility_id, "
						+ "e.status,"
						+ "cs.customer_id,"
						+ "	cs.customer_name,"
						+ "	cs.customer_phone,"
						+ "	cs.customer_cnic,"
						+ "CONCAT(c.city_name, ', ', d.district_name) AS 'district_name',"
						+ "cs.status,"
						+ "sal.salary_range,"
						+ "a.appliance_id,"
						+ "(SELECT COUNT(DISTINCT pl.loan_id) FROM sold_to sld JOIN payment_loan pl ON pl.soldto_id = sld.sold_to_id "
						+ "JOIN loan_payments l ON l.loan_id = pl.loan_id WHERE sld.customer_id = cs.customer_id AND l.Amount_Paid IS NULL) appliances ,"
						+ "a.appliance_status"
						+ "FROM eligibility e INNER JOIN appliance a ON e.appliance_id = a.appliance_id "
						+ "INNER JOIN customer cs ON e.customer_id = cs.customer_id "
						+ "LEFT JOIN city c ON cs.customer_city = c.city_id "
						+ "INNER JOIN salary sal ON cs.customer_monthly_income = sal.salary_id "
						+ "LEFT JOIN city_district cd "
						+ "ON c.city_id = cd.city_id "
						+ "LEFT JOIN district d ON cd.district_id = d.district_id "
						+ "INNER JOIN sold_to st ON st.appliance_id = a.appliance_id "
						+ "INNER JOIN salesman s ON e.salesman_id = s.salesman_id "
						+ "INNER JOIN payment_loan pl ON pl.soldto_id = st.sold_to_id "
						+ "INNER JOIN loan_payments lp ON pl.loan_id = lp.loan_id "
						+ "WHERE lp.Amount_Paid IS NULL OR pl.remaining_balance = 0");

		query.append(condition);
		try (Connection connection = Connect.getConnection()) {
			System.out.println(query.toString());
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query.toString());
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id")
						+ "");
				map.put("DT_RowId",
						"row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerCnic", resultSet.getString("customer_cnic"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber",
						resultSet.getString("customer_phone"));
				map.put("monthlyIncome", resultSet.getString("salary_range"));
				map.put("customerStatus", resultSet.getInt("cs.status") + "");
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maps;
	}

	public ArrayList<HashMap<String, String>> getQuickedFilteredAppliances(
			int applianceStatus, String[] soldStatus, String[] healthStatus,
			int columnIndex, String dir, int start, int range, String search) {
		System.out.println("QuickFilterBAL.getQuickedFilteredAppliances()");
		ArrayList<HashMap<String, String>> maps = new ArrayList();
		StringBuilder condition = new StringBuilder();
		condition.append(" WHERE ((e.status !=3 && e.status !=2)) ");

		// if appliance filter is enabled
		if (applianceStatus != -1) {
			condition.append(" AND a.appliance_status = " + applianceStatus);
		}

		if ((soldStatus != null && soldStatus.length > 0)) {
			condition.append(" AND a.status IN ");
			for (int i = 0; i < soldStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(soldStatus[i]);

				if (i >= 0 && i < (soldStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (soldStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if ((healthStatus != null && healthStatus.length > 0)) {
			condition.append(" AND a.health_status IN ");
			for (int i = 0; i < healthStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(healthStatus[i]);

				if (i >= 0 && i < (healthStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (healthStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if (!search.equals("")) {
			condition.append(" AND (a.appliance_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " a.imei_number LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " d.district_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " c.customer_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " s.salesman_name LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " fo.fo_name LIKE CONCAT(" + "'%','" + search
					+ "','%'))  ");
		}
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
		case 7:
			column = "fo.fo_name";
			break;
		default:
			column = "cs.customer_name";
			break;
		}

		// order by
		condition.append(" ORDER BY " + column + " " + dir);

		// limit
		condition.append(" LIMIT " + start + ", " + range);

		// start connection and run query and then get result

		String query = "SELECT "
				+ "a.appliance_id, "
				+ "a.appliance_GSMno, "
				+ "CONCAT(a.appliance_name, ', ', (SELECT IF((cpl.`payment_loan_id` = pl.loan_id), GROUP_CONCAT(cpl.`appliance_name` SEPARATOR ', '), '') " 
				+ "FROM custom_payment_loan cpl "  
				+ "WHERE cpl.`payment_loan_id` = pl.loan_id  AND cpl.`tracking_status` != 1))AS appliance_name, "
				+ "a.appliance_product_no, "
				+ "a.appliance_price, "
				+ "a.appliance_status, "
				+ "c.customer_name, "
				+ "d.district_name, "
				+ "s.salesman_name, "
				+ "a.status, "
				+ "s.salesman_id, "
				+ "c.customer_phone, isDefaulted(a.appliance_id) AS defaulted, a.imei_number, "
				+ "fo.fo_name ,a.is_alive,"
				+ "DATEDIFF(CURDATE(),CAST(a.dead_since AS DATE))AS deadSince ,a.dead_since ,a.health_status "
				+ "FROM eligibility e  " + "INNER JOIN customer c  "
				+ "ON c.customer_id = e.customer_id  "
				+ "INNER JOIN appliance a  "
				+ "ON a.appliance_id = e.appliance_id "
				+ "INNER JOIN salesman s  "
				+ "ON s.salesman_id = e.salesman_id "
				+ "INNER JOIN district d "
				+ "ON d.district_id = s.salesman_district "
				+ "INNER JOIN field_officer fo ON fo.fo_id=s.fo_id "
				+ "LEFT JOIN `sold_to` st ON st.`appliance_id` = a.`appliance_id` "
				+ "LEFT JOIN `payment_loan` pl ON pl.`soldto_id` = st.`sold_to_id` "
				/*+ "LEFT JOIN `custom_payment_loan` cpl ON cpl.`payment_loan_id` = pl.`loan_id`"*/;
		System.err.println(query);
		StringBuilder queryBuilder = new StringBuilder(query);
		queryBuilder.append(condition);
		System.err.println(queryBuilder.toString());
		try (Connection connection = Connect.getConnection()) {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryBuilder
					.toString());
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
				map.put("foName", resultSet.getString("fo.fo_name"));
				map.put("islive", resultSet.getInt("is_alive") + "");
				map.put("deadSince", resultSet.getInt("deadSince") + "");
				map.put("deadDate", resultSet.getString("dead_since") + "");
				map.put("healthStatus", resultSet.getInt("health_status") + "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static int countQuickedFilteredAppliances(int applianceStatus,
			String[] soldStatus, String[] healthStatus, String search) {
		int count = 0;
		StringBuilder condition = new StringBuilder();
		// add 'where clause' if appliance status or customer status is filtered
		// if (applianceStatus != -1
		// || (soldStatus != null && soldStatus.length > 0)) {
		condition.append(" WHERE (e.status !=3 && e.status !=2) ");
		// }
		//
		// if appliance filter is enabled
		if (applianceStatus != -1) {
			condition.append("AND a.appliance_status = " + applianceStatus);
		}

		// if eligibility of customer status is filter
		if ((soldStatus != null && soldStatus.length > 0)) {
			condition.append(" AND a.status IN ");
			for (int i = 0; i < soldStatus.length; i++) {
				// System.out.println(i+" Eligibility Status "+soldStatus[i]);
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(soldStatus[i]);

				if (i >= 0 && i < (soldStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (soldStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}

		if ((healthStatus != null && healthStatus.length > 0)) {
			condition.append(" AND a.health_status IN ");
			for (int i = 0; i < healthStatus.length; i++) {
				// System.out.println(i+" Eligibility Status "+soldStatus[i]);
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(healthStatus[i]);

				if (i >= 0 && i < (healthStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (healthStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if (!search.equals("")) {
			condition.append(" AND (a.appliance_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " a.appliance_gsmno LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " d.district_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " c.customer_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " s.salesman_name LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " fo.fo_name LIKE CONCAT(" + "'%','" + search
					+ "','%'))  ");
		}

		String query = "SELECT " + "COUNT(*) " + "FROM eligibility e  "
				+ "INNER JOIN customer c  "
				+ "ON c.customer_id = e.customer_id  "
				+ "INNER JOIN appliance a  "
				+ "ON a.appliance_id = e.appliance_id "
				+ "INNER JOIN salesman s  "
				+ "ON s.salesman_id = e.salesman_id "
				+ "INNER JOIN district d "
				+ "ON d.district_id = s.salesman_district "
				+ "INNER JOIN field_officer fo ON fo.fo_id=s.fo_id ";
		StringBuilder queryBuilder = new StringBuilder(query);
		queryBuilder.append(condition);
		try (Connection connection = Connect.getConnection()) {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryBuilder
					.toString());
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<HashMap<String, String>> getQuickedFilteredApplianceswithSearch(
			int applianceStatus, String[] soldStatus, String[] healthStatus,
			int columnIndex, String dir, int start, int range, String search) {
		System.out.println("QuickFilterBAL.getQuickedFilteredAppliances()");
		ArrayList<HashMap<String, String>> maps = new ArrayList();
		StringBuilder condition = new StringBuilder();
		// add 'where clause' if appliance status or customer status is filtered
		if (applianceStatus != -1
				|| (soldStatus != null && soldStatus.length > 0)) {
			condition.append(" WHERE ");
		}

		// if appliance filter is enabled
		if (applianceStatus != -1 && applianceStatus != 0) {
			condition.append(" a.appliance_status = 1  ");
		} else {
			condition.append(" a.appliance_status = 0  ");
		}

		// if eligibility of customer status is filter
		if ((soldStatus != null && soldStatus.length > 0)) {
			// condition.append(" a.status = 0 ");
			for (int i = 0; i < soldStatus.length; i++) {
				System.out.println(i + " Eligibility Status " + soldStatus[i]);
				if (soldStatus[i].equals("0")) {
					condition.append(" AND a.status= 0 ");
				} else if (soldStatus[i].equals("1")) {
					condition.append(" AND a.status= 1 ");
				} else if (soldStatus[i].equals("5")) {
					condition.append(" AND a.status= 5 ");
				} else if (soldStatus[i].equals("6")) {
					condition.append(" AND a.status= 6 ");
				}
			}
		}
		if ((healthStatus != null && healthStatus.length > 0)) {
			condition.append(" AND a.health_status IN ");
			for (int i = 0; i < healthStatus.length; i++) {
				// System.out.println(i+" Eligibility Status "+soldStatus[i]);
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(healthStatus[i]);

				if (i >= 0 && i < (healthStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (healthStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if (!search.equals("")) {
			condition.append(" AND  (a.appliance_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " a.appliance_gsmno LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " d.district_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " c.customer_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " s.salesman_name LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " fo.fo_name LIKE CONCAT(" + "'%','" + search
					+ "','%'))  ");
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
		case 7:
			column = "fo.fo_name";
			break;
		default:
			column = "cs.customer_name";
			break;
		}
		// order by
		condition.append(" ORDER BY " + column + " " + dir);

		// limit
		condition.append(" LIMIT " + start + ", " + range);
		String query = "SELECT a.appliance_id, "
				+ "a.appliance_GSMno, "
				+ "a.appliance_name, "
				+ "a.appliance_product_no, "
				+ "a.appliance_price, "
				+ "a.appliance_status, "
				+ "c.customer_name, "
				+ "d.district_name, "
				+ "s.salesman_name, "
				+ "a.status, "
				+ "e.date,"
				+ "e.accepted_date,"
				+ "e.varified_date,"
				+ "a.imei_assign_date,"
				+ "c.customer_cnic, "
				+ "s.salesman_id, "
				+ "c.customer_phone, isDefaulted(a.appliance_id) AS defaulted, a.imei_number, "
				+ "fo.fo_name,a.health_status " + "FROM eligibility e  "
				+ "INNER JOIN customer c  "
				+ "ON c.customer_id = e.customer_id  "
				+ "INNER JOIN appliance a  "
				+ "ON a.appliance_id = e.appliance_id "
				+ "INNER JOIN salesman s  "
				+ "ON s.salesman_id = e.salesman_id "
				+ "INNER JOIN district d "
				+ "ON d.district_id = s.salesman_district "
				+ "INNER JOIN field_officer fo ON fo.fo_id=s.fo_id ";

		StringBuilder queryBuilder = new StringBuilder(query);
		queryBuilder.append(condition);
		System.err.println(queryBuilder.toString());
		try (Connection connection = Connect.getConnection()) {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryBuilder
					.toString());
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("appliancePrice",
						resultSet.getString("appliance_price"));
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerCnic", resultSet.getString("customer_cnic"));
				map.put("applianceGSM", resultSet.getString("appliance_GSMno"));
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("salesmanDistrict",
						resultSet.getString("district_name"));
				map.put("customerPhone", resultSet.getString("customer_phone"));
				map.put("salesmanName", resultSet.getString("salesman_name"));
				map.put("applianceStatusActive",
						resultSet.getInt("appliance_status") + "");
				map.put("applianceStatus", resultSet.getInt("a.status") + "");
				map.put("imeiNumber", resultSet.getString("imei_number"));
				map.put("foName", resultSet.getString("fo.fo_name"));
				map.put("pendingDate", "" + resultSet.getDate("date"));
				map.put("RTADAte", "" + resultSet.getDate("accepted_date"));
				map.put("ADPDate", "" + resultSet.getDate("imei_assign_date"));
				// map.put("installedDate",""+resultSet.getDate("product_handover"));
				// map.put("returnedDate",""+resultSet.getDate("returned_date"));
				map.put("isDefaulted", resultSet.getInt("defaulted") + "");
				map.put("healthStatus", resultSet.getInt("health_status") + "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maps;
	}

	public static void main(String[] args) {

	}

}
