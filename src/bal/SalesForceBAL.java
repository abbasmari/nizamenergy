package bal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.itextpdf.text.log.SysoLogger;

import connection.Connect;

public class SalesForceBAL {

	final static Logger logger = Logger.getLogger(SalesForceBAL.class);

	public static HashMap<String, String> map = null;

	// public static List<List<HashMap<String, String>> list = null;

	public static List<HashMap<String, String>> getActiveND() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try (Connection con = Connect.getConnection()) {
			// List<HashMap<String, String>> list=new
			// ArrayList<HashMap<String,String>>();
			if (con != null) {

				CallableStatement prepareCall = con
						.prepareCall("CALL distirct_wise_active_total_weekly_monthly()");
				ResultSet rs = prepareCall.executeQuery();

				while (rs.next()) {
					HashMap<String, String> map = null;
					map = new HashMap<String, String>();

					map.put("DistrictName", rs.getString("district_name"));
					map.put("total", rs.getInt("total") + "");
					map.put("UserName", rs.getString("user_name"));
					map.put("TotalFO", rs.getInt("total_Fo") + "");
					map.put("TotalND", rs.getInt("total_salesman") + "");
					map.put("weekly", rs.getString("weekly"));
					map.put("monthly", rs.getString("monthly"));
					// map.put("InstalledApps",
					// getInstalledApplications().get(0).get("InstalledApplications"));
					// map.put("activeSalesmanMonthly",
					// district_wise_active_nd_month_wise().get(0).get("activeSalesmanMonthly"));
					// map.put("salesmanActiveWeekly",district_wise_active_nd_week_wise().get(0).get("salesmanActiveWeekly"));
					// map.put("TotalAppsWeekly",
					// district_wise_total_applications_week_wise().get(0).get("TotalAppsWeekly"));
					// map.put("TotalAppsMonthly",
					// district_wise_total_applications_month_wise().get(0).get("TotalAppsMonthly"));
					// map.put("installedAppsWeekly",
					// district_wise_installed_applications_week_wise().get(0).get("installedAppsWeekly"));
					// map.put("installedAppsMonthly",
					// district_wise_installed_applications_month_wise().get(0).get("installedAppsMonthly"));
					list.add(map);
				}
				System.err.println(list.size());
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static List<HashMap<String, String>> getTotalApplications() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("CALL district_wise_total_applications()");
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = null;

				map = new HashMap<String, String>();
				map.put("DistrictName", rs.getString("district_name"));
				map.put("total", rs.getInt("total") + "");
				map.put("UserName", rs.getString("user_name"));
				map.put("weekly", rs.getString("weekly"));
				map.put("monthly", rs.getString("monthly"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		list.removeAll(Collections.singleton(null));
		return list;

	}

	public static List<HashMap<String, String>> getInstalledApplications() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("CALL district_wise_installed_applications()");
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();

				map.put("DistrictName", rs.getString("district_name"));
				map.put("total", rs.getInt("total") + "");
				map.put("UserName", rs.getString("user_name"));
				map.put("weekly", rs.getString("weekly"));
				map.put("monthly", rs.getString("monthly"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		list.removeAll(Collections.singleton(null));
		return list;

	}

	public static ArrayList<HashMap<String, String>> district_wise_active_nd_month_wise() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		Connection con = Connect.getConnection();

		try {
			CallableStatement prepareCall = con
					.prepareCall("{CALL district_wise_active_nd_month_wise()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();
				map.put("activeSalesmanMonthly",
						resultSet.getInt("active_salesman") + "");

				// map.put("district_name",
				// resultSet.getString("district_name"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> district_wise_active_nd_week_wise() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		Connection con = Connect.getConnection();
		try {
			CallableStatement prepareCall = con
					.prepareCall("{CALL district_wise_active_nd_week_wise()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();
				map.put("salesmanActiveWeekly",
						resultSet.getString("active_salesman"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		list.removeAll(Collections.singleton(null));
		return list;
	}

	public static ArrayList<HashMap<String, String>> district_wise_installed_applications_month_wise() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		Connection con = Connect.getConnection();
		try {
			CallableStatement prepareCall = con
					.prepareCall("{CALL district_wise_installed_applications_month_wise()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();
				map.put("installedAppsMonthly",
						resultSet.getInt("installed_apps") + "");

				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> district_wise_installed_applications_week_wise() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		Connection con = Connect.getConnection();
		try {
			CallableStatement prepareCall = con
					.prepareCall("{CALL district_wise_installed_applications_week_wise()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();

				map.put("installedAppsWeekly",
						resultSet.getInt("installed_apps") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> district_wise_total_applications_month_wise() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		Connection con = Connect.getConnection();
		try {
			CallableStatement prepareCall = con
					.prepareCall("{CALL district_wise_total_applications_month_wise()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();
				map.put("TotalAppsMonthly", resultSet.getInt("all_apps") + "");

				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> district_wise_total_applications_week_wise() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		Connection con = Connect.getConnection();
		try {
			CallableStatement prepareCall = con
					.prepareCall("{CALL district_wise_total_applications_week_wise()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = null;
				map = new HashMap<String, String>();
				map.put("TotalAppsWeekly", resultSet.getInt("all_apps") + "");

				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		List<HashMap<String, String>> list = SalesForceBAL.getActiveND();
		System.out.println(getInstalledApplications());
		System.out.println(getActiveND());

	}

}
