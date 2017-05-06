<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.mapBAL"%>
<%@page import="bean.MapBean"%>
<%@page import="bean.Salesman"%>
<%@page import="bean.ApplianceStatusBean"%>
<%@page import="bal.ApplianceStatusBAL"%>
<%@page import="bean.InfowindowBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.AlertsBAL"%>
<%@page import="bean.AlertsBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.CustomerBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bean.SoldToBean"%>
<%@page import="bal.SoldtoBAL"%>
<%@page import="bean.ApplianceBean"%>
<%@page import="bal.ApplianceBAL"%>
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
<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== BEGIN BASE JS ================== -->
<!--   <script src="http://www.amcharts.com/lib/3/amcharts.js" type="text/javascript"></script>  -->
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<!-- 	<script src="amcharts/gauge.js" type="text/javascript"></script> -->
<!-- 	<script src="http://www.amcharts.com/lib/3/themes/chalk.js" type="text/javascript"></script> -->
<!-- ================== END BASE JS ================== -->
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=true"></script>

<style type="text/css">
#appTable, thead, tr, th {
	border-top: #252830;
}

#appTable tbody tr th {
	/*   background: #eee; */
	/*   border-top: #252830; */
	border-style: none;
}

#appTable tbody tr td {
	border-style: none;
	/* 	border-top: #252830; */
}

.my-fancy-container {
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 6px;
	margin: 5px;
	/*     padding: 5px; */
	width: 40%;
}

.my-text {
	font-family: "Courier-new";
	color: white;
	padding: inherit;
	display: inline-block;
	text-align: center;
	width: 99%;
}

.mask {
	border: 0;
	height: 1px;
	background: white;
	background-image: linear-gradient(to right, #ccc, #ffffff, #ccc);
}

.divider-inside-top-bottom {
	position: relative;
	overflow: hidden;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}

.divider-inside-top-bottom:before {
	content: "";
	position: absolute;
	z-index: 1;
	width: 96%;
	top: -10px;
	height: 10px;
	left: 2%;
	border-radius: 100px/5px;
	box-shadow: 0 0 18px rgba(0, 0, 0, 0.6);
}

.divider-inside-top-bottom:after {
	content: "";
	position: absolute;
	z-index: 1;
	width: 96%;
	bottom: -10px;
	height: 10px;
	left: 2%;
	border-radius: 100px/5px;
	box-shadow: 0 0 18px rgba(0, 0, 0, 0.6);
}

#setImage {
	width: 100%;
	color: white;
	margin-top: 10px;
}

#headingSet {
	color: white;
	text-align: center;
}
</style>


<%
	String current = "";
	UserBean userbean = (UserBean) session.getAttribute("email");

	ApplianceBean bean = (ApplianceBean) request.getAttribute("bean");
	System.out.println("skjdfkjsdb " + bean);

	if (userbean == null) {
		response.sendRedirect("SolarHomeSystemLogin");
	} else {

		ApplianceBAL.updateApplianceAlerts(bean.getApplianceId());
		int lastId = ApplianceStatusBAL.getLastStatusId(bean.getApplianceId());
		ApplianceStatusBean statusList = null;
		statusList = ApplianceStatusBAL.getStatusInfo(lastId, bean.getApplianceId());
		ArrayList<HashMap<String, String>> list = null;
		int countRequests = EligibilityBAL.getUnseenRequests();
		double latitude = Double
				.parseDouble(session.getAttribute("latitud" + bean.getApplianceId()).toString());
		double Longitude = Double.parseDouble(session.getAttribute("longi" + bean.getApplianceId()).toString());
		double lat;
		double longi;
		int type = 0;
		if (statusList != null) {
			lat = statusList.getLatitude();
			longi = statusList.getLongitude();
			type = statusList.getType();
		} else {
			lat = 30.0;
			longi = 69.0;
		}

		ArrayList<HashMap<String, String>> map = ApplianceBAL.getApplianceStatusbyId(bean.getApplianceId());
		System.out.println("cccccccccccccccccccccccccccccccccccccccc");
%>
<script type="text/javascript">
	var a =
<%=lat%>
	;

	;
	var b =
<%=longi%>
	;
	var c =
<%=latitude%>
	;
	var d =
<%=Longitude%>
	;
	var status =
<%=type%>
	;
	var icons;
	if (status == 1) {
		icons = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
	}

	if (status == 0) {
		icons = "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
	}
	var karachi = new google.maps.LatLng(a, b);
	var karach = new google.maps.LatLng(a, b);
	var circle_center = new google.maps.LatLng(c, d);

	function initialize() {
		var mapProp = {
			center : karachi,
			zoom : 18,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);
		var marker = new google.maps.Marker({
			position : karach,
			icon : icons

		});

		marker.setMap(map);
		// 		var myCity = new google.maps.Circle({
		// 			center : circle_center,
		// 			radius : 10,
		// 			strokeColor : "#0000FF",
		// 			strokeOpacity : 0.8,
		// 			strokeWeight : 2,
		// 			fillColor : "#0000FF",
		// 			fillOpacity : 0.4
		// 		});

		//	myCity.setMap(map);

		// 		google.maps.Circle.prototype.contains = function(latLng) {
		// 			return this.getBounds().contains(latLng)
		// 					&& google.maps.geometry.spherical.computeDistanceBetween(
		// 							this.getCenter(), latLng) <= this.getRadius();
		//		};
		// 		function geoFencing() {
		// 			return (myCity.getBounds().contains(karach));
		// 		}
		// 		if (geoFencing() == true) {

		// 		} else {
		// 			//alert('please inactivate the device');
		// 		}
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>
<body>




	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<input type="hidden" id="appID" value="<%=bean.getApplianceId()%>">
	<input type="hidden" id="gsmNumber"
		value="<%=bean.getApplianceGsmNo()%>">
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
							<a href="javascript:;"><img src="assets/img/user-13.jpg"
								alt="" /></a>
						</div>
						<div class="info">
							<%=userbean.getUserName()%>
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

					<li class="has-sub active"><a href="doDeployment.jsp"><i
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
			<h1 class="page-header">
				Appliance :
				<%=bean.getApplianceName()%></h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-6 -->
				<div class="col-md-8 ui-sortable" style="margin-top: 2%">
					<!-- begin panel -->
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">
								Appliance Detail/<%=bean.getApplianceName()%></h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
										<th>Appliance IMEI Number</th>
										<td>
											<%
												String imei = "";
													if (bean.getImiNumber() == null || bean.getImiNumber() == "") {
														imei = "N/A";
													} else {
														imei = bean.getImiNumber();

													}
											%> <input type="text" id="imeinumber" data-table="appliance"
											data-type="imei" data-column="imei_number" value="<%=imei%>"
											maxlength="15" disabled
											style="background-color: white; border: none" /> <span
											style="color: red" id="imeiresult"></span>
									</tr>
									<tr>
										<th>GSM Number</th>
										<td>
											<%
												String gsm = "";

													if (bean.getApplianceGsmNo() == null || bean.getApplianceGsmNo() == "") {
											%> N/A <%
												} else {
														gsm = bean.getApplianceGsmNo().replace("92", "");
														StringBuilder buldgsm = new StringBuilder(gsm);
											%> <span>(+92) </span> <%=buldgsm.insert(3, "-")%>
										</td>
										<%
											}
										%>
									</tr>
									<tr>
									<th>Consumer Number</th>
									<% if(bean.getConsumerNumber() == null ) { %>
									<td> N/A </td>
									<% } else {%>
									<td> <%= bean.getConsumerNumber() %> </td>
									<% }%>
									</tr>
									<tr>
										<th>Charging State</th>
										<td>
											<%
												boolean state = bean.getState();

													if (state == true) {
											%> <span class="label" style="background-color: #2ecc71">Active</span>
											<%
												} else {
											%> <span class="label label-danger">Inactive</span> <%
 	}
 %>
										</td>
									</tr>
									<tr>
										<th>Price</th>
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(Math.round(bean.getPrice()))%><b>
												PKR</b></td>
									</tr>
									<tr>
										<th>Customer Name</th>
										<td><a
											href="ViewServlet?click=doview&cnic=<%=bean.getCustomerCnic()%>&applianceId=<%=bean.getApplianceId()%>"><%=bean.getCustomerName()%>
										</a></td>
									</tr>
									<tr>
										<th>Hand over At</th>
										<td>
											<%
												if (bean.getHandoverDate() == null) {
											%> N/A <%
												} else {
											%> <%=bean.getHandoverDate()%></td>
										<%
											}
										%>
									</tr>

									<tr>
										<th>Field Officer</th>
										<td><a
											href="doFieldOfficerProfile.jsp?fo_id=<%=bean.getFoid()%>"><%=bean.getFoName()%>
										</a></td>
									</tr>
									<tr>
										<th>Sales Person</th>
										<td><a
											href="Test?click=vleprofile&salesman_id=<%=bean.getSalesmanId()%>&salesmanGsm=<%=bean.getSalesmanPhone()%>"><%=bean.getSalesmanName()%>
										</a></td>
									</tr>
								</table>
							</div>

						</div>
					</div>
					<!-- end panel -->
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-heading">

							<h4 class="panel-title">MAP</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<div class="map-canvas-container " id="map" style="">
									<div id="googleMap" style="width: 100%; height: 384px;"></div>
								</div>
							</div>

						</div>
					</div>

				</div>
				<div class="col-md-4">

					<%
						String lastupdated = "0";
							String batteryvoltage = "0";
							String batteryampere = "0";
							String solarvoltage = "0";
							String solarampere = "0";
							String loadvoltage = "0";
							String loadcurrent = "0";
							String temprature = "0";
							String signalstrength = "0";
							String lidstatus = "Close";
							if (map.size() > 0) {
								lastupdated = map.get(0).get("date");
								batteryvoltage = map.get(0).get("batteryVolt");
								batteryampere = map.get(0).get("batteryAmp");
								solarvoltage = map.get(0).get("solarVolt");
								solarampere = map.get(0).get("solarAmp");
								loadvoltage = map.get(0).get("loadVolt");
								loadcurrent = map.get(0).get("loadAmp");
								temprature = map.get(0).get("temprature");
								signalstrength = map.get(0).get("gsmSignal");
								lidstatus = map.get(0).get("lid");
								if (lidstatus.equalsIgnoreCase("0")) {
									lidstatus = "Close";
								} else {
									lidstatus = "Open";
								}
							}
					%>

					<div class="example divider-inside-top-bottom" style="margin: 5px;">

						<h4 style="text-align: center; margin-top: 4%;">Appliance
							Summary</h4>

						<div style="text-align: center;">

							<div class="my-fancy-container" style="background-color: #00aba9">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-clock-o fa-stack-2x"></i></span>

								<h4 id="headingSet">Last Update</h4>

								<div class="mask"></div>
								<span class="my-text" style="font-size: 12px;"><%=lastupdated%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #ee4035">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-bolt fa-stack-2x"></i></span>

								<h4 id="headingSet">Battery</h4>

								<div class="mask"></div>
								<span class="my-text"><%=batteryvoltage + " V"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #a200ff">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-bolt fa-stack-2x"></i></span>
								<h4 id="headingSet">Battery</h4>
								<div class="mask"></div>
								<span class="my-text"><%=batteryampere + " A"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #1ba1e2">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-sun-o fa-stack-2x"></i></span>
								<h4 id="headingSet">Solar</h4>
								<div class="mask"></div>
								<span class="my-text"><%=solarvoltage + " V"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #f09609">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-sun-o fa-stack-2x"></i></span>
								<h4 id="headingSet">Solar</h4>
								<div class="mask"></div>
								<span class="my-text"><%=solarampere + " A"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #00aba9">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-tachometer fa-stack-2x"></i></span>
								<h4 id="headingSet">Load</h4>
								<div class="mask"></div>
								<span class="my-text"><%=loadvoltage + " V"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #ee4035">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-tachometer fa-stack-2x"></i></span>
								<h4 id="headingSet">Load</h4>
								<div class="mask"></div>
								<span class="my-text"><%=loadcurrent + " A"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #a200ff">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-fire fa-stack-2x"></i></span>
								<h4 id="headingSet">Temperature</h4>
								<div class="mask"></div>
								<span class="my-text"><%=temprature + " °C"%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #1ba1e2">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-signal fa-stack-2x"></i></span>
								<h4 id="headingSet">Signal Strength</h4>
								<div class="mask"></div>
								<span class="my-text"><%=signalstrength%></span>
							</div>

							<div class="my-fancy-container" style="background-color: #f09609">
								<span id="setImage" class="fa-stack fa-lg"> <i
									class="fa fa-archive fa-stack-2x"></i></span>
								<h4 id="headingSet">Lid Status</h4>
								<div class="mask"></div>
								<span class="my-text"><%=lidstatus%></span>
							</div>

						</div>

					</div>



				</div>



			</div>
			<!-- end row -->

			<div class="row" style="padding-top: 2%">
				<div class="col-md-12">
					<ul id="ioniconsTab" class="nav nav-tabs">
						<li class="active"><a href="#default" data-toggle="tab">
								<div class="text-center"></div> <span class="hidden-xs m-l-3">Power
									Usage Graph <span class="badge badge-inverse m-l-3"></span>
							</span>
						</a></li>
						<li><a href="#ios" data-toggle="tab">
								<div class="text-center"></div> <span class="hidden-xs m-l-3">
									Load and Solar Current <span class="badge badge-inverse m-l-3"></span>
							</span>
						</a></li>
						<span><input
							style="height: 30px; margin: 5px; float: right;" type="date"
							name="dateSet" id="dateSet"
							onchange="loadSoalrAmpere(), staticsDateWise()"></span>
					</ul>



					<div id="ioniconsTabContenta" class="tab-content">

						<div class="tab-pane fade in active" id="default">
							<div>
								<input type="hidden" id="current" value="<%=current%>">
								<input type="hidden" id="SolarCurrent" value="<%=current%>">
								<!-- 								<input type="date" name="dates" id="dates" onchange="staticsDateWise();"> -->
							</div>

							<div id="chartdiv2"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>
						<div class="tab-pane fade" id="ios">
							<!-- 							<input type="date" name="dateSolar" id="dateSolar" onchange="loadSoalrAmpere();"> -->
							<div id="chartdiv3"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>

					</div>
				</div>
			</div>


			<div class="row">

				<div class="col-md-12">

					<!-- begin panel -->
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">
								Appliance Alert History /
								<%=bean.getApplianceName()%>
							</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="alarmsTable" class="table table-bordered">
									<thead>
										<tr>
											<th>DateTime</th>
											<th>Alerts</th>
											<th>Status</th>
											<th>Resolved Time</th>

										</tr>
									</thead>
								</table>
							</div>
							<%
								} //session close
							%>
						</div>
					</div>
					<!-- end panel -->

				</div>
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
			<script src="assets/plugins/switchery/switchery.min.js"></script>
			<!-- 	<script src="assets/plugins/powerange/powerange.min.js"></script> -->
			<script src="assets/js/form-slider-switcher.demo.min.js"></script>
			<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
			<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
			<script src="assets/js/table-manage-colvis.demo.min.js"></script>
			<script src="assets/js/apps.min.js"></script>
			<!-- ================== END PAGE LEVEL JS ================== -->

			<script src="amcharts/gauge.js" type="text/javascript"></script>

			<script src="amcharts/serial.js" type="text/javascript"></script>
			<script src="amcharts/serial.js" type="text/javascript"></script>
			<script src="js/applianceview.js"></script>
			<!-- ================== BEGIN PAGE LEVEL JS ================== -->
			<script src="assets/js/apps.min.js"></script>
			<!-- ================== END PAGE LEVEL JS ================== -->


			<script>
				$(document).ready(function() {
					App.init();
					loadSoalrAmpere();
					staticsDateWise();
					doAlarmTable();
				});
			</script>
</body>
</html>
