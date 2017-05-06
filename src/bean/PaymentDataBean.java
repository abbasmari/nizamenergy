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
public class PaymentDataBean {
    private int soldId;
    private double totalAmount;
    private int totalInstalment;
    private double payedAmount;
    private double monthlyAmount;
    private Date createdDate;

    public PaymentDataBean() {}

    public int getSoldId() {
        return soldId;
    }

    public void setSoldId(int soldId) {
        this.soldId = soldId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalInstalment() {
        return totalInstalment;
    }

    public void setTotalInstalment(int totalInstalment) {
        this.totalInstalment = totalInstalment;
    }

    public double getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(double payedAmount) {
        this.payedAmount = payedAmount;
    }

    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "PaymentDataBean{" + "soldId=" + soldId + ", totalAmount=" + totalAmount + ", totalInstalment=" + totalInstalment + ", payedAmount=" + payedAmount + ", monthlyAmount=" + monthlyAmount + ", createdDate=" + createdDate + '}';
    }
    
}
