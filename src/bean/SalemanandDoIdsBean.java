package bean;

public class SalemanandDoIdsBean {
	
	private int salesmanId;
	private int doId;
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public int getDoId() {
		return doId;
	}
	public void setDoId(int doId) {
		this.doId = doId;
	}
	@Override
	public String toString() {
		return "SalemanandDoIdsBean [salesmanId=" + salesmanId + ", doId="
				+ doId + "]";
	}
	
	

}
