package bean;

public class ChartData {
	
	private String doName;
	private String date;
	
	public ChartData() {
		// TODO Auto-generated constructor stub
	}

	public String getDoName() {
		return doName;
	}

	public void setDoName(String doName) {
		this.doName = doName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ChartData [doName=" + doName + ", date=" + date + "]";
	}
	
	

}
