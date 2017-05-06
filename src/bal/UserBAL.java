package bal;

import bean.UserBean;
import bean.doBean;
import bean.doBeansalesman;
import connection.Connect;

import com.mysql.jdbc.Connection;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import security.Hashing;

public class UserBAL {

	final static Logger logger = Logger.getLogger(UserBAL.class);

	public static enum Mode {

		ALPHA, ALPHANUMERIC, NUMERIC
	}

	static Statement st = null;
	static ResultSet rs = null;
	static Connection con = null;

	public static int addNock(String userName, String email, String password,
			String cnicNo, int user_type, String gender, Double basic_salary,
			String phone, String address) {

		String query = "INSERT INTO user(user_name,user_email, user_password, user_cnic,user_type, user_gender,user_basic_salary,user_phone,user_address)"
				+ "Values('"
				+ userName
				+ "','"
				+ email
				+ "','"
				+ password
				+ "','"
				+ cnicNo
				+ "',"
				+ user_type
				+ ",'"
				+ gender
				+ "',"
				+ basic_salary + ",'" + phone + "','" + address + "')";
		System.out.println("Query : " + query);
		int row = 0;
		try (Connection con = Connect.getConnection();
				Statement st = con.createStatement();) {

			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted12");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	} // end of addUser

	public static UserBean checkUser(String email, String pasword) {
		UserBean bean = null;
		String pass = Hashing.getMD5(pasword);
		try (Connection connection = Connect.getConnection();) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL check_user(?, ?)}");
				prepareCall.setString(1, email);
				prepareCall.setString(2, pass);
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					int userId = resultSet.getInt(1);
					String userName = resultSet.getString(2);
					String emailId = resultSet.getString(3);
					String password = resultSet.getString(4);
					String cnicNo = resultSet.getString(5);
					int userType = resultSet.getInt(6);
					String gender = resultSet.getString(7);
					String lastLogin = resultSet.getString(8);
					int do_district = resultSet.getInt(9);
					String user_district_name = resultSet.getString(10);
					bean = new UserBean();
					bean.setUserId(userId);
					bean.setUserName(userName);
					bean.setEmail(email);
					bean.setPassword(password);
					bean.setCnicNo(cnicNo);
					bean.setUserType(userType);
					bean.setGender(gender);
					bean.setUserLastLogin(lastLogin);
					bean.setUser_district(do_district);
					bean.setUser_district_name(user_district_name);
				}
			}
		} catch (SQLException e) {
			logger.error("UserBAL.checkUser()", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static int deleteUser(UserBean bean) {
		String query = "Delete FROM user WHERE user_id = " + bean.getUserId();
		Statement s = null;
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been DELETED");
			} else {
				System.out.println("Data has not been DELETD");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	} // end of UserBean

	public static boolean checkUserEmail(String email) {

		System.out.print(email);

		boolean result = false;
		try (Connection con = Connect.getConnection(); Statement st = null;) {
			ResultSet rs = null;
			String query = "SELECT * FROM user WHERE user_email=?";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			System.out.println(query);
			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static String generateRandomString(int length, int a)
			throws Exception {

		StringBuffer buffer = new StringBuffer();
		String characters = "";
		switch (a) {

		case 1:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case 2:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case 3:
			characters = "1234567890";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public static int updateUserPassword(String password, String email) {

		String query = "Update user SET user_password='" + password
				+ "' WHERE user_email='" + email + "';";
		int row = 0;
		ResultSet rs = null;
		Statement st = null;
		try (Connection con = Connect.getConnection();) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	}

	public static int addUsers(String userName, String email, String password,
			String cnicNo, int user_type, String gender, Double basic_salary,
			int d_id, String phone, String address, String dateOfBith,
			String dateOfJoining, String educated, String marrital_status,
			String blood_group, String licence_no, String user_mobile,
			String degree, String institute, String start_date,
			String end_date, double percentage, String vehicle,
			InputStream input) {

		String pass = Hashing.getMD5(password);
		String query = "INSERT INTO user(user_name,user_email, user_password, user_cnic,user_type, user_gender,user_basic_salary,user_district,"
				+ "user_phone,user_address,date_of_birth,joining_date,educated,marrital_status,blood_group,licence_no,user_mobile, "
				+ "degree,institute,start_date,end_date,percentage,vehicle,user_image)"
				+ "Values('"
				+ userName
				+ "','"
				+ email
				+ "','"
				+ pass
				+ "','"
				+ cnicNo
				+ "',"
				+ user_type
				+ ",'"
				+ gender
				+ "',"
				+ basic_salary
				+ ","
				+ d_id
				+ ",'"
				+ phone
				+ "','"
				+ address
				+ "','"
				+ dateOfBith
				+ "','"
				+ dateOfJoining
				+ "','"
				+ educated
				+ "','"
				+ marrital_status
				+ "','"
				+ blood_group
				+ "','"
				+ licence_no
				+ "','"
				+ user_mobile
				+ "','"
				+ degree
				+ "','"
				+ institute
				+ "','"
				+ start_date
				+ "','"
				+ end_date
				+ "',"
				+ percentage
				+ ",'" + vehicle + "','" + input + "')";
		System.out.println("Query : " + query);
		int userId = 0;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement st = null;
			st = con.createStatement();
			st.executeUpdate(query, st.RETURN_GENERATED_KEYS);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				userId = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return userId;
	} // end of addUser

	public static int insertFamilyMembers(int userId, String memberName,
			String relation, String cnic, String phoneNo) {
		con = connection.Connect.getConnection();
		String query = "INSERT INTO family_details(user_id,member_name,relation,phone_no,cnic) VALUES("
				+ userId
				+ ",'"
				+ memberName
				+ "','"
				+ relation
				+ "','"
				+ phoneNo + "','" + cnic + "');";
		System.out.println("Query : " + query);
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement st = null;
			st = con.createStatement();
			st.executeUpdate(query);

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return 0;
	} // end of addUser

	public static int addDOfficer(String do_name, String do_cnic,
			String do_phone, double do_basic_sallery, String do_address,
			int do_district) {

		String query = "INSERT INTO district_officer(do_name, do_cnic, do_phone, do_basic_sallery, do_address, do_district)"
				+ "Values('"
				+ do_name
				+ "','"
				+ do_cnic
				+ "','"
				+ do_phone
				+ "',"
				+ do_basic_sallery
				+ ",'"
				+ do_district
				+ "',"
				+ do_district + ");";
		System.out.println("Query : " + query);
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement st = null;
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	} // end of addUser

	public static UserBean getDo_id(String district) {
		System.out.print(district);

		UserBean bean = null;

		int user_id;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			String query = "SELECT user_id FROM user WHERE user_district=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, district);
			rs = stmt.executeQuery();
			System.out.println(query);
			if (rs.next()) {
				user_id = rs.getInt(1);
				bean = new UserBean();
				bean.setUserId(user_id);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static boolean checkDistrict(int districtId) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			String query = "SELECT user_district FROM user WHERE user_district=?;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, districtId);
			rs = stmt.executeQuery();
			System.out.println(query);
			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkEmail(String email) {
		System.out.println("UserBAL.checkEmail()");
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			// connection.Connect.init();
			if (con != null) {
				// Begin Stored Procedure && Edited -- Jeevan
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_email(?)}");
				prepareCall.setString(1, email);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("UserBAL.checkEmail()/n", e);
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<doBean> getdoid() {
		ArrayList<doBean> list = new ArrayList<>();
		String query = "select user_id ,district_name from district join user on district.district_id=user.user_district";
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				list.add(new doBean(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<doBeansalesman> getdoidSuper() {
		ArrayList<doBeansalesman> list = new ArrayList<>();
		String query = "select district_id ,district_name from district join user on district.district_id=user.user_district order by district_name";
		try (Connection con = Connect.getConnection();) {

			ResultSet rs = null;
			Statement s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				list.add(new doBeansalesman(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int addDOffice() {

		String a = "0000:00:00";
		String query = "INSERT INTO user(start_date)" + "Values('" + a + "');";
		System.out.println("Query : " + query);
		int row = 0;
		try (Connection con = Connect.getConnection();) {

			Statement st = con.createStatement();
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return row;
	} // end of addUser

	public static String getUserEmailByMobileNumber(String mobileNumber) {

		String email = "";
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;

			String query = "SELECT user_email FROM user WHERE user_phone=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
			System.out.println(query);
			if (rs.next()) {
				email = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return email;
	}

	public static ArrayList<HashMap<String, String>> getSuperAdmin() {
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();

		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_super_admin()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("userId", resultSet.getInt("user_id") + "");
				map.put("userName", resultSet.getString("user_name"));
				map.put("userDistrict", resultSet.getString("district_name"));
				mapList.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapList;
	}

	public static int insertUser(HashMap<String, String> usermap) {
		// Connection con = connection.Connect.getConnection();
		int userId = 0;
		// boolean err = true;
		try (Connection con = Connect.getConnection();) {
			Statement st = null;
			// *************** Open *************
			String pass = Hashing.getMD5(usermap.get("password") + "");
			String query = "";

			if (usermap.get("imageId").equals("")) {
				query = "INSERT INTO user(user_name,user_email, user_password, user_cnic,user_type, user_gender,user_basic_salary,user_district,"
						+ "user_phone,user_address,date_of_birth,joining_date,educated,marrital_status,blood_group,licence_no,user_mobile, "
						+ "degree,institute,start_date,end_date,percentage,vehicle)"
						+ "Values('"
						+ usermap.get("firstName")
						+ "','"
						+ usermap.get("email")
						+ "','"
						+ pass
						+ "','"
						+ usermap.get("cnic")
						+ "',"
						+ usermap.get("user_type_value")
						+ ",'"
						+ usermap.get("gender")
						+ "',"
						+ usermap.get("salary")
						+ ","
						+ usermap.get("d_id")
						+ ",'"
						+ usermap.get("phone1")
						+ "','"
						+ usermap.get("address")
						+ "','"
						+ usermap.get("dateOfBirth")
						+ "','"
						+ usermap.get("joiningDate")
						+ "','"
						+ usermap.get("educated")
						+ "','"
						+ usermap.get("marritalStatus")
						+ "','"
						+ usermap.get("bloodGroup")
						+ "','"
						+ usermap.get("licence")
						+ "','"
						+ usermap.get("phone2")
						+ "','"
						+ usermap.get("degree")
						+ "','"
						+ usermap.get("college")
						+ "','"
						+ usermap.get("dateOfStart")
						+ "','"
						+ usermap.get("dateOfEnd")
						+ "',"
						+ usermap.get("percentage")
						+ ",'"
						+ usermap.get("vehicle") + "')";

			} else {

				query = "INSERT INTO user(user_name,user_email, user_password, user_cnic,user_type, user_gender,user_basic_salary,user_district,"
						+ "user_phone,user_address,date_of_birth,joining_date,educated,marrital_status,blood_group,licence_no,user_mobile, "
						+ "degree,institute,start_date,end_date,percentage,vehicle,image_id)"
						+ "Values('"
						+ usermap.get("firstName")
						+ "','"
						+ usermap.get("email")
						+ "','"
						+ pass
						+ "','"
						+ usermap.get("cnic")
						+ "',"
						+ usermap.get("user_type_value")
						+ ",'"
						+ usermap.get("gender")
						+ "',"
						+ usermap.get("salary")
						+ ","
						+ usermap.get("d_id")
						+ ",'"
						+ usermap.get("phone1")
						+ "','"
						+ usermap.get("address")
						+ "','"
						+ usermap.get("dateOfBirth")
						+ "','"
						+ usermap.get("joiningDate")
						+ "','"
						+ usermap.get("educated")
						+ "','"
						+ usermap.get("marritalStatus")
						+ "','"
						+ usermap.get("bloodGroup")
						+ "','"
						+ usermap.get("licence")
						+ "','"
						+ usermap.get("phone2")
						+ "','"
						+ usermap.get("degree")
						+ "','"
						+ usermap.get("college")
						+ "','"
						+ usermap.get("dateOfStart")
						+ "','"
						+ usermap.get("dateOfEnd")
						+ "',"
						+ usermap.get("percentage")
						+ ",'"
						+ usermap.get("vehicle")
						+ "','"
						+ usermap.get("imageId") + "')";
			}

			System.out.println("Query : " + query);

			st = con.createStatement();
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				userId = rs.getInt(1);
			}

			// *************** End *************
			// if(userId>0){
			// *************** OPEN *************
			// if(clist.size() > 0){
			// for (HashMaFp<String, String> cMap : clist) {
			//
			// String query2 = "INSERT INTO
			// family_details(user_id,member_name,relation,phone_no,cnic)
			// VALUES(" + userId + ",'"
			// + cMap.get("memberName") + "','" + cMap.get("relation") + "','" +
			// cMap.get("memberPhoneNo") + "','" + cMap.get("memberCnic") +
			// "');";
			// System.out.println("Query : " + query2);
			// st = con.createStatement();
			// st.executeUpdate(query2);
			// }
			// }
			// *************** End *************

			// if(mailmap.size() > 0){
			// //*************** Open *************
			// final String username = "support@solarpaygo.com";
			// final String newpassword = "support@123";
			// Properties props = new Properties();
			// props.put("mail.smtp.auth", "true");
			//
			// props.put("mail.smtp.host", "mocha6007.mochahost.com");
			// props.put("mail.smtp.port", "2525");
			//
			// System.out.println("at port 2525");
			//
			// Session session = Session.getInstance(props,
			// new javax.mail.Authenticator() {
			// protected PasswordAuthentication getPasswordAuthentication() {
			// return new PasswordAuthentication(username, newpassword);
			// }
			// });
			//
			//
			// Message message = new MimeMessage(session);
			// message.setFrom(new InternetAddress(username));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(mailmap.get("email")));
			// message.setSubject("Welcome to Nizam Energy ");
			// message.setText("Dear "+mailmap.get("firstName")+","+ "\n\n
			// Welcome to Nizam Energy as a District Officer. You can access
			// SolarPayGo here :www.solarpaygo.com \n\n"
			// + "\n\n User Id: "+mailmap.get("email")
			// + " \n\n Password:"+mailmap.get("password")+"\n\n\n\n Thank you
			// \n\n Nizam Energy (PVT) Ltd");
			//
			// Transport.send(message);
			//
			// System.out.println("Done");
			// }
			// *************** End *************

			// String str1 = "Welcome "+usermap.get("firstName")+" to Nizam
			// Energy as Distric Officer. your login id/email is:
			// "+usermap.get("email")+" and password is: "+pass+" .";
			// String str2 = "Welcome "+usermap.get("firstName")+" to Nizam
			// Energy as Service Operation Cotroller. your login id/email is:
			// "+usermap.get("email")+" and password is: "+pass+" .";
			// String str3 = "Welcome "+usermap.get("firstName")+" to Nizam
			// Energy as Call Center Agent. your login id/email is:
			// "+usermap.get("email")+" and password is: "+pass+" .";
			//
			// int uType = Integer.parseInt(usermap.get("user_type_value"));
			// System.out.println("userType------------------------------------------------"+uType);
			// if(uType>0){
			// if(uType == 101){
			// try {
			// CallingXML.sendSingleWellcome("92"+usermap.get("phone1"), str1);
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			//
			// // response.sendRedirect("DistrictOfficer");
			// }else if(uType == 103) {
			// try {
			// CallingXML.sendSingleWellcome("92"+usermap.get("phone1"), str2);
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// // response.sendRedirect("superAdminNock.jsp");
			// }else{
			// try {
			// CallingXML.sendSingleWellcome("92"+usermap.get("phone1"), str3);
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// // response.sendRedirect("DistrictOfficer");
			// }
			//
			// }
			// }else{
			//
			// return false;
			// }

			// return true;
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return userId;
	}

	// this method is used to update user info
	public static int updateUser(HashMap<String, String> usermap) {

		int row = 0;
		try (Connection con = Connect.getConnection();) {
			con.setAutoCommit(false);
			Statement st = null;
			ResultSet rs = null;

			// *************** Open *************
			// String pass = Hashing.getMD5(usermap.get("password")+"");
			String query = "";

			if (usermap.get("imageId").equals("0")) {
				query = "UPDATE user SET user_name = '"
						+ usermap.get("firstName") + "', user_email = '"
						+ usermap.get("email") + "', user_cnic = '"
						+ usermap.get("cnic") + "',user_gender = '"
						+ usermap.get("gender") + "', user_basic_salary = '"
						+ usermap.get("salary") + "', user_phone = '"
						+ usermap.get("phone1") + "', user_address = '"
						+ usermap.get("address") + "', date_of_birth = '"
						+ usermap.get("dateOfBirth") + "', joining_date = '"
						+ usermap.get("joiningDate") + "', educated = '"
						+ usermap.get("educated") + "', marrital_status = '"
						+ usermap.get("marritalStatus") + "', blood_group = '"
						+ usermap.get("bloodGroup") + "', licence_no = '"
						+ usermap.get("licence") + "', user_mobile = '"
						+ usermap.get("phone2") + "', degree = '"
						+ usermap.get("degree") + "', institute = '"
						+ usermap.get("college") + "', start_date = '"
						+ usermap.get("dateOfStart") + "', end_date = '"
						+ usermap.get("dateOfEnd") + "', percentage = '"
						+ usermap.get("percentage") + "', vehicle = '"
						+ usermap.get("vehicle") + "' WHERE user_id = '"
						+ usermap.get("user_id") + "'";
			} else {

				query = "UPDATE user SET user_name = '"
						+ usermap.get("firstName") + "', user_email = '"
						+ usermap.get("email") + "', user_cnic = '"
						+ usermap.get("cnic") + "',user_gender = '"
						+ usermap.get("gender") + "', user_basic_salary = '"
						+ usermap.get("salary") + "', user_phone = '"
						+ usermap.get("phone1") + "', user_address = '"
						+ usermap.get("address") + "', date_of_birth = '"
						+ usermap.get("dateOfBirth") + "', joining_date = '"
						+ usermap.get("joiningDate") + "', educated = '"
						+ usermap.get("educated") + "', marrital_status = '"
						+ usermap.get("marritalStatus") + "', blood_group = '"
						+ usermap.get("bloodGroup") + "', licence_no = '"
						+ usermap.get("licence") + "', user_mobile = '"
						+ usermap.get("phone2") + "', degree = '"
						+ usermap.get("degree") + "', institute = '"
						+ usermap.get("college") + "', start_date = '"
						+ usermap.get("dateOfStart") + "', end_date = '"
						+ usermap.get("dateOfEnd") + "', percentage = '"
						+ usermap.get("percentage") + "', vehicle = '"
						+ usermap.get("vehicle") + "', image_id = '"
						+ usermap.get("imageId") + "' WHERE user_id = '"
						+ usermap.get("user_id") + "'";
			}

			System.out.println("Query : " + query);
			st = con.createStatement();
			row = st.executeUpdate(query);
			con.commit();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return row;

	}// end update UserInfo

	public static int getImage(int user_id) {
		int id = 0;
		String query = "SELECT im.image_id FROM image im JOIN user us USING(image_id) WHERE us.user_id=?";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("image_id");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static String getFormattedPhoneNumber(String num) {
		String number = "";

		try {
			String concat = "+" + num;
			number = String.format("(%s) %s-%s", concat.substring(0, 3),
					concat.substring(3, 6), concat.substring(6, 13));
		} catch (Exception e) {
			number = "Not defined";
			logger.error("", e);
			e.printStackTrace();
		}
		return number;
	}

	public static String getMac(String hostIp) {
		String macAddress = "";
		try {
			// InetAddress address = InetAddress.getLocalHost();
			InetAddress address = InetAddress.getByName(hostIp);
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null) {
				byte[] mac = ni.getHardwareAddress();
				System.out.println("Mac " + mac);
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						System.out.format("%02X%s", mac[i],
								(i < mac.length - 1) ? "-" : "");
						macAddress += macAddress.format("%02X%s", mac[i],
								(i < mac.length - 1) ? "-" : "");
					}
				} else {
					System.out.println("Address doesn't exist or is not "
							+ "accessible.");
				}
			} else {
				System.out.println("Network Interface for the specified "
						+ "address is not found.");
			}
		} catch (UnknownHostException e) {
			logger.error("", e);
			e.printStackTrace();
		} catch (SocketException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return macAddress;
	}

	public static void main(String arg[]) {
	}

}