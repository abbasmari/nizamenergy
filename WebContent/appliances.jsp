<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bean.ApplianceInfoBean"%>
<%@page import="bal.ApplianceBAL"%>
<%@page import="bean.EligibilityBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

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
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />

<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->

<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->

<!-- ================== END BASE JS ================== -->
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

<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />

</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("shsLogin.jsp");
		} else {
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

					<li class="has_sub"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="Request"> <i
							class="icon-note"></i> <span>New Loan Request</span> <span
							class="badge pull-right" id="unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub"><a href="Customer"> <i
							class="ion-ios-people"></i> <span>Customers</span>
					</a></li>
					<li class="active"><a href="Appliances"> <i
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
				Appliances <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold">
				</span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" id="appliancePanel">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Appliances</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px;">

								<span id="basic_filter">

									<ul
										class="nav nav-pills f-s-15 inline bordered round-corner m-r-5">

										<li class=""><a class="m-0 p-5 m-r-15" id="active_rb_li">
												<input type="radio" name="appliance_status_rb"
												id="active_rb" value="1"> <span class="label"
												style="background-color: #16a085; color: white; font-weight: bold;"></span>
												<label for="active_rb">Active</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15"
											id="inactive_rb_li"> <input type="radio"
												name="appliance_status_rb" id="inactive_rb" value="0">
												<span class="label"
												style="background-color: red; color: white; font-weight: bold;"></span>
												<label for="inactive_rb">Inactive</label>
										</a></li>
									</ul>


									<ul class="nav nav-pills f-s-15 inline bordered round-corner">
										<li class=""><a class="m-0 p-5 m-r-15" id="dumb_cb_li">
												<input type="checkbox" name="health_status_cb" id="dumb_cb"
												value="0"> <span class="label label-default"></span>
												<label for="dumb_cb">Dumb</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15" id="dead_cb_li">
												<input type="checkbox" name="health_status_cb" id="dead_cb"
												value="1"> <span class="label bg-purple"></span> <label
												for="dead_cb">DEAD</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15"
											id="dead_since_cb_li"> <input type="checkbox"
												name="health_status_cb" id="dead_since_cb" value="2">
												<span class="label"
												style="background-color: #7f8c8d; color: white; font-weight: bold;"></span>
												<label for="dead_since_cb">DEAD Since</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15" id="alive_cb_li">
												<input type="checkbox" name="health_status_cb" id="alive_cb"
												value="3"> <span class="label"
												style="background-color: #42a4f4; color: white; font-weight: bold;"></span>
												<label for="alive_cb">ALIVE</label>
										</a></li>




									</ul>




									<ul class="nav nav-pills f-s-15 inline bordered round-corner">
										<li class=""><a class="m-0 p-5 m-r-15" id="pending_cb_li">
												<input type="checkbox" name="sold_status_cb" id="pending_cb"
												value="0"> <span class="label label-default"></span>
												<label for="pending_cb">Pending</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15" id="rta_cb_li">
												<input type="checkbox" name="sold_status_cb" id="rta_cb"
												value="1"> <span class="label bg-purple"></span> <label
												for="rta_cb">Ready to assign</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15" id="afd_cb_li">
												<input type="checkbox" name="sold_status_cb" id="afd_cb"
												value="5"> <span class="label"
												style="background-color: #7f8c8d; color: white; font-weight: bold;"></span>
												<label for="afd_cb" title="Awaiting For Downpayment">ADP</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15" id="rdp_cb_li">
												<input type="checkbox" name="sold_status_cb" id="rdp_cb"
												value="2"> <span class="label"
												style="background-color: #42a4f4; color: white; font-weight: bold;"></span>
												<label for="rdp_cb">RDP</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15"
											id="installed_ns_cb_li"> <input type="checkbox"
												name="sold_status_cb" id="installed_ns_cb" value="7">
												<span class="label bg-green"></span> <label
												for="installed_ns_cb">Installed-NS</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15"
											id="installed_cb_li"> <input type="checkbox"
												name="sold_status_cb" id="installed_cb" value="6"> <span
												class="label bg-green"></span> <label for="installed_cb">Installed</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15"
											id="returned_cb_li"> <input type="checkbox"
												name="sold_status_cb" id="returned_cb" value="4"> <span
												class="label"
												style="background-color: #d35400; color: white; font-weight: bold;"></span>
												<label for="returned_cb">Returned</label>
										</a></li>



									</ul>


								</span>

								<div id="msgid" style="padding-top: 12px">

									<table id="appliance-table"
										class="table table-hover table-bordered">
										<thead>
											<tr>
												<th><input type="checkbox" id="selectall" /></th>
												<th>Name</th>
												<th>IMEI</th>
												<th>Charging Status</th>
												<th>District</th>
												<th>Customer Name</th>
												<th>Nizam Dost</th>
												<th>Field Officer</th>
												<th>Transferred Status</th>
												<th>Health Check</th>
											</tr>
										</thead>

									</table>
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
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->



		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"> <i class="fa fa-angle-up"></i>
		</a>
		<!-- end scroll to top btn -->


		<!-- footer start -->

		<!-- footer end -->

		<%
			}
		%>

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
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->


	<script src="assets/plugins/morris/raphael.min.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/dashboard-v2.min.js"></script>
	<script src="assets/js/chart-morris.demo.minAppliance.js"></script>

	<!-- 	<script src="assets/js/apps.min.js"></script> -->
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/js/chart-morris.demo.minAppliance.js"></script>

	<script src="assets/js/chart-morris.demo.minAppliance.js"></script>

	<script src="assets/plugins/icheck/icheck.min.js"></script>


	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- appliance table async process -->


	<script>
		var table;
		$(function() {
			$.ajax({
				url : "ApplianceController",
				type : 'POST',
				dataType : 'json',
				data : {
					action : "countApplianceAndSoldStatus"
				},
				success : function(data) {
					console.log("All Count")
					console.log(data)

					if (data.active != undefined && data.active != 0) {
						$('#active_rb_li span').text(data.active)
					} else {
						$('#active_rb_li span').hide();
					}

					// 					InActive
					if (data.inactive != undefined && data.inactive != 0) {
						$('#inactive_rb_li span').text(data.inactive)
					} else {
						$('#inactive_rb_li span').hide();
					}
					if (data.pending != undefined && data.pending != 0) {
						$('#pending_cb_li span').text(data.pending)
					} else {
						$('#pending_cb_li span').hide();
					}

					// 					Ready To Assign
					if (data.readyToAssign != undefined
							&& data.readyToAssign != 0) {
						$('#rta_cb_li span').text(data.readyToAssign)
					} else {
						$('#rta_cb_li span').hide();
					}
					if (data.returned != undefined && data.returned != 0) {
						$('#returned_cb_li span').text(data.returned)
					} else {
						$('#returned_cb_li span').hide();
					}
					if (data.awaitingForDownpayment != undefined
							&& data.awaitingForDownpayment != 0) {
						$('#afd_cb_li span').text(data.awaitingForDownpayment)
					} else {
						$('#afd_cb_li span').hide();
					}
					if (data.installed != undefined && data.installed != 0) {
						$('#installed_cb_li span').text(data.installed)
					} else {
						$('#installed_cb_li span').hide();
					}
					if (data.installedNoSignal != undefined
							&& data.installedNoSignal != 0) {
						$('#installed_ns_cb_li span').text(
								data.installedNoSignal)
					} else {
						$('#installed_ns_cb_li span').hide();
					}
					if (data.rdp != undefined && data.rdp != 0) {
						$('#rdp_cb_li span').text(data.rdp)
					} else {
						$('#rdp_cb_li span').hide();
					}
					if (data.dumb != undefined && data.dumb != 0) {
						$('#dumb_cb_li span').text(data.dumb)
					} else {
						$('#dumb_cb_li span').hide();
					}

					if (data.dead != undefined && data.dead != 0) {
						$('#dead_cb_li span').text(data.dead)
					} else {
						$('#dead_cb_li span').hide();
					}
					if (data.dead_since != undefined && data.dead_since != 0) {
						$('#dead_since_cb_li span').text(data.dead_since)
					} else {
						$('#dead_since_cb_li span').hide();
					}
					if (data.alive != undefined && data.alive != 0) {
						$('#alive_cb_li span').text(data.alive)
					} else {
						$('#alive_cb_li span').hide();

					}

				}

			})

			// 			end count active, inactive, pending, sold, handoverd, owned returned, 
			// ---------------------------------------------------------------------------------
			// 			Begin Create Quick Filters Buttons

			$(
					'[name="appliance_status_rb"], [name="sold_status_cb"],[name="health_status_cb"] ')
					.each(function() {
						var id = $(this).attr('id');
						var inputClass
						if (id == 'active_rb') {
							inputClass = 'icheckbox_square-blue'
						} else if (id == 'inactive_rb') {
							inputClass = 'icheckbox_square-red'
						} else if (id == 'pending_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'rta_cb') {
							inputClass = 'icheckbox_square-purple'
						} else if (id == 'installed_cb') {
							inputClass = 'icheckbox_square-green'
						} else if (id == 'handovered_cb') {
							inputClass = 'icheckbox_square-orange'
						} else if (id == 'afd_cb') {
							inputClass = 'icheckbox_square-yellow'
						} else if (id == 'returned_cb') {
							inputClass = 'icheckbox_square-orange'
						} else if (id == 'rdp_cb') {
							inputClass = 'icheckbox_square-blue'
						} else if (id == 'installed_ns_cb') {
							inputClass = 'icheckbox_square-green'
						} else if (id == 'dumb_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'dead_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'dead_since_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'alive_cb') {
							inputClass = 'icheckbox_square-blue'
						}

						$(this).iCheck({
							radioClass : inputClass,
							checkboxClass : inputClass
						})

					})
			var applianceStatus = -1;
			$('[name="appliance_status_rb"]').on('ifClicked', function() {
				if ($(this).is(":checked")) {
					$(this).iCheck('uncheck')
					applianceStatus = -1;
				} else {
					applianceStatus = $(this).val()
				}
				quickFilters()

			})

			// 			Manage Check of Sold Status Checkbox
			var soldStatus = [];
			$('[name="sold_status_cb"]').on('ifChanged', function() {
				if ($.inArray($(this).val(), soldStatus) != -1) {
					soldStatus.splice($.inArray($(this).val(), soldStatus), 1)
				} else {
					soldStatus.push($(this).val())
				}
				quickFilters()

			})

			var healthStatus = [];
			$('[name="health_status_cb"]').on(
					'ifChanged',
					function() {
						if ($.inArray($(this).val(), healthStatus) != -1) {
							// 					console.log("In Array")
							// 					$.soldStatus.sp
							// 					$(this).iCheck('uncheck')
							healthStatus.splice($.inArray($(this).val(),
									healthStatus), 1)
						} else {
							// 					console.log("Not in Array")
							healthStatus.push($(this).val())
						}
						// 				console.log($.inArray($(this).val(), soldStatus))
						quickFilters()

					})

			// 			Begin Quick Filters
			function quickFilters() {
				table.clear()
				$('#appliance-table')
						.DataTable(
								{
									dom : "lftipr",
									destroy : true,
									"processing" : true,
									"serverSide" : true,
									"order" : [ [ 1, "asc" ] ],
									"ajax" : {
										"url" : "QuickFilterController",
										"type" : "post",
										"data" : function(d) {
											d.action = "getApplianceByQuickFilters"
											d.applianceStatus = applianceStatus
											d.soldStatus = soldStatus
											d.healthStatus = healthStatus
										},

										"dataSrc" : function(json) {
											console.log('===============');
											console.log(json);
											console.log(json.data);
											console.log(json.applianceCount);

											$('.page-header span').text(
													json.applianceCount)

											for ( var i = 0; i < json.data.length; i++) {

												if (json.data[i].imeiNumber == undefined
														|| json.data[i].imeiNumber == null
														|| json.data[i].imeiNumber == ' ') {
													json.data[i].imeiNumber = "Not Assigned";
												}

												json.data[i].selectRow = '<input type="checkbox"  class="case" onclick="onSelect(this)" value="'
														+ json.data[i].applianceGSM
														+ '">'
												// appliance status

												// 												if (json.data[i].isDefaulted == 1) {
												// 													json.data[i].applianceStatus = '<span class="label label-default">Pick up</span>'
												// 												} else 

												console
														.log(json.data[i].applianceStatus)
												if (json.data[i].applianceStatus == 0) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 1) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 2) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#42a4f4; color:white;font-weight: bold;">RDP</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'

												} else if (json.data[i].applianceStatus == 3) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#7f8c8d; color:white;font-weight: bold;">Owner</span>'
												} else if (json.data[i].applianceStatus == 4) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 5) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment </span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 6) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed </span>'
												} else if (json.data[i].applianceStatus == 7) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed and NoSignal</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD</span>'
												}

												if (json.data[i].applianceStatusActive == 0) {
													json.data[i].applianceStatusActive = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
												} else if (json.data[i].applianceStatusActive == 1) {
													json.data[i].applianceStatusActive = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
												}

												if (json.data[i].applianceName == "60 W"
														|| json.data[i].applianceName == "P-60"
														|| json.data[i].applianceName == "7 W") {

													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'

												} else {

													if (json.data[i].islive == 1) {

														json.data[i].islive = '<span class="label"  style="background-color:#2980b9; color:white;font-weight: bold;">ALIVE</span>'

													} else if (json.data[i].islive == 0) {

														if (json.data[i].deadSince == 0) {
															json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Today</span>'

														} else if (json.data[i].deadSince == 1) {

															json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Yesterday</span>'
														} else {

															json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since '
																	+ json.data[i].deadSince
																	+ ' days</span>'

														}
													}
												}

												// 												

											}
											return json.data;
										},

									// 								success : function(data){
									// 									console.log("-----------------");
									// 									console.log(data);
									// 								}
									},

									"columns" : [ {
										"data" : "selectRow",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "applianceName"
									}, {
										"data" : "imeiNumber",
										"orderable" : false,
									}, {
										"data" : "applianceStatusActive",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "salesmanDistrict"
									}, {
										"data" : "customerName"
									},

									{
										"data" : "salesmanName"
									}, {
										"data" : "foName"
									}, {
										"data" : "applianceStatus",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "islive",
										"orderable" : false,
										"searchable" : false

									} ],

									"rowCallback" : function(row, data) {
										//			if($.inArray(data.DT_RowId, selected) !== -1){
										$(row).data(
												'link',
												'ViewServlet?click=viewAppliance&id='
														+ data.applianceId)
										//$(row).addClass('link');
										$(row).addClass('link-not-first-child')
										//			}
									},

									"columnsDefs" : [ {
										"targets" : 0,
										"orderable" : false
									}, {
										"targets" : 3,
										"orderable" : false
									}, {
										"targets" : 7,
										"orderable" : false
									} ]
								//disable ordering feature of Column 0 / Select / Checkbox

								});

			}
			// 			End Quick Filters

			// 			var table = $('#appliance-table').dataTable();

			// 			on load Page create Table
			table = $('#appliance-table')
					.DataTable(
							{
								dom : "lftirps",
								"destroy" : true,
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 1, "asc" ] ],
								"ajax" : {
									"url" : "ApplianceController",
									"type" : "post",
									"data" : function(d) {
										d.action = "getApplianceTable"
									},

									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.applianceCount)

										for ( var i = 0; i < json.data.length; i++) {
											if (json.data[i].imeiNumber == undefined
													|| json.data[i].imeiNumber == null
													|| json.data[i].imeiNumber == ' ') {
												json.data[i].imeiNumber = "Not Assigned";
											}

											json.data[i].selectRow = '<input type="checkbox" class="case" onclick="onSelect(this)" value="'
													+ json.data[i].applianceGSM
													+ '">'
											if (json.data[i].applianceStatus == 0) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
												json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
											} else if (json.data[i].applianceStatus == 1) {
												json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												json.data[i].applianceStatus = '<span class="label " style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
											} else if (json.data[i].applianceStatus == 2) {
												json.data[i].applianceStatus = '<span class="label"  style="background-color:#42a4f4; color:white;font-weight: bold;">RDP</span>'
												json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'

											} else if (json.data[i].applianceStatus == 3) {
												json.data[i].applianceStatus = '<span class="label"  style="background-color:#7f8c8d; color:white;font-weight: bold;">Owner</span>'
											} else if (json.data[i].applianceStatus == 4) {
												json.data[i].applianceStatus = '<span class="label"  style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
												json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
											} else if (json.data[i].applianceStatus == 5) {
												json.data[i].applianceStatus = '<span class="label"  style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment</span>'
												json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
											} else if (json.data[i].applianceStatus == 6) {
												json.data[i].applianceStatus = '<span class="label"  style="background-color:green; color:white;font-weight: bold;">Installed </span>'
											} else if (json.data[i].applianceStatus == 7) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed and NoSignal</span>'
												json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD</span>'
											}
											// appliance active/inactive status

											if (json.data[i].applianceStatusActive == 0) {
												json.data[i].applianceStatusActive = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
											} else if (json.data[i].applianceStatusActive == 1) {
												json.data[i].applianceStatusActive = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
											}

											if (json.data[i].applianceName == "60 W"
													|| json.data[i].applianceName == "P-60"
													|| json.data[i].applianceName == "7 W") {

												json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'

											} else {

												if (json.data[i].islive == 1) {

													json.data[i].islive = '<span class="label"  style="background-color:#2980b9; color:white;font-weight: bold;">ALIVE</span>'

												} else if (json.data[i].islive == 0) {

													if (json.data[i].deadSince == 0) {
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since today</span>'

													} else if (json.data[i].deadSince == 1) {

														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Yesterday</span>'
													} else {

														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since '
																+ json.data[i].deadSince
																+ ' days</span>'

													}
												}

											}

											//  											}
											// 													|| json.data[i].health == null
											// 													|| json.data[i].health == 'null'){
											//  												json.data[i].health='<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD</span>'

											//  											}else if(!(json.data[i].health == undefined
											// 													|| json.data[i].health == null
											// 													|| json.data[i].health == 'null')){
											//  												json.data[i].health='<span class="label"  style="background-color:#2980b9; color:white;font-weight: bold;">LIVE Since'+json.data[i].health+'</span>'

											//  											}

										}

										return json.data;
									},

								// 								success : function(data){
								// 									console.log("-----------------");
								// 									console.log(data);
								// 								}
								},

								"columns" : [ {
									"data" : "selectRow",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "applianceName"
								}, {
									"data" : "imeiNumber",
									"orderable" : false,
								}, {
									"data" : "applianceStatusActive",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "salesmanDistrict"
								}, {
									"data" : "customerName"
								},

								{
									"data" : "salesmanName"
								}, {
									"data" : "foName"
								}, {
									"data" : "applianceStatus",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "islive",
									"orderable" : false,
									"searchable" : false
								} ],

								"rowCallback" : function(row, data) {
									//			if($.inArray(data.DT_RowId, selected) !== -1){
									$(row).data(
											'link',
											'ViewServlet?click=viewAppliance&id='
													+ data.applianceId)

									$(row).addClass('link-not-first-child')
									//			}
								},

								"columnsDefs" : [ {
									"targets" : 0,
									"orderable" : false
								}, {
									"targets" : 3,
									"orderable" : false
								}, {
									"targets" : 7,
									"orderable" : false
								} ]
							//disable ordering feature of Column 0 / Select / Checkbox

							});

			$("#appliance-table tbody").on('click',
					'tr.link-not-first-child>td:not(:first-child)',
					function(event) {
						link = $(this).parent().data('link');
						if (link != undefined) {
							window.location = link;
						}
					});

			$('#btn_filter').click(function() {
				advancedFilter()
			});//end btn filter

		});

		function advancedFilter() {
			table.clear()
			var customername = $("[name = 'customer_name']").val();
			// 			var customernumber = $("[name = 'customer_number']").val();
			var appliancestatus = $("[name = 'appliance_status']").val();
			var appliancename = $("[name = 'appliance_name']").val();
			var appliancenumber = $("[name = 'appliance_number']").val();
			var district = $("[name = 'district']").val();
			var salesmanname = $("[name = 'salesman_name']").val();
			// 			var salesmannumber = $("[name = 'salesman_number']").val();
			var status = $("[name = 'status']").val();
			console.log("customer name : " + customername);
			console.log("appliance status : " + appliancestatus);
			console.log("appliance name : " + appliancename
					+ ", appliance number : " + appliancenumber);
			console.log("district: " + district);
			console.log("salesman name : " + salesmanname);
			console.log("status : " + status);
			$('#appliance-table')
					.DataTable(
							{
								dom : "lftipr",
								destroy : true,
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 1, "asc" ] ],
								"ajax" : {
									"url" : "AdvancedFilterController",
									"type" : "post",
									"data" : function(d) {
										d.click = "appliancefilter"
										d.customername = customername
										// 										d.customernumber = customernumber
										d.appliancestatus = appliancestatus
										d.appliancename = appliancename
										d.appliancenumber = appliancenumber
										d.district = district
										d.salesmanname = salesmanname
										// 										d.salesmannumber = salesmannumber
										d.status = status

									},

									"dataSrc" : function(json) {
										console.log('===============');
										console.log(json);
										console.log(json.data);
										console.log(json.applianceCount);

										$('.page-header span').text(
												json.applianceCount + 'B')

										for ( var i = 0; i < json.data.length; i++) {

											if (json.data[i].imeiNumber == undefined
													|| json.data[i].imeiNumber == null
													|| json.data[i].imeiNumber == ' ') {
												json.data[i].imeiNumber = "Not Assigned";
											}

											json.data[i].selectRow = '<input type="checkbox" class="case"  class="case" onclick="onSelect(this)" value="'
													+ json.data[i].applianceGSM
													+ '">'
											// appliance status
											// 											if (json.data[i].isDefaulted == 1) {
											// 													json.data[i].applianceStatus = '<span class="label label-default">Pick up</span>'
											// 												} else 
											if (json.data[i].applianceStatus == 0) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
											} else if (json.data[i].applianceStatus == 1) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
											} else if (json.data[i].applianceStatus == 2) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Handover</span>'
											} else if (json.data[i].applianceStatus == 3) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#7f8c8d; color:white;font-weight: bold;">Owner</span>'
											} else if (json.data[i].applianceStatus == 4) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
											} else if (json.data[i].applianceStatus == 5) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment </span>'
											} else if (json.data[i].applianceStatus == 6) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed </span>'
											}

											// appliance active/inactive status

											if (json.data[i].applianceStatusActive == 0) {
												json.data[i].applianceStatusActive = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
											} else if (json.data[i].applianceStatusActive == 1) {
												json.data[i].applianceStatusActive = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
											}
										}

										return json.data;
									},

								// 								success : function(data){
								// 									console.log("-----------------");
								// 									console.log(data);
								// 								}
								},

								"columns" : [ {
									"data" : "selectRow",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "applianceName"
								}, {
									"data" : "imeiNumber"
								}, {
									"data" : "applianceStatusActive",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "salesmanDistrict"
								}, {
									"data" : "customerName"
								}, {
									"data" : "salesmanName"
								}, {
									"data" : "applianceStatus",
									"orderable" : false,
									"searchable" : false

								} ],

								"rowCallback" : function(row, data) {
									//			if($.inArray(data.DT_RowId, selected) !== -1){
									alert(data.customerCnic);
									$(row).data(
											'link',
											'ViewServlet?click=viewAppliance&id='
													+ data.applianceId)

									$(row).addClass('link-not-first-child')
									//			}
								},

								"columnsDefs" : [ {
									"targets" : 0,
									"orderable" : false
								}, {
									"targets" : 3,
									"orderable" : false
								}, {
									"targets" : 7,
									"orderable" : false
								} ]
							//disable ordering feature of Column 0 / Select / Checkbox

							});
		}
	</script>
	<!-- end appliance table async process -->

	<script type="text/javascript">
		$(function() {

			$('#district')
					.change(
							function() {
								var districtId = $(this).val()
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
												$('#salesman_name').empty()
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
																					option)
																})
											}

										})
							})

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

			//  Begin Get All District By Default
			$.ajax({
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
					$.each(data, function(e) {
						var option = '<option value="'+data[e].districtId+'">'
								+ data[e].districtName + '</option>';
						$('#district').append(option);
					});
				}

			});

			// 	End Get All District By Default

			function filter() {
				console.clear();
				console.log("click")
				var customername = $("[name = 'customer_name']").val();
				var customernumber = $("[name = 'customer_number']").val();
				var appliancestatus = $("[name = 'appliance_status']").val();
				var appliancename = $("[name = 'appliance_name']").val();
				var appliancenumber = $("[name = 'appliance_number']").val();
				var district = $("[name = 'district']").val();
				var salesmanname = $("[name = 'salesman_name']").val();
				var salesmannumber = $("[name = 'salesman_number']").val();
				var status = $("[name = 'status']").val();
				console.log("customer name : " + customername
						+ ", customer number : " + customernumber);
				console.log("appliance status : " + appliancestatus);
				console.log("appliance name : " + appliancename
						+ ", appliance number : " + appliancenumber);
				console.log("district: " + district);
				console.log("salesman name : " + salesmanname
						+ ", salesman number : " + salesmannumber);
				console.log("status : " + status);
				$
						.ajax({
							url : 'AdvancedFilterController',
							data : {
								click : "appliancefilter",
								customername : customername,
								customernumber : customernumber,
								appliancestatus : appliancestatus,
								appliancename : appliancename,
								appliancenumber : appliancenumber,
								district : district,
								salesmanname : salesmanname,
								salesmannumber : salesmannumber,
								status : status
							},
							method : 'post',
							success : function(data) {
								var table = $('#data-table').DataTable()
								table.clear();
								// 							table.
								// 							$('#applianceTable').empty();
								// 							console.log(data)
								var jsonResult = JSON.parse(data);
								$('.alert').alert('close')
								if (jsonResult.map) {
									$
											.each(
													jsonResult.map,
													function(e) {
														var chargingStatus;
														if (jsonResult.map[e][3] == 0) {
															chargingStatus = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>';
														} else if (jsonResult.map[e][3] == 1) {
															chargingStatus = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>';
														}

														var transferStatus;

														if (jsonResult.map[e][7] == 0) {

															transferStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>';
														} else if (jsonResult.map[e][7] == 1) {
															transferStatus = '<span class="label" style="background-color:#bdc3c7; color:purple;font-weight: bold;">Pending</span>';
														} else if (jsonResult.map[e][7] == 2) {
															transferStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Handover</span>';

														} else if (jsonResult.map[e][7] == 3) {
															transferStatus = '<span class="label label-success">owned</span>';
														} else if (jsonResult.map[e][7] == 4) {
															transferStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">returned</span>';
														} else if (jsonResult.map[e][7] == 5) {
															transferStatus = '<span class="label" style="background-color:yellow; color:black;font-weight: bold;">Ready for pick up</span>';

														} else if (jsonResult.map[e][7] == 3) {
															transferStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed</span>';
														}
														var node = table.row
																.add(
																		[
																				"<input onclick='onSelect(this)' type='checkbox' class='case' id='case' value= '"
																						+ jsonResult.map[e][2]
																						+ "'>",
																				jsonResult.map[e][1],
																				jsonResult.map[e][2],
																				chargingStatus,
																				jsonResult.map[e][4],
																				jsonResult.map[e][5],
																				jsonResult.map[e][6],
																				transferStatus ])
																.draw().node();
														$(node)
																.addClass(
																		'link-not-first-child');
														$(node)
																.data(
																		'link',
																		"ViewServlet?click=viewAppliance&id="
																				+ jsonResult.map[e][0]);

													})

								} else {
									console.log("Data not Exists");
									var alert = '<div class="alert alert-danger fade in">'
											+ '<span class="close" data-dismiss="alert">×</span>'
											+ '<i class="fa fa-check fa-2x pull-left"></i>'
											+ '<p>Data Not Found</p>'
											+ '</div>';
									$('#appliancePanel .panel-body').before(
											alert)
								}
							}
						})

			}

		})
	</script>
	<script type="text/javascript">
		var data = [];
		$(document).ready(function() {
			// add multiple select / deselect functionality
			$('#advanced_filter').hide();
			$('#basic_filter').show();

			$('#toggle_filter').click(function() {
				$('#advanced_filter').slideToggle();
				$('#basic_filter').fadeToggle();
				$(this).toggleClass('active')
			});

			$('')

			$("#selectall").click(function() {
				$('.case').attr('checked', this.checked);
			});
			// if all checkbox are selected, check the selectall checkbox  also        
			// 				$(".case").click(function() {
			// 					var selText = $(this).attr('value');
			// 					alert(selText)
			// 					data.push(selText);

			// 					var all = new Array();
			// 					if ($(".case").length == $(".case:checked").length) {

			// 						$("#selectall").attr("checked", "checked");

			// 					} else {
			// 						$("#selectall").removeAttr("checked");
			// 					}
			// 				});
		});
		function onSelect(checkbox) {
			var selText = $(checkbox).attr('value');
			// 				alert(selText)
			data.push(selText);

			var all = new Array();
			if ($(".case").length == $(".case:checked").length) {

				$("#selectall").attr("checked", "checked");

			} else {
				$("#selectall").removeAttr("checked");
			}
		}

		function on_device() {

			var myJsonString = JSON.stringify(data);
			// alert(myJsonString);
			for (i = 0; i < data.length; i++) {
				// alert(data[i]);
			}
			data.length = 0;
			$('#msgid').find('input[type=checkbox]:checked').removeAttr(
					'checked');
			$.ajax({

				url : 'SendMessage_All?phone=' + myJsonString + '&msg=on',
				success : function(data) {
					document.getElementById("emailErrorMsg").innerHTML = data;
				}
			});

		}

		function of_device() {

			var myJsonString = JSON.stringify(data);
			// 				alert(myJsonString);
			for (i = 0; i < data.length; i++) {
				// 					alert(data[i]);
			}
			data.length = 0;
			$('#msgid').find('input[type=checkbox]:checked').removeAttr(
					'checked');
			$.ajax({

				url : 'SendMessage_All?phone=' + myJsonString + '&msg=of',
				success : function(data) {
					document.getElementById("emailErrorMsg").innerHTML = data;
				}
			});

		}

		function GET_device() {

			var myJsonString = JSON.stringify(data);
			//  alert(myJsonString);
			for (i = 0; i < data.length; i++) {
				// alert(data[i]);
			}
			data.length = 0;
			$('#msgid').find('input[type=checkbox]:checked').removeAttr(
					'checked');
			$.ajax({

				url : 'SendMessage_All?phone=' + myJsonString + '&msg=get',
				success : function(data) {
					document.getElementById("emailErrorMsg").innerHTML = data;
				}
			});

		}
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			App.init();
		})
	</script>

</body>


</html>