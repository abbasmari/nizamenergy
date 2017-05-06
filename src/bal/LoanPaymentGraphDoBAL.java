package bal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.LoanPaymentGraphDoBean;


public class LoanPaymentGraphDoBAL {
	final static Logger logger = Logger.getLogger(LoanPaymentGraphDoBAL.class);
	
     public static ArrayList<LoanPaymentGraphDoBean> getDOPayementGraphValues(int do_id) {
    	
         ResultSet rs = null;
        
    	 LoanPaymentGraphDoBean bean = null;
        ArrayList<LoanPaymentGraphDoBean> customerList = new ArrayList<LoanPaymentGraphDoBean>();
       
        double value;
        int amount;
        
        
        try(Connection con = Connect.getConnection()){
//            String query = "SELECT distinct(sumOfAmountPaidDo(?)) as amount , CountApplianceSumDo(a.appliance_price,?) as count, (sumOfAmountPaidDo(?)*100)/ (CountApplianceSumDo(a.appliance_price,?)) FROM appliance a ;";
            
//            stmt.setInt(1, do_id);
//            stmt.setInt(2, do_id);
//            stmt.setInt(3, do_id);
//            stmt.setInt(4, do_id);

            
            String query = "SELECT SUM(lp.amount_paid) AS payment ,( "
            		+"((SELECT SUM(lp.Amount_Paid) FROM loan_payments lp "
            		+"INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
            		+"INNER JOIN sold_to st ON pl.soldto_id = st.sold_to_id "
            		+"INNER JOIN salesman sl ON sl.salesman_id = st.salesman_id " 
            		+"INNER JOIN do_salesman ds ON ds.salesman_id = sl.salesman_id "  
            		+"WHERE ds.do_id = ? )*100)/( "
            		+"SELECT SUM(pl.total_amount) FROM payment_loan pl " 
            		+"INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id " 
            		+"INNER JOIN salesman sl ON sl.salesman_id = st.salesman_id " 
            		+"INNER JOIN do_salesman ds ON ds.salesman_id = sl.salesman_id "  
            		+"WHERE ds.do_id = ? "
            		+") ) percentage " 
            		+"FROM loan_payments lp " 
            			+"INNER JOIN payment_loan pl ON pl.loan_id = lp.loan_id "
            			+"INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
            			+"INNER JOIN salesman sl ON sl.salesman_id = st.salesman_id " 
            			+"INNER JOIN do_salesman ds ON ds.salesman_id = sl.salesman_id "  
            	        +"WHERE ds.do_id = ? "
            		+"AND lp.amount_paid IS NOT NULL;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            
            stmt.setInt(1, do_id);
            stmt.setInt(2, do_id);
            stmt.setInt(3, do_id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                amount= rs.getInt(1);
                value = rs.getDouble(2);
                
                bean = new LoanPaymentGraphDoBean();
                bean.setAmount(amount);
                bean.setPercentage(value);
             
               
              
                
                
                customerList.add(bean);
            }
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return customerList;
    
    }
     
//      public static void main(String args[])
//     {
//         System.err.print(getDOPayementGraphValues(54));
//     }
    

}
