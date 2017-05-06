package bean;

import java.sql.Date;

public class CommissionBreakUpBean {
	private String customerName;
	private String applianceGsmNumber;
	private int remainingAmount;
	private int amountPaid;
	private Date paidDate;
	private Date dueDate;
	
	public CommissionBreakUpBean(){
		
	}
	
	public CommissionBreakUpBean(String customerName,
			String applianceGsmNumber, int amountPaid, Date paidDate,
			Date dueDate) {
		super();
		this.customerName = customerName;
		this.applianceGsmNumber = applianceGsmNumber;
		this.amountPaid = amountPaid;
		this.paidDate = paidDate;
		this.dueDate = dueDate;
	}
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getApplianceGsmNumber() {
		return applianceGsmNumber;
	}
	public void setApplianceGsmNumber(String applianceGsmNumber) {
		this.applianceGsmNumber = applianceGsmNumber;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(int remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	@Override
	public String toString() {
		return "CommissionBreakUpBean [customerName=" + customerName
				+ ", applianceGsmNumber=" + applianceGsmNumber
				+ ", amountPaid=" + amountPaid + ", paidDate=" + paidDate
				+ ", dueDate=" + dueDate + "]";
	}
	
	
}
