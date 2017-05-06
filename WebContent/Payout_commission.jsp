<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.SalaryBean"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bean.Suggestion"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.UserBAL"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bean.EligibilityBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bean.LoanAndCustomerIdBean"%>
<%@page import="bal.LoanAndCustomerIdBAL"%>
<%@page import="bean.RecoveryGraphBean"%>
<%@page import="bal.RecoveryGraphBAL"%>
<%@page import="security.EncryptDecrypt"%>
<%@page import="java.util.HashMap"%>
<%@page import="schedule.ScheduleBAL"%>


<%-- <%@ %> --%>

<!-- LoanAndCustomerIdBean -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>


<!-- 		<link href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" rel="stylesheet"> -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.bootstrap.min.css"
	rel="stylesheet">

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<!-- ================== END BASE CSS STYLE ================== -->

<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />

<!-- 		<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/ionRangeSlider/css/ion.rangeSlider.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/ionRangeSlider/css/ion.rangeSlider.skinNice.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/password-indicator/css/password-indicator.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-combobox/css/bootstrap-combobox.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet" /> -->

<!-- 		<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" /> -->
<!-- 		<link href="assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet" /> -->

<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<!-- ==================END USER DEFINED STYLE===================== -->

<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->



<style>
span.bigcheck-target {
	font-family: FontAwesome; /* use an icon font for the checkbox */
}

input[type='checkbox'].bigcheck {
	position: relative;
	left: -999em; /* hide the real checkbox */
}

input[type='checkbox'].bigcheck+span.bigcheck-target:after {
	content: "\f096"; /* In fontawesome, is an open square (fa-square-o) */
}

input[type='checkbox'].bigcheck:checked+span.bigcheck-target:after {
	content: "\f046"; /* fontawesome checked box (fa-check-square-o) */
}
</style>




</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {

			// Removed to all these By Jetander
			// 		        ArrayList<CustomerInfoBean> loans = CustomerBAL.getCutomers_onLoan(0);
			// 		        ArrayList<CustomerInfoBean> cash = CustomerBAL.getCutomers_onCash();
			// 		        ArrayList<CustomerInfoBean> appliad = CustomerBAL.getCutomers_onWait();
			// 		        ArrayList<CustomerInfoBean> accepted = CustomerBAL.getCutomers_Accepted();
			// 		        ArrayList<CustomerInfoBean> rejected = CustomerBAL.getCutomers_Rejected();
			// 		        ArrayList<CustomerInfoBean> suggested = CustomerBAL.getCutomers_suggested();
			// 		        ArrayList<CustomerInfoBean> Active = CustomerBAL.getCutomers_onActive(0,0);
			// 		        ArrayList<CustomerInfoBean> inActive = CustomerBAL.getCutomers_onInActive();
			// 		        ArrayList<CustomerInfoBean> notinterested= CustomerBAL.getCutomers_notinterested_Super();
			// 		        ArrayList<LoanAndCustomerIdBean> loadId = LoanAndCustomerIdBAL.getLoanAndCustomerId();
			// 		     	RecoveryGraphBean recoveryBean = null;

			// 		    	for(int i=1; i<topCustomer.size(); i++) {
			// 		    		for(int j=0; j<topCustomer.size()-1; j++) {
			// 		    		if(topCustomer.get(j).getPercentage() < topCustomer.get(j+1).getPercentage() ){
			// 		    		double temp = topCustomer.get(j).getPercentage();
			// 		    		String customer = topCustomer.get(j).getCustomerName();
			// 		    		topCustomer.get(j).setPercentage(topCustomer.get(j+1).getPercentage());
			// 		    		topCustomer.get(j).setCustomerName(topCustomer.get(j+1).getCustomerName());
			// 		    		topCustomer.get(j+1).setPercentage(temp);
			// 		    		topCustomer.get(j+1).setCustomerName(customer);
			// 		    		}
			// 		    		}
			// 		    	}
	%>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar">
		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp"%>
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
							<small>Superadmin</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">

					<li class="has-sub"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>
					<li class="has-sub"><a href="Request"> <i
							class="fa fa-inbox"></i> <span>Loan Request</span> <span
							class="badge pull-right" id="unseen_loan_request_count"
							style="font-size: 13px;"></span>
					</a></li>
					<li class="active"><a href="Customer"> <i
							class="fa fa-suitcase"></i> <span>Customers </span>
					</a></li>
					<li class="has-sub"><a href="Appliances"> <i
							class="fa fa-file-o"></i> <span>Appliances</span>
					</a></li>
					<li class="has-sub "><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="DistrictOfficerr">District Officer</a></li>
							<li><a href="superAdminFO.jsp">Field Officer</a></li>
							<li><a href="Salesmen">Nizam Dost</a></li>
							<!-- 							<li><a href="doSaleforce.jsp">DO Sales</a></li> -->
							<!-- 							<li><a href="foSalesforce.jsp">FO Sales</a></li> -->
							<!-- 							<li><a href="ndSalesforce.jsp">ND Sales</a></li> -->
						</ul></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Finance</span>
					</a>
						<ul class="sub-menu active">

							<li><a href="Finance">Payments</a></li>
							<li><a href="Loan">Loan books</a></li>
							<li><a href="commissionPayout.jsp">Commissions</a></li>
						</ul></li>

					<li class="has-sub"><a href="SuperAdminNock"> <i
							class="icon-support"></i> <span>Alarms</span>
					</a></li>


					<li class="has-sub"><a href="Analytics"> <i
							class="ion-podium"></i> <span>Analytics</span>
					</a></li>
					<li class="has-sub"><a href="CashSale.jsp"> <i
							class="ion-podium"></i> <span>Cash Sales</span>
					</a></li>
					<!-- 					<li class="has-sub"><a href="FinanceAnalytics.jsp"> <i -->
					<!-- 							class="ion-podium"></i> <span>Finance Analytics</span> -->
					<!-- 					</a></li> -->
					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Reports</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="reportAnalytics.jsp">Late/Defaulter Report</a></li>
							<li><a href="futureLoanBooks.jsp">Future loan book</a></li>
							<li><a href="SaReports.jsp">Reports</a></li>
						</ul></li>
					<!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn"
						data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
					<!-- end sidebar minify button -->
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
			<!-- 			<ol class="breadcrumb pull-right f-s-12"> -->
			<!-- 				<li><a href="SuperAdminDashboard">Dashboard</a></li> -->
			<!-- 				<li class="active">Customers</li> -->
			<!-- 			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">
				Customers <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold">
					<%-- <%=bean.size() %> --%>
				</span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->



			<div class="row">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->




					<div id="customerPanel" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Customers Table</h4>
						</div>
						<div class="panel-body">
							<div class="row">


								<div class="row m-10">
									<button class="btn btn-xs btn-primary  m-r-5 m-l-5 pull-right"
										id="switch_filter">Advanced Filter</button>
									<button class="btn btn-xs btn-white pull-right" id="clickMe">Send
										SMS</button>
								</div>
							</div>

							<div class="table-responsive" style="font-size: 13px;">
								<div id="advanced_filter" class="row p-10 m-10">
									<form class="">
										<div class="col-sm-4">
											<div class="form-group">
												<label for="customer_name">Customer Name</label> <input
													type="text" id="customer_name" name="customer_name"
													class="form-control">
											</div>
										</div>

										<div class="col-sm-4">
											<div class="form-group">
												<label for="customer_monthly_income">Customer
													Monthly Income</label> <select id="customer_monthly_income"
													name="customer_monthly_income" class="form-control">
													<option value="-1">Select Customer Monthly Income</option>
												</select>
											</div>
										</div>

										<div class="col-sm-4">
											<div class="form-group">
												<label for="appliance_name">Appliance Name</label>
												<%-- <% ArrayList<InventoryBean> inventory = InventoryBAL.getAppliances();
												
										%> --%>
												<select class="form-control" id="appliance_name"
													name="appliance_name">
													<option value="-1">Select Appliance</option>

												</select>
											</div>
										</div>

										<div class="col-sm-4">
											<div class="form-group">
												<label for="appliance_number">Appliance IMEI</label> <input
													type="number" id="appliance_number" name="appliance_number"
													class="form-control">
											</div>
										</div>

										<div class="col-sm-4">
											<div class="form-group">
												<label for="district"> District</label> <select
													tabindex="-1" class="form-control" name="district"
													id="district">
													<option value="-1">Select District</option>
												</select>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label for="salesman_name">Nizam Dost Name</label>
												<!-- 										<input type="text" id="salesman_name" name="salesman_name" class="form-control"> -->
												<select class="form-control" id="salesman_name"
													name="salesman_name">
													<option value="0">Select Nizam Dost</option>

												</select>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label>Nizam Dost Number</label> <input type="number"
													id="salesman_number" name="salesman_number"
													class="form-control">
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label for="status">Transfer Status</label> <select
													class="form-control" id="status" name="status">
													<option value="-1">Select Transfer Status</option>
													<option value="0">Pending</option>
													<option value="1">Sold</option>
													<option value="2">Rejected</option>
													<option value="3">Owner</option>
													<option value="4">Returned</option>
												</select>
											</div>
										</div>

										<div class="col-sm-4">
											<input type="button" class="btn btn-primary" id="btn_filter"
												value="Filter" /> <input type="reset"
												class="btn btn-default" id="btn_clear" value="Reset Filter" />
										</div>
									</form>
								</div>

								<div id="myDialog" style="width: 150px">
									Kindly Enter Message for Customers in Textbox
									<textarea rows="4" cols="50" id="myTextBox" /></textarea>
								</div>







								<div class="row" style="padding-top: 2%">
									<ul id="ioniconsTab" class="nav nav-tabs">
										<li class="active"><a href="#default" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Accepted
													Customers<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#ios" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Rejected
													Customers<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#notinterested" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Not
													Interested Customers<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
									</ul>

									<div id="ioniconsTabContenta" class="tab-content">

										<div class="tab-pane fade in active" id="default">
											<div class="table-responsive" style="font-size: 13px;">

												<table id="customer_table"
													class="table table-hover table-bordered">
													<thead>
														<tr>
															<th><input type="checkbox" id="selectall"></th>
															<th>Appliances In Account</th>
															<th>Name</th>
															<th>City, District</th>
															<th>Monthly Income</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>

											</div>
										</div>
										<div class="tab-pane fade" id="ios">
											<div class="table-responsive" style="font-size: 13px;">
												<table id="customer"
													class="table table-hover table-bordered">
													<thead>
														<tr>
															<th><input type="checkbox" id="selectall"></th>
															<th>Appliances In Account</th>
															<th>Name</th>
															<th>City, District</th>
															<th>Monthly Income</th>
														</tr>
													</thead>
													<tbody>

														<!-- 									<tr><td>xxxx</td></tr> -->
														<!-- 									<tr><td>yyyy</td></tr> -->

													</tbody>
												</table>

											</div>
										</div>

										<div class="tab-pane fade" id="notinterested">
											<div class="table-responsive" style="font-size: 13px;">
												<table id="not_interested"
													class="table table-hover table-bordered">
													<thead>
														<tr>
															<!-- <th><input type="checkbox" id="selectall"></th> -->

															<th>Name</th>
															<th>Appliance</th>
															<!--System.err.println(hashMap.get("customer_id")); -->

														</tr>
													</thead>
													<tbody>
														<%
															ArrayList<HashMap<String, String>> list = ScheduleBAL
																		.getSuspended();
																for (HashMap<String, String> hashMap : list) {
														%>
														<tr>
															<td><%=hashMap.get("customer_name")%></td>
															<td><%=hashMap.get("appliance_name")%></td>
														</tr>
														<%
															}
														%>
													</tbody>
												</table>

											</div>
										</div>

									</div>
								</div>








							</div>
							<!-- end panel -->
						</div>
						<!-- end col-10 -->

						<div class="modal fade" id="modal-alert">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">×</button>
										<h4 class="modal-title">Accept Request</h4>
									</div>
									<div class="modal-body">
										<div class="alert alert-success m-b-0">
											<input type="hidden" id="applianceId" />


											<p>
												Are you sure you want to accept loan request of <i
													id="customerId"> customer </i> ?
											</p>
										</div>
									</div>
									<div class="modal-footer">
										<a href="javascript:;" class="btn btn-sm btn-white"
											data-dismiss="modal">Close</a> <a id="updateURL"
											class="btn btn-sm btn-success">Action</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- end row -->
				</div>
				<!-- end #content -->

				<a href="javascript:;"
					class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
					data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
				<!-- end scroll to top btn -->
			</div>


			<!-- footer start -->

			<!-- footer end -->

		</div>
		<!-- end page container -->

		<!-- ================== BEGIN BASE JS ================== -->
		<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
		<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
		<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
		<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
		<!--[if lt IE 9]>
			  <script src="assets/crossbrowserjs/html5shiv.js"></script>
			  <script src="assets/crossbrowserjs/respond.min.js"></script>
			  <script src="assets/crossbrowserjs/excanvas.min.js"></script>
			 <![endif]-->
		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<script type="text/javascript" src="assets/js/myscript.js"></script>
		<!-- ================== END BASE JS ================== -->


		<!-- ================== BEGIN PAGE LEVEL JS ================== -->


		<!-- 			 <script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script> -->
		<!-- 			 <script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script> -->
		<!-- 			 <script src="assets/js/table-manage-colvis.demo.min.js"></script> -->


		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/ionRangeSlider/js/ion-rangeSlider/ion.rangeSlider.min.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script> -->
		<!-- 			 <script src="assets/plugins/masked-input/masked-input.min.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/password-indicator/js/password-indicator.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script> -->
		<!-- 			 <script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput-typeahead.js"></script> -->
		<!-- 			 <script src="assets/plugins/jquery-tag-it/js/tag-it.min.js"></script> -->
		<!-- 			 <script src="assets/plugins/bootstrap-daterangepicker/moment.js"></script> -->
		<!-- 			 <script -->
		<!-- 			  src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> -->
		<!-- 			 <script src="assets/plugins/select2/dist/js/select2.min.js"></script> -->
		<!-- 			 <script src="assets/js/form-plugins.demo.min.js"></script> -->
		<script src="assets/plugins/chart-js/chart.js"></script>
		<!--  <script src="assets/js/apps.min.js"></script> -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
		<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
		<script
			src="https://cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
		<script
			src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.bootstrap.min.js"></script>
		<script src="assets/js/table-manage-colvis.demo.min.js"></script>
		<script src="assets/js/chart-js.demo.min.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<!-- ================== END PAGE LEVEL JS ================== -->
		<script src="assets/plugins/icheck/icheck.min.js"></script>



		<!-- Begin Initialize Customer Table -->
		<script type="text/javascript">
			$(function() {
				// 	Begin Quick Filter Button Facinating
				$('input[name="appliance_status_rb"]').each(function() {
					var radioId = $(this).attr('id')
					if (radioId === 'active_rb') {
						$(this).iCheck({
							radioClass : 'icheckbox_square-blue'
						})
					} else if (radioId === 'inactive_rb') {
						$(this).iCheck({
							radioClass : 'icheckbox_square-red'
						})
					}
				})

				$('input:checkbox').each(function() {
					var checkboxId = $(this).attr('id')
					// 		console.log(checkboxId)
					if (checkboxId === 'applied_cb') {
						$(this).iCheck({
							checkboxClass : 'icheckbox_square-blue'
						})
					} else if (checkboxId === 'accepted_cb') {
						$(this).iCheck({
							checkboxClass : 'icheckbox_square-green'
						})
					} else if (checkboxId === 'rejected_cb') {
						$(this).iCheck({
							checkboxClass : 'icheckbox_square-red'
						})
					} else if (checkboxId === 'not_interested_cb') {
						$(this).iCheck({
							checkboxClass : 'icheckbox_square-yellow'
						})
					}
				})
				// 	End Quick Filter Button Facinating

				var applianceStatus = -1;
				var customerStatus = [];

				$('input[name="appliance_status_rb"]').on('ifClicked',
						function() {

							if ($(this).is(':checked')) {
								$(this).iCheck('uncheck')
								applianceStatus = -1
								quickFilter()
							}

						})
				$('input[name="appliance_status_rb"]').on('ifChanged',
						function() {
							applianceStatus = $(this).val()
							quickFilter()
						})

				$('input[name="eligibility_status"]').on('ifChecked',
						function() {

							customerStatus.push($(this).val())
							quickFilter()
							// 		console.log("Customer Status : "+customerStatus)
						})

				$('input[name="eligibility_status"]').on(
						'ifUnchecked',
						function() {

							var index = $('input[name="eligibility_status"]')
									.index(this)
							customerStatus.splice($.inArray($(this).val(),
									customerStatus), 1)
							quickFilter()
							// 		console.log("Customer Status : "+customerStatus)
						})

				// 	function dummyFilter(){
				// 		console.log("Appliance Status : "+applianceStatus)
				// 		console.log("Eligibility Status : "+customerStatus)
				// 		quickFilter()
				// 	}

				// 	Begin Quick Filter 
				function quickFilter() {

					// 	console.log(customerStatus)
					table.clear();
					table = $('#customer_table')
							.DataTable(
									{
										"destroy" : true,
										"order" : [ [ 2, "asc" ] ], //default order of column 1 / Order by Name
										"processing" : true,
										"serverSide" : true,
										"ajax" : {
											"url" : 'QuickFilterController',
											"type" : 'POST',
											"data" : function(d) {
												d.action = "quickFilter"
												// 	 				d.customername = $("[name = 'customer_name']").val();
												d.customerstatus = customerStatus
												// 	 				d.customermonthlyincome = $("[name = 'customer_monthly_income']").val();
												// 	 				d.appliancename = $("[name = 'appliance_name']").val();
												// 	 				d.appliancenumber = $("[name = 'appliance_number']").val();
												d.appliancestatus = applianceStatus;
												// 	 				d.district = $("[name = 'district']").val();
												// 	 				d.salesmanname = $("[name = 'salesman_name']").val()
											},
											"dataSrc" : function(json) {
												// 					$('.page-header span').text(json.recordsTotal)
												for ( var i = 0; i < json.data.length; i++) {

													json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
													json.data[i].select = '<span class="label label-info"><span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
															+ json.data[i].applianceName
															+ '</span>'
													data.customerName = '<a href="ViewServlet?click=view&gsm='
															+ data.customerPhoneNumber
															+ '&applianceId='
															+ data.applianceId
															+ '>'
															+ data.customerName
															+ "</a>"
													// 							json.data[i].view = '<a href="ViewServlet?click=view&gsm='+json.data[i].customerPhoneNumber+'">'+'view'+'</a>'

													//Status
													if (json.data[i].eligibilityStatus == 0) {
														json.data[i].status = '<span class="label label-primary">Applied</span>'
													} else if (json.data[i].eligibilityStatus == 1
															|| json.data[i].eligibilityStatus == 6) {
														json.data[i].status = '<span class="label label-success">Accepted</span>'
													} else if (json.data[i].eligibilityStatus == 2
															|| json.data[i].eligibilityStatus == 4) {
														json.data[i].status = '<span class="label label-danger">Rejected</span>'
													} else if (json.data[i].eligibilityStatus == 3) {
														json.data[i].status = '<span class="label bg-orange">Not Intrested</span>'
													} else if (json.data[i].eligibilityStatus == 7) {
														json.data[i].status = '<span class="label bg-green">Verified</span>'
													} else if (json.data[i].eligibilityStatus == 8) {
														json.data[i].status = '<span class="label bg-green">Ready for pickup</span>'
													}

													//Appliance Status

													if (json.data[i].applianceStatus == 0) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
													} else if (json.data[i].applianceStatus == 1) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
													}
												}
												return json.data;
											},

										//					,"success" : function(data){
										//						console.log(data)
										//					},	

										}

										,
										"rowCallback" : function(row, data) {
											//	 			if($.inArray(data.DT_RowId, selected) !== -1){
											$(row)
													.data(
															'link',
															'ViewServlet?click=view&gsm='
																	+ data.customerPhoneNumber
																	+ '&applianceId='
																	+ data.applianceId)
											$(row).addClass(
													'link-not-first-child')
											//	 			}
										},
										"columns" : [ {
											"data" : "selectRow",
											"orderable" : false,
											"searchable" : false
										}, {
											"data" : "select",
											"orderable" : false,
											"searchable" : false
										}, {
											"data" : "customerName"
										}, {
											"data" : "districtName"
										},
										// 			             {"data" : "status",
										// 			            	"orderable":false,
										// 							"searchable":false},
										{
											"data" : "monthlyIncome"
										},
										// 			             {"data" : "applianceName"}
										],
										"columnsDefs" : []
									//disable ordering feature of Column 0 / Select / Checkbox

									});
				}
				// 	End Quick Filters

				// 	Begin Get Count Active, Inactive, Applied, Accepted, Rejected, Not Interested
				$.ajax({
					url : 'CustomerController',
					method : 'POST',
					data : {
						action : 'getAllCountCustomerTableStatus'
					},
					dataType : 'json',
					success : function(data) {
						// 			console.log("Quick Filters Count" )
						// 			console.log(data)
						$(data).each(
								function() {
									// 				console.log(this)
									if (this.Active !== undefined
											&& this.Active !== 0) {
										$('#active_rb_li span.label').text(
												this.Active)
									}
									if (this.Inactive !== undefined
											&& this.Inactive !== 0) {
										$('#inactive_rb_li span.label').text(
												this.Inactive)
									}
									if (this.Applied !== undefined
											&& this.Applied !== 0) {
										$('#applied_cb_li span.label').text(
												this.Applied)
									}
									if (this.Accepted !== undefined
											&& this.Accepted !== 0) {
										$('#accepted_cb_li span.label').text(
												this.Accepted)
									}
									if (this.Rejected !== undefined
											&& this.Rejected !== 0) {
										$('#rejected_cb_li span.label').text(
												this.Rejected)
									}
									if (this.NotInterested !== undefined
											&& this.NotInterested !== 0) {
										$('#not_interested_cb_li span.label')
												.text(NotInterested)
									}
								})
					}
				})
				// End Get Count Active, Inactive, Applied, Accepted, Rejected, Not Interested

				var selected = [];

				var customertable = $('#customer_table')
						.DataTable(
								{
									// 		dom : 'C<"clear">lfrtip',
									"destroy" : true,
									"order" : [ [ 2, "asc" ] ], //default order of column 1 / Order by Name
									"processing" : true,
									"serverSide" : true,
									"ajax" : {
										"url" : 'CustomerController',
										"type" : 'POST',
										"data" : function(d) {
											d.action = "getCustomersByLimitAndRange"
										},
										"dataSrc" : function(json) {
											// 											console.log(json)
											$('.page-header span').text(
													json.recordsTotal)
											for ( var i = 0; i < json.data.length; i++) {

												json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
												json.data[i].select = '<span class="label label-info">  <span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
														+ json.data[i].applianceName
														+ '</span>'
												// 						json.data[i].customerName = '<a href="ViewServlet?click=view&gsm='+data.customerPhoneNumber+'&applianceId='+data.applianceId+'>'+data.customerName+"</a>"
												// 						json.data[i].view = '<a href="ViewServlet?click=view&gsm='+json.data[i].customerPhoneNumber+'">'+'view'+'</a>'

												json.data[i].customerName = "<a href='ViewServlet?click=view&gsm="
														+ json.data[i].customerPhoneNumber
														+ "&applianceId="
														+ json.data[i].applianceId
														+ "'>"
														+ json.data[i].customerName
														+ "</a>"

												if (json.data[i].eligibilityStatus == 0) {
													json.data[i].status = '<span class="label label-primary">Applied</span>'
												} else if (json.data[i].eligibilityStatus == 1
														|| json.data[i].eligibilityStatus == 6) {
													json.data[i].status = '<span class="label label-success">Accepted</span>'
												}
												// 						else if(json.data[i].eligibilityStatus == 2 || json.data[i].eligibilityStatus == 4){
												// 							json.data[i].status = '<span class="label label-danger">Rejected</span>'
												// 						}
												else if (json.data[i].eligibilityStatus == 3) {
													json.data[i].status = '<span class="label bg-orange">Not Intrested</span>'
												}

												//Appliance Status

												if (json.data[i].applianceStatus == 0) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
												} else if (json.data[i].applianceStatus == 1) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
												}

											}
											return json.data;
										},

									//				,"success" : function(data){
									//					console.log(data)
									//				},	

									}

									,
									"rowCallback" : function(row, data) {
										// 			if($.inArray(data.DT_RowId, selected) !== -1){
										$(row)
												.data(
														'link',
														'ViewServlet?click=view&gsm='
																+ data.customerPhoneNumber
																+ '&applianceId='
																+ data.applianceId)
										$(row).addClass('link-not-first-child')
										// 			}
									},
									"columns" : [ {
										"data" : "selectRow",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "select",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "customerName"
									}, {
										"data" : "districtName"
									},
									// 		             {"data" : "status",
									// 		            	"orderable":false,
									// 						"searchable":false},
									{
										"data" : "monthlyIncome"
									},
									// 		             {"data" : "applianceName"}
									],
									"columnsDefs" : []
								//disable ordering feature of Column 0 / Select / Checkbox

								});

				// 	$.ajax({
				// 		url : 'CustomerController',
				// 		method : 'POST',
				// 		data : {
				// 			action : 'getAllCountCustomerTableStatus'
				// 		},
				// 		dataType : 'json',
				// 		success : function(data){
				// // 			console.log("Quick Filters Count" )
				// // 			console.log(data)
				// 			$(data).each(function(){
				// // 				console.log(this)
				// 				if(this.Active !== undefined && this.Active !== 0){
				// 					$('#active_rb_li span.label').text(this.Active)
				// 				}
				// 				if(this.Inactive !== undefined && this.Inactive !== 0){
				// 					$('#inactive_rb_li span.label').text(this.Inactive)
				// 				}
				// 				if(this.Applied !== undefined && this.Applied !== 0){
				// 					$('#applied_cb_li span.label').text(this.Applied)
				// 				}
				// 				if(this.Accepted !== undefined && this.Accepted !== 0){
				// 					$('#accepted_cb_li span.label').text(this.Accepted)
				// 				}
				// 				if(this.Rejected !== undefined && this.Rejected !== 0){
				// 					$('#rejected_cb_li span.label').text(this.Rejected)
				// 				}
				// 				if(this.NotInterested !== undefined && this.NotInterested !== 0){
				// 					$('#not_interested_cb_li span.label').text(NotInterested)
				// 				}
				// 			})
				// 		}
				// 	})
				// End Get Count Active, Inactive, Applied, Accepted, Rejected, Not Interested

				var selected = [];

				var table = $('#customer')
						.DataTable(
								{
									// 		dom : 'C<"clear">lfrtip',
									"destroy" : true,
									"order" : [ [ 2, "asc" ] ], //default order of column 1 / Order by Name
									"processing" : true,
									"serverSide" : true,
									"ajax" : {
										"url" : 'CustomerController',
										"type" : 'POST',
										"data" : function(d) {
											d.action = "getRejectedCustomers"
										},
										"dataSrc" : function(json) {
											// 											$('.page-header span').text( json.recordsTotal)
											for ( var i = 0; i < json.data.length; i++) {
												// 						console.log(json.data[i].customerName)
												// 						alert(json.data[i].customerName);
												json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
												json.data[i].select = '<span class="label label-danger">  <span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
														+ json.data[i].applianceName
														+ '</span>'
												// 						json.data[i].customerName = '<a href="ViewServlet?click=view&gsm='+data.customerPhoneNumber+"&applianceId="+data.applianceId+">"+json.data[i].customerName+"</a>"
												// 						json.data[i].view = '<a href="ViewServlet?click=view&gsm='+json.data[i].customerPhoneNumber+'">'+'view'+'</a>'

												// 						if(json.data[i].eligibilityStatus == 0){
												// 							json.data[i].status = '<span class="label label-primary">Applied</span>'
												// 						}
												// 						else if(json.data[i].eligibilityStatus == 1 || json.data[i].eligibilityStatus == 6){
												// 							json.data[i].status = '<span class="label label-success">Accepted</span>'
												// 						}
												// 						if(json.data[i].eligibilityStatus == 2 || json.data[i].eligibilityStatus == 4){
												// 							json.data[i].status = '<span class="label label-danger">Rejected</span>'
												// 						}
												//  						else if(json.data[i].eligibilityStatus == 3){
												// 							json.data[i].status = '<span class="label bg-orange">Not Interested</span>'
												// 						}

												// 						//Appliance Status

												// 						if(json.data[i].applianceStatus == 0){
												// 							json.data[i].applianceStatus = '<span class="label label-danger">Inactive</span>'
												// 						}
												// 						else if(json.data[i].applianceStatus == 1){
												// 							json.data[i].applianceStatus = '<span class="label label-success">Active</span>'
												// 						}

											}
											return json.data;
										},

									//				,"success" : function(data){
									//					console.log(data)
									//				},	

									}

									,
									"rowCallback" : function(row, data, index) {
										// 			if($.inArray(data.DT_RowId, selected) !== -1){
										$(row)
												.data(
														'link',
														'ViewServlet?click=view&gsm='
																+ data.customerPhoneNumber
																+ '&applianceId='
																+ data.applianceId)
										$(row).addClass('link-not-first-child')
										// 			}
									},
									"columns" : [ {
										"data" : "selectRow",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "select",
										"orderable" : false,
										"searchable" : false
									},

									{
										"data" : "customerName"
									}, {
										"data" : "districtName"
									},
									// 		             {"data" : "status",
									// 		            	"orderable":false,
									// 						"searchable":false},
									{
										"data" : "monthlyIncome"
									},
									// 		             {"data" : "applianceName"}
									],
									"columnsDefs" : []
								//disable ordering feature of Column 0 / Select / Checkbox

								});

				// 	Advanced Filter 
				$('#btn_filter')
						.click(
								function() {
									table.clear();
									table = $('#customer_table')
											.DataTable(
													{
														"destroy" : true,
														"order" : [ [ 2, "asc" ] ], //default order of column 1 / Order by Name
														"processing" : true,
														"serverSide" : true,
														"ajax" : {
															"url" : 'AdvancedFilterController',
															"type" : 'POST',
															"data" : function(d) {
																d.click = "customerfilter"
																d.customername = $(
																		"[name = 'customer_name']")
																		.val();
																d.customerstatus = $(
																		"[name = 'customer_status']")
																		.val();
																d.customermonthlyincome = $(
																		"[name = 'customer_monthly_income']")
																		.val();
																d.appliancename = $(
																		"[name = 'appliance_name']")
																		.val();
																d.appliancenumber = $(
																		"[name = 'appliance_number']")
																		.val();
																d.appliancestatus = $(
																		"[name = 'appliance_status']")
																		.val();
																d.district = $(
																		"[name = 'district']")
																		.val();
																d.salesmanname = $(
																		"[name = 'salesman_name']")
																		.val()
															},
															"dataSrc" : function(
																	json) {
																// 					$('.page-header span').text(json.recordsTotal)
																for ( var i = 0; i < json.data.length; i++) {

																	json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
																	json.data[i].select = '<span class="label label-info"><span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
																			+ json.data[i].applianceName
																			+ '</span>'
																	// 							json.data[i].view = '<a href="ViewServlet?click=view&gsm='+json.data[i].customerPhoneNumber+'">'+'view'+'</a>'

																	//Status
																	if (json.data[i].eligibilityStatus == 0) {
																		json.data[i].status = '<span class="label label-primary">Applied</span>'
																	} else if (json.data[i].eligibilityStatus == 1
																			|| json.data[i].eligibilityStatus == 6) {
																		json.data[i].status = '<span class="label label-success">Accepted</span>'
																	} else if (json.data[i].eligibilityStatus == 2
																			|| json.data[i].eligibilityStatus == 4) {
																		json.data[i].status = '<span class="label label-danger">Rejected</span>'
																	} else if (json.data[i].eligibilityStatus == 3) {
																		json.data[i].status = '<span class="label bg-orange">Not Intrested</span>'
																	}

																	//Appliance Status

																	if (json.data[i].applianceStatus == 0) {
																		json.data[i].applianceStatus = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
																	} else if (json.data[i].applianceStatus == 1) {
																		json.data[i].applianceStatus = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
																	}

																}
																return json.data;
															},

														//					,"success" : function(data){
														//						console.log(data)
														//					},	

														}

														,
														"rowCallback" : function(
																row, data) {
															//	 			if($.inArray(data.DT_RowId, selected) !== -1){
															$(row)
																	.data(
																			'link',
																			'ViewServlet?click=view&gsm='
																					+ data.customerPhoneNumber
																					+ '&applianceId='
																					+ data.applianceId)
															$(row)
																	.addClass(
																			'link-not-first-child')
															//	 			}
														},
														"columns" : [
																{
																	"data" : "selectRow",
																	"orderable" : false,
																	"searchable" : false
																},
																{
																	"data" : "select",
																	"orderable" : false,
																	"searchable" : false
																},
																{
																	"data" : "customerName"
																},
																{
																	"data" : "districtName"
																},
																// 			             {"data" : "status",
																// 			            	"orderable":false,
																// 							"searchable":false},
																{
																	"data" : "monthlyIncome"
																},
														// 			             {"data" : "applianceName"}
														],
														"columnsDefs" : []
													//disable ordering feature of Column 0 / Select / Checkbox

													});
								})

				$("#customer_table tbody").on('click', 'tr>td',
						function(event) {
							// 		link = $(this).parent().data('link');
							console.log($(this).text())
							if ($(this).eq(0).html() != "") {
								// 			if (link != undefined) {
								// 				window.location=link;
								// 			}			
							}
						});

				$("#customer tbody").on('click', 'tr>td', function(event) {
					// 		link = $(this).parent().data('link');
					console.log($(this).text())
					if ($(this).eq(0).html() != "") {
						// 			if (link != undefined) {
						// 				window.location=link;
						// 			}			
					}
				});

				$("#customer_table tbody").on('click', 'tr>td>span>span.fa',
						function(event) {
							var span = $(this)
							var tr = $(this).closest('tr');
							var row = customertable.row(tr);
							console.log("tr : ");
							console.log(tr)
							console.log("Row : ");
							console.log(row)
							console.log("Row Data : ");
							console.log(row.data())
							if (row.child.isShown()) {
								row.child.hide();
								tr.removeClass('shown');
								$(span).addClass('fa-plus-circle')
								$(span).removeClass('fa-minus-circle')
							} else {
								row.child(format(row.data())).show();
								tr.addClass('shown');
								$(span).addClass('fa-minus-circle')
								$(span).removeClass('fa-plus-circle')
							}
						});

				$("#customer tbody").on('click', 'tr>td>span>span.fa',
						function(event) {
							var span = $(this)
							var tr = $(this).closest('tr');
							var row = table.row(tr);
							if (row.child.isShown()) {
								row.child.hide();
								tr.removeClass('shown');
								$(span).addClass('fa-plus-circle')
								$(span).removeClass('fa-minus-circle')
							} else {
								row.child(formatRejection(row.data())).show();
								tr.addClass('shown');
								$(span).addClass('fa-minus-circle')
								$(span).removeClass('fa-plus-circle')
							}
						});

				function format(d) {
					var id = "inner_table_" + d.customerId
					$('#' + id).remove();

					var innerTable = "<table id='"+id+"'>";
					$
							.getJSON(
									"GetCustomer?click=getExpansion&id="
											+ d.customerId,
									{},
									function(result, response) {
										$
												.each(
														result,
														function(key, value) {
															console.log(result)
															if (value.length <= 0) {
																$('#' + id)
																		.append(
																				"Record Not Found");
															} else {
																$('#' + id)
																		.append(
																				'<th class="p-10">Appliance Name</th>'
																						+ '<th class="p-10">Nizam Dost Name</th>'
																						+ '<th class="p-10">Charging Status</th>'
																						+ '<th class="p-10">Loan Status</th>')
																for ( var i = 0; i < value.length; ++i) {
																	//                 	   console.log(value[i].applianceName)
																	var row;
																	row += '<tr>'
																			+ '<td class="p-r-10 p-l-10 p-b-5">'
																			+ value[i].applianceName
																			+ '</td>'
																			+ '<td class="p-r-10 p-l-10 p-b-5">'
																			+ value[i].salesmanName
																			+ '</td>';
																	// 					            if(value[i].eligibilityStatus == 0 ) {
																	// 					            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-success">'+ "applied" +'</span></td>'
																	// 					            }else if(value[i].eligibilityStatus == 1 || value[i].eligibilityStatus == 6){
																	// 					            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-success">'+ 'accepted' +'</span></td>'
																	// 					            }else if(value[i].eligibilityStatus == 2 || value[i].eligibilityStatus == 4){
																	// 					            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-danger">'+ 'rejected' +'</span></td>'
																	// 					            }

																	// 					            if(value[i].isDefaulted == 1) {
																	// 						            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-success">'+ 'pickup' +'</span></td>'
																	// 						        }else if(value[i].transferStatus == 0) {
																	// 					            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-success">'+ 'applied' +'</span></td>'
																	// 					            }else if(value[i].transferStatus == 1){
																	// 					            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-success">'+ 'sold' +'</span></td>'
																	// 					            }else if(value[i].transferStatus == 2){
																	// 					            row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label label-success">'+ 'handover' +'</span></td>'
																	// 					            }

																	if (value[i].applianceStatus == 1) {
																		row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:#16a085; color:white;font-weight: bold;">'
																				+ 'active'
																				+ '</span></td>'
																	} else {
																		row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:Red; color:white;font-weight: bold;">'
																				+ 'inActive'
																				+ '</span></td>'
																	}

																	if (value[i].loanStatus == 'defaulter') {
																		row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:#34495e; color:white;font-weight: bold;">'
																				+ value[i].loanStatus
																				+ '</span></td>'
																	} else if (value[i].loanStatus == 'maintained') {
																		row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:blue; color:white;font-weight: bold;">'
																				+ value[i].loanStatus
																				+ '</span></td>'
																	} else if (value[i].loanStatus == 'owned') {
																		row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:#7f8c8d; color:black;font-weight: bold;">'
																				+ value[i].loanStatus
																				+ '</span></td>'
																	}

																	row += '</tr>'
																			+ '';
																}
																$('#' + id)
																		.append(
																				row)
															}
														})

									})
					return innerTable + "</table>";
				}

				function formatRejection(d) {
					var id = "inner_table_" + d.customerId
					$('#' + id).remove();

					var innerTable = "<table id='"+id+"'>";
					$
							.getJSON(
									"GetCustomer?click=getRejectedExpansion&id="
											+ d.customerId,
									{},
									function(result, response) {
										$
												.each(
														result,
														function(key, value) {

															if (value.length <= 0) {
																$('#' + id)
																		.append(
																				"Record Not Found");
															} else {
																//                 		alert(value.length)
																$('#' + id)
																		.append(
																				'<th class="p-10">Appliance Name</th>'
																						+ '<th class="p-10">Nizam Dost Name</th>'
																						+ '<th class="p-10">Rejection Purpose</th>')
																for ( var i = 0; i < value.length; i++) {
																	// 	                	   alert(value.length)
																	var row;
																	row += '<tr>'
																			+ '<td class="p-r-10 p-l-10 p-b-5">'
																			+ value[i].applianceName
																			+ '</td>'
																			+ '<td class="p-r-10 p-l-10 p-b-5">'
																			+ value[i].salesmanName
																			+ '</td>'
																			+ '<td class="p-r-10 p-l-10 p-b-5">'
																			+ value[i].purpose
																			+ '</td>';

																	row += '</tr>'
																			+ '';
																}
																$('#' + id)
																		.append(
																				row)
															}
														})

									})
					return innerTable + "</table>";
				}

				function checkUserIdExists(applianceId) {
					return $.ajax({
						url : 'GetCustomer',
						click : 'get',
						type : 'GET',
						cache : false,
						data : {
							id : applianceId
						},
						success : function(data) {
							return data;
						}
					});
				}

				// 	$(document).ready(function() {
				// add multiple select / deselect functionality
				$("#selectall").click(function() {
					// 			$('.case').each(function(){alert($(this).val())})
					$('.case').attr('checked', this.checked);

				});
				// if all checkbox are selected, check the selectall checkbox  also        

				$("#customer_table tbody")
						.on(
								'click',
								'tr>td>input.case',
								function() {
									// 			console.log(this)
									var selText = $(this).attr('value');
									data.push(selText);

									var all = new Array();
									if ($(".case").length == $(".case:checked").length) {

										$("#selectall").attr("checked",
												"checked");

									} else {
										$("#selectall").removeAttr("checked");
									}

								});
				// 	});
				// 	$('#customer_table tbody').on('click', 'tr', function(){
				// 		alert($(this).data('link'))
				// 		var id = this.id;
				// // 		var index = $.inArray(id, selected);

				// // 		if(index === -1){
				// // 			selected.push(id)
				// // 		}else{
				// // 			selected.splice(index, 1)
				// // 		}

				// // 		$(this).toggleClass('active')
				// 	})

				//		$.ajax({
				//			url : 'CustomerController',
				//			method : 'POST',
				//			data : {
				//				action : "getCustomers"
				//			},
				//			success : function(data){
				//				console.log(data)
				//			}
				//		})
			})
			var data = [];
		</script>
		<!-- End Initialize Customer Table -->

		<!-- Begin Advanced Filter -->
		<script>
			$(function() {

				$('#advanced_filter').hide();

				$('#switch_filter').click(function() {
					$('#advanced_filter').slideToggle();
					$('#basic_filter').fadeToggle();
				})

				// 	$('#advanced_filter').bind('afterToggle', function(){alert("hell")})
				// 	Begin Get All Appliance Name by SolTo	
				$.ajax({
					url : 'ApplianceController',
					method : 'POST',
					data : {
						action : 'getAllApplianceNameBySoldTo'
					},
					dataType : "JSON",
					success : function(data) {
						// 				$('#customer_monthly_income').empty();
						// 				$('#customer_monthly_income').append('<option value="0">Select Salary Range</option>');
						// 				console.log("Monthly Income")
						// 				console.log(data)
						$.each(data, function(e) {
							var option = document.createElement('option');
							$(option).attr("value",
									"'" + data[e].applianceName + "'")
							$(option).text("'" + data[e].applianceName + "'")
							// 					var option = '<option value="\''+data[e].applianceName+'\'">'+data[e].applianceName+'</option>';
							$('#appliance_name').append(option);
						});
					}

				});

				// 	End Get All Appliance Name by SolTo

				// 	Begin Get All Customer Salary Range	
				$
						.ajax({
							url : 'CustomerController',
							method : 'POST',
							data : {
								action : 'getAllCustomerSalaryRanges'
							},
							dataType : "JSON",
							success : function(data) {
								// 				$('#customer_monthly_income').empty();
								// 				$('#customer_monthly_income').append('<option value="0">Select Salary Range</option>');
								// 				console.log("Monthly Income")
								// 				console.log(data)
								$
										.each(
												data,
												function(e) {
													var option = '<option value="'+data[e].salaryRangeId+'">'
															+ data[e].salaryRange
															+ '</option>';
													$(
															'#customer_monthly_income')
															.append(option);
												});
							}

						});

				// 	End Get All Customer Salary Range

				// 	Begin Get All VLE By Default
				$
						.ajax({
							url : 'SalesmanController',
							method : 'POST',
							data : {
								click : 'getAllSalesman'
							},
							dataType : "JSON",
							success : function(data) {
								$('#salesman_name').empty();
								$('#salesman_name')
										.append(
												'<option value="0">Select Nizam Dost</option>');
								// 				console.log("VLE List")
								// 				console.log(data)
								$
										.each(
												data,
												function(e) {
													var option = '<option value="'+data[e].salesmanId+'">'
															+ data[e].salesmanName
															+ '</option>';
													$('#salesman_name').append(
															option);
												});
							}

						});
				// 	End Get All VLE By Default

				//  Begin Get All District By Default
				$
						.ajax({
							url : 'DistrictController',
							method : 'POST',
							data : {
								click : 'getAllDistrictBySoldTo'
							},
							dataType : "JSON",
							success : function(data) {
								// 				$('#salesman_name').empty();
								// 				$('#salesman_name').append('<option value="0">Select Nizam Dost</option>');
								// 				console.log("District List")
								// 				console.log(data)
								$
										.each(
												data,
												function(e) {
													var option = '<option value="'+data[e].districtId+'">'
															+ data[e].districtName
															+ '</option>';
													$('#district').append(
															option);
												});
							}

						});

				// 	End Get All District By Default

				// 	Begin On Select District Change VLE List
				$('#district')
						.change(
								function() {
									var districtId = $(this).val();
									$
											.ajax({
												url : 'SalesmanController',
												method : 'POST',
												data : {
													click : 'getSalesmansByDistrictId',
													districtid : districtId
												},
												dataType : "JSON",
												success : function(data) {
													$('#salesman_name').empty();
													$('#salesman_name')
															.append(
																	'<option value="0">Select Nizam Dost</option>');
													$
															.each(
																	data,
																	function(e) {
																		var option = '<option value="'+data[e].salesmanId+'">'
																				+ data[e].name
																				+ '</option>';
																		$(
																				'#salesman_name')
																				.append(
																						option);
																	});
												}

											});
								});
				// 	End On Select District Change VLE List

				// 	function filter(){
				// 		console.clear();
				// 		console.log("click")
				// 		var customername = $("[name = 'customer_name']").val();
				// 		var customerstatus = $("[name = 'customer_status']").val();
				// 		var customermonthlyincome = $("[name = 'customer_monthly_income']").val();
				// 		var appliancename = $("[name = 'appliance_name']").val();
				// 		var appliancenumber = $("[name = 'appliance_number']").val();
				// 		var appliancestatus = $("[name = 'appliance_status']").val();
				// 		var district = $("[name = 'district']").val();
				// 		var salesmanname = $("[name = 'salesman_name']").val();
				// // 		var status = $("[name = 'status']").val();

				// 		console.log("customer name : "+customername+", customer status : "+customerstatus+", customer monthly income "+customermonthlyincome);
				// 		console.log("appliance status : "+appliancestatus);
				// 		console.log("appliance name : "+appliancename+", appliance number : "+appliancenumber);
				// 		console.log("district: "+district);
				// 		console.log("VLE name : "+salesmanname);
				// 		var table = $('#customer_table').dataTable({
				// 			"ajax" : {
				// 				"url" : 'CustomerController',
				// 				"type" : 'POST',
				// 				"data" : function(d) {
				// 					d.action = "getCustomersByLimitAndRange"
				// 				},
				// 				"dataSrc" : function(json){
				// 					$('.page-header span').text(json.recordsTotal)
				// 						for(var i = 0; i < json.data.length; i++){

				// 							json.data[i].selectRow = '<input type="checkbox" class="case">'
				// 							//Status
				// 							/* if(json.data[i].eligibilityStatus == 0){
				// 								json.data[i].status = '<span class="label label-primary">Applied</span>'
				// 							}
				// 							else if(json.data[i].eligibilityStatus == 1 || json.data[i].eligibilityStatus == 6){
				// 								json.data[i].status = '<span class="label label-success">Accepted</span>'
				// 							}
				// 							else if(json.data[i].eligibilityStatus == 2 || json.data[i].eligibilityStatus == 4){
				// 								json.data[i].status = '<span class="label label-danger">Rejected</span>'
				// 							}
				// 							else if(json.data[i].eligibilityStatus == 3){
				// 								json.data[i].status = '<span class="label bg-orange">Not Interested</span>'
				// 							} */

				// 							//Appliance Status

				// 							/* if(json.data[i].applianceStatus == 0){
				// 								json.data[i].applianceStatus = '<span class="label label-danger">Inactive</span>'
				// 							}
				// 							else if(json.data[i].applianceStatus == 1){
				// 								json.data[i].applianceStatus = '<span class="label label-success">Active</span>'
				// 							} */

				// 						}
				// 					return json.data;
				// 				},

				// //					,"success" : function(data){
				// //						console.log(data)
				// //					},	

				// 			}
				// 		})
				// 		$.ajax({
				// 			url:'AdvancedFilterController',
				// 			data : {
				// 				click : "customerfilter",
				// 				customername : customername,
				// 				customerstatus : customerstatus,
				// 				customermonthlyincome : customermonthlyincome,
				// 				appliancename : appliancename,
				// 				appliancenumber : appliancenumber,
				// 				appliancestatus : appliancestatus,
				// 				district : district,
				// 				salesmanname : salesmanname
				// 			},
				// 			method : 'post',
				// 			success : function(data){
				// 				var table = $('#customer_table').DataTable()

				// 				try{

				// 					table.clear();
				// 				}catch(e){
				// 					console.log(e)
				// 				}
				// //					table.
				// //					$('#applianceTable').empty();
				// 				var jsonResult = JSON.parse(data);
				// 					console.log(jsonResult)
				// 				$('.alert').alert('close')
				// 				if(jsonResult.map){
				// 					if (jsonResult.map.length == 0) {
				// 						console.log("Length Zero")
				// 						var alert = '<div class="alert alert-danger fade in">'
				// 							+'<span class="close" data-dismiss="alert">×</span>'
				// 							+'<i class="fa fa-check fa-2x pull-left"></i>'
				// 							+'<p>Data Not Found</p>'
				// 							+'</div>';
				// 							$('#customerPanel .panel-body').before(alert)
				// 					}

				// 					$.each(jsonResult.map, function(e){
				// 						var chargingStatus;
				// 						if (jsonResult.map[e].applianceStatus == 1) {
				// 							chargingStatus = '<span class="label label-success">Active</span>';
				// 						}
				// 						else if (jsonResult.map[e].applianceStatus == 0) {
				// 							chargingStatus = '<span class="label label-danger">Inactive</span>';
				// 						}else{
				// 							chargingStatus = '<span class="label label-default">Data Not Exists</span>';
				// 						}

				// 						var customerStatus;

				// 						if(jsonResult.map[e].customerStatus == 0){

				// 							customerStatus = '<span class="label label-info">Pending</span>';
				// 						}else if(jsonResult.map[e].customerStatus == 1){
				// 							customerStatus = '<span class="label label-success">Accepted</span>';
				// 						}else if(jsonResult.map[e].customerStatus == 2){
				// 							customerStatus = '<span class="label label-danger">Rejected</span>';

				// 						}else if(jsonResult.map[e].customerStatus == 3){
				// 							customerStatus = '<span class="label label-warning">owned</span>';										
				// 						}else if(jsonResult.map[e].customerStatus == 4){
				// 							customerStatus = '<span class="label label-default">returned</span>';										
				// 						}
				// 						var node = table.row.add(["<input type='checkbox' class='case' value='"+jsonResult.map[e].phoneNo+"'>",
				// 						               jsonResult.map[e].customerName,
				// 						               jsonResult.map[e].district,
				// 						               customerStatus,
				// 						               jsonResult.map[e].monthlyIncome,
				// 						               jsonResult.map[e].applianceName,
				// 						               chargingStatus]).draw().node();
				// 						if (jsonResult.map[e].customerStatus == 1) {
				// 							$(node).addClass('link-not-first-child');
				// 							$(node).data('link',"ViewServlet?click=view&id="+jsonResult.map[e].soldId+"&doId="+jsonResult.map[e].salesmanId+"&idGet="+jsonResult.map[e].customerId);																
				// 						}
				// 						redirect();
				// // 						id=soldId doId=salesmanId idGet=customerId
				// /* 									var tr = document.createElement("tr");
				// 						var td = "<tr><td>"+jsonResult.map[e][0]+"</td>"
				// 							+"<td>"+jsonResult.map[e][1]+"</td>"
				// 							+"<td>"+jsonResult.map[e][2]+"</td>"										
				// 							+"<td>"+jsonResult.map[e][3]+"</td>"
				// 							+"<td>"+jsonResult.map[e][4]+"</td>"
				// 							+"<td>"+jsonResult.map[e][5]+"</td>"
				// 							+"<td>"+jsonResult.map[e][6]+"</td>"
				// 							+"<td>"+jsonResult.map[e][7]+"</td></tr>";
				// 							$('#applianceTable').append(td);*/
				// //							console.log(jsonResult.map[e])
				// 					})
				// //						$('#data-table').DataTable().draw();
				// 				}else{
				// 					console.log("Data not Exists");
				// 					var alert = '<div class="alert alert-danger fade in">'
				// 					+'<span class="close" data-dismiss="alert">×</span>'
				// 					+'<i class="fa fa-check fa-2x pull-left"></i>'
				// 					+'<p>Data Not Found</p>'
				// 					+'</div>';
				// 					$('#appliancePanel .panel-body').before(alert)
				// 				}
				// 			}
				// 		})
				// 	}
				$('#btn_clear').click(function() {
					filter();
				})
				// 	$('#btn_filter').click(function(){
				// 		filter();

				// 	});//end btn filter
			});
		</script>
		<!-- End Advanced Filter -->

		<!-- <script type="text/javascript">
		$('.chcktbl').click(function() {
			var length = $('.chcktbl:checked').length;

			alert(length);

		});
	</script>
 -->
		<script type="text/javascript">
			// 		var data = [];
			// 		$(document).ready(function() {
			// 			// add multiple select / deselect functionality
			// 			$("#selectall").click(function() {
			// 				$('.case').each(function(){alert($(this).val())})
			// 				$('.case').attr('checked', this.checked);

			// 			});
			// 			// if all checkbox are selected, check the selectall checkbox  also        

			// 			$(".case").click(function() {
			// 				var selText = $(this).attr('value');
			// 				alert(selText)
			// 				data.push(selText);

			// 				var all = new Array();
			// 				if ($(".case").length == $(".case:checked").length) {

			// 					$("#selectall").attr("checked", "checked");

			// 				} else {
			// 					$("#selectall").removeAttr("checked");
			// 				}

			// 			});
			// 		});
		</script>
		<script>
			$("#myDialog")
					.dialog(
							{
								autoOpen : false,
								modal : true,
								title : "",
								buttons : {
									'OK' : function() {
										var textValue = $('#myTextBox').val();
										var myJsonString = JSON.stringify(data);

										for (i = 0; i < data.length; i++) {
											// alert(data[i]);
										}

										$
												.ajax({

													url : 'SendMessage_All?phone='
															+ myJsonString
															+ '&msg='
															+ textValue,
													success : function(data) {
														document
																.getElementById("emailErrorMsg").innerHTML = data;
													}
												});

										data.length = 0;
										$('#msgid').find(
												'input[type=checkbox]:checked')
												.removeAttr('checked');
										$(this).dialog("close");
										// alert((data[i]));}

										// alert('The value of the text box is ' + textValue);
										//Now you have the value of the textbox, you can do something with it, maybe an AJAX call to your server!
									},
									'Close' : function() {
										alert('The Close button was clicked');
										$(this).dialog('close');
									}
								}
							});

			//Open the dialog box when the button is clicked.
			$('#clickMe').click(function() {
				$("#myDialog").dialog("open");
			});
		</script>

		<script>
			// 		$(document).ready(function() {
			// 			$('input:radio[name = "filter"]').click(function() {
			// 				var i = $(this).val()
			// 				if (i == 1) {
			// 					$('.0, .1, .2, .3, .4,.s-0').hide()
			// 					$('.s-1').show();
			// 				} else if (i == 2) {
			// 					$('.s-1, .0, .1, .2, .3, .4').hide()
			// 					$('.s-0').show();

			// 				} else if (i == 3) {
			// 					$('.1, .2, .3, .4, .6').hide()
			// 					$('.0').show();

			// 				} else if (i == 4) {
			// 					$('.0, .2, .3, .4').hide()
			// 					$('.1').show();
			// 					$('.6').show();

			// 				} else if (i == 5) {
			// 					$('.0, .1, .3, .6').hide()
			// 					$('.2').show();
			// 					$('.4').show();

			// 				} else if (i == 6) {
			// 					$('.0, .1, .2, .4, .6').hide()
			// 					$('.3').show();

			// 				} else if (i == 7) {
			// 					$('.0, .1, .3, .2, .4, .5, .6').show()
			// 				}
			// 			});

			// 			$('#data-table_paginate').click(function() {
			// 				filterAppliance();
			// 			});

			// 			//				cal_func();
			// 			//				$('#buttonLogin').click(function() {
			// 			//					$('#login_Box_Div').toggle();
			// 			//				});

			// 			//				App.init();
			// 			//				MorrisChart.init();

			// 			//				FormPlugins.init();
			// 			//				TableManageColVis.init();
			// 			//				DashboardV2.init();
			// 		});

			$(document).ready(function() {

				// 			$('#buttonLogin').click(function() {
				// 				$('#login_Box_Div').toggle();
				// 			});
				App.init();
				// 			TableManageColVis.init();
				// 		   FormPlugins.init();
				ChartJs.init();
			});
		</script>






		<%
			}
		%>
	
</body>
<!-- Mirrored from seantheme.com/color-admin-v1.7/admin/html/page_with_light_sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 24 Apr 2015 11:01:38 GMT -->
</html>
