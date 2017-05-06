/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.RecoveryBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import connection.Connect;

public class RecoveryBAL1 {

	final static Logger logger = Logger.getLogger(RecoveryBAL1.class);

	public static ArrayList<RecoveryBean> getRecoverylist() {
		ResultSet rs = null;
		Statement s = null;

		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		String query = "SELECT \n"
				+ "(SELECT COUNT(*) FROM sold_to WHERE salesman_id=1) AS Total_Customers, COUNT(sld.payement_option),SUM(ci.installments_amount) \n"
				+ "FROM sold_to sld \n"
				+ "JOIN customer_installments ci ON ci.customer_id=sld.customer_id\n"
				+ "WHERE ci.paid_date>ci.due_date AND sld.payement_option=1 AND sld.salesman_id=1;";

		int total_customers, customer_on_installment;

		try (Connection con = Connect.getConnection()) {
			RecoveryBean bean = null;

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				total_customers = rs.getInt(1);
				customer_on_installment = rs.getInt(2);

				bean = new RecoveryBean();

				bean.setCustomer_on_installments(customer_on_installment);
				bean.setTotal_customers(total_customers);
				// bean.setInstallment_amount(installment_amount);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static RecoveryBean getRecovery(int salesman_id, String datefrom,
			String dateto) {
		ResultSet rs = null;
		Statement s = null;
		RecoveryBean list = null;
		String query = "SELECT DISTINCT(appliance.appliance_name) AS apname ,(SELECT CountNumberDate(apname,"
				+ salesman_id
				+ ",'"
				+ datefrom
				+ "','"
				+ dateto
				+ "')) \n"
				+ "FROM appliance \n"
				+ "JOIN sold_to ON appliance.appliance_id=sold_to.appliance_id";

		String app_name;
		int count;

		try (Connection con = Connect.getConnection()) {
			RecoveryBean bean = null;
			// connection.Connect.init();
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

		ResultSet rs = null;
		Statement s = null;
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
					+ salesman_id
					+ ",'"
					+ formatted
					+ "','"
					+ formatted1
					+ "')) \n"
					+ "FROM appliance \n"
					+ "JOIN sold_to ON appliance.appliance_id=sold_to.appliance_id";

			int total_amount = 0;

			String app_name;
			int rates = 0;
			int count;
			try (Connection con = Connect.getConnection()) {
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

					}

					else if (app_name.equalsIgnoreCase("60 W")) {
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

	public static ArrayList<RecoveryBean> getRecoveryBean(int salesman_id,
			String formatted1, String formatted) {
		ResultSet rs = null;
		Statement s = null;
		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;

		String query = "SELECT DISTINCT(appliance.appliance_name) AS apname ,(SELECT CountNumberDate(apname,"
				+ salesman_id
				+ ",'"
				+ formatted
				+ "','"
				+ formatted1
				+ "')) \n"
				+ "FROM appliance \n"
				+ "JOIN sold_to ON appliance.appliance_id=sold_to.appliance_id";

		int total_amount = 0;

		String app_name;
		int rates = 0;
		int count;
		try (Connection con = Connect.getConnection()) {
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
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

				}

				else if (app_name.equalsIgnoreCase("60 W")) {
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

	public static ArrayList<RecoveryBean> getRecoveryCommissionBean(
			int salesman_id, String formatted1, String formatted) {
		ResultSet rs = null;
		Statement s = null;
		ArrayList<RecoveryBean> list = new ArrayList<RecoveryBean>();
		RecoveryBean bean = null;
		String query = "SELECT CustomerOnloans(" + salesman_id + ",'"
				+ formatted + "'),countlatepaidpeople(" + salesman_id + ",'"
				+ formatted + "','" + formatted1 + "'), commissionAmount("
				+ salesman_id + ",'" + formatted + "','" + formatted1
				+ "'),LateCustomer(" + salesman_id + ",'" + formatted + "','"
				+ formatted1 + "') ";
		System.err.println(query);

		int total_customers, total_amount = 0;

		int total_paid_people;
		int late_customer;
		try (Connection con = Connect.getConnection()) {
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				total_customers = rs.getInt(1);

				total_paid_people = rs.getInt(2);
				total_amount = rs.getInt(3);
				late_customer = rs.getInt(4);
				System.out.print(total_customers + "   " + total_paid_people
						+ "  " + total_amount + "  " + late_customer);
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

}
