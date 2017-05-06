<!DOCTYPE html>
<%@page import="bal.UserBAL"%>
<%@page import="bean.UpComingRevocoveries"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bal.TargetsBAL"%>
<%@page import="bal.FieldOfficerBAL"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<html lang="en">

<head>
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



<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ================== END PAGE LEVEL STYLE ================== -->

<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="assets/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->


<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />

<link rel="stylesheet" href="style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>

<script type="text/javascript">
function getCommissionByDate(fo_id ,bt, ot , at){
	var start_date = ""+document.getElementById("fromDate").value;
	var end_date = ""+document.getElementById("endDate").value;
	if(startDate !="" && endDate!=""){
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

		 $.ajax({
			url : 'FoCommissionFilter',
			dataType : 'json',
			method : 'POST',
			data : {
				foId : fo_id,
				fromDate : from_date,
				endDate : end_date,
				beforeTime : bbt,
				onTime : oot,
				afterTime : aat
				
			},
			success : function(data){
				
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

			}
		
		});
	}
	

}  




</script>
<script>
 function setMoodValue(foId, fo_name) {
  var id = document.getElementById("foId").innerHTML = foId;
  var name = document.getElementById("foId").innerHTML = fo_name;
   document.getElementById('updateURL').onclick = function() {
	$.ajax({
		url : 'FoRemovingController',
		method : 'POST',
		dataType : 'json',
		data : {
			click : "remove",
			foId : id
		},
		success : function(data) {
			$('#hello').text(data.Have_Network)
			$("#modal-remove").modal('hide');	
			$("#modal-list").modal('show');	
			$("#foIdd").text(data.foName)
			if(data.bulkFo == "null"){
				$("#bulk").text('Alert ! there is no Bulk FO of his District so you can not convert or remove this Fo. Kindly create Bulk FO');
			}else {
				$("#district").text(data.bulkFo)
			}
			}
	})
 }
   document.getElementById('update').onclick = function() {
	   console.log("foId "+foId)
	   helloWorld(foId);
	 }

}
 
 function helloWorld(foId) {
	 console.log("foId :"+foId)
		var link = "FoRemovingController?click=ok&foId="
				+ foId;
		document.getElementById('update').href = link;

	}
</script>

</head>

<body>
	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		int foID = Integer.parseInt(request.getParameter("fo_id"));
		ArrayList<HashMap<String, String>> list = FieldOfficerBAL
				.getFoSalesman(foID);
		HashMap<String, String> maps = FieldOfficerBAL
				.getFieldOfficerDetail(foID);
		String phone = UserBAL
				.getFormattedPhoneNumber(maps.get("foNumber"));
	%>

	<div class="modal fade" id="modal-remove">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Remove Fo</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you would like to remove FO <b><i id="foId">
							</i></b> ?
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
	<div class="modal fade" id="modal-list">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Remove Fo</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							<b><i id="hello"> </i></b> Would you still like to proceed and
							transfer FO <b><i id="foIdd"> </i></b>'s network to <b><i
								id="district"> </i></b> ?
						</p>
						<b><p id="bulk"></p></b>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="update"
						class="btn btn-sm btn-success">Accept</a>
				</div>
			</div>
		</div>
	</div>

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
							<%=bean.getUserName()%>
							<small> Superadmin </small>
						</div>
					</li>
				</ul>
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
					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Reports</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="reportAnalytics.jsp">Late/Defaulter Report</a></li>
							<li><a href="futureLoanBooks.jsp">Future loan book</a></li>
							<li><a href="SaReports.jsp">Reports</a></li>
						</ul></li>

					<li class="has-sub"><a href="Analytics"> <i
							class="ion-podium"></i> <span>Analytics</span>
					</a></li>
					<li class="has-sub"><a href="CashSale.jsp"> <i
							class="ion-podium"></i> <span>Cash Sales</span>
					</a></li>
					<!-- 					<li class="has-sub"><a href="FinanceAnalytics.jsp"> <i -->
					<!-- 							class="ion-podium"></i> <span>Finance Analytics</span> -->
					<!-- 					</a></li> -->

				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->
		<div id="content" class="content">

			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Field Officer</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tbody>
										<tr>
											<th>CNIC</th>
											<td><%=maps.get("Cnic")%></td>
										</tr>
										<tr>
											<th>Phone Number</th>
											<td><%=phone%></td>
										</tr>
										<tr>
											<th>Address</th>
											<td><%=maps.get("address")%></td>
										</tr>
										<tr>
											<th>District Officer</th>
											<td><a
												href="DistrictOfficer?do_id=<%=maps.get("user_id")%>"><%=maps.get("DistrictOfficer")%></a></td>
										</tr>

										<tr>
											<th>District</th>
											<td><%=maps.get("District")%></td>
										</tr>

										<tr>
											<th>Tehsil</th>
											<td><%=maps.get("city")%></td>
										</tr>
										<tr>
											<th>Date Of Birth</th>
											<td><%=maps.get("date_of_birth")%></td>
										</tr>
										<tr>
											<th>Date Of Joining</th>
											<td><%=maps.get("date_of_joining")%></td>
										</tr>
										<tr>
											<th>Monthly Recovery Commission</th>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
					Integer.parseInt(maps.get("foontime")))%> %</td>
										</tr>

										<tr>
											<th>Per Sale Commission</th>
											<%
												if (maps.get("per_sale") == "" || maps.get("per_sale") == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(Integer.parseInt(maps.get("per_sale"))))%><b> PKR</b></td>

											<%
												}
											%>

										</tr>

										<tr>
											<th>Field Officer Acc Number</th>

											<%
												if (maps.get("fo_acount_no") == ""
														|| maps.get("fo_acount_no") == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=maps.get("fo_acount_no")%></td>
											<%
												}
											%>
										</tr>
										<tr>
											<th>Update Profile</th>
											<td><a href="updatefo.jsp?foId=<%=maps.get("fo_id")%>"
												class="label"
												style="background-color: #3449ee; color: white; font-weight: bold; width: 100%">Edit</a></td>
										</tr>
										<tr>
											<td>
												<button type="button" class="btn btn-primary pull-left"
													data-toggle="modal" data-target="#modal-remove" id="foId"
													onclick="setMoodValue('<%=foID%>','<%=maps.get("foName")%>')">Remove
													Field Officer</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
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

							<li><a href="#defaultCustomers" data-toggle="tab"> <span
									class="hidden-xs m-l-3">Default Customers<span
										class="badge badge-inverse m-l-3"></span>
								</span></a></li>

							<li><a href="#recoveries" data-toggle="tab"> <span
									class="hidden-xs m-l-3">Recoveries<span
										class="badge badge-inverse m-l-3"></span>
								</span></a></li>
						</ul>

						<div id="ioniconsTabContenta" class="tab-content">

							<div class="tab-pane fade in active" id="customer">
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px">
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Name</th>
													<th>Number</th>
													<th>CNIC</th>
													<th>Address</th>
													<th>Salary</th>
													<th>Date of Joining</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (int i = 0; i < list.size(); i++) {
														StringBuilder focell = new StringBuilder(list.get(i)
																.get("Phone").replace("92", ""));
														focell = focell.insert(3, "-");
												%>
												<tr class="link"
													data-link="Test?click=ok&salesman_id=<%=list.get(i).get("salesmanID")%>&salesmanGsm=<%=list.get(i).get("Phone")%>">
													<td><%=list.get(i).get("SalesName")%></td>
													<td><span>(+92) </span><%=focell%></td>
													<td><%=list.get(i).get("Cnic")%></td>
													<td><%=list.get(i).get("Address")%></td>
													<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Double.parseDouble(list.get(i).get("Salary")))%><b> PKR</b></td>
													<td><%=list.get(i).get("date")%></td>

												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="defaultCustomers">
								<div class="panel-body">
									<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade active in" id="home"
											style="margin: -15px">
											<div class="panel-body">
												<div class="table-responsive" style="font-size: 13px;">
													<table class="table table-bordered table-hover">
														<thead>
															<tr>
																<th>Name</th>
																<th>CNIC</th>
																<th>Phone Number</th>
																<th>Appliance Name</th>
																<th>Appliance IMEI</th>
																<th>Due Date</th>
															</tr>
														</thead>
														<tbody>
															<%
																ArrayList<HashMap<String, String>> listdefaultcustomer = FieldOfficerBAL
																		.getDefaultCustomerSpFo(foID);
																if (listdefaultcustomer.size() != 0) {
																	for (int i = 0; i < listdefaultcustomer.size(); i++) {
																		StringBuilder focell = new StringBuilder(
																				listdefaultcustomer.get(i).get("customer_phone")
																						.replace("92", ""));
																		focell = focell.insert(3, "-");
															%>
															<tr>
																<td><%=listdefaultcustomer.get(i).get("customer_name")%></td>
																<td><%=listdefaultcustomer.get(i).get("customer_cnic")%></td>
																<td><span>(+92) </span><%=focell%></td>

																<td><%=listdefaultcustomer.get(i).get("appliance_name")%></td>
																<td><%=listdefaultcustomer.get(i).get("appliance_GSMno")%></td>
																<td><%=listdefaultcustomer.get(i).get("due_date")%></td>

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
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="recoveries">
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px;">


										<div class="panel-body">
											<table id="do-salesman-view-table"
												class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Name</th>
														<th>Appliance Name</th>
														<th>Remaining Days</th>
													</tr>
												</thead>
												<tbody>
													<%
														List<UpComingRevocoveries> upComingRevocoveries = FieldOfficerBAL
																.upComingRevocoveriesByFOId(foID);

														for (int i = 0; i < upComingRevocoveries.size(); i++) {
															String color = "white";
															String txtcolor = "white";

															if (Integer.parseInt(upComingRevocoveries.get(i)
																	.getRemainingDays()) >= -7
																	&& Integer.parseInt(upComingRevocoveries.get(i)
																			.getRemainingDays()) <= -1) {
																color = "Red";
															} else if (Integer.parseInt(upComingRevocoveries.get(i)
																	.getRemainingDays()) == 0) {
																color = "black";
															} else if (Integer.parseInt(upComingRevocoveries.get(i)
																	.getRemainingDays()) >= 1
																	&& Integer.parseInt(upComingRevocoveries.get(i)
																			.getRemainingDays()) <= 4) {
																color = "pink";
															} else if (Integer.parseInt(upComingRevocoveries.get(i)
																	.getRemainingDays()) >= 5
																	&& Integer.parseInt(upComingRevocoveries.get(i)
																			.getRemainingDays()) <= 10) {
																color = "yellow";
																txtcolor = "black";
															} else if (Integer.parseInt(upComingRevocoveries.get(i)
																	.getRemainingDays()) >= 11
																	&& Integer.parseInt(upComingRevocoveries.get(i)
																			.getRemainingDays()) <= 29) {
																color = "GreenYellow";
																txtcolor = "black";
															}
													%>
													<tr
														style="background-color:<%=color%>;color:<%=txtcolor%> <%if (Integer.parseInt(upComingRevocoveries.get(i)
						.getRemainingDays()) == 0) {%> color:black;<%}%> ">
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
								</div>
							</div>
						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-10 -->
			</div>
			<!-- tab panel row end -->
		</div>

		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/myscript.js"></script>

	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/morris/raphael.min.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/js/chart-morris.demo.min.dashboard.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script type='text/javascript'
		src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
	<script type="text/javascript" src="assets/async/dashboard.js"></script>
	<script>
		$(document).ready(function() {
 			App.init();
		});
	</script>

</body>
</html>