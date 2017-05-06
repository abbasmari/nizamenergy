package bal;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;



import connection.Connect;
import bean.ActiveInActiveGraphDoBean;

public class ActiveInActiveGraphDoBAL {

	final static Logger logger = Logger.getLogger(ActiveInActiveGraphDoBAL.class);
	    	
	     public static ActiveInActiveGraphDoBean getActiveCustomerValue(int do_id) {
	    	ActiveInActiveGraphDoBean bean = null;

	        double value;
	        int active;
	      
	    
	        	
	        
	  		  // made by Jetander
	  		  try(Connection con=Connect.getConnection()) {
	  		   // Begin Procedure Call / Jetander
	  		   CallableStatement prepareCall = con.prepareCall("{call get_do_active_and_percentage_of_appliance(?)}");
	  		   prepareCall.setInt(1, do_id);
	  		  

	  		   ResultSet rs = prepareCall.executeQuery();
	            while (rs.next()) {
	                active = rs.getInt(1);
	                value=rs.getDouble(2);
	                bean = new ActiveInActiveGraphDoBean();
	                bean.setActiveCustomer(active);
	                bean.setActivePercentage(value);
//	                list.add(bean);
	            }
	            
	        } catch (SQLException e) {
	        	logger.error("",e);
	            e.printStackTrace();
	        }
	        return bean;
	    
	    }
	     
	    
	public static void main(String[] argv){
		System.out.println(getActiveCustomerValue(121));
		
	}
	
}
