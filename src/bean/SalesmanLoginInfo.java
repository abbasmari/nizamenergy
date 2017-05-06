package bean;

import java.util.Date;

public class SalesmanLoginInfo {

	public final static String SALESMAN_ID = "salesmanId";
	public final static String SALESMAN_PHONE = "salesmanPhone";
	public final static String SALESMAN_NAME = "salesmanName";
	public final static String SALESMAN_CNIC = "salesmanCnic";
	public final static String SALESMAN_TARGET = "salesmanTargets";
	public final static String TARGET_START_ON = "targetStartOn";
	public final static String TARGET_END_ON = "targetEndOn";
	public final static String BEFORE_TIME_PERC = "beforeTimePerc";
	public final static String ON_TIME_PERC = "onTimePerc";
	public final static String AFTER_TIME_PERC = "afterTimePerc";
	public final static String SALEMAN_IMG = "salemanImg";
	public final static String SALEMAN_JOIN_DATE = "joinDate";

	private int salesmanId;

	private String salesmanPhone;
	private String salesmanName;
	private String salesmanCnic;

	private int salesmanTarget;

	private Date targetStartOn;
	private Date targetEndOn;

	private double beforeTimePerc;
	private double onTimePerc;
	private double afterTimePerc;

	private String salemanImg;
	private String joinDate;

	public SalesmanLoginInfo() {
		super();
	}

	public SalesmanLoginInfo(int salesmanId, String salesmanPhone,
			String salesmanName, String salesmanCnic, int salesmanTarget,
			Date targetStartOn, Date targetEndOn, double beforeTimePerc,
			double onTimePerc, double afterTimePerc, String salemanImg,
			String joinDate) {
		super();
		this.salesmanId = salesmanId;
		this.salesmanPhone = salesmanPhone;
		this.salesmanName = salesmanName;
		this.salesmanCnic = salesmanCnic;
		this.salesmanTarget = salesmanTarget;
		this.targetStartOn = targetStartOn;
		this.targetEndOn = targetEndOn;
		this.beforeTimePerc = beforeTimePerc;
		this.onTimePerc = onTimePerc;
		this.afterTimePerc = afterTimePerc;

		this.salemanImg = salemanImg;
		this.joinDate = joinDate;
	}

	public int getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesmanPhone() {
		return salesmanPhone;
	}

	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getSalesmanCnic() {
		return salesmanCnic;
	}

	public void setSalesmanCnic(String salesmanCnic) {
		this.salesmanCnic = salesmanCnic;
	}

	public int getSalesmanTarget() {
		return salesmanTarget;
	}

	public void setSalesmanTarget(int salesmanTarget) {
		this.salesmanTarget = salesmanTarget;
	}

	public Date getTargetStartOn() {
		return targetStartOn;
	}

	public void setTargetStartOn(Date targetStartOn) {
		this.targetStartOn = targetStartOn;
	}

	public Date getTargetEndOn() {
		return targetEndOn;
	}

	public void setTargetEndOn(Date targetEndOn) {
		this.targetEndOn = targetEndOn;
	}

	public double getBeforeTimePerc() {
		return beforeTimePerc;
	}

	public void setBeforeTimePerc(double beforeTimePerc) {
		this.beforeTimePerc = beforeTimePerc;
	}

	public double getOnTimePerc() {
		return onTimePerc;
	}

	public void setOnTimePerc(double onTimePerc) {
		this.onTimePerc = onTimePerc;
	}

	public double getAfterTimePerc() {
		return afterTimePerc;
	}

	public void setAfterTimePerc(double afterTimePerc) {
		this.afterTimePerc = afterTimePerc;
	}

	public String getSalemanImg() {
		return salemanImg;
	}

	public void setSalemanImg(String salemanImg) {
		this.salemanImg = salemanImg;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "SalesmanLoginInfo [salesmanId=" + salesmanId
				+ ", salesmanPhone=" + salesmanPhone + ", salesmanName="
				+ salesmanName + ", salesmanCnic=" + salesmanCnic
				+ ", salesmanTarget=" + salesmanTarget + ", targetStartOn="
				+ targetStartOn + ", targetEndOn=" + targetEndOn
				+ ", beforeTimePerc=" + beforeTimePerc + ", onTimePerc="
				+ onTimePerc + ", afterTimePerc=" + afterTimePerc
				+ ", joinDate=" + joinDate + "]";
	}

}
