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
<%@page import="bal.TargetsBAL"%>
<%@page import="bean.UpComingRevocoveries"%>
<%@page import="java.util.*"%>


<!DOCTYPE html>
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

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->


<meta charset="utf-8" />
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
<link rel="stylesheet" href="style.css" type="text/css">

<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

</head>
<body data-salesman="<%=request.getParameter("salesman_id")%>">


	<%
		UserBean bean = (UserBean) session.getAttribute("email");

		if (bean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			int salesmanId = Integer.parseInt(request.getParameter("salesman_id"));
			int imageId = UserBAL.getImage(bean.getUserId());
			SalesManBean salesman = SalesmanBAL.getSalesManByID(salesmanId);
			List<String> endTarget = TargetsBAL.getTargetsDateForSales(salesmanId);
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
							<!-- 						End	SuperAdminHeader.js UnseenRequest() -->
					</a></li>


					<li class="has-sub"><a href="doDeployment.jsp"><i
							class="fa fa-star"></i><span>Deployment</span></a></li>

					<!-- <li class="has-sub active"><a href="doFieldOfficer.jsp"><i class="fa fa-star"></i><span>Field Officer</span></a></li> -->

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="doFieldOfficer.jsp">Field Officer</a></li>
							<li><a href="doSalesman.jsp">Nizam Dost</a></li>
						</ul></li>
					<li class="has-sub"><a href="DoTechnician"> <i
							class="fa fa-envelope"></i> <span>Technician</span>
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
			<!-- begin breadcrumb -->
			<!-- <ol class="breadcrumb pull-right">
				<li><a href="DistrictOfficerDashboard">Home</a></li>
				<li><a href="doFieldOfficer.jsp">Field Officer</a></li>
				<li><a href="doSalesmen.jsp">Salesman</a></li>
				<li class="active">Salesman view</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header"><%=request.getParameter("salesman_name")%><span
					id="customer_count"
					style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<!-- 					<div class="panel panel-inverse"> -->
					<div class="row" style="padding-top: 2%">
						<ul id="ioniconsTab" class="nav nav-tabs">
							<li class="active"><a href="#customer" data-toggle="tab">
									<span class="hidden-xs m-l-3">Customers<span
										class="badge badge-inverse m-l-3"></span>
								</span>
							</a></li>

							<li><a href="#comission" data-toggle="tab"> <span
									class="hidden-xs m-l-3">Commission<span
										class="badge badge-inverse m-l-3"></span>
								</span></a></li>
							<li><a href="#recoveries" data-toggle="tab"> <span
									class="hidden-xs m-l-3">Recoveries<span
										class="badge badge-inverse m-l-3"></span>
								</span></a></li>
						</ul>
						<!--                         <div class="panel-heading"> -->
						<!--                             <h4 class="panel-title">Salesman Table</h4> -->
						<!--                         </div> -->
						<div id="ioniconsTabContenta" class="tab-content">

							<div class="tab-pane fade in active" id="customer">
								<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px;">
										<table id="do-salesman-view-table"
											class="table table-bordered">
											<thead>
												<tr>
													<th>Name</th>
													<th>Father Name</th>
													<th>Mother Name</th>
													<th>CNIC</th>
													<!-- <th>Email</th> -->
													<th>Address</th>
													<th>Date of Created</th>
													<th>Customer Number</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>

									</div>
									<!-- 										</div> -->
								</div>
							</div>
							<div class="tab-pane fade" id="comission">
								<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px;">

										<!-- <div class="panel-body">
														<div id="chartdiv"
															style="width: 100%; height: 250px; margin-top: -8px; background-color: #fff; border-radius: 10px;"></div>
													</div> -->

										<h4 class="panel-title" style="width: 20%">
											<select id="targetDate" class="form-control"
												onchange="getTargetAchive(<%=salesman.getSalesmanId()%>,<%=salesman.getBeforeTime()%>,<%=salesman.getOnTime()%>,<%=salesman.getAfterTime()%>)">

												<%
													for (int i = 0; i < endTarget.size(); i++) {
												%>
												<option
													value="<%=endTarget.get(i).split(" to ")[0] + " to " + endTarget.get(i).split(" to ")[1]%>"><%=endTarget.get(i).split(" to ")[0]%>
													to
													<%=endTarget.get(i).split(" to ")[1]%></option>
												<%
													}
												%>

											</select>
										</h4>

										<table class="table">
											<thead>
												<tr>
													<th>Appliance</th>
													<th>Recoveries</th>
													<th>Amount</th>
													<th>Total</th>
												</tr>
											</thead>
											<tbody>
												<%
													if (endTarget.size() > 0) {
															int bt50W = TargetsBAL.getBeforeTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "50 W");
															int bt80W = TargetsBAL.getBeforeTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "80 W");
															int bt100W = TargetsBAL.getBeforeTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "100 W");
															int ot50W = TargetsBAL.getOnTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "50 W");
															int ot80W = TargetsBAL.getOnTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "80 W");
															int ot100W = TargetsBAL.getOnTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "100 W");
															int at50W = TargetsBAL.getAfterTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "50 W");
															int at80W = TargetsBAL.getAfterTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "80 W");
															int at100W = TargetsBAL.getAfterTime(endTarget.get(0).split(" to ")[0],
																	endTarget.get(0).split(" to ")[1], salesman.getSalesmanId(), "100 W");
												%>
												<tr class="link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=50 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=BT"
													style="background-color: #08C; color: white;">

													<td>50 W</td>
													<td id="50WBeforeTimeNumberOfRecovery"><%=bt50W%></td>
													<%
														String beforetime = "" + salesman.getBeforeTime();
																if (beforetime.length() == 1) {
																	beforetime = "0" + salesman.getBeforeTime();
																}
													%>
													<td id="50WBeforeTimeNumberOfPercentage"><%=bt50W * 1000 * Float.parseFloat("0." + beforetime)%>
													</td>
													<td rowspan="3" style="border-left: 1px solid white;"
														id="btTotal"><%=(bt50W * 1000 * Float.parseFloat("0." + beforetime))
							+ (bt80W * 1500 * Float.parseFloat("0." + beforetime))
							+ (bt100W * 2000 * Float.parseFloat("0." + beforetime))%></td>

												</tr>
												<tr class="link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=80 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=BT"
													style="background-color: #08C; color: white;">
													<td>80 W</td>
													<td id="80WBeforeTimeNumberOfRecovery"><%=bt80W%></td>
													<td id="80WBeforeTimeNumberOfPercentage"><%=bt80W * 1500 * Float.parseFloat("0." + beforetime)%></td>
												</tr>
												<tr class="link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=100 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=BT"
													style="background-color: #08C; color: white;">
													<td>100 W</td>
													<td id="100WBeforeTimeNumberOfRecovery"><%=bt100W%></td>
													<td id="100WBeforeTimeNumberOfPercentage"><%=bt100W * 2000 * Float.parseFloat("0." + beforetime)%></td>

												</tr>
												<tr class="link success"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=50 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=OT">

													<td>50 W</td>
													<td id="50WOnTimeNumberOfRecovery"><%=ot50W%></td>
													<%
														String ontime = "" + salesman.getOnTime();
																if (ontime.length() == 1) {
																	ontime = "0" + salesman.getOnTime();
																}
													%>
													<td id="50WOnTimeNumberOfPercentage"><%=ot50W * 1000 * Float.parseFloat("0." + ontime)%></td>
													<td rowspan="3" style="border-left: 1px solid #99dede;"
														id="otTotal"><%=(ot50W * 1000 * Float.parseFloat("0." + ontime))
							+ (ot80W * 1500 * Float.parseFloat("0." + ontime))
							+ (ot100W * 2000 * Float.parseFloat("0." + ontime))%></td>
												</tr>
												<tr class="link success"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=80 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=OT">
													<td>80 W</td>
													<td id="80WOnTimeNumberOfRecovery"><%=ot80W%></td>

													<td id="80WOnTimeNumberOfPercentage"><%=ot80W * 1500 * Float.parseFloat("0." + ontime)%></td>
												</tr>
												<tr class="success link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=100 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=OT">

													<td>100 W</td>
													<td id="100WOnTimeNumberOfRecovery"><%=ot100W%></td>

													<td id="100WOnTimeNumberOfPercentage"><%=ot100W * 2000 * Float.parseFloat("0." + ontime)%></td>

												</tr>
												<tr class="danger link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=50 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=AT">
													<td>50 W</td>
													<td id="50WAfterTimeNumberOfRecovery"><%=at50W%></td>
													<%
														String aftertime = "" + salesman.getAfterTime();
																if (aftertime.length() == 1) {
																	aftertime = "0" + salesman.getAfterTime();
																}
													%>
													<td id="50WAfterTimeNumberOfPercentage"><%=at50W * 1000 * Float.parseFloat("0." + aftertime)%></td>
													<td rowspan="3" style="border-left: 1px solid #ffbdbc;"
														id="atTotal"><%=(at50W * 1000 * Float.parseFloat("0." + aftertime))
							+ (ot80W * 1500 * Float.parseFloat("0." + aftertime))
							+ (ot80W * 1500 * Float.parseFloat("0." + aftertime))%></td>
												</tr>
												<tr class="danger link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=80 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=AT">
													<td>80 W</td>
													<td id="80WAfterTimeNumberOfRecovery"><%=ot80W%></td>
													<td id="80WAfterTimeNumberOfPercentage"><%=ot80W * 1500 * Float.parseFloat("0." + aftertime)%></td>
												</tr>
												<tr class="danger link"
													data-link="CommissionBreakUp?salesmanId=<%=salesmanId%>&applianceName=100 W&targetEndDate=<%=endTarget.get(0).split(" to ")[0] + " to " + endTarget.get(0).split(" to ")[1]%>&paidTime=AT">
													<td>100 W</td>
													<td id="100WAfterTimeNumberOfRecovery"><%=at100W%></td>
													<td id="100WAfterTimeNumberOfPercentage"><%=at100W * 2000 * Float.parseFloat("0." + aftertime)%></td>

												</tr>
												<%
													}
												%>
											</tbody>
										</table>

									</div>
									<!-- 										</div> -->
								</div>
							</div>
							<div class="tab-pane fade" id="recoveries">
								<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px;">


										<div class="panel-body">
											<table id="do-salesman-view-table"
												class="table table-bordered">
												<thead>
													<tr>
														<th>Name</th>
														<th>Appliance Name</th>
														<th>Remaining Days</th>
													</tr>
												</thead>
												<tbody>
													<%
														List<UpComingRevocoveries> upComingRevocoveries = SalesmanBAL
																	.upComingRevocoveriesBySalesmanId(salesmanId);

															for (int i = 0; i < upComingRevocoveries.size(); i++) {
																String color = "white";

																if (Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) >= -7
																		&& Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) <= -1) {
																	color = "Red";
																} else if (Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) == 0) {
																	color = "black";
																} else if (Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) >= 1
																		&& Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) <= 4) {
																	color = "pink";
																} else if (Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) >= 5
																		&& Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) <= 10) {
																	color = "yellow";
																} else if (Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) >= 11
																		&& Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) <= 29) {
																	color = "GreenYellow";
																}
													%>
													<tr
														style="background-color:<%=color%>; <%if (Integer.parseInt(upComingRevocoveries.get(i).getRemainingDays()) == 0) {%> color:white;<%}%> ">
														<td><%=upComingRevocoveries.get(i).getCustomerName()%></td>
														<td><%=upComingRevocoveries.get(i).getApplianceName()%></td>
														<td><%=upComingRevocoveries.get(i).getRemainingDays()%></td>
													</tr>
													<%
														}
													%>

												</tbody>
											</table>

										</div>
									</div>
									<!-- 									</div> -->
								</div>
							</div>
						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-10 -->

				<%
					} // session else closed
				%>

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
	<script src="amcharts/amcharts.js" type="text/javascript"></script>
	<script src="amcharts/serial.js" type="text/javascript"></script>

	<script>
		$(function() {
			// 	function getTable(){
			$("#do-salesman-view-table").DataTable(
					{

						"processing" : true,
						"serverSide" : true,
						"order" : [ [ 0, "asc" ] ],
						"ajax" : {
							"url" : "DoSalesmanController",
							"method" : "post",
							"data" : function(d) {
								d.action = "doView", d.sali = $(
										'[data-salesman]').data("salesman")
							},

							"dataSrc" : function(json) {
								$('#salesman_name').text()
								$('#customer_count').text(json.data.length);
								console.log("+++++++++++++++++++++++")
								console.log(json)
								return json.data;
							}

						},

						"columns" : [ {
							"data" : "customerName"
						}, {
							"data" : "customerFatherName"
						}, {
							"data" : "customerMotherName"
						}, {
							"data" : "customerCnic"
						}/* , {
							"data" : "customerEmail"
						} */, {
							"data" : "customerAddress"
						}, {
							"data" : "customerCreated"
						}, {
							"data" : "customerPhone"
						} ]

					})

		});
		
		function getTargetAchive(salesmanId ,bt, ot , at){
			
			var targetDate = document.getElementById("targetDate").value;
			var oot = ''+ot;
			var bbt = ''+bt;
			var aat = ''+at;
			
			if(oot.length==1){
				oot = "0"+oot;
				
			}
			if(bbt.length==1){
				bbt = "0"+bbt;
			}
			if(aat.length==1){
				aat = "0"+aat;
			}

			
			
			
			
			
			
			
			var links = $('tr[data-link]').get();
			$.each(links, function(e){
				var row = $(links[e]).data('link')
				console.info(row)
				rrow = row.replace(/[0-9]{4}-?[0-9]{2}-[0-9]{2} to [0-9]{4}-?[0-9]{2}-[0-9]{2}/, targetDate)
//	 			console.error(rrow)
				$(links[e]).data('link',rrow);
				
			});
			//alert(salesmanId);
			//url : '/NizamEnergyProject/CommissionFilter',
			var d = targetDate.split(" to ");

			 $.ajax({
				url : 'CommissionFilter',
				dataType : 'json',
				method : 'POST',
				data : {
					salesmanId : salesmanId,
					targetStartDate : d[0],
					targetEndDate : d[1],
					beforeTime : bbt,
					onTime : oot,
					afterTime : aat
					
				},
				success : function(data){
					
//	 				console.log(data[9].TARGETDATE);
//	 				console.log(dataProviders);
					
//	 				dbGraph = data[9].TARGETDATE;
					
					$('#50WBeforeTimeNumberOfRecovery').text(''+data[0].BT50W);
					var per= data[0].BT50W*1000*parseFloat("0."+bbt);
						$('#50WBeforeTimeNumberOfPercentage').text(per+'.0');
						
					$('#80WBeforeTimeNumberOfRecovery').text(''+data[1].BT80W);
					per=data[1].BT80W*1500*parseFloat("0."+bbt);
						$('#80WBeforeTimeNumberOfPercentage').text(per+'.0');
					
					$('#100WBeforeTimeNumberOfRecovery').text(''+data[2].BT100W);
					per= data[2].BT100W*2000*parseFloat("0."+bbt);
						$('#100WBeforeTimeNumberOfPercentage').text(per+'.0');
					$("#btTotal").text(((parseFloat(data[0].BT50W*1000*parseFloat("0."+bbt)))+(parseFloat(data[1].BT80W*1500*parseFloat("0."+bbt)))+(parseFloat(data[2].BT100W*2000*parseFloat("0."+bbt))))+'.0');
				//	alert((parseFloat(data[0].BT50W*1000*parseFloat("0."+bbt)))+(parseFloat(data[1].BT80W*1500*parseFloat("0."+bbt)))+(parseFloat(data[2].BT100W*2000*parseFloat("0."+bbt))));
					//document.getElementById("50WOnTimeNumberOfRecovery").innerHtml =data[3].OT50W;
					$('#50WOnTimeNumberOfRecovery').text(''+data[3].OT50W);
					per= data[3].OT50W*1000*parseFloat("0."+oot);
					
						$('#50WOnTimeNumberOfPercentage').text(per+'.0');
					
					$('#80WOnTimeNumberOfRecovery').text(''+data[4].OT80W);
					per= data[4].OT80W*1500*parseFloat("0."+oot);
					
						$('#80WOnTimeNumberOfPercentage').text(per+'.0');
						
					
					
					$('#100WOnTimeNumberOfRecovery').text(''+data[5].OT100W);
					per= data[5].OT100W*2000*parseFloat("0."+oot);
					
						$('#100WOnTimeNumberOfPercentage').text(per+'.0');
						$("#otTotal").text(((parseFloat(data[3].OT50W*1000*parseFloat("0."+oot)))+(parseFloat(data[4].OT80W*1500*parseFloat("0."+oot)))+(parseFloat(data[5].OT100W*2000*parseFloat("0."+oot))))+'.0');
					
					$('#50WAfterTimeNumberOfRecovery').text(''+data[6].AT50W);
					per= data[6].AT50W*1000*parseFloat("0."+aat);
					
						$('#50WAfterTimeNumberOfPercentage').text(per+'.0');
					
					$('#80WAfterTimeNumberOfRecovery').text(''+data[7].AT80W);
					per= data[7].AT80W*1500*parseFloat("0."+aat);
						$('#80WAfterTimeNumberOfPercentage').text(per+'.0');
					
					$('#100WAfterTimeNumberOfRecovery').text(''+data[8].AT100W);
					per= data[8].AT100W*2000*parseFloat("0."+aat);
						$('#100WAfterTimeNumberOfPercentage').text(per+'.0');
						$("#atTotal").text(((parseFloat(data[6].AT50W*1000*parseFloat("0."+aat)))+(parseFloat(data[7].AT80W*1500*parseFloat("0."+aat)))+(parseFloat(data[8].AT100W*2000*parseFloat("0."+aat))))+'.0');
					
					
						
//	 					graphFunction(dbGraph);
						
						
		            
					
				}
			
			});
		
		}  
		
	</script>

	<script>
		$(document).ready(function() {
			App.init();
			// 			getTable();
			// 			TableManageColVis.init();
		});
	</script>

</body>


</html>
