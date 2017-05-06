<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.HashMap"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.mapBAL"%>
<%@page import="bean.MapBean"%>
<%@page import="bal.DistrictOfficerBAL"%>
<%@page import="bean.DistrictOfficerBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.Format"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="bal.Payment_loanNewBAL"%>
<%@page import="bean.Payment_LoanBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.FieldOfficerBAL"%>
<%@page import="security.EncryptDecrypt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>


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
<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->

<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->

</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			ArrayList<HashMap<String, String>> list = null;

			list = FieldOfficerBAL.getAllFieldOfficers();
	%>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

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
			<h1 class="page-header">Field Officer</h1>
			<!-- end page-header -->

			<!-- begin row -->










			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn"></div>
					<h4 class="panel-title">Field Officers</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive" style="font-size: 13px">
						<table id="data-table" class="table table-hover table-bordered">
							<thead>
								<tr>
									<th>District</th>
									<th>Officer Name</th>
									<th>Total Nizam Dost</th>
									<th>Active Nizam Dost</th>
									<th>Last Sale</th>
									<th>Total Apps</th>
									<th>Total Installs</th>
									<th>Current Month Apps</th>
									<th>Current Month Installs</th>
									<th>Current Week Apps</th>
									<th>Current Week Installs</th>
									<th>Recovery Rate</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < list.size(); i++) {
								%>
								<tr>
									<td><%=list.get(i).get("district_name")%>, <%=list.get(i).get("user_name")%></td>
									<td><a
										href="fieldOfficerProfile.jsp?fo_id=<%=list.get(i).get("fo_id")%>"><%=list.get(i).get("fo_name")%></a></td>
									<td><%=list.get(i).get("vles")%></td>
									<td><%=list.get(i).get("activ_vles")%> %</td>
									<td><%=list.get(i).get("last_sale")%></td>
									<td><%=list.get(i).get("total_apps")%></td>
									<td><%=list.get(i).get("installed_apps")%></td>
									<td><%=list.get(i).get("currentMonthApps")%></td>
									<td><%=list.get(i).get("currentMonthInstalls")%></td>
									<td><%=list.get(i).get("currentweekApps")%></td>
									<td><%=list.get(i).get("currentWeekInstalls")%></td>
									<td><%=list.get(i).get("recovery_rate")%> %</td>
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
			<%
				}
			%>
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

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/js/table-manage-default.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->



	<!-- <script>
	 
	 // created by Junaid Ali
	 
		$(function(){
			
			$('#do-table').DataTable({
				
				"processing" : true,
				"serverSide" : true,
				
				"order" : [[ 0, "asc" ]],
				
				"ajax" : {
					"url" : "DistrictOfficerController",
					"type" : "post",
					"data" : function(d){
						d.action = "getFoTable"
					},
					
					
				"dataSrc" : function(json){
					console.log("=================");
					console.log(json);
					console.log(json.data);
					
					$.each(json.data, function(e){
// 						json.data[e].userId
						json.data[e].use =  '<a href="updateDo.jsp?doId=' +json.data[e].userId+ '" class="label" style="background-color:#3449ee; color:white;font-weight: bold; width:100%">Update</a>';
					})
// 					console.log(json.data.userId)
					return json.data;
				},
				
					
					/* success : function(data){
						console.log("===========");
						console.log(data);
					} */
				},
				"columns" : [
					            {"data" : "userName"},
					            {"data" : "districtName"},
					            {"data" : "districtofficer"}, 
					            {"data" : "totalvle"},
					            {"data" : "use" }
				            ],
				"rowCallback" : function(row, data){
//					if($.inArray(data.DT_RowId, selected) !== -1){
					$(row).data('link', 'DistrictOfficer?do_id='+data.userId)
					$(row).addClass('link')
//					}
				},           
					
			});
			
			$("#do-table tbody").on('click', 'tr.link>td',function(event) {
				link = $(this).parent().data('link');
				if (link != undefined) {
					window.location=link;			
				}
			});
			
		});
	</script>  -->

	<script>
		$(document).ready(function() {
			App.init();
			TableManageDefault.init();
		});
	</script>

</body>


</html>
