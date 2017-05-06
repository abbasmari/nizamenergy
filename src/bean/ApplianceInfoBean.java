/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

public class ApplianceInfoBean {
	private int applianceId;
	private int salesmanId;
	private String applianceName;
	private String productId;
	private String applianceGsmNo;
	private double price;
	private boolean state;
	private String district;
	private String customer;
	private String customerPhone;
	private String customerAddress;

	private String salesman;
	private int status;
	private int applianceStatus;

	private int customerId;
	private String customerCnic;
	private int customer_status;
	private int cityId;
	private String cityName;

	private String activatedDate;

	public ApplianceInfoBean() {
	}

	public int getApplianceStatus() {
		return applianceStatus;
	}

	public void setApplianceStatus(int applianceStatus) {
		this.applianceStatus = applianceStatus;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public int getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public int getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}

	public String getApplianceName() {
		return applianceName;
	}

	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getApplianceGsmNo() {
		return applianceGsmNo;
	}

	public void setApplianceGsmNo(String applianceGsmNo) {
		this.applianceGsmNo = applianceGsmNo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCnic() {
		return customerCnic;
	}

	public void setCustomerCnic(String customerCnic) {
		this.customerCnic = customerCnic;
	}

	public int getCustomer_status() {
		return customer_status;
	}

	public void setCustomer_status(int customer_status) {
		this.customer_status = customer_status;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getActivatedDate() {
		return activatedDate;
	}

	public void setActivatedDate(String activatedDate) {
		this.activatedDate = activatedDate;
	}

	@Override
	public String toString() {
		return "ApplianceInfoBean [applianceId=" + applianceId
				+ ", salesmanId=" + salesmanId + ", applianceName="
				+ applianceName + ", productId=" + productId
				+ ", applianceGsmNo=" + applianceGsmNo + ", price=" + price
				+ ", state=" + state + ", district=" + district + ", customer="
				+ customer + ", customerPhone=" + customerPhone + ", salesman="
				+ salesman + ", status=" + status + ", applianceStatus="
				+ applianceStatus + ", customerId=" + customerId
				+ ", customerCnic=" + customerCnic + ", customer_status="
				+ customer_status + ", cityId=" + cityId + ", cityName="
				+ cityName + ", activatedDate=" + activatedDate + "]";
	}

}
