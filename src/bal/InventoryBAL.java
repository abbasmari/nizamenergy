/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;


import bean.InventoryBean;
import bean.Suggestion;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 *
 * @author Jeatnder Khatri
 */
public class InventoryBAL {

	final static Logger logger = Logger.getLogger(InventoryBAL.class);
	
    public static ArrayList<InventoryBean> getAppliances() {
    	ResultSet rs=null;
        InventoryBean bean = null;
        ArrayList<InventoryBean> list = new ArrayList<>();
        int id;
        String name;
        try(Connection con=Connect.getConnection()) {
        	
            String query = "SELECT id, appliance_name FROM inventory ;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                bean = new InventoryBean();
                bean.setId(id);
                bean.setName(name);
                list.add(bean);
            }
            
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return list;
    } // end of getting all custome

    public static ArrayList<Suggestion> getSuggestion(int customerId) {
    	ResultSet rs=null;
        Suggestion bean = null;
        ArrayList<Suggestion> list = new ArrayList<>();
        int id, scheme;
        String name;
        try (Connection con=Connect.getConnection()){
        	
            String query = "SELECT id, appName,scheme FROM suggestion where customer_id = "+ customerId +";";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                scheme = rs.getInt(3);
                bean = new Suggestion();
                bean.setId(id);
                bean.setAppName(name);
                bean.setScheme(scheme);
                list.add(bean);
            }
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return list;
    } // end of getting all customer

}
