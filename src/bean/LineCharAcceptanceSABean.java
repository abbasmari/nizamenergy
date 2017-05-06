package bean;

public class LineCharAcceptanceSABean {


	
	int count;
	String doName;
	double percentage;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
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
		return "LineCharAcceptanceSABean [count=" + count + ", doName="
				+ doName + ", percentage=" + percentage + "]";
	}
	
	

}
