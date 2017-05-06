package bean;

public class MonthlyExpenses {

	private int expenseId;
	private int customerId;
	private String electricity;
	private String generator;
	private String ups;
	private String solar;
	private String electricityusage;
	private double electricityexpense;
	private String majorexpensedescription;
	private double majorexpenseamount;
	private double totalexpense;
	public MonthlyExpenses() {
		
	}
	
	
	
	public MonthlyExpenses(int expenseId, int customerId, String electricity,
			String generator, String ups, String solar,
			String electricityusage, double electricityexpense,
			String majorexpensedescription, double majorexpenseamount,
			double totalexpense) {
		
		this.expenseId = expenseId;
		this.customerId = customerId;
		this.electricity = electricity;
		this.generator = generator;
		this.ups = ups;
		this.solar = solar;
		this.electricityusage = electricityusage;
		this.electricityexpense = electricityexpense;
		this.majorexpensedescription = majorexpensedescription;
		this.majorexpenseamount = majorexpenseamount;
		this.totalexpense = totalexpense;
	}



	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	
	public String getElectricity() {
		return electricity;
	}



	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}



	public String getGenerator() {
		return generator;
	}



	public void setGenerator(String generator) {
		this.generator = generator;
	}



	public String getUps() {
		return ups;
	}



	public void setUps(String ups) {
		this.ups = ups;
	}



	public String getSolar() {
		return solar;
	}



	public void setSolar(String solar) {
		this.solar = solar;
	}



	public String getElectricityusage() {
		return electricityusage;
	}
	public void setElectricityusage(String electricityusage) {
		this.electricityusage = electricityusage;
	}
	public double getElectricityexpense() {
		return electricityexpense;
	}
	public void setElectricityexpense(double electricityexpense) {
		this.electricityexpense = electricityexpense;
	}
	public String getMajorexpensedescription() {
		return majorexpensedescription;
	}
	public void setMajorexpensedescription(String majorexpensedescription) {
		this.majorexpensedescription = majorexpensedescription;
	}
	public double getMajorexpenseamount() {
		return majorexpenseamount;
	}
	public void setMajorexpenseamount(double majorexpenseamount) {
		this.majorexpenseamount = majorexpenseamount;
	}
	public double getTotalexpense() {
		return totalexpense;
	}
	public void setTotalexpense(double totalexpense) {
		this.totalexpense = totalexpense;
	}



	@Override
	public String toString() {
		return "MonthlyExpenses [expenseId=" + expenseId + ", customerId="
				+ customerId + ", electricity=" + electricity + ", generator="
				+ generator + ", ups=" + ups + ", solar=" + solar
				+ ", electricityusage=" + electricityusage
				+ ", electricityexpense=" + electricityexpense
				+ ", majorexpensedescription=" + majorexpensedescription
				+ ", majorexpenseamount=" + majorexpenseamount
				+ ", totalexpense=" + totalexpense + "]";
	}
	
	

	
	
	
	
	
}
