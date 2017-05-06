/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.WottageGraphBean;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 *
 * @author Jhaman-Khatri
 */
public class WottageGraphBAL {
	final static Logger logger = Logger.getLogger(WottageGraphBAL.class);
	
    static Statement st = null;
    static ResultSet rs = null;
    static Connection con;
     public static ArrayList<WottageGraphBean> getWattage() {
    	 
    	 System.out.println("WottageGraphBAL.getWattage()");
        WottageGraphBean bean = null;
        ArrayList<WottageGraphBean> customerList = new ArrayList<WottageGraphBean>();
       
        int value;
        
        try(Connection  con=Connect.getConnection()) {
//                String query="SELECT (@cum_sum := @cum_sum + entries) as cumulative FROM ( Select CountNewDoAppliance(a.appliance_id,'6 W',d.do_id) as sixappliance,"
//+" CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)  as thirty,"
//+"CountNewDoAppliance(a.appliance_id,'60 W',d.do_id) as sixtyappliance,"
//+"((CountNewDoAppliance(a.appliance_id,'6 W',d.do_id)*6)+(CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)*30)"
//+"+(CountNewDoAppliance(a.appliance_id,'60 W',d.do_id)*60)) as sum ,((CountNewDoAppliance(a.appliance_id,'6 W',d.do_id)*6)+(CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)*30)"
//+"+(CountNewDoAppliance(a.appliance_id,'60 W',d.do_id)*60)) as entries,s.sold_date"
//+"from appliance a"
//+" INNER JOIN  sold_to s  ON a.appliance_id= s.appliance_id"
//+"JOIN salesman sl ON sl.salesman_id = s.salesman_id JOIN do_salesman d ON s.salesman_id = d.salesman_id"
//+" WHERE d.do_id = ?"
//+" group by s.sold_date) e CROSS JOIN (select @cum_sum := 0) param";
//            
//            String query2="select (@cum_sum := @cum_sum + entries) as cumulative FROM ( Select CountAppliance(a.appliance_id,'6 W') as sixappliance,"
//            		+ " CountAppliance(a.appliance_id,'30 W')  as thirty, CountAppliance(a.appliance_id,'60 W') as sixtyappliance,"
//            		+ "((CountAppliance(a.appliance_id,'6 W')*6)+(CountAppliance(a.appliance_id,'30 W')*30)+(CountAppliance(a.appliance_id,'60 W')*60)) as sum ,"
//            		+ "((CountAppliance(a.appliance_id,'6 W')*6)+(CountAppliance(a.appliance_id,'30 W')*30) +(CountAppliance(a.appliance_id,'60 W')*60)) as entries,"
//            		+ "s.sold_date from appliance a INNER JOIN  sold_to s  ON a.appliance_id= s.appliance_id group by s.sold_date) e"
//            		+ " CROSS JOIN (select @cum_sum := 0) param;";
//            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query2);
//            rs = stmt.executeQuery();
            
//            Begin Stored Procedure -- Jeevan
            CallableStatement prepareCall = con.prepareCall("{CALL get_wattage()}");
            ResultSet rs = prepareCall.executeQuery();
//            End Stored Procedure
            while (rs.next()) {
                value = rs.getInt(1);
                
                bean = new WottageGraphBean();
                bean.setSum(value);
             
               
              
                
                
                customerList.add(bean);
            }
           
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return customerList;
    
    }
     
     
     public static ArrayList<WottageGraphBean> getWattageDo(int do_id) {
         WottageGraphBean bean = null;
         ArrayList<WottageGraphBean> customerList = new ArrayList<WottageGraphBean>();
        
         int value;
         
         try(Connection  con=Connect.getConnection()) {
//             String query = "SELECT (CountTenAppliance(a.appliance_id,s.sold_date)) AS count10 "
//             		+ ",(CountTwentyAppliance(a.appliance_id,s.sold_date)) AS counter20 ,"
//             		+ "CountThirtyAppliance(a.appliance_id,s.sold_date) AS count30 ,"
//             		+ "CountFiftyAppliance(a.appliance_id,s.sold_date) AS count50,"
//             		+ "CountSixtyAppliance(a.appliance_id,s.sold_date) AS count60 ,"
//             		+ "(CountTenAppliance(a.appliance_id,s.sold_date)*10)+(CountTwentyAppliance(a.appliance_id,s.sold_date)*20)+(CountThirtyAppliance(a.appliance_id,s.sold_date)*30)+(CountFiftyAppliance(a.appliance_id,s.sold_date)*50)+(CountSixtyAppliance(a.appliance_id,s.sold_date)*60) AS SUM,"
//             		+ "s.sold_date FROM sold_to s  "
//             		+ "INNER JOIN  appliance a  ON a.appliance_id= s.appliance_id "
//             		+ "JOIN salesman sl ON sl.salesman_id = s.salesman_id JOIN do_salesman d ON s.salesman_id = d.salesman_id  WHERE d.do_id = ? GROUP BY s.sold_date;";
//             String query="SELECT (@cum_sum := @cum_sum + entries) as cumulative FROM ( Select CountNewDoAppliance(a.appliance_id,'6 W',d.do_id) as sixappliance,"
//            		 +" CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)  as thirty,"
//            		 +"CountNewDoAppliance(a.appliance_id,'60 W',d.do_id) as sixtyappliance,"
//            		 +"((CountNewDoAppliance(a.appliance_id,'6 W',d.do_id)*6)+(CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)*30)"
//            		 +"+(CountNewDoAppliance(a.appliance_id,'60 W',d.do_id)*60)) as sum ,((CountNewDoAppliance(a.appliance_id,'6 W',d.do_id)*6)+(CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)*30)"
//            		 +"+(CountNewDoAppliance(a.appliance_id,'60 W',d.do_id)*60)) as entries,s.sold_date"
//            		 +"from appliance a"
//            		 +" INNER JOIN  sold_to s  ON a.appliance_id= s.appliance_id"
//            		 +"JOIN salesman sl ON sl.salesman_id = s.salesman_id JOIN do_salesman d ON s.salesman_id = d.salesman_id"
//            		 +" WHERE d.do_id = ?"
//            		 +" group by s.sold_date) e CROSS JOIN (select @cum_sum := 0) param";
             
             String query="SELECT (@cum_sum := @cum_sum + entries) as cumulative FROM "
                     + "( Select CountNewDoAppliance(a.appliance_id,'6 W',d.do_id) as sixappliance,"
                     + " CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)  as thirty,"
                     + " CountNewDoAppliance(a.appliance_id,'60 W',d.do_id) as sixtyappliance,"
                     + "((CountNewDoAppliance(a.appliance_id,'6 W',d.do_id)*6)+(CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)*30) +(CountNewDoAppliance(a.appliance_id,'60 W',d.do_id)*60)) as sum ,"
                     + "((CountNewDoAppliance(a.appliance_id,'6 W',d.do_id)*6)+(CountNewDoAppliance(a.appliance_id,'30 W',d.do_id)*30)+(CountNewDoAppliance(a.appliance_id,'60 W',d.do_id)*60)) as entries,"
                     + "s.sold_date from appliance a  INNER JOIN  "
                     + "sold_to s  ON a.appliance_id= s.appliance_id JOIN "
                     + "salesman sl ON sl.salesman_id = s.salesman_id JOIN"
                     + " do_salesman d ON s.salesman_id = d.salesman_id WHERE d.do_id = ? "
                     + "group by s.sold_date) e CROSS JOIN (select @cum_sum := 0) param";
//             
//             String query2="SELECT (@cum_sum := @cum_sum + entries) as cumulative FROM"
//             		+ " ( Select CountAppliance(a.appliance_id,'6 W') as sixappliance,"
//             		+ "CountAppliance(a.appliance_id,'30 W')  as thirty,"
//             		+ "CountAppliance(a.appliance_id,'60 W') as sixtyappliance,"
//             		+ "((CountAppliance(a.appliance_id,'6 W')*6)+(CountAppliance(a.appliance_id,'30 W')*30)+(CountAppliance(a.appliance_id,'60 W')*60)) as sum ,((CountAppliance(a.appliance_id,'6 W')*6)+(CountAppliance(a.appliance_id,'30 W')*30)+(CountAppliance(a.appliance_id,'60 W')*60)) as entries,"
//             		+ "s.sold_datefrom appliance a INNER JOIN  sold_to s "
//             		+ " ON a.appliance_id= s.appliance_id JOIN salesman sl ON"
//             		+ " sl.salesman_id = s.salesman_id JOIN do_salesman d ON"
//             		+ " s.salesman_id = d.salesman_id WHERE d.do_id = ? group by s.sold_date) e CROSS JOIN (select @cum_sum := 0) param";
             PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
             stmt.setInt(1, do_id);
            
              ResultSet rs = null;
             rs = stmt.executeQuery();
             while (rs.next()) {
                 value = rs.getInt(1);
                 
                 bean = new WottageGraphBean();
                 bean.setSum(value);                 
                 customerList.add(bean);
             }
            
         } catch (SQLException e) {
        	 logger.error("",e);
             e.printStackTrace();
         }
         return customerList;
     
     }
     public static void main(String args[])
     {
         System.err.print(getWattageDo(21));
     }
    
    
    
    
}
