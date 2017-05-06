/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Jhaman-Khatri
 */
public class AlertsLogBean {
    
    Time logTime;
    Date logDate;
    int status;
    int task_status;
    boolean log_panel_lost;
    boolean log_lid_open;
    boolean log_battery_low;
    boolean log_location_changed;
    boolean temp_high;

    public AlertsLogBean() {
    }

    public AlertsLogBean(Time logTime, Date logDate, int status, int task_status, boolean log_panel_lost, boolean log_lid_open, boolean log_battery_low, boolean log_location_changed, boolean temp_high) {
        this.logTime = logTime;
        this.logDate = logDate;
        this.status = status;
        this.task_status = task_status;
        this.log_panel_lost = log_panel_lost;
        this.log_lid_open = log_lid_open;
        this.log_battery_low = log_battery_low;
        this.log_location_changed = log_location_changed;
        this.temp_high = temp_high;
    }

    public Time getLogTime() {
        return logTime;
    }

    public void setLogTime(Time logTime) {
        this.logTime = logTime;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTask_status() {
        return task_status;
    }

    public void setTask_status(int task_status) {
        this.task_status = task_status;
    }

    public boolean getLog_panel_lost() {
        return log_panel_lost;
    }

    public void setLog_panel_lost(boolean log_panel_lost) {
        this.log_panel_lost = log_panel_lost;
    }

    public boolean getLog_lid_open() {
        return log_lid_open;
    }

    public void setLog_lid_open(boolean log_lid_open) {
        this.log_lid_open = log_lid_open;
    }

    public boolean getLog_battery_low() {
        return log_battery_low;
    }

    public void setLog_battery_low(boolean log_battery_low) {
        this.log_battery_low = log_battery_low;
    }

    public boolean getLog_location_changed() {
        return log_location_changed;
    }

    public void setLog_location_changed(boolean log_location_changed) {
        this.log_location_changed = log_location_changed;
    }

    public boolean getTemp_high() {
        return temp_high;
    }

    public void setTemp_high(boolean temp_high) {
        this.temp_high = temp_high;
    }

    @Override
    public String toString() {
        return "AlertsLogBean{" + "logTime=" + logTime + ", logDate=" + logDate + ", status=" + status + ", task_status=" + task_status + ", log_panel_lost=" + log_panel_lost + ", log_lid_open=" + log_lid_open + ", log_battery_low=" + log_battery_low + ", log_location_changed=" + log_location_changed + ", temp_high=" + temp_high + '}';
    }

  

 
  
    
    
}
