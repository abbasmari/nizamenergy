package bal;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import bean.MessageNotificationsBean;
import connection.Connect;

public class MessageNotificationsBal {
	final static Logger logger = Logger.getLogger(MessageNotificationsBal.class);
	
	
	public static ArrayList<MessageNotificationsBean> getUnseenMessagesNotifications(int said){
		System.out.println("MessageNotificationsBal.getMessageNotificationsCount()");
		
		ArrayList<MessageNotificationsBean> list = new ArrayList<>();
		MessageNotificationsBean bean = null;
		int count;
		int sender;
		int receiver;
		Timestamp time;
		
		try (Connection connection=Connect.getConnection()){
			CallableStatement call = connection.prepareCall("{CALL get_unseen_messages_notifications(?)}");
			call.setInt(1, said);
			ResultSet rs = call.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
				sender = rs.getInt(2);
				receiver = rs.getInt(3);
				time = rs.getTimestamp(4);
				
				bean = new MessageNotificationsBean();
				bean.setCount(count);
				bean.setSender(sender);
				bean.setReciever(receiver);
				bean.setTime(time);
				
				list.add(bean);
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	
		

}
