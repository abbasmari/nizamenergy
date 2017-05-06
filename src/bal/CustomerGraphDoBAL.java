package bal;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.CustomerGraphDoBean;

public class CustomerGraphDoBAL {
	
	final static Logger logger = Logger.getLogger(CustomerGraphDoBAL.class);	
	 
    public static ArrayList<CustomerGraphDoBean> getCutomersDo(int do_id) {
    	
        ResultSet rs = null;
    	CustomerGraphDoBean bean = null;
       ArrayList<CustomerGraphDoBean> customerList = new ArrayList<CustomerGraphDoBean>();
      
       int value;
      
       try ( Connection  con=Connect.getConnection()){

    	// Begin Procedure Call / Jetander
  		   CallableStatement prepareCall = con.prepareCall("{call get_do_total_customers(?)}");
  		   prepareCall.setInt(1, do_id);
  		 
  		   rs = prepareCall.executeQuery();
           while (rs.next()) {
               value = rs.getInt(1);
               bean = new CustomerGraphDoBean();
               bean.setCount(value);
               customerList.add(bean);
           }
           
       } catch (SQLException e) {
    	   logger.error("",e);
           e.printStackTrace();
       }
       return customerList;
   } // end of getting all customers form Db
}


