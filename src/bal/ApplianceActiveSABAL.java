package bal;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import connection.Connect;
import bean.ApplianceActiveSABean;

public class ApplianceActiveSABAL {
	
	
	final static Logger logger = Logger.getLogger(ApplianceActiveSABAL.class);
	
     public static ArrayList<ApplianceActiveSABean> getApplianceCounterGraph() {
    	
         ResultSet rs = null;
    	 ApplianceActiveSABean bean = null;
        ArrayList<ApplianceActiveSABean> customerList = new ArrayList<ApplianceActiveSABean>();
      double sixWott; 
      double thirtyWott;
      double  sixtyWott;
   
      try(Connection con = Connect.getConnection()) {
            
            String query = "select distinct(CountTwentyAppliance(appliance_id,curdate())),CountThirtyAppliance(appliance_id,curdate()),CountFiftyAppliance(appliance_id,curdate()),CountSixtyAppliance(appliance_id,curdate()) from appliance;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	sixWott=rs.getDouble(1);
            	thirtyWott=rs.getDouble(2);
              
                sixtyWott=rs.getInt(3);
         
                
                bean = new ApplianceActiveSABean();
                
                bean.setSixWottPercentage(sixWott);
                bean.setThirtyWottPercentage(thirtyWott);
                bean.setSixtyWottPercentage(sixtyWott);
                customerList.add(bean);
            }
            
            
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return customerList;
    
    }
     

}
