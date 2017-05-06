<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<%@page import="java.text.NumberFormat"%>


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
<script src="js/validation.js"></script>
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=true"></script>

<script>
	function setMoodValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		/ alert(id); /
		document.getElementById('updateURL').onclick = function() {
			helloWorld(appliance_id);
		};
	}

	function helloWorld(applianceValue) {
		var link = "DeviceReturnController?click=return&applianceId="
				+ applianceValue;
		document.getElementById('updateURL').href = link;
	}

	function setMoodalValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		/ alert(id); /
		document.getElementById('updateInstallURL').onclick = function() {
			hello(appliance_id);
		};
	}

	function hello(applianceValue) {
		var link = "LoanPaymentController?click=install&applianceId="
				+ applianceValue;
		document.getElementById('updateInstallURL').href = link;
	}
	function setMoodalNoSignalValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		document.getElementById('updateNoSignalURL').onclick = function() {
			helloNoSignal(appliance_id);
		};
	}

	function helloNoSignal(applianceValue) {
		var link = "LoanPaymentController?click=varifySignals&applianceId="
				+ applianceValue;
		document.getElementById('updateNoSignalURL').href = link;
	}
</script>



<style type="text/css">
#appTable,thead,tr,th {
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

	ApplianceBean bean = (ApplianceBean) request.getAttribute("bean");
	UserBean userbean = (UserBean) session.getAttribute("email");

	if (userbean == null) {
		response.sendRedirect("SolarHomeSystemLogin");
	} else {

		ApplianceBAL.updateApplianceAlerts(bean.getApplianceId());
		int lastId = ApplianceStatusBAL.getLastStatusId(bean
				.getApplianceId());
		ApplianceStatusBean statusList = null;
		statusList = ApplianceStatusBAL.getStatusInfo(lastId,
				bean.getApplianceId());
		ArrayList<HashMap<String, String>> list = null;
		int countRequests = EligibilityBAL.getUnseenRequests();
		double latitude = Double.parseDouble(session.getAttribute(
				"latitud" + bean.getApplianceId()).toString());
		double Longitude = Double.parseDouble(session.getAttribute(
				"longi" + bean.getApplianceId()).toString());
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

		ArrayList<HashMap<String, String>> map = ApplianceBAL
				.getApplianceStatusbyId(bean.getApplianceId());
		ArrayList<HashMap<String, String>> maps = ApplianceStatusBAL
				.getStatusInfo(bean.getApplianceId());
		boolean state = bean.getState();
		String noSignal = "";
		if (bean.getSignal() == 0) {
			noSignal = "NO Signal";
		}
%>
<script type="text/javascript">
	var a =
<%=lat%>
	;

	var b =
<%=longi%>
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

	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>

<body>


	<div class="modal fade" id="modal-return">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Return Customer</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to returned the device <b><i
								id="applianceid"> </i></b> ?
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

	<div class="modal fade" id="modal-install">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Install Device</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to install the device <b><i
								id="applianceid"> </i></b> ?
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="updateInstallURL"
						class="btn btn-sm btn-success">Accept</a>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-nosignal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Install Device</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to install the device <b><i
								id="applianceid"> </i></b> ?
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="updateNoSignalURL"
						class="btn btn-sm btn-success">Accept</a>
				</div>
			</div>
		</div>
	</div>



	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->
	<%
		
	%>
	<input type="hidden" id="appID" value="<%=bean.getApplianceId()%>">
	<input type="hidden" id="customer_id" value="<%=bean.getCustomerId()%>">
	<input type="hidden" id="gsmNumber"
		value="<%=bean.getApplianceGsmNo()%>">
	<!-- begin #page-container -->
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

					<li class="has-sub"><a href="Request"> <i
							class="icon-note"></i> <span>New Loan Request</span> <span
							class="badge pull-right" id="unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub"><a href="Customer"> <i
							class="ion-ios-people"></i> <span>Customers</span>
					</a></li>
					<li class="active"><a href="Appliances"> <i
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
			<h1 class="page-header">
				Appliance <b><%=bean.getApplianceName() + " " + noSignal%> </b>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row">

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

													if (bean.getApplianceGsmNo() == null
															|| bean.getApplianceGsmNo() == "") {
											%> N/A <%
												} else {
														gsm = bean.getApplianceGsmNo().substring(2);
														StringBuilder buldgsm = new StringBuilder(gsm);
											%> <span>(+92) </span><input type="text" id="gsm"
											value="<%=buldgsm.insert(3, "-")%>" maxlength="11"
											onkeypress="setMobileDash(event,'dophone1')" disabled
											style="background-color: white; border: none">

										</td>
										<%
											}
										%>
									</tr>

									<tr>
										<th>Consumer Number</th>

										<%
											if (bean.getStatus() == 5
														|| bean.getStatus() == 6
														|| bean.getStatus() == 7) {
										%>
										<td><input type="text" id="ConsumerNumber"
											data-table="appliance" data-type="consumer"
											data-column="consumer" value="<%=bean.getConsumerNumber()%>"
											maxlength="15" disabled
											style="background-color: white; border: none" /> <span
											style="color: red" id="consumerNum"></span> <input
											type="button" class="btn btn-sm btn-success"
											id="saveConsumer" style="display: none" value="Save">
											<button class="btn  btn-danger" id="cancelEditConsumer"
												style="display: none">
												<i class="fa fa-times"></i>
											</button>


											<button class="btn btn-sm btn-success" id="editConsumer">
												<i class="fa fa-pencil"></i>
											</button> <span id="Consumerloader"></span></td>
										<%
											} else {

													if (bean.getConsumerNumber() == null) {
										%>

										<td>N/A</td>
										<%
											} else {
										%>
										<td><%=bean.getConsumerNumber()%></td>

										<%
											}
												}
										%>





									</tr>
									<tr>
										<th>Charging State</th>
										<td>
											<%
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
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(bean.getPrice()))%><b> PKR</b></td>
									</tr>
									<tr>
										<th>Customer Name</th>
										<td><a
											href="ViewServlet?click=view&cnic=<%=bean.getCustomerCnic()%>&applianceId=<%=bean.getApplianceId()%>">
												<%=bean.getCustomerName()%>
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
										<th>District Officer</th>
										<td><a href="DistrictOfficer?do_id=<%=bean.getDoid()%>"><%=bean.getUserName()%></a></td>
									</tr>
									<tr>
										<th>Field Officer</th>
										<td><a
											href="fieldOfficerProfile.jsp?fo_id=<%=bean.getFoid()%>"><%=bean.getFoName()%>
										</a></td>
									</tr>
									<tr>
										<th>Nizam Dost</th>
										<td><a
											href="Test?click=ok&salesman_id=<%=bean.getSalesmanId()%>&salesmanGsm=<%=bean.getSalesmanPhone()%>"><%=bean.getSalesmanName()%>
										</a></td>
									</tr>



									<tr>
										<%
											if (bean.getIsOwned() != 0.0) {
										%>
										<th>Appliance</th>
										<td><input id="switch_appliance" type="checkbox"
											data-render="switchery" data-theme="default" /></td>
										<script>
											var state =
										<%=state%>
											var imei =
										<%=bean.getImiNumber()%>
											if (imei == null) {
												$('#switch_appliance').attr(
														'disabled', 'disabled');

											}
											if (state == true) {
												$('#switch_appliance').attr(
														"checked", '');
											}
										</script>

										<%
											}
										%>
									</tr>
									<tr>
										<th>Replace Appliance</th>
										<td><input type="button" class="btn btn-sm btn-success"
											id="saveApp" style="display: none" value="Save">
											<button class="btn  btn-danger" id="cancelEdit"
												style="display: none">
												<i class="fa fa-times"></i>
											</button> <%
											 	if (bean.getImiNumber() == null
											 				|| (bean.getApplianceGsmNo() == null || bean
											 						.getApplianceGsmNo() == "")) {
											 %>
											<button style="background-color: gainsboro"
												class="btn btn-sm btn-success" id="editApp" disabled>
												<i class="fa fa-pencil"></i>
											</button> <span id="loader"></span> <%
												 	} else {
												 %>
											<button class="btn btn-sm btn-success" id="editApp">
												<i class="fa fa-pencil"></i>
											</button> <span id="loader"></span> <%
										 	}
										 %></td>

									</tr>
									<tr>
										<th>Go to LoanBook</th>
										<td>
														<% 
															if (bean.getStatusget().equals("maintend")
																		|| bean.getStatusget().equals("owned")
																		|| bean.getStatusget().equals("defaulter")) {
														%> <a
														href="PaymentLoanServlet?appliace_key=<%=bean.getApplianceId()%>"
														class="label"
														style="background-color: #34495e; color: white; font-weight: bold; width: 100%;font-size: 11px;">View Loanbook</a>
														<%
															} else {
														%> N/A <%
															}
														%>
													</td>
									</tr>
									<%
										if (state == false && bean.getStatus() == 2) {
									%>
									<tr>
										<td>

											<button type="button" class="btn btn-primary pull-left"
												data-toggle="modal" data-target="#modal-install"
												id="applianceid"
												onclick="setMoodalValue('<%=bean.getApplianceId()%>','<%=bean.getCustomerName()%>')">Insatll
												Device</button>
										</td>
										<td>
											<button type="button" class="btn btn-primary pull-left"
												data-toggle="modal" data-target="#modal-nosignal"
												id="applianceid"
												onclick="setMoodalNoSignalValue('<%=bean.getApplianceId()%>','<%=bean.getCustomerName()%>')">Installed
												and NoSignal</button>
										</td>
									</tr>
									<%
										}
									%>
									<tr>
										<td>
											<%
												if (bean.getStatus() == 6 || bean.getStatus() == 7) {
											%>
											<button type="button" class="btn btn-primary pull-left"
												data-toggle="modal" data-target="#modal-return"
												id="applianceid"
												onclick="setMoodValue('<%=bean.getApplianceId()%>','<%=bean.getCustomerName()%>')">Return
												Device</button> <%
 	}
 %>
										</td>
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


	<div class="col-md-6 ">
     
     <div class="panel panel-inverse" data-sortable-id="index-1">
      <div class="panel-heading">
       <div class="panel-heading-btn"></div>
       <h4 class="panel-title">District Summary</h4>
      </div>
      <div class="panel-body">
       <div class="table-responsive" style="font-size: 13px">

        <table class="table table-bordered">
         <thead>
          <tr>
           <th>District</th>
           <th>Avg Power Produced</th>
           <th>Avg Load Consumed</th>
           <th>Standard Avg Load Consumption </th>
           <th>Standard Avg Power Production</th>
           
          </tr>
         </thead>
         <tbody id="district_summary">

         </tbody>
        </table>
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
						<li><a href="#batteryperformance" data-toggle="tab">
								<div class="text-center"></div> <span class="hidden-xs m-l-3">
									Battery Performance <span class="badge badge-inverse m-l-3"></span>
							</span>
						</a></li>
						<span><input
							style="height: 30px; margin: 5px; float: right;" type="date"
							name="dateSet" id="dateSet"
							onchange="loadSoalrAmpere(), staticsDateWise(), loadBatteryAndSolarVoltage(), loadBatteryAndSolarCurrent(), loadApplianceTemperature()"></span>
					</ul>



					<div id="ioniconsTabContenta" class="tab-content">
						<div class="tab-pane fade in active" id="default">
						
						<div>
      					  <input type="hidden" id="current" value="<%=current%>">
        					<input type="hidden" id="SolarCurrent" value="<%=current%>">
        					
        					<span id="powertag"></span>  <span id="powerproduced"></span>
        
      						  WH &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span id="loadtag"></span>  
        					  <span id="loadConsumed"></span>
        
       						 WH &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span id="chargingtag"></span> <span id="hours"></span>
        
       						 Hours  <span id="mins"></span>  Mins
       
        			
        <!--         <input type="date" name="dates" id="dates" onchange="staticsDateWise();"> -->
      				 </div>


							<div id="chartdiv2"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>
						<div class="tab-pane fade" id="ios">
							<div id="chartdiv3"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>

						<div class="tab-pane fade" id="batteryperformance">
							<div id="chartdiv4"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>

							<div id="chartdiv5"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>

							<div id="chartdiv6"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>
					</div>
				</div>
			</div>

			<!-- appliance summary div class row ended -->

			<!-- start row -->

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
	<script src="js/applianceview.js"></script>

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- second am chart  -->

	<script>
		$(document).ready(function() {
			App.init();
			loadSoalrAmpere();
			staticsDateWise();
			loadBatteryAndSolarVoltage();
			loadBatteryAndSolarCurrent();
			loadApplianceTemperature();
			FormSliderSwitcher.init();
			getDistrictSummary();
		});

		var app_id =
	<%=bean.getApplianceId()%>
		var customer_id =
	<%=bean.getCustomerId()%>
		var GSM =
	<%=bean.getApplianceGsmNo()%>
		$(document).ready(function() {
			var checker = document.getElementById('#switch_appliance');
			$('#switch_appliance').change(function() {
				var on_off;
				if ($(this).is(":checked")) {
					$(".switchery").addClass("disabled");
					on_off = "on";
				} else {
					on_off = "off";
				}
				//alert(on_off)
				$.ajax({
					method : "POST",
					url : "OnOff",
					type : "json",
					data : {
						click : on_off,
						id : app_id,
						gsm : GSM
					},
					success : function(data) {
						var js = $.parseJSON(data);
						alert(js)
					}
				});

			})
		});
	</script>
</body>
</html>
