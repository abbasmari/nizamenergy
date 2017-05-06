package bal;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;



import connection.Connect;
import bean.AlertGraphDoBean;

public class AlertGraphDoBAL {
	final static Logger logger = Logger.getLogger(AlertGraphDoBAL.class);	
	    
	     public static ArrayList<AlertGraphDoBean> getDoAlerts(int do_id) {
	    	 Statement st = null;
	         ResultSet rs = null;
	    	 AlertGraphDoBean bean = null;
	        ArrayList<AlertGraphDoBean> customerList = new ArrayList<AlertGraphDoBean>();
	       
	        int value;
	        
	        try(Connection con = Connect.getConnection()) {    

	            String query2="SELECT (@cum_sum := @cum_sum + entries) as cumulative FROM"
	                    + " (SELECT al.alerts_id  as date, (CountTempHigh(al.alerts_id)+CountLocationChanged(al.alerts_id)+CountPanelLost(al.alerts_id)+CountLidOpen(al.alerts_id)+CountBatteryLow(al.alerts_id)) as entries FROM"
	                    + " alerts al INNER JOIN  appliance a ON a.appliance_id= al.appliance_id Join sold_to t ON a.appliance_id= t.appliance_id JOIN salesman sl ON sl.salesman_id = t.salesman_id  "
	                    + "JOIN do_salesman d ON t.salesman_id = d.salesman_id  WHERE al.alerts_date <= curdate() and d.do_id = ? GROUP BY al.alerts_id) s CROSS JOIN (select @cum_sum := 0) params ";
	                  
	                  
	                  PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query2);
	            stmt.setInt(1, do_id);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                value = rs.getInt(1);
	                
	                bean = new AlertGraphDoBean();
	                bean.setValue(value);
	             
	               
	              
	                
	                
	                customerList.add(bean);
	                
	            }
	            
	          
	            
	        } catch (SQLException e) {
	        	logger.error("",e);
	            e.printStackTrace();
	        }
	        return customerList;
	    
	    }
	     
	    

}
