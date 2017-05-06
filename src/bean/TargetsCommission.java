package bean;

public class TargetsCommission {

	private int amount;
	private String date;
	private int onTime;
	private int afterTime;
	private int bt_recovery;
	private int ot_recovery;
	private int at_recovery;
	
	
	public TargetsCommission(){}
	


	public TargetsCommission(int amount, String date, int onTime,
			int afterTime, int bt_recovery, int ot_recovery, int at_recovery) {
		super();
		this.amount = amount;
		this.date = date;
		this.onTime = onTime;
		this.afterTime = afterTime;
		this.bt_recovery = bt_recovery;
		this.ot_recovery = ot_recovery;
		this.at_recovery = at_recovery;
	}




	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	
	
	public int getOnTime() {
		return onTime;
	}




	public void setOnTime(int onTime) {
		this.onTime = onTime;
	}




	public int getAfterTime() {
		return afterTime;
	}




	public void setAfterTime(int afterTime) {
		this.afterTime = afterTime;
	}




	public int getBt_recovery() {
		return bt_recovery;
	}



	public void setBt_recovery(int bt_recovery) {
		this.bt_recovery = bt_recovery;
	}



	public int getOt_recovery() {
		return ot_recovery;
	}



	public void setOt_recovery(int ot_recovery) {
		this.ot_recovery = ot_recovery;
	}



	public int getAt_recovery() {
		return at_recovery;
	}



	public void setAt_recovery(int at_recovery) {
		this.at_recovery = at_recovery;
	}



	@Override
	public String toString() {
		return "TargetsCommission [amount=" + amount + ", date=" + date
				+ ", onTime=" + onTime + ", afterTime=" + afterTime
				+ ", bt_recovery=" + bt_recovery + ", ot_recovery="
				+ ot_recovery + ", at_recovery=" + at_recovery + "]";
	}



	
	
	
	
}
