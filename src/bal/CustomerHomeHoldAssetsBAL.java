package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerHomeHoldAssetsBAL {
	
	final static Logger logger = Logger.getLogger(CustomerHomeHoldAssetsBAL.class);
	
	public int insertCustomerBusinessInfo(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_home_hold_assets(?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setBoolean(2, Boolean.parseBoolean(map.get("home")));
       	 	prepareCall.setBoolean(3, Boolean.parseBoolean(map.get("car")));
       	 	prepareCall.setBoolean(4, Boolean.parseBoolean(map.get("motorcycle")));
       	 	prepareCall.setBoolean(5, Boolean.parseBoolean(map.get("washingMachine")));
       	 	prepareCall.setBoolean(6, Boolean.parseBoolean(map.get("tv")));
       	 	prepareCall.setBoolean(7, Boolean.parseBoolean(map.get("computer")));
       	 	prepareCall.setBoolean(8, Boolean.parseBoolean(map.get("fridge")));
	           prepareCall.setString(9, map.get("others"));
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
