package schedule;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import connection.Connect;

public class ReminderUtility {

	private final static Logger logger = Logger
			.getLogger(ReminderUtility.class);

	public static ArrayList<HashMap<String, String>> sendDueDateReminders() {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = connection.Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_duedate_reminders()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map = new HashMap<>();
				int days = resultSet.getInt("days");
				int downpayment_days = resultSet.getInt("downpayment_days");
				String customerName = resultSet.getString("customer_name");
				String customerPhone = resultSet.getString("customer_phone");
				String applianceName = resultSet.getString("appliance_name");
				int monthlyInstallment = resultSet
						.getInt("installment_amount_month");
				String gsmNumber = resultSet.getString("appliance_GSMno");
				Date duedate = resultSet.getDate("due_date");
				String imei_number = resultSet.getString("imei_number");
				String NdName = resultSet.getString("salesman_name");
				String foName = resultSet.getString("fo_name");
				String doName = resultSet.getString("user_name");
				Date date = addDays(duedate, 10);
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				map.put("days", days + "");
				map.put("downpayment_days", downpayment_days + "");
				map.put("customerName", customerName + "");
				map.put("customerPhone", customerPhone + "");
				map.put("applianceName", applianceName + "");
				map.put("monthlyInstallment", monthlyInstallment + "");
				map.put("gsmNumber", gsmNumber + "");
				map.put("duedate", duedate + "");
				map.put("imei_number", imei_number + "");
				map.put("afterTenDays", df.format(date) + "");
				map.put("NdName", NdName + "");
				map.put("foName", foName + "");
				map.put("doName", doName + "");
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> sendDueDateRemindersDoWise(
			int doId) {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = connection.Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_do_duedate_reminders(?)}");
			prepareCall.setInt(1, doId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map = new HashMap<>();
				int days = resultSet.getInt("days");
				String customerName = resultSet.getString("customer_name");
				int monthlyInstallment = resultSet
						.getInt("installment_amount_month");
				Date duedate = resultSet.getDate("due_date");
				Date date = addDays(duedate, 10);
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				map.put("days", days + "");
				map.put("customerName", customerName + "");
				map.put("duedate", duedate + "");
				map.put("afterTenDays", df.format(date) + "");
				map.put("monthlyInstallment", monthlyInstallment + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("ndName", resultSet.getString("salesman_name") + "");
				list.add(map);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> sendDueDateRemindersFoWise(
			int foId) {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = connection.Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_fo_duedate_reminders(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map = new HashMap<>();
				int days = resultSet.getInt("days");
				int downpayment_days = resultSet.getInt("downpayment_days");
				String customerName = resultSet.getString("customer_name");
				String customerPhone = resultSet.getString("customer_phone");
				String applianceName = resultSet.getString("appliance_name");
				int monthlyInstallment = resultSet
						.getInt("installment_amount_month");
				String gsmNumber = resultSet.getString("appliance_GSMno");
				Date duedate = resultSet.getDate("due_date");
				String imei_number = resultSet.getString("imei_number");
				Date date = addDays(duedate, 10);
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				map.put("days", days + "");
				map.put("downpayment_days", downpayment_days + "");
				map.put("customerName", customerName + "");
				map.put("customerPhone", customerPhone + "");
				map.put("applianceName", applianceName + "");
				map.put("monthlyInstallment", monthlyInstallment + "");
				map.put("gsmNumber", gsmNumber + "");
				map.put("duedate", duedate + "");
				map.put("imei_number", imei_number + "");
				map.put("afterTenDays", df.format(date) + "");
				list.add(map);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return list;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	// public static int revokeCustomer(int applianceId) {
	//
	// Statement s = null;
	// String query =
	// "UPDATE eligibility e SET e.status = 1, e.date = curdate(), e.accepted_date = curdate() WHERE e.`appliance_id` = "
	// + applianceId;
	// int row = 0;
	// try (Connection con = connection.Connect.getConnection();) {
	// s = con.createStatement();
	// row = s.executeUpdate(query);
	// if (row > 0) {
	// System.out.println("Data is updated");
	// } else {
	// System.out.println("Data is not updated");
	// }
	// con.close();
	// } catch (Exception ex) {
	// ex.getMessage();
	// }
	// return 0;
	// }

	public static ArrayList<HashMap<String, String>> getUserIdList() {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery("SELECT u.user_id,user_email FROM sold_to sld JOIN `salesman` sl ON sl.salesman_id = sld.`salesman_id` "
					+ "JOIN `field_officer` fo ON fo.`fo_id` = sl.`fo_id` JOIN `nizamdb_tester`.user u ON u.`user_id` = fo.`do_id`"
					+ " GROUP BY u.`user_id`");
			while (rs.next()) {
				map = new HashMap<>();
				map.put("userId", rs.getInt("user_id") + "");
				map.put("userEmail", rs.getString("user_email") + "");
				list.add(map);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoList() {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery("SELECT fo.fo_id, fo.`fo_name` FROM sold_to sld JOIN `salesman` sl ON sl.salesman_id = sld.`salesman_id` "
					+ "JOIN `field_officer` fo ON fo.`fo_id` = sl.`fo_id` GROUP BY fo.`fo_id`");
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foId", rs.getInt("fo_id") + "");
				map.put("foPhoneNo", rs.getInt("fo_priamary_phone") + "");
				list.add(map);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static int revokeCustomer(int applianceId) {
		int status = 0;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery("SELECT eligibility.`last_status` FROM eligibility WHERE eligibility.`appliance_id`="
					+ applianceId);
			while (rs.next()) {
				status = rs.getInt("last_status");
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		if (status == 7) {
			Statement s = null;
			String query = "UPDATE eligibility e SET e.status = 7, e.date = curdate(), e.accepted_date = curdate() WHERE e.`appliance_id` = "
					+ applianceId;
			int row = 0;
			try {
				setBackToRTA(applianceId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				ex.getMessage();
			}

		} else {
			Statement s = null;
			String query = "UPDATE eligibility e SET e.status = 1, e.date = curdate(), e.accepted_date = curdate() WHERE e.`appliance_id` = "
					+ applianceId;
			int row = 0;
			try {
				setBackToRTA(applianceId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try (Connection con = Connect.getConnection();) {
				s = con.createStatement();
				row = s.executeUpdate(query);
				if (row > 0) {
					System.out.println("Data is updated");
				} else {
					System.out.println("Data is not updated");
				}
				con.close();
			} catch (Exception ex) {
				ex.getMessage();
			}

		}
		return 0;
	}

	public static void setBackToRTA(int appliance_id) throws SQLException {
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE appliance SET appliance.`status`=1 WHERE appliance.`appliance_id`="
							+ appliance_id);
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static ArrayList<HashMap<String, String>> getHealth() {

		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery("SELECT ap.appliance_id , HealthStatus(ap.appliance_id)AS number "
					+ " FROM appliance ap INNER JOIN sold_to sld on sld.appliance_id = ap.appliance_id "
					+ " WHERE (ap.appliance_name!='60 W' AND ap.appliance_name!='7 W' AND ap.appliance_name!='P-60') AND (ap.status = 6 || ap.status = 7) "
					+ " GROUP BY ap.appliance_id;");
			while (rs.next()) {
				map = new HashMap<>();
				map.put("appId", rs.getInt("appliance_id") + "");
				map.put("number", rs.getInt("number") + "");
				list.add(map);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static void main(String arg[]) {
		System.out.println(getHealth());
	}
}
