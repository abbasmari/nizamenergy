package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.DistrictWiseRecoveryBean;

public class DistrictWiseRecoveryBAL {

	final static Logger logger = Logger
			.getLogger(DistrictWiseRecoveryBAL.class);

	public static ArrayList<DistrictWiseRecoveryBean> getDistrictWiseData(
			int do_id) {

		ResultSet rs = null;

		DistrictWiseRecoveryBean bean = null;
		ArrayList<DistrictWiseRecoveryBean> RecoveryGraphBeanList = new ArrayList<>();

		String doName;
		double percentagte;

		String query = "SELECT doName(?), weeklyRecoveryDo(?);";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, do_id);
			stmt.setInt(2, do_id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				doName = rs.getString(1);
				percentagte = rs.getDouble(2);

				bean = new DistrictWiseRecoveryBean();
				bean.setDoName(doName);
				bean.setPercentage(percentagte);
				RecoveryGraphBeanList.add(bean);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return RecoveryGraphBeanList;
	}

}
