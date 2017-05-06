package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import connection.Connect;

public class Appliance_Message {

	final static Logger logger = Logger.getLogger(Appliance_Message.class);

	public static int insertAppliance_Message(String message_from, String message_to, String message_date,
			String message_text, int message_seen) {
		Statement st = null;

		String query = "INSERT INTO appliance_message (`msg_from`,`msg_to`,`msg_text`,`msg_date`,`msg_seen`)\n"
				+ " VALUES('" + message_from + "',' " + message_to + "','" + message_text + "','" + message_date + "',"
				+ 0 + " );";
		int row = 0;
		System.out.println(query);
		try (Connection con = Connect.getConnection()) {

			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 0;
	}

	public static int insertAppliance_MessageStatus(String message_from, String message_to, String message_date,
			String message_text, int message_seen) {
		Statement st = null;

		String query = "INSERT INTO appliance_message_status (`msg_from`,`msg_to`,`msg_text`,`msg_date`,`msg_seen`)\n"
				+ " VALUES('" + message_from + "',' " + message_to + "','" + message_text + "','" + message_date + "',"
				+ 0 + " );";
		int row = 0;
		System.out.println(query);
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 0;
	}

	public static int applianceOnOff(int status, String applianceGsm) {

		CallableStatement cst = null;
		int row = 0;

		String query = "CALL appliance_on_off(?, ?)";
		try (Connection con = Connect.getConnection()) {
			cst = con.prepareCall(query);
			cst.setInt(1, status);
			cst.setString(2, applianceGsm);

			row = cst.executeUpdate();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		System.err.println("status : " + status + "   applianceGsm  " + applianceGsm + "    row = " + row);
		return row;
	}

}
