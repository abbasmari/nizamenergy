/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.CustomerGraphBean;
import connection.Connect;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

public class CustomerGraphBAL {
    
	final static Logger logger = Logger.getLogger(CustomerGraphBAL.class);
	
      Statement st = null;
     ResultSet rs = null;
    static Connection con;
     public static CustomerGraphBean getCutomers() {
    	 System.out.println("CustomerGraphBAL.getCutomers()");
        CustomerGraphBean bean = null;
        ArrayList<CustomerGraphBean> customerList = new ArrayList<CustomerGraphBean>();
       
        int value;
        
        try (Connection connection = Connect.getConnection()){

            
//            Begin Stored Procedure -- Jeevan
        	
            CallableStatement prepareCall = connection.prepareCall("{CALL get_cummulative_customers2()}");
            ResultSet rs = prepareCall.executeQuery();
            
//            End Stored Procedure
            while (rs.next()) {
                value = rs.getInt(1);
                
                bean = new CustomerGraphBean();
                bean.setCount(value);

                customerList.add(bean);
            }
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return bean;
    } // end of getting all customers form Db
     
     
}



