package bean;

public class AllSalesmansCommission {

	private int onTime;
	private int beforeTime;
	private int afterTime;
	private String time;
	
	public AllSalesmansCommission(){}

	public AllSalesmansCommission(int onTime, int beforeTime, int afterTime,
			String time) {
		super();
		this.onTime = onTime;
		this.beforeTime = beforeTime;
		this.afterTime = afterTime;
		this.time = time;
	}

	public int getOnTime() {
		return onTime;
	}

	public void setOnTime(int onTime) {
		this.onTime = onTime;
	}

	public int getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(int beforeTime) {
		this.beforeTime = beforeTime;
	}

	public int getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(int afterTime) {
		this.afterTime = afterTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "AllSalesmansCommission [onTime=" + onTime + ", beforeTime="
				+ beforeTime + ", afterTime=" + afterTime + ", time=" + time
				+ "]";
	}

	
	
	
	
	
}
