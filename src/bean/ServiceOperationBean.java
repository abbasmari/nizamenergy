package bean;

public class ServiceOperationBean {

	private int userId;
	private String userName;
	private int userDistrict;
	private String districtName;

	public ServiceOperationBean() {

	}

	public ServiceOperationBean(int userId, String userName, int userDistrict) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userDistrict = userDistrict;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserDistrict() {
		return userDistrict;
	}

	public void setUserDistrict(int userDistrict) {
		this.userDistrict = userDistrict;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return "ServiceOperationBean [userId=" + userId + ", userName=" + userName + ", userDistrict=" + userDistrict
				+ "]";
	}

}
