package bean;

public class ApplianceActiveSABean {
	
	
	double sixWottPercentage;
	double thirtyWottPercentage;
	double sixtyWottPercentage;
	public double getSixWottPercentage() {
		return sixWottPercentage;
	}
	public void setSixWottPercentage(double sixWottPercentage) {
		this.sixWottPercentage = sixWottPercentage;
	}
	public double getThirtyWottPercentage() {
		return thirtyWottPercentage;
	}
	public void setThirtyWottPercentage(double thirtyWottPercentage) {
		this.thirtyWottPercentage = thirtyWottPercentage;
	}
	public double getSixtyWottPercentage() {
		return sixtyWottPercentage;
	}
	public void setSixtyWottPercentage(double sixtyWottPercentage) {
		this.sixtyWottPercentage = sixtyWottPercentage;
	}
	@Override
	public String toString() {
		return "ApplianceActiveSABean [sixWottPercentage=" + sixWottPercentage
				+ ", thirtyWottPercentage=" + thirtyWottPercentage
				+ ", sixtyWottPercentage=" + sixtyWottPercentage + "]";
	}
	

}
