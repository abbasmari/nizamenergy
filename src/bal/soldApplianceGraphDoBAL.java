package bal;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;



import connection.Connect;
import bean.soldApplianceGraphDoBean;

public class soldApplianceGraphDoBAL {
	
	final static Logger logger = Logger.getLogger(soldApplianceGraphDoBAL.class);
	
    static Statement st = null;
    static ResultSet rs = null;
    static Connection con;
   public static soldApplianceGraphDoBean getSoldGraphDoValues(int do_id) {
	   soldApplianceGraphDoBean bean = null;
//      ArrayList<soldApplianceGraphDoBean> customerList = new ArrayList<soldApplianceGraphDoBean>();
     
      double value;
      int accepted;
      
//      try {
//          connection.Connect.init();
//          String query = "SELECT (SELECT COUNT(e.status) FROM eligibility e JOIN salesman sl ON sl.salesman_id = e.salesman_id  JOIN do_salesman d ON d.salesman_id = sl.salesman_id "
//          		+ "JOIN user u ON u.user_id = d.do_id WHERE u.user_id = ?) As Accept , doAcceptance(?) AS acceptance;";
//          PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
//         stmt.setInt(1, do_id);
//         stmt.setInt(2, do_id);
//         
//          rs = stmt.executeQuery();
      try(Connection con = Connect.getConnection()) {
	   // Begin Procedure Call / Jetander
	   CallableStatement prepareCall = con.prepareCall("{call get_do_accepted_customers(?)}");
	   prepareCall.setInt(1, do_id);
	   ResultSet rs = prepareCall.executeQuery();
          while (rs.next()) {
              accepted=rs.getInt(1);
              value = rs.getDouble(2);
              bean = new soldApplianceGraphDoBean();
              bean.setPercentage(value);
              bean.setAccepted(accepted);
          }
         
      } catch (SQLException e) {
    	  logger.error("",e);
          e.printStackTrace();
      }
      return bean;
  
  }
   
   

}
