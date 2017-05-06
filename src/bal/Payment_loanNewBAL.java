package bal;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.mysql.jdbc.Statement;

import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;
import bean.Payment_LoanBean;
import connection.Connect;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;

public class Payment_loanNewBAL {

	private static ResultSet rs = null;

	private static PreparedStatement pstmt;
	private static Connection con;
	private final static Logger logger = Logger
			.getLogger(Payment_loanNewBAL.class);

	public static CustomerLoanBean getApplianceDetailsForLoanAccount(
			int applianceId) {
		System.out.print("Payment_loanNewBAL.get_appliance_loan_info");
		CustomerLoanBean bean = null;
		int customerId, loanId, salesmanId, total_installments, grace_period, applianceStatus;
		double appliancePrice, remaining_balance, total_amount, monthly_amount, down_payment;
		String CustomerName, CustomerCnic, applianceName, salesmanName, imeiNumber, date, terminate_date, customerPhone, salemanPhone;
		int other_loan;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_appliance_loan_info(?)}");
				prepareCall.setInt(1, applianceId);
				ResultSet rs = prepareCall.executeQuery();
				if (rs.next()) {
					CustomerName = rs.getString(1);
					total_installments = rs.getInt(2);
					date = rs.getString(3);
					grace_period = rs.getInt(4);
					down_payment = rs.getDouble(5);
					remaining_balance = rs.getDouble(6);
					terminate_date = rs.getString(7);
					total_amount = rs.getDouble(8);
					monthly_amount = rs.getDouble(9);
					customerId = rs.getInt(10);
					customerPhone = rs.getString(11);
					salemanPhone = rs.getString(12);
					applianceName = rs.getString(13);
					imeiNumber = rs.getString(14);
					appliancePrice = rs.getDouble(15);
					applianceStatus = rs.getInt(16);
					loanId = rs.getInt(17);
					salesmanId = rs.getInt(18);
					salesmanName = rs.getString(19);
					String status = rs.getString("loanStatus");
					other_loan = rs.getInt("other_loan");
					int appliance_id = rs.getInt(21);
					bean = new CustomerLoanBean();
					bean.setOther_loan(other_loan);
					bean.setCustomerId(customerId);
					bean.setCustomerName(CustomerName);
					bean.setTotal_installments(total_installments);
					bean.setCreated_on(date);
					bean.setGrace_period(grace_period);
					bean.setDownPayment(down_payment);
					bean.setRemaining_balanse(remaining_balance);
					bean.setTerminate_date(terminate_date);
					bean.setTotal_amount(total_amount);
					bean.setMonthly_amount(monthly_amount);
					bean.setPhoneNo(customerPhone);
					bean.setSalemanPhone(salemanPhone);
					bean.setApplianceName(applianceName);
					bean.setGsmNumber(rs.getString("appliance_GSMno"));
					bean.setAppliancePrice(appliancePrice);
					bean.setApplianceStatus(applianceStatus);
					bean.setLoanId(loanId);
					bean.setSalesmanId(salesmanId);
					bean.setSalesmanName(salesmanName);
					bean.setLoanStatus(status);
					bean.setAppliance_id(appliance_id);
					bean.setFieldOfficer(rs.getString("fo_name"));
					bean.setUserName(rs.getString("user_name"));
					bean.setImeiNumber(imeiNumber);
					bean.setCnicNo(rs.getString("cs.customer_cnic"));
					bean.setFoid(rs.getInt("fo_id"));
					bean.setCustomer_Rating(rs.getInt("rating"));
					bean.setDoid(rs.getInt("user_id"));
					bean.setConsumer_number(rs.getString("consumer_number"));

				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // seraching customer by firstName

	
	
	
	public static ArrayList<HashMap<String, String>> getOwnApplianceList(
			int ploan_id) {
		
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		// made by Abbas
		try (Connection connection = Connect.getConnection();) {
			// Begin Procedure Call // Abbas
			CallableStatement cs = connection
					.prepareCall("{call get_own_appliance(?)}");
			cs.setInt(1, ploan_id);
			ResultSet rs = cs.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
//				System.err.println(metaData.getColumnLabel(a + 1));
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					if(string.equalsIgnoreCase("created_on") || string.equalsIgnoreCase("returned_date")){
						map.put(string, ""+rs.getDate(string));
					}else{
						map.put(string, rs.getString(string));
					}
//					System.err.println(rs.getString(string));
				}
				System.err.println(map);
				list.add(map);
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}
	
	
	// TODO Remove unused code found by UCDetector
	// public static ArrayList<Payment_LoanBean> getDoPayments_detail(int id) {
	// Payment_LoanBean bean = null;
	// ArrayList<Payment_LoanBean> paymentList = new
	// ArrayList<Payment_LoanBean>();
	// int grace_period, appliance_id, loan_id, customer_id, status, scheme;
	// double total_amount, remaining_balance, paid_amount;
	// String appliance_name, customer_name, customer_phone, district,
	// salesman_name;
	// Date terminate;
	// try {
	// Connection con = connection.Connect.getConnection();
	// String query =
	// "SELECT DISTINCT(ap.appliance_name), sld.status ,
	// c.city_name,cs.customer_name ,
	// cs.customer_phone,(ap.appliance_price-sld.down_payment),pl.total_installments,pl.payed_installments_amount
	// ,(pl.installment_amount_month*pl.total_installment_remaining)AS
	// monthly_amount , ap.appliance_id,pl.loan_id ,cs.customer_id ,"
	// +
	// "sl.salesman_name, pl.terminated_on, pl.grace_period FROM appliance ap
	// \n"
	// + "INNER JOIN sold_to sld ON ap.appliance_id=sld.Appliance_Id \n"
	// + "INNER JOIN customer cs ON sld.customer_id=cs.customer_id \n"
	// + "INNER JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
	// + "INNER JOIN salesman sl ON sld.salesman_id=sl.salesman_id\n "
	// +
	// "INNER JOIN do_salesman ds ON sl.salesman_id=ds.salesman_id join city c
	// on cs.customer_city=c.city_id WHERE ds.do_id="
	// + id + " ORDER BY loan_id DESC;";
	// pstmt = con.prepareStatement(query);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// appliance_name = rs.getString(1);
	// status = rs.getInt(2);
	// district = rs.getString(3);
	// customer_name = rs.getString(4);
	// customer_phone = rs.getString(5);
	// total_amount = rs.getDouble(6);
	// scheme = rs.getInt(7);
	// paid_amount = rs.getDouble(8);
	// remaining_balance = rs.getDouble(9);
	// appliance_id = rs.getInt(10);
	// loan_id = rs.getInt(11);
	// customer_id = rs.getInt(12);
	// salesman_name = rs.getString(13);
	// terminate = rs.getDate(14);
	// grace_period = rs.getInt(15);
	// bean = new Payment_LoanBean();
	// bean.setAppliances_Number(appliance_name);
	// bean.setStatus(status);
	// bean.setCustomer_name(customer_name);
	// bean.setCustomer_phone(customer_phone);
	// bean.setDistrict(district);
	// bean.setLoan_amount(total_amount);
	// bean.setRemaining_balance(remaining_balance);
	// bean.setGrace_period(grace_period);
	// bean.setScheme(scheme);
	// bean.setPaid_amount(paid_amount);
	// bean.setLoan_key(loan_id);
	// bean.setAppliance_key(appliance_id);
	// bean.setCustomer_id(customer_id);
	// bean.setSalesman(salesman_name);
	// bean.setTerminate(terminate);
	// paymentList.add(bean);
	// }
	// con.close();
	// } catch (SQLException e) {
	// logger.error("",e);
	// e.printStackTrace();
	// }
	//
	// return paymentList;
	// } // end of getting all customers form Db

	// TODO Remove unused code found by UCDetector
	// public static ArrayList<paymentnewBean> loan_history(int loan_id) {
	// paymentnewBean bean = null;
	// ArrayList<paymentnewBean> customerList = new ArrayList<paymentnewBean>();
	// Date due_date, paid_date;
	// boolean payment_method;
	// int grace, scheme;
	// double installment_amount, paid_amount, monthlyAmount;
	// try {
	// Connection con = connection.Connect.getConnection();
	// System.out.println("test");
	// String query =
	// "SELECT ci.due_date
	// ,ci.installments_amount,ci.paid_date,ci.recent_payed,pl.grace_period,ci.payment_type,
	// pl.total_installments FROM customer_installments ci JOIN payment_loan pl
	// ON ci.payment_loan_id=pl.loan_id WHERE pl.loan_id=?";
	// pstmt = con.prepareStatement(query);
	// pstmt.setInt(1, loan_id);
	// rs = pstmt.executeQuery();
	// System.out.println(query);
	// while (rs.next()) {
	// due_date = rs.getDate(1);
	// System.out.print(due_date);
	// installment_amount = rs.getDouble(2);
	// paid_date = rs.getDate(3);
	// paid_amount = rs.getDouble(4);
	// grace = rs.getInt(5);
	// payment_method = rs.getBoolean(6);
	// scheme = rs.getInt(7);
	// bean = new paymentnewBean();
	// bean.setDuedate(due_date);
	// bean.setPaiddate(paid_date);
	// bean.setInstallment(installment_amount);
	// bean.setGraceperiod(scheme);
	// bean.setPaid_amount(paid_amount);
	// boolean status = false;
	// if (paid_date == null) {
	// status = true;
	// } else {
	// status = false;
	// }
	// bean.setStatus(status);
	// bean.setScheme(scheme);
	// customerList.add(bean);
	// }
	// con.close();
	// } catch (SQLException e) {
	// logger.error("",e);
	// e.printStackTrace();
	// }
	// return customerList;
	// }

	// TODO Remove unused code found by UCDetector
	// public static Date getPayedDate(int loan_id) {
	// Date paid_date = null;
	//
	// try {
	// Connection con = connection.Connect.getConnection();
	// System.out.println("test");
	// String query =
	// "SELECT paid_date FROM customer_installments WHERE payment_loan_id=?";
	// pstmt = con.prepareStatement(query);
	// pstmt.setInt(1, loan_id);
	// rs = pstmt.executeQuery();
	// System.out.println(query);
	// while (rs.next()) {
	// paid_date = rs.getDate(1);
	// }
	// con.close();
	// } catch (SQLException e) {
	// logger.error("",e);
	// e.printStackTrace();
	// }
	// return paid_date;
	// }

	// TODO Remove unused code found by UCDetector
	// public static paymentnewBean loanHistory(int loan_id) {
	// paymentnewBean bean = null;
	// // ArrayList<LoanScheduleBean> customerList = new
	// // ArrayList<LoanScheduleBean>();
	// Date due_date, paid_date;
	//
	// // String paid_date1;
	// boolean payment_method;
	// int grace;
	// double installment_amount, paid_amount;
	// try {
	// Connection con = connection.Connect.getConnection();
	// System.out.println("test");
	// String query =
	// "SELECT ci.due_date
	// ,ci.installments_amount,ci.paid_date,pl.recent_payed_amount,pl.grace_period,ci.payment_type
	// FROM customer_installments ci JOIN payment_loan pl ON
	// ci.payment_loan_id=pl.loan_id WHERE pl.loan_id=?";
	// pstmt = con.prepareStatement(query);
	// pstmt.setInt(1, loan_id);
	// rs = pstmt.executeQuery();
	// System.out.println(query);
	// while (rs.next()) {
	// due_date = rs.getDate(1);
	// installment_amount = rs.getDouble(2);
	// paid_date = rs.getDate(3);
	// // System.out.print("casting");
	// // System.out.print(paid_date);
	// paid_amount = rs.getDouble(4);
	// grace = rs.getInt(5);
	// payment_method = rs.getBoolean(6);
	// bean = new paymentnewBean();
	// bean.setDuedate(due_date);
	// bean.setPaiddate(paid_date);
	// bean.setInstallment(installment_amount);
	// bean.setGraceperiod(grace);
	// bean.setPaid_amount(paid_amount);
	// bean.setPayment_method(payment_method);
	// System.out.println("seting :" + bean.getPaiddate());
	//
	// }
	// con.close();
	// } catch (SQLException e) {
	// logger.error("",e);
	// e.printStackTrace();
	// }
	// return bean;
	// }

	// TODO Remove unused code found by UCDetector
	// public static double getMonthlyInstallment(int loanId) {
	//
	// double installment_amount = 0;
	// try {
	// Connection con = connection.Connect.getConnection();
	// System.out.println("test");
	// String query =
	// "SELECT installment_amount_month from payment_loan WHERE loan_id=?";
	// pstmt = con.prepareStatement(query);
	// pstmt.setInt(1, loanId);
	// rs = pstmt.executeQuery();
	// System.out.println(query);
	// while (rs.next()) {
	// installment_amount = rs.getDouble(1);
	// }
	// con.close();
	// } catch (SQLException e) {
	// logger.error("",e);
	// e.printStackTrace();
	// }
	// return installment_amount;
	// }

	public static ArrayList<MonthlyWisePayment> payment_loan_history(int loan_id) {

		// timeforupdate.CheckupatePayment(loan_id);

		MonthlyWisePayment bean = null;
		ArrayList<MonthlyWisePayment> customerList = new ArrayList<MonthlyWisePayment>();
		Date due_date, paid_date;
		// boolean payment_method;
		int grace;
		// int days;
		String trasnction_id, paid_amount;
		// int outstading, remaining_balance, outstanding,
		int payment_method;
		// SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		// String inputString1;
		// String inputString2;
		// int installment_amount, monthlyAmount,
		int activated_date;
		// Date ;
		// Date current_date = new Date();
		try (Connection con = Connect.getConnection()) {

			System.out.println("test");
			String query = "SELECT due_date ,amount,paid_date,activated_Date,grace_period ,payment_method,trasnction_id FROM payments_details\n"
					+ "                WHERE loan_id=" + loan_id;
			pstmt = con.prepareStatement(query);

			System.out.print(query);
			rs = pstmt.executeQuery(query);

			while (rs.next()) {
				due_date = rs.getDate(1);
				paid_amount = "" + rs.getInt(2);

				paid_date = rs.getDate(3);

				activated_date = rs.getInt(4);

				grace = rs.getInt(5);
				payment_method = rs.getInt(6);
				trasnction_id = rs.getString(7);

				bean = new MonthlyWisePayment();
				// bean.setDays(days);
				bean.setDue_date(due_date);
				bean.setPaid_amount(paid_amount);
				bean.setPaid_date(paid_date);
				bean.setDays_activated(activated_date);

				bean.setGrace_consumed(grace);
				bean.setPayment_method(payment_method);
				bean.setTrasnction_id(trasnction_id);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	}

	// dAnish, yAseen edited
	public static ArrayList<MonthlyWisePayment> loanBookByApplianceId(
			int applianceId, String applianceName) {
		ResultSet rs = null;
		CallableStatement cstmt;
		ArrayList<MonthlyWisePayment> customerList = new ArrayList<MonthlyWisePayment>();
		Date due_date, paid_date;
		// boolean payment_method;
		String trasnction_id;
		String paid_amount, appliance_imei;
		int activated_days, remaining_days;
		try (Connection con = Connect.getConnection()) {
			String query = "{CALL get_monthly_wise_payment(?)}";
			cstmt = con.prepareCall(query);
			cstmt.setInt(1, applianceId);
			System.out.println(query);
			rs = cstmt.executeQuery();
			int k =0;
			while (rs.next()) {
				due_date = rs.getDate(1);
				paid_amount = "" + rs.getInt(2);
				paid_date = rs.getDate(3);
				activated_days = rs.getInt(5);
				trasnction_id = "" + rs.getInt(6);
				remaining_days = rs.getInt(7);
				appliance_imei = rs.getString(8);
				MonthlyWisePayment bean = new MonthlyWisePayment();
				bean.setDue_date(due_date);
				bean.setPaid_amount(paid_amount);
				bean.setPaid_date(paid_date);
				bean.setDays_activated(activated_days);
				bean.setTrasnction_id(trasnction_id);
				bean.setRemaining_days(remaining_days);
				bean.setAppliance_imei(rs.getString("consumer_number"));
				bean.setApplianceDetails(rs.getString("appliance_detail"));
				bean.setDue_amount(rs.getInt("due_amount"));
				bean.setApplianceDue_detail(rs.getString("applianceDue_detail"));
				bean.setInstallmentCount(rs.getInt("installmentCount"));
				customerList.add(bean);
				k++;
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	}

	public static String[] getLoanOutStandingAndEndDateByApplianceId(
			int appliance) {
		String arr[] = new String[2];
		try (Connection con = Connect.getConnection()) {
			String query = "SELECT pl.remaining_balance ,  ADDDATE(created_on, INTERVAL pl.total_installments MONTH) AS endDate FROM "
					+ " payment_loan pl INNER JOIN sold_to st ON pl.soldto_id = st.sold_to_id "
					+ " WHERE st.appliance_id =  ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, appliance);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr[0] = "" + rs.getInt("pl.remaining_balance");
				arr[1] = "" + rs.getDate("endDate");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return arr;
	}

	public static ArrayList<HashMap<String, String>> getSuperAdminLoanBook(
			int start, int range, String order, int column, String search)
			throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getCustomersList(
			String from, String to) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_weekly_customer_list(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerContact",
						resultSet.getString("customer_phone"));
				map.put("foContact", resultSet.getString("fo_priamary_phone"));
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("NdContact", resultSet.getString("salesman_phone_no")
						+ "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("insalled_date", resultSet.getString("insalled_date")
						+ "");
				map.put("consumerNumber", resultSet.getString("imei_number")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getCustomerRatingList() {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_customer_rating()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("rating", resultSet.getString("rating") + "");
				maps.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getDoSales(String from,
			String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_sales_report_by_perf_from_to(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("fos", resultSet.getInt("fos") + "");
				map.put("active_nds", resultSet.getInt("active_nds") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				map.put("average", resultSet.getInt("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoSalesAndDefaulter(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_sales_report_and_defaulter(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesAndDefaulter(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_and_defaulter(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoRecoveryRateReport(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_recovery_rate_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				map.put("average_rating", resultSet.getInt("average_rating")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoRecoveryRateReportByLimit(
			String from, String to, String limit) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			if (to.isEmpty() && from.isEmpty()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_do_recovery_rate_report_by_limit(?)}");
				prepareCall.setInt(1, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_do_recovery_rate_report_from_to_by_limit(?,?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
				prepareCall.setInt(3, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSales(String from,
			String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_by_perf_from_to(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("total_nds", resultSet.getInt("total_nds") + "");
				map.put("active_nds", resultSet.getInt("active_nds") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesfoDOs(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_for_dos(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSales(String from,
			String to, String limit) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			if (to.isEmpty() && from.isEmpty()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_fo_sales_report_by_limit(?)}");
				prepareCall.setInt(1, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_fo_sales_report_from_to_by_limit(?,?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
				prepareCall.setInt(3, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoRecoveryRateReport(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_recovery_rate_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				map.put("average_rating", resultSet.getInt("average_rating")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoRecoveryRateReportByLimit(
			String from, String to, String limit) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			if (to.isEmpty() && from.isEmpty()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_fo_recovery_rate_report_by_limit(?)}");
				prepareCall.setInt(1, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_fo_recovery_rate_report_from_to_by_limit(?,?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
				prepareCall.setInt(3, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNDSales(String from,
			String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nd_sales_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNDSales(String from,
			String to, String limit) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			if (to.isEmpty() && from.isEmpty()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_nd_sales_report_by_limit(?)}");
				prepareCall.setInt(1, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_nd_sales_report_from_to_by_limit(?,?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
				prepareCall.setInt(3, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNDRecoveryRateReport(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nd_recovery_rate_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesman_name", resultSet.getString("salesman_name")
						+ "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				map.put("average_rating", resultSet.getInt("average_rating")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNDRecoveryRateReportByLimit(
			String from, String to, String limit) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			if (to.isEmpty() && from.isEmpty()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_nd_recovery_rate_report_by_limit(?)}");
				prepareCall.setInt(1, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_nd_recovery_rate_report_from_to_by_limit(?,?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
				prepareCall.setInt(3, Integer.valueOf(limit));
				resultSet = prepareCall.executeQuery();
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesman_name", resultSet.getString("salesman_name")
						+ "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanBook(int start,
			int range, String order, int column, int doId, String search)
			throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");

				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int getDoLoanBookSearchCount(int doId, String search) {
		int count = 0;

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_do_loan_books_count(?,?)}");
			callst.setInt(1, doId);
			callst.setString(2, search);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return count;
	}

	// created by Junaid Ali

	public static int getCountLoanBook() {

		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_count()}");
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

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getSuperAdminLoanBookWithSearch(
			int start, int length, String search, String orde, int col) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {

			call = con
					.prepareCall("{CALL get_loan_book_data_by_search_asc_desc(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, search);
			call.setString(4, orde);
			call.setInt(5, col);
			rs = call.executeQuery();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("customerName", rs.getString("customer_name"));
				// map.put("loanId", rs.getInt("loan_id")+"");
				map.put("applianceName", rs.getString("appliance_name"));

				map.put("applianceNumber", rs.getString("imei_number"));

				map.put("cityName", rs.getString("city_name"));
				map.put("monthlyPay", Math.round(rs.getDouble("monthly_pay"))
						+ "");
				map.put("installmentScheme", rs.getInt("installment_scheme")
						+ "");
				map.put("downPayment", Math.round(rs.getDouble("down_payment"))
						+ "");
				map.put("cummalativePaid", rs.getInt("cummalative_paid") + "");
				map.put("statusGet", rs.getString("status_get"));
				map.put("cummalativeDue", rs.getInt("cummalative_due") + "");
				// map.put("appliancePrice",
				// rs.getDouble("appliance_price")+"");
				map.put("totalPaid", rs.getInt("total_paid") + "");
				map.put("remainingBalance",
						Math.round(rs.getDouble("remaining_balance")) + "");
				map.put("remaining_days", rs.getInt("remaining_days") + "");
				if (rs.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", rs.getInt("rating") + "  %");
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali

	public static int getCountLoanBookSearchCount(String search) {

		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_by_search_count(?)}");
			prepareCall.setString(1, search);
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

	// public static ArrayList<HashMap<String, String>>
	// getLoanBookMaintained(int start, int range)
	// throws ServiceException, IOException, JSONException {
	// System.out.println("Payment_loanNewBAL.getLoanBookMaintained()");
	// ArrayList<HashMap<String, String>> maps = new ArrayList<>();
	//
	// try (Connection connection = Connect.getConnection()) {
	// CallableStatement prepareCall = connection.prepareCall("{CALL
	// get_super_admin_loan_book_maintained(?, ?)}");
	// prepareCall.setInt(1, start);
	// prepareCall.setInt(2, range);
	// ResultSet resultSet = prepareCall.executeQuery();
	//
	// while (resultSet.next()) {
	// HashMap<String, String> map = new HashMap<>();
	// map.put("applianceId", resultSet.getInt("appliance_id") + "");
	// map.put("customerName", resultSet.getString("customer_name"));
	// map.put("loanId", resultSet.getInt("loan_id") + "");
	// map.put("applianceName", resultSet.getString("appliance_name"));
	// map.put("applianceNumber", resultSet.getString("appliance_GSMno"));
	// map.put("cityName", resultSet.getString("city_name"));
	// map.put("monthlyPay", Math.round(resultSet.getDouble("monthly_pay")) +
	// "");
	// map.put("installmentScheme", resultSet.getInt("installment_scheme") +
	// "");
	// map.put("downPayment", Math.round(resultSet.getDouble("down_payment")) +
	// "");
	// map.put("cummalativePaid", resultSet.getInt("cummalative_paid") + "");
	// map.put("statusGet", resultSet.getString("status_get"));
	// map.put("cummalativeDue", resultSet.getInt("cummalative_due") + "");
	// map.put("appliancePrice",
	// Math.round(resultSet.getDouble("appliance_price")) + "");
	// map.put("totalPaid", resultSet.getInt("total_paid") + "");
	// map.put("remainingBalance",
	// Math.round(resultSet.getDouble("remaining_balance")) + "");
	// map.put("remaining_days", resultSet.getInt("remaining_days") + "");
	// if(resultSet.getInt("rating")==0){
	// map.put("customer_rating", "N/A" );
	// } else {
	// map.put("customer_rating",resultSet.getInt("rating") + " %" );}
	// maps.add(map);
	//
	// telenor.SendSms(resultSet.getString("appliance_name"), "$4$");
	//
	// }
	//
	// } catch (SQLException e) {
	// logger.error("", e);
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return maps;
	// }
	//

	public static ArrayList<HashMap<String, String>> getLoanBookMaintained(
			int start, int range, String order, int column, String search)
			throws ServiceException, IOException, JSONException {
		System.out.println("Payment_loanNewBAL.getLoanBookMaintained()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_maintained(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanBookMaintained(
			int start, int range, int doId) throws ServiceException,
			IOException, JSONException {
		System.out.println("Payment_loanNewBAL.getLoanBookMaintained()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_maintained(?,?, ?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setInt(3, doId);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber",
						resultSet.getString("appliance_GSMno"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay",
						Math.round(resultSet.getDouble("monthly_pay")) + "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						Math.round(resultSet.getDouble("down_payment")) + "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", resultSet.getInt("total_paid") + "");
				map.put("remainingBalance",
						Math.round(resultSet.getDouble("remaining_balance"))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getLoanBookDefaulter(
			int start, int range) {
		System.out.println("Payment_loanNewBAL.getLoanBookDefaulter()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_defaulter(?, ?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber",
						resultSet.getString("appliance_GSMno"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay",
						Math.round(resultSet.getDouble("monthly_pay")) + "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						Math.round(resultSet.getDouble("down_payment")) + "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", resultSet.getInt("total_paid") + "");
				map.put("remainingBalance",
						Math.round(resultSet.getDouble("remaining_balance"))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getLoanBookLate(int start,
			int range, String order, int column, String search) {
		System.out
				.println("Payment_loanNewBAL.get_super_admin_loan_book_late()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_late(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public ArrayList<HashMap<String, String>> getDoLoanBookDefaulter(int start,
			int range, int doId) {
		System.out.println("Payment_loanNewBAL.getLoanBookDefaulter()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_defaulter(?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setInt(3, doId);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber",
						resultSet.getString("appliance_GSMno"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay",
						Math.round(resultSet.getDouble("monthly_pay")) + "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						Math.round(resultSet.getDouble("down_payment")) + "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", resultSet.getInt("total_paid") + "");
				map.put("remainingBalance",
						Math.round(resultSet.getDouble("remaining_balance"))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public ArrayList<HashMap<String, String>> getLoanBookOwned(int start,
			int range) {
		System.out.println("Payment_loanNewBAL.getLoanBookOwned()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_owned(?, ?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber",
						resultSet.getString("appliance_GSMno"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay",
						Math.round(resultSet.getDouble("monthly_pay")) + "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						Math.round(resultSet.getDouble("down_payment")) + "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", resultSet.getInt("total_paid") + "");
				map.put("remainingBalance",
						Math.round(resultSet.getDouble("remaining_balance"))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public ArrayList<HashMap<String, String>> getDoLoanBookOwned(int start,
			int range, int doId) {
		System.out.println("Payment_loanNewBAL.getLoanBookOwned()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_owned(?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setInt(3, doId);

			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber",
						resultSet.getString("appliance_GSMno"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay",
						Math.round(resultSet.getDouble("monthly_pay")) + "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						Math.round(resultSet.getDouble("down_payment")) + "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", resultSet.getInt("total_paid") + "");
				map.put("remainingBalance",
						Math.round(resultSet.getDouble("remaining_balance"))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public int countLoanBookMaintained(String search) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookMaintained()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_maintained(?)}");
			prepareCall.setString(1, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int countDoLoanBookMaintained(int doId) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookMaintained()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {

			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_maintained(?)}");
			prepareCall.setInt(1, doId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int countLoanBookLate(String search) {
		System.out.println("Payment_loanNewBAL.countSuperAdminLoanBookOwned()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_late(?)}");
			prepareCall.setString(1, search);
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

	public int countLoanBookDefaulter() {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookDefaulter()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = connection.prepareCall(
					"{CALL count_super_admin_loan_book_defaulter()}")
					.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int countDoLoanBookDefaulter(int doId) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookDefaulter()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_defaulter(?)}");
			prepareCall.setInt(1, doId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int countLoanBookOwned() {
		System.out.println("Payment_loanNewBAL.countSuperAdminLoanBookOwned()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = connection.prepareCall(
					"{CALL count_super_admin_loan_book_owned()}")
					.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public int countDoLoanBookOwned(int doId) {
		System.out.println("Payment_loanNewBAL.countSuperAdminLoanBookOwned()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_owned(?)}");
			prepareCall.setInt(1, doId);
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

	public HashMap<String, String> countLoanBookFilters() {
		System.out.println("Payment_loanNewBAL.countLoanBookFilters()");
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_filters()}");

			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map.put("Maintained", resultSet.getInt(1) + "");
				map.put("Late", resultSet.getInt(3) + "");
				map.put("Defaulter", resultSet.getInt(2) + "");
				map.put("Owned", resultSet.getInt(4) + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getLoanBookList()
			throws ServiceException, IOException, JSONException {
		System.out.println("Payment_loanNewBAL.getLoanBookMaintained()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_books_pdf()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("doName", resultSet.getString("user_name"));
				map.put("doNumber", resultSet.getString("user_name"));
				map.put("foName", resultSet.getString("fo_name"));
				map.put("ndName", resultSet.getString("salesman_name"));
				map.put("foNumber", resultSet.getString("fo_priamary_phone"));
				map.put("ndNumber", resultSet.getString("salesman_phone_no"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("imeiNumber", resultSet.getString("imei_number"));
				map.put("monthlyPay",
						Math.round(resultSet.getDouble("monthly_pay")) + "");
				map.put("dueDate", resultSet.getString("due_date"));
				map.put("customerNumber", resultSet.getString("customer_phone"));
				map.put("customerNumber", resultSet.getString("customer_phone"));
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("remainingBalance",
						Math.round(resultSet.getDouble("remaining_balance"))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public int countLoanBookDefaulter(String search) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookDefaulter()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_defaulter(?)}");
			prepareCall.setString(1, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// public HashMap<String, String> countDoLoanBookFilters(int userId) {
	// System.out.println("Payment_loanNewBAL.countLoanBookFilters()");
	// HashMap<String, String> map = new HashMap<>();
	// try (Connection connection = Connect.getConnection()) {
	// CallableStatement prepareCall = connection.prepareCall("{CALL
	// count_do_loan_book_filters(?)}");
	// prepareCall.setInt(1, userId);
	// ResultSet resultSet = prepareCall.executeQuery();
	// while (resultSet.next()) {
	// if (resultSet.getString("status_get").equals("maintend")) {
	// map.put("Maintained", resultSet.getInt("_count") + "");
	// } else if (resultSet.getString("status_get").equals("defaulter")) {
	// map.put("Defaulter", resultSet.getInt("_count") + "");
	// } else if (resultSet.getString("status_get").equals("owned")) {
	// map.put("Owned", resultSet.getInt("_count") + "");
	// }
	// }
	//
	// } catch (SQLException e) {
	// logger.error("", e);
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return map;
	// }

	public static int updateLoan(int loan_id, int amount) throws SQLException {

		int rows = 0;

		try (Connection connection = Connect.getConnection()) {

			try (Statement statement = (Statement) con.createStatement()) {
				rows = statement
						.executeUpdate("UPDATE payment_loan  SET `installment_amount_month` ="
								+ amount + " WHERE `loan_id` = " + loan_id);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return rows;

	}

	public static int insert_other_loan(int loan_id, int amount, String reason)
			throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		int a = 0;
		String query3 = "INSERT INTO packeg_installments(package_amount,package_details,loan_id) values ("
				+ amount + ",'" + reason + "'," + loan_id + ") ";
		System.out.println(query3);
		try (Connection connection = Connect.getConnection()) {
			st = (Statement) con.createStatement();
			a = st.executeUpdate(query3);

			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return a;
	}

	public static boolean isFirstDayofMonth(Calendar calender) {
		if (calender == null)
			return false;

		int dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
		return (dayOfMonth == 1);
	}

	public HashMap<String, Integer> countDoLoanBookFilters(int userId) {
		System.out.println("Payment_loanNewBAL.countLoanBookFilters()");
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_filters(?)}");
			prepareCall.setInt(1, userId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map.put("Maintained", resultSet.getInt(1));
				map.put("Late", resultSet.getInt(3));
				map.put("Defaulter", resultSet.getInt(2));
				map.put("Owned", resultSet.getInt(4));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public int countDoLoanBookMaintained(int doId, String search) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookMaintained()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {

			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_maintained(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanBookMaintained(
			int start, int range, String order, int column, int doId,
			String search) throws ServiceException, IOException, JSONException {
		System.out.println("Payment_loanNewBAL.getLoanBookMaintained()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_maintained(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public int countDoLoanBookLate(int doId, String search) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookDefaulter()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_late(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanBookLate(
			int start, int range, String order, int column, int doId,
			String search) throws ServiceException, IOException, JSONException {
		System.out.println("Payment_loanNewBAL.get_do_loan_book_late()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_late(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public int countDoLoanBookDefaulter(int doId, String search) {
		System.out
				.println("Payment_loanNewBAL.countSuperAdminLoanBookDefaulter()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_defaulter(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<HashMap<String, String>> getDoLoanBookDefaulter(int start,
			int range, String order, int column, int doId, String search) {
		System.out.println("Payment_loanNewBAL.getLoanBookDefaulter()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_defaulter(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public int countDoLoanBookOwned(int doId, String search) {
		System.out.println("Payment_loanNewBAL.countSuperAdminLoanBookOwned()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_owned(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
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

	public ArrayList<HashMap<String, String>> getDoLoanBookOwned(int start,
			int range, String order, int column, int doId, String search) {
		System.out.println("Payment_loanNewBAL.getLoanBookOwned()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_owned(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getLoanBookDefaulter(
			int start, int range, String order, int column, String search) {
		System.out.println("Payment_loanNewBAL.getLoanBookDefaulter()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_defaulter(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public ArrayList<HashMap<String, String>> getLoanBookOwned(int start,
			int range, String order, int column, String search) {
		System.out.println("Payment_loanNewBAL.getLoanBookOwned()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_owned(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public int countLoanBookOwned(String search) {
		System.out.println("Payment_loanNewBAL.countSuperAdminLoanBookOwned()");
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_owned(?)}");
			prepareCall.setString(1, search);
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

	public static ArrayList<HashMap<String, String>> getDosLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_wise_late_defaulter()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("late", resultSet.getInt("late") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFosLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_wise_late_defaulter()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("late", resultSet.getInt("late") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomersLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_late_and_defaulters()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("duedate", resultSet.getString("due_date") + "");
				map.put("customerName", resultSet.getString("customer_name")
						+ "");
				map.put("customerNumber", resultSet.getString("customer_phone")
						+ "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("NdNumber", resultSet.getString("salesman_phone_no")
						+ "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("doNumber", resultSet.getString("user_phone") + "");
				map.put("foNumber", resultSet.getString("fo_priamary_phone")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("remaining_days", resultSet.getString("remaining_days")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoSalesAverage(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_last_sales_average_do(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("handover", resultSet.getString("handover") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesAverage(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_last_sales_average_fo(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("handover", resultSet.getString("handover") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNdSalesAverage(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_last_sales_average_nd(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("handover", resultSet.getString("handover") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDosPerformance() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_perfomance()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("fos", resultSet.getString("fos") + "");
				map.put("sales", resultSet.getString("sales") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesByTop(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_from_to_by_limit(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getString("sales") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNdSalesByTop(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nd_sales_report_from_to_by_limit(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getString("sales") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoWiseLoanApps(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = null;
			if (from.isEmpty() && to.isEmpty()) {
				prepareCall = connection
						.prepareCall("{CALL district_wise_laon_apps()}");
			} else {
				prepareCall = connection
						.prepareCall("{CALL district_wise_laon_apps_day_wise(?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
			}
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("pending", resultSet.getString("pending") + "");
				map.put("accepted", resultSet.getString("accepted") + "");
				map.put("varified", resultSet.getString("varified") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoWiseLoanApps(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = null;
			if (from.isEmpty() && to.isEmpty()) {
				prepareCall = connection
						.prepareCall("{CALL fo_wise_laon_apps()}");
			} else {
				prepareCall = connection
						.prepareCall("{CALL fo_wise_laon_apps_day_wise(?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
			}
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("pending", resultSet.getString("pending") + "");
				map.put("accepted", resultSet.getString("accepted") + "");
				map.put("varified", resultSet.getString("varified") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomersRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_customer_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name")
						+ "");
				map.put("rating", resultSet.getInt("rating") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNizamDostRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL nizamdost_wise_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("district", resultSet.getString("district_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("rating", resultSet.getInt("rating") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL fo_wise_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("district", resultSet.getString("district_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("rating", resultSet.getInt("rating") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL do_wise_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("rating", resultSet.getInt("rating") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> futureLoanBooks() {
		System.out.println("Payment_loanNewBAL.countLoanBookFiltersToDate()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		Calendar calendar = Calendar.getInstance();
		int currentMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		Date today = new Date();

		DateFormat day = new SimpleDateFormat("dd");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat year = new SimpleDateFormat("YYYY");

		int currentday = Integer.parseInt(day.format(today));
		String months = month.format(today) + "";
		String years = year.format(today) + "";

		for (int i = 1; currentday <= currentMonthDays; i++) {
			String passDate = years + "-" + months + "-" + currentday;

			System.err.println("today's date == " + passDate);

			try (Connection connection = Connect.getConnection()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_super_admin_loan_book_filters_toDate(?)}");
				prepareCall.setString(1, passDate);

				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					map = new HashMap<>();
					map.put("Date", passDate + "");
					map.put("Maintained", resultSet.getInt(1) + "");
					map.put("Defaulter", resultSet.getInt(2) + "");
					map.put("Late", resultSet.getInt(3) + "");
					list.add(map);
				}

			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			currentday++;
		}
		return list;
	}

		public static void setOwnDeviceStatus(int appId, int deviceStatus) {
			int row = 0; 
			try(Connection con = Connect.getConnection()){
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) con
						.prepareStatement("UPDATE `custom_payment_loan` SET `return_status` = ?, tracking_status = ?, returned_date = NOW() WHERE `custom_pl_id` = ? ");
				prepareStatement.setInt(1, deviceStatus);
				prepareStatement.setInt(2, deviceStatus);
				prepareStatement.setInt(3, appId);
				
				System.err.println("deviceStatus"+deviceStatus+" // appId"+appId);
				row = prepareStatement.executeUpdate();
				if(row > 0){
					System.err.println("data updated");
				}else{
					System.err.println("data not updated");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
	public static void main(String[] args) {
		getOwnApplianceList(694);
	}





}
