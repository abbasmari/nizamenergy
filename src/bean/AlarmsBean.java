package bean;

public class AlarmsBean {
	
	private int alarmsId;
	private int applianceId;
    private double latitude;
    private double longitude;
    private String IMEI;
    private boolean betteryLevel;
    private boolean solarPower;
    private boolean currentLoad;
    private boolean temperature;
    private boolean lidOpen;
    private boolean signalStrength;
    private boolean commonFault;
    private boolean batteryOvercharge;
    private boolean gsm_suicide;

    
    
    /**
	 * @return the commonFault
	 */
	public boolean isCommonFault() {
		return commonFault;
	}

	/**
	 * @param commonFault the commonFault to set
	 */
	public void setCommonFault(boolean commonFault) {
		this.commonFault = commonFault;
	}

	/**
	 * @return the batteryOvercharge
	 */
	public boolean isBatteryOvercharge() {
		return batteryOvercharge;
	}

	/**
	 * @param batteryOvercharge the batteryOvercharge to set
	 */
	public void setBatteryOvercharge(boolean batteryOvercharge) {
		this.batteryOvercharge = batteryOvercharge;
	}

	/**
	 * @return the gsm_suicide
	 */
	public boolean isGsm_suicide() {
		return gsm_suicide;
	}

	/**
	 * @param gsm_suicide the gsm_suicide to set
	 */
	public void setGsm_suicide(boolean gsm_suicide) {
		this.gsm_suicide = gsm_suicide;
	}

	public AlarmsBean() {
		// TODO Auto-generated constructor stub
	}

	public int getAlarmsId() {
		return alarmsId;
	}

	public void setAlarmsId(int alarmsId) {
		this.alarmsId = alarmsId;
	}

	public int getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public boolean isBetteryLevel() {
		return betteryLevel;
	}

	public void setBetteryLevel(boolean betteryLevel) {
		this.betteryLevel = betteryLevel;
	}

	public boolean isSolarPower() {
		return solarPower;
	}

	public void setSolarPower(boolean solarPower) {
		this.solarPower = solarPower;
	}

	public boolean isCurrentLoad() {
		return currentLoad;
	}

	public void setCurrentLoad(boolean currentLoad) {
		this.currentLoad = currentLoad;
	}

	public boolean isTemperature() {
		return temperature;
	}

	public void setTemperature(boolean temperature) {
		this.temperature = temperature;
	}

	public boolean isLidOpen() {
		return lidOpen;
	}

	public void setLidOpen(boolean lidOpen) {
		this.lidOpen = lidOpen;
	}

	public boolean isSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(boolean signalStrength) {
		this.signalStrength = signalStrength;
	}

	@Override
	public String toString() {
		return "AlarmsBean[alarmsId=" + alarmsId + ", applianceId=" + applianceId + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", IMEI=" + IMEI + ", betteryLevel=" + betteryLevel + ", solarPower="
				+ solarPower + ", currentLoad=" + currentLoad + ", temperature=" + temperature + ", lidOpen=" + lidOpen
				+ ", signalStrength=" + signalStrength + ", getAlarmsId()=" + getAlarmsId() + ", getApplianceId()="
				+ getApplianceId() + ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude()
				+ ", getIMEI()=" + getIMEI() + ", isBetteryLevel()=" + isBetteryLevel() + ", isSolarPower()="
				+ isSolarPower() + ", isCurrentLoad()=" + isCurrentLoad() + ", isTemperature()=" + isTemperature()
				+ ", isLidOpen()=" + isLidOpen() + ", isSignalStrength()=" + isSignalStrength() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}
