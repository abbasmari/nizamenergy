package bean;

public class SalaryBean {
private int salary_id;
private String salary_range;
public SalaryBean() {
	// TODO Auto-generated constructor stub
}
public int getSalary_id() {
	return salary_id;
}
public void setSalary_id(int salary_id) {
	this.salary_id = salary_id;
}
public String getSalary_range() {
	return salary_range;
}
public void setSalary_range(String salary_range) {
	this.salary_range = salary_range;
}
@Override
public String toString() {
	return "SalaryBean [salary_id=" + salary_id + ", salary_range="
			+ salary_range + "]";
}

}
