package bean;

public class DistrictOfficerList {

	private int user_id;
	private String user_name;
	private String user_district;
	
	
	public DistrictOfficerList() {
		
	}
	
	public DistrictOfficerList(int user_id, String user_name, String user_district) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_district = user_district;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_district() {
		return user_district;
	}


	public void setUser_district(String user_district) {
		this.user_district = user_district;
	}


	@Override
	public String toString() {
		return "SuperAdminList [user_id=" + user_id + ", user_name="
				+ user_name + ", user_district=" + user_district + "]";
	}
	
}
