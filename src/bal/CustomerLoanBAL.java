
package bal;

import static bal.UserBAL.st;
import static java.lang.Math.round;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.sql.CallableStatement;

import org.apache.log4j.Logger;
import com.mysql.jdbc.Connection;

import connection.Connect;

public class CustomerLoanBAL {
	static int customer_id;
	static Date due_date;
	static Date old_duedate;
	static Date activated_date;
	static int remaining_balance;
	static int grace_period12;
	static int days;
	static int newgrace;
	static String activated_Date;
	static int appliance_id;
	static int customer_id12;
	static String GSMNO;
	static int updateappliance;
	static int payment_method;

	// static Calendar active = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
	final static Logger logger = Logger.getLogger(CustomerLoanBAL.class);

	public static int InsertLoanSchedule(int loan_id, int customer_id, double total_amount, int installments)
			throws ParseException {

		Calendar calendar = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		Date cuurentdate = new Date();

		for (int i = 1; i < installments; i++) {
			double amount = Math.round(total_amount / installments);
			calendar.setTime(cuurentdate);
			calendar.add(Calendar.MONTH, i);
			Date nextMonth = calendar.getTime();

			DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd");
			String formattedDate = df2.format(nextMonth);

			String query = "INSERT INTO customer_installments(payment_loan_id,customer_id,installments_amount,due_date)"
					+ "Values(" + loan_id + "," + customer_id + "," + amount + ",'" + formattedDate + "')";
			System.out.println("Query : " + query);
			int row = 0;
			try (Connection con = Connect.getConnection()) {
				st = con.createStatement();
				row = st.executeUpdate(query);
				if (row > 0) {
					System.out.println("Data is inserted");
				} else {
					System.out.println("Data is not inserted");
				}

			} catch (Exception e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static int InsertLoanPayment2(int loan_id, double total_amount, double installments) {
		DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd");

		Calendar calendardue_date = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		Calendar activatedate = new GregorianCalendar(2009, 11, 31, 23, 30, 0);

		Date sold_date = new Date();
		Date currdate = new Date();
		activatedate.setTime(currdate);
		calendardue_date.setTime(sold_date);

		int amount = (int) (installments / 30);

		double daysactivate = round(total_amount / amount);
		int daysactivate1 = (int) daysactivate;

		String query = "SELECT sld.sold_date,pl.installment_amount_month , sld.customer_id,lp.due_date ,lp.due_date,pl.grace_period FROM sold_to sld\n"
				+ "                JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
				+ "                JOIN loan_payments lp ON pl.loan_id=lp.loan_id\n"
				+ "                WHERE pl.loan_id=" + loan_id + " ORDER BY lp.Loan_payment_id DESC LIMIT 1;";

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt = (Statement) con.prepareStatement(query);

			System.out.print(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				sold_date = rs.getDate(1);
				customer_id = rs.getInt(3);
				old_duedate = rs.getDate(5);
				newgrace = rs.getInt(6);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		System.out.print("TEST" + old_duedate);

		String currDate = df2.format(currdate);
		Calendar active = new GregorianCalendar(2009, 11, 31, 23, 30, 0);

		if (newgrace < 0) {
			active.setTime(currdate);
			active.add(Calendar.DAY_OF_MONTH, daysactivate1);
		} else {
			active.setTime(old_duedate);
			active.add(Calendar.DAY_OF_MONTH, daysactivate1);
		}
		Date activedate = active.getTime();
		activated_Date = df2.format(activedate);

		if (currdate.after(old_duedate)) {
			System.out.print("YOU ARE IN GRACE PERIOD " + daysactivate1);
		} else {
			System.out.print("YOU ARE IN NOT GRACE PERIOD ");
		}

		if (old_duedate.compareTo(currdate) < 0) {

			long diff = currdate.getTime() - old_duedate.getTime();

			days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}

		String query1 = "INSERT INTO loan_payments (Customer_id,loan_id,Payment_Method,Amount_Paid,Paid_Date,Activated_Date,due_date)"
				+ "Values(" + customer_id + "," + loan_id + "," + 1 + "," + total_amount + ",'" + currDate + "','"
				+ activated_Date + "','" + activated_Date + "')";
		System.out.println("Query : " + query1);
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query1);
			if (row > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String queryactivated = "SELECT activated_date ,Payment_Method FROM loan_payments WHERE loan_id=" + loan_id
				+ " ORDER BY Loan_payment_id DESC LIMIT 1\n" + "";

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt = (Statement) con.prepareStatement(queryactivated);
			System.out.print(queryactivated);
			rs = stmt.executeQuery(queryactivated);

			while (rs.next()) {
				activated_date = rs.getDate(1);
				payment_method = rs.getInt(2);

			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		String querypayment = "INSERT INTO  payments_details(customer_id,loan_id,amount,due_date,paid_date,grace_period,activated_date,payment_method) VALUE ("
				+ customer_id + "," + loan_id + "," + total_amount + ",'" + old_duedate + "','" + currDate + "',"
				+ newgrace + ",'" + activated_date + "'," + payment_method + ")";
		System.out.println("Query : " + querypayment);
		int rowpayment = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			rowpayment = st.executeUpdate(querypayment);
			if (rowpayment > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String query341 = "SELECT remaining_balance ,grace_period FROM payment_loan WHERE loan_id=" + loan_id;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt34 = (Statement) con.prepareStatement(query341);
			System.out.print(query);
			rs = stmt34.executeQuery(query341);
			while (rs.next()) {
				remaining_balance = rs.getInt(1);
				grace_period12 = rs.getInt(2);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		int paidamount = (int) total_amount;
		System.out.println("this is Grace periods" + grace_period12);

		String queryupdate = "UPDATE payment_loan SET remaining_balance =" + (remaining_balance - paidamount)
				+ " ,grace_period=" + (grace_period12 - days) + " WHERE loan_id=" + loan_id;
		int rowupdate = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(queryupdate);
			if (rowupdate > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return 0;
	}

	public static int InsertLoanPayment(int loan_id, double total_amount, double installments, String transctionNo) {
		DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd");

		Calendar calendardue_date = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		Calendar activatedate = new GregorianCalendar(2009, 11, 31, 23, 30, 0);

		Date sold_date = new Date();
		Date currdate = new Date();
		activatedate.setTime(currdate);
		calendardue_date.setTime(sold_date);

		int amount = (int) (installments / 30);

		double daysactivate = round(total_amount / amount);
		int daysactivate1 = (int) daysactivate;

		String query = "SELECT sld.sold_date,pl.installment_amount_month , sld.customer_id,lp.due_date ,lp.due_date,pl.grace_period FROM sold_to sld\n"
				+ "                JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
				+ "                JOIN loan_payments lp ON pl.loan_id=lp.loan_id\n"
				+ "                WHERE pl.loan_id=" + loan_id + " ORDER BY lp.Loan_payment_id DESC LIMIT 1;";

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt = (Statement) con.prepareStatement(query);

			System.out.print(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				sold_date = rs.getDate(1);

				customer_id = rs.getInt(3);
				old_duedate = rs.getDate(5);
				newgrace = rs.getInt(6);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		System.out.print("TEST" + old_duedate);

		String currDate = df2.format(currdate);

		Calendar active = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		if (newgrace < 0) {
			active.setTime(currdate);
			active.add(Calendar.DAY_OF_WEEK, daysactivate1);
		} else {
			active.setTime(old_duedate);
			active.add(Calendar.DAY_OF_WEEK, daysactivate1);
		}
		Date activedate = active.getTime();
		activated_Date = df2.format(activedate);

		if (currdate.after(old_duedate)) {
			System.out.print("YOU ARE IN GRACE PERIOD " + daysactivate1);
		} else {
			System.out.print("YOU ARE IN NOT GRACE PERIOD ");
		}

		if (old_duedate.compareTo(currdate) < 0) {

			long diff = currdate.getTime() - old_duedate.getTime();

			days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}

		String query1 = "INSERT INTO loan_payments (Customer_id,loan_id,Payment_Method,Amount_Paid,Paid_Date,Activated_Date,due_date)"
				+ "Values(" + customer_id + "," + loan_id + "," + 1 + "," + total_amount + ",'" + currDate + "','"
				+ activated_Date + "','" + activated_Date + "')";
		System.out.println("Query : " + query1);
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query1);
			if (row > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}
			st.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String queryactivated = "SELECT activated_date ,Payment_Method FROM loan_payments WHERE loan_id=" + loan_id
				+ " ORDER BY Loan_payment_id DESC LIMIT 1\n" + "";

		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt = (Statement) con.prepareStatement(queryactivated);
			System.out.print(queryactivated);
			rs = stmt.executeQuery(queryactivated);
			while (rs.next()) {
				activated_date = rs.getDate(1);
				payment_method = rs.getInt(2);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		String querypayment = "INSERT INTO  payments_details(customer_id,loan_id,amount,due_date,paid_date,grace_period,activated_date,payment_method,trasnction_id) VALUE ("
				+ customer_id + "," + loan_id + "," + total_amount + ",'" + old_duedate + "','" + currDate + "',"
				+ newgrace + ",'" + activated_date + "'," + payment_method + ",'" + transctionNo + "')";
		System.out.println("Query : " + querypayment);
		int rowpayment = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			rowpayment = st.executeUpdate(querypayment);
			if (rowpayment > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}
			st.close();

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String query341 = "SELECT remaining_balance ,grace_period FROM payment_loan WHERE loan_id=" + loan_id;
		try (Connection con = Connect.getConnection()) {

			Statement stmt34 = (Statement) con.prepareStatement(query341);
			ResultSet rs = null;
			System.out.print(query);
			rs = stmt34.executeQuery(query341);
			while (rs.next()) {
				remaining_balance = rs.getInt(1);
				grace_period12 = rs.getInt(2);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		int paidamount = (int) total_amount;
		System.out.println("this is Grace periods" + grace_period12);

		String queryupdate = "UPDATE payment_loan SET remaining_balance =" + (remaining_balance - paidamount)
				+ " ,grace_period=" + (grace_period12 - days) + " WHERE loan_id=" + loan_id;
		int rowupdate = 0;
		try (Connection con = Connect.getConnection()) {

			st = con.createStatement();
			row = st.executeUpdate(queryupdate);
			if (rowupdate > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 0;
	}

	public static int CheckupatePayment(int loan_id) {

		Calendar calendardue_date = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		Calendar activatedate = new GregorianCalendar(2009, 11, 31, 23, 30, 0);

		Date sold_date = new Date();
		Date currdate = new Date();
		activatedate.setTime(currdate);
		calendardue_date.setTime(sold_date);

		String query = "SELECT sld.sold_date,pl.installment_amount_month , sld.customer_id,lp.due_date ,lp.due_date FROM sold_to sld\n"
				+ "                JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n"
				+ "                JOIN loan_payments lp ON pl.loan_id=lp.loan_id\n"
				+ "                WHERE pl.loan_id=" + loan_id + " ORDER BY lp.Loan_payment_id DESC LIMIT 1;";

		try (Connection con = Connect.getConnection()) {
			Statement stmt = (Statement) con.prepareStatement(query);
			ResultSet rs = null;
			System.out.print(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				sold_date = rs.getDate(1);

				customer_id = rs.getInt(3);
				old_duedate = rs.getDate(5);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		System.out.print("TEST" + old_duedate);

		Calendar active = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		active.setTime(old_duedate);

		if (currdate.after(old_duedate)) {
			System.out.print("YOU ARE IN GRACE PERIOD ");
		} else {
			System.out.print("YOU ARE IN NOT GRACE PERIOD ");
		}
		if (old_duedate.compareTo(currdate) < 0) {
			long diff = currdate.getTime() - old_duedate.getTime();
			days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}

		String query341 = "SELECT remaining_balance ,grace_period FROM payment_loan WHERE loan_id=" + loan_id;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt34 = (Statement) con.prepareStatement(query341);
			System.out.print(query);
			rs = stmt34.executeQuery(query341);
			while (rs.next()) {
				remaining_balance = rs.getInt(1);
				grace_period12 = rs.getInt(2);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		System.out.println("this is Grace periods" + grace_period12);
		String queryupdate = "UPDATE payment_loan SET grace_period=" + (grace_period12 - days) + " WHERE loan_id="
				+ loan_id;
		int rowupdate = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			rowupdate = st.executeUpdate(queryupdate);
			if (rowupdate > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 0;
	}

	public static int Insertgrace(int loan_id) throws SQLException {

		String queryupdate = "SELECT aps.appliance_id,sld.customer_id,aps.appliance_GSMno FROM appliance aps\n"
				+ "JOIN sold_to sld ON aps.appliance_id=sld.appliance_id\n"
				+ "JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n" + "WHERE pl.loan_id=" + loan_id;
		try (Connection con = Connect.getConnection()) {

			Statement stmt34 = (Statement) con.prepareStatement(queryupdate);
			ResultSet rs = null;
			System.out.print(queryupdate);
			rs = stmt34.executeQuery(queryupdate);
			while (rs.next()) {
				appliance_id = rs.getInt(1);
				customer_id12 = rs.getInt(2);
				GSMNO = rs.getString(3);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String queryupdate12 = "UPDATE payment_loan SET grace_period=" + 15 + " WHERE loan_id=" + loan_id;
		int rowupdate = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			rowupdate = st.executeUpdate(queryupdate12);
			if (rowupdate > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 1;
	}

	public static int updateappliance_status(int loan_id) throws SQLException {

		String queryupdate = "SELECT aps.appliance_id,sld.customer_id,aps.appliance_GSMno FROM appliance aps\n"
				+ "JOIN sold_to sld ON aps.appliance_id=sld.appliance_id\n"
				+ "JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id\n" + "WHERE pl.loan_id=" + loan_id;
		try (Connection con = Connect.getConnection()) {

			Statement stmt34 = (Statement) con.prepareStatement(queryupdate);
			ResultSet rs = null;
			System.out.print(queryupdate);
			rs = stmt34.executeQuery(queryupdate);
			while (rs.next()) {
				updateappliance = rs.getInt(1);
				customer_id12 = rs.getInt(2);
				GSMNO = rs.getString(3);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String updatestatus = "UPDATE appliance   SET appliance_status=" + 0 + "  WHERE appliance_id="
				+ updateappliance;
		int rowupdate = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			rowupdate = st.executeUpdate(updatestatus);
			if (rowupdate > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String querypayment = "INSERT INTO customer_message(customer_id, text, gsm_no, status, message_date,type) VALUES ("
				+ customer_id12 + ",'off','" + GSMNO + "'," + updateappliance + ", CURRENT_TIMESTAMP," + 1 + ")";
		System.out.println("Query : " + querypayment);
		int rowpayment = 0;
		try (Connection con = Connect.getConnection()) {
			Statement st = null;
			st = con.createStatement();
			rowpayment = st.executeUpdate(querypayment);
			if (rowpayment > 0) {
				System.out.println("Data is inserted already");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 1;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanBooks(int doId, int str, int end, String order, int col) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con.prepareCall("{Call get_do_loan_books(?,?,?,?,?)}");

			callst.setInt(1, doId);
			callst.setInt(2, str);
			callst.setInt(3, end);
			callst.setString(4, order);
			callst.setInt(5, col);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("remainingDays", rs.getString("remaining_days"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceIMEI", rs.getString("imei_number"));
				map.put("cityName", rs.getString("city_name"));
				map.put("loanOutStanding", rs.getString("loanoutstanding"));
				map.put("loanDue", rs.getString("monthly_pay"));
				map.put("loanPaid", rs.getString("total_paid"));
				map.put("status", rs.getString("status_get"));
				map.put("scheme", rs.getString("installment_scheme"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getDoLoanBooksBySearch(int doId, int strt, int end, String order,
			int col, String search) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con.prepareCall("{CALL get_do_loan_books_search(?,?,?,?,?,?)}");
			callst.setInt(1, doId);
			callst.setInt(2, strt);
			callst.setInt(3, end);
			callst.setString(4, order);
			callst.setInt(5, col);
			callst.setString(6, search);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("remainingDays", rs.getString("remaining_days"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceIMEI", rs.getString("imei_number"));
				map.put("cityName", rs.getString("city_name"));
				map.put("loanOutStanding", rs.getString("loanoutstanding"));
				map.put("loanDue", rs.getString("monthly_pay"));
				map.put("loanPaid", rs.getString("total_paid"));
				map.put("status", rs.getString("status_get"));
				map.put("scheme", rs.getString("installment_scheme"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getDoLoanBookSearchCount(int doId, String search) {
		int count = 0;

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con.prepareCall("{CALL get_do_loan_books_search_count(?,?)}");
			callst.setInt(1, doId);
			callst.setString(2, search);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int getDoLoanBooksCount(int doId) {
		int count = 0;

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con.prepareCall("{CALL get_do_loan_books_count(?)}");
			callst.setInt(1, doId);

			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static void main(String args[]) {
		System.out.println(getDoLoanBooksCount(103));
	}

}
