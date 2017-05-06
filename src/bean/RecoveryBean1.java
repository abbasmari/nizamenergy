/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


public class RecoveryBean1 {
    
    private int total_customers;
    private int customer_on_installments;
    private double installment_amount;
    private String app_name;
    private int rate;
    private int late_customer;
    private int paid_late;
    private double Recored_amount;

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

    public double getInstallment_amount() {
        return installment_amount;
    }

    public void setInstallment_amount(double installment_amount) {
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

    public RecoveryBean1() {
    }

    public RecoveryBean1(int total_customers, int customer_on_installments, double installment_amount, String app_name, int rate, int late_customer, int paid_late, double Recored_amount) {
        this.total_customers = total_customers;
        this.customer_on_installments = customer_on_installments;
        this.installment_amount = installment_amount;
        this.app_name = app_name;
        this.rate = rate;
        this.late_customer = late_customer;
        this.paid_late = paid_late;
        this.Recored_amount = Recored_amount;
    }

    @Override
    public String toString() {
        return "RecoveryBean{" + "total_customers=" + total_customers + ", customer_on_installments=" + customer_on_installments + ", installment_amount=" + installment_amount + ", app_name=" + app_name + ", rate=" + rate + ", late_customer=" + late_customer + ", paid_late=" + paid_late + ", Recored_amount=" + Recored_amount + '}';
    }

}