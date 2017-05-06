package bean;

import java.sql.Timestamp;



public class MessageNotificationsBean {

	private int count;
	private int sender;
	private int reciever;
	private Timestamp time;
	
	
	public MessageNotificationsBean(){
		
	}


	public MessageNotificationsBean(int count, int sender, int reciever,
			Timestamp time) {
		super();
		this.count = count;
		this.sender = sender;
		this.reciever = reciever;
		this.time = time;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getSender() {
		return sender;
	}


	public void setSender(int sender) {
		this.sender = sender;
	}


	public int getReciever() {
		return reciever;
	}


	public void setReciever(int reciever) {
		this.reciever = reciever;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "MessageNotificationsBean [count=" + count + ", sender="
				+ sender + ", reciever=" + reciever + ", time=" + time + "]";
	}
	
	
	
}
