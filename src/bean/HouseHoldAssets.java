package bean;

public class HouseHoldAssets {

	private int assetId;
	private int customerId;
	private boolean hasHome;
	private boolean hasCar;
	private boolean hasBike;
	private boolean hasWashingMachine;
	private boolean hasTv;
	private boolean hasComputer;
	private boolean hasFridge;
	private String otherAssets;
	
	public HouseHoldAssets() {
		// TODO Auto-generated constructor stub
	}


	
	public boolean isHasHome() {
		return hasHome;
	}



	public void setHasHome(boolean hasHome) {
		this.hasHome = hasHome;
	}



	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public boolean isHasCar() {
		return hasCar;
	}

	public void setHasCar(boolean hasCar) {
		this.hasCar = hasCar;
	}

	public boolean isHasBike() {
		return hasBike;
	}

	public void setHasBike(boolean hasBike) {
		this.hasBike = hasBike;
	}

	public boolean isHasWashingMachine() {
		return hasWashingMachine;
	}

	public void setHasWashingMachine(boolean hasWashingMachine) {
		this.hasWashingMachine = hasWashingMachine;
	}

	public boolean isHasTv() {
		return hasTv;
	}

	public void setHasTv(boolean hasTv) {
		this.hasTv = hasTv;
	}

	public boolean isHasComputer() {
		return hasComputer;
	}

	public void setHasComputer(boolean hasComputer) {
		this.hasComputer = hasComputer;
	}

	public boolean isHasFridge() {
		return hasFridge;
	}

	public void setHasFridge(boolean hasFridge) {
		this.hasFridge = hasFridge;
	}

	public String getOtherAssets() {
		return otherAssets;
	}

	public void setOtherAssets(String otherAssets) {
		this.otherAssets = otherAssets;
	}



	@Override
	public String toString() {
		return "HouseHoldAssets [assetId=" + assetId + ", customerId="
				+ customerId + ", hasHome=" + hasHome + ", hasCar=" + hasCar
				+ ", hasBike=" + hasBike + ", hasWashingMachine="
				+ hasWashingMachine + ", hasTv=" + hasTv + ", hasComputer="
				+ hasComputer + ", hasFridge=" + hasFridge + ", otherAssets="
				+ otherAssets +"]";
	}
	
	
	
}
