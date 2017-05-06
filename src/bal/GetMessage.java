/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.SMSAnalytics;

/**
 * 
 * @author Jeatnder Khatri
 */
public class GetMessage {

	final static Logger logger = Logger.getLogger(GetMessage.class);

	public static ArrayList<SMSAnalytics> get_Message() {
		String Date;

		ArrayList<SMSAnalytics> list = new ArrayList<SMSAnalytics>();
		SMSAnalytics bean = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT COUNT(*) ,msg_from,DATE(Msg_Date) FROM total_msg_customer_appliance  GROUP BY Msg_Date,Msg_from";
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("As");
				bean = new SMSAnalytics();
				if (rs.getString(2).equals("Mobilink")) {
					bean.setMobilink(rs.getInt(1));
				} else if (rs.getString(2).equals("telenor")) {
					bean.setTelenor(rs.getInt(1));
				} else if (rs.getString(2).equals("telenor")) {
					bean.setTelenor(rs.getInt(1));
				}
				Date = rs.getString(3);

				bean.setDate(Date);

				list.add(bean);

			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<SMSAnalytics> get_FinanceGraph() {
		String Date;

		ArrayList<SMSAnalytics> list = new ArrayList<SMSAnalytics>();
		SMSAnalytics bean = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT SUM(lpp.Amount_Paid) AS Recoverd ,DATE_FORMAT(lpp.paid_date,'%Y-%m-%d') AS paid_date"
					+ ",(SELECT SUM(pl.installment_amount_month) FROM payment_loan pl  JOIN loan_payments lp ON pl.loan_id=lp.loan_id  WHERE lp.due_date=lpp.due_date ) AS expected"
					+ " FROM loan_payments lpp WHERE lpp.Amount_Paid IS NOT NULL AND lpp.is_first_installment= 0 GROUP BY  DATE_FORMAT(lpp.paid_date,'%Y-%m-%d') ORDER BY paid_date ASC ";
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("As");
				bean = new SMSAnalytics();

				bean.setMobilink(rs.getInt("Recoverd"));

				bean.setTelenor(rs.getInt("expected"));

				Date = rs.getString(2);

				bean.setDate(Date);

				list.add(bean);

			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<SMSAnalytics> get_SalesGraph() {
		String Date;

		ArrayList<SMSAnalytics> list = new ArrayList<SMSAnalytics>();
		SMSAnalytics bean = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT COUNT(lpp.loan_id) AS Recoverd ,DATE_FORMAT(lpp.paid_date,'%Y-%m-%d') AS paid_date"
					+ " FROM loan_payments lpp WHERE lpp.Amount_Paid IS NOT NULL AND lpp.is_first_installment=1 GROUP BY  DATE_FORMAT(lpp.paid_date,'%Y-%m-%d') ORDER BY paid_date ASC ";
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("As");
				bean = new SMSAnalytics();
				bean.setMobilink(rs.getInt("Recoverd"));
				Date = rs.getString(2);
				bean.setDate(Date);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<SMSAnalytics> get_FinanceGraph_LoanBook() {
		String Date;

		ArrayList<SMSAnalytics> list = new ArrayList<SMSAnalytics>();
		SMSAnalytics bean = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT Late_Customers(lp.due_date), Defaulter_Customers(lp.due_date), Maintained_Customer(lp.due_date), DATE_FORMAT(lp.due_date,'%Y-%m-%d')"
					+ "FROM appliance a " + "  JOIN sold_to st    USING (appliance_id)"
					+ " JOIN customer cs    USING (customer_id) " + " JOIN city c    ON c.city_id = cs.customer_city "
					+ " JOIN payment_loan pl    ON pl.soldto_id = st.sold_to_id "
					+ " JOIN loan_payments lp USING(loan_id)  "
					+ "GROUP BY EXTRACT(MONTH FROM lp.paid_date ) ORDER BY lp.paid_date ASC";
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("As");
				bean = new SMSAnalytics();
				bean.setUfone(rs.getInt(2));
				bean.setMobilink(rs.getInt(2));
				bean.setTelenor(rs.getInt(3));
				Date = rs.getString(4);
				bean.setDate(Date);
				list.add(bean);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}
	
	public static void main(String[] args) {
		  System.out.print(get_Message());
		  }
		}
