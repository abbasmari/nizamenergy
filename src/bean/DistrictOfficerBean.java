/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author waseem
 */
public class DistrictOfficerBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String district_name;
    private String do_name;
    private Double sale;
    private int totalsale;
    private int do_id;
    private String phone;
    private double sallery;
    private String cnic;
    private String address;
    private Double latitude;
    private Double longitutde;
    private String date;
    private String useremail;
    private String dateofbirth;
    private String dateofjoining;
    public DistrictOfficerBean() {
    }

    public DistrictOfficerBean(String district_name, String do_name, Double sale, int totalsale, int do_id, String phone, double sallery, String cnic, String address, Double latitude, Double longitutde) {
        this.district_name = district_name;
        this.do_name = do_name;
        this.sale = sale;
        this.totalsale = totalsale;
        this.do_id = do_id;
        this.phone = phone;
        this.sallery = sallery;
        this.cnic = cnic;
        this.address = address;
        this.latitude = latitude;
        this.longitutde = longitutde;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
    public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getDateofjoining() {
		return dateofjoining;
	}

	public void setDateofjoining(String dateofjoining) {
		this.dateofjoining = dateofjoining;
	}

	public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDo_name() {
        return do_name;
    }

    public void setDo_name(String do_name) {
        this.do_name = do_name;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public int getTotalsale() {
        return totalsale;
    }

    public void setTotalsale(int totalsale) {
        this.totalsale = totalsale;
    }

    public int getDo_id() {
        return do_id;
    }

    public void setDo_id(int do_id) {
        this.do_id = do_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSallery() {
        return sallery;
    }

    public void setSallery(double sallery) {
        this.sallery = sallery;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitutde() {
        return longitutde;
    }

    public void setLongitutde(Double longitutde) {
        this.longitutde = longitutde;
    }

    @Override
    public String toString() {
        return "DistrictOfficerBean{" + "district_name=" + district_name + ", do_name=" + do_name + ", sale=" + sale + ", totalsale=" + totalsale + ", do_id=" + do_id + ", phone=" + phone + ", sallery=" + sallery + ", cnic=" + cnic + ", address=" + address + ", latitude=" + latitude + ", longitutde=" + longitutde + '}';
    }
    
    

    

    
    
    
    
}
