package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerOtherLoanBAL {
	
	final static Logger logger = Logger.getLogger(CustomerOtherLoanBAL.class);
	
	public int insertCustomerEmploymentDetails(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_other_loan_details(?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setBoolean(2, Boolean.parseBoolean(map.get("isLoaned")));
	           prepareCall.setString(3, map.get("loanDonner"));
	           prepareCall.setString(4, map.get("loanAmount"));
	           prepareCall.setInt(5, Integer.parseInt(map.get("loanPeriod")));
	           prepareCall.setDouble(6,Double.parseDouble( map.get("monthlyInstallment")));
	           prepareCall.setDouble(7,Double.parseDouble( map.get("remainingPayment")));
	           prepareCall.setBoolean(8, Boolean.parseBoolean(map.get("isBankAccount")));
	           prepareCall.setString(9, map.get("bankName"));
	           ResultSet resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}	
		return id;
	}

}
