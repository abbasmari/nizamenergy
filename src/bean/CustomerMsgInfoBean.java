package bean;

public class CustomerMsgInfoBean {
private int applianceId, customerId, salesmanId, e_status,state;

public CustomerMsgInfoBean() {
	
}

public CustomerMsgInfoBean(int applianceId, int customerId, int salesmanId,
		int e_status, int state) {
	
	this.applianceId = applianceId;
	this.customerId = customerId;
	this.salesmanId = salesmanId;
	this.e_status = e_status;
	this.state = state;
}

public int getApplianceId() {
	return applianceId;
}

public void setApplianceId(int applianceId) {
	this.applianceId = applianceId;
}

public int getCustomerId() {
	return customerId;
}

public void setCustomerId(int customerId) {
	this.customerId = customerId;
}

public int getSalesmanId() {
	return salesmanId;
}

public void setSalesmanId(int salesmanId) {
	this.salesmanId = salesmanId;
}

public int getE_status() {
	return e_status;
}

public void setE_status(int e_status) {
	this.e_status = e_status;
}

public int getState() {
	return state;
}

public void setState(int state) {
	this.state = state;
}

@Override
public String toString() {
	return "CustomerMsgInfoBean [applianceId=" + applianceId + ", customerId="
			+ customerId + ", salesmanId=" + salesmanId + ", e_status="
			+ e_status + ", state=" + state + "]";
}
	

	
}
