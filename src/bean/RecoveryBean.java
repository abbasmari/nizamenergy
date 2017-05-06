/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;


public class RecoveryBean {
    
    private int total_customers;
    private int customer_on_installments;
    private int installment_amount;
    private String app_name;
    private int rate;
    private int late_customer;
    private int paid_late;
    private double Recored_amount;
    private String salesman_name;
    private String district_name;
    private int latecustomer;
    private int salesman_id;
    private String customer_name;
    private String phone_number;
    private String address;
    private String customer_district;
    private String appliace_number;
    private Boolean status;
    private int total_installments;
    private Date due_date;
    private int grace;

    public int getTotal_customers() {
        return total_customers;
    }

    public void setTotal_customers(int total_customers) {
        this.total_customers = total_customers;
    }

    public int getCustomer_on_installments() {
        return customer_on_installments;
    }

    public void setCustomer_on_installments(int customer_on_installments) {
        this.customer_on_installments = customer_on_installments;
    }

    public int getInstallment_amount() {
        return installment_amount;
    }

    public void setInstallment_amount(int installment_amount) {
        this.installment_amount = installment_amount;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLate_customer() {
        return late_customer;
    }

    public void setLate_customer(int late_customer) {
        this.late_customer = late_customer;
    }

    public int getPaid_late() {
        return paid_late;
    }

    public void setPaid_late(int paid_late) {
        this.paid_late = paid_late;
    }

    public double getRecored_amount() {
        return Recored_amount;
    }

    public void setRecored_amount(double Recored_amount) {
        this.Recored_amount = Recored_amount;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public int getLatecustomer() {
        return latecustomer;
    }

    public void setLatecustomer(int latecustomer) {
        this.latecustomer = latecustomer;
    }

    public int getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(int salesman_id) {
        this.salesman_id = salesman_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer_district() {
        return customer_district;
    }

    public void setCustomer_district(String customer_district) {
        this.customer_district = customer_district;
    }

    public String getAppliace_number() {
        return appliace_number;
    }

    public void setAppliace_number(String appliace_number) {
        this.appliace_number = appliace_number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getTotal_installments() {
        return total_installments;
    }

    public void setTotal_installments(int total_installments) {
        this.total_installments = total_installments;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getGrace() {
        return grace;
    }

    public void setGrace(int grace) {
        this.grace = grace;
    }

    public RecoveryBean() {
    }

    public RecoveryBean(int total_customers, int customer_on_installments, int installment_amount, String app_name, int rate, int late_customer, int paid_late, double Recored_amount, String salesman_name, String district_name, int latecustomer, int salesman_id, String customer_name, String phone_number, String address, String customer_district, String appliace_number, Boolean status, int total_installments, Date due_date, int grace) {
        this.total_customers = total_customers;
        this.customer_on_installments = customer_on_installments;
        this.installment_amount = installment_amount;
        this.app_name = app_name;
        this.rate = rate;
        this.late_customer = late_customer;
        this.paid_late = paid_late;
        this.Recored_amount = Recored_amount;
        this.salesman_name = salesman_name;
        this.district_name = district_name;
        this.latecustomer = latecustomer;
        this.salesman_id = salesman_id;
        this.customer_name = customer_name;
        this.phone_number = phone_number;
        this.address = address;
        this.customer_district = customer_district;
        this.appliace_number = appliace_number;
        this.status = status;
        this.total_installments = total_installments;
        this.due_date = due_date;
        this.grace = grace;
    }

    @Override
    public String toString() {
        return "RecoveryBean{" + "total_customers=" + total_customers + ", customer_on_installments=" + customer_on_installments + ", installment_amount=" + installment_amount + ", app_name=" + app_name + ", rate=" + rate + ", late_customer=" + late_customer + ", paid_late=" + paid_late + ", Recored_amount=" + Recored_amount + ", salesman_name=" + salesman_name + ", district_name=" + district_name + ", latecustomer=" + latecustomer + ", salesman_id=" + salesman_id + ", customer_name=" + customer_name + ", phone_number=" + phone_number + ", address=" + address + ", customer_district=" + customer_district + ", appliace_number=" + appliace_number + ", status=" + status + ", total_installments=" + total_installments + ", due_date=" + due_date + ", grace=" + grace + '}';
    }
    
    
     
}