package bal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import connection.Connect;

public class ComplainRegBAL {

	final static Logger logger = Logger.getLogger(ComplainRegBAL.class);

	public static void regComplain(int user_id, String complaint, int customer_id, int app_id)

	{
		int row = 0;

		String query = "INSERT INTO customer_complaint(user_id,complaint,customer_id,complaint_date,appliance_id) VALUES ("
				+ user_id + ",'" + complaint + "'," + customer_id + ",NOW()," + app_id + ");";
		try (Connection con = Connect.getConnection()) {

			Statement ps = (Statement) con.createStatement();
			row = ps.executeUpdate(query);
			if (row > 0) {
				System.out.println("REcord /added");
			} else {
				System.out.println("NOt INSERted");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static int getId() {
		int id = 0;

		String query = "SELECT MAX(complaint_id) FROM customer_complaint";
		try (Connection con = Connect.getConnection()) {

			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

}
