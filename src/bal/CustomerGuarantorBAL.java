package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerGuarantorBAL {
	
	final static Logger logger = Logger.getLogger(CustomerGuarantorBAL.class);

	public int insertCustomerGuarantor(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_customer_guarantors(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setString(2, map.get("gguarantorName"));
	           prepareCall.setString(3, map.get("gguarantorFather"));
	           prepareCall.setString(4, map.get("gguarantorCnic"));
	           prepareCall.setString(5, map.get("gguarantorDob"));
	           prepareCall.setString(6, map.get("gRelationToCustomer"));
	           prepareCall.setString(7,map.get("gmarritalStatus"));
	           prepareCall.setString(8, map.get("gguarantorPhone"));
	           prepareCall.setDouble(9, Double.parseDouble(map.get("gguarantorIncome")));
	           prepareCall.setString(10, "92"+map.get("gguarantorPhone"));
	           prepareCall.setString(11, map.get("gDesidition"));
	           
	           prepareCall.setString(12, map.get("gPhoneNumber"));
	           prepareCall.setString(13, map.get("gCompanyAddess"));
	           prepareCall.setString(14, map.get("gBusinessName"));
	           prepareCall.setString(15, map.get("gBusnessType"));
	           prepareCall.setString(16, map.get("gBusnessNumber"));
	           prepareCall.setString(17, map.get("gBusinessAddress"));
	           prepareCall.setInt(18, Integer.parseInt(map.get("guaranterType")));

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
