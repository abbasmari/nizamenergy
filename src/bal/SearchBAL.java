package bal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import connection.Connect;
import bean.SearchBean;

public class SearchBAL {
	static Connection con;
	final static Logger logger = Logger.getLogger(SearchBAL.class);

	public static SearchBean searchByAppNumber(String appnum) {
		String c_name, c_cnic, c_phone, a_name, a_GSMno;
		int c_city, c_id, a_id;
		boolean a_status;
		double pl_amount_paid, a_price;
		Date pl_pade_date, pl_activated_date, pl_due_date;

		SearchBean sbean = null;

		String query = "SELECT cs.customer_name,cs.customer_cnic,cs.customer_phone,cs.customer_city, a.appliance_name,a.appliance_GSMno, a.appliance_status,appliance_price,pl.Amount_Paid,pl.Paid_Date, pl.Activated_Date,pl.due_date,cs.customer_id,a.appliance_id FROM Customer cs JOIN sold_to sld ON cs.customer_id=sld.customer_id JOIN appliance a ON sld.appliance_id=a.appliance_id JOIN  loan_payments pl ON cs.customer_id=pl.Customer_id WHERE a.appliance_GSMno= "
				+ appnum;

		try (Connection con = Connect.getConnection()) {

			Statement ps = (Statement) con.createStatement();
			ResultSet rs = ps.executeQuery(query);
			// ps.setString(1, appnum);
			while (rs.next()) {
				c_name = rs.getString(1);
				c_cnic = rs.getString(2);
				c_phone = rs.getString(3);
				c_city = rs.getInt(4);
				a_name = rs.getString(5);
				a_GSMno = rs.getString(6);
				a_status = rs.getBoolean(7);
				a_price = rs.getDouble(8);
				pl_amount_paid = rs.getDouble(9);
				pl_pade_date = rs.getDate(10);
				pl_activated_date = rs.getDate(11);
				pl_due_date = rs.getDate(12);
				c_id = rs.getInt(13);
				a_id = rs.getInt(14);

				sbean = new SearchBean();
				sbean.setCustomer_name(c_name);
				sbean.setCustomer_cnic(c_cnic);
				sbean.setCustomer_phone(c_phone);
				sbean.setCustomer_city(c_city);
				sbean.setAppliance_name(a_name);
				sbean.setAppliance_GSMno(a_GSMno);
				sbean.setAppliance_status(a_status);
				sbean.setAppliance_price(a_price);
				sbean.setAmount_Paid(pl_amount_paid);
				sbean.setPaid_Date(pl_pade_date);
				sbean.setActivated_Date(pl_activated_date);
				sbean.setDue_date(pl_due_date);
				sbean.setAppliance_id(a_id);
				sbean.setCustomer_id(c_id);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return sbean;
	}

	public static ArrayList<SearchBean> searchByCustomerNumber(String customer) {
		ArrayList<SearchBean> list = new ArrayList();
		String c_name, c_cnic, c_phone, a_name, a_GSMno;
		int c_city, a_id, c_id;
		boolean a_status;
		double pl_amount_paid, a_price;
		Date pl_pade_date, pl_activated_date, pl_due_date;

		SearchBean sbean = null;

		String query = "SELECT cs.customer_id,cs.customer_name,cs.customer_cnic,cs.customer_phone,cs.customer_city, a.appliance_name,a.appliance_GSMno, a.appliance_status,appliance_price,pl.Amount_Paid,pl.Paid_Date, pl.Activated_Date,pl.due_date,a.appliance_id "
				+ "FROM Customer cs" + " JOIN sold_to sld ON cs.customer_id=sld.customer_id "
				+ " JOIN appliance a ON sld.appliance_id=a.appliance_id  "
				+ " JOIN  loan_payments pl ON cs.customer_id=pl.Customer_id"
				+ " JOIN payment_loan lp ON lp.loan_id =pl.loan_id" + " WHERE cs.customer_phone=" + customer
				+ " AND lp.soldto_id = sld.sold_to_id";

		try (Connection con = Connect.getConnection()) {
			Statement ps = (Statement) con.createStatement();
			ResultSet rs = ps.executeQuery(query);
			// ps.setString(1, appnum);
			while (rs.next()) {
				c_id = rs.getInt(1);
				c_name = rs.getString(2);
				c_cnic = rs.getString(3);
				c_phone = rs.getString(4);
				c_city = rs.getInt(5);
				a_name = rs.getString(6);
				a_GSMno = rs.getString(7);
				a_status = rs.getBoolean(8);
				a_price = rs.getDouble(9);
				pl_amount_paid = rs.getDouble(10);
				pl_pade_date = rs.getDate(11);
				pl_activated_date = rs.getDate(12);
				pl_due_date = rs.getDate(13);
				a_id = rs.getInt(14);

				sbean = new SearchBean();
				sbean.setCustomer_name(c_name);
				sbean.setCustomer_cnic(c_cnic);
				sbean.setCustomer_phone(c_phone);
				sbean.setCustomer_city(c_city);
				sbean.setAppliance_name(a_name);
				sbean.setAppliance_GSMno(a_GSMno);
				sbean.setAppliance_status(a_status);
				sbean.setAppliance_price(a_price);
				sbean.setAmount_Paid(pl_amount_paid);
				sbean.setPaid_Date(pl_pade_date);
				sbean.setActivated_Date(pl_activated_date);
				sbean.setDue_date(pl_due_date);

				sbean.setCustomer_id(c_id);
				sbean.setAppliance_id(a_id);
				list.add(sbean);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

	}

}
