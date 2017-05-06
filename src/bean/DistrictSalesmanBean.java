package bean;

public class DistrictSalesmanBean {

	private int salesman_id;
	private int salesman_district;
	private String salesman_name;
	private String district_name;
	
	public DistrictSalesmanBean() {
		
	}

	public DistrictSalesmanBean(int salesman_id, int salesman_district,
			String salesman_name, String district_name) {
		
		this.salesman_id = salesman_id;
		this.salesman_district = salesman_district;
		this.salesman_name = salesman_name;
		this.district_name = district_name;
	}

	public int getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(int salesman_id) {
		this.salesman_id = salesman_id;
	}

	public int getSalesman_district() {
		return salesman_district;
	}

	public void setSalesman_district(int salesman_district) {
		this.salesman_district = salesman_district;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	@Override
	public String toString() {
		return "DistrictSalesmanBean [salesman_id=" + salesman_id
				+ ", salesman_district=" + salesman_district
				+ ", salesman_name=" + salesman_name + ", district_name="
				+ district_name + "]";
	}
	
	
	
	
}
