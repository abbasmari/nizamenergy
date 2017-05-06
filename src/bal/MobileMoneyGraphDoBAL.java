package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.MobileMoneyGraphDoBean;

public class MobileMoneyGraphDoBAL {
	final static Logger logger = Logger.getLogger(MobileMoneyGraphDoBAL.class);

	public static ArrayList<MobileMoneyGraphDoBean> getMobileMoneyGraphDo(
			int do_id) {

		ResultSet rs = null;

		MobileMoneyGraphDoBean bean = null;
		ArrayList<MobileMoneyGraphDoBean> customerList = new ArrayList<MobileMoneyGraphDoBean>();

		double value;
		int money;

		try (Connection con = Connect.getConnection()) {
			String query = "select distinct(CountSumofMobileMoneyDo(?)),(CountSumofMobileMoneyDo(?)/CountSumofTotalMoneyDo(?))*100 as percentage  from  payments_details l;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, do_id);
			stmt.setInt(2, do_id);
			stmt.setInt(3, do_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				money = rs.getInt(1);
				value = rs.getDouble(2);

				bean = new MobileMoneyGraphDoBean();
				bean.setMoney(money);
				bean.setMobileMoneyPercentage(value);

				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;

	}

}
