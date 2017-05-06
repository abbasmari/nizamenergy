package bal;


import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import connection.Connect;

public class UpdateCustomerBAL {
	
	
	final static Logger logger = Logger.getLogger(UpdateCustomerBAL.class);
	
	
	public static int UpdateCustomerInfo(String name,String cnic,String dateob,String address,int city,String phone,String phone2, 
			int monthlyIncom,int familyIncom, String father,String mother, String email,String gender,int age,String relation,
			boolean educated,String education,String incomeSource,int familySize,String occupation,String number)
	{
		System.out.println("UpdateCustomerBAL.UpdateCustomerInfo()");
		int row=0;
		try(Connection  con=Connect.getConnection()) {
			CallableStatement prePareCall=(CallableStatement) con.prepareCall("{CALL update_customer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			prePareCall.setString(1, name);
			prePareCall.setString(2, cnic);
			prePareCall.setString(3, dateob);
			prePareCall.setString(4, address);
			prePareCall.setInt(5, city);
			prePareCall.setString(6, phone);
			prePareCall.setString(7, phone2);
			prePareCall.setInt(8, monthlyIncom);
			prePareCall.setInt(9, familyIncom);
			//prePareCall.setInt(10, status);
			prePareCall.setString(10, father);
			prePareCall.setString(11, mother);
	
			prePareCall.setString(12, email);
			prePareCall.setString(13, gender);
			prePareCall.setInt(14, age);
			prePareCall.setString(15, relation);
			prePareCall.setBoolean(16, educated);
			prePareCall.setString(17, education);
			prePareCall.setString(18, incomeSource);
			prePareCall.setInt(19, familySize);
			prePareCall.setString(20, occupation);
			prePareCall.setString(21, number);
			row=prePareCall.executeUpdate();
			if(row>0)
			{
				System.out.println(" customer ki data  Update hogai hy");
			}else
			{
				System.out.println(" ki data update nai ho rai hy ");
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return row;
	}
public static void main(String args[])
{
	UpdateCustomerInfo("Mehran Ali", "41803-0587703-7", "2015-12-30", "Kotri", 21, "923063739933", "923087878933", 2, 4,  "Father", "Mother", "aqeel.rahu@gmmail.com", "Male", 13, "marrie", true, null, "Employee", 0, "Pakoory wala", "923063739933");
}
}
