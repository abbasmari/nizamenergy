/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author AqeelRahu
 */
public class AlertLiveBean {
    
    
    private int alerts_id;
    private int appliance_id;
    private String first_name;
    private String phone_no;
    private String address;
    private boolean pannel_lost;
    private String connection;
    private String battery_low;
    private String panel;
    private String change_control_lost;
    private Date AlertDate;

    public AlertLiveBean() {
    }

    public AlertLiveBean(int alerts_id, int appliance_id, String first_name, String phone_no, String address, boolean pannel_lost, String connection, String battery_low, String panel, String change_control_lost, Date AlertDate) {
        this.alerts_id = alerts_id;
        this.appliance_id = appliance_id;
        this.first_name = first_name;
        this.phone_no = phone_no;
        this.address = address;
        this.pannel_lost = pannel_lost;
        this.connection = connection;
        this.battery_low = battery_low;
        this.panel = panel;
        this.change_control_lost = change_control_lost;
        this.AlertDate = AlertDate;
    }

    public Date getAlertDate() {
        return AlertDate;
    }

    public void setAlertDate(Date AlertDate) {
        this.AlertDate = AlertDate;
    }
    
    public String getChange_control_lost() {
        return change_control_lost;
    }

    public void setChange_control_lost(String change_control_lost) {
        this.change_control_lost = change_control_lost;
    }

    
    public int getAlerts_id() {
        return alerts_id;
    }

    public void setAlerts_id(int alerts_id) {
        this.alerts_id = alerts_id;
    }

    public int getAppliance_id() {
        return appliance_id;
    }

    public void setAppliance_id(int appliance_id) {
        this.appliance_id = appliance_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getPannel_lost() {
        return pannel_lost;
    }

    public void setPannel_lost(boolean pannel_lost) {
        this.pannel_lost = pannel_lost;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getBattery_low() {
        return battery_low;
    }

    public void setBattery_low(String battery_low) {
        this.battery_low = battery_low;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    @Override
    public String toString() {
        return "AlertLiveBean{" + "alerts_id=" + alerts_id + ", appliance_id=" + appliance_id + ", first_name=" + first_name + ", phone_no=" + phone_no + ", address=" + address + ", pannel_lost=" + pannel_lost + ", connection=" + connection + ", battery_low=" + battery_low + ", panel=" + panel + ", change_control_lost=" + change_control_lost + ", AlertDate=" + AlertDate + '}';
    }

    
   
    
}
