
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="bean.CommissionBreakUpBean" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

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
<link href="assets/plugins/ionicons/css/ionicons.min.css" rel="stylesheet" />
	<link href="assets/plugins/simple-line-icons/simple-line-icons.css" rel="stylesheet" />
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
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar">
		<!-- begin #header -->
			<%@include file="/superAdminHeader.jsp" %>
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
							<%-- <%=userbean.getUserName()%> --%>
							<small>Superadmin</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					
					<li class="has-sub"><a href="SuperAdminDashboard;"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>
					<li class="has-sub"><a href="Request"> 
<%-- 					<span class="badge pull-right"><%=countRequests%></span>  --%>
							<i
							class="fa fa-inbox"></i> <span>Loan Request</span>
					</a></li>
					<li class="has-sub"><a href="Customer"> <i
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
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Commission Breakup  </h1>
			<!-- end page-header -->

			<!-- begin row -->

			<div class="row" style="padding-top: 2%">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Commission Breakup Table</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px;">
							
 					<table id="data-table"
									class="table table-hover table-bordered">
									<thead>
										<tr>
											<th>Customer name</th>
											<th>GSM number</th>
											<th>Amount paid</th>
											<th>Remaing amount</th>
											<th>Paid date</th>
											<th>Due date</th>
										</tr>
									</thead>
									<tbody>
											<% ArrayList<CommissionBreakUpBean> customerList = (ArrayList<CommissionBreakUpBean>)request.getAttribute("customerList");
												if(customerList.size() > 0){
													for(int i = 0; i < customerList.size(); i++){
														CommissionBreakUpBean bean = customerList.get(i);
													%>
										<tr>
											<td><%= bean.getCustomerName() %></td>
											<td><%= bean.getApplianceGsmNumber() %></td>
											<td><%= bean.getAmountPaid() %></td>
											<td><%= bean.getRemainingAmount() %></td>
											<td><%= bean.getPaidDate() %></td>
											<td><%= bean.getDueDate() %></td>
										</tr>
													<%
													}
												}
											%>
												
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-10 -->

				<!-- profile modal -->
				<div class="modal fade" id="profile-modal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Modal Dialog</h4>
							</div>
							<div class="modal-body"></div>
							<div class="modal-footer">
								<a href="javascript:;" class="btn btn-sm btn-white"
									data-dismiss="modal">Close</a> <a href="javascript:;"
									class="btn btn-sm btn-success">Action</a>
							</div>
						</div>
					</div>
				</div>


				<!-- accept request model -->
				<div class="modal fade" id="modal-alert">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header success">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Accept Request</h4>
							</div>
							<div class="modal-body">
								<div class="alert m-b-0">
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
									class="btn btn-sm btn-success">Accept</a>
							</div>
						</div>
					</div>
				</div>
				<!-- accept request model end -->



				<!-- delete request model -->
				<div class="modal fade" id="modal-alert-delete">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header danger">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Suggest / Delete Request</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<h4 class="m-b-0 p-b-15 m-t-0 underline">Appliance</h4>
									<div class="p-15">
										<label class="checkbox-inline"> <input type="checkbox"
											name="appliance" value="6"
											onchange="(getCheki('appliance'));"> 6 Watt
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="appliance" value="30"
											onchange="(getCheki('appliance'));"> 30 Watt
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="appliance" value="60"
											onchange="(getCheki('appliance'));"> 60 Watt
										</label>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a href="javascript:;" class="btn btn-sm btn-white"
									data-dismiss="modal">Close</a> <a id="updateURLsuggest"
									class="btn btn-sm btn-success">Suggest</a> <a
									id="updateURLreject" class="btn btn-sm btn-danger">Reject</a>
							</div>
						</div>
					</div>
				</div>
				<!-- delete request model end -->


				<!-- info Message model -->
				<div class="modal fade" id="modal-alert-info">
					<div class="modal-dialog">
						<div class="modal-content message">
							<div class="modal-header primary">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Message</h4>
							</div>
							<div class="modal-body">
								<textarea class="form-control" placeholder="Textarea"
									name="customerText" onblur="setTextAreaValue();"
									id="customerText" rows="5"></textarea>
							</div>
							<div class="modal-footer">
								<a href="javascript:;" class="btn btn-sm btn-white"
									data-dismiss="modal">Close</a> <a id="sendMessage"
									class="btn btn-sm btn-info" data-dismiss="modal">Send</a>
							</div>
						</div>
					</div>
				</div>
				<!-- info request model endd -->
			</div>
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
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	
	<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
	
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/plugins/chart-js/chart.js"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script>
		$(document).ready(function() {
			App.init();
			TableManageColVis.init();
			
			
		});
	</script>
	
	
</body>


</html>
