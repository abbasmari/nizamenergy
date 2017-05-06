package bean;

import java.sql.Date;

public class SearchBean {
	private int customer_id;
	private String customer_name;
	private String customer_cnic;
	private String customer_phone;
	private int customer_city;
	private int appliance_id;
	private String appliance_name;
	private String appliance_GSMno;
	private boolean appliance_status;
	private double appliance_price;
	private double Amount_Paid;
	private Date Paid_Date;
	private Date Activated_Date;
	private Date due_date;
	public SearchBean() {
		
	}
	public SearchBean(int customer_id, String customer_name,
			String customer_cnic, String customer_phone, int customer_city,
			int appliance_id, String appliance_name, String appliance_GSMno,
			boolean appliance_status, double appliance_price, double amount_Paid,
			Date paid_Date, Date activated_Date, Date due_date) {
		
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_cnic = customer_cnic;
		this.customer_phone = customer_phone;
		this.customer_city = customer_city;
		this.appliance_id = appliance_id;
		this.appliance_name = appliance_name;
		this.appliance_GSMno = appliance_GSMno;
		this.appliance_status = appliance_status;
		this.appliance_price = appliance_price;
		Amount_Paid = amount_Paid;
		Paid_Date = paid_Date;
		Activated_Date = activated_Date;
		this.due_date = due_date;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	public int getCustomer_city() {
		return customer_city;
	}
	public void setCustomer_city(int customer_city) {
		this.customer_city = customer_city;
	}
	public int getAppliance_id() {
		return appliance_id;
	}
	public void setAppliance_id(int appliance_id) {
		this.appliance_id = appliance_id;
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
	public boolean getAppliance_status() {
		return appliance_status;
	}
	public void setAppliance_status(boolean appliance_status) {
		this.appliance_status = appliance_status;
	}
	public double getAppliance_price() {
		return appliance_price;
	}
	public void setAppliance_price(double appliance_price) {
		this.appliance_price = appliance_price;
	}
	public double getAmount_Paid() {
		return Amount_Paid;
	}
	public void setAmount_Paid(double amount_Paid) {
		Amount_Paid = amount_Paid;
	}
	public Date getPaid_Date() {
		return Paid_Date;
	}
	public void setPaid_Date(Date paid_Date) {
		Paid_Date = paid_Date;
	}
	public Date getActivated_Date() {
		return Activated_Date;
	}
	public void setActivated_Date(Date activated_Date) {
		Activated_Date = activated_Date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	@Override
	public String toString() {
		return "SearchBean [customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", customer_cnic=" + customer_cnic
				+ ", customer_phone=" + customer_phone + ", customer_city="
				+ customer_city + ", appliance_id=" + appliance_id
				+ ", appliance_name=" + appliance_name + ", appliance_GSMno="
				+ appliance_GSMno + ", appliance_status=" + appliance_status
				+ ", appliance_price=" + appliance_price + ", Amount_Paid="
				+ Amount_Paid + ", Paid_Date=" + Paid_Date
				+ ", Activated_Date=" + Activated_Date + ", due_date="
				+ due_date + "]";
	}
	
	

}
