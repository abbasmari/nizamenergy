<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.Salesman"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="bean.RecoveryGraphBean"%>
<%@page import="bal.RecoveryGraphBAL"%>
<%@page import="bean.RankingGraphBean"%>
<%@page import="bal.RankingGraphBAL"%>


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

<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-multiple-select/css/bootstrap-multiple-select.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />



</head>
<body>


	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

		<!-- begin #header -->
		<%-- 		<%@include file="/superAdminHeader.jsp"%> --%>
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
					<li><a href="javascript:;" class="sidebar-minify-btn"
						data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>


				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->

		<!-- begin #content -->
		<div id="content" class="content">

			<h1 class="page-header">
				Key <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold">
					<%-- <%=bean.size() %> --%>
				</span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->

			<div class="row">
				<div class="col-md-12" class="bg-silver">
					<form data-serial class="form-horizontal">
						<!-- 						<legend>Comments</legend> -->
						<div class="form-group has-feedback">
							<div class="col-md-6">
								<input class="form-control" name="serial" id="serial" cols="40"
									rows="5" placeholder="write message here"
									style="width: 280px; height: 35px;" required>
								</textarea>
							</div>
							<div class="col-md-6">
								<input class="form-control" name="duedate" id="duedate"
									cols="40" rows="6" placeholder="write message here"
									style="width: 320px; height: 35px;" required>
								</textarea>
							</div>
							<div class="col-md-2">
								<button type="button" class="btn btn-primary pull-right"
									id="getKeyChain1">Send</button>
							</div>
							<div class="col-md-3 col-md-offset-3" style="margin-left: 326px;">
								<button type="button" class="btn btn-primary pull-right"
									id="getKeyChain2">Send</button>
							</div>
						</div>
					</form>
				</div>
				<div class="row">
					<div id="keys"></div>
				</div>

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

		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<!-- ================== END BASE JS ================== -->

		<script type="text/javascript" src="assets/js/myscript.js"></script>
		<script src="assets/plugins/parsley/dist/parsley.js"></script>

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script src="assets/js/apps.min.js"></script>
		<!-- ================== END PAGE LEVEL JS ================== -->

		<script>
			$(function() {
				$('#getKeyChain1').click(function() {
					if ($('[data-serial]').data('serial') != "true") {
						var serial = $('[name="serial"]').val();
						var duedate = $('[name="duedate"]').val();
						console.log(serial + " ..... " + duedate);
						$.ajax({
							url : 'KeyController',
							method : 'POST',
							dataType : 'json',
							data : {
								click : "insert",
								serial : serial,
								duedate : duedate
							},
							success : function(data) {
								console.log(data)
								console.log(data.key)
								$("#keys").append(data.key+"</br>");
							}
						})
					}
					clearText();
				})

			}) // end

			$(function() {
				$('#getKeyChain2').click(function() {
					if ($('[data-serial]').data('serial') != "true") {
						var serial = $('[name="serial"]').val();
						var duedate = $('[name="duedate"]').val();
						console.log(serial + " ..... " + duedate);
						$.ajax({
							url : 'KeyController',
							method : 'POST',
							dataType : 'json',
							data : {
								click : "insertSerial",
								serial : serial,
								duedate : duedate
							},
							success : function(data) {
								console.log(data)
								console.log(data.key)
								$("#keys").append(data.key+"</br>");
							}
						})
					}
					clearText();
				})

			}) // end
			function clearText() {
				document.getElementById('serial').value = "";
				document.getElementById('duedate').value = "";
			} // end
		</script>
		<script>
			$(document).ready(function() {
				App.init();
			});
		</script>
</body>


</html>
