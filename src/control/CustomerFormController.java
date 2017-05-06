package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerFormBAL;
import bal.CustomerBAL;
import bal.CustomerFormWizardBAL;
import bal.DistrictOfficerBAL;
import bal.SalesmanBAL;
import bean.CityBean;
import bean.SalaryBean;
import bean.SalesManBean;
import bean.UserBean;

@WebServlet("/CustomerFormController")
public class CustomerFormController extends HttpServlet {
	final static Logger logger = Logger.getLogger(CustomerFormController.class);
	private static final long serialVersionUID = 1L;

	public CustomerFormController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private CustomerFormWizardBAL customerFormWizardBAL;
	private CustomerFormBAL customerBAL;

	// private CustomerFormBAL CustomerFormBAL;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();
			UserBean bean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + bean.getUserName() + " , UserID : "
					+ bean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String action = request.getParameter("action");
			customerFormWizardBAL = new CustomerFormWizardBAL();
			customerBAL = new CustomerFormBAL();
			if (action.equals("getCustomerForm")) {
				out.print(getCustomerForm(request));
			}
			if (action.equals("insertCustomerInfo")) {
				out.print(insertCustomerInfo(request));
			}
			if (action.equals("insertCustomerIncomeSource")) {
				out.print(insertCustomerIncomeSource(request));
			}
			if (action.equals("insertIncomeSource")) {
				out.print(insertIncomeSource(request));
			}
			if (action.equals("insertCustomerEmployeeInfo")) {
				out.print(insertCustomerEmployeeInfo(request));
			}
			if (action.equals("insertCustomerBusinessInfo")) {
				out.print(insertCustomerBusinessInfo(request));
			}
			if (action.equals("insertCustomerExpense")) {
				out.print(insertCustomerExpense(request));
			}
			if (action.equals("insertMonthlyExpense")) {
				out.print(insertMonthlyExpense(request));
			}
			if (action.equals("insertCustomerLivestockAssets")) {
				out.print(insertCustomerLivestockAssets(request));
			}
			if (action.equals("insertLoan")) {
				out.print(insertLoan(request));
			}

			if (action.equals("insertAssets")) {
				out.print(insertAssets(request));
			}

			if (action.equals("getSchemeFrequencies")) {
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("data", CustomerFormBAL.getLoan());
				} catch (Exception e) {
					jobject.put("status", "error");
					jobject.put("message", e.getMessage());
				}
				out.print(jobject);
			}
			if (action.equals("insertGuarantor")) {
				out.print(insertGuarantor(request));
			}
			if (action.equals("insertVleGuarantor")) {
				JSONObject json = new JSONObject();
				try {
					int customerId = Integer.parseInt(request.getParameter("customerId"));
					int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
					int id = customerBAL.insertCustomerVleGaurantor(customerId, salesmanId);
					json.put("status", "ok");
					json.put("data", id);
				} catch (Exception e) {
					json.put("status", "error");
					json.put("message", e.getMessage());
				}
				out.print(json);
			}
			if (action.equals("getCnic")) {
				JSONObject json = new JSONObject();
				try {
					String cnic = request.getParameter("customerCnic");
					String str = CustomerBAL.getCnic(cnic);
					if (str.equals(cnic)) {
						json.put("status", "ok");
					} else {
						json.put("status", "not");
					}
				} catch (Exception e) {
					json.put("status", "error");
					json.put("message", e.getMessage());
				}
				out.print(json);
			}
			if (action.equals("insertAppliance")) {
				out.print(insertAppliance(request));
			}

			if (action.equals("getDOCities")) {
				String districtid = request.getParameter("districtid");
				JSONObject json = new JSONObject();
				if (districtid != null) {
					if (!districtid.isEmpty()) {
						ArrayList<CityBean> districtCities = DistrictOfficerBAL
								.getDistrictCities(Integer.parseInt(districtid));
						if (districtCities != null) {
							json.put("status", "ok");
							json.put("data", districtCities);
						} else {
							json.put("status", "error");
							json.put("message", "district cities is null");
						}
					} else {
						json.put("status", "error");
						json.put("message", "doid is empty");
					}
				} else {
					json.put("status", "error");
					json.put("message", "doid is null");
				}
				out.print(json);
			}
			if (action.equals("getSalaryRanges")) {
				JSONObject json = new JSONObject();
				ArrayList<SalaryBean> salary = CustomerBAL
						.Customer_salary_range();
				if (salary != null) {
					json.put("status", "ok");
					json.put("data", salary);
				} else {
					json.put("status", "error");
					json.put("message", "salary ranges is null");
				}
				out.print(json);
			}

			if (action.equals("getVLEs")) {
				JSONObject json = new JSONObject();
				try {
					ArrayList<SalesManBean> salesmen = SalesmanBAL
							.getSallesman(Integer.parseInt(request
									.getParameter("userId")));
					if (salesmen != null) {
						json.put("status", "ok");
						json.put("data", salesmen);
					} else {
						json.put("status", "error");
						json.put("message", "salesmen is null");
					}
				} catch (Exception e) {
					json.put("message", e.getStackTrace());
					e.printStackTrace();
				}
				out.print(json);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject insertAppliance(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			int vleId = Integer.parseInt(request.getParameter("vleId"));

			String[] applianceName = request
					.getParameterValues("data[appliance][]");
			String[] scheme = request.getParameterValues("data[schemes][]");
			String[] advance = request.getParameterValues("data[advance][]");
			String[] monthly = request.getParameterValues("data[monthly][]");
			String[] total = request.getParameterValues("data[total][]");
			String[] isAdvance = request.getParameterValues("data[isAdvance][]");
			System.err.println("isAdvance.........."+isAdvance);
			for (String string : scheme) {
				System.err.println(string);
			}

//			int[] id = customerBAL.insertApplianceInEligibility(customerId,
//					vleId, applianceName, scheme, advance, monthly, total, isAdvance);
			json.put("status", "ok");
//			json.put("data", id);
		} catch (Exception e) {
			try {
				json.put("status", "error");
				json.put("message", e.getStackTrace());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return json;
	}

	private JSONObject insertGuarantor(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			int customerId = Integer.parseInt(request
					.getParameter("customerId"));
			int guarantorType = Integer.parseInt(request
					.getParameter("guarantorType"));

			HashMap<String, String> map = new HashMap<>();
			map.put("customerId", customerId + "");
			map.put("guarantorType", guarantorType + "");

			map.put("name", request.getParameter("data[name]"));
			map.put("cnic", request.getParameter("data[cnic]"));
			map.put("cellNumber", request.getParameter("data[cellNumber]"));
			map.put("secondaryCellNumber",
					request.getParameter("data[secondaryCellNumber]"));
			map.put("relation", request.getParameter("data[relation]"));
			map.put("incomeSource", request.getParameter("data[incomeSource]"));
			map.put("guarantorProfession",
					request.getParameter("data[guarantorProfession]"));

			int id = customerBAL.insertCustomerGuarantorInfo(map, customerId,
					guarantorType);
			json.put("status", "ok");
			json.put("data", id);
		} catch (Exception e) {
			try {
				json.put("status", "error");
				json.put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;
	}

	private JSONObject insertAssets(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String[] types = request.getParameterValues("data[types][]");
			String[] amounts = request.getParameterValues("data[amounts][]");
			String customerId = request.getParameter("customerId");
			if (customerId != null && types != null && amounts != null) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < types.length; i++) {
					if (!types[i].isEmpty() && !amounts[i].isEmpty())
						map.put(types[i], amounts[i]);
				}
				json.put("data", customerBAL.insertAssets(
						Integer.parseInt(customerId), map));
				json.put("status", "ok");
			} else {
				json.put("status", "error");
				json.put("message", "customer id or assets is null");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject insertMonthlyExpense(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerId = request.getParameter("customerId");
		HashMap<String, String> map = new HashMap<>();
		try {
			if (customerId != null) {
				if (!customerId.isEmpty()) {
					String[] types = request
							.getParameterValues("data[monthlyExpenseType][]");
					String[] amounts = request
							.getParameterValues("data[monthlyExpenseAmount][]");
					if (types != null && amounts != null) {
						for (int i = 0; i < types.length; i++) {
							map.put(types[i], amounts[i]);
						}
						json.put("status", "ok");
						json.put(
								"data",
								customerBAL.insertMonthlyExpense(
										Integer.parseInt(customerId), map));
					}
				} else {
					json.put("status", "error");
					json.put("message", "customer id is empty");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id is null");
			}
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
			try {
				json.put("status", "error");
				json.put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;
	}

	private JSONObject insertIncomeSource(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerId = request.getParameter("data[customerId]");
		System.err.println("customerId called " + customerId);
		try {
			if (customerId != null) {
				if (!customerId.isEmpty()) {
					HashMap<String, String> incomeSourceMap = new HashMap<>();
					HashMap<String, String> incomemmothlyYearly = new HashMap<>();
					incomeSourceMap.put("customerId", customerId);
					incomeSourceMap.put("salaryOrPension",
							request.getParameter("data[salaryOrPension]"));
					incomeSourceMap.put("businessIncome",
							request.getParameter("data[businessIncome]"));
					incomeSourceMap.put("farmingIncome",
							request.getParameter("data[farmingIncome]"));
					incomeSourceMap
							.put("familyContribution",
									request.getParameter("data[customer_contributers_amount]"));
					incomeSourceMap
							.put("customer_income_comtributers",
									request.getParameter("data[customer_income_comtributers]"));

					incomemmothlyYearly.put("customerId", customerId);
					incomemmothlyYearly
							.put("customer_total_income_name",
									request.getParameter("data[customer_total_income_name]"));
					incomemmothlyYearly
							.put("customer_total_irregularly_income_name",
									request.getParameter("data[customer_total_irregularly_income_name]"));
					incomemmothlyYearly
							.put("agricultureProfitFrequency",
									request.getParameter("data[agricultureProfitFrequency]"));
					incomemmothlyYearly.put("customerLivestockIncome", request
							.getParameter("data[customerLivestockIncome]"));
					incomemmothlyYearly
							.put("customerLivestockType",
									request.getParameter("data[customerLivestockType]"));
					incomemmothlyYearly
							.put("customerLivestockOthertype",
									request.getParameter("data[customerLivestockOthertype]"));
					incomemmothlyYearly.put("customerRemittances",
							request.getParameter("data[customerRemittances]"));
					incomemmothlyYearly
							.put("customerRemittancesFrequency",
									request.getParameter("data[customerRemittancesFrequency]"));
					incomemmothlyYearly
							.put("customerRemittancesRelation",
									request.getParameter("data[customerRemittancesRelation]"));
					incomemmothlyYearly
							.put("customer_remittances_profession",
									request.getParameter("data[customer_remittances_profession]"));
					incomemmothlyYearly
							.put("customer_rental_profit_income",
									request.getParameter("data[customer_rental_profit_income]"));
					incomemmothlyYearly
							.put("customer_rental_frequency",
									request.getParameter("data[customer_rental_frequency]"));
					incomemmothlyYearly.put("customer_rental_from",
							request.getParameter("data[customer_rental_from]"));
					incomemmothlyYearly.put("customer_labour_amount", request
							.getParameter("data[customer_labour_amount]"));
					incomemmothlyYearly.put("customer_labour_type",
							request.getParameter("data[customer_labour_type]"));
					incomemmothlyYearly.put("customer_labour_in_month", request
							.getParameter("data[customer_labour_in_month]"));
					incomemmothlyYearly
							.put("customer_other_labour_type",
									request.getParameter("data[customer_other_labour_type]"));
					System.err
							.println("data[customer_other_labour_type]: "
									+ request
											.getParameter("data[customer_other_labour_type]"));

					customerBAL.updateCustomerIncomeSource(incomeSourceMap);
					System.err
							.println("inserMonthlyYearlyIncome called from Controller");
					customerBAL.inserMonthlyYearlyIncome(incomemmothlyYearly);
					HashMap<String, String> otherIncomeSource = new HashMap<>();
					String[] types = request
							.getParameterValues("data[otherIncomeSourcesType][]");
					String[] amounts = request
							.getParameterValues("data[otherIncomeSourcesAmount][]");
					int i = 0;
					if (types != null) {
						for (String type : types) {
							otherIncomeSource.put(type, amounts[i]);
							i++;
						}
						json.put("otherIncomeSourceIds", customerBAL
								.insertOtherIncomeResource(
										Integer.parseInt(customerId),
										otherIncomeSource));
					}
					json.put("status", "ok");

				} else {
					json.put("status", "error");
					json.put("message", "customer id is empty");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id is null");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			try {
				json.put("status", "error");
				json.put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;
	}

	private JSONObject getCustomerForm(HttpServletRequest request) {
		String customerIdString = request.getParameter("customerId");
		JSONObject json = new JSONObject();
		if (customerIdString != null) {
			if (!customerIdString.isEmpty()) {
				HashMap<String, Object> customerForm = customerFormWizardBAL
						.getCustomerForm(Integer.parseInt(customerIdString));
				try {
					json.put("customerForm", new JSONObject(customerForm));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("CustomerFormController.doPost()");
			System.err.println("Customer Id Null or Empty : "
					+ customerIdString);
		}
		return json;

	}

	private JSONObject insertCustomerInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		HashMap<String, String> map = new HashMap<>();

		try {
			map.put("customerIdd", request.getParameter("customerId"));
			map.put("applianceId", request.getParameter("applianceId"));
			
			map.put("education_medical_amount", request
					.getParameter("education_medical_amount") == null ? "0"
					: request.getParameter("education_medical_amount"));
			map.put("city", request.getParameter("city") == null ? "" : request
							.getParameter("city"));
			map.put("province", request.getParameter("province") == "" ? ""
					: request.getParameter("province"));
			System.err.println("province: " + request.getParameter("province"));
			// map.put("customer_village_address",
			// request.getParameter("customer_village_address") == null ? "" :
			// request.getParameter("customer_village_address"));
			map.put("address", request.getParameter("address") == null ? ""
					: request.getParameter("address"));
			map.put("customer_union_council", request
					.getParameter("customer_union_council") == null ? ""
					: request.getParameter("customer_union_council"));
			map.put("requesteddate",
					request.getParameter("requesteddate") == null ? ""
							: request.getParameter("requesteddate"));
			map.put("vle", request.getParameter("vle") == null ? "0" : request
							.getParameter("vle"));
			map.put("customer_sys_installed_place",
					request.getParameter("customer_sys_installed_place"));
			map.put("customer_installment_address",
					request.getParameter("customer_installment_address"));

			map.put("fullname", request.getParameter("fullname") == null ? ""
					: request.getParameter("fullname"));
			map.put("fatherName", request.getParameter("fatherName") == null ? "" : request
							.getParameter("fatherName"));
			map.put("gender", request.getParameter("gender") == null ? ""
					: request.getParameter("gender"));
			map.put("dateOfBirth", request.getParameter("dateOfBirth") == null ? "" : request
							.getParameter("dateOfBirth"));
			map.put("customerCaste", request.getParameter("customerCaste") == null ? ""
							: request.getParameter("customerCaste"));
			map.put("cnic", request.getParameter("cnic") == null ? "" : request
							.getParameter("cnic"));
			map.put("primaryPhone",
					request.getParameter("primaryPhone") == null ? "" : request
							.getParameter("primaryPhone"));
			map.put("secondaryPhone", request.getParameter("secondaryPhone") == null ? ""
							: request.getParameter("secondaryPhone"));
			// map.put("email", request.getParameter("email") == null ? "" :
			// request.getParameter("email"));
			map.put("maritalStatus", request.getParameter("maritalStatus") == null
							&& !request.getParameter("maritalStatus").equals(
									"on") ? "" : request
							.getParameter("maritalStatus"));
			map.put("education", request.getParameter("education") == null ? ""
					: request.getParameter("education"));
			map.put("customer_disibility", request.getParameter("customer_disibility") == null ? ""
							: request.getParameter("customer_disibility"));
			map.put("customer_training_skill", request
					.getParameter("customer_training_skill") == null ? ""
					: request.getParameter("customer_training_skill"));
			map.put("customer_course_name", request
					.getParameter("customer_course_name") == null ? ""
					: request.getParameter("customer_course_name"));
			map.put("customer_head_faimly", request
					.getParameter("customer_head_faimly") == null ? ""
					: request.getParameter("customer_head_faimly"));
			map.put("customer_total_household_members",
					request.getParameter("customer_total_household_members") == null ? ""
							: request .getParameter("customer_total_household_members"));
			map.put("customer_dependants_members", request
					.getParameter("customer_dependants_members") == null ? ""
					: request.getParameter("customer_dependants_members"));
			map.put("customer_children",
					request.getParameter("customer_children") == null ? ""
							: request.getParameter("customer_children"));
			map.put("customer_education_amount", request
					.getParameter("customer_education_amount") == null ? ""
					: request.getParameter("customer_education_amount"));
			map.put("customer_total_adult_members", request
					.getParameter("customer_total_adult_members") == null ? ""
					: request.getParameter("customer_total_adult_members"));
			map.put("customer_medical_recuring", request
					.getParameter("customer_medical_recuring") == null ? ""
					: request.getParameter("customer_medical_recuring"));
			map.put("customer_medical_recuring_amount",
					request.getParameter("customer_medical_recuring_amount") == null ? ""
							: request.getParameter("customer_medical_recuring_amount"));
			map.put("customer_families_type", request
					.getParameter("customer_families_type") == null ? ""
					: request.getParameter("customer_families_type"));

			map.put("customer_families_household", request
					.getParameter("customer_families_household") == null ? ""
					: request.getParameter("customer_families_household"));
			map.put("customer_roomoccupied_household",
					request.getParameter("customer_roomoccupied_household") == null ? ""
							: request .getParameter("customer_roomoccupied_household"));
			map.put("customer_residence",
					request.getParameter("customer_residence") == null ? ""
							: request.getParameter("customer_residence"));
			map.put("customer_other_residence", request
					.getParameter("customer_other_residence") == null ? ""
					: request.getParameter("customer_other_residence"));
			map.put("customer_residence_period", request
					.getParameter("customer_residence_period") == null ? ""
					: request.getParameter("customer_residence_period"));

			
			System.err.println("getPar:"+request.getParameter("customerId")+":///getMap:"+map.get("customerIdd")+":");
			
			int customerId = customerBAL.insertCustomer(map);
			if (customerId > 0 || Integer.parseInt(request.getParameter("customerId")) > 0) {
				customerBAL .insertEducationMedicalAmount(
							customerId, (map.get("education_medical_amount") == null || map
							.get("education_medical_amount") == "") ? Double.parseDouble("0")
							: Double.parseDouble(map.get("education_medical_amount")));

				ArrayList<HashMap<String, String>> list = new ArrayList<>();

				String[] whoIsOther = request
						.getParameterValues("data[who_is_other][]");
				String[] relationCustomer = request
						.getParameterValues("data[relation_with_customer][]");
				String[] otherPhone = request
						.getParameterValues("data[customer_other_phone][]");

				if (whoIsOther != null) {
					for (int k = 0; k < whoIsOther.length; k++) {
						System.err.println("whoIsOther  " + k);
						HashMap<String, String> mapgeneral = new HashMap<>();
						mapgeneral.put("whoIsOther",
								whoIsOther[k].isEmpty() ? "" : whoIsOther[k]);
						mapgeneral.put("relationCustomer", relationCustomer[k]
								.isEmpty() ? "" : relationCustomer[k]);
						mapgeneral.put("otherPhone",
								otherPhone[k].isEmpty() ? "" : otherPhone[k]);
						list.add(mapgeneral);
					}
					json.put("otherPhoneIds", customerBAL
							.insertOtherPhoneDetails(customerId, list));
				}
			}
			ArrayList<HashMap<String, String>> listgenfields = new ArrayList<>();
			HashMap<String, String> generalTextMap = new HashMap<>();
			HashMap<String, String> generalDigitMap = new HashMap<>();

			String[] generalTextFieldTypes = request
					.getParameterValues("data[general_field_in_text_foramte_type][]");
			String[] generalTextField = request
					.getParameterValues("data[general_field_in_text_foramte][]");

			String[] generalDigitFieldTypes = request
					.getParameterValues("data[general_field_in_digit_foramte_type][]");
			String[] generalDigitField = request
					.getParameterValues("data[general_field_in_digit_foramte][]");

			int ii = 0;
			if (generalTextFieldTypes != null) {
				for (String type : generalTextFieldTypes) {
					generalTextMap.put(type, generalTextField[ii]);
					// listgenfields.add(generalTextMap);
					ii++;
				}
				json.put("generalTextFieldIds", customerBAL
						.insertGeneralDetails(customerId, generalTextMap));
			}

			int jj = 0;
			if (generalDigitFieldTypes != null) {
				for (String type : generalDigitFieldTypes) {
					generalDigitMap.put(type, generalDigitField[jj]);
					// listgenfields.add(generalDigitMap);
					jj++;
				}
				json.put("generalDigitFieldIds", customerBAL
						.insertGeneralDetails(customerId, generalDigitMap));
			}

			json.put("status", "ok");
			json.put("data", customerId);
			// json.put("eligibilityId", eligibilityId);
		} catch (Exception e) {
			try {
				json.put("status", "error");
				json.put("message", e.getStackTrace());
				e.printStackTrace();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;

	}

	private JSONObject insertCustomerIncomeSource(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("salaryOrPension",
							request.getParameter("salaryOrPension"));
					map.put("businessIncome",
							request.getParameter("businessIncome"));
					map.put("farming", request.getParameter("farmingIncome"));
					map.put("familyContribution",
							request.getParameter("familyContribution"));
					map.put("incomeSource",
							request.getParameter("incomeSource"));
					int status = customerBAL.updateCustomerIncomeSource(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("data", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("data", "customer id not declared");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject insertCustomerExpense(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();

					map.put("customerId", customerIdString);
					map.put("customer_total_expense_income", request
							.getParameter("customer_total_expense_income"));
					map.put("customer_electricity_type",
							request.getParameter("customer_Connectivity"));
					map.put("customer_sourceof_electricity", request
							.getParameter("customer_sourceof_electricity"));
					map.put("customer_electricity_amount", request
							.getParameter("customer_primary_sourse_amount"));
					map.put("customer_mobile_type",
							request.getParameter("customer_mobile_type"));
					map.put("customer_mobilebills_amount",
							request.getParameter("customer_mobilebills_amount"));
					map.put("mobilink_network",
							request.getParameter("mobilink_network"));
					map.put("ufone_network",
							request.getParameter("ufone_network"));
					map.put("zong_network",
							request.getParameter("zong_network"));
					map.put("warid_network",
							request.getParameter("warid_network"));
					map.put("telenor_network",
							request.getParameter("telenor_network"));
					map.put("customer_transport_mode",
							request.getParameter("customer_transport_mode"));
					map.put("customer_transport_amount",
							request.getParameter("customer_transport_amount"));
					map.put("customer_karyana",
							request.getParameter("customer_karyana"));
					map.put("customer_karyana_amount",
							request.getParameter("customer_karyana_amount"));
					// map.put("customer_estimated_amount",
					// request.getParameter("customer_estimated_amount"));

					int status = customerBAL.insertCustomerExpense(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("data", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("data", "customer id not declared");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject insertCustomerLivestockAssets(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				System.err.println("customerTotalAssetsIncome: "
						+ request.getParameter("customerTotalAssetsIncome"));
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("customerTotalAssetsIncome",
							request.getParameter("customerTotalAssetsIncome"));
					map.put("customer_land",
							request.getParameter("customer_land"));
					map.put("customer_bbuffalo",
							request.getParameter("customer_bbuffalo"));
					map.put("customer_cow",
							request.getParameter("customer_cow"));
					map.put("customer_calf",
							request.getParameter("customer_calf"));
					map.put("customer_goat_sheep",
							request.getParameter("customer_goat_sheep"));
					map.put("customer_bike",
							request.getParameter("customer_bike"));
					map.put("customer_car",
							request.getParameter("customer_car"));
					map.put("customer_tractors",
							request.getParameter("customer_tractors"));
					map.put("customer_tv", request.getParameter("customer_tv"));
					map.put("customer_fridge",
							request.getParameter("customer_fridge"));

					map.put("customer_land_amount",
							request.getParameter("customer_land_amount"));
					map.put("customer_buffalo_amount",
							request.getParameter("customer_buffalo_amount"));
					map.put("customer_cow_amount",
							request.getParameter("customer_cow_amount"));
					map.put("customer_calf_amount",
							request.getParameter("customer_calf_amount"));
					map.put("customer_goat_Sheep_amount",
							request.getParameter("customer_goat_Sheep_amount"));
					map.put("customer_bike_amount",
							request.getParameter("customer_bike_amount"));
					map.put("customer_car_amount",
							request.getParameter("customer_car_amount"));
					map.put("customer_tractors_amount",
							request.getParameter("customer_tractors_amount"));
					map.put("customer_tv_amount",
							request.getParameter("customer_tv_amount"));
					map.put("customer_fridge_amount",
							request.getParameter("customer_fridge_amount"));

					int status = customerBAL.insertCustomerLivestockAssets(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("data", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("data", "customer id not declared");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject insertCustomerEmployeeInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					// String phone = request.getParameter("officePhoneNo");
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("companyTitle",
							request.getParameter("companyTitle"));
					map.put("designation", request.getParameter("designation"));
					map.put("jobPeriod", request.getParameter("jobPeriod"));
					/*
					 * if(phone.equals("null") || phone == null || phone == ""){
					 * map.put("officePhoneNo", phone); }else {
					 * map.put("officePhoneNo", "92"+phone); }
					 */
					// map.put("supervisorName",
					// request.getParameter("supervisorName"));
					// map.put("address", request.getParameter("address"));
					int status = customerBAL.insertCustomerEmployementInfo(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("message", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id not declared");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject insertCustomerBusinessInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					// String phone = request.getParameter("data[phoneNo]");
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("title", request.getParameter("data[title]"));
					map.put("type", request.getParameter("data[type]"));
					map.put("timePeriod",
							request.getParameter("data[timePeriod]"));
					/*
					 * if (phone.equals("null") || phone == null || phone == "")
					 * { map.put("phoneNo", phone); } else { map.put("phoneNo",
					 * "92"+phone); }
					 */
					// map.put("address",
					// request.getParameter("data[address]"));
					// map.put("comment",
					// request.getParameter("data[comment]"));
					int status = customerBAL.insertCustomerBusinessInfo(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("message", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id not declared");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;

	}

	private JSONObject insertLoan(HttpServletRequest request) {

		String customerId = request.getParameter("customerId");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		JSONObject json = new JSONObject();
		if (customerId != null) {
			if (!customerId.isEmpty()) {
				try {
					System.err.println("customerId ::  " + customerId);
					String[] names = request
							.getParameterValues("data[bankOrOrgName][]");
					String[] types = request
							.getParameterValues("data[types][]");
					String[] amounts = request
							.getParameterValues("data[totalAmount][]");
					String[] installments = request
							.getParameterValues("data[monthlyInstallment][]");
					String[] remainingAmounts = request
							.getParameterValues("data[remainingAmount][]");

					String[] customerPaymentPlanInMonth = request
							.getParameterValues("data[customer_payment_plan_in_month][]");
					String[] customerFrequencyInMonth = request
							.getParameterValues("data[customer_frequency_in_month][]");

					for (int i = 0; i < names.length; i++) {
						HashMap<String, String> map = new HashMap<>();
						map.put("donner", names[i].isEmpty() ? "" : names[i]);
						map.put("type", types[i].isEmpty() ? "0" : types[i]);
						map.put("totalAmount", amounts[i].isEmpty() ? "0"
								: amounts[i]);
						map.put("monthlyInstallment",
								installments[i].isEmpty() ? "0"
										: installments[i]);
						map.put("remainingAmount", remainingAmounts[i]
								.isEmpty() ? "0" : remainingAmounts[i]);
						map.put("customerPaymentPlanInMonth",
								customerPaymentPlanInMonth[i] == "" ? "0"
										: customerPaymentPlanInMonth[i]);
						map.put("customerFrequencyInMonth",
								customerFrequencyInMonth[i] == "" ? "0"
										: customerFrequencyInMonth[i]);
						list.add(map);
					}
					System.err.println("out side the loop");
					json.put("data", customerBAL.insertLoan(
							Integer.parseInt(customerId), list));
					json.put("status", "ok");
				} catch (Exception e) {
					try {
						json.put("status", "error");
						json.put("message", e.getMessage());
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					json.put("status", "error");
					json.put("message", "customer id is empty");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				json.put("status", "error");
				json.put("message", "customer id is null");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
}