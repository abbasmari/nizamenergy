/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author waseem
 */
public class paymentnewBean {
    
    private Date duedate;
    private double installment;
    private double paid_amount;
    private double monthlyPayment;
    private boolean status;
    private double remainingbalnce;
    private int graceperiod;
    private boolean payment_method;
    private Date paiddate;
    private int scheme;

    public int getScheme() {
        return scheme;
    }

    public void setScheme(int scheme) {
        this.scheme = scheme;
    }
    

    public paymentnewBean(){}

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    
    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public double getInstallment() {
        return installment;
    }

    public void setInstallment(double installment) {
        this.installment = installment;
    }

    public double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getRemainingbalnce() {
        return remainingbalnce;
    }

    public void setRemainingbalnce(double remainingbalnce) {
        this.remainingbalnce = remainingbalnce;
    }

    public int getGraceperiod() {
        return graceperiod;
    }

    public void setGraceperiod(int graceperiod) {
        this.graceperiod = graceperiod;
    }

    public boolean isPayment_method() {
        return payment_method;
    }

    public void setPayment_method(boolean payment_method) {
        this.payment_method = payment_method;
    }

    public Date getPaiddate() {
        return paiddate;
    }

    public void setPaiddate(Date paiddate) {
        this.paiddate = paiddate;
    }

    @Override
    public String toString() {
        return "paymentnewBean{" + "duedate=" + duedate + ", installment=" + installment + ", paid_amount=" + paid_amount + ", monthlyPayment=" + monthlyPayment + ", status=" + status + ", remainingbalnce=" + remainingbalnce + ", graceperiod=" + graceperiod + ", payment_method=" + payment_method + ", paiddate=" + paiddate + '}';
    }
    
    
    
}
