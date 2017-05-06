package bal;

import bean.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import connection.Connect;

public class UserBAL1 {
	final static Logger logger = Logger.getLogger(UserBAL1.class);
	
    public static enum Mode {

        ALPHA, ALPHANUMERIC, NUMERIC
    }
    static Statement st = null;
    static ResultSet rs = null;

    public static UserBean checkUser(String email, String pasword) {
        UserBean bean = null;
        try(Connection  con=Connect.getConnection()) {
            String query = "SELECT user_id,user_name,user_email, user_password, user_cnic,user_type, user_gender, user_last_login,user_district FROM user Where user_email =? And user_password=?;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, pasword);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt(1);
                String userName = rs.getString(2);
               
                String password = rs.getString(4);
                String cnicNo = rs.getString(5);
                int userType = rs.getInt(6);
                String gender = rs.getString(7);
                String lastLogin = rs.getString(8);
                int do_district=rs.getInt(9);

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
            }
           
        } catch (SQLException e) {
        	logger.error("",e);
            System.out.println(e.getMessage());
        }
        return bean;
    }

    public static int deleteUser(UserBean bean) {
        String query = "Delete FROM user WHERE user_id = " + bean.getUserId();
        Statement s = null;
        int row = 0;
        try(Connection  con=Connect.getConnection()) {
            s = con.createStatement();
            row = s.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data has been DELETED");
            } else {
                System.out.println("Data has not been DELETD");
            }
        } catch (Exception e) {
        	logger.error("",e);
            System.err.println(e);
        }
        return row;
    } // end of UserBean

    public static boolean checkUserEmail(String email) {
        
        System.out.print(email);
       
        boolean result = false;
        try(Connection  con=Connect.getConnection()) {
            String query = "SELECT * FROM user WHERE user_email=?";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            System.out.println(query);
            if (rs.next()) {
                result = true;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return result;
    }

    public static String generateRandomString(int length, int a) throws Exception {

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
    	
        String query = "Update user SET user_password='"+ password+"' WHERE user_email='"+ email +"';";
        int row = 0;
        try(Connection  con=Connect.getConnection()) {
            st =con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is updated");
            } else {
                System.out.println("Data is not updated");
            }
            
        } catch (Exception e) {
        	logger.error("",e);
            System.err.println(e);
        }
        return row;
    }

    public static int addUserSuper(String userName, String email, String password, String cnicNo, int user_type, String gender) {
    	
        String query = "INSERT INTO user(user_name,user_email, user_password, user_cnic,user_type, user_gender)" + "Values('" + userName + "','" + email + "','" + password + "'," + cnicNo + "," + user_type + ",'" + gender + "')";
        System.out.println("Query : " + query);
        int row = 0;
        try(Connection  con=Connect.getConnection()) {
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            st.close();
            con.close();
        } catch (Exception e) {
        	logger.error("",e);
            System.err.println(e);
        }
        return row;
    } // end of addUser
    
    public static int addDOfficer(String do_name, String do_cnic, String do_phone, double do_basic_sallery, String do_address, String do_district) {
    	
        String query = "INSERT INTO district_officer(do_name, do_cnic, do_phone, do_basic_sallery, do_address, do_district)" + "Values('" + do_name + "','" + do_cnic + "','" + do_phone + "'," + do_basic_sallery + ",'" + do_district + "','" + do_district + "');";
        System.out.println("Query : " + query);
        int row = 0;
        try(Connection  con=Connect.getConnection()) {
            st =con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
        } catch (Exception e) {
        	logger.error("",e);
            System.err.println(e);
        }
        return row;
    } // end of addUser
    
    

    public static void main(String[] args) {
        System.out.println(checkUser("usman@nizam.com", "nizam"));
    }

} // end iof class
