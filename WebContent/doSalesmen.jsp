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
<%@page import="bal.FieldOfficerBAL"%>
<%@page import="bal.TargetsBAL"%>
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
<%@page import="java.util.Map"%>


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

<script type='text/javascript'
	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type='text/javascript'
	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
<script src="http://maps.googleapis.com/maps/api/js"></script>

<style>
#map {
	/*        width: 500px;*/
	height: 400px;
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
		try{
		var myOptions = {
			center : new google.maps.LatLng(30.890542, 71.274856),
			zoom : 5,
			mapTypeId : google.maps.MapTypeId.ROADMAP

		};
		var map = new google.maps.Map(document.getElementById("map"), myOptions);


		$.ajax({
			url : 'Salesman_locations.jsp',
			dataType : 'json',
			success : function(data) {
				for ( var iii = 0; iii < data.length; iii++) {
					MarkerLongi.push(data[iii].Longitude);
					infoName.push(data[iii].Time);
					infoType.push(data[iii].gsm);
					MarkerLati.push(data[iii].Latitude);
					infoScheme.push(data[iii].Name);
				}
				locations = new Array(MarkerLati.length);
				var marker, i;
				for ( var i = 0; i < locations.length; i++) {
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
							+ "Number: " + number + " <br/>Name: "
							+ name + "</b>";

					var infowindow = new google.maps.InfoWindow();

					google.maps.event.addListener(marker, 'mouseover', (function(
							marker, content, infowindow) {
						return function() {
							infowindow.setContent(content);
							infowindow.open(map, marker);
						};
					})
					
					(marker, content, infowindow));
					google.maps.event.addListener(marker,'mouseout', (function(marker,content,infowindow){ 
					    return function() {
					       infowindow.setContent(content);
					       infowindow.close(map,marker);
					    };
					})(marker,content,infowindow));
					
				}

				jQuery(document).ready(function() {
				});
			}

		});
		}catch(e){
			console.error(e);
		}
	}
</script>


<script>
	
	
	
	</script>


</head>
<body data-foId="<%=request.getParameter("fo_id")%>"
	onload="add_marker()">


	<%
		UserBean bean = (UserBean) session.getAttribute("email");

		if (bean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			int foID = Integer.parseInt(request.getParameter("fo_id"));
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

					<li class="has-sub "><a href="doDeployment.jsp"><i
							class="fa fa-suitcase"></i><span>Deployment</span></a></li>

					<li class="has-sub"><a href="doLoanBooks.jsp"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>
					<li class="has-sub active"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="doFieldOfficer.jsp">Field Officer</a></li>
							<li class="active"><a href="doSalesman.jsp">Nizam Dost</a></li>
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
			<!-- begin breadcrumb -->
			<!-- <ol class="breadcrumb pull-right">
				<li><a href="DistrictOfficerDashboard">Home</a></li>
				<li><a href="doFieldOfficer.jsp">Field Officer</a></li>
				<li class="active">Salesman</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">
				Nizam Dost <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->


			<div class="row" style="padding-top: 2%">

				<!-- Map Start -->

				<div class="col-md-8">


					<div class="panel panel-inverse" data-sortable-id="table-basic-6"
						style="font-size: 13px">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a>

							</div>
							<h4 class="panel-title">MAP</h4>
						</div>
						<div class="panel-body">
							<div id="map"></div>
						</div>
					</div>


				</div>

				<div class="row" style="padding-top: 2%">

					<!-- begin col-12 -->
					<div class="col-md-12">
						<!-- begin panel -->
						<!-- 					<div class="panel panel-inverse"> -->
						<div class="row" style="padding-top: 2%">
							<ul id="ioniconsTab" class="nav nav-tabs">
								<li class="active"><a href="#customer" data-toggle="tab">
										<span class="hidden-xs m-l-3">Nizam Dost<span
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
											<table id="do-salesman-table"
												class="table table-hover table-bordered">
												<thead>
													<tr>
														<th>Nizam Dost Name</th>
														<th>Phone Number</th>
														<th>CNIC</th>
														<th>Address</th>
														<th>Salary</th>
														<th>Date of Joining</th>
														<th>Total Customer</th>
														<th>Total Sales</th>
														<th>Late Customer</th>
														<th>Total Commission</th>
														<!-- <th>options</th> -->
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
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in" id="home"
												style="margin: -15px">
												<div class="panel-body">
													<div class="table-responsive" style="font-size: 13px;">
														<%
															HashMap<String, String> map = FieldOfficerBAL.getJoiningDateByFoId(foID);
														%>

														<table>
															<tr>
																<td><input type="date" class="form-control"
																	id="fromDate" name="fromDate" style="width: 150px;" />
																</td>
																<td><b>to</b></td>
																<td><input type="date" class="form-control"
																	id="endDate" name="endDate" style="width: 150px;" /></td>
																<td><input type="button" value="Filter"
																	onclick="getCommissionByDate(<%=foID%> ,<%=map.get("bt")%>, <%=map.get("ot")%> , <%=map.get("at")%>)" />
																</td>
															</tr>
														</table>

														<div class="col-md-4"></div>


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
																	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
																		String currentDate = sdf.format(new Date());
																		int bt50W = TargetsBAL.getBeforeTimeForFO(map.get("joiningDate"), currentDate, foID, "50 W");
																		int bt80W = TargetsBAL.getBeforeTimeForFO(map.get("joiningDate"), currentDate, foID, "80 W");
																		int bt100W = TargetsBAL.getBeforeTimeForFO(map.get("joiningDate"), currentDate, foID, "100 W");
																		int ot50W = TargetsBAL.getOnTimeForFo(map.get("joiningDate"), currentDate, foID, "50 W");
																		int ot80W = TargetsBAL.getOnTimeForFo(map.get("joiningDate"), currentDate, foID, "80 W");
																		int ot100W = TargetsBAL.getOnTimeForFo(map.get("joiningDate"), currentDate, foID, "100 W");
																		int at50W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID, "50 W");
																		int at80W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID, "80 W");
																		int at100W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID, "100 W");
																%>
																<tr class="link"
																	style="background-color: #08C; color: white;">

																	<td>50 W</td>
																	<td id="50WBeforeTimeNumberOfRecovery"><%=bt50W%></td>
																	<%
																		String beforetime = map.get("bt");
																			if (beforetime.length() == 1) {
																				beforetime = "0" + beforetime;
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
																	style="background-color: #08C; color: white;">
																	<td>80 W</td>
																	<td id="80WBeforeTimeNumberOfRecovery"><%=bt80W%></td>
																	<td id="80WBeforeTimeNumberOfPercentage"><%=bt80W * 1500 * Float.parseFloat("0." + beforetime)%></td>
																</tr>
																<tr class="link"
																	style="background-color: #08C; color: white;">
																	<td>100 W</td>
																	<td id="100WBeforeTimeNumberOfRecovery"><%=bt100W%></td>
																	<td id="100WBeforeTimeNumberOfPercentage"><%=bt100W * 2000 * Float.parseFloat("0." + beforetime)%></td>
																</tr>
																<tr class="link success">
																	<td>50 W</td>
																	<td id="50WOnTimeNumberOfRecovery"><%=ot50W%></td>
																	<%
																		String ontime = map.get("ot");
																			if (ontime.length() == 1) {
																				ontime = "0" + ontime;
																			}
																	%>
																	<td id="50WOnTimeNumberOfPercentage"><%=ot50W * 1000 * Float.parseFloat("0." + ontime)%></td>
																	<td rowspan="3" style="border-left: 1px solid #99dede;"
																		id="otTotal"><%=(ot50W * 1000 * Float.parseFloat("0." + ontime))
						+ (ot80W * 1500 * Float.parseFloat("0." + ontime))
						+ (ot100W * 2000 * Float.parseFloat("0." + ontime))%></td>
																</tr>
																<tr class="link success">
																	<td>80 W</td>
																	<td id="80WOnTimeNumberOfRecovery"><%=ot80W%></td>

																	<td id="80WOnTimeNumberOfPercentage"><%=ot80W * 1500 * Float.parseFloat("0." + ontime)%></td>
																</tr>
																<tr class="success link">
																	<td>100 W</td>
																	<td id="100WOnTimeNumberOfRecovery"><%=ot100W%></td>
																	<td id="100WOnTimeNumberOfPercentage"><%=ot100W * 2000 * Float.parseFloat("0." + ontime)%></td>

																</tr>
																<tr class="danger link">
																	<td>50 W</td>
																	<td id="50WAfterTimeNumberOfRecovery"><%=at50W%></td>
																	<%
																		String aftertime = map.get("at");
																			if (aftertime.length() == 1) {
																				aftertime = "0" + aftertime;
																			}
																	%>
																	<td id="50WAfterTimeNumberOfPercentage"><%=at50W * 1000 * Float.parseFloat("0." + aftertime)%></td>
																	<td rowspan="3" style="border-left: 1px solid #ffbdbc;"
																		id="atTotal"><%=(at50W * 1000 * Float.parseFloat("0." + aftertime))
						+ (ot80W * 1500 * Float.parseFloat("0." + aftertime))
						+ (ot80W * 1500 * Float.parseFloat("0." + aftertime))%></td>
																</tr>
																<tr class="danger link">
																	<td>80 W</td>
																	<td id="80WAfterTimeNumberOfRecovery"><%=ot80W%></td>
																	<td id="80WAfterTimeNumberOfPercentage"><%=ot80W * 1500 * Float.parseFloat("0." + aftertime)%></td>
																</tr>
																<tr class="danger link">
																	<td>100 W</td>
																	<td id="100WAfterTimeNumberOfRecovery"><%=at100W%></td>
																	<td id="100WAfterTimeNumberOfPercentage"><%=at100W * 2000 * Float.parseFloat("0." + aftertime)%></td>

																</tr>

															</tbody>
														</table>
													</div>

												</div>
											</div>






										</div>
										<!-- 										</div> -->
									</div>
								</div>
								<div class="tab-pane fade" id="recoveries">
									<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
									<div class="panel-body">
										<div class="table-responsive" style="font-size: 13px;">


											<div class="panel-body">
												<table id="do-salesman-view-tablee"
													class="table table-bordered">
													<thead>
														<tr>
															<th>Name</th>
															<th>Appliance Name</th>
															<th>Remaining Days</th>
														</tr>
													</thead>
													<%-- <tbody>
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

													</tbody> --%>
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



				</div>



				<%
					} // session else closed
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
	
	$("#do-salesman-table").DataTable({
		
		"processing" : true,
		"serverSide" : true,
		"order" : [ [ 0, "asc" ] ],
		"ajax" : {
			"url" : "DoSalesmanController",
			"method" : "post",
			"data" : function(d){
				d.action = "getDoSalesmanTable",
				d.foId = <%=request.getParameter("fo_id")%>//$('[data-foId]').data("foId")
			},
// 			success : function(data){
// 				console.log("+++++++++++++++++++++++")
// 				console.log(data)
// 			}
			"dataSrc" : function(json){
				
				$(".page-header span").text(json.salesmanCount);
				console.log("+++++++++++++++++++++++")
				console.log(json)
				
				for(var i=0; i<json.data.length; i++){
					if(json.data[i].commissionDate > "0"){
						json.data[i].commissionDate = 'Commission will issue after '+json.data[i].commissionDate+' days';
					}else{
						json.data[i].commissionDate = 'Commission amount Rs.'+json.data[i].salesmanCommission;
					}
				}
				
				return json.data;
			},
			
		},
		"columns" : [
		             {"data" : "salesmanName"},
		             {"data" : "salesmanNumber"},
		             {"data" : "salesmanCnic"},
		             {"data" : "salesmanAddress"},
		             {"data" : "salesmanSallery"},
		             {"data" : "salesmanDateJoin"},
		             {"data" : "totalCustomers"},
		             {"data" : "totalSales"},
		             {"data" : "lateCustomer"},
		             {"data" : "commissionDate"}
		             ],
		 
		"rowCallback" : function(row, data){
//		if($.inArray(data.DT_RowId, selected) !== -1){
			$(row).data('link', 'doSalesmanView.jsp?salesman_id='+data.salesmanId+'&salesman_name='+data.salesmanName);
			$(row).addClass('link')
//		}
		}
		             
	          
		
	});
	$("#do-salesman-table tbody").on('click', 'tr.link>td',function(event) {
		link = $(this).parent().data('link');
		if (link != undefined) {
			window.location=link;			
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
