package bal;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import connection.Connect;



public class LoginHistoryBAL {
	final static Logger logger = Logger.getLogger(LoginHistoryBAL.class);
	
    public static int addLoginHistory(int userId,String userIp, String operatingSystem){
    	int count = 0;
    	try(Connection connect = Connect.getConnection()){
    		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    		String d = s.format(new Date());
    		System.out.println(d);
    		PreparedStatement ps = (PreparedStatement) connect.prepareStatement("SELECT COUNT(*) FROM login_history WHERE  login_date_time LIKE '"+d+"%' AND user_ip = ? AND user_id=?");
    		ps.setString(1, userIp);
    		ps.setInt(2, userId);
    		ResultSet rs = ps.executeQuery();
    		rs.next();
    		
    		count = rs.getInt(1);
    		if(count<=0){
    			System.out.println("dfsdfsd");
    			Calendar calendar = Calendar.getInstance();
    			java.util.Date now = calendar.getTime();
    			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    			ps = (PreparedStatement) connect.prepareStatement("INSERT INTO login_history (user_id,login_date_time,user_ip,operating_system)VALUES (?,?,?,?)");
    			ps.setInt(1, userId);
    			ps.setTimestamp(2, currentTimestamp);
    			ps.setString(3, userIp);
    			ps.setString(4, operatingSystem);    			
    			ps.executeUpdate();
    		}
    		
    	}catch(Exception e){
    		logger.error("",e);
    		e.printStackTrace();
    	}   	
    	
    	return count;
    }
    
    public static int checkUserInUserIp(int userId,String localIp, String publicIp){
    	int count = 0;
    	try(Connection connect = Connect.getConnection()){
    		
    		
    		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    		String d = s.format(new Date());
    		System.out.println(d);
    		PreparedStatement ps = (PreparedStatement) connect.prepareStatement("SELECT COUNT(*) FROM user_ip WHERE user_id = ? AND local_ip = ? AND public_ip = ? and created_on like "+d+"%");
    		ps.setInt(1, userId);
    		ps.setString(2, localIp);
    		ps.setString(3, publicIp);
    		ResultSet rs = ps.executeQuery();
    		rs.next();
    		
    		count = rs.getInt(1);
    	}catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
    	return count;
    }
    
    public static int addUserIp(int userId,String localIp, String publicIp)
    {
    	int count=0;
    	try(Connection connect = Connect.getConnection()){
    		
    		PreparedStatement ps = (PreparedStatement) connect.prepareStatement("INSERT INTO user_ip (user_id,local_ip,public_ip,created_on)VALUES (?,?,?,CURDATE());");
    		ps.setInt(1, userId);
    		ps.setString(2, localIp);
    		ps.setString(3, publicIp);
    		count  = ps.executeUpdate();
    		ps.close();
    		
    	}catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
    	return count;
    
    }
    
    
    
}
