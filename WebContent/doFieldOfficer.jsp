<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bal.UserBAL"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bal.mapBAL"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="bean.MapBean"%>
<%@page import="bean.MapBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.DistrictOfficerBean"%>
<%@page import="bean.CustomerBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bean.InventoryBean"%>
<%@page import="java.util.*"%>


<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- Mirrored from seantheme.com/color-admin-v1.7/admin/html/page_with_light_sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 24 Apr 2015 11:01:38 GMT -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

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

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<script type='text/javascript'
	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type='text/javascript'
	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
<script src="http://maps.googleapis.com/maps/api/js"></script>

<style>
#map {
	width: 100%;
	height: 300px;
}
</style>

<script src="https://maps.googleapis.com/maps/api/js"></script>
<script>
	var markersValue;
	var MarkerLati = [];
	var MarkerLongi = [];
	var infoName = [];
	var infoType = [];
	var infoScheme = [];
	var lati = [];
	var longi = [];
	var locations;
	var name;
	var lat;
	var long;
	var add;
	var scheme;

	function add_marker() {
		try {
			var myOptions = {
				center : new google.maps.LatLng(30.890542, 71.274856),
				zoom : 5,
				mapTypeId : google.maps.MapTypeId.ROADMAP

			};
			var map = new google.maps.Map(document.getElementById("map"),
					myOptions);

			$.ajax({
				url : 'Salesman_locations.jsp',
				dataType : 'json',
				success : function(data) {
					for (var iii = 0; iii < data.length; iii++) {
						MarkerLongi.push(data[iii].Longitude);
						infoName.push(data[iii].Time);
						infoType.push(data[iii].gsm);
						MarkerLati.push(data[iii].Latitude);
						infoScheme.push(data[iii].Name);
					}
					locations = new Array(MarkerLati.length);
					var marker, i;
					for (var i = 0; i < locations.length; i++) {
						locations[i] = new Array(5);
						lat = locations[i][0] = MarkerLati[i];
						long = locations[i][1] = MarkerLongi[i];
						Time = locations[i][2] = infoName[i];
						number = locations[i][3] = infoType[i];
						name = locations[i][4] = infoScheme[i];
						var marker = new google.maps.Marker({
							map : map,
							title : name,
							position : new google.maps.LatLng(lat, long),
						});
						//map.setCenter(marker.getPosition())
						// 					alert(name);
						var content = "<b>Last Updated:" + Time + '<br/> '
								+ "Number: " + number + " <br/>Name: " + name
								+ "</b>";

						var infowindow = new google.maps.InfoWindow();

						google.maps.event.addListener(marker, 'mouseover',
								(function(marker, content, infowindow) {
									return function() {
										infowindow.setContent(content);
										infowindow.open(map, marker);
									};
								})

								(marker, content, infowindow));
						google.maps.event.addListener(marker, 'mouseout',
								(function(marker, content, infowindow) {
									return function() {
										infowindow.setContent(content);
										infowindow.close(map, marker);
									};
								})(marker, content, infowindow));

					}

					jQuery(document).ready(function() {
					});
				}

			});
		} catch (e) {
			console.error(e);
		}
	}
</script>


</head>
<body onload="add_marker()">


	<%
		UserBean bean = (UserBean) session.getAttribute("email");

		if (bean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			int imageId = UserBAL.getImage(bean.getUserId());
	%>

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
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="ShowImage?id=<%=imageId%>"
								alt="" /></a>
						</div>
						<div class="info">
							<%=bean.getUserName()%>
							<small>District Officer</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<!-- 					<li class="nav-header">Navigation</li> -->
					<li class="has-sub"><a href="DistrictOfficerDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> <%-- <span class="badge pull-right"><%=countRequests%></span> --%>
							<i class="icon-note"></i> <span>Loan Request</span> <!-- 						Begin	SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>

					<li class="has-sub"><a href="doDeployment.jsp"><i
							class="fa fa-suitcase"></i><span>Deployment</span></a></li>

					<li class="has-sub"><a href="doLoanBooks.jsp"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>


					<li class="has-sub active"><a href="javascript:;"> <b
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
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">
				Field Officer <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- Map Start -->

				<div class="row">

					<!-- begin col-12 -->
					<div class="col-md-12">
						<!-- begin panel -->
						<div class="panel panel-inverse">
							<div class="panel-heading">
								<div class="panel-heading-btn">
									<!--                                 <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
 -->
								</div>
								<h4 class="panel-title">Field Officer Table</h4>
							</div>

							<div class="panel-body">
								<div class="table-responsive" style="font-size: 13px;">
									<table id="do-fo-table"
										class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>FO Name</th>
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

										</tbody>
									</table>
									<%
										} // session else closed
									%>
								</div>
							</div>
						</div>
						<!-- end panel -->
					</div>
					<!-- end col-10 -->



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
			$("#do-fo-table")
					.DataTable(
							{

								"processing" : true,
								"serverSide" : true,
								// 		"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "DOFieldOfficerController",
									"method" : "post",
									"data" : function(d) {
										d.action = "getDoFieldOfficerTable"
									},
									"dataSrc" : function(json) {

										$(".page-header span").text(
												json.FOCount);
										for (var i = 0; i < json.data.length; i++) {
											json.data[i].recovery = json.data[i].recovery_rate
													+ " %";
											json.data[i].activeNDs = json.data[i].activ_vles
													+ " %";
										}

										return json.data;
									},

								},
								"columns" : [ {
									"data" : "foName"
								}, {
									"data" : "vles"
								}, {
									"data" : "activeNDs"
								}, {
									"data" : "last_sale",
									"orderable" : false
								}, {
									"data" : "total_apps"
								}, {
									"data" : "installed_apps"
								}, {
									"data" : "currentMonthApps"
								}, {
									"data" : "currentMonthInstalls"
								}, {
									"data" : "currentweekApps"
								}, {
									"data" : "currentWeekInstalls"
								}, {
									"data" : "recovery"
								} ],

								"rowCallback" : function(row, data) {
									//		if($.inArray(data.DT_RowId, selected) !== -1){
									$(row)
											.data(
													'link',
													'doSalesmen.jsp?fo_id='
															+ data.foid)
									$(row).addClass('link')
									//		}
								}
							});
			$("#do-fo-table tbody").on('click', 'tr.link>td', function(event) {
				link = $(this).parent().data('link');
				if (link != undefined) {
					window.location = link;
				}
			});
		</script>

		<script>
			$(document).ready(function() {
				App.init();
				// 			TableManageColVis.init();
			});
		</script>
</body>


</html>
