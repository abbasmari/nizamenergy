<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="security.EncryptDecrypt"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.GraceBAL"%>
<%@page import="java.util.Calendar"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bal.Payment_loanNewBAL"%>
<%@page import="bean.Payment_LoanBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.DistrictBAL"%>
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

<link href="assets/css/mystyle.css" rel="stylesheet">

<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />
<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->


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

			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">
				<!-- begin col-12 -->
				<div class="col-md-12 ">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Future Loan Books Table</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">

								<table class="table table-bordered">
									<thead>
										<tr>
											<th>Date</th>
											<th>Maintained</th>
											<th>Late</th>
											<th>Defaulter</th>
										</tr>
									</thead>
									<tbody id="count_super_admin_loan_book_filters_toDate">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->

		<%
			} // session else closed
		%>

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
	<!-- 			 <script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script> -->
	<!-- ================== END BASE JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>

	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>

	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<!-- 			 <script src="assets/js/chart-js.demo.min.js"></script> -->
	<script src="assets/js/apps.min.js"></script>
	<script src="assets/plugins/icheck/icheck.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		function futureLoanBooks() {
			console.log("futureLoanBooks");

			$
					.ajax({
						url : 'FutureLoanBookController',
						method : 'POST',
						dataType : 'json',
						data : {
							action : 'getFutureLoanBooks'
						},
						success : function(data) {
							console.log('**********dataSrc*******');
							console.log('Length === ' + data.length);
							if (data.length > 0) {
								$
										.each(
												data,
												function(e) {
													console
															.log('count_super_admin_loan_book_filters_toDate ..... '
																	+ data);
													var tds = '<tr>'
															+ '<td>'
															+ data[e].Date
															+ '</td>'
															+ '<td>'
															+ data[e].Maintained
															+ '</td>' + '<td>'
															+ data[e].Late
															+ '</td>' + '<td>'
															+ data[e].Defaulter
															+ '</td>' + '</tr>';
													$(
															"#count_super_admin_loan_book_filters_toDate")
															.append(tds);
												});
							}
							return data;
						}
					});
		}
	</script>

	<script>
		$(document).ready(function() {
			App.init();
			futureLoanBooks();
		});
	</script>


</body>


</html>
