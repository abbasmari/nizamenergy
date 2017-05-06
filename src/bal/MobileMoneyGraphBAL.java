/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.MobileMoneyGraphBean;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;


public class MobileMoneyGraphBAL {

	final static Logger logger = Logger.getLogger(MobileMoneyGraphBAL.class);

	public static MobileMoneyGraphBean getMobileMoneyGraphValues() {
		ResultSet rs = null;
		System.out.println("MobileMoneyGraphBAL.getMobileMoneyGraphValues()");
		MobileMoneyGraphBean bean = new MobileMoneyGraphBean();
		double value;
		int money;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				// Begin Stored Procedure
				CallableStatement prepareCall = con.prepareCall("{CALL count_sum_of_mobile_money()}");
				rs = prepareCall.executeQuery();
				while (rs.next()) {
					money = rs.getInt(1);
					value = (double) rs.getInt(2);
					bean.setMobileMoney(value);
					bean.setMoney(money);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;

	}

	public static MobileMoneyGraphBean getMobileMoneyGraphValuesByDistrict(int districtId) {
		ResultSet rs = null;
		System.out.println("MobileMoneyGraphBAL.getMobileMoneyGraphValues()");
		MobileMoneyGraphBean bean = new MobileMoneyGraphBean();
		double value;
		int money;

		try (Connection con = Connect.getConnection()) {

			if (con != null) {
				// Begin Stored Procedure
				CallableStatement prepareCall = con.prepareCall("{CALL count_sum_of_mobile_money_by_district(?)}");
				prepareCall.setInt(1, districtId);
				rs = prepareCall.executeQuery();
				// End Stored Procedure

				while (rs.next()) {
					money = rs.getInt(1);
					value = (double) rs.getInt(2);

					bean.setMobileMoney(value);
					bean.setMoney(money);

					// customerList.add(bean);
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;

	}

}
