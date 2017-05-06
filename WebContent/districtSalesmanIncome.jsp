<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="bean.AlertsForNumber" %>
<%@page import="bean.NumberOfMsgFrom" %>
<%@page import="bean.ShowMsgAdminBean" %>
<%@page import="bean.CustomerInfoBean" %>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bal.mapBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.Formatter"%>
<%@page import="java.text.Format"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="bal.RecoveryBAL"%>
<%@page import="bean.RecoveryBean"%>
<%@page import="bean.SalesManBean"%>
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
		UserBean bean = (UserBean) session.getAttribute("email");
	
		if(bean==null){
			response.sendRedirect("SolarHomeSystemLogin");
		}else{
	
			
			
		SalesManBean salesman = (SalesManBean) request.getAttribute("bean");
	    int saleman_id = (Integer) request.getAttribute("salesman_id");
	  
        int countRequests = EligibilityBAL.getUnseenRequests();
	    String days= (String)request.getAttribute("time");
	    System.out.print("salesman_id " + salesman);
	    Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	
	    int month = cal.get(Calendar.MONTH);
	    Date newDate = new Date();
	
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(Calendar.MONTH, -month);
	    newDate = c.getTime();
    %>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar">
		<!-- begin #header -->
			<%@include file="/superAdminHeader.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
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
							<%=bean.getUserName()%>
							<small>Superadmin</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					
					<li class="has-sub">
						<a href="SuperAdminDashboard">
						    
						    <i class="fa fa-laptop"></i>
						    <span>Dashboard</span>
					    </a>
						
					</li>
					<li class="has-sub">
						<a href="Request">
							<span class="badge pull-right" id="unseen_loan_request_count" style="font-size: 13px;"></span>
							<i class="fa fa-inbox"></i> 
							<span>Loan Request</span>
						</a>
						
					</li>
					<li class="has-sub">
						<a href="Customer">
						    
						    <i class="fa fa-suitcase"></i>
						    <span>Customers </span> 
						</a>
						
					</li>
					<li class="has-sub">
						<a href="Appliances">
						   
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
							<li ><a href="Salesmen">Nizam Dost</a></li>
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
					
					<li class="has-sub"><a href="Analytics"> <i
							class="ion-podium"></i> <span>Analytics</span>
					</a></li>
					
					
					<li class="has-sub"><a href="commissionPayout.jsp"> <i
							class="ion-podium"></i> <span>Commission</span>
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
		<!-- end #sidebar -->
		
		<!-- begin #content -->
<div id="content" class="content">
			<!-- begin breadcrumb -->
			<!-- <ol class="breadcrumb pull-right">
				<li><a href="SuperAdminDashboard">Home</a></li>
				<li><a href="Salesmen">Salesman</a></li>
				<li class="active">View</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Salesman View</h1>
			<!-- end page-header -->
			
			<!-- begin row -->
			<div class="row" style="padding-top: 2%">
			   
			   
			   <%

                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

                    //                                        for (int i = 0; i < 12; i++) {
                    //                                           
                    //                                            Date date12 = format1.parse(formatted);
                    //                                            Date date1 = format1.parse(formatted1);
                    //                                        RecoveryBean bean = RecoveryBAL.getRecovery(saleman_id, formatted1, formatted);
                    //                                       
                        Calendar cal12 = Calendar.getInstance();
                        Calendar Current = Calendar.getInstance();
                        Date date3 = new Date();
                        Current.setTime(date3);
                        cal12.setTime(salesman.getDatejoin());
                        int month12 = cal12.get(Calendar.MONTH);
                        Calendar c3 = Calendar.getInstance();
                    //                c3.setTime(salesman.getDatejoin());
                    //                 c3.add(Calendar.MONTH,1);
                        Calendar c4 = Calendar.getInstance();
                        c4.setTime(salesman.getDatejoin());
                        c4.add(Calendar.MONTH, -1);
                        Date result = c4.getTime();
                        Date news = Current.getTime();
                        int m1 = c4.get(Calendar.YEAR) * 12 + c4.get(Calendar.MONTH);
                        int m2 = Current.get(Calendar.YEAR) * 12 + Current.get(Calendar.MONTH);
                        int total = m2 - m1 + 1;

                        int am = mapBAL.differenceInMonths(result, news);

                        for (int i = 1; i <= am; i++) {
                            c.add(Calendar.MONTH, i);
                            Calendar c1 = Calendar.getInstance();
                            Calendar c2 = Calendar.getInstance();
                            Calendar c5 = Calendar.getInstance();
                            c5.setTime(result);
                            c1.setTime(salesman.getDatejoin());
                            c2.setTime(salesman.getDatejoin());
                            c3.setTime(salesman.getDatejoin());

                            c1.add(Calendar.MONTH, i - 1);
                            c2.add(Calendar.MONTH, i - 2);
                            c3.add(Calendar.MONTH, i);
                            c5.add(Calendar.MONTH, i);
                            String formatted1 = format1.format(c3.getTime());
                            String formatted = format1.format(c5.getTime());

                            ArrayList<RecoveryBean> list = RecoveryBAL.getRecoveryBean(saleman_id, formatted, formatted1);
                            ArrayList<RecoveryBean> list1 = RecoveryBAL.getRecoveryCommissionBean(saleman_id, formatted, formatted1);
                            System.out.print(list1.get(0).getTotal_customers());
                            System.out.println("from jsp");
                            Formatter fmt = new Formatter();
                            Formatter fmt1 = new Formatter();
                            fmt1 = new Formatter();
                            fmt = new Formatter();
                            fmt.format(" %1$tB %1$td, %1$tY\n", c3);

                            fmt1.format(" %1$tB %1$td, %1$tY\n", c5);

                    %>
			   
			   
			    <!-- begin col-8 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"><%= fmt1 + "<b> To </b>" + fmt%></h4>
                        </div>
                        
                        
                        <div class="panel-body">
                            <div class="table-responsive" style="font-size: 13px">
                                <table id="data-table" class="table table-bordered">
                                    <thead>
                                        <tr>
		                                    <th>Appliance Name</th>
		                                    <th>Quantity Sold</th>
		                                    <th>Commission/Unit</th>
		                                    <th>Total Commission</th>
		                                    <th>Customer On Loan</th>
		                                    <th>Defaulted Customers</th>
		                                    <th>Paid Defaulter</th>
		                                    <th>Recovered Amount</th>
		                                    <th>Commission Earned</th>
		                                    <th><b>Total Amount Earned</b></th>
		                                </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <% 
                                     int a = 0;
                                     double totalcommission = 0;
	                                 for (int j = 0; j < list.size(); j++) {
	                                 int a1 = 0;
                                     a = a + list.get(j).getInstallment_amount();
                                    %>
                                <tr>
                                    <td> <%=list.get(j).getApp_name()%> </td>
                                    <td> <%=list.get(j).getInstallment_amount()%> </td>
                                    <td><%=list.get(j).getRate()%></td>
                                    <td><%=list.get(j).getInstallment_amount() * list.get(j).getRate()%></td>
                                    <%  a = (int) Math.round(totalcommission + list.get(j).getInstallment_amount() * list.get(j).getRate()) + a; 
                                    	}
                                        for (int k = 0; k < list1.size(); k++) {
                                        
                                      totalcommission = list1.get(k).getRecored_amount() * .03;
                                    %>
                                    <td> <%=list1.get(k).getTotal_customers()%></td>
                                    <td><%=list1.get(k).getLate_customer() %></td>
                                    <td><%=list1.get(k).getPaid_late()%></td>
                                    <td><%=list1.get(k).getRecored_amount()%>
                                    </td>

                                    <td><%=(int) Math.round(list1.get(k).getRecored_amount() * .03)%></td>

                                    <td><%= (int) Math.round(list1.get(k).getRecored_amount() * .03)%></td>
                                    <% } %>

                                </tr>
                                    
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <%}%>
                <!-- end col-8 -->
                
                
                
                
                
                
                
            </div>
            <!-- end row -->
            
            
            
            
            <!-- begin row -->
			<div class="row" >
			   
			     <!-- begin col-6 -->
			    <div class="col-md-6">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Salesman Details</h4>
                        </div>
                        
                        <div class="panel-body">
                            <div class="table-responsive" style="font-size: 13px">
                                <table class="table">
                                    <tr>
                                        <th>Salesman Name</th> 
                                        <td><%= salesman.getName()%></td>
                                    </tr>

                                    <tr>
                                        <th>Salesman CNIC</th>
                                        <td><%= salesman.getCnic()%></td>
                                    </tr>
                                    <tr>
                                        <th>Phone No</th>
                                        <td><%= salesman.getPhone_number()%></td>
                                    </tr>
                                    <tr>
                                        <th>Total Customers</th>
                                        <td><%= salesman.getTotal_customer()%></td>

                                    </tr>
                                    <tr>
                                        <th> Customers On Cash</th>
                                        <td><%= salesman.getCustomer_on_cash()%></td>

                                    </tr>
                                    <tr>
                                        <th> Customers On Installments</th>
                                        <td><%= salesman.getCustomer_on_installments()%></td>

                                    </tr>
                                    <tr>
                                        <th>Basic Salary</th>
                                        <td><%=Math.round(salesman.getSallery())%></td>

                                    </tr>
                                    <tr>
                                        <th>Date Of Joining</th>
                                        <td><%= salesman.getDatejoin()%></td>

                                    </tr>
                                    <tr>
                                        <th>Address</th>
                                        <td><%= salesman.getAddress()%></td>

                                    </tr>
                                    
                                    <tr>
                                        <th>Commission</th>
                                        <td>The Commission will issue after <b><%= days%></b> days </td>

                                    </tr>
                                    
                                    <tr>
                                        <th>Employee Status</th>
                                        <td><%= salesman.getEmployee_status()%></td>

                                    </tr>
                                    <% 
                                    }	// session else closed
                    				%>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-6 -->
                
                
                
                
                
            </div>
            <!-- end row -->
            
            
            
            
            
            
            
		</div>
		<!-- end #content -->
		
       
		
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
// 			TableManageColVis.init();
		});
	</script>
	
</body>


</html>
