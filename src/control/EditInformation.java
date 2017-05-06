package control;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.itextpdf.text.log.SysoLogger;

import bal.CustomerBAL;
import bean.UserBean;

/**
 * Servlet implementation class EditInformation
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditInformation" })
public class EditInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(EditInformation.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();

		int customrId = Integer.parseInt(request.getParameter("customrId"));
		String action = request.getParameter("action");
		if (action.equals("update")) {

			String gaurdianName = request.getParameter("gardianName");
			String gaurdianPhone = request.getParameter("gardianPhone").trim();

			HashMap<String, Object> family = new HashMap<>();
			family.put("customrId", customrId);
			family.put("gaurdianName", gaurdianName);
			family.put("gaurdianPhone", "92" + gaurdianPhone);

			CustomerBAL.updateCustomerGuardian(family);

		} else if (action.equalsIgnoreCase("saveFam")) {
			System.out.println("Save Family");
		}

		else if (action.equals("updatecustomers")) {
			HashMap<String, Object> profile = new HashMap<>();

			String customerName = request.getParameter("customerName");

			String customerCnic = request.getParameter("customerCnic");
			String customerAddress = request.getParameter("customerAddress");

			String customerPrimaryNumber = request.getParameter("customerPrimaryNumber");

			String customerSecondaryNumber = request.getParameter("customerSecondaryNumber");

			String customerGender = request.getParameter("cusotmerGender");
			int customerDob = Integer.parseInt(request.getParameter("customerDob"));
			String customerFather = request.getParameter("customerFather");
			String customerAge = request.getParameter("customerAge");
			String customerFamily = request.getParameter("customerFamily");
			String generalOne = request.getParameter("generalOne");
			String generalTwo = request.getParameter("generalTwo");
			String generalThree = request.getParameter("generalThree");
			String generalFoure = request.getParameter("generalFoure");
			String generalFive = request.getParameter("generalFive");
			profile.put("customrId", customrId);
			profile.put("customerName", customerName);
			profile.put("customerCnic", customerCnic);

			profile.put("customerAddress", customerAddress);
			profile.put("customerPrimaryNumber", "92" + customerPrimaryNumber);
			profile.put("customerSecondaryNumber", customerSecondaryNumber);
			profile.put("customrMonthlyIncome", 2);
			profile.put("customrFamilyIncom", 3);
			profile.put("cusotmerGender", customerGender);
			profile.put("educationId", customerDob);
			profile.put("customerFather", customerFather);
			profile.put("relationStatus", customerAge);
			profile.put("familySize", customerFamily);
			profile.put("generalOne", generalOne);
			profile.put("generalTwo", generalTwo);
			profile.put("generalThree", generalThree);
			profile.put("generalFoure", generalFoure);
			profile.put("generalFive", generalFive);
			boolean status = CustomerBAL.updateProfile(profile);
			try {
				json.put("data", status);
			} catch (JSONException e) {

				e.printStackTrace();
			}

			out.print(json);
		} else if (action.equals("saveGaurantor")) {

			HashMap<String, String> familyguarantor = new HashMap<>();

			String famGaurantorName = request.getParameter("famGaurantorName");
			String famGaurantorFather = request.getParameter("famGaurantorFather");
			String famGaurantorPhone = request.getParameter("famGaurantorPhone");

			String famGaurantorCnic = request.getParameter("famGaurantorCnic");
			String famGaurantorRelation = request.getParameter("famGaurantorRelation");
			String famGaurantorId = request.getParameter("famGaurantorId");
			String famGaurantorIncome = request.getParameter("famGaurantorIncome");
			familyguarantor.put("customrId", "" + customrId);
			familyguarantor.put("famGaurantorName", famGaurantorName);
			familyguarantor.put("famGaurantorFather", famGaurantorFather);
			familyguarantor.put("famGaurantorPhone", "92" + famGaurantorPhone);
			familyguarantor.put("famGaurantorCnic", famGaurantorCnic);
			familyguarantor.put("famGaurantorRelation", famGaurantorRelation);
			familyguarantor.put("famGaurantorIncome", famGaurantorIncome);
			familyguarantor.put("famGaurantorId", famGaurantorId);
			boolean status = CustomerBAL.updateCustomerGuarantorOne(familyguarantor);
			try {
				json.put("data", status);
				out.print(json);
			} catch (JSONException e) {

				e.printStackTrace();
			}

			HashMap<String, String> outsiderguarantor = new HashMap<>();

			String outGaurantorName = request.getParameter("outGaurantorName");
			String outGaurantorFather = request.getParameter("outGaurantorFather");
			String outGaurantorPhone = request.getParameter("outGaurantorPhone");
			String outGaurantorIncome = request.getParameter("outGaurantorIncome");
			String outGaurantorCnic = request.getParameter("outGaurantorCnic");
			String outGaurantorRelation = request.getParameter("outGaurantorRelation");
			String outGaurantorId = request.getParameter("outGaurantorId");
			outsiderguarantor.put("customrId", "" + customrId);
			outsiderguarantor.put("outGaurantorName", outGaurantorName);
			outsiderguarantor.put("outGaurantorFather", outGaurantorFather);
			outsiderguarantor.put("outGaurantorPhone", "92" + outGaurantorPhone);
			outsiderguarantor.put("outGaurantorCnic", outGaurantorCnic);
			outsiderguarantor.put("outGaurantorRelation", outGaurantorRelation);
			outsiderguarantor.put("outGaurantorId", outGaurantorId);
			outsiderguarantor.put("outGaurantorIncome", outGaurantorIncome);
			CustomerBAL.updateCustomerGuarantorTwo(outsiderguarantor);

			// update businessinfo
		} else if (action.equals("SaveIncome")) {
			System.out.println("*****************************************************************");
			HashMap<String, String> businessinfo = new HashMap<>();
			String[] otherincomeIds = request.getParameterValues("data[incomeIds][]");
			String[] otherincomeDetails = request.getParameterValues("data[incomeDetails][]");
			String[] otherincomeAmounts = request.getParameterValues("data[incomeAmounts][]");
			String business = request.getParameter("business");
			String businessType = request.getParameter("businessType");
			System.out.println(businessType);
			String businessPlace = request.getParameter("businessAddress");
			String businessPhone = request.getParameter("businessPhone");

			String bussinessOwnerShip = request.getParameter("businessPeriod");
			String businessComments = request.getParameter("bussinessComments");
			String businessId = request.getParameter("businessId");

			businessinfo.put("customrId", "" + customrId);
			businessinfo.put("businessId", businessId);
			businessinfo.put("business", business);
			businessinfo.put("businessType", businessType);
			businessinfo.put("businessAddress", businessPlace);
			if (businessPhone.equals("null") || businessPhone == null || businessPhone == "") {
				businessinfo.put("businessPhone", businessPhone);
			} else {
				businessinfo.put("businessPhone", "92" + businessPhone);
			}
			businessinfo.put("businessPeriod", bussinessOwnerShip);
			businessinfo.put("businessComments", businessComments);

			System.out.println(businessinfo);
			boolean status = CustomerBAL.updateBusinessInfo(businessinfo);

			HashMap<String, String> empdetails = new HashMap<>();
			String employmentPosition = request.getParameter("employmentPosition");
			String employmentPeriod = request.getParameter("employmentPeriod");
			String employmentOrg = request.getParameter("employmentOrg");
			String employmentOrgPhone = request.getParameter("employmentOrgPhone");
			String EmployementAddress = request.getParameter("EmployementAddress");
			String employementId = request.getParameter("employementId");
			String supervisor = request.getParameter("supervisor");

			empdetails.put("customrId", "" + customrId);
			empdetails.put("employmentPosition", employmentPosition);
			empdetails.put("employmentPeriod", employmentPeriod);
			empdetails.put("employmentOrg", employmentOrg);
			// empdetails.put("employmentOrgPhone", employmentOrgPhone);
			if (employmentOrgPhone.equals("null") || employmentOrgPhone == null || employmentOrgPhone == "") {
				empdetails.put("employmentOrgPhone", employmentOrgPhone);
			} else {
				empdetails.put("employmentOrgPhone", "92" + employmentOrgPhone);
			}
			empdetails.put("SupervisorName", supervisor);
			empdetails.put("EmployementAddress", EmployementAddress);
			empdetails.put("employementId", employementId);
			System.out.println(empdetails);
			boolean status2 = CustomerBAL.updateEmploymentDetails(empdetails);
			double salryPansion = 0.0;
			double farming = 0, businessIncome = 0, familyContribution = 0;

			String salary = request.getParameter("customerSalaryPansion");
			String farmingStr = request.getParameter("customerFarming");
			String businessInc = request.getParameter("customerBusinessIncome");
			String contribution = request.getParameter("customerFamilyContribution");
			if (salary.isEmpty() || salary == null || salary.equals("null") || salary == "") {
				salryPansion = 0;
			} else {
				salryPansion = Double.parseDouble(salary);
			}

			if (farmingStr.isEmpty() || farmingStr == null || farmingStr.equals("") || farmingStr == "") {
				farming = 0;
			} else {
				farming = Integer.parseInt(farmingStr);
			}

			if (businessInc.isEmpty() || businessInc == null || businessInc.equals("") || businessInc == "") {
				businessIncome = 0;
			} else {
				businessIncome = Integer.parseInt(businessInc);
			}

			if (contribution.isEmpty() || contribution == null && contribution.equals("") || contribution == "") {
				familyContribution = 0;
			} else {
				familyContribution = Integer.parseInt(contribution);
			}

			HashMap<String, Object> incomeMap = new HashMap<>();
			incomeMap.put("customerId", customrId);
			incomeMap.put("salaryPension", salryPansion);
			incomeMap.put("farmiMing", farming);
			incomeMap.put("bussinessIncome", businessIncome);
			incomeMap.put("familyContribution", familyContribution);

			System.out.println("++++++++++++++++++++++++++++++++++-----" + incomeMap);
			System.out.println("customerId " + customrId + " Salary " + salryPansion + " farming " + farming
					+ " familycottribution" + familyContribution + " bussinessIncome " + businessIncome);
			if (otherincomeIds != null) {
				CustomerBAL.updateOtherIncomeDetails(otherincomeIds, otherincomeDetails, otherincomeAmounts);
			}
			boolean incomeStat = CustomerBAL.updateIncomeInfo(incomeMap);

			try {
				json.put("data", status);
				json.put("employement", status2);
				json.put("income", incomeStat);
				out.print(json);
			} catch (JSONException e) {

				e.printStackTrace();
			}
		} else if (action.equals("savegenInfo")) {
			String[] expensetype = request.getParameterValues("data[monthlyExpenseType][]");
			String[] amount = request.getParameterValues("data[monthlyExpenseAmount][]");
			String[] expenseIds = request.getParameterValues("data[ExpenseIDs][]");
			String[] assetType = request.getParameterValues("data[assetTypesArray][]");
			String[] assetAmount = request.getParameterValues("data[assetAmountArray][]");
			String[] assetIds = request.getParameterValues("data[assetsIds][]");
			String loanDonner = request.getParameter("loanDonner");
			String borrowedAmmount = request.getParameter("borrowedAmmount");
			String remainingAmount = request.getParameter("remainingAmount");
			String monthlyInstallment = request.getParameter("monthlyInstallment");
			String GKloanDonner = request.getParameter("GKloanDonner");
			String GKborrowedAmmount = request.getParameter("GKborrowedAmmount");
			String GKremainingAmount = request.getParameter("GKremainingAmount");
			String GKmonthlyInstallment = request.getParameter("GKmonthlyInstallment");
			String FFloanDonner = request.getParameter("FFloanDonner");
			String FFborrowedAmmount = request.getParameter("FFborrowedAmmount");
			String FFremainingAmount = request.getParameter("FFremainingAmount");
			String FFmonthlyInstallment = request.getParameter("FFmonthlyInstallment");
			String loanID = request.getParameter("loanID");
			String GKloanID = request.getParameter("GKloanID");
			String FFloanID = request.getParameter("FFloanID");

			int loanid = 0, gkloanId = 0, ffloanid = 0;
			if (loanID != null) {
				loanid = Integer.parseInt(loanID);
			}
			if (GKloanID != null) {
				gkloanId = Integer.parseInt(GKloanID);
			}
			if (FFloanID != null) {
				ffloanid = Integer.parseInt(FFloanID);
			}
			int monthlyExpense = CustomerBAL.updateMonthlyHomeExpensesByExpenseId(customrId, expenseIds, expensetype,
					amount);
			int borrowedAm = 0, remainingAm = 0, monthlyAm = 0;
			if (borrowedAmmount != null && !borrowedAmmount.equals("")) {
				borrowedAm = Integer.parseInt(borrowedAmmount);
			}
			if (remainingAmount != null && !remainingAmount.equals("")) {
				remainingAm = Integer.parseInt(remainingAmount);
			}
			if (monthlyInstallment != null && !monthlyInstallment.equals("")) {
				monthlyAm = Integer.parseInt(monthlyInstallment);
			}
			int GKborrowedAm = 0, GKremainingAm = 0, GKmonthlyAm = 0;
			if (GKborrowedAmmount != null && !GKborrowedAmmount.equals("")) {
				GKborrowedAm = Integer.parseInt(GKborrowedAmmount);
			}
			if (GKremainingAmount != null && !GKremainingAmount.equals("")) {
				GKremainingAm = Integer.parseInt(GKborrowedAmmount);
			}
			if (GKmonthlyInstallment != null && !GKmonthlyInstallment.equals("")) {
				GKmonthlyAm = Integer.parseInt(GKmonthlyInstallment);
			}
			int FFborrowedAm = 0, FFremainingAm = 0, FFmonthlyAm = 0;
			if (FFborrowedAmmount != null && !FFborrowedAmmount.equals("")) {
				FFborrowedAm = Integer.parseInt(FFborrowedAmmount);
			}
			if (FFremainingAmount != null && !FFremainingAmount.equals("")) {
				FFremainingAm = Integer.parseInt(FFborrowedAmmount);
			}
			if (FFmonthlyInstallment != null && !FFmonthlyInstallment.equals("")) {
				FFmonthlyAm = Integer.parseInt(FFmonthlyInstallment);
			}
			int bankloan = CustomerBAL.updateOtherLoanByOltype(customrId, loanid, loanDonner, borrowedAm, remainingAm,
					monthlyAm, "1");
			int kryanaGroceryLoan = CustomerBAL.updateOtherLoanByOltype(customrId, gkloanId, GKloanDonner, GKborrowedAm,
					GKremainingAm, GKmonthlyAm, "2");
			int familyFreindloan = CustomerBAL.updateOtherLoanByOltype(customrId, ffloanid, FFloanDonner, FFborrowedAm,
					FFremainingAm, FFmonthlyAm, "3");
			int assets = CustomerBAL.UpdateAssetsById(customrId, assetIds, assetType, assetAmount);
			try {
				json.put("monthlyExpense", monthlyExpense);
				json.put("bank", bankloan);
				json.put("kryanaGrocery", kryanaGroceryLoan);
				json.put("familyFreind", familyFreindloan);
				json.put("assets", assets);
				out.print(json);
			} catch (JSONException e) {
				logger.equals(e);
				e.printStackTrace();
			}
		}

	}

}
