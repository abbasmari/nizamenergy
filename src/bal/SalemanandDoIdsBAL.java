package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.SalemanandDoIdsBean;

public class SalemanandDoIdsBAL {

	static Connection con;
	final static Logger logger = Logger.getLogger(SalemanandDoIdsBAL.class);

	public static ArrayList<SalemanandDoIdsBean> getIds() {
		SalemanandDoIdsBean bean = null;
		ArrayList<SalemanandDoIdsBean> customerList = new ArrayList<SalemanandDoIdsBean>();

		int salesmanId;
		int doID;

		try (Connection con = Connect.getConnection()) {

			ResultSet rs = null;
			String query = "SELECT s.salesman_id, d.do_id FROM do_salesman d JOIN salesman s ON s.salesman_id = d.salesman_id;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);

			rs = stmt.executeQuery();
			while (rs.next()) {
				salesmanId = rs.getInt(1);
				doID = rs.getInt(2);

				bean = new SalemanandDoIdsBean();
				bean.setSalesmanId(salesmanId);
				bean.setDoId(doID);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;

	}

}
