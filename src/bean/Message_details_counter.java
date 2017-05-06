package bean;

public class Message_details_counter {

	private int mobilink;
	private int telenor;
	
	public Message_details_counter() {
		// TODO Auto-generated constructor stub
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
		return "Message_details_counter [mobilink=" + mobilink + ", telenor="
				+ telenor + "]";
	}
}
