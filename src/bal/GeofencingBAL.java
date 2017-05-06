package bal;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class GeofencingBAL {
	final static Logger logger = Logger.getLogger(GeofencingBAL.class);

	public static int insertstatus(int app_id, double voltage, double batery, double temp, String environment,
			double lati, double longi) {
		int row = 0;
		Statement st = null;

		String query = "INSERT INTO appliance_status(appliance_id,appliance_voltage, appliance_battery,appliance_temperature,appliance_environment,latitude,longitude)VALUES ("
				+ app_id + "," + voltage + "," + batery + "," + temp + ",'" + environment + "'," + lati + "," + longi
				+ ");";
		try (Connection con = Connect.getConnection()) {

			System.out.println("Query : " + query);

			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

}
