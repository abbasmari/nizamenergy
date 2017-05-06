package bean;

public class AlertsForNumber {
    
	private int aler_num_id;
	private String number;
	private int appliance_id;
	String time;
	
	
	public AlertsForNumber() {
	
	}

	
	
	public AlertsForNumber(int aler_num_id, String number, int appliance_id,
			String time) {
		super();
		this.aler_num_id = aler_num_id;
		this.number = number;
		this.appliance_id = appliance_id;
		this.time = time;
	}



	public int getAppliance_id() {
		return appliance_id;
	}



	public void setAppliance_id(int appliance_id) {
		this.appliance_id = appliance_id;
	}




	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public int getAler_num_id() {
		return aler_num_id;
	}
	public void setAler_num_id(int aler_num_id) {
		this.aler_num_id = aler_num_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "AlertsForNumber [aler_num_id=" + aler_num_id + ", number="
				+ number + "]";
	}
	
	
}
