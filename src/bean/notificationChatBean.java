package bean;

import java.sql.Timestamp;

public class notificationChatBean {

	private int chat_id;
	private String chat_text;
	private int sender;
	private int receiver;
	private Timestamp timestamp;
	private int seen;
	private String senderName;
	
	public notificationChatBean(){
		
	}
	
	

	public notificationChatBean(int chat_id, String chat_text, int sender,
			int receiver, Timestamp timestamp, int seen, String senderName) {
		super();
		this.chat_id = chat_id;
		this.chat_text = chat_text;
		this.sender = sender;
		this.receiver = receiver;
		this.timestamp = timestamp;
		this.seen = seen;
		this.senderName = senderName;
	}



	public String getSenderName() {
		return senderName;
	}



	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}



	public int getChat_id() {
		return chat_id;
	}

	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}

	public String getChat_text() {
		return chat_text;
	}

	public void setChat_text(String chat_text) {
		this.chat_text = chat_text;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "notificationChatBean [chat_id=" + chat_id + ", chat_text="
				+ chat_text + ", sender=" + sender + ", receiver=" + receiver
				+ ", timestamp=" + timestamp + ", seen=" + seen + "]";
	}
	
	
	
}
