package bean;

public class lat_long_bean {

	
	private double latitude,longitude;
	private long gsm_no;
	private String salesName,locationtime, date;
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public lat_long_bean() {
		// TODO Auto-generated constructor stub
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getGsm_no() {
		return gsm_no;
	}

	public void setGsm_no(long gsm_no) {
		this.gsm_no = gsm_no;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getLocationtime() {
		return locationtime;
	}

	public void setLocationtime(String locationtime) {
		this.locationtime = locationtime;
	}

	@Override
	public String toString() {
		return "lat_long_bean [latitude=" + latitude + ", longitude="
				+ longitude + ", gsm_no=" + gsm_no + ", salesName=" + salesName
				+ ", locationtime=" + locationtime + ", date=" + date + "]";
	}

	
}
