package bal;


import bean.RecoveryGraphBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;


public class RecoveryGraphBAL {

	final static Logger logger = Logger.getLogger(RecoveryGraphBAL.class);
	
	public static RecoveryGraphBean getAllData(int loanid) {
		 
		 ResultSet rs = null;

		RecoveryGraphBean bean = null;
		
		
		String query = "SELECT CumalativeDue(?) AS cumalativeDue, (SELECT SUM(Amount_Paid) FROM loan_payments WHERE "
				+ "loan_id = ?) AS cumalativePaid, ROUND(((SELECT SUM(Amount_Paid) FROM loan_payments WHERE "
				+ "loan_id = ?)*100/CumalativeDue(?))) AS perc, cs.customer_name, p.loan_id,cs.customer_id FROM sold_to s JOIN customer cs ON cs.customer_id = s.customer_id "
				+ " JOIN payment_loan p ON s.sold_to_id = p.soldto_id WHERE p.loan_id = ? limit 1;";
		try(Connection con=Connect.getConnection()) {
			
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanid);
			stmt.setInt(2, loanid);
			stmt.setInt(3, loanid);
			stmt.setInt(4, loanid);
			stmt.setInt(5, loanid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bean = new RecoveryGraphBean();
				bean.setComulativeDue(rs.getDouble(1));
				bean.setCumulativePaid(rs.getDouble(2));
				bean.setPercentage(rs.getDouble(3));
				bean.setCustomerName(rs.getString(4));
				bean.setLoanId(rs.getInt(5));
				bean.setCustomerId(rs.getInt(6));
				
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return bean;
	}
	
	public static RecoveryGraphBean rancking(int loanId, int customerId)
	{
		 
		 ResultSet rs = null;
		RecoveryGraphBean bean = null;
		
		String query = "SELECT CumalativeDue(?) AS cumalativeDue, (SELECT SUM(Amount_Paid) FROM loan_payments WHERE loan_id = ?) AS cumalativePaid, ROUND(((SELECT SUM(Amount_Paid) FROM loan_payments WHERE loan_id = ?)*100/CumalativeDue(?))) AS perc, cs.customer_name, p.loan_id,cs.customer_id FROM sold_to s JOIN customer cs ON cs.customer_id = s.customer_id JOIN payment_loan p ON s.sold_to_id = p.soldto_id WHERE p.loan_id = ? AND cs.customer_id=?";
		
		try(Connection con=Connect.getConnection()) {
			
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
		
			stmt.setInt(1, loanId);
			stmt.setInt(2, loanId);
			stmt.setInt(3, loanId);
			stmt.setInt(4, loanId);
			stmt.setInt(5, loanId);
			stmt.setInt(6, customerId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bean = new RecoveryGraphBean();
				bean.setComulativeDue(rs.getDouble(1));
				bean.setCumulativePaid(rs.getDouble(2));
				bean.setPercentage(rs.getDouble(3));
				bean.setCustomerName(rs.getString(4));
				bean.setLoanId(rs.getInt(5));
				bean.setCustomerId(rs.getInt(6));
			}
		
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return bean;
	}

	
}
