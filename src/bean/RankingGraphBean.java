package bean;

public class RankingGraphBean {
	
	private double recovery;
	private double acceptance;
	private double active;
	private double sales;
	private double ranking;
	String doName;
	String salesmanName;
	public double getRecovery() {
		return recovery;
	}
	public void setRecovery(double recovery) {
		this.recovery = recovery;
	}
	public double getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(double acceptance) {
		this.acceptance = acceptance;
	}
	public double getActive() {
		return active;
	}
	public void setActive(double active) {
		this.active = active;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public double getRanking() {
		return ranking;
	}
	public void setRanking(double ranking) {
		this.ranking = ranking;
	}
	public String getDoName() {
		return doName;
	}
	public void setDoName(String doName) {
		this.doName = doName;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	@Override
	public String toString() {
		return "RankingGraphBean [recovery=" + recovery + ", acceptance="
				+ acceptance + ", active=" + active + ", sales=" + sales
				+ ", ranking=" + ranking + ", doName=" + doName
				+ ", salesmanName=" + salesmanName + "]";
	}
	

	
	
	

}
