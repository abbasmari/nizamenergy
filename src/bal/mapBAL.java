/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.JSONException;

import connection.Connect;
import bean.InfowindowBean;
import bean.MapBean;
import bean.lat_long_bean;

public class mapBAL {

	private final static Logger logger = Logger.getLogger(mapBAL.class);

	public static InfowindowBean viewCashbean(int id) {
		// ArrayList<InfowindowBean> list = new ArrayList();
		String query = "SELECT app.appliance_name,"
				+ " cs.customer_name,"
				+ " IF(ast.latitude > 0, ast.latitude, 30) AS latitude,"
				+ " IF(ast.longitude >0, ast.longitude, 69) AS longitude,"
				+ " pl.total_installments, app.appliance_status FROM appliance app "
				+ " JOIN appliance_status ast "
				+ "	ON app.appliance_id = ast.appliance_id "
				+ "JOIN sold_to sld "
				+ "	ON app.appliance_id = sld.appliance_id "
				+ "JOIN customer cs " + "	ON sld.customer_id = cs.customer_id "
				+ "JOIN payment_loan pl "
				+ "	ON sld.sold_to_id = pl.soldto_id "
				+ "WHERE sld.payement_option = 1 AND app.appliance_id = " + id;

		Statement s = null;
		ResultSet rs = null;
		double latitude, longitude;
		String customer_name, app_name;
		int scheme, type;
		InfowindowBean bean = null;
		try (Connection con = Connect.getConnection()) {

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customer_name = rs.getString(2);
				app_name = rs.getString(1);
				latitude = rs.getDouble(3);
				longitude = rs.getDouble(4);
				scheme = rs.getInt(5);
				type = rs.getInt(6);
				bean = new InfowindowBean();
				bean.setLati(latitude);
				bean.setLongi(longitude);
				bean.setApp_name(app_name);
				bean.setCustomer_name(customer_name);
				bean.setScheme(scheme);
				bean.setType(type);
				// list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static InfowindowBean viewLatLong(int id) {
		// ArrayList<InfowindowBean> list = new ArrayList();

		ResultSet rs = null;
		double latitude, longitude;
		String customer_name, app_name;

		InfowindowBean bean = null;
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT app.appliance_name, cs.customer_name,ast.latitude,ast.longitude FROM sold_to sld "
					+ "JOIN appliance app ON app.appliance_id = sld.appliance_id JOIN appliance_status ast ON app.appliance_id = ast.appliance_id "
					+ "JOIN customer cs ON sld.customer_id= cs.customer_id WHERE sld.payement_option=1 AND sld.appliance_id ="
					+ id;

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			// stmt.setInt(1, id);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				app_name = rs.getString(1);
				customer_name = rs.getString(2);
				latitude = rs.getDouble(3);
				longitude = rs.getDouble(4);
				bean = new InfowindowBean();
				bean.setLati(latitude);
				bean.setLongi(longitude);
				bean.setApp_name(app_name);
				bean.setCustomer_name(customer_name);
				// list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static int differenceInMonths(Date d1, Date d2) {

		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		int diff = 0;
		try {
			if (c2.after(c1)) {
				while (c2.after(c1)) {
					c1.add(Calendar.MONTH, 1);
					if (c2.after(c1)) {
						diff++;
					}
				}
			} else if (c2.before(c1)) {
				while (c2.before(c1)) {
					c1.add(Calendar.MONTH, -1);
					if (c1.before(c2)) {
						diff--;
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return diff;
	}

	public static ArrayList<InfowindowBean> viewCash() {
		System.out.println("mapBAL.viewCash()");
		ArrayList<InfowindowBean> list = new ArrayList<InfowindowBean>();
		// String query = "SELECT DISTINCT(app.appliance_name),
		// cs.customer_name,ast.latitude,ast.longitude ,pl.total_installments
		// FROM appliance app \n"
		// + "JOIN appliance_status ast ON app.appliance_id=ast.appliance_id\n"
		// + "JOIN sold_to sld ON app.appliance_id =sld.appliance_id\n"
		// + "JOIN customer cs ON sld.customer_id=cs.customer_id\n"
		// + "JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
		// + "WHERE sld.payement_option=1";
		// Statement s = null;
		// ResultSet rs = null;
		double latitude, longitude;
		String customer_name, app_name;
		int scheme, type;
		try (Connection con = Connect.getConnection()) {
			// s = connection.Connect.con.createStatement();
			// rs = s.executeQuery(query);

			// Begin Stored Procedure -- Jeevan
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL view_cash()}");
				ResultSet resultSet = prepareCall.executeQuery();

				while (resultSet.next()) {
					InfowindowBean bean = new InfowindowBean();
					customer_name = resultSet.getString(2);
					app_name = resultSet.getString(1);
					latitude = resultSet.getDouble(3);
					longitude = resultSet.getDouble(4);
					scheme = resultSet.getInt(5);
					type = resultSet.getInt(6);

					bean.setLati(latitude);
					bean.setLongi(longitude);
					bean.setApp_name(app_name);
					bean.setCustomer_name(customer_name);
					bean.setScheme(scheme);
					bean.setType(type);
					list.add(bean);
				}

			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<InfowindowBean> viewActive() {
		ArrayList<InfowindowBean> list = new ArrayList<>();
		String query = "SELECT DISTINCT(app.appliance_name), cs.customer_name,ast.latitude,ast.longitude ,pl.total_installments FROM appliance app \n"
				+ "	JOIN appliance_status ast ON app.appliance_id=ast.appliance_id\n"
				+ "	JOIN sold_to sld ON app.appliance_id =sld.appliance_id\n"
				+ "	JOIN customer cs ON sld.customer_id=cs.customer_id\n"
				+ "	JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
				+ "	WHERE sld.payement_option=1 AND app.appliance_status=1";
		Statement s = null;
		ResultSet rs = null;
		double latitude, longitude;
		String customer_name, app_name;
		int scheme;
		try (Connection con = Connect.getConnection()) {

			InfowindowBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customer_name = rs.getString(2);
				app_name = rs.getString(1);
				latitude = rs.getDouble(3);
				longitude = rs.getDouble(4);
				scheme = rs.getInt(5);
				bean = new InfowindowBean();
				bean.setLati(latitude);
				bean.setLongi(longitude);
				bean.setApp_name(app_name);
				bean.setCustomer_name(customer_name);
				bean.setScheme(scheme);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<InfowindowBean> viewInactive() {
		ArrayList<InfowindowBean> list = new ArrayList<>();
		String query = "SELECT DISTINCT(app.appliance_name), cs.customer_name,ast.latitude,ast.longitude ,pl.total_installments FROM appliance app \n"
				+ "	JOIN appliance_status ast ON app.appliance_id=ast.appliance_id\n"
				+ "	JOIN sold_to sld ON app.appliance_id =sld.appliance_id\n"
				+ "	JOIN customer cs ON sld.customer_id=cs.customer_id\n"
				+ "	JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
				+ "	WHERE sld.payement_option=1 AND app.appliance_status=0";
		Statement s = null;
		ResultSet rs = null;
		double latitude, longitude;
		String customer_name, app_name;
		int scheme;
		try (Connection con = Connect.getConnection()) {
			InfowindowBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customer_name = rs.getString(2);
				app_name = rs.getString(1);
				latitude = rs.getDouble(3);
				longitude = rs.getDouble(4);
				scheme = rs.getInt(5);
				bean = new InfowindowBean();
				bean.setLati(latitude);
				bean.setLongi(longitude);
				bean.setApp_name(app_name);
				bean.setCustomer_name(customer_name);
				bean.setScheme(scheme);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<MapBean> viewInstallment() {
		ArrayList<MapBean> list = new ArrayList<MapBean>();
		String query = "SELECT a.latitude,a.longitude FROM appliance_status a\n"
				+ "INNER JOIN sold_to sld ON a.appliance_id=sld.appliance_id\n"
				+ "WHERE sld.payement_option=0;";
		Statement s = null;
		ResultSet rs = null;
		double latitude, longitude;
		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {

				latitude = rs.getDouble(1);
				longitude = rs.getDouble(2);
				bean = new MapBean();
				bean.setLatitude(latitude);
				bean.setLongitude(longitude);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<MapBean> viewDistrict(int id) {
		ArrayList<MapBean> list = new ArrayList<MapBean>();
		String query = "SELECT district_id,latitude,longitude FROM district WHERE district_id="
				+ id + "";
		Statement s = null;
		ResultSet rs = null;
		int id1 = 0;
		double latitude, longitude;
		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				id1 = rs.getInt(1);
				latitude = rs.getDouble(2);
				longitude = rs.getDouble(3);
				bean = new MapBean();
				bean.setDistrict_id(id1);
				bean.setLatitude(latitude);
				bean.setLongitude(longitude);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<MapBean> viewAppliance(int id) {
		ArrayList<MapBean> list = new ArrayList<MapBean>();
		String query = "SELECT aps.latitude,aps.longitude FROM appliance_status aps \n"
				+ "JOIN appliance ap ON aps.appliance_id=ap.appliance_id\n"
				+ "WHERE ap.appliance_id=14";
		Statement s = null;
		ResultSet rs = null;

		double latitude, longitude;
		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				// name=rs.getString(1);
				latitude = rs.getDouble(1);
				longitude = rs.getDouble(2);

				bean = new MapBean();
				// bean.setDistrict_name(name);

				bean.setLatitude(latitude);
				bean.setLongitude(longitude);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;

	}

	public static ArrayList<MapBean> viewDistrictID() {
		ArrayList<MapBean> list = new ArrayList<MapBean>();
		String query = "SELECT district_id From district";
		Statement s = null;
		ResultSet rs = null;
		int id = 0;
		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
				bean = new MapBean();
				bean.setDistrict_id(id);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<MapBean> viewCash_DO(int dist_id) {
		ArrayList<MapBean> list = new ArrayList<MapBean>();
		String query = "SELECT aps.status_appliance_id,aps.appliance_id,aps.appliance_voltage,aps.appliance_battery,aps.appliance_temperature,aps.appliance_environment,aps.appliance_status,aps.latitude,aps.longitude FROM appliance_Status aps\n"
				+ "JOIN sold_to sld ON aps.appliance_id=sld.appliance_id\n"
				+ "JOIN do_salesman ds ON sld.salesman_id=ds.salesman_id\n"
				+ "WHERE ds.do_id=" + dist_id;
		Statement s = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				list.add(new MapBean(rs.getInt(1), rs.getInt(2), rs
						.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs
						.getString(6), rs.getBoolean(7), rs.getDouble(8), rs
						.getDouble(9)));
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<MapBean> viewInstallment_District(int dist_id) {
		ArrayList<MapBean> list = new ArrayList<MapBean>();
		String query = "SELECT aps.status_appliance_id,aps.appliance_id,aps.appliance_voltage,aps.appliance_battery,aps.appliance_temperature,aps.appliance_environment,aps.appliance_status,aps.latitude,aps.longitude FROM appliance_Status aps\n"
				+ "JOIN sold_to sld ON aps.appliance_id=sld.appliance_id\n"
				+ "JOIN do_salesman ds ON sld.salesman_id=ds.salesman_id\n"
				+ "WHERE ds.do_id=" + dist_id;
		Statement s = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				list.add(new MapBean(rs.getInt(1), rs.getInt(2), rs
						.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs
						.getString(6), rs.getBoolean(7), rs.getDouble(8), rs
						.getDouble(9)));
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<MapBean> viewDistrictAppliances(int id) {
		ArrayList<MapBean> applist = new ArrayList<>();
		String query = "SELECT a.latitude,a.longitude FROM appliance_status a\n"
				+ "JOIN sold_to sld ON a.appliance_id=sld.appliance_id\n"
				+ "JOIN do_salesman ds ON sld.salesman_id=ds.salesman_id\n"
				+ "WHERE sld.payement_option=0 AND do_id=" + id + "";
		Statement s = null;
		ResultSet rs = null;

		double latitude, longitude;
		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {

				latitude = rs.getDouble(1);
				longitude = rs.getDouble(2);

				bean = new MapBean();
				bean.setLatitude(latitude);
				bean.setLongitude(longitude);
				applist.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return applist;
	}

	public static ArrayList<MapBean> view_District_Appliances_on_Inst(int id) {
		ArrayList<MapBean> applist = new ArrayList<>();
		String query = "SELECT a.latitude,a.longitude FROM appliance_status a\n"
				+ "JOIN sold_to sld ON a.appliance_id=sld.appliance_id\n"
				+ "JOIN do_salesman ds ON sld.salesman_id=ds.salesman_id\n"
				+ "WHERE sld.payement_option=1 AND do_id=" + id + "";
		Statement s = null;
		ResultSet rs = null;

		double latitude, longitude;
		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {

				latitude = rs.getDouble(1);
				longitude = rs.getDouble(2);

				bean = new MapBean();
				bean.setLatitude(latitude);
				bean.setLongitude(longitude);
				applist.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return applist;
	}

	public static ArrayList<MapBean> viewAppLoction(int id) {
		ArrayList<MapBean> list = new ArrayList<>();
		String query = "SELECT latitude ,longitude FROM appliance_status WHERE appliance_id="
				+ id + " ORDER BY status_appliance_id DESC LIMIT 1";
		Statement s = null;
		ResultSet rs = null;
		double latitude, longitude;

		try (Connection con = Connect.getConnection()) {
			MapBean bean = null;

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				latitude = rs.getDouble(1);
				longitude = rs.getDouble(2);
				bean = new MapBean();
				bean.setLatitude(latitude);
				bean.setLongitude(longitude);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

//	public static ArrayList<lat_long_bean> Salesman_numbers()
//			throws IOException, JSONException {
//		ArrayList<lat_long_bean> applist = new ArrayList<>();
//		String query = "SELECT DISTINCT(salesman_phone_no), salesman_name FROM salesman limit 3";
//		Statement s = null;
//		ResultSet rs = null;
//
//		String name;
//		try (Connection con = Connect.getConnection()) {
//
//			lat_long_bean bean = null;
//			s = con.createStatement();
//			rs = s.executeQuery(query);
//			while (rs.next()) {
//
//				bean = new lat_long_bean();
//				name = rs.getString(2);
//				bean.setSalesName(name);
//				bean = CallingXML.get_current_location(rs.getNString(1));
//
//				System.out.println("Numbers: " + rs.getNString(1));
//				System.out.println(bean);
//				applist.add(bean);
//			}
//
//		} catch (Exception e) {
//			logger.error("", e);
//			e.printStackTrace();
//		}
//
//		return applist;
//	}

	public static ArrayList<HashMap<String, Object>> getDoAppliances(int userId) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement cal = con
					.prepareCall("{CALL view_do_appliances_marker(?)}");
			cal.setInt(1, userId);
			ResultSet rs = cal.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id"));
				map.put("status", rs.getInt("appliance_status"));
				map.put("latitude", rs.getDouble("latitude"));
				map.put("longitude", rs.getDouble("longitude"));
				map.put("ApplianceName", rs.getString("appliance_name"));
				map.put("CustomerName", rs.getString("customer_name"));

				list.add(map);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static double genrateLAtiLong(String number)	{
		System.out.println("mapBAL.genrateLAtiLong()");

		double result = 0.0;
		try {
			if (number.length() > 4) {
				double value = Double.parseDouble(number);
				String value2 = String.valueOf(value);
				String lati = value2.substring(0, 2);
				String latiMinSec = value2.substring(2, 7);
				String latimin = latiMinSec.substring(0, 2);
				String latisec = latiMinSec.substring(3, 5);
				if (!latimin.equals("") && !latisec.equals("")) {
					result = Double.parseDouble(lati)
							+ Double.parseDouble(latimin) / 60
							+ Double.parseDouble(latisec) / 3600;
				}
			} else {
				result = Double.parseDouble(number);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String ags[]) {
		// System.out.println(genrateLAtiLong("0000006706.1301"));

		System.out.println(getDoAppliances(107));
	}

}
