package bean;

public class RecoveryGraphBean {
	
	 
    String customerName;
    int amountDue;
    double comulativeDue;
    int amountPaid;
    double cumulativePaid;
    double percentage;
    int loanId,customerId;
    
    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	int salesmanId;
    int doId;

    
    
    public int getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public int getDoId() {
		return doId;
	}

	public void setDoId(int doId) {
		this.doId = doId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public double getComulativeDue() {
        return comulativeDue;
    }

    public void setComulativeDue(double comulativeDue) {
        this.comulativeDue = comulativeDue;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getCumulativePaid() {
        return cumulativePaid;
    }

    public void setCumulativePaid(double cumulativePaid) {
        this.cumulativePaid = cumulativePaid;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

	@Override
	public String toString() {
		return "RecoveryGraphBean [customerName=" + customerName
				+ ", amountDue=" + amountDue + ", comulativeDue="
				+ comulativeDue + ", amountPaid=" + amountPaid
				+ ", cumulativePaid=" + cumulativePaid + ", percentage="
				+ percentage + ", loanId=" + loanId + ", salesmanId="
				+ salesmanId + ", doId=" + doId + "]";
	}

    
            

}
