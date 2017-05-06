package bal;

import static bal.UserBAL.st;
import bean.Customer;
import bean.GetDateBean;
import bean.Message;
import bean.MessageBean;
import bean.PaymentDataBean;
import bean.Suggestion;
import connection.Connect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class StatusUpdateBAL {

	final static Logger logger = Logger.getLogger(StatusUpdateBAL.class);

	public static int updateStatusOfAppliance(int id, int status) {

		String query = "Update sold_to SET appliance_status=" + status
				+ " WHERE appliance_id=" + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {

			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int getSoldIdById(int applianceId) {

		int soldId = 0;
		String query = "Select sold_to_id from sold_to WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				soldId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return soldId;
	}

	public static int update(int loanId, String transaction, double amount) {

		System.out.print(loanId);
		String query = "UPDATE customer_installments SET paid_date= CURRENT_TIMESTAMP, transaction_no="
				+ Integer.parseInt(transaction)
				+ ", recent_payed="
				+ amount
				+ " WHERE \n"
				+ " CURRENT_TIMESTAMP <= DATE_ADD(due_date, INTERVAL 30 DAY) AND  \n"
				+ " CURRENT_TIMESTAMP > DATE_ADD(due_date, INTERVAL -6 DAY) AND paid_date IS NULL AND payment_loan_id = "
				+ loanId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int getdoIdFromApplianceID(int salesManId) {

		int doID = 0;
		String query = "select do_id from do_salesman where salesman_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, salesManId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				doID = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return doID;
	}

	public static int getSalesManIdByAppId(int appId) {

		int salesmanId = 0;
		String query = "Select salesman_id from sold_to WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, appId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				salesmanId = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return salesmanId;
	}

	public static String getDistrictName(int id) {
		String district = "";

		String query = "SELECT district_name FROM district d JOIN user u ON u.user_district = d.district_id WHERE u.user_id = ?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				district = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return district;
	}

	public static Date getTermainedAt(int id) {
		Date date = null;

		String query = "SELECT MAX(due_date) FROM customer_installments WHERE payment_loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				date = rs.getDate(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return date;
	}

	public static int updateAlertMessage(int id) {

		String query = "Update alert_messages SET status=" + 1
				+ " WHERE alert_message_id= " + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static String getHandoverMessagesAlert(int id) {
		String gsmNo = "", text = "";

		String query = "Select gsm_number, message from alert_messages where alert_message_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gsmNo = rs.getString(1);
				text = rs.getString(2);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		if (!gsmNo.isEmpty() && !text.isEmpty()) {
			gsmNo = gsmNo + ";" + text;
			return gsmNo;
		} else {
			return "null";
		}
	}

	public static int getMiniummIdOfALertMsg() {
		int id = 0;

		String query = "SELECT MIN(alert_message_id) FROM alert_messages WHERE status=0;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return id;
	}

	public static int updateHandoverMessage(int id) {

		String query = "Update customer_message SET status=" + 1
				+ " WHERE message_id= " + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static String getHandoverMessages(int id) {

		String gsmNo = "", text = "";
		String query = "Select gsm_no, text from customer_message where message_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gsmNo = rs.getString(1);
				text = rs.getString(2);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		if (!gsmNo.isEmpty() && !text.isEmpty()) {
			gsmNo = gsmNo + ";" + text;
			return gsmNo;
		} else {
			return "null";
		}
	}

	public static int getMiniummId() {

		int id = 0;
		String query = "SELECT MIN(message_id) FROM customer_message WHERE status=0;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return id;
	}

	public static String getGsmNumber(int applianceId) {
		String gsmNumber = "";

		String query = "Select appliance_GSMno from appliance WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gsmNumber = rs.getString(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return gsmNumber;
	}

	public static int updatePaymentLoan(int id, double remainingAmount,
			int remainingInstallment) {

		String query = "Update payment_loan SET total_installment_remaining ="
				+ remainingInstallment + " ,remaining_balance="
				+ remainingAmount + " WHERE loan_id=" + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int insertMessageForHandover(int customerId, String message,
			String gsm, int type) throws SQLException {

		int rows = 0;
		try (Connection con = Connect.getConnection()) {
			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO customer_message(customer_id, text, gsm_no, status, message_date,type) VALUES ("
								+ customerId
								+ ",'"
								+ message
								+ "','"
								+ gsm
								+ "'," + 0 + ", current_date()," + type + ");");
			}
		}

		return rows;
	}

	public static int getCustomerIdFromApplianceId(int appId) {

		int customerId = 0;
		String query = "Select customer_id from sold_to WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, appId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return customerId;
	}

	public static String getCustomerGsmNumber(int customerId) {
		String gsmNumber = "";

		String query = "Select customer_phone from customer WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gsmNumber = rs.getString(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return gsmNumber;
	}

	public static boolean checkStatus(int alert_id) {

		boolean checkStatus = false;
		String query = "SELECT status FROM alerts WHERE alerts_id = ? AND status=2;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				checkStatus = true;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return checkStatus;
	}

	public static int updateStatusAlert(int alert_id, int status, int h_status,
			int task_status) {

		String query = "UPDATE alerts SET status=" + status + ", h_status="
				+ h_status + ", task_status=" + task_status
				+ " WHERE alerts_id = " + alert_id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int updateStatusElapseTime(int alert_id, String time) {

		String query = "UPDATE alerts SET elapse_time='" + time
				+ "' WHERE alerts_id = " + alert_id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int getApplianceIdFromAlertID(int alertID) {
		int applianceID = 0;

		String query = "Select appliance_id from alerts WHERE alerts_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alertID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceID = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return applianceID;
	}

	public static int getGracePeriod(int loanId) {
		int graceperiod = 0;

		String query = "Select grace_period from payment_loan WHERE loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				graceperiod = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return graceperiod;
	}

	public static int insertMessageForAlert(int alert_id, String message,
			String gsm, int status, int app_id) throws SQLException {

		int rows = 0;
		try (Connection con = Connect.getConnection()) {

			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO alert_messages(alert_id, message, gsm_number, status, date_time,app_id) VALUES ("
								+ alert_id
								+ ",'"
								+ message
								+ "','"
								+ gsm
								+ "',"
								+ 0
								+ ", CURRENT_TIMESTAMP,"
								+ app_id
								+ ");");
			}
		}

		return rows;
	}

	public static int getAlertID(int app_id) {

		// boolean dueDate = false;
		// String name = "";
		int idd = 0;
		// Date d = null;
		// String query = "SELECT due_date FROM customer_installments WHERE
		// payment_loan_id = 55 AND paid_date IS NOT NULL AND paid_date <=
		// DATE_ADD(due_date,INTERVAL 5 DAY) AND paid_date > due_date ;";
		String query = "select alerts_id from alerts where appliance_id=? and status=0;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, app_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				idd = rs.getInt(1);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return idd;
	}

	public static Suggestion get(int customerId) {
		Suggestion bean = null;
		int id, scheme;

		// int customerid, quantity;
		String name = null;
		String query = "SELECT id, customer_id, appName, quantity, scheme FROM suggestion WHERE customer_id= ?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			// int i = 0;
			while (rs.next()) {
				id = rs.getInt(1);
				customerId = rs.getInt(2);
				name = rs.getString(3);
				// quantity = rs.getInt(4);
				scheme = rs.getInt(5);
				bean = new Suggestion();
				bean.setId(id);
				bean.setScheme(scheme);
				bean.setAppName(name);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return bean;
	}

	public static String getAlertMsgFromGsm(String no, int alert_id, int msg_id) {
		String query = "Select message from alert_messages where gsm_number=? and alert_id=? and alert_message_id=?";

		String msg2 = "";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, no);
			stmt.setInt(2, alert_id);
			stmt.setInt(3, msg_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				msg2 = rs.getString(1);
			}

			// System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return msg2;
	}

	public static int getMaxAlertMsgID(int alert_id) {

		String query = "select max(alert_message_id) from alert_messages where alert_id=? and status=1;";
		int id = 0;
		// String msg = "";
		try (Connection con = Connect.getConnection()) {

			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			// System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public static String getTecnicanPhoneNoFromName(String name) {
		String query = "SELECT technican_phone FROM technician WHERE technican_name=?";

		String tname = "";
		try (Connection con = Connect.getConnection()) {

			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				tname = rs.getString(1);
			}

			// System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return tname;
	}

	public static int getTecnicanIdFromPhoneNo(String number) {
		String query = "SELECT technician_id FROM technician WHERE technican_phone=?";
		int id = 0;

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, number);
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			// System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public static String getTickeNo(int alert_id) {
		String ticketNo = "";

		// Date d = null;
		String query = "select ticket_no from alerts where alerts_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ticketNo = rs.getString(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return ticketNo;
	}

	public static String getTechinicanName(int alert_id) {
		String name = "";

		// Date d = null;
		String query = "select technician from alerts where alerts_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return name;
	}

	public static int updateAlert(int alert_id, boolean pannel_lost,
			boolean conn, boolean lid, boolean battery, boolean pannel,
			int taskStatus) {

		Date date1 = new Date();
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Karachi"));
		Date currentDate = calendar.getTime();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));
		df2.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));

		System.out.println("Date and time in Madrid: " + df.format(date1));
		System.out.println("Date and time in Madrid: "
				+ df2.format(currentDate));

		String query = "UPDATE alerts SET pannel_lost =" + pannel_lost
				+ " , location_changed = " + conn + ", lid_open = " + lid
				+ ",battery_low= " + battery + ", temp_high =" + pannel
				+ ", task_status= " + taskStatus + " , alert_time= '"
				+ df.format(date1) + "',is_seen = " + 0 + ",admin_is_seen = "
				+ 0 + " WHERE alerts_id = " + alert_id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;

			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int getStatusOf(int alert_id) {
		// boolean dueDate = false;
		int state = 0;
		// String name = "";
		// Date d = null;

		// String query = "SELECT due_date FROM customer_installments WHERE
		// payment_loan_id = 55 AND paid_date IS NOT NULL AND paid_date <=
		// DATE_ADD(due_date,INTERVAL 5 DAY) AND paid_date > due_date ;";
		String query = "select status from alerts where alerts_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				state = rs.getInt(1);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return state;
	}

	public static boolean getFoundAlertSameOne(int alert_id,
			boolean pannel_lost, boolean lid_open, boolean battery_low,
			boolean location_changed, boolean temp_high) {
		boolean foundd = false;

		String query = "select  if( exists (select pannel_lost,lid_open,battery_low,location_changed,temp_high \n"
				+ "from alerts where alerts_id=? and  ?=1 or ?=1 or ?=1 or ?= 1 or ?=1),TRUE,false) as value; ";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			stmt.setBoolean(2, pannel_lost);
			stmt.setBoolean(3, lid_open);
			stmt.setBoolean(4, battery_low);
			stmt.setBoolean(5, location_changed);
			stmt.setBoolean(6, temp_high);
			rs = stmt.executeQuery();
			while (rs.next()) {
				foundd = rs.getBoolean(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return foundd;
	}

	public static boolean getFoundAlertNotSameButExists(int alert_id) {
		boolean found = false;

		String query = "select max(alerts_id),pannel_lost,lid_open,battery_low,location_changed,temp_high from alerts where alerts_id=? and  pannel_lost=1 or lid_open=1 or battery_low=1 or location_changed=1 or temp_high=1;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				found = true;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return found;
	}

	public static boolean getFoundAlertNotSameAndNotExists(int alert_id,
			boolean pannel_lost, boolean lid_open, boolean battery_low,
			boolean location_changed, boolean temp_high) {
		boolean found = false;
		String query = "select max(alerts_id),pannel_lost,lid_open,battery_low,location_changed,temp_high from alerts where alerts_id=? and  pannel_lost=0 and lid_open=0 and battery_low=0 and location_changed=0 and temp_high=0;";

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				found = true;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return found;
	}

	public static boolean getFoundByStatus(int alerts_id) {
		boolean foundid = false;

		String query = "SELECT appliance_id FROM alerts WHERE alerts_id = ? and h_status=0 and status=0 or status=1;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alerts_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				foundid = true;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return foundid;
	}

	public static boolean getFoundAlertSame(int alert_id, boolean pannel_lost,
			boolean lid_open, boolean battery_low, boolean loation_changed,
			boolean temp_high) {
		boolean found = false;

		String query = "select pannel_lost,lid_open,battery_low,location_changed,temp_high from alerts where alerts_id=? and pannel_lost=? and lid_open=? and battery_low=? and location_changed=? and temp_high=? and status != 3;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alert_id);
			stmt.setBoolean(2, pannel_lost);
			stmt.setBoolean(3, lid_open);
			stmt.setBoolean(4, battery_low);
			stmt.setBoolean(5, loation_changed);
			stmt.setBoolean(6, temp_high);
			rs = stmt.executeQuery();
			while (rs.next()) {
				found = true;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return found;
	}

	public static boolean getFound(int alertID) {
		boolean id = false;

		String query = "SELECT appliance_id FROM alerts WHERE alerts_id = ? and h_status=0 and status=0 or status=1 or status=2;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, alertID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				id = true;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return id;
	}

	public static int getLastAlertsId(int appId) {
		int AlertId = 0;

		String query = "Select MAX(alerts_id) from alerts WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, appId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AlertId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return AlertId;
	}

	public static int getApplianceIdByGsm(String gsmNumber) {
		int applianceId = 0;

		String query = "Select appliance_id from appliance WHERE appliance_GSMno=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, gsmNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return applianceId;
	}

	public static int getCustomerPhoneById(String number) {
		int customerId = 0;

		String query = "Select customer_id from customer WHERE customer_phone = ?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, number);
			rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return customerId;
	}

	public static int insertIntoAdmin(int id, String message, int customerId)
			throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {
			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO domessage(do_id, message,customer_id) VALUES ("
								+ id + ",'" + message + "'," + customerId + ")");
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}
		return rows;
	}

	public static int getEligibilityId(int customerId) {
		int eligibility_id = 0;

		String query = "Select eligibility_id from eligibility WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				eligibility_id = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return eligibility_id;
	}

	public static int getSalesManIdFromEl(int customerId) {
		int salesmanId = 0;

		String query = "Select salesman_id from eligibility WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				salesmanId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return salesmanId;
	}

	public static int getApplianceIdFromEl(int customerId) {
		int applianceId = 0;

		String query = "Select appliance_id from eligibility WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return applianceId;
	}

	public static int getSchemeFromEl(int applianceId) {
		int scheme = 0;

		String query = "Select installment_scheme from eligibility WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				scheme = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return scheme;
	}

	public static int insertIntoSuggestion(int id, String appName, int scheme)
			throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {
			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO suggestion(customer_id, appName, scheme) VALUES ("
								+ id + ",'" + appName + "'," + scheme + ")");
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}
		return rows;
	}

	public static ArrayList<Message> getMessage(int doId, int id) {
		ArrayList<Message> list = new ArrayList<Message>();
		Message bean = null;
		String message;
		int customerId;

		// int Id, doId1;
		String query = "Select id, do_id, message,customer_id from domessage where do_id = "
				+ doId + " AND customer_id = " + id + ";";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				// Id = rs.getInt(1);
				// doId1 = rs.getInt(2);
				message = rs.getString(3);
				customerId = rs.getInt(4);
				bean = new Message();
				bean.setId(id);
				bean.setDoId(doId);
				bean.setMessage(message);
				bean.setCustomerId(customerId);
				list.add(bean);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.println(ex.getMessage());
		}

		return list;
	}

	public static int getUserId(int id) {
		int userId = 0;

		String query = "Select do_id from do_salesman WHERE salesman_id=?;";
		try (Connection connection = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return userId;
	}

	public static int getUserIdByDictrict(int district) {
		int userId = 0;

		String query = "Select user_id from user WHERE user_district=?;";
		try (Connection con = Connect.getConnection()) {

			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, district);
			rs = stmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return userId;
	}

	public static String getUserName(int id) {
		String user = "";

		String query = "Select user_name from user WHERE user_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = rs.getString(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return user;
	}

	public static int updateStatusCustomer(int id, int status) {

		String query = "Update customer SET status=" + status
				+ " WHERE customer_id=" + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int updateStatusAppliance(int appliance_Id,
			int appliance_status, String applianceGsm) {

		PreparedStatement pst = null;
		String query = "UPDATE appliance SET STATUS = ?, appliance_GSMno = ?  WHERE appliance_id = ? ;";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			pst = con.prepareStatement(query);
			pst.setInt(1, appliance_status);
			pst.setString(2, applianceGsm);
			pst.setInt(3, appliance_Id);
			row = pst.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
		return row;
	}

	public static int updateStatusAppliance(int id, int status) {

		String query = "Update appliance SET status=" + status
				+ " WHERE appliance_id=" + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int insertIntoMessage(int customerId, String gsmNo,
			String message) throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {

			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO message(customer_id, salesman_gsm_no, message_text) VALUES ("
								+ customerId
								+ ",'"
								+ gsmNo
								+ "','"
								+ message
								+ "')");
			} catch (Exception e) {
				logger.error("", e);
				System.err.println(e);
				con.close();
			}
		}
		return rows;
	}

	public static Customer getCustomerName(int customerId) {
		Customer customer = null;
		String customerName, phoneNo, cnic, address;

		String query = "Select customer_name, customer_phone, customer_cnic, customer_address from customer WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				phoneNo = rs.getString(2);
				cnic = rs.getString(3);
				address = rs.getString(4);
				customer = new Customer();
				customer.setName(customerName);
				customer.setPhone(phoneNo);
				customer.setAddress(address);
				customer.setCnic(cnic);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return customer;
	}

	public static int getSalesManId(int customerId) {
		int salesmanId = 0;

		String query = "Select salesman_id from eligibility WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				salesmanId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return salesmanId;
	}

	public static int getApplianceId(int customerId) {
		int applianceId = 0;

		String query = "Select appliance_id from eligibility WHERE customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return applianceId;
	}

	public static String getSalesManGsm(int salesmanId) {
		String gsmNo = null;

		String query = "Select salesman_phone_no from salesman WHERE salesman_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, salesmanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gsmNo = rs.getString(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return gsmNo;
	}

	public static double getAppliancePrice(int applianceId) {
		double appliancePrice = 0;

		String query = "Select appliance_price from appliance WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				appliancePrice = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return appliancePrice;
	}

	public static String getApplianceName(int applianceId) {
		String applianceName = null;

		String query = "Select appliance_name from appliance WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceName = rs.getString(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return applianceName;
	}

	public static MessageBean getMessages() {
		MessageBean bean = null;
		String gsmNo, text;

		String query = "Select salesman_gsm_no, message_text from message;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gsmNo = rs.getString(1);
				text = rs.getString(2);
				bean = new MessageBean();
				bean.setGsmNo(gsmNo);
				bean.setMessage(text);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return bean;
	}

	public static int insertIntoPaymentLoan1(int soldToId, double totalAmount,
			double recentPayedAmount, double monthlyAmount, int scheme,
			double remainingAmount, int totalRemainingInstallment,
			double downPayment, boolean downpaymentStatus) throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {
			String query = "INSERT INTO payment_loan(soldto_id,total_amount,total_installments,recent_payed_amount,installment_amount_month,total_installment_remaining,remaining_balance,down_payment,down_payment_status,created_on) VALUES (?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP);";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, soldToId);
			stmt.setDouble(2, totalAmount);
			stmt.setInt(3, scheme);
			stmt.setDouble(4, recentPayedAmount);
			stmt.setDouble(5, monthlyAmount);
			stmt.setInt(6, totalRemainingInstallment);
			stmt.setDouble(7, remainingAmount);
			stmt.setDouble(8, downPayment);
			stmt.setBoolean(9, downpaymentStatus);
			rows = stmt.executeUpdate();
			// con.createStatement()) {
			// rows = statement.executeUpdate("INSERT INTO
			// payment_loan(soldto_id, total_amount, total_installments,
			// recent_payed_amount,installment_amount_month,created_on,total_installment_remaining,remaining_balance)
			// VALUES (" + soldToId + "," + totalAmount + "," + totalInsalment +
			// "," + recentPayedAmount + "," + monthlyAmount +
			// ",CURRENT_TIMESTAMP," + scheme + "," + totalAmount + ")");
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
			e.printStackTrace();
		}
		return rows;
	}

	public static PaymentDataBean getPaymentData(int customerId) {
		PaymentDataBean bean = null;
		// double totalAmount;
		int totalInstalment;
		double downPayment;
		double monthlyAmount;
		// Date createdDate;

		String query = "Select instalment, installment_scheme, down_payment from eligibility where customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				monthlyAmount = rs.getDouble(1);
				totalInstalment = rs.getInt(2);
				downPayment = rs.getDouble(3);
				bean = new PaymentDataBean();
				bean.setPayedAmount(downPayment);
				bean.setTotalInstalment(totalInstalment);
				bean.setMonthlyAmount(monthlyAmount);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return bean;
	}

	public static double getDownPayment(int applianceId) {
		double downPayment = 0;

		String query = "Select down_payment from sold_to WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				downPayment = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return downPayment;
	}

	public static int insertIntoSoldTo(int customer_id, int salesman_id,
			int appliance_id, int appliance_option, boolean payement_option,
			int status, double down_payment) throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {
			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO sold_to(customer_id, salesman_id, appliance_id, sold_date,appliance_option,payement_option, product_handover, status, down_payment) VALUES ("
								+ customer_id
								+ ","
								+ salesman_id
								+ ","
								+ appliance_id
								+ ",CURRENT_TIMESTAMP,"
								+ appliance_option
								+ ","
								+ payement_option
								+ ",CURRENT_TIMESTAMP ,"
								+ status
								+ ","
								+ down_payment + ")");
			} catch (Exception e) {
				logger.error("", e);
				System.err.println(e);
				con.close();
			}
		}

		return rows;
	}

	public static int updateStatusofLoan(int customerId, int status) {

		String query = "Update sold_to SET status=" + status
				+ " WHERE customer_id=" + customerId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int updateStatus(int id, int status) {

		String query = "Update sold_to SET status=" + status
				+ ", product_handover=CURRENT_TIMESTAMP WHERE customer_id="
				+ id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int updateHandoverStatus(int customerId, int status) {
		String query = "Update eligibility SET status=" + status
				+ " WHERE customer_id=" + customerId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int getRemainingInstalment(int id) {
		int installment = 0;

		String query = "SELECT total_installment_remaining FROM payment_loan WHERE soldto_id =?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				installment = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return installment;
	}

	public static Date getDueDateOfInstallment(int soldId) {
		Date dueDate = null;

		String query = "SELECT sold_date FROM sold_to WHERE sold_to_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, soldId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dueDate = rs.getDate(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return dueDate;
	}

	public static Date getPayedDateOfInstallment(int loanId) {
		Date paidDate = null;

		String query = "SELECT paid_date FROM customer_installments WHERE payment_loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				paidDate = rs.getDate(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return paidDate;
	}

	public static int getLoanId(int soldId) {
		int loanId = 0;

		String query = "Select loan_id from payment_loan WHERE soldto_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, soldId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				loanId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return loanId;
	}

	public static int updateApplianceStatus(int applianceId, int status) {

		String query = "Update appliance SET appliance_status=" + status
				+ " WHERE appliance_id=" + applianceId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int getApplianceIdByproductId(String productId) {
		int applianceId = 0;

		String query = "Select appliance_id from appliance WHERE appliance_product_no=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, productId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return applianceId;
	}

	public static double getAppliancePriceById(int id) {
		double price = 0.0;

		String query = "Select appliance_price from appliance WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				price = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return price;
	}

	public static int getSoldId(int applianceId) {
		int soldId = 0;

		String query = "Select sold_to_id from sold_to WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				soldId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return soldId;
	}

	public static int getSalesmanId(String number) {
		int id = 0;

		String query = "Select salesman_id from salesman WHERE salesman_phone_no=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, number);
			rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return id;
	}

	public static int getScheme(int applianceId) {
		int scheme = 0;

		String query = "Select total_installments from payment_loan WHERE soldto_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				scheme = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return scheme;
	}

	public static int insertIntoCustomerInstallment(int loanId,
			double installmentAmount, Date dueDate) throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {
			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO customer_installments(payment_loan_id, installments_amount,paid_date,payment_ref_no,due_date) VALUES ("
								+ loanId
								+ ","
								+ installmentAmount
								+ ",CURRENT_TIMESTAMP, null,'" + dueDate + "')");
			} catch (Exception e) {
				logger.error("", e);
				System.err.println(e);
				con.close();
			}
		}
		return rows;
	}

	public static Date getSoldDate(int soldId) {

		Date dueDate = null;
		String query = "SELECT sold_date FROM sold_to WHERE sold_to_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, soldId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dueDate = rs.getDate(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return dueDate;
	}

	public static double getDownPaymentEligibility(int applianceId) {
		double downPayment = 0;

		String query = "Select down_payment from eligibility WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				downPayment = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return downPayment;
	}

	public static String[] updateDownPayment(int customerId, int applianceId) {
		// int row = 0;
		String arr[] = new String[2];

		// CHANGED BY JETANDER
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call
			CallableStatement prepareCall = connection
					.prepareCall("{call update_appliace_handover(?,?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setInt(2, applianceId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				arr[0] = rs.getString(1);
				arr[1] = rs.getString(2);
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return arr;

	}

	public static int updateApplianceStatus(int applianceId) {

		String query = "Update appliance SET appliance_status=" + 1
				+ " WHERE appliance_id=" + applianceId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int insertIntoInstallment(int loanId, Date due_date,
			double payment) throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {
			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("INSERT INTO customer_installments(payment_loan_id, paid_date, due_date, installments_amount) VALUES ("
								+ loanId
								+ ",CURRENT_TIMESTAMP,'"
								+ due_date
								+ "'," + payment + ")");
			} catch (Exception e) {
				logger.error("", e);
				System.err.println(e);
				con.close();
			}
		}
		return rows;
	}

	public static int updateAmountOfLoan(int id, double amount,
			int remainingInstallment) {

		String query = "Update payment_loan SET total_amount=" + amount
				+ ",total_installment_remaining=" + remainingInstallment
				+ " WHERE soldto_id=" + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int updateInsatllmetPaaidDate(int id, int loanId) {
		String query = "Update customer_installments SET paid_date=CURRENT_TIMESTAMP  WHERE installments_id="
				+ id + " AND payment_loan_id = " + loanId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int updatepaidPayment(int loanId, double pay) {

		String query = "Update payment_loan SET recent_payed_amount=" + pay
				+ " WHERE loan_id= " + loanId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static double getLoanPayment(int loanId) {
		double payment = 0;

		String query = "Select total_amount from payment_loan WHERE loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				payment = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return payment;
	}

	public static int insertintoloan_details(int customerId, int loan_id,
			double total_amount) {
		DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd");
		Calendar activatedate = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		Date current = new Date();
		Calendar currentdate = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		currentdate.setTime(current);
		currentdate.add(Calendar.DAY_OF_MONTH, 1);
		Date activedate = currentdate.getTime();
		String activated_Date = df2.format(activedate);
		// Date sold_date = new Date();
		Date currdate = new Date();
		activatedate.setTime(currdate);
		String currDate = df2.format(currdate);
		int newgrace = 90;
		String querypayment = "INSERT INTO  payments_details(customer_id,loan_id,amount,paid_date,grace_period,activated_date) VALUE ("
				+ customerId
				+ ","
				+ loan_id
				+ ","
				+ total_amount
				+ ",'"
				+ currDate + "'," + newgrace + ",'" + activated_Date + "')";
		System.out.println("Query : " + querypayment);
		int rowpayment = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			rowpayment = st.executeUpdate(querypayment);
			if (rowpayment > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return rowpayment;
	}

	public static int getRemainingInstallmnet(int loanId) {
		int payment = 0;

		String query = "Select total_installment_remaining from payment_loan WHERE loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				payment = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return payment;
	}

	public static ArrayList<GetDateBean> getDueDate(int loanId) {
		ArrayList<GetDateBean> list = new ArrayList<>();
		GetDateBean bean = null;
		Date d;

		int id;
		String query = "SELECT payment_loan_id, due_date, installments_id FROM customer_installments WHERE payment_loan_id= ?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			// int i = 0;
			while (rs.next()) {
				loanId = rs.getInt(1);
				d = rs.getDate(2);
				id = rs.getInt(3);
				bean = new GetDateBean();
				bean.setLoanId(loanId);
				bean.setDate(d);
				bean.setId(id);
				list.add(bean);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return list;
	}

	public static double getMonthlyInstallment(int loanId) {
		double downPayment = 0;

		String query = "Select installment_amount_month from payment_loan WHERE loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				downPayment = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return downPayment;
	}

	public static int update(int loanId) {

		System.out.print(loanId);
		String query = "UPDATE  customer_installments SET paid_date= CURRENT_TIMESTAMP WHERE "
				+ "DATE_FORMAT(due_date,'%y-%m') = DATE_FORMAT(CURRENT_TIMESTAMP,'%y-%m') AND payment_loan_id ="
				+ loanId + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static boolean getDueDateStatus(int loanId) {
		boolean result = false;

		String query = "SELECT paid_date FROM customer_installments WHERE DATE_FORMAT(paid_date,'%y-%m') = DATE_FORMAT(CURRENT_TIMESTAMP,'%y-%m') AND payment_loan_id=?;";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return result;
	}

	public static int insertIntoPaymentLoan(int soldToId, double totalAmount,
			double recentPayedAmount, double monthlyAmount, int scheme,
			double remainingAmount, int totalRemainingInstallment,
			double downPayment, boolean downpaymentStatus) throws SQLException {
		int rows = 0;

		try (Connection con = Connect.getConnection()) {

			Statement st = null;
			String query = "INSERT INTO payment_loan(soldto_id,total_amount,total_installments,recent_payed_amount,installment_amount_month,total_installment_remaining,remaining_balance,down_payment,down_payment_status,created_on) "
					+ "VALUES ("
					+ soldToId
					+ ","
					+ totalAmount
					+ ","
					+ scheme
					+ ","
					+ recentPayedAmount
					+ ","
					+ monthlyAmount
					+ ","
					+ scheme
					+ ","
					+ remainingAmount
					+ ","
					+ downPayment
					+ ","
					+ false + ",CURRENT_TIMESTAMP);";
			st = con.createStatement();
			rows = st.executeUpdate(query);
			if (rows > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
			e.printStackTrace();
		}
		return rows;
	}

	public static String updateAllLoanReqAccepted(int applianceId,
			int customerId, String applianceGsm) {

		String customerGsm = null, customerName = null, applianceName = null;

		int info_status = 0;
		int elg_status = 1;
		int appliance_status = 1;
		int customer_status = 1;

		int appliance_option = 1;
		boolean payement_option = true;

		int st_status = 0;
		double down_payment = 0.0;
		boolean down_payment_status = false;

		String query = "{CALL update_all_loan_request_accepted(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		try (Connection con = Connect.getConnection()) {
			CallableStatement cst = con.prepareCall(query);

			cst.setInt(1, applianceId);
			cst.setInt(2, customerId);
			cst.setString(3, applianceGsm);
			cst.setInt(4, info_status);
			cst.setInt(5, elg_status);
			cst.setInt(6, appliance_status);
			cst.setInt(7, customer_status);
			cst.setInt(8, appliance_option);
			cst.setBoolean(9, payement_option);
			cst.setInt(10, st_status);
			cst.setDouble(11, down_payment);
			cst.setBoolean(12, down_payment_status);

			cst.registerOutParameter(13, java.sql.Types.VARCHAR);
			cst.registerOutParameter(14, java.sql.Types.VARCHAR);
			cst.registerOutParameter(15, java.sql.Types.VARCHAR);

			cst.executeUpdate();

			customerGsm = cst.getString(13);
			customerName = cst.getString(14);
			applianceName = cst.getString(15);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return customerGsm + ":" + customerName + ":" + applianceName;
	}

	public static String updateAllLoanAccepted(int applianceId, int customerId,
			String applianceGsm, String IMInumber, int elg_status) {

		String customerPhoneNo = null, customerName = null, applianceName = null, status = null, payment = null;
		try (Connection con = Connect.getConnection()) {

			int info_status = 0;
			int appliance_status = 1;
			int customer_status = 1;

			int appliance_option = 1;
			boolean payement_option = true;

			int st_status = 0;
			double down_payment = 0.0;

			boolean down_payment_status = true;

			String query = "{CALL update_all_loan_request_accepted(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";

			CallableStatement cst = con.prepareCall(query);

			cst.setInt(1, applianceId);
			cst.setInt(2, customerId);
			cst.setInt(3, info_status);
			cst.setInt(4, elg_status);
			cst.setInt(5, appliance_status);
			cst.setInt(6, customer_status);
			cst.setInt(7, appliance_option);
			cst.setBoolean(8, payement_option);
			cst.setInt(9, st_status);
			cst.setDouble(10, down_payment);
			cst.setBoolean(11, down_payment_status);

			cst.registerOutParameter(12, java.sql.Types.VARCHAR);
			cst.registerOutParameter(13, java.sql.Types.VARCHAR);
			cst.registerOutParameter(14, java.sql.Types.VARCHAR);
			cst.registerOutParameter(15, java.sql.Types.VARCHAR);
			cst.registerOutParameter(16, java.sql.Types.VARCHAR);
			cst.executeUpdate();

			customerPhoneNo = cst.getString(12);
			customerName = cst.getString(13);
			applianceName = cst.getString(14);
			status = cst.getString(15);
			payment = cst.getString(16);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("", ex);
		}

		return customerPhoneNo + ":" + customerName + ":" + applianceName + ":"
				+ status + ":" + payment;
	}

	public static int acceptLoanRequestBySuperadmin(int applianceId) {
		PreparedStatement ps = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{call accept_loan_request(?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.executeUpdate();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
			System.err.println(ex);
		}

		return 0;
	}
	
	
	public static int acceptLoanRequestBySuperadminAddOn(int addOn_id, int trackingFlag, int returnFlag) {
		PreparedStatement ps = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{call accept_reject_addon_request(?, ?, ?)}");
			prepareCall.setInt(1, addOn_id);
			prepareCall.setInt(2, trackingFlag);
			prepareCall.setInt(3, returnFlag);
			prepareCall.executeUpdate();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
			System.err.println(ex);
		}

		return 0;
	}

	public static String getDatails(int applianceId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String phone = null, customerName = null, applianceName = null, downpaymet = null;
		try (Connection con = Connect.getConnection()) {
			st = con.prepareStatement("SELECT cs.customer_name,customer_phone, a.appliance_name, e.down_payment FROM eligibility e "
					+ "JOIN appliance a ON a.appliance_id = e.appliance_id "
					+ "JOIN customer cs ON cs.customer_id = e.customer_id WHERE a.appliance_id = ?");
			st.setInt(1, applianceId);
			rs = st.executeQuery();
			if (rs.next()) {
				phone = rs.getString("customer_phone");
				customerName = rs.getString("customer_name");
				applianceName = rs.getString("appliance_name");
				downpaymet = rs.getString("down_payment");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return phone + " " + customerName + " " + applianceName + " "
				+ downpaymet;
	}

	/**
	 * @param arg
	 */
	public static void main(String arg[]) {
		System.out
				.println("ddhd " + updateAllLoanAccepted(361, 401, "", "", 1));

	}

}
