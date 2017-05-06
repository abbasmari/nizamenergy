package bal;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.FinanceBean;
import bean.TargetBean;
import bean.TargetsCommission;

public class TargetsBAL {

	final static Logger logger = Logger.getLogger(TargetsBAL.class);

	public static List<String> getTargetsDateForSales(int salesmanId) {

		ResultSet rs = null;

		System.out.print("SalesmanBAL.get_salesman_target_dates(?)");
		List<String> endTargetDates = new ArrayList<String>();

		// made by Jetander
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_salesman_target_dates(?)}");
			prepareCall.setInt(1, salesmanId);
			rs = prepareCall.executeQuery();
			int i = 0;
			while (rs.next()) {
				if (rs.getBoolean(3)) {
					i = 1;
				} else {
					i = 0;
				}
				endTargetDates.add(new SimpleDateFormat("yyyy-MM-dd").format(rs
						.getDate(1))
						+ " to "
						+ new SimpleDateFormat("yyyy-MM-dd").format(rs
								.getDate(2)) + " to " + i);
				// System.out.println(new
				// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(1)));
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return endTargetDates;
	}

	public static TargetBean getTargetsBySalesmanId(int salesmanId) {
		System.out.print("TargetBAL.get_targets_of_salesman()");
		TargetBean bean = null;

		ResultSet rs = null;
		// String query =
		// "SELECT * FROM targets t WHERE  t.salesman_id =? ORDER BY t.target_id DESC";

		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = con
						.prepareCall("{call get_targets_of_salesman(?)}");
				prepareCall.setInt(1, salesmanId);
				rs = prepareCall.executeQuery();
				if (rs.next()) {
					bean = new TargetBean();
					bean.setTarget_id(rs.getInt("t.target_id"));
					bean.setSalesman_id(rs.getInt("t.salesman_id"));
					bean.setTotal_target(rs.getInt("t.total_target"));
					bean.setTarget_start_on(rs.getString("t.target_start_on"));
					bean.setTarget_end_on(rs.getString("t.target_end_on"));
					bean.setIs_sales(rs.getInt("t.is_sales"));
					bean.setIs_recovery(rs.getBoolean("t.is_recovery"));

				}
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static int getSalesOfCurrentMonthBySalesmanId(int salesmanId) {

		ResultSet rs = null;
		System.out.print("TargetBAL.get_total_sales_of_current_month()");
		int countSales = 0;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = con
						.prepareCall("{call get_total_sales_of_current_month(?)}");
				prepareCall.setInt(1, salesmanId);
				rs = prepareCall.executeQuery();
				// connection.Connect.init();
				// PreparedStatement stmt = (PreparedStatement)
				// con.prepareStatement(query);
				// stmt.setInt(1, salesmanId);
				// rs = stmt.executeQuery();
				// String salesmanName,dateJoin,startTargetDate,endTargetDate;
				while (rs.next()) {
					// salesmanName=rs.getString(1);
					// dateJoin=rs.getString(2);
					// startTargetDate=rs.getString(3);
					// endTargetDate=rs.getString(4);
					// countSales=rs.getInt(5);
					countSales = rs.getInt(1);
					// bean = new TargetBean();
					// bean.setCustomer_id(customer_id);
					// bean.setSalesmanName(salesmanName);
					// bean.setJoinDate(dateJoin);
					// bean.setTarget_start_on(startTargetDate);
					// bean.setTarget_end_on(endTargetDate);
					// bean.setIs_sales(countSales);
					// bean.setIs_recovery(rs.getBoolean("t.is_recovery"));
				}
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return countSales;
	}

	// public static List<String> getTargetsDateForSales(int salesmanId){
	// List<String> endTargetDates = new ArrayList<String>();
	// String query =
	// "SELECT target_start_on , target_end_on , t.is_sales  FROM targets t WHERE t.salesman_id =? ORDER BY t.target_end_on DESC";
	// try {
	// connection.Connect.init();
	// PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
	// stmt.setInt(1, salesmanId);
	// rs = stmt.executeQuery();
	// System.out.println(rs.getRow());
	// int i=0;
	// while (rs.next()) {
	// if(rs.getBoolean(3)){
	// i=1;
	// }else{
	// i=0;
	// }
	// endTargetDates.add(new
	// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(1))+" to "+new
	// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(2))+" to "+i);
	// //System.out.println(new
	// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(1)));
	// }
	// } catch (SQLException ex) {
	// System.out.printf(ex.getMessage());
	// }
	// return endTargetDates;
	// }

	public static int getBeforeTime(String startDate, String endDate,
			int salesmanId, String applianceName) {
		System.out.print("TargetBAL.get_before_time(?,?,?,?)");

		ResultSet rs = null;
		// connection.Connect.init();
		// String query = "SELECT COUNT( DISTINCT lp.Loan_payment_id) "
		// +" FROM loan_payments lp "
		// +" INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
		// +" INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
		// +" INNER JOIN appliance a ON st.appliance_id = a.appliance_id"
		// + " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
		// + " INNER JOIN customer c ON c.customer_id = lp.Customer_id "
		// +" WHERE st.salesman_id =  "+salesmanId
		// +" AND lp.Paid_Date >=  '"+startDate+"' "
		// + " AND lp.Paid_Date <= '"+endDate+"' "
		// +" AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 3 "
		// +" AND a.appliance_name = '"+applianceName+"' "
		// +" AND lp.is_first_installment=0  ";
		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_before_time(?,?,?,?)}");
			prepareCall.setInt(3, salesmanId);
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setString(4, applianceName);
			rs = prepareCall.executeQuery();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);

			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static int getOnTime(String startDate, String endDate,
			int salesmanId, String applianceName) {

		ResultSet rs = null;
		System.out.print("TargetBAL.get_on_time()");
		// String query = "SELECT COUNT(DISTINCT  lp.Loan_payment_id) "
		// +" FROM loan_payments lp "
		// +" INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
		// +" INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
		// +" INNER JOIN appliance a ON st.appliance_id = a.appliance_id"
		// + " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
		// + " INNER JOIN customer c ON c.customer_id = lp.Customer_id "
		// +" WHERE st.salesman_id =  "+salesmanId
		// +" AND lp.Paid_Date >= '"+startDate+"' "
		// + " AND lp.Paid_Date <= '"+endDate+"' "
		// +" AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 3 AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 0) "
		// +" AND a.appliance_name = '"+applianceName+"' "
		// +" AND lp.is_first_installment=0 ";
		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_on_time(?,?,?,?)}");
			prepareCall.setInt(3, salesmanId);
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setString(4, applianceName);
			rs = prepareCall.executeQuery();
			// connection.Connect.init();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);

			while (rs.next()) {
				i = rs.getInt(1);
				// System.out.println(new
				// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(1)));
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static int getAfterTime(String startDate, String endDate,
			int salesmanId, String applianceName) {

		ResultSet rs = null;
		System.out.print("TargetBAL.get_after_time()");
		// String query = "SELECT COUNT(DISTINCT  lp.Loan_payment_id) "
		// +" FROM loan_payments lp "
		// +" INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
		// +" INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
		// +" INNER JOIN appliance a ON st.appliance_id = a.appliance_id"
		// + " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
		// + " INNER JOIN customer c ON c.customer_id = lp.Customer_id "
		// +" WHERE st.salesman_id =  "+salesmanId
		// +" AND lp.Paid_Date >= '"+startDate+"' "
		// + " AND lp.Paid_Date <= '"+endDate+"' "
		// +" AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 0 AND DATEDIFF(lp.due_date,lp.Paid_Date) > -7)  "
		// +" AND a.appliance_name = '"+applianceName+"' "
		// +" AND lp.is_first_installment=0 ";
		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_after_time(?,?,?,?)}");
			prepareCall.setInt(3, salesmanId);
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setString(4, applianceName);
			rs = prepareCall.executeQuery();
			// connection.Connect.init();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);

			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static int getRecovries(int salesmanId, String startDate,
			String endDate) {

		ResultSet rs = null;
		System.err.println("CustomerBal.get_recoveries_for_salesman(?,?,?)");
		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_recoveries_for_salesman(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, startDate);
			prepareCall.setString(3, endDate);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static ArrayList<TargetsCommission> getApplianceCommissionWithDate(
			String startDate, String endDate, int salesmanId) {

		ResultSet rs = null;
		TargetsCommission bean = null;
		ArrayList<TargetsCommission> list = new ArrayList<TargetsCommission>();
		int amount;
		Date dateGet;

		// String query = "getApplianceCommissionWithDate(?,?,?)";

		/*
		 * String query =
		 * "(SELECT ((COUNT(DISTINCT(lp.Loan_payment_id))*pl.installment_amount_month)*(SELECT before_time_percentage FROM salesman WHERE salesman_id =  "
		 * +salesmanId+ " )) co ,lp.Paid_Date FROM loan_payments lp " +
		 * " INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id " +
		 * " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id " +
		 * " INNER JOIN appliance a ON st.appliance_id = a.appliance_id " +
		 * " INNER JOIN targets t ON t.salesman_id = st.salesman_id " +
		 * "	INNER JOIN customer c ON c.customer_id = lp.Customer_id " +
		 * " WHERE st.salesman_id = " +salesmanId+ " AND lp.Paid_Date >= '"
		 * +startDate+ "' AND lp.Paid_Date <= '" +endDate+
		 * "' AND (a.appliance_name = '50 W') " +
		 * " AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 3 " +
		 * "	AND !(c.created_on >= '" +startDate+ "' ) " +
		 * " AND !(c.created_on <= '" +endDate+ "' ) " +
		 * " GROUP BY lp.Paid_Date) " + " UNION " +
		 * " (SELECT ((COUNT(DISTINCT(lp.Loan_payment_id))*pl.installment_amount_month)*(SELECT before_time_percentage FROM salesman WHERE salesman_id =  "
		 * +salesmanId+ " )) co ,lp.Paid_Date " +
		 * " FROM loan_payments lp INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
		 * + " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id " +
		 * " INNER JOIN appliance a ON st.appliance_id = a.appliance_id " +
		 * " INNER JOIN targets t ON t.salesman_id = st.salesman_id " +
		 * " INNER JOIN customer c ON c.customer_id = lp.Customer_id " +
		 * " WHERE st.salesman_id = " +salesmanId+ " AND lp.Paid_Date >= '"
		 * +startDate+ "' AND lp.Paid_Date <= '" +endDate+
		 * "' AND (a.appliance_name = '80 W') " +
		 * " AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 3 " +
		 * " AND !(c.created_on >=   '" +startDate+ "' ) " +
		 * " AND !(c.created_on <= '" +endDate+ "' ) " +
		 * " GROUP BY lp.Paid_Date ) " + " UNION " +
		 * " (SELECT ((COUNT(DISTINCT(lp.Loan_payment_id))*pl.installment_amount_month)*(SELECT before_time_percentage FROM salesman WHERE salesman_id =  "
		 * +salesmanId+ " )) co ,lp.Paid_Date " +
		 * " FROM loan_payments lp INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
		 * + " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id " +
		 * " INNER JOIN appliance a ON st.appliance_id = a.appliance_id " +
		 * " INNER JOIN customer c ON c.customer_id = lp.Customer_id " +
		 * " INNER JOIN targets t ON t.salesman_id = st.salesman_id WHERE st.salesman_id = "
		 * +salesmanId+ " AND lp.Paid_Date >= '" +startDate+
		 * "' AND lp.Paid_Date <= '" +endDate+
		 * "' AND (a.appliance_name = '100 W') " +
		 * " AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 3 " +
		 * " AND !(c.created_on >=   '" +startDate+ "' ) " +
		 * " AND !(c.created_on <= '" +endDate+ "' ) " +
		 * " GROUP BY lp.Paid_Date) ";
		 */

		try (Connection con = Connect.getConnection()) {
			// PreparedStatement stmt = (PreparedStatement)
			// con.prepareStatement(query);

			CallableStatement prepareCall = con
					.prepareCall("{CALL getApplianceCommissionWithDate(?,?,?)}");
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setInt(3, salesmanId);

			rs = prepareCall.executeQuery();
			// System.out.println(rs.getRow());

			while (rs.next()) {
				System.out.println("result set called");
				// System.out.println("Amount "+rs.getInt(1)+
				// " Date "+rs.getDate(2));
				amount = rs.getInt(1);
				dateGet = rs.getDate(2);
				// endTargetDates.add((rs.getInt(1))+" to "+new
				// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(2)));
				// System.out.println(endTargetDates);
				// i = rs.getInt(1);
				bean = new TargetsCommission();

				bean.setDate(dateGet + "");
				bean.setAmount(amount);
				list.add(bean);
			}
			rs.close();
			con.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<TargetsCommission> getApplianceCommissionWithDateOnTime(
			String startDate, String endDate, int salesmanId) {
		TargetsCommission bean = null;

		ResultSet rs = null;

		ArrayList<TargetsCommission> list = new ArrayList<TargetsCommission>();
		int onTimeGet;
		Date onTimeDate;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL getApplianceCommissionWithDateOnTime(?,?,?)}");
			call.setString(1, startDate);
			call.setString(2, endDate);
			call.setInt(3, salesmanId);
			rs = call.executeQuery();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);
			// System.out.println(rs.getRow());

			while (rs.next()) {
				System.out.println("result set called");
				// System.out.println("Amount "+rs.getInt(1)+
				// " Date "+rs.getDate(2));
				onTimeGet = rs.getInt(1);
				onTimeDate = rs.getDate(2);
				// endTargetDates.add((rs.getInt(1))+" to "+new
				// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(2)));
				// System.out.println(endTargetDates);
				// i = rs.getInt(1);
				bean = new TargetsCommission();
				bean.setOnTime(onTimeGet);
				bean.setDate(onTimeDate + "");
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<TargetsCommission> getApplianceCommissionWithDateAfterTime(
			int salesmanId) {
		TargetsCommission bean = null;

		ResultSet rs = null;
		ArrayList<TargetsCommission> list = new ArrayList<TargetsCommission>();
		int afterTimeGet;
		Date dateGet;

		String query = " (SELECT ((COUNT(DISTINCT(lp.Loan_payment_id))*pl.installment_amount_month)*(SELECT after_time_percentage FROM salesman WHERE salesman_id =  "
				+ salesmanId
				+ " )) ontime ,lp.Paid_Date FROM loan_payments lp "
				+ " INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
				+ " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
				+ " INNER JOIN appliance a ON st.appliance_id = a.appliance_id "
				+ " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
				+ "	INNER JOIN customer c ON c.customer_id = lp.Customer_id "
				+ " WHERE st.salesman_id = "
				+ salesmanId
				+ " AND (a.appliance_name = '50 W') "
				+ " AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 0 AND DATEDIFF(lp.due_date,lp.Paid_Date) > -7) "
				+ " GROUP BY lp.Paid_Date) "
				+ " UNION "
				+ " (SELECT ((COUNT(DISTINCT(lp.Loan_payment_id))*pl.installment_amount_month)*(SELECT after_time_percentage FROM salesman WHERE salesman_id =  "
				+ salesmanId
				+ " )) ontime ,lp.Paid_Date "
				+ " FROM loan_payments lp INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
				+ " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
				+ " INNER JOIN appliance a ON st.appliance_id = a.appliance_id "
				+ " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
				+ " INNER JOIN customer c ON c.customer_id = lp.Customer_id "
				+ " WHERE st.salesman_id = "
				+ salesmanId
				+ " AND (a.appliance_name = '80 W') "
				+ " AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 0 AND DATEDIFF(lp.due_date,lp.Paid_Date) > -7) "
				+ " GROUP BY lp.Paid_Date ) "
				+ " UNION "
				+ " (SELECT ((COUNT(DISTINCT(lp.Loan_payment_id))*pl.installment_amount_month)*(SELECT after_time_percentage FROM salesman WHERE salesman_id =  "
				+ salesmanId
				+ " )) ontime ,lp.Paid_Date "
				+ " FROM loan_payments lp INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
				+ " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
				+ " INNER JOIN appliance a ON st.appliance_id = a.appliance_id "
				+ " INNER JOIN customer c ON c.customer_id = lp.Customer_id "
				+ " INNER JOIN targets t ON t.salesman_id = st.salesman_id WHERE st.salesman_id = "
				+ salesmanId
				+ " AND (a.appliance_name = '100 W') "
				+ " AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 0 AND DATEDIFF(lp.due_date,lp.Paid_Date) > -7) "
				+ " GROUP BY lp.Paid_Date) ";

		try (Connection con = Connect.getConnection()) {

			// CallableStatement prepareCall =
			// con.prepareCall("{CALL getApplianceCommissionWithDateAfterTime(?,?,?)}");
			// prepareCall.setString(1, startDate);
			// prepareCall.setString(2, endDate);
			// prepareCall.setInt(3, salesmanId);
			Statement stmt = (Statement) con.createStatement();
			// rs = prepareCall.executeQuery();
			rs = stmt.executeQuery(query);
			// System.out.println(rs.getRow());

			while (rs.next()) {
				System.out.println("result set called");
				// System.out.println("Amount "+rs.getInt(1)+
				// " Date "+rs.getDate(2));
				afterTimeGet = rs.getInt(1);
				dateGet = rs.getDate(2);
				// endTargetDates.add((rs.getInt(1))+" to "+new
				// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(2)));
				// System.out.println(endTargetDates);
				// i = rs.getInt(1);
				bean = new TargetsCommission();
				bean.setAfterTime(afterTimeGet);
				bean.setDate(dateGet + "");
				list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<TargetsCommission> getApplianceCommissionWithDateCombine(
			String startDate, String endDate, int salesmanId) {

		ResultSet rs = null;
		ArrayList<TargetsCommission> list = new ArrayList<TargetsCommission>();
		int afterTimeGet;
		int withTime;
		int onTime;
		Date date;
		Date onTimeDate;
		Date afterTimeDate;

		try (Connection con = Connect.getConnection()) {

			CallableStatement prepareCall = con
					.prepareCall("{CALL getApplianceCommissionWithDateAfterTime(?,?,?)}");
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setInt(3, salesmanId);

			rs = prepareCall.executeQuery();

			while (rs.next()) {
				TargetsCommission bean = new TargetsCommission();
				System.out.println("result set called");
				afterTimeGet = rs.getInt(1);
				afterTimeDate = rs.getDate(2);
				bean.setAfterTime(afterTimeGet);
				if (afterTimeDate != null) {
					bean.setDate(afterTimeDate + "");
				}
				list.add(bean);
			}

			CallableStatement prepareCall2 = con
					.prepareCall("{CALL getApplianceCommissionWithDate(?,?,?)}");
			prepareCall2.setString(1, startDate);
			prepareCall2.setString(2, endDate);
			prepareCall2.setInt(3, salesmanId);

			rs = prepareCall2.executeQuery();

			while (rs.next()) {
				TargetsCommission bean = new TargetsCommission();
				System.out.println("result set called");
				withTime = rs.getInt(1);
				date = rs.getDate(2);
				bean.setAmount(withTime);
				if (date != null) {
					bean.setDate(date + "");
				}
				list.add(bean);
			}

			CallableStatement prepareCall3 = con
					.prepareCall("{CALL getApplianceCommissionWithDateOnTime(?,?,?)}");
			prepareCall3.setString(1, startDate);
			prepareCall3.setString(2, endDate);
			prepareCall3.setInt(3, salesmanId);

			rs = prepareCall3.executeQuery();

			while (rs.next()) {
				TargetsCommission bean = new TargetsCommission();
				System.out.println("result set called");
				onTime = rs.getInt(1);
				onTimeDate = rs.getDate(2);
				bean.setOnTime(onTime);
				if (onTimeDate != null) {
					bean.setDate(onTimeDate + "");
				}
				list.add(bean);
			}

			System.out.println("list of bean " + list);

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<TargetsCommission> getDateOfSalesman(int customerId) {
		TargetsCommission bean = null;

		ResultSet rs = null;
		ArrayList<TargetsCommission> list = new ArrayList<>();
		ArrayList<TargetsCommission> list2 = new ArrayList<>();
		ArrayList<TargetsCommission> list3 = new ArrayList<>();
		String dateCachedValue = "";
		int btCached = 0;
		int otCached = 0;
		int atCached = 0;
		Date date;
		int bt = 0;
		int ot = 0;
		int at = 0;
		boolean cached = false;

		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL getDateDurationOfCustomer(?)}");
			call.setInt(1, customerId);
			rs = call.executeQuery();

			while (rs.next()) {
				bean = new TargetsCommission();
				date = rs.getDate(1);
				bt = rs.getInt(2);
				ot = rs.getInt(3);
				// bt = 1000;
				// ot = 5000;
				at = rs.getInt(4);
				// System.out.println("OT = "+ot+" BT"+bt);
				bean.setDate(date + "");
				bean.setAmount(bt);
				bean.setOnTime(ot);
				bean.setAfterTime(at);
				System.out.println(bean.getAmount());
				list.add(bean);
				// System.out.println();
			}

			boolean b = false;
			String dateGet = "";
			int beforeTimeGet = 0;
			int onTimeGet = 0;
			int afterTimeGet = 0;
			for (int x = 0; x < list.size() - 1; x++) {
				String abc = list.get(x).getDate();
				b = false;
				for (int z = 0; z < list.size() - 1; z++) {

					if (abc.equals(list.get(z + 1).getDate())) {
						b = true;
						dateGet = (list.get(z).getDate());
						beforeTimeGet = beforeTimeGet
								+ ((list.get(z).getAmount()) + (list.get(z + 1)
										.getAmount()));
						onTimeGet = onTimeGet
								+ ((list.get(z).getOnTime()) + (list.get(z + 1)
										.getOnTime()));
						afterTimeGet = afterTimeGet
								+ ((list.get(z).getAfterTime()) + (list
										.get(z + 1).getAfterTime()));
						list.remove(z);
						/*
						 * bean = new TargetsCommission();
						 * bean.setDate(dateGet); bean.setAmount(beforeTimeGet);
						 * bean.setOnTime(onTimeGet);
						 * bean.setAfterTime(afterTimeGet); list2.add(bean);
						 */

					}
				}
				if (b) {
					bean = new TargetsCommission();
					bean.setDate(dateGet);
					bean.setAmount(beforeTimeGet);
					bean.setOnTime(onTimeGet);
					bean.setAfterTime(afterTimeGet);
					list2.add(bean);
				}
			}

			for (int w = 0; w < list2.size(); w++) {

				dateCachedValue = list2.get(w).getDate();
				cached = false;
				for (int e = 1; e < list2.size(); e++) {

					if (dateCachedValue.equals(list2.get(e).getDate())) {
						cached = true;
						dateCachedValue = list2.get(e).getDate();
						btCached = btCached
								+ (list2.get(e).getAmount() + list2.get(e + 1)
										.getAmount());
						otCached = otCached
								+ (list2.get(e).getOnTime() + list2.get(e + 1)
										.getOnTime());
						atCached = atCached
								+ (list2.get(e).getAfterTime() + list2.get(
										e + 1).getAfterTime());
						list2.remove(e);
					}

					if (cached) {
						bean = new TargetsCommission();
						bean.setDate(dateCachedValue);
						bean.setAmount(btCached);
						bean.setOnTime(otCached);
						bean.setAfterTime(atCached);
						list3.add(bean);
					}

				}

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.println(ex.getMessage());
		}

		return list2;

	}

	public static int updateTarget(int targetId, int units) {
		int row = 0;

		try (Connection con = Connect.getConnection()) {
			PreparedStatement preparedStatement = con
					.prepareStatement("UPDATE targets SET total_target = ?  WHERE target_id = ?");
			preparedStatement.setInt(1, units);
			preparedStatement.setInt(2, targetId);

			row = preparedStatement.executeUpdate();

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static ArrayList<TargetsCommission> getDateDurationOfCustomerWithData(
			int salesmanId, double btGet, double otget, double atGet) {

		ResultSet rs = null;
		ArrayList<TargetsCommission> list = new ArrayList<TargetsCommission>();
		ArrayList<TargetsCommission> list2 = new ArrayList<TargetsCommission>();
		int bt;
		int ot;
		int at;
		int bt_rec;
		int ot_rec;
		int at_rec;
		String dateCached;
		int btCached = 0;
		int otCached = 0;
		int atCached = 0;
		Date date;
		int btr;
		int otr;
		int atr;
		int btrCached;
		int otrCached;
		int atrCached;

		try (Connection con = Connect.getConnection()) {

			CallableStatement prepareCall = con
					.prepareCall("{CALL getDateDurationOfCustomer(?)}");
			prepareCall.setInt(1, salesmanId);

			rs = prepareCall.executeQuery();

			while (rs.next()) {
				TargetsCommission bean = new TargetsCommission();
				// System.out.println("result set called");
				date = rs.getDate(1);
				bt = rs.getInt(2);
				ot = rs.getInt(3);
				at = rs.getInt(4);
				bt_rec = rs.getInt(5);
				ot_rec = rs.getInt(6);
				at_rec = rs.getInt(7);

				bean.setDate(date + "");
				bean.setAfterTime(at);
				bean.setAmount(bt);
				bean.setOnTime(ot);
				bean.setBt_recovery(bt_rec);
				bean.setOt_recovery(ot_rec);
				bean.setAt_recovery(at_rec);
				list.add(bean);
			}

			System.out.println("List 1 " + list);

			CallableStatement prepareCall2 = con
					.prepareCall("{CALL getDateDurationOfCustomerWithData(?,?,?,?)}");
			prepareCall2.setInt(1, salesmanId);
			prepareCall2.setDouble(2, btGet);
			prepareCall2.setDouble(3, otget);
			prepareCall2.setDouble(4, atGet);

			rs = prepareCall2.executeQuery();

			while (rs.next()) {
				TargetsCommission bean = new TargetsCommission();
				// System.out.println("result set called");
				date = rs.getDate(1);
				bt = rs.getInt(2);
				ot = rs.getInt(3);
				at = rs.getInt(4);
				btr = rs.getInt(5);
				otr = rs.getInt(6);
				atr = rs.getInt(7);

				bean.setDate(date + "");
				bean.setAfterTime(at);
				bean.setAmount(bt);
				bean.setOnTime(ot);
				bean.setBt_recovery(btr);
				bean.setOt_recovery(otr);
				bean.setAt_recovery(atr);
				list2.add(bean);

			}

			System.out.println("List 2 " + list2);

			for (int x = 0; x < list.size(); x++) {

				dateCached = list.get(x).getDate();

				for (int z = 0; z < list2.size(); z++) {

					// bean.setDate(dateCached);
					if (dateCached.equals(list2.get(z).getDate())) {
						// list.get(x).setAmount(list2.get(z));

						// System.out.println("cached");

						btCached = list2.get(z).getAmount();
						otCached = list2.get(z).getOnTime();
						atCached = list2.get(z).getAfterTime();
						btrCached = list2.get(z).getBt_recovery();
						otrCached = list2.get(z).getOt_recovery();
						atrCached = list2.get(z).getAt_recovery();

						// bean.setDate(dateCached);
						// bean.setAmount(btCached);
						// bean.setOnTime(otCached);
						// bean.setAfterTime(atCached);
						// System.out.println("BT Time "+btCached+" OT Time"+otCached+" AT Time"+atCached);
						list.get(x).setAmount(btCached);
						list.get(x).setOnTime(otCached);
						list.get(x).setAfterTime(atCached);
						list.get(x).setBt_recovery(btrCached);
						list.get(x).setOt_recovery(otrCached);
						list.get(x).setAt_recovery(atrCached);

						// list3.add(bean);

					}
				}

				// list3.add(bean);

			}

			System.out.println("list 3 " + list);

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return list;
	}

	public static int getBeforeTimeForFO(String startDate, String endDate,
			int foId, String applianceName) {
		System.out.print("TargetBAL.get_before_time_for_fo(?,?,?,?)");

		ResultSet rs = null;

		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_before_time_for_fo(?,?,?,?)}");
			prepareCall.setInt(3, foId);
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setString(4, applianceName);
			rs = prepareCall.executeQuery();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);

			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static int getOnTimeForFo(String startDate, String endDate,
			int foId, String applianceName) {

		ResultSet rs = null;
		System.out.print("TargetBAL.get_on_time_for_fo()");

		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_on_time_for_fo(?,?,?,?)}");
			prepareCall.setInt(3, foId);
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setString(4, applianceName);
			rs = prepareCall.executeQuery();
			// connection.Connect.init();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);

			while (rs.next()) {
				i = rs.getInt(1);
				// System.out.println(new
				// SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(1)));
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static int getAfterTimeForFo(String startDate, String endDate,
			int foId, String applianceName) {

		ResultSet rs = null;
		System.out.print("TargetBAL.get_after_time_for_fo()");

		int i = 0;
		try (Connection con = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = con
					.prepareCall("{call get_after_time_for_fo(?,?,?,?)}");
			prepareCall.setInt(3, foId);
			prepareCall.setString(1, startDate);
			prepareCall.setString(2, endDate);
			prepareCall.setString(4, applianceName);
			rs = prepareCall.executeQuery();
			// connection.Connect.init();
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);

			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return i;
	}

	public static void main(String arg[]) {
		System.out.println(getTargetsBySalesmanId(5));
	}

}
