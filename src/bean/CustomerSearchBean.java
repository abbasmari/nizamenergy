package bean;

public class CustomerSearchBean {
	
	private int customerId;
    private String customerName;
    private String cnicNo;
    private String date_of_birth;
    private String address;
    private String city;
    private String phoneNo;
    private String phoneNo2;
    private Double monthlyIncome;
    private Double familyIncome;
    private int status;
   
    
    
    
    
    
    
    
	public CustomerSearchBean() {
		
		
		
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCnicNo() {
		return cnicNo;
	}
	public void setCnicNo(String cnicNo) {
		this.cnicNo = cnicNo;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhoneNo2() {
		return phoneNo2;
	}
	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public Double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(Double familyIncome) {
		this.familyIncome = familyIncome;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
    
    

}
