package bean;

import java.sql.Time;

public class AlertsBean {

    private int alertsId;
    private int applianceId;
    private int taskStatus;
    private boolean pannel_lost;
    private String connection;
    private boolean lidOpen;
    private Time time;
    private int techId;
    private boolean battery_low;
    private String panel;
    private boolean change_control_lost;
    private String alerts_date;
//    private String time;
    private String ticket;
    private String status;
    private boolean location_changed;
    private boolean temp_high;
    private String technician;
    private String elapseTime;

    public AlertsBean() {
    }

    public AlertsBean(int alertsId, int applianceId, int taskStatus, boolean pannel_lost, String connection, boolean lidOpen, Time time, int techId, boolean battery_low, String panel, boolean change_control_lost, String alerts_date, String ticket, String status, boolean location_changed, boolean temp_high, String technician, String elapseTime) {
        this.alertsId = alertsId;
        this.applianceId = applianceId;
        this.taskStatus = taskStatus;
        this.pannel_lost = pannel_lost;
        this.connection = connection;
        this.lidOpen = lidOpen;
        this.time = time;
        this.techId = techId;
        this.battery_low = battery_low;
        this.panel = panel;
        this.change_control_lost = change_control_lost;
        this.alerts_date = alerts_date;
        this.ticket = ticket;
        this.status = status;
        this.location_changed = location_changed;
        this.temp_high = temp_high;
        this.technician = technician;
        this.elapseTime = elapseTime;
    }

    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }
    
     public boolean getLidOpen() {
        return lidOpen;
    }

    public void setLidOpen(boolean lidOpen) {
        this.lidOpen = lidOpen;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getLocation_changed() {
        return location_changed;
    }

    public void setLocation_changed(boolean location_changed) {
        this.location_changed = location_changed;
    }

    public boolean getTemp_high() {
        return temp_high;
    }

    public void setTemp_high(boolean temp_high) {
        this.temp_high = temp_high;
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

    public boolean getBattery_low() {
        return battery_low;
    }

    public void setBattery_low(boolean battery_low) {
        this.battery_low = battery_low;
    }

    public boolean getChange_control_lost() {
        return change_control_lost;
    }

    public void setChange_control_lost(boolean change_control_lost) {
        this.change_control_lost = change_control_lost;
    }

   

 

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

   

    public String getAlerts_date() {
        return alerts_date;
    }

    public void setAlerts_date(String alerts_date) {
        this.alerts_date = alerts_date;
    }

    public int getAlertsId() {
        return alertsId;
    }

    public void setAlertsId(int alertsId) {
        this.alertsId = alertsId;
    }

    public int getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(int applianceId) {
        this.applianceId = applianceId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getElapseTime() {
        return elapseTime;
    }

    public void setElapseTime(String elapseTime) {
        this.elapseTime = elapseTime;
    }
    
    

    public AlertsBean(int alertsId, int applianceId, int taskStatus, boolean pannel_lost, String connection, boolean lidOpen, Time time, boolean battery_low, String panel, boolean change_control_lost, String alerts_date, String ticket, String status, boolean location_changed, boolean temp_high, String technician, String elapseTime) {
        this.alertsId = alertsId;
        this.applianceId = applianceId;
        this.taskStatus = taskStatus;
        this.pannel_lost = pannel_lost;
        this.connection = connection;
        this.lidOpen = lidOpen;
        this.time = time;
        this.battery_low = battery_low;
        this.panel = panel;
        this.change_control_lost = change_control_lost;
        this.alerts_date = alerts_date;
        this.ticket = ticket;
        this.status = status;
        this.location_changed = location_changed;
        this.temp_high = temp_high;
        this.technician = technician;
        this.elapseTime = elapseTime;
    }

    @Override
    public String toString() {
        return "AlertsBean{" + "alertsId=" + alertsId + ", applianceId=" + applianceId + ", taskStatus=" + taskStatus + ", pannel_lost=" + pannel_lost + ", connection=" + connection + ", lidOpen=" + lidOpen + ", time=" + time + ", battery_low=" + battery_low + ", panel=" + panel + ", change_control_lost=" + change_control_lost + ", alerts_date=" + alerts_date + ", ticket=" + ticket + ", status=" + status + ", location_changed=" + location_changed + ", temp_high=" + temp_high + ", technician=" + technician + ", elapseTime=" + elapseTime + '}';
    }

   

   

  
   
   

}
