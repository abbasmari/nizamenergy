/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.AlertGraphBean;



/**	
 *
 * @author Jetandar-Khatri
 */
public class AlertGraphBAL {
    
	final static Logger logger = Logger.getLogger(AlertGraphBAL.class);    
    
     public static ArrayList<AlertGraphBean> getAlerts() {
    	 System.out.println("AlertGraphBAL.getAlerts()");
        AlertGraphBean bean = null;
        ArrayList<AlertGraphBean> customerList = new ArrayList<AlertGraphBean>();
        /*connection.Connect.init();*/
       
        int value;
        
        try(Connection con = Connect.getConnection()) {    

        	
            CallableStatement prepareCall1 = con.prepareCall("{CALL get_alerts()}");

            ResultSet rs = prepareCall1.executeQuery();           
//            End Stored Procedure 
            while (rs.next()) {
                value = rs.getInt(1);
                
                bean = new AlertGraphBean();
                bean.setComulative(value);
                
                customerList.add(bean);
            }
            
            
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return customerList;
    
    }
     
     
}