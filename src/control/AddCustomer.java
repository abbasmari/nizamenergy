package control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import compressImage.CompressJPEGFileExample;
import bal.AddCustomerBAL;
import bal.CallingXML;
import bal.CustomerBAL;
import bal.telenor;
import bean.BusinessDetails;
import bean.CustomerBean;
import bean.CustomerChild;
import bean.CustomerGuardian;
import bean.EmploymentDetails;
import bean.GuarantorBean;
import bean.HouseHoldAssets;
import bean.MonthlyExpenses;
import bean.OtherLoanDetails;
import bean.UserBean;

@WebServlet("/AddCustomer")
@MultipartConfig
public class AddCustomer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddCustomer.class);
	public AddCustomer() {
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		String option = request.getParameter("click");
		InputStream inputStream = null;
		InputStream is = null;
		
		
		if (option.equalsIgnoreCase("Add Customer")) {
			
			UserBean bean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));
						
			String fullname = request.getParameter("fullname");
			String fatherName = request.getParameter("fathername");
			String motherName = request.getParameter("mothername");
			System.out.print(fatherName + " " + motherName+" "+fullname);

			String gender = request.getParameter("gender");
			String cnic = request.getParameter("cnic");
			String date = request.getParameter("dob");
			
			System.out.println(date+" "+gender+" "+cnic);
			String age = request.getParameter("age");
			System.out.print(age);
			String mobile_no = request.getParameter("phone");
			mobile_no = mobile_no.replace("-", "");
			String mobile_no2 = "";
			mobile_no2 = request.getParameter("phone2");
			mobile_no2 = mobile_no2.replace("-", "");
			String email = request.getParameter("email");
			
			System.out.println(mobile_no+" "+mobile_no2+" "+email);
			String income = request.getParameter("income");

			String fincome = request.getParameter("fincome");
			String sourceOfIncome = request.getParameter("source");
//			String occupation = request.getParameter("occupation");
			String relationStatus = request.getParameter("status");
			String educated = request.getParameter("educated");
			String education = request.getParameter("education");
			String cityId = request.getParameter("city");
			System.out.println(income+" "+fincome+" "+sourceOfIncome+" "+relationStatus+" "+educated+" "+education+" "+cityId);

			String address = request.getParameter("address");
			/*String guardianName = request.getParameter("guardian");
			String guardianPhone = request.getParameter("guardianPhone");
			String customerfamily = request.getParameter("customerfamily");*/
			String home = request.getParameter("home");
			String car = request.getParameter("car");
			String bike = request.getParameter("bike");
			String washingMachine = request.getParameter("washingMachine");
			
			
			String fridge = request.getParameter("fridge");
			String tv = request.getParameter("tv");

			System.err.println(tv+" "+fridge+" "+home+" "+car+" "+bike+" "+washingMachine);
			
			boolean isHome = false;
			boolean isCar = false;
			boolean isBike = false;
			boolean isTv = false;
			boolean isWashingMachine = false;
			boolean isFridge = false;

			if (home == "1") {
				isHome = true;
			} else if (home == "") {
				isHome = false;
			}
			if (car == "1") {
				isCar = true;
			} else if (car == "") {
				isCar = false;
			}
			if (bike == "1") {
				isBike = true;
			} else if (bike == "") {
				isBike = false;
			}
			if (washingMachine == "1") {
				isWashingMachine = true;
			} else if (washingMachine == "") {
				isWashingMachine = false;
			}
			if (tv == "1") {
				isTv = true;
			} else if (tv == "") {
				isTv = false;
			}
			if (fridge == "1") {
				isFridge = true;
			} else if (fridge == "") {
				isFridge = false;
			}
			String other = request.getParameter("other");
			System.out.println(other);
			String businessName = request.getParameter("businessName");
			String businessType = request.getParameter("businessType");
			String owner = request.getParameter("owner");
			String businessTime = request.getParameter("businessTime");
			String currentPlace = request.getParameter("currentPlace");
	//		int worker = Integer.parseInt(request.getParameter("worker"));
        	int worker = 11;
			String businessPlace = request.getParameter("businessPlace");
			String businessPhone = request.getParameter("businessPhone");
			businessPhone = businessPhone.replace("-", "");

			String orgName = request.getParameter("orgName");
			String jobPosition = request.getParameter("position");

			String jobPeriod = request.getParameter("period");

			String orgPhone = request.getParameter("orgPhone");
			orgPhone = orgPhone.replace("-", "");

			//double salary = Double.parseDouble(request.getParameter("salary"));

			String orgAddress = request.getParameter("orgAddress");
			System.out.println(businessName+" "+businessType+" "+businessPhone+" "+businessPlace+" "+businessTime+" "+orgName+" "+jobPosition+" "+jobPeriod+" "+orgPhone+"");
			
			boolean isLoanTaken = Boolean.parseBoolean(request.getParameter("isLoanTaken"));
			String donnerName = request.getParameter("donnerName");
			String lp=request.getParameter("loanPayment");
			String remain=request.getParameter("remaining");
			double loanPayment=0.0,monthlyInstallment=0.0,remaining=0.0;
			String otherinstalments=request.getParameter("otherInstallment");
			String lonp=request.getParameter("loanperiod");
			int loanperiod=0;
			if(!lp.equals("") && !lonp.equals("") && !remain.equals("")){
				loanPayment= (double)(Double.parseDouble(lp));
				loanperiod = Integer.parseInt(lonp);
				monthlyInstallment = Double.parseDouble(otherinstalments);
				remaining = Double.parseDouble(remain);
			}
			
		 
			
			boolean isBankAccount = Boolean.parseBoolean(request.getParameter("isBankAccount"));
			String bankName = request.getParameter("bankName");

//			System.out.println(isLoanTaken+" "+donnerName+" "+loanPayment+""+loanperiod+" "+monthlyInstallment+" "+remaining+" "+isBankAccount+" "+bankName);
			
			String electricity = request.getParameter("electricity");
			String generator = request.getParameter("generator");
			String ups = request.getParameter("ups");
			String solar = request.getParameter("solar");
//			String otherelusage = request.getParameter("otherelusage");
			
			System.out.println(electricity+" "+generator+" "+ups+" "+solar);
			String electricityusage = request.getParameter("electricityusage");
			double electricityexpense = Double.parseDouble(request.getParameter("electricityexpense"));
			
			String majorexpensedescription = request.getParameter("majorexpensedescription");
			double majorexpenseamount = Double.parseDouble(request.getParameter("majorexpenseamount"));
			
			double totalexpense = Double.parseDouble(request.getParameter("totalexpense"));
			System.out.println(electricityusage+" "+electricityexpense+" "+majorexpenseamount+" "+majorexpensedescription+" "+totalexpense);
			
//			double HouseHoldchores = Double.parseDouble(request
//					.getParameter("chores"));
//			double studentEducation = Double.parseDouble(request
//					.getParameter("stdEdu"));
//			double medicine = Double.parseDouble(request
//					.getParameter("medicine"));
//			double anyOtherLoan = Double.parseDouble(request
//					.getParameter("anyloan"));
//			double OtherExpense = Double.parseDouble(request
//					.getParameter("otherExpense"));
//
//			double totalExpense = rent + utility + travelExpense
//					+ HouseHoldchores + studentEducation + medicine
//					+ anyOtherLoan;
//			double businessIncome = Double.parseDouble(request
//					.getParameter("businessIncome"));
//			double salaryIncome = Double.parseDouble(request
//					.getParameter("salaryIncome"));
//			double otherIncome = Double.parseDouble(request
//					.getParameter("otherIncome"));
//			double totalIncome = businessIncome + otherIncome + salaryIncome;

			String g1guarantorName = request.getParameter("g1guarantorName");
			String g1guarantorFather = request.getParameter("g1guarantorFather");
			String g1guarantorCnic = request.getParameter("g1guarantorCnic");
			String g1guarantorDob = request.getParameter("g1guarantorDob");
			String g1RelationToCustomer = request.getParameter("g1RelationToCustomer");
			String g1marritalStatus = request.getParameter("g1marritalStatus");
			String g1guarantorPhone = request.getParameter("g1guarantorPhone");
			String g1guarantorIncome = request.getParameter("g1guarantorIncome");
			
			String g1CompanyName = request.getParameter("g1CompanyName");
			String g1Desidition = request.getParameter("g1Desidition");
			String g1PhoneNumber = request.getParameter("g1PhoneNumber");
			g1PhoneNumber = g1PhoneNumber.replace("-", "");
			String g1CompanyAddess = request.getParameter("g1CompanyAddess");
			String g1BusinessName = request.getParameter("g1BusinessName");
			
			String g1BusnessType = request.getParameter("g1BusnessType");

			String g1BusnessNumber = request.getParameter("g1BusnessNumber");
			g1BusnessNumber = g1BusnessNumber.replace("-", "");
			String g1BusinessAddress = request.getParameter("g1BusinessAddress");
			
			System.out.println(g1guarantorName+" "+g1guarantorPhone+" "+g1guarantorFather+" "+g1guarantorCnic+" "+g1guarantorDob+" "+g1guarantorIncome);
			System.out.println(g1CompanyAddess+" "+g1CompanyName+" "+g1Desidition+" "+g1PhoneNumber+" "+g1BusinessAddress+" "+g1BusinessName+" "+g1BusnessNumber+" "+g1BusnessType);
			
			String g2guarantorName = request.getParameter("g2guarantorName");
			String g2guarantorFather = request.getParameter("g2guarantorFather");
			String g2guarantorCnic = request.getParameter("g2guarantorCnic");
			String g2guarantorDob = request.getParameter("g2guarantorDob");
			String g2RelationToCustomer = request.getParameter("g2RelationToCustomer");
			String g2guarantorIncome = request.getParameter("g2guarantorIncome");
			String g2guarantorPhone = request.getParameter("g2guarantorPhone");
			String g2marritalStatus = request.getParameter("g2marritalStatus");
			String g2CompanyName = request.getParameter("g2CompanyName");
			String g2Designation = request.getParameter("g2Designation");
			String g2PhoneNumber = request.getParameter("g2PhoneNumber");
			g2PhoneNumber = g2PhoneNumber.replace("-", "");
			String g2CompanyAddress = request.getParameter("g2CompanyAddress");
			String g2BusinessName = request.getParameter("g2BusinessName");
			String g2BusinessType = request.getParameter("g2BusinessType");
			String g2BusinessNumber = request.getParameter("g2BusinessNumber");
			g2BusinessNumber = g2BusinessNumber.replace("-", "");
			String g2BusinessAddress = request.getParameter("g2BusinessAddress");

//			String gsalesman3 = request.getParameter("gsalesman3");

			System.out.println(g2guarantorName+" "+g2guarantorPhone+" "+g2guarantorFather+" "+g2guarantorCnic+" "+g2guarantorDob+" "+g2guarantorIncome);
			System.out.println(g2CompanyAddress+" "+g2CompanyName+" "+g2Designation+" "+g2PhoneNumber+" "+g2BusinessAddress+" "+g2BusinessName+" "+g2BusinessNumber+" "+g2BusinessType);
			
			// Part filePart = request.getPart("photo");
			// String fileName = getFileName(filePart);

			String appname = request.getParameter("appname");
			String gsm_no = request.getParameter("gsm");

			double price = 0;
			if (appname.equals("50 W")) {
				price = (36 * 1000) + 1000 + 8000;
			} else if (appname.equals("80 W")) {
				price = (36 * 1500) + 1500 + 10000;
			} else if (appname.equals("100 W")) {
				price = (36 * 2000) + 2000 + 12000;
			}
			int salesmanId = Integer.parseInt(request.getParameter("gsalesman3"));
			int scheme = Integer.parseInt(request.getParameter("scheme"));
			double downPayment = Double.parseDouble(request
					.getParameter("downPayment"));
			double installment = Double.parseDouble(request
					.getParameter("installment"));
			
//			int appliance_id = Integer.parseInt(request.getParameter("findappid"));

			
//			String city = request.getParameter("city");

			Part filePart = request.getPart("photo");

			// String fileName = AddCustomerBAL.getFileName(filePart);

			// System.out.println("File:" + fileName);
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
				BufferedImage originalImage = ImageIO.read(inputStream);
				int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage bufferedImage = CompressJPEGFileExample.resizeImage(originalImage, type);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
								
				ImageIO.write(bufferedImage, "jpg", baos);
				is = new ByteArrayInputStream(baos.toByteArray());
				
			}

			CustomerBean customerBean = new CustomerBean();
			customerBean.setCustomerName(fullname);
			customerBean.setFatherName(fatherName);
			customerBean.setMotherName(motherName);
			customerBean.setEmail(email);
			customerBean.setPhoneNo("92"+mobile_no);
			customerBean.setPhoneNo2("92"+mobile_no2);
			customerBean.setAddress(address);
			customerBean.setDistrict(cityId);
			customerBean.setAge(Integer.parseInt(age));
			customerBean.setDateOfBirth(date);
			customerBean.setCnicNo(cnic);
			customerBean.setRelationStatus(relationStatus);
			customerBean.setSourceOfIncome(sourceOfIncome);
			customerBean.setEducated(Boolean.parseBoolean(educated));
			customerBean.setEducation(education);
			customerBean.setGender(gender);
//			customerBean.setFamilyMember();
			customerBean.setMonthlyIncome(Double.parseDouble(income));
			customerBean.setFamilyIncome(Double.parseDouble(fincome));
			customerBean.setCustomer_pic(is);
//			int customerId = AddCustomerBAL.addCustomerInfo(customerBean);
//			System.out.println("Customer_id"+customerId);
//			int child_limit = Integer.parseInt(customerfamily);
			/*CustomerChild cc_bean = new CustomerChild();
			ArrayList<CustomerChild> childlist = new ArrayList<>();
			
			for (int i = 1; i <= child_limit; i++) { 

				String childname = request.getParameter("childname" + i);
				String childrelation = request.getParameter("childrelation" + i);
				String childmobile = request.getParameter("childmobile" + i);
				childmobile = childmobile.replace("-", "");
				String child_dob = request.getParameter("child_dob" + i);
				String childcnic = request.getParameter("childcnic" + i);  
				String childworkdetail = request.getParameter("childworkdetail"+ i);
				
				String childemploy_name = request.getParameter("childemploy_name"+ i);
				String childemploy_adress = request.getParameter("childemploy_adress"+ i);
				
				String childbusiness = request.getParameter("childbusiness"+ i);
				String childbusiness_adress = request.getParameter("childbusiness_adress"+ i);

				double childmonthlyincome = Double.parseDouble(request.getParameter("childmonthlyincome"+ i));

//				cc_bean.setCustomer_id(customerId);
				cc_bean.setChild_name(childname);
				cc_bean.setChild_relation(childrelation);
				cc_bean.setChild_mobile("92"+childmobile);
				
				cc_bean.setChild_dob(child_dob);
				cc_bean.setChild_cnic(childcnic);
				cc_bean.setChild_workdetail(childworkdetail);
				cc_bean.setChild_employ_name(childemploy_name);
				cc_bean.setChildemploy_adress(childemploy_adress);
				cc_bean.setChild_business(childbusiness);
				cc_bean.setChildbusiness_adress(childbusiness_adress);
							
				cc_bean.setChild_monthlyincome(childmonthlyincome);
				
				childlist.add(cc_bean);
				
			}*/

//			int cc = AddCustomerBAL.addCustomerChild(cc_bean);

//			CustomerGuardian guardianBean = new CustomerGuardian();
//			guardianBean.setCustomerId(customerId);
			/*guardianBean.setGuardianName(guardianName);
			guardianBean.setPhone_no("92"+guardianPhone);
			guardianBean.setFamilyMember(Integer.parseInt(customerfamily));*/
//			int g = AddCustomerBAL.addGuardianDetails(guardianBean);

			HouseHoldAssets assetBean = new HouseHoldAssets();
//			assetBean.setCustomerId(customerId);
			assetBean.setHasHome(isHome);	
			assetBean.setHasBike(isBike);
			assetBean.setHasCar(isCar);
			assetBean.setHasTv(isTv);
			assetBean.setHasFridge(isFridge);
			assetBean.setHasWashingMachine(isWashingMachine);
			assetBean.setOtherAssets(other);
//			int a = AddCustomerBAL.addHouseHoldAssetDetails(assetBean);

			BusinessDetails businessDetails = new BusinessDetails();
//			businessDetails.setCustomerId(customerId);
			businessDetails.setBusinessName(businessName);
			businessDetails.setOrgPhone(businessPhone);
			businessDetails.setBusinessPlace(businessPlace);
			businessDetails.setWorkers(worker);
			businessDetails.setPeriod(businessTime);
			businessDetails.setOwnedOrPartner(owner);
			businessDetails.setBusinessType(businessType);
			businessDetails.setCurrentPlacePeriod(currentPlace);

//			int b = 0;
			try{
			if(businessDetails != null){
//				b = AddCustomerBAL.addBusinessDetails(businessDetails);
			}
			}catch(Exception ex){
				ex.getStackTrace();
			}
			 

			EmploymentDetails employmentDetails = new EmploymentDetails();

//			employmentDetails.setCustomerId(customerId);
			employmentDetails.setJobPeriod(jobPeriod);
			employmentDetails.setJobPosition(jobPosition);
			employmentDetails.setOrgAddress(orgAddress);
			employmentDetails.setOrganisationName(orgName);
			employmentDetails.setPhoneNo("92"+orgPhone);
			employmentDetails.setSalary(150000);

//			int emp = 0;
			try{
			if(employmentDetails != null){
//				emp = AddCustomerBAL.addEmployementDetails(employmentDetails);
			}
			}catch(Exception ex){
				ex.getStackTrace();
			}
			

			MonthlyExpenses monthlyBean = new MonthlyExpenses();
//			monthlyBean.setCustomerId(customerId);
			monthlyBean.setElectricity(electricity);
			monthlyBean.setGenerator(generator);
			monthlyBean.setUps(ups);
			monthlyBean.setSolar(solar);
			monthlyBean.setElectricityusage(electricityusage);
			monthlyBean.setElectricityexpense(electricityexpense);
			monthlyBean.setMajorexpensedescription(majorexpensedescription);
			monthlyBean.setMajorexpenseamount(majorexpenseamount);
			monthlyBean.setTotalexpense(totalexpense);
			

//			int exp = AddCustomerBAL.addHomeExpenseDetails(monthlyBean);

			OtherLoanDetails loanDetails = new OtherLoanDetails();
//			loanDetails.setCustomerId(customerId);
			loanDetails.setBankName(bankName);
			loanDetails.setOtherLoan(isLoanTaken);
			loanDetails.setRemainingPayment(remaining);
			loanDetails.setBankAccount(isBankAccount);
			loanDetails.setLoanDonner(donnerName);
			loanDetails.setLoanAmount(loanPayment);
			loanDetails.setLoanPeriod(loanperiod);
			loanDetails.setMonthlyInstallment(monthlyInstallment);
			
//			int l = 0;
			try{
			
			if(loanDetails != null){
//				l = AddCustomerBAL.addOtherLoanDetails(loanDetails);
			}
			}catch(Exception ex){
				ex.getStackTrace();
			}

			
			
			
			

			GuarantorBean guarantorBean1 = new GuarantorBean();
//			guarantorBean1.setCustomerId(customerId);
			
			guarantorBean1.setGguarantorName(g1guarantorName);
			guarantorBean1.setGguarantorFather(g1guarantorFather);
			guarantorBean1.setGguarantorCnic(g1guarantorCnic);
			guarantorBean1.setGguarantorDob(g1guarantorDob);
			guarantorBean1.setgRelationToCustomer(g1RelationToCustomer);
			guarantorBean1.setGmarritalStatus(g1marritalStatus);
			
			guarantorBean1.setGguarantorPhone("92"+g1guarantorPhone);
			guarantorBean1.setGguarantorIncome(g1guarantorIncome);
			guarantorBean1.setgCompanyName(g1CompanyName);
			guarantorBean1.setgDesidition(g1Desidition);
			guarantorBean1.setgPhoneNumber("92"+g1PhoneNumber);
			guarantorBean1.setgCompanyAddess(g1CompanyAddess);
			guarantorBean1.setgBusinessName(g1BusinessName);
			guarantorBean1.setgBusnessType(g1BusnessType);
			guarantorBean1.setgBusnessNumber("92"+g1BusnessNumber);
			guarantorBean1.setgBusinessAddress(g1BusinessAddress);
			int fguarantertype = 1;
			guarantorBean1.setGuarantertype(fguarantertype);
			
			
			GuarantorBean guarantorBean2 = new GuarantorBean();
//			guarantorBean2.setCustomerId(customerId);
			
			guarantorBean2.setGguarantorName(g2guarantorName);
			guarantorBean2.setGguarantorFather(g2guarantorFather);
			guarantorBean2.setGguarantorCnic(g2guarantorCnic);
			guarantorBean2.setGguarantorDob(g2guarantorDob);
			guarantorBean2.setgRelationToCustomer(g2RelationToCustomer);
			guarantorBean2.setGmarritalStatus(g2marritalStatus);
			
			guarantorBean2.setGguarantorPhone("92"+g2guarantorPhone);
			guarantorBean2.setGguarantorIncome(g2guarantorIncome);
			guarantorBean2.setgCompanyName(g2CompanyName);
			guarantorBean2.setgDesidition(g2Designation);
			guarantorBean2.setgPhoneNumber("92"+g2PhoneNumber);
			guarantorBean2.setgCompanyAddess(g2CompanyAddress);
			guarantorBean2.setgBusinessName(g2BusinessName);
			guarantorBean2.setgBusnessType(g2BusinessType);
			guarantorBean2.setgBusnessNumber("92"+g2BusinessNumber);
			guarantorBean2.setgBusinessAddress(g2BusinessAddress);
			int oguarantertype = 2;
			guarantorBean2.setGuarantertype(oguarantertype);

//			int gc = AddCustomerBAL.addGuarantor(guarantorBean1);
//			int gc1 = AddCustomerBAL.addGuarantor(guarantorBean2);
//			int applianceId = 0;
			
			HashMap<String, String> mapappliance = new HashMap<>();
			mapappliance.put("gsm_no", gsm_no);
			mapappliance.put("appname", appname);
			mapappliance.put("price", ""+price);
			mapappliance.put("state", ""+0);
			try {
//				applianceId = AddCustomerBAL.SaveAppliance2(gsm_no, appname, price, 0);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			HashMap<String, String> mapelig = new HashMap<>();
//			mapelig.put("customerId", ""+customerId);
//			mapelig.put("applianceId", ""+appliance_id);
			mapelig.put("salesmanId", ""+salesmanId);
			mapelig.put("downPayment", ""+downPayment);
			mapelig.put("scheme", ""+scheme);
			mapelig.put("installment", ""+installment);
			try {
//				AddCustomerBAL.saveEligibilty2(customerId, applianceId, salesmanId, downPayment, scheme, installment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 String str = "Welcome to Nizam Energy, Dear "+fullname+" You Have to need to Contact your District Office of Nizam Emnergy.";
			 try {
				 System.out.println("Mobile number for mesg:    "+mobile_no);
				 telenor.SendSms("92"+mobile_no, str);
				//CallingXML.sendSingleWellcome();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
//			System.out.print(customerId + " " + g + " " + a + " " + b + " "
//					+ emp + " " + l + " " + exp + " " + gc + " " + gc1 + " "
//					+ cc + " " + applianceId + " " + salesmanId);
			System.out.println("data added: " + fullname);
			
			ArrayList<GuarantorBean> glist = new ArrayList<>();
			glist.add(guarantorBean1);
			glist.add(guarantorBean2);
			List<Part> parts = new ArrayList<Part>();
			parts.add(request.getPart("attachment1"));
			parts.add(request.getPart("attachment2"));
			parts.add(request.getPart("attachment3"));
			parts.add(request.getPart("attachment4"));
			
			try {
				boolean err = AddCustomerBAL.customerInfo(customerBean, /*childlist, guardianBean,*/ assetBean, businessDetails, employmentDetails, monthlyBean,loanDetails, glist, mapappliance, mapelig,parts);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String message = "";
			try {
    			telenor.SendSms("92"+mobile_no, message);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("LoanRequest");
		} else if(option.equalsIgnoreCase("Add Appliance")){
			int exe_customerid = Integer.parseInt(request.getParameter("exe_customerid"));
			int exe_salesmanid = Integer.parseInt(request.getParameter("salesmandetail"));
			// for Customer
			
			int exescheme = Integer.parseInt(request.getParameter("scheme"));
			double exedownPayment = (double)(Integer.parseInt(request.getParameter("downPayment")));
			double exeinstallment = (double)(Integer.parseInt(request.getParameter("installment")));
			String exappname = request.getParameter("appname");
			String exe_gsm = request.getParameter("exe_gsm");
			
			double exeprice = 0;
			if (exappname.equals("50 W")) {
				exeprice = 32000;
			} else if (exappname.equals("80 W")) {
				exeprice = 46000;
			} else if (exappname.equals("100 W")) {
				exeprice = 60000;
			}
			System.err.println(exe_customerid+" / "+exe_salesmanid+" / "+exescheme+" / "+exedownPayment+" / "+exeinstallment+" / "+exappname+" / "+exe_gsm+" // "+exeprice);
			
			
			
			int exe_applianceId = 0;
			try {
				exe_applianceId = AddCustomerBAL.SaveAppliance2(exe_gsm, exappname, exeprice, 0);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			try {
				AddCustomerBAL.saveEligibilty2(exe_customerid, exe_applianceId, exe_salesmanid, exedownPayment, exescheme, exeinstallment);
//				String message = "";
//				try {
//	    			telenor.SendSms("92"+mobile_no, message);
//				} catch (ServiceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				RequestDispatcher rd = request.getRequestDispatcher("LoanRequest");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
					System.err.println(exe_customerid+"--"+exescheme+"--"+exedownPayment+"--"+exeinstallment);
		
		}else if(option.equalsIgnoreCase("getApplianceId")){
			UserBean bean2 = (UserBean) session.getAttribute("email");
			// JSON Values for appliance_id 
			//JSONObject obj = new JSONObject();
			//JSONArray jArray = new JSONArray();
			String appliance = request.getParameter("appliance_name");
			int appid = CustomerBAL.getApplianceIdByAppName(appliance, bean2.getUserId());
			
			try (PrintWriter out = response.getWriter()){
				if(appid > 0){
					System.err.println("+++++++++++++++++++++   if  ++++++++++++++++++++++  "+appid);
					out.println(appid);
	//			jArray.put(obj);
				}else{
					out.println(appid);
					System.err.println("+++++++++++++++++++++   else  ++++++++++++++++++++++  "+appid);
				}
				//out.println(obj);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

	}

}
