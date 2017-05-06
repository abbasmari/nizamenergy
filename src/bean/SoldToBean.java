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
public class SoldToBean {
    
    private int soldId;
    private int customerId;
    private int salesManId;
    private int applianceId;
    private Date soldDate;
    private String payamentType;
    private int applianceOption;
    private Date handoverDate;
    private boolean state;
    private double downPayment;

    public SoldToBean() {}

    public SoldToBean(int soldId, int customerId, int salesManId, int applianceId, Date soldDate, String payamentType, int applianceOption, Date handoverDate, boolean state, double downPayment) {
        this.soldId = soldId;
        this.customerId = customerId;
        this.salesManId = salesManId;
        this.applianceId = applianceId;
        this.soldDate = soldDate;
        this.payamentType = payamentType;
        this.applianceOption = applianceOption;
        this.handoverDate = handoverDate;
        this.state = state;
        this.downPayment = downPayment;
    }

    public int getSoldId() {
        return soldId;
    }

    public void setSoldId(int soldId) {
        this.soldId = soldId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalesManId() {
        return salesManId;
    }

    public void setSalesManId(int salesManId) {
        this.salesManId = salesManId;
    }

    public int getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(int applianceId) {
        this.applianceId = applianceId;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public String getPayamentType() {
        return payamentType;
    }

    public void setPayamentType(String payamentType) {
        this.payamentType = payamentType;
    }

    public int getApplianceOption() {
        return applianceOption;
    }

    public void setApplianceOption(int applianceOption) {
        this.applianceOption = applianceOption;
    }

    public Date getHandoverDate() {
        return handoverDate;
    }

    public void setHandoverDate(Date handoverDate) {
        this.handoverDate = handoverDate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    @Override
    public String toString() {
        return "SoldToBean{" + "soldId=" + soldId + ", customerId=" + customerId + ", salesManId=" + salesManId + ", applianceId=" + applianceId + ", soldDate=" + soldDate + ", payamentType=" + payamentType + ", applianceOption=" + applianceOption + ", handoverDate=" + handoverDate + ", state=" + state + ", downPayment=" + downPayment + '}';
    }
    
}
