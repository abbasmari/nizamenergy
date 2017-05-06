/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.AlertLiveBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import connection.Connect;

public class BAL {

	final static Logger logger = Logger.getLogger(BAL.class);
//    public static ArrayList<AlertLiveBean> getAlertList() {
//    	  ResultSet rs = null;
//    	     Statement s = null;
//    	   
//
//        AlertLiveBean bean = null;
//        
//        ArrayList<AlertLiveBean> list = new ArrayList<>();
//        String query = "SELECT a.alerts_id, s.appliance_id, cs.customer_name, cs.customer_phone, c.city_name, "
//                + "a.pannel_lost,a.connection,a.battery_low,a.panel,a.change_control_lost"
//                + " FROM alerts a INNER JOIN sold_to s ON s.appliance_id = a.appliance_id inner join city c on cs.customer_city=c.city_id INNER JOIN customer cs ON cs.customer_id=s.customer_id;";
//        System.out.println(query);
//       
//        
//        try(Connection con = Connect.getConnection()) {
//            
//            s = connection.Connect.con.createStatement();
//            rs = s.executeQuery(query);
//            while (rs.next()) {
//                bean = new AlertLiveBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),null);
//                list.add(bean);
//            }
//            
//            
//            
//        } catch (SQLException ex) {
//        	logger.error("",ex);
//        	        }
//     return list;
//    }
    
   
}
