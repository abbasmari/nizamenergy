package bean;

import java.sql.Date;

public class SocCustomerSupportBean {

	
	//cc.complaint_id,cc.complaint_date,cc.complaint,us.user_name,a.appliance_name,a.appliance_GSMno 
	private int complaint_id;
	private Date complaint_date;
	
	private String complaint;
	private String user_name;
	private String appliance_num;
	private String appliance_name;
	public SocCustomerSupportBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getComplaint_id() {
		return complaint_id;
	}

	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}

	public Date getComplaint_date() {
		return complaint_date;
	}

	public void setComplaint_date(Date complaint_date) {
		this.complaint_date = complaint_date;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAppliance_num() {
		return appliance_num;
	}

	public void setAppliance_num(String appliance_num) {
		this.appliance_num = appliance_num;
	}

	public String getAppliance_name() {
		return appliance_name;
	}

	public void setAppliance_name(String appliance_name) {
		this.appliance_name = appliance_name;
	}

	@Override
	public String toString() {
		return "SocCustomerSupportBean [complaint_id=" + complaint_id
				+ ", complaint_date=" + complaint_date + ", complaint="
				+ complaint + ", user_name=" + user_name + ", appliance_num="
				+ appliance_num + ", appliance_name=" + appliance_name + "]";
	}

	
	
}
