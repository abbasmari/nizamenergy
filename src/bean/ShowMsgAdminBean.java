package bean;

public class ShowMsgAdminBean {

	private int msg_id;
	private String msg_admin;
	private String msg_time;
	public ShowMsgAdminBean() {
		
	}
	
	
	
	public ShowMsgAdminBean(int msg_id, String msg_admin, String msg_time) {
		super();
		this.msg_id = msg_id;
		this.msg_admin = msg_admin;
		this.msg_time = msg_time;
	}



	public String getMsg_time() {
		return msg_time;
	}



	public void setMsg_time(String msg_time) {
		this.msg_time = msg_time;
	}



	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_admin() {
		return msg_admin;
	}
	public void setMsg_admin(String msg_admin) {
		this.msg_admin = msg_admin;
	}
	@Override
	public String toString() {
		return "ShowMsgAdminBean [msg_id=" + msg_id + ", msg_admin="
				+ msg_admin + "]";
	}
	
	
}
