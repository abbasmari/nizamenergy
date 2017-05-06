/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 *
 * @author AqeelRahu
 */
public class GraceBAL {
	final static Logger logger = Logger.getLogger(GraceBAL.class);

	public static int getGracePeriod(int loanId) {
		int gracePeriod = 0;
		String query = "Select due_date from customer_installments WHERE paid_date > due_date AND payment_loan_id=?";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, loanId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				gracePeriod++;
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return gracePeriod;
	}

}
