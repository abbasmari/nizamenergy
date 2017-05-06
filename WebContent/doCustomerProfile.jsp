<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.UserBAL"%>
<%@ page import="java.io.File"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="bal.ImageBAL"%>
<%@page import="bean.EligibilityExistCustomer"%>
<%@page import="java.util.HashMap"%>
<%@page import="bean.CustomerChild"%>
<%@page import="bean.EmploymentDetails"%>
<%@page import="bean.GuarantorBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bean.CustomerBean"%>
<%@page import="bean.ApplianceNameById"%>
<%@page import="bean.CustomerGuardian"%>
<%@page import="bean.MonthlyExpenses"%>
<%@page import="bean.OtherLoanDetails"%>
<%@page import="bean.HouseHoldAssets"%>
<%@page import="bean.BusinessDetails"%>
<%@page import="bean.EmploymentDetails"%>
<%@page import="bean.CustomerMessageBean"%>
<%@page import="bal.ApplianceBAL"%>
<%@page import="bean.MonthlyHomeExpenses"%>
<%@page import="bean.CustomerProfileViewBean"%>
<%@page import="bal.CustomerRetrieveDataBAL"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/myGallery.css" rel="stylesheet" />

<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<link href="assets/plugins/isotope/isotope.css" rel="stylesheet" />
<link href="assets/plugins/lightbox/css/lightbox.css" rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->
<link rel="stylesheet" href="assets/css/viewer.css" />
<link rel="stylesheet" href="assets/css/main.css" />
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />



<!-- ================== BEGIN BASE JS ================== -->
<!-- <script src="assets/plugins/pace/pace.min.js"></script> -->
<!-- ================== END BASE JS ================== -->
</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		CustomerProfileViewBean customebean = (CustomerProfileViewBean) request
				.getAttribute("customerInformation");
		ArrayList<EligibilityExistCustomer> applist = ApplianceBAL
				.getApplianceByCustomerId(customebean.getCustomerId());

		GuarantorBean fguarantorBean1 = CustomerRetrieveDataBAL
				.getGuaranterDetailById(customebean.getCustomerId(), 1);
		GuarantorBean oguarantorBean2 = CustomerRetrieveDataBAL
				.getGuaranterDetailById(customebean.getCustomerId(), 2);
		// 		ArrayList<CustomerMessageBean> messageList = CustomerBAL
		// 				.getMessages(customebean.getCustomerId());

		String customerNumber = UserBAL.getFormattedPhoneNumber(customebean
				.getPhoneNo());

		String customerNumber2 = UserBAL
				.getFormattedPhoneNumber(customebean.getPhoneNo2());

		int key = (Integer) request.getAttribute("key");
		ArrayList<HashMap<String, String>> maps = CustomerBAL
				.getRequestStatus(customebean.getCustomerId());
		ArrayList<HashMap<String, String>> appliancesInAccount = CustomerBAL
				.getApplianceInAccount(customebean.getCustomerId());
		String appname = (String) request.getAttribute("appName");
		System.out.println("App name ====== " + appname);
		String appID = "";
		if (key == 1) {
			appID = request.getAttribute("appId").toString();
		}
		if (key == 2) {
			appID = request.getAttribute("appId").toString();
		}
		// 		ArrayList<HashMap<String, String>> mapss = SaDoChatBAL.getChat(
		// 				userbean.getUserId(), customebean.getCustomerId());
		// 		System.out.println(request.getServletContext().getRealPath(
		// 				"/Images/"));
		ArrayList<MonthlyHomeExpenses> list = CustomerBAL
				.getMonthlyExpensesById(customebean.getCustomerId());

		HashMap<String, String> totalLiveStockAssetsList = CustomerRetrieveDataBAL
				.getCustomerTotalLiveStockAssets(customebean
						.getCustomerId());

		ArrayList<HashMap<String, String>> assetsListget = CustomerRetrieveDataBAL
				.getAssetsOfCustomer(customebean.getCustomerId());

		HashMap<String, String> totalHomeExpensesList = CustomerRetrieveDataBAL
				.getCustomerTotalMonthlyHomeExpenses(customebean
						.getCustomerId());

		ArrayList<HashMap<String, String>> otherIncomes = CustomerRetrieveDataBAL
				.getOtherIncome(customebean.getCustomerId());

		ArrayList<HashMap<String, String>> loanAndLiabilitiesList = CustomerRetrieveDataBAL
				.getLoanAndLiabilities(customebean.getCustomerId());

		ArrayList<HashMap<String, String>> getCustomerOtherPhoneDetails = CustomerRetrieveDataBAL
				.getCustomerOtherPhoneDetails(customebean.getCustomerId());
	%>

	<input type="hidden" id="customerBeanID"
		value="<%=customebean.getCustomerId()%>">
	<input type="hidden" id="expenses" value="<%=list.size()%>">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="assets/img/user-13.jpg"
								alt="" /></a>
						</div>
						<div class="info">
							<%=userbean.getUserName()%>
							<small> Superadmin </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">

					<li class="has-sub"><a href="DistrictOfficerDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub active"><a href="LoanRequest"> <%-- <span class="badge pull-right"><%=countRequests%></span> --%>
							<i class="icon-note"></i> <span>Loan Request</span> <!-- 						Begin	SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>

					<li class="has-sub"><a href="doDeployment.jsp"><i
							class="fa fa-suitcase"></i><span>Deployment</span></a></li>

					<li class="has-sub"><a href="doLoanBooks.jsp"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="doFieldOfficer.jsp">Field Officer</a></li>
							<li><a href="doSalesman.jsp">Nizam Dost</a></li>
						</ul></li>

					<li class="has-sub"><a href="doAlarmsPage.jsp"> <i
							class="fa fa-star"></i> <span>Live Alerts</span>
					</a></li>
					<li class="has-sub"><a href="DoCashCustomer.jsp"> <i
							class="fa fa-star"></i> <span>Cash Customers</span>
					</a></li>
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->

		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<%-- <ol class="breadcrumb pull-right f-s-12">
<!-- 				<li><a href="SuperAdminDashboard">Dashboard</a></li> -->
				<li>
				<%
					if(key == 1){
						%><a href="Request">Laon Request</a><%
					} else if(key == 2){
						%><a href="Customer">Customers</a><%
					}
				%>				
				</li>
				<li class="active">Customer Profile</li>
			</ol> --%>
			<!-- end breadcrumb -->
			<!-- begin page-header -->

			<span style="float: left; color: black; margin-top: 2%;"
				class="page-header"><%=customebean.getCustomerName()%></span> <span
				style="float: right; margin-left: 9px;"> <a
				href="javascript:;" class="btn btn-lg btn-block btn-success"> <i
					class="fa fa-calendar pull-left"></i>&nbsp;Created Date /<small>
						&nbsp;<%=customebean.getAccountCreatedDate()%></small>
			</a> <%
 	if (customebean == null
 			|| customebean.getCustomerRequestedDate() == null) {
 %> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Requested Date /<small>
						&nbsp;<%="0000-00-00"%></small>
			</a><br> <%
 	} else {
 %> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Requested Date /<small>
						&nbsp;<%=customebean.getCustomerRequestedDate()%></small>
			</a><br> <%
 	}
 %>
			</span> <span style="float: right;"> <%
 	String salesman = "";
 	String username = "";
 	String fo_name = "";
 	if (key == 1) {
 		for (int j = 0; j < appliancesInAccount.size(); j++) {
 			String status = appliancesInAccount.get(j).get("Status");

 			salesman = appliancesInAccount.get(j).get("salesman");
 			username = appliancesInAccount.get(j).get("user_name");
 			fo_name = appliancesInAccount.get(j).get("fo_name");

 		}
 %> <%
 	for (int i = 0; i < maps.size(); i++) {
 %> <%
 	System.out
 					.println("+++++++++++++++++++++++ Customer Name = "
 							+ maps.get(i).get("customerName")
 							+ " Status = " + maps.get(i).get("Status")
 							+ " customer bean status = "
 							+ customebean.getStatus());
 			if ((maps.get(i).get("Status").equals("0")) /* && Integer.parseInt(maps.get(i).get("ApplianceId"))==appID */) {
 %> <a href="javascript:;" class="btn btn-lg btn-block btn-inverse">
					<i class="ion-cube pull-left" style="margin-top: 15px;"></i> &nbsp;
					Loan Request for<small> <%=maps.get(i).get("ApplianceName")%></small><br>
					Scheme <small><%=maps.get(i).get("scheme")%></small>
			</a><br> <span
				style="display: flex; margin-top: -15px; margin-bottom: 10px;">
					<a href="#modal-alert"
					onclick="setMoodValue('<%=maps.get(i).get("EligibilityId")%>')"
					class="btn"
					style="background-color: #2980b9; color: white; font-weight: bold; width: 120px"
					data-toggle="modal" style="width:70%;">Accept<span
						class="fa fa-check"> </span></a> <input
					style="background-color: black;" type="hidden" name="appliance"
					id="applianceValue" value="<%=maps.get(i).get("ApplianceId")%>">

					<a href="#modal-alert-delete"
					onclick="setMoodValueDelete('<%=maps.get(i).get("ApplianceId")%>','<%=maps.get(i).get("customerId")%>')"
					class="btn btn-danger"
					style="color: white; font-weight: bold; margin-left: 3%; width: 120px"
					data-toggle="modal"> Reject &nbsp;<span class="fa fa-times"></span>

				</a>
			</span> <%
 	}
 %> <%
 	}//end for loop
 	}//end if 
 	else {
 %> <%-- <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Created On <small>/
						&nbsp;<%=customebean.getAccountCreatedDate()%></small>
			</a><br> --%> <%
 	}
 %>
			</span>


			<!-- end page-header -->
			<!-- begin profile-container -->

			<!-- begin profile-section -->


			<!-- begin profile-image -->
			<!-- 						<div class="profile-image docs-pictures clearfix"> -->
			<!-- 							<img src="assets/img/profile-cover.jpg" /> <i
<!-- 								class="fa fa-user hide"></i> -->
			<!-- 							<iframe -->
			<%-- 								src="<%=request.getContextPath()%>/ImageServlet?customerId=<%=customebean.getCustomerId()%>" --%>
			<!-- 								style="width: 205px; height: 100%; overflow: hidden;"></iframe> -->

			<!-- 						</div> -->


			<!-- end profile-highlight -->

			<!-- end profile-left -->
			<!-- begin profile-right -->


			<!-- begin profile-info -->

			<!-- begin table -->

			<div class="row">
				<div class="col-sm-12">
					<div class="panel  panel-with-tabs">
						<div class="panel-heading" style="margin-left: -15px">
							<ul id="" class="nav nav-tabs">
								<li class="active"><a href="#profile" data-toggle="tab">
										<span class="hidden-xs">Personal Information</span>
								</a></li>

								<li><a href="#income" data-toggle="tab"><span
										class="hidden-xs">Income Details</span></a></li>
								<li><a href="#geninfo" data-toggle="tab"><span
										class="hidden-xs">Assets, Expenses & Liabilities</span></a></li>
								<li><a href="#gaur" data-toggle="tab"><span
										class="hidden-xs">Guarantors Information</span></a></li>
								<li><a href="#loanAsse" data-toggle="tab"><span
										class="hidden-xs">Field officer assessment</span></a></li>
							</ul>
							<h4 class="panel-title"></h4>
						</div>
						<div id="myTabContent" class="tab-content">

							<div class="tab-pane fade in active" id="profile">
								<div class="panel-body " style="">
									<div class="table table-striped" style="font-size: 13px">


										<form style="" ng-app="myApp" ng-controller="validateCtrl"
											name="customerForm">
											<div class="col-sm-6 well">

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Full
															Name</b></label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getCustomerName().isEmpty()
																	|| customebean.getCustomerName() == null) {
														%>
														<input type="text" id="name" value="N/A" disabled
															style="background-color: white; border: none">
														<%
															} else {
														%>
														<input type="text"
															value="<%=customebean.getCustomerName()%>" name="user"
															ng-model="user" id="name" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>
														<span style="color: red"
															ng-show="customerForm.user.$dirty && customerForm.user.$invalid">
															<span ng-show="customerForm.user.$error.required">Customer
																name is required.</span>
														</span>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Primary
															Number</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getPhoneNo() == null
																	|| customebean.getPhoneNo().equals("")
																	|| customebean.getPhoneNo().isEmpty()) {
														%>
														<span>(+92)</span> <input type="text"
															name="customerPrimaryNumber"
															ng-model="customerPrimaryNumber"
															id="customerPrimaryNumber" value="N/A" required disabled
															style="background-color: white; border: none" />
														<%
															} else {
																StringBuilder pcell = new StringBuilder(customebean
																		.getPhoneNo().replace("92", ""));
																pcell = pcell.insert(3, "-");
														%>
														<span>(+92)</span> <input type="text"
															name="customerPrimaryNumber" id="customerPrimaryNumber"
															value="<%=pcell%>" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>
														<span style="color: red"
															ng-show="customerForm.customerPrimaryNumber.$dirty && customerForm.customerPrimaryNumber.$invalid">
															<span
															ng-show="customerForm.customerPrimaryNumber.$error.required">Primary
																number is required.</span>
														</span>
													</div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Secondary
															Number</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getPhoneNo2() == null
																	|| customebean.getPhoneNo2().equals("")
																	|| customebean.getPhoneNo2().isEmpty()) {
														%>
														<span>(+92)</span> <input type="text"
															name="customerSecondaryNumber"
															ng-model="customerSecondaryNumber"
															id="customerSecondaryNumber" value="N/A" required
															disabled style="background-color: white; border: none" />
														<%
															} else {
																StringBuilder scell = new StringBuilder(customebean
																		.getPhoneNo2().replace("92", ""));
																scell = scell.insert(3, "-");
														%>
														<span>(+92)</span> <input type="text"
															name="customerSecondaryNumber"
															id="customerSecondaryNumber" value="<%=scell%>" required
															disabled style="background-color: white; border: none" />
														<%
															}
														%>
														<span style="color: red"
															ng-show="customerForm.customerSecondaryNumber.$dirty && customerForm.customerSecondaryNumber.$invalid">
															<span
															ng-show="customerForm.customerSecondaryNumber.$error.required">Secondary
																number is required.</span>
														</span>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>CNIC</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getCnicNo() == null
																	|| customebean.getCnicNo().equals("")
																	|| customebean.getCnicNo().isEmpty()) {
														%>
														<input type="text" name="custCnic" ng-model="custCnic"
															id="customerCnic"
															onkeypress="setNicDash(event,'customerCnic')" value="N/A"
															required disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" name="custCnic" ng-model="custCnic"
															id="customerCnic"
															onkeypress="setNicDash(event,'customerCnic')"
															value="<%=customebean.getCnicNo()%>" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>
														<span style="color: red"
															ng-show="customerForm.custCnic.$dirty && customerForm.custCnic.$invalid">
															<span ng-show="customerForm.custCnic.$error.required">CNIC
																is required.</span>
														</span>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Date
															of Birth</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getDateOfBirth() == null
																	|| customebean.getDateOfBirth() == ""
																	|| customebean.getDateOfBirth().isEmpty()) {
														%>
														<input type="text" id="dob" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="dob"
															value="<%=customebean.getDateOfBirth()%>" required
															disabled style="background-color: white; border: none" />
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Gender</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getGender() == null
																	|| customebean.getGender().isEmpty()
																	|| customebean.getGender().equals("")) {
														%>
														<input type="text" id="gender" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="gender"
															value="<%=customebean.getGender()%>" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Marital
															Status</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getMarritalStatus() == null
																	|| customebean.getMarritalStatus().equals("")
																	|| customebean.getMarritalStatus().isEmpty()) {
														%>
														<input type="text" id="MaritalStatus" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="MaritalStatus"
															value="<%=customebean.getMarritalStatus()%>" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Disability</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerDisability() == null
																	|| customebean.getCustomerDisability().isEmpty()) {
														%>
														<input type="text" id="disability" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="disability"
															value="<%=customebean.getCustomerDisability()%>" required
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br> <br>

												<legend>
													<b>Location</b>
												</legend>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>System
															installed place, Address</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerSystemInstalledPlace() == null
																	|| customebean.getCustomerSystemInstalledPlace().isEmpty()
																	&& customebean.getCustomerSystemInstalledAddress() == null
																	|| customebean.getCustomerSystemInstalledAddress()
																			.isEmpty()) {
														%>
														<input type="text" id="sysIntalledPlaceAddress"
															value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																if (customebean.getCustomerSystemInstalledPlace()
																		.equalsIgnoreCase("other")) {
														%>
														<p id="sysIntalledPlaceAddress"><%=customebean.getCustomerSystemInstalledAddress()%></p>
														<%
															}// end of inner if
																else {
														%>
														<p id="sysIntalledPlaceAddress"><%=customebean.getCustomerSystemInstalledPlace()
							+ ", "
							+ customebean.getCustomerSystemInstalledAddress()%></p>
														<%
															}
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Address</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getAddress() == null
																	|| customebean.getAddress().equals("")
																	|| customebean.getAddress().isEmpty()) {
														%>
														<input type="text" name="custAddress"
															ng-model="custAddress" id="customerAddress" value="N/A"
															required disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>

														<p id="custAddress"><%=customebean.getAddress()%></p>
														<%
															}
														%>
														<span style="color: red"
															ng-show="customerForm.custAddress.$dirty && customerForm.custAddress.$invalid">
															<span ng-show="customerForm.custAddress.$error.required">Address
																is required.</span>
														</span>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Union
															Council</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerUnionCouncil() == null
																	|| customebean.getCustomerUnionCouncil().isEmpty()) {
														%>
														<input type="text" id="uc" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="uc"
															value="<%=customebean.getCustomerUnionCouncil()%>"
															required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Tehsil,
															District</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getCity_name() == ""
																	|| customebean.getCity_name() == null) {
														%>
														<input type="text" id="cityDistrict" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="cityDistrict"
															value="<%=customebean.getCity_name()%>" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Province</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerProvince() == null
																	|| customebean.getCustomerProvince().isEmpty()) {
														%>
														<input type="text" id="province" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="province"
															value="<%=customebean.getCustomerProvince()%>" required
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br> <br>

												<legend>
													<b>Additional Contact Information</b>
												</legend>

												<table class="table equal table-responsive table-bordered"
													style="font-size: 13px">

													<thead style="background-color: ghostwhite;">
														<tr>
															<th>Name</th>
															<th>Relation</th>
															<th>Phone Number</th>
														</tr>
													</thead>
													<tbody>
														<%
															if (getCustomerOtherPhoneDetails.size() != 0
																	|| getCustomerOtherPhoneDetails != null) {
																for (int i = 0; i < getCustomerOtherPhoneDetails.size(); i++) {
																	String name = getCustomerOtherPhoneDetails.get(i).get(
																			"other_name");
																	String relation = getCustomerOtherPhoneDetails.get(i).get(
																			"relation_with_other");
																	//String phone = getCustomerOtherPhoneDetails.get(i).get("other_phone_number");

																	StringBuilder phone = new StringBuilder(
																			getCustomerOtherPhoneDetails.get(i)
																					.get("other_phone_number")
																					.replace("92", ""));
																	phone = phone.insert(3, "-");
														%>
														<tr>
															<input type="hidden" name="otherphoneIds"
																value="<%=getCustomerOtherPhoneDetails.get(i).get(
							"customer_othre_phone_id")%>">

															<td><input type="text" name="otherPhoneDetails"
																id="otherPhoneDetails" value="<%=name%>" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" name="otherPhoneDetails"
																id="otherPhoneDetails" value="<%=relation%>" disabled
																style="background-color: white; border: none"></td>
															<td><span>(+92) </span><input type="text"
																name="otherPhoneDetails" id="otherPhoneDetails"
																value="<%=phone%>" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<%
															}
															}
														%>
													</tbody>
												</table>
												 <input
													type="submit" class="btn btn-info btn-sm" id="cust"
													value="Edit information"> <input type="submit"
													id="savecustomers"
													ng-disabled="customerForm.user.$dirty && customerForm.user.$invalid 
															|| customerForm.custCnic.$dirty && customerForm.custCnic.$invalid
															|| customerForm.father.$dirty && customerForm.father.$invalid
															|| customerForm.custAddress.$dirty && customerForm.custAddress.$invalid
															|| customerForm.customerPrimaryNumber.$dirty && customerForm.customerPrimaryNumber.$invalid"
													class="btn btn-info" value="Save" style="display: none">
												<input type="submit" id="cancelcust" class="btn btn-info"
													value="Cancel" style="display: none"
													onClick="window.location.reload()"> <span
													class="fa fa-circle-o-notch fa-spin" style="display: none"
													id="customerCircle"></span> <span class="fa fa-check"
													style="color: green; font-size: 18px; display: none"
													id="customerSuccess"><b>Success</b></span>
											</div>

											<div class="col-sm-6 well">

												<Legend>
													<b>Household & Family</b>
												</Legend>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Father
															Name</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getFatherName() == null
																	|| customebean.getFatherName().equals("")
																	|| customebean.getFatherName().isEmpty()) {
														%>
														<input type="text" name="father" ng-model="father"
															id="fatherName" value="N/A" required disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<p id="fatherName"><%=customebean.getFatherName()%></p>
														<%
															}
														%>
														<span style="color: red"
															ng-show="customerForm.father.$dirty && customerForm.father.$invalid">
															<span ng-show="customerForm.father.$error.required">Father
																name is required.</span>
														</span>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Head
															of family</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerIsFamilyHead() == null
																	|| customebean.getCustomerIsFamilyHead().isEmpty()) {
														%>
														<input type="text" id="familyHead" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="familyHead"
															value="<%=customebean.getCustomerIsFamilyHead()%>"
															required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Caste</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getCustomerCaste() == null
																	|| customebean.getCustomerCaste().isEmpty()) {
														%>
														<input type="text" id="caste" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="caste"
															value="<%=customebean.getCustomerCaste()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Household
															members</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerHouseHoldMembers() == null
																	|| customebean.getCustomerHouseHoldMembers().isEmpty()) {
														%>
														<input type="text" id="hhMembers" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="hhMembers"
															value="<%=customebean.getCustomerHouseHoldMembers()%>"
															required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Household
															Members Depends (Under 18)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerDependantMembers() == null
																	|| customebean.getCustomerDependantMembers().isEmpty()) {
														%>
														<input type="text" id="dMembers" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="dMembers"
															value="<%=customebean.getCustomerDependantMembers()%>"
															required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Total
															Household Members (Adult & Above 60)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerAdultMembers() == null
																	|| customebean.getCustomerAdultMembers().isEmpty()) {
														%>
														<input type="text" id="adultMembers" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="adultMembers"
															value="<%=customebean.getCustomerAdultMembers()%>"
															required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Number
															of Childrens going to School</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerChildrens() == null
																	|| customebean.getCustomerChildrens().isEmpty()) {
														%>
														<input type="text" id="childrens" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="childrens"
															value="<%=customebean.getCustomerChildrens()%>" required
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Family
															Type</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerFamilyType() == null
																	|| customebean.getCustomerFamilyType().isEmpty()) {
														%>
														<input type="text" id="familyType" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="familyType"
															value="<%=customebean.getCustomerFamilyType()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Families
															living in Household</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerHouseHoldFamilies() == null
																	|| customebean.getCustomerHouseHoldFamilies().isEmpty()) {
														%>
														<input type="text" id="houseHoldFamilies" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="houseHoldFamilies"
															value="<%=customebean.getCustomerHouseHoldFamilies()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Room
															Occupied By Household (Only bedroom & living room)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerRoomsAccupiedHouseHold() == null
																	|| customebean.getCustomerRoomsAccupiedHouseHold()
																			.isEmpty()) {
														%>
														<input type="text" id="houseHoldRooms" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="houseHoldRooms"
															value="<%=customebean.getCustomerRoomsAccupiedHouseHold()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Residence</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerResidence() == null
																	|| customebean.getCustomerResidence().isEmpty()
																	&& customebean.getCustomerOtherResidence() == null
																	|| customebean.getCustomerOtherResidence().isEmpty()) {
														%>
														<input type="text" id="residence" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																if (customebean.getCustomerResidence()
																		.equalsIgnoreCase("other")) {
														%>
														<p id="otherResidence"><%=customebean.getCustomerOtherResidence()%></p>
														<%
															}// end of inner if
																else {
														%>
														<p id="residence"><%=customebean.getCustomerResidence()%></p>
														<%
															}
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Other
															Residence</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerOtherResidence() == null
																	|| customebean.getCustomerOtherResidence().isEmpty()) {
														%>
														<input type="text" id="otherResidence" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																if (customebean.getCustomerResidence()
																		.equalsIgnoreCase("other")) {
														%>
														<input type="text" id="otherResidence" value="N/A"
															required disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="otherResidence"
															value="<%=customebean.getCustomerOtherResidence()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Residence
															Since (number of years)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerResidencePeriod() == null
																	|| customebean.getCustomerResidencePeriod().isEmpty()) {
														%>
														<input type="text" id="residencePeriod" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="residencePeriod"
															value="<%=customebean.getCustomerResidencePeriod()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br> <br>

												<legend>
													<b>Qualification & Other Information</b>
												</legend>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Education</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getEducation() == null
																	|| customebean.getEducation().equals("")
																	|| customebean.getEducation().isEmpty()) {
														%>
														<select id="slectEducation" disabled
															style="background-color: white; border: none; -webkit-appearance: none; -moz-appearance: none;">
															<option selected disabled>N/A</option>
															<option value="1">Uneducated</option>
															<option value="2">(1-5) Primary</option>
															<option value="3">(6-8) Middle</option>
															<option value="4">(9-10) Secondary</option>
															<option value="5">(11-12) Higher</option>
															<option value="6">(12-14) Diploma/BSc</option>
															<option value="7">(14-16) Graduation</option>
															<option value="8">Post Graduate</option>

														</select>
														<%
															} else {
														%>
														<select id="slectEducation" disabled
															style="background-color: white; border: none; -webkit-appearance: none; -moz-appearance: none;">
															<option selected
																value="<%=customebean.getEducationId()%>"><%=customebean.getEducation()%></option>
															<option value="1">Uneducated</option>
															<option value="2">(1-5) Primary</option>
															<option value="3">(6-8) Middle</option>
															<option value="4">(9-10) Secondary</option>
															<option value="5">(11-12) Higher</option>
															<option value="6">(12-14) Diploma/BSc</option>
															<option value="7">(14-16) Graduation</option>
															<option value="8">Post Graduate</option>

														</select>
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Technical/Vocational
															Training, Course name</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerTrainingSkill() == null
																	|| customebean.getCustomerTrainingSkill().isEmpty()
																	&& customebean.getCustomerCourseName() == null
																	|| customebean.getCustomerCourseName().isEmpty()) {
														%>
														<input type="text" id="trainingSkill" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<%-- <input type="text" id="trainingSkill"
															name="trainingSkill" value="<%=customebean.getCustomerTrainingSkill() +", "+ customebean.getCustomerCourseName() %>" required disabled
															style="background-color: white; border: none" /> --%>

														<p id="trainingSkill"><%=customebean.getCustomerTrainingSkill() + ", "
						+ customebean.getCustomerCourseName()%></p>
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>General1</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getGeneral1() == null
																	|| customebean.getGeneral1().equals("")
																	|| customebean.getGeneral1().isEmpty()) {
														%>
														<input type="text" id="general1" value="N/A"
															placeholder="please insert a string value" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="general1"
															value="<%=customebean.getGeneral1()%>"
															placeholder="please insert a string value" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>General2</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getGeneral2() == null
																	|| customebean.getGeneral2().equals("")
																	|| customebean.getGeneral2().isEmpty()) {
														%>
														<input type="text" id="general2" value="N/A"
															placeholder="please insert a string value" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="general2"
															value="<%=customebean.getGeneral2()%>"
															placeholder="please insert a string value" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>


												<div class="form-group row">
													<label class="col-md-7 control-label"><b>General3</b>
													</label>
													<div class="col-md-5">
														<%
															if (customebean == null || customebean.getGeneral3() == null
																	|| customebean.getGeneral3().equals("")
																	|| customebean.getGeneral3().isEmpty()) {
														%>
														<input type="text" id="general3" value="N/A"
															placeholder="please insert a string value" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="general3"
															value="<%=customebean.getGeneral3()%>"
															placeholder="please insert a string value" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
											</div>
										</form>

									</div>

								</div>
							</div>



							<div class="tab-pane fade " id="gaur">


								<div class="panel-body" style="">
									<div class="table-responsive" style="font-size: 13px">
										<div class="col-sm-6 well">
											<form name="guarantorForm">
												<legend>
													<b>Family Gaurantor</b>
												</legend>


												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Name</b> </label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGguarantorName() == null
																	|| fguarantorBean1.getGguarantorName().isEmpty()
																	|| fguarantorBean1.getGguarantorName() == "") {
														%>
														<input type="text" id="famGaurantorName" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="famGaurantorName"
															value="<%=fguarantorBean1.getGguarantorName()%>"
															placeholder="please insert a number" required disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>CNIC</b> </label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGguarantorCnic() == null
																	|| fguarantorBean1.getGguarantorCnic().isEmpty()
																	|| fguarantorBean1.getGguarantorCnic() == "") {
														%>
														<input type="text" id="famGaurantorCnic" value="N/A"
															disabled style="background-color: white; border: none"
															onkeypress="setNicDash(event,'famGaurantorCnic')" />
														<%
															} else {
														%>
														<input type="text" id="famGaurantorCnic"
															value="<%=fguarantorBean1.getGguarantorCnic()%>"
															onkeypress="setNicDash(event,'famGaurantorCnic')"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Primary
															Phone</b> </label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGguarantorPhone() == null
																	|| fguarantorBean1.getGguarantorPhone() == "92"
																	|| fguarantorBean1.getGguarantorPhone().equals("92")
																	|| fguarantorBean1.getGguarantorPhone() == "") {
														%>
														<span>(+92)</span> <input type="text"
															id="famGaurantorPhone" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																StringBuilder fgcell = new StringBuilder(fguarantorBean1
																		.getGguarantorPhone().replace("92", ""));
														%>
														<span>(+92)</span> <input type="text"
															id="famGaurantorPhone" value="<%=fgcell.insert(3, "-")%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Secondary
															Phone</b> </label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGuarantorSecondaryPhone() == null
																	|| fguarantorBean1.getGuarantorSecondaryPhone() == "92"
																	|| fguarantorBean1.getGuarantorSecondaryPhone()
																			.equals("92")
																	|| fguarantorBean1.getGuarantorSecondaryPhone() == "") {
														%>
														<span>(+92)</span> <input type="text"
															id="famGaurantorSecondaryPhone" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																StringBuilder fgcell = new StringBuilder(fguarantorBean1
																		.getGuarantorSecondaryPhone().replace("92", ""));
														%>
														<span>(+92)</span> <input type="text"
															id="famGaurantorSecondaryPhone"
															value="<%=fgcell.insert(3, "-")%>" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Relationship</b>
													</label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getgRelationToCustomer() == null
																	|| fguarantorBean1.getgRelationToCustomer().isEmpty()
																	|| fguarantorBean1.getgRelationToCustomer() == "") {
														%>
														<input type="text" id="famGaurantorRelation" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="famGaurantorRelation"
															value="<%=fguarantorBean1.getgRelationToCustomer()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Monthly
															Income</b> </label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGguarantorIncome() == null
																	|| fguarantorBean1.getGguarantorIncome() == ""
																	|| fguarantorBean1.getGguarantorIncome().isEmpty()) {
														%>
														<input type="text" id="famGaurantorIncome" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {

																String fmincome = NumberFormat.getNumberInstance(Locale.US)
																		.format(Math.round(Integer.parseInt(fguarantorBean1
																				.getGguarantorIncome())));
																fmincome = fmincome.concat(" PKR");
														%>
														<span id="famGaurantorIncomeSpan"><%=fmincome.split(" ")[0]%>
															<b><%=fmincome.split(" ")[1]%></b></span> <input type="hidden"
															id="famGaurantorIncome"
															value="<%=fguarantorBean1.getGguarantorIncome()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGuarantorId() == 0) {
														%>
														<input type="hidden" id="famGaurantorId" value="0">
														<%
															} else {
														%>
														<input type="hidden" id="famGaurantorId"
															value="<%=fguarantorBean1.getGuarantorId()%>">
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Profession</b>
													</label>
													<div class="col-md-5">
														<%
															if (fguarantorBean1 == null
																	|| fguarantorBean1.getGuarantorProfession() == null
																	|| fguarantorBean1.getGuarantorProfession().isEmpty()
																	|| fguarantorBean1.getGuarantorProfession() == "") {
														%>
														<input type="text" id="famGaurantorProfession" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="famGaurantorProfession"
															value="<%=fguarantorBean1.getGuarantorProfession()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>
											</form>


										</div>

										<div class="col-sm-6 well">
											<legend>
												<b>Outside Gaurantor</b>
											</legend>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Name</b></label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGguarantorName() == ""
																|| oguarantorBean2.getGguarantorName() == null
																|| oguarantorBean2.getGguarantorName().isEmpty()) {
													%>
													<input type="text" id="outGaurantorName" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>
													<input type="text" id="outGaurantorName"
														value="<%=oguarantorBean2.getGguarantorName()%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>
											<div class="form-group row">
												<label class="col-md-7 control-label"><b>CNIC</b> </label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGguarantorCnic() == ""
																|| oguarantorBean2.getGguarantorCnic().isEmpty()) {
													%>
													<input type="text" id="outGaurantorCnic" value="N/A"
														onkeypress="setNicDash(event,'outGaurantorCnic')" disabled
														style="background-color: white; border: none" />
													<%
														} else {
													%>
													<input type="text" id=outGaurantorCnic
														value="<%=oguarantorBean2.getGguarantorCnic()%>"
														onkeypress="setNicDash(event,'outGaurantorCnic')" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>
											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Phone</b> </label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGguarantorPhone() == null
																|| oguarantorBean2.getGguarantorPhone().equals("92")
																|| oguarantorBean2.getGguarantorPhone().isEmpty()) {
													%>
													<span>(+92)</span> <input type="text"
														id="outGaurantorPhone" value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
															StringBuilder ogcell = new StringBuilder(oguarantorBean2
																	.getGguarantorPhone().replace("92", ""));
													%>
													<span>(+92)</span> <input type="text" id=outGaurantorPhone
														value="<%=ogcell.insert(3, "-")%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Secondary
														Phone</b> </label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGuarantorSecondaryPhone() == null
																|| oguarantorBean2.getGuarantorSecondaryPhone()
																		.equals("92")
																|| oguarantorBean2.getGuarantorSecondaryPhone() == "") {
													%>
													<span>(+92)</span> <input type="text"
														id="outGaurantorSecondaryPhone" value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
															StringBuilder fgcell = new StringBuilder(oguarantorBean2
																	.getGuarantorSecondaryPhone().replace("92", ""));
													%>
													<span>(+92)</span> <input type="text"
														id="outGaurantorSecondaryPhone"
														value="<%=fgcell.insert(3, "-")%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Relationship</b>
												</label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getgRelationToCustomer() == null
																|| oguarantorBean2.getgRelationToCustomer().isEmpty()) {
													%>
													<input type="text" id="outGaurantorRelation" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>
													<input type="text" id="outGaurantorRelation"
														value="<%=oguarantorBean2.getgRelationToCustomer()%>"
														disabled style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Monthly
														Income</b> </label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGguarantorIncome() == null
																|| oguarantorBean2.getGguarantorIncome().isEmpty()) {
													%>
													<input type="text" id="outGaurantorIncome" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {

															String fmincome = NumberFormat.getNumberInstance(Locale.US)
																	.format(Math.round(Integer.parseInt(oguarantorBean2
																			.getGguarantorIncome())));
															fmincome = fmincome.concat(" PKR");
													%>
													<span id="outGaurantorIncomeSpan"><%=fmincome.split(" ")[0]%>
														<b><%=fmincome.split(" ")[1]%></b></span> <input type="hidden"
														id="outGaurantorIncome"
														value="<%=oguarantorBean2.getGguarantorIncome()%>"
														disabled style="background-color: white; border: none" />
													<%
														}
													%>
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGuarantorId() == 0) {
													%>
													<input type="hidden" id="outGaurantorId" value="0">
													<%
														} else {
													%>
													<input type="hidden" id="outGaurantorId"
														value="<%=oguarantorBean2.getGuarantorId()%>">
													<%
														}
													%>
												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Profession</b>
												</label>
												<div class="col-md-5">
													<%
														if (oguarantorBean2 == null
																|| oguarantorBean2.getGuarantorProfession() == null
																|| oguarantorBean2.getGuarantorProfession().isEmpty()
																|| oguarantorBean2.getGuarantorProfession() == null) {
													%>
													<input type="text" id="outGaurantorProfession" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>
													<input type="text" id="outGaurantorProfession"
														value="<%=oguarantorBean2.getGuarantorProfession()%>"
														disabled style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>
										</div>

										<div id="gaurantors">
											<input type="submit" id="garantorButton"
												value="Edit Information" class="btn btn-info btn-sm">
											<input type="submit" id="saveGaran" class="btn btn-info"
												value="Save" style="display: none"> <input
												type="submit" id="cancelGarantor" class="btn btn-info"
												value="Cancel" style="display: none"> <span
												class="fa fa-circle-o-notch fa-spin" style="display: none"
												id="guarantorCircle"></span> <span class="fa fa-check"
												style="color: green; font-size: 18px; display: none;"
												id="guarantorSuccess"><b>Success</b></span>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="income">
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px">

										<div class="col-sm-6 well">

											<legend>
												<b>Monthly Income</b>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


												<%
													double dynamicAmountIncome = 0;
													if (otherIncomes.size() != 0 || otherIncomes != null) {
														for (int i = 0; i < otherIncomes.size(); i++) {
															String amount = NumberFormat.getNumberInstance(Locale.US)
																	.format(Math.round(Integer.parseInt(otherIncomes
																			.get(i).get("amount"))));

															int dynamicAmount = Integer.parseInt(otherIncomes.get(i)
																	.get("amount"));
															System.out.println("Dynamic amount Income: == "
																	+ dynamicAmount);

															dynamicAmountIncome += dynamicAmount;

															System.out.println("Dynamic amount Income: == "
																	+ dynamicAmountIncome);
														}
													}

													if (customebean == null
															|| customebean.getCustomerTotalIncomeName() == 0.0
															|| customebean.getCustomerTotalIrregularlyIncomeName() == 0.0) {
														double netIncome = (customebean
																.getCustomerTotalIrregularlyIncomeName() / 12)
																+ customebean.getCustomerTotalIncomeName();
												%>
												<b><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(dynamicAmountIncome + netIncome))%> PKR</b>
												<%
													} else {
														double netIncome = (customebean
																.getCustomerTotalIrregularlyIncomeName() / 12)
																+ customebean.getCustomerTotalIncomeName();
														System.out.println("Net Income ========= ///// " + netIncome);
												%>
												<b><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(dynamicAmountIncome + netIncome))%> PKR</b>
												<%
													}
												%>

											</legend>
											<div class="form-group row" sty>
												<label class="col-md-7 control-label" id="totalIncome"><b
													style="font-size: 15px">Total Monthly Income</b> </label>

												<div class="col-md-5">
													<%
														if (customebean == null
																|| customebean.getCustomerTotalIncomeName() == 0.0) {
													%>

													<input type="text" id="totalIncomeName"
														class="income_count"
														value="<%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(customebean.getSalary()
								+ customebean.getBusinessIncome()
								+ customebean.getCustomerRemittances()
								+ customebean.getCustomerRentalProfitIncome()
								+ customebean.getCustomerLabourAmount()))%>  PKR"
														disabled
														style="background-color: white; border: none; font-weight: bold;" />
													<%
														} else {
															String monthlyIncome = NumberFormat
																	.getNumberInstance(Locale.US).format(
																			customebean.getCustomerTotalIncomeName());
															monthlyIncome = monthlyIncome.concat(" PKR");
													%>
													<b><span id="totalIncomeName"><%=monthlyIncome.split(" ")[0]%>
															<b><%=monthlyIncome.split(" ")[1]%></b></span><input
														type="hidden" id="totalIncomeName" class="income_count"
														value="<%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(customebean.getSalary()
								+ customebean.getBusinessIncome()
								+ customebean.getCustomerRemittances()
								+ customebean.getCustomerRentalProfitIncome()
								+ customebean.getCustomerLabourAmount()))%>  PKR"
														disabled
														style="background-color: white; border: none; font-weight: bold;" /></b>
													<%
														}
													%>

												</div>
											</div>
											<br>
											<div class="form-group row">
												<label class="col-md-7 control-label"><b
													style="font-size: 15px">Total Irregularly Income</b> </label>

												<div class="col-md-5">
													<%
														if (customebean == null
																|| customebean.getCustomerTotalIrregularlyIncomeName() == 0.0) {
													%>

													<input type="text" id="totalIrregularilyIncomeName"
														class="income_count" value="N/A" disabled
														style="background-color: white; border: none;" />
													<%
														} else {
															String bincome = NumberFormat.getNumberInstance(Locale.US)
																	.format(customebean
																			.getCustomerTotalIrregularlyIncomeName());
															bincome = bincome.concat(" PKR");
													%>
													<b><span id="totalIrregularilyIncomeName"><%=bincome.split(" ")[0]%>
															<b><%=bincome.split(" ")[1]%></b></span> <input type="hidden"
														id=totalIrregularilyIncomeName class="income_count"
														value="<%=customebean.getCustomerTotalIrregularlyIncomeName()%>"
														disabled style="background-color: white; border: none;" /></b>
													<%
														}
													%>
												</div>
											</div>
											<legend></legend>
											<br> <br>


											<legend>
												<b>Monthly Income Details</b>
											</legend>
											<form name="incomeForm" id="incomeForm">

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Remittances
															Frequency (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerRemittancesFrequency() == null
																	|| customebean.getCustomerRemittancesFrequency().equals("")
																	|| customebean.getCustomerRemittancesFrequency().isEmpty()) {
														%>
														<input type="text" id="customerRemittanceFrequency"
															value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getCustomerRemittances());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="customerRemittanceFrequency"><%=customebean.getCustomerRemittancesFrequency() + " ("
						+ afaimly.split(" ")[0]%> <b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Remittances
															Relation</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerRemittancesRelation() == null
																	|| customebean.getCustomerRemittancesRelation().equals("")
																	|| customebean.getCustomerRemittancesRelation().isEmpty()) {
														%>
														<input type="text" id="customerRemittanceRelation"
															value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="customerRemittanceRelation"
															value="<%=customebean.getCustomerRemittancesRelation()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Remittances
															Profession</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerRemittancesProfession() == null
																	|| customebean.getCustomerRemittancesProfession()
																			.equals("")
																	|| customebean.getCustomerRemittancesProfession().isEmpty()) {
														%>
														<input type="text" id="customerRemittanceProfession"
															value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="customerRemittanceProfession"
															value="<%=customebean.getCustomerRemittancesProfession()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Rental
															Frequency, Rental Income (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerRentalProfitFrequency() == null
																	|| customebean.getCustomerRentalProfitFrequency()
																			.equals("")
																	|| customebean.getCustomerRentalProfitFrequency().isEmpty()) {
														%>
														<input type="text" id="rentalFrequencyAmount" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getCustomerRentalProfitIncome());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="rentalFrequencyAmount"><%=customebean.getCustomerRentalProfitFrequency() + " ("
						+ afaimly.split(" ")[0]%> <b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Rental
															Profit Income Source</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerRentalIncomeSource() == null
																	|| customebean.getCustomerRentalIncomeSource().equals("")
																	|| customebean.getCustomerRentalIncomeSource().isEmpty()) {
														%>
														<input type="text" id="rentalIncomeSource" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="rentalIncomeSource"
															value="<%=customebean.getCustomerRentalIncomeSource()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Labour
															Type</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerLabourType() == null
																	|| customebean.getCustomerLabourType().equals("")
																	|| customebean.getCustomerLabourType().isEmpty()) {
														%>
														<input type="text" id="labourType" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																if (customebean.getCustomerLabourType().equalsIgnoreCase(
																		"other")) {
														%>
														<p id="otherLabourType"><%=customebean.getCustomerOtherLabourType()%></p>
														<%
															} else {
														%>
														<p id="labourType"><%=customebean.getCustomerLabourType()%></p>
														<%
															}
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Labour
															Amount (Daily)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerLabourAmount() == 0) {
														%>
														<input type="text" id="labourAmount" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getCustomerLabourAmount());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="labourAmount"><%=afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1]%></b></span> <input type="hidden"
															id="labourAmount" class="labour_amount"
															value="<%=customebean.getCustomerLabourAmount()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Other
															Labour Type</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerOtherLabourType() == null
																	|| customebean.getCustomerOtherLabourType().equals("")
																	|| customebean.getCustomerOtherLabourType().isEmpty()) {
														%>
														<input type="text" id="otherLabourType" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																if (customebean.getCustomerLabourType().equalsIgnoreCase(
																		"other")) {
														%>
														<input type="text" id="otherLabourType" value="N/A"
															disabled style="background-color: white; border: none" />

														<%
															} else {
														%>
														<input type="text" id="otherLabourType"
															value="<%=customebean.getCustomerOtherLabourType()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>No of
															Labour Days in Month</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerLabourInMonth() == null
																	|| customebean.getCustomerLabourInMonth().equals("")
																	|| customebean.getCustomerLabourInMonth().isEmpty()) {
														%>
														<input type="text" id="labourDays" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
														%>
														<input type="text" id="labourDays"
															value="<%=customebean.getCustomerLabourInMonth()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br> <br>

												<legend>
													<b>Irregularly Income Details</b>
												</legend>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Agri
															income frequency (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerAgricultureProfitFrequency() == null
																	|| customebean.getCustomerAgricultureProfitFrequency()
																			.equals("")
																	|| customebean.getCustomerAgricultureProfitFrequency()
																			.isEmpty() && customebean.getFarming() == 0) {
														%>
														<input type="text" id="agriFrequency" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getFarming());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="landAmount"><%=customebean.getCustomerAgricultureProfitFrequency()
						+ " (" + afaimly.split(" ")[0]%> <b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Live
															stock Type</b> </label>
													<div class="col-md-5">
														<%
															if ((customebean == null
																	|| customebean.getCustomerLiveStockIncomeType() == null
																	|| customebean.getCustomerLiveStockIncomeType().equals("") || customebean
																	.getCustomerLiveStockIncomeType().isEmpty())
																	&& (customebean.getCustomerLiveStockOtherIncomeType() == null
																			|| customebean
																					.getCustomerLiveStockOtherIncomeType()
																					.equals("") || customebean
																			.getCustomerLiveStockOtherIncomeType().isEmpty())) {
														%>
														<input type="text" id="liveStockType" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																if (customebean.getCustomerLiveStockIncomeType()
																		.equalsIgnoreCase("other")) {
														%>
														<p id="liveStockOtherIncomeType"><%=customebean.getCustomerLiveStockOtherIncomeType()%></p>

														<%
															} else {
														%>
														<p id="liveStockType"><%=customebean.getCustomerLiveStockIncomeType()%></p>
														<%
															}
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Live
															stock (Profit)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerLiveStockIncome() == 0) {
														%>
														<input type="text" id="liveStockAmount" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getCustomerLiveStockIncome());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="liveStockAmount"><%=afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1]%></b></span> <input type="hidden"
															id="liveStockAmount" class="live_stock_amount"
															value="<%=customebean.getCustomerLiveStockIncome()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Other
															Income Contributors (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerIncomeContributors() == null
																	|| customebean.getCustomerIncomeContributors().equals("")
																	|| customebean.getCustomerIncomeContributors().isEmpty()) {
														%>
														<input type="text" id="incomeContributors" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getFamilyContribution());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="incomeContributorsAmount"><%=customebean.getCustomerIncomeContributors() + " ("
						+ afaimly.split(" ")[0]%> <b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>

													</div>
												</div>
												<br>


											</form>
											<%-- 											<%}	%> --%>


											<div id="customerIncomeDetails">
												<input type="submit" class="btn btn-info btn-sm"
													id="customerIncomeButton" value="Edit Information">
												<input type="submit" id="saveCustomerIncome"
													class="btn btn-info" value="Save" style="display: none">
												<input type="submit" id="canceCustomerIncome"
													class="btn btn-info" value="Cancel" style="display: none">
												<span class="fa fa-circle-o-notch fa-spin" id="incomeCircle"
													style="display: none"></span> <span class="fa fa-check"
													style="color: green; font-size: 18px; display: none"
													id="incomeSuccess"><b>Success</b></span>

											</div>
										</div>


										<div class="col-sm-6 well">
											<legend>
												<b>Additional Income Details</b>
											</legend>

											<table class="table equal table-responsive table-bordered"
												style="font-size: 13px">

												<thead style="background-color: ghostwhite;">
													<tr>
														<th>Detail</th>
														<th>Amount</th>
													</tr>
												</thead>
												<tbody>
													<%
														if (otherIncomes.size() != 0 || otherIncomes == null) {
															for (int i = 0; i < otherIncomes.size(); i++) {
																String amount = NumberFormat.getNumberInstance(Locale.US)
																		.format(Math.round(Integer.parseInt(otherIncomes
																				.get(i).get("amount"))));
																amount = amount.concat(" PKR");
													%>
													<tr>
														<input type="hidden" name="otherIncomeIds"
															value="<%=otherIncomes.get(i).get("incomeId")%>">

														<td><input type="text" name="otherIncomeDetail"
															id="otherIncomeDetail"
															value="<%=otherIncomes.get(i).get("detail")%>" disabled
															style="background-color: white; border: none"></td>
														<td><span id="otherIncomeAmountSpan"><%=amount.split(" ")[0]%>
																<b><%=amount.split(" ")[1]%></b></span> <input type="hidden"
															name="otherIncomeAmount" id="otherIncomeAmount"
															value="<%=otherIncomes.get(i).get("amount")%>" disabled
															style="background-color: white; border: none"></td>

													</tr>
													<%
														}
														}
													%>
												</tbody>
											</table>
											<br> <br>


											<legend>
												<b>Business Details</b>
											</legend>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Business
														Income (monthly)</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getBusinessIncome() == 0.0) {
													%>

													<input type="text" id="businessIncome" value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
															String bincome = NumberFormat.getNumberInstance(Locale.US)
																	.format(customebean.getBusinessIncome());
															bincome = bincome.concat(" PKR");
													%>
													<span id="businessIncomeSpan"><%=bincome.split(" ")[0]%>
														<b><%=bincome.split(" ")[1]%></b></span> <input type="hidden"
														id=businessIncome class="income_count"
														value="<%=customebean.getBusinessIncome()%>" disabled
														style="background-color: white; border: none" />

													<!-- <input type="text"   id="businessIncome" class="income_count" value="N/A"  disabled style="background-color:white;border:none" /> -->
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Business
														Title</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getBusinessName() == ""
																|| customebean.getBusinessName() == null
																|| customebean.getBusinessName().isEmpty()) {
													%>
													<input type="text" id="customerBusinessName" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>

													<input type="text" id="customerBusinessName"
														value="<%=customebean.getBusinessName()%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Business
														Type </b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getBusinessType() == ""
																|| customebean.getBusinessType() == null
																|| customebean.getBusinessType().isEmpty()) {
													%>
													<input type="text" id="customerBusinessType" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>
													<p id="customerBusinessType"><%=customebean.getBusinessType()%></p>
													<%
														}
													%>

												</div>
											</div>
											<br>


											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Telephone
												</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getOfficePhone() == ""
																|| customebean.getOfficePhone() == "92"
																|| customebean.getOfficePhone() == null) {
													%>
													<span>(+92)</span> <input type="text"
														id="customerBusinessPhone" value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
															StringBuilder tele = new StringBuilder(customebean
																	.getOfficePhone().replace("92", ""));
															System.err.print(customebean.getOfficePhone()
																	+ "   tele:::::::::: " + tele);
															if (tele.length() != 0) {
																tele.insert(3, "-");
													%>

													<span>(+92)</span> <input type="text"
														id="customerBusinessPhone" value="<%=tele%>" disabled
														style="background-color: white; border: none" />
													<%
														} else {
													%>
													<span>(+92)</span> <input type="text"
														id="customerBusinessPhone" value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														}
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Years
														in Business </b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getPeriod() == ""
																|| customebean.getPeriod() == null) {
													%>
													<input type="text" id="bussinessPeriod" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>

													<input type="text" id="bussinessPeriod"
														value="<%=customebean.getPeriod()%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>


											<div class="form-group row">
												<label class="col-md-7 control-label"><b>
														Address </b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getBusinessAddress() == ""
																|| customebean.getBusinessAddress() == null) {
													%>
													<input type="text" id="customerBusinessAddress" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>
													<%-- <input type="text" id="customerBusinessAddress"
															value="<%=customebean.getBusinessAddress()%>" disabled
															style="background-color: white; border: none" /> --%>
													<p id="customerBusinessAddress"><%=customebean.getBusinessAddress()%></p>
													<%
														}
													%>

												</div>
											</div>
											<br>


											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Comments
												</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getComment() == ""
																|| customebean.getComment() == null) {
													%>
													<input type="text" id="customerBusinessComment" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>

													<input type="text" id="customerBusinessComment"
														value="<%=customebean.getComment()%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>
													<input type="hidden" id="businessId"
														value="<%=customebean.getBusinessid()%>">
												</div>
											</div>
											<br> <br>


											<legend>
												<b>Employment Details</b>
											</legend>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Salary/Pension</b>
												</label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getSalary() == 0.0) {
													%>

													<input type="text" id="salaryOrPansion"
														class="income_count" value="N/A" disabled
														style="background-color: white; border: none; font-weight: bold;" />

													<!-- <input type="text"  class="income_count"  id="salaryOrPansion"  value="N/A"  disabled style="background-color:white;border:none" /> -->

													<%
														} else {
															int salary = (int) customebean.getSalary();
															String salarypkr = NumberFormat.getNumberInstance(Locale.US)
																	.format(salary);
															salarypkr = salarypkr.concat(" PKR");

															String salarytxt = NumberFormat.getNumberInstance(Locale.US)
																	.format(salary);
													%>
													<span id="salaryOrPansionSpan"><%=salarypkr.split(" ")[0]%>
														<b><%=salarypkr.split(" ")[1]%></b></span> <input type="hidden"
														id=salaryOrPansion class="income_count"
														value="<%=salary%>" disabled
														style="background-color: white; border: none;" />
													<!-- <span  style="margin-left: -100px;"><b> PKR</b></span> -->

													<%-- <input type="text" id=salaryOrPansion class="income_count" value="<%=salary%>"   disabled style="background-color:white;border:none" /> --%>

													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Company/Org.
														Name</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getOrganisationName() == ""
																|| customebean.getOrganisationName() == null) {
													%>
													<input type="text" id="customerEmploymentOrganization"
														value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
													%>

													<%-- <input type="text" id="customerEmploymentOrganization"
															value="<%=customebean.getOrganisationName()%>" disabled
															style="background-color: white; border: none" /> --%>

													<p id="customerEmploymentOrganization"><%=customebean.getOrganisationName()%></p>
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Designation
												</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getJobPosition() == ""
																|| customebean.getJobPosition() == null) {
													%>
													<input type="text" id="customerEmploymentPosition"
														value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
													%>
													<p id="customerEmploymentPosition"><%=customebean.getJobPosition()%></p>
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Length
														of Job </b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getJobPeriod() == ""
																|| customebean.getJobPeriod() == null) {
													%>
													<input type="text" id="customerEmploymentPeriod"
														value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
													%>

													<input type="text" id="customerEmploymentPeriod"
														value="<%=customebean.getJobPeriod()%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>


											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Office
														Telephone </b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getOrgPhone() == "92"
																|| customebean.getOrgPhone() == null
																|| customebean.getOrgPhone() == "") {
													%>
													<span>(+92)</span> <input type="text"
														id="customerEmploymentOrgPhone" value="N/A" disabled
														style="background-color: white; border: none" />
													<%
														} else {
															StringBuilder officetele = new StringBuilder(customebean
																	.getOrgPhone().replace("92", ""));
													%>

													<span>(+92)</span> <input type="text"
														id="customerEmploymentOrgPhone"
														value="<%=officetele.insert(3, "-")%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>

											<div class="form-group row">
												<label class="col-md-7 control-label"><b>Supervisor
														Name</b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getSupervisorName() == ""
																|| customebean.getSupervisorName() == null) {
													%>
													<input type="text" id="EmployementSupervisor" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>

													<input type="text" id="EmployementSupervisor"
														value="<%=customebean.getSupervisorName()%>" disabled
														style="background-color: white; border: none" />
													<%
														}
													%>

												</div>
											</div>
											<br>
											<div class="form-group row">
												<label class="col-md-7 control-label"><b>
														Address </b> </label>
												<div class="col-md-5">
													<%
														if (customebean == null || customebean.getOrgAddress() == ""
																|| customebean.getOrgAddress() == null
																|| customebean.getOrgAddress().isEmpty()) {
													%>
													<input type="text" id="EmployementAddress" value="N/A"
														disabled style="background-color: white; border: none" />
													<%
														} else {
													%>
													<p id="EmployementAddress"><%=customebean.getOrgAddress()%></p>
													<%
														}
													%>
													<input type="hidden" id="employementId"
														value="<%=customebean.getEmployementId()%>">
												</div>
											</div>

										</div>





									</div>
								</div>
							</div>
							<div class="tab-pane fade " id="geninfo">
								<div class="row">
									<div class="panel-body">
										<div class="table-responsive" style="font-size: 13px">
											<div class="col-sm-6 well">

												<legend>
													<b>Assets</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<%
														double dynamicAmountAssets = 0;
														if (!assetsListget.isEmpty() || assetsListget != null) {
															for (int i = 0; i < assetsListget.size(); i++) {
																String aamount = NumberFormat.getNumberInstance(Locale.US)
																		.format(Integer.parseInt(assetsListget.get(i).get(
																				"amount")));
																aamount = aamount.concat(" PKR");

																int dynamicAmount = Integer.parseInt(assetsListget.get(i)
																		.get("amount"));
																//System.out.println("Dynamic amount : == " + dynamicAmount);

																dynamicAmountAssets += dynamicAmount;

																//System.out.println("Dynamic amount : == " + dynamicAmountAssets);
															}
														}

														if (totalLiveStockAssetsList == null
																|| totalLiveStockAssetsList
																		.get("customerTotalAssetsIncome") == null) {
													%>
													<span><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(dynamicAmountAssets))%> PKR</span>
													<%
														} else {
													%>
													<b><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(dynamicAmountAssets
								+ Double.parseDouble(totalLiveStockAssetsList
										.get("customerTotalAssetsIncome"))))%> PKR</b>
													<%
														}
													%>

													<%-- <b><%=NumberFormat.getNumberInstance(Locale.US).format(Math.round(Double.parseDouble(totalLiveStockAssetsList.get("customerTotalAssetsIncome")))) %> PKR</b> --%>

												</legend>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Land
															in acres (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerLand") == null
																	|| totalLiveStockAssetsList.get("customerLand") == ""
																	&& totalLiveStockAssetsList.get("customerLandAmount") == null
																	|| totalLiveStockAssetsList.get("customerLandAmount") == "") {
														%>
														<input type="text" id="land" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String land = totalLiveStockAssetsList.get("customerLand");
																String landAmount = totalLiveStockAssetsList
																		.get("customerLandAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(landAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="landAmount"><%=land + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Buffalo
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerBuffalo") == null
																	|| totalLiveStockAssetsList.get("customerBuffalo") == ""
																	&& totalLiveStockAssetsList.get("customerBuffaloAmount") == null
																	|| totalLiveStockAssetsList.get("customerBuffaloAmount") == "") {
														%>
														<input type="text" id="buffalo" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String buffalo = totalLiveStockAssetsList
																		.get("customerBuffalo");
																String buffaloAmount = totalLiveStockAssetsList
																		.get("customerBuffaloAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(buffaloAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="buffaloAmount"><%=buffalo + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Cow
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerCow") == null
																	|| totalLiveStockAssetsList.get("customerCow") == ""
																	&& totalLiveStockAssetsList.get("customerCowAmount") == null
																	|| totalLiveStockAssetsList.get("customerCowAmount") == "") {
														%>
														<input type="text" id="cow" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String cow = totalLiveStockAssetsList.get("customerCow");
																String cowAmount = totalLiveStockAssetsList
																		.get("customerCowAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(cowAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="cowAmount"><%=cow + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Calf
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerCalf") == null
																	|| totalLiveStockAssetsList.get("customerCalf") == ""
																	&& totalLiveStockAssetsList.get("customerCalfAmount") == null
																	|| totalLiveStockAssetsList.get("customerCalfAmount") == "") {
														%>
														<input type="text" id="calf" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String calf = totalLiveStockAssetsList.get("customerCalf");
																String calfAmount = totalLiveStockAssetsList
																		.get("customerCalfAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(calfAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="calfAmount"><%=calf + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Goat/Sheep
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerGoatSheep") == null
																	|| totalLiveStockAssetsList.get("customerGoatSheep") == ""
																	&& totalLiveStockAssetsList.get("customerGoatSheepAmount") == null
																	|| totalLiveStockAssetsList.get("customerGoatSheepAmount") == "") {
														%>
														<input type="text" id="goatSheep" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String goatSheep = totalLiveStockAssetsList
																		.get("customerGoatSheep");
																String goatSheepAmount = totalLiveStockAssetsList
																		.get("customerGoatSheepAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(goatSheepAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="goatSheepAmount"><%=goatSheep + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>No of
															Motar Bikes (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerBike") == null
																	|| totalLiveStockAssetsList.get("customerBike") == ""
																	&& totalLiveStockAssetsList.get("customerBikeAmount") == null
																	|| totalLiveStockAssetsList.get("customerBikeAmount") == "") {
														%>
														<input type="text" id="bike" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String bike = totalLiveStockAssetsList.get("customerBike");
																String bikeAmount = totalLiveStockAssetsList
																		.get("customerBikeAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(bikeAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="bikeAmount"><%=bike + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>No. of
															Cars (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerCar") == null
																	|| totalLiveStockAssetsList.get("customerCar") == ""
																	&& totalLiveStockAssetsList.get("customerCarAmount") == null
																	|| totalLiveStockAssetsList.get("customerCarAmount") == "") {
														%>
														<input type="text" id="car" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String car = totalLiveStockAssetsList.get("customerCar");
																String carAmount = totalLiveStockAssetsList
																		.get("customerCarAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(carAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="carAmount"><%=car + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>No. of
															Tractors (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerTractors") == null
																	|| totalLiveStockAssetsList.get("customerTractors") == ""
																	&& totalLiveStockAssetsList.get("customerTractorsAmount") == null
																	|| totalLiveStockAssetsList.get("customerTractorsAmount") == "") {
														%>
														<input type="text" id="tractor" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String tractor = totalLiveStockAssetsList
																		.get("customerTractors");
																String tractorAmount = totalLiveStockAssetsList
																		.get("customerTractorsAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(tractorAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="tractorAmount"><%=tractor + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>TV
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerTv") == null
																	|| totalLiveStockAssetsList.get("customerTv") == ""
																	&& totalLiveStockAssetsList.get("customerTvAmount") == null
																	|| totalLiveStockAssetsList.get("customerTvAmount") == "") {
														%>
														<input type="text" id="tv" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String tv = totalLiveStockAssetsList.get("customerTv");
																String tvAmount = totalLiveStockAssetsList
																		.get("customerTvAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(tvAmount));
																afaimly = afaimly.concat(" PKR");
																if (tv.equals("1")) {
																	tv = "Yes";
																} else {
																	tv = "No";
																}
														%>
														<span id="tvAmount"><%=tv + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Fridge
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalLiveStockAssetsList == null
																	|| totalLiveStockAssetsList.get("customerFridge") == null
																	|| totalLiveStockAssetsList.get("customerFridge") == ""
																	&& totalLiveStockAssetsList.get("customerFridgeAmount") == null
																	|| totalLiveStockAssetsList.get("customerFridgeAmount") == "") {
														%>
														<input type="text" id="fridge" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String fridge = totalLiveStockAssetsList.get("customerFridge");
																String fridgeAmount = totalLiveStockAssetsList
																		.get("customerFridgeAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(fridgeAmount));
																afaimly = afaimly.concat(" PKR");

																if (fridge.equals("1")) {
																	fridge = "Yes";
																} else {
																	fridge = "No";
																}
														%>
														<span id="fridgeAmount"><%=fridge + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>

														<%
															}
														%>
													</div>
												</div>
												<br> <br>

												<legend>
													<b>Additional Assets</b>
												</legend>
												<table id="assetTable"
													class="table equal table-responsive table-bordered"
													style="font-size: 13px">

													<thead style="background-color: ghostwhite;">
														<th>Asset Type</th>
														<th>Asset Amount</th>
													</thead>
													<tbody>
														<%
															if (!assetsListget.isEmpty() || assetsListget != null) {
																for (int i = 0; i < assetsListget.size(); i++) {
																	String aamount = NumberFormat.getNumberInstance(Locale.US)
																			.format(Integer.parseInt(assetsListget.get(i).get(
																					"amount")));
																	aamount = aamount.concat(" PKR");

																	/* int dynamicAmount = Integer.parseInt(assetsListget.get(i).get("amount"));
																	System.out.println("Dynamic amount : == " + dynamicAmount);
																	
																	dynamicAmountAssets += dynamicAmount;
																	
																	System.out.println("Dynamic amount : == " + dynamicAmountAssets); */
														%>
														<tr>
															<td style="display: none"><input type="hidden"
																name="assetsIDs"
																value="<%=assetsListget.get(i).get("assetId")%>"></td>
															<td><input type="text" id="assetsType<%=i%>"
																name="assetsType"
																value="<%=assetsListget.get(i).get("type")%>" disabled
																style="background-color: white; border: none"></td>
															<td><span id="assetsAmountSpan"><%=aamount.split(" ")[0]%>
																	<b><%=aamount.split(" ")[1]%></b></span> <input type="hidden"
																id="assetsAmount" name="assetsAmount"
																value="<%=assetsListget.get(i).get("amount")%>" disabled
																style="background-color: white; border: none"></td>

														</tr>
														<%
															}//end for loop
															}//end if   
															else {
														%>
														<tr>
															<td style="display: none"><input type="hidden"
																name="assetsIDs" value="0"></td>
															<td><input type="text" id="assetsType"
																name="assetsType" value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="assetsAmount"
																name="assetsAmount" value="N/A" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<%
															}
														%>
														<tr style="display: none" class="addOther">
															<td>
																<button class="btn btn-block btn-success ">
																	<i class="fa fa-plus"> Add other</i>
																</button>
															</td>
															<td><button class="btn btn-sm btn-danger">
																	<i class="fa fa-times"></i>
																</button></td>
														</tr>

													</tbody>
												</table>
											</div>

											<div class="col-sm-6 well">
												<legend>
													<b>Monthly Expenses</b>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


													<%
														double dynamicAmountMonthlyExpenses = 0;
														if (list.size() != 0 || list != null) {
															for (int i = 0; i < list.size(); i++) {
																int amount = (int) list.get(i).getAmount();

																String eamount = NumberFormat.getNumberInstance(Locale.US)
																		.format(amount);
																eamount = eamount.concat(" PKR");

																//int dynamicAmount = Integer.parseInt(amount);
																System.out.println("Dynamic amount Expenses: == " + amount);

																dynamicAmountMonthlyExpenses += amount;
															}
															System.out.println("Dynamic amount Expenses: == "
																	+ dynamicAmountMonthlyExpenses);
														}

														if (totalHomeExpensesList == null
																|| totalHomeExpensesList.get("customerTotalExpenseIncome") == null) {
													%>
													<span><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(dynamicAmountMonthlyExpenses))%> PKR</span>
													<%
														} else {
													%>
													<b><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(dynamicAmountMonthlyExpenses
								+ Double.parseDouble(totalHomeExpensesList
										.get("customerTotalExpenseIncome"))))%> PKR</b>
													<%
														}
													%>

												</legend>



												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Connectivity</b>
													</label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerElectricityType") == null
																	|| totalHomeExpensesList.get("customerElectricityType") == "") {
														%>
														<input type="text" id="elecType" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String electricityType = totalHomeExpensesList
																		.get("customerElectricityType");
																if (electricityType.equals("0")) {
														%>
														<span id="elecType"><%="Off-Grid"%></span>
														<%
															} else {
														%>
														<span id="elecType"><%="On-Grid"%></span>
														<%
															} // inner else

															} // outer else
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Primary
															Source of Electricity</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerSourceOfElectricity") == null
																	|| totalHomeExpensesList.get("customerSourceOfElectricity") == "") {
														%>
														<input type="text" id="sourceOfElectricity" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String sourceOfElectricity = totalHomeExpensesList
																		.get("customerSourceOfElectricity");
														%>
														<span id="sourceOfElectricity"><%=sourceOfElectricity%></span>
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Electricity
															Expense (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerElectricityAmount") == null
																	|| totalHomeExpensesList.get("customerElectricityAmount") == "") {
														%>
														<input type="text" id="elecExpensesAmount" value="0"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String electExpensesAmount = totalHomeExpensesList
																		.get("customerElectricityAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(electExpensesAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="elecExpensesAmount"><%=afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1]%></b></span> <input type="hidden"
															id="elecExpensesAmount" class="elec_expenses_amount"
															value="<%=electExpensesAmount%>" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Mobile
															Type</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerMobileType") == null
																	|| totalHomeExpensesList.get("customerMobileType") == "") {
														%>
														<input type="text" id="mobileType" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String mobileType = totalHomeExpensesList
																		.get("customerMobileType");
														%>
														<span id="mobileType"><%=mobileType%></span>
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Mobile
															Bills Amount</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerMobileBillsAmount") == null
																	|| totalHomeExpensesList.get("customerMobileBillsAmount") == "") {
														%>
														<input type="text" id="mobileBillsAmount" value="0"
															disabled style="background-color: white; border: none" />

														<%
															} else {
																String mobileBillsAmount = totalHomeExpensesList
																		.get("customerMobileBillsAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(mobileBillsAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="mobileBillsAmount"><%=afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1]%></b></span> <input type="hidden"
															id="mobileBillsAmount" class="mobile_bills_amount"
															value="<%=mobileBillsAmount%>" disabled
															style="background-color: white; border: none" />
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Network
															types in Area</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("networks") == null
																	|| totalHomeExpensesList.get("networks") == "") {
														%>
														<input type="text" id="networksInArea" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String networks = totalHomeExpensesList.get("networks");
																if (networks.equals("") || networks == null) {
														%>
														<span id="networksInArea">N/A</span>
														<%
															} else {
														%>
														<span id="networksInArea"><%=networks%></span>
														<%
															}
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Transport
															Mode (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerTransportMode") == null
																	|| totalHomeExpensesList.get("customerTransportMode") == ""
																	&& totalHomeExpensesList.get("customerTransportAmount") == null
																	|| totalHomeExpensesList.get("customerTransportAmount") == "") {
														%>
														<input type="text" id="transportModeAmount" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String transportMode = totalHomeExpensesList
																		.get("customerTransportMode");
																String transportAmount = totalHomeExpensesList
																		.get("customerTransportAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(transportAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="transportModeAmount"><%=transportMode + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Karyana
															(Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (totalHomeExpensesList == null
																	|| totalHomeExpensesList.get("customerKaryana") == null
																	|| totalHomeExpensesList.get("customerKaryana") == ""
																	&& totalHomeExpensesList.get("customerKaryanaAmount") == null
																	|| totalHomeExpensesList.get("customerKaryanaAmount") == "") {
														%>
														<input type="text" id="karyanaAmount" value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String karyana = totalHomeExpensesList.get("customerKaryana");
																String karyanaAmount = totalHomeExpensesList
																		.get("customerKaryanaAmount");
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(Double.parseDouble(karyanaAmount));
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="karyanaAmount"><%=karyana + " (" + afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>
													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Education
															Expense Amount</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerEducationAmount() == 0) {
														%>
														<input type="text" id="childrensEducationAmount"
															value="N/A" disabled
															style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat.getNumberInstance(Locale.US)
																		.format(customebean.getCustomerEducationAmount());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="childrensEducationAmount"><%=afaimly.split(" ")[0]%>
															<b><%=afaimly.split(" ")[1]%></b></span> <input type="hidden"
															id="childrensEducationAmount" class="income_count"
															value="<%=customebean.getCustomerEducationAmount()%>"
															disabled style="background-color: white; border: none" />
														<%
															}
														%>

													</div>
												</div>
												<br>

												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Medical
															Recurring (Amount)</b> </label>
													<div class="col-md-5">
														<%
															if (customebean == null
																	|| customebean.getCustomerMedicalRecurring() == null
																	|| customebean.getCustomerMedicalRecurring().isEmpty()
																	&& customebean.getCustomerMedicalRecurringAmount() == 0) {
														%>
														<input type="text" id="medicalRecurringAmount" value="N/A"
															disabled style="background-color: white; border: none" />
														<%
															} else {
																String afaimly = NumberFormat
																		.getNumberInstance(Locale.US)
																		.format(customebean.getCustomerMedicalRecurringAmount());
																afaimly = afaimly.concat(" PKR");
														%>
														<span id="medicalRecurringAmount"><%=customebean.getCustomerMedicalRecurring() + " ("
						+ afaimly.split(" ")[0]%> <b><%=afaimly.split(" ")[1] + ")"%></b></span>
														<%
															}
														%>

													</div>
												</div>
												<br> <br>

												<legend>
													<b>Additional Expenses</b>
												</legend>

												<table id="expenseTable"
													class="table equal table-responsive table-bordered"
													style="font-size: 13px">

													<thead style="background-color: ghostwhite;">
														<th>Expense Type</th>
														<th>Expense Amount</th>
													</thead>
													<tbody>
														<%
															if (list.size() != 0 || list != null) {
																for (int i = 0; i < list.size(); i++) {
																	int amount = (int) list.get(i).getAmount();

																	String eamount = NumberFormat.getNumberInstance(Locale.US)
																			.format(amount);
																	eamount = eamount.concat(" PKR");
														%>
														<tr>
															<td style="display: none"><input type="hidden"
																name="expenseIDs"
																value="<%=list.get(i).getExpenseId()%>"></td>
															<td><input type="text" id="expenseType"
																name="expenseTypes"
																value="<%=list.get(i).getExpenseType()%>" disabled
																style="background-color: white; border: none"></td>
															<td><span id="expenseAmountSpan"><%=eamount.split(" ")[0]%>
																	<b><%=eamount.split(" ")[1]%></b></span> <input
																id="expenseAmount" name="expenseAmount" type="hidden"
																value="<%=amount%>" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<%
															} //end for loop
															} //end if
															else {
														%>
														<tr>
															<td style="display: none"><input type="hidden"
																name="expenseIDs" value="0"></td>
															<td><input type="text" id="expenseTypes"
																name="expenseTypes" value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="expenseAmount"
																name="expenseAmount" value="N/A" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<%
															}
														%>
													</tbody>
												</table>

												<span class="addOther " style="display: none">
													<button id="addOneRow" class=" btn btn-success col-md-4">
														<i class="fa fa-plus"> Add other</i>
													</button>

													<button id="removeRow"
														class="btn btn-sm btn-danger col-md-offset-1 col-md-1"
														disabled>
														<i class="fa fa-times"></i>
													</button>
												</span>
											</div>

											<div class="col-sm-12 well">
												<legend>
													<b>Loan and Liabilities</b>
												</legend>
												<table class="table equal table-responsive table-bordered"
													style="font-size: 13px">

													<tbody>
														<tr>
															<th>Type of loan</th>
															<th>Name</th>
															<th>Borrowed Amount</th>
															<th>Remaining Amount</th>
															<th>Monthly Installments</th>
															<th>Duration of Loan</th>
															<th>Frequency of Payments</th>
														</tr>
														<%
															if (!loanAndLiabilitiesList.isEmpty()
																	|| loanAndLiabilitiesList != null) {
																for (int i = 0; i < loanAndLiabilitiesList.size(); i++) {
																	if (loanAndLiabilitiesList.get(i).get("type").equals("1")) {

																		String bamount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("borrowedAmount")));
																		bamount = bamount.concat(" PKR");
																		String ramount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("remainingAmount")));
																		ramount = ramount.concat(" PKR");
																		String minsallemnet = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("monthlyInstallment")));
																		minsallemnet = minsallemnet.concat(" PKR");

																		String paymentPlanMonth = loanAndLiabilitiesList.get(i)
																				.get("paymentPlanInMonth");
																		String paymentFrequencyMonth = loanAndLiabilitiesList
																				.get(i).get("frequencyPaymentInMonth");
														%>
														<tr>
															<th>Bank/NGO/Foundation</th>
															<td style="display: none"><input type="hidden"
																id="loanID"
																value="<%=loanAndLiabilitiesList.get(i).get("loanId")%>"></td>
															<td><%=(loanAndLiabilitiesList.get(i).get("donner") == null || loanAndLiabilitiesList
								.get(i).get("donner").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"loanDonners\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"donner")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="borrowedAmmountSpan"><%=bamount.split(" ")[0]%>
																	<b><%=bamount.split(" ")[1]%></b></span> <%=loanAndLiabilitiesList.get(i).get(
								"borrowedAmount") == null
								|| loanAndLiabilitiesList.get(i)
										.get("borrowedAmount").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"borrowedAmmount\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"borrowedAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="remainingAmountSpan"><%=ramount.split(" ")[0]%>
																	<b><%=ramount.split(" ")[1]%></b></span> <%=(loanAndLiabilitiesList.get(i).get(
								"remainingAmount") == null || loanAndLiabilitiesList
								.get(i).get("remainingAmount").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"remainingAmount\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"remainingAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="monthlyInstallmentSpan"><%=minsallemnet.split(" ")[0]%>
																	<b><%=minsallemnet.split(" ")[1]%></b></span> <%=loanAndLiabilitiesList.get(i).get(
								"monthlyInstallment") == null
								|| loanAndLiabilitiesList.get(i)
										.get("monthlyInstallment").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"monthlyInstallment\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"monthlyInstallment")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"paymentPlanInMonth") == null || loanAndLiabilitiesList
								.get(i).get("paymentPlanInMonth").isEmpty()) ? "<input id=\"paymentPlan\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"paymentPlan\" value='"
										+ paymentPlanMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"frequencyPaymentInMonth") == null || loanAndLiabilitiesList
								.get(i).get("frequencyPaymentInMonth")
								.isEmpty()) ? "<input id=\"paymentFrequency\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"paymentFrequency\" value='"
										+ paymentFrequencyMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
														</tr>
														<%
															}
																	if (loanAndLiabilitiesList.get(i).get("type")
																			.equalsIgnoreCase("2")) {

																		String bamount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("borrowedAmount")));
																		bamount = bamount.concat(" PKR");
																		String ramount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("remainingAmount")));
																		ramount = ramount.concat(" PKR");
																		String minsallemnet = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("monthlyInstallment")));
																		minsallemnet = minsallemnet.concat(" PKR");

																		String paymentPlanMonth = loanAndLiabilitiesList.get(i)
																				.get("paymentPlanInMonth");
																		String paymentFrequencyMonth = loanAndLiabilitiesList
																				.get(i).get("frequencyPaymentInMonth");
														%>
														<tr>
															<th>Grocery/Karyana</th>
															<td style="display: none"><input type="hidden"
																id="GKloanID"
																value="<%=loanAndLiabilitiesList.get(i).get("loanId")%>"></td>
															<td><%=(loanAndLiabilitiesList.get(i).get("donner") == null || loanAndLiabilitiesList
								.get(i).get("donner").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"GKloanDonner\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"donner")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="GKborrowedAmmountSpan"><%=bamount.split(" ")[0]%>
																	<b><%=bamount.split(" ")[1]%></b></span> <%=loanAndLiabilitiesList.get(i).get(
								"borrowedAmount") == null
								|| loanAndLiabilitiesList.get(i)
										.get("borrowedAmount").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"GKborrowedAmmount\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"borrowedAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="GKremainingAmountSpan"><%=ramount.split(" ")[0]%>
																	<b><%=ramount.split(" ")[1]%></b></span> <%=(loanAndLiabilitiesList.get(i).get(
								"remainingAmount") == null || loanAndLiabilitiesList
								.get(i).get("remainingAmount").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"GKremainingAmount\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"remainingAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="GKmonthlyInstallmentSpan"><%=minsallemnet.split(" ")[0]%>
																	<b><%=minsallemnet.split(" ")[1]%></b></span> <%=loanAndLiabilitiesList.get(i).get(
								"monthlyInstallment") == null
								|| loanAndLiabilitiesList.get(i)
										.get("monthlyInstallment").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"GKmonthlyInstallment\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"monthlyInstallment")
										+ "' disabled style=\"background-color:white;border:none;\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"paymentPlanInMonth") == null || loanAndLiabilitiesList
								.get(i).get("paymentPlanInMonth").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"GKpaymentPlan\" value='"
										+ paymentPlanMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"frequencyPaymentInMonth") == null || loanAndLiabilitiesList
								.get(i).get("frequencyPaymentInMonth")
								.isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"GKpaymentFrequency\" value='"
										+ paymentFrequencyMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

														</tr>
														<%
															}
																	if (loanAndLiabilitiesList.get(i).get("type").equals("3")) {

																		String bamount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("borrowedAmount")));
																		bamount = bamount.concat(" PKR");
																		String ramount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("remainingAmount")));
																		ramount = ramount.concat(" PKR");
																		String minsallemnet = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("monthlyInstallment")));
																		minsallemnet = minsallemnet.concat(" PKR");

																		String paymentPlanMonth = loanAndLiabilitiesList.get(i)
																				.get("paymentPlanInMonth");
																		String paymentFrequencyMonth = loanAndLiabilitiesList
																				.get(i).get("frequencyPaymentInMonth");
														%>
														<tr>
															<th>Family/Friend</th>
															<td style="display: none"><input type="hidden"
																id="FFloanID"
																value="<%=loanAndLiabilitiesList.get(i).get("loanId")%>"></td>
															<td><%=(loanAndLiabilitiesList.get(i).get("donner")
								.equals("null") || loanAndLiabilitiesList
								.get(i).get("donner").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"FFloanDonner\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"donner")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="FFborrowedAmmountSpan"><%=bamount.split(" ")[0]%>
																	<b><%=bamount.split(" ")[1]%></b></span> <%=loanAndLiabilitiesList.get(i)
								.get("borrowedAmount").equals("null")
								|| loanAndLiabilitiesList.get(i)
										.get("borrowedAmount").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"FFborrowedAmmount\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"borrowedAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="FFremainingAmountSpan"><%=ramount.split(" ")[0]%>
																	<b><%=ramount.split(" ")[1]%></b></span> <%=(loanAndLiabilitiesList.get(i)
								.get("remainingAmount").equals("null") || loanAndLiabilitiesList
								.get(i).get("remainingAmount").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"FFremainingAmount\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"remainingAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span id="FFmonthlyInstallmentSpan"><%=minsallemnet.split(" ")[0]%>
																	<b><%=minsallemnet.split(" ")[1]%></b></span> <%=loanAndLiabilitiesList.get(i)
								.get("monthlyInstallment").equals("null")
								|| loanAndLiabilitiesList.get(i)
										.get("monthlyInstallment").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"FFmonthlyInstallment\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"monthlyInstallment")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"paymentPlanInMonth") == null || loanAndLiabilitiesList
								.get(i).get("paymentPlanInMonth").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"FFpaymentPlan\" value='"
										+ paymentPlanMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"frequencyPaymentInMonth") == null || loanAndLiabilitiesList
								.get(i).get("frequencyPaymentInMonth")
								.isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"FFpaymentFrequency\" value='"
										+ paymentFrequencyMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

														</tr>
														<%
															} else if (loanAndLiabilitiesList.get(i).get("type")
																			.equalsIgnoreCase("0")) {

																		String bamount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("borrowedAmount")));
																		bamount = bamount.concat(" PKR");
																		String ramount = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("remainingAmount")));
																		ramount = ramount.concat(" PKR");
																		String minsallemnet = NumberFormat.getNumberInstance(
																				Locale.US).format(
																				Integer.parseInt(loanAndLiabilitiesList.get(i)
																						.get("monthlyInstallment")));
																		minsallemnet = minsallemnet.concat(" PKR");

																		String paymentPlanMonth = loanAndLiabilitiesList.get(i)
																				.get("paymentPlanInMonth");
																		String paymentFrequencyMonth = loanAndLiabilitiesList
																				.get(i).get("frequencyPaymentInMonth");
														%>
														<tr>
															<th>Other loan</th>
															<td><%=(loanAndLiabilitiesList.get(i).get("donner") == null || loanAndLiabilitiesList
								.get(i).get("donner").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"donner")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span><%=bamount.split(" ")[0]%> <b><%=bamount.split(" ")[1]%></b></span>
																<%=loanAndLiabilitiesList.get(i).get(
								"borrowedAmount") == null
								|| loanAndLiabilitiesList.get(i)
										.get("borrowedAmount").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"borrowedAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span><%=ramount.split(" ")[0]%> <b><%=ramount.split(" ")[1]%></b></span>
																<%=(loanAndLiabilitiesList.get(i).get(
								"remainingAmount") == null || loanAndLiabilitiesList
								.get(i).get("remainingAmount").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"remainingAmount")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><span><%=minsallemnet.split(" ")[0]%> <b><%=minsallemnet.split(" ")[1]%></b></span>
																<%=loanAndLiabilitiesList.get(i).get(
								"monthlyInstallment") == null
								|| loanAndLiabilitiesList.get(i)
										.get("monthlyInstallment").isEmpty() ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								: "<input type=\"hidden\" id=\"\" value='"
										+ loanAndLiabilitiesList.get(i).get(
												"monthlyInstallment")
										+ "' disabled style=\"background-color:white;border:none\">"%></td>
															<td><%=(loanAndLiabilitiesList.get(i).get(
								"paymentPlanInMonth") == null || loanAndLiabilitiesList
								.get(i).get("paymentPlanInMonth").isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"paymentPlan\" value='"
										+ paymentPlanMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

															<td><%=(loanAndLiabilitiesList.get(i).get(
								"frequencyPaymentInMonth") == null || loanAndLiabilitiesList
								.get(i).get("frequencyPaymentInMonth")
								.isEmpty()) ? "<input id=\"\" type=\"text\" value=\"N/A\">"
								:

								"<input type=\"text\" id=\"paymentFrequency\" value='"
										+ paymentFrequencyMonth
										+ " Months"
										+ "' disabled style=\"background-color:white;border:none\">"%></td>

														</tr>
														<%
															}//end if else

																}//end for loop
															}//end if
															else {
														%>
														<tr>
															<th>Bank/NGO/Foundation</th>

															<td style="display: none"><input type="hidden"
																id="loanID" value="0"></td>
															<td><input type="text" id="loanDonners" value="N/A"
																disabled style="background-color: white; border: none"></td>
															<td><input type="text" id="borrowedAmmount"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="remainingAmount"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="monthlyInstallment"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="paymentPlan" value="N/A"
																disabled style="background-color: white; border: none"></td>
															<td><input type="text" id="paymentFrequency"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<tr>
															<th>Grocery/Karyana</th>
															<td style="display: none"><input type="hidden"
																id="GKloanID" value="0"></td>
															<td><input type="text" id="GKloanDonner" value="N/A"
																disabled style="background-color: white; border: none"></td>
															<td><input type="text" id="GKborrowedAmmount"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="GKremainingAmount"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="GKmonthlyInstallment"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="GKpaymentPlan"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="GKpaymentFrequency"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<tr>
															<th>Family/Friend</th>
															<td style="display: none"><input type="hidden"
																value="0" id="FFloanID"></td>
															<td><input type="text" id="FFloanDonner" value="N/A"
																disabled style="background-color: white; border: none"></td>
															<td><input type="text" id="FFborrowedAmmount"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="FFremainingAmount"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="FFmonthlyInstallment"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="FFpaymentPlan"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
															<td><input type="text" id="FFpaymentFrequency"
																value="N/A" disabled
																style="background-color: white; border: none"></td>
														</tr>
														<%
															} // end else
														%>
													</tbody>

												</table>

												<div id="generalInformation">
													<input type="submit" id="customerGenralInformation"
														class="btn btn-info btn-sm" value="Edit Information">
													<input type="submit" class="btn btn-info btn-sm"
														id="genralSave" value="Save" style="display: none">
													<input type="submit" class="btn btn-info btn-sm"
														id="genralcancel" value="cancel" style="display: none">
													<span class="fa fa-circle-o-notch fa-spin"
														id="genInfoCircle" style="display: none"></span> <span
														class="fa fa-check"
														style="color: green; font-size: 18px; display: none"
														id="genInfoSuccess"><b>Success</b></span>

												</div>

											</div>

										</div>
									</div>
								</div>

							</div>

							<div class="tab-pane fade in" id="loanAsse">
								<div class="panel-body">
									<!-- /////////////////////////// Gallery Start///////////////////////////// -->

									<%
										String path = request.getServletContext().getRealPath("/Images")
												+ File.separator + customebean.getCustomerId();
										String ss = "";
										System.out.println("path " + path);
										HashMap<String, String[]> imagelist = ImageBAL
												.getAssetsImages(path);
										HashMap<String, String[]> imagelist2 = ImageBAL
												.getAssetsImages2(path + File.separator + "101");
										if (imagelist2 != null) {
											ArrayList<HashMap<String, String>> assetsList = EligibilityBAL
													.getAllAssetsByCustomer(customebean.getCustomerId());
									%>
									<div class="container-fluid" style="margin-left: 5%">
										<div class="row">
											<div class="docs-galley col-sm-10 col-md-8">
												<div class="row ">
													<legend>Assets</legend>
													<%
														for (HashMap<String, String> map : assetsList) {
													%>
													<div class="row well">
														<div class="col-sm-2"><%=map.get("type")%></div>
														<div class="col-sm-10">
															<ul class="docs-pictures clearfix ">
																<%
																	if (imagelist2.get(map.get("asset_id")) != null)
																				for (String image : imagelist2.get(map.get("asset_id"))) {
																					ss = "Images/" + customebean.getCustomerId()
																							+ "/101/" + map.get("asset_id")
																							+ File.separator + image;
																%>
																<li><img src="<%=ss%>" alt="Cuo Na Lake"
																	class="img img-rounded"
																	style="width: 50%; height: 50px"></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
													<%
														}
													%>
												</div>
											</div>
										</div>

										<%
											}
										%>

										<%
											if (imagelist != null) {
												for (String string : imagelist.keySet()) {
													String[] stringArr = imagelist.get(string);
													System.out.println("string ----------------" + string);
													System.out.println("stringArr++++++++++++++++++++++++++++ "
															+ stringArr);
										%>

										<%
											if (string.equals("103")) {
										%>

										<div class="row" style="margin-top: 2%">
											<div class="docs-galley col-sm-10 col-md-8">
												<div class="row">
													<fieldset>
														<legend>Documents</legend>
														<div class="row  well">
															<div class="col-sm-2">Loan Agreement</div>
															<div class="col-sm-10">
																<ul class="docs-pictures clearfix ">
																	<li id="missing_loan_f"><span style="color: red"><p>Image
																				Missing</p></span></li>
																	<li id="missing_loan_b"><span style="color: red"><p>Image
																				Missing</p></span></li>
																	<%
																		for (String stringSub : stringArr) {
																						ss = "Images/" + customebean.getCustomerId() + "/"
																								+ string + "/";

																						System.out.println("*/*/*//*//*/" + stringSub);
																						if (stringSub.equals("Loan Agreement Front.jpg")) {
																	%>
																	<script>console.log('okkkkkkkkkes');hide('#missing_loan_f');</script>
																	<li><img
																		src="<%=ss + "Loan Agreement Front.jpg"%>"
																		alt="Image Not Availble" class="img img-rounded"
																		style="width: 50%; height: 50px"></li>
																	<%
																		} else if (stringSub
																								.equals("Loan Agreement Back.jpg")) {
																	%>
																	<script>console.log('okkkkkkkkkes');hide('#missing_loan_b');</script>

																	<li><img src="<%=ss + "Loan Agreement Back.jpg"%>"
																		alt="Image Not Availble" class="img img-rounded"
																		style="width: 50%; height: 50px"></li>
																	<%
																		}
																					}
																	%>
																</ul>
															</div>
														</div>
													</fieldset>

													<div class="row well">
														<div class="col-sm-2">Customer Photo</div>
														<div class="col-sm-10">
															<ul class="docs-pictures clearfix ">
																<li id="missing_customer_profile"><span
																	style="color: red"><p>Image Missing</p></span></li>
																<%
																	for (String stringSub : stringArr) {

																					if (stringSub.equals("Customer Photo.jpg")) {
																						ss = "Images/" + customebean.getCustomerId()
																								+ "/" + string + "/";
																%>

																<script>console.log('okkkkkkkkkes');hide('#missing_customer_profile');</script>
																<li><img src="<%=ss + "Customer Photo.jpg"%>"
																	alt="missing" class="img img-rounded"
																	style="width: 50%; height: 50px"></li>

																<%
																	}
																				}
																%>
															</ul>
														</div>
													</div>


												</div>
											</div>
										</div>

										<%
											}
										%>

										<%
											if (string.equals("102")) {
										%>
										<div class="row" style="margin-top: 2%">
											<div class="docs-galley col-sm-10 col-md-8">

												<div class="row well">
													<fieldset>
														<legend>CNIC</legend>
														<div class="row well">
															<div class="col-sm-2">Customer Cnic</div>
															<div class="col-sm-10">
																<ul class="docs-pictures clearfix ">
																	<%
																		for (String stringSub : stringArr) {
																						ss = "Images/" + customebean.getCustomerId() + "/"
																								+ string + "/";
																						if (stringSub.equals("Customer Front.jpg")) {
																	%>

																	<li><img src="<%=ss + "Customer Front.jpg"%>"
																		alt="missing" class="img img-rounded"
																		style="width: 50%; height: 50px"></li>
																	<%
																		}
																						if (stringSub.equals("Customer Back.jpg")) {
																	%>

																	<li><img src="<%=ss + "Customer Back.jpg"%>"
																		alt="missing" class="img img-rounded"
																		style="width: 50%; height: 50px"></li>
																	<%
																		}
																	%>
																	<%
																		}
																	%>
																</ul>
															</div>
														</div>
														<div class="col-sm-2">Family Guarantor Cnic</div>
														<div class="col-sm-10">
															<ul class="docs-pictures clearfix ">
																<%
																	for (String stringSub : stringArr) {
																					ss = "Images/" + customebean.getCustomerId() + "/"
																							+ string + "/";
																					if (stringSub.equals("Family Guarantor Front.jpg")) {
																%>

																<li><img
																	src="<%=ss + "Family Guarantor Front.jpg"%>"
																	alt="missing" class="img img-rounded"
																	style="width: 50%; height: 50px"></li>
																<%
																	}
																					if (stringSub.equals("Family Guarantor Back.jpg")) {
																%>

																<li><img
																	src="<%=ss + "Family Guarantor Back.jpg"%>"
																	alt="missing" class="img img-rounded"
																	style="width: 50%; height: 50px"></li>
																<%
																	}
																%>
																<%
																	}
																%>
															</ul>
														</div>
													</fieldset>
												</div>
												<div class="row well">
													<div class="col-sm-2">Guarantor 2 Cnic</div>
													<div class="col-sm-10">
														<ul class="docs-pictures clearfix ">
															<%
																for (String stringSub : stringArr) {
																				ss = "Images/" + customebean.getCustomerId() + "/"
																						+ string + "/";
																				if (stringSub.equals("Guarantor 2 Front.jpg")) {
															%>

															<li><img src="<%=ss + "Guarantor 2 Front.jpg"%>"
																alt="missing" class="img img-rounded"
																style="width: 50%; height: 50px"></li>
															<%
																}
																				if (stringSub.equals("Guarantor 2 Back.jpg")) {
															%>

															<li><img src="<%=ss + "Guarantor 2 Back.jpg"%>"
																alt="missing" class="img img-rounded"
																style="width: 50%; height: 50px"></li>
															<%
																}
															%>
															<%
																}
															%>
														</ul>
													</div>
												</div>



											</div>
										</div>
										<%
											}
										%>

										<%
											}
											}
										%>






									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="panel  panel-with-tabs">
							<ul id="ioniconsTab" class="nav nav-tabs">
								<li class="active"><a href="#default" data-toggle="tab">
										<span class="hidden-xs m-l-3">Accepted Appliances<span
											class="badge badge-inverse m-l-3"></span>
									</span>
								</a></li>
								<li><a href="#ios" data-toggle="tab"> <span
										class="hidden-xs m-l-3">Rejected Appliances<span
											class="badge badge-inverse m-l-3"></span>
									</span></a></li>
							</ul>
							<div id="ioniconsTabContenta" class="tab-content">
								<div class="tab-pane fade in active" id="default">
									<div data-scrollbar="false" style="font-size: 13px;"
										class="table-responsive">
										<table id="sold" class="table table-bordered">
											<thead>
												<tr>
													<th>Appliance</th>
													<th>District Officer</th>
													<th>Field Officer</th>
													<th>Nizam Dost</th>
													<th>Eligibility Status</th>
													<th>Appliance Transfer Status</th>
													<th>Appliance Charging Status</th>
													<th>Loan Status</th>
													<th>Loan Book</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (int i = 0; i < appliancesInAccount.size(); i++) {
												%>

												<tr<%-- class="link"
												data-link="ViewServlet?click=viewAppliance&id=<%appliancesInAccount.get(i).get("ApplianceId");%>" --%> >


													<td><a
														href="ViewServlet?click=viewAppliance&id=<%=appliancesInAccount.get(i).get("ApplianceId")%>">

															<%=appliancesInAccount.get(i).get("ApplianceName")%></a></td>
													<td><a
														href="DistrictOfficer?do_id=<%=appliancesInAccount.get(i).get("user_id")%>"><%=appliancesInAccount.get(i).get("user_name")%></a></td>
													<td><a
														href="fieldOfficerProfile.jsp?fo_id=<%=appliancesInAccount.get(i).get("fo_id")%>"><%=appliancesInAccount.get(i).get("fo_name")%></a></td>
													<td><a
														href="Test?click=ok&salesman_id=<%=appliancesInAccount.get(i).get("salesman_id")%>">
															<%=appliancesInAccount.get(i).get("salesman")%>
													</a></td>

													<td>
														<%
															if (appliancesInAccount.get(i).get("Status").equals("0")) {
														%> <span class="label"
														style="background-color: #bdc3c7; color: white; font-weight: bold;">Applied</span>
														<%
															} else if (appliancesInAccount.get(i).get("Status").equals("1")) {
														%> <span class="label"
														style="background-color: #2980b9; color: white; font-weight: bold;">Accepted</span>
														<%
															} else if (appliancesInAccount.get(i).get("Status").equals("3")) {
														%> <span class="label"
														style="background-color: #2980b9; color: white; font-weight: bold;">Not
															Interested</span> <%
 	} else if (appliancesInAccount.get(i).get("Status").equals("7")
 				|| appliancesInAccount.get(i).get("Status").equals("8")
 				|| appliancesInAccount.get(i).get("Status").equals("6")) {
 %> <span class="label"
														style="background-color: #2ecc71; color: white; font-weight: bold;">Verified</span>
														<%
															} else {
														%> N/A <%
															}
														%>
													</td>

													<td style="widht: 20%">
														<%
															if (appliancesInAccount.get(i).get("applianceStatus") != null) {
														%> <%
 	if (appliancesInAccount.get(i).get("applianceStatus")
 					.equals("0")) {
 %> <span class="label"
														style="background-color: #bdc3c7; color: white; font-weight: bold;">Pending</span>
														<%
															} else if (appliancesInAccount.get(i)
																			.get("applianceStatus").equals("1")) {
														%> <span class="label"
														style="background-color: #bdc3c7; color: white; font-weight: bold;">Ready
															To Assign</span> <%
 	} else if (appliancesInAccount.get(i)
 					.get("applianceStatus").equals("2")) {
 %> <span class="label"
														style="background-color: #1abc9c; color: white; font-weight: bold;">Handover</span>
														<%
															} else if (appliancesInAccount.get(i)
																			.get("applianceStatus").equals("3")) {
														%> <span class="label"
														style="background-color: #7f8c8d; color: white; font-weight: bold;">Owner</span>
														<%
															} else if (appliancesInAccount.get(i)
																			.get("applianceStatus").equals("4")) {
														%> <span class="label"
														style="background-color: #d35400; color: black; font-weight: bold;">Returned</span>
														<%
															} else if (appliancesInAccount.get(i)
																			.get("applianceStatus").equals("5")) {
														%> <span class="label"
														style="background-color: #1abc9c; color: white; font-weight: bold;">Awaiting
															Downpayment</span> <%
 	} else if (appliancesInAccount.get(i)
 					.get("applianceStatus").equals("6")) {
 %> <span class="label"
														style="background-color: green; color: white; font-weight: bold;">Installed</span>
														<%
															} else {
														%> <span class="label"
														style="background-color: #bdc3c7; color: white; font-weight: bold;">Pending</span>
														<%
															}
																} else {
														%> N/A <%
															}
														%>
													</td>


													<%-- <td style="widht:20%"><%=appliancesInAccount.get(i).get("appStatus")%></td> --%>

													<%-- <td style="widht:20%"><%=appliancesInAccount.get(i).get("salesman")%></td> --%>


													<td style="widht: 20%">
														<%
															if (appliancesInAccount.get(i).get("appStatus").equals("1")) {
														%> <span class="label"
														style="background-color: #16a085; color: white; font-weight: bold;">
															Active</span> <%
 	} else {
 %> <span class="label"
														style="background-color: Red; color: white; font-weight: bold;">
															Inactive</span> <%
 	}
 %>
													</td>

													<td style="widht: 20%">
														<%
															if (appliancesInAccount.get(i).get("status_get")
																		.equals("maintend")) {
														%> <span class="label"
														style="background-color: blue; color: white; font-weight: bold;">Maintained</span>
														<%
															} else if (appliancesInAccount.get(i).get("status_get")
																		.equals("owned")) {
														%> <span class="label"
														style="background-color: #7f8c8d; color: black; font-weight: bold;">Owned</span>
														<%
															} else if (appliancesInAccount.get(i).get("status_get")
																		.equals("defaulter")) {
														%> <span class="label"
														style="background-color: #34495e; color: white; font-weight: bold;">Defaulter</span>
														<%
															} else {
														%> N/A <%
															}
														%>
													</td>
													<td>
														<%
															if (appliancesInAccount.get(i).get("status_get")
																		.equals("maintend")
																		|| appliancesInAccount.get(i).get("status_get")
																				.equals("owned")
																		|| appliancesInAccount.get(i).get("status_get")
																				.equals("defaulter")) {
														%> <a
														href="PaymentLoanServlet?appliace_key=<%=appliancesInAccount.get(i).get("ApplianceId")%>"
														class="label"
														style="background-color: #34495e; color: white; font-weight: bold; width: 100%">View</a>
														<%
															} else {
														%> N/A <%
															}
														%>
													</td>

												</tr>
												<%
													}
												%>
											</tbody>
										</table>

									</div>
								</div>

								<div class="tab-pane" id="ios">
									<div style="font-size: 13px;">

										<table id="reject"
											class="table table-condensed table-bordered ">
											<thead>
												<th>Appliance</th>
												<th>Nizam Dost</th>
												<th>Purpose</th>
											</thead>
											<tbody>
												<%
													ArrayList<CustomerInfoBean> rejectedAppliances = CustomerBAL
															.getRejectedAppliances(customebean.getCustomerId());
													for (int i = 0; i < rejectedAppliances.size(); i++) {
												%>

												<tr<%-- class="link"
												data-link="ViewServlet?click=viewAppliance&id=<%appliancesInAccount.get(i).get("ApplianceId");%>" --%> >


													<td><a
														href="ViewServlet?click=viewAppliance&id=<%=rejectedAppliances.get(i).getApplianceId()%>">

															<%=rejectedAppliances.get(i).getApplianceName()%></a></td>

													<td><a
														href="Test?click=ok&salesman_id=<%=rejectedAppliances.get(i).getSalesamanId()%>">
															<%=rejectedAppliances.get(i).getSalesmanName()%>
													</a></td>
													<td><%=rejectedAppliances.get(i).getPurpose()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- <div class="tab-pane fade" id="ios">
											<div style="font-size: 13px;">

											</div></div> -->

						</div>
					</div>
				</div>
			</div>



		</div>
		<!-- end scrollbar -->

		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	</div>

	<!-- ================== BEGIN BASE JS ================== -->


	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/viewer.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/plugins/isotope/jquery.isotope.min.js"></script>
	<script src="assets/plugins/lightbox/js/lightbox-2.6.min.js"></script>
	<script src="assets/js/gallery.demo.min.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="js/profile2.js"></script>
	<script src="js/validation.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
			$(document).ready(function() {
				App.init();

			});
			
			var app = angular.module('myApp', []);
			app.controller('validateCtrl', function($scope) {
			    $scope.user = '<%=customebean.getCustomerName() == null ? "N/A"
					: customebean.getCustomerName()%>'
			    $scope.father = '<%=customebean.getFatherName() == null ? "N/A" : customebean
					.getFatherName()%>'
			    $scope.custCnic='<%=customebean.getCnicNo() == null ? "N/A" : customebean
					.getCnicNo()%>'
			    $scope.custAddress='<%=customebean.getAddress() == null ? "N/A" : customebean
					.getAddress()%>'
			    $scope.customerPrimaryNumber='<%=customebean.getPhoneNo() == null ? "N/A" : customebean.getPhoneNo().replace("92", "")%>'
						});
	</script>
</body>

</html>