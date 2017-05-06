package bean;

public class BusinessDetails {

	private int businessId;
	private int customerId;
	private String businessName;
	private String businessType;
	private String ownedOrPartner;
	private String period;
	private String currentPlacePeriod;
	private int workers;
	private String businessPlace;
	private String orgPhone;
	
	public BusinessDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public BusinessDetails(int businessId, int customerId, String businessName,
			String businessType, String ownedOrPartner, String period,
			String currentPlacePeriod, int workers, String businessPlace,
			String orgPhone) {
		
		this.businessId = businessId;
		this.customerId = customerId;
		this.businessName = businessName;
		this.businessType = businessType;
		this.ownedOrPartner = ownedOrPartner;
		this.period = period;
		this.currentPlacePeriod = currentPlacePeriod;
		this.workers = workers;
		this.businessPlace = businessPlace;
		this.orgPhone = orgPhone;
	}




	public String getOwnedOrPartner() {
		return ownedOrPartner;
	}

	public void setOwnedOrPartner(String ownedOrPartner) {
		this.ownedOrPartner = ownedOrPartner;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCurrentPlacePeriod() {
		return currentPlacePeriod;
	}

	public void setCurrentPlacePeriod(String currentPlacePeriod) {
		this.currentPlacePeriod = currentPlacePeriod;
	}

	public int getWorkers() {
		return workers;
	}

	public void setWorkers(int workers) {
		this.workers = workers;
	}

	public String getBusinessPlace() {
		return businessPlace;
	}

	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	@Override
	public String toString() {
		return "BusinessDetails [businessId=" + businessId + ", customerId="
				+ customerId + ", businessName=" + businessName
				+ ", businessType=" + businessType + ", ownedOrPartner="
				+ ownedOrPartner + ", period=" + period
				+ ", currentPlacePeriod=" + currentPlacePeriod + ", workers="
				+ workers + ", businessPlace=" + businessPlace + ", orgPhone="
				+ orgPhone + ", getOwnedOrPartner()=" + getOwnedOrPartner()
				+ ", getBusinessId()=" + getBusinessId() + ", getCustomerId()="
				+ getCustomerId() + ", getBusinessName()=" + getBusinessName()
				+ ", getBusinessType()=" + getBusinessType() + ", getPeriod()="
				+ getPeriod() + ", getCurrentPlacePeriod()="
				+ getCurrentPlacePeriod() + ", getWorkers()=" + getWorkers()
				+ ", getBusinessPlace()=" + getBusinessPlace()
				+ ", getOrgPhone()=" + getOrgPhone() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
