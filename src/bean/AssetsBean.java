package bean;

public class AssetsBean {
	
	private int customerId;
	private String assetType;
	private double amount;
	
	public AssetsBean() {
		// TODO Auto-generated constructor stub
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AssetsBean [customerId=" + customerId + ", assetType=" + assetType + ", amount=" + amount + "]";
	}
	
	
	
}
