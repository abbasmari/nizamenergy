package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.CustomerProfileViewBean;
import bean.GuarantorBean;
import connection.Connect;

public class CustomerRetrieveDataBAL {
	final static Logger logger = Logger
			.getLogger(CustomerRetrieveDataBAL.class);

	public static CustomerProfileViewBean getCustomerProfile(String cnic) {
		System.out.println("CustomerBAL.get_customers_profile(?)");

		CustomerProfileViewBean bean = null;
		try (Connection con = Connect.getConnection()) {

			CallableStatement prepareCall = con
					.prepareCall("{call get_customers_profile(?)}");
			prepareCall.setString(1, cnic);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				bean = new CustomerProfileViewBean();
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setCustomerName(rs.getString("customer_name"));
				bean.setDateOfBirth(rs.getString("dob") + "");
				// System.err.println("Dob == " + rs.getDate("date_of_birth"));
				bean.setCnicNo(rs.getString("customer_cnic"));
				bean.setAddress(rs.getString("customer_address"));
				bean.setCity_name(rs.getString("cityDistrictName"));
				// bean.setCity_name(rs.getString("city_name"));
				// bean.setDistrict(rs.getString("district_name"));
				bean.setPhoneNo(rs.getString("customer_phone"));
				bean.setPhoneNo2(rs.getString("customer_phone2"));
				bean.setStatus(rs.getInt("e.status"));
				bean.setFatherName(rs.getString("father_name"));
				bean.setMotherName(rs.getString("mother_name"));
				bean.setEmail(rs.getString("email"));
				bean.setGender(rs.getString("gender"));
				bean.setRelationStatus(rs.getString("relation_status"));
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

				// New customer Form updates By Waseem Ahmed

				bean.setCustomerCaste(rs.getString("customerCaste"));
				bean.setCustomerUnionCouncil(rs
						.getString("customer_union_council"));
				bean.setNdFoName(rs.getString("NdFOName"));
				bean.setCustomerDisability(rs.getString("customer_disibility"));
				bean.setCustomerTrainingSkill(rs
						.getString("customer_training_skill"));
				bean.setCustomerCourseName(rs.getString("customer_course_name"));
				bean.setCustomerIsFamilyHead(rs
						.getString("customer_head_faimly"));
				bean.setCustomerDependantMembers(rs
						.getString("customer_dependants_members"));
				bean.setCustomerHouseHoldMembers(rs
						.getString("customer_total_household_members"));
				bean.setCustomerHouseHoldFamilies(rs
						.getString("customer_families_household"));
				bean.setCustomerRoomsAccupiedHouseHold(rs
						.getString("customer_roomoccupied_household"));
				bean.setCustomerResidence(rs.getString("customer_residence"));
				bean.setCustomerOtherResidence(rs
						.getString("customer_other_residence"));
				bean.setCustomerResidencePeriod(rs
						.getString("customer_residence_period"));
				bean.setCustomerProvince(rs.getString("province"));
				bean.setCustomerRequestedDate(rs.getString("requesteddate"));
				bean.setCustomerChildrens(rs.getString("customer_children"));
				bean.setCustomerEducationAmount(rs
						.getDouble("customer_education_amount"));
				bean.setCustomerAdultMembers(rs
						.getString("customer_total_adult_members"));
				bean.setCustomerMedicalRecurring(rs
						.getString("customer_medical_recuring"));
				bean.setCustomerMedicalRecurringAmount(rs
						.getDouble("customer_medical_recuring_amount"));
				bean.setCustomerFamilyType(rs
						.getString("customer_families_type"));
				bean.setCustomerSystemInstalledPlace(rs
						.getString("customer_sys_installed_place"));
				bean.setCustomerSystemInstalledAddress(rs
						.getString("customer_installment_address"));
				bean.setCustomerIncomeContributors(rs
						.getString("customer_income_comtributers"));

				bean.setCustomerTotalIncomeName(rs
						.getDouble("totalMonthlyIncome"));
				bean.setCustomerTotalIrregularlyIncomeName(rs
						.getDouble("totalIrregularIncome"));
				bean.setCustomerAgricultureProfitFrequency(rs
						.getString("agricultureProfitFrequency"));
				bean.setCustomerLiveStockIncome(rs
						.getDouble("customerLivestockIncome"));
				bean.setCustomerLiveStockIncomeType(rs
						.getString("customerLivestockType"));
				bean.setCustomerLiveStockOtherIncomeType(rs
						.getString("customerLivestockOthertype"));
				bean.setCustomerRemittances(rs.getDouble("customerRemittances"));
				bean.setCustomerRemittancesFrequency(rs
						.getString("customerRemittancesFrequency"));
				bean.setCustomerRemittancesRelation(rs
						.getString("customerRemittancesRelation"));
				bean.setCustomerRemittancesProfession(rs
						.getString("customer_remittances_profession"));
				bean.setCustomerRentalProfitIncome(rs
						.getDouble("customer_rental_profit_income"));
				bean.setCustomerRentalProfitFrequency(rs
						.getString("customer_rental_frequency"));
				bean.setCustomerRentalIncomeSource(rs
						.getString("customer_rental_from"));
				bean.setCustomerLabourAmount(rs
						.getDouble("customer_labour_amount"));
				bean.setCustomerLabourType(rs.getString("customer_labour_type"));
				bean.setCustomerLabourInMonth(rs
						.getString("customer_labour_in_month"));
				bean.setCustomerOtherLabourType(rs
						.getString("customer_other_labour_type"));

				double netIncome = bean.getCustomerTotalIncomeName();

			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return bean;
	}

	public static ArrayList<HashMap<String, String>> getCustomerOtherPhoneDetails(
			int customerId) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_other_phone_number(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultset = prepareCall.executeQuery();

			while (resultset.next()) {
				map = new HashMap<>();
				map.put("customer_id", resultset.getInt("customer_id") + "");
				map.put("customer_othre_phone_id",
						resultset.getInt("customer_othre_phone_id") + "");
				map.put("other_name", resultset.getString("other_name") + "");
				map.put("relation_with_other",
						resultset.getString("relation_with_other") + "");
				map.put("other_phone_number",
						resultset.getString("other_phone_number") + "");

				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> getCustomerTotalMonthlyHomeExpenses(
			int customerId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_total_monthly_home_expenses(?)}");
			prepareCall.setInt(1, customerId);

			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customerExpenseId", rs.getInt("customer_expense_id")
						+ "");
				map.put("customerId", rs.getString("customerId"));
				map.put("customerTotalExpenseIncome",
						rs.getDouble("customer_total_expense_income") + "");
				map.put("customerElectricityType",
						rs.getInt("customer_electricity_type") + "");
				map.put("customerSourceOfElectricity",
						rs.getString("customer_sourceof_electricity") + "");
				map.put("customerElectricityAmount",
						rs.getDouble("customer_electricity_amount") + "");
				map.put("customerMobileType",
						rs.getString("customer_mobile_type") + "");
				map.put("customerMobileBillsAmount",
						rs.getDouble("customer_mobilebills_amount") + "");

				map.put("networks",
						(!rs.getBoolean("mobilink_network") ? "" : "Mobilink")
								+ ""
								+ (!rs.getBoolean("ufone_network") ? ""
										: ", Ufone")
								+ ""
								+ (!rs.getBoolean("zong_network") ? ""
										: ", Zong")
								+ ""
								+ (!rs.getBoolean("warid_network") ? ""
										: ", Warid")
								+ ""
								+ (!rs.getBoolean("telenor_network") ? ""
										: ", Telenor") + "" + "");

				map.put("customerTransportMode",
						rs.getString("customer_transport_mode") + "");
				map.put("customerTransportAmount",
						rs.getDouble("customer_transport_amount") + "");
				map.put("customerKaryana", rs.getString("customer_karyana")
						+ "");
				map.put("customerKaryanaAmount",
						rs.getDouble("customer_karyana_amount") + "");
				map.put("customerEstimatedAmount",
						rs.getDouble("customer_estimated_amount") + "");
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return map;
	}

	public static ArrayList<HashMap<String, String>> getOtherIncome(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		String query = "SELECT * FROM other_income_resource WHERE customer_id = ?";
		PreparedStatement ps;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection();) {
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

	public static HashMap<String, String> getCustomerTotalLiveStockAssets(
			int customerId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_total_live_stock_assets(?)}");
			System.out.println("prepareCall");
			prepareCall.setInt(1, customerId);

			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("assetId", rs.getInt("live_asset_id") + "");
				map.put("customerId", rs.getString("customer_id"));
				map.put("customerTotalAssetsIncome",
						rs.getDouble("customerTotalAssetsIncome") + "");
				map.put("customerLand", rs.getString("customer_land") + "");
				map.put("customerBuffalo", rs.getString("customer_bbuffalo")
						+ "");
				map.put("customerCow", rs.getString("customer_cow") + "");
				map.put("customerCalf", rs.getString("customer_calf") + "");
				map.put("customerGoatSheep",
						rs.getString("customer_goat_sheep") + "");
				map.put("customerBike", rs.getString("customer_bike") + "");
				map.put("customerCar", rs.getString("customer_car") + "");
				map.put("customerTractors", rs.getString("customer_tractors")
						+ "");
				map.put("customerTv", rs.getString("customer_tv") + "");
				map.put("customerFridge", rs.getString("customer_fridge") + "");
				map.put("customerLandAmount",
						rs.getDouble("customer_land_amount") + "");
				map.put("customerBuffaloAmount",
						rs.getDouble("customer_buffalo_amount") + "");
				map.put("customerCowAmount",
						rs.getDouble("customer_cow_amount") + "");
				map.put("customerCalfAmount",
						rs.getDouble("customer_calf_amount") + "");
				map.put("customerGoatSheepAmount",
						rs.getDouble("customer_goat_Sheep_amount") + "");
				map.put("customerBikeAmount",
						rs.getDouble("customer_bike_amount") + "");
				map.put("customerCarAmount",
						rs.getDouble("customer_car_amount") + "");
				map.put("customerTractorsAmount",
						rs.getDouble("customer_tractors_amount") + "");
				map.put("customerTvAmount", rs.getDouble("customer_tv_amount")
						+ "");
				map.put("customerFridgeAmount",
						rs.getDouble("customer_fridge_amount") + "");
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return map;
	}

	public static ArrayList<HashMap<String, String>> getAssetsOfCustomer(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		String query = "SELECT * FROM assets WHERE assets.customer_id = ?";
		PreparedStatement ps;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection();) {

			ps = con.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("assetId", rs.getInt("asset_id") + "");
				map.put("customerId", rs.getString("customer_id"));
				map.put("type", rs.getString("type") + "");
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
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_loan_and_liabilities(?)}");
			System.out.println("prepareCall");
			prepareCall.setInt(1, userId);

			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("loanId", rs.getInt("loan_id") + "");
				map.put("customerId", rs.getInt("customer_id") + "");
				map.put("donner", rs.getString("donner"));
				map.put("borrowedAmount", rs.getInt("barrowed_amount") + "");
				map.put("remainingAmount", rs.getInt("remaining_amount") + "");
				map.put("monthlyInstallment", rs.getInt("monthly_installment")
						+ "");
				map.put("paymentPlanInMonth", rs.getInt("payment_planInMonth")
						+ "");
				map.put("frequencyPaymentInMonth",
						rs.getInt("frequency_paymentInMonth") + "");
				map.put("type", rs.getInt("type") + "");
				list.add(map);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static GuarantorBean getGuaranterDetailById(int customerId, int gtype) {

		GuarantorBean bean = null;

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_guarantor(?,?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setInt(2, gtype);

			ResultSet rs = prepareCall.executeQuery();

			while (rs.next()) {
				bean = new GuarantorBean();
				bean.setGuarantorId(rs.getInt("guarantor_id"));
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setGguarantorName(rs.getString("gguarantorName"));
				bean.setGguarantorCnic(rs.getString("gguarantorCnic"));
				bean.setGguarantorPhone(rs.getString("gguarantorPhone"));
				bean.setGuarantorSecondaryPhone(rs
						.getString("gguarantorSecondryPhone"));
				bean.setgRelationToCustomer(rs.getString("gRelationToCustomer"));
				bean.setGguarantorIncome(rs.getString("gguarantorIncome"));
				bean.setGuarantorProfession(rs.getString("guaranterProfession"));
				bean.setGuarantertype(rs.getInt("guarantertype"));
			}
			rs.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return bean;
	}

	public static void main(String[] args) {
		System.out.print(getCustomerProfile("5747777777777"));
	}

}
