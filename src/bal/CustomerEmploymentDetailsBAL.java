package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerEmploymentDetailsBAL {
	
	final static Logger logger = Logger.getLogger(AccessControlBAL.class);
	
	public int insertCustomerEmploymentDetails(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_employment_details(?,?,?,?,?,?,?)}");
			
	           prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
				prepareCall.setString(2, map.get("organisationName"));
		           prepareCall.setString(3, map.get("jobPosition"));
		           prepareCall.setString(4, map.get("jobPeriod"));
		           prepareCall.setInt(5, Integer.parseInt(map.get("salary")));
		           prepareCall.setString(6, "92"+map.get("officePhoneNo"));
		           prepareCall.setString(7, map.get("address"));
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
