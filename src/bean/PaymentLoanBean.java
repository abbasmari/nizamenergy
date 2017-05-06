/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Jeatnder Khatri
 */
public class PaymentLoanBean {
    
    private String appliances_Number;
    private Boolean status;
    private String customer_name;
    private String customer_phone;
    private String district;
    private Double loan_amount;
    private Double remaining_balance;
    private int grace_period;
    private Double paid_amount;
    private int appliance_key;
    private int loan_key;

    public PaymentLoanBean() {
    }

    public PaymentLoanBean(String appliances_Number, Boolean status, String customer_name, String customer_phone, String district, Double loan_amount, Double remaining_balance, int grace_period, Double paid_amount, int appliance_key, int loan_key) {
        this.appliances_Number = appliances_Number;
        this.status = status;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.district = district;
        this.loan_amount = loan_amount;
        this.remaining_balance = remaining_balance;
        this.grace_period = grace_period;
        this.paid_amount = paid_amount;
        this.appliance_key = appliance_key;
        this.loan_key = loan_key;
    }

    public String getAppliances_Number() {
        return appliances_Number;
    }

    public void setAppliances_Number(String appliances_Number) {
        this.appliances_Number = appliances_Number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(Double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Double getRemaining_balance() {
        return remaining_balance;
    }

    public void setRemaining_balance(Double remaining_balance) {
        this.remaining_balance = remaining_balance;
    }

    public int getGrace_period() {
        return grace_period;
    }

    public void setGrace_period(int grace_period) {
        this.grace_period = grace_period;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public int getAppliance_key() {
        return appliance_key;
    }

    public void setAppliance_key(int appliance_key) {
        this.appliance_key = appliance_key;
    }

    public int getLoan_key() {
        return loan_key;
    }

    public void setLoan_key(int loan_key) {
        this.loan_key = loan_key;
    }

    @Override
    public String toString() {
        return "PaymentLoanBean{" + "appliances_Number=" + appliances_Number + ", status=" + status + ", customer_name=" + customer_name + ", customer_phone=" + customer_phone + ", district=" + district + ", loan_amount=" + loan_amount + ", remaining_balance=" + remaining_balance + ", grace_period=" + grace_period + ", paid_amount=" + paid_amount + ", appliance_key=" + appliance_key + ", loan_key=" + loan_key + '}';
    }
    
    
}
