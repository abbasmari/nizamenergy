package bean;

public class Commission_payout {

	private String customer_name;
	private String Acount_number;
	private double total_commission;
	private double current_month_commission;
	private double default_commission;
	private double hold_amount;
	private double paid_today;
	private int payer_id;
	private String fo_name;
	private double previous_month_commission;

	public Commission_payout() {
		// TODO Auto-generated constructor stub
	}

	public String getFo_name() {
		return fo_name;
	}

	public void setFo_name(String fo_name) {
		this.fo_name = fo_name;
	}

	
	public double getPrevious_month_commission() {
		return previous_month_commission;
	}

	public void setPrevious_month_commission(double previous_month_commission) {
		this.previous_month_commission = previous_month_commission;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getAcount_number() {
		return Acount_number;
	}

	public void setAcount_number(String acount_number) {
		Acount_number = acount_number;
	}

	public double getTotal_commission() {
		return total_commission;
	}

	public void setTotal_commission(double total_commission) {
		this.total_commission = total_commission;
	}

	public double getCurrent_month_commission() {
		return current_month_commission;
	}

	public void setCurrent_month_commission(double current_month_commission) {
		this.current_month_commission = current_month_commission;
	}

	public double getDefault_commission() {
		return default_commission;
	}

	public void setDefault_commission(double default_commission) {
		this.default_commission = default_commission;
	}

	public double getHold_amount() {
		return hold_amount;
	}

	public void setHold_amount(double hold_amount) {
		this.hold_amount = hold_amount;
	}

	public double getPaid_today() {
		return paid_today;
	}

	public void setPaid_today(double paid_today) {
		this.paid_today = paid_today;
	}

	public int getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(int payer_id) {
		this.payer_id = payer_id;
	}

	@Override
	public String toString() {
		return "Commission_payout [customer_name=" + customer_name + ", Acount_number=" + Acount_number
				+ ", total_commission=" + total_commission + ", current_month_commission=" + current_month_commission
				+ ", default_commission=" + default_commission + ", hold_amount=" + hold_amount + ", paid_today="
				+ paid_today + ", payer_id=" + payer_id + ", fo_name=" + fo_name + ", previous_month_commission="
				+ previous_month_commission + "]";
	}

	

}
