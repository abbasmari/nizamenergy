package bal;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

import connection.Connect;
import bean.Commission_payout;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import java.util.Date;

public class LoanPaymentBAL {

	// private static connect.Connection.con con;
	final static Logger logger = Logger.getLogger(LoanPaymentBAL.class);

	public static Integer loanPaying(int applianceId, int amount) {

		int result = -1; // -1 error
							// 0 successfully amount paid, and updated loan
							// payment remaining amount and installment
							// 1 Paying to large amount
							// 2 Record Not Found In Loan Payment Installments

		try (Connection con = connection.Connect.getConnection()) {
			// System.out.println("Query Run");
			//
			// PreparedStatement checkLoanPaymentStatement = con
			// .prepareStatement("SELECT lp.Loan_payment_id, lp.Customer_id,
			// lp.loan_id, "
			// + " lp.due_date, pl.total_installment_remaining,
			// pl.remaining_balance , a.appliance_name "
			// + " FROM loan_payments lp INNER JOIN payment_loan pl ON
			// pl.loan_id = lp.loan_id "
			// + " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
			// + " INNER JOIN appliance a ON a.appliance_id = st.appliance_id
			// WHERE st.appliance_id = ? "
			// + " AND lp.Amount_Paid IS NULL AND lp.Paid_Date IS NULL");

			// checkLoanPaymentStatement.setInt(1, applianceId);
			// ResultSet rs = checkLoanPaymentStatement.executeQuery();
			if (con != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = con
						.prepareCall("{call get_payment_required_data(?)}");
				prepareCall.setInt(1, applianceId);
				ResultSet rs = prepareCall.executeQuery();
				int loanId;
				String applianceName = "";
				if (rs.next()) {
					int loanPaymentId = rs.getInt("lp.Loan_payment_id");
					loanId = rs.getInt("pl.loan_id");
					int customerId = rs.getInt("lp.Customer_id");
					Date dueDate = rs.getDate("lp.due_date");
					applianceName = rs.getString("ap.appliance_name");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dueDate);
					int remainingInstallments = rs
							.getInt("pl.total_installment_remaining");
					int remainingAmount = rs.getInt("pl.remaining_balance");
					String customerNumber = rs.getString("cs.customer_phone");
					// String SalesmanNumber =
					// rs.getString("s.salesman_phone_no");
					System.out.println("-----Before Installment Update-------");
					System.out.println("Remaining Installment : "
							+ remainingInstallments);
					System.out.println("Remaining Amount : " + remainingAmount);

					if (amount <= remainingAmount) {
						if (applianceName.equalsIgnoreCase("50 W")) {
							if (amount == 4000) {
								calendar.add(Calendar.MONTH, 5);
								remainingAmount -= 4000;
								remainingInstallments -= 5;
							} else if (amount == 8000) {
								calendar.add(Calendar.MONTH, 11);
								remainingAmount -= 8000;
								remainingInstallments -= 11;
							} else if (amount == 12000) {
								calendar.add(Calendar.MONTH, 17);
								remainingAmount -= 12000;
								remainingInstallments -= 17;
							} else {
								System.out.println(amount / 1000);
								calendar.add(Calendar.MONTH, amount / 1000);
								remainingAmount -= amount;
								remainingInstallments -= (amount / 1000);
							}
						} else if (applianceName.equalsIgnoreCase("80 W")) {
							if (amount == 6000) {
								calendar.add(Calendar.MONTH, 5);
								remainingAmount -= 6000;
								remainingInstallments -= 5;
							} else if (amount == 12000) {
								calendar.add(Calendar.MONTH, 11);
								remainingAmount -= 12000;
								remainingInstallments -= 11;
							} else if (amount == 18000) {
								calendar.add(Calendar.MONTH, 17);
								remainingAmount -= 18000;
								remainingInstallments -= 17;
							} else {
								calendar.add(Calendar.MONTH, amount / 1500);
								remainingAmount -= amount;
								remainingInstallments -= (amount / 1500);
							}
						} else if (applianceName.equalsIgnoreCase("100 W")) {
							if (amount == 8000) {
								calendar.add(Calendar.MONTH, 5);
								remainingAmount -= 8000;
								remainingInstallments -= 5;
							} else if (amount == 16000) {
								calendar.add(Calendar.MONTH, 11);
								remainingAmount -= 16000;
								remainingInstallments -= 11;
							} else if (amount == 24000) {
								calendar.add(Calendar.MONTH, 17);
								remainingAmount -= 24000;
								remainingInstallments -= 17;
							} else {
								calendar.add(Calendar.MONTH, amount / 2000);
								remainingAmount -= amount;
								remainingInstallments -= (amount / 2000);
							}
						}
						System.out.println("Year "
								+ calendar.get(Calendar.YEAR) + " Month : "
								+ calendar.get(Calendar.MONTH) + " Day : "
								+ calendar.get(Calendar.DAY_OF_MONTH));

						PreparedStatement psUpdatePaymentLoanStatment = con
								.prepareStatement("update payment_loan "
										+ "set total_installment_remaining = ?, "
										+ "remaining_balance = ? "
										+ "where loan_id = ?");

						psUpdatePaymentLoanStatment.setInt(1,
								remainingInstallments);
						psUpdatePaymentLoanStatment.setInt(2, remainingAmount);
						psUpdatePaymentLoanStatment.setInt(3, loanId);
						psUpdatePaymentLoanStatment.executeUpdate();

						System.out
								.println("-----Before Installment Update-------");
						System.out.println("Remaining Installment : "
								+ remainingInstallments);
						System.out.println("Remaining Amount : "
								+ remainingAmount);

						PreparedStatement updateLoanPaymentStatement = con
								.prepareStatement("update loan_payments "
										+ "set Amount_Paid = ?, "
										+ "Paid_Date = ? "
										+ "where Loan_payment_id = ?" + "");
						updateLoanPaymentStatement.setInt(1, amount);

						updateLoanPaymentStatement.setDate(2, new Date(Calendar
								.getInstance().getTime().getTime()));
						updateLoanPaymentStatement.setInt(3, loanPaymentId);
						int paid = updateLoanPaymentStatement.executeUpdate();
						if (paid == 1) {

							PreparedStatement insertIntoLoanPaymentStatement = con
									.prepareStatement("INSERT INTO loan_payments "
											+ "(Customer_id, "
											+ "loan_id, "
											+ "Payment_Method, "
											+ "due_date,is_first_installment) "
											+ "VALUES (?, ?, ?, ?,0);");
							insertIntoLoanPaymentStatement
									.setInt(1, customerId);
							insertIntoLoanPaymentStatement.setInt(2, loanId);
							insertIntoLoanPaymentStatement.setInt(3, 1);
							// System.out.println(calendar.getTime().getTime());
							insertIntoLoanPaymentStatement.setDate(4, new Date(
									calendar.getTime().getTime()));
							insertIntoLoanPaymentStatement.execute();
						}
						CallingXML.SendMessage(customerNumber,
								"Dear Customer, your " + amount + " "
										+ "has been recieved");
						if ((remainingAmount == 0.0 && remainingInstallments == 0)
								|| remainingAmount == 0.0) {
							CallingXML
									.SendMessage(customerNumber,
											"Dear Customer Congratulations, you have been owner of this appliance. Thanks");
						}
						result = 0;
					} else {
						result = 1;
					}

					// int loanId

				} else {
					result = 2;
				}
			}
			if (con != null) {
				con.close();

			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("LoanPayment loanPaying Ex " + e);
			result = -1;
		}
		return result;
	}

	public static Integer loanPayingOnDownPayment(int applianceId) {

		int result = -1; // -1 error
							// 0 successfully amount paid, and updated loan
							// payment remaining amount and installment
							// 1 Paying to large amount
							// 2 Record Not Found In Loan Payment Installments

		try (Connection con = connection.Connect.getConnection()) {
			System.out.println("Query Run");

			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT lp.Loan_payment_id, "
							+ "lp.Customer_id, "
							+ "lp.loan_id,"
							+ "lp.due_date,"
							+ "pl.total_installment_remaining,"
							+ "pl.remaining_balance "
							+ "FROM loan_payments lp "
							+ "INNER JOIN payment_loan pl ON pl.loan_id = lp.loan_id "
							+ "INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
							+ "WHERE st.appliance_id = ? ");

			checkLoanPaymentStatement.setInt(1, applianceId);
			ResultSet rs = checkLoanPaymentStatement.executeQuery();
			int loanId;
			if (rs.next()) {

				loanId = rs.getInt("lp.loan_id");
				int customerId = rs.getInt("lp.Customer_id");
				Date dueDate = rs.getDate("lp.due_date");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dueDate);

				int remainingInstallments = rs
						.getInt("pl.total_installment_remaining");
				int remainingAmount = rs.getInt("pl.remaining_balance");

				System.out.println("-----Before Installment Update-------");
				System.out.println("Remaining Installment : "
						+ remainingInstallments);
				System.out.println("Remaining Amount : " + remainingAmount);

				PreparedStatement insertIntoLoanPaymentStatement = con
						.prepareStatement("INSERT INTO loan_payments "
								+ "(Customer_id, loan_id, Payment_Method, due_date,is_first_installment) "
								+ "VALUES (?, ?, ?, ?,0);");
				insertIntoLoanPaymentStatement.setInt(1, customerId);
				insertIntoLoanPaymentStatement.setInt(2, loanId);
				insertIntoLoanPaymentStatement.setInt(3, 1);
				// System.out.println(calendar.getTime().getTime());
				insertIntoLoanPaymentStatement.setDate(4, new Date(calendar
						.getTime().getTime()));
				result = insertIntoLoanPaymentStatement.executeUpdate();

				// int loanId

			} else {
				result = 0;
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("LoanPayment loanPaying Ex " + e);
			result = -1;
		}
		return result;
	}

	public static void update_fo_defaulter_commission() throws SQLException {

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT Loan_payment_id, SUM(pl.installment_amount_month*fo.fo_on_time*0.01) AS defaulted_commission,fo.fo_id, lp.due_date, "
							+ "DATEDIFF(CURDATE(), lp.due_date) AS date_difference "
							+ "FROM sold_to s INNER JOIN payment_loan pl ON pl.soldto_id = s.sold_to_id JOIN loan_payments lp ON pl.loan_id=lp.loan_id "
							+ "JOIN salesman sal ON s.salesman_id=sal.salesman_id JOIN field_officer fo ON sal.fo_id=fo.fo_id "
							+ "WHERE (DATEDIFF(lp.due_date, CURDATE()) < 0) AND lp.Paid_Date IS NULL AND pl.defaulter_status=1 AND lp.fo_loanpayment_status = 0 GROUP BY sal.salesman_id;");

			rs = checkLoanPaymentStatement.executeQuery();
			Double amount = 0.0;
			Double amount2 = 0.0;
			int loanId;
			int fo_id;
			Date due_date;
			int numberOfMonths = 0;
			int date_difference = 0;
			while (rs.next()) {
				loanId = rs.getInt(1);
				amount = rs.getDouble(2);
				fo_id = rs.getInt(3);
				due_date = rs.getDate(4);
				date_difference = rs.getInt(5);
				System.out
						.println("update_fo_defaulter_commission ...Date Difference :: Default"
								+ date_difference);

				if (date_difference > 0 && date_difference < 31) {
					// 1st Month Commission
					amount2 = 1 * amount;
				} else if (date_difference == 31) {
					// 3er Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 62) {
					// 3er Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 93) {
					// 3er Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 124) {
					// 4th Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 155) {
					// 5th Month Commission
					amount2 = 1 * amount;

				}

				/*
				 * else if (date_difference > 30) {
				 * 
				 * // Next Months Commission numberOfMonths =
				 * date_difference/30; amount = numberOfMonths*amount; }
				 */

				System.err.println("date in current month");
				System.err.println("Total Amount " + amount2 + "....User_id "
						+ fo_id + "....Loan Id " + loanId
						+ "......... update_fo_defaulter_commission");

				PreparedStatement psUpdatePaymentLoanStatment = con
						.prepareStatement("update user_finiance "
								+ "set onhold_amount = onhold_amount+current_amount, defaulter_amount = defaulter_amount+ ?, "
								+ "current_amount=0, onhold_status = 1 "
								+ "where user_id = ? AND user_type=100");

				psUpdatePaymentLoanStatment.setDouble(1, amount2);
				psUpdatePaymentLoanStatment.setInt(2, fo_id);
				psUpdatePaymentLoanStatment.executeUpdate();

				PreparedStatement ps = con
						.prepareStatement("update loan_payments "
								+ "set fo_loanpayment_status = 1 where Loan_payment_id = ?");
				ps.setInt(1, loanId);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void update_vle_defaulter_commission() throws SQLException {

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT Loan_payment_id, SUM(pl.installment_amount_month*sal.on_time_percentage*0.01) AS defaulted_commission,sal.salesman_id, "
							+ "DATEDIFF(CURDATE(), lp.due_date) AS date_difference "
							+ "FROM sold_to s INNER JOIN payment_loan pl ON pl.soldto_id = s.sold_to_id JOIN loan_payments lp ON pl.loan_id=lp.loan_id JOIN salesman sal ON s.salesman_id=sal.salesman_id "
							+ "WHERE (DATEDIFF(lp.due_date, CURDATE()) < 0) AND lp.Paid_Date IS NULL AND pl.defaulter_status=1 AND lp.vle_loanpayment_status = 0 GROUP BY sal.salesman_id;");

			rs = checkLoanPaymentStatement.executeQuery();
			Double amount = 0.0;
			Double amount2 = 0.0;
			int vle_id, loanId;
			int numberOfMonths = 0;
			int date_difference = 0;
			while (rs.next()) {
				loanId = rs.getInt(1);
				amount = rs.getDouble(2);
				vle_id = rs.getInt(3);
				date_difference = rs.getInt(4);
				System.out
						.println("update_vle_defaulter_commission ... Date Difference :: Defaulted "
								+ date_difference);

				if (date_difference > 0 && date_difference < 31) {
					// 1st Month Commission
					amount2 = 1 * amount;
				} else if (date_difference == 31) {
					// 3er Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 62) {
					// 3er Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 93) {
					// 3er Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 124) {
					// 4th Month Commission
					amount2 = 1 * amount;

				} else if (date_difference == 155) {
					// 5th Month Commission
					amount2 = 1 * amount;

				}

				System.err.println("Total Amount " + amount2 + "....loanId "
						+ loanId + "....vle_id " + vle_id + "...loanId "
						+ loanId + "....... update_vle_defaulter_commission");
				PreparedStatement psUpdatePaymentLoanStatment = con
						.prepareStatement("update user_finiance "
								+ "set onhold_amount = onhold_amount+current_amount, defaulter_amount =defaulter_amount+ ?, "
								+ "current_amount=0, onhold_status = 1 "
								+ "where user_id = ? AND user_type=101");//

				psUpdatePaymentLoanStatment.setDouble(1, amount2);
				psUpdatePaymentLoanStatment.setInt(2, vle_id);

				psUpdatePaymentLoanStatment.executeUpdate();

				PreparedStatement ps = con
						.prepareStatement("update loan_payments "
								+ "set vle_loanpayment_status = 1 where Loan_payment_id = ?");

				ps.setInt(1, loanId);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void update_onhold_commission() throws SQLException {
		System.err.println("12");
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT defaulter_amount ,finiance_id FROM user_finiance WHERE defaulter_amount>0");

			rs = checkLoanPaymentStatement.executeQuery();
			Double amount = 0.0;
			int finiance_id;
			while (rs.next()) {
				System.err.println("123");
				amount = rs.getDouble(1);
				finiance_id = rs.getInt(2);
				System.out.println("Amount " + amount + " ID " + finiance_id);
				PreparedStatement psUpdatePaymentLoanStatment = con
						.prepareStatement("update user_finiance "
								+ "set onhold_status = 1 "
								+ "where finiance_id = ? ");// set
															// onhold_amount
															// =
															// onhold_amount+?,

				// psUpdatePaymentLoanStatment.setDouble(1, amount);
				psUpdatePaymentLoanStatment.setInt(1, finiance_id);

				psUpdatePaymentLoanStatment.executeUpdate();

			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static ArrayList<Commission_payout> get_vle_commissions()
			throws SQLException {
		ArrayList<Commission_payout> list = new ArrayList<Commission_payout>();

		ResultSet rs = null;
		String salesman, vle_acount;
		int salesman_id;
		double total_amount, current_commission, defaulter_commission, hold_amount, paid_today;
		try (Connection con = connection.Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT  sal.salesman_name, "
							+ " sal.vle_acount_no, "
							+ " fin.total_amount, "
							+ " fin.current_amount, "
							+ " fin.defaulter_amount, "
							+ " fin.onhold_amount, "
							+ " fin.paid_today, "
							+ " sal.salesman_id , "
							+ " count_customer, fo.fo_name, fin.previous_month_commission "
							+ " FROM user_finiance fin "
							+ " JOIN salesman sal  "
							+ " ON fin.user_id = sal.salesman_id "
							+ "JOIN `field_officer` fo ON fo.`fo_id` = sal.`fo_id` "
							+ " JOIN district dist  "
							+ " ON dist.district_id = sal.salesman_district "
							+ " LEFT JOIN  "
							+ " (SELECT COUNT(st.customer_id) AS count_customer, "
							+ " COUNT(st.appliance_id) AS count_sales, "
							+ " st.salesman_id FROM "
							+ " sold_to st  "
							+ " WHERE st.status > 0 "
							+ " GROUP BY st.salesman_id) AS salesman_customer_count "
							+ " ON salesman_customer_count.salesman_id = sal.salesman_id "
							+ " WHERE count_customer>0 AND  fin.`user_type`=101 "
							+ " GROUP BY sal.salesman_id ");

			rs = checkLoanPaymentStatement.executeQuery();

			while (rs.next()) {
				salesman = rs.getString(1);
				vle_acount = rs.getString(2);
				total_amount = rs.getDouble(3);
				current_commission = rs.getDouble(4);
				defaulter_commission = rs.getDouble(5);
				hold_amount = rs.getDouble(6);
				paid_today = rs.getDouble(7);
				salesman_id = rs.getInt(8);

				Commission_payout bean = new Commission_payout();
				bean.setAcount_number(vle_acount);
				bean.setCurrent_month_commission(current_commission);
				bean.setCustomer_name(salesman);
				bean.setPaid_today(paid_today);
				bean.setTotal_commission(total_amount);
				bean.setDefault_commission(defaulter_commission);
				bean.setHold_amount(hold_amount);
				bean.setPayer_id(salesman_id);
				bean.setFo_name(rs.getString("fo_name"));
				bean.setPrevious_month_commission(rs
						.getDouble("previous_month_commission"));
				System.err.println(bean);
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<Commission_payout> get_fo_commissions()
			throws SQLException {
		ArrayList<Commission_payout> list = new ArrayList<Commission_payout>();

		ResultSet rs = null;
		String salesman, vle_acount;
		int fo_id;
		double total_amount, current_commission, defaulter_commission, hold_amount, paid_today;
		try (Connection con = connection.Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT "
							+ " fo.fo_name, "
							+ " fo.fo_acount_no, "
							+ " fin.total_amount, "
							+ " fin.current_amount, "
							+ " fin.defaulter_amount, "
							+ " fin.onhold_amount, "
							+ " fin.paid_today, "
							+ " fo.fo_id, "
							+ " count_customer, fin.previous_month_commission "
							+ " FROM user_finiance fin "
							+ " JOIN `field_officer` fo "
							+ " ON fin.`user_id` = fo.`fo_id` "
							+ " JOIN salesman sal "
							+ " ON fo.`fo_id` = sal.`fo_id` "
							+ " JOIN district dist "
							+ " ON dist.district_id = sal.salesman_district "
							+ " LEFT JOIN "
							+ " (SELECT COUNT(st.customer_id) AS count_customer, "
							+ " COUNT(st.appliance_id) AS count_sales, "
							+ " st.salesman_id "
							+ " FROM sold_to st "
							+ " WHERE st.status > 0 "
							+ " GROUP BY st.salesman_id) AS salesman_customer_count "
							+ " ON salesman_customer_count.salesman_id = sal.salesman_id "
							+ " WHERE count_customer>0 AND  fin.`user_type`=100 "
							+ " GROUP BY fo.`fo_id`");

			rs = checkLoanPaymentStatement.executeQuery();

			while (rs.next()) {
				salesman = rs.getString(1);
				vle_acount = rs.getString(2);
				total_amount = rs.getDouble(3);
				current_commission = rs.getDouble(4);
				defaulter_commission = rs.getDouble(5);
				hold_amount = rs.getDouble(6);
				paid_today = rs.getDouble(7);
				fo_id = rs.getInt(8);
				Commission_payout bean = new Commission_payout();
				bean.setAcount_number(vle_acount);
				bean.setCurrent_month_commission(current_commission);
				bean.setCustomer_name(salesman);
				bean.setPaid_today(paid_today);
				bean.setTotal_commission(total_amount);
				bean.setDefault_commission(defaulter_commission);
				bean.setHold_amount(hold_amount);
				bean.setPayer_id(fo_id);
				bean.setPrevious_month_commission(rs
						.getDouble("previous_month_commission"));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int Pay_commission(int type, int payer_id)
			throws SQLException {
		int row = 0;
		int total_paid = 0;

		ResultSet rs = null;
		try (Connection con = connection.Connect.getConnection()) {
			PreparedStatement get_total_paid_amount = con
					.prepareStatement("SELECT MAX(user_finiance_histroy.total_paid) FROM user_finiance_histroy WHERE user_finiance_histroy.user_type=? AND user_finiance_histroy.user_id=? ");
			get_total_paid_amount.setInt(1, type);
			get_total_paid_amount.setInt(2, payer_id);

			rs = get_total_paid_amount.executeQuery();
			Double amount = 0.0;
			// int vle_id;
			while (rs.next()) {
				// amount=rs.getDouble(1);
				total_paid = rs.getInt(1);
			}

			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT previous_month_commission FROM user_finiance WHERE user_type=? AND user_id=?");
			checkLoanPaymentStatement.setInt(1, type);
			checkLoanPaymentStatement.setInt(2, payer_id);

			rs = checkLoanPaymentStatement.executeQuery();

			while (rs.next()) {
				// amount=rs.getDouble(1);
				amount = rs.getDouble(1);
				total_paid = (int) (total_paid + amount);
				PreparedStatement psUpdatePaymentLoanStatment = con
						.prepareStatement("INSERT INTO user_finiance_histroy(finiance_month,paid_amount,total_paid,user_id,user_type) VALUES(CURDATE(),?,?,?,?)");

				psUpdatePaymentLoanStatment.setDouble(1, amount);
				psUpdatePaymentLoanStatment.setInt(2, total_paid);
				psUpdatePaymentLoanStatment.setInt(3, payer_id);
				psUpdatePaymentLoanStatment.setInt(4, type);
				psUpdatePaymentLoanStatment.execute();

			}
			PreparedStatement psUpdatePaymentLoanStatment = con
					.prepareStatement("update user_finiance "
							+ "set previous_month_commission = 0, total_amount = total_amount+? "
							+ " where user_type=? AND user_id = ?");

			psUpdatePaymentLoanStatment.setDouble(1, amount);
			psUpdatePaymentLoanStatment.setInt(2, type);
			psUpdatePaymentLoanStatment.setInt(3, payer_id);

			psUpdatePaymentLoanStatment.executeUpdate();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return row;
	}

	public static int Pay_commission_onhold(int type, int payer_id)
			throws SQLException {
		int row = 0;
		int total_paid = 0;

		ResultSet rs = null;
		try (Connection con = connection.Connect.getConnection()) {
			PreparedStatement get_total_paid_amount = con
					.prepareStatement("SELECT MAX(user_finiance_histroy.total_paid) FROM user_finiance_histroy WHERE user_finiance_histroy.user_type=? AND user_finiance_histroy.user_id=? ");
			get_total_paid_amount.setInt(1, type);
			get_total_paid_amount.setInt(2, payer_id);

			rs = get_total_paid_amount.executeQuery();
			Double amount = 0.0;
			// int vle_id;
			while (rs.next()) {
				// amount=rs.getDouble(1);
				total_paid = rs.getInt(1);
			}

			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT onhold_amount FROM user_finiance WHERE user_type=? AND user_id=?");
			checkLoanPaymentStatement.setInt(1, type);
			checkLoanPaymentStatement.setInt(2, payer_id);

			rs = checkLoanPaymentStatement.executeQuery();

			while (rs.next()) {
				// amount=rs.getDouble(1);
				amount = rs.getDouble(1);
				total_paid = (int) (total_paid + amount);
				PreparedStatement psUpdatePaymentLoanStatment = con
						.prepareStatement("INSERT INTO user_finiance_histroy(finiance_month,paid_amount,total_paid,user_id,user_type) VALUES(CURDATE(),?,?,?,?)");

				psUpdatePaymentLoanStatment.setDouble(1, amount);
				psUpdatePaymentLoanStatment.setInt(2, total_paid);
				psUpdatePaymentLoanStatment.setInt(3, payer_id);
				psUpdatePaymentLoanStatment.setInt(4, type);
				psUpdatePaymentLoanStatment.execute();

			}
			PreparedStatement psUpdatePaymentLoanStatment = con
					.prepareStatement("update user_finiance "
							+ "set total_amount = total_amount+?, onhold_amount = 0, onhold_status = 0  "
							+ " where user_type = ? AND user_id = ?");
			psUpdatePaymentLoanStatment.setDouble(1, amount);
			psUpdatePaymentLoanStatment.setInt(2, type);
			psUpdatePaymentLoanStatment.setInt(3, payer_id);
			psUpdatePaymentLoanStatment.execute();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;

	}

	public static void update_fo_defaulter_loans() throws SQLException {
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT pl.loan_id FROM payment_loan pl JOIN loan_payments lp ON pl.loan_id=lp.loan_id  "
							+ "WHERE (DATEDIFF(lp.due_date, CURDATE()) < 0)  AND lp.Paid_Date IS NULL AND pl.defaulter_status=0 ");

			rs = checkLoanPaymentStatement.executeQuery();

			int loan_id;
			while (rs.next()) {
				System.err.println(".......");
				loan_id = rs.getInt(1);
				System.err.println("User_id " + loan_id
						+ "................update_fo_defaulter_loans");
				System.out.println("User_id " + loan_id);
				PreparedStatement psUpdatePaymentLoanStatment = con
						.prepareStatement("update payment_loan SET defaulter_status=1 where loan_id=?");

				psUpdatePaymentLoanStatment.setInt(1, loan_id);

				psUpdatePaymentLoanStatment.executeUpdate();

				/*
				 * PreparedStatement psUpdateUserFiniance = con
				 * .prepareStatement(
				 * "update payment_loan SET defaulter_status=1,  where loan_id=?"
				 * );
				 * 
				 * psUpdateUserFiniance.setInt(1, loan_id);
				 * 
				 * psUpdateUserFiniance.executeUpdate();
				 */
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static boolean checkCustomerDueDate() {
		// int customer_id, int loan_id
		ResultSet rs = null;
		boolean result = false;
		try (Connection con = Connect.getConnection()) {
			// connection.Connect.init();
			String query = "SELECT DATEDIFF(lp.due_date,CURDATE()) AS diffdate, lp.due_date,lp.Loan_payment_id FROM appliance ap "
					+ "JOIN sold_to sld ON ap.appliance_id=sld.appliance_id "
					+ "JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id "
					+ "JOIN customer cs ON sld.customer_id=cs.customer_id "
					+ "JOIN loan_payments lp ON pl.loan_id=lp.loan_id "
					+ "WHERE "
					+ "AND (DATEDIFF(lp.due_date ,CURDATE()) < 0) AND lp.Paid_Date IS NULL "
					+ "AND pl.defaulter_status = 1 AND (lp.fo_loanpayment_status = 1 OR lp.vle_loanpayment_status = 1) ";
			System.out.println("checkCustomerDueDate........." + query);
			PreparedStatement psCusDueDate = con.prepareStatement(query);
			// psCusDueDate.setInt(1, loan_id);
			// psCusDueDate.setInt(2, customer_id);
			rs = psCusDueDate.executeQuery();
			int diffdate = 0;
			Date due_date = null;
			int loan_payId = 0;
			while (rs.next()) {

				diffdate = rs.getInt(1);
				due_date = rs.getDate(2);
				loan_payId = rs.getInt(3);
				String due = due_date.toString();

				System.err.println("**--**--** " + result);
				if (diffdate < 0) {
					result = true;
					System.err.println("IF **--**--** " + result);
				} else {
					result = false;
					System.err.println("ELSE 222222  " + result);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return result;

	}

	public static void UpdateFoDateCrossOneMonth() throws SQLException {

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT Loan_payment_id, SUM(pl.installment_amount_month*fo.fo_on_time*0.01) AS defaulted_commission,fo.fo_id, lp.due_date, "
							+ "DATEDIFF(CURDATE(), lp.due_date) AS date_difference "
							+ "FROM sold_to s INNER JOIN payment_loan pl ON pl.soldto_id = s.sold_to_id JOIN loan_payments lp ON pl.loan_id=lp.loan_id "
							+ "JOIN salesman sal ON s.salesman_id=sal.salesman_id JOIN field_officer fo ON sal.fo_id=fo.fo_id "
							+ "WHERE (DATEDIFF(lp.due_date, CURDATE()) < 0) AND lp.Paid_Date IS NULL AND pl.defaulter_status=1 AND lp.fo_loanpayment_status = 1 GROUP BY pl.`loan_id`;");

			rs = checkLoanPaymentStatement.executeQuery();
			Double amount = 0.0;
			Double amount2 = 0.0;
			int loanId;
			int fo_id;
			Date due_date;
			int numberOfMonths = 0;
			int date_difference = 0;
			while (rs.next()) {
				loanId = rs.getInt(1);
				amount = rs.getDouble(2);
				fo_id = rs.getInt(3);
				due_date = rs.getDate(4);
				date_difference = rs.getInt(5);
				System.out
						.println("update_fo_defaulter_commission ...Date Difference "
								+ date_difference);

				if (date_difference == 31) {
					// 1st Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set fo_loanpayment_status = 0 where Loan_payment_id = ?");
					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 62) {
					// 3er Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set fo_loanpayment_status = 0 where Loan_payment_id = ?");
					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 93) {
					// 3er Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set fo_loanpayment_status = 0 where Loan_payment_id = ?");
					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 124) {
					// 4th Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set fo_loanpayment_status = 0 where Loan_payment_id = ?");
					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 155) {
					// 5th Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set fo_loanpayment_status = 0 where Loan_payment_id = ?");
					ps.setInt(1, loanId);
					ps.executeUpdate();

				}

				System.err.println("Total Amount " + amount2 + "....User_id "
						+ fo_id + "....Loan Id " + loanId
						+ "..... update_fo_defaulter_commission");
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void UpdateVLEDateCrossOneMonth() throws SQLException {
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement checkLoanPaymentStatement = con
					.prepareStatement("SELECT Loan_payment_id, SUM(pl.installment_amount_month*sal.on_time_percentage*0.01) AS defaulted_commission,sal.salesman_id, "
							+ "DATEDIFF(CURDATE(), lp.due_date) AS date_difference, lp.vle_loanpayment_status, COUNT(*)  "
							+ "FROM sold_to s INNER JOIN payment_loan pl ON pl.soldto_id = s.sold_to_id JOIN loan_payments lp ON pl.loan_id=lp.loan_id JOIN salesman sal ON s.salesman_id=sal.salesman_id "
							+ "WHERE (DATEDIFF(lp.due_date, CURDATE()) < 0) AND lp.Paid_Date IS NULL AND pl.defaulter_status=1 AND lp.vle_loanpayment_status = 1 GROUP BY pl.`loan_id`;");

			rs = checkLoanPaymentStatement.executeQuery();
			Double amount = 0.0;
			Double amount2 = 0.0;
			int vle_id, loanId;
			int numberOfMonths = 0;
			int date_difference = 0;
			int vle_loanpayment_status;
			int count = 0;
			while (rs.next()) {
				loanId = rs.getInt(1);
				amount = rs.getDouble(2);
				vle_id = rs.getInt(3);
				date_difference = rs.getInt(4);
				vle_loanpayment_status = rs.getInt(5);
				count = rs.getInt(6);

				System.out
						.println(count
								+ "  update_vle_defaulter_commission ... Date Difference "
								+ date_difference
								+ "  vle_loanpayment_status :: "
								+ vle_loanpayment_status);

				if (date_difference == 31) {
					// 1st Month Commission
					// amount2 = 1*amount;
					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set vle_loanpayment_status = 0 where Loan_payment_id = ?");

					ps.setInt(1, loanId);
					ps.executeUpdate();
				} else if (date_difference == 62) {
					// 3er Month Commission
					// amount2 = 1*amount;
					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set vle_loanpayment_status = 0 where Loan_payment_id = ?");

					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 93) {
					// 3er Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set vle_loanpayment_status = 0 where Loan_payment_id = ?");

					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 124) {
					// 4th Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set vle_loanpayment_status = 0 where Loan_payment_id = ?");

					ps.setInt(1, loanId);
					ps.executeUpdate();

				} else if (date_difference == 155) {
					// 5th Month Commission
					// amount2 = 1*amount;

					PreparedStatement ps = con
							.prepareStatement("update loan_payments "
									+ "set vle_loanpayment_status = 0 where Loan_payment_id = ?");

					ps.setInt(1, loanId);
					ps.executeUpdate();

				}

				System.err.println("Total Amount " + amount2 + "....vle_id "
						+ vle_id + "...loanId " + loanId
						+ "....... update_vle_defaulter_commission");

			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static void updateCurrentMonthCommission() {
		ResultSet rs = null;
		int row = 0;
		try (Connection con = Connect.getConnection()) {

			PreparedStatement currentMonthCommm = con
					.prepareStatement("UPDATE `user_finiance` u SET u.`previous_month_commission` = u.`previous_month_commission`+u.`current_amount`, "
							+ "u.`current_amount`=0 WHERE u.`defaulter_amount`=0 AND u.`onhold_amount`=0 ");
			// + "u.`user_id`=33 AND u.`user_type`=101");
			row = currentMonthCommm.executeUpdate();
			if (row > 0) {
				System.out.println("user_finiance has been updated");
			} else {
				System.out.println("user_finiance has not been updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void main(String[] argv) throws SQLException {
		// update_fo_defaulter_commission();
		// update_onhold_commission();
		// update_fo_defaulter_loans();
		// update_vle_defaulter_commission();
		// update_fo_defaulter_loans();
		// update_fo_defaulter_commission();
		System.err.println(get_vle_commissions());
		;

	}

}
