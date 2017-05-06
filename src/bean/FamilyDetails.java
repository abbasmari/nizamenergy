package bean;

public class FamilyDetails {

	private int familyDetailId;
	private int customerId;
	private String memberName;
	private String relation;
	private boolean isProfessional; 
	private String occupation;
	
	public FamilyDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getFamilyDetailId() {
		return familyDetailId;
	}

	public void setFamilyDetailId(int familyDetailId) {
		this.familyDetailId = familyDetailId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public boolean isProfessional() {
		return isProfessional;
	}

	public void setProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	


	
}
