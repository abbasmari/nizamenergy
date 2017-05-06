package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;



public class NotificationChatBAL {
	final static Logger logger = Logger.getLogger(NotificationChatBAL.class);	

	public static ArrayList<HashMap<String, Integer>> getDoUnseenUserNotificationChat(int doId){
		System.out.println("NotificationChatBAL.getDoUnseenUserNotificationChat()");
		ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
		
		try(Connection con=Connect.getConnection()) {
			
			CallableStatement prepareCall = con.prepareCall("{CALL get_do_unseen_notification_chat(?)}");
			prepareCall.setInt(1, doId);
			ResultSet resultSet = prepareCall.executeQuery();
			while(resultSet.next()){
				HashMap<String, Integer> map = new HashMap<>();
				map.put("count", resultSet.getInt(1));
				map.put("userId", resultSet.getInt(2));
				list.add(map);
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
