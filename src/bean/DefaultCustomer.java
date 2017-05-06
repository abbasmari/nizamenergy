package bean;

public class DefaultCustomer {
	
	private int appliance_id;
	private String customer_name;
	private String customer_cnic;
	private String customer_phone;
	private String appliance_name;
	private String appliance_GSMno;
	private String due_date;
	
	public DefaultCustomer() {
		
	}

	public DefaultCustomer(int appliance_id, String customer_name,
			String customer_cnic, String customer_phone, String appliance_name,
			String appliance_GSMno, String due_date) {
		
		this.appliance_id = appliance_id;
		this.customer_name = customer_name;
		this.customer_cnic = customer_cnic;
		this.customer_phone = customer_phone;
		this.appliance_name = appliance_name;
		this.appliance_GSMno = appliance_GSMno;
		this.due_date = due_date;
	}

	public int getAppliance_id() {
		return appliance_id;
	}

	public void setAppliance_id(int appliance_id) {
		this.appliance_id = appliance_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_cnic() {
		return customer_cnic;
	}

	public void setCustomer_cnic(String customer_cnic) {
		this.customer_cnic = customer_cnic;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getAppliance_name() {
		return appliance_name;
	}

	public void setAppliance_name(String appliance_name) {
		this.appliance_name = appliance_name;
	}

	public String getAppliance_GSMno() {
		return appliance_GSMno;
	}

	public void setAppliance_GSMno(String appliance_GSMno) {
		this.appliance_GSMno = appliance_GSMno;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		return "DefaultCustomer [appliance_id=" + appliance_id
				+ ", customer_name=" + customer_name + ", customer_cnic="
				+ customer_cnic + ", customer_phone=" + customer_phone
				+ ", appliance_name=" + appliance_name + ", appliance_GSMno="
				+ appliance_GSMno + ", due_date=" + due_date + "]";
	}
	
	
	
}
