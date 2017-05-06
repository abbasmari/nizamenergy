/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.CityBean;
import bean.DistrictBean;
import bean.DistrictOfficerBean;
import connection.Connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

/**
 * 
 * @author waseem
 */
public class DistrictOfficerBAL {
	static Connection con;
	// static Statement st = null;
	final static Logger logger = Logger.getLogger(DistrictOfficerBAL.class);

	public static ArrayList<DistrictOfficerBean> getDistrictList() {
		System.out.print("DistrictOfficerBAL.get_district_officer_list");
		ArrayList<DistrictOfficerBean> list = new ArrayList<DistrictOfficerBean>();
		DistrictOfficerBean bean = null;
		int do_id, total_sale;
		String do_name, do_district, date;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_do_list_for_superadmin()}");
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				do_name = rs.getString(1);
				do_district = rs.getString(2);
				total_sale = rs.getInt(3);
				do_id = rs.getInt(4);
				date = rs.getString(5);
				bean = new DistrictOfficerBean();
				bean.setDistrict_name(do_district);
				bean.setDo_name(do_name);
				bean.setTotalsale(total_sale);
				bean.setDo_id(do_id);
				bean.setDate(date);
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static DistrictOfficerBean getDO_details(int do_id) {
		System.out.println("DistrictOfficerBAL.getDO_details()");
		DistrictOfficerBean bean = null;
		String do_name, do_cnic, do_phone, do_district, do_address;
		double sallery;
		double latitude;
		double longitude;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_detail(?)}");
			prepareCall.setInt(1, do_id);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				do_name = resultSet.getString(1);
				do_cnic = resultSet.getString(2);
				do_phone = resultSet.getString(3);
				sallery = resultSet.getDouble(4);
				do_district = resultSet.getString(5);
				do_address = resultSet.getString(6);
				latitude = resultSet.getDouble(7);
				longitude = resultSet.getDouble(8);
				System.out.println(longitude);
				bean = new DistrictOfficerBean();
				bean.setDo_name(do_name);
				bean.setCnic(do_cnic);
				bean.setPhone(do_phone);
				bean.setDistrict_name(do_district);
				bean.setAddress(do_address);
				bean.setSallery(sallery);
				bean.setLatitude(latitude);
				bean.setLongitutde(longitude);
				bean.setUseremail(resultSet.getString("user_email"));
				bean.setDateofjoining(resultSet.getString("joining_date"));
				bean.setDateofbirth(resultSet.getString("date_of_birth"));
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static ArrayList<CityBean> getDistrictCities(int district) {

		ResultSet rs = null;
		ArrayList<CityBean> city_list = new ArrayList<CityBean>();
		int city_id;
		String city_name;
		CityBean bean;
		// String query =
		// "SELECT city.city_id,city.city_name FROM city JOIN city_district ON city.city_id=city_district.city_id WHERE district_id="+district;
		String query = "SELECT city.city_id,city.city_name,dis.`district_name` FROM city "
				+ "JOIN city_district ON city.city_id=city_district.city_id "
				+ "JOIN `district` dis ON dis.`district_id`=`city_district`.`district_id` "
				+ "WHERE city_district.district_id=" + district;
		try (Connection con = Connect.getConnection()) {

			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				city_id = rs.getInt(1);
				city_name = rs.getString(2);
				bean = new CityBean();
				bean.setCity_id(city_id);
				bean.setCity_name(city_name);
				bean.setDistrict_name(rs.getString("district_name"));
				city_list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return city_list;
	}

	public static ArrayList<DistrictBean> getDistrict() {

		ResultSet rs = null;
		ArrayList<DistrictBean> city_list = new ArrayList<DistrictBean>();
		int district_id;
		String district_name;
		DistrictBean bean;
		// String query = "SELECT city.city_id,city.city_name FROM city JOIN
		// city_district ON city.city_id=city_district.city_id WHERE
		// district_id="+district;
		String query = "SELECT district_id, district_name FROM district";
		try (Connection con = Connect.getConnection()) {
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				district_id = rs.getInt(1);
				district_name = rs.getString(2);
				bean = new DistrictBean();
				bean.setDistrict_id(district_id);
				bean.setDistrict_name(district_name);
				city_list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return city_list;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDistricOfficerListWithOrder(
			int start, int length, int order, String dir) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs;

		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL getDodata(?,?,?,?)}");
			// ascending order

			if (call != null) {
				call.setInt(1, start);
				call.setInt(2, length);
				call.setString(3, dir);
				call.setInt(4, order);
				rs = call.executeQuery();
				while (rs.next()) {

					HashMap<String, String> map = new HashMap<>();
					map.put("userId", rs.getInt("user_id") + "");
					map.put("userName", rs.getString("district_name") + ",  "
							+ rs.getString("user_name"));
					map.put("districtName", rs.getString("district_name"));
					map.put("fos", rs.getInt("fos") + "");
					map.put("vle", rs.getInt("vle") + "");
					map.put("activeND", rs.getInt("activ_vle") + "");
					map.put("totalApps", rs.getInt("total_Apps") + "");
					map.put("Installed", rs.getInt("total_installed") + "");
					map.put("monthlyInstalled", rs.getInt("monthly_installed")
							+ "");
					map.put("weeklyInstalled", rs.getInt("weekly_installed")
							+ "");
					map.put("monthlyApps", rs.getInt("monthly_apps") + "");
					map.put("weeklyApps", rs.getInt("weekly_apps") + "");
					map.put("recovery", rs.getString("recovery") + " %");
					list.add(map);
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	// created by Junaid Ali
	public static int getDistrictOfficerListCount() {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_district_officer_list_count()}");
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDistrictOfficerListWithSearch(
			int start, int length, String order, int col, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL getDoDataSearch(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("userId", rs.getInt("user_id") + "");
				map.put("userName",
						rs.getString("district_name") + ",  "
								+ rs.getString("user_name"));
				map.put("districtName", rs.getString("district_name"));
				map.put("fos", rs.getInt("fos") + "");
				map.put("vle", rs.getInt("vle") + "");
				map.put("activeND", rs.getInt("activ_vle") + "");
				map.put("totalApps", rs.getInt("total_Apps") + "");
				map.put("Installed", rs.getInt("total_installed") + "");
				map.put("monthlyInstalled", rs.getInt("monthly_installed") + "");
				map.put("weeklyInstalled", rs.getInt("weekly_installed") + "");
				map.put("monthlyApps", rs.getInt("monthly_apps") + "");
				map.put("weeklyApps", rs.getInt("weekly_apps") + "");
				map.put("recovery", rs.getString("recovery") + " %");
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getDistrictOfficerListBySearchCount(String a) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_district_officer_list_search_count(?)}");
			call.setString(1, a);
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

	public static ArrayList<HashMap<String, String>> getDistrictOfficerList() {

		ResultSet rs = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int district_id;
		String district_name;
		DistrictBean bean;
		// String query = "SELECT city.city_id,city.city_name FROM city JOIN
		// city_district ON city.city_id=city_district.city_id WHERE
		// district_id="+district;
		String query = "SELECT u.user_id, u.user_name FROM user u  WHERE u.user_type = 101 ORDER BY u.user_name ";
		try (Connection con = Connect.getConnection()) {
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			HashMap<String, String> map;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("userId", "" + rs.getInt(1));
				map.put("userName", rs.getString(2));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String arg[]) throws IOException {

		for (int i = 0; i < 50000; i++) {

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = 5 + (int) (Math.random() * ((15 - 5) + 1));

			int randomNum2 = 5 + (int) (Math.random() * ((15 - 5) + 1));
			String ur = "http://spg-cloud.tk/GetApplianceCommunication?st=18&IMEI=322039658144500&lat=0&lon=0&tp="
					+ randomNum2
					+ "&la="
					+ randomNum
					+ "&lv="
					+ randomNum2
					+ "&sa="
					+ randomNum
					+ "&sv="
					+ randomNum
					+ "&ba="
					+ randomNum + "&bv=" + randomNum + "&db=2";

			System.out.println(ur);
			// String
			// ur="http://221.132.117.58:7700/sendsms_url.html?Username=03028501626&Password=123.123&From=NizamEnergy&To=03002021964&Message=Test";
			URL url = new URL(ur);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Length", "500000");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type",
					"text/xml; charset=utf-8");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			HttpURLConnection conn = (HttpURLConnection) connection;
			conn.connect();
			Object content = conn.getContent();
			InputStream stream = (InputStream) content;
			String str = null;

			BufferedReader br = new BufferedReader(
					new InputStreamReader(stream));

			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}

		}

	}

}
