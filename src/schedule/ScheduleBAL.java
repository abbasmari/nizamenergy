package schedule;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import connection.Connect;

public class ScheduleBAL {

	private final static Logger logger = Logger.getLogger(ScheduleBAL.class);

	public static ArrayList<HashMap<String, String>> getRemaining() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		String query = "SELECT e.`eligibility_id` ,c.customer_name, DATEDIFF(CURDATE(), e.date) AS remaining ,"
				+ " a.`appliance_id` "
				+ " FROM eligibility e"
				+ " INNER JOIN customer c ON c.customer_id = e.customer_id"
				+ " INNER JOIN `appliance` a ON e.`appliance_id` = a.`appliance_id`"
				+ " WHERE a.status = 7;";
		try (Connection con = connection.Connect.getConnection();) {
			Statement s = null;
			ResultSet rs = null;
			HashMap<String, String> map = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customer_name", rs.getString("c.customer_name"));
				map.put("remaining", "" + rs.getInt("remaining"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				list.add(map);
			}

		} catch (Exception e) {
			logger.error(e);
			e.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNotInterstedCustomers() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection con = connection.Connect.getConnection();) {
			Statement s = null;
			ResultSet rs = null;

			CallableStatement prepareCall = con
					.prepareCall("{CALL get_notInterested_customers()}");
			rs = prepareCall.executeQuery();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("customer_name", "" + rs.getString("c.customer_name"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("appliance_name", "" + rs.getString("a.appliance_name"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("last_status", "" + rs.getInt("e.last_status"));

				list.add(map);
			}

		} catch (Exception ex) {
			logger.error(ex);
			ex.getStackTrace();
		}
		return list;
	}

	public static void updateStatus(String applianceId, int lastStatus) {

		Statement s = null;
		String query = "UPDATE eligibility e SET e.status = 3,  e.last_status = "
				+ lastStatus + " WHERE e.`appliance_id` = " + applianceId;
		System.out.println(query);
		int row = 0;
		try (Connection con = connection.Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			con.close();
		} catch (Exception ex) {
			logger.error(ex);
			ex.getMessage();
		}

	}

	public static ArrayList<HashMap<String, String>> getSuspended() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		String query = "SELECT c.`customer_id`, c.`customer_name`,a.`appliance_id`, "
				+ "a.`appliance_name`, e.`eligibility_id`, e.`status` "
				+ " FROM customer c "
				+ " INNER JOIN eligibility e ON e.`customer_id` = c.`customer_id` "
				+ " INNER JOIN `appliance` a ON a.`appliance_id` = e.`appliance_id` "
				+ " WHERE e.`status` = 3 ";
		try (Connection con = connection.Connect.getConnection();) {
			Statement s = null;
			ResultSet rs = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("customer_name", "" + rs.getString("c.customer_name"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("appliance_name", "" + rs.getString("a.appliance_name"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("status", "" + rs.getInt("e.status"));

				list.add(map);
			}

		} catch (Exception ex) {
			logger.error(ex);
			ex.getStackTrace();
		}
		return list;
	}

	public static void deleteSoldToByApplianceId(String applianceId) {

		Statement s = null;
		String query = "DELETE FROM sold_to WHERE appliance_id = "
				+ applianceId;
		int row = 0;
		try (Connection con = connection.Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				// System.out.println("Data is deleted");
			} else {
				// System.out.println("Data is not deleted");
			}
			con.close();
		} catch (Exception ex) {
			logger.error(ex);
			ex.getMessage();
		}

	}

	public static void deleteApplianceByApplianceId(String applianceId) {

		Statement s = null;
		String query = "UPDATE appliance SET imei_number = null, appliance_GSMno = null, STATUS = 0 WHERE appliance_id = "
				+ applianceId;
		int row = 0;
		try (Connection con = connection.Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			// System.err.println("appliance : "+row);
			if (row > 0) {
				// System.out.println("Data is deleted");
			} else {
				// System.out.println("Data is not deleted");
			}
			s.close();
			con.close();
		} catch (Exception ex) {
			logger.error(ex);
			ex.getMessage();
		}

	}

	public static void deletePaymentLoanBySoldToId(String soldto_id) {

		Statement s = null;
		String query = "DELETE FROM payment_loan WHERE soldto_id = "
				+ soldto_id;
		int row = 0;
		try (Connection con = connection.Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				// System.out.println("Data is deleted");
			} else {
				// System.out.println("Data is not deleted");
			}
			con.close();
		} catch (Exception ex) {
			logger.error(ex);
			ex.getMessage();
		}

	}

	public static ArrayList<HashMap<String, String>> getVerifiedCustomers() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection con = connection.Connect.getConnection();) {
			Statement s = null;
			ResultSet rs = null;

			CallableStatement prepareCall = con
					.prepareCall("{CALL get_verified_customers()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customer_name", rs.getString("c.customer_name"));
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("remaining", "" + rs.getInt("remaining"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				list.add(map);
			}

		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAcceptedCustomers() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection con = connection.Connect.getConnection();) {
			Statement s = null;
			ResultSet rs = null;

			CallableStatement prepareCall = con
					.prepareCall("{CALL get_accepted_customers()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customer_name", rs.getString("c.customer_name"));
				map.put("remaining", "" + rs.getInt("remaining"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("customer_id", rs.getInt("c.customer_id") + "");

				list.add(map);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoNotInterstedCustomers(
			int userId) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection con = connection.Connect.getConnection();) {
			Statement s = null;
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{call get_do_notInterested_customers(?)}");
			prepareCall.setInt(1, userId);
			rs = prepareCall.executeQuery();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("customer_name", "" + rs.getString("c.customer_name"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("appliance_name", "" + rs.getString("a.appliance_name"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("last_status", "" + rs.getInt("e.last_status"));

				list.add(map);
			}

		} catch (Exception ex) {
			logger.error(ex);
			ex.getStackTrace();
		}
		return list;
	}

	public static void revokeConsumerNum(int appliance_id, int csId)
			throws SQLException {
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE customer SET customer.`consumer_number`=NULL WHERE customer.`customer_id`="
							+ csId);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE appliance SET appliance.imei_number=NULL , appliance.appliance_GSMno=NULL , appliance.`status`=1 WHERE appliance.`appliance_id`="
							+ appliance_id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		updateStatus("691", 1);
	}
}
