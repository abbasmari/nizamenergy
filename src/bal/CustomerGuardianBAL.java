package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerGuardianBAL {
	
	final static Logger logger = Logger.getLogger(AccessControlBAL.class);

	public int insertCustomerGuardian(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_customer_guardian(?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setString(2, map.get("guardianName"));
	           prepareCall.setString(3, map.get("phoneNo"));
	           prepareCall.setInt(4, Integer.parseInt(map.get("customerFamilySize")));
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
