package schedule;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import java.sql.Time;

import bal.CallingXML;
import bal.Payment_loanNewBAL;
import bal.telenor;
import connection.Connect;


public class AlarmsAlertsToFo extends Thread {
	private final static Logger logger = Logger.getLogger(AlarmsAlertsToFo.class);

	public void run() {
		try {
			while (true) {
				Date date1 = new Date();
				Time curremtTime = new Time(date1.getTime());
				String yourTime="09:00:00";
                SimpleDateFormat ra = new SimpleDateFormat("HH:mm:ss");
				Date date2 = ra.parse(yourTime);
				Time fixedTime = new Time(date2.getTime());
				if (curremtTime.after(fixedTime) && curremtTime.after(fixedTime)) {
//					getNotSeenAlarms();
				}
				Thread.sleep(3000000); 
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	public static int getNotSeenAlarms() {
		int status = -1;
		try (Connection con = Connect.getConnection();
				CallableStatement call = con.prepareCall("{call send_appliance_alarms_msg_to_fo()}");) {
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				String foName = rs.getString("fo_name");
				String foPhoneNo = rs.getString("fo_priamary_phone");
				String battery = rs.getString("battery_level");
				String currentLoad = rs.getString("current_load");
				String temperature = rs.getString("temperature");
				String lidConnectivity = rs.getString("lid_connectivity");
				String gsSignalStrength = rs.getString("gsm_signal_strength");
				String customerName = rs.getString("customer_name");
				String customerAddress = rs.getString("customer_address");
				String imeiNumber = rs.getString("imei_number");
				String customerNumber = rs.getString("customer_phone");
				status = rs.getInt("aa.status");
				String str = "";
				if (status == 0) {
					str = "Pending";
				} else {
					str = "Resolved";
				}
				String problem = "";
				if (battery != null || battery != "null" || battery != "0") {
					problem += "battery";
				}
				if (gsSignalStrength != null || gsSignalStrength != "null" || gsSignalStrength != "0") {
					problem += "gsSignalStrength";
				}
				if (currentLoad != null || currentLoad != "null" || currentLoad != "0") {
					problem += "currentLoad";
				}
				if (temperature != null || temperature != "null" || temperature != "0") {
					problem += "temperature";
				}
				if (lidConnectivity != null || lidConnectivity != "null" || lidConnectivity != "0") {
					problem += "lidConnectivity";
				}
				System.out.print("hfdgh" + customerAddress);
				if (foPhoneNo != null && (battery != null || currentLoad != null || temperature != null
						|| gsSignalStrength != null || lidConnectivity != null)) {
					try {
						CallingXML.SendMessage(foPhoneNo,
								"FAULT ALERT : FO attention needed, Problem: " + problem + " CustomerName: "
										+ customerName + " Customer Number: " + customerNumber + " Address: "
										+ customerAddress + " IMEI: " + imeiNumber);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return status;
	}

	public static void main(String arg[]) {
		String ar[] = new String[3];
		ar[0] = "h";
		ar[2] = "a";

		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == null) {
				ar[i] = ar[i + 1];
			}
		}

		System.out.print("dfghg" + ar[1]);
	}

}
