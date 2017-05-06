package bal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class SalesmanLogin {
	final static Logger logger = Logger.getLogger(SalesmanLogin.class);
	
	public static String checkNumber(String phoneNumber) {
		
		String query = "SELECT s.password FROM salesman s WHERE s.salesman_phone_no = ?";
		String password = "";
		try(Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, phoneNumber);
			System.err.println(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				password = rs.getString(1);
			}
			
		} catch (Exception ex) {
			logger.error("",ex);
			System.out.printf(ex.getMessage());
		}
		return password;
	}

	public static HashMap<String, String> checkPasswrd(String phoneNumber,
			String password) {
		HashMap<String, String> map = null;
		try(Connection con = Connect.getConnection()) {
			CallableStatement cst = con.prepareCall("CALL saleman_login_procedure(?, ?)");
			cst.setString(1, phoneNumber);
			cst.setString(2, password);
			ResultSet rs = cst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			
			String columns[] = new String[metaData.getColumnCount()];
			for(int i = 0; i < columns.length; i++){
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			
			if (rs.next()) {
				map = new HashMap<>();
				for(int i = 0; i < columns.length; i++){
					map.put(columns[i], rs.getString(columns[i]));
				}
//				map.put(columns[columns.length-1], Base64.encode(rs.getBytes(columns[columns.length-1])));
			}
			rs.close();
			cst.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("",ex);
			System.out.printf(ex.getMessage());
		}
		return map;
	}
	
	
	public static int addRegId(String app_reg_id, String salesmanId) {

		String query = "UPDATE salesman SET app_reg_id = ? WHERE salesman_id = ?";
		int row = 0;
		try(Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, app_reg_id);
			ps.setString(2, salesmanId);
			row = ps.executeUpdate();
			System.err.println("row => " + row);
			ps.close();
			con.close();
		} catch (Exception e) {
			logger.error("",e);
			System.err.println(e);
		}
		return row;
	}
}
