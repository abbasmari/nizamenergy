package bean;

import java.io.InputStream;

public class CustomerBean {
    
    private int customerId;
    private String customerName;
    private String cnicNo;
    private String phoneNo;
    private String phoneNo2;
    private String district;
    private String address;
    private String occpation;
    
    private Double monthlyIncome;
    private Double familyIncome;
    private String paymentMethod;
    private InputStream customer_pic;
    private String accountCreatedDate;
    private int status;
    private int FamilyMember;
    private String fatherName;
    private String motherName;
    private String email;
    private String telephoneNo;
    private String gender;
    private String relationStatus;
    private String education;
    private String sourceOfIncome;
    private int age;
    private boolean educated;
    private String dateOfBirth;
    private String Customer_pic;
    private byte image;
    
    public byte getImage() {
		return image;
	}








	



	
    public CustomerBean() {}
    
	
    





	public void setCustomer_pic(String customer_pic) {
		Customer_pic = customer_pic;
	}








	public CustomerBean(int customerId, String customerName, String cnicNo,
			String phoneNo, String phoneNo2, String district, String address,
			String occpation, Double monthlyIncome, Double familyIncome,
			String paymentMethod, InputStream customer_pic,
			String accountCreatedDate, int status, int familyMember,
			String fatherName, String motherName, String email,
			String telephoneNo, String gender, String relationStatus,
			String education, String sourceOfIncome, int age, boolean educated,
			String dateOfBirth, String customer_pic2) {
		
		this.customerId = customerId;
		this.customerName = customerName;
		this.cnicNo = cnicNo;
		this.phoneNo = phoneNo;
		this.phoneNo2 = phoneNo2;
		this.district = district;
		this.address = address;
		this.occpation = occpation;
		this.monthlyIncome = monthlyIncome;
		this.familyIncome = familyIncome;
		this.paymentMethod = paymentMethod;
		this.customer_pic = customer_pic;
		this.accountCreatedDate = accountCreatedDate;
		this.status = status;
		FamilyMember = familyMember;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.email = email;
		this.telephoneNo = telephoneNo;
		this.gender = gender;
		this.relationStatus = relationStatus;
		this.education = education;
		this.sourceOfIncome = sourceOfIncome;
		this.age = age;
		this.educated = educated;
		this.dateOfBirth = dateOfBirth;
		Customer_pic = customer_pic2;
	}








	public int getFamilyMember() {
		return FamilyMember;
	}








	public void setFamilyMember(int familyMember) {
		FamilyMember = familyMember;
	}














	public int getStatus() {
		return status;
	}

	


	public void setStatus(int status) {
		this.status = status;
	}
	



	public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCnicNo() {
        return cnicNo;
    }

    public void setCnicNo(String cnicNo) {
        this.cnicNo = cnicNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    
    public String getPhoneNo2() {
		return phoneNo2;
	}

	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

	public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccpation() {
        return occpation;
    }

    public void setOccpation(String occpation) {
        this.occpation = occpation;
    }

   
    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(Double familyIncome) {
        this.familyIncome = familyIncome;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public InputStream getCustomer_pic() {
        return customer_pic;
    }

    public void setCustomer_pic(InputStream customer_pic) {
        this.customer_pic = customer_pic;
    }

    public String getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(String accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }
    
    public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(String relationStatus) {
		this.relationStatus = relationStatus;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSourceOfIncome() {
		return sourceOfIncome;
	}

	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public boolean isEducated() {
		return educated;
	}








	public void setEducated(boolean educated) {
		this.educated = educated;
	}








	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String date) {
		this.dateOfBirth = date;
	}








	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", customerName="
				+ customerName + ", cnicNo=" + cnicNo + ", phoneNo=" + phoneNo
				+ ", phoneNo2=" + phoneNo2 + ", district=" + district
				+ ", address=" + address + ", occpation=" + occpation
				+ ", monthlyIncome=" + monthlyIncome + ", familyIncome="
				+ familyIncome + ", paymentMethod=" + paymentMethod
				+ ", customer_pic=" + customer_pic + ", accountCreatedDate="
				+ accountCreatedDate + ", status=" + status + ", FamilyMember="
				+ FamilyMember + ", fatherName=" + fatherName + ", motherName="
				+ motherName + ", email=" + email + ", telephoneNo="
				+ telephoneNo + ", gender=" + gender + ", relationStatus="
				+ relationStatus + ", education=" + education
				+ ", sourceOfIncome=" + sourceOfIncome + ", age=" + age
				+ ", educated=" + educated + ", dateOfBirth=" + dateOfBirth
				+ ", Customer_pic=" + Customer_pic + ", image=" + image + "]";
	}





	

	
    
}
