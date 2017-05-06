package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.Message_details_counter;

public class Message_details {
	final static Logger logger = Logger.getLogger(Message_details.class);

	public static Message_details_counter get_counter_telco() {

		System.out.println("Message_details.get_counter_telco()");

		Message_details_counter bean = null;

		// String query = "SELECT (SELECT COUNT(*) FROM
		// total_msg_customer_appliance WHERE Msg_from='telenor') AS
		// telenor,(SELECT COUNT(*) FROM total_msg_customer_appliance WHERE
		// Msg_from='Mobilink') AS mobilink;";
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {

			// PreparedStatement stmt = (PreparedStatement)
			// con.prepareStatement(query);

			// rs = stmt.executeQuery();
			// while (rs.next()) {
			// telenor=rs.getInt(1);
			// mobilink=rs.getInt(2);
			// bean=new Message_details_counter();
			//
			// bean.setMobilink(mobilink);
			// bean.setTelenor(telenor);
			// }

			// -------------------------------------------------------
			// Begin Stored Procedure -- Jeevan
			prepareCall = con.prepareCall("{call count_telenor_and_mobilink_messages()}");

			ResultSet resultSet = prepareCall.executeQuery();
			bean = new Message_details_counter();
			while (resultSet.next()) {
				if (resultSet.getString("msg_from").equalsIgnoreCase("telenor")) {
					bean.setTelenor(resultSet.getInt("no_sms"));
				} else if (resultSet.getString("msg_from").equalsIgnoreCase("mobilink")) {
					bean.setMobilink(resultSet.getInt("no_sms"));
				}
			}

			// End Stored Procedures -- Jeevan

		} catch (SQLException ex) {
			logger.error("", ex);
			// System.out.printf(ex.getMessage());
			System.err.println("Message_details.get_counter_telco()");
			ex.printStackTrace();
		}

		try {
			prepareCall.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static Message_details_counter get_counter_telco_customer() {
		System.out.println("Message_details.get_counter_telco_customer()");
		Message_details_counter bean = null;

		// String query = "SELECT (SELECT COUNT(*) FROM customer_message WHERE
		// telco=3001) AS telenor,(SELECT COUNT(*) FROM customer_message WHERE
		// telco=7005148) AS mobilink;";
		try (Connection con = Connect.getConnection()) {

			// PreparedStatement stmt = (PreparedStatement)
			// con.prepareStatement(query);

			// rs = stmt.executeQuery();
			// while (rs.next()) {
			// telenor=rs.getInt(1);
			// mobilink=rs.getInt(2);
			// bean=new Message_details_counter();
			//
			// bean.setMobilink(mobilink);
			// bean.setTelenor(telenor);
			// }

			CallableStatement prepareCall = con.prepareCall("{call count_teleco_customer()}");
			// prepareCall.registerOutParameter(1, Types.INTEGER);
			// prepareCall.registerOutParameter(2, Types.INTEGER);
			ResultSet resultSet = prepareCall.executeQuery();
			bean = new Message_details_counter();
			while (resultSet.next()) {
				if (resultSet.getInt("telco") == 3001) {
					bean.setMobilink(resultSet.getInt("no_sms"));
				} else if (resultSet.getInt("telco") == 7005148) {
					bean.setTelenor(resultSet.getInt("no_sms"));
				}
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static Message_details_counter getCounterTeleco() {
		Message_details_counter message_details_counter = new Message_details_counter();
		try (Connection con = Connect.getConnection()) {

			if (con != null) {

				CallableStatement prepareCall = con.prepareCall("{call count_telenor_and_mobilink_messages()}");

				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					if (resultSet.getString("msg_from").equalsIgnoreCase("telenor")) {
						message_details_counter.setTelenor(resultSet.getInt("no_sms"));
					} else if (resultSet.getString("msg_from").equalsIgnoreCase("mobilink")) {
						message_details_counter.setMobilink(resultSet.getInt("no_sms"));
					}
				}

				resultSet = null;
				prepareCall = null;

				prepareCall = con.prepareCall("{call count_teleco_customer()}");
				// prepareCall.registerOutParameter(1, Types.INTEGER);
				// prepareCall.registerOutParameter(2, Types.INTEGER);
				resultSet = prepareCall.executeQuery();
				// bean = new Message_details_counter();
				while (resultSet.next()) {
					if (resultSet.getInt("telco") == 3001) {
						message_details_counter
								.setMobilink(message_details_counter.getMobilink() + resultSet.getInt("no_sms"));
					} else if (resultSet.getInt("telco") == 7005148) {
						message_details_counter
								.setTelenor(message_details_counter.getTelenor() + resultSet.getInt("no_sms"));
					}
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message_details_counter;
	}

	public void insertMessages(String message, String phone) {
		int id = 0;
		try {
			Connection connection = Connect.getConnection();
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `pending_messages` ( " + " `reciever_no`, " + " `message` " + " )  "
							+ " VALUES " + " ( " + "?, " + "? " + " ) ; ");
			prepareStatement.setString(1, phone);
			prepareStatement.setString(2, message);
			prepareStatement.executeUpdate();
			id = (int) prepareStatement.getLastInsertID();
			System.out.println(prepareStatement.asSql());
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

}
