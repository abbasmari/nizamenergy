/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;


import bean.TechnicanDetailsBean;





import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;





import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 *
 * @author Jhaman-Khatri
 */
public class TechnicanDetailsBAL {
    
	final static Logger logger = Logger.getLogger(TechnicanDetailsBAL.class);
	
     public static ArrayList<TechnicanDetailsBean> getTechnicianDetail(int tech_id) {
    
 		ResultSet rs=null;
 		 
        TechnicanDetailsBean bean = null;
        ArrayList<TechnicanDetailsBean> list = new ArrayList<>();
        
         String technican_name;
    String ticketNo;
    String elapse_time;
        
    try(Connection  con=Connect.getConnection()) {
        	
            String query = "SELECT technician, elapse_time,ticket_no from alerts where technician_id=? and status!=0;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setInt(1, tech_id);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                technican_name=rs.getString(1);
                elapse_time=rs.getString(2);
                ticketNo=rs.getString(3);
                
                bean = new TechnicanDetailsBean();
                bean.setTechnican_name(technican_name);
                bean.setTechnican_ticketNo(ticketNo);
                bean.setElapse_time(elapse_time);
                
                list.add(bean);

            }
           
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return list;
    } // end of getting all customers form Db`
    
     
    public static void main(String args[])
    {
    
    System.out.println(getTechnicianDetail(12));
    }
}
