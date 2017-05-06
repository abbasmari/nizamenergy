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
public class AlHBean {
    
    
    private String first_name;
    private String phone_no;
    private String address;
    private int appliance_id;
    private Date alerts_date;
    private int alerts_id;
    private boolean panel_lost;
    private String connection;
    private String battery_low;
    private String panel;
    private String change_control_lost;
    private String status;
    public AlHBean() {
    }

    public AlHBean(String first_name, String phone_no, String address, int appliance_id, Date alerts_date, int alerts_id, boolean panel_lost, String connection, String battery_low, String panel, String change_control_lost, String status ) {
        this.first_name = first_name;
        this.phone_no = phone_no;
        this.address = address;
        this.appliance_id = appliance_id;
        this.alerts_date = alerts_date;
        this.alerts_id = alerts_id;
        this.panel_lost = panel_lost;
        this.connection = connection;
        this.battery_low = battery_low;
        this.panel = panel;
        this.change_control_lost = change_control_lost;
        this.status=status;
        
    }

    public String getChange_control_lost() {
        return change_control_lost;
    }

    public void setChange_control_lost(String change_control_lost) {
        this.change_control_lost = change_control_lost;
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

    public int getAppliance_id() {
        return appliance_id;
    }

    public void setAppliance_id(int appliance_id) {
        this.appliance_id = appliance_id;
    }

    public Date getAlerts_date() {
        return alerts_date;
    }

    public void setAlerts_date(Date alerts_date) {
        this.alerts_date = alerts_date;
    }

    public int getAlerts_id() {
        return alerts_id;
    }

    public void setAlerts_id(int alerts_id) {
        this.alerts_id = alerts_id;
    }

    public boolean isPanel_lost() {
        return panel_lost;
    }

    public void setPanel_lost(boolean panel_lost) {
        this.panel_lost = panel_lost;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "AlHBean{" + "first_name=" + first_name + ", phone_no=" + phone_no + ", address=" + address + ", appliance_id=" + appliance_id + ", alerts_date=" + alerts_date + ", alerts_id=" + alerts_id + ", panel_lost=" + panel_lost + ", connection=" + connection + ", battery_low=" + battery_low + ", panel=" + panel + ", change_control_lost=" + change_control_lost + '}';
    }
    
    
}
