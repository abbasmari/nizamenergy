
<%@page import="java.util.HashMap"%>
<%@page import="bal.FieldOfficerBAL"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bal.mapBAL"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="java.util.Locale"%>
<%@page import="bean.MapBean"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.MapBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.DistrictOfficerBean"%>
<%@page import="bean.CustomerBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="bal.DistrictBAL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
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


<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->
<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->
<!-- 	<script type='text/javascript' src="http://maps.google.com/maps/api/js?sensor=false"></script> -->




<%
	DistrictOfficerBean do_officer = (DistrictOfficerBean) request.getAttribute("bean");
%>



</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");

		if (bean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {

			//DistrictOfficerBean do_officer = (DistrictOfficerBean) request.getAttribute("bean");

			Integer dist_id = (Integer) request.getAttribute("district");
			ArrayList<SalesManBean> Salesmanlist = SalesmanBAL.getDistrictSalesman(dist_id);
			ArrayList<HashMap<String, String>> list = FieldOfficerBAL.getFieldOfficers(dist_id);

			System.out.println("########################################" + list);

			System.out.print("id: " + dist_id);
			System.out.print("list " + list);
			int imageId = UserBAL.getImage(dist_id);
			System.out.println("##################################################" + imageId);

			String number2 = UserBAL.getFormattedPhoneNumber(do_officer.getPhone());

	%>


	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

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
							<%=bean.getUserName()%>
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
			<!-- <ol class="breadcrumb pull-right f-s-12">
				<li><a href="SuperAdminDashboard">Dashboard</a></li>
				<li><a href="DistrictOfficerr">District Sales</a></li>
				<li class="active">District Officer Profile</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<%-- <span class="image image-responsive image-circle">
			<% if(imageId != 0 ){ %>
			<img src="ShowImage?id=<%=imageId%>" width="100px" height="100px">
			<% }else { %>
			<img src="assets/img/cross.png" width="100px" height="100px">
			<% } %>
			</span> --%>
			<span class="page-header" style="color: black;"><%=do_officer.getDo_name()%></span>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-6 -->
				<div class="col-md-6">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!--  <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a> -->
							</div>
							<h4 class="panel-title">District Officer</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<!-- 								<tr> -->
									<!--                                     <th>Name</th> -->
									<%--                                     <td> <%= do_officer.getDo_name()%> </td> --%>
									<!--                                 </tr> -->
									<tr>
										<th>CNIC</th>
										<td><%=do_officer.getCnic()%></td>
									</tr>
									<tr>
										<th>Phone Number</th>
										<td><%=number2%></td>
									</tr>
									<tr>
										<th>Basic Salary</th>
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(Math.round(do_officer.getSallery()))%><b>
												PKR</b></td>
									</tr>
									<tr>
										<th>Address</th>
										<td><%=do_officer.getAddress()%></td>
									</tr>
									<tr>
										<th>District</th>
										<td><%=do_officer.getDistrict_name()%></td>
									</tr>

									<tr>
										<th>Date Of Birth</th>
										<td><%=do_officer.getDateofbirth()%></td>
									</tr>

									<tr>
										<th>Date Of Joining</th>
										<td><%=do_officer.getDateofjoining()%></td>
									</tr>

									<tr>
										<th>Email</th>
										<td><%=do_officer.getUseremail()%></td>
									</tr>

								</table>
							</div>

						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-6 -->

				<!-- begin col-6 -->

				<!-- end col-6 -->

			</div>
			<!-- end row -->


			<!-- start row -->

			<div class="row">

				<div class="col-md-12">

					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!-- <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a> -->
							</div>
							<h4 class="panel-title">Field Officers</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="data-table" class="table table-hover table-bordered">
									<thead>
										<tr>

											<th>Name</th>
											<th>Number</th>
											<th>CNIC</th>
											<th>Address</th>
											<th>Salary</th>
											<th>Date of Joining</th>
											<th>Update Profile</th>
											<!-- 		                        <th>Total Commission</th> -->
											<!-- 		                        <th>Sales Rate</th> -->
											<!-- 		                        <th>Recovery Rate</th> -->

										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < list.size(); i++) {
													// int fo_id = Integer.parseInt(list.get(i).get("fo_priamary_phone"));
													//double recovery = SalesmanBAL.totalRecovery(id);
													//double commission = SalesmanBAL.totalRecovery(id);
													//double totalCommission = //recovery + commission;
										%>
										<tr class="link"
											data-link="fieldOfficerProfile.jsp?fo_id=<%=list.get(i).get("foid")%>">

											<td><%=list.get(i).get("foName")%></td>
											<td>
												<%-- <% String foNumber = list.get(i).get("foPhone");
        				String foConcat = "+"+number;
        				String foNumber2 =  String.format("(%s) %s-%s", foConcat.substring(0, 3), foConcat.substring(3, 6), 
        						foConcat.substring(6, 13)); %> --%> <%
 	String foNumber2 = UserBAL.getFormattedPhoneNumber(list.get(i).get("foPhone"));
 %> <%=foNumber2%>
											</td>
											<td><%=list.get(i).get("cnic")%></td>
											<td><%=list.get(i).get("address")%></td>
											<td><%=NumberFormat.getNumberInstance(Locale.US)
							.format(Integer.parseInt(list.get(i).get("salary")))%><b> PKR</b></td>
											<td><%=list.get(i).get("joiningDate")%></td>
											<td><a
												href="updatefo.jsp?foId=<%=list.get(i).get("foid")%>"
												class="label"
												style="background-color: #3449ee; color: white; font-weight: bold; width: 100%">Edit</a></td>
											<%--                         <td><%= list.get(i).get("")%></td> --%>
											<%--                         <td><%= list.get(i).get("")%></td> --%>
											<%--                         <% if (totalCommission != 0.0) {%> --%>
											<%--                         <td><%= Math.round(totalCommission)%></td> --%>
											<%--                         <% } else { %> --%>
											<!--                         <td> </td> -->
											<%--                         <% }%> --%>
											<!--                         <td> -->
											<!--                         </td> -->
											<!--                         <td></td> -->
											<!--                         <td></td> -->
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



				</div>
			</div>

			<!-- end row -->

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
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script>
		$(document).ready(function() {
			App.init();
			//TableManageColVis.init();
		});
	</script>

</body>


</html>
