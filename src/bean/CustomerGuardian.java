package bean;

public class CustomerGuardian {
	
	private int guardianId;
	private int customerId;
	private String guardianName;
	private String phone_no;
	private int familyMember;
	
	public CustomerGuardian() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public CustomerGuardian(int guardianId, int customerId,
			String guardianName, String phone_no, int familyMember) {
		
		this.guardianId = guardianId;
		this.customerId = customerId;
		this.guardianName = guardianName;
		this.phone_no = phone_no;
		this.familyMember = familyMember;
	}




	public int getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(int guardianId) {
		this.guardianId = guardianId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}


	public int getFamilyMember() {
		return familyMember;
	}


	public void setFamilyMember(int familyMember) {
		this.familyMember = familyMember;
	}




	@Override
	public String toString() {
		return "CustomerGuardian [guardianId=" + guardianId + ", customerId="
				+ customerId + ", guardianName=" + guardianName + ", phone_no="
				+ phone_no + ", familyMember=" + familyMember + "]";
	}
	
	
	
	
}
