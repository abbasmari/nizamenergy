/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import static bal.UserBAL.st;
import bean.updategrace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import connection.Connect;

/**
 *
 * @author waseem
 */
public class timeforupdate extends TimerTask {
	
	final static Logger logger = Logger.getLogger(timeforupdate.class);
	
	Date now;

	@Override
	public void run() {

		updategrace bean = null;
		ArrayList<updategrace> paymentList = new ArrayList<updategrace>();

		Calendar calendardue_date = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
		Calendar activatedate = new GregorianCalendar(2009, 11, 31, 23, 30, 0);

		Date sold_date = new Date();
		Date currdate = new Date();
		activatedate.setTime(currdate);
		calendardue_date.setTime(sold_date);

		int loan_id;
		Date due_date;
		int grace_period;

		String query = "SELECT DISTINCT(pl.loan_id) AS idd  ,(SELECT GetDue_Date(idd)) AS due_date,lp.grace_period FROM loan_payments pl\n"
				+ "JOIN payment_loan lp ON pl.loan_id=lp.loan_id";

		try (Connection con = Connect.getConnection()) {
			Statement stmt = (Statement) con.prepareStatement(query);
			ResultSet rs = null;
			System.out.print(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				loan_id = rs.getInt(1);
				due_date = rs.getDate(2);
				grace_period = rs.getInt(3);
				bean = new updategrace();
				bean.setLoan_id(loan_id);
				bean.setDue_date(due_date);
				bean.setGrace(grace_period);
				paymentList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		for (int i = 0; i < paymentList.size(); i++) {

			int days = 0;
			if (paymentList.get(i).getDue_date().compareTo(currdate) < 0) {
				long diff = currdate.getTime() - paymentList.get(i).getDue_date().getTime();
				days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			}

			String queryupdate = "UPDATE payment_loan SET grace_period=" + (paymentList.get(i).getGrace() - days)
					+ " WHERE loan_id=" + paymentList.get(i).getLoan_id();
			int rowupdate = 0;
			try (Connection con = Connect.getConnection()) {
				st = con.createStatement();
				rowupdate = st.executeUpdate(queryupdate);
				if (rowupdate > 0) {
					System.out.println("Data is updated");
				} else {
					System.out.println("Data is not updated");
				}
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}

		}

	}

}
