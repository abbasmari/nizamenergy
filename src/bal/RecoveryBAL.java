/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.RecoveryBean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import connection.Connect;

public class RecoveryBAL {

	static ResultSet rs = null;
	static Statement s = null;
	static Connection con = null;
	final static Logger logger = Logger.getLogger(RecoveryBAL.class);

	public static ArrayList<RecoveryBean> getRecoverylist() {
		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		String query = "SELECT \n"
				+ "(SELECT COUNT(*) FROM sold_to WHERE salesman_id=1) AS Total_Customers, COUNT(sld.payement_option),SUM(ci.installments_amount) \n"
				+ "FROM sold_to sld \n" + "JOIN customer_installments ci ON ci.customer_id=sld.customer_id\n"
				+ "WHERE ci.paid_date>ci.due_date AND sld.payement_option=1 AND sld.salesman_id=1;";

		int total_customers, customer_on_installment;
		int installment_amount;

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement s = null;
			RecoveryBean bean = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				total_customers = rs.getInt(1);
				customer_on_installment = rs.getInt(2);
				installment_amount = rs.getInt(3);
				bean = new RecoveryBean();
				bean.setCustomer_on_installments(customer_on_installment);
				bean.setTotal_customers(total_customers);
				bean.setInstallment_amount(installment_amount);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static RecoveryBean getRecovery(int salesman_id, String datefrom, String dateto) {

		RecoveryBean list = null;
		String query = "SELECT DISTINCT(appliance.appliance_name) AS apname ,(SELECT CountNumberDate(apname,"
				+ salesman_id + ",'" + datefrom + "','" + dateto + "')) \n" + "FROM appliance \n"
				+ "JOIN sold_to ON appliance.appliance_id=sold_to.appliance_id";

		String app_name;
		int count;
		try (Connection con = Connect.getConnection()) {
			RecoveryBean bean = null;
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				app_name = rs.getString(1);
				count = rs.getInt(2);
				bean = new RecoveryBean();
				bean.setApp_name(app_name);
				bean.setTotal_customers(count);
				System.out.print(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<RecoveryBean> getRecoveryList(int salesman_id) {

		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		Date newDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -month);
		newDate = c.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 12; i++) {
			c.add(Calendar.MONTH, i);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(newDate);
			c1.add(Calendar.MONTH, i - 1);
			String formatted1 = format1.format(c1.getTime());
			String formatted = format1.format(c.getTime());

			String query = "SELECT DISTINCT(appliance.appliance_name) AS apname ,(SELECT CountNumberDate(apname,"
					+ salesman_id + ",'" + formatted + "','" + formatted1 + "')) \n" + "FROM appliance \n"
					+ "JOIN sold_to ON appliance.appliance_id=sold_to.appliance_id";

			int total_amount = 0;

			String app_name;
			int rates = 0;
			int count;
			try (Connection con = Connect.getConnection()) {
				ResultSet rs = null;
				Statement s = null;
				s = con.createStatement();
				rs = s.executeQuery(query);
				System.err.println(query);
				while (rs.next()) {
					app_name = rs.getString(1);
					if (app_name.equalsIgnoreCase("6 W")) {
						count = rs.getInt(2);

						if ((count >= 10) && (count <= 20)) {
							total_amount = count * 5;
						} else if ((count >= 21) && (count <= 35)) {
							total_amount = count * 10;
							rates = 10;
						} else if ((count >= 36) && (count <= 50)) {
							total_amount = count * 20;
							rates = 20;
						} else if ((count >= 51)) {
							total_amount = count * 30;
							rates = 30;
						}

					} else if (app_name.equalsIgnoreCase("30 W")) {
						count = rs.getInt(2);

						if ((count >= 10) && (count <= 20)) {
							total_amount = count * 15;
							rates = 15;
						} else if ((count >= 21) && (count <= 35)) {
							total_amount = count * 10;
							rates = 15;
						} else if ((count >= 36) && (count <= 50)) {
							total_amount = count * 20;
							rates = 20;
						} else if ((count >= 51)) {
							total_amount = count * 30;
							rates = 30;
						}

					} else if (app_name.equalsIgnoreCase("60 W")) {
						count = rs.getInt(2);

						if ((count >= 10) && (count <= 20)) {
							total_amount = count * 35;
							rates = 35;
						} else if ((count >= 21) && (count <= 35)) {
							total_amount = count * 45;
							rates = 45;
						} else if ((count <= 36) && (count <= 50)) {
							total_amount = count * 55;
							rates = 55;
						} else if ((count <= 51)) {
							total_amount = count * 65;
							rates = 65;
						}

					} else if (app_name.equalsIgnoreCase("120 W")) {
						count = rs.getInt(2);

						if ((count >= 10) && (count <= 20)) {
							total_amount = count * 45;
							rates = 45;
						} else if ((count >= 21) && (count <= 35)) {
							total_amount = count * 55;
							rates = 55;
						} else if ((count >= 36) && (count <= 50)) {
							total_amount = count * 65;
							rates = 65;
						} else if ((count >= 51)) {
							total_amount = count * 75;
							rates = 75;
						}

					}
					count = rs.getInt(2);
					bean = new RecoveryBean();
					bean.setApp_name(app_name);
					bean.setInstallment_amount(count);
					bean.setTotal_customers(total_amount);
					bean.setRate(rates);
					list.add(bean);
					System.out.print(bean);
				}

			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}
		return list;
	}

	public static ArrayList<RecoveryBean> getRecoveryBean(int salesmanId, String formatted1, String formatted) {
		System.out.println("RecoveryBAL.get_appliance_sales_details()");
		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;

		// String query = "SELECT DISTINCT(appliance.appliance_name) AS apname
		// ,(SELECT CountNumberDate(apname," + salesman_id + ",'" + formatted +
		// "','" + formatted1 + "')) \n"
		// + "FROM appliance \n"
		// + "JOIN sold_to ON appliance.appliance_id=sold_to.appliance_id";

		int total_amount = 0;

		String app_name;
		int rates = 0;
		int count;
		// connection.Connect.init();
		// try {
		// s = connection.Connect.con.createStatement();
		// rs = s.executeQuery(query);
		// System.err.println(query);
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con.prepareCall("{call get_appliance_sales_details(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, formatted);
			prepareCall.setString(3, formatted1);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				app_name = rs.getString(1);
				count = rs.getInt(2);
				if (app_name.equalsIgnoreCase("6 W")) {
					if ((count >= 1) && (count <= 10)) {
						total_amount = 0;
						rates = 0;
					} else if ((count >= 10) && (count <= 20)) {
						total_amount = count * 5;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 10;
						rates = 10;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 20;
						rates = 20;
					} else if ((count >= 51)) {
						total_amount = count * 30;
						rates = 30;
					}

				} else if (app_name.equalsIgnoreCase("30 W")) {
					count = rs.getInt(2);
					if ((count >= 1) && (count <= 10)) {
						total_amount = 0;
						rates = 0;
					} else if ((count >= 10) && (count <= 20)) {
						total_amount = count * 15;
						rates = 15;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 10;
						rates = 15;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 20;
						rates = 20;
					} else if ((count >= 51)) {
						total_amount = count * 30;
						rates = 30;
					}

				} else if (app_name.equalsIgnoreCase("60 W")) {
					count = rs.getInt(2);
					if ((count >= 1) && (count <= 10)) {
						total_amount = 0;
						rates = 0;
					} else if ((count >= 10) && (count <= 20)) {
						total_amount = count * 25;
						rates = 25;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 40;
						rates = 40;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 60;
						rates = 60;
					} else if ((count >= 51)) {
						total_amount = count * 65;
						rates = 65;
					}

				} else if (app_name.equalsIgnoreCase("120 W")) {
					count = rs.getInt(2);
					if ((count >= 1) && (count <= 10)) {
						total_amount = 0;
						rates = 0;
					} else if ((count >= 10) && (count <= 20)) {
						total_amount = count * 45;
						rates = 45;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 60;
						rates = 60;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 80;
						rates = 80;
					} else if ((count >= 51)) {
						total_amount = count * 75;
						rates = 75;
					}

				}

				bean = new RecoveryBean();
				bean.setApp_name(app_name);
				bean.setInstallment_amount(count);
				bean.setTotal_customers(total_amount);
				bean.setRate(rates);
				list.add(bean);
				System.out.print(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<RecoveryBean> getRecoveryCommissionBean(int salesmanId, String formatted1,
			String formatted) {
		System.out.println("RecoveryBAL.get_recovery_commission_bean()");
		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;
		// String query = "SELECT CustomerOnloans(" + salesman_id + ",'" +
		// formatted + "'),countlatepaidpeople(" + salesman_id + ",'" +
		// formatted + "','" + formatted1 + "'), commissionAmount(" +
		// salesman_id + ",'" + formatted + "','" + formatted1 +
		// "'),LateCustomer(" + salesman_id + ",'" + formatted + "','" +
		// formatted1 + "') ";
		// System.err.println(query);

		int total_customers, total_amount = 0;

		int total_paid_people;
		int late_customer;
		// System.out.print(query);
		// connection.Connect.init();
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con.prepareCall("{call get_recovery_commission_bean(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, formatted);
			prepareCall.setString(3, formatted1);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				total_customers = rs.getInt(1);
				total_paid_people = rs.getInt(2);
				total_amount = rs.getInt(3);
				late_customer = rs.getInt(4);
				System.out.print(
						total_customers + "   " + total_paid_people + "  " + total_amount + "  " + late_customer);
				bean = new RecoveryBean();
				bean.setTotal_customers(total_customers);
				bean.setPaid_late(total_paid_people);
				bean.setRecored_amount(total_amount);
				bean.setLate_customer(late_customer);
				list.add(bean);
				System.out.print(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<RecoveryBean> getLateRecovery() {

		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;

		String query = "SELECT s.salesman_id,s.salesman_name ,s.salesman_district ,SalesmanCustomer(s.salesman_id) AS totalcustomer ,SalesmanTotalLateCustomer(s.salesman_id) AS latecustomer FROM salesman s\n"
				+ "JOIN do_salesman ds ON s.salesman_id=ds.salesman_id\n;";

		String SalesmanName, SalesmanDistrict;
		int total_customers, salesman_id;

		int rates = 0;

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				salesman_id = rs.getInt(1);
				SalesmanName = rs.getString(2);
				SalesmanDistrict = rs.getString(3);
				total_customers = rs.getInt(4);
				// customer_late = rs.getInt(5);

				bean = new RecoveryBean();
				bean.setSalesman_id(salesman_id);
				bean.setSalesman_name(SalesmanName);
				bean.setDistrict_name(SalesmanDistrict);
				bean.setTotal_customers(total_customers);

				bean.setRate(rates);
				list.add(bean);
				System.out.print(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<RecoveryBean> getLateRecovery(int doId) {

		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;
		String query = "SELECT s.salesman_id,s.salesman_name ,s.salesman_district ,SalesmanCustomer(s.salesman_id) AS totalcustomer ,SalesmanTotalLateCustomer(s.salesman_id) AS latecustomer FROM salesman s\n"
				+ "JOIN do_salesman ds ON s.salesman_id=ds.salesman_id\n" + "WHERE ds.do_id=" + doId
				+ " ORDER BY salesman_id DESC;";

		String SalesmanName, SalesmanDistrict;
		int total_customers, customer_late, salesman_id;

		int rates = 0;

		try (Connection con = connection.Connect.getConnection()) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				salesman_id = rs.getInt(1);
				SalesmanName = rs.getString(2);
				SalesmanDistrict = rs.getString(3);
				total_customers = rs.getInt(4);
				customer_late = rs.getInt(5);

				bean = new RecoveryBean();
				bean.setSalesman_id(salesman_id);
				bean.setSalesman_name(SalesmanName);
				bean.setDistrict_name(SalesmanDistrict);
				bean.setTotal_customers(total_customers);
				bean.setLate_customer(customer_late);
				bean.setRate(rates);
				list.add(bean);
				System.out.print(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<RecoveryBean> getLateRecoveryRecords(int salesman) {

		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;

		String query = "SELECT cs.customer_name, cs.customer_phone , cs.customer_address,d.district_name, ap.appliance_GSMno ,ap.appliance_status ,pl.total_installments,ci.due_date,pl.loan_id \n"
				+ "                FROM appliance ap \n"
				+ "                JOIN sold_to sld ON ap.appliance_id=sld.Appliance_Id\n"
				+ "             JOIN customer cs ON sld.customer_id=cs.customer_id\n"
				+ "               JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
				+ "               JOIN salesman sl ON sld.salesman_id=sl.salesman_id\n"
				+ "               JOIN loan_payments ci ON pl.loan_id=ci.loan_id\n"
				+ "               JOIN city_district cd  ON cs.customer_city=cd.city_id \n"
				+ "              WHERE    ci.due_date<NOW() AND sl.salesman_id=" + salesman
				+ " GROUP BY cs.customer_name";

		String CustomermanName, customer_phone, customer_address, customer_district, applianceGSM;
		int total_installments;

		Date due_date;

		int grace;
		boolean status;
		try (Connection con = connection.Connect.getConnection()) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				CustomermanName = rs.getString(1);
				customer_phone = rs.getString(2);
				customer_address = rs.getString(3);
				customer_district = rs.getString(4);
				applianceGSM = rs.getString(5);
				status = rs.getBoolean(6);
				total_installments = rs.getInt(7);
				due_date = rs.getDate(8);
				grace = rs.getInt(9);
				bean = new RecoveryBean();
				bean.setCustomer_name(CustomermanName);
				bean.setCustomer_district(customer_district);
				bean.setDue_date(due_date);
				bean.setStatus(status);

				bean.setPhone_number(customer_phone);
				bean.setAddress(customer_address);
				bean.setTotal_installments(total_installments);
				bean.setAppliace_number(applianceGSM);
				bean.setGrace(grace);
				list.add(bean);
				System.out.print(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int defaulter(int salesman) {

		int count = 0;
		String query = "SELECT SQL_CALC_FOUND_ROWS lp.customer_id  FROM loan_payments lp\n"
				+ "JOIN payment_loan pl ON lp.loan_id=pl.loan_id \n"
				+ "JOIN sold_to sld ON pl.soldto_id=sld.sold_to_id\n"
				+ "WHERE (lp.due_date)<NOW() AND (pl.grace_period<0) AND sld.salesman_id=" + salesman + "\n"
				+ "GROUP BY   lp.loan_id  ORDER BY lp.Loan_payment_id DESC";

		try (Connection con = Connect.getConnection()) {
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				count = count + 1;
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getRecoveriesByFO(int foId) {
		System.out.println("RecoveryBAL.getRecoveriesByFO()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{CALL `get_recoveries_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
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

	public static ArrayList<HashMap<String, String>> getAllCustomerBySaler(int salerId) {
		System.err.println("CustomerAndBAL.getAllCustomerBySaler()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement cs = con.prepareCall("{CALL get_recoveries_by_vle(?)}");
			cs.setInt(1, salerId);
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

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}// end of getting all customers by SalerID

	public static void main(String[] args) {
		System.out.println(RecoveryBAL.getRecoveriesByFO(4));
	}

}
