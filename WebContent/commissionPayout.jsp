<%@page import="bean.Commission_payout"%>
<%@page import="bal.LoanPaymentBAL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
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
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>

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
		ArrayList<Commission_payout> vlelist = LoanPaymentBAL
				.get_vle_commissions();
		ArrayList<Commission_payout> folist = LoanPaymentBAL
				.get_fo_commissions();

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
							<i class="icon-note"></i> <span>New Loan Request</span> <!--       Begin SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="unseen_loan_request_count"></span>
							<!--       End SuperAdminHeader.js UnseenRequest() -->
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
			<h1 class="page-header">Commission Payout</h1>
			<!-- end page-header -->


			<div class="row" style="margin-top: 4%;">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#default-tab-1" data-toggle="tab">Nizam
								Dost Commission</a></li>
						<li class=""><a href="#default-tab-2" data-toggle="tab">Field
								Officer Commission</a></li>

					</ul>
					<div class="tab-content" style="font-size: 13px;">
						<div class="tab-pane fade active in" id="default-tab-1">
							<!-- begin panel -->
							<div class="panel panel-inverse">

								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px">
										<table id="data-table"
											class="table table-hover table-bordered">
											<thead>
												<tr>

													<th>Name</th>
													<th>FO Name</th>
													<th>Account number</th>
													<th>Total commission</th>
													<th>Previous month commission</th>
													<th>Pay Monthly Now</th>
													<th>Current month commission</th>
													<th>Default amount</th>
													<th>Unpaid hold amount</th>

													<!-- <th>Pay Unpayed Now</th> -->


												</tr>
											</thead>
											<tbody>
												<%
													for (int i = 0; i < vlelist.size(); i++) {

															DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
															Calendar cal = Calendar.getInstance();
															// 															System.out.println("today........... " + dateFormat.format(cal.getTime()));

															Calendar date = Calendar.getInstance();
															date.set(Calendar.DAY_OF_MONTH, 1);
															DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
															String startDate = df.format(date.getTime());
															// 															System.out.println("startDate ........... " + startDate);
												%>
												<tr>
													<td><%=vlelist.get(i).getCustomer_name()%></td>
													<td><%=vlelist.get(i).getFo_name()%></td>
													<%
														if (vlelist.get(i).getAcount_number() == "null"
																		|| vlelist.get(i).getAcount_number() == ""
																		|| vlelist.get(i).getAcount_number() == "0") {
													%>
													<td>N/A</td>
													<%
														} else {
													%>
													<td><%=vlelist.get(i).getAcount_number()%></td>
													<%
														}
													%>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							vlelist.get(i).getTotal_commission())%><b>
															PKR</b></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							vlelist.get(i).getPrevious_month_commission())%><b>
															PKR</b></td>

													<%
														if (vlelist.get(i).getPrevious_month_commission() > 0 /* && (startDate.equalsIgnoreCase(dateFormat.format(cal.getTime()))) */) {
													%>
													<td><a type="Button" id="fo_submit1" class="label"
														style="background-color: #eea434; color: white; font-weight: bold; width: 100%; height: 100%"
														href="Payout?type=101&fo_id=<%=vlelist.get(i).getPayer_id()%>"
														class="spice-link">pay now</a></td>
													<%
														} else {
													%>
													<td><a
														href="Payout?type=101&fo_id=<%=vlelist.get(i).getPayer_id()%>"
														style="display: none">N/A vlelist</a></td>
													<%
														}
													%>

													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							vlelist.get(i).getCurrent_month_commission())%><b>
															PKR</b></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							vlelist.get(i).getDefault_commission())%><b>
															PKR</b></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							vlelist.get(i).getHold_amount())%><b>
															PKR</b></td>
													<%-- <% if((vlelist.get(i).getDefault_commission()<1) && (vlelist.get(i).getHold_amount()>1) ){ %>
                                        <td><a id="fo_submit" class="label" style="background-color:#eea434; color:white;font-weight: bold; width:100%;height: 100%" href="Unhold_Pay?type=101&fo_id=<%=vlelist.get(i).getPayer_id()%>" style="display: true">pay now</a></td>
                                        <% } else {%>
                                        <td><a id="fo_submit" href="Unhold_Pay?type=101&fo_id=<%=vlelist.get(i).getPayer_id()%>" style="display: none">N/A vlelist</a></td>
                                         <%} %> --%>
												</tr>

												<%
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- end panel -->
						</div>
						<div class="tab-pane fade" id="default-tab-2">
							<!-- begin panel -->
							<div class="panel panel-inverse">


								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px">
										<table id="data-table2"
											class="table table-hover table-bordered">
											<thead>
												<tr>

													<th>Name</th>
													<th>Account number</th>
													<th>Total commission</th>
													<th>Previous month commission</th>
													<th>Pay Monthly Now</th>
													<th>Current month commission</th>
													<th>Default amount</th>
													<th>Unpaid hold amount</th>

													<!-- <th>Pay Now </th> -->


												</tr>
											</thead>
											<tbody>
												<%
													for (int i = 0; i < folist.size(); i++) {
															DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
															Calendar cal = Calendar.getInstance();
															// 															System.out.println(dateFormat.format(cal.getTime()));

															Calendar date = Calendar.getInstance();
															date.set(Calendar.DAY_OF_MONTH, 1);
															DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
															String startDate = df.format(date.getTime());
															// 															System.out.println(startDate);
												%>
												<tr>
													<td><%=folist.get(i).getCustomer_name()%></td>
													<td><%=folist.get(i).getAcount_number()%></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							folist.get(i).getTotal_commission())%><b>
															PKR</b></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							folist.get(i).getPrevious_month_commission())%><b>
															PKR</b></td>

													<%
														if (folist.get(i).getPrevious_month_commission() > 0 /* && (startDate.equalsIgnoreCase(dateFormat.format(cal.getTime()))) */) {
													%>
													<td><a id="fo_submit1" class="label"
														style="background-color: #eea434; color: white; font-weight: bold; width: 100%; height: 100%"
														href="Payout?type=100&fo_id=<%=folist.get(i).getPayer_id()%>"
														class="spice-link">pay now</a></td>
													<%
														} else {
													%>
													<td><a
														href="Payout?type=100&fo_id=<%=folist.get(i).getPayer_id()%>"
														style="display: none">N/A FO</a></td>
													<%
														}
													%>

													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							folist.get(i).getCurrent_month_commission())%><b>
															PKR</b></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							folist.get(i).getDefault_commission())%><b>
															PKR</b></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							folist.get(i).getHold_amount())%><b>
															PKR</b></td>
													<%-- <% if((folist.get(i).getDefault_commission()<1) && (folist.get(i).getHold_amount()>1)){ %>
                                        <td><a id="fo_submit" class="label" style="background-color:#eea434; color:white;font-weight: bold; width:100%;height: 100%" href="Unhold_Pay?type=100&fo_id=<%=folist.get(i).getPayer_id()%>" style="display: true">pay now</a></td>
                                        <% } else {%>
                                        <td><a id="fo_submit"  href="Unhold_Pay?type=100&fo_id=<%=folist.get(i).getPayer_id()%>" style="display: none" >N/A FO</a></td>
                                         <%} %> --%>
												</tr>

												<%
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- end panel -->
						</div>

					</div>


				</div>

				<!-- end col-12 -->
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
	<script src="assets/js/table-manage-default.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		$(document).ready(function() {
			var someDate = new Date();
			var dd = someDate.getDate();

			if (dd > 2) {

				$(function() {

					$('.spice-link').css('display', 'none');
					// disable all links in a document

				});

			}

			App.init();
			TableManageDefault.init();

		});
	</script>

</body>


</html>
