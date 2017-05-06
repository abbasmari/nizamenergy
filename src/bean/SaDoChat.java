package bean;

import java.sql.Timestamp;

public class SaDoChat {

	private int chatId;
	private int sender;
	private String senderName;
	private int receiver;
	private String chatText;
	private Timestamp timeStamp;
	private int seen;
	private int customer;

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public String getChatText() {
		return chatText;
	}

	public void setChatText(String chatText) {
		this.chatText = chatText;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "SaDoChat [chatId=" + chatId + ", sender=" + sender
				+ ", receiver=" + receiver + ", chatText=" + chatText
				+ ", timeStamp=" + timeStamp + ", seen=" + seen + ", customer="
				+ customer + "]";
	}

}
