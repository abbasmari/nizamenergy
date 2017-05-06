package bean;

public class NumberOfApplianceStatusMessages {

	
	
	private int msg_id;
	private String msg_form;
	private String msg_time;
	private int appliance_id;
	
	public NumberOfApplianceStatusMessages() {
	
	}

	
	
	public NumberOfApplianceStatusMessages(int msg_id, String msg_form,
			String msg_time, int appliance_id) {
		super();
		this.msg_id = msg_id;
		this.msg_form = msg_form;
		this.msg_time = msg_time;
		this.appliance_id = appliance_id;
	}

	


	public int getAppliance_id() {
		return appliance_id;
	}



	public void setAppliance_id(int appliance_id) {
		this.appliance_id = appliance_id;
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
	public String getMsg_form() {
		return msg_form;
	}
	public void setMsg_form(String msg_form) {
		this.msg_form = msg_form;
	}
	
	@Override
	public String toString() {
		return "NumberOfMsgFrom [msg_id=" + msg_id + ", msg_form=" + msg_form
				+ "]";
	}
	
	
	
	
}
