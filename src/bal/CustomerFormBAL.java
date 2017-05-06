package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerFormBAL {
	final static Logger logger = Logger.getLogger(CustomerFormBAL.class);

	public int insertCustomer(HashMap<String, String> map) {
		
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			
			System.err.println(":"+Integer.parseInt(map.get("customerIdd"))+":///:"+map.get("customerIdd")+":");
			if (Integer.parseInt(map.get("customerIdd")) != 0) {
				System.err.println("update update_customers_info");
				CallableStatement prepareCall = connection.
						prepareCall("call update_customers_info(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				prepareCall.setString(1, map.get("city"));
				prepareCall.setString(2, map.get("province"));
				System.err.println("province : " + map.get("province"));
				prepareCall.setString(3, map.get("address"));
				prepareCall.setString(4, map.get("customer_union_council"));
				prepareCall.setString(5, map.get("requesteddate"));
				prepareCall.setString(6, map.get("vle"));

				prepareCall.setString(7, map.get("fullname"));
				prepareCall.setString(8, map.get("fatherName"));
				prepareCall.setString(9, map.get("gender"));
				prepareCall.setString(10, map.get("dateOfBirth"));
				prepareCall.setString(11, map.get("customerCaste"));
				prepareCall.setString(12, map.get("cnic"));

				prepareCall.setString(13, map.get("primaryPhone"));
				prepareCall.setString(14, map.get("secondaryPhone"));
				prepareCall.setString(15, map.get("maritalStatus"));
				prepareCall.setString(16, map.get("education"));
				prepareCall.setString(17, map.get("customer_disibility"));
				prepareCall.setString(18, map.get("customer_training_skill"));
				prepareCall.setString(19,
						map.get("customer_training_skill").equalsIgnoreCase("Not Applicable") ? "" : map
								.get("customer_course_name"));
				prepareCall.setString(20, map.get("customer_head_faimly"));
				prepareCall.setString(21,map.get("customer_total_household_members"));
				prepareCall.setString(22, map.get("customer_dependants_members"));
				prepareCall.setString(23, map.get("customer_children"));
				prepareCall.setDouble(24,Double.parseDouble(map.get("customer_education_amount")));
				prepareCall.setString(25, map.get("customer_total_adult_members"));
				prepareCall.setString(26, map.get("customer_medical_recuring"));
				prepareCall.setDouble(27, map.get("customer_medical_recuring")
										.equalsIgnoreCase("No") ? 0
										: Double.parseDouble(map.get("customer_medical_recuring_amount")));
				prepareCall.setString(28, map.get("customer_families_type"));
				prepareCall.setString(29, map.get("customer_families_type").equalsIgnoreCase(
								"single family") ? "1" : map.get("customer_families_household"));

				prepareCall.setString(30, map.get("customer_roomoccupied_household"));
				prepareCall.setString(31, map.get("customer_residence"));
				prepareCall.setString(32,map.get("customer_residence").equalsIgnoreCase(
										"other") ? map.get("customer_other_residence") : "");
				prepareCall.setString(33, map.get("customer_residence_period"));
				prepareCall.setString(34, map.get("customer_sys_installed_place"));
				prepareCall.setString(35, map.get("customer_sys_installed_place").equalsIgnoreCase(
								"Home") ? "" : map.get("customer_installment_address"));
				prepareCall.setInt(36, Integer.parseInt(map.get("customerIdd")));
				prepareCall.setInt(37, Integer.parseInt(map.get("applianceId")));
				prepareCall.executeUpdate();
				
				id =  Integer.parseInt(map.get("customerIdd"));
				updateWizart(id, 1);
				}else{
			System.err.println("insert_customers_info");
																																																																																																													CallableStatement prepareCall = connection
					.prepareCall("{call insert_customers_info(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				prepareCall.setString(1, map.get("city"));
				prepareCall.setString(2, map.get("province"));
				System.err.println("province : " + map.get("province"));
				prepareCall.setString(3, map.get("address"));
				prepareCall.setString(4, map.get("customer_union_council"));
				prepareCall.setString(5, map.get("requesteddate"));
				prepareCall.setString(6, map.get("vle"));
	
				prepareCall.setString(7, map.get("fullname"));
				prepareCall.setString(8, map.get("fatherName"));
				// prepareCall.setString(9, map.get("email"));
				prepareCall.setString(9, map.get("gender"));
				prepareCall.setString(10, map.get("dateOfBirth"));
				// prepareCall.setString(13, map.get("familySize"));
				// prepareCall.setString(17, map.get("relationStatus"));
				// prepareCall.setInt(14,
				// (map.get("isEducated").equals("true")?1:0));
				prepareCall.setString(11, map.get("customerCaste"));
				prepareCall.setString(12, map.get("cnic"));
				// prepareCall.setInt(13, family);
	
				prepareCall.setString(13, map.get("primaryPhone"));
				prepareCall.setString(14, map.get("secondaryPhone"));
				prepareCall.setString(15, map.get("maritalStatus"));
				prepareCall.setString(16, map.get("education"));
				prepareCall.setString(17, map.get("customer_disibility"));
				prepareCall.setString(18, map.get("customer_training_skill"));
				prepareCall.setString(
						19,
						map.get("customer_training_skill").equalsIgnoreCase(
								"Not Applicable") ? "" : map
								.get("customer_course_name"));
				prepareCall.setString(20, map.get("customer_head_faimly"));
				prepareCall.setString(21,
						map.get("customer_total_household_members"));
				prepareCall.setString(22, map.get("customer_dependants_members"));
				prepareCall.setString(23, map.get("customer_children"));
				prepareCall.setDouble(24,
						Double.parseDouble(map.get("customer_education_amount")));
				System.out.println("customer_education_amount "
						+ map.get("customer_education_amount"));
				prepareCall.setString(25, map.get("customer_total_adult_members"));
				prepareCall.setString(26, map.get("customer_medical_recuring"));
				prepareCall
						.setDouble(
								27,
								map.get("customer_medical_recuring")
										.equalsIgnoreCase("No") ? 0
										: Double.parseDouble(map
												.get("customer_medical_recuring_amount")));
				prepareCall.setString(28, map.get("customer_families_type"));
				prepareCall.setString(
						29,
						map.get("customer_families_type").equalsIgnoreCase(
								"single family") ? "1" : map
								.get("customer_families_household"));
	
				prepareCall.setString(30,
						map.get("customer_roomoccupied_household"));
				prepareCall.setString(31, map.get("customer_residence"));
				prepareCall
						.setString(
								32,
								map.get("customer_residence").equalsIgnoreCase(
										"other") ? map.get("customer_other_residence") : "");
				prepareCall.setString(33, map.get("customer_residence_period"));
				prepareCall.setString(34, map.get("customer_sys_installed_place"));
				prepareCall.setString(35,
						map.get("customer_sys_installed_place").equalsIgnoreCase(
								"Home") ? "" : map.get("customer_installment_address"));
	
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("customer_id");
					updateWizart(id, 1);
				}
		}
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int[] insertOtherPhoneDetails(int customerId,
			ArrayList<HashMap<String, String>> list) {

		int[] ids = new int[list.size()];
		int i = 0;

		try(Connection connection = Connect.getConnection()) {
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM customer_other_phonenumber co WHERE co.customerId = ?");
		preparedStatement.setInt(1, customerId);
		ResultSet resultSet2 = preparedStatement.executeQuery();
		if (resultSet2.next()) {
			int j = 0;
			for (HashMap<String, String> map : list) {
				CallableStatement prepareCall = connection
						.prepareCall("{call update_customer_other_phonenumber(?,?,?,?,?)}");
				prepareCall.setInt(1, customerId);
				prepareCall.setString(2, map.get("whoIsOther"));
				prepareCall.setString(3, map.get("relationCustomer"));
				prepareCall.setString(4, map.get("otherPhone"));
				prepareCall.setInt(5, j);
				ResultSet resultset = prepareCall.executeQuery();
				if (resultset.next()) {
					ids[i] = resultset.getInt("inserted_other_phone_id");
				}
				i++;
				j++;
			}
		}else{
			
		for (HashMap<String, String> map : list) {
			int j = 0;
				CallableStatement prepareCall = connection
						.prepareCall("{call customer_other_phonenumber(?,?,?,?,?)}");
				prepareCall.setInt(1, customerId);
				prepareCall.setString(2, map.get("whoIsOther"));
				prepareCall.setString(3, map.get("relationCustomer"));
				prepareCall.setString(4, map.get("otherPhone"));
				prepareCall.setInt(5, j);
				ResultSet resultset = prepareCall.executeQuery();
				if (resultset.next()) {
					ids[i] = resultset.getInt("inserted_other_phone_id");
				}
				i++;
				j++;
			}
		}
			connection.close();
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
		}
		updateWizart(customerId, 1);
		return ids;
	}

	public int insertEducationMedicalAmount(int customerId,
			double edu_medical_expense) {
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM insert_customer_expense ic WHERE ic.customerId = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				CallableStatement prepareCall = connection.prepareCall("{call update_educational_medical_expense(?,?)}");
				prepareCall.setInt(1, customerId);
				prepareCall.setDouble(2, edu_medical_expense);
				prepareCall.executeUpdate();
				System.err.println("Update update_educational_medical_expense");
			}else{
				System.err.println(" insert_educational_medical_expense");
				CallableStatement prepareCall = connection.prepareCall("{call insert_educational_medical_expense(?,?)}");
				prepareCall.setInt(1, customerId);
				prepareCall.setDouble(2, edu_medical_expense);
	
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("inserted_edu_medical_id");
					updateWizart(customerId, 1);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return id;
	}

	public int updateCustomerIncomeSource(HashMap<String, String> map) {
		System.out.println("CustomerBAL.updateCustomerIncomeSource()");
		System.out.println(map);
		int isupdated = 0;
		try(Connection connection = Connect.getConnection()) {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE  "
							+ "  `customer`  "
							+ "  SET "
							+ "    `salary_or_pension` = ?, "
							+ "    `business_income` = ?, "
							+ "`farming` = ?, "
							+ "`family_contribution` = ?,customer_income_comtributers = ? "
							+ "  WHERE `customer_id` = ? ;");
			prepareStatement.setString(
					1,
					map.get("salaryOrPension").isEmpty() ? "0" : map
							.get("salaryOrPension"));
			prepareStatement.setString(
					2,
					map.get("businessIncome").isEmpty() ? "0" : map
							.get("businessIncome"));
			prepareStatement.setString(3,
					map.get("farmingIncome").isEmpty() ? "0" : map.get("farmingIncome"));
			prepareStatement.setString(4,map.get("customer_income_comtributers").equalsIgnoreCase("0") ? "0" : (map.get("familyContribution").isEmpty() ? "0" : map.get("familyContribution")));
			prepareStatement.setString(5,map.get("customer_income_comtributers").isEmpty() ? "0": map.get("customer_income_comtributers"));
			prepareStatement.setInt(6, Integer.parseInt(map.get("customerId")));

			prepareStatement.executeUpdate();
			updateWizart(Integer.parseInt(map.get("customerId")), 2);
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return isupdated;
	}

	public int inserMonthlyYearlyIncome(HashMap<String, String> map) {
		System.err.println("CustomerBAL.inserMonthlyYearlyIncome()");
		int id = -1;

		try(Connection connection = Connect.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM monthly_and_yearly_income my WHERE my.customerId = ?");
			preparedStatement
					.setInt(1, Integer.parseInt(map.get("customerId")));
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				// Update
				CallableStatement prepareCall = connection
						.prepareCall("{call update_monthly_yearly_income(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				prepareCall.setString(1, map.get("customerId"));
				prepareCall.setString(2, map.get("customer_total_income_name"));
				prepareCall.setString(3,
						map.get("customer_total_irregularly_income_name"));
				prepareCall.setString(4, map.get("agricultureProfitFrequency"));
				prepareCall.setString(
						5,
						map.get("customerLivestockType").equalsIgnoreCase(
								"None") ? "0" : map
								.get("customerLivestockIncome"));
				prepareCall.setString(6, map.get("customerLivestockType"));
				prepareCall.setString(
						7,
						map.get("customerLivestockType").equalsIgnoreCase(
								"other") ? "" : map
								.get("customerLivestockOthertype"));
				prepareCall.setString(
						8,
						map.get("customerRemittancesFrequency")
								.equalsIgnoreCase("None") ? "0" : map
								.get("customerRemittances"));
				prepareCall.setString(9,
						map.get("customerRemittancesFrequency"));
				prepareCall.setString(
						10,
						map.get("customerRemittancesFrequency")
								.equalsIgnoreCase("None") ? "" : map
								.get("customerRemittancesRelation"));
				prepareCall.setString(
						11,
						map.get("customerRemittancesFrequency")
								.equalsIgnoreCase("None") ? "" : map
								.get("customer_remittances_profession"));
				prepareCall.setString(
						12,
						map.get("customer_rental_frequency").equalsIgnoreCase(
								"None") ? "0" : map
								.get("customer_rental_profit_income"));
				prepareCall.setString(13, map.get("customer_rental_frequency"));
				prepareCall.setString(
						14,
						map.get("customer_rental_frequency").equalsIgnoreCase(
								"None") ? "" : map.get("customer_rental_from"));
				prepareCall.setString(
						15,
						map.get("customer_labour_type")
								.equalsIgnoreCase("None") ? "0" : map
								.get("customer_labour_amount"));
				prepareCall.setString(16, map.get("customer_labour_type"));
				prepareCall.setString(
						17,
						map.get("customer_labour_type")
								.equalsIgnoreCase("None") ? "0" : map
								.get("customer_labour_in_month"));
				prepareCall.setString(
						18,
						map.get("customer_labour_type").equalsIgnoreCase(
								"Other") ? map
								.get("customer_other_labour_type") : "0");

				System.err.println("customer_labour_amount"
						+ map.get("customer_labour_amount"));
				System.err.println(map.get("customer_labour_type"));
				System.err.println(map.get("customer_labour_in_month"));
				System.err.println(map.get("customer_other_labour_type"));

				prepareCall.executeUpdate();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{call inser_monthly_yearly_income(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				prepareCall.setString(1, map.get("customerId"));
				prepareCall.setString(2, map.get("customer_total_income_name"));
				prepareCall.setString(3,
						map.get("customer_total_irregularly_income_name"));
				prepareCall.setString(4, map.get("agricultureProfitFrequency"));
				prepareCall.setString(
						5,
						map.get("customerLivestockType").equalsIgnoreCase(
								"None") ? "0" : map
								.get("customerLivestockIncome"));
				prepareCall.setString(6, map.get("customerLivestockType"));
				prepareCall.setString(
						7,
						map.get("customerLivestockType").equalsIgnoreCase(
								"other") ? "" : map
								.get("customerLivestockOthertype"));
				prepareCall.setString(
						8,
						map.get("customerRemittancesFrequency")
								.equalsIgnoreCase("None") ? "0" : map
								.get("customerRemittances"));
				prepareCall.setString(9,
						map.get("customerRemittancesFrequency"));
				prepareCall.setString(
						10,
						map.get("customerRemittancesFrequency")
								.equalsIgnoreCase("None") ? "" : map
								.get("customerRemittancesRelation"));
				prepareCall.setString(
						11,
						map.get("customerRemittancesFrequency")
								.equalsIgnoreCase("None") ? "" : map
								.get("customer_remittances_profession"));
				prepareCall.setString(
						12,
						map.get("customer_rental_frequency").equalsIgnoreCase(
								"None") ? "0" : map
								.get("customer_rental_profit_income"));
				prepareCall.setString(13, map.get("customer_rental_frequency"));
				prepareCall.setString(
						14,
						map.get("customer_rental_frequency").equalsIgnoreCase(
								"None") ? "" : map.get("customer_rental_from"));
				prepareCall.setString(
						15,
						map.get("customer_labour_type")
								.equalsIgnoreCase("None") ? "0" : map
								.get("customer_labour_amount"));
				prepareCall.setString(16, map.get("customer_labour_type"));
				prepareCall.setString(
						17,
						map.get("customer_labour_type")
								.equalsIgnoreCase("None") ? "0" : map
								.get("customer_labour_in_month"));
				prepareCall.setString(
						18,
						map.get("customer_labour_type").equalsIgnoreCase(
								"Other") ? map
								.get("customer_other_labour_type") : "0");

				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("inserted_my_income_id");
					updateWizart(Integer.parseInt(map.get("customerId")), 2);
				}
			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int[] insertOtherIncomeResource(int customerId,
			HashMap<String, String> map) {
		System.err.println("insertOtherIncomeResource");
		int[] id = new int[map.size()];
		int i = 0;
		try(Connection connection = Connect.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM other_income_resource oi WHERE oi.customer_id = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				System.err.println("update /////////////////");
				for (String key : map.keySet()) {
					int j = 0;
					com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
							.prepareStatement("UPDATE `other_income_resource` SET `  "
									+ "  `detail` = ?, " + "  `amount` = ?"
											+ "where customer_id` = ? AND income_key = ?;");
					prepareStatement.setInt(1, customerId);
					prepareStatement.setString(2, key);
					prepareStatement.setString(3, map.get(key));
					prepareStatement.setInt(4, j);
					prepareStatement.executeUpdate();
					j++; 
				}
			} else {
				System.err.println("insert //////////////");
				for (String key : map.keySet()) {
					int j = 0;
					com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
							.prepareStatement("INSERT INTO `other_income_resource` ( "
									+ "  `customer_id`, "
									+ "  `detail`, "
									+ "  `amount`, "
									+ "  income_key "
									+ ")  "
									+ "VALUES "
									+ "  ( "
									+ "    ?, "
									+ "    ?, "
									+ "    ?,"
									+ "	   ? "
									+ "  ) ;");
					prepareStatement.setInt(1, customerId);
					prepareStatement.setString(2, key);
					prepareStatement.setString(3, map.get(key));
					prepareStatement.setInt(4, j);
					prepareStatement.executeUpdate();
					id[i] = (int) prepareStatement.getLastInsertID();
					i++;
					System.out.println("Other Income Resource Inserted, Last Id : "
									+ prepareStatement.getLastInsertID());
					j++;
				}
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public int insertCustomerEmployementInfo(HashMap<String, String> map) {
		int id = 0;
		try(Connection connection = Connect.getConnection()) {

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM employment_details ed WHERE ed.customer_id = ?");
			preparedStatement
					.setInt(1, Integer.parseInt(map.get("customerId")));
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				// Update
				CallableStatement prepareCall = connection
						.prepareCall("{call update_employment_details2(?,?,?,?)}");

				prepareCall.setString(1, map.get("customerId"));
				prepareCall.setString(2, map.get("companyTitle"));
				prepareCall.setString(3, map.get("designation"));
				prepareCall.setString(4, map.get("jobPeriod"));
				prepareCall.executeUpdate();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{call insert_employment_details(?,?,?,?)}");

				prepareCall.setString(1, map.get("customerId"));
				prepareCall.setString(2, map.get("companyTitle"));
				prepareCall.setString(3, map.get("designation"));
				prepareCall.setString(4, map.get("jobPeriod"));
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("inserted_emp_id");
					updateWizart(Integer.parseInt(map.get("customerId")), 2);
				}
				// System.out.println(prepareStatement.asSql());
			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int insertCustomerBusinessInfo(HashMap<String, String> map) {
		int id = 0;
		try(Connection connection = Connect.getConnection()) {

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM business_info WHERE customer_id = ?");
			preparedStatement
					.setInt(1, Integer.parseInt(map.get("customerId")));
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				CallableStatement prepareCall = connection
						.prepareCall("{call update_business_info2(?,?,?,?)}");
				prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
				prepareCall.setString(2, map.get("title"));
				prepareCall.setString(3, map.get("type"));
				prepareCall.setString(4, map.get("timePeriod"));

				prepareCall.executeUpdate();
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{call insert_business_info(?,?,?,?)}");
				prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
				prepareCall.setString(2, map.get("title"));
				prepareCall.setString(3, map.get("type"));
				prepareCall.setString(4, map.get("timePeriod"));
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("inserted_businessId");
					updateWizart(Integer.parseInt(map.get("customerId")), 2);
				}

				// id = (int) prepareCall.getLastInsertID();
				connection.close();
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int insertCustomerLivestockAssets(HashMap<String, String> map) {
		
		int customerId = Integer.parseInt(map.get("customerId"));
		int id = -1;
		try(Connection connection = Connect.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM customer_livestock_assets cl WHERE cl.customer_id = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				// Update
				CallableStatement prepareCall = connection
						.prepareCall("{call update_customer_livestock_assets(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				prepareCall.setString(1, map.get("customerId"));
				prepareCall.setString(2, map.get("customerTotalAssetsIncome"));
				prepareCall.setString(3, map.get("customer_land"));
				prepareCall.setString(4, map.get("customer_bbuffalo"));
				prepareCall.setString(5, map.get("customer_cow"));
				prepareCall.setString(6, map.get("customer_calf"));
				prepareCall.setString(7, map.get("customer_goat_sheep"));
				prepareCall.setString(8, map.get("customer_bike"));
				prepareCall.setString(9, map.get("customer_car"));
				prepareCall.setString(10, map.get("customer_tractors"));
				prepareCall.setString(11, map.get("customer_tv"));
				prepareCall.setString(12, map.get("customer_fridge"));
				prepareCall.setString(13, map.get("customer_land_amount"));
				prepareCall.setString(14, map.get("customer_buffalo_amount"));
				prepareCall.setString(15, map.get("customer_cow_amount"));
				prepareCall.setString(16, map.get("customer_calf_amount"));
				prepareCall
						.setString(17, map.get("customer_goat_Sheep_amount"));
				prepareCall.setString(18, map.get("customer_bike_amount"));
				prepareCall.setString(19, map.get("customer_car_amount"));
				prepareCall.setString(20, map.get("customer_tractors_amount"));
				prepareCall.setString(21, map.get("customer_tv_amount"));
				prepareCall.setString(22, map.get("customer_fridge_amount"));
				prepareCall.executeUpdate();
				updateWizart(Integer.parseInt(map.get("customerId")), 3);
			} else {
				CallableStatement prepareCall = connection
						.prepareCall("{call insert_customer_livestock_assets(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				prepareCall.setString(1, map.get("customerId"));
				prepareCall.setString(2, map.get("customerTotalAssetsIncome"));
				prepareCall.setString(3, map.get("customer_land"));
				prepareCall.setString(4, map.get("customer_bbuffalo"));
				prepareCall.setString(5, map.get("customer_cow"));
				prepareCall.setString(6, map.get("customer_calf"));
				prepareCall.setString(7, map.get("customer_goat_sheep"));
				prepareCall.setString(8, map.get("customer_bike"));
				prepareCall.setString(9, map.get("customer_car"));
				prepareCall.setString(10, map.get("customer_tractors"));
				prepareCall.setString(11, map.get("customer_tv"));
				prepareCall.setString(12, map.get("customer_fridge"));
				prepareCall.setString(13, map.get("customer_land_amount"));
				prepareCall.setString(14, map.get("customer_buffalo_amount"));
				prepareCall.setString(15, map.get("customer_cow_amount"));
				prepareCall.setString(16, map.get("customer_calf_amount"));
				prepareCall
						.setString(17, map.get("customer_goat_Sheep_amount"));
				prepareCall.setString(18, map.get("customer_bike_amount"));
				prepareCall.setString(19, map.get("customer_car_amount"));
				prepareCall.setString(20, map.get("customer_tractors_amount"));
				prepareCall.setString(21, map.get("customer_tv_amount"));
				prepareCall.setString(22, map.get("customer_fridge_amount"));

				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("inserted_live_asset_id");
					System.err.println("inserted_live_asset_id : " + id);
					updateWizart(Integer.parseInt(map.get("customerId")), 3);
				}
			}

			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int insertCustomerExpense(HashMap<String, String> map) {

		// int customerId = Integer.parseInt(map.get("customerId"));
		int id = -1;
		try (Connection connection = Connect.getConnection()) {

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM insert_customer_expense ic WHERE ic.customerId = ?");
			preparedStatement
					.setInt(1, Integer.parseInt(map.get("customerId")));
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				// Update
				CallableStatement prepareCall = connection
						.prepareCall("{call update_customer_expense(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
				prepareCall.setInt(2, Integer.parseInt(map
						.get("customer_total_expense_income")));
				prepareCall.setInt(3, (Integer.parseInt(map.get("customer_electricity_type"))));
				System.out.println("00000000//////////////////00000000000000000////////////"+(map
						.get("customer_electricity_type")));
				prepareCall.setString(4,
						map.get("customer_sourceof_electricity"));
				prepareCall.setDouble(5, Double.parseDouble(map
						.get("customer_electricity_amount")));
				prepareCall.setString(6, map.get("customer_mobile_type"));
				prepareCall.setDouble(7, Double.parseDouble(map
						.get("customer_mobilebills_amount")));
				prepareCall.setInt(8,
						Integer.parseInt(map.get("mobilink_network")));
				prepareCall.setInt(9,
						Integer.parseInt(map.get("ufone_network")));
				prepareCall.setInt(10,
						Integer.parseInt(map.get("zong_network")));
				prepareCall.setInt(11,
						Integer.parseInt(map.get("warid_network")));
				prepareCall.setInt(12,
						Integer.parseInt(map.get("telenor_network")));
				prepareCall.setString(13, map.get("customer_transport_mode"));
				prepareCall.setDouble(14, Double.parseDouble(map
						.get("customer_transport_amount")));
				prepareCall.setString(15, map.get("customer_karyana"));
				prepareCall.setDouble(16,
						Double.parseDouble(map.get("customer_karyana_amount")));
				prepareCall.setDouble(17, 0);

				prepareCall.executeUpdate();
				updateWizart(Integer.parseInt(map.get("customerId")), 3);
			} else {

				CallableStatement prepareCall = connection
						.prepareCall("{call insert_customer_expense(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				prepareCall.setInt(1, Integer.parseInt(map.get("customerId")));
				prepareCall.setInt(2, Integer.parseInt(map
						.get("customer_total_expense_income")));
				prepareCall.setInt(3, (Integer.parseInt(map.get("customer_electricity_type"))));
				prepareCall.setString(4,
						map.get("customer_sourceof_electricity"));
				prepareCall.setDouble(5, Double.parseDouble(map
						.get("customer_electricity_amount")));
				prepareCall.setString(6, map.get("customer_mobile_type"));
				prepareCall.setDouble(7, Double.parseDouble(map
						.get("customer_mobilebills_amount")));
				prepareCall.setInt(8,
						Integer.parseInt(map.get("mobilink_network")));
				prepareCall.setInt(9,
						Integer.parseInt(map.get("ufone_network")));
				prepareCall.setInt(10,
						Integer.parseInt(map.get("zong_network")));
				prepareCall.setInt(11,
						Integer.parseInt(map.get("warid_network")));
				prepareCall.setInt(12,
						Integer.parseInt(map.get("telenor_network")));
				prepareCall.setString(13, map.get("customer_transport_mode"));
				prepareCall.setDouble(14, Double.parseDouble(map
						.get("customer_transport_amount")));
				prepareCall.setString(15, map.get("customer_karyana"));
				prepareCall.setDouble(16,
						Double.parseDouble(map.get("customer_karyana_amount")));
				prepareCall.setDouble(17, 0);

				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("inserted_expense_id");
					updateWizart(Integer.parseInt(map.get("customerId")), 3);
				}
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int[] insertMonthlyExpense(int customerId,
			HashMap<String, String> map) {
		int[] ids = new int[map.size()];
		System.out.println("CustomerBAL.insertMonthlyExpense()");
		int i = 0;

		try(Connection connection = Connect.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM monthly_home_expenses mh WHERE mh.customer_id = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				for (String key : map.keySet()) {
					com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
							.prepareStatement("UPDATE `monthly_home_expenses` SET `type` = ?, "
									+ "  `amount` = ? where `customer_id` = ?");
					prepareStatement.setString(1, key);
					prepareStatement.setInt(2, Integer.parseInt(map.get(key)));
					prepareStatement.setInt(3, customerId);
					prepareStatement.executeUpdate();
					i++;
				}

			} else {
				for (String key : map.keySet()) {
					com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
							.prepareStatement("INSERT INTO `monthly_home_expenses` ( "
									+ "  `customer_id`, "
									+ "  `type`, "
									+ "  `amount` "
									+ "  )  "
									+ "  VALUES "
									+ "  ( "
									+ "  ?, "
									+ "  ?, "
									+ "  ? "
									+ "  ) ;");
					prepareStatement.setInt(1, customerId);
					prepareStatement.setString(2, key);
					prepareStatement.setInt(3, Integer.parseInt(map.get(key)));
					prepareStatement.executeUpdate();
					ids[i] = (int) prepareStatement.getLastInsertID();
					prepareStatement.close();
					i++;
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		updateWizart(customerId, 3);
		return ids;
	}

	public int[] insertAssets(int customerId, HashMap<String, String> map) {
		System.out.println("CustomerBAL.insertAssets()");
		int[] ids = new int[map.size()];
		try(Connection connection = Connect.getConnection()) {

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM assets a WHERE a.customer_id = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				for (String key : map.keySet()) {
					int i = 0;

					com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
							.prepareStatement("UPDATE `assets` Set `type` = ?, `amount` = ? "
									+ " Where `customer_id` = ? AND assets_key = ?");
					prepareStatement.setString(1, key);
					prepareStatement.setString(2, map.get(key));
					prepareStatement.setInt(3, customerId);
					prepareStatement.setInt(4, i);
					prepareStatement.executeUpdate();
					i++;
				}
			} else {
				for (String key : map.keySet()) {
					int i = 0;
					com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
							.prepareStatement("INSERT INTO `assets` ( "
									+ "  `customer_id`, " + "    `type`, "
									+ "    `amount` " + ", assets_key  )  " + "  VALUES "
									+ "    ( " + "      ?, " + "      ?, "
									+ "      ?, ? " + "    ) ;");
					prepareStatement.setInt(1, customerId);
					prepareStatement.setString(2, key);
					prepareStatement.setString(3, map.get(key));
					prepareStatement.setInt(4, i);
					prepareStatement.executeUpdate();
					ids[i] = (int) prepareStatement.getLastInsertID();
					prepareStatement.close();
					i++;
				}
			}
			updateWizart(customerId, 3);
			connection.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return ids;
	}

	public int[] insertLoan(int customerId,
			ArrayList<HashMap<String, String>> list) {
		int[] ids = new int[list.size()];
		System.err.println("insertLoan");
		int i = 0;
		try(Connection connection = Connect.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM other_loan io WHERE io.customer_id = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				for (HashMap<String, String> map : list) {
					// Update
					System.err.println("if insertLoan Updated " + map);
					CallableStatement prepareCall = connection
							.prepareCall("{call update_other_loan(?,?,?,?,?,?,?,?)}");
					prepareCall.setInt(1, customerId);
					prepareCall.setString(2, map.get("donner"));
					prepareCall.setString(3, map.get("totalAmount"));
					prepareCall.setString(4, map.get("remainingAmount"));
					prepareCall.setString(5, map.get("monthlyInstallment"));
					prepareCall.setString(6,
							map.get("customerPaymentPlanInMonth"));
					prepareCall.setString(7,
							map.get("customerFrequencyInMonth"));
					prepareCall.setInt(8, Integer.parseInt(map.get("type")));
					System.err.println(map.get("customerPaymentPlanInMonth"));
					System.err.println(map.get("customerFrequencyInMonth"));
					System.err.println("00000000: "
							+ Integer.parseInt(map.get("type")));
					// prepareCall.executeUpdate();
				}
			} else {
				System.err.println("if insertLoan insert");
				for (HashMap<String, String> map : list) {

					System.err.println("insertLoan: " + map);
					CallableStatement prepareCall = connection
							.prepareCall("{call insert_other_loan(?,?,?,?,?,?,?,?)}");
					prepareCall.setInt(1, customerId);
					prepareCall.setString(2, map.get("donner"));
					prepareCall.setString(3, map.get("totalAmount"));
					prepareCall.setString(4, map.get("remainingAmount"));
					prepareCall.setString(5, map.get("monthlyInstallment"));
					prepareCall.setString(6,
							map.get("customerPaymentPlanInMonth"));
					prepareCall.setString(7,
							map.get("customerFrequencyInMonth"));
					prepareCall.setInt(8, Integer.parseInt(map.get("type")));
					System.err.println("000000000000000000000 "
							+ Integer.parseInt(map.get("type")));
					ResultSet resultset = prepareCall.executeQuery();
					System.err.println("000000000000000000222 "
							+ Integer.parseInt(map.get("type")));
					if (resultset.next()) {
						System.err.println("if insertLoan");
						ids[i] = resultset.getInt("inserted_loan_id");
						System.err.println("inserted_loan_id:: " + ids[i]);
					} else {
						System.err.println("else insertLoan");
					}
				}
				i++;

			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		updateWizart(customerId, 3);
		return ids;
	}

	public int insertCustomerGuarantorInfo(HashMap<String, String> map,
			int customerId, int guarantorType) {
		int id = 0;
		try(Connection connection = Connect.getConnection()) {

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM customer_guarantor cg WHERE cg.customer_id = ? AND cg.guarantertype = ?");
			preparedStatement
					.setInt(1, Integer.parseInt(map.get("customerId")));
			preparedStatement.setInt(2, guarantorType);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			if (resultSet2.next()) {
				// Update
				CallableStatement prepareCall = connection
						.prepareCall("{call update_customer_guarantors(?,?,?,?,?,?,?,?,?)}");

				prepareCall.setInt(1, customerId);
				prepareCall.setString(2, map.get("name"));
				prepareCall.setString(3, map.get("cnic"));
				prepareCall.setString(4, "92" + map.get("cellNumber"));
				prepareCall.setString(5, "92" + map.get("secondaryCellNumber"));
				prepareCall.setString(6, map.get("relation"));
				prepareCall.setString(7, map.get("incomeSource"));
				prepareCall.setString(8, map.get("guarantorProfession"));
				prepareCall.setString(9, map.get("guarantorType"));
				prepareCall.executeUpdate();
			} else {

				CallableStatement prepareCall = connection
						.prepareCall("{call insert_customer_guarantors(?,?,?,?,?,?,?,?,?)}");

				prepareCall.setInt(1, customerId);
				prepareCall.setString(2, map.get("name"));
				prepareCall.setString(3, map.get("cnic"));
				prepareCall.setString(4, "92" + map.get("cellNumber"));
				prepareCall.setString(5, "92" + map.get("secondaryCellNumber"));
				prepareCall.setString(6, map.get("relation"));
				prepareCall.setString(7, map.get("incomeSource"));
				prepareCall.setString(8, map.get("guarantorProfession"));
				prepareCall.setString(9, map.get("guarantorType"));

				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("live_asset_id");
					updateWizart(Integer.parseInt(map.get("customerId")), 4);
				}
			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public static int updateWizart(int customerId, int WizardStep) {
		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			int formWizardStep = 0;
			PreparedStatement prepareCall = connection
					.prepareStatement("SELECT f.form_wizard_step FROM form_wizard f WHERE f.customer_id = ?");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				formWizardStep = Integer.parseInt(resultSet.getString(1));
			}

			if (WizardStep > formWizardStep) {
				System.err.println("if (WizardStep > formWizardStep) "
						+ WizardStep + " > " + formWizardStep);

				com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("UPDATE form_wizard SET form_wizard_step = ?, updated_date = NOW()  WHERE customer_id = ?");
				updateStatement.setInt(1, WizardStep);
				updateStatement.setInt(2, customerId);
				updateStatement.executeUpdate();
				System.out.println(updateStatement.asSql());
			} else {
				System.err.println("Else " + WizardStep + " < "
						+ formWizardStep);
				com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("UPDATE form_wizard SET updated_date = NOW()  WHERE customer_id = ?");
				updateStatement.setInt(1, customerId);
				updateStatement.executeUpdate();
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public static ArrayList<HashMap<String, String>> getLoan() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			com.mysql.jdbc.PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT * FROM monthly_scheme_frequency");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("scheme_frequency_id",
						" " + resultSet.getString("scheme_frequency_id"));
				map.put("schemeFrequency",
						" " + resultSet.getString("scheme_frequency"));
				list.add(map);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public int insertCustomerVleGaurantor(int customerId, int salesmanId) {
		System.out.println("CustomerBAL.insertCustomerVleGaurantor()");
		int id = 0;
		try(Connection connection = Connect.getConnection()) {
			com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("INSERT INTO `eligibility` ( "
							+ "  `customer_id`, " + "  `salesman_id` " + ")  "
							+ "VALUES " + "  ( " + "    ?, " + "    ? ) ;");
			prepareStatement.setInt(1, customerId);
			prepareStatement.setInt(2, salesmanId);
			prepareStatement.executeUpdate();
			id = (int) prepareStatement.getLastInsertID();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int[] insertApplianceInEligibility(int customerId, int vleId,
			String[] applianceName, String[] scheme, String[] advance,
			String[] monthly, String[] price, String[] isAdvance) {
		int[] id = new int[applianceName.length];
		int appliancePrice = 0;
		int installment = 0;
		int downPayment = 0;
		int isAdvanceDevice = 0;
		int term = 0;
		int i = 0;
		try(Connection connection = Connect.getConnection()) {
			for (int j = 0; j < applianceName.length; j++) {
				if (scheme[j] == null) {
					scheme[j] = scheme[j + 1];
				}
				downPayment = Integer.parseInt(advance[j]);
				installment = Integer.parseInt(monthly[j]);
				appliancePrice = Integer.parseInt(price[j]);
				isAdvanceDevice = Integer.parseInt(isAdvance[j]);
				
				term = Integer.parseInt(scheme[j]);
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `appliance` ( "
								+ " `appliance_name`, " + " `appliance_price` "
								+ " )  " + " VALUES " + " ( " + "?, " + "? "
								+ " ) ; ");
				prepareStatement.setString(1, applianceName[i]);
				prepareStatement.setInt(2, appliancePrice);
				prepareStatement.executeUpdate();
				id[i] = (int) prepareStatement.getLastInsertID();
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
				updateStatement.setInt(7, isAdvanceDevice);
				updateStatement.setInt(8, 0);
				updateStatement.executeUpdate();
				i++;
			}
			try {
				com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("UPDATE form_wizard SET form_wizard_step = 5, updated_date = NOW()  WHERE customer_id = ?");
				updateStatement.setInt(1, customerId);
				updateStatement.executeUpdate();
			} catch (SQLException ex) {
				logger.error("", ex);
				ex.printStackTrace();
			}
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return id;
	}

	public int[] insertGeneralDetails(int customerId,
			HashMap<String, String> map) {

		int[] ids = new int[map.size()];
		int i = 0;
		for (String key : map.keySet()) {
			try(Connection connection = Connect.getConnection()) {
				com.mysql.jdbc.PreparedStatement prepareStatement = (com.mysql.jdbc.PreparedStatement) connection
						.prepareStatement("INSERT INTO `customer_general_field_details` ( "
								+ "  `customer_id`, "
								+ "  `general_field_label`, "
								+ "  `general_field_value` "
								+ ")  "
								+ "VALUES "
								+ "  ( "
								+ "   ?, "
								+ "  ?, "
								+ "  ? ) ;");
				prepareStatement.setInt(1, customerId);
				prepareStatement.setString(2, key);
				prepareStatement.setString(3, map.get(key));
				prepareStatement.executeUpdate();
				ids[i] = (int) prepareStatement.getLastInsertID();
				i++;
				System.out.println("General Information Inserted, Last Ids : "
						+ prepareStatement.getLastInsertID());

			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}
		try(Connection connection = Connect.getConnection()) {
			com.mysql.jdbc.PreparedStatement updateStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("UPDATE form_wizard SET form_wizard_step = 1, updated_date = NOW()  WHERE customer_id = ?");
			updateStatement.setInt(1, customerId);
			updateStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return ids;
	}
	public static void main(){
		System.err.println(getLoan());
	}

}