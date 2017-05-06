package bean;

import bal.*;

public class MapBean {

    private int status_appliance_id;
    private int appliance_id;
    private double appliance_voltage;
    private double appliance_battery;
    private double appliance_temprature;
    private String appliance_environment;
    private boolean appliance_status;
    private double latitude;
    private double longitude;
    private int district_id;

    public MapBean() {
    }

    public MapBean(int status_appliance_id, int appliance_id, double appliance_voltage, double appliance_battery, double appliance_temprature, String appliance_environment, boolean appliance_status, double latitude, double longitude) {
        this.status_appliance_id = status_appliance_id;
        this.appliance_id = appliance_id;
        this.appliance_voltage = appliance_voltage;
        this.appliance_battery = appliance_battery;
        this.appliance_temprature = appliance_temprature;
        this.appliance_environment = appliance_environment;
        this.appliance_status = appliance_status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getStatus_appliance_id() {
        return status_appliance_id;
    }

    public void setStatus_appliance_id(int status_appliance_id) {
        this.status_appliance_id = status_appliance_id;
    }

    public int getAppliance_id() {
        return appliance_id;
    }

    public void setAppliance_id(int appliance_id) {
        this.appliance_id = appliance_id;
    }

    public double getAppliance_voltage() {
        return appliance_voltage;
    }

    public void setAppliance_voltage(double appliance_voltage) {
        this.appliance_voltage = appliance_voltage;
    }

    public double getAppliance_battery() {
        return appliance_battery;
    }

    public void setAppliance_battery(double appliance_battery) {
        this.appliance_battery = appliance_battery;
    }

    public double getAppliance_temprature() {
        return appliance_temprature;
    }

    public void setAppliance_temprature(double appliance_temprature) {
        this.appliance_temprature = appliance_temprature;
    }

    public String getAppliance_environment() {
        return appliance_environment;
    }

    public void setAppliance_environment(String appliance_environment) {
        this.appliance_environment = appliance_environment;
    }

    public boolean isAppliance_status() {
        return appliance_status;
    }

    public void setAppliance_status(boolean appliance_status) {
        this.appliance_status = appliance_status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    
    @Override
    public String toString() {
        return "MapBean{" + "status_appliance_id=" + status_appliance_id + ", appliance_id=" + appliance_id + ", appliance_voltage=" + appliance_voltage + ", appliance_battery=" + appliance_battery + ", appliance_temprature=" + appliance_temprature + ", appliance_environment=" + appliance_environment + ", appliance_status=" + appliance_status + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
