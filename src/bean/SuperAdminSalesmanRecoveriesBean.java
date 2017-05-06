package bean;

import java.sql.Date;

public class SuperAdminSalesmanRecoveriesBean {

	private int paidRecoveries;
	private int remainingRecoveries;
	private String date;
	private int paidAmount;
	private int remainingAmount;
	
	
	
	
	public SuperAdminSalesmanRecoveriesBean() {
		super();
	}
	
	
	

	public SuperAdminSalesmanRecoveriesBean(int paidRecoveries,
			int remainingRecoveries, String date, int paidAmount,
			int remainingAmount) {
		super();
		this.paidRecoveries = paidRecoveries;
		this.remainingRecoveries = remainingRecoveries;
		this.date = date;
		this.paidAmount = paidAmount;
		this.remainingAmount = remainingAmount;
	}







	public int getPaidRecoveries() {
		return paidRecoveries;
	}
	public void setPaidRecoveries(int paidRecoveries) {
		this.paidRecoveries = paidRecoveries;
	}
	public int getRemainingRecoveries() {
		return remainingRecoveries;
	}
	public void setRemainingRecoveries(int remainingRecoveries) {
		this.remainingRecoveries = remainingRecoveries;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	public int getPaidAmount() {
		return paidAmount;
	}



	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}



	public int getRemainingAmount() {
		return remainingAmount;
	}




	public void setRemainingAmount(int remainingAmount) {
		this.remainingAmount = remainingAmount;
	}




	@Override
	public String toString() {
		return "SuperAdminSalesmanRecoveriesBean [paidRecoveries="
				+ paidRecoveries + ", remainingRecoveries="
				+ remainingRecoveries + ", date=" + date + ", paidAmount="
				+ paidAmount + ", remainingAmount=" + remainingAmount + "]";
	}



	
}
