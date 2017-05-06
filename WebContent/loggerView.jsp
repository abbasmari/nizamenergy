<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="bean.UserBean"%>

<%@page import="bean.AlertsLogBean"%>
<%@page import="bal.AlertsLogBAL"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href="assets/icons/favicon.png" />
	<title>Nizam Energy</title>
	
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
	<link href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="assets/css/animate.min.css" rel="stylesheet" />
	<link href="assets/css/style.min.css" rel="stylesheet" />
	<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	
	<link href="assets/plugins/ionicons/css/ionicons.min.css" rel="stylesheet" />
	<link href="assets/plugins/simple-line-icons/simple-line-icons.css" rel="stylesheet" />
	
	<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
	<link href="assets/plugins/DataTables/css/data-table.css" rel="stylesheet" />
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	 
	<!-- ================== END BASE JS ================== -->
	
</head>
<body>


         <%
          UserBean userbean = (UserBean) session.getAttribute("email");
         
         if(userbean==null){
				response.sendRedirect("shsLogin.jsp");
			}else{
         
           // int id=Integer.parseInt(request.getParameter("user"));
            
            
             

        %>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<!-- <div id="page-container" class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar"> -->
	<div id="page-container" class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
			<%@include file="/superAdminHeader.jsp" %>
		<!-- end #header -->
		
<%-- 		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="assets/img/user-13.jpg" alt="" /></a>
						</div>
						<div class="info">
							<%=userbean.getUserName()%>
							<small>Superadmin</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					
					<li class="has-sub">
						<a href="superAdminDashboard.jsp">
						    
						    <i class="fa fa-laptop"></i>
						    <span>Dashboard</span>
					    </a>
						
					</li>
					<li class="has-sub">
						<a href="request.jsp">
							<span class="badge pull-right">10</span>
							<i class="fa fa-inbox"></i> 
							<span>Loan Request</span>
						</a>
						
					</li>
					<li class="has-sub">
						<a href="customer.jsp">
						    
						    <i class="fa fa-suitcase"></i>
						    <span>Customers </span> 
						</a>
						
					</li>
					<li class="has-sub">
						<a href="appliances.jsp">
						   
						    <i class="fa fa-file-o"></i>
						    <span>Appliances</span> 
						</a>
						
					</li>
					<li class="has-sub "><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales Force</span>
					</a>
						<ul class="sub-menu active">
							<li ><a href="DistrictOfficerr">District Officer</a></li>
							<li ><a href="superAdminFO.jsp">Field Officer</a></li>
							<li class="active"><a href="Salesmen">Nizam Dost</a></li>
						</ul></li>
					<li class="has-sub">
						<a href="Loan">
						    
							<i class="fa fa-star"></i> 
							<span>Loan Books</span>
						</a>
						
					</li>
					<li class="has-sub">
					    <a href="Finance">
						    
					        <i class="fa fa-envelope"></i>
					        <span>Payments</span>
					    </a>
						
					</li>
					<li class="has-sub"><a href="SuperAdminNock"> <i
							class="icon-support"></i> <span>Alarms</span>
					</a></li>
					
					<li class="has-sub"><a href="Analytics.jsp"> <i
							class="ion-podium"></i> <span>Analytics</span>
					</a></li>
					
			        <!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
			        <!-- end sidebar minify button -->
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar --> --%>
		
		<!-- begin #content -->
	<div id="content" class="content">
			<!-- begin breadcrumb -->
			<!-- <ol class="breadcrumb pull-right">
				<li><a href="superAdminDashboard.jsp">Home</a></li>
				<li class="active">Logger View</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Logger View</h1>
			<!-- end page-header -->
			

            
            <!-- tab panel row starts -->
			
			
			
			
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default panel-with-tabs"
						data-sortable-id="ui-widget-9">
						<div class="panel-heading">
							<ul id="myTab" class="nav nav-tabs pull-right">
								<li class="active"><a href="#defaultprofile" data-toggle="tab"
									aria-expanded="true"><i class="fa fa-user"></i> <span
										class="hidden-xs">INFO</span></a></li>
								
								
										<!-- Danish -->

							</ul>
							<h2 class="panel-title">Panel with Tabs</h2>
						</div>
						<div id="myTabContent" class="tab-content">

							<div class="tab-pane fade active in" id="defaultprofile"
								style="margin: -15px">

								<div class="panel panel-inverse">

									<div class="panel-body">

										<a href="error.log">View Errors</a>
										<div class="table-responsive" style="font-size: 13px;">



											<div id="" style="padding-top: 12px;">
												<div id="data-table_wrapper"
													class="dataTables_wrapper no-footer">
											
													
													<table id="data-table"
														class="table table-hover table-bordered">
														<thead>
															<tr>
																<th>Date</th>
																<th>Screen</th>
																<th>User Name</th>
																<th>User ID</th>
																<th>User IP</th>
															</tr>
														</thead>
														<tbody>
															
															<% 
								                            	//File file = new File(getServletContext().getRealPath("")+"/src/info.log");
															File file = new File(getServletContext().getRealPath("")+"/info.log");
																FileReader fileReader = new FileReader(file);
																BufferedReader bufferedReader = new BufferedReader(fileReader);
																String line;
																while ((line = bufferedReader.readLine()) != null) {
																	if(line.contains("INFO")){
																	String arr[] = line.split(" ");																	
																%>
																<tr>
																	<td><%=arr[0] %></td>
																	<td><%=arr[3] %></td>
																	<td><%=arr[8] %></td>
																	<td><%=arr[12] %></td>
																	<td><%=arr[16] %></td>
																</tr>	
															<% 	}
																}
																fileReader.close();
															%>

														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
							
							            
            
		</div>
		<!-- end #content -->
		
       <%} %>
		
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
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
		$(document).ready(function() {
			App.init();
			TableManageColVis.init();
		});
	</script>
	
</body>


</html>
