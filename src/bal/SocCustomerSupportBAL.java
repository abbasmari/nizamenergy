package bal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import connection.Connect;
import bean.SocCustomerSupportBean;

public class SocCustomerSupportBAL {

	static Connection con;
	final static Logger logger = Logger.getLogger(SocCustomerSupportBAL.class);

	public static ArrayList<SocCustomerSupportBean> getComplaints() {
		ArrayList<SocCustomerSupportBean> list = new ArrayList<SocCustomerSupportBean>();
		int complaint_id;
		String complaint, appliance_name, appliance_GSMno, user_name;
		Date complaint_date;
		SocCustomerSupportBean bean = null;
		String query = "SELECT cc.complaint_id,cc.complaint_date,cc.complaint,us.user_name,a.appliance_name,a.appliance_GSMno FROM customer_complaint cc JOIN USER us ON  us.user_id = cc.user_id JOIN appliance a ON a.appliance_id=cc.appliance_id WHERE user_type='104'";
		try (Connection con = Connect.getConnection()) {
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				complaint_id = rs.getInt(1);
				complaint_date = rs.getDate(2);
				complaint = rs.getString(3);
				user_name = rs.getString(4);
				appliance_name = rs.getString(5);
				appliance_GSMno = rs.getString(6);

				bean = new SocCustomerSupportBean();
				bean.setAppliance_num(appliance_GSMno);
				bean.setComplaint(complaint);
				bean.setComplaint_date(complaint_date);
				bean.setComplaint_id(complaint_id);
				System.out.println(user_name);
				bean.setUser_name(user_name);
				bean.setAppliance_name(appliance_name);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
