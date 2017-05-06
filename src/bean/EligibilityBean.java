package bean;

public class EligibilityBean {

    private int applianceId,customerId, salesmanId, elegibilityId, totalInstallments, infoStatus;
    private String customerName, monthlyIncome ,customer_number, applianceName, appliance_number, salesmanName, salesManNumber, cnic, generator, electricity, ups, solar, district,city;
    private double monthlyInstallment, appliancePrice;
    private int paymentMethod, status,seen;
    private String doName,foName;

public EligibilityBean() {
	// TODO Auto-generated constructor stub
}

public int getApplianceId() {
	return applianceId;
}

public void setApplianceId(int applianceId) {
	this.applianceId = applianceId;
}

public int getCustomerId() {
	return customerId;
}

public void setCustomerId(int customerId) {
	this.customerId = customerId;
}

public int getSalesmanId() {
	return salesmanId;
}

public void setSalesmanId(int salesmanId) {
	this.salesmanId = salesmanId;
}

public int getElegibilityId() {
	return elegibilityId;
}

public void setElegibilityId(int elegibilityId) {
	this.elegibilityId = elegibilityId;
}

public int getTotalInstallments() {
	return totalInstallments;
}

public void setTotalInstallments(int totalInstallments) {
	this.totalInstallments = totalInstallments;
}

public int getInfoStatus() {
	return infoStatus;
}

public void setInfoStatus(int infoStatus) {
	this.infoStatus = infoStatus;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public String getMonthlyIncome() {
	return monthlyIncome;
}

public void setMonthlyIncome(String monthlyIncome) {
	this.monthlyIncome = monthlyIncome;
}

public String getCustomer_number() {
	return customer_number;
}

public void setCustomer_number(String customer_number) {
	this.customer_number = customer_number;
}

public String getApplianceName() {
	return applianceName;
}

public void setApplianceName(String applianceName) {
	this.applianceName = applianceName;
}

public String getAppliance_number() {
	return appliance_number;
}

public void setAppliance_number(String appliance_number) {
	this.appliance_number = appliance_number;
}

public String getSalesmanName() {
	return salesmanName;
}

public void setSalesmanName(String salesmanName) {
	this.salesmanName = salesmanName;
}

public String getSalesManNumber() {
	return salesManNumber;
}

public void setSalesManNumber(String salesManNumber) {
	this.salesManNumber = salesManNumber;
}

public String getCnic() {
	return cnic;
}

public void setCnic(String cnic) {
	this.cnic = cnic;
}

public String getGenerator() {
	return generator;
}

public void setGenerator(String generator) {
	this.generator = generator;
}

public String getElectricity() {
	return electricity;
}

public void setElectricity(String electricity) {
	this.electricity = electricity;
}

public String getUps() {
	return ups;
}

public void setUps(String ups) {
	this.ups = ups;
}

public String getSolar() {
	return solar;
}

public void setSolar(String solar) {
	this.solar = solar;
}

public String getDistrict() {
	return district;
}

public void setDistrict(String district) {
	this.district = district;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public double getMonthlyInstallment() {
	return monthlyInstallment;
}

public void setMonthlyInstallment(double monthlyInstallment) {
	this.monthlyInstallment = monthlyInstallment;
}

public double getAppliancePrice() {
	return appliancePrice;
}

public void setAppliancePrice(double appliancePrice) {
	this.appliancePrice = appliancePrice;
}

public int getPaymentMethod() {
	return paymentMethod;
}

public void setPaymentMethod(int paymentMethod) {
	this.paymentMethod = paymentMethod;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public int getSeen() {
	return seen;
}

public void setSeen(int seen) {
	this.seen = seen;
}

public String getDoName() {
	return doName;
}

public void setDoName(String doName) {
	this.doName = doName;
}

public String getFoName() {
	return foName;
}

public void setFoName(String foName) {
	this.foName = foName;
}

@Override
public String toString() {
	return "EligibilityBean [applianceId=" + applianceId + ", customerId="
			+ customerId + ", salesmanId=" + salesmanId + ", elegibilityId="
			+ elegibilityId + ", totalInstallments=" + totalInstallments
			+ ", infoStatus=" + infoStatus + ", customerName=" + customerName
			+ ", monthlyIncome=" + monthlyIncome + ", customer_number="
			+ customer_number + ", applianceName=" + applianceName
			+ ", appliance_number=" + appliance_number + ", salesmanName="
			+ salesmanName + ", salesManNumber=" + salesManNumber + ", cnic="
			+ cnic + ", generator=" + generator + ", electricity="
			+ electricity + ", ups=" + ups + ", solar=" + solar + ", district="
			+ district + ", city=" + city + ", monthlyInstallment="
			+ monthlyInstallment + ", appliancePrice=" + appliancePrice
			+ ", paymentMethod=" + paymentMethod + ", status=" + status
			+ ", seen=" + seen + ", doName=" + doName + ", foName=" + foName
			+ "]";
}


    

}
