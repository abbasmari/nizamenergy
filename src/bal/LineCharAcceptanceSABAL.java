package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.LineCharAcceptanceSABean;

public class LineCharAcceptanceSABAL {

	final static Logger logger = Logger
			.getLogger(LineCharAcceptanceSABAL.class);

	public static ArrayList<LineCharAcceptanceSABean> getLineChartValue() {

		ResultSet rs = null;
		LineCharAcceptanceSABean bean = null;
		ArrayList<LineCharAcceptanceSABean> customerList = new ArrayList<LineCharAcceptanceSABean>();
		int count;
		String doName;
		double percentage;

		try (Connection con = Connect.getConnection()) {

			String query = "SELECT COUNT(el.STATUS), u.user_name as do , (t.sold_date) ,COUNT(el.STATUS)*100/countDistrictAcceptace() as percentage "
					+ "FROM eligibility el join sold_to t on el.customer_id=t.customer_id join  do_salesman d on  d.salesman_id=t.salesman_id "
					+ "join do_salesman do on do.salesman_id=t.salesman_id "
					+ "join user u on u.user_id=do.do_id WHERE el.STATUS = 1 and t.sold_date<=curdate() group by  CONCAT(YEAR(t.sold_date), '/', WEEK(t.sold_date));";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
				doName = rs.getString(2);
				percentage = rs.getDouble(4);

				bean = new LineCharAcceptanceSABean();
				bean.setCount(count);
				bean.setDoName(doName);
				bean.setPercentage(percentage);

				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;

	}

}
