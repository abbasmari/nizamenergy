package bean;

public class EligibilityExistCustomer {

	
	
	
	
	private String customerNumber;
	private String customreName;
	private String applianceName;
	private String applianceNumber;
	private int applianceid;
	private int customerId;
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomreName() {
		return customreName;
	}
	public void setCustomreName(String customreName) {
		this.customreName = customreName;
	}
	public String getApplianceName() {
		return applianceName;
	}
	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}
	public String getApplianceNumber() {
		return applianceNumber;
	}
	public void setApplianceNumber(String applianceNumber) {
		this.applianceNumber = applianceNumber;
	}
	public int getApplianceid() {
		return applianceid;
	}
	public void setApplianceid(int applianceid) {
		this.applianceid = applianceid;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "EligibilityExistCustomer [customerNumber=" + customerNumber
				+ ", customreName=" + customreName + ", applianceName="
				+ applianceName + ", applianceNumber=" + applianceNumber
				+ ", applianceid=" + applianceid + ", customerId=" + customerId
				+ "]";
	}
	
	
	
}
