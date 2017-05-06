package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerBusinessBAL {
	
	final static Logger logger = Logger.getLogger(CustomerBusinessBAL.class);
	
	public int insertCustomerBusinessInfo(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_business_info(?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setString(2, map.get("businessName"));
	           prepareCall.setString(3, map.get("busineesType"));
	           prepareCall.setString(4, map.get("owned"));
	           prepareCall.setString(5, map.get("period"));
	           prepareCall.setString(6, map.get("current_place_period"));
	           prepareCall.setInt(7, Integer.parseInt(map.get("workers")));
	           prepareCall.setString(8, map.get("businessPlace"));
	           prepareCall.setString(9, "92"+map.get("phoneNo"));
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
