package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class CashSale {

	public static boolean enter_cash_sale_oneCommission(int nd_fo_id,
			int nd_fo_type, int tehsil_id, int amount, String customer_name,
			String customer_cnic, String phone, String address,
			String imei_number, String gsm, String appliance_name) {

		String status = null;
		System.out.println("****************One Commission********");
		System.out.println("nd_fo_id    " + nd_fo_id);
		System.out.println("nd_fo_type    " + nd_fo_type);
		System.out.println("tehsil_id    " + tehsil_id);
		System.out.println("amount    " + amount);
		System.out.println("customer_name    " + customer_name);
		System.out.println("phone    " + phone);
		System.out.println("address    " + address);
		System.out.println("imei_number    " + imei_number);
		System.out.println("consumer_number    " + gsm);

		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `Cash_Sale_OneCommission`(?,?,?,?,?, ?,?,?,?,?)}");
			prepareCall.setInt(1, nd_fo_id);
			prepareCall.setInt(2, nd_fo_type);
			prepareCall.setInt(3, amount);
			prepareCall.setString(4, customer_name);
			prepareCall.setString(5, customer_cnic);
			prepareCall.setString(6, phone);
			prepareCall.setString(7, address);
			prepareCall.setString(8, imei_number);
			prepareCall.setString(9, gsm);
			prepareCall.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = prepareCall.executeQuery();
			status = prepareCall.getString(10);
			System.out.println("===============================");
			System.out.println("Status     " + status);
			System.out.println("===============================");
			System.out.println("Result retunr  " + rs.toString());

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (status == "TRUE") {
			return true;
		} else
			return false;
	}

	public static boolean enter_cash_sale_TwoCommission(int fo_id, int nd_id,
			int tehsil_id, int amount, String customer_name,
			String customer_cnic, String phone, String address,
			String imei_number, String gsm, String appliance_name) {
		String status = null;
		System.out.println("****************Two Commission********");
		System.out.println("nd_fo_id    " + fo_id);
		System.out.println("nd_fo_type    " + nd_id);
		System.out.println("tehsil_id    " + tehsil_id);
		System.out.println("amount    " + amount);
		System.out.println("customer_name    " + customer_name);
		System.out.println("phone    " + phone);
		System.out.println("address    " + address);
		System.out.println("imei_number    " + imei_number);
		System.out.println("consumer_number    " + gsm);

		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `Cash_Sale_TwoCommission`(?,?,?,?,?, ?,?,?,?,?)}");
			prepareCall.setInt(1, fo_id);
			prepareCall.setInt(2, nd_id);
			prepareCall.setInt(3, amount);
			prepareCall.setString(4, customer_name);
			prepareCall.setString(5, customer_cnic);
			prepareCall.setString(6, phone);
			prepareCall.setString(7, address);
			prepareCall.setString(8, imei_number);
			prepareCall.setString(9, gsm);
			prepareCall.registerOutParameter(10, java.sql.Types.VARCHAR);

			System.out.println(java.sql.Types.VARCHAR + "  Argument  ");
			ResultSet rs = prepareCall.executeQuery();
			status = prepareCall.getString(10);
			System.out.println("===============================");
			System.out.println("Status     " + status);
			System.out.println("===============================");
			System.out.println("Result retunr  " + rs.toString());

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (status == "TRUE") {
			return true;
		} else
			return false;
	}

	public static boolean enter_cash_sale_OvertheCounter(int fo_id, int nd_id,
			int tehsil_id, int amount, String customer_name,
			String customer_cnic, String phone, String address,
			String imei_number, String gsm, String appliance_name) {
		String status = null;
		System.out.println("****************Two Commission********");
		System.out.println("nd_fo_id    " + fo_id);
		System.out.println("nd_fo_type    " + nd_id);
		System.out.println("tehsil_id    " + tehsil_id);
		System.out.println("amount    " + amount);
		System.out.println("customer_name    " + customer_name);
		System.out.println("phone    " + phone);
		System.out.println("address    " + address);
		System.out.println("imei_number    " + imei_number);
		System.out.println("consumer_number    " + gsm);

		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `Cash_saleOTC`(?,?,?,?,?, ?,?,?,?,?)}");
			prepareCall.setInt(1, fo_id);
			prepareCall.setInt(2, nd_id);
			prepareCall.setInt(3, amount);
			prepareCall.setString(4, customer_name);
			prepareCall.setString(5, customer_cnic);
			prepareCall.setString(6, phone);
			prepareCall.setString(7, address);
			prepareCall.setString(8, imei_number);
			prepareCall.setString(9, gsm);
			prepareCall.registerOutParameter(10, java.sql.Types.VARCHAR);

			System.out.println(java.sql.Types.VARCHAR + "  Argument  ");
			ResultSet rs = prepareCall.executeQuery();
			status = prepareCall.getString(10);
			System.out.println("===============================");
			System.out.println("Status     " + status);
			System.out.println("===============================");
			System.out.println("Result retunr  " + rs.toString());

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (status == "TRUE") {
			return true;
		} else
			return false;
	}

	public static void main(String arg[]) throws SQLException {
		// enter_cash_sale_TwoCommission(int fo_id,int nd_id, int tehsil_id,int
		// amount,String customer_name,String customer_cnic,String phone,String
		// address,String imei_number,String gsm,String appliance_name){

		boolean status = enter_cash_sale_TwoCommission(60, 165, 275, 1,
				"waseem", "45454-45454", "923313754297",
				"Housing Society dada", "45645612", "123123", "p-60");

		System.out.println(status);
	}

}
