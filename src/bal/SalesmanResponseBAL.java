/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.SalesManResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class SalesmanResponseBAL {
	final static Logger logger = Logger.getLogger(SalesmanResponseBAL.class);

	public static SalesManResponse getSalesManResponse() {

		ResultSet rs = null;

		SalesManResponse bean = null;
		int responseId, salesmanId, customerId;
		String message;
		Date responseDate;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT response_id, sales_man_id, customer_id, message, response_date FROM salesman_response;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				responseId = rs.getInt(1);
				salesmanId = rs.getInt(2);
				customerId = rs.getInt(3);
				message = rs.getString(4);
				responseDate = rs.getDate(5);
				bean = new SalesManResponse();
				bean.setResponseId(responseId);
				bean.setCustomerId(customerId);
				bean.setSalesManId(salesmanId);
				bean.setMessage(message);
				bean.setResposeDate(responseDate);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // end of getting all customers form Db


}
