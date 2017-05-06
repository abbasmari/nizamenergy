package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.FinanceBean;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class ReportAnalyticsBAL {
	
	final static Logger logger = Logger.getLogger(ReportAnalyticsBAL.class);
	
	public static ArrayList<HashMap<String, String>> getDoWiseLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{CALL get_do_wise_late_defaulter()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("user_name", resultSet.getString(1) + "");
					map.put("district_name", resultSet.getString(2) + "");
					map.put("late", resultSet.getInt(3) + "");
					map.put("defaulter", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getDoWiseLateDefaulters
	
	public static ArrayList<HashMap<String, String>> getFoWiseLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{CALL get_fo_wise_late_defaulter()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fo_name", resultSet.getString(1) + "");
					map.put("district_name", resultSet.getString(2) + "");
					map.put("late", resultSet.getInt(3) + "");
					map.put("defaulter", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getFoWiseLateDefaulters
	
	public static ArrayList<HashMap<String, String>> getFoWiseLateDefaultersFilter(int start, int length, int col, String order) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		
		String district_name, field_officer;
		int late, defaulters;
		
		FinanceBean bean = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {

			call = con.prepareCall("{CALL get_fo_wise_late_defaulter_filter(?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, col);
			call.setString(4, order);

			rs = call.executeQuery();
			while (rs.next()) {
				
				HashMap<String, String> map = new HashMap<>();
				map.put("fo_name", rs.getString(1));
				map.put("district_name", rs.getString(2));
				map.put("late", rs.getInt(3) + "");
				map.put("defaulter", rs.getInt(4) + "");
				

//				district_name = rs.getString(2);
//
//				field_officer = rs.getString(1);
//				late = rs.getInt(3);
//				defaulters = rs.getInt(4);
//				
//				bean = new FinanceBean();
//				bean.setDistrict_name(district_name);
//				bean.setFo_name(field_officer);
				
				list.add(map);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;

	}
	
	public static int getFoWiseLateDefaultersCount() {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_fo_wise_late_defaulter_count()}");

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
	
	public static ArrayList<HashMap<String, String>> getFoWiseLateDefaulterSearchFilter(int start, int length, int col, String order,
			String value) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {

			call = con.prepareCall("{call get_fo_wise_late_defaulter_search_filter(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, col);
			call.setString(4, order);
			call.setString(5, value);

			rs = call.executeQuery();
			while (rs.next()) {

				HashMap<String, String> map = new HashMap<>();
				map.put("fo_name", rs.getString(1));
				map.put("district_name", rs.getString(2));
				map.put("late", rs.getInt(3) + "");
				map.put("defaulter", rs.getInt(4) + "");

				// bean.setDue_date(due_Date);
				list.add(map);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;

	}
	
	public static int getFoWiseLateDefaultersSearchCount(String value) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_fo_wise_late_defaulter_search_count(?)}");
			call.setString(1, value);
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

	public static void main(String[] args) {
//		System.err.println(getDoWiseLateDefaulters());
//		System.err.println(getFoWiseLateDefaulters());		
//		System.err.println(getFoWiseLateDefaulterSearchFilter(0, 100, 3, "asc", "layyah"));
//		System.err.println(getFoWiseLateDefaultersSearchCount("layyah"));
//		System.err.println(getFoWiseLateDefaultersFilter(0, 10, 3, "asc"));
//		System.err.println(getFoWiseLateDefaultersCount());
	}

}
