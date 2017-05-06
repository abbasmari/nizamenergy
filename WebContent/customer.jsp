
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.SalaryBean"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bean.Suggestion"%>
<%@page import="bal.SaDoChatBAL"%>
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

<script>
	function setMoodValue(appliance_id, customer_name) {
		document.getElementById("customer_name").innerHTML = customer_name;
		document.getElementById('updateURL').onclick = function() {
			helloWorld(appliance_id);
		};
	}

	function helloWorld(applianceValue) {
		var link = "RevokeController?click=revoke&applianceId="
				+ applianceValue;
		document.getElementById('updateURL').href = link;

	}
</script>


</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
	%>
	<%
		ArrayList<HashMap<String, String>> list = ScheduleBAL
					.getNotInterstedCustomers();
			for (HashMap<String, String> hashMap : list) {
				int last_status = Integer.parseInt(hashMap
						.get("last_status"));
	%>
	<div class="modal fade" id="modal-revoke-customer">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Revoke Customer</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to revoke <b><i id="customer_name">
							</i></b> ?
						</p>
						</p>
					</div>

				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="updateURL"
						class="btn btn-sm btn-success">Accept</a>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

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
							<small> Superadmin </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">

					<li class="has-sub"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="Request"> <%-- <span class="badge pull-right"><%=countRequests%></span> --%>
							<i class="icon-note"></i> <span>New Loan Request</span> <!-- 						Begin	SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="unseen_loan_request_count"></span>
							<!-- 						End	SuperAdminHeader.js UnseenRequest() -->
					</a></li>
					<li class="active"><a href="Customer"> <i
							class="ion-ios-people"></i> <span>Customers</span>
					</a></li>
					<li class="has-sub"><a href="Appliances"> <i
							class="ion-cube"></i> <span>Pipeline</span>
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
									<!-- <button class="btn btn-xs btn-primary  m-r-5 m-l-5 pull-right"
										id="switch_filter">Advanced Filter</button> -->
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
												<label for="appliance_name">Appliance Name</label> <select
													class="form-control" id="appliance_name"
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
													<option value="0">Select VLE</option>

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







								<div class="row" style="padding-top: 2%; width: 100%;">
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
															<th>Customer Rating</th>
															<th>City, District</th>
															<!-- 															<th>Monthly Income</th> -->
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
															<!-- 															<th>Monthly Income</th> -->
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>

										<div class="tab-pane fade" id="notinterested">
											<div class="table-responsive" style="font-size: 13px;">
												<table class="table">
													<tr>
														<td><label class="label" for="verified"
															style="background-color: #1abc9c; color: white;">Verified</label>
															Not interested since 30 days</td>

													</tr>

													<tr>
														<td><label class="label" for="accepted"
															style="background-color: blue; color: white;">Accepted</label>
															Not interested since 10 days</td>

													</tr>
												</table>
												<table id="not_interested"
													class="table table-hover table-bordered">
													<thead>
														<tr>
															<th>Name</th>
															<th>Appliance</th>
															<th>Last status</th>
															<th>Action</th>
															<!--System.err.println(hashMap.get("customer_id")); -->

														</tr>
													</thead>
													<tbody>
														<%
															ArrayList<HashMap<String, String>> list1 = ScheduleBAL
																		.getNotInterstedCustomers();
																for (HashMap<String, String> hashMap : list1) {
																	int last_status = Integer.parseInt(hashMap
																			.get("last_status"));
																	String name = hashMap.get("appliance_name");
														%>
														<tr>
															<td><%=hashMap.get("customer_name")%></td>
															<td><%=hashMap.get("appliance_name")%></td>

															<%
																if (last_status == 1) {
															%>
															<td><label class="label"
																style="background-color: #2980b9; color: white; font-weight: bold">Accepted</label></td>
															<%
																} else if (last_status == 7) {
															%>
															<td><label class="label"
																style="background-color: #1abc9c; color: white; font-weight: bold">Verified</label></td>
															<%
																}
															%>

															<td><a href="#modal-revoke-customer"
																onclick="setMoodValue('<%=hashMap.get("appliance_id")%>', '<%=hashMap.get("customer_name")%>')"
																style="text-decoration: none;" data-toggle="modal"
																style="width:70%;">Revoke<span> </span></a></td>

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



								<%
									} //session else closed
								%>





							</div>
							<!-- end panel -->
						</div>
						<!-- end col-10 -->


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

		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<script type="text/javascript" src="assets/js/myscript.js"></script>
		<!-- ================== END BASE JS ================== -->


		<!-- ================== BEGIN PAGE LEVEL JS ================== -->



		<script src="assets/plugins/chart-js/chart.js"></script>
		<!--  <script src="assets/js/apps.min.js"></script> -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
		<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
		<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
		<script src="assets/js/table-manage-default.demo.min.js"></script>
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
										d.action = "getCustomersByLimitAndRange";
									},
									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.recordsTotal);
										console.log(json.data)
										for ( var i = 0; i < json.data.length; i++) {

											json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
											json.data[i].select = '<span class="label label-info">  <span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
													+ json.data[i].applianceName
													+ '</span>'
											// 						json.data[i].customerName = '<a href="ViewServlet?click=view&gsm='+data.customerPhoneNumber+'&applianceId='+data.applianceId+'>'+data.customerName+"</a>"
											// 						json.data[i].view = '<a href="ViewServlet?click=view&gsm='+json.data[i].customerPhoneNumber+'">'+'view'+'</a>'

											json.data[i].customerName = "<a href='ViewServlet?click=view&cnic="
													+ json.data[i].customerCnic
													+ "&applianceId="
													+ json.data[i].applianceId
													+ "'>"
													+ json.data[i].customerName
													+ "</a>";

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
									}
								},
								"rowCallback" : function(row, data) {
									// 			if($.inArray(data.DT_RowId, selected) !== -1){
									$(row).data(
											'link',
											'ViewServlet?click=view&cnic='
													+ data.customerCnic
													+ '&applianceId='
													+ data.applianceId);
									$(row).addClass('link-not-first-child');
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
									"data" : "customer_rating"
								}, {
									"data" : "districtName"
								} ],
								"columnsDefs" : []
							//disable ordering feature of Column 0 / Select / Checkbox

							});

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
										// 											$('.page-header span').text(json.data.length);
										for ( var i = 0; i < json.data.length; i++) {
											// 						console.log(json.data[i].customerName)
											// 						alert(json.data[i].customerName);
											json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
											json.data[i].select = '<span class="label label-danger">  <span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
													+ json.data[i].applianceName
													+ '</span>'

										}
										return json.data;
									}

								//				,"success" : function(data){
								//					console.log(data)
								//				},	

								}

								,
								"rowCallback" : function(row, data, index) {
									// 			if($.inArray(data.DT_RowId, selected) !== -1){
									$(row).data(
											'link',
											'ViewServlet?click=view&cnic='
													+ data.customerCnic
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
								}
								// 		             {"data" : "status",
								// 		            	"orderable":false,
								// 						"searchable":false},
								// 									{
								// 										"data" : "monthlyIncome"
								// 									},
								// 		             {"data" : "applianceName"}
								],
								"columnsDefs" : []
							//disable ordering feature of Column 0 / Select / Checkbox

							});

			$("#customer_table tbody").on('click', 'tr>td', function(event) {
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
																console
																		.log(value[i].applianceName)
																var row;
																row += '<tr>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].applianceName
																		+ '</td>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].salesmanName
																		+ '</td>';

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
															$('#' + id).append(
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
															$('#' + id).append(
																	row)
														}
													})

								})
				return innerTable + "</table>";
			}
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

			});
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

										if (myJsonString.length == 2) {
											alert('Please Select Customer');
											$(this).dialog("close");
										} else {
											$
													.ajax({

														url : 'SendMessage_All?phone='
																+ myJsonString
																+ '&msg='
																+ textValue,
														success : function(data) {
															document
																	.getElementById("emailErrorMsg").innerHTML = data;
															$('#myTextBox')
																	.val("");
														}
													});
										}
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
			$(document).ready(function() {
				$('a[href="#notinterested"]').click(function() {

					$("#clickMe").hide();
				});

				$('a[href="#ios"]').click(function() {
					$("#default").find('input[type=checkbox]').each(function() {
						// some staff

						this.checked = false;
					});

					$("#clickMe").show();
				});

				$('a[href="#default"]').click(function() {
					$("#ios").find('input[type=checkbox]').each(function() {
						// some staff
						this.checked = false;
					});

					$("#clickMe").show();
				});
				App.init();
				TableManageDefault.init();

			});
		</script>
</body>

</html>
