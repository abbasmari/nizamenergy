/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.AlertsLogBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import connection.Connect;

/**
 *
 * @author Jhaman-Khatri
 */
public class AlertsLogBAL {

	final static Logger logger = Logger.getLogger(AlertsLogBAL.class);

	public static AlertsLogBean getLogInfo(int alerts_id) {
		AlertsLogBean bean = null;
		Time log_time;
		Date log_date;
		int status, task_statuss;
		boolean panel_lost, lid_open, battery_low, location_changed, temp_high;

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			String query = "select alert_time, alerts_date, status,task_status,pannel_lost,lid_open,battery_low,location_changed,temp_high from alerts where alerts_id=?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alerts_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				log_time = rs.getTime(1);
				log_date = rs.getDate(2);
				status = rs.getInt(3);
				task_statuss = rs.getInt(4);
				panel_lost = rs.getBoolean(5);
				lid_open = rs.getBoolean(6);
				battery_low = rs.getBoolean(7);
				location_changed = rs.getBoolean(8);
				temp_high = rs.getBoolean(9);

				bean = new AlertsLogBean();
				bean.setLogTime(log_time);
				bean.setLogDate(log_date);
				bean.setStatus(status);
				bean.setTask_status(task_statuss);
				bean.setLog_panel_lost(panel_lost);
				bean.setLog_battery_low(battery_low);
				bean.setLog_lid_open(lid_open);
				bean.setTemp_high(temp_high);
				bean.setLog_location_changed(location_changed);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;

	}

	public static ArrayList<AlertsLogBean> getLogInfoList(int alerts_id) {
		// ArrayList<AlertsLogBean> list=null;

		// CustomerInfoBean bean = null;
		AlertsLogBean bean = null;
		// ArrayList<CustomerInfoBean> list = new ArrayList<>();
		ArrayList<AlertsLogBean> list = new ArrayList<>();

		Time log_time;
		Date log_date;
		int status, task_statuss;
		boolean log_panel_lost, log_lid_open, log_battery_low, log_location_changed, log_temp_high;

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			String query = "select * from  alerts_log where alerts_id= ?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alerts_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				log_time = rs.getTime(2);
				log_date = rs.getDate(3);
				status = rs.getInt(4);
				task_statuss = rs.getInt(6);
				log_panel_lost = rs.getBoolean(7);
				log_lid_open = rs.getBoolean(8);
				log_battery_low = rs.getBoolean(9);
				log_location_changed = rs.getBoolean(10);
				log_temp_high = rs.getBoolean(11);
				bean = new AlertsLogBean();
				bean.setLogTime(log_time);
				bean.setLogDate(log_date);
				bean.setStatus(status);
				bean.setTask_status(task_statuss);
				bean.setLog_panel_lost(log_panel_lost);

				bean.setLog_lid_open(log_lid_open);
				bean.setLog_battery_low(log_battery_low);
				bean.setLog_location_changed(log_location_changed);
				bean.setTemp_high(log_temp_high);
				list.add(bean);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}

	public static int insertIntoAlertLog(Time lo_time, Date log_date, int status, int alertID, int task_statuss,
			boolean pannel_lost, boolean location, boolean lid_open, boolean battery_low, boolean temp)
					throws SQLException {
		int rows = 0;
		try (Connection con = Connect.getConnection()) {
			try (PreparedStatement prepareStatement = con.prepareStatement(
					"INSERT INTO alerts_log(" + "log_time," + " log_date," + " log_status," + " alerts_id,"
							+ " log_task_status," + " log_pannel_lost," + " log_location_changed," + " log_lid_open,"
							+ " log_battery_low," + " log_temp_high," + " log_datetime)" + " VALUES (" + "?," // log_time
							+ "?," // log_date
							+ "?," // log_status
							+ "?," // alerts_id
							+ "?," // log_task_status
							+ "?," // log_pannel_lost
							+ "?," // log_location_changed
							+ "?," // log_lid_open
							+ "?," // log_battery_low
							+ "?" // log_temp_high
							+ "?," // log_datetime
							+ ")")) {
				prepareStatement.setTime(1, lo_time);
				prepareStatement.setDate(2, java.sql.Date.valueOf(log_date.toString()));
				prepareStatement.setInt(2, status);
				prepareStatement.setInt(2, alertID);
				prepareStatement.setInt(2, task_statuss);
				prepareStatement.setBoolean(2, pannel_lost);
				prepareStatement.setBoolean(2, location);
				prepareStatement.setBoolean(2, lid_open);
				prepareStatement.setBoolean(2, battery_low);
				prepareStatement.setBoolean(2, temp);
				rows = prepareStatement.executeUpdate();
				// statement.close();
				con.close();

			} catch (Exception e) {
				logger.error("", e);
				e.printStackTrace();
				// con.close();
			}
		}
		return rows;
	}

	public static String getFirstDate(int alertID) {
		String firsDate = "";
		String query = "SELECT log_datetime FROM alerts_log where alerts_id=? and log_id=(select min(log_id) from alerts_log where alerts_id=?);";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertID);
			stmt.setInt(2, alertID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				firsDate = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return firsDate;
	}

	public static String getLastDate(int alertID) {
		String lastDate = "";
		String query = "SELECT log_datetime FROM alerts_log where alerts_id=? and log_id=(select max(log_id) from alerts_log where alerts_id=?);";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, alertID);
			stmt.setInt(2, alertID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				lastDate = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return lastDate;
	}

	public static String calculateElapseTime(String firstDate, String secondDate) {
		String dateStart = firstDate;// "2015-08-12 17:50:19";
		String dateStop = secondDate;// "2015-08-12 17:50:49";
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
		} catch (ParseException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000);
		System.out.println("Time in seconds: " + diffSeconds + " seconds.");
		System.out.println("Time in minutes: " + diffMinutes + " minutes.");
		System.out.println("Time in hours: " + diffHours + " hours.");

		return "" + (diffMinutes + (diffHours * 60));
	}

}