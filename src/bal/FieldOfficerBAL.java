package bal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import bean.UpComingRevocoveries;

import java.sql.Connection;

import connection.Connect;

public class FieldOfficerBAL {

	final static Logger logger = Logger.getLogger(FieldOfficerBAL.class);

	public static HashMap<String, String> getFieldOfficerDetail(int foID) {
		HashMap<String, String> map = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_field_officer_details(?)}");
			call.setInt(1, foID);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("fo_id", rs.getInt("fo_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("Cnic", rs.getString("fo_cnic"));
				map.put("foNumber", rs.getString("fo_priamary_phone"));
				map.put("address", rs.getString("fo_address"));
				map.put("DistrictOfficer", rs.getString("user_name"));
				map.put("District", rs.getString("district_name"));
				map.put("salary", rs.getString("fo_base_salary"));
				map.put("per_sale", rs.getInt("per_sale") + "");
				map.put("fo_acount_no", rs.getString("fo_acount_no") + "");
				map.put("foontime", rs.getInt("fo_on_time") + "");
				map.put("imageId", rs.getInt("image_id") + "");
				map.put("city", rs.getString("fo_city"));
				map.put("date_of_birth", rs.getString("fo_date_of_birth"));
				map.put("date_of_joining", rs.getString("fo_date_of_joining"));
				map.put("user_id", rs.getString("user_id"));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	// For full detail of fo
	public static HashMap<String, String> getFieldOfficerDetailForUpdate(
			int foId) {
		HashMap<String, String> map = null;

		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_field_officer_details(?)}");
			call.setInt(1, foId);
			rs = call.executeQuery();

			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}

			if (rs.next()) {
				map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getFOlist(int start,
			int length, String order, int column, int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list1(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foid", rs.getInt("fo_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("last_sale", rs.getString("last_sale"));
				map.put("vles", rs.getString("vles"));
				map.put("activ_vles", rs.getString("activ_vles"));
				map.put("total_apps", rs.getString("total_apps"));
				map.put("installed_apps", rs.getString("installed_apps"));
				map.put("currentMonthApps", rs.getString("currentMonthApps"));
				map.put("currentweekApps", rs.getString("currentweekApps"));
				map.put("currentMonthInstalls",
						rs.getString("currentMonthInstalls"));
				map.put("currentWeekInstalls",
						rs.getString("currentWeekInstalls"));
				map.put("recovery_rate", rs.getString("recovery_rate"));
				map.put("currentWeekInstalls",
						rs.getString("currentWeekInstalls"));
				map.put("recovery_rate", rs.getString("recovery_rate"));
				list.add(map);
			}
			// ResultSetMetaData metaData = rs.getMetaData();
			// String columns[] = new String[metaData.getColumnCount()];
			//
			// for (int i = 0; i < columns.length; i++) {
			// columns[i] = metaData.getColumnLabel(i+1);
			// }
			//
			// while (rs.next()) {
			// map = new HashMap<>();
			//
			// for (int i = 0; i < columns.length; i++) {
			// columns[i] = metaData.getColumnLabel(i+1);
			//
			// map.put(columns[i], rs.getString(columns[i]));
			// }
			// list.add(map);
			// }
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesman(int foID) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_fo_salesman(?) }");
			call.setInt(1, foID);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("SalesName", rs.getString("salesman_name"));
				map.put("Cnic", rs.getString("salesman_cnic"));
				map.put("Address", rs.getString("salesman_address"));
				map.put("District", rs.getString("salesman_district"));
				map.put("date", rs.getString("salesman_date_join"));
				map.put("Phone", rs.getString("salesman_phone_no"));
				map.put("Salary", rs.getDouble("salesman_basic_sallery") + "");
				map.put("SalesMan_id", rs.getInt("salesman_id") + "");
				map.put("lateCustomer", rs.getInt("latecustomer") + "");
				map.put("Sales", rs.getInt("Sales") + "");
				map.put("salesmanID", rs.getInt("salesman_id") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getSalesmen(int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_salesmen(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFieldOfficers(int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_field_officers(?)}");
			call.setInt(1, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foid", rs.getString("fo_id"));
				map.put("foName", rs.getString("fo_name"));
				map.put("foPhone", rs.getString("fo_priamary_phone"));
				map.put("cnic", rs.getString("fo_cnic"));
				map.put("address", rs.getString("fo_address"));
				map.put("salary", rs.getString("fo_base_salary"));
				map.put("joiningDate", rs.getString("fo_date_of_joining"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getFOlistCount(String order, int column, int userId) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list_count(?,?,?)}");
			call.setString(1, order);
			call.setInt(2, column);
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

	public static ArrayList<HashMap<String, String>> getFOlistSearch(int start,
			int length, String order, int column, int userId, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list_search(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, userId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foid", rs.getInt("fo_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("last_sale", rs.getString("last_sale"));
				map.put("vles", rs.getString("vles"));
				map.put("activ_vles", rs.getString("activ_vles"));
				map.put("total_apps", rs.getString("total_apps"));
				map.put("installed_apps", rs.getString("installed_apps"));
				map.put("currentMonthApps", rs.getString("currentMonthApps"));
				map.put("currentweekApps", rs.getString("currentweekApps"));
				map.put("currentMonthInstalls",
						rs.getString("currentMonthInstalls"));
				map.put("currentWeekInstalls",
						rs.getString("currentWeekInstalls"));
				map.put("recovery_rate", rs.getString("recovery_rate"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getFOlistSearchCount(String order, int column,
			int userId, String search) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list_search_count(?,?,?,?)}");
			call.setString(1, order);
			call.setInt(2, column);
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

	public static ArrayList<HashMap<String, String>> getDefaultedCustomersByFO(
			int foId) {
		System.out.println("FieldOfficerBAL.getDefaultedCustomersByFO()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_defaulted_customers_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static String getPassword(String phoneNumber) {

		String query = "SELECT fo.password FROM field_officer fo WHERE fo.fo_priamary_phone = ?";
		String password = "";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, phoneNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				password = rs.getString(1);

			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return password;

	}

	public static HashMap<String, String> login(String cellNumber,
			String password) {
		HashMap<String, String> map = null;

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL login_fo(?, ?)}");
			prepareCall.setString(1, cellNumber);
			prepareCall.setString(2, password);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			String columns[] = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			if (resultSet.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				// map.put(columns[columns.length - 1], Base64.encode(resultSet
				// .getBytes(columns[columns.length - 1])));
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();

		}
		return map;
	}

	public static HashMap<String, String> getJoiningDateByFoId(int foId) {

		String query = "SELECT fo.fo_date_of_joining,fo.fo_before_time,fo.fo_on_time,fo.fo_after_time FROM field_officer fo WHERE fo.fo_id = ?";
		HashMap<String, String> map = new HashMap<String, String>();
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, foId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("joiningDate", rs.getString(1));
				map.put("bt", "" + rs.getInt(2));
				map.put("ot", "" + rs.getInt(3));
				map.put("at", "" + rs.getInt(4));

			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;

	}

	public static String getNameById(int foId) {

		String query = "SELECT fo.fo_name FROM field_officer fo WHERE fo.fo_id =  ?";
		String name = "";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, foId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);

			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return name;

	}

	public static List<UpComingRevocoveries> upComingRevocoveriesByFOId(int foId) {
		System.err
				.println("SalesmanBAL.get_upcoming_recoveries_of_salesman(?)");

		List<UpComingRevocoveries> upComingRevocoveries = new ArrayList<UpComingRevocoveries>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_upcoming_recoveries_of_field_officer(?)}");
			prepareCall.setInt(1, foId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				UpComingRevocoveries uRevocoveries = new UpComingRevocoveries();
				uRevocoveries.setCustomerName(rs.getString(2));
				uRevocoveries.setApplianceName(rs.getString(3));
				uRevocoveries.setRemainingDays("" + rs.getInt(4));
				upComingRevocoveries.add(uRevocoveries);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return upComingRevocoveries;
	}

	public static HashMap<String, String> getFOCounters(int foId) {

		HashMap<String, String> map = new HashMap<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement cstmt = con
					.prepareCall("{CALL get_counts_for_android_app_fo(?)}");
			cstmt.setInt(1, foId);
			ResultSet resutlSet = cstmt.executeQuery();
			ResultSetMetaData metaDeta = resutlSet.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaDeta.getColumnLabel(a + 1);
			}
			if (resutlSet.next()) {
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resutlSet.getString(columns[i]));
				}
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getVLECounters(int vleId) {

		HashMap<String, String> map = new HashMap<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement cstmt = con
					.prepareCall("{CALL get_counts_for_android_app_vle(?)}");
			cstmt.setInt(1, vleId);
			ResultSet resutlSet = cstmt.executeQuery();
			ResultSetMetaData metaDeta = resutlSet.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaDeta.getColumnLabel(a + 1);
			}
			if (resutlSet.next()) {
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resutlSet.getString(columns[i]));
				}
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getAllFieldOfficers() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_field_officer_data()}");
			rs = call.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllFieldOfficersById(
			int doid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection();) {
			call = con.prepareCall("{Call get_all_field_officers_by_doid(?)}");
			call.setInt(1, doid);
			rs = call.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);

					map.put(columns[i], rs.getString(columns[i]));
				}

				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDefaultCustomerSpFo(
			int foid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement cs = connection
					.prepareCall("call get_fo_default_customers(?)");
			cs.setInt(1, foid);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDefaultCustomerDoFo(
			int foid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement cs = connection
					.prepareCall("call get_fo_default_customers(?)");
			cs.setInt(1, foid);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getProductPrice() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_product_price()}");

			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("product_id", "" + rs.getInt("product_id"));
				map.put("product_name", rs.getString("product_name"));
				map.put("product_price", rs.getString("product_price"));

				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> checkFoHaveNetwork(int foId) {

		HashMap<String, String> map = null;
		try (Connection con = connection.Connect.getConnection();) {
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{CALL check_fo_have_network(?)}");
			prepareCall.setInt(1, foId);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("fo_id", "" + rs.getString("fo.fo_id"));
				map.put("foName", "" + rs.getString("fo.fo_name"));
				map.put("district", "" + rs.getString("district_name"));
				map.put("Have_Network", "" + rs.getString("Have_Network"));
				map.put("bulkFo", "" + rs.getString("bulkFo"));
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	} // end of checkFoHaveNetwork

	public static HashMap<String, String> checkSalesmanHaveNetwork(
			int salesmanId) {
		HashMap<String, String> map = null;
		String network = "";
		try (Connection con = connection.Connect.getConnection();) {
			ResultSet rs = null;

			CallableStatement prepareCall = con
					.prepareCall("{CALL check_nd_have_network(?)}");
			prepareCall.setInt(1, salesmanId);
			rs = prepareCall.executeQuery();

			while (rs.next()) {
				map = new HashMap<>();
				map.put("ndId", "" + rs.getString("salesman_id"));
				map.put("ndName", "" + rs.getString("salesman_name"));
				network = rs.getString("Have_Network");
				map.put("Have_Network", "" + network);
				map.put("district", "" + rs.getString("district_name"));
				map.put("bulkNd", "" + rs.getString("bulkNd"));
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	} // end of checkSalesmanHaveNetwork

	public static ArrayList<HashMap<String, String>> getBulkUsers() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection con = connection.Connect.getConnection();) {
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_bulk_users()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("fo_id", "" + rs.getInt("fo.fo_id"));
				map.put("fo_name", "" + rs.getString("fo.fo_name"));
				list.add(map);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	} // end of getBulkUsers

	public static String updateFo(int foIdd) {
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		String search = "Bulk";
		int foId = 0;
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_nd_list(?)}");
			prepareCall.setInt(1, foIdd);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foId", "" + rs.getInt("fo_id"));
				map.put("foName", "" + rs.getString("fo_name"));
				map.put("ndId", "" + rs.getInt("salesman_id"));
				map.put("doId", "" + rs.getInt("do_id"));
				list.add(map);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		Statement s = null;

		try (Connection con = Connect.getConnection();) {
			s = con.createStatement();
			rs = s.executeQuery("SELECT fo.fo_id FROM field_officer fo JOIN `nizamdb_tester`.`user` u ON u.user_id = fo.do_id "
					+ "JOIN district d ON d.district_id = u.user_district WHERE u.`user_id` = "
					+ list.get(0).get("doId")
					+ " AND fo.fo_name LIKE '%Bulk%' ");
			if (rs.next()) {
				foId = rs.getInt("fo_id");
				System.err.println("foId" + foId);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		if (foId != 0) {
			for (int i = 0; i < list.size(); i++) {
				try (Connection con = Connect.getConnection();) {
					s = con.createStatement();
					s.executeUpdate("UPDATE `salesman` SET fo_id = " + foId
							+ ",last_fo = '" + list.get(i).get("foName")
							+ "' WHERE `salesman_id` = "
							+ list.get(i).get("ndId") + " And fo_id = "
							+ list.get(i).get("foId") + " ");
				} catch (Exception ex) {
					logger.error("", ex);
					ex.printStackTrace();
				}
			}

			try (Connection con = Connect.getConnection();) {
				s = con.createStatement();
				s.executeUpdate("UPDATE `field_officer` SET is_left = 1 WHERE `fo_id` = "
						+ foIdd + "");
			} catch (Exception ex) {
				logger.error("", ex);
				ex.printStackTrace();
			}
		}
		return "Ok Done";
	}

	public static String updateNizamDost(int ndIdd) {
		ResultSet rs = null;
		Statement s = null;
		ArrayList<HashMap<String, String>> listEl = new ArrayList<>();
		ArrayList<HashMap<String, String>> listSold = new ArrayList<>();
		int ndId = 0;
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_list(?)}");
			prepareCall.setInt(1, ndIdd);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foId", "" + rs.getInt("fo_id"));
				map.put("ndId", "" + rs.getInt("salesman_id"));
				map.put("doId", "" + rs.getInt("user_id"));
				listEl.add(map);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		System.err.println("listEl " + listEl);
		try (Connection con = Connect.getConnection();) {
			String query = "SELECT e.`sold_to_id`,a.`appliance_id`,s.salesman_id, fo.`fo_id`,u.`user_id` FROM `sold_to` e JOIN `customer` "
					+ "cs ON cs.`customer_id` = e.`customer_id` JOIN `appliance` a ON a.`appliance_id` = e.`appliance_id`"
					+ " JOIN `salesman` s ON s.`salesman_id` = e.`salesman_id` JOIN `field_officer` fo  "
					+ "ON fo.`fo_id` = s.`fo_id` JOIN user u ON u.`user_id` = fo.`do_id` WHERE s.`salesman_id` ="
					+ ndIdd;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foId", "" + rs.getInt("fo_id"));
				map.put("ndId", "" + rs.getInt("salesman_id"));
				map.put("doId", "" + rs.getInt("user_id"));
				listSold.add(map);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		System.err.println(listSold);

		try (Connection con = Connect.getConnection();) {
			s = con.createStatement();
			rs = s.executeQuery("select salesman_id from salesman s JOIN field_officer fo on fo.fo_id = s.fo_id WHERE `do_id`= "
					+ listEl.get(0).get("doId")
					+ " AND salesman_name LIKE '%Bulk%' ");
			if (rs.next()) {
				ndId = rs.getInt("salesman_id");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		System.err.println("Nd Id : " + ndId);
		if (ndId != 0) {
			for (int i = 0; i < listEl.size(); i++) {
				try (Connection con = Connect.getConnection();) {
					s = con.createStatement();
					s.executeUpdate("UPDATE `eligibility` SET salesman_id = "
							+ ndId + " WHERE `salesman_id` = "
							+ listEl.get(i).get("ndId") + " ");
				} catch (Exception ex) {
					logger.error("", ex);
					ex.printStackTrace();
				}
			}

			for (int i = 0; i < listSold.size(); i++) {
				try (Connection con = Connect.getConnection();) {
					s = con.createStatement();
					s.executeUpdate("UPDATE `sold_to` SET salesman_id = "
							+ ndId + " WHERE `salesman_id` = "
							+ listSold.get(i).get("ndId") + " ");
				} catch (Exception ex) {
					logger.error("", ex);
					ex.printStackTrace();
				}
			}

			try (Connection con = Connect.getConnection();) {
				s = con.createStatement();
				s.executeUpdate("UPDATE `salesman` SET is_left = 1 WHERE `salesman_id` = "
						+ ndIdd + "");
			} catch (Exception ex) {
				logger.error("", ex);
				ex.printStackTrace();
			}
		}
		return "Ok Done";
	}

	public static void main(String[] args) {
		System.out.println(updateNizamDost(414));
	}

}
