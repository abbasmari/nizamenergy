package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerMonthlyExpensesBAL {
	
	final static Logger logger = Logger.getLogger(CustomerMonthlyExpensesBAL.class);
	
	public int insertCustomerEmploymentDetails(HashMap<String, String> map){
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call insert_monthly_home_expenses(?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareCall.setString(2, map.get("gridElectricity"));
	           prepareCall.setString(3, map.get("generator"));
	           prepareCall.setString(4, map.get("ups"));
	           prepareCall.setString(5, map.get("solar"));
	           prepareCall.setString(6, map.get("electricityUsage"));
	           prepareCall.setDouble(7,Double.parseDouble( map.get("electricityExpense")));
	           prepareCall.setString(8, map.get("majorExpenseDescription"));
	           prepareCall.setDouble(9,Double.parseDouble( map.get("majorExpenseAmount")));
	           prepareCall.setDouble(10,Double.parseDouble( map.get("totalExpense")));
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
