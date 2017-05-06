package bal;

import bean.ChartData;
import bean.EligibilityBean;
import bean.EligibilityBean2;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class EligibilityBAL {

	final static Logger logger = Logger.getLogger(EligibilityBAL.class);

	public static EligibilityBean2 getEligibiltyD(int id) {

		EligibilityBean2 bean = null;
		double price, monthlyInstalment, familyIncome, downp;
		Date CreatedOn = null;
		String monthlyIncome;
		int applianceId, customerId, salesmanId, familySize, elegibilityId, paymentMethod, totalInstallments;
		String customerName, customer_phone, applianceName, productId, address, cnic, appliance_phone, salesman, salesmanphone, occupation;
		int apliaceStatus;
		String query = "SELECT cs.customer_name, cs.customer_phone, a.appliance_name, a.appliance_GSMno, a.appliance_price, \n"
				+ "cs.customer_monthly_inc, cs.customer_payment_type, s.salesman_name,s.salesman_phone_no, e.status ,\n"
				+ "((a.appliance_price-e.down_payment)/e.installment_scheme) AS monthlyinstallments, cs.customer_id,a.appliance_id,e.eligibility_id, "
				+ "appliance_product_no,e.down_payment,cs.customer_cnic,cs.customer_address,cs.customer_family_size, cs.customer_family_income,"
				+ " e.installment_scheme,s.salesman_id, cs.occupation FROM eligibility e  \n"
				+ "JOIN customer cs ON cs.customer_id = e.customer_id \n"
				+ "JOIN appliance a ON a.appliance_id = e.appliance_id \n"
				+ "JOIN salesman s ON s.salesman_id = e.salesman_id WHERE eligibility_id = "
				+ id + ";";
		Statement s = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customerName = rs.getString(1);
				customer_phone = rs.getString(2);
				applianceName = rs.getString(3);
				appliance_phone = rs.getString(4);
				price = rs.getDouble(5);
				monthlyIncome = rs.getString(6);
				paymentMethod = rs.getInt(7);
				salesman = rs.getString(8);
				salesmanphone = rs.getString(9);
				apliaceStatus = rs.getInt(10);
				monthlyInstalment = rs.getDouble(11);
				customerId = rs.getInt(12);
				applianceId = rs.getInt(13);
				elegibilityId = rs.getInt(14);
				productId = rs.getString(15);
				downp = rs.getDouble(16);
				cnic = rs.getString(17);
				address = rs.getString(18);
				familySize = rs.getInt(19);
				familyIncome = rs.getDouble(20);
				CreatedOn = rs.getDate(21);
				totalInstallments = rs.getInt(22);
				salesmanId = rs.getInt(23);
				occupation = rs.getString(23);
				bean = new EligibilityBean2();
				bean.setCustomerName(customerName);
				bean.setCustomer_number(customer_phone);
				bean.setApplianceName(applianceName);
				bean.setAppliance_number(appliance_phone);
				bean.setAppliancePrice(price);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setPaymentMethod(paymentMethod);
				bean.setSalesmanName(salesman);
				bean.setSalesManNumber(salesmanphone);
				bean.setStatus(apliaceStatus);
				bean.setMonthlyInstallment(monthlyInstalment);
				bean.setCustomerId(customerId);
				bean.setApplianceId(applianceId);
				bean.setElegibilityId(elegibilityId);
				bean.setFamilySize(familySize);
				bean.setAddress(address);
				bean.setTotalInstallments(totalInstallments);
				bean.setFamilyIncome(familyIncome);
				bean.setCnic(cnic);
				bean.setProductId(productId);
				bean.setDateOfBirth(CreatedOn);
				bean.setDownpayment(downp);
				bean.setSalesmanId(salesmanId);
				bean.setOccupation(occupation);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static ArrayList<EligibilityBean2> getEligibiltyDo_pending(
			int district) {

		ArrayList<EligibilityBean2> list = new ArrayList<EligibilityBean2>();
		double price, monthlyInstalment;
		String monthlyIncome;
		int customerId, paymentMethod;
		String customerName, customer_phone, applianceName, appliance_phone, salesman, salesmanphone;
		int apliaceStatus;
		String query = "SELECT cs.customer_name, cs.customer_phone, a.appliance_name, a.appliance_GSMno, a.appliance_price, \n"
				+ "cs.customer_monthly_income, cs.customer_payment_type, s.salesman_name,s.salesman_phone_no, e.status ,\n"
				+ "((a.appliance_price-e.down_payment)/e.installment_scheme) AS monthlyinstallments, cs.customer_id\n"
				+ "FROM eligibility e  \n"
				+ "JOIN customer cs ON cs.customer_id = e.customer_id \n"
				+ "JOIN appliance a ON a.appliance_id = e.appliance_id \n"
				+ "JOIN salesman s ON s.salesman_id = e.salesman_id join city c on cs.customer_city=c.city_id join city_district cd on cs.customer_city=cd.city_id join salary sal on cs.customer_monthly_income=sal.salary_id WHERE (e.status = 0 ) AND (cd.district_id = "
				+ district + ") ORDER BY customer_id DESC;";
		Statement s = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			EligibilityBean2 bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customerName = rs.getString(1);
				customer_phone = rs.getString(2);
				applianceName = rs.getString(3);
				appliance_phone = rs.getString(4);
				price = rs.getDouble(5);
				monthlyIncome = rs.getString(6);
				paymentMethod = rs.getInt(7);
				salesman = rs.getString(8);
				salesmanphone = rs.getString(9);
				apliaceStatus = rs.getInt(10);
				monthlyInstalment = rs.getDouble(11);
				customerId = rs.getInt(12);
				bean = new EligibilityBean2();
				bean.setCustomerName(customerName);
				bean.setCustomer_number(customer_phone);
				bean.setApplianceName(applianceName);
				bean.setAppliance_number(appliance_phone);
				bean.setAppliancePrice(price);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setPaymentMethod(paymentMethod);
				bean.setSalesmanName(salesman);
				bean.setSalesManNumber(salesmanphone);
				bean.setStatus(apliaceStatus);
				bean.setMonthlyInstallment(monthlyInstalment);
				bean.setCustomerId(customerId);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<EligibilityBean2> getEligibiltyDo_accepted(
			int district) {

		ArrayList<EligibilityBean2> list = new ArrayList<EligibilityBean2>();
		double price, monthlyInstalment;
		String monthlyIncome;
		int customerId, paymentMethod;
		String customerName, customer_phone, applianceName, appliance_phone, salesman, salesmanphone;
		int apliaceStatus;
		String query = "SELECT cs.customer_name, cs.customer_phone, a.appliance_name, a.appliance_GSMno, a.appliance_price, \n"
				+ "sal.salary_range, cs.customer_payment_type, s.salesman_name,s.salesman_phone_no, e.status ,\n"
				+ "((a.appliance_price-e.down_payment)/e.installment_scheme) AS monthlyinstallments, cs.customer_id\n"
				+ "FROM eligibility e  \n"
				+ "JOIN customer cs ON cs.customer_id = e.customer_id \n"
				+ "JOIN appliance a ON a.appliance_id = e.appliance_id \n"
				+ "JOIN salesman s ON s.salesman_id = e.salesman_id join salary sal on cs.customer_monthly_income=sal.salary_id join city c on cs.customer_city=c.city_id  JOIN city_district cd ON cs.customer_city=cd.city_id WHERE (e.status = 1 ) AND (cd.district_id = "
				+ district + ") ORDER BY customer_id DESC;";
		Statement s = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			EligibilityBean2 bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customerName = rs.getString(1);
				customer_phone = rs.getString(2);
				applianceName = rs.getString(3);
				appliance_phone = rs.getString(4);
				price = rs.getDouble(5);
				monthlyIncome = rs.getString(6);
				paymentMethod = rs.getInt(7);
				salesman = rs.getString(8);
				salesmanphone = rs.getString(9);
				apliaceStatus = rs.getInt(10);
				monthlyInstalment = rs.getDouble(11);
				customerId = rs.getInt(12);
				bean = new EligibilityBean2();
				bean.setCustomerName(customerName);
				bean.setCustomer_number(customer_phone);
				bean.setApplianceName(applianceName);
				bean.setAppliance_number(appliance_phone);
				bean.setAppliancePrice(price);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setPaymentMethod(paymentMethod);
				bean.setSalesmanName(salesman);
				bean.setSalesManNumber(salesmanphone);
				bean.setStatus(apliaceStatus);
				bean.setMonthlyInstallment(monthlyInstalment);
				bean.setCustomerId(customerId);
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static int getEligibilitySingleId(int id) {
		ResultSet rs = null;
		PreparedStatement ps = null;

		int data = 0;
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT DISTINCT(e.salesman_id) FROM salesman INNER JOIN eligibility e WHERE appliance_id = "
					+ id + " ORDER BY e.salesman_id";
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				data = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return data;
	} // end of getting all customers form Db

	public static ArrayList<EligibilityBean> getEligibility() {

		System.out.println("EligibilityBAL.getEligibility()");
		EligibilityBean bean = null;
		ArrayList<EligibilityBean> customerList = new ArrayList<EligibilityBean>();
		// ArrayList<HashMap<String, String>> hsmap = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call / Jetander
			// CallableStatement prepareCall =
			// con.prepareCall("{call get_eligibilities()}");
			CallableStatement prepareCall = con
					.prepareCall("{Call get_eligibilities()}");
			ResultSet rs = prepareCall.executeQuery();

			while (rs.next()) {
				// HashMap<String, String> map =new HashMap<>();
				// map.put("id", rs.getInt("eligibility_id")+"");
				// map.put("customerId", rs.getInt("customer_id")+"");
				// map.put("appId", rs.getInt("appliance_id")+"");
				// map.put("salID", rs.getInt("salesman_id")+"");
				// map.put("customerName", rs.getString("customer_name"));
				// map.put("appName", rs.getString("appliance_name"));
				// map.put("appPrice",rs.getString("appliance_price"));
				// map.put("appPrice",rs.getInt("status")+"");
				// map.put("salesman",rs.getString("salesman_name"));
				// map.put("salry range",rs.getString("salary_range")+"");
				// map.put("instalment", rs.getDouble("instalment")+"");
				// map.put("scheme", rs.getInt("installment_scheme")+"");
				// map.put("customerPhone",rs.getString("customer_phone"));
				// map.put("city", rs.getString("city_name"));
				// map.put("infoStatus",rs.getInt("info_status")+"");
				// map.put("cnic", rs.getString("customer_cnic"));
				// map.put("salesPhone", rs.getString("salesman_phone_no"));
				// map.put("appliancGSm", rs.getString("appliance_GSMno"));
				// map.put("seen", rs.getInt("seen")+"");
				// map.put("userName",rs.getString("user_name"));
				// map.put("foname", rs.getString("fo_name"));
				// hsmap.add(map);
				bean = new EligibilityBean();
				bean.setElegibilityId(rs.getInt("eligibility_id"));
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setApplianceId(rs.getInt("appliance_id"));
				bean.setSalesmanId(rs.getInt("salesman_id"));
				bean.setCustomerName(rs.getString("customer_name"));
				bean.setApplianceName(rs.getString("appliance_name"));
				bean.setAppliancePrice(rs.getDouble("appliance_price"));
				bean.setStatus(rs.getInt("status"));
				bean.setSalesmanName(rs.getString("salesman_name"));
				bean.setMonthlyIncome(rs.getString("salary_range"));
				bean.setMonthlyInstallment(rs.getDouble("instalment"));
				bean.setTotalInstallments(rs.getInt("installment_scheme"));

				bean.setCustomer_number(rs.getString("customer_phone"));
				bean.setCity(rs.getString("city_name"));
				bean.setDistrict(rs.getString("district_name"));

				bean.setInfoStatus(rs.getInt("info_status"));
				bean.setCnic(rs.getString("customer_cnic"));
				bean.setElectricity(rs.getString("grid_electricity"));
				bean.setGenerator(rs.getString("generator"));
				bean.setUps(rs.getString("ups"));
				bean.setSolar(rs.getString("solar"));
				bean.setSalesManNumber(rs.getString("salesman_phone_no"));
				bean.setAppliance_number(rs.getString("appliance_GSMno"));
				bean.setSeen(rs.getInt("seen"));
				bean.setDoName(rs.getString("user_name"));
				bean.setFoName(rs.getString("fo_name"));

				customerList.add(bean);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getLoanRequest(int str,
			int rng, int col, String orde, String search) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);

			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequest(int str,
			int rng, int col, String orde, String search, int doId) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static int getLoanRequestCount(String search) {
		int count = 0;
		System.out.println("EligibilityBAL.getLoanRequestCount()");
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_count`(?)}");
			prepareCall.setString(1, search);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				count = rs.getInt("counts");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} // end of getting all customers form Loan Requests Count

	public static int getDoLoanRequestCount(String search, int doId) {
		int count = 0;
		System.out.println("EligibilityBAL.getLoanRequestCount()");
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_count_do`(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setInt(2, doId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counts");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} // end of getting all customers form Loan Requests Count

	public static ArrayList<HashMap<String, String>> getEligibiltyDo(int doId) {
		ResultSet rs = null;

		System.out.println("CustomerBAL.get_do_eligibilities2(districtId)");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			// Altered By Me
			CallableStatement prepareCall = con
					.prepareCall("{call get_do_eligibilities2(?)}");
			prepareCall.setInt(1, doId);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("elgID", rs.getInt("eligibility_id") + "");
				map.put("status", rs.getInt("status") + "");
				map.put("do_id", rs.getInt("do_id") + "");
				map.put("downPay", rs.getDouble("down_payment") + "");
				map.put("customerName", rs.getString("cs.customer_name"));
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancPhone", rs.getString("appliance_GSMno"));
				map.put("salary", rs.getString("salary_range"));
				map.put("salesman", rs.getString("salesman_name"));
				map.put("morInfo", rs.getInt("seen") + "");
				map.put("installment", rs.getDouble("instalment") + "");
				map.put("customerId", rs.getInt("customer_id") + "");
				map.put("customerCnic", rs.getString("customer_cnic"));
				map.put("dayafterActive", rs.getInt("dayafterActive") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("scheme", rs.getString("installment_scheme"));
				map.put("city", rs.getString("city_name"));
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<EligibilityBean2> getAcceptedEligibiltyDo(
			int district) {
		ResultSet rs = null;

		ArrayList<EligibilityBean2> list = new ArrayList<EligibilityBean2>();
		double price, monthlyInstalment;
		String monthlyIncome;
		int applianceId, customerId, elegibilityId, paymentMethod;
		String customerName, customer_phone, applianceName, appliance_phone, salesman, salesmanphone;
		int apliaceStatus;
		String query = "SELECT cs.customer_name, cs.customer_phone, a.appliance_name, a.appliance_GSMno, a.appliance_price, \n"
				+ "sal.salary_range, cs.customer_payment_type, s.salesman_name,s.salesman_phone_no, e.status ,\n"
				+ "((a.appliance_price-e.down_payment)/e.installment_scheme) AS monthlyinstallments, cs.customer_id,a.appliance_id,eligibility_id\n"
				+ "FROM eligibility e  \n"
				+ "JOIN customer cs ON cs.customer_id = e.customer_id \n"
				+ "JOIN appliance a ON a.appliance_id = e.appliance_id \n"
				+ "JOIN city_district cd ON cs.customer_city=cd.city_id \n"
				+ "JOIN salary sal ON cs.customer_monthly_income=sal.salary_id \n"
				+ "JOIN salesman s ON s.salesman_id = e.salesman_id WHERE (e.status=1 AND e.accepted_seen = 0) AND (cd.district_id = "
				+ district + ") ORDER BY customer_id DESC;";
		Statement s = null;

		try (Connection con = Connect.getConnection()) {
			EligibilityBean2 bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				customerName = rs.getString(1);
				customer_phone = rs.getString(2);
				applianceName = rs.getString(3);
				appliance_phone = rs.getString(4);
				price = rs.getDouble(5);
				monthlyIncome = rs.getString(6);
				paymentMethod = rs.getInt(7);
				salesman = rs.getString(8);
				salesmanphone = rs.getString(9);
				apliaceStatus = rs.getInt(10);
				monthlyInstalment = rs.getDouble(11);
				customerId = rs.getInt(12);
				applianceId = rs.getInt(13);
				elegibilityId = rs.getInt(14);
				bean = new EligibilityBean2();
				bean.setCustomerName(customerName);
				bean.setCustomer_number(customer_phone);
				bean.setApplianceName(applianceName);
				bean.setAppliance_number(appliance_phone);
				bean.setAppliancePrice(price);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setPaymentMethod(paymentMethod);
				bean.setSalesmanName(salesman);
				bean.setSalesManNumber(salesmanphone);
				bean.setStatus(apliaceStatus);
				bean.setMonthlyInstallment(monthlyInstalment);
				bean.setCustomerId(customerId);
				bean.setApplianceId(applianceId);
				bean.setElegibilityId(elegibilityId);
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static int getUnseenRequests() {
		System.out.println("EligibilityBAL.getUnseenRequests() ");
		int countEligibility = 0;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			// Begin Stored Procedure Calling -- Jeevan
			if (con != null) {
				prepareCall = con
						.prepareCall("{call count_unseen_eligibility_request()}");
				ResultSet resultSet = prepareCall.executeQuery();
				resultSet.next();
				countEligibility = resultSet.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		} finally {
		}
		return countEligibility;
	} // end of getting unseen notifications of Loan Request

	public static int getUnseenRequestsByDistrict(int districtId) {

		System.out.println("EligibilityBAL.getUnseenRequests() ");

		int countEligibility = 0;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			// --------------------------------------------------------------------------
			// Begin Stored Procedure Calling -- Jeevan
			if (con != null) {
				prepareCall = con
						.prepareCall("{call count_unseen_eligibility_request_by_district(?)}");
				prepareCall.setInt(1, districtId);
				// prepareCall.registerOutParameter(1, Types.INTEGER);
				ResultSet resultSet = prepareCall.executeQuery();
				resultSet.next();
				countEligibility = resultSet.getInt(1);
				// End Stored Procedure Calling -- Jeevan
				// --------------------------------------------------------------------------

				prepareCall.close();

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return countEligibility;
	} // end of getting unseen notifications of Loan Request

	public static int updateSeenNotifications(int id) {

		Statement st = null;

		String query = "Update eligibility SET accepted_seen=" + 1
				+ " WHERE eligibility_id=" + id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

	// Removed by me :P
	// public static ArrayList<EligibilityBean2> getEligibiltyDos(int
	// districtId) {
	// ResultSet rs=null;
	//
	// System.out.println("CustomerBAL.get_do_eligibilities(districtId)");
	// ArrayList<EligibilityBean2> list = new ArrayList<EligibilityBean2>();
	// EligibilityBean2 bean = null;
	// double price, monthlyInstalment, downpayment;
	// String monthlyIncome;
	// int applianceId, customerId, elegibilityId;
	// String customerName, customer_phone, applianceName, appliance_phone,
	// salesman, salesmanphone;
	// int apliaceStatus;
	// Connection con=connection.Connect.getConnection();
	// try {
	// if(con != null){
	// // Begin Stored Procedure Calling -- Jetander
	// CallableStatement prepareCall =
	// con.prepareCall("{call get_do_eligibilities(?)}");
	// prepareCall.setInt(1, districtId);
	// rs = prepareCall.executeQuery();
	// while (rs.next()) {
	// customerName = rs.getString(1);
	// customer_phone = rs.getString(2);
	// applianceName = rs.getString(3);
	// appliance_phone = rs.getString(4);
	// price = rs.getDouble(5);
	// monthlyIncome = rs.getString(6);
	// salesman = rs.getString(7);
	// salesmanphone = rs.getString(8);
	// apliaceStatus = rs.getInt(9);
	// monthlyInstalment = rs.getDouble(10);
	// customerId = rs.getInt(11);
	// applianceId = rs.getInt(12);
	// elegibilityId = rs.getInt(13);
	// downpayment = rs.getDouble(14);
	//
	// bean = new EligibilityBean2();
	// bean.setCustomerName(customerName);
	// bean.setCustomer_number(customer_phone);
	// bean.setApplianceName(applianceName);
	// bean.setAppliance_number(appliance_phone);
	// bean.setAppliancePrice(price);
	// bean.setMonthlyIncome(monthlyIncome);
	// bean.setSalesmanName(salesman);
	// bean.setSalesManNumber(salesmanphone);
	// bean.setStatus(apliaceStatus);
	// bean.setMonthlyInstallment(monthlyInstalment);
	// bean.setCustomerId(customerId);
	// bean.setApplianceId(applianceId);
	// bean.setElegibilityId(elegibilityId);
	// bean.setDownpayment(downpayment);
	// list.add(bean);
	// }
	// }
	// rs.close();
	//
	// con.close();
	// } catch (SQLException ex) {
	// System.out.println(ex.getMessage());
	// }
	// return list;
	// }

	public static int InsertEligibilty(int customer_id, int appliance_id,
			int salesman_id, double downpayment, int scheme,
			double installment, int infoStatus) throws SQLException {

		int rows = 0;
		try (Connection con = Connect.getConnection();
				Statement statement = con.createStatement()) {
			rows = statement
					.executeUpdate("INSERT INTO eligibility(customer_id,appliance_id,salesman_id,date,down_payment,installment_scheme, instalment,status, info_status) VALUES ("
							+ customer_id
							+ ","
							+ appliance_id
							+ ","
							+ salesman_id
							+ ", CURRENT_TIMESTAMP  ,"
							+ downpayment
							+ ","
							+ scheme
							+ ","
							+ installment
							+ "," + 0 + "," + infoStatus + ");");
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return rows;
	}

	public int insertIntoEligibility(HashMap<String, String> map) {

		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_into_eligibility(?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setInt(2, Integer.parseInt(map.get("applianceId")));
			prepareCall.setInt(3, Integer.parseInt(map.get("salesmanId")));
			prepareCall
					.setDouble(4, Double.parseDouble(map.get("installment")));
			prepareCall.setInt(5,
					Integer.parseInt(map.get("installmentScheme")));

			prepareCall
					.setDouble(6, Double.parseDouble(map.get("downPayment")));
			prepareCall.setInt(7, Integer.parseInt(map.get("status")));

			ResultSet resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public static HashMap<String, String> getDashboardData(String userType,
			int userId) {
		HashMap<String, String> map = new HashMap<>();
		System.out.println("EligibilityBAL.getDashboardData()");
		try (Connection con = Connect.getConnection()) {
			CallableStatement cs = con
					.prepareCall("{CALL get_payment_status(?, ?)}");
			cs.setString(1, userType);
			cs.setInt(2, userId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			String columns[] = new String[metaData.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
			}
			if (rs.next()) {
				for (String column : columns) {
					map.put(column, rs.getString(column));
				}
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getPaymentHistory(
			String userType, int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		System.out.println("EligibilityBAL.getPaymentHistory()");

		try (Connection con = Connect.getConnection()) {
			CallableStatement cs = con
					.prepareCall("{CALL get_payment_history(?, ?)}");
			cs.setString(1, userType);
			cs.setInt(2, userId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			String columns[] = new String[metaData.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String column : columns) {
					map.put(column, rs.getString(column));
				}
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllCustomersByFO(
			int foId) {
		System.out.println("EligibilityBAL.getAllCustomersByFO()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_eligibility_by_fo`(?)}");
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

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllAcceptedEligibilityByFO(
			int foId) {
		System.out.println("EligibilityBAL.getAllAcceptedCustomersByFO()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_accepted_eligibility_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap map = new HashMap();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				String customerId = (String) map.get("customer_id");
				System.out.println(customerId);
				map.put("assets",
						getAllAssetsByCustomer(Integer.parseInt(customerId)));

				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllAssetsByCustomer(
			int customerId) {
		System.out.println("EligibilityBAL.getAllAssetsByCustomer()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_assets_by_customer`(?)}");
			prepareCall.setInt(1, customerId);
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

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllReadyForPickUpEligibilityByFO(
			int foId) {
		System.out
				.println("EligibilityBAL.getAllReadyForPickUpEligibilityByFO()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_ready_for_pickup_eligibility_by_fo`(?)}");
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

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllHandoveredEligibilityByFO(
			int foId) {
		System.out.println("EligibilityBAL.getAllHandoveredEligibilityByFO()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_handovered_eligibility_by_fo`(?)}");
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

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static boolean updateEligibilityRequest(int eligibilityId, int status) {
		System.out.println("EligibilityBAL.updateEligibilityRequest()");
		boolean result = false;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `update_eligibility_status`(?, ?)}");
			prepareCall.setInt(1, eligibilityId);
			prepareCall.setInt(2, status);
			if (prepareCall.executeUpdate() == 1)
				result = true;

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return result;
	}

	public static int insertIntoRejection(int eligibilityId) {
		int customerId = 0;
		try (Connection con = Connect.getConnection()) {

			Statement statement = con.createStatement();
			ResultSet rs = null;
			rs = statement
					.executeQuery("Select customer_id from eligibility WHERE eligibility_id = "
							+ eligibilityId);

			while (rs.next()) {
				customerId = rs.getInt(1);
			}
			String text = "Fo rejected";
			statement
					.executeUpdate("insert into loan_rejection_purpose(customer_id,text) values("
							+ customerId + ",'" + text + "')");

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return customerId;
	}

	public static int updateEligibiltyStatus(int applianceId)
			throws SQLException {
		int rows = 0;
		try (Connection con = Connect.getConnection()) {

			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("UPDATE eligibility e SET e.`status` = 8 WHERE e.`appliance_id` = "
								+ applianceId);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return rows;
	}

	public static int updateAWDdate(int applianceId) throws SQLException {
		int rows = 0;
		try (Connection con = Connect.getConnection()) {

			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("UPDATE sold_to e SET e.`status` = 8 WHERE e.`appliance_id` = "
								+ applianceId);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return rows;
	}

	public static ArrayList<HashMap<String, String>> getTopFiveNDs() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_top_five_nds()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("nd_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getting unseen notifications of Loan Request

	public static ArrayList<HashMap<String, String>> getTopFiveFos() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_top_fos()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fo_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getting unseen notifications of Loan Request

	public static ArrayList<HashMap<String, String>> getTopFiveFosSales(
			int month) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_top_fos_sales(?)}");
				prepareCall.setInt(1, month);
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fo_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getting unseen notifications of Loan Request

	public static ArrayList<HashMap<String, String>> getTopFiveDos() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_top_five_dos()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("do_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					map.put("last_sale", resultSet.getInt(5) + "");
					map.put("average",
							roundOffTo2DecPlaces(resultSet.getDouble(3)
									/ resultSet.getDouble(6))
									+ "");
					map.put("district_wise_rating", resultSet.getInt(7) + "");
					map.put("recovery",
							((resultSet.getInt(9) - resultSet.getInt(8)) * 100)
									/ resultSet.getInt(9) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getting unseen notifications of Loan Request

	public static ArrayList<ChartData> getChartData() {
		ArrayList<ChartData> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_chart_data()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					ChartData data = new ChartData();
					data.setDate(resultSet.getString(2));
					if (resultSet.getString(3).equals("Asghar Ali")) {
						data.setDoName(resultSet.getString(1));
					} else if (resultSet.getString(3).equals("Syed Najaf Ali")) {
						data.setDoName(resultSet.getString(1));
					} else if (resultSet.getString(3)
							.equals("Zulfiqar Hussain")) {
						data.setDoName(resultSet.getString(1));
					} else if (resultSet.getString(3).equals("Liaqat Ali Khan")) {
						data.setDoName(resultSet.getString(1));
					}
					list.add(data);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end of getting unseen notifications of Loan Request

	public static int updateTransferStatus(int applianceId) throws SQLException {

		int rows = 0;
		try (Connection con = Connect.getConnection()) {

			try (Statement statement = con.createStatement()) {
				rows = statement
						.executeUpdate("UPDATE appliance SET status = 2 WHERE appliance_id = "
								+ applianceId);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return rows;

	}

	public static ArrayList<HashMap<String, String>> getLateRecoveries() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call Late_Recoveries_district_wise()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("do_name", resultSet.getInt(1) + "");
					map.put("district", resultSet.getInt(2) + "");
					map.put("current_month_sale", resultSet.getString(3) + "");

					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getRecoveries() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		double total_expected = 0;
		float total_recoverd, expected, expected_defaulter, total_portfolio = 0;
		double total_recoverd_amount = 0;
		int totalAmount = 0;
		int totalRecovered = 0;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call current_month_recovery()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("district_name", resultSet.getString(1) + "");
					map.put("total_sale", resultSet.getInt(2) + "");
					map.put("total_amount", resultSet.getInt(3) + "");

					if (resultSet.getInt(4) == 0) {
						total_recoverd = 0;
					} else {
						total_recoverd = ((float) resultSet.getInt(4))
								/ resultSet.getInt(3) * 100;
					}

					if (resultSet.getInt(6) == 0) {
						expected = 0;
					} else {
						expected = ((float) resultSet.getInt(6))
								/ resultSet.getInt(3) * 100;
					}
					if (resultSet.getInt(8) == 0) {
						expected_defaulter = 0;
					} else {
						expected_defaulter = ((float) resultSet.getInt(8))
								/ resultSet.getInt(3) * 100;
					}
					expected_defaulter = (float) Double
							.parseDouble(new DecimalFormat("##.##")
									.format(expected_defaulter));
					total_recoverd = (float) Double
							.parseDouble(new DecimalFormat("##.##")
									.format(total_recoverd));
					expected = (float) Double.parseDouble(new DecimalFormat(
							"##.##").format(expected));

					total_expected = resultSet.getInt(3) + total_expected;
					if (resultSet.getInt(4) == 0) {
						total_recoverd_amount = 0 + total_recoverd_amount;
					}
					total_recoverd_amount = resultSet.getInt(4)
							+ total_recoverd_amount;
					total_portfolio = resultSet.getInt(3) + total_portfolio;

					map.put("recovered", total_recoverd + "");
					map.put("expected", expected + "");
					map.put("defaulter", expected_defaulter + "");
					map.put("total_recovered", resultSet.getInt(4) + "");

					map.put("current_month_total", resultSet.getInt(3) + "");
					totalAmount = totalAmount + resultSet.getInt(3);
					totalRecovered = totalRecovered + resultSet.getInt(4);
					map.put("totalAmount", totalAmount + "");
					map.put("totalRecovered", totalRecovered + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> monthlyPortfolio() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call current_month_recovery_breakup()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					map = new HashMap<>();
					map.put("district", resultSet.getString(1) + "");
					map.put("sales", resultSet.getInt(2) + "");
					map.put("portfolio",
							roundOffTo2DecPlaces(resultSet.getDouble(3)) + "");
					map.put("recovered",
							roundOffTo2DecPlaces(resultSet.getDouble(4)) + "");
					map.put("expected",
							roundOffTo2DecPlaces(resultSet.getDouble(5)) + "");
					map.put("late",
							roundOffTo2DecPlaces(resultSet.getDouble(6)) + "");
					map.put("total",
							roundOffTo2DecPlaces(resultSet.getDouble(7)) + "");
					map.put("previousRecoveryUnpaid", resultSet.getInt(8) + "");
					map.put("previousRecoveryPaid", resultSet.getInt(9) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}

	public static HashMap<String, String> totalPortfolioHealth() {
		HashMap<String, String> map = null;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call total_portfolio_health()}");
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					map = new HashMap<>();
					map.put("BeforeTen",
							roundOffTo2DecPlaces(resultSet.getDouble(1)) + "");
					map.put("BeforeFive",
							roundOffTo2DecPlaces(resultSet.getDouble(2)) + "");
					map.put("BeforeDueDate",
							roundOffTo2DecPlaces(resultSet.getDouble(3)) + "");
					map.put("dueDate",
							roundOffTo2DecPlaces(resultSet.getDouble(4)) + "");
					map.put("MinusOneToFive",
							roundOffTo2DecPlaces(resultSet.getDouble(5)) + "");
					map.put("MinusSixToTen",
							roundOffTo2DecPlaces(resultSet.getDouble(6)) + "");
					map.put("AfterMinusTen",
							roundOffTo2DecPlaces(resultSet.getDouble(7)) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getrating_portfolio() {
		HashMap<String, String> map = new HashMap<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call Recovery_portfolio()}");
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					int total_sale = 0;
					total_sale = resultSet.getInt(1);
					double a = (resultSet.getInt(2) / total_sale);
					map.put("sixto10",
							Math.round((((float) resultSet.getInt(2) / (float) total_sale) * 100))
									+ "");
					map.put("oneto5",
							Math.round(((float) resultSet.getInt(3) / (float) total_sale) * 100)
									+ "");
					map.put("zeroto0",
							Math.round(((float) resultSet.getInt(4) / (float) total_sale) * 100)
									+ "");
					map.put("4to0",
							Math.round(((float) resultSet.getInt(5) / (float) total_sale) * 100)
									+ "");
					map.put("tento0",
							Math.round(((float) resultSet.getInt(6) / (float) total_sale) * 100)
									+ "");
					map.put("hundredto0",
							Math.round(((float) resultSet.getInt(7) / (float) total_sale) * 100)
									+ "");

				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;

	}

	public static ArrayList<HashMap<String, String>> getDistrict_wise_rating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call District_wise_rating()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("district_name", resultSet.getString(1) + "");
					map.put("district_rating", resultSet.getInt(2) + "");

					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	}

	public static HashMap<String, Integer> countDoLoanRequestFilters(int doId) {
		System.out.println("EligibilityBAL.count_do_loan_application()");
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_loan_application`(?)}");
			prepareCall.setInt(1, doId);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countSALoanRequestFilters() {
		System.out.println("EligibilityBAL.count_sa_loan_application()");
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_sa_loan_application`()}");
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countDoLoanRequestFiltersWithSearch(
			int doId, String search) {
		System.out.println("EligibilityBAL.count_do_loan_application()");
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_loan_application_search`(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countSALoanRequestFiltersWithSearch(
			String search) {
		System.out.println("EligibilityBAL.count_sa_loan_application_search()");
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_sa_loan_application_search`(?)}");
			prepareCall.setString(1, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanRequestPending(
			int str, int rng, int col, String orde, String search, int doId) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do_pending`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getSALoanRequestPending(
			int str, int rng, int col, String orde, String search) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_pending`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequestAccepted(
			int str, int rng, int col, String orde, String search, int doId) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do_accepted`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getSALoanRequestAccepted(
			int str, int rng, int col, String orde, String search) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_accepted`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequestVarified(
			int str, int rng, int col, String orde, String search, int doId) {

		System.out.println("EligibilityBAL.getLoanRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do_varified`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getSALoanRequestVarified(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_varified`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static String roundOffTo2DecPlaces(double val) {
		return String.format("%.2f", val);
	}

	public static void main(String[] args) {
		System.out.println(monthlyPortfolio());
	}

}