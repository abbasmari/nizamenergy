package bean;

public class SaUnSeenMsgBean {

	private int msg_id;
	private String getMsg_do;
	private String get_time;
	
	public SaUnSeenMsgBean() {
		
	}



	public SaUnSeenMsgBean(int msg_id, String getMsg_do, String get_time) {
		super();
		this.msg_id = msg_id;
		this.getMsg_do = getMsg_do;
		this.get_time = get_time;
	}



	public String getGet_time() {
		return get_time;
	}



	public void setGet_time(String get_time) {
		this.get_time = get_time;
	}



	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getGetMsg_do() {
		return getMsg_do;
	}

	public void setGetMsg_do(String getMsg_do) {
		this.getMsg_do = getMsg_do;
	}

	@Override
	public String toString() {
		return "SaUnSeenMsgBean [msg_id=" + msg_id + ", getMsg_do=" + getMsg_do
				+ "]";
	}
	
	
	
}
