
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<%@page import="bal.UserBAL"%>
<%@page import="bean.UpComingRevocoveries"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bal.TargetsBAL"%>
<%@page import="bal.FieldOfficerBAL"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<html lang="en">
<!--<![endif]-->

<!-- Mirrored from seantheme.com/color-admin-v1.7/admin/html/page_with_light_sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 24 Apr 2015 11:01:38 GMT -->
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

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- 	<link href="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" /> -->
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

<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== BEGIN BASE JS ================== -->
<!--   -->
<!-- ================== END BASE JS ================== -->
<!-- <script type='text/javascript' src="http://maps.google.com/maps/api/js?sensor=false"></script> -->

<!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->


<!-- <link rel="stylesheet" href="style.css" type="text/css"> -->
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

		//alert(salesmanId);
		//url : '/NizamEnergyProject/CommissionFilter',

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

</head>

<body>
	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		int foID = Integer.parseInt(request.getParameter("fo_id"));
		int imageId = UserBAL.getImage(bean.getUserId());
		ArrayList<HashMap<String, String>> list = FieldOfficerBAL.getFoSalesman(foID);
		HashMap<String, String> maps = FieldOfficerBAL.getFieldOfficerDetail(foID);

		String fosalary = maps.get("salary");

		String phone = UserBAL.getFormattedPhoneNumber(maps.get("foNumber"));
	%>
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
							<li class="active"><a href="doFieldOfficer.jsp">Field
									Officer</a></li>
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

		<div id="content" class="content">

			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<%-- <span class="image image-responsive image-circle"><img src="ShowImage?id=<%=maps.get("imageId") %>" width="100px" height="100px"/><h2><%=FieldOfficerBAL.getNameById(foID) %></h2></span> --%>
			<!-- 			<h1 class="page-header">Field officer</h1> -->

			<!-- end page-header -->
			<!-- begin row -->
			<%-- <div class="row">
				<div id="customerPanel" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!-- <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand">
									<i class="fa fa-expand"></i>
								</a> -->
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload">
									<i class="fa fa-repeat"></i>
								</a> 
								<!-- <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse">
									<i class="fa fa-minus"></i>
								</a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove">
									<i class="fa fa-times"></i>
								</a> -->
							</div>
						  <h4 class="panel-title">Salesmen</h4>
                        </div>
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
		                        <th>Total Commission</th>
		                        <th>Sales Rate</th>
		                        <th>Recovery Rate</th>
		                      
                             </tr>
                                </thead>
                                <tbody>
							<%for(int i=0;i<list.size();i++){ %>
							<tr class="link" data-link="Test?click=ok&salesman_id=<%=list.get(i).get("salesmanID")%>&salesmanGsm=<%=list.get(i).get("Phone") %>">
							<td><%=list.get(i).get("SalesName")%></td>
							<td><%=list.get(i).get("Phone")%></td>
							<td><%=list.get(i).get("Cnic")%></td>
							<td><%=list.get(i).get("Address")%></td>
							<td><%=list.get(i).get("Salary")%></td>
							<td><%=list.get(i).get("date") %></td>
							<td><%=list.get(i).get("lateCustomer") %></td>
							<td><%=list.get(i).get("Sales")%></td>
							<td></td>
							</tr>
							<%} %>
							</tbody>
							</table>
								</div>
						</div>
						<!-- end panel -->
					</div>
					<!-- end col-10 -->
				
			</div> --%>

			<!-- tab panel row starts -->




			<%-- <div class="row">






				<div class="col-md-12">
					
					<div class="panel panel-default panel-with-tabs"
						data-sortable-id="ui-widget-9">
						<div class="panel-heading">
							<ul id="myTab" class="nav nav-tabs pull-right">
								
								<li class="active"><a href="#home" data-toggle="tab"
									aria-expanded="true"><i class="fa fa-home"></i> <span
										class="hidden-xs">Commission </span></a></li>
								
								<!-- Danish -->

							</ul>
							<h2 class="panel-title">Panel with Tabs</h2>
						</div>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in" id="home" style="margin: -15px">
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px;">
									<%
										HashMap<String,String> map = FieldOfficerBAL.getJoiningDateByFoId(foID);
									%>
									<table>
										<tr>
											<td>
												<input type="date" class="form-control"  id="fromDate" name="fromDate" style="width: 150px;"/> 
											</td>
											<td><b>to</b></td>
											<td> 
												<input type="date" class="form-control" id="endDate" name="endDate" style="width: 150px;"/>
											</td>
											<td>
												<input type="button" value="Filter" onclick="getCommissionByDate(<%=foID %> ,<%=map.get("bt") %>, <%=map.get("ot")%> , <%=map.get("at")%>)" />
											</td>
										</tr>
									</table>

										<!-- 								<div class="row"> -->
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
														int at50W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID,"50 W");
														int at80W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID,"80 W");
														int at100W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID,"100 W");
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
					</div>

				</div>







			</div> --%>






			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!--                                 <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
 -->
							</div>
							<h4 class="panel-title">Field Officer</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<!-- 								<tr> -->
									<!--                                     <th>Name</th> -->

									<!--                                 </tr> -->
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
											<th>Basic Salary</th>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(Math.round(Integer.parseInt(fosalary)))%>
												<b> PKR</b></td>
										</tr>
										<tr>
											<th>Address</th>
											<td><%=maps.get("address")%></td>
										</tr>
										<tr>
											<th>District Officer</th>
											<td><%=maps.get("DistrictOfficer")%></td>
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

										<%--  <tr>
                                	<th>Before Time Commission</th>
									<td><%=maps.get("fobeforetime") %> %</td>                                
                                </tr> --%>

										<tr>
											<th>Monthly Recovery Commission</th>
											<td><%=maps.get("foontime")%> %</td>
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
											<td><%=NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(Integer.parseInt(maps.get("per_sale"))))%><b>
													PKR</b></td>

											<%
												}
											%>

										</tr>

										<tr>
											<th>Field Officer Acc Number</th>

											<%
												if (maps.get("fo_acount_no") == "" || maps.get("fo_acount_no") == null) {
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
										<%--  <tr>
                                	<th>After Time Commission</th>
									<td><%=maps.get("foaftertime") %> %</td>                                
                                </tr> --%>

										<%--  <tr>
                                	<th>Update Profile</th>
                                	<td><a href="updatefo.jsp?foId=<%= maps.get("fo_id") %>" class="label" style="background-color:#3449ee; color:white;font-weight: bold; width:100%" >Edit</a></td>
                                </tr> --%>
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

							<!-- <li><a href="#comission" data-toggle="tab">
								 <span class="hidden-xs m-l-3">Commission<span class="badge badge-inverse m-l-3"></span>
									</span></a></li> -->

							<li><a href="#defaultCustomers" data-toggle="tab"> <span
									class="hidden-xs m-l-3">Default Customers<span
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
								<div class="panel panel-inverse">
									<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
									<div class="panel-body">
										<div class="table-responsive" style="font-size: 13px">
											<table id="data-table"
												class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Name</th>
														<th>Number</th>
														<th>CNIC</th>
														<th>Address</th>
														<th>Salary</th>
														<th>Date of Joining</th>
														<th>Total Commission</th>
														<th>Sales Rate</th>
														<th>Recovery Rate</th>

													</tr>
												</thead>
												<tbody>
													<%
														for (int i = 0; i < list.size(); i++) {
															String number = NumberFormat.getNumberInstance(Locale.US)
																	.format(Double.parseDouble(list.get(i).get("Salary")));
													%>
													<tr class="link"
														data-link="Test?click=vleprofile&salesman_id=<%=list.get(i).get("salesmanID")%>&salesmanGsm=<%=list.get(i).get("Phone")%>">
														<td><%=list.get(i).get("SalesName")%></td>
														<td><%=UserBAL.getFormattedPhoneNumber(list.get(i).get("Phone"))%></td>
														<td><%=list.get(i).get("Cnic")%></td>
														<td><%=list.get(i).get("Address")%></td>
														<td><%=NumberFormat.getNumberInstance(Locale.US)
						.format(Double.parseDouble(list.get(i).get("Salary")))%><b>
																PKR</b></td>
														<td><%=list.get(i).get("date")%></td>
														<td><%=NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(Integer.parseInt(list.get(i).get("lateCustomer"))))%><b>
																PKR</b></td>
														<td><%=NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(list.get(i).get("Sales")))%><b>
																PKR</b></td>
														<td></td>
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
																ArrayList<HashMap<String, String>> listdefaultcustomer = FieldOfficerBAL.getDefaultCustomerDoFo(foID);
																if (listdefaultcustomer.size() != 0) {
																	for (int i = 0; i < listdefaultcustomer.size(); i++) {
																		StringBuilder focell = new StringBuilder(
																				listdefaultcustomer.get(i).get("customer_phone").replace("92", ""));
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

							<%-- <div class="tab-pane fade" id="comission">
<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
										<div class="panel-body">
											<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in" id="home" style="margin: -15px">
								<div class="panel-body">
									<div class="table-responsive" style="font-size: 13px;">
									<%
										HashMap<String,String> map = FieldOfficerBAL.getJoiningDateByFoId(foID);
									%>
									<table>
										<tr>
											<td>
												<input type="date" class="form-control"  id="fromDate" name="fromDate" style="width: 150px;"/> 
											</td>
											<td><b>to</b></td>
											<td> 
												<input type="date" class="form-control" id="endDate" name="endDate" style="width: 150px;"/>
											</td>
											<td>
												<input type="button" value="Filter" onclick="getCommissionByDate(<%=foID %> ,<%=map.get("bt") %>, <%=map.get("ot")%> , <%=map.get("at")%>)" />
											</td>
										</tr>
									</table>

										<!-- 								<div class="row"> -->
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
														int at50W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID,"50 W");
														int at80W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID,"80 W");
														int at100W = TargetsBAL.getAfterTimeForFo(map.get("joiningDate"), currentDate, foID,"100 W");
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
								</div> --%>
							<div class="tab-pane fade" id="recoveries">
								<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
								<div class="panel panel-inverse">
									<!-- 									<div data-scrollbar="true" data-height="480px" style="font-size: 13px;"> -->
									<div class="panel-body">
										<div class="table-responsive" style="font-size: 13px">
											<table id="data-table2"
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
														List<UpComingRevocoveries> upComingRevocoveries = FieldOfficerBAL.upComingRevocoveriesByFOId(foID);

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

	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/js/table-manage-default.demo.min.js"></script>

	<!-- 	<script src="assets/js/apps.min.js"></script> -->
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<!-- 	<script src="assets/plugins/gritter/js/jquery.gritter.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.time.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.resize.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.pie.min.js"></script> -->

	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
	<!-- 	<script src="assets/js/dashboard.min.js"></script> -->
	<!-- 	<script src="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script> -->
	<!-- 	<script src="assets/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script> -->
	<!-- 	<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> -->
	<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
	<!-- <script src="assets/js/map-google.demo.min.js"></script> -->
	<!--             <script src="assets/js/apps.min.js"></script> -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script type='text/javascript'
		src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
	<script type="text/javascript" src="assets/async/dashboard.js"></script>
	<script>
		$(document).ready(function() {
 			App.init();
 			TableManageDefault.init();
		});
	</script>

</body>
</html>