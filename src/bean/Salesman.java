/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author Jeatnder Khatri
 */
public class Salesman {

    private int salesmanId;
    private String Name;
    private String cnicNo;
    private String district;
    private String address;
    private String phoneNo;
    private double basicSalary;
    private Date joiningDate;
    private int lateCustomer;
    private int totalSales;
    private int userId;
    private String userName;
    private String districtName; 
    
    public Salesman() {
    }

    public Salesman(int salesmanId, String Name, String cnicNo, String district, String address, String phoneNo, double basicSalary, Date joiningDate, int lateCustomer) {
        this.salesmanId = salesmanId;
        this.Name = Name;
        this.cnicNo = cnicNo;
        this.district = district;
        this.address = address;
        this.phoneNo = phoneNo;
        this.basicSalary = basicSalary;
        this.joiningDate = joiningDate;
        this.lateCustomer = lateCustomer;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCnicNo() {
        return cnicNo;
    }

    public void setCnicNo(String cnicNo) {
        this.cnicNo = cnicNo;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getLateCustomer() {
        return lateCustomer;
    }

    public void setLateCustomer(int lateCustomer) {
        this.lateCustomer = lateCustomer;
    }

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
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
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return "Salesman [salesmanId=" + salesmanId + ", Name=" + Name
				+ ", cnicNo=" + cnicNo + ", district=" + district
				+ ", address=" + address + ", phoneNo=" + phoneNo
				+ ", basicSalary=" + basicSalary + ", joiningDate="
				+ joiningDate + ", lateCustomer=" + lateCustomer
				+ ", totalSales=" + totalSales + ", userId=" + userId
				+ ", userName=" + userName + "]";
	}

   
    
}
