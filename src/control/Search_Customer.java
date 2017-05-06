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

import org.json.JSONArray;
import org.json.JSONObject;

import bal.ApplianceBAL;
import bal.CustomerBAL;
import bean.ApplianceNameById;
import bean.BusinessDetails;
import bean.CustomerBean;
import bean.CustomerChild;
import bean.CustomerGuardian;
import bean.EligibilityExistCustomer;
import bean.EmploymentDetails;
import bean.GuarantorBean;
import bean.HouseHoldAssets;
import bean.MonthlyExpenses;
import bean.OtherLoanDetails;
import bean.Salesman;
import bean.UserBean;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

@WebServlet("/SearchCustomer")
public class Search_Customer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Search_Customer.class);

	public Search_Customer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String gsm = request.getParameter("searchelement");

			CustomerBean customerList = CustomerBAL.getCustomerDetailByNumber("92" + gsm);
			if (customerList != null) {
				String tele = customerList.getPhoneNo();
				String ptele = tele.substring(2, tele.length());
				String stele = customerList.getPhoneNo2().substring(2, customerList.getPhoneNo2().length());

				CustomerGuardian customerguarian = CustomerBAL.getCustomerGuardian(customerList.getCustomerId());
				String gardiantele = customerguarian.getPhone_no().substring(2, customerguarian.getPhone_no().length());
				ArrayList<EligibilityExistCustomer> applist = ApplianceBAL
						.getApplianceByCustomerId(customerList.getCustomerId());
				System.err.println("Appliance: //////////////////// " + applist);
				HouseHoldAssets householdlist = CustomerBAL.getHouseAssetsById(customerList.getCustomerId());
				System.err.println("householdlist: //////////////////// " + householdlist);
				BusinessDetails businessdetaillist = CustomerBAL.getCustomerBusiness(customerList.getCustomerId());
				System.err.println("businessdetaillist: //////////////////// " + businessdetaillist);
				EmploymentDetails employdetail = CustomerBAL.getEmployDetailById(customerList.getCustomerId());
				System.err.println("employdetail: //////////////////// " + employdetail);
				OtherLoanDetails otherloandetail = CustomerBAL.getOtherLoanDeatailById(customerList.getCustomerId());
				System.err.println("otherloandetail: //////////////////// " + otherloandetail);
				MonthlyExpenses monthlyExpenses = CustomerBAL.getMonthlyExpenseById(customerList.getCustomerId());
				System.err.println("monthlyExpenses: //////////////////// " + monthlyExpenses);
				GuarantorBean fguarantorBean1 = CustomerBAL.getGuaranterDetailById(customerList.getCustomerId(), 1);
				System.err.println("fguarantorBean1: //////////////////// " + fguarantorBean1);
				String guarantorTele = fguarantorBean1.getGguarantorPhone().substring(2,
						fguarantorBean1.getGguarantorPhone().length());
				System.err.println("guarantorTele:: > 1 //////////////////// " + guarantorTele);

				GuarantorBean oguarantorBean2 = CustomerBAL.getGuaranterDetailById(customerList.getCustomerId(), 2);
				System.err.println("oguarantorBean2: //////////////////// " + oguarantorBean2);
				String guarantor2Tele = oguarantorBean2.getGguarantorPhone().substring(2,
						oguarantorBean2.getGguarantorPhone().length());
				System.err.println("guarantor2Tele:: > 2 //////////////////// " + guarantor2Tele);
				ArrayList<CustomerChild> childlist = CustomerBAL.getCustomerChildById(customerList.getCustomerId());
				System.err.println("childlist: //////////////////// " + childlist);

				JSONArray SArray = new JSONArray();
				if (customerList != null && applist != null && childlist != null) {
					JSONObject customerJSON = new JSONObject();

					customerJSON.put("exeid", customerList.getCustomerId());

					customerJSON.put("name", customerList.getCustomerName());
					customerJSON.put("fathername", customerList.getFatherName());
					customerJSON.put("mothername", customerList.getMotherName());
					customerJSON.put("gender", customerList.getGender());
					customerJSON.put("gsm", ptele);
					customerJSON.put("gsm2", stele);
					customerJSON.put("email", customerList.getEmail());
					customerJSON.put("relationstatus", customerList.getRelationStatus());
					customerJSON.put("educated ", customerList.isEducated());
					customerJSON.put("education", customerList.getEducation());

					customerJSON.put("status", customerList.getStatus());
					customerJSON.put("mincome", customerList.getMonthlyIncome());
					customerJSON.put("cnic", customerList.getCnicNo());
					customerJSON.put("dob", customerList.getDateOfBirth());
					customerJSON.put("adress", customerList.getAddress());
					customerJSON.put("city", customerList.getDistrict());

					customerJSON.put("date", customerguarian.getCustomerId());
					customerJSON.put("guardian", customerguarian.getGuardianName());
					customerJSON.put("guardiancell", gardiantele);
					customerJSON.put("guardianchild", customerguarian.getFamilyMember());

					customerJSON.put("isLoanTaken", otherloandetail.isOtherLoan());
					customerJSON.put("donnerName", otherloandetail.getLoanDonner());
					customerJSON.put("loanPayment", otherloandetail.getLoanAmount());
					customerJSON.put("loanperiod", otherloandetail.getLoanPeriod());
					customerJSON.put("installment", otherloandetail.getMonthlyInstallment());
					customerJSON.put("remaining", otherloandetail.getRemainingPayment());

					customerJSON.put("isBankAccount", otherloandetail.isBankAccount());
					customerJSON.put("bankName", otherloandetail.getBankName());

					customerJSON.put("ups", monthlyExpenses.getUps());
					customerJSON.put("electricity", monthlyExpenses.getElectricity());
					customerJSON.put("generator", monthlyExpenses.getGenerator());
					customerJSON.put("solar", monthlyExpenses.getSolar());
					customerJSON.put("otherelusage", monthlyExpenses.getElectricityusage());

					customerJSON.put("electricityusage", monthlyExpenses.getElectricityusage());
					customerJSON.put("electricityexpense", monthlyExpenses.getElectricityexpense());
					customerJSON.put("majorexpensedescription", monthlyExpenses.getMajorexpensedescription());
					customerJSON.put("majorexpenseamount", monthlyExpenses.getMajorexpenseamount());
					customerJSON.put("totalexpense", monthlyExpenses.getTotalexpense());

					customerJSON.put("income", customerList.getMonthlyIncome());
					customerJSON.put("fincome", customerList.getFamilyIncome());
					customerJSON.put("source", customerList.getSourceOfIncome());

					customerJSON.put("other", householdlist.getOtherAssets());
					// System.err.println(householdlist.getOtherAssets()+"-------=-=-=-=-=");
					customerJSON.put("home", householdlist.isHasHome());
					customerJSON.put("car", householdlist.isHasCar());
					customerJSON.put("bike", householdlist.isHasBike());
					customerJSON.put("washingMachine", householdlist.isHasWashingMachine());
					customerJSON.put("fridge", householdlist.isHasFridge());
					customerJSON.put("tv", householdlist.isHasTv());

					// Family Guarantor
					customerJSON.put("g1guarantorName", fguarantorBean1.getGguarantorName());
					customerJSON.put("g1guarantorFather", fguarantorBean1.getGguarantorFather());
					customerJSON.put("g1guarantorDob", fguarantorBean1.getGguarantorDob());
					customerJSON.put("g1guarantorCnic", fguarantorBean1.getGguarantorCnic());
					customerJSON.put("g1RelationToCustomer", fguarantorBean1.getgRelationToCustomer());
					customerJSON.put("g1marritalStatus", fguarantorBean1.getGmarritalStatus());
					customerJSON.put("g1guarantorPhone", guarantorTele);
					customerJSON.put("g1guarantorIncome", fguarantorBean1.getGguarantorIncome());

					customerJSON.put("g1CompanyName", fguarantorBean1.getgCompanyName());
					customerJSON.put("g1Desidition", fguarantorBean1.getgDesidition());
					customerJSON.put("g1PhoneNumber",
							fguarantorBean1.getgPhoneNumber().substring(2, fguarantorBean1.getgPhoneNumber().length()));
					customerJSON.put("g1CompanyAddess", fguarantorBean1.getgCompanyAddess());
					customerJSON.put("g1BusinessName", fguarantorBean1.getgBusinessName());
					customerJSON.put("g1BusnessType", fguarantorBean1.getgBusnessType());
					customerJSON.put("g1BusnessNumber", fguarantorBean1.getgBusnessNumber());
					customerJSON.put("g1BusinessAddress", fguarantorBean1.getgBusinessAddress());

					// outerside Guarantor
					customerJSON.put("g2guarantorName", oguarantorBean2.getGguarantorName());
					customerJSON.put("g2guarantorFather", oguarantorBean2.getGguarantorFather());
					customerJSON.put("g2guarantorDob", oguarantorBean2.getGguarantorDob());
					customerJSON.put("g2guarantorCnic", oguarantorBean2.getGguarantorCnic());
					customerJSON.put("g2RelationToCustomer", oguarantorBean2.getgRelationToCustomer());
					customerJSON.put("g2marritalStatus", oguarantorBean2.getGmarritalStatus());
					customerJSON.put("g2guarantorPhone", guarantor2Tele);
					customerJSON.put("g2guarantorIncome", oguarantorBean2.getGguarantorIncome());

					customerJSON.put("g2CompanyName", oguarantorBean2.getgCompanyName());
					customerJSON.put("g2Designation", oguarantorBean2.getgDesidition());
					customerJSON.put("g2PhoneNumber",
							oguarantorBean2.getgPhoneNumber().substring(2, oguarantorBean2.getgPhoneNumber().length()));
					customerJSON.put("g2CompanyAddress", oguarantorBean2.getgCompanyAddess());
					customerJSON.put("g2BusinessName", oguarantorBean2.getgBusinessName());
					customerJSON.put("g2BusinessType", oguarantorBean2.getgBusnessType());
					customerJSON.put("g2BusinessNumber", oguarantorBean2.getgBusnessNumber());
					customerJSON.put("g2BusinessAddress", oguarantorBean2.getgBusinessAddress());

					// Income Details
					customerJSON.put("businessName", businessdetaillist.getBusinessName());
					customerJSON.put("businessType", businessdetaillist.getBusinessType());
					customerJSON.put("owner", businessdetaillist.getOwnedOrPartner());
					customerJSON.put("businessTime", businessdetaillist.getPeriod());
					customerJSON.put("worker", businessdetaillist.getWorkers());
					customerJSON.put("businessPlace", businessdetaillist.getBusinessPlace());
					customerJSON.put("businessPhone", businessdetaillist.getOrgPhone());

					customerJSON.put("orgName", employdetail.getOrganisationName());
					customerJSON.put("position", employdetail.getJobPosition());
					customerJSON.put("period", employdetail.getJobPeriod());
					customerJSON.put("orgPhone", employdetail.getPhoneNo());
					customerJSON.put("salary", employdetail.getSalary());
					customerJSON.put("orgAddress", employdetail.getOrgAddress());

					if (childlist != null) {
						customerJSON.put("childlist", childlist);
					}

					customerJSON.put("applist", applist);
					customerJSON.put("getAppliances",
							ApplianceBAL.getApplianceByCustomerId(customerList.getCustomerId()));

					customerJSON.put("exe_acceptedapp",
							ApplianceBAL.getExeAcceptedApplianceByCustomerId(customerList.getCustomerId()));
					customerJSON.put("exe_rejectedapp",
							ApplianceBAL.getExeRejectApplianceByCustomerId(customerList.getCustomerId()));
					customerJSON.put("exe_notintrestedapp",
							ApplianceBAL.getSuspendedCustomer(customerList.getCustomerId()));
					SArray.put(customerJSON);

				}

				out.println(SArray);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

}