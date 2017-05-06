package bean;

public class ActiveInActiveGraphDoBean {
	
	
	int activeCustomer;
	double activePercentage;
	public int getActiveCustomer() {
		return activeCustomer;
	}
	public void setActiveCustomer(int activeCustomer) {
		this.activeCustomer = activeCustomer;
	}
	public double getActivePercentage() {
		return activePercentage;
	}
	public void setActivePercentage(double activePercentage) {
		this.activePercentage = activePercentage;
	}
	@Override
	public String toString() {
		return "ActiveInActiveGraphDoBean [activeCustomer=" + activeCustomer
				+ ", activePercentage=" + activePercentage + "]";
	}
	

}
