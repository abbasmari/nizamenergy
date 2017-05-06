package bean;

public class SMSAnalytics {

	
	private String date;
	private int ufone,mobilink,telenor;
	
	public SMSAnalytics() {
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUfone() {
		return ufone;
	}
	public void setUfone(int ufone) {
		this.ufone = ufone;
	}
	public int getMobilink() {
		return mobilink;
	}
	public void setMobilink(int mobilink) {
		this.mobilink = mobilink;
	}
	public int getTelenor() {
		return telenor;
	}
	public void setTelenor(int telenor) {
		this.telenor = telenor;
	}
	@Override
	public String toString() {
		return "SMSAnalytics [date=" + date + ", ufone=" + ufone
				+ ", mobilink=" + mobilink + ", telenor=" + telenor + "]";
	}
	


}
