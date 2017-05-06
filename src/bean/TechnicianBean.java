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
public class TechnicianBean {

    private int id;
    private int doId;
    private String name;
    private String phone;
    private String address;
    private double salary;
    private String cnic;

    private Date joiningDate;

    public TechnicianBean() {
    }

    public TechnicianBean(int id, int doId, String name, String phone, String address, double salary, String cnic,  Date joiningDate) {
        this.id = id;
        this.doId = doId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.cnic = cnic;
      
        this.joiningDate = joiningDate;
    }

  

  
    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

 

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoId() {
        return doId;
    }

    public void setDoId(int doId) {
        this.doId = doId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "TechnicianBean{" + "id=" + id + ", doId=" + doId + ", name=" + name + ", phone=" + phone + ", address=" + address + ", salary=" + salary + ", cnic=" + cnic + ", joiningDate=" + joiningDate + '}';
    }

 

    
    

}
