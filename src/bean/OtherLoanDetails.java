package bean;

public class OtherLoanDetails {

	private int otherLoanId;
	private int customerId;
	private String loanDonner;
	private boolean isOtherLoan;
	private double loanAmount;
	private int loanPeriod;
	private double monthlyInstallment;
	private double remainingPayment;
	private boolean isBankAccount;
	private String bankName;
	private String type;

	public OtherLoanDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOtherLoanId() {
		return otherLoanId;
	}

	public void setOtherLoanId(int otherLoanId) {
		this.otherLoanId = otherLoanId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getLoanDonner() {
		return loanDonner;
	}

	public void setLoanDonner(String loanDonner) {
		this.loanDonner = loanDonner;
	}

	public boolean isOtherLoan() {
		return isOtherLoan;
	}

	public void setOtherLoan(boolean isOtherLoan) {
		this.isOtherLoan = isOtherLoan;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public double getMonthlyInstallment() {
		return monthlyInstallment;
	}

	public void setMonthlyInstallment(double monthlyInstallment) {
		this.monthlyInstallment = monthlyInstallment;
	}

	public double getRemainingPayment() {
		return remainingPayment;
	}

	public void setRemainingPayment(double remainingPayment) {
		this.remainingPayment = remainingPayment;
	}

	public boolean isBankAccount() {
		return isBankAccount;
	}

	public void setBankAccount(boolean isBankAccount) {
		this.isBankAccount = isBankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "OtherLoanDetails [otherLoanId=" + otherLoanId + ", customerId=" + customerId + ", loanDonner="
				+ loanDonner + ", isOtherLoan=" + isOtherLoan + ", loanAmount=" + loanAmount + ", loanPeriod="
				+ loanPeriod + ", monthlyInstallment=" + monthlyInstallment + ", remainingPayment=" + remainingPayment
				+ ", isBankAccount=" + isBankAccount + ", bankName=" + bankName + ", type=" + type + "]";
	}

	
	
}
