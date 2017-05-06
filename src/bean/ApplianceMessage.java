package bean;

import java.util.Date;

public class ApplianceMessage {

	private int msg_id;
	private int msg_from;
	private String msg_text;
	private Date msg_date;
	private int msg_seen;
	
	
	public ApplianceMessage() {
		
	}


	public ApplianceMessage(int msg_id, int msg_from, String msg_text,
			Date msg_date, int msg_seen) {
		
		this.msg_id = msg_id;
		this.msg_from = msg_from;
		this.msg_text = msg_text;
		this.msg_date = msg_date;
		this.msg_seen = msg_seen;
	}


	public int getMsg_id() {
		return msg_id;
	}


	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}


	public int getMsg_from() {
		return msg_from;
	}


	public void setMsg_from(int msg_from) {
		this.msg_from = msg_from;
	}


	public String getMsg_text() {
		return msg_text;
	}


	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}


	public Date getMsg_date() {
		return msg_date;
	}


	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}


	public int getMsg_seen() {
		return msg_seen;
	}


	public void setMsg_seen(int msg_seen) {
		this.msg_seen = msg_seen;
	}


	@Override
	public String toString() {
		return "ApplianceMessage [msg_id=" + msg_id + ", msg_from=" + msg_from
				+ ", msg_text=" + msg_text + ", msg_date=" + msg_date
				+ ", msg_seen=" + msg_seen + "]";
	}
	
	
	
	
}
