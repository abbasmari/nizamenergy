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
public class CustomerInstallments {
    
    private int installmentId;
    private int soldtoId;
    private int customerId;
    private double insatllmentAmount;
    private Date payedDate;

    public CustomerInstallments() {
    }

    public CustomerInstallments(int installmentId, int soldtoId, int customerId, double insatllmentAmount, Date payedDate) {
        this.installmentId = installmentId;
        this.soldtoId = soldtoId;
        this.customerId = customerId;
        this.insatllmentAmount = insatllmentAmount;
        this.payedDate = payedDate;
    }

    public int getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(int installmentId) {
        this.installmentId = installmentId;
    }

    public int getSoldtoId() {
        return soldtoId;
    }

    public void setSoldtoId(int soldtoId) {
        this.soldtoId = soldtoId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getInsatllmentAmount() {
        return insatllmentAmount;
    }

    public void setInsatllmentAmount(double insatllmentAmount) {
        this.insatllmentAmount = insatllmentAmount;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    @Override
    public String toString() {
        return "CustomerInstallments{" + "installmentId=" + installmentId + ", soldtoId=" + soldtoId + ", customerId=" + customerId + ", insatllmentAmount=" + insatllmentAmount + ", payedDate=" + payedDate + '}';
    }
    
    
    
    
    
    
    
    
}
