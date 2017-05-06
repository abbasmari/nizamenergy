<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bal.WottageGraphBAL"%>
<%@page import="java.util.HashMap"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bean.WottageGraphBean"%>
<%@page import="bal.AlertGraphBAL"%>
<%@page import="bean.AlertGraphBean"%>
<%@page import="bal.CustomerGraphBAL"%>
<%@page import="bean.CustomerGraphBean"%>
<%@page import="bal.LoanPaymentGraphBAL"%>
<%@page import="bean.LoanPaymentGraphBean"%>
<%@page import="bal.MobileMoneyGraphBAL"%>
<%@page import="bean.MobileMoneyGraphBean"%>
<%@page import="bean.soldApplianceGraphBean"%>
<%@page import="bal.soldApplianceGraphBAL"%>
<%@page import="bean.ActiveInActiveGraph"%>
<%@page import="bal.ActiveInActiveGraphBAL"%>
<%@page import="bal.ActiveInActiveGraphDoBAL"%>
<%@page import="bean.ActiveInActiveGraphDoBean"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.mapBAL"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bean.MapBean"%>
<%@page import="bean.DistrictBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bean.DistrictOfficerBean"%>
<%@page import="bean.EligibilityBean"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.LoanPaymentGraphDoBean"%>
<%@page import="bal.LoanPaymentGraphDoBAL"%>
<%@page import="bean.MobileMoneyGraphDoBean"%>
<%@page import="bal.MobileMoneyGraphDoBAL"%>
<%@page import="bean.soldApplianceGraphDoBean"%>
<%@page import="bal.soldApplianceGraphDoBAL"%>
<%@page import="bean.CustomerGraphDoBean"%>
<%@page import="bean.AlertGraphDoBean"%>
<%@page import="bal.AlertGraphDoBAL"%>
<%@page import="bean.DistrictSalesmanBean"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bean.TargetBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="bal.TargetsBAL"%>
<%@page import="bal.CustomerGraphDoBAL"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>
<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet" />
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

<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->
<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script> -->
<!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->


<script type='text/javascript' src="http://maps.google.com/maps/api/js"></script>



<!-- <script type='text/javascript' -->
<!-- 	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script> -->


<style type='text/css'>
#googleMap {
	height: 536px;
}
</style>
<%
	UserBean bean = (UserBean) session.getAttribute("email");
	int imageId = UserBAL.getImage(bean.getUserId());
	if (bean.equals(null)) {
		response.sendRedirect("SolarHomeSystemLogin");
	} else {
		DistrictOfficerBean dobean = (DistrictOfficerBean) session
				.getAttribute("dobean");
%>

<script type='text/javascript'>
	var markersValue;
	var MarkerLati = [];
	var MarkerLongi = [];
	var infoName = [];
	var infoType = [];
	var infoScheme = [];
	var lati = [];
	var longi = [];
	var infoStatus = [];
	var locations;
	var name;
	var lat;
	var long;
	var add;
	var scheme;
	var status;
<%double lat = dobean.getLatitude();
				double longt = dobean.getLongitutde();%>
	var a =
<%=lat%>
	;
	var b =
<%=longt%>
	;

	function add_marker() {

		try {
			var myOptions = {
				center : new google.maps.LatLng(a, b),
				zoom : 8,
				mapTypeId : google.maps.MapTypeId.ROADMAP

			};

			var map = new google.maps.Map(document.getElementById("googleMap"),
					myOptions);

			$
					.ajax({
						url : 'MapController',
						method : 'post',
						data : {
							action : "getDomarkersdata"
						},
						dataType : 'json',
						success : function(data) {

							for ( var iii = 0; iii < data.length; iii++) {
								MarkerLongi.push(data[iii].Longitude);
								infoName.push(data[iii].Name);
								infoType.push(data[iii].Type);
								MarkerLati.push(data[iii].Latitude);
								infoScheme.push(data[iii].Scheme);
								infoStatus.push(data[iii].status)
							}
							locations = new Array(MarkerLati.length);
							var marker, i, icons;

							for ( var i = 0; i < locations.length; i++) {
								locations[i] = new Array(5);
								lat = locations[i][0] = MarkerLati[i];
								long = locations[i][1] = MarkerLongi[i];
								name = locations[i][2] = infoName[i];
								add = locations[i][3] = infoType[i];
								scheme = locations[i][4] = infoScheme[i];
								status = locations[i][5] = infoStatus[i];
								if (status == 1) {
									icons = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
								}
								if (status == 0) {
									icons = "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
								}
								var marker = new google.maps.Marker(
										{
											map : map,

											icon : icons,
											position : new google.maps.LatLng(
													lat, long),
										});
								//map.setCenter(marker.getPosition())

								var content = "<b>Name: " + name + '<br/> '
										+ "Appliance  Type: " + add;

								var infowindow = new google.maps.InfoWindow();

								google.maps.event.addListener(marker,
										'mouseover', (function(marker, content,
												infowindow) {
											return function() {
												infowindow.setContent(content);
												infowindow.open(map, marker);
											};
										})

										(marker, content, infowindow));
								google.maps.event.addListener(marker,
										'mouseout', (function(marker, content,
												infowindow) {
											return function() {
												infowindow.setContent(content);
												infowindow.close(map, marker);
											};
										})(marker, content, infowindow));

							}

						}

					});
		} catch (e) {
			console.error(e);
		}
	}
</script>
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
		<%@include file="/doHeader.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div>
				<!-- begin sidebar user -->

				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="" alt="" /></a>
						</div>
						<div class="info">
							<%=bean.getUserName()%>
							<small>District Officer </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<!-- 					<li class="nav-header">Navigation</li> -->
					<li class="has-sub active"><a href="DistrictOfficerDashboard">
							<i class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> <%-- <span class="badge pull-right"><%=countRequests%></span> --%>
							<i class="icon-note"></i> <span>Loan Request</span> <!-- 						Begin	SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>

					<li class="has-sub "><a href="doDeployment.jsp"><i
							class="fa fa-suitcase"></i><span>Deployment</span></a></li>

					<li class="has-sub"><a href="doLoanBooks.jsp"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="doFieldOfficer.jsp">Field Officer</a></li>
							<li><a href="doSalesman.jsp">Nizam Dost</a></li>
						</ul></li>


					<li class="has-sub"><a href="doAlarmsPage.jsp"> <i
							class="fa fa-star"></i> <span>Live Alerts</span>
					</a></li>
					<li class="has-sub"><a href="DoCashCustomer.jsp"> <i
							class="fa fa-star"></i> <span>Cash Customers</span>
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
			<h1 class="page-header">Dashboard</h1>

			<div class="row">
				<!-- begin col-3 -->
				<div class="col-md-3">
					<div class="widget widget-stats bg-green" id="recovery_widget">
						<div class="stats-icon stats-icon-lg">
							<i class=""></i>
						</div>
						<!-- 						<div class="stats-title">Recovery Rate</div> -->

						<div class="stats-number">0</div>

						<div class="stats-progress progress">
							<div class="progress-bar"></div>

						</div>

						<div class="stats-desc">0</div>


					</div>
				</div>

				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3">
					<div class="widget widget-stats bg-blue" id="overdue_widget">
						<div class="stats-icon stats-icon-lg">
							<i class=""></i>
						</div>
						<!-- 						<div class="stats-title"></div> -->


						<div class="stats-number">0</div>

						<div class="stats-progress progress">

							<div class="progress-bar"></div>

						</div>
						<div class="stats-desc">0</div>

					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3">
					<!-- 				Script dashboard.js value updates in getMobileMOney  -->
					<div class="widget widget-stats bg-purple" id="defaulter_widget">
						<div class="stats-icon stats-icon-lg">
							<i class=""></i>
						</div>
						<!-- 						<div class="stats-title"></div> -->

						<div class="stats-number">0</div>


						<div class="stats-progress progress">

							<div class="progress-bar"></div>

						</div>
						<div class="stats-desc">0</div>
					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3">
					<!-- 				Script dashboard.js value updates in getSoldApplianceValue -->
					<div class="widget widget-stats bg-black" id="installed_widget">
						<div class="stats-icon stats-icon-lg">
							<i class=""></i>
						</div>
						<div class="stats-number">0</div>
						<div class="stats-progress progress">
							<div class="progress-bar"></div>
						</div>
						<div class="stats-desc">0</div>
					</div>
				</div>
				<!-- end col-3 -->
			</div>
			<!-- end row -->


			<!-- begin row -->
			<div class="row">



				<!-- map content start-->


				<!-- begin col-8 -->
				<div class="col-md-8 ">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">District Map</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div id="googleMap">
									<div style="height: 300px; width: 683px"
										style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 683px; height: 300px;"
										class="flot-base"></div>
								</div>
								<div class=""></div>
							</div>
						</div>
					</div>


				</div>
				<!-- end col-8 -->


				<!-- map content end-->


				<!-- begin col-4 -->
				<div class="col-md-4 ">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Pipeline Details</h4>
						</div>
						<div class="panel-body p-t-0" style="padding: inherit;">
							<div class="table-responsive">
								<!-- 							Trending has been removed by Jeevan -->

								<table class="table table-valign-middle m-b-0">
									<thead>
										<tr>
											<th>Status</th>
											<th>Customers</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><label class="label"
												style="background-color: #bdc3c7; color: white; font-weight: bold;">Pending</label></td>

											<td><span id="countPendingCustomers"
												class="text-success"></span></td>
										</tr>

										<tr>
											<td><label class="label "
												style="background-color: #2980b9; color: white; font-weight: bold;">Accepted</label></td>

											<td><span id="countAcceptedCustomers"
												class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #2ecc71; color: white; font-weight: bold;">Verfied</label></td>

											<td><span id="countVarifiedCustomers"
												class="text-primary"></span></td>
										</tr>

										<tr>
											<td><label class="label"
												style="background-color: #9b59b6; color: white; font-weight: bold;">Ready
													To Assign</label></td>

											<td><span id="ReadyToAssign" class="text-primary"></span></td>
										</tr>

										<tr>
											<td><label class="label"
												style="background-color: #1abc9c; color: white; font-weight: bold;">ADP</label></td>

											<td><span id="AwaitingDownPayment" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #1abc9c; color: white; font-weight: bold;">RDP</label></td>

											<td><span id="RDP" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: green; color: white; font-weight: bold;">Installed</label></td>

											<td><span id="Installed" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #d35400; color: white; font-weight: bold;">Returned</label></td>

											<td><span id="returned" class="text-primary"></span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

					</div>
					<!-- end col-4 -->

					<div class="panel panel-inverse" data-sortable-id="morris-chart-4">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Loanbook Detail</h4>
						</div>
						<div class="panel-body p-t-0" style="padding: inherit;">
							<div class="table-responsive">
								<table class="table table-valign-middle m-b-0">
									<thead>
										<tr>
											<th>Loanbook Status</th>
											<th>Customers</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><label class="label"
												style="background-color: #7f8c8d">Owned </label></td>

											<td><span id="Owned" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label " style="background-color: blue">Maintain</label></td>

											<td><span id="Maintain" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label " style="background-color: red">Late</label></td>

											<td><span id="Late" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #34495e">Defaulter</label></td>

											<td><span id="Defaulter" class="text-primary"></span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<!-- end row -->
				<div class="col-md-12">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Sales and Operations</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="sales" class="table table-bordered">
									<thead>
										<tr>
											<th>Total Applications</th>
											<th>Installed</th>
											<th>Current Month Applications</th>
											<th>Current Week Applications</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="totalApps"></td>
											<td id="installed"></td>
											<td id="currentMonthApps"></td>
											<td id="currentWeekApps"></td>
										</tr>
									</tbody>


								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Salesforce</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<ul class="nav nav-pills f-s-15 inline bordered round-corner">
									<li>
										<div class="row">
											<div class="col-xs-5">
												<label for="ex1">From:</label> <input class="form-control"
													type="date" name="from" id="from" onchange="checkDate();">
											</div>

											<div class="col-xs-5 input-control text"
												data-role="datepicker">
												<label for="ex1">To:</label> <input class="form-control too"
													type="date" name="to" id="to" onchange="checkDateTo();">

											</div>

											<div class="col-xs-5 input-control text">
												<p id="date_message" style="display: none; color: red">
													Select date Less than today's date</p>

											</div>

											<input class="btn btn-default" type="button"
												style="background-color: #AFB1A2; margin-top: 26px; border-color: greenyellow;"
												class="btn btn-sm btn-success" id="filterid" value="Filter"
												onclick="getDoPerformanceDetails()">
										</div>
									</li>
								</ul>
								<table id="next_dues" class="table table-bordered">
									<thead>
										<tr>
											<th>Field Officers</th>
											<th>Nizam Dost</th>
											<th>Active Nizam Dost</th>
											<th>Installations</th>
											<th>Total Applications</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="fos"></td>
											<td id="nds"></td>
											<td id="active_nds"></td>
											<td id="installation"></td>
											<td id="total"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>



			<div class="col-md-13">
				<div class="panel panel-inverse" data-sortable-id="index-1">
					<div class="panel-heading">
						<div class="panel-heading-btn"></div>
						<h4 class="panel-title">Top Five NDs</h4>
					</div>

					<div class="panel-body">
						<div class="table-responsive" style="font-size: 13px">
							<ul class="nav nav-pills f-s-15 inline bordered round-corner">
								<li>
									<div class="row">
										<div class="col-xs-5">
											<label for="ex1">From:</label> <input class="form-control"
												type="date" name="from" id="from_1" onchange="checkDate();">
										</div>

										<div class="col-xs-5 input-control text"
											data-role="datepicker">
											<label for="ex1">To:</label> <input class="form-control too"
												type="date" name="to" id="to_1" onchange="checkDateTo();">

										</div>

										<div class="col-xs-5 input-control text">
											<p id="date_message" style="display: none; color: red">
												Select date Less than today's date</p>

										</div>

										<input class="btn btn-default" type="button"
											style="background-color: #AFB1A2; margin-top: 26px; border-color: greenyellow;"
											class="btn btn-sm btn-success" id="top_five" value="Filter"
											onclick="getDoTopFiveNDs()">
									</div>
								</li>
							</ul>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Nizam Dost</th>
										<th>Installations</th>
										<th>Total Applications</th>
									</tr>
								</thead>
								<tbody id="top_five_nds">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>


		<span style="padding-left: 33%"> © 2016 Nizam Energy - All
			Rights Reserved </span>
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

	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/morris/raphael.min.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/js/chart-morris.demo.min.do.dashboard.js"></script>
	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
	<script type="text/javascript" src="assets/async/doDashboard.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->



	<script>
		function checkDate() {
			var EnteredDate = document.getElementById("from").value; //for javascript

			var EnteredDate = $("#from").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);

			var myDate = new Date(endYear, endMonth - 1, endDate);

			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}

			else {
				$("#date_message").css("display", "none");
			}
		}

		function checkDateTo() {
			var EnteredDate = document.getElementById("to").value; //for javascript

			var EnteredDate = $("#to").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);
			var myDate = new Date(endYear, endMonth - 1, endDate);
			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}
			else {
				$("#date_message").css("display", "none");
			}
		}

		$(document).ready(function() {
			App.init();
			add_marker();

		});
	</script>

</body>


</html>
