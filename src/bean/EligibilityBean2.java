/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author Jeatnder Khatri
 */
public class EligibilityBean2 {

//    private int applianceId, customerId, salesmanId, elegibilityId, totalInstallments;
//    private String customerName, customer_number, applianceName, appliance_number, salesmanName, salesManNumber;
//    private double monthlyInstallment, appliancePrice, monthlyIncome;
//    private int paymentMethod, status;
//
    public EligibilityBean2() {
    }
//
//    public EligibilityBean2(int applianceId, int customerId, int salesmanId, int elegibilityId, int totalInstallments, String customerName, String customer_number, String applianceName, String salesmanName, String salesManNumber, double monthlyInstallment, double appliancePrice, double monthlyIncome, int paymentMethod, int status) {
//        this.applianceId = applianceId;
//        this.customerId = customerId;
//        this.salesmanId = salesmanId;
//        this.elegibilityId = elegibilityId;
//        this.totalInstallments = totalInstallments;
//        this.customerName = customerName;
//        this.customer_number = customer_number;
//        this.applianceName = applianceName;
//        this.salesmanName = salesmanName;
//        this.salesManNumber = salesManNumber;
//        this.monthlyInstallment = monthlyInstallment;
//        this.appliancePrice = appliancePrice;
//        this.monthlyIncome = monthlyIncome;
//        this.paymentMethod = paymentMethod;
//
//    }
//
//    public String getSalesmanName() {
//        return salesmanName;
//    }
//
//    public void setSalesmanName(String salesmanName) {
//        this.salesmanName = salesmanName;
//    }
//
//    public int getApplianceId() {
//        return applianceId;
//    }
//
//    public void setApplianceId(int applianceId) {
//        this.applianceId = applianceId;
//    }
//
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public int getSalesmanId() {
//        return salesmanId;
//    }
//
//    public void setSalesmanId(int salesmanId) {
//        this.salesmanId = salesmanId;
//    }
//
//    public int getElegibilityId() {
//        return elegibilityId;
//    }
//
//    public void setElegibilityId(int elegibilityId) {
//        this.elegibilityId = elegibilityId;
//    }
//
//    public int getTotalInstallments() {
//        return totalInstallments;
//    }
//
//    public void setTotalInstallments(int totalInstallments) {
//        this.totalInstallments = totalInstallments;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getApplianceName() {
//        return applianceName;
//    }
//
//    public void setApplianceName(String applianceName) {
//        this.applianceName = applianceName;
//    }
//
//    public double getMonthlyInstallment() {
//        return monthlyInstallment;
//    }
//
//    public void setMonthlyInstallment(double monthlyInstallment) {
//        this.monthlyInstallment = monthlyInstallment;
//    }
//
//    public double getAppliancePrice() {
//        return appliancePrice;
//    }
//
//    public void setAppliancePrice(double appliancePrice) {
//        this.appliancePrice = appliancePrice;
//    }
//
//    public double getMonthlyIncome() {
//        return monthlyIncome;
//    }
//
//    public void setMonthlyIncome(double monthlyIncome) {
//        this.monthlyIncome = monthlyIncome;
//    }
//
//    public int getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(int paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getCustomer_number() {
//        return customer_number;
//    }
//
//    public void setCustomer_number(String customer_number) {
//        this.customer_number = customer_number;
//    }
//
//    public String getSalesManNumber() {
//        return salesManNumber;
//    }
//
//    public void setSalesManNumber(String salesManNumber) {
//        this.salesManNumber = salesManNumber;
//    }
//
//    public String getAppliance_number() {
//        return appliance_number;
//    }
//
//    public void setAppliance_number(String appliance_number) {
//        this.appliance_number = appliance_number;
//    }
//
//    @Override
//    public String toString() {
//        return "EligibilityBean{" + "applianceId=" + applianceId + ", customerId=" + customerId + ", salesmanId=" + salesmanId + ", elegibilityId=" + elegibilityId + ", totalInstallments=" + totalInstallments + ", customerName=" + customerName + ", customer_number=" + customer_number + ", applianceName=" + applianceName + ", appliance_number=" + appliance_number + ", salesmanName=" + salesmanName + ", salesManNumber=" + salesManNumber + ", monthlyInstallment=" + monthlyInstallment + ", appliancePrice=" + appliancePrice + ", monthlyIncome=" + monthlyIncome + ", paymentMethod=" + paymentMethod + ", status=" + status + '}';
//    }

	
	private int applianceId, customerId, salesmanId, elegibilityId, totalInstallments, familySize;
    private String customerName, customer_number,monthlyIncome, applianceName, appliance_number, address, occupation, salesmanName, salesManNumber, productId, cnic;
    private double monthlyInstallment, appliancePrice,  downpayment, familyIncome;
    private int paymentMethod, status;
    Date dateOfBirth;
	public EligibilityBean2(int applianceId, int customerId, int salesmanId, int elegibilityId, int totalInstallments,
			int familySize, String customerName, String customer_number, String applianceName, String appliance_number,
			String address, String occupation, String salesmanName, String salesManNumber, String productId,
			String cnic, double monthlyInstallment, double appliancePrice, String monthlyIncome, double downpayment,
			double familyIncome, int paymentMethod, int status, Date dateOfBirth) {
		super();
		this.applianceId = applianceId;
		this.customerId = customerId;
		this.salesmanId = salesmanId;
		this.elegibilityId = elegibilityId;
		this.totalInstallments = totalInstallments;
		this.familySize = familySize;
		this.customerName = customerName;
		this.customer_number = customer_number;
		this.applianceName = applianceName;
		this.appliance_number = appliance_number;
		this.address = address;
		this.occupation = occupation;
		this.salesmanName = salesmanName;
		this.salesManNumber = salesManNumber;
		this.productId = productId;
		this.cnic = cnic;
		this.monthlyInstallment = monthlyInstallment;
		this.appliancePrice = appliancePrice;
		this.monthlyIncome = monthlyIncome;
		this.downpayment = downpayment;
		this.familyIncome = familyIncome;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.dateOfBirth = dateOfBirth;
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
	public int getFamilySize() {
		return familySize;
	}
	public void setFamilySize(int familySize) {
		this.familySize = familySize;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCnic() {
		return cnic;
	}
	public void setCnic(String cnic) {
		this.cnic = cnic;
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
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public double getDownpayment() {
		return downpayment;
	}
	public void setDownpayment(double downpayment) {
		this.downpayment = downpayment;
	}
	public double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "EligibilityBean2 [applianceId=" + applianceId + ", customerId=" + customerId + ", salesmanId="
				+ salesmanId + ", elegibilityId=" + elegibilityId + ", totalInstallments=" + totalInstallments
				+ ", familySize=" + familySize + ", customerName=" + customerName + ", customer_number="
				+ customer_number + ", applianceName=" + applianceName + ", appliance_number=" + appliance_number
				+ ", address=" + address + ", occupation=" + occupation + ", salesmanName=" + salesmanName
				+ ", salesManNumber=" + salesManNumber + ", productId=" + productId + ", cnic=" + cnic
				+ ", monthlyInstallment=" + monthlyInstallment + ", appliancePrice=" + appliancePrice
				+ ", monthlyIncome=" + monthlyIncome + ", downpayment=" + downpayment + ", familyIncome=" + familyIncome
				+ ", paymentMethod=" + paymentMethod + ", status=" + status + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
	
    
    
    
    
}
