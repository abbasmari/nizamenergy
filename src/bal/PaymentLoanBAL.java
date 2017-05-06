/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.PaymentLoanBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 *
 * @author waseem
 */
public class PaymentLoanBAL {
    
	final static Logger logger = Logger.getLogger(PaymentLoanBAL.class);
	
    public static ArrayList<PaymentLoanBean> getPaymentsDetail(){
    	
        ResultSet rs = null;
       
        PaymentLoanBean bean = null;
        ArrayList<PaymentLoanBean> paymentList = new ArrayList<PaymentLoanBean>();
        int grace_period,appliance_id,loan_id;
        double total_amount,remaining_balance,paid_amount;
        String appliance_name,customer_name,customer_phone;
        boolean status;
        try(Connection con=Connect.getConnection()){
        	
                String query = "SELECT DISTINCT(ap.appliance_name) ,ap.appliance_status , cs.customer_name, cs.customer_phone, pl.total_amount,pl.grace_period,pl.payed_installments_amount,(pl.total_amount-pl.payed_installments_amount)AS remaining_amount , ap.appliance_id, pl.loan_id FROM appliance ap \n" +
                                "INNER JOIN sold_to sld ON ap.appliance_id=sld.appliance_id\n" +
                                "INNER JOIN customer cs ON sld.customer_id=cs.customer_id\n" +
                                "INNER JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id";
                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
                rs = stmt.executeQuery();
                while(rs.next()){
                    appliance_name = rs.getString(1);
                    status = rs.getBoolean(2);
                    customer_name = rs.getString(3);
                    customer_phone = rs.getString(4);
                    total_amount = rs.getDouble(5);
                    grace_period = rs.getInt(6);
                    paid_amount = rs.getDouble(7);
                    remaining_balance = rs.getDouble(8);
                    appliance_id=rs.getInt(9);
                    loan_id=rs.getInt(10);
                    bean = new PaymentLoanBean();
                    bean.setAppliances_Number(appliance_name);
                    bean.setStatus(status);
                    bean.setCustomer_name(customer_name);
                    bean.setCustomer_phone(customer_phone);
                    bean.setLoan_amount(total_amount);
                    bean.setRemaining_balance(remaining_balance);
                    bean.setGrace_period(grace_period);
                    bean.setPaid_amount(paid_amount);
                    bean.setLoan_key(loan_id);
                    bean.setAppliance_key(appliance_id);
                    paymentList.add(bean);
                }    
               
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return  paymentList;
    } // end of getting all customers form Db
    
    public static void main(String[] args) {
        System.out.println("payment"+getPaymentsDetail());
    }
    
}
