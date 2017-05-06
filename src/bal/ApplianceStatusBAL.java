/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.AlarmsBean;
import bean.ApplianceStatusBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

/**
 * 
 * @author Jeatnder Khatri
 */
public class ApplianceStatusBAL {

	final static Logger logger = Logger.getLogger(ApplianceStatusBAL.class);

	public static int getLastStatusId(int id) {
		ResultSet rs = null;
		int lastId = 0;

		String query = "Select MAX(status_appliance_id) from appliance_status WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				lastId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return lastId;
	}

	public static ApplianceStatusBean getStatusInfo(int lastId, int applianceID) {
		ResultSet rs = null;

		ApplianceStatusBean bean = null;
		int statusId, applianceId, type;

		double voltage, backup, temperatue, lati, longi;
		String environment;
		String statusDate;
		try (Connection con = Connect.getConnection()) {

			// String query =
			// "SELECT status_appliance_id, appliance_id, appliance_voltage,
			// appliance_battery, appliance_temperature,
			// appliance_environment,appliance_status,latitude, longitude,
			// status_date FROM appliance_status WHERE status_appliance_id=? AND
			// appliance_id=?;";
			String query = "SELECT status_appliance_id, appliance_id, appliance_voltage, appliance_battery, appliance_temperature, latitude, longitude,ast.status_date,app.appliance_status FROM appliance_status ast JOIN appliance app USING(appliance_id) WHERE status_appliance_id=? AND appliance_id=?;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, lastId);
			stmt.setInt(2, applianceID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				statusId = rs.getInt(1);
				applianceId = rs.getInt(2);
				voltage = rs.getDouble(3);
				backup = rs.getDouble(4);
				temperatue = rs.getDouble(5);
				type = rs.getInt("appliance_status");
				// environment = rs.getString(6);

				lati = rs.getDouble(6);
				longi = rs.getDouble(7);
				statusDate = rs.getString(8);
				bean = new ApplianceStatusBean();
				bean.setStatusId(statusId);
				bean.setApplianceId(applianceId);
				bean.setVolatge(voltage);
				bean.setTemperature(temperatue);
				bean.setBackup(backup);
				bean.setStatusDate(statusDate);
				// bean.setEnvironment(environment);
				bean.setLatitude(lati);
				bean.setLongitude(longi);
				bean.setType(type);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static int insertStatus(HashMap<String, String> map) {

		int id = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_status_of_appliance(?,?,?,?,?,?,?,?,?,?,?,?)}");
			System.out.println("longiii " + map.get("long"));
			System.out.println("IMEI : " + map.get("imei"));
			prepareCall.setDouble(1, Double.parseDouble(map.get("bv")));
			prepareCall.setDouble(2, Double.parseDouble(map.get("ba")));
			prepareCall.setDouble(3, Double.parseDouble(map.get("sv")));
			prepareCall.setDouble(4, Double.parseDouble(map.get("sa")));
			prepareCall.setDouble(5, Double.parseDouble(map.get("lv")));
			prepareCall.setDouble(6, Double.parseDouble(map.get("la")));
			prepareCall.setBoolean(7, Boolean.parseBoolean(map.get("lid")));
			System.out.println(Integer.parseInt(map.get("db")));
			prepareCall.setInt(8, Integer.parseInt(map.get("db")));
			prepareCall.setDouble(9, Double.parseDouble(map.get("tp")));
			prepareCall.setString(10, map.get("lat"));
			prepareCall.setString(11, map.get("long"));
			prepareCall.setString(12, map.get("imei"));
			ResultSet resultSet = null;
			resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static int insertStatus(ApplianceStatusBean bean) {
		int id = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_status_of_appliance(?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setDouble(1, bean.getBv());
			prepareCall.setDouble(2, bean.getBa());
			prepareCall.setDouble(3, bean.getSv());
			prepareCall.setDouble(4, bean.getSa());
			prepareCall.setDouble(5, bean.getLv());
			prepareCall.setDouble(6, bean.getLa());
			prepareCall.setBoolean(7, bean.isLid());
			prepareCall.setInt(8, bean.getSignalStrength());
			prepareCall.setDouble(9, bean.getTemperature());
			prepareCall.setDouble(10, bean.getLatitude());
			prepareCall.setDouble(11, bean.getLongitude());
			prepareCall.setString(12, bean.getIMEI());
			ResultSet resultSet = null;
			resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static int insertAlarms(HashMap<String, String> map) {
		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_appliance_alarms(?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setString(1, map.get("imeiNumber"));
			prepareCall.setString(2, map.get("betteryLevel"));
			prepareCall.setString(3, map.get("solarPower"));
			prepareCall.setString(4, map.get("currentLoad"));
			prepareCall.setString(5, map.get("temperature"));
			prepareCall.setString(6, map.get("lidOpen"));
			prepareCall.setString(7, map.get("signalStrength"));
			prepareCall.setDouble(8, Double.parseDouble(map.get("latitude")));
			prepareCall.setDouble(9, Double.parseDouble(map.get("longitude")));
			prepareCall.setString(10, map.get("commonFault"));
			prepareCall.setString(11, map.get("gsm_suicide"));
			prepareCall.setString(12, map.get("battery_overcharge"));
			ResultSet resultSet = null;
			resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static int insertAlarms(AlarmsBean bean) {
		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_appliance_alarms(?,?,?,?,?,?,?,?,?)}");
			prepareCall.setString(1, bean.getIMEI());
			prepareCall.setBoolean(2, bean.isBetteryLevel());
			prepareCall.setBoolean(3, bean.isSolarPower());
			prepareCall.setBoolean(4, bean.isCurrentLoad());
			prepareCall.setBoolean(5, bean.isTemperature());
			prepareCall.setBoolean(6, bean.isLidOpen());
			prepareCall.setBoolean(7, bean.isSignalStrength());
			prepareCall.setDouble(8, bean.getLatitude());
			prepareCall.setDouble(9, bean.getLongitude());

			ResultSet resultSet = null;
			resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static ArrayList<HashMap<String, String>> getApplianceAlarmsbyId(
			int id) {
		ArrayList<HashMap<String, String>> list = new ArrayList();
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{call get_appliance_alarms_by_id(?)}");
			call.setInt(1, id);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("ticketNo", rs.getString("ticket_number") + "");
				map.put("ticketStatus", rs.getInt("aa.status") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getApplianceAlarms() {
		ArrayList<HashMap<String, String>> list = new ArrayList();
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{call get_appliance_alarms()}");
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("aa.status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getApplianceAlarms(int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList();
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{call get_do_appliance_alarms(?)}");
			call.setInt(1, doId);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("aa.status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSuperadminAlarmsList(
			int start, int length, String order, int column) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("call superadmin_appliance_alarms(?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSuperadminAlarmsListCount() {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("call superadmin_appliance_alarms_count()");
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getSuperadminAlarmListSearch(
			int start, int length, String order, int col, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;

		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_appliance_alarms_search(?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSuperadminAlarmsListSearchCount(String order, int col,
			String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_appliance_alarms_search_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, col);
			call.setString(3, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoApplianceAlarmsList(
			int start, int length, String order, int col, int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL do_appliance_alarms(?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setInt(5, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getDoApplianceAlarmsListCount(String order, int col,
			int userId) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL do_appliance_alarms_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, col);
			call.setInt(3, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoApplianceAlarmsListSearch(
			int start, int length, String order, int col, int userId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL do_appliance_alarms_search(?,?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setInt(5, userId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getDoApplianceAlarmsListSearchCount(String order,
			int col, int userId, String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL do_appliance_alarms_search_count(?,?,?,?)");
			call.setString(1, order);
			call.setInt(2, col);
			call.setInt(3, userId);
			call.setString(4, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getSuperadminApplianceViewAlertList(
			int start, int length, String order, int column, int appId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table(?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("gsm_suicide", rs.getBoolean("gsm_suicide") + "");
				map.put("common_fault", rs.getBoolean("common_fault") + "");
				map.put("battery_overcharge",
						rs.getBoolean("battery_overcharge") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				// map.put("foName", rs.getString("fo_name")+"");
				// map.put("userName", rs.getString("user_name")+"");
				// map.put("ticketNo", rs.getString("ticket_number")+"");
				map.put("ticketStatus", rs.getInt("status") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSuperadminApplianceViewAlertListCount(String order,
			int column, int appId) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getSuperadminApplianceviewAlertsListSearch(
			int start, int length, String order, int column, int appId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table_search(?,?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, appId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("gsm_suicide", rs.getBoolean("gsm_suicide") + "");
				map.put("common_fault", rs.getBoolean("common_fault") + "");
				map.put("battery_overcharge",
						rs.getBoolean("battery_overcharge") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				// map.put("foName", rs.getString("fo_name")+"");
				// map.put("userName", rs.getString("user_name")+"");
				// map.put("ticketNo", rs.getString("ticket_number")+"");
				map.put("ticketStatus", rs.getInt("status") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSuperadminApplianceviewAlertsListSearchCount(
			String order, int column, int appId, String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table_search_count(?,?,?,?)");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, appId);
			call.setString(4, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getStatusInfo(int appId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_status_info(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("max_solar", rs.getDouble("max_solar_wattage") + "");
				map.put("max_load", rs.getDouble("max_load_wattage") + "");
				map.put("min_solar", rs.getDouble("min_solar_wattage") + "");
				map.put("min_load", rs.getDouble("min_load_wattage") + "");
				map.put("avg_solar", rs.getDouble("avg_solar_wattage") + "");
				map.put("avg_load", rs.getDouble("avg_load_wattage") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static double[] ApplianceSummary(int appid) {

		ArrayList<String> timelist = getApplianceSummaryTime(appid);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getApplianceSummaryWatt(appid);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);

		// int[] watts = new int[watt.length];
		// int[] times = new int[time.length];

		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		double total_time = 0.0;
		int i, start = 0;
		int times = 0;
		double total_watt_hours, counter = 0.0;
		double[] returnvalues;
		returnvalues = new double[] { 0.0, 0.0, 0.0 };
		counter = 0;

		total_watt_hours = 0.0;
		start = 0;
		double sum = 0.0;

		//

		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 15
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_hours > 0) {

						hours = ((Double.valueOf(time[i]) - start) / 3600);
						total_time = total_time + hours;
						this_time_WH = sum * hours;
						total_watt_hours = total_watt_hours + this_time_WH;
						System.out.println("no.of time: " + times
								+ " and watt is = " + this_time_WH + " in "
								+ hours + " and watt hour = "
								+ total_watt_hours);
					} else {
						hours = ((Double.valueOf(time[i]) - start) / 3600);
						total_time = hours;
						total_watt_hours = sum * hours;
						System.out.println("no.of time: " + times
								+ " and watt is = " + total_watt_hours + "in "
								+ hours + " and watt hour = "
								+ total_watt_hours);
					}
					sum = 0;
					counter = 0;
				}

			}

		}
		// int c=(int)(a/3600);
		returnvalues[0] = total_watt_hours;
		returnvalues[1] = total_time;
		returnvalues[2] = (total_time * 3600 % 3600) % 60;
		return returnvalues;

	}

	public static double ApplianceLoadSummary(int appid) {

		ArrayList<String> timelist = getLoadSummaryTime(appid);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getloadSummaryWatt(appid);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);

		// int[] watts = new int[watt.length];getloadSummaryWatt
		// int[] times = new int[time.length];

		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		int i, start = 0;
		int times = 0;
		double total_watt_consume, counter = 0.0;
		counter = 0;
		total_watt_consume = 0.0;
		start = 0;
		double sum = 0.0;

		//

		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 1
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_consume > 0) {

						hours = ((Double.valueOf(time[i]) - start) / 3600);
						this_time_WH = sum * hours;
						total_watt_consume = total_watt_consume + this_time_WH;
						System.out.println("no.of time: " + times
								+ " and watt is = " + this_time_WH + " in "
								+ hours + " and watt hour = "
								+ total_watt_consume);
						System.out.println("Currnet index is : " + i);
					} else {
						hours = ((Double.valueOf(time[i]) - start) / 3600);
						total_watt_consume = sum * hours;
						System.out.println("no.of time: " + times
								+ " and watt is = " + total_watt_consume
								+ "in " + hours + " and watt hour = "
								+ total_watt_consume);
						System.out.println("Currnet index is : " + i);

					}
					sum = 0;
					counter = 0;
				}

			}

		}

		return Math.round(total_watt_consume);

	}

	public static ArrayList<String> getApplianceSummaryTime(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getLoadSummaryTime(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getloadSummaryWatt(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("loadwatt") + " ");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getApplianceSummaryWatt(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("watt") + " ");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static double[] ApplianceSummaryDateWise(int appid, String date) {

		ArrayList<String> timelist = getApplianceSummaryTimeDateWise(appid,
				date);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getApplianceSummaryWattDateWise(appid,
				date);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);

		// int[] watts = new int[watt.length];
		// int[] times = new int[time.length];

		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		double total_time = 0.0;
		int i, start = 0;
		int times = 0;
		double total_watt_hours, counter = 0.0;
		double[] returnvalues;
		returnvalues = new double[] { 0.0, 0.0, 0.0 };
		counter = 0;

		total_watt_hours = 0.0;
		start = 0;
		double sum = 0.0;

		//

		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 15
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_hours > 0) {

						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_time = total_time + hours;
						this_time_WH = sum * hours;
						total_watt_hours = total_watt_hours + this_time_WH;
						// System.out.println("no.of time: "+ times +
						// " and watt is = " + this_time_WH +
						// " in "+ hours + " and watt hour = "+ total_watt_hours
						// );
					} else {
						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_time = hours;
						total_watt_hours = sum * hours;
						// System.out.println("no.of time: "+ times +
						// " and watt is = " + sum +
						// "in "+ total_time + " and watt hour = "+
						// total_watt_hours );

					}
					sum = 0;
					counter = 0;
				}

			}

		}
		// int c=(int)(a/3600);
		returnvalues[0] = total_watt_hours;
		returnvalues[1] = total_time;
		returnvalues[2] = (total_time * 3600 % 3600) / 60;
		return returnvalues;

	}

	public static double ApplianceLoadSummaryDateWise(int appid, String date) {

		ArrayList<String> timelist = getLoadSummaryTimeDateWise(appid, date);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getloadSummaryWattDateWise(appid, date);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);

		// int[] watts = new int[watt.length];getloadSummaryWatt
		// int[] times = new int[time.length];

		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		int i, start = 0;
		int times = 0;
		double total_watt_consume, counter = 0.0;
		counter = 0;
		total_watt_consume = 0.0;
		start = 0;
		double sum = 0.0;

		//

		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 1
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_consume > 0) {

						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						this_time_WH = sum * hours;
						total_watt_consume = total_watt_consume + this_time_WH;
						// System.out.println("no.of time: "+ times +
						// " and watt is = " + this_time_WH +
						// " in "+ hours + " and watt hour = "+
						// total_watt_consume );
						// System.out.println("Currnet index is : "+i);
					} else {
						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_watt_consume = sum * hours;
						// System.out.println("no.of time: "+ times +
						// " and watt is = " + sum +
						// "in "+ hours + " and watt hour = "+
						// total_watt_consume );
						// System.out.println("Currnet index is : "+i);

					}
					sum = 0;
					counter = 0;
				}

			}

		}

		return Math.round(total_watt_consume);

	}

	public static ArrayList<String> getApplianceSummaryTimeDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getLoadSummaryTimeDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getloadSummaryWattDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("loadwatt") + " ");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getApplianceSummaryWattDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("watt") + " ");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String args[]) {
		System.out.println(getStatusInfo(302));
	}

}
