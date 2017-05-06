package bean;

public class LoanPaymentGraphDoBean {
	
	
	int amount;
    
	double percentage;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "LoanPaymentGraphDoBean [amount=" + amount + ", percentage="
				+ percentage + "]";
	}
 
 

}
