package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.CustomerMessageBean;
import bean.SaDoChat;
import bean.ShowMsgAdminBean;
import bean.notificationChatBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.ocpsoft.pretty.time.PrettyTime;

import connection.Connect;

public class SaDoChatBAL {

	final static Logger logger = Logger.getLogger(SaDoChatBAL.class);

	public SaDoChatBAL() {

	}

	public int countAdminChatList(int doId, int cId) {

		ResultSet rs = null;

		int count = 0;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("select count(s.id)" + " from sa_do_chat s"
					+ " where s.receiver = ?" + " and s.customer = ?" + " and s.seen = '0'");
			ps.setInt(1, doId);
			ps.setInt(2, cId);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static String prettyTime() {

		ResultSet rs = null;
		PreparedStatement ps = null;

		Timestamp count = null;
		String bingoo = null;
		PrettyTime p = new PrettyTime();
		String query = "SELECT msg_date FROM appliance_message LIMIT 1";

		try (Connection con = Connect.getConnection()) {
			// PreparedStatement ps = (PreparedStatement)
			// con.prepareStatement("SELECT COUNT(s.id) FROM sa_do_chat s WHERE
			// s.sender = "+sendernum+" AND s.seen = '0'");
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getTimestamp(1);

				Date date = new Date(count.getTime());
				bingoo = p.format((date));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return bingoo;
	}

	public static int countSaMessagesToDoChatNotifications(int user_id) {

		PreparedStatement ps2 = null;

		int count = 0;

		String query = "SELECT COUNT(s.id) FROM sa_do_chat s WHERE s.receiver = " + user_id + " AND s.seen = '0'";

		try (Connection con = Connect.getConnection()) {

			ResultSet rs2 = null;
			// PreparedStatement ps = (PreparedStatement)
			// con.prepareStatement("SELECT COUNT(s.id) FROM sa_do_chat s WHERE
			// s.sender = "+sendernum+" AND s.seen = '0'");
			ps2 = (PreparedStatement) con.prepareStatement(query);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				count = rs2.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	
	public int countAdminChatListMessages(int doId, int cId) {
		int count = 0;
		try (Connection con = connection.Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"SELECT COUNT(s.id) FROM sa_do_chat s WHERE s.sender = ? AND s.customer = ? and s.seen = '0'");
			ps.setInt(1, doId);
			ps.setInt(2, cId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static int countDoChat(int sendernum) {

		int count = 0;

		String query = "SELECT COUNT(s.id) FROM sa_do_chat s WHERE s.sender = " + sendernum + " AND s.seen = '0'";

		try (Connection con = connection.Connect.getConnection()) {
			// PreparedStatement ps = (PreparedStatement)
			// con.prepareStatement("SELECT COUNT(s.id) FROM sa_do_chat s WHERE
			// s.sender = "+sendernum+" AND s.seen = '0'");
			ResultSet rs = null;
			PreparedStatement ps = null;
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static int countDoChatNotifications() {

		System.out.println("SaDoChatBAL.countDoChatNotifications()");
		int count = 0;
		// connection.Connect.init2();
		// String query = "SELECT COUNT(s.id) FROM sa_do_chat s WHERE s.receiver
		// IS NULL AND s.seen = '0'";
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con.prepareCall("{CALL count_do_chat_notifications()}");
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static ArrayList<ShowMsgAdminBean> getUnseenMessagesForAdmin() {
		System.out.println("SaDoChatBAL.getUnseenMessagesForAdmin()");
		// connection.Connect.init2();
		ArrayList<ShowMsgAdminBean> list = new ArrayList<>();
		ShowMsgAdminBean bean;
		Timestamp time;
		String prettyTime;
		int msg_id;
		String msg;
		PrettyTime p = new PrettyTime();
		// String query = "SELECT d.id, d.chat_text, d.timestamp FROM sa_do_chat
		// d WHERE d.receiver IS NULL AND d.seen = '0' ORDER BY d.id DESC LIMIT
		// 6";

		try (Connection con = Connect.getConnection()) {
			if (con != null) {

				CallableStatement prepareCall = con.prepareCall("{CALL get_unseen_messages_for_admin()}");
				ResultSet resultSet = prepareCall.executeQuery();

				while (resultSet.next()) {

					msg_id = resultSet.getInt(1);
					msg = resultSet.getString(2);
					time = resultSet.getTimestamp(3);

					Date date = new Date(time.getTime());
					prettyTime = p.format((date));

					bean = new ShowMsgAdminBean();
					bean.setMsg_id(msg_id);
					bean.setMsg_admin(msg);
					bean.setMsg_time(prettyTime);
					list.add(bean);
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return list;
	}

	public ArrayList<SaDoChat> getAdminChatList(int saId, int doId, int cId) {

		ArrayList<SaDoChat> chatList = new ArrayList<>();
		String query = "SELECT s.id," + " s.chat_text," + " s.sender," + " s.receiver," + " s.customer,"
				+ " s.timestamp," + " s.seen," + " u.user_name " + " FROM sa_do_chat s"
				+ "	left join user u on u.user_id = s.sender " + " WHERE (s.sender = ? || s.receiver = ?)"
				+ " and s.customer = ?";
		// String query = "select * from do_sa_chat";

		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			// System.err.println(ps.getPreparedSql());
			ps.setInt(1, doId);
			// ps.setInt(1, saId);
			ps.setInt(2, doId);
			ps.setInt(3, cId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaDoChat chat = new SaDoChat();
				chat.setChatId(rs.getInt("s.id"));
				chat.setChatText(rs.getString("s.chat_text"));
				chat.setSender(rs.getInt("s.sender"));
				chat.setReceiver(rs.getInt("s.receiver"));
				chat.setTimeStamp(rs.getTimestamp("s.timestamp"));
				chat.setCustomer(rs.getInt("s.customer"));
				chat.setSenderName(rs.getString("u.user_name"));
				chatList.add(chat);
			}
			System.out.println("Chat List Size : " + chatList.size());
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
					"UPDATE sa_do_chat s SET s.seen = '1' WHERE s.sender = ? AND s.customer = ? AND s.seen = '0'");
			pstmt.setInt(1, doId);
			pstmt.setInt(2, cId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			// TODO Auto-generated catch block
			System.err.println("SaDoChatBAL sent " + e.getLocalizedMessage());
		}
		return chatList;
	}

	public ArrayList<SaDoChat> getDistrictOfficerChatList(int doid, int customerId) {
		ArrayList<SaDoChat> chatList = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			String query = "select u.user_id, u.user_name," + " s.id," + "	s.chat_text," + "	s.sender,"
					+ "	s.receiver," + "	s.customer," + "	s.timestamp," + "	s.seen"
					+ "	from sa_do_chat s left join user u on u.user_id = s.sender"
					+ "	where (s.receiver = ? || s.sender = ?) and s.customer = ?;";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
			stmt.setInt(1, doid);
			stmt.setInt(2, doid);
			stmt.setInt(3, customerId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SaDoChat chat = new SaDoChat();
				chat.setChatId(rs.getInt("s.id"));
				chat.setSender(rs.getInt("s.sender"));
				chat.setSenderName(rs.getString("u.user_name"));
				chat.setReceiver(rs.getInt("s.receiver"));
				chat.setTimeStamp(rs.getTimestamp("s.timestamp"));
				chat.setSeen(rs.getInt("s.seen"));
				chat.setChatText(rs.getString("s.chat_text"));
				chatList.add(chat);
				// System.out.println(rs.getString("u.user_name")+"\t"+rs.getInt("s.sender"));
			}

			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
					"UPDATE sa_do_chat s SET s.seen = '1' WHERE s.receiver = ? AND s.customer = ? AND s.seen = '0'");
			pstmt.setInt(1, doid);
			pstmt.setInt(2, customerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex getDistrictOfficerChatList " + e.getLocalizedMessage());
		}

		return chatList;

	}

	public static SaDoChat insert(String message, int sender, int receiver, int customer) {

		String insertQuery = "INSERT INTO sa_do_chat (chat_text, sender, receiver, customer) VALUES (?, ?, ?, ?);";
		String getQuery = "select s.id," + " s.chat_text," + " s.sender," + " u.user_name," + " s.receiver,"
				+ " s.timestamp," + " s.seen" + " from sa_do_chat s" + " left join user u on u.user_id = s.sender"
				+ " where s.id = ?;";
		SaDoChat chat = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement psInsert = (PreparedStatement) con.prepareStatement(insertQuery);
			psInsert.setString(1, message);
			psInsert.setInt(2, sender);
			psInsert.setInt(3, receiver);
			psInsert.setInt(4, customer);
			psInsert.execute();
			long id = psInsert.getLastInsertID();
			// System.out.println(id);
			PreparedStatement psGet = (PreparedStatement) con.prepareStatement(getQuery);
			psGet.setLong(1, id);
			System.out.println(psGet.getPreparedSql());
			ResultSet rs = psGet.executeQuery();
			if (rs.next()) {
				chat = new SaDoChat();
				System.out.println(rs.getStatement());
				chat.setChatId(rs.getInt("s.id"));
				chat.setSender(rs.getInt("s.sender"));
				chat.setSenderName(rs.getString("u.user_name"));
				chat.setChatText(rs.getString("s.chat_text"));
				chat.setTimeStamp(rs.getTimestamp("s.timestamp"));
				chat.setSeen(rs.getInt("s.seen"));

				// System.out.println(chat);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return chat;
	}

	public static SaDoChat insert(String message, int sender, int customerid) {

		SaDoChat chat = new SaDoChat();
		String getQuery = "select s.id," + " s.sender," + " u.user_name," + " s.receiver," + " s.chat_text,"
				+ " s.timestamp," + " s.customer," + " s.seen" + " from sa_do_chat s"
				+ " left join user u on u.user_id = s.sender" + " where s.id = ?;";
		try (Connection con = Connect.getConnection()) {
			String query = "insert into sa_do_chat(chat_text, sender, customer) values (?, ?, ?);";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, message);
			ps.setInt(2, sender);
			ps.setInt(3, customerid);
			ps.execute();
			long id = ps.getLastInsertID();
			PreparedStatement psGet = (PreparedStatement) con.prepareStatement(getQuery);
			psGet.setLong(1, id);
			// System.out.println(psGet.getPreparedSql());
			ResultSet rs = psGet.executeQuery();
			if (rs.next()) {
				chat = new SaDoChat();
				// System.out.println(rs.getStatement());
				chat.setChatId(rs.getInt("s.id"));
				chat.setSender(rs.getInt("s.sender"));
				chat.setSenderName(rs.getString("u.user_name"));
				chat.setReceiver(rs.getInt("s.receiver"));
				chat.setChatText(rs.getString("s.chat_text"));
				chat.setTimeStamp(rs.getTimestamp("s.timestamp"));
				chat.setSeen(rs.getInt("s.seen"));
				chat.setCustomer(rs.getInt("s.customer"));
				System.out.println(chat);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("SaDoChatBal insert " + e.getLocalizedMessage());
			// e.printStackTrace();
		}
		return chat;
	}

	public ArrayList<notificationChatBean> getNotificationChat(int saId, int doId) {
		System.out.println("SaDoChatBAL.getNotificationChat()");

		notificationChatBean chat = null;
		int chatId;
		String msg;
		int senderId;
		int receiverId;
		Timestamp time;
		int seen;
		String senderName;
		ArrayList<notificationChatBean> chatList = new ArrayList<>();
		// String query = "SELECT s.chat_id, s.chat_text, s.sender, s.receiver,
		// s.time, s.seen, u.user_name FROM notification_chat s "
		// + "JOIN USER u ON u.user_id = s.sender WHERE (s.sender = ? ||
		// s.receiver = ?) AND (s.sender = ? || s.receiver = ?)";
		// String query = "select * from do_sa_chat";

		try (Connection con = Connect.getConnection()) {

			// PreparedStatement ps = (PreparedStatement)
			// connection.prepareStatement(query);
			// System.err.println(ps.getPreparedSql());
			// ps.setInt(1, doId);
			// ps.setInt(2, doId);
			// ps.setInt(3, saId);
			// ps.setInt(4, saId);

			// Begin Stored Procedure -- Jeevan
			System.out.println("Sender(SuperAdmin) : " + saId + ", Receiver(DO) : " + doId);
			CallableStatement prepareCall = con.prepareCall("{CALL get_notification_chat(?, ?)}");
			prepareCall.setInt(1, saId);
			prepareCall.setInt(2, doId);

			ResultSet rs = prepareCall.executeQuery();
			// End Stored Procedure
			// System.out.println(ps.toString());
			while (rs.next()) {
				chatId = rs.getInt("chat_id");
				msg = rs.getString("chat_text");
				senderId = rs.getInt("sender");
				receiverId = rs.getInt("receiver");
				time = rs.getTimestamp("time");
				seen = rs.getInt("seen");
				senderName = rs.getString("user_name");
				chat = new notificationChatBean();
				chat.setChat_id(chatId);
				chat.setChat_text(msg);
				chat.setReceiver(receiverId);
				chat.setSender(senderId);
				chat.setTimestamp(time);
				chat.setSeen(seen);
				chat.setSenderName(senderName);
				chatList.add(chat);
			}

			// System.out.println("Chat List Size : " + chatList.size());

			// PreparedStatement pstmt = (PreparedStatement)
			// connection.prepareStatement(
			// "UPDATE notification_chat n SET n.seen = 1 WHERE (n.sender = ?
			// and n.receiver = ?) AND n.seen = 0");
			// pstmt.setInt(1, doId);
			// pstmt.setInt(2, saId);
			// pstmt.executeUpdate();

			// Begin Stored Procedure -- Jeevan

			prepareCall = con.prepareCall("{CALL update_get_notification_chat(?, ?)}");
			prepareCall.setInt(1, saId);
			prepareCall.setInt(2, doId);
			prepareCall.executeUpdate();
			prepareCall.close();
			con.close();
			// End Stored Procedure
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return chatList;
	}

	public static notificationChatBean insertMessage(String message, int sender, int receiver) {
		// String insertQuery = "INSERT INTO notification_chat (chat_text,
		// sender, receiver, seen) VALUES (?, ?, ?, ?);";

		// String getQuery = "SELECT s.chat_id, s.chat_text, s.sender,
		// u.user_name, s.receiver,"
		// + " s.time, s.seen FROM notification_chat s LEFT JOIN USER u ON
		// u.user_id = s.sender"
		// + " WHERE s.chat_id = ?";
		notificationChatBean chat = null;
		try (Connection con = Connect.getConnection()) {
			// PreparedStatement psInsert = (PreparedStatement)
			// connection.prepareStatement(insertQuery);
			// psInsert.setString(1, message);
			// psInsert.setInt(2, sender);
			// psInsert.setInt(3, receiver);
			// psInsert.setInt(4, seen);
			// psInsert.execute();

			// Begin Stored Procedure -- Jeevan
			CallableStatement prepareCall = con.prepareCall("{CALL insert_into_notification_chat(?, ?, ?)}");
			prepareCall.setString(1, message);
			prepareCall.setInt(2, sender);
			prepareCall.setInt(3, receiver);
			ResultSet resultSet = prepareCall.executeQuery();
			// End Stored Procedure

			// long id = psInsert.getLastInsertID();
			// System.out.println(id);
			// PreparedStatement psGet = (PreparedStatement)
			// connection.prepareStatement(getQuery);
			// psGet.setLong(1, id);
			// System.out.println(psGet.getPreparedSql());

			// prepareCall.close();
			// prepareCall = null;
			//// Begin Stored Procedure -- Jeevan
			// prepareCall = connection.prepareCall("{CALL
			// get_notification_chat_by_id(?)}");
			// prepareCall.setInt(1, id);
			//// End Stored Procedure
			// ResultSet rs = prepareCall.executeQuery();
			if (resultSet.next()) {
				chat = new notificationChatBean();

				chat.setChat_id(resultSet.getInt("s.chat_id"));
				chat.setSender(resultSet.getInt("s.sender"));
				chat.setSenderName(resultSet.getString("u.user_name"));
				chat.setChat_text(resultSet.getString("s.chat_text"));
				chat.setTimestamp(resultSet.getTimestamp("s.time"));
				chat.setSeen(resultSet.getInt("s.seen"));

				// System.out.println(chat);
			}

			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return chat;
	}

	public static int doSocChatNotifications() {
		System.out.println("SaDoChatBAL.doSocChatNotifications()");
		int count = 0;

		// connection.Connect.init2();

		try (Connection con = Connect.getConnection()) {

			// PreparedStatement ps = (PreparedStatement)
			// con.prepareStatement("SELECT COUNT(s.id) FROM sa_do_chat s WHERE
			// s.sender = "+sendernum+" AND s.seen = '0'");
			// ps2 = (PreparedStatement) connection.prepareStatement(query);
			// rs2 = ps2.executeQuery();

			// Begin Stored Procedure -- Jeevan
			if (con != null) {

				CallableStatement prepareCall = con.prepareCall("{CALL count_do_soc_chat_notifications()}");
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedures
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return count;
	}

	public static int saSocChatNotifications() {
		int count = 0;

		PreparedStatement ps2;
		String query = "SELECT COUNT(s.chat_id) FROM notification_chat s JOIN USER u ON u.user_id = s.sender WHERE (u.user_type = 100 || u.user_type = 103) AND s.seen = 0";

		try (Connection con = Connect.getConnection()) {

			ResultSet rs = null;
			ps2 = (PreparedStatement) con.prepareStatement(query);
			rs = ps2.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static int doChatHasNewMessage(int user_id) {

		System.out.println("SaDoChatBAL.doChatHasNewMessage() user_id = " + user_id);
		int count = 0;

		ResultSet rs = null;
		PreparedStatement ps = null;

		String query = "SELECT COUNT(s.chat_id) FROM notification_chat s JOIN USER u ON u.user_id = s.sender WHERE (u.user_type = 101 || u.user_type = 103) AND s.seen = 0 AND u.user_id ="
				+ user_id;

		try (Connection con = Connect.getConnection()) {

			// PreparedStatement ps = (PreparedStatement)
			// con.prepareStatement("SELECT COUNT(s.id) FROM sa_do_chat s WHERE
			// s.sender = "+sendernum+" AND s.seen = '0'");
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static int saChatHasNewMessages(int user_id) {
		int count = 0;

		ResultSet rs = null;
		PreparedStatement ps = null;

		String query = "SELECT COUNT(s.chat_id) FROM notification_chat s JOIN USER u ON u.user_id = s.sender WHERE (u.user_type = 100 || u.user_type = 103) AND s.seen = 0 AND u.user_id ="
				+ user_id;

		try (Connection con = Connect.getConnection()) {

			// PreparedStatement ps = (PreparedStatement)
			// con.prepareStatement("SELECT COUNT(s.id) FROM sa_do_chat s WHERE
			// s.sender = "+sendernum+" AND s.seen = '0'");
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("Ex countAdminChatList " + e.getLocalizedMessage());
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getUnseenReceiverChat(int senderId, int receiverId) {
		System.out.println("SaDoChatBAL.getUnseenReceiverChat()");
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con.prepareCall("{CALL get_unseen_user_receiver_chat(?, ?)}");
			prepareCall.setInt(1, senderId);
			prepareCall.setInt(2, receiverId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("chatId", "" + resultSet.getInt("chat_id"));
				map.put("sender", "" + resultSet.getInt("sender"));
				map.put("receiverId", "" + resultSet.getInt("receiver"));
				map.put("chatText", resultSet.getString("chat_text"));
				map.put("senderName", resultSet.getString("user_name"));
				map.put("chatDateTime", resultSet.getString("time"));
				mapList.add(map);
				CallableStatement updatePc = con.prepareCall("{CALL update_notificatioin_chat_by_id(?)}");
				updatePc.setInt(1, resultSet.getInt("chat_id"));
				updatePc.executeQuery();
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return mapList;
	}

	public static int insertchat(String message, int sender, int reciever, int customer) {
		int row = 0;

		String query = "INSERT INTO sa_do_chat(chat_text,sender,receiver,TIMESTAMP,customer)VALUES ('" + message + "',"
				+ sender + "," + reciever + ",TIMESTAMP(NOW())," + customer + ");";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("Data Added");
			} else {
				System.out.println("Data not added");
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static ArrayList<HashMap<String, String>> getMessages(int doId) {
		ArrayList<HashMap<String, String>> maps = new ArrayList();
		String query = "SELECT COUNT(DISTINCT(customer)) FROM sa_do_chat WHERE receiver=? AND seen=1";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, doId);
			rs = ps.executeQuery();

			while (rs.next()) {

				HashMap<String, String> map = new HashMap<>();
				map.put("Count", rs.getString("COUNT(DISTINCT(customer))"));

				maps.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getChat(int receiver, int customer) {
		ArrayList<HashMap<String, String>> maps = new ArrayList();
		String query = "SELECT chat_text FROM sa_do_chat WHERE receiver=? AND customer=?";

		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, receiver);
			ps.setInt(2, customer);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("messages", rs.getString("chat_text"));
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getMoreInfoCustomers(int doId) {
		ArrayList<HashMap<String, String>> maps = new ArrayList();
		String query = "SELECT DISTINCT(customer) FROM sa_do_chat WHERE receiver=? AND seen=1";
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, doId);
			rs = ps.executeQuery();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerId", rs.getString("customer"));
				maps.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	// public int countNew( )
	public static void main(String arg[]) {
		// ArrayList<SaDoChat> chatList = new SaDoChatBAL().getAdminChatList(17,
		// 16, 41);
		// System.out.println(chatList);

		// int c = new SaDoChatBAL().countAdminChatList(17, 44);
		// System.out.println(insert("hello",16,17,14));

		// System.out.println(unSeenSaMessagesToDoChatNotifications(17));

		System.out.println(doSocChatNotifications());

	}
}
