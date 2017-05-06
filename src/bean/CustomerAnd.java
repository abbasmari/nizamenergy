package bean;

import java.util.Date;

public class CustomerAnd {

	private int id;
	private String name, aplName, address;
	private Date dueDate;
	private int difference;

	public CustomerAnd() {
		super();
	}

	public CustomerAnd(int id, String name, String aplName, String address, Date dueDate,
			int difference) {
		super();
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.aplName = aplName;
		this.address = address;
		this.difference = difference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAplName() {
		return aplName;
	}

	public void setAplName(String aplName) {
		this.aplName = aplName;
	}

	public int getDifference() {
		return difference;
	}

	public void setDifference(int difference) {
		this.difference = difference;
	}

	@Override
	public String toString() {
		return "CustomerAnd [id=" + id + ", name=" + name + ", aplName="
				+ aplName + ", dueDate=" + dueDate + ", difference="
				+ difference + "]";
	}

}
