package bean;

public class doBean {

	private int user_id;
	private String disrict;
	
	
	
	public doBean(int user_id, String disrict) {
		super();
		this.user_id = user_id;
		this.disrict = disrict;
	}



	public doBean() {
		
	}



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public String getDisrict() {
		return disrict;
	}



	public void setDisrict(String disrict) {
		this.disrict = disrict;
	}



	@Override
	public String toString() {
		return "doBean [user_id=" + user_id + ", disrict=" + disrict + "]";
	}
	
	
}
