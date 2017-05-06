/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.ApplianceBean;
import bean.ApplianceInfoBean;
import bean.CustomerAnd;
import bean.CustomerInfoBean;
import bean.EligibilityExistCustomer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import connection.Connect;
import control.UpdateAppliance;

public class ApplianceBAL {

	private final static Logger logger = Logger.getLogger(ApplianceBAL.class);

	// start get Accepted Applience requests by salemanId
	public static ArrayList<CustomerAnd> getAcceptedAppliencesBySalemanId(
			int salemanId) {
		ResultSet rs = null;

		CallableStatement cstmt = null;

		CustomerAnd bean = null;
		ArrayList<CustomerAnd> customerList = new ArrayList<CustomerAnd>();

		try (Connection con = Connect.getConnection()) {
			String customerName, applianceName;
			cstmt = con
					.prepareCall("{CALL get_accepted_appliences_by_salemanId(?)}");
			cstmt.setInt(1, salemanId);
			rs = cstmt.executeQuery();
			while (rs.next()) {

				customerName = rs.getString(2);
				applianceName = rs.getString(4);
				bean = new CustomerAnd();
				bean.setName(customerName);
				bean.setAplName(applianceName);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("", e);
			return customerList;
		}

		return customerList;
	}// end get Accepted Applience requests by salemanId

	// start get Active Applience requests by salemanId
	public static List<CustomerAnd> getActiveAppliencesBySalemanId(int salemanId) {
		ResultSet rs = null;

		CallableStatement cstmt = null;
		CustomerAnd bean = null;
		ArrayList<CustomerAnd> customerList = new ArrayList<CustomerAnd>();

		try (Connection con = Connect.getConnection()) {
			String customerName, applianceName;
			cstmt = con
					.prepareCall("{CALL get_active_appliences_by_salemanId(?)}");
			cstmt.setInt(1, salemanId);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(2);
				applianceName = rs.getString(4);
				bean = new CustomerAnd();
				bean.setName(customerName);
				bean.setAplName(applianceName);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			return customerList;

		}

		return customerList;
	}// end get Active Applience by salemanId

	public static ArrayList<EligibilityExistCustomer> getApplianceByCustomerId(
			int customerId) {
		System.out.println("ApplianceBAL.get_customer_appliances(?)");

		EligibilityExistCustomer bean = null;

		ArrayList<EligibilityExistCustomer> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call
			CallableStatement prepareCall = con
					.prepareCall("{call get_customer_appliances(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {

				bean = new EligibilityExistCustomer();
				// HashMap<String, String> map = new HashMap();

				bean.setApplianceName(rs.getString("appliance_name"));
				bean.setApplianceNumber(rs.getString("appliance_GSMno"));
				bean.setCustomreName(rs.getString("customer_name"));
				// bean.setCustomerNumber(rs.getString(""));
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setApplianceid(rs.getInt("appliance_id"));
				// map.put("CustomerName", rs.getString("customer_name"));
				// map.put("appLianceName", rs.getString("appliance_name"));
				// map.put("applianceNumber", rs.getString("appliance_GSMno"));
				// map.put("applianceId", rs.getInt("appliance_id")+"");
				// map.put("customerId", rs.getInt("customer_id")+"");

				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceBean> getAppliance(int customerId) {
		ResultSet rs = null;

		ApplianceBean bean = null;

		ArrayList<ApplianceBean> list = new ArrayList<>();
		int applianceID, paymentType;
		double price;
		boolean state;
		Date handoverDate = null;
		String applianceName, productId, gsmNumber, salesmanName;

		String query = "SELECT a.appliance_name, a.appliance_GSMno, a.appliance_product_no, a.appliance_price, a.appliance_id, a.appliance_status, \n"
				+ "s.salesman_name, sld.payement_option, sld.product_handover FROM sold_to sld JOIN appliance a ON a.appliance_id=sld.appliance_id\n"
				+ "JOIN customer cs ON cs.customer_id = sld.customer_id JOIN salesman s ON s.salesman_id = sld.salesman_id WHERE cs.customer_id=?;";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceName = rs.getString(1);
				gsmNumber = rs.getString(2);
				productId = rs.getString(3);
				price = rs.getDouble(4);
				applianceID = rs.getInt(5);
				state = rs.getBoolean(6);
				salesmanName = rs.getString(7);
				paymentType = rs.getInt(8);
				handoverDate = rs.getDate(9);
				bean = new ApplianceBean();
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setApplianceId(applianceID);
				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setHandoverDate(handoverDate);
				bean.setPaymentType(paymentType);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_rejected() {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;
		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE sold.status=2 \n"
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_handover(
			int active, int inactive, int owner) {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		String querycon = null;
		if (active == 1) {
			querycon = " and a.appliance_status=1 ";
		} else if (inactive == 1) {
			querycon = " and a.appliance_status=0 ";
		} else {
			querycon = "";
		}

		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE a.status = 2 AND sold.down_payment>0    \n"
				+ querycon
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_owner() {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   INNER JOIN payment_loan pl ON sold.sold_to_id=pl.soldto_id\n"
				+ "                   WHERE pl.remaining_balance<0 OR a.status =4 \n"
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_inActive(
			int handover) {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		String querycon = null;
		if (handover == 1) {
			querycon = " and sold.down_payment>0 ";
		} else {
			querycon = "";
		}
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE a.appliance_status=0\n"
				+ querycon
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_onReturned() {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE a.status=3\n"
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_Active(
			int handover) {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		String querycon = null;
		if (handover == 1) {
			querycon = " and sold.down_payment>0 ";
		} else {
			querycon = "";
		}
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE a.appliance_status=1\n"
				+ querycon
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_onsold() {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE a.status=1 and sold.down_payment<1 \n"
				+ "                 ORDER BY a.appliance_id DESC ";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList_onRejected() {
		ResultSet rs = null;

		ApplianceInfoBean bean = null;
		ArrayList<ApplianceInfoBean> list = new ArrayList<ApplianceInfoBean>();
		int applianceId, status;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman;
		String query = "(SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                   a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status FROM sold_to sold  \n"
				+ "                   INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                   INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                   INNER JOIN salesman s ON s.salesman_id = sold.salesman_id\n"
				+ "                   WHERE sold.status=2\n"
				+ "                   )ORDER BY a.appliance_id DESC ;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceInfoBean> getApplianceList() {

		System.out.print("ApplianceBAL.get_appliances()");
		ApplianceInfoBean bean = null;
		ArrayList<ApplianceInfoBean> list = new ArrayList<>();
		int applianceId, status, id, applianceStatus;
		double price;

		String applianceName, district, productId, gsmNumber, customer, salesman, number;

		try (Connection con = Connect.getConnection()) {

			if (con != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = con
						.prepareCall("{call get_appliances()}");
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					applianceId = rs.getInt(1);
					gsmNumber = rs.getString(2);
					applianceName = rs.getString(3);
					productId = rs.getString(4);
					price = rs.getDouble(5);
					applianceStatus = rs.getInt(6);
					customer = rs.getString(7);
					district = rs.getString(8);
					salesman = rs.getString(9);
					status = rs.getInt(10);
					id = rs.getInt(11);
					number = rs.getString(12);
					// End Stored Procedure Calling -- Jetander
					bean = new ApplianceInfoBean();
					bean.setApplianceId(applianceId);
					bean.setApplianceName(applianceName);
					bean.setPrice(price);
					bean.setApplianceStatus(applianceStatus);
					bean.setProductId(productId);
					bean.setApplianceGsmNo(gsmNumber);
					bean.setDistrict(district);
					bean.setCustomer(customer);
					bean.setSalesman(salesman);
					bean.setStatus(status);
					bean.setSalesmanId(id);
					bean.setCustomerPhone(number);
					list.add(bean);
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of All Appliances

	public static ApplianceBean getApplianceInfo(int applianceId) {
		System.out.print("ApplianceBAL.get_appliance_info(applianceId)");
		ApplianceBean bean = null;
		int salesmanId, soldtoId, doid, applianceIdd;
		double price;
		Date handover;
		String IMInumber;
		boolean state;
		String applianceName, productId, customercnic, gsmNumber, image, salesman, customer, salesmanPhone, customerPhone, userName, foName;

		try (Connection con = Connect.getConnection()) {
			System.err.println("applianceId== "+applianceId);
			CallableStatement prepareCall = con
					.prepareCall("{call get_appliance_info(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {

				gsmNumber = rs.getString("appliance_GSMno");
				applianceName = rs.getString("appliance_name");
				price = rs.getDouble("appliance_price");
				state = rs.getBoolean("appliance_status");
				salesman = rs.getString("salesman_name");
				customer = rs.getString("customer_name");
				handover = rs.getDate("product_handover");

				salesmanId = rs.getInt("salesman_id");
				salesmanPhone = rs.getString("salesman_phone_no");
				customerPhone = rs.getString("customer_phone");
				soldtoId = rs.getInt("sold_to_id");
				userName = rs.getString("user_name");
				foName = rs.getString("fo_name");
				doid = rs.getInt("user_id");

				IMInumber = rs.getString("a.imei_number");
				customercnic = rs.getString("c.customer_cnic");
				applianceIdd = rs.getInt("a.appliance_id");
				bean = new ApplianceBean();
				bean.setApplianceId(applianceIdd);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);

				bean.setApplianceGsmNo(gsmNumber);

				bean.setSalesmanName(salesman);
				bean.setHandoverDate(handover);
				bean.setCustomerName(customer);
				bean.setImiNumber(IMInumber);
				bean.setSalesmanId(salesmanId);
				bean.setSalesmanPhone(salesmanPhone);
				bean.setCustomerPhone(customerPhone);
				bean.setSoldtoId(soldtoId);
				bean.setUserName(userName);
				bean.setFoName(foName);
				bean.setDoid(doid);
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setFoid(rs.getInt("fo.fo_id"));
				bean.setCustomerCnic(customercnic);
				bean.setConsumerNumber(rs.getString("consumer_number"));
				bean.setIsOwned(rs.getDouble("remaining_balance"));
				bean.setInstalledDate(rs.getString("product_handover"));
				bean.setSignal(rs.getInt("is_signal_catching"));
				bean.setStatus(rs.getInt("status"));
				bean.setStatusget(rs.getString("status_get"));
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static List<ApplianceBean> getApplianceInfoByCustomer(int customerId) {
		ResultSet rs = null;

		List<ApplianceBean> ApplianceBeanList = new ArrayList<ApplianceBean>();

		int applianceID;
		double price;
		Date handover;
		boolean state;

		String applianceName, productId, gsmNumber, image, salesman, customer;
		String query = "SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price, "
				+ "a.appliance_image, a.appliance_status, sl.salesman_name, c.customer_name,s.product_handover,c.customer_id FROM sold_to s JOIN appliance a ON a.appliance_id = s.appliance_id JOIN customer c ON c.customer_id = s.customer_id "
				+ "JOIN salesman sl ON sl.salesman_id = s.salesman_id WHERE s.customer_id=?;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceID = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				image = rs.getString(6);
				state = rs.getBoolean(7);
				salesman = rs.getString(8);
				customer = rs.getString(9);
				handover = rs.getDate(10);
				customerId = rs.getInt(11);
				ApplianceBean bean = new ApplianceBean();
				bean.setApplianceId(applianceID);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setImage(image);
				bean.setSalesmanName(salesman);
				bean.setHandoverDate(handover);
				bean.setCustomerName(customer);
				ApplianceBeanList.add(bean);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return ApplianceBeanList;
	}

	public static ArrayList<ApplianceBean> getApplianceInfoByProductId(
			String productID) {
		ResultSet rs = null;

		ApplianceBean bean = null;
		ArrayList<ApplianceBean> list = new ArrayList<>();
		int applianceId;
		double price;
		boolean state;

		String applianceName, productId, gsmNumber, image;
		String query = "Select appliance_id, appliance_GSMno, appliance_name, appliance_product_no, appliance_price, appliance_image,appliance_status FROM appliance where product_id=?;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, productID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				image = rs.getString(6);
				state = rs.getBoolean(7);
				bean = new ApplianceBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setImage(image);
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ApplianceBean> getApplianceInfoByPrice(
			double price1, double price2) {
		ResultSet rs = null;

		ApplianceBean bean = null;
		ArrayList<ApplianceBean> list = new ArrayList<>();
		int applianceId;
		double price;
		boolean state;

		String applianceName, productId, gsmNumber, image;
		String query = "Select appliance_id, appliance_GSMno, appliance_name, appliance_product_no, appliance_price, appliance_image,appliance_status FROM appliance where price BETWEEN "
				+ price1 + "AND " + price2 + ";";
		try (Connection con = Connect.getConnection()) {

			Statement stmt = (Statement) con.prepareStatement(query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				image = rs.getString(6);
				state = rs.getBoolean(7);
				bean = new ApplianceBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setImage(image);
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static int getLastId() {

		String query = "SELECT MAX(appliance_id) FROM appliance";

		ApplianceBean bean = null;
		int applianceId = 0;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement s = null;

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				applianceId = rs.getInt(1);
				bean = new ApplianceBean();
				bean.setApplianceId(applianceId);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return applianceId;
	}

	// public static int Insert_Appliance(String gsm_no, String name,
	// double price, int status) throws SQLException {
	//
	//
	// int rows = 0;
	// Connection con=connection.Connect.getConnection();
	// try (Statement statement = con.createStatement()) {
	// rows = statement
	// .executeUpdate("INSERT INTO
	// appliance(appliance_GSMno,appliance_name,appliance_price,appliance_image,appliance_status)
	// VALUES ('"
	// + gsm_no
	// + "','"
	// + name
	// + "',"
	// + price
	// + ","
	// + null
	// + "," + status + ")");
	// }
	//
	//
	//
	// con.close();
	// return rows;
	// }

	public static ArrayList<ApplianceInfoBean> getAppliancefilter(
			String appliance_name, String district_id, String transfer_status,
			String startdate, String endtime, String salesman12)
			throws java.text.ParseException {
		ApplianceInfoBean bean = null;

		ResultSet rs = null;

		Boolean found;
		String conStr = null;
		found = transfer_status.contains("5");
		if (found == true) {
			conStr = " and sold.down_payment>=1200 ";
		}
		{
			conStr = "";
		}
		// System.out.println(newDateString);

		// (1) create a SimpleDateFormat object with the desired format.
		// this is the format/pattern we're expecting to receive.
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		try {
			// (2) give the formatter a String that matches the SimpleDateFormat
			// pattern

			Date strtdate = formatter.parse(startdate);

			// (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
			System.out.println("Date  " + strtdate);

		} catch (Exception e) {
			// execution will come here if the String that is given
			// does not match the expected format.
			logger.error("", e);
			e.printStackTrace();
		}

		ArrayList<ApplianceInfoBean> list = new ArrayList<>();
		int applianceId, status, id;
		double price;
		boolean state;

		String applianceName, district, productId, gsmNumber, customer, salesman, number;
		String query = "SELECT a.appliance_id, a.appliance_GSMno, a.appliance_name, a.appliance_product_no, a.appliance_price,\n"
				+ "                a.appliance_status,c.customer_name,s.salesman_district,s.salesman_name, a.status, s.salesman_id,c.customer_phone FROM sold_to sold  \n"
				+ "                 INNER JOIN customer c ON c.customer_id = sold.customer_id \n"
				+ "                INNER JOIN appliance a ON a.appliance_id = sold.appliance_id \n"
				+ "                 INNER JOIN salesman s ON s.salesman_id = sold.salesman_id 	where a.appliance_name in("
				+ appliance_name
				+ ") "
				+ conStr
				+ " and sold.status in("
				+ transfer_status
				+ ") and sold.sold_date between '"
				+ startdate
				+ "' and '"
				+ endtime
				+ "' and s.salesman_district in ("
				+ district_id
				+ ")  and s.salesman_name LIKE '"
				+ salesman12
				+ "%' or s.salesman_name like '%"
				+ salesman12
				+ "' ORDER BY a.appliance_id DESC";
		try (Connection con = connection.Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			System.out.println(query);
			while (rs.next()) {
				applianceId = rs.getInt(1);
				gsmNumber = rs.getString(2);
				applianceName = rs.getString(3);
				productId = rs.getString(4);
				price = rs.getDouble(5);
				state = rs.getBoolean(6);
				customer = rs.getString(7);
				district = rs.getString(8);
				salesman = rs.getString(9);
				status = rs.getInt(10);
				id = rs.getInt(11);
				number = rs.getString(12);
				bean = new ApplianceInfoBean();
				bean.setApplianceId(applianceId);
				bean.setApplianceName(applianceName);
				bean.setPrice(price);
				bean.setState(state);
				bean.setProductId(productId);
				bean.setApplianceGsmNo(gsmNumber);
				bean.setDistrict(district);
				bean.setCustomer(customer);
				bean.setSalesman(salesman);
				bean.setStatus(status);
				bean.setSalesmanId(id);
				bean.setCustomerPhone(number);
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getApplianceByLimitAndRangeAndOrderBy(
			int start, int range, int column, String dir, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		try (Connection connection = Connect.getConnection()) {
			call = connection
					.prepareCall("{Call get_appliance_list_search(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, range);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("appliancePrice", rs.getString("appliance_price"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("applianceGSM", rs.getString("appliance_GSMno"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("salesmanDistrict", rs.getString("district_name"));
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("doName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("applianceStatusActive", rs.getInt("appliance_status")
						+ "");
				map.put("imeiNumber", rs.getString("imei_number"));
				map.put("applianceStatus", rs.getInt("a.status") + "");
				map.put("isDefaulted", rs.getInt("defaulted") + "");
				map.put("islive", rs.getInt("is_alive") + "");
				map.put("deadSince", rs.getInt("deadSince") + "");
				map.put("deadDate", rs.getString("dead_since") + "");
				list.add(map);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return list;
	}

	public static int countAppliances(int start, int range) {
		System.out.println("ApplianceBAL.count_appliance_for_superadmin()");

		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_appliance_for_superadmin(?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countAppliances() {
		System.out.println("ApplianceBAL.countAppliances()");

		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_appliance_count()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countAppliancesDo(int districId) {
		System.out.println("ApplianceBAL.countAppliances()");

		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_appliance_count_do(?)}");
			prepareCall.setInt(1, districId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<HashMap<String, String>> getAllApplianceBySoldTo() {
		System.out.println("ApplianceBAL.getAllApplianceBySoldTo()");
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_appliance_by_sold_to()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceName", resultSet.getString("appliance_name"));
				mapList.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return mapList;
	}

	public static ArrayList<HashMap<String, String>> getAppliancesWithSearch(
			String values) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_appliance_with_search(?)}");
			call.setString(1, values);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				// map.put("soldToId", rs.getInt("sold_to_id")+"");
				// map.put("customerId", rs.getInt("customer_id")+"");
				// map.put("salesmanId", rs.getInt("salesman_id")+"");
				// map.put("soldDate", rs.getDate("sold_date")+"");
				// map.put("applianceOption", rs.getInt("appliance_option")+"");
				// map.put("paymentOption", rs.getInt("payement_option")+"");
				// map.put("productHandover",
				// rs.getDate("product_handover")+"");
				//
				// map.put("downPayment", rs.getInt("down_payment")+"");
				//
				// map.put("customerCnic", rs.getString("customer_cnic"));
				// map.put("dateOfBirth", rs.getDate("date_of_birth")+"");
				// map.put("customerAddress", rs.getString("customer_address"));
				// map.put("customer_city", rs.getInt("customer_city")+"");
				// map.put("customerPhone", rs.getString("customer_phone"));
				// map.put("customerPhone2", rs.getString("customer_phone2"));
				// map.put("customerMonthlyIncome",
				// rs.getInt("customer_monthly_income")+"");
				// map.put("customerFamilyIncome",
				// rs.getInt("customer_family_income")+"");
				map.put("appliancePrice", rs.getString("appliance_price"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("applianceGSM", rs.getString("appliance_GSMno"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("salesmanDistrict", rs.getString("district_name"));
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("applianceStatusActive", rs.getInt("appliance_status")
						+ "");
				map.put("applianceStatus", rs.getInt("a.status") + "");

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getAppliancesWithSearch(
			String values, int str, int rng, int orde, String dir) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_appliance_data_with_search(?,?,?,?,?)}");
			call.setString(1, values);
			call.setInt(2, str);
			call.setInt(3, rng);
			call.setInt(4, orde);
			call.setString(5, dir);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				// map.put("applianceId", rs.getInt("appliance_id") + "");
				// map.put("appliancePrice", rs.getString("appliance_price"));
				// map.put("customerName", rs.getString("customer_name"));
				// map.put("applianceGSM", rs.getString("appliance_GSMno"));
				// map.put("applianceName", rs.getString("appliance_name"));
				// map.put("salesmanDistrict", rs.getString("district_name"));
				// map.put("customerPhone", rs.getString("customer_phone"));
				// map.put("doName", rs.getString("user_name"));
				// map.put("foName", rs.getString("fo_name"));
				// map.put("salesmanName", rs.getString("salesman_name"));
				// map.put("applianceStatusActive",
				// rs.getInt("appliance_status")+"");
				// map.put("imeiNumber", rs.getString("imei_number"));
				// map.put("applianceStatus", rs.getInt("a.status") + "");
				// map.put("isDefaulted", rs.getInt(13) + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");

				map.put("pendingDate", "" + rs.getDate("date"));
				map.put("ADPDate", "" + rs.getDate("imei_assign_date"));
				map.put("RTADAte", "" + rs.getDate("accepted_date"));
				// map.put("installedDate", "" +
				// rs.getDate("product_handover"));
				// map.put("returnedDate", "" + rs.getDate("returned_date"));
				map.put("appliancePrice", rs.getString("appliance_price"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("applianceGSM", rs.getString("appliance_GSMno"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("salesmanDistrict", rs.getString("district_name"));
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("doName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("applianceStatusActive", rs.getInt("appliance_status")
						+ "");
				map.put("imeiNumber", rs.getString("imei_number"));
				map.put("applianceStatus", rs.getInt("a.status") + "");

				map.put("isDefaulted", rs.getInt("defaulted") + "");

				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getApplianceSearchCount(String value) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_appliance_with_search_count(?)}");
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

	public static HashMap<String, String> countAllStatusAndApplianceStatus() {
		System.out.println("ApplianceBAL.countAllStatusAndApplianceStatus()");
		HashMap<String, String> map = new HashMap<>();
		int dumb = 0;
		int dead = 0;
		int dead_since = 0;
		int alive = 0;
		int active = 0;
		int inactive = 0;
		int pending = 0;
		int readyToAssign = 0;
		int rdp = 0;
		int owned = 0;
		int returned = 0;
		int awaitingForDownpayment = 0;
		int installed = 0;
		int installedNoSignal = 0;
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = connection.prepareCall(
					"{CALL count_all_status_and_appliance_status()}")
					.executeQuery();
			while (resultSet.next()) {
				switch (resultSet.getInt("appliance_status")) {
				case 0:
					inactive += resultSet.getInt("count_appliance_status");
					break;
				case 1:
					active += resultSet.getInt("count_appliance_status");
					break;
				}
				
				switch (resultSet.getInt("health_status")) {
			    case 0:
			     dumb += resultSet.getInt("count_health_status");
			     break;
			    case 1:
			     dead += resultSet.getInt("count_health_status");
			     break;
			    case 2:
			     dead_since += resultSet.getInt("count_health_status");
			     break;
			    case 3:
			     alive += resultSet.getInt("count_health_status");
			     break;
			    }
				switch (resultSet.getInt("status")) {
				case 0:
					pending += resultSet.getInt("count_status");
					break;
				case 1:
					readyToAssign += resultSet.getInt("count_status");
					break;
				case 2:
					rdp += resultSet.getInt("count_status");
					break;
				case 3:
					owned += resultSet.getInt("count_status");
					break;
				case 4:
					returned += resultSet.getInt("count_status");
					break;
				case 5:
					awaitingForDownpayment += resultSet.getInt("count_status");
					break;
				case 6:
					installed += resultSet.getInt("count_appliance_status");
					break;
				case 7:
					installedNoSignal += resultSet
							.getInt("count_appliance_status");
					break;
				}
				map.put("active", active + "");
				map.put("inactive", inactive + "");
				map.put("pending", pending + "");
				map.put("readyToAssign", readyToAssign + "");
				map.put("rdp", rdp + "");
				map.put("owned", owned + "");
				map.put("awaitingForDownpayment", awaitingForDownpayment + "");
				map.put("returned", returned + "");
				map.put("installed", installed + "");
				map.put("installedNoSignal", installedNoSignal + "");
				map.put("dumb", dumb + "");
			    map.put("dead", dead + "");
			    map.put("dead_since", dead_since + "");
			    map.put("alive", alive + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getSuperAdminDonutChart() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_appliances_count_for_super_admin()}");
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceCount", rs.getInt("app_count") + "");
				map.put("applianceInactive", rs.getInt("inactive_appliance")
						+ "");
				map.put("applianceActive", rs.getInt("active_appliance") + "");
				map.put("appliancePercent", rs.getString("perc"));
				map.put("applianceName", rs.getString("appliance_name"));

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int get_appliance_id(String GSM) {

		CallableStatement call = null;
		ResultSet rs;
		int appliance_id = 0;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_appliance_id_byGSM(?)}");
			call.setString(1, GSM);
			rs = call.executeQuery();
			while (rs.next()) {
				appliance_id = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return appliance_id;
	}

	public static void update_appliance_lat_long(String lati, String lon,
			int appliance_id) {

		CallableStatement call = null;
		// ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL update_appliance_lat_long(?,?,?)}");
			call.setString(1, lati);
			call.setString(2, lon);
			call.setInt(3, appliance_id);
			call.executeQuery();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void update_appliance_on_off(boolean on_off, int appliance_id) {

		CallableStatement call = null;
		// ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL update_appliance_on_off(?,?)}");
			call.setInt(1, appliance_id);
			call.setBoolean(2, on_off);
			call.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void insert_applianceStatus(int appliance_id,
			double appliance_voltage, double appliance_battery,
			double appliance_temperature, double appliance_solar_voltage,
			double appliance_load_current, int appliance_gsm_signal,
			boolean appliance_lid_connectivity, double latititude,
			double longitude) {

		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL insert_appliance_status(?,?,?,?,?,?,?,?,?,?)}");
			call.setInt(1, appliance_id);
			call.setDouble(2, appliance_voltage);
			call.setDouble(3, appliance_battery);
			call.setDouble(4, appliance_temperature);
			call.setDouble(5, appliance_solar_voltage);
			call.setDouble(6, appliance_load_current);
			call.setInt(7, appliance_gsm_signal);
			call.setBoolean(8, appliance_lid_connectivity);
			call.setDouble(9, latititude);
			call.setDouble(10, longitude);
			call.executeQuery();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void insert_applianceStatus_alerts(int appliance_id,
			String column, double value) throws SQLException {
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("INSERT INTO appliance_status(appliance_id,"
							+ column + ",status_date) VALUES (" + appliance_id
							+ "," + value + ",NOW() )");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void insert_applianceStatus_lidopen(int appliance_id,
			boolean value) throws SQLException {

		// int rows;

		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			// rows =
			statement
					.executeUpdate("INSERT INTO appliance_status(appliance_id,appliance_lid_connectivity,status_date) VALUES ("
							+ appliance_id + "," + value + ",NOW() )");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void insert_applianceStatus_lat_long(int appliance_id,
			double lati, double longi) throws SQLException {

		// int rows;

		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			// rows =
			statement
					.executeUpdate("INSERT INTO appliance_status(appliance_id,latitude,longitude,status_date) VALUES ("
							+ appliance_id
							+ ","
							+ lati
							+ ","
							+ longi
							+ ",NOW() )");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void insert_applianceStatus_gsm_signal(int appliance_id,
			int gsm_signal) throws SQLException {

		// int rows;

		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			// rows =
			statement
					.executeUpdate("INSERT INTO appliance_status(appliance_id,appliance_gsm_signal,status_date) VALUES ("
							+ appliance_id + "," + gsm_signal + ",NOW() )");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public int insertIntoAppliance(HashMap<String, String> map) {

		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_into_appliances(?,?,?,?)}");
			prepareCall.setString(1, map.get("applianceGsmNumber"));
			prepareCall.setInt(2, Integer.parseInt(map.get("applianceName")));
			prepareCall.setInt(3, Integer.parseInt(map.get("applianceStatus")));
			prepareCall.setDouble(4,
					Double.parseDouble(map.get("appliancePrice")));
			ResultSet resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static ArrayList<HashMap<String, String>> getApplianceStatusbyId(
			int app_id) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{call get_appliance_allstatus(?)}");
			call.setInt(1, app_id);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				map.put("temprature", rs.getDouble("appliance_temperature")
						+ "");

				map.put("gsmSignal", rs.getDouble("appliance_gsm_signal") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("batteryVolt", rs.getDouble("battery_voltage") + "");
				map.put("batteryAmp", rs.getDouble("battery_ampere") + "");
				map.put("solarVolt", rs.getDouble("solar_voltage") + "");
				map.put("solarAmp", rs.getDouble("solar_ampare") + "");
				map.put("loadAmp", rs.getDouble("load_ampere") + "");
				map.put("loadVolt", rs.getDouble("load_voltage") + "");
				map.put("appId", rs.getInt("appliance_id") + "");
				map.put("lid", rs.getInt("appliance_lid_connectivity") + "");
				map.put("date", rs.getString("status_date") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoDonutChart(
			int districtId) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_appliances_of_district(?)}");
			call.setInt(1, districtId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceCount", rs.getInt("app_count") + "");
				map.put("applianceInactive", rs.getInt("appliance_inactive")
						+ "");
				map.put("appliancePercent", rs.getString("perc"));
				map.put("applianceName", rs.getString("appliance_name"));

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getWattageStatics(int appId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call appliance_bv_graph(?)}");
			call.setInt(1, 162);
			rs = call.executeQuery();
			System.out.println(rs.getMetaData());
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("batteryWatt", rs.getDouble("battery_watt") + "");
				map.put("lodWatt", rs.getDouble("solar_watt") + "");
				map.put("solarWatt", rs.getDouble("power_watt") + "");
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 5);
				map.put("datetime", s);

				list.add(map);
				// System.out.println(s);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getWattageStaticsDateWise(
			int appId, String date) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL appliance_bv_graph_datewise(?,?)}");
			call.setInt(1, appId);
			call.setString(2, date);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("batteryWatt", rs.getDouble("battery_watt") + "");
				map.put("lodWatt", rs.getDouble("solar_watt") + "");
				map.put("solarWatt", rs.getDouble("power_watt") + "");
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 2);
				map.put("datetime", s);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<CustomerInfoBean> getExeAcceptedApplianceByCustomerId(
			int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();

		CustomerInfoBean bean = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_exe_accepted_customer_appliances(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setCustomerId(resultSet.getInt("customer_id"));
				bean.setApplianceId(resultSet.getInt("appliance_id"));
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setApplianceStatus(resultSet.getInt("appliance_status"));
				bean.setSalesmanName(resultSet.getString("salesman_name"));
				bean.setTransferStatus(resultSet.getInt("a.status"));
				bean.setEligibilityStatus(resultSet.getInt("e.status"));
				bean.setLoanStatus(resultSet.getString("loan_status"));
				System.out.print(" P " + resultSet.getInt("pickup"));
				bean.setIsDefaulted(resultSet.getInt("pickup"));
				maps.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<CustomerInfoBean> getExeRejectApplianceByCustomerId(
			int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();

		CustomerInfoBean bean = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_exe_reject_customer_appliances(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setCustomerId(resultSet.getInt("customer_id"));
				bean.setApplianceId(resultSet.getInt("appliance_id"));
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setSalesmanName(resultSet.getString("salesman_name"));
				bean.setPurpose(resultSet.getString("text"));
				maps.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getSuspendedCustomer(
			int cid) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		String query = "SELECT c.`customer_id`, c.`customer_name`,a.`appliance_id`, "
				+ "a.`appliance_name`, e.`eligibility_id`, e.`status`, s.`salesman_name` "
				+ " FROM customer c "
				+ " INNER JOIN eligibility e ON e.`customer_id` = c.`customer_id` "
				+ " INNER JOIN `appliance` a ON a.`appliance_id` = e.`appliance_id` "
				+ " INNER JOIN salesman s ON s.salesman_id = e.salesman_id  "
				+ " WHERE e.`status` = 3 AND c.`customer_id` = " + cid;
		try (Connection con = Connect.getConnection()) {
			Statement s = null;
			ResultSet rs = null;

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("customer_name", "" + rs.getString("c.customer_name"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("appliance_name", "" + rs.getString("a.appliance_name"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("status", "" + rs.getInt("e.status"));
				map.put("salesman_name", "" + rs.getInt("s.salesman_name"));

				list.add(map);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.getStackTrace();
		}
		return list;
	}

	public static int addAppliance(HashMap<String, String> map) {
		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call add_appliances(?,?,?,?,?)}");
			prepareCall.setString(1, map.get("applianceGsmNumber"));
			prepareCall.setString(2, map.get("applianceName"));
			prepareCall.setString(3, map.get("imei"));
			prepareCall.setDouble(4,
					Double.parseDouble(map.get("appliancePrice")));
			prepareCall.setInt(5, Integer.parseInt(map.get("userId")));
			ResultSet resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	// created by Junaid Ali
	public static boolean updateApplianceAlerts(int applianceId) {
		boolean row = false;
		CallableStatement call = null;
		// ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL update_appliance_alerts_with_appliance_id(?)}");
			call.setInt(1, applianceId);
			// rs =
			call.executeQuery();
			row = true;

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int updateImei(int applianceId, String imei, String gsm)
			throws SQLException {

		int rows = 0;

		Connection con = Connect.getConnection();
		try (Statement statement = con.createStatement()) {
			rows = statement
					.executeUpdate("UPDATE appliance SET `imei_number` = "
							+ imei + ",`appliance_GSMno` = " + gsm
							+ ", `status` = 5 WHERE `appliance_id` = "
							+ applianceId);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		con.close();
		return rows;
	}

	public static String updateApplianceStatus(int applianceId,
			int applianceStatus) {
		String phoneNo = "";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet rs = null;
			row = statement.executeUpdate("UPDATE appliance SET `status` = "
					+ applianceStatus + " WHERE `appliance_id` = "
					+ applianceId);
			statement = con.createStatement();
			rs = statement
					.executeQuery("Select cs.customer_phone from eligibility e JOIN customer cs "
							+ "ON cs.customer_id = e.customer_id WHERE e.appliance_id = "
							+ applianceId);

			while (rs.next()) {
				phoneNo = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return phoneNo + ":" + row;
	}

	public static ArrayList<HashMap<String, Object>> getloadSolarAmpere(
			int appId, String date) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{Call get_loadSolar_ampare(?,?)}");
			callst.setInt(1, appId);
			callst.setString(2, date);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("ApplianceId", rs.getInt("appliance_id"));
				map.put("solar", rs.getDouble("solar_ampare"));
				map.put("load", rs.getDouble("load_ampere"));
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 2);
				map.put("datetime2", s);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int appliancStatusLive(int appId) {
		int status = 0;
		String query = "SELECT appliance_status, imei_number FROM appliance WHERE appliance_id=?";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, appId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				status = rs.getInt("appliance_status");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return status;
	}

	// public static int updateAppliance(String appGSM, String appIMEI, int
	// appId) {
	// int row = 0;
	// String query = "UPDATE appliance SET appliance_GSMno=" + appGSM
	// + ", imei_number=" + appIMEI + " WHERE appliance_id = ?";
	// try (Connection conn = Connect.getConnection()) {
	// PreparedStatement ps = conn.prepareStatement(query);
	// ps.setInt(1, appId);
	// row = ps.executeUpdate();
	// conn.close();
	// } catch (SQLException e) {
	// logger.error("", e);
	// e.printStackTrace();
	// }
	// return row;
	// }

	public static String updateAppliance(String appGSM, String appIMEI,
			int appId, int customerId) {
		int row = 1;
		String ap = "0";
		String status = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `change_device`(?,?,?,?,?)}");
			prepareCall.setInt(1, appId);
			prepareCall.setInt(2, customerId);
			prepareCall.setString(3, appGSM);
			prepareCall.setString(4, appIMEI);
			prepareCall.registerOutParameter(5, java.sql.Types.VARCHAR);
			prepareCall.executeQuery();
			ap = prepareCall.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ap;
	}

	public static int updatePricePlan(int appId, int scheme, int downpayment,
			int monthlyPayment, int totalPrice) {
		int loanId = 0;
		int row = 0;
		try (Connection conn = Connect.getConnection()) {
			String query = "UPDATE appliance SET appliance_price ="
					+ totalPrice + " WHERE appliance_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, appId);
			row = ps.executeUpdate();
			System.err.println("row1 : " + row);
			query = "UPDATE eligibility SET down_payment =" + downpayment
					+ ",instalment =" + monthlyPayment + ","
					+ "installment_scheme =" + scheme
					+ " WHERE appliance_id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, appId);
			row = ps.executeUpdate();
			System.err.println("row2 : " + row);
			conn.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			PreparedStatement ps = null;
			ps = con.prepareStatement("select loan_id from sold_to sld "
					+ "Join payment_loan pl on pl.soldto_id = sld.sold_to_id WHERE sld.appliance_id = ?");
			ps.setInt(1, appId);
			rs = ps.executeQuery();
			while (rs.next()) {
				loanId = rs.getInt("loan_id");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		System.err.println("loanId : " + loanId);

		if (loanId != 0) {
			try (Connection conn = Connect.getConnection()) {
				String query = "UPDATE payment_loan SET total_amount ="
						+ totalPrice + ",total_installments =" + scheme
						+ ",installment_amount_month =" + monthlyPayment
						+ ",down_payment =" + downpayment
						+ " WHERE loan_id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, loanId);
				row = ps.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}

		return row;
	}

	public static String checkImeiInAppliance(String imei) {
		String imeiStatus = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection con = Connect.getConnection();) {

			ps = con.prepareStatement("SELECT ap.appliance_name FROM appliance ap WHERE ap.imei_number = ?");
			ps.setString(1, imei);
			rs = ps.executeQuery();
			while (rs.next()) {
				imeiStatus = rs.getString("appliance_name");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return imeiStatus;
	}

	public static String checkNumberInAppliance(String number) {
		String imeiStatus = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection con = Connect.getConnection();) {
			ps = con.prepareStatement("SELECT ap.appliance_name FROM appliance ap WHERE ap.appliance_GSMno = ?");
			ps.setString(1, number);
			rs = ps.executeQuery();
			while (rs.next()) {
				imeiStatus = rs.getString("appliance_name");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return imeiStatus;
	}

	public static HashMap<String, String> countAllStatusAndApplianceStatusDo(
			int districId) {
		HashMap<String, String> map = new HashMap<>();
		int active = 0;
		int inactive = 0;
		int pending = 0;
		int readyToAssign = 0;
		int rdp = 0;
		int owned = 0;
		int returned = 0;
		int awaitingForDownpayment = 0;
		int installed = 0;
		int installedNoSignal = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement preparecall = connection
					.prepareCall("{CALL count_all_status_and_appliance_status_do(?)}");
			preparecall.setInt(1, districId);
			ResultSet resultSet = preparecall.executeQuery();
			while (resultSet.next()) {
				switch (resultSet.getInt("appliance_status")) {
				case 0:
					inactive += resultSet.getInt("count_appliance_status");
					break;
				case 1:
					active += resultSet.getInt("count_appliance_status");
					break;
				}
				switch (resultSet.getInt("status")) {
				case 0:
					pending += resultSet.getInt("count_status");
					break;
				case 1:
					readyToAssign += resultSet.getInt("count_status");
					break;
				case 2:
					rdp += resultSet.getInt("count_status");
					break;
				case 3:
					owned += resultSet.getInt("count_status");
					break;
				case 4:
					returned += resultSet.getInt("count_status");
					break;
				case 5:
					awaitingForDownpayment += resultSet.getInt("count_status");
					break;
				case 6:
					installed += resultSet.getInt("count_status");
					break;
				case 7:
					installedNoSignal += resultSet.getInt("count_status");
					break;
				}
				map.put("active", active + "");
				map.put("inactive", inactive + "");

				map.put("pending", pending + "");
				map.put("readyToAssign", readyToAssign + "");
				map.put("rdp", rdp + "");
				map.put("owned", owned + "");
				map.put("awaitingForDownpayment", awaitingForDownpayment + "");
				map.put("returned", returned + "");
				map.put("installed", installed + "");
				map.put("installedNoSignal", installedNoSignal + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

	public static ArrayList<HashMap<String, Object>> getApplianceBatteryPerformance(
			int appId, String date) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL appliance_battery_performance_graph_datewise(?,?)}");
			callst.setInt(1, appId);
			callst.setString(2, date);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("ApplianceId", rs.getInt("appliance_id"));
				map.put("batteryVoltage", rs.getDouble("battery_voltage"));
				map.put("batteryAmpere", rs.getDouble("battery_ampere"));
				map.put("solarVoltage", rs.getDouble("solar_voltage"));
				map.put("solarAmpere", rs.getDouble("solar_ampare"));
				map.put("applianceTemperature",
						rs.getDouble("appliance_temperature"));
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 2);
				map.put("datetime2", s);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int updateReturned(int applianceId) {
		boolean status = false;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL update_returned_device(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet resultSet = prepareCall.executeQuery();
			status = resultSet.next();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean updateImei(int applianceId, String imei, String gsm,
			String csId, String consumerNum) throws SQLException {
		boolean row = false;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL update_imei_and_consumer(?,?,?,?,?)}");
			call.setInt(1, applianceId);
			call.setString(2, imei);
			call.setString(3, gsm);
			call.setString(4, csId);
			call.setString(5, consumerNum);
			call.executeQuery();
			row = true;
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static boolean checkGsm(String gsm) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_gsm(?)}");
				prepareCall.setString(1, gsm);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkImei(String imei) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_imei(?)}");
				prepareCall.setString(1, imei);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkConsumer(String consumer) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_consumer(?)}");
				prepareCall.setString(1, consumer);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static void revokeByThread(int appliance_id, int csId)
			throws SQLException {
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("DELETE FROM consumer_list WHERE appliance_id="
							+ appliance_id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE appliance SET appliance.imei_number=NULL , appliance.appliance_GSMno=NULL ,appliance.`status`=0 "
							+ "WHERE appliance.`appliance_id`=" + appliance_id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void revokeConsumerNum(int appliance_id, int csId)
			throws SQLException {
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("DELETE FROM consumer_list WHERE appliance_id="
							+ appliance_id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE appliance SET appliance.imei_number=NULL , appliance.appliance_GSMno=NULL , appliance.`status`=1 WHERE appliance.`appliance_id`="
							+ appliance_id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void installDevice(int applianceId) throws SQLException {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL update_device_into_installed(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet resultSet = prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void EditApplianceCredentials(int appliance_id, String gsm,
			String imei, String consumer) throws SQLException {
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE consumer_list SET consumer_list.consumer_number="
							+ consumer
							+ " WHERE consumer_list.appliance_id="
							+ appliance_id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			statement
					.executeUpdate("UPDATE appliance SET appliance.imei_number='"
							+ imei
							+ "', appliance.appliance_GSMno='"
							+ gsm
							+ "' WHERE appliance.appliance_id=" + appliance_id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		System.err.println("EditApplianceCredentials imei  " + imei
				+ "consumer" + consumer + "gsm" + gsm);
	}

	public static void updateNoSignalDevice(int applianceId)
			throws SQLException {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL varify_signals(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet resultSet = prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, String> getDistrictSummary(int appid) {
		HashMap<String, String> map = new HashMap<>();
		int districtId = 0;
		String consumed;
		String produced;
		int StdLoad = 156;
		int StdPower = 187;
		String DistName;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery("SELECT  d.`district_id`" + " FROM appliance a"
					+ " JOIN sold_to s ON s.`appliance_id`=a.`appliance_id`"
					+ " JOIN salesman sm ON s.`salesman_id`=sm.`salesman_id`"
					+ " JOIN city_district cd ON cd.`city_id`=sm.`city_id`"
					+ " JOIN district d ON d.`district_id`=cd.`district_id` "
					+ " WHERE a.`appliance_id`=" + appid
					+ " GROUP BY d.`district_name`");

			if (rs.next()) {
				districtId = rs.getInt("district_id");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		try (Connection con = Connect.getConnection();) {
			ResultSet rss = null;
			Statement s = null;
			s = con.createStatement();
			rss = s.executeQuery("SELECT  AVG(a.`load_consumed`)AS avg_consumed,AVG(a.`power_produced`)AS avg_produced ,d.`district_name`"
					+ " FROM appliance a"
					+ " JOIN sold_to s ON s.`appliance_id`=a.`appliance_id`"
					+ " JOIN salesman sm ON s.`salesman_id`=sm.`salesman_id`"
					+ " JOIN city_district cd ON cd.`city_id`=sm.`city_id`"
					+ " JOIN district d ON d.`district_id`=cd.`district_id`"
					+ " WHERE d.`district_id`=" + districtId);

			if (rss.next()) {
				map.put("consumed", rss.getString("avg_consumed") + " ");
				map.put("produced", rss.getString("avg_produced") + " ");
				map.put("name", rss.getString("district_name") + " ");
				map.put("StdProduced", StdPower + " ");
				map.put("StdLoad", StdLoad + " ");
			}

		} catch (Exception ex) {
			ex.getStackTrace();
		}

		return map;

	}

	public static void main(String arg[]) throws SQLException {

		System.out.print(countAllStatusAndApplianceStatus());
	}
}