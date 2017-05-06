/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.soldApplianceGraphBean;
import connection.Connect;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

/**
 *
 * @author Jhaman-Khatri
 */
public class soldApplianceGraphBAL {
	final static Logger logger = Logger.getLogger(soldApplianceGraphBAL.class);
	
	static Statement st = null;
	static ResultSet rs = null;
	static Connection con;
	public static soldApplianceGraphBean getSoldGraphValues() {
//		System.out.println("soldApplianceGraphBAL.getSoldGraphValues()");
		soldApplianceGraphBean bean = new soldApplianceGraphBean();
		// ArrayList<soldApplianceGraphBean> customerList = new
		// ArrayList<soldApplianceGraphBean>();

		double value;
		int productHandover;

		try(Connection con = Connect.getConnection()) {
			if (con != null) {

				// String query = "SELECT (SELECT COUNT(STATUS) FROM eligibility
				// WHERE STATUS = 1) AS accept,(SELECT (SELECT COUNT(STATUS)
				// FROM eligibility WHERE STATUS = 1)*100 /(SELECT COUNT(STATUS)
				// "
				// + "FROM eligibility )) AS acceptance;";
				// PreparedStatement stmt = (PreparedStatement)
				// con.prepareStatement(query);
				// rs = stmt.executeQuery();

				// -----------------------------------------------------------------------------
				// Begin Stored Procedure -- Jeevan

				CallableStatement prepareCall = con.prepareCall("{call get_sold_graph_value()}");
				ResultSet rs = prepareCall.executeQuery();
				// End Stored Procedure

				while (rs.next()) {
					productHandover = rs.getInt(1);
					value = rs.getDouble(2);
					bean.setValue(value);
					bean.setHandover(productHandover);
					// customerList.add(bean);

				}
				
			}
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return bean;

	}
	
	
	
	
	
	public static soldApplianceGraphBean getSoldGraphValuesByDistrict(int districtId) {
//		System.out.println("soldApplianceGraphBAL.getSoldGraphValues()");
		soldApplianceGraphBean bean = new soldApplianceGraphBean();
		// ArrayList<soldApplianceGraphBean> customerList = new
		// ArrayList<soldApplianceGraphBean>();

		double value;
		int productHandover;

		try(Connection con = Connect.getConnection()) {
			if (con != null) {

				// String query = "SELECT (SELECT COUNT(STATUS) FROM eligibility
				// WHERE STATUS = 1) AS accept,(SELECT (SELECT COUNT(STATUS)
				// FROM eligibility WHERE STATUS = 1)*100 /(SELECT COUNT(STATUS)
				// "
				// + "FROM eligibility )) AS acceptance;";
				// PreparedStatement stmt = (PreparedStatement)
				// con.prepareStatement(query);
				// rs = stmt.executeQuery();

				// -----------------------------------------------------------------------------
				// Begin Stored Procedure -- Jeevan

				CallableStatement prepareCall = con.prepareCall("{call get_sold_graph_values_by_districtId(?)}");
				prepareCall.setInt(1, districtId);
				ResultSet rs = prepareCall.executeQuery();
				// End Stored Procedure

				while (rs.next()) {
					productHandover = rs.getInt(1);
					value = rs.getDouble(2);
					bean.setValue(value);
					bean.setHandover(productHandover);
					// customerList.add(bean);

				}
				
			}
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return bean;

	}
	
	
	

	

}
