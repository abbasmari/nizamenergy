/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.SoldToBean;

/**
 *
 * @author Jeatnder Khatri
 */
public class SoldtoBAL {
//	static Connection con;
	final static Logger logger = Logger.getLogger(SoldtoBAL.class);
	
    public static SoldToBean getSoldInfo(int applianceID) {
    	
		ResultSet rs=null;
		
        SoldToBean bean = null;
        int soldtoId, salesmanId, customerId, applianceId, applianceOption;
        String paymentType ;
        Date soldDate, handoverDate;
        double downPayment;
        boolean state;
        try(Connection con = Connect.getConnection()) {
        	
            String query = "SELECT sold_to_id, customer_id, salesman_id, appliance_id, sold_date, appliance_option, payement_option, product_handover, status,down_payment FROM sold_to where appliance_id =?;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setInt(1, applianceID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                soldtoId = rs.getInt(1);
                customerId= rs.getInt(2);
                salesmanId = rs.getInt(3);
                applianceId = rs.getInt(4);
                soldDate  = rs.getDate(5);
                applianceOption = rs.getInt(6);
                paymentType = rs.getString(7);
                handoverDate = rs.getDate(8);
                state = rs.getBoolean(9);
                downPayment = rs.getDouble(10);
                bean = new SoldToBean();
                bean.setSoldId(soldtoId);
                bean.setCustomerId(customerId);
                bean.setApplianceId(applianceId);
                bean.setSalesManId(salesmanId);
                bean.setSoldDate(soldDate);
                bean.setApplianceOption(applianceOption);
                bean.setPayamentType(paymentType);
                bean.setHandoverDate(handoverDate);
                bean.setState(state);
                bean.setDownPayment(downPayment);
            }
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return bean;
    } // end of getting all customers form Db
    
    public static SoldToBean getSoldInfoById(int soldtoID) {
        SoldToBean bean = null;
        
		ResultSet rs=null;
		
        int soldtoId, salesmanId, customerId, applianceId, applianceOption;
        String  paymentType ;
        Date soldDate, handoverDate;
        double downPayment;
        boolean state;
        try(Connection con = Connect.getConnection()) {
            
            String query = "SELECT sold_to_id, customer_id, salesman_id, appliance_id, sold_date, appliance_option, payement_option, product_handover, status, down_payment FROM sold_to where sold_to_id=?;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setInt(1, soldtoID);
            rs = stmt.executeQuery();
            while (rs.next()) {
               soldtoId = rs.getInt(1);
                salesmanId= rs.getInt(2);
                applianceId = rs.getInt(3);
                customerId = rs.getInt(4);
                soldDate  = rs.getDate(5);
                applianceOption = rs.getInt(6);
                paymentType = rs.getString(7);
                handoverDate = rs.getDate(8);
                state = rs.getBoolean(9);
                downPayment = rs.getDouble(10);
                bean = new SoldToBean();
                bean.setSoldId(soldtoId);
                bean.setCustomerId(customerId);
                bean.setApplianceId(applianceId);
                bean.setSalesManId(salesmanId);
                bean.setSoldDate(soldDate);
                bean.setApplianceOption(applianceOption);
                bean.setPayamentType(paymentType);
                bean.setHandoverDate(handoverDate);
                bean.setState(state);
                bean.setDownPayment(downPayment);
            }
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return bean;
    } // end of getting all customers form Db
    
    public static SoldToBean getSoldInfoByCustomer(int customerID) {
    	
		ResultSet rs=null;
		
        SoldToBean bean = null;
        int soldtoId, salesmanId, customerId, applianceId, applianceOption;
        String  paymentType ;
        Date soldDate, handoverDate;
        double downPayment;
        boolean state;
        try(Connection con = Connect.getConnection()) {
        
            String query = "SELECT sold_to_id, customer_id, salesman_id, appliance_id, sold_date, appliance_option, payement_option, product_handover, status,down_payment FROM sold_to where customer_id=?;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setInt(1, customerID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                soldtoId = rs.getInt(1);
                salesmanId= rs.getInt(2);
                applianceId = rs.getInt(3);
                customerId = rs.getInt(4);
                soldDate  = rs.getDate(5);
                applianceOption = rs.getInt(6);
                paymentType = rs.getString(7);
                handoverDate = rs.getDate(8);
                state = rs.getBoolean(9);
                downPayment = rs.getDouble(10);
                bean = new SoldToBean();
                bean.setSoldId(soldtoId);
                bean.setCustomerId(customerId);
                bean.setApplianceId(applianceId);
                bean.setSalesManId(salesmanId);
                bean.setSoldDate(soldDate);
                bean.setApplianceOption(applianceOption);
                bean.setPayamentType(paymentType);
                bean.setHandoverDate(handoverDate);
                bean.setState(state);
                bean.setDownPayment(downPayment);
            }
           
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return bean;
    } // end of getting all customers form Db
    
    
}
