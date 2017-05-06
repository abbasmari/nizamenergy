package bal;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.apache.http.ParseException;

import org.apache.log4j.Logger;

//import security.EncryptDecrypt;

import com.ocpsoft.pretty.time.PrettyTime;

import bean.AssetsBean;
import bean.BusinessDetails;
import bean.CustomerBean;
import bean.CustomerChild;
import bean.CustomerGuardian;
import bean.CustomerInfoBean;
import bean.CustomerLoanBean;
import bean.CustomerMessageBean;
import bean.CustomerProfileViewBean;
import bean.CustomerSearchBean;
import bean.DistrictOfficerList;
import bean.EligibilityBean2;
import bean.EmploymentDetails;
import bean.GuarantorBean;
import bean.HouseHoldAssets;
import bean.MonthlyExpenses;
import bean.MonthlyHomeExpenses;
import bean.NumberOfMsgFrom;
import bean.OccupationBean;
import bean.OtherLoanDetails;
import bean.PaymentBean;
import bean.SalaryBean;
import bean.Salesman;
import bean.ServiceOperationBean;
import bean.SuperAdminListBean;
import connection.Connect;

public class CustomerBAL {

	final static Logger logger = Logger.getLogger(CustomerBAL.class);

	public static CustomerInfoBean getCustomerWithId(int customer_id) {

		CustomerInfoBean bean = null;

		Connection con = Connect.getConnection();
		int customerId, status, customerFamilySize;
		String customerFamilyIncome, monthlyIncome, customerName, occupation, createdOn, cnicNo, district, gsmNumber, customerAddress;

		String query = "select * from customer where customer_id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				customerAddress = rs.getString(4);
				district = rs.getString(5);
				customerFamilySize = rs.getInt(6);
				gsmNumber = rs.getString(7);
				monthlyIncome = rs.getString(8);
				customerFamilyIncome = rs.getString(9);
				createdOn = rs.getTimestamp(12).toString();
				status = rs.getInt(13);
				occupation = rs.getString(14);
				bean = new CustomerInfoBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(customerAddress);
				bean.setDistrict(district);
				bean.setFamilySize(customerFamilySize);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setFamilyIncome(customerFamilyIncome);
				bean.setCreatedOn(createdOn);
				bean.setStatus(status);
				bean.setOccupation(occupation);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static CustomerSearchBean getCustomerByNumber(String customer_phone) {

		CustomerSearchBean bean = null;
		// ArrayList<CustomerSearchBean> customerList = new
		// ArrayList<CustomerSearchBean>();

		Connection con = Connect.getConnection();
		int customerId, status;
		String customerName, customer_dob, cnicNo, phone1, phone2, customerAddress, city;
		double monthlyIncome, customerFamilyIncome;

		String query = "select customer_id," + "customer_name,"
				+ "customer_cnic," + "date_of_birth," + "customer_address,"
				+ "customer_city," + "customer_phone," + "customer_phone2,"
				+ "customer_monthly_income," + "customer_family_income,"
				+ "status from customer where customer_phone=?";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, customer_phone);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				customer_dob = rs.getString(4);
				customerAddress = rs.getString(5);
				city = rs.getString(6);
				phone1 = rs.getString(7);
				phone2 = rs.getString(8);
				monthlyIncome = rs.getDouble(9);
				customerFamilyIncome = rs.getDouble(10);
				status = rs.getInt(11);

				bean = new CustomerSearchBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDate_of_birth(customer_dob);
				bean.setAddress(customerAddress);
				bean.setCity(city);
				bean.setPhoneNo(phone1);
				bean.setPhoneNo2(phone2);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setFamilyIncome(customerFamilyIncome);
				bean.setStatus(status);

				// customerList.add(bean);

			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static CustomerBean getCustomerDetailByNumber(String customer_phone) {

		CustomerBean bean = null;
		// ArrayList<CustomerSearchBean> customerList = new
		// ArrayList<CustomerSearchBean>();

		Connection conn = Connect.getConnection();
		int customerId, status, age;
		String customerName, customer_dob, cnicNo, phone1, phone2, customerAddress, city;
		String father_name, mother_name, email, gender, relation_status, education, source_of_income;
		double monthlyIncome, customerFamilyIncome;
		boolean is_educated;

		String query = "select customer_id," + "customer_name,"
				+ "customer_cnic," + "date_of_birth," + "customer_address,"
				+ "customer_city," + "customer_phone," + "customer_phone2,"
				+ "customer_monthly_income," + "customer_family_income,"
				+ "status,"

				+ "father_name," + "mother_name," + "email," + "gender,"
				+ "age," + "relation_status," + "is_educated," + "education,"
				+ "source_of_income from customer where customer_phone=?";
		try {

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, customer_phone);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				customerId = rs.getInt(1);

				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				customer_dob = rs.getString(4);
				customerAddress = rs.getString(5);
				city = rs.getString(6);
				phone1 = rs.getString(7);
				phone2 = rs.getString(8);
				monthlyIncome = rs.getDouble(9);
				customerFamilyIncome = rs.getDouble(10);

				status = rs.getInt(11);

				father_name = rs.getString(12);
				mother_name = rs.getString(13);
				email = rs.getString(14);
				gender = rs.getString(15);
				age = rs.getInt(16);
				relation_status = rs.getString(17);
				is_educated = rs.getBoolean(18);
				education = rs.getString(19);
				source_of_income = rs.getString(20);

				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDateOfBirth(customer_dob);
				bean.setAddress(customerAddress);
				bean.setDistrict(city);
				bean.setPhoneNo(phone1);
				bean.setPhoneNo2(phone2);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setFamilyIncome(customerFamilyIncome);
				bean.setStatus(status);
				bean.setFatherName(father_name);
				bean.setMotherName(mother_name);
				bean.setEmail(email);
				bean.setGender(gender);
				bean.setRelationStatus(relation_status);
				bean.setEducated(is_educated);
				bean.setEducation(education);
				bean.setSourceOfIncome(source_of_income);
				bean.setAge(age);

			}

			// rs.close();
			// stmt.close();
			// con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.getStackTrace();
		}
		return bean;
	}

	public static ArrayList<HashMap<String, String>> getAssetsOfCustomer(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		Connection con = Connect.getConnection();
		String query = "SELECT * FROM assets WHERE assets.customer_id = ?";
		PreparedStatement ps;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("assetId", rs.getInt("asset_id") + "");
				map.put("type", rs.getString("type"));
				map.put("amount", rs.getInt("amount") + "");
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getOtherIncome(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		Connection con = Connect.getConnection();
		String query = "SELECT * FROM other_income_resource WHERE customer_id = ?";
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("incomeId", rs.getInt("id") + "");
				map.put("detail", rs.getString("detail"));
				map.put("amount", rs.getInt("amount") + "");
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getLoanAndLiabilities(
			int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;

		Connection con = Connect.getConnection();

		String query = "SELECT * FROM other_loan WHERE other_loan.customer_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("loanId", rs.getInt("loan_id") + "");
				map.put("customerId", rs.getInt("customer_id") + "");
				map.put("donner", rs.getString("donner"));
				map.put("borrowedAmount", rs.getInt("barrowed_amount") + "");
				map.put("remainingAmount", rs.getInt("remaining_amount") + "");
				map.put("monthlyInstallment", rs.getInt("monthly_installment")
						+ "");
				map.put("type", rs.getInt("type") + "");
				list.add(map);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static CustomerProfileViewBean getCustomerProfile(String cnic) {
		System.out.println("CustomerBAL.get_customers_profile(?)");

		CustomerProfileViewBean bean = null;
		// Connection connection = Connect.getConnection();
		// CHANGED BY JETANDER
		try {
			// Begin Procedure Call
			System.out.println("hdgfjsb");
			CallableStatement prepareCall = Connect.getConnection()
					.prepareCall("{call get_customers_profile(?)}");
			System.out.println("prepareCall");
			prepareCall.setString(1, cnic);
			System.out.println("cnic");

			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				System.out.println();
				bean = new CustomerProfileViewBean();
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setCustomerName(rs.getString("customer_name"));
				bean.setCnicNo(rs.getString("customer_cnic"));
				bean.setAddress(rs.getString("customer_address"));
				bean.setCity_name(rs.getString("city_name"));
				bean.setPhoneNo(rs.getString("customer_phone"));
				bean.setPhoneNo2(rs.getString("customer_phone2"));
				// bean.setMonthlyIncome(rs.getString("sal.salary_range"));
				// bean.setFamilyIncome(rs.getString("sall.salary_range"));
				bean.setStatus(rs.getInt("e.status"));
				bean.setFatherName(rs.getString("father_name"));
				bean.setMotherName(rs.getString("mother_name"));
				bean.setEmail(rs.getString("email"));
				bean.setGender(rs.getString("gender"));
				bean.setRelationStatus(rs.getString("relation_status"));
				bean.setEducation(rs.getString("edu.education"));
				System.out.println("jhfdhhfd" + rs.getString("edu.education"));
				System.out.println("jhfdhhfd" + rs.getString("education"));
				System.out.println("id" + rs.getString("education_id"));
				bean.setSourceOfIncome(rs.getString("source_of_income"));
				bean.setDoId(rs.getInt("do_id"));
				bean.setBusinessName(rs.getString("business_name"));
				bean.setBusinessPlace(rs.getString("business_place"));
				bean.setBusinessType(rs.getString("businees_type"));
				bean.setOwnedOrPartner(rs.getString("owned"));
				bean.setPeriod(rs.getString("period"));
				bean.setCurrentPlacePeriod(rs.getString("current_place_period"));
				bean.setWorkers(rs.getInt("workers"));
				bean.setOfficePhone(rs.getString("phone_no"));
				bean.setOrgPhone(rs.getString("office_phone_no"));
				bean.setOrganisationName(rs.getString("organisation_name"));
				bean.setOrgAddress(rs.getString("ed.address"));
				bean.setBusinessAddress(rs.getString("bn.address"));
				bean.setJobPeriod(rs.getString("job_period"));
				bean.setJobPosition(rs.getString("job_position"));
				bean.setSalary(rs.getDouble("salary"));
				bean.setAccountCreatedDate(rs.getString("created_on"));

				bean.setIncomeDetails(rs.getString("detail"));
				bean.setAmount(rs.getInt("amount"));
				bean.setSalary(rs.getDouble("salary_or_pension"));
				bean.setBusinessIncome(rs.getInt("business_income"));
				bean.setFarming(rs.getInt("farming"));
				bean.setFamilyContribution(rs.getInt("family_contribution"));
				bean.setMarritalStatus(rs.getString("relation_status"));
				bean.setSupervisorName(rs.getString("supervisor_name"));
				bean.setEducationId(rs.getInt("education_id"));
				bean.setGeneral1(rs.getString("general_one"));
				bean.setGeneral2(rs.getString("general_two"));
				bean.setGeneral3(rs.getString("general_three"));
				bean.setGeneral4(rs.getInt("general_four"));
				bean.setGeneral5(rs.getInt("general_five"));
				bean.setFamilyMember(rs.getInt("customer_family_size"));
				bean.setComment(rs.getString("comment"));
				bean.setBusinessid(rs.getInt("business_id"));
				bean.setEmployementId(rs.getInt("emp_id"));
			}
			// connection.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static CustomerProfileViewBean getCustomerProfile(int customerId) {
		System.out.println("CustomerBAL.get_customers_profile(?)");

		CustomerProfileViewBean bean = null;
		// Connection connection = Connect.getConnection();
		// CHANGED BY JETANDER
		try {
			// Begin Procedure Call
			CallableStatement prepareCall = Connect.getConnection()
					.prepareCall("{call get_customers_profile_by_id(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				System.out.println();
				bean = new CustomerProfileViewBean();
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setCustomerName(rs.getString("customer_name"));
				bean.setCnicNo(rs.getString("customer_cnic"));
				bean.setAddress(rs.getString("customer_address"));
				bean.setCity_name(rs.getString("city_name"));
				bean.setPhoneNo(rs.getString("customer_phone"));
				bean.setPhoneNo2(rs.getString("customer_phone2"));
				// bean.setMonthlyIncome(rs.getString("sal.salary_range"));
				// bean.setFamilyIncome(rs.getString("sall.salary_range"));
				bean.setStatus(rs.getInt("e.status"));
				bean.setFatherName(rs.getString("father_name"));
				bean.setMotherName(rs.getString("mother_name"));
				bean.setEmail(rs.getString("email"));
				bean.setGender(rs.getString("gender"));
				bean.setRelationStatus(rs.getString("relation_status"));
				bean.setEducated(rs.getBoolean("is_educated"));
				bean.setEducation(rs.getString("edu.education"));
				bean.setSourceOfIncome(rs.getString("source_of_income"));
				bean.setDoId(rs.getInt("do_id"));
				bean.setBusinessName(rs.getString("business_name"));
				bean.setBusinessPlace(rs.getString("business_place"));
				bean.setBusinessType(rs.getString("businees_type"));
				bean.setOwnedOrPartner(rs.getString("owned"));
				bean.setPeriod(rs.getString("period"));
				bean.setCurrentPlacePeriod(rs.getString("current_place_period"));
				bean.setWorkers(rs.getInt("workers"));
				bean.setOfficePhone(rs.getString("phone_no"));
				bean.setOrgPhone(rs.getString("office_phone_no"));
				bean.setOrganisationName(rs.getString("organisation_name"));
				bean.setOrgAddress(rs.getString("ed.address"));
				bean.setBusinessAddress(rs.getString("bn.address"));
				bean.setJobPeriod(rs.getString("job_period"));
				bean.setJobPosition(rs.getString("job_position"));
				bean.setSalary(rs.getDouble("salary"));
				bean.setAccountCreatedDate(rs.getString("created_on"));
				bean.setIncomeDetails(rs.getString("detail"));
				bean.setAmount(rs.getInt("amount"));
				bean.setSalary(rs.getDouble("salary_or_pension"));
				bean.setBusinessIncome(rs.getInt("business_income"));
				bean.setFarming(rs.getInt("farming"));
				bean.setFamilyContribution(rs.getInt("family_contribution"));
				bean.setMarritalStatus(rs.getString("relation_status"));
				bean.setSupervisorName(rs.getString("supervisor_name"));
				bean.setAccountCreatedDate(rs.getString("created_on"));
				bean.setVleName(rs.getString("salesman_name"));
				bean.setFoName(rs.getString("fo_name"));
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static CustomerInfoBean getCustomerWithIdAscendig(int customer_id) {

		CustomerInfoBean bean = null;
		Connection con = Connect.getConnection();

		int customerId, status, customerFamilySize;
		String customerFamilyIncome, monthlyIncome, customerName, cnicNo, district, gsmNumber, customerAddress;
		String query = "SELECT cs.customer_id," // 1 customer id
				+ "cs.customer_name," // 2 customer name
				+ "cs.customer_cnic," // 3 customer address
				+ "cs.customer_address," // 4 city name
				+ "c.city_name," // 5 customer family size
				+ "cs.customer_family_size," // 6 customer family size
				+ "cs.customer_phone," // 7 customer phone number
				+ "sd.salary_range," // 8 customer salary range
				+ "sss.salary_range," // 9 salary salary range
				+ ""
				+ "cs.customr_image," // 10 customer image
				+ "cs.created_on," // 11 created on
				+ "cs.status" // 12 customer status
				// + "o.occupation_name"
				+ " FROM customer cs INNER JOIN city c ON c.city_id = customer_city INNER JOIN salary sd ON sd.salary_id = cs.customer_monthly_income INNER JOIN salary sss ON sss.salary_id = cs.customer_family_income"
				+ " WHERE cs.customer_id=?";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);// 1 customer id
				customerName = rs.getString(2);// 2 customer name
				cnicNo = rs.getString(3);// 3 customer address
				customerAddress = rs.getString(4);// 4 city name
				district = rs.getString(5);// 5 customer family size
				customerFamilySize = rs.getInt(6);// 6 customer family size
				gsmNumber = rs.getString(7);// 7 customer phone number
				monthlyIncome = rs.getString(8);// 8 customer salary range
				customerFamilyIncome = rs.getString(9);// 9 salary salary range

				// applianceId = rs.getInt(11);
				// createdOn = rs.getTimestamp(12).toString();// 11 created on
				System.out.println("Date Formate+++++++++++++++++++"
						+ rs.getString(12));
				status = rs.getInt(12);// 12 customer status
				// occupation = rs.getString(14);
				bean = new CustomerInfoBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(customerAddress);
				bean.setDistrict(district);
				bean.setFamilySize(customerFamilySize);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setFamilyIncome(customerFamilyIncome);

				// bean.setCreatedOn(createdOn);
				bean.setStatus(status);
				// bean.setOccupation(occupation);

			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static String getOccupation(String id) {
		String occupation = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select occupation_name from occupation where occupation_id = "
				+ id + ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				occupation = rs.getString(1);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return occupation;
	} // end of getting all customers form Db

	public static HouseHoldAssets getHouseAssetsById(int customerId) {

		boolean isHome, isBike, isCar, isTv, isFridge, isWashingMachine;
		String others;
		HouseHoldAssets bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select home,motorcycle,car,tv,fridge,washing_machine,others from home_hold_assets where customer_id = "
				+ customerId + ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				isHome = rs.getBoolean(1);
				isBike = rs.getBoolean(2);
				isCar = rs.getBoolean(3);
				isTv = rs.getBoolean(4);
				isFridge = rs.getBoolean(5);
				isWashingMachine = rs.getBoolean(6);
				others = rs.getString(7);

				bean = new HouseHoldAssets();
				bean.setHasHome(isHome);
				bean.setHasBike(isBike);
				bean.setHasCar(isCar);
				bean.setHasTv(isTv);
				bean.setHasFridge(isFridge);
				bean.setHasWashingMachine(isWashingMachine);
				bean.setOtherAssets(others);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static CustomerGuardian getCustomerGuardian(int customerId) {
		Connection conn = Connect.getConnection();
		int customer_id, customer_family_size;
		String guardian_name, phone_no;
		CustomerGuardian bean = null;
		// Connection conn=Connect.getConnection();
		String query = "Select customer_id,guardian_name,phone_no,customer_family_size from customer_guardian where customer_id = ?";
		try {

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				customer_id = rs.getInt(1);
				guardian_name = rs.getString(2);
				phone_no = rs.getString(3);
				customer_family_size = rs.getInt(4);

				bean = new CustomerGuardian();
				bean.setCustomerId(customer_id);
				bean.setGuardianName(guardian_name);
				bean.setPhone_no(phone_no);
				bean.setFamilyMember(customer_family_size);
			}
			rs.close();
			stmt.close();
			// con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static Salesman getSalesManById(int salesmanid) {
		int salesman_id;
		String salesman_name;
		Salesman bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select salesman_id, salesman_name  from salesman where salesman_id = "
				+ salesmanid + ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				salesman_id = rs.getInt(1);
				salesman_name = rs.getString(2);

				bean = new Salesman();
				bean.setSalesmanId(salesman_id);
				bean.setName(salesman_name);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return bean;
	}

	public static BusinessDetails getCustomerBusiness(int customerId) {

		int business_id, workers;
		String businessName, busineesType, owned, period, current_place, business_place, phone_no;
		BusinessDetails bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select business_id," + "business_name,"
				+ "businees_type," + "owned," + "period,"
				+ "current_place_period," + "workers," + "business_place,"
				+ "phone_no from business_info where customer_id = "
				+ customerId + ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				business_id = rs.getInt(1);
				businessName = rs.getString(2);
				busineesType = rs.getString(3);
				owned = rs.getString(4);
				period = rs.getString(5);
				current_place = rs.getString(6);
				workers = rs.getInt(7);
				business_place = rs.getString(8);
				phone_no = rs.getString(9);

				bean = new BusinessDetails();
				bean.setBusinessId(business_id);
				bean.setBusinessName(businessName);
				bean.setBusinessType(busineesType);
				bean.setOwnedOrPartner(owned);
				bean.setPeriod(period);
				bean.setCurrentPlacePeriod(current_place);
				bean.setWorkers(workers);
				bean.setBusinessPlace(business_place);
				bean.setOrgPhone(phone_no);

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static EmploymentDetails getEmployDetailById(int customerId) {

		int emp_id;
		double salary;
		String organisation_name, job_position, job_period, office_phone_no, address;
		EmploymentDetails bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select emp_id," + "organisation_name,"
				+ "job_position," + "job_period," + "salary,"
				+ "office_phone_no," + "address"
				+ " from employment_details where customer_id = " + customerId
				+ ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				emp_id = rs.getInt(1);
				organisation_name = rs.getString(2);
				job_position = rs.getString(3);
				job_period = rs.getString(4);
				salary = rs.getDouble(5);
				office_phone_no = rs.getString(6);
				address = rs.getString(7);

				bean = new EmploymentDetails();
				bean.setEmploymentId(emp_id);
				bean.setOrganisationName(organisation_name);
				bean.setJobPosition(job_position);
				bean.setJobPeriod(job_period);
				bean.setSalary(salary);
				bean.setPhoneNo(office_phone_no);
				bean.setOrgAddress(address);

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static ArrayList<CustomerChild> getCustomerChildById(int customerId) {

		int cc_id;
		ArrayList<CustomerChild> list = new ArrayList<>();

		String child_name, child_relation, child_mobile, child_dob, child_cnic;
		CustomerChild bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select cc_id," + "child_name," + "child_relation,"
				+ "child_mobile," + "child_dob," + "child_cnic"
				+ " from customerchild where customer_id = " + customerId + ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cc_id = rs.getInt(1);
				child_name = rs.getString(2);
				child_relation = rs.getString(3);
				child_mobile = rs.getString(4);
				child_dob = rs.getString(5);
				child_cnic = rs.getString(6);

				bean = new CustomerChild();
				bean.setCc_id(cc_id);
				bean.setChild_name(child_name);
				bean.setChild_relation(child_relation);
				bean.setChild_mobile(child_mobile);
				bean.setChild_dob(child_dob);
				bean.setChild_cnic(child_cnic);
				list.add(bean);

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static ArrayList<OtherLoanDetails> getOtherLoanDeatailByIdd(
			int customerId) {
		ArrayList<OtherLoanDetails> list = new ArrayList<>();
		String loanDonner, type;
		double loanAmount, monthlyInstallment, remainingPayment;

		OtherLoanDetails bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select donner,barrowed_amount," + "remaining_amount,"
				+ "monthly_installment,"
				+ "type from other_loan where customer_id = " + customerId
				+ ";";
		try {
			System.out.println(query);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				loanDonner = rs.getString("donner");
				loanAmount = rs.getDouble("barrowed_amount");
				monthlyInstallment = rs.getDouble("monthly_installment");
				remainingPayment = rs.getDouble("remaining_amount");
				type = rs.getString("type");

				bean = new OtherLoanDetails();
				bean.setLoanDonner(loanDonner);
				bean.setLoanAmount(loanAmount);
				bean.setMonthlyInstallment(monthlyInstallment);
				bean.setRemainingPayment(remainingPayment);
				bean.setType(type);
				;
				list.add(bean);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static OtherLoanDetails getOtherLoanDeatailById(int customerId) {
		String loanDonner, type;
		double loanAmount, monthlyInstallment, remainingPayment;

		OtherLoanDetails bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select donner,barrowed_amount," + "remaining_amount,"
				+ "monthly_installment,"
				+ "type from other_loan where customer_id = " + customerId
				+ ";";
		try {
			System.out.println(query);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				loanDonner = rs.getString("donner");
				loanAmount = rs.getDouble("barrowed_amount");
				monthlyInstallment = rs.getDouble("monthly_installment");
				remainingPayment = rs.getDouble("remaining_amount");
				type = rs.getString("type");

				bean = new OtherLoanDetails();
				bean.setLoanDonner(loanDonner);
				bean.setLoanAmount(loanAmount);
				bean.setMonthlyInstallment(monthlyInstallment);
				bean.setRemainingPayment(remainingPayment);
				bean.setType(type);
				;
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static ArrayList<MonthlyHomeExpenses> getMonthlyExpenses(
			int customerId) {
		ArrayList<MonthlyHomeExpenses> list = new ArrayList<>();
		MonthlyHomeExpenses bean = null;

		String type;
		Double amount;
		Connection con = connection.Connect.getConnection();
		String query = "Select type, amount from monthly_home_expenses where customer_id = "
				+ customerId + ";";
		try {
			System.out.println(query);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				type = rs.getString("type");
				amount = rs.getDouble("amount");

				bean = new MonthlyHomeExpenses();
				bean.setExpenseType(type);
				bean.setAmount(amount);
				list.add(bean);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<EligibilityBean2> getAppliances(int customerId) {
		ArrayList<EligibilityBean2> list = new ArrayList<>();
		EligibilityBean2 bean = null;

		double instalment, downpayment, price;
		int scheme;
		Connection con = connection.Connect.getConnection();
		String query = "SELECT installment_scheme, instalment, down_payment, (installment_scheme*instalment)+down_payment AS total "
				+ "FROM eligibility WHERE customer_id = " + customerId + ";";
		try {
			System.out.println(query);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				downpayment = rs.getDouble("down_payment");
				instalment = rs.getDouble("instalment");
				price = rs.getDouble("total");
				scheme = rs.getInt("installment_scheme");
				bean = new EligibilityBean2();
				bean.setAppliancePrice(price);
				bean.setMonthlyInstallment(instalment);
				bean.setDownpayment(downpayment);
				bean.setTotalInstallments(scheme);
				list.add(bean);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<AssetsBean> getAssets(int customerId) {
		ArrayList<AssetsBean> list = new ArrayList<>();
		AssetsBean bean = null;

		String type;
		Double amount;
		Connection con = connection.Connect.getConnection();
		String query = "Select type, amount from assets where customer_id = "
				+ customerId + ";";
		try {
			System.out.println(query);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				type = rs.getString("type");
				amount = rs.getDouble("amount");

				bean = new AssetsBean();
				bean.setAssetType(type);
				bean.setAmount(amount);
				list.add(bean);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static MonthlyExpenses getMonthlyExpenseById(int customerId) {

		int expense_id;
		String grid_electricity, generator, ups, solar, electricityusage, majorexpensedescription;
		double electricityexpense, majorexpenseamount, totalexpense;

		MonthlyExpenses bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select expense_id,"
				+ "grid_electricity,"
				+ "generator,"
				+ "ups,"
				+ "solar,"
				+ "electricityusage,"
				+ "electricityexpense,"
				+ "majorexpensedescription,"
				+ "majorexpenseamount,"
				+ "totalexpense from monthly_home_expenses where customer_id = "
				+ customerId + ";";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				expense_id = rs.getInt(1);
				grid_electricity = rs.getString(2);
				generator = rs.getString(3);
				ups = rs.getString(4);
				solar = rs.getString(5);
				electricityusage = rs.getString(6);
				electricityexpense = rs.getDouble(7);
				majorexpensedescription = rs.getString(8);
				majorexpenseamount = rs.getDouble(9);
				totalexpense = rs.getDouble(10);

				bean = new MonthlyExpenses();
				bean.setExpenseId(expense_id);
				bean.setElectricity(grid_electricity);
				bean.setGenerator(generator);
				bean.setUps(ups);
				bean.setSolar(solar);
				bean.setElectricityusage(electricityusage);
				bean.setElectricityexpense(electricityexpense);
				bean.setMajorexpensedescription(majorexpensedescription);
				bean.setMajorexpenseamount(majorexpenseamount);
				bean.setTotalexpense(totalexpense);

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static GuarantorBean getGuaranterDetailById(int customerId, int gtype) {

		int guarantor_id, customer_id;
		String gguarantorName, gguarantorCnic, gRelationToCustomer, gguarantorPhone;
		GuarantorBean bean = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select guarantor_id, customer_id, gguarantorName, gguarantorCnic, gRelationToCustomer,gguarantorPhone, gguarantorIncome"
				+ " From customer_guarantor where customer_id = "
				+ customerId
				+ " and guarantertype = " + gtype + ";";
		System.out.println(query);

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				guarantor_id = rs.getInt("guarantor_id");
				customer_id = rs.getInt("customer_id");
				gguarantorName = rs.getString("gguarantorName");
				gguarantorCnic = rs.getString("gguarantorCnic");
				// gguarantorDob = rs.getString(6);
				gRelationToCustomer = rs.getString("gRelationToCustomer");
				gguarantorPhone = rs.getString("gguarantorPhone");

				bean = new GuarantorBean();
				bean.setGuarantorId(guarantor_id);
				bean.setCustomerId(customer_id);
				bean.setGguarantorName(gguarantorName);
				bean.setGguarantorCnic(gguarantorCnic);
				bean.setgRelationToCustomer(gRelationToCustomer);
				bean.setGguarantorPhone(gguarantorPhone);
				bean.setGguarantorIncome(rs.getString("gguarantorIncome"));

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static ArrayList<MonthlyHomeExpenses> getMonthlyExpensesById(
			int customerId) {
		MonthlyHomeExpenses bean = new MonthlyHomeExpenses();
		ArrayList<MonthlyHomeExpenses> list = new ArrayList<>();
		// int expenseId, customer_id;
		Connection con = connection.Connect.getConnection();
		String query = "Select expense_id, customer_id, monthly_home_expenses.type AS typ, amount from monthly_home_expenses where customer_id = ?";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			System.out.println(query);
			while (rs.next()) {
				bean = new MonthlyHomeExpenses();
				bean.setExpenseId(rs.getInt("expense_id"));
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setExpenseType(rs.getString("typ"));
				bean.setAmount(rs.getDouble("amount"));
				list.add(bean);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<CustomerInfoBean> getCutomers_Rejected() {

		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ " a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id, \n"
					+ " a.appliance_name, cs.status FROM eligibility e\n"
					+ " JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id\n"
					+ " JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    WHERE e.status=2 OR e.status = 4";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(11);
				salesmanId = rs.getInt(12);
				applianceName = rs.getString(13);
				status = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getDoCutomers_accepted(
			int districtId) {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN sold_to sld ON cs.customer_id=sld.customer_id\n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+ "                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE cd.district_id ="
					+ districtId + " AND e.status=1 or e.status=6; ";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				familySize = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				bean.setFamilySize(familySize);
				customerList.add(bean);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getDoCutomers_rejected(
			int districtId) {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+ "                     \n"
					+ "                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE cd.district_id="
					+ districtId
					+ " AND cs.status=2 GROUP BY cs.customer_id\n"
					+ "    ";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				familySize = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				bean.setFamilySize(familySize);
				customerList.add(bean);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_onCash() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name ,sld.payement_option, a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id       \n"
					+ "                     JOIN sold_to sld ON cs.customer_id=sld.customer_id\n"
					+ "                     JOIN city c ON cs.customer_city=c.city_id\n"
					+

					"                    WHERE sld.payement_option=0";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(11);
				salesmanId = rs.getInt(12);
				applianceName = rs.getString(13);
				status = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_onWait() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                     INNER JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                    INNER JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                    INNER JOIN customer cs ON e.customer_id = cs.customer_id\n"
					+ "      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    WHERE e.status=0;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static int getCutomers_onWaitTotal() {
		int applianceId = 0;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT count(*) FROM eligibility e\n"
					+ "                     	INNER JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                    	INNER JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                    	INNER JOIN customer cs ON e.customer_id = cs.customer_id\n"
					+ "      					JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    	WHERE e.status=0;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				applianceId = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return applianceId;

	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_Accepted() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id\n"
					+ "      JOIN city c ON cs.customer_city=c.city_id WHERE e.status=1 or e.status=6;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_onLoan(int active) {
		CustomerInfoBean bean = null;
		String querycon = null;
		if (active == 1) {
			querycon = " and cs.status=1 ";
		} else {
			querycon = "";
		}
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id       \n"
					+ "                     JOIN sold_to sld ON cs.customer_id=sld.customer_id\n"
					+ "   JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    WHERE sld.payement_option=1"
					+ querycon;
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getDoCutomers_appliad(
			int districtId) {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+ "                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE cd.district_id="
					+ districtId + " AND cs.status=0 GROUP BY cs.customer_id\n";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				familySize = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				bean.setFamilySize(familySize);
				customerList.add(bean);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_suggested() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id\n"
					+ "                     JOIN sold_to sld ON cs.customer_id=sld.customer_id\n"
					+ "  JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    WHERE e.status=3";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(11);
				salesmanId = rs.getInt(12);
				applianceName = rs.getString(13);
				status = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static int updateCustomer(int id, String name, String cnic,
			String phone, String address, int familySize, double monthlyIncome,
			double familyIncome, int status, String occupation) {
		Connection con = connection.Connect.getConnection();
		String query = "Update customer SET customer_name = '" + name
				+ "',customer_cnic= '" + cnic + "',customer_address= '"
				+ address + "',customer_family_size= '" + familySize
				+ "',customer_phone ='" + phone + "',customer_monthly_income='"
				+ monthlyIncome + "',customer_family_income='" + familyIncome
				+ "',status= '" + status + "',occupation= '" + occupation
				+ "' WHERE customer_id= " + id + ";";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

			st.close();
			con.close();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

	public static int updateAppliance(int id, String name, String gsm,
			String productNo, double price, int status) {
		Connection con = connection.Connect.getConnection();
		String query = "Update appliance SET appliance_GSMno='" + gsm
				+ "', appliance_name='" + name + "', appliance_product_no='"
				+ productNo + "', appliance_price=" + price
				+ ", appliance_status=" + status + " WHERE appliance_id=" + id
				+ ";";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

			st.close();
			con.close();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

	public static int updateEligibility(int id, int customerId,
			int applianceId, int salesmanId, double downPayment, int scheme,
			double installment) {
		Connection con = connection.Connect.getConnection();
		String query = "Update eligibility SET customer_id=" + customerId
				+ ", appliance_id=" + applianceId + ", salesman_id="
				+ salesmanId + ", down_payment=" + downPayment
				+ ", installment_scheme=" + scheme + ", instalment="
				+ installment + ", status=" + 0 + " WHERE eligibility_id=" + id
				+ ";";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

			st.close();
			con.close();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

	public static ArrayList<CustomerInfoBean> getCutomerTicket() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, d.district_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ " a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id inner join city c on cs.customer_city=c.city_id inner join city_district dc on c.city_id=dc.city_id inner join district d on dc.district_id=d.district_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id "
					+ "WHERE h_status = 0 or h_status=1 ORDER BY al.status ASC";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getHighAlerts() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, d.district_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ " a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id inner join city c on cs.customer_city=c.city_id inner join city_district dc on c.city_id=dc.city_id inner join district d on dc.district_id=d.district_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id "
					+ "WHERE al.pannel_lost IS TRUE AND al.battery_low IS FALSE;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getMediumAlerts() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, d.district_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ " a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id inner join city c on cs.customer_city=c.city_id inner join city_district dc on c.city_id=dc.city_id inner join district d on dc.district_id=d.district_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id "
					+ "WHERE al.pannel_lost IS FALSE AND al.battery_low IS FALSE AND al.lid_open IS TRUE;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getLowAlerts() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, d.district_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ " a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id inner join city c on cs.customer_city=c.city_id inner join city_district dc on c.city_id=dc.city_id inner join district d on dc.district_id=d.district_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id "
					+ "WHERE location_changed IS TRUE AND battery_low IS FALSE AND pannel_lost IS false AND lid_open IS FALSE;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getVeryHighAlerts() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, d.district_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ " a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id inner join city c on cs.customer_city=c.city_id inner join city_district dc on c.city_id=dc.city_id inner join district d on dc.district_id=d.district_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id "
					+ "WHERE al.battery_low IS TRUE ;";
			// h_status = 0 or h_status=1 ORDER BY al.status ASC
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCustomers() {
		System.out.println("CustomerBAL.get_customers()");
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, e_status, c_status, state;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		Connection connection = Connect.getConnection();
		try {

			if (connection != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = connection
						.prepareCall("{call get_customers()}");
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					customerId = rs.getInt("customer_id");
					customerName = rs.getString("customer_name");
					cnicNo = rs.getString("customer_cnic");
					phoneNo = rs.getString("customer_phone");
					district = rs.getString("district_name");
					monthlyIncome = rs.getString("salary_range");
					gsmNumber = rs.getString("appliance_gsmno");
					state = rs.getInt("appliance_status");
					salesmanName = rs.getString("salesman_name");

					applianceId = rs.getInt("appliance_id");
					salesmanId = rs.getInt("salesman_id");
					applianceName = rs.getString("appliance_name");
					e_status = rs.getInt("e.status");
					c_status = rs.getInt("cs.status");
					bean = new CustomerInfoBean();
					bean.setCustomerName(customerName);
					bean.setCnicNo(cnicNo);
					bean.setDistrict(district);
					bean.setGsmNumber(gsmNumber);
					bean.setMonthlyIncome(monthlyIncome);
					bean.setApplianceStatus(state);
					bean.setSalesmanName(salesmanName);
					bean.setPhoneNo(phoneNo);
					bean.setSalesamanId(salesmanId);
					bean.setApplianceId(applianceId);
					bean.setCustomerId(customerId);
					bean.setApplianceName(applianceName);
					bean.setStatus(e_status);
					bean.setCustomerStatus(c_status);
					customerList.add(bean);
					// End Stored Procedure Calling -- Jetander
				}
			}
			if (connection != null) {
				connection.close();

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getDoCutomers(int districtId) {
		System.out.println("CustomerBAL.get_do_customers()");
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize, state;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		Connection connection = Connect.getConnection();
		try {
			if (connection != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = connection
						.prepareCall("{call get_do_customers(?)}");
				prepareCall.setInt(1, districtId);
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					customerId = rs.getInt(1);
					customerName = rs.getString(2);
					cnicNo = rs.getString(3);
					phoneNo = rs.getString(4);
					district = rs.getString(5);
					monthlyIncome = rs.getString(6);
					gsmNumber = rs.getString(7);
					state = rs.getInt(8);
					salesmanName = rs.getString(9);
					applianceId = rs.getInt(10);
					salesmanId = rs.getInt(11);
					applianceName = rs.getString(12);
					status = rs.getInt(13);
					familySize = rs.getInt(14);
					// End Stored Procedure Calling -- Jetander
					bean = new CustomerInfoBean();
					bean.setCustomerName(customerName);
					bean.setCnicNo(cnicNo);
					bean.setDistrict(district);
					bean.setGsmNumber(gsmNumber);
					bean.setMonthlyIncome(monthlyIncome);
					bean.setApplianceStatus(state);
					bean.setSalesmanName(salesmanName);
					bean.setPhoneNo(phoneNo);
					bean.setSalesamanId(salesmanId);
					bean.setApplianceId(applianceId);
					bean.setCustomerId(customerId);
					bean.setApplianceName(applianceName);
					bean.setStatus(status);
					bean.setFamilySize(familySize);
					customerList.add(bean);
				}
				rs.close();
				connection.close();
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static String getDistrict(String districtId) {
		String applianceName = null;
		Connection con = connection.Connect.getConnection();
		String query = "Select district_name from district WHERE district_id=?;";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, districtId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				applianceName = rs.getString(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return applianceName;
	}

	public static int getSoldId(int customerId) {
		int soldId = 0;
		Connection con = connection.Connect.getConnection();
		String query = "Select sold_to_id from sold_to WHERE customer_id=?;";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				soldId = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return soldId;
	}

	public static int getStatus(int applianceId) {
		int status = 0;
		Connection con = connection.Connect.getConnection();
		String query = "SELECT status FROM sold_to WHERE appliance_id = ?";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, applianceId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				status = rs.getInt(2);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return status;
	}

	public static int getCustomerId(String phone, int status) {
		int customerId = 0;
		Connection con = connection.Connect.getConnection();
		String query = "SELECT customer_id FROM customer WHERE customer_phone = ? AND status = ?;";
		try {

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, phone);
			stmt.setInt(2, status);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return customerId;
	}

	public static int deleteCustomer(int id) {

		Connection con = connection.Connect.getConnection();

		String query = "Delete FROM customer WHERE customer_id = " + id;
		Statement s = null;
		int row = 0;
		try {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been DELETED");
			} else {
				System.out.println("Data has not been DELETD");
			}

			s.close();
			con.close();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	public static int deleteAppliance(int id) {

		Connection con = connection.Connect.getConnection();

		String query = "Delete FROM appliance WHERE appliance_id = " + id;
		Statement s = null;
		int row = 0;
		try {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been DELETED");
			} else {
				System.out.println("Data has not been DELETD");
			}

			s.close();
			con.close();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	public static int deleteEligibility(int id) {

		Connection con = connection.Connect.getConnection();

		String query = "Delete FROM eligibility WHERE eligibility_id = " + id;
		Statement s = null;
		int row = 0;
		try {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been DELETED");
			} else {
				System.out.println("Data has not been DELETD");
			}

			s.close();
			con.close();

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	// getting all customers form Db
	public static CustomerInfoBean getCutomers(int soldID) {
		CustomerInfoBean bean = null;
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;
		Blob image;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name , sal.salary_range, a.appliance_GSMno, "
					+ "a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, sold.appliance_id, sold.customer_id, sold.salesman_id, cs.created_on, sold.product_handover,cs.status,cs.customr_image,d.district_name FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id join city c on cs.customer_city=c.city_id JOIN salary sal on cs.customer_monthly_income=sal.salary_id JOIN district d ON s.salesman_district=d.district_id WHERE sold.sold_to_id=?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, soldID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(19) + "/" + rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);

				createdOn = rs.getString(14);
				handoverAt = rs.getString(15);
				status = rs.getInt(16);
				image = rs.getBlob(17);

				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);

				bean.setStatus(status);
				bean.setImage(image);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // end of getting all customers form Db

	public static int getLoanId(int soldID) {
		int loanId = 0;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT loan_id from payment_loan WHERE soldto_id=?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, soldID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loanId = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return loanId;
	} // end of getting all customers form Db

	// getting all customers form Db

	// getting all customers form Db
	public static ArrayList<CustomerInfoBean> getCutomerTicket(int doId) {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name , cs.customer_monthly_income, a.appliance_GSMno, "
					+ "a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, "
					+ "cs.customer_family_size,cs.created_on, sold.product_handover,cs.status FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id join city c on cs.customer_city INNER JOIN salesman s ON s.salesman_id = sold.salesman_id JOIN city_district cd ON cs.customer_city=cd.city_id INNER JOIN alerts al ON sold.appliance_id = al.appliance_id INNER JOIN do_salesman ds "
					+ "ON s.salesman_id=ds.salesman_id WHERE cd.district_id="
					+ doId + " AND al.status=1 ORDER BY al.status ;";
			Statement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				list.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static PaymentBean getCustomerDetails(int loanId, int applianceID) {
		PaymentBean loanBean = null;
		new ArrayList<CustomerLoanBean>();
		int total_installments, grace_period;
		double total_amount, monthly_amount;
		Date createdOn, terminatedOn;
		String CustomerName;
		System.out.println(loanId);
		System.out.print(applianceID);
		Connection con = connection.Connect.getConnection();
		try {

			String query = "SELECT cs.customer_name, pl.total_installments ,pl.created_on,pl.grace_period,pl.recent_payed_amount,(pl.total_amount - pl.payed_installments_amount) AS remaining_balanse,pl.terminated_on ,pl.total_amount ,(pl.total_amount/total_installments)AS monthly_amount FROM payment_loan pl RIGHT JOIN sold_to sld ON pl.soldto_id=sld.sold_to_id  RIGHT JOIN customer cs ON cs.customer_id=sld.customer_id WHERE pl.loan_id="
					+ loanId + " AND sld.appliance_id=" + applianceID + ";";

			Statement stmt = con.prepareStatement(query);
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				CustomerName = rs.getString(1);
				total_installments = rs.getInt(2);
				createdOn = rs.getDate(3);
				grace_period = rs.getInt(4);
				rs.getDouble(5);
				rs.getDouble(6);
				terminatedOn = rs.getDate(7);
				total_amount = rs.getDouble(8);
				monthly_amount = rs.getDouble(9);
				loanBean = new PaymentBean();
				loanBean.setCustomerName(CustomerName);
				loanBean.setInstallmentCount(total_installments);
				loanBean.setCreatedOn(createdOn);
				loanBean.setGracePeriod(grace_period);
				loanBean.setInstallmentRemaining(total_installments);
				loanBean.setTerminatedOn(terminatedOn);
				loanBean.setTotalAmount(total_amount);
				loanBean.setTotalPayedInstallment(monthly_amount);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return loanBean;
	} // seraching customer by fi

	public static ArrayList<CustomerBean> getCutomersById(int customerID) {
		CustomerBean bean = null;
		ArrayList<CustomerBean> customerList = new ArrayList<CustomerBean>();
		int customerId, familyMember;
		double monthlyIncome, familyIncome;
		String customerName, cnicNo, phoneNo, address, district, customer_pic, accountCreatedDate;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT customer_id, customer_name, customer_cnic, customer_address, customer_district, customer_family_size, customer_phone,customer_monthly_income, customer_family_income, customer_payment_type, customr_image, created_on FROM customer where customer_id=?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customerID);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				address = rs.getString(4);
				district = rs.getString(5);
				familyMember = rs.getInt(6);
				phoneNo = rs.getString(7);
				monthlyIncome = rs.getDouble(8);
				familyIncome = rs.getDouble(9);
				rs.getString(10);
				customer_pic = rs.getString(11);
				accountCreatedDate = rs.getString(12);
				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);
				bean.setFamilyMember(familyMember);
				bean.setFamilyIncome(familyIncome);
				bean.setPhoneNo(phoneNo);

				bean.setAccountCreatedDate(accountCreatedDate);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setCustomer_pic(customer_pic);
				customerList.add(bean);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerMessageBean> getMessages(int customerID) {
		CustomerMessageBean bean = null;
		ArrayList<CustomerMessageBean> customerList = new ArrayList<CustomerMessageBean>();
		int customerId, messageId, status;
		String gsmNo, message;
		Date d = null;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT message_id, customer_id, text, gsm_no, status, message_date FROM customer_message where customer_id="
					+ customerID + " and type=0;";
			Statement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				messageId = rs.getInt(1);
				customerId = rs.getInt(2);
				message = rs.getString(3);
				gsmNo = rs.getString(4);
				status = rs.getInt(5);
				d = rs.getDate(6);
				bean = new CustomerMessageBean();
				bean.setMessageId(messageId);
				bean.setCustomerId(customerId);
				bean.setMessage(message);
				bean.setStatus(status);
				bean.setGsmNumber(gsmNo);
				bean.setMsgDate(d);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerBean> getCutomers(Date fromDate, Date toDate) {
		CustomerBean bean = null;
		ArrayList<CustomerBean> customerList = new ArrayList<CustomerBean>();
		int customerId, familyMember;
		double monthlyIncome, familyIncome;
		String customerName, cnicNo, phoneNo, address, district, customer_pic, accountCreatedDate;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT customer_id, customer_name, customer_cnic, customer_address, customer_district, customer_family_size, customer_phone,customer_monthly_income, customer_family_income, customer_payment_type, customr_image, created_on FROM customer where created_on BETWEEN("
					+ fromDate + "" + toDate + ") ;";
			Statement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				address = rs.getString(4);
				district = rs.getString(5);
				familyMember = rs.getInt(6);
				phoneNo = rs.getString(7);
				monthlyIncome = rs.getDouble(8);
				familyIncome = rs.getDouble(9);
				rs.getString(10);
				customer_pic = rs.getString(11);
				accountCreatedDate = rs.getString(12);
				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);
				bean.setFamilyMember(familyMember);
				bean.setFamilyIncome(familyIncome);
				bean.setPhoneNo(phoneNo);

				bean.setAccountCreatedDate(accountCreatedDate);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setCustomer_pic(customer_pic);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	// Viewing profile by Id
	public static CustomerBean getCutomer(int customerID) {
		CustomerBean bean = null;
		int customerId;
		double monthlyIncome, familyIncome;
		String customerName, cnicNo, phoneNo, address, district, customer_pic, accountCreatedDate;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT customer_id, customer_name, customer_cnic, customer_address, city_name,  customer_phone,customer_monthly_income, customer_family_income,  customr_image,created_on FROM customer join city on customer_city=city_id Where customer_id = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customerID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				address = rs.getString(4);
				district = rs.getString(5);

				phoneNo = rs.getString(7);
				monthlyIncome = rs.getDouble(8);
				familyIncome = rs.getDouble(9);

				customer_pic = rs.getString(11);
				accountCreatedDate = rs.getString(12);
				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);

				bean.setFamilyIncome(familyIncome);
				bean.setPhoneNo(phoneNo);

				bean.setAccountCreatedDate(accountCreatedDate);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setCustomer_pic(customer_pic);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // seraching customer by firstName

	// // seraching customer by firstName
	public static CustomerBean getCutomerByName(String customerFirstName,
			String customerLastName) {
		CustomerBean bean = null;
		int customerId, familyMember;
		double monthlyIncome, familyIncome;
		String customerName, cnicNo, phoneNo, address, district, customer_pic, accountCreatedDate;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT customer_id, customer_name, customer_cnic, customer_address, customer_district, customer_family_size, customer_phone,customer_monthly_income, customer_family_income, customer_payment_type, customr_image, created_on FROM customer Where first_name = ? And last_name=?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, customerFirstName);
			stmt.setString(2, customerLastName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				address = rs.getString(4);
				district = rs.getString(5);
				familyMember = rs.getInt(6);
				phoneNo = rs.getString(7);
				monthlyIncome = rs.getDouble(8);
				familyIncome = rs.getDouble(9);
				rs.getString(10);
				customer_pic = rs.getString(11);
				accountCreatedDate = rs.getString(12);
				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);
				bean.setFamilyMember(familyMember);
				bean.setFamilyIncome(familyIncome);
				bean.setPhoneNo(phoneNo);

				bean.setAccountCreatedDate(accountCreatedDate);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setCustomer_pic(customer_pic);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	} // seraching customer by firstName

	public static ArrayList<CustomerBean> getCutomerByName(
			String customerFirstName) {
		CustomerBean bean = null;
		ArrayList<CustomerBean> list = new ArrayList<CustomerBean>();
		int customerId, familyMember;
		double monthlyIncome, familyIncome;
		String customerName, cnicNo, phoneNo, address, district, customer_pic, accountCreatedDate;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT customer_id, customer_name, customer_cnic, customer_address, customer_district, customer_family_size, customer_phone,customer_monthly_income, customer_family_income, customer_payment_type, customr_image, created_on FROM customer Where user_name = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, customerFirstName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				address = rs.getString(4);
				district = rs.getString(5);
				familyMember = rs.getInt(6);
				phoneNo = rs.getString(7);
				monthlyIncome = rs.getDouble(8);
				familyIncome = rs.getDouble(9);
				rs.getString(10);
				customer_pic = rs.getString(11);
				accountCreatedDate = rs.getString(12);
				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);
				bean.setFamilyMember(familyMember);
				bean.setFamilyIncome(familyIncome);
				bean.setPhoneNo(phoneNo);

				bean.setAccountCreatedDate(accountCreatedDate);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setCustomer_pic(customer_pic);
				list.add(bean);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	} // seraching customer by firstName

	public static ArrayList<CustomerBean> getCutomerByDistrict(String District) {
		CustomerBean bean = null;
		ArrayList<CustomerBean> list = new ArrayList<CustomerBean>();
		int customerId, familyMember;
		double monthlyIncome, familyIncome;
		String customerName, cnicNo, phoneNo, address, district, customer_pic, accountCreatedDate;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT customer_id, customer_name, customer_cnic, customer_address, customer_district, customer_family_size, customer_phone,customer_monthly_income, customer_family_income, customer_payment_type, customr_image, created_on FROM customer Where customer_address = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, District);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				address = rs.getString(4);
				district = rs.getString(5);
				familyMember = rs.getInt(6);
				phoneNo = rs.getString(7);
				monthlyIncome = rs.getDouble(8);
				familyIncome = rs.getDouble(9);
				rs.getString(10);
				customer_pic = rs.getString(11);
				accountCreatedDate = rs.getString(12);
				bean = new CustomerBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);
				bean.setFamilyMember(familyMember);
				bean.setFamilyIncome(familyIncome);
				bean.setPhoneNo(phoneNo);

				bean.setAccountCreatedDate(accountCreatedDate);
				bean.setMonthlyIncome(monthlyIncome);
				bean.setCustomer_pic(customer_pic);
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // seraching customer by District

	public static CustomerLoanBean getCustomer_details(int customerId,
			int applianceId) {
		CustomerLoanBean bean = null;
		new ArrayList<CustomerLoanBean>();
		int total_installments, grace_period;
		double remaining_balanse, total_amount, monthly_amount, down_payment;
		String CustomerName, date, terminate_date, customerPhone, salemanPhone;
		System.out.println(customerId);
		System.out.print(applianceId);
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_Name, pl.total_Installments ,pl.created_on,pl.grace_period,pl.recent_payed_amount,(SELECT ap.appliance_price-(SELECT SUM(Amount_Paid) FROM loan_payments WHERE loan_id = 1) FROM appliance ap INNER "
					+ "JOIN sold_to sld ON ap.appliance_id=sld.Appliance_Id INNER JOIN customer cs ON sld.customer_id=cs.customer_id "
					+ "INNER JOIN payment_loan pl ON sld.sold_to_id=pl.soldto_id WHERE loan_id = 1) AS LONAoUTSTANDING,pl.terminated_on ,pl.total_amount ,pl.installment_amount_month, pl.terminated_on ,cs.customer_id, cs.customer_phone, s.salesman_phone_no FROM payment_loan pl \n"
					+ " JOIN sold_to sld ON pl.soldto_id=sld.sold_to_id\n"
					+ " JOIN customer cs ON sld.customer_id=cs.customer_id\n"
					+ " JOIN salesman s ON s.salesman_id = sld.salesman_id\n"
					+ " WHERE cs.customer_id="
					+ customerId
					+ " AND sld.appliance_id=" + applianceId + "";
			Statement stmt = con.prepareStatement(query);
			System.out.println(query);
			ResultSet rs = null;
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				CustomerName = rs.getString(1);
				total_installments = rs.getInt(2);
				date = rs.getString(3);
				grace_period = rs.getInt(4);
				down_payment = rs.getDouble(5);
				remaining_balanse = rs.getDouble(6);
				terminate_date = rs.getString(7);
				total_amount = rs.getDouble(8);
				monthly_amount = rs.getDouble(9);
				rs.getInt(10);
				customerPhone = rs.getString(11);
				salemanPhone = rs.getString(12);

				bean = new CustomerLoanBean();
				bean.setCustomerId(customerId);
				bean.setCustomerName(CustomerName);
				bean.setTotal_installments(total_installments);
				bean.setCreated_on(date);
				bean.setGrace_period(grace_period);
				bean.setDownPayment(down_payment);
				bean.setRemaining_balanse(remaining_balanse);
				bean.setTerminate_date(terminate_date);
				bean.setTotal_amount(total_amount);
				bean.setMonthly_amount(monthly_amount);
				bean.setPhoneNo(customerPhone);
				bean.setSalemanPhone(salemanPhone);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // seraching customer by firstName

	public static int getEligibilityId(int applianceId) {
		int eligibilityId = 0;
		String query = "Select eligibility_id from eligibility WHERE appliance_id=?;";
		try {
			Connection con = connection.Connect.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, applianceId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				eligibilityId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return eligibilityId;
	}

	// //////////// Removed By me haha :D :P
	// public static EligibilityExistCustomer getEligibilityById(int cid) {
	//
	// EligibilityExistCustomer eligObj = null;
	// int eligibility_id, installment_scheme, status, salesman_id;
	// double instalment, down_payment;
	// String date;
	// Connection con =Connect.getConnection();
	// String query = "SELECT eligibility_id,salesman_id, instalment,
	// //////////// installment_scheme, down_payment, DATE, status FROM
	// //////////// eligibility WHERE customer_id = ?;";
	// try {
	//
	// PreparedStatement stmt = con.prepareStatement(query);
	// stmt.setInt(1, cid);
	// rs = stmt.executeQuery();
	// while (rs.next()) {
	// eligibility_id = rs.getInt(1);
	// salesman_id = rs.getInt(2);
	//
	// installment_scheme = rs.getInt(3);
	// instalment = rs.getDouble(4);
	// down_payment = rs.getDouble(5);
	// date = rs.getString(6);
	// status = rs.getInt(7);
	//
	// eligObj = new EligibilityExistCustomer();
	// eligObj.setEligibility_id(eligibility_id);
	// eligObj.setSalesman_id(salesman_id);
	// eligObj.setInstallment_scheme(installment_scheme);
	// eligObj.setInstalment(instalment);
	// eligObj.setDown_payment(down_payment);
	// eligObj.setDate(date);
	// eligObj.setStatus(status);
	// }
	// } catch (SQLException ex) {
	// logger.error("",ex);
	// System.out.println(ex.getErrorCode());
	// }
	// return eligObj;
	// }

	public static int getEligibility(int applianceId) {
		int eligibilityId = 0;
		String query = "Select eligibility_id from eligibility WHERE customer_id=?;";
		try {
			Connection con = connection.Connect.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, applianceId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				eligibilityId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return eligibilityId;
	}

	public static int insertIntoMessage(int customerId, String message,
			String gsm, int telco) throws SQLException {
		int rows = 0;
		Connection con = connection.Connect.getConnection();
		try (Statement statement = con.createStatement()) {
			rows = statement
					.executeUpdate("INSERT INTO customer_message(customer_id, text, gsm_no, status, message_date,type,telco) VALUES ("
							+ customerId
							+ ",'"
							+ message
							+ "','"
							+ gsm
							+ "',"
							+ 0
							+ ", CURRENT_TIMESTAMP,"
							+ 0
							+ ","
							+ telco
							+ ");");
		}
		con.close();
		return rows;
	}

	public static int insertMessageIntoCustomer(CustomerMessageBean bean)
			throws SQLException {
		int rows = 0;
		Connection con = connection.Connect.getConnection();
		try (Statement statement = con.createStatement()) {
			rows = statement
					.executeUpdate("INSERT INTO customer_message(customer_id, text, gsm_no, status, message_date,type) VALUES ("
							+ bean.getCustomerId()
							+ ",'"
							+ bean.getMessage()
							+ "','"
							+ bean.getGsmNumber()
							+ "',"
							+ 1
							+ ", CURRENT_TIMESTAMP," + 0 + ");");
		}
		con.close();
		return rows;
	}

	public static int updateEligibilityInfo(int id, int status) {
		Connection con = connection.Connect.getConnection();
		String query = "Update eligibility SET info_status = " + status
				+ " WHERE eligibility_id = " + id;
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	public static int updateInfoStatus(int id, int status) {
		Connection con = connection.Connect.getConnection();
		String query = "Update eligibility SET info_status = " + status
				+ " WHERE eligibility_id = " + id;
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	public static int updateEligibility(int customerId, int applianceId,
			String text) {
		int row = 0;
		Connection connection = Connect.getConnection();
		// CHANGED BY JETANDER
		try {
			// Begin Procedure Call
			CallableStatement prepareCall = connection
					.prepareCall("{call update_rejectection_of_appliance(?,?,?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setInt(2, applianceId);
			prepareCall.setString(3, text);
			row = prepareCall.executeUpdate();
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of rejection

	public static int updateAppliance(int id, int status) {
		Connection con = connection.Connect.getConnection();
		String query = "Update appliance SET status = " + status
				+ " WHERE appliance_id = " + id;
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	public static int updateCustomerStatus(int id, int status) {
		Connection con = connection.Connect.getConnection();
		String query = "Update customer SET status = " + status
				+ " WHERE customer_id = " + id;
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of UserBean

	public static ArrayList<CustomerInfoBean> getDoCutomers_active(
			int districtId) {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+ "                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE cd.district_id="
					+ districtId
					+ " AND a.appliance_status =1 GROUP BY cs.customer_id\n";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				familySize = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				bean.setFamilySize(familySize);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getDoCutomers_inactive(
			int districtId) {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+ "                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE cd.district_id="
					+ districtId
					+ " AND a.appliance_status =0 GROUP BY cs.customer_id\n";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				familySize = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				bean.setFamilySize(familySize);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_onActive(int onloan,
			int oncash) {
		CustomerInfoBean bean = null;
		System.out.println("Onloan " + onloan + " oncsah " + oncash);
		// if(onloan==0 && oncash==0){querycon="";}
		//
		// if(onloan==1){querycon="and sld.payement_option=1";}
		// else if (oncash==1){querycon="and sld.payement_option=0";} else
		// {querycon="";}
		// // if((onloan==1) && (oncash==1)){querycon="and sld.payement_option=0
		// or sld.payement_option=1";} else {querycon="";}
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name ,sld.payement_option, a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id       \n"
					+ "                     JOIN sold_to sld ON cs.customer_id=sld.customer_id\n"
					+ "            JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    WHERE a.appliance_status=1;";
			PreparedStatement stmt = con.prepareStatement(query);
			System.out.print(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(11);
				salesmanId = rs.getInt(12);
				applianceName = rs.getString(13);
				status = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_onInActive() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, \n"
					+ "                    a.appliance_GSMno, a.appliance_status, s.salesman_name ,a.appliance_status, a.appliance_id, s.salesman_id, \n"
					+ "                    a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      JOIN appliance a ON  e.appliance_id =a.appliance_id \n"
					+ "                     JOIN salesman s ON  e.salesman_id =s.salesman_id \n"
					+ "                     JOIN customer cs ON e.customer_id = cs.customer_id       \n"
					+ "                     JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                    WHERE a.appliance_status=0";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(11);
				salesmanId = rs.getInt(12);
				applianceName = rs.getString(13);
				status = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static int getUnseenCountAlerts() {
		int count = 0;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT COUNT(is_seen) from alerts where is_seen = 0";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return count;
	} // end of getting all customers form Db

	public static int getUnseenCountAlertsForAdmin() {
		System.out.println("CustomerBAL.getUnseenCountAlertsForAdmin()");
		int count = 0;
		Connection con = connection.Connect.getConnection();
		try {

			/*
			 * String query =
			 * "SELECT COUNT(msg_seen) FROM appliance_message m JOIN appliance ap ON m.msg_from = ap.appliance_GSMno WHERE msg_seen = 0"
			 * ; ps2 = (PreparedStatement) con2.prepareStatement(query); rs2 =
			 * ps2.executeQuery();
			 */

			// Begin Stored Procedure -- Jeevan
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_unseen_count_alerts_for_admin()}");
			ResultSet resultSet = prepareCall.executeQuery();
			// End Stored Procedure

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			resultSet.close();
			prepareCall.close();
			// con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getUnseenAlerts() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ "a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,"
					+ "cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id join city c on cs.customer_city=c.city_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id WHERE al.is_seen=0 ORDER BY al.status ASC;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getUnseenAlertsForAdmin() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> list = new ArrayList<>();
		String monthlyIncome;
		int applianceId, soldId, customerId, salesmanId, size, status, alertID;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, createdOn, handoverAt;
		boolean state;

		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT distinct(cs.customer_name), cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income, a.appliance_GSMno, "
					+ "a.appliance_status, s.salesman_name, sold.payement_option, sold.sold_to_id, a.appliance_id, sold.customer_id, sold.salesman_id, cs.customer_family_size,"
					+ "cs.created_on, sold.product_handover,al.status,al.alerts_id FROM sold_to sold INNER JOIN customer cs ON cs.customer_id=sold.customer_id "
					+ " INNER JOIN appliance a ON a.appliance_id=sold.appliance_id join city c on cs.customer_city=c.city_id INNER JOIN salesman s ON s.salesman_id = sold.salesman_id INNER JOIN alerts al ON al.appliance_id=sold.appliance_id WHERE al.admin_is_seen=0 ORDER BY al.status ASC;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerName = rs.getString(1);
				cnicNo = rs.getString(2);
				phoneNo = rs.getString(3);
				district = rs.getString(4);
				monthlyIncome = rs.getString(5);
				gsmNumber = rs.getString(6);
				state = rs.getBoolean(7);
				salesmanName = rs.getString(8);
				rs.getBoolean(9);
				soldId = rs.getInt(10);
				applianceId = rs.getInt(11);
				customerId = rs.getInt(12);
				salesmanId = rs.getInt(13);
				size = rs.getInt(14);
				createdOn = rs.getString(15);
				handoverAt = rs.getString(16);
				status = rs.getInt(17);
				alertID = rs.getInt(18);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSoldId(soldId);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setHandoverAt(handoverAt);
				bean.setCreatedOn(createdOn);
				bean.setFamilySize(size);
				bean.setStatus(status);
				bean.setAlertId(alertID);
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static int updateSeenAlerts(int id) {
		Connection con = connection.Connect.getConnection();
		String query = "Update alerts SET is_seen=" + 1 + " WHERE alerts_id="
				+ id + ";";
		int row = 0;
		try {

			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

	public static int updateSeenAlertsForSuperadmin(int id) {
		Connection con = connection.Connect.getConnection();
		String query = "Update alerts SET admin_is_seen=" + 1
				+ " WHERE alerts_id=" + id + ";";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	}

	public static ArrayList<OccupationBean> getOccupations() {
		OccupationBean bean = null;
		ArrayList<OccupationBean> list = new ArrayList<>();
		int occupation_id;
		String occupation_name;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT occupation_id,occupation_name FROM occupation";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				occupation_id = rs.getInt(1);
				occupation_name = rs.getString(2);
				bean = new OccupationBean();
				bean.setOccupation_id(occupation_id);
				bean.setOccupation_name(occupation_name);
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static int Add_customer(String name, String cnic, String Address,
			int customer_dist, int family_member, String phone_no,
			double monthly_income, double family_income, int payment_method,
			int status, int occupation, InputStream input) throws SQLException {
		int rows = 0;
		try {
			Connection con = connection.Connect.getConnection();

			PreparedStatement statement = con
					.prepareStatement("INSERT INTO customer(customer_name,customer_cnic,customer_address,customer_city,customer_family_size,customer_phone,customer_monthly_income,customer_family_income,customer_payment_type,created_on,status,occupation,customr_image) VALUES (?,?,?,?,?,?,?, ?,?,CURRENT_TIMESTAMP,?,?,?);");
			statement.setString(1, name);
			statement.setString(2, cnic);
			statement.setString(3, Address);
			statement.setString(6, phone_no);
			statement.setInt(11, occupation);
			statement.setInt(4, customer_dist);
			statement.setInt(5, family_member);
			statement.setInt(9, payment_method);
			statement.setInt(10, status);
			statement.setDouble(7, monthly_income);
			statement.setDouble(8, family_income);
			statement.setBlob(12, input);
			// statement.setString(10,CURRENT_TIM);
			statement.executeUpdate();
			// rows = statement.executeUpdate("INSERT INTO
			// customer(customer_name,customer_cnic,customer_address,customer_district,customer_family_size,customer_phone,customer_monthly_income,customer_family_income,customer_payment_type,created_on,status,occupation,customr_image)
			// VALUES ('" + name + "','" + cnic + "','" + Address + "'," +
			// customer_dist + "," + family_member + ",'" + phone_no + "'," +
			// monthly_income + ", " + family_income + "," + payment_method +
			// ",CURRENT_TIMESTAMP," + status + ",'" + occupation +
			// "','"+input+"');");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return rows;
	}

	public static ArrayList<SalaryBean> Customer_salary_range() {
		SalaryBean bean = null;
		ArrayList<SalaryBean> list = new ArrayList<>();
		int salary_id;
		String salary_range;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT salary_id,salary_range FROM salary";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				salary_id = rs.getInt(1);
				salary_range = rs.getString(2);
				bean = new SalaryBean();
				bean.setSalary_id(salary_id);
				bean.setSalary_range(salary_range);
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_notinterested() {
		CustomerInfoBean bean = null;
		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id join city c on cs.customer_city=c.city_id WHERE  cs.status=3 GROUP BY cs.customer_id\n";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getDoCutomers_notinterested(
			int districtId) {
		CustomerInfoBean bean = null;

		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status, familySize;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status, cs.customer_family_size FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+

					"                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE cd.district_id="
					+ districtId + " AND cs.status=3 GROUP BY cs.customer_id\n";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				familySize = rs.getInt(14);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				bean.setFamilySize(familySize);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_Filtered(
			String districtfiltered, String salesman_name, String startdate,
			String endtime, String payment) throws java.text.ParseException {
		CustomerInfoBean bean = null;

		String STARTDATE = null;
		String ENDDATE = null;

		// System.out.println(newDateString);

		// (1) create a SimpleDateFormat object with the desired format.
		// this is the format/pattern we're expecting to receive.
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		try {
			// (2) give the formatter a String that matches the SimpleDateFormat
			// pattern

			Date strtdate = formatter.parse(startdate);
			Date endddate = formatter.parse(endtime);

			// (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
			System.out.println("Date  " + strtdate);
			STARTDATE = new SimpleDateFormat("yyyy-MM-dd").format(strtdate);
			ENDDATE = new SimpleDateFormat("yyyy-MM-dd").format(endddate);

		} catch (ParseException e) {
			logger.error("", e);
			// execution will come here if the String that is given
			// does not match the expected format.
			e.printStackTrace();
		}

		// System.out.println("enddate1 Date SERVER "+date2);

		System.out.println("districtfiltered+ " + districtfiltered);
		System.out.println("salesman_name+ " + salesman_name);
		System.out.println("startdate+ " + startdate);
		System.out.println("endtime+ " + endtime);
		System.out.println("payment+ " + payment);

		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, sal.salary_range, a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,  a.appliance_name, e.status FROM eligibility e \n"
					+ "INNER JOIN appliance a ON  e.appliance_id =a.appliance_id  \n"
					+ "INNER JOIN salesman s ON  e.salesman_id =s.salesman_id  \n"
					+ "INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "join sold_to sld on cs.customer_id=sld.customer_id\n"
					+ "JOIN do_salesman ds ON  s.salesman_id=ds.salesman_id\n"
					+ "JOIN city c ON cs.customer_city=c.city_id\n"
					+ "JOIN city_district cd ON c.city_id=cd.city_id\n"
					+ "JOIN salary sal ON cs.customer_monthly_income=sal.salary_id\n"
					+

					"where cd.district_id IN ("
					+ districtfiltered
					+ ") and s.salesman_name LIKE '"
					+ salesman_name
					+ "%' and s.salesman_name like '%"
					+ salesman_name
					+ "' and cs.created_on BETWEEN '"
					+ STARTDATE
					+ "' AND '"
					+ ENDDATE
					+ "' and sld.payement_option IN ("
					+ payment
					+ ")\n" + "order by cs.customer_name";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			System.out.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);
				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static ArrayList<CustomerInfoBean> getCutomers_notinterested_Super() {
		CustomerInfoBean bean = null;

		ArrayList<CustomerInfoBean> customerList = new ArrayList<CustomerInfoBean>();
		String monthlyIncome;
		int applianceId, customerId, salesmanId, status;
		String customerName, cnicNo, phoneNo, district, gsmNumber, salesmanName, applianceName;
		boolean state;
		try {
			Connection con = connection.Connect.getConnection();
			String query = "SELECT cs.customer_id ,cs.customer_name, cs.customer_cnic ,cs.customer_phone, c.city_name, cs.customer_monthly_income,\n"
					+ "                      a.appliance_GSMno, a.appliance_status, s.salesman_name , a.appliance_id, s.salesman_id,\n"
					+ "                      a.appliance_name, cs.status FROM eligibility e\n"
					+ "                      INNER JOIN appliance a ON  e.appliance_id =a.appliance_id\n"
					+ "                      INNER JOIN salesman s ON  e.salesman_id =s.salesman_id\n"
					+ "                      INNER JOIN customer cs ON e.customer_id = cs.customer_id \n"
					+ "                      JOIN city c ON cs.customer_city=c.city_id\n"
					+ "                      JOIN city_district cd ON cs.customer_city=cd.city_id\n"
					+

					"                      INNER JOIN do_salesman ds ON s.salesman_id=ds.salesman_id WHERE e.status=3 GROUP BY cs.customer_id\n";
			Statement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			System.err.println(query);
			while (rs.next()) {
				customerId = rs.getInt(1);
				customerName = rs.getString(2);
				cnicNo = rs.getString(3);
				phoneNo = rs.getString(4);
				district = rs.getString(5);
				monthlyIncome = rs.getString(6);
				gsmNumber = rs.getString(7);
				state = rs.getBoolean(8);
				salesmanName = rs.getString(9);

				applianceId = rs.getInt(10);
				salesmanId = rs.getInt(11);
				applianceName = rs.getString(12);
				status = rs.getInt(13);

				bean = new CustomerInfoBean();
				bean.setCustomerName(customerName);
				bean.setCnicNo(cnicNo);
				bean.setDistrict(district);
				bean.setGsmNumber(gsmNumber);
				bean.setMonthlyIncome(monthlyIncome);

				bean.setState(state);
				bean.setSalesmanName(salesmanName);
				bean.setPhoneNo(phoneNo);
				bean.setSalesamanId(salesmanId);
				bean.setApplianceId(applianceId);
				bean.setCustomerId(customerId);
				bean.setApplianceName(applianceName);
				bean.setStatus(status);

				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	} // end of getting all customers form Db

	public static int updateSeenRequest(int id) {
		Connection con = connection.Connect.getConnection();
		String query = "Update eligibility SET is_seen=" + 1
				+ " WHERE eligibility_id = " + id + ";";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			st.close();
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int updateSeenAcceptedRequest(int id) {
		Connection con = connection.Connect.getConnection();
		String query = "Update eligibility SET accepted_seen=" + 1
				+ " WHERE eligibility_id = " + id + ";";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static String getCustomerName(int customer_id) {

		String customer_name = null;
		String query = "select customer_name from customer where customer_id=?";
		try {
			Connection con = connection.Connect.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customer_name = rs.getString(1);

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customer_name;
	}

	public static CustomerInfoBean getCustomerLoanDetails(String appliance_gsm) {

		CustomerInfoBean bean = null;

		int appliance_id, loan_id, customer_id;

		String query = "SELECT ap.appliance_id,p.loan_id,sld.customer_id FROM appliance ap "
				+ " JOIN sold_to sld ON ap.appliance_id=sld.appliance_id "
				+ " JOIN payment_loan p ON sld.sold_to_id=p.soldto_id"
				+ " WHERE ap.appliance_GSMno=" + appliance_gsm;
		try {
			Connection con = connection.Connect.getConnection();

			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			System.out.println(query);
			while (rs.next()) {
				appliance_id = rs.getInt(1);
				loan_id = rs.getInt(2);
				customer_id = rs.getInt(3);
				System.out.println(appliance_id + " " + loan_id + "  "
						+ customer_id);
				bean = new CustomerInfoBean();
				bean.setApplianceId(appliance_id);
				bean.setCustomerId(customer_id);
				bean.setSoldId(loan_id);

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static String[][] getCustomerBySalesmanId(int salesmanId) {
		String[][] list = null;
		Connection connection = Connect.getConnection();
		// made by Jetander
		try {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customers_for_salesman(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet rs = prepareCall.executeQuery();
			System.out.println("--- Get Salesmans Customers -----\n");
			rs.last();
			System.out.println("Total Customers " + rs.getRow());
			list = new String[rs.getRow()][10];
			int i = 0;
			if (rs.first()) {
				do {
					list[i][0] = "" + rs.getInt("c.customer_id");
					list[i][1] = rs.getString("c.customer_name");
					list[i][2] = rs.getString("c.customer_cnic");
					list[i][3] = rs.getString("c.customer_phone");
					list[i][4] = "" + rs.getInt("c.status");
					list[i][5] = rs.getString("a.appliance_name");
					list[i][6] = rs.getString("a.imei_number");
					list[i][7] = rs.getString("a.appliance_status");
					list[i][8] = rs.getString("cty.city_name");
					list[i][9] = rs.getInt("a.appliance_id") + "";
					i++;
				} while (rs.next());

			}
			connection.close();
			// connection.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("CustomerBal getCustomerBySalesmanId : "
					+ e.getLocalizedMessage());
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomersDetailBySalesmanId(
			Integer salesmanId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		Connection con = connection.Connect.getConnection();
		try {
			CallableStatement cs = con
					.prepareCall("{CALL get_customer_by_salemanId(?)}");
			cs.setInt(1, salesmanId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("CustomerBal getCustomerBySalesmanId : "
					+ e.getLocalizedMessage());
		}

		return list;
	}

	public static int insertTotalMessage(String send_from, String send_to,
			String message) throws SQLException {
		int rows = 0;
		Connection con = connection.Connect.getConnection();
		try (Statement statement = con.createStatement()) {

			rows = statement
					.executeUpdate("INSERT INTO total_msg_customer_appliance(Msg_from,Msg_to,Msg_text,Msg_Date) VALUES ('"
							+ send_from
							+ "','"
							+ send_to
							+ "','"
							+ message
							+ "', CURRENT_TIMESTAMP);");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		con.close();
		return rows;
	}

	public static int getUnseenCountMessagesForAdmin() {
		System.out.println("CustomerBAL.getUnseenCountMessagesForAdmin()");
		int count = 0;

		// connection.Connect.init2();
		// String query = "SELECT COUNT(msg_seen) FROM appliance_message_status
		// m JOIN appliance ap ON m.msg_from = ap.appliance_GSMno WHERE msg_seen
		// = 0";
		try {
			// ps2 = (PreparedStatement) con2.prepareStatement(query);
			// rs2 = ps2.executeQuery();
			// Begin Stored Procedure -- Jeevan
			Connection connection = Connect.getConnection();
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_unseen_count_messages_for_admin()}");
			ResultSet resultSet = prepareCall.executeQuery();
			// End Stored Procedure
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			// rs.close();
			// con.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;

	}// end of getting all message from Db

	public static ArrayList<NumberOfMsgFrom> getUnseenMessagesNumberForAdmin() {
		System.out.println("CustomerBAL.getUnseenMessagesNumberForAdmin()");
		String msgfrom;
		int msgfromid;
		Timestamp dateTime;
		String prettyTime;
		int appliance_id;
		// connection.Connect.init2();
		PrettyTime p = new PrettyTime();
		ArrayList<NumberOfMsgFrom> list = new ArrayList<>();
		NumberOfMsgFrom bean;

		try {

			Connection connection = Connect.getConnection();
			if (connection != null) {

				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_unseen_messages_number_for_admin()}");
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedure
				while (resultSet.next()) {
					msgfrom = resultSet.getString(1);
					msgfromid = resultSet.getInt(2);
					dateTime = resultSet.getTimestamp(3);
					appliance_id = resultSet.getInt(4);
					Date date = new Date(dateTime.getTime());
					prettyTime = p.format((date));

					bean = new NumberOfMsgFrom();
					bean.setMsg_form(msgfrom);
					bean.setMsg_id(msgfromid);
					bean.setMsg_time(prettyTime);
					bean.setAppliance_id(appliance_id);
					list.add(bean);
				}
				// rs.close();
				// con.close();
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {

		}
		return list;

	}// end of getting all message number from appliance_message

	// begin Count Unseen Messages From Appliance Status
	// Coded by -- Jeevan
	public static int countUnseenMessagesFromApplianceStatus(int doId) {
		System.out
				.println("CustomerBAL.countUnseenMessagesFromApplianceStatus()");
		int count = 0;
		try {
			Connection connection = Connect.getConnection();
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_unseen_messages_from_appliance_status(?)}");
				prepareCall.setInt(1, doId);
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedure
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
				resultSet.close();
				prepareCall.close();
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {

		}
		return count;

	}// duplicate of getting all message number from appliance_message

	// begin Count Unseen Messages From Appliance Status
	// Coded by -- Jeevan
	public static ArrayList<HashMap<String, String>> getUnseenMessagesFromApplianceStatus() {
		// System.out.println("CustomerBAL.getUnseenMessagesFromApplianceStatus()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection();) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_unseen_messages_from_appliance_status()}");
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedure
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("messageId",
							String.valueOf(resultSet.getInt("msg_id")));
					map.put("messageFrom", resultSet.getString("msg_from"));

					PrettyTime prettyTime = new PrettyTime();
					String messageTime = prettyTime.format(new Date(resultSet
							.getTimestamp("msg_date").getTime()));
					// System.out.println(prettyTime);
					map.put("messageDate", messageTime);

					map.put("applianceId",
							String.valueOf(resultSet.getInt("appliance_id")));
					list.add(map);
				}
				// resultSet.close();
				// prepareCall.close();
				// connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}// duplicate of getting all message number from appliance_message

	public static ArrayList<HashMap<String, String>> getUnseenMessagesFromAppliance() {
		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection();) {
			// Begin Stored Procedure -- Jeevan
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_unseen_status_messages_from_appliance()}");
			ResultSet result = prepareCall.executeQuery();
			// End Stored Procedure
			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("messageId", String.valueOf(result.getInt("alarm_id")));
				map.put("messageFrom", result.getString("appliance_GSMno"));
				Timestamp dateTime = result.getTimestamp("alarm_time");
				Date date = new Date(dateTime.getTime());
				// System.out.println(p.format((date)));
				map.put("messageDate", p.format((date)));
				map.put("applianceId", result.getString("appliance_id"));
				list.add(map);
			}
			result.close();
			prepareCall.close();
			connection.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}// end of getting all message number from appliance_message

	// Begin CustomerBAL.countUnseenMessagesFromAppliance() created & coded by
	// Jeevan
	public static int countUnseenMessagesFromAppliance() {
		// System.out.println("CustomerBAL.countUnseenMessagesFromAppliance()");
		int count = 0;

		try (Connection connection = Connect.getConnection();) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_unseen_status_messages_from_appliance()}");
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedure
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
				// rs.close();
				// con.close();
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;

	}

	// End CustomerBAL.countUnseenMessagesFromAppliance() created & coded by
	// Jeevan

	public static int getApplianceNumberById() {
		int appliancenum = 0;
		return appliancenum;
	}// end getApplianceNumberById from DB

	public static int updateStatusAlerts(int id) {
		String query = "UPDATE appliance_message_status SET msg_seen = " + 1
				+ " WHERE msg_id=" + id;
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			st.close();
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int updateAlert(int id) {
		String query = "UPDATE appliance_message SET msg_seen = " + 1
				+ " WHERE msg_id=" + id;
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int updateAllApplianceMessageAlerts() {
		Connection con = connection.Connect.getConnection();
		String query = "UPDATE appliance_message m JOIN appliance ap ON m.msg_from = ap.appliance_GSMno SET m.msg_seen = 1 WHERE m.msg_seen = 0";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			st.close();
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int updateAllApplianceMessageStatusAlerts() {
		Connection con = connection.Connect.getConnection();
		String query = "UPDATE appliance_message_status m JOIN appliance ap ON m.msg_from = ap.appliance_GSMno SET m.msg_seen = 1 WHERE m.msg_seen = 0";
		int row = 0;
		try {
			Statement st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			st.close();
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static ArrayList<DistrictOfficerList> getDistrictOfficerList() {
		// System.out.println("CustomerBAL.getDistrictOfficerList()");
		int userId;
		String userName;
		String districtId;
		// connection.Connect.init2();
		ArrayList<DistrictOfficerList> list = new ArrayList<>();
		DistrictOfficerList bean;

		// String query = "SELECT u.user_id, u.user_name, d.district_name FROM
		// user u JOIN district d ON d.district_id = u.user_district WHERE
		// user_type = 101";
		try {
			// Begin Stored Procedures -- Jeevan
			Connection connection = Connect.getConnection();
			if (connection != null) {

				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_district_officer_list()}");
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedures
				// ps2 = (PreparedStatement) con2.prepareStatement(query);
				// rs2 = ps2.executeQuery();
				while (resultSet.next()) {
					userId = resultSet.getInt(1);
					userName = resultSet.getString(2);
					districtId = resultSet.getString(3);
					bean = new DistrictOfficerList();
					bean.setUser_id(userId);
					bean.setUser_name(userName);
					bean.setUser_district(districtId);
					list.add(bean);
				}
				// rs.close();
				// con.close();
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}

	// For full detail of DO
	public static HashMap<String, String> getDoDetailForUpdate(int doId) {
		HashMap<String, String> map = null;
		Connection con = Connect.getConnection();
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			call = con.prepareCall("{CALL get_district_officer_detail(?)}");
			call.setInt(1, doId);
			rs = call.executeQuery();

			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}

			if (rs.next()) {
				map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

	public static ArrayList<ServiceOperationBean> getSOCList() {
		// System.out.println("CustomerBAL.getSOCList()");
		int userId;
		String userName;
		int districtId;
		// connection.Connect.init2();
		ArrayList<ServiceOperationBean> list = new ArrayList<>();
		ServiceOperationBean bean;

		// String query = "SELECT user_id, user_name, user_district FROM user
		// WHERE user_type = 103";
		try {
			Connection connection = Connect.getConnection();
			if (connection != null) {

				// ps2 = (PreparedStatement) con2.prepareStatement(query);

				// Begin Stored Procedure -- Jeevan
				CallableStatement prepareCall = connection
						.prepareCall("{CALL get_soc_list()}");
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedures
				while (resultSet.next()) {
					userId = resultSet.getInt(1);
					userName = resultSet.getString(2);
					districtId = resultSet.getInt(3);
					bean = new ServiceOperationBean();
					bean.setUserId(userId);
					bean.setUserName(userName);
					bean.setUserDistrict(districtId);
					list.add(bean);
				}
				// rs.close();
				// con.close();
				connection.close();
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}

	public static String[][] getCustomerDateWiseBySalesmanId(
			Integer salesmanId, String startDate, String endDate) {
		System.err
				.println("CustomerBal.get_customers_of_salesman_date_wise(?,?,?)");
		String[][] list = null;
		// Connection con = connection.Connect.getConnection();
		// try {
		// String query = "SELECT DISTINCT(a.appliance_id), " + " c.customer_id,
		// " + " c.customer_name, "
		// + " c.customer_cnic, " + " c.customer_phone, " + " c.status, " + "
		// a.appliance_name, "
		// + " a.appliance_GSMno, " + " a.appliance_status, " + "
		// c.customer_city, " + " cty.city_name "
		// + " FROM sold_to s " + " INNER JOIN appliance a ON a.appliance_id =
		// s.appliance_id "
		// + " INNER JOIN payment_loan pl ON pl.soldto_id = s.sold_to_id "
		// + " INNER JOIN loan_payments lp ON lp.loan_id = pl.loan_id "
		// + " INNER JOIN customer c ON lp.Customer_id = c.customer_id "
		// + " INNER JOIN city cty ON cty.city_id = c.customer_city " + " WHERE
		// c.created_on >= '" + startDate
		// + "' " + " AND c.created_on <= '" + endDate + "' " + " AND
		// s.salesman_id = " + salesmanId;
		//
		// Statement stmt = con.createStatement();
		// rs = stmt.executeQuery(query);
		Connection connection = Connect.getConnection();
		// made by Jetander
		try {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customers_of_salesman_date_wise(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, startDate);
			prepareCall.setString(3, endDate);
			ResultSet rs = prepareCall.executeQuery();
			System.out.println("--- Get Salesmans Customers -----\n");
			rs.last();
			System.out.println("Total Customers " + rs.getRow());
			list = new String[rs.getRow()][12];
			int i = 0;
			if (rs.first()) {
				do {
					list[i][0] = "" + rs.getInt("c.customer_id");
					list[i][1] = rs.getString("c.customer_name");
					list[i][2] = rs.getString("c.customer_cnic");
					list[i][3] = rs.getString("c.customer_phone");
					list[i][4] = "" + rs.getInt("c.status");
					list[i][5] = rs.getString("a.appliance_name");
					list[i][6] = rs.getString("a.appliance_GSMno");
					list[i][7] = rs.getString("a.appliance_status");
					list[i][8] = rs.getString("cty.city_name");
					list[i][9] = rs.getInt("a.appliance_id") + "";
					list[i][10] = rs.getString("lp.Activated_Date");
					list[i][11] = rs.getString("c.Customer_address");
					i++;
				} while (rs.next());
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
			System.err.println("CustomerBal getCustomerBySalesmanId : "
					+ e.getLocalizedMessage());
		}

		return list;
	}

	public static ArrayList<SuperAdminListBean> getSuperAdminList() {
		int userId;
		String userName;
		String districtId;
		Connection con = connection.Connect.getConnection();
		ArrayList<SuperAdminListBean> list = new ArrayList<>();
		SuperAdminListBean bean;

		String query = "SELECT u.user_id, u.user_name, d.district_name FROM user u JOIN district d ON d.district_id = u.user_district WHERE user_type = 100";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
				userName = rs.getString(2);
				districtId = rs.getString(3);
				bean = new SuperAdminListBean();
				bean.setUser_id(userId);
				bean.setUser_name(userName);
				bean.setUser_district(districtId);
				list.add(bean);
			}
			// rs.close();
			// con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}

	public static boolean getCustomerNumber(String column, String table,
			String number) {
		System.out.println("CustomerBAL.getCustomerNumber()");
		int mobile = 0;
		System.err.println(table);
		String query = "SELECT count(*) FROM " + table + " WHERE " + column
				+ "= '" + number + "'";
		System.out.println(query);
		try (Connection con = Connect.getConnection();) {

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mobile = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		if (mobile > 0) {
			return true;
		} else {
			return false;
		}

	}

	// coded by Jeevan
	public static ArrayList<HashMap<String, String>> getCustomersByLimitAndRangeAndOrderBy(
			int start, int length, int column, String dir) {
		System.out
				.println("CustomerBAL.getCustomersByLimitAndRangeAndOrderBy()");
		System.out.println("Column : " + column + ", Order Direction : " + dir);
		ArrayList<HashMap<String, String>> listMap = new ArrayList<>();
		Connection connection = Connect.getConnection();
		CallableStatement prepareCall = null;
		ResultSet resultSet;
		try {

			// Ascending Order
			if (dir.equalsIgnoreCase("asc")) {
				switch (column) {
				case 2:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_customer_name_asc(?, ?)}");
					break;
				case 3:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_district_asc(?, ?)}");
					break;
				case 4:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_monthly_income_asc(?, ?)}");
					break;
				case 6:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_appl_name_asc(?, ?)}");
					break;
				default:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_customer_name_asc(?, ?)}");
					break;
				}

				// Descending Order
			} else if (dir.equalsIgnoreCase("desc")) {
				switch (column) {
				case 2:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_customer_name_desc(?, ?)}");
					break;
				case 3:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_district_desc(?, ?)}");
					break;
				case 4:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_monthly_income_des(?, ?)}");
					break;
				case 6:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_appl_name_des(?, ?)}");
					break;
				default:
					prepareCall = connection
							.prepareCall("{CALL get_customers_by_limit_and_range_and_order_by_customer_name_desc(?, ?)}");
					break;
				}

			}
			if (prepareCall != null) {

				prepareCall.setInt(1, start);
				prepareCall.setInt(2, length);

				resultSet = prepareCall.executeQuery();

				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("eligibilityId", resultSet.getInt("eligibility_id")
							+ "");
					map.put("DT_RowId",
							"row_customer_id_"
									+ resultSet.getInt("eligibility_id"));
					map.put("eligibilityStatus", resultSet.getInt("e.status")
							+ "");
					map.put("customerId", resultSet.getInt("customer_id") + "");
					map.put("applianceId", resultSet.getInt("appliance_id")
							+ "");
					map.put("customerName",
							resultSet.getString("customer_name"));
					map.put("districtName",
							resultSet.getString("district_name"));
					map.put("customerPhoneNumber",
							resultSet.getString("customer_phone"));
					map.put("customerCnic",
							resultSet.getString("customer_cnic"));
					// map.put("monthlyIncome",
					// resultSet.getString("salary_range"));
					map.put("customerStatus", resultSet.getInt("cs.status")
							+ "");
					map.put("applianceName", resultSet.getString("appliances"));
					map.put("applianceStatus",
							resultSet.getInt("appliance_status") + "");
					if (resultSet.getInt("rating") == 0) {
						map.put("customer_rating", "N/A");
					} else {
						map.put("customer_rating", resultSet.getInt("rating")
								+ " %");
					}
					listMap.add(map);
				}

				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return listMap;
	}

	// Jetander
	public static ArrayList<HashMap<String, String>> getRejectedCustomers(
			int start, int range) {
		Connection connection = Connect.getConnection();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_rejected_appliance_loan_requests(?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id")
						+ "");
				map.put("DT_RowId",
						"row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber",
						resultSet.getString("customer_phone"));
				// map.put("monthlyIncome",
				// resultSet.getString("salary_range"));
				map.put("customerStatus", resultSet.getInt("cs.status") + "");
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status")
						+ "");
				list.add(map);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	// coded by Jeevan
	public static int countCustomers() {
		System.out.println("CustomerBAL.countCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_customers()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countCustomersDo(int districtId) {
		System.out.println("CustomerBAL.countCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_customers_do(?)}");
			prepareCall.setInt(1, districtId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	// jetander
	public static int countRejectedCustomers(int start, int range) {
		System.out.println("CustomerBAL.count_rejected_customers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_rejected_customers(?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<HashMap<String, String>> getAllCustomerSalaryRanges() {
		System.out.println("CustomerBAL.getAllCustomerSalaryRanges()");
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
		Connection connection = Connect.getConnection();
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_cutomer_salary_range()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salaryRangeId", resultSet.getInt("salary_id") + "");
				map.put("salaryRange", resultSet.getString("salary_range"));
				mapList.add(map);
			}
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return mapList;
	}

	public static int addAttachments(InputStream image, int customerId) {
		int row = 0;
		String query = "INSERT INTO customer_attachments(image,customer_id) VALUES (?,'"
				+ customerId + "')";
		try {
			Connection con = connection.Connect.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setBlob(1, image);
			row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("Data added");
			} else {
				System.out.println("Data ni ja rahi");
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int countCustomersByRegexSearch(String regex) {
		System.out
				.println("CustomerBAL.count_all_customers_by_limit_range_regex()");
		int count = 0;
		Connection connection = Connect.getConnection();

		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_all_customers_by_limit_range_regex(?)}");
			prepareCall.setString(1, regex);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	// jetander
	public static int countRejectedCustomersByRegexSearch(String regex,
			int start, int range) {
		System.out
				.println("CustomerBAL.count_all_rejected_customer_by_search()");
		int count = 0;
		Connection connection = Connect.getConnection();

		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_all_rejected_customer_by_search(?,?,?)}");
			prepareCall.setString(1, regex);
			prepareCall.setInt(2, start);
			prepareCall.setInt(3, range);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getRequestStatus(int id) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL acceptedRejectedLoans(?)}");
			prepareCall.setInt(1, id);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				map.put("EligibilityId", rs.getInt("eligibility_id") + "");
				map.put("Status", rs.getString("status"));
				map.put("ApplianceName", rs.getString("appliance_name"));
				map.put("ApplianceId", rs.getString("appliance_id"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerId", rs.getString("customer_id"));
				map.put("appStatus", "appliance_status");
				map.put("salesman", rs.getString("salesman_name"));
				map.put("scheme", rs.getInt("installment_scheme") + "");

				maps.add(map);
			}
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}
	
	
	public static ArrayList<HashMap<String, String>> getRequestAddOnStatus(int customerId, int applianceId) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL acceptRejectAddOnLoans(?, ?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setInt(2, applianceId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				map.put("custom_pl_id", rs.getInt("custom_pl_id") + "");
				map.put("payment_loan_id", rs.getInt("payment_loan_id") + "");
				map.put("tracking_status", rs.getInt("tracking_status") + "");
				map.put("appliance_name", rs.getString("appliance_name"));
				map.put("total_installements", rs.getInt("total_installements") + "");
				
				System.err.println(map);

				maps.add(map);
			}
			System.err.println(maps);
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}
	

	public static HashMap<String, String> getCountAllEligibilityAndApplianceStatus() {
		// ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		Connection connection = Connect.getConnection();
		try {
			ResultSet resultSet = connection.prepareCall(
					"{CALL count_all_eligibility_and_appliance_status}")
					.executeQuery();
			int accepted = 0;
			int rejected = 0;
			while (resultSet.next()) {
				String status = resultSet.getString("t.status");
				if (status != null && !status.isEmpty()) {
					if (status.equals("appliance_status")) {

						if (resultSet.getInt("status") == 0) {
							map.put("Inactive", resultSet.getInt(1) + "");
						} else if (resultSet.getInt("status") == 1) {
							map.put("Active", resultSet.getInt(1) + "");
						}
					} else if (status.equals("eligibility_status")) {
						if (resultSet.getInt("status") == 0) {
							map.put("Applied", resultSet.getInt(1) + "");
						} else if (resultSet.getInt("status") == 1
								|| resultSet.getInt("status") == 6) {
							accepted += resultSet.getInt(1);
							// System.out.println("Current Accepted
							// "+resultSet.getInt(1));
							System.out.println("Accepted : " + accepted);
							map.put("Accepted", accepted + "");
						} else if (resultSet.getInt("status") == 2
								|| resultSet.getInt("status") == 4) {
							rejected += resultSet.getInt(1);
							// System.out.println("Rejected : "+rejected);
							map.put("Rejected", rejected + "");
						} else if (resultSet.getInt("status") == 3) {
							map.put("NotInterested", resultSet.getInt(1) + "");
						}
					}
				}
				// maps.add(map);

			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getCustomersByRegexSearch(
			String regex, int start, int range, String ord, int col) {
		System.out.println("CustomerBAL.getCustomersByRegexSearch()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();

		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_customers_by_limit_range_regex_asc_desc(?, ?, ?, ?, ?)}");
			prepareCall.setString(1, regex);
			prepareCall.setInt(2, start);
			prepareCall.setInt(3, range);
			prepareCall.setString(4, ord);
			prepareCall.setInt(5, col);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id")
						+ "");
				map.put("DT_RowId",
						"row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerCnic", resultSet.getString("customer_cnic"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber",
						resultSet.getString("customer_phone"));
				/*
				 * map.put("monthlyIncome",
				 * resultSet.getString("salary_range"));
				 */
				map.put("customerStatus", resultSet.getInt("cs.status") + "");
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status")
						+ "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ " %");
				}
				maps.add(map);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getRejectedCustomersByRegexSearch(
			String regex, int start, int range) {
		System.out.println("CustomerBAL.getCustomersByRegexSearch()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();

		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_rejected_appliances_by_search(?, ?, ?)}");
			prepareCall.setString(1, regex);
			prepareCall.setInt(2, start);
			prepareCall.setInt(3, range);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id")
						+ "");
				map.put("DT_RowId",
						"row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber",
						resultSet.getString("customer_phone"));
				/*
				 * map.put("monthlyIncome",
				 * resultSet.getString("salary_range"));
				 */
				map.put("customerStatus", resultSet.getInt("cs.status") + "");
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status")
						+ "");
				maps.add(map);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<CustomerInfoBean> getCustomersAppliances(
			int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();
		CustomerInfoBean bean = null;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_customer_expantion(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setCustomerId(resultSet.getInt("customer_id"));
				bean.setApplianceId(resultSet.getInt("appliance_id"));
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setApplianceStatus(resultSet.getInt("appliance_status"));
				bean.setSalesmanName(resultSet.getString("salesman_name"));
				bean.setTransferStatus(resultSet.getInt("a.status"));
				bean.setEligibilityStatus(resultSet.getInt("e.status"));
				bean.setLoanStatus(resultSet.getString("loan_status"));
				System.out.print(" P " + resultSet.getInt("pickup"));
				bean.setIsDefaulted(resultSet.getInt("pickup"));
				bean.setFoName(resultSet.getString("fo_name"));
				bean.setDoName(resultSet.getString("user_name"));
				maps.add(bean);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<CustomerInfoBean> getRejectedAppliances(
			int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();
		CustomerInfoBean bean = null;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_rejected_expansion(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setCustomerId(resultSet.getInt("customer_id"));
				bean.setApplianceId(resultSet.getInt("appliance_id"));
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setSalesmanName(resultSet.getString("salesman_name"));
				bean.setPurpose(resultSet.getString("text"));
				bean.setFoName(resultSet.getString("fo_name"));
				bean.setDoName(resultSet.getString("user_name"));
				maps.add(bean);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	// getApplianceInAccount
	public static ArrayList<HashMap<String, String>> getApplianceInAccount(
			int id) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		Connection connection = Connect.getConnection();
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_appliance_inaccount2(?)}");
			prepareCall.setInt(1, id);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("Status", rs.getString("status"));
				map.put("ApplianceName", rs.getString("appliance_name"));
				map.put("ApplianceId", rs.getString("appliance_id"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerId", rs.getString("customer_id"));
				map.put("appStatus", rs.getString("appliance_status"));
				map.put("salesman", rs.getString("salesman_name"));
				map.put("applianceStatus", rs.getString("applianceStatus"));
				map.put("salesman_id", rs.getString("salesman_id"));
				map.put("status_get", rs.getString("status_get"));
				map.put("defaulted", rs.getString("defaulted"));
				map.put("user_name", rs.getString("user_name"));
				map.put("fo_name", rs.getString("fo_name"));
				map.put("user_id", rs.getString("user_id"));
				map.put("fo_id", rs.getString("fo_id"));
				maps.add(map);
			}
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int updateCustomerNotifi(int customerId) {
		int row = 0;
		String query = "UPDATE customer SET seen=1 WHERE customer_id=?";
		Connection con2 = Connect.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) con2
					.prepareStatement(query);
			ps.setInt(1, customerId);
			row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("notification on hy");
			} else {
				System.out.println("notification not updated");
			}
			ps.close();
			con2.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int countUpdateNotification() {
		System.out.println("CustomerBAL.countUpdateNotification()");
		int notifications = 0;
		String query = "SELECT COUNT(*) FROM customer WHERE seen=1";
		Connection con2 = Connect.getConnection();
		try {
			PreparedStatement ps = con2.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notifications = rs.getInt(1);
			}
			ps.close();
			con2.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return notifications;
	}

	public int insertCustomer(HashMap<String, String> map) {
		System.out.println("CustomerBAL.insertCustomer()");
		System.out.println(map);
		int family = 0, foure = 0, five = 0;
		if (map.get("family") != "") {
			family = Integer.parseInt(map.get("family"));

		}

		if (map.get("general_Four") != "") {
			foure = Integer.parseInt(map.get("general_Four"));
		}
		if (map.get("general_Five") != "") {
			five = Integer.parseInt(map.get("general_Five"));
		}

		Connection connection = Connect.getConnection();
		int id = -1;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_customers_info(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setString(1, map.get("fullname"));
			prepareCall.setString(2, map.get("cnic"));
			prepareCall.setString(3, "0000:00:00");
			prepareCall.setString(4, map.get("address"));
			prepareCall.setString(5, map.get("city"));
			prepareCall.setString(6, map.get("primaryPhone"));
			prepareCall.setString(7, map.get("secondaryPhone"));
			// prepareCall.setString(8, map.get("customerMonthlyIncome"));
			// prepareCall.setString(9, map.get("customerFamilyIncome"));
			// prepareCall.setString(11, map.get("customerImage"));
			prepareCall.setString(8, map.get("fatherName"));
			// prepareCall.setString(9, map.get("motherName"));
			prepareCall.setString(9, map.get("email"));
			prepareCall.setString(10, map.get("gender"));
			prepareCall.setString(11, map.get("maritalStatus"));
			// prepareCall.setString(13, map.get("familySize"));
			// prepareCall.setString(17, map.get("relationStatus"));
			// prepareCall.setInt(14,
			// (map.get("isEducated").equals("true")?1:0));
			prepareCall.setString(12, map.get("education"));
			prepareCall.setString(13, map.get("vle"));
			prepareCall.setInt(14, family);
			prepareCall.setString(15, map.get("general_One"));
			prepareCall.setString(16, map.get("general_Two"));
			prepareCall.setString(17, map.get("general_Three"));
			prepareCall.setInt(18, foure);
			prepareCall.setInt(19, five);

			ResultSet resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("customer_id");
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static int updatMoreInfoStatus(int customerID) {
		int row = 0;
		String query = "UPDATE customer SET seen =3 WHERE customer_id=?";
		Connection con = Connect.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, customerID);
			row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("Uodated");
			} else {
				System.out.println("Not updated");
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoCustomerTable(
			int start, int length, int column, String order, int doId) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		// HashMap<String, String> map = new HashMap<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try {
			Connection con = Connect.getConnection();
			System.out.println("start");
			// ascending order
			if (order.equalsIgnoreCase("asc")) {
				switch (column) {
				case 0:
					// System.out.println("asdasdasd");
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_name_asc(?,?,?)}");
					break;
				case 1:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_cnic_asc(?,?,?)}");
					break;
				case 2:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_number_asc(?,?,?)}");
					break;
				case 3:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_status_asc(?,?,?)}");
					break;
				case 4:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_appliance_name_asc(?,?,?)}");
					break;
				case 5:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_appliance_number_asc(?,?,?)}");
					break;
				case 6:
					call = con
							.prepareCall("CALL get_do_customer_list_order_by_charging_status_asc(?,?,?)");
					break;
				case 7:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_salesman_asc(?,?,?)}");
					break;
				case 8:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_city_asc(?,?,?)}");
					break;

				}
				// descending order
			} else if (order.equalsIgnoreCase("desc")) {
				switch (column) {
				case 0:
					// System.out.println("asdsdafa");
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_name_desc(?,?,?)}");
					break;
				case 1:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_cnic_desc(?,?,?)}");
					break;
				case 2:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_number_desc(?,?,?)}");
					break;
				case 3:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_customer_status_desc(?,?,?)}");
					break;
				case 4:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_appliance_name_desc(?,?,?)}");
					break;
				case 5:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_appliance_number_desc(?,?,?)}");
					break;
				case 6:
					call = con
							.prepareCall("CALL get_do_customer_list_order_by_charging_status_desc(?,?,?)");
					break;
				case 7:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_salesman_desc(?,?,?)}");
					break;
				case 8:
					call = con
							.prepareCall("{CALL get_do_customer_list_order_by_city_desc(?,?,?)}");
					break;

				}

			}
			if (call != null) {

				call.setInt(1, start);
				call.setInt(2, length);
				call.setInt(3, doId);
				rs = call.executeQuery();
				// System.out.println("aaaaacalled");
				while (rs.next()) {
					HashMap<String, String> map = new HashMap<>();
					System.out.println("called");
					map.put("customerId", rs.getInt("customer_id") + "");
					map.put("customerName", rs.getString("customer_name"));
					map.put("customerCnic", rs.getString("customer_cnic"));
					map.put("customerPhone", rs.getString("customer_phone"));
					map.put("city", rs.getString("city_name"));
					map.put("applianceNumber", rs.getString("appliance_GSMno"));
					map.put("applianceStatus", rs.getInt("appliance_status")
							+ "");
					map.put("salesmanName", rs.getString("salesman_name"));
					map.put("applianceId", rs.getInt("appliance_id") + "");
					map.put("salesmanId", rs.getInt("salesman_id") + "");
					map.put("applianceName", rs.getString("appliance_name"));
					map.put("status", rs.getInt("status") + "");
					list.add(map);
				}
				con.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	// created by Junaid Ali
	public static int getDoSalesmanListCount(int doId) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		Connection con = Connect.getConnection();

		try {
			call = con.prepareCall("{CALL get_do_customer_list_count(?)}");
			call.setInt(1, doId);
			rs = call.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoCustomerSearchList(
			int start, int length, String search, int dId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		CallableStatement call = null;
		ResultSet rs = null;
		Connection con = Connect.getConnection();
		try {

			call = con
					.prepareCall("{CALL get_do_customer_list_search(?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, search);
			call.setInt(4, dId);

			rs = call.executeQuery();

			while (rs.next()) {
				map = new HashMap<>();
				System.out.println("called");
				map.put("customerId", rs.getInt("customer_id") + "");
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerCnic", rs.getString("customer_cnic"));
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("city", rs.getString("city_name"));
				map.put("applianceNumber", rs.getString("appliance_GSMno"));
				map.put("applianceStatus", rs.getInt("appliance_status") + "");
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("status", rs.getInt("status") + "");
				list.add(map);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static int getDoSalesmanListSearchCount(String search, int doId) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		Connection con = Connect.getConnection();

		try {
			call = con
					.prepareCall("{CALL get_do_customer_list_search_count(?,?)}");
			call.setString(1, search);
			call.setInt(2, doId);
			rs = call.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoAcceptedCustomers(
			int do_Id) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		Connection con = Connect.getConnection();
		try {
			call = con.prepareCall("{Call do_accepted_customeres(?)}");
			call.setInt(1, do_Id);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("CustomerName", rs.getString("customer_name"));
				map.put("CustomerCnic", rs.getString("customer_cnic"));
				map.put("CustomerPhone", rs.getString("customer_phone"));
				map.put("appName", rs.getString("appliance_name"));
				map.put("appNumber", rs.getString("appliance_GSMno"));
				map.put("SalesName", rs.getString("salesman_name"));
				map.put("cityName", rs.getString("cc.city_name"));
				map.put("status", rs.getInt("e.status") + "");
				map.put("doId", rs.getInt("do_id") + "");
				list.add(map);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoRejectedCustomers(
			int do_Id) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		Connection con = Connect.getConnection();
		try {
			call = con.prepareCall("{Call do_rejected_cusotmers(?)}");
			call.setInt(1, do_Id);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("CustomerName", rs.getString("customer_name"));
				map.put("CustomerCnic", rs.getString("customer_cnic"));
				map.put("CustomerPhone", rs.getString("customer_phone"));
				map.put("appName", rs.getString("appliance_name"));
				map.put("appNumber", rs.getString("appliance_GSMno"));
				map.put("SalesName", rs.getString("salesman_name"));
				map.put("cityName", rs.getString("cc.city_name"));
				map.put("status", rs.getInt("e.status") + "");
				map.put("doId", rs.getInt("do_id") + "");
				list.add(map);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static String getCustomerNameByPhone(String phone) {
		System.out.print("get_customer_name_by_phone");
		CallableStatement call = null;
		ResultSet rs = null;
		Connection con = Connect.getConnection();
		String customerName = null;
		try {
			call = con.prepareCall("{Call get_customer_name_by_phone(?)}");
			call.setString(1, phone);
			rs = call.executeQuery();
			while (rs.next()) {
				customerName = rs.getString("customer_name");
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerName;
	}

	public static int getApplianceIdByAppName(String appname, int userid) {

		int id = 0;
		String query = " SELECT a.`appliance_id` FROM appliance a "
				+ " WHERE"
				+ " a.`appliance_id` NOT IN (SELECT e.appliance_id FROM eligibility e)"
				+ " AND a.`appliance_name` = '" + appname
				+ "' AND a.user_id = " + userid + " LIMIT 1";

		Connection con2 = Connect.getConnection();
		try {
			PreparedStatement ps = con2.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			ps.close();
			con2.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static int countUnseenMessagesFromAppliance(int userId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_unseen_status_messages_of_do(?)}");
				prepareCall.setInt(1, userId);
				ResultSet resultSet = prepareCall.executeQuery();
				// End Stored Procedure
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;

	}

	public static ArrayList<HashMap<String, String>> getUnseenMessagesFromAppliance(
			int userId) {

		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection();) {
			// Begin Stored Procedure -- Jeevan
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_unseen_status_messages_from_appliance_of_do(?)}");
			prepareCall.setInt(1, userId);
			ResultSet result = prepareCall.executeQuery();
			// End Stored Procedure
			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("messageId", String.valueOf(result.getInt("alarm_id")));
				map.put("messageFrom", result.getString("appliance_GSMno"));
				// map.put("messageText", resultSet.getString("msg_text"));

				Timestamp dateTime = result.getTimestamp("alarm_time");
				Date date = new Date(dateTime.getTime());

				map.put("messageDate", p.format((date)));
				map.put("applianceId", result.getString("appliance_id"));

				list.add(map);
			}
			result.close();
			prepareCall.close();
			connection.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}// end of getting all message number from appliance_message

	public int updateCustomerIncomeSource(HashMap<String, String> map) {
		System.out.println("CustomerBAL.updateCustomerIncomeSource()");
		System.out.println(map);
		int isupdated = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE  " + "  `customer`  " + "  SET "
							+ "    `salary_or_pension` = ?, "
							+ "    `business_income` = ?, "
							+ "    `farming` = ?, "
							+ "    `family_contribution` = ?"
							+ "  WHERE `customer_id` = ? ;");
			prepareStatement.setString(
					1,
					map.get("salaryOrPension").isEmpty() ? "0" : map
							.get("salaryOrPension"));
			prepareStatement.setString(
					2,
					map.get("businessIncome").isEmpty() ? "0" : map
							.get("businessIncome"));
			prepareStatement.setString(
					3,
					map.get("farmingIncome").isEmpty() ? "0" : map
							.get("farmingIncome"));
			prepareStatement.setString(4, map.get("familyContribution")
					.isEmpty() ? "0" : map.get("familyContribution"));
			prepareStatement.setInt(5, Integer.parseInt(map.get("customerId")));

			prepareStatement.executeUpdate();
			System.out.println(prepareStatement.getPreparedSql());
			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 2, updated_date = NOW() WHERE customer_id = ?");
			updateStatement.setString(1, map.get("customerId"));
			isupdated = updateStatement.executeUpdate();
			System.out.println(updateStatement.asSql());
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return isupdated;
	}

	public int insertCustomerBusinessInfo(HashMap<String, String> map) {
		System.out.println("CustomerBAL.insertCustomerBusinessInfo()");
		System.out.println(map);
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `business_info` ("
							+ " `customer_id`, " + "   `business_name`, "
							+ "   `businees_type`, " + "   `period`, "
							+ "   `phone_no`," + "  address," + "  comment)  "
							+ " VALUES (?, " + "     ?, " + "     ?, "
							+ "     ?, " + "     ?, " + "     ?, "
							+ "     ?) ;");
			prepareStatement.setInt(1, Integer.parseInt(map.get("customerId")));
			prepareStatement.setString(2, map.get("title"));
			prepareStatement.setString(3, map.get("type"));
			prepareStatement.setString(4, map.get("timePeriod"));
			prepareStatement.setString(5, map.get("phoneNo"));
			prepareStatement.setString(6, map.get("address"));
			prepareStatement.setString(7, map.get("comment"));

			prepareStatement.executeUpdate();
			System.out.println(prepareStatement.asSql());
			id = (int) prepareStatement.getLastInsertID();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int insertCustomerEmployementInfo(HashMap<String, String> map) {
		System.out.println("CustomerBAL.insertCustomerEmployementInfo()");
		System.out.println(map);
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `employment_details` ( "
							+ "  `customer_id`, " + "  `organisation_name`, "
							+ "  `job_position`, " + "  `job_period`, "
							+ "  `supervisor_name`, " + "  `office_phone_no`, "
							+ "  `address` " + ")  " + "VALUES " + "  ( "
							+ "    ?, " + "    ?, " + "    ?, " + "    ?, "
							+ "    ?, " + "    ?, " + "    ? " + "  ) ;");

			prepareStatement.setString(1, map.get("customerId"));
			prepareStatement.setString(2, map.get("companyTitle"));
			prepareStatement.setString(3, map.get("designation"));
			prepareStatement.setString(4, map.get("jobPeriod"));
			prepareStatement.setString(5, map.get("supervisorName"));
			prepareStatement.setString(6, map.get("officePhoneNo"));
			prepareStatement.setString(7, map.get("address"));
			prepareStatement.executeUpdate();
			System.out.println(prepareStatement.asSql());
			id = (int) prepareStatement.getLastInsertID();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int insertCustomerOtherLoanInfo(HashMap<String, String> map) {
		System.out.println("CustomerBAL.insertCustomerOtherLoanInfo()");
		System.out.println(map);
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `other_loan_details` ( "
							+ "  `customer_id`, " + "  `is_loaned`, "
							+ "  `loan_donner`, " + "  `loan_amount`, "
							+ "  `loan_period`, " + "  `monthly_installment`, "
							+ "  `remaining_payment`, "
							+ "  `is_bank_account`, " + "  `bank_name` "
							+ ")  " + "VALUES " + "  ( " + "    ?, "
							+ "    ?, " + "    ?, " + "    ?, " + "    ?, "
							+ "    ?, " + "    ?, " + "    ?, " + "    ? "
							+ "  ) ; ");

			prepareStatement.setString(1, map.get("customerId").trim());
			prepareStatement.setString(2,
					map.get("gotAnyLoan").trim().equals("true") ? "1" : "0");
			prepareStatement.setString(3, map.get("bankOrOrgName").trim());
			prepareStatement.setString(4, map.get("totalAmount").trim());
			prepareStatement.setString(5, map.get("loanPeriod").trim());
			prepareStatement.setString(6, map.get("monthlyInstallment").trim());
			prepareStatement.setString(7, map.get("remainingAmount").trim());
			prepareStatement.setString(8, map.get("haveAnyBankAccount").trim()
					.equals("true") ? "1" : "0");
			prepareStatement.setString(9, map.get("bankName").trim());
			prepareStatement.executeUpdate();

			id = (int) prepareStatement.getLastInsertID();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int insertCustomerElectricityExpenses(HashMap<String, String> map) {
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `monthly_home_expenses` ( "
							+ "`customer_id`, " + "`grid_electricity`, "
							+ "`generator`, " + "`ups`, " + "`solar`, "
							+ "`electricityusage`, " + "`electricityexpense`, "
							+ "`majorexpensedescription`, "
							+ "`majorexpenseamount`, " + "`totalexpense` "
							+ ")  " + "VALUES " + "( " + "?, "// 1
																// customer_id
							+ "?, "// 2 grid_electricity
							+ "?, "// 3 generator
							+ "?, "// 4 ups
							+ "?, "// 5 solar
							+ "?, "// 6 electricityusage
							+ "?, "// 7 electricityexpense
							+ "?, "// 8 majorexpensedescription
							+ "?, "// 9 majorexpenseamount
							+ "? "// 10 totalexpense
							+ ") ;");
			prepareStatement.setString(1, map.get("customerId"));
			prepareStatement.setString(
					2,
					map.get("Electric Supply") == null ? "" : map
							.get("Electric Supply"));
			prepareStatement.setString(3, map.get("Generator") == null ? ""
					: map.get("Generator"));
			prepareStatement.setString(4,
					map.get("UPS") == null ? "" : map.get("UPS"));
			prepareStatement.setString(5,
					map.get("Solar") == null ? "" : map.get("Solar"));
			prepareStatement.setString(6,
					map.get("Other") == null ? "" : map.get("Other"));
			prepareStatement.setString(7, map.get("electricityExpenses"));
			prepareStatement.setString(8, map.get("majorExpenseDescription"));
			prepareStatement.setString(9, map.get("majorExpenseAmount"));
			prepareStatement.setString(10, map.get("totalExpenseAmount"));
			prepareStatement.executeUpdate();
			id = (int) prepareStatement.getLastInsertID();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public void insertCustomerHouseHoldAssets(ArrayList<String> list,
			int customerId) {
		System.out.println("CustomerBAL.insertCustomerHouseHoldAssets()");
		Connection connection = Connect.getConnection();
		try {
			for (String houseHoldAssets : list) {
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `house_hold_assets` ( "
								+ "`customer_id`, " + "`house_hold_asset` "
								+ ")  " + "VALUES " + "( " + "  ?, "
								+ "  ? ) ;");
				prepareStatement.setInt(1, customerId);
				prepareStatement.setString(2, houseHoldAssets);
				prepareStatement.executeUpdate();
			}
			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 3 WHERE customer_id = ?");
			updateStatement.setInt(1, customerId);
			updateStatement.executeUpdate();
			System.out.println(updateStatement.asSql());
			connection.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public int insertCustomerHouseHoldAssets(HashMap<String, String> map) {
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `home_hold_assets` ("
							+ "  `customer_id`, " + "  `home`, " + "  `car`, "
							+ "  `motorcycle`, " + "  `washing_machine`, "
							+ "  `tv`, " + "  `computer`, " + "  `fridge`, "
							+ "  `others` " + ")  " + "VALUES " + "  ( "
							+ "    ?, " + "    ?, " + "    ?, " + "    ?, "
							+ "    ?, " + "    ?, " + "    ?, " + "    ?, "
							+ "    ? " + "  ) ;");

			prepareStatement.setString(1, map.get("customer_id"));
			prepareStatement.setString(2, map.get("home"));
			prepareStatement.setString(3, map.get("car"));
			prepareStatement.setString(4, map.get("motorcycle"));
			prepareStatement.setString(5, map.get("washing_machine"));
			prepareStatement.setString(6, map.get("tv"));
			prepareStatement.setString(7, map.get("computer"));
			prepareStatement.setString(8, map.get("fridge"));
			prepareStatement.setString(9, map.get("others"));
			prepareStatement.executeUpdate();

			id = (int) prepareStatement.getLastInsertID();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int insertCustomerGuarantorInfo(HashMap<String, String> map,
			int customerId, int guarantorType) {
		System.out.println("CustomerBAL.insertCustomerGuarantorInfo()");
		System.out.println(map);
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `customer_guarantor` ( "
							+ "  `customer_id`, " + "  `gguarantorName`, "
							+ "  `gguarantorCnic`, " + "  `gguarantorPhone`, "
							+ "  `gRelationToCustomer`, "
							+ "  `gguarantorIncome`," + " guarantertype "
							+ ")  " + "VALUES " + "  ( " + "    ?, "
							+ "    ?, " + "    ?, " + "    ?, " + "    ?, "
							+ "    ?," + " ? " + "  ) ;");

			prepareStatement.setInt(1, customerId);
			prepareStatement.setString(2, map.get("name"));
			prepareStatement.setString(3, map.get("cnic"));
			prepareStatement.setString(4, "92" + map.get("cellNumber"));
			prepareStatement.setString(5, map.get("relation"));
			prepareStatement.setString(6, map.get("incomeSource"));
			prepareStatement.setString(7, map.get("guarantorType"));
			prepareStatement.executeUpdate();
			System.out.println(prepareStatement.asSql());
			id = (int) prepareStatement.getLastInsertID();

			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 4, updated_date = NOW()  WHERE customer_id = ?");
			updateStatement.setInt(1, customerId);
			updateStatement.executeUpdate();
			System.out.println(updateStatement.asSql());

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int insertCustomerVleGaurantor(int customerId, int salesmanId) {
		System.out.println("CustomerBAL.insertCustomerVleGaurantor()");
		int id = 0;
		Connection connection = Connect.getConnection();
		try {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `eligibility` ( "
							+ "  `customer_id`, " + "  `salesman_id` " + ")  "
							+ "VALUES " + "  ( " + "    ?, " + "    ? ) ;");
			prepareStatement.setInt(1, customerId);
			prepareStatement.setInt(2, salesmanId);
			prepareStatement.executeUpdate();
			System.out.println(prepareStatement.asSql());
			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 1 WHERE customer_id = ?");
			updateStatement.setInt(1, customerId);
			updateStatement.executeUpdate();
			System.out.println(updateStatement.asSql());
			id = (int) prepareStatement.getLastInsertID();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int[] insertApplianceInEligibility(int customerId, int vleId,
			String[] applianceName, String[] scheme, String[] advance,
			String[] monthly, String[] price) {
		System.out.print("insertEligibility");
		int[] id = new int[applianceName.length];
		System.out.print("id" + id);

		int appliancePrice = 0;
		int installment = 0;
		int downPayment = 0;
		int term = 0;
		Connection connection = Connect.getConnection();
		int i = 0;
		try {
			for (int j = 0; j < applianceName.length; j++) {
				if (scheme[j] == null) {
					scheme[j] = scheme[j + 1];
				}

				System.out
						.println("**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-"
								+ scheme[j]);
				downPayment = Integer.parseInt(advance[j]);
				installment = Integer.parseInt(monthly[j]);
				appliancePrice = Integer.parseInt(price[j]);
				term = Integer.parseInt(scheme[j]);
				System.out.print("hfhghf" + downPayment + " " + installment
						+ " " + appliancePrice + " " + term);

				System.out.print("gdgshgds");

				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `appliance` ( "
								+ " `appliance_name`, " + " `appliance_price` "
								+ " )  " + " VALUES " + " ( " + "?, " + "? "
								+ " ) ; ");
				prepareStatement.setString(1, applianceName[i]);
				prepareStatement.setInt(2, appliancePrice);
				prepareStatement.executeUpdate();
				id[i] = (int) prepareStatement.getLastInsertID();
				System.out.println(prepareStatement.asSql());

				com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `eligibility` ("
								+ "  `customer_id`, " + "  `appliance_id`, "
								+ "  `salesman_id`, " + "  `date`, "
								+ "  `instalment`, "
								+ "  `installment_scheme`, "
								+ "  `down_payment`, " + "  `status`, "
								+ "  `is_seen` " + "  ) " + "  VALUES "
								+ "  ( ?, " + "  ?, " + "  ?, " + "  NOW(), "
								+ "  ?, " + "  ?, " + "  ?, " + "  ?, "
								+ "  ? " + "  ) ;");
				updateStatement.setInt(1, customerId);
				updateStatement.setInt(2, id[i]);
				updateStatement.setInt(3, vleId);
				updateStatement.setInt(4, installment);
				updateStatement.setInt(5, term);
				updateStatement.setInt(6, downPayment);
				updateStatement.setInt(7, 0);
				updateStatement.setInt(8, 0);
				updateStatement.executeUpdate();
				System.out.println(updateStatement.asSql());
				i++;
			}
			try {
				com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("UPDATE form_wizard SET form_wizard_step = 5, updated_date = NOW()  WHERE customer_id = ?");
				updateStatement.setInt(1, customerId);
				updateStatement.executeUpdate();
				System.out.println(updateStatement.asSql());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static boolean updateProfile(HashMap<String, Object> profile) {
		System.out.println("updateProfile");
		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int row = 0;
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_customers(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			String general4 = (String) profile.get("generalFoure"), general5 = (String) profile
					.get("generalFive"), family = (String) profile
					.get("familySize");
			int familysize = 0, generalFoure = 0, generalFive = 0;

			if (family != null && !family.equals("N/A") && !family.equals("")
					&& family.matches("\\d")) {
				familysize = Integer.parseInt(family);
			}

			if (general4 != null && !general4.equals("N/A")
					&& general4.matches("\\d") && !general4.equals("")) {
				generalFoure = Integer.parseInt(general4);
			}

			if (general5 != null && !general5.equals("N/A")
					&& !general5.equals("") && general5.matches("\\d")) {
				generalFive = Integer.parseInt(general5);
			}
			prePareCall.setInt(1, (int) profile.get("customrId"));
			prePareCall.setString(2, (String) profile.get("customerName"));
			prePareCall.setString(3, (String) profile.get("customerCnic"));
			prePareCall.setString(4, (String) profile.get("customerAddress"));
			prePareCall.setString(5,
					(String) profile.get("customerPrimaryNumber"));
			prePareCall.setString(6,
					(String) profile.get("customerSecondaryNumber"));
			prePareCall.setInt(7, (int) profile.get("customrMonthlyIncome"));
			prePareCall.setInt(8, (int) profile.get("customrFamilyIncom"));
			prePareCall.setString(9, (String) profile.get("cusotmerGender"));
			prePareCall.setInt(10, (int) profile.get("educationId"));
			prePareCall.setString(11, (String) profile.get("customerFather"));
			prePareCall.setString(12, (String) profile.get("relationStatus"));
			prePareCall.setInt(13, familysize);
			prePareCall.setString(14, (String) profile.get("generalOne"));
			prePareCall.setString(15, (String) profile.get("generalTwo"));
			prePareCall.setString(16, (String) profile.get("generalThree"));
			prePareCall.setInt(17, generalFoure);
			prePareCall.setInt(18, generalFive);

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out.println("customers data has been Updated ");
			} else {
				System.out.println("customers data has not Updated");
			}

			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateCustomerGuardian(HashMap<String, Object> family) {
		Connection con = Connect.getConnection();
		boolean err = true;
		int row = 0;
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = con
					.prepareCall("{CALL update_customer_guardian(?,?,?)}");
			prePareCall.setInt(1, (int) family.get("customrId"));
			prePareCall.setString(2, (String) family.get("gaurdianName"));
			prePareCall.setString(3, (String) family.get("gaurdianPhone"));

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("update_customer_guardian data has been Updated ");
			} else {
				System.out
						.println("update_customer_guardian data has not Updated");
			}
			/*
			 * String query = "UPDATE `customer_guardian` cg SET" +
			 * " cg.`guardian_name` = '"+family+"'," +" cg.`phone_no` = '"
			 * +family+"'" +" WHERE cg.`customer_id` = '"+family+"'";
			 * System.err.println("Query:  "+query); Statement st =
			 * con.createStatement(); st.executeUpdate(query);
			 */

			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				e.printStackTrace();
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateCustomerGuarantorOne(
			HashMap<String, String> familyguarantor) {
		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int row = 0;
		int famGaurantorId = 0;
		if (!familyguarantor.get("famGaurantorId").equals("0")) {
			famGaurantorId = Integer.parseInt(familyguarantor
					.get("famGaurantorId"));
		}
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_family_guarantor(?,?,?,?,?,?,?,?)}");

			prePareCall.setInt(1,
					Integer.parseInt(familyguarantor.get("customrId")));
			prePareCall.setInt(2, famGaurantorId);
			prePareCall.setString(3, familyguarantor.get("famGaurantorName"));
			prePareCall.setString(4, familyguarantor.get("famGaurantorPhone"));
			prePareCall.setString(5, familyguarantor.get("famGaurantorFather"));
			prePareCall.setString(6, familyguarantor.get("famGaurantorCnic"));
			prePareCall.setString(7,
					familyguarantor.get("famGaurantorRelation"));
			prePareCall.setString(8, familyguarantor.get("famGaurantorIncome"));

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out.println("family_guarantor data has been Updated");
			} else {
				System.out.println("family_guarantor data has not Updated ");
			}

			/*
			 * String query = "UPDATE `customer_guarantor` cg SET" +
			 * " cg.`gguarantorName` = '"+cguarantor1+"'," +
			 * " cg.`gguarantorPhone` = '"+cguarantor1+"'," +
			 * " cg.`gguarantorFather` = '"+cguarantor1+"'," +
			 * " cg.`gguarantorIncome` = '"+cguarantor1+"'," +
			 * " cg.`gguarantorCnic` = '"+cguarantor1+"'," +
			 * " cg.`gRelationToCustomer` = '"+cguarantor1+"'" +
			 * " WHERE cg.`customer_id` = '" +cguarantor1+
			 * "' AND cg.`guarantertype` = 1"; System.err.println("Query:  "
			 * +query); Statement st = con.createStatement();
			 * st.executeUpdate(query);
			 */
			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateCustomerGuarantorTwo(
			HashMap<String, String> outsiderguarantor) {
		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int row = 0;
		int outGaurantorId = 0;
		if (!outsiderguarantor.get("outGaurantorId").equals("0")) {
			outGaurantorId = Integer.parseInt(outsiderguarantor
					.get("outGaurantorId"));
		}
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_outsider_guarantor(?,?,?,?,?,?,?,?)}");

			prePareCall.setInt(1,
					Integer.parseInt(outsiderguarantor.get("customrId")));
			prePareCall.setInt(2, outGaurantorId);
			prePareCall.setString(3, outsiderguarantor.get("outGaurantorName"));
			prePareCall
					.setString(4, outsiderguarantor.get("outGaurantorPhone"));
			prePareCall.setString(5,
					outsiderguarantor.get("outGaurantorFather"));
			prePareCall.setString(6, outsiderguarantor.get("outGaurantorCnic"));
			prePareCall.setString(7,
					outsiderguarantor.get("outGaurantorRelation"));
			prePareCall.setString(8,
					outsiderguarantor.get("outGaurantorIncome"));

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("update_outsider_guarantor data has been Updated");
			} else {
				System.out
						.println("update_outsider_guarantor data has not Updated ");
			}

			/*
			 * String query = "UPDATE `customer_guarantor` cg SET" +
			 * " cg.`gguarantorName` = '"+cguarantor2+"'," +
			 * " cg.`gguarantorPhone` = '"+cguarantor2+"'," +
			 * " cg.`gguarantorFather` = '"+cguarantor2+"'," +
			 * " cg.`gguarantorIncome` = '"+cguarantor2+"'," +
			 * " cg.`gguarantorCnic` = '"+cguarantor2+"'," +
			 * " cg.`gRelationToCustomer` = '"+cguarantor2+"'" +
			 * " WHERE cg.`customer_id` = '" +cguarantor2+
			 * "' AND cg.`guarantertype` = 2"; System.err.println("Query:  "
			 * +query); Statement st = con.createStatement();
			 * st.executeUpdate(query);
			 */
			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateBusinessInfo(
			HashMap<String, String> businessinfo) {
		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int row = 0;
		int businessId = 0;
		if (!businessinfo.get("businessId").equals("0")) {
			businessId = Integer.parseInt(businessinfo.get("businessId"));
		}
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_business_info(?,?,?,?,?,?,?,?)}");

			prePareCall.setInt(1,
					Integer.parseInt(businessinfo.get("customrId")));
			prePareCall.setInt(2, businessId);
			prePareCall.setString(3, businessinfo.get("business"));
			prePareCall.setString(4, businessinfo.get("businessType"));
			prePareCall.setString(5, businessinfo.get("businessAddress"));
			prePareCall.setString(6, businessinfo.get("businessPhone"));
			prePareCall.setString(7, businessinfo.get("businessPeriod"));
			prePareCall.setString(8, businessinfo.get("businessComments"));
			// prePareCall.setString(7, businessinfo.get("bussinessWorkers"));
			// prePareCall.setString(8, businessinfo);

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("update_business_info data has been Updated");
			} else {
				System.out
						.println("update_business_info data has not Updated ");
			}

			/*
			 * String query = "UPDATE `business_info` bi SET" +
			 * " bi.`business_name` = '"+businessinfo+"'," +
			 * " bi.`businees_type` = '"+businessinfo+"'," +
			 * " bi.`business_place` = '"+businessinfo+"'," +
			 * " bi.`current_place_period` = '"+businessinfo+"'," +
			 * " bi.`period` = '"+businessinfo+"'," +" bi.`phone_no` = '"
			 * +businessinfo+"'," +" bi.`workers` = '"+businessinfo+"'" +
			 * " WHERE bi.`customer_id` = '"+businessinfo+"'";
			 * System.err.println("Query:  "+query); Statement st =
			 * con.createStatement(); st.executeUpdate(query);
			 */
			con.commit();
			return true;

		} catch (Exception e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateEmploymentDetails(
			HashMap<String, String> empdetails) {
		System.out.println("updateProfile");

		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int employementId = 0;
		if (!empdetails.get("employementId").equals("0")) {
			employementId = Integer.parseInt(empdetails.get("employementId"));
		}
		int row = 0;
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_employment_details(?,?,?,?,?,?,?,?)}");

			prePareCall.setInt(1, employementId);
			prePareCall
					.setInt(2, Integer.parseInt(empdetails.get("customrId")));
			prePareCall.setString(3, empdetails.get("employmentPosition"));
			prePareCall.setString(4, empdetails.get("employmentPeriod"));
			prePareCall.setString(5, empdetails.get("employmentOrg"));
			prePareCall.setString(6, empdetails.get("employmentOrgPhone"));
			prePareCall.setString(7, empdetails.get("SupervisorName"));
			prePareCall.setString(8, empdetails.get("EmployementAddress"));

			// prePareCall.setString(7, empdetails.get("employeeAddress"));

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("update_employment_details data has been Updated");
			} else {
				System.out
						.println("update_employment_details data has not Updated ");
			}

			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateIncomeInfo(HashMap<String, Object> incomeInfo) {
		Connection con = Connect.getConnection();
		boolean err = true;
		int row = 0;
		try {
			con.setAutoCommit(false);
			CallableStatement callst = con
					.prepareCall("{CALL update_customer_income_info(?,?,?,?,?)}");
			callst.setInt(1, (int) incomeInfo.get("customerId"));
			callst.setDouble(2, (double) incomeInfo.get("salaryPension"));
			callst.setDouble(3, (double) incomeInfo.get("bussinessIncome"));
			callst.setDouble(4, (double) incomeInfo.get("farmiMing"));
			callst.setDouble(5, (double) incomeInfo.get("familyContribution"));

			row = callst.executeUpdate();
			if (row > 0) {
				System.out
						.println("upate income details data has been Updated");
			} else {
				System.out
						.println("update income details data has not been Updated ");
			}
			con.commit();
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateOtherLoanDetails(
			HashMap<String, String> otherldetails) {
		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int row = 0;
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_other_loan_details(?,?,?,?,?,?)}");

			prePareCall.setInt(1,
					Integer.parseInt(otherldetails.get("customrId")));
			prePareCall.setString(2, otherldetails.get("loanDonner"));
			prePareCall.setDouble(3,
					Double.parseDouble(otherldetails.get("loanAmount")));
			prePareCall.setDouble(4, Double.parseDouble(otherldetails
					.get("monthlyInstallments")));
			prePareCall.setString(5, otherldetails.get("loanPeriod"));
			prePareCall.setDouble(6,
					Double.parseDouble(otherldetails.get("loanDueAmount")));

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("update_other_loan_details data has been Updated");
			} else {
				System.out
						.println("update_other_loan_details data has not Updated ");
			}

			/*
			 * String query = "UPDATE `other_loan_details` ol SET" +
			 * " ol.`loan_donner` = '"+otherldetails+"'," +
			 * " ol.`loan_amount` = '"+otherldetails+"'," +
			 * " ol.`monthly_installment` = '"+otherldetails+"'," +
			 * " ol.`loan_period` = '"+otherldetails+"'," +
			 * " ol.`remaining_payment` = '"+otherldetails+"'" +
			 * " WHERE ol.`customer_id` = '"+otherldetails+"'";
			 * System.err.println("Query:  "+query); Statement st =
			 * con.createStatement(); st.executeUpdate(query);
			 */
			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public static boolean updateMonthlyHomeExpenses(
			HashMap<String, String> monthexpenses) {
		Connection con = connection.Connect.getConnection();
		boolean err = true;
		int row = 0;
		try {
			con.setAutoCommit(false);

			CallableStatement prePareCall = (CallableStatement) con
					.prepareCall("{CALL update_monthly_home_expenses(?,?,?,?,?,?)}");

			prePareCall.setInt(1,
					Integer.parseInt(monthexpenses.get("customrId")));
			prePareCall
					.setDouble(2, Double.parseDouble(monthexpenses
							.get("electricityExpense")));
			prePareCall.setString(3, monthexpenses.get("electricityUsage"));
			prePareCall.setDouble(4,
					Double.parseDouble(monthexpenses.get("majorExpense")));
			prePareCall.setString(5, monthexpenses.get("expenseDesc"));
			prePareCall.setDouble(6,
					Double.parseDouble(monthexpenses.get("totalExpense")));

			row = prePareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("update_monthly_home_expenses data has been Updated");
			} else {
				System.out
						.println("update_monthly_home_expenses data has not Updated ");
			}

			/*
			 * String query = "UPDATE `monthly_home_expenses` mh SET" +
			 * " mh.`electricityexpense` = '"+monthexpenses+"'," +
			 * " mh.`electricityusage` = '"+monthexpenses+"'," +
			 * " mh.`majorexpenseamount` = '"+monthexpenses+"'," +
			 * " mh.`majorexpensedescription` = '"+monthexpenses+"'," +
			 * " mh.`totalexpense` = '"+monthexpenses+"'" +
			 * " WHERE mh.`customer_id` = '"+monthexpenses+"'";
			 * System.err.println("Query:  "+query); Statement st =
			 * con.createStatement(); st.executeUpdate(query);
			 */
			con.commit();
			return true;

		} catch (SQLException e) {
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
				logger.error("", e);
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			err = false;
		}
		return err;
	}

	public int[] insertOtherIncomeResource(int customerId,
			HashMap<String, String> map) {
		int[] id = new int[map.size()];
		Connection connection = Connect.getConnection();
		int i = 0;
		for (String key : map.keySet()) {
			try {
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `other_income_resource` ( "
								+ "  `customer_id`, "
								+ "  `detail`, "
								+ "  `amount` "
								+ ")  "
								+ "VALUES "
								+ "  ( "
								+ "    ?, " + "    ?, " + "    ? " + "  ) ;");
				prepareStatement.setInt(1, customerId);
				prepareStatement.setString(2, key);
				prepareStatement.setString(3, map.get(key));
				prepareStatement.executeUpdate();
				id[i] = (int) prepareStatement.getLastInsertID();
				i++;
				System.out.println("Other Income Resource Inserted, Last Id : "
						+ prepareStatement.getLastInsertID());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public int[] insertMonthlyExpense(int customerId,
			HashMap<String, String> map) {
		int[] ids = new int[map.size()];
		System.out.println("CustomerBAL.insertMonthlyExpense()");
		System.out.println(map);
		Connection connection = Connect.getConnection();
		int i = 0;
		for (String key : map.keySet()) {
			try {
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `monthly_home_expenses` ( "
								+ "  `customer_id`, "
								+ "  `type`, "
								+ "  `amount` "
								+ "  )  "
								+ "  VALUES "
								+ "  ( " + "  ?, " + "  ?, " + "  ? " + "  ) ;");
				prepareStatement.setInt(1, customerId);
				prepareStatement.setString(2, key);
				prepareStatement.setString(3, map.get(key));
				prepareStatement.executeUpdate();
				ids[i] = (int) prepareStatement.getLastInsertID();
				prepareStatement.close();
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			i++;
		}
		try {
			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 3, updated_date = NOW()  WHERE customer_id = ?");
			updateStatement.setInt(1, customerId);
			updateStatement.executeUpdate();
			System.out.println(updateStatement.asSql());
		} catch (SQLException e1) {
			logger.error("", e1);
			e1.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return ids;
	}

	public int[] insertAssets(int customerId, HashMap<String, String> map) {
		System.out.println("CustomerBAL.insertAssets()");
		System.out.println(map);
		int[] ids = new int[map.size()];
		Connection connection = Connect.getConnection();
		try {
			int i = 0;
			for (String key : map.keySet()) {
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `assets` ( "
								+ "  `customer_id`, " + "    `type`, "
								+ "    `amount` " + "  )  " + "  VALUES "
								+ "    ( " + "      ?, " + "      ?, "
								+ "      ? " + "    ) ;");
				prepareStatement.setInt(1, customerId);
				prepareStatement.setString(2, key);
				prepareStatement.setString(3, map.get(key));
				prepareStatement.executeUpdate();
				ids[i] = (int) prepareStatement.getLastInsertID();
				prepareStatement.close();
				i++;
			}
			try {
				com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("UPDATE form_wizard SET form_wizard_step = 3, updated_date = NOW()  WHERE customer_id = ?");
				updateStatement.setInt(1, customerId);
				updateStatement.executeUpdate();
				System.out.println(updateStatement.asSql());
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			connection.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return ids;
	}

	public int[] insertLoan(int customerId,
			ArrayList<HashMap<String, String>> list) {
		System.out.println("CustomerBAL.insertLoan()");
		System.out.println(list);
		int[] ids = new int[list.size()];

		Connection connection = Connect.getConnection();
		int i = 0;
		for (HashMap<String, String> map : list) {
			try {
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `other_loan` ( "
								+ "  `customer_id`, " + "  `donner`, "
								+ "  `barrowed_amount`, "
								+ "  `remaining_amount`, "
								+ "  `monthly_installment`, " + "  `type` "
								+ ")  " + "VALUES " + "  ( " + "    ?, "
								+ "    ?, " + "    ?, " + "    ?, " + "    ?, "
								+ "    ? " + "  ) ;");
				prepareStatement.setInt(1, customerId);
				prepareStatement.setString(2, map.get("name"));
				prepareStatement.setString(3, map.get("totalAmount"));
				prepareStatement.setString(4, map.get("monthlyInstallment"));
				prepareStatement.setString(5, map.get("remainingAmount"));
				prepareStatement.setString(6, map.get("type"));
				prepareStatement.executeUpdate();
				System.out.println(prepareStatement.asSql());
				ids[i] = (int) prepareStatement.getLastInsertID();
				i++;
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}

		}
		try {
			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 3, updated_date = NOW()  WHERE customer_id = ?");
			updateStatement.setInt(1, customerId);
			updateStatement.executeUpdate();
			System.out.println(updateStatement.asSql());
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return ids;

	}

	// public static ArrayList<HashMap<String, String>> getAssetsOfCustomer(int
	// customerId){
	// ArrayList<HashMap<String, String>> list = new ArrayList<>();
	// HashMap<String, String> map = null;
	//
	// Connection con = Connect.getConnection();
	// String query = "SELECT * FROM assets WHERE assets.customer_id = ?";
	// PreparedStatement ps;
	// ResultSet rs = null;
	// try {
	//
	// ps = con.prepareStatement(query);
	// ps.setInt(1, customerId);
	// rs = ps.executeQuery();
	// while(rs.next()){
	// map = new HashMap<>();
	// map.put("assetId", rs.getInt("asset_id")+"");
	// map.put("type", rs.getString("type"));
	// map.put("amount", rs.getInt("amount")+"");
	// list.add(map);
	// }
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return list;
	// }
	//
	//
	//
	// public static ArrayList<HashMap<String, String>>
	// getLoanAndLiabilities(int userId){
	// ArrayList<HashMap<String, String>> list = new ArrayList<>();
	// HashMap<String, String> map = null;
	// ResultSet rs = null;
	//
	// Connection con = Connect.getConnection();
	//
	// String query = "SELECT * FROM other_loan WHERE other_loan.customer_id =
	// ?";
	// try {
	// PreparedStatement ps = con.prepareStatement(query);
	// ps.setInt(1, userId);
	// rs = ps.executeQuery();
	// while(rs.next()){
	// map = new HashMap<>();
	// map.put("loanId", rs.getInt("loan_id")+"");
	// map.put("customerId", rs.getInt("customer_id")+"");
	// map.put("donner", rs.getString("donner"));
	// map.put("borrowedAmount", rs.getInt("barrowed_amount")+"");
	// map.put("remainingAmount", rs.getInt("remaining_amount")+"");
	// map.put("monthlyInstallment", rs.getInt("monthly_installment")+"");
	// map.put("type", rs.getInt("type")+"");
	// list.add(map);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return list;
	// }
	//
	public static String getCnic(String cnic) {
		String str = null;
		String query = "Select customer_cnic from customer where customer_cnic = "
				+ cnic;

		try (Connection con = Connect.getConnection();) {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return str;
	}

	public static int updateMonthlyHomeExpensesByCustomerId(int customer_id,
			String typee, String amount) {
		// ArrayList<HashMap<String, String>> list = new ArrayList<>();
		int row = 0;
		try {
			Connection connection = Connect.getConnection();

			CallableStatement prepareCall = connection
					.prepareCall("{CALL update_monthly_home_expenses_by_CustomerId(?,?,?)}");
			prepareCall.setInt(1, customer_id);
			prepareCall.setString(2, typee);
			prepareCall.setDouble(3, Double.parseDouble(amount));
			row = prepareCall.executeUpdate();
			if (row > 0) {
				System.out.println("Data has been Updated of monthly Expenses");
			} else {
				System.out.println("Data has not been Updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();

		}
		return row;
	}

	// public static int updateOtherLoanByOltype(int customer_id, String donner,
	// int barrowed_amount, int remaining_amount,
	// int monthly_installment, String ol_type) {
	// // ArrayList<HashMap<String, String>> list = new ArrayList<>();
	// int row = 0;
	// try {
	// Connection connection = Connect.getConnection();
	//
	// CallableStatement prepareCall = connection.prepareCall("{CALL
	// update_other_loan_by_oltype(?,?,?,?,?,?)}");
	// prepareCall.setInt(1, customer_id);
	// prepareCall.setString(2, donner);
	// prepareCall.setInt(3, barrowed_amount);
	// prepareCall.setInt(4, remaining_amount);
	// prepareCall.setInt(5, monthly_installment);
	// prepareCall.setString(6, ol_type);
	// row = prepareCall.executeUpdate();
	// if (row > 0) {
	// System.out.println("Data has been Updated of loan liblities and assets");
	// } else {
	// System.out.println("Data has not been Updated");
	// }
	//
	// } catch (Exception e) {
	// logger.error("", e);
	// System.out.printf(e.getMessage());
	//
	// }
	// return row;
	// }

	public static int UpdateAssetsById(int customerid, String typee,
			String amount) {
		int row = 0;
		try {
			Connection connection = Connect.getConnection();
			CallableStatement callable = connection
					.prepareCall("{CALL update_assets(?,?,?) }");
			callable.setInt(1, customerid);
			callable.setString(2, typee);
			callable.setDouble(3, Double.parseDouble(amount));
			row = callable.executeUpdate();
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;

	}

	public static int updateMonthlyHomeExpensesByExpenseId(int customerId,
			String ids[], String typee[], String amount[]) {
		// ArrayList<HashMap<String, String>> list = new ArrayList<>();
		int row = 0;
		CallableStatement prepareCall = null;
		try {
			Connection connection = Connect.getConnection();
			for (int i = 0; i < typee.length; i++) {
				prepareCall = connection
						.prepareCall("{CALL update_monthly_home_expenses_by_ExpenseId(?,?,?,?)}");
				prepareCall.setInt(2, customerId);
				prepareCall.setInt(1, Integer.parseInt(ids[i]));
				prepareCall.setString(3, typee[i]);
				prepareCall.setDouble(4, Double.parseDouble(amount[i]));
				row = prepareCall.executeUpdate();
				if (row > 0) {
					System.out
							.println("Data has been Updated of monthly Expenses");
				} else {
					System.out
							.println("Data has not been Updated of monthly Expenses");
				}

			} // end for
			prepareCall.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();

		}
		return row;
	}

	public static int updateOtherLoanByOltype(int customer_id, int loanId,
			String donner, int barrowed_amount, int remaining_amount,
			int monthly_installment, String ol_type) {
		// ArrayList<HashMap<String, String>> list = new ArrayList<>();
		int row = 0;

		try {
			Connection connection = Connect.getConnection();

			CallableStatement prepareCall = connection
					.prepareCall("{CALL update_other_loan_by_oltype(?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, customer_id);
			prepareCall.setInt(2, loanId);
			prepareCall.setString(3, donner);
			prepareCall.setInt(4, barrowed_amount);
			prepareCall.setInt(5, remaining_amount);
			prepareCall.setInt(6, monthly_installment);
			prepareCall.setString(7, ol_type);
			row = prepareCall.executeUpdate();
			if (row > 0) {
				System.out
						.println("Data has been Updated of loan liblities and assets");
			} else {
				System.out.println("Data has not been Updated");
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();

		}
		return row;
	}

	public static int UpdateAssetsById(int customerId, String assetsIds[],
			String typee[], String amount[]) {
		int row = 0;
		CallableStatement callable = null;
		try {
			Connection connection = Connect.getConnection();

			for (int i = 0; i < typee.length; i++) {
				callable = connection
						.prepareCall("{CALL update_assets(?,?,?,?) }");
				callable.setInt(1, Integer.parseInt(assetsIds[i]));
				callable.setInt(2, customerId);
				callable.setString(3, typee[i]);
				callable.setDouble(4, Double.parseDouble(amount[i]));

				row = callable.executeUpdate();
				if (row > 0) {
					System.out.println("Data  of assets  Updated");
				} else {
					System.out.println("Data of assets not been Updated");
				}
			} // end for loop
			callable.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return row;

	}

	public static int updateOtherIncomeDetails(String otherIncomeIds[],
			String otherIncomeDetails[], String otherIcomeAmounts[]) {
		int row = 0;
		CallableStatement callableStatement = null;
		try {
			Connection connection = Connect.getConnection();
			for (int j = 0; j < otherIncomeIds.length; j++) {
				callableStatement = connection
						.prepareCall("CALL update_other_income_resources(?,?,?)");
				callableStatement
						.setInt(1, Integer.parseInt(otherIncomeIds[j]));
				callableStatement.setString(2, otherIncomeDetails[j]);
				callableStatement.setInt(3,
						Integer.parseInt(otherIcomeAmounts[j]));
				row = callableStatement.executeUpdate();
				if (row > 0) {
					System.out
							.println("Data  of otherIncomeresources  Updated");
				} else {
					System.out
							.println("Data of otherIncomeresources not been Updated");
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int countPendingCustomers() {
		System.out.println("CustomerBAL.countPendingCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_pending_customers()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countAcceptedCustomers() {
		System.out.println("CustomerBAL.countAcceptedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_accepted_customers()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countVarifiedCustomers() {
		System.out.println("CustomerBAL.countVarifiedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_varified_customers()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int count12MonthScheme() {
		System.out.println("CustomerBAL.countVarifiedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_12month_scheme()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int count18MonthScheme() {
		System.out.println("CustomerBAL.countVarifiedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_18month_scheme()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int count36MonthScheme() {
		System.out.println("CustomerBAL.countVarifiedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_36month_scheme()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countPendingCustomersDo(int districtId) {
		System.out.println("CustomerBAL.countPendingCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_pending_customers_do(?)}");
			prepareCall.setInt(1, districtId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countAcceptedCustomersDo(int districtId) {
		System.out.println("CustomerBAL.countAcceptedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_accepted_customers_do(?)}");
			prepareCall.setInt(1, districtId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static int countVarifiedCustomersDo(int districtId) {
		System.out.println("CustomerBAL.countVarifiedCustomers()");
		Connection connection = Connect.getConnection();
		int count = 0;
		try {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_varified_customers_do(?)}");
			prepareCall.setInt(1, districtId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public static void sendDueDateReminders() {
		try (Connection con = connection.Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_duedate_reminders()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				int days = resultSet.getInt("days");
				String customerName = resultSet.getString("customer_name");
				String customerPhone = resultSet.getString("customer_phone");
				String applianceName = resultSet.getString("appliance_name");
				double monthlyInstallment = resultSet
						.getInt("installment_amount_month");
				String gsmNumber = resultSet.getString("appliance_GSMno");
				Date duedate = resultSet.getDate("due_date");
				String imei_number = resultSet.getString("imei_number");
				Date date = addDays(duedate, 10);
				// Date nextCurrentDate = addDays(new Date(), 1);
				System.out.println("ghhds " + customerPhone);
				if (days == 10) {
					String str = "Moaziz Saarif: aap ko Rs."
							+ monthlyInstallment
							+ " ki agli mahaana adaaigi "
							+ days
							+ " dinoon ma karni hai. Aap ka Consumer number "
							+ imei_number
							+ ", brahay meharbani is pe "
							+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}

				}
				if (days == 7) {
					String str = "Moaziz Saarif: aap ko Rs."
							+ monthlyInstallment
							+ " ki agli mahaana adaaigi "
							+ days
							+ " dinoon ma karni hai. Aap ka Consumer number "
							+ imei_number
							+ ", brahay meharbani is pe "
							+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}

				}
				if (days == 5) {
					String str = "Moaziz Saarif: aap ko Rs."
							+ monthlyInstallment
							+ " ki agli mahaana adaaigi "
							+ days
							+ " dinoon ma karni hai. Aap ka Consumer number "
							+ imei_number
							+ ", brahay meharbani is pe "
							+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}

				}
				if (days >= 0 && days <= 3) {
					String str = "Moaziz Saarif aap ko Rs."
							+ monthlyInstallment
							+ " ki agli mahaana adaaigi "
							+ days
							+ " dinoon ma karni hai. Aap ka Consumer number "
							+ imei_number
							+ ", brahay meharbani is pe "
							+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}

				} else if (days < 0 && days >= 5) {
					String str = "Aap ki adaaigi ki tareekh guzar chuki hai. Brahay meharbaani Rs."
							+ monthlyInstallment
							+ " ki "
							+ "foran adaaigi kar dein. Aap ka Consumer number hai "
							+ imei_number
							+ ", brahay meharbani is pe "
							+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}

				} else if (days < -5 && days >= -10) {
					String str = "Aap ki "
							+ duedate
							+ " ki tareekh ki mahaana adaaigi abhi tak rehti hai. "
							+ "Brahay meharbani "
							+ date
							+ " tareekh tak apni adaaigi laazmi kar dein. Adaaigi na honay "
							+ "ki sourat main Nizam Bijli apna system wapis le le ge. Aap ka Consumer number hai "
							+ imei_number
							+ ", brahay "
							+ "meharbani is pe apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (days < -10) {
					String str = "Brahay meharbani apnay wajib ul adaa ki fouran adaaigi kijiyay. Agar aap yeh"
							+ " adaaigi naheen kar saktay ya Nizam Bijli ka system istamaal naheen karna chahtay toh "
							+ "kal tak Nizam Bijli k office apna system wapis kar k advance ki di"
							+ "howi raqam hasil kar lein. Xyz tareekh tak system wapis na karnay ki sourat main "
							+ "Nizam Bijli system wapis lenay k baad advance ki raqam wapis naheen ki jae ge. Aap "
							+ "ka Consumer number hai "
							+ imei_number
							+ ", brahay meharbani is pe apni adaaigi kar dijiyay. Nizam Bijli "
							+ "istimaal karnay k lia shukriya";
					try {
						System.out.println(customerPhone + " " + customerName
								+ " " + str);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
			}
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static ArrayList<CustomerInfoBean> getCashCustomer(int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();
		System.out.println("GET CUSTOMER APPLIANCE  ------------------");
		CustomerInfoBean bean = null;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_Cash_Customer_infro(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setCnicNo(resultSet.getString("customer_cnic"));
				bean.setAddress(resultSet.getString("customer_address"));
				bean.setPhoneNo(resultSet.getString("customer_phone"));
				bean.setFoName(resultSet.getString("fo_priamary_phone"));
				bean.setSalesmanName(resultSet.getString("salesman_phone_no"));
				bean.setHandoverAt(resultSet.getString("paid_date"));
				maps.add(bean);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, Integer>> getAppliance() {
		ResultSet rs = null;
		HashMap<String, Integer> map = null;
		ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL count_month_wise_scheme()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("scheme", rs.getInt("scheme_name"));
				map.put("noOfInstalledCustomers",
						rs.getInt("countInstalledScheme"));
				list.add(map);
			}
			

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String args[]) {
		/*System.out.println(getUnseenMessagesFromAppliance());*/
		getRequestAddOnStatus(841, 805);
	}

}
