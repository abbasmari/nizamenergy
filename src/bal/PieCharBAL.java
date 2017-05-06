/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.PieCharBean;

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
public class PieCharBAL {
	final static Logger logger = Logger.getLogger(PieCharBAL.class);
	
	public static ArrayList<PieCharBean> getApplianceCounter() {
		 
		 ResultSet rs = null;
		  
		PieCharBean bean = null;
		ArrayList<PieCharBean> customerList = new ArrayList<PieCharBean>();
		int sixWott;
		int thirtyWott;

		int sixtyWott;

		try (Connection con=Connect.getConnection()){
			
			String query = "select distinct(CountApplianceSuperAdmin('50 W')),CountApplianceSuperAdmin('80 W'),CountApplianceSuperAdmin('100 W')";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sixWott = rs.getInt(1);
				thirtyWott = rs.getInt(2);
				sixtyWott = rs.getInt(3);

				bean = new PieCharBean();
				bean.setTwentyWottCount(sixWott);
				bean.setThirtyWottCount(thirtyWott);

				bean.setSixyWottCount(sixtyWott);

				customerList.add(bean);
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return customerList;

	}

	public static ArrayList<PieCharBean> getApplianceCounterDo(int do_id) {
		
		 ResultSet rs = null;
		PieCharBean bean = null;
		ArrayList<PieCharBean> customerList = new ArrayList<PieCharBean>();
		int twentyWott;
		int thirtyWott;

		int sixtyWott;

		try(Connection	con=Connect.getConnection()) {
		
			String query = " select CountDoAppliancePie('6 W',?),CountDoAppliancePie('30 W',?),CountDoAppliancePie('60 W',?);";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);

			stmt.setInt(1, do_id);
			stmt.setInt(2, do_id);
			stmt.setInt(3, do_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				twentyWott = rs.getInt(1);
				thirtyWott = rs.getInt(2);

				sixtyWott = rs.getInt(3);

				bean = new PieCharBean();
				bean.setTwentyWottCount(twentyWott);
				bean.setThirtyWottCount(thirtyWott);

				bean.setSixyWottCount(sixtyWott);

				customerList.add(bean);
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return customerList;

	}

	

}
