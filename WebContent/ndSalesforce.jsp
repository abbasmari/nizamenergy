<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.FinanceBAL"%>
<%@page import="bean.FinanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>

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
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<!-- ================== END BASE CSS STYLE ================== -->

<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END BASE JS ================== -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<script type="text/javascript">
	$(function() {
		$('.date-picker').datepicker(
				{
					changeMonth : true,
					changeYear : true,
					showButtonPanel : true,
					dateFormat : 'yy-mm',
					onClose : function(dateText, inst) {
						$(this).datepicker(
								'setDate',
								new Date(inst.selectedYear, inst.selectedMonth,
										1));
					}
				});
	});
</script>
<style>
.ui-datepicker-calendar {
	display: none;
}
</style>
</head>
<body>


	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
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

					<li class="has-sub"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="Request"> <%-- <span class="badge pull-right"><%=countRequests%></span> --%>
							<i class="icon-note"></i> <span>New Loan Request</span> <!-- 						Begin	SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="unseen_loan_request_count"></span>
							<!-- 						End	SuperAdminHeader.js UnseenRequest() -->
					</a></li>
					<li class="has-sub"><a href="Customer"> <i
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
							<li><a href="doSaleforce.jsp">DO Sales</a></li>
							<li><a href="foSalesforce.jsp">FO Sales</a></li>
							<li><a href="ndSalesforce.jsp">ND Sales</a></li>
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
					<li class="has-sub"><a href="FinanceAnalytics.jsp"> <i
							class="ion-podium"></i> <span>Finance Analytics</span>
					</a></li>

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
			<h1 class="page-header">Sales Force Dashboard</h1>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-inverse">
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<ul class="nav nav-pills f-s-15 inline bordered round-corner">
									<li>
										<div class="row">
											<div class="col-xs-4">
												<label for="ex1">From:</label> <input
													class="date-picker form-control" name="fromDate"
													id="fromDate">
											</div>

											<div class="col-xs-4">
												<label for="ex1">To:</label> <input
													class="date-picker form-control" name="toDate" id="toDate">
											</div>

											<div class=" col-xs-1  ">
												<input class="btn btn-default" type="button"
													style="background-color: #AFB1A2; margin-top: 26px; border-color: greenyellow;"
													class="btn btn-sm btn-success" id="filterid" value="Filter"
													onclick="getTopNds()">
											</div>
										</div>
									</li>
								</ul>
								<table id="salesforce-nd-table" class="table table-bordered">
									<thead>
										<tr>
											<th>Nizam Dost</th>
											<th>District</th>
											<th>Current Sales</th>
											<th>Total Sales</th>
											<th>Recovery</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- begin row -->
			<%
				}
			%>
			<!-- end row -->
		</div>
		<!-- end #content -->



		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->

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
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<!-- <script src="assets/js/jquery-ui-1.8.18.custom.js"></script> -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		function getTopNds() {

			var table = $('#salesforce-nd-table').DataTable();

			table.clear();
			table.destroy();
			var to = document.getElementById("toDate").value;
			var from = document.getElementById("fromDate").value;

			$("#salesforce-nd-table")
					.dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],

								"ajax" : {
									"type" : "POST",
									"url" : "DoDashboardController2",
									"data" : function(d) {
										d.action = "getTopNds", d.to = to,
												d.from = from
									},
									"dataSrc" : function(json) {
										console.log('**********dataSrc*******');
										console.log(json);
										console.log(json.data);
										$
												.each(
														json.data,
														function(e) {
															json.data[e].recovery = json.data[e].recovery
																	+ " %";
														})
										console.log(json);
										console.log(json.data);
										return json.data;
									},
								},
								"columns" : [ {
									"data" : "NdName"
								}, {
									"data" : "districtName"
								}, {
									"data" : "sales"
								}, {
									"data" : "total_sales"
								}, {
									"data" : "recovery"
								} ]
							});
		}

		$(document).ready(function() {
			App.init();
			// 				getTopFos()
			// 			clearCache()
		});
	</script>
</body>


</html>
