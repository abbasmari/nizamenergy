package bean;

import java.util.Date;

public class TargetBean {

	private int target_id;
	private int salesman_id;
	private int customer_id;
	private int total_target;
	private String target_start_on;
	private String target_end_on;
	private int is_sales;
	private boolean is_recovery;
	private String salesmanName;
	private String joinDate;
	public TargetBean() {
		
	}

	public TargetBean(int target_id, int salesman_id, int total_target,
			String target_start_on, String target_end_on, int is_sales,
			boolean is_recovery) {
		
		this.target_id = target_id;
		this.salesman_id = salesman_id;
		this.total_target = total_target;
		this.target_start_on = target_start_on;
		this.target_end_on = target_end_on;
		this.is_sales = is_sales;
		this.is_recovery = is_recovery;
	}

	public int getTarget_id() {
		return target_id;
	}

	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}

	public int getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(int salesman_id) {
		this.salesman_id = salesman_id;
	}
	
	

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getTotal_target() {
		return total_target;
	}

	public void setTotal_target(int total_target) {
		this.total_target = total_target;
	}

	public String getTarget_start_on() {
		return target_start_on;
	}

	public void setTarget_start_on(String target_start_on) {
		this.target_start_on = target_start_on;
	}

	public String getTarget_end_on() {
		return target_end_on;
	}

	public void setTarget_end_on(String target_end_on) {
		this.target_end_on = target_end_on;
	}

	public int isIs_sales() {
		return is_sales;
	}

	public void setIs_sales(int is_sales) {
		this.is_sales = is_sales;
	}

	public boolean isIs_recovery() {
		return is_recovery;
	}

	public void setIs_recovery(boolean is_recovery) {
		this.is_recovery = is_recovery;
	}
	
	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	
	

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public int getIs_sales() {
		return is_sales;
	}

	@Override
	public String toString() {
		return "TargetBean [target_id=" + target_id + ", salesman_id="
				+ salesman_id + ", total_target=" + total_target
				+ ", target_start_on=" + target_start_on + ", target_end_on="
				+ target_end_on + ", is_sales=" + is_sales + ", is_recovery="
				+ is_recovery + ", salesmanName=" + salesmanName
				+ ", joinDate=" + joinDate + "]";
	}
	
	
	
	
	
	
}
