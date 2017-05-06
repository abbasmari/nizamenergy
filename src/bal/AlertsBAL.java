package bal;

import bean.AlertsBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class AlertsBAL {

	final static Logger logger = Logger.getLogger(AlertsBAL.class);

	public static AlertsBean getAlerts(int alertsIdd) {
		AlertsBean bean = null;
		int alertsId, applianceID, task_status;
		Time time;
		boolean panelLost, location, temp_high, lidOpen, changeControlLost, batteryLow;
		String connetion, panel, ticket, status, technician;
		String alertsDate, elapseTime;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT alerts_id, appliance_id, pannel_lost, connection, lid_open, battery_low, panel, change_control_lost, alerts_date, location_changed, status, ticket_no, temp_high,technician,alert_time,task_status,elapse_time FROM alerts WHERE alerts_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertsIdd);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);
				applianceID = rs.getInt(2);
				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				location = rs.getBoolean(10);
				status = rs.getString(11);
				ticket = rs.getString(12);
				temp_high = rs.getBoolean(13);
				technician = rs.getString(14);
				time = rs.getTime(15);
				task_status = rs.getInt(16);
				elapseTime = rs.getString(17);
				System.out.print(alertsDate + "" + location + " " + batteryLow);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceID);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setAlerts_date(alertsDate);
				bean.setLocation_changed(location);
				bean.setStatus(status);
				bean.setTemp_high(temp_high);
				bean.setTicket(ticket);
				bean.setTechnician(technician);
				bean.setTime(time);
				bean.setTaskStatus(task_status);
				bean.setElapseTime(elapseTime);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static ArrayList<AlertsBean> getAlertsById(int alertId) {
		AlertsBean bean = null;
		ArrayList<AlertsBean> list = new ArrayList<AlertsBean>();
		int alertsId, applianceId;
		boolean panelLost, location, temp_high, lidOpen, changeControlLost, batteryLow;
		String connetion, panel, ticket, status, technician;
		String alertsDate;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT alerts_id, appliance_id, pannel_lost, connection, lid_open, battery_low, panel, change_control_lost, alerts_date, location_changed, status, ticket_no, temp_high,technician FROM alerts WHERE alerts_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);
				applianceId = rs.getInt(2);
				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				location = rs.getBoolean(10);
				status = rs.getString(11);
				ticket = rs.getString(12);
				temp_high = rs.getBoolean(13);
				technician = rs.getString(14);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceId);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setAlerts_date(alertsDate);
				bean.setLocation_changed(location);
				bean.setStatus(status);
				bean.setTemp_high(temp_high);
				bean.setTicket(ticket);
				bean.setTechnician(technician);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<AlertsBean> getAlertList(int applianceId) {
		AlertsBean bean = null;
		ArrayList<AlertsBean> list = new ArrayList<AlertsBean>();
		int alertsId;
		boolean panelLost, lidOpen, batteryLow, changeControlLost;
		String connetion, panel;
		Time time;
		String alertsDate;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT alerts_id, appliance_id, pannel_lost, CONNECTION, lid_open, battery_low, panel, change_control_lost, alerts_date,alert_time FROM alerts WHERE appliance_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, applianceId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);

				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				time = rs.getTime(10);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceId);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setTime(time);
				bean.setAlerts_date(alertsDate);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int updateTicket(int id, int status) {
		Statement st = null;

		String query = "UPDATE appliance SET alert_status=" + status + " WHERE appliance_id = " + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

			st.close();
			con.close();

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int update(int id, int appId, int status) {
		Statement st = null;

		String query = "UPDATE alerts SET h_status=" + status + " WHERE appliance_id = " + appId + " AND alerts_id = "
				+ id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

			st.close();
			con.close();

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int insertAlerts(int id, boolean pannel_lost, boolean connection1, boolean lid, boolean battery,
			boolean pannel, int status, int taskStatus) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Karachi"));
		Date currentDate = calendar.getTime();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));
		df2.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));

		System.out.println("Date and time in Madrid: " + df.format(date));
		System.out.println("Date and time in Madrid: " + df2.format(currentDate));

		String query = "INSERT INTO alerts (`appliance_id`,`pannel_lost`,`connection`,`panel`,`change_control_lost`,`location_changed`,`lid_open`,`battery_low`,`temp_high`,`alerts_date`,`alert_time`,`status`,`h_status`,`task_status`,`is_seen`,`admin_is_seen`)\n"
				+ " VALUES(" + id + ", " + pannel_lost + "," + 0 + "," + 0 + "," + 0 + "," + connection1 + "," + lid
				+ "," + battery + "," + pannel + ",'" + df2.format(currentDate) + "','" + df.format(date) + "'," + 0
				+ "," + status + "," + taskStatus + "," + 0 + "," + 0 + ");";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			row = stmt.executeUpdate();
			if (row > 0) {
				System.out.println("Data inserted");
			} else {
				System.out.print("Data Not Inserted");
			}

			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return row;
	}

	public static ArrayList<AlertsBean> getApplianceHistory(int appId) {
		AlertsBean bean = null;
		ArrayList<AlertsBean> list = new ArrayList<AlertsBean>();
		int alertsId, applianceID, task_status, techId;
		Time time;
		boolean panelLost, location, temp_high, lidOpen, changeControlLost, batteryLow;
		String connetion, panel, ticket, status, technician;
		String alertsDate, elapseTime;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT alerts_id, appliance_id, pannel_lost, connection, lid_open, battery_low, panel, change_control_lost, alerts_date, location_changed, status, ticket_no, temp_high,technician,alert_time,task_status,elapse_time,technician_id FROM alerts WHERE appliance_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, appId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);
				applianceID = rs.getInt(2);
				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				location = rs.getBoolean(10);
				status = rs.getString(11);
				ticket = rs.getString(12);
				temp_high = rs.getBoolean(13);
				technician = rs.getString(14);
				time = rs.getTime(15);
				task_status = rs.getInt(16);
				elapseTime = rs.getString(17);
				techId = rs.getInt(18);
				System.out.print(alertsDate + "" + location + " " + batteryLow);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceID);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setAlerts_date(alertsDate);
				bean.setLocation_changed(location);
				bean.setStatus(status);
				bean.setTemp_high(temp_high);
				bean.setTicket(ticket);
				bean.setTechnician(technician);
				bean.setTime(time);
				bean.setTaskStatus(task_status);
				bean.setElapseTime(elapseTime);
				bean.setTechId(techId);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getAlertsVhigh() {
		int total = 0;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			String query = " select count(*) from alerts WHERE battery_low IS TRUE";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);

			rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return total;
	}

	public static int getAlertshigh() {
		int total = 0;
		try (Connection con = Connect.getConnection()) {
			String query = " select count(*) from alerts WHERE pannel_lost IS TRUE AND battery_low IS FALSE;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = null;
			rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return total;
	}

	public static int getAlertslow() {
		int total = 0;
		try (Connection con = Connect.getConnection()) {
			String query = " select count(*) from alerts WHERE location_changed IS TRUE AND battery_low IS FALSE AND pannel_lost IS false AND lid_open IS FALSE;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = null;
			rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return total;
	}

	public static int getAlertsmedium() {
		int total = 0;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			String query = " select count(*) from alerts WHERE lid_open IS TRUE AND battery_low IS FALSE AND pannel_lost IS false;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return total;
	}

	public static AlertsBean getAlerts_low(int alertsIdd) {
		AlertsBean bean = null;
		int alertsId, applianceID, task_status;
		Time time;
		boolean panelLost, location, temp_high, lidOpen, changeControlLost, batteryLow;
		String connetion, panel, ticket, status, technician;
		String alertsDate, elapseTime;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			String query = "SELECT alerts_id, appliance_id, pannel_lost, connection, lid_open, battery_low, panel, change_control_lost, alerts_date, location_changed, status, ticket_no, temp_high,technician,alert_time,task_status,elapse_time FROM alerts WHERE location_changed IS TRUE and alerts_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertsIdd);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);
				applianceID = rs.getInt(2);
				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				location = rs.getBoolean(10);
				status = rs.getString(11);
				ticket = rs.getString(12);
				temp_high = rs.getBoolean(13);
				technician = rs.getString(14);
				time = rs.getTime(15);
				task_status = rs.getInt(16);
				elapseTime = rs.getString(17);
				System.out.print(alertsDate + "" + location + " " + batteryLow);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceID);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setAlerts_date(alertsDate);
				bean.setLocation_changed(location);
				bean.setStatus(status);
				bean.setTemp_high(temp_high);
				bean.setTicket(ticket);
				bean.setTechnician(technician);
				bean.setTime(time);
				bean.setTaskStatus(task_status);
				bean.setElapseTime(elapseTime);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static AlertsBean getAlerts_vhigh(int alertsIdd) {
		AlertsBean bean = null;
		int alertsId, applianceID, task_status;
		Time time;
		boolean panelLost, location, temp_high, lidOpen, changeControlLost, batteryLow;
		String connetion, panel, ticket, status, technician;
		String alertsDate, elapseTime;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;

			String query = "SELECT alerts_id, appliance_id, pannel_lost, connection, lid_open, battery_low, panel, change_control_lost, alerts_date, location_changed, status, ticket_no, temp_high,technician,alert_time,task_status,elapse_time FROM alerts WHERE alerts_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertsIdd);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);
				applianceID = rs.getInt(2);
				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				location = rs.getBoolean(10);
				status = rs.getString(11);
				ticket = rs.getString(12);
				temp_high = rs.getBoolean(13);
				technician = rs.getString(14);
				time = rs.getTime(15);
				task_status = rs.getInt(16);
				elapseTime = rs.getString(17);
				System.out.print(alertsDate + "" + location + " " + batteryLow);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceID);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setAlerts_date(alertsDate);
				bean.setLocation_changed(location);
				bean.setStatus(status);
				bean.setTemp_high(temp_high);
				bean.setTicket(ticket);
				bean.setTechnician(technician);
				bean.setTime(time);
				bean.setTaskStatus(task_status);
				bean.setElapseTime(elapseTime);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static AlertsBean getAlerts_medium(int alertsIdd) {
		AlertsBean bean = null;
		int alertsId, applianceID, task_status;
		Time time;
		boolean panelLost, location, temp_high, lidOpen, changeControlLost, batteryLow;
		String connetion, panel, ticket, status, technician;
		String alertsDate, elapseTime;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			String query = "SELECT alerts_id, appliance_id, pannel_lost, connection, lid_open, battery_low, panel, change_control_lost, alerts_date, location_changed, status, ticket_no, temp_high,technician,alert_time,task_status,elapse_time FROM alerts WHERE lid_open IS TRUE and alerts_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertsIdd);
			rs = stmt.executeQuery();
			while (rs.next()) {
				alertsId = rs.getInt(1);
				applianceID = rs.getInt(2);
				panelLost = rs.getBoolean(3);
				connetion = rs.getString(4);
				lidOpen = rs.getBoolean(5);
				batteryLow = rs.getBoolean(6);
				panel = rs.getString(7);
				changeControlLost = rs.getBoolean(8);
				alertsDate = rs.getString(9);
				location = rs.getBoolean(10);
				status = rs.getString(11);
				ticket = rs.getString(12);
				temp_high = rs.getBoolean(13);
				technician = rs.getString(14);
				time = rs.getTime(15);
				task_status = rs.getInt(16);
				elapseTime = rs.getString(17);
				System.out.print(alertsDate + "" + location + " " + batteryLow);
				bean = new AlertsBean();
				bean.setAlertsId(alertsId);
				bean.setApplianceId(applianceID);
				bean.setConnection(connetion);
				bean.setPannel_lost(panelLost);
				bean.setBattery_low(batteryLow);
				bean.setLidOpen(lidOpen);
				bean.setChange_control_lost(changeControlLost);
				bean.setPanel(panel);
				bean.setAlerts_date(alertsDate);
				bean.setLocation_changed(location);
				bean.setStatus(status);
				bean.setTemp_high(temp_high);
				bean.setTicket(ticket);
				bean.setTechnician(technician);
				bean.setTime(time);
				bean.setTaskStatus(task_status);
				bean.setElapseTime(elapseTime);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

}
