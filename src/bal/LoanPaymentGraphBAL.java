/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.LoanPaymentGraphBean;




import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 *
 * @author Jhaman-Khatri
 */
public class LoanPaymentGraphBAL {
	final static Logger logger = Logger.getLogger(LoanPaymentGraphBAL.class);
	
	public static LoanPaymentGraphBean getPayementGraphValues() {
		System.out.println("LoanPaymentGraphBAL.getPayementGraphValues()");
		LoanPaymentGraphBean bean = new LoanPaymentGraphBean();
		// ArrayList<LoanPaymentGraphBean> customerList = new
		// ArrayList<LoanPaymentGraphBean>();
		 
		double value;
		int amount;

		try(Connection con = Connect.getConnection()){
			if (con != null) {

				// String query = "SELECT distinct(sumOfAmountPaid()) as amount
				// , CountApplianceSum(a.appliance_price) as count,
				// (sumOfAmountPaid()*100)/
				// (CountApplianceSum(a.appliance_price)) FROM appliance a ;";
				CallableStatement stmt = con.prepareCall("{call get_payment_graph_value2()}");
				ResultSet resultSet = stmt.executeQuery();

				while (resultSet.next()) {
					amount = resultSet.getInt(1);
					value = resultSet.getDouble(2);

					bean.setAmount(amount);
					bean.setPayment(value);

					// customerList.add(bean);
				}
			}
				
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return bean;

	}
	
	
	
	
	public static LoanPaymentGraphBean getPayementGraphValuesByDistrict(int districtId) {
		System.out.println("LoanPaymentGraphBAL.getPayementGraphValues()");
		LoanPaymentGraphBean bean = new LoanPaymentGraphBean();
		// ArrayList<LoanPaymentGraphBean> customerList = new
		// ArrayList<LoanPaymentGraphBean>();
		 
		double value;
		int amount;

		try(Connection con =Connect.getConnection()){
			if (con != null) {

				// String query = "SELECT distinct(sumOfAmountPaid()) as amount
				// , CountApplianceSum(a.appliance_price) as count,
				// (sumOfAmountPaid()*100)/
				// (CountApplianceSum(a.appliance_price)) FROM appliance a ;";
				CallableStatement stmt = con.prepareCall("{call get_payment_graph_value_by_district(?)}");
				stmt.setInt(1, districtId);
				ResultSet resultSet = stmt.executeQuery();

				while (resultSet.next()) {
					amount = resultSet.getInt(1);
					value = resultSet.getDouble(2);

					bean.setAmount(amount);
					bean.setPayment(value);

					// customerList.add(bean);
				}
				con.close();
			}
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return bean;

	}
	

	public static void main(String args[]) {
		System.err.print(getPayementGraphValuesByDistrict(504));
	}

}
