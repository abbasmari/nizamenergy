<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <% %> --%>
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
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
<link rel="stylesheet" href="style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>
<!-- ================== END BASE JS ================== -->

</head>
<body>


	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {

			ArrayList<FinanceBean> list = FinanceBAL.getPayments();
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
			<!-- 				<li class="active">Analytics</li> -->
			<!-- 			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Late Recoveries</h1>
			<!-- end page-header -->
			<table id="fiance_table" class="table table-hover table-bordered"
				style="background: whitesmoke;">
				<thead>
					<tr>
						<th>District</th>
						<th>Field Officer</th>
						<!-- 						                    <th>Customer Phone</th> -->
						<th>Nizam Dost</th>

						<!-- 						                    <th>Loan ID</th> -->

						<th>Customer</th>
						<th>Recovery</th>

						<th>Imei Number</th>

					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<div class="col-md-12">

				<div class="panel panel-inverse" data-sortable-id="morris-chart-4">
					<div class="panel-heading"
						style="background: #d9e0e7; color: black;">
						<!-- <div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div> -->
						<h4 class="panel-title" style="font-size: 20px;">SMS Counter
							Analytics Graph</h4>
					</div>
					<div class="panel-body" style="background: #d9e0e7">
						<!-- 							<h3>Salesmans Commission Graph</h3> -->
						<div id="chartdiv" style="width: 100%; height: 400px;"></div>

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
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		function table_load() {

			$("#fiance_table")
					.dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "LateRecoveryController",
									"type" : "get",
									"data" : function(d) {
										d.action = "getpaymentorderby"
									},
									"dataSrc" : function(json) {
										console.log('**********dataSrc*******');
										console.log(json);
										console.log(json.data);
										for (var i = 0; i < json.data.length; i++) {
											if (json.data[i].installment_paid) {
												json.data[i].installment_paid = json.data[i].installment_paid
														+ "<b> PKR</b>";
											}
										}

										console.log(json.data);
										return json.data;
									},
								},
								"columns" : [ {
									"data" : "bank_name"
								}, {
									"data" : "customer_name"
								}, {
									"data" : "date"
								}, {
									"data" : "transaction_id"
								}, {
									"data" : "installment_paid"
								}, {
									"data" : "imei_number"
								} ]
							});
		}

		function salesmanBarChart() {

			var chart;
			var salesmanData;

			$
					.ajax({

						url : 'SmsAnalytics',
						dataType : 'json',
						method : 'post',
						data : {},
						success : function(data) {

							console.log("===============");
							console.log(data[0].sms);

							salesmanData = data[0].sms;

							chart = new AmCharts.AmSerialChart();
							chart.dataProvider = salesmanData;
							chart.categoryField = "date";
							//	             chart.plotAreaBorderAlpha = 0.2;
							chart.dataDateFormat = "YYYY-MM-DD";
							chart.startDuration = 1;
							//	             chart.plotAreaBorderAlpha = 0.2;

							// AXES
							// category
							var categoryAxis = chart.categoryAxis;
							//	             categoryAxis.gridAlpha = 0.1;
							//	             categoryAxis.axisAlpha = 0;
							categoryAxis.equalSpacing = true;
							categoryAxis.parseDates = true;
							categoryAxis.minPeriod = "DD";
							categoryAxis.gridPosition = "start";
							//	             categoryAxis.parseDates = true;
							//	             categoryAxis.minPeriod = "DD";

							// value
							var valueAxis = new AmCharts.ValueAxis();
							valueAxis.stackType = "regular";
							//	             valueAxis.gridAlpha = 0.1;
							//	             valueAxis.axisAlpha = 0;
							chart.addValueAxis(valueAxis);

							// GRAPHS
							// first graph
							var graph = new AmCharts.AmGraph();
							graph.title = "Mobilink";
							graph.labelText = "[[value]]";
							graph.valueField = "mobilink";
							graph.type = "column";
							graph.lineAlpha = 0;
							graph.fillAlphas = 1;
							graph.lineColor = "#00acac";
							graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
							chart.addGraph(graph);

							// second graph
							graph = new AmCharts.AmGraph();
							graph.title = "Telenor";
							graph.labelText = "[[value]]";
							graph.valueField = "telenor";
							graph.type = "column";
							graph.lineAlpha = 0;
							graph.fillAlphas = 1;
							graph.lineColor = "#08C";
							graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
							chart.addGraph(graph);

							// third graph
							graph = new AmCharts.AmGraph();
							graph.title = "Ufone";
							graph.labelText = "[[value]]";
							graph.valueField = "ufone";
							graph.type = "column";
							graph.lineAlpha = 0;
							graph.fillAlphas = 1;
							graph.lineColor = "#E53935";
							graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
							chart.addGraph(graph);
							// SCROLLBAR
							var chartScrollbar = new AmCharts.ChartScrollbar();
							chartScrollbar.scrollbarHeight = 30;
							chartScrollbar.graph = graph; // as we want graph to be displayed in the scrollbar, we set graph here
							chartScrollbar.graphType = "line"; // we don't want candlesticks to be displayed in the scrollbar
							chartScrollbar.gridCount = 4;
							chartScrollbar.color = "#FFFFFF";
							chart.addChartScrollbar(chartScrollbar);
							// LEGEND
							var legend = new AmCharts.AmLegend();
							legend.borderAlpha = 0.2;
							legend.horizontalGap = 10;
							chart.addLegend(legend);
							// WRITE
							chart.write("chartdiv");
						}

					});

		}

		$(document).ready(function() {
			App.init();
			salesmanBarChart();
			table_load();
		});
	</script>

</body>


</html>
