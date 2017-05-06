package bean;

public class ApplianceNameById {
	private int applianceId;
	private int salesmanId;
	private String applianceName;
	private String gsm;
	
	
	public String getGsm() {
		return gsm;
	}


	public void setGsm(String gsm) {
		this.gsm = gsm;
	}


	public ApplianceNameById() {
		
	}


	public int getApplianceId() {
		return applianceId;
	}


	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}


	public int getSalesmanId() {
		return salesmanId;
	}


	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}


	public String getApplianceName() {
		return applianceName;
	}


	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}


	@Override
	public String toString() {
		return "ApplianceNameById [applianceId=" + applianceId
				+ ", salesmanId=" + salesmanId + ", applianceName="
				+ applianceName + ", gsm=" + gsm + "]";
	}


	
	

}
