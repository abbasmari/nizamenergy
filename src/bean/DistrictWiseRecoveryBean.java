package bean;

public class DistrictWiseRecoveryBean {
	
	String doName;
	double percentage;
	public String getDoName() {
		return doName;
	}
	public void setDoName(String doName) {
		this.doName = doName;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "DistrictWiseRecoveryBean [doName=" + doName + ", percentage="
				+ percentage + "]";
	}
	
	
	
	

}
