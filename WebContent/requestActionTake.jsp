<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="bean.AlertsForNumber" %>
<%@page import="bean.NumberOfMsgFrom" %>
<%@page import="bean.ShowMsgAdminBean" %>
<%@page import="bean.CustomerInfoBean" %>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.ApplianceBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.ApplianceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Suggestion"%>


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
	
	<link href="assets/css/mystyle.css" rel="stylesheet">
	
	<!-- ================== BEGIN BASE JS ================== -->
	 
	<!-- ================== END BASE JS ================== -->
	
	<script type="text/javascript">

	
	function autoResize(id){
	    var newheight;
	    var newwidth;

	    if(document.getElementById){
	        newheight = document.getElementById(id).contentWindow.document .body.scrollHeight;
	        newwidth = document.getElementById(id).contentWindow.document .body.scrollWidth;
	    }

	    document.getElementById(id).height = (newheight) + "px";
	    document.getElementById(id).width = (newwidth) + "px";
	}
	
	
	
			var customer;
			var appliance;
			
			function getCheki(checkboxName) {
			    var checkboxes = document.querySelectorAll('input[name="' + checkboxName + '"]:checked'), values = [];
			    Array.prototype.forEach.call(checkboxes, function(el) {
			        values.push(el.value);
			    });
				document.getElementById('updateURLsuggest').href = "StatusRejected?click=suggest&customerId="+customer+"&applianceId="+appliance+"&appliance="+values+"&scheme="+36+"";
			}
	
		function setMoodValueDelete(applianceID, customerID){
			customer = customerID;
			appliance = applianceID;
			document.getElementById('updateURLreject').href = "StatusRejected?click=ok&customerId="+customerID+"&applianceId="+applianceID+"";
// 			document.getElementById('updateURLsuggest').href = "StatusRejected?click=suggest&customerId="+customerID+"&applianceId="+applianceID+"";
			
		}
		function setMoodValue(appliance,customer,applianceValue,customerValue){
			//alert(appliance);
			//alert(customer);
			document.getElementById("applianceId").innerHTML = appliance;
			document.getElementById("customerId").innerHTML = customer;
			
			document.getElementById('updateURL').href = "StatusUpdate?applianceId="+applianceValue+"&customerId="+customerValue+"";
			
		}
		
		var textArea;
		var customerIDInstance;
		var salesmanIDInstance;
		function setMoodValueInfo(customerID,salesmanID){
			customerIDInstance = customerID;
			salesmanIDInstance = salesmanID;
// 			document.getElementById("applianceId").innerHTML = appliance;
			textArea = document.getElementById("customerText").value;
			
			
			
			document.getElementById('updateURLinfo').href = "MoreInfo?id="+customerID+"&doId="+salesmanID+"&click=message&customerText="+textArea;
			
		}
		function setTextAreaValue(){
			textArea = document.getElementById("customerText").value;
/* 			document.getElementById('updateURLinfo').href = "MoreInfo?id="+customerIDInstance+"&doId="+salesmanIDInstance+"&click=message&customerText="+textArea;
 */		}
		
		
		function goBack() {
			alert("bingooo");
		    window.location.hash = window.location.lasthash[window.location.lasthash.length-1];
		    //blah blah blah
		    window.location.lasthash.pop();
		}
		
		
	

	</script>

	
	
</head>
<body>

<%
                UserBean bean = (UserBean) session.getAttribute("email");

				if(bean==null){
					response.sendRedirect("SolarHomeSystemLogin");
				}else{

				String dateFixed;
				ApplianceBean appBean = (ApplianceBean) request.getAttribute("beanData");
				CustomerInfoBean customerBean = (CustomerInfoBean) request.getAttribute("beanDataCustomer");
				
				
			    int countRequests = EligibilityBAL.getUnseenRequests();
			    
			    
				
				
				
// 			    if(customerBean.getCreatedOn().contains(".")){
// 				    String date[] = customerBean.getCreatedOn().split(".");
// 				    dateFixed = date[0];
// 			    }else{
// 			    	dateFixed = customerBean.getCreatedOn();
// 			    }
// 			    dateFixed = customerBean.getCreatedOn();
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
							<a href="javascript:;"><img src="assets/img/user-13.jpg"
								alt="" /></a>
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
					<li class="active">
						<a href="Request">
							<span class="badge pull-right"><%= countRequests %></span>
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
				<li><a href="Request">Loan Request</a></li>
				<li class="active">View</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Loan Request View</h1>
			<!-- end page-header -->
			
			<div class="panel-body">
			
			
			<a href="#modal-alert-delete" style="float: right;"
			onclick="setMoodValueDelete('<%=appBean.getApplianceId()%>','<%=customerBean.getCustomerId()%>')"
			class="btn btn-danger m-r-5 m-b-5"
			data-toggle="modal"> <span>Reject</span></a>
			
			
			
			
			
			<a href="#modal-alert" style="float: right;"
			onclick="setMoodValue('<%=appBean.getApplianceName()%>','<%=customerBean.getCustomerName() %>',<%=appBean.getApplianceId()%>,<%=customerBean.getCustomerId() %> )"
			class="btn btn-success m-r-5 m-b-5"
			data-toggle="modal"><span>Accept</span></a>
			
			</div>


<!-- 			<button type="button" class="btn btn-danger m-r-5 m-b-5" style="float: right;">Reject</button> -->
			
			<!-- begin row -->
			<div class="row">
			   
			    <!-- begin col-6 -->
			    <div class="col-md-6 ">
			        <!-- begin panel -->
                    <div class="panel panel-inverse" >
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Customer Detail</h4>
                        </div>
                        <div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
                                    <th>Name</th>
                                     <td> <%= customerBean.getCustomerName()%></td>
                                    </tr>
                                    <tr>
                                    <th>CNIC</th>
                                    <td> <%= customerBean.getCnicNo()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Address</th>
                                    <td> <%= customerBean.getAddress()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>District</th>
                                     <td> <%= customerBean.getDistrict()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Family Size</th>
                                    <td><%= customerBean.getFamilySize()%> </td>
                                    </tr>
                                    
                                    
                                    <tr>
                                    <th>Number</th>
                                    <td> <%= customerBean.getGsmNumber()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Monthly Income</th>
                                    <td> <%= customerBean.getMonthlyIncome()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Family Income</th>
                                     <td> <%= (customerBean.getFamilyIncome())%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Payment Type</th>
                                    <td> <%= customerBean.getPaymentMethod()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Created On</th>
                                    <td> <%= customerBean.getCreatedOn()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Status</th>
                                     <% 
									 int status = customerBean.getStatus();
									 %>
									
									<td> 
                                     
		                                     
		                            <%  if (status == 0) { %>
		                            <span class="label" style="background-color: #3498db">Applied</span> 
		                         
		                            <% } else if (status == 1) { %>
		                            <span class="label" style="background-color: #1abc9c">Accepted</span>
		                        
		                            <% } else if (status == 2) { %>
		                            <span class="label label-danger" >Rejected</span> 
		                          
		                            <% } else if (status == 3) { %>
		                            <span class="label label-default">Not Interested</span> 
		                             
		                            
		                            <% } else if (status == 4) { %>
		                            <span class="label label-danger"> Rejected</span> 
		                            <% }%>
		                            </td>
		                            </tr>
                                    
                                    <tr>
                                    <th>Occupation</th> 
                                    <td> <%= customerBean.getOccupation() %> </td>                                  
                                 
                                </tr>
                                
                             
								</table>
							</div>

						</div>
					</div>
                    <!-- end panel -->
			    </div>
                <!-- end col-6 -->
                
                
                <!-- begin col-6 -->
			    <div class="col-md-6 ">
			        <!-- begin panel -->
                    <div class="panel panel-inverse" >
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Appliance Detail</h4>
                        </div>
                        <div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
                                    <th>Id</th>
                                    <td> <%= appBean.getApplianceId()%></td>
                                    </tr>
                                    
                                    <tr>
                                    <th>GSM Number</th>
                                    <td> <%= appBean.getApplianceGsmNo()%> </td>
                                    </tr>
                                    
                                    
                                    <tr>
                                    <th>Name</th>
                                    <td> <%= appBean.getApplianceName()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Product Number</th>
                                    <td> <%= appBean.getProductId()%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Price</th>
                                    <td> Rs. <%= Math.round(appBean.getPrice())%> </td>
                                    </tr>
                                    
                                    <tr>
                                    <th>Salesman</th>
                                    <td> <%= appBean.getSalesmanName()%> </td>
                                    </tr>
                                	
								</table>
							</div>

						</div>
					</div>
					
                    <!-- end panel -->
                    <!-- chat box -->
					<div class="panel panel-inverse" >
						<div class="panel-heading">
							<h4 class="panel-title">
								Messages 
							</h4>
						</div>
						<div class="panel-body bg-silver">
							<div data-scrollbar="true" data-height="225px">
								<ul class="chats" id="chatBox"
									data-receiver="<%= request.getAttribute("doId")%>"
									data-sender="<%= bean.getUserId()%>"
									data-receivername="<%= appBean.getSalesmanName()%>"
									data-sendername="<%= bean.getUserName()%>"
									data-customerid="<%= customerBean.getCustomerId() %>">
								</ul>
							</div>
						</div>
						<div class="panel-footer">
							<!-- <form name="send_message_form" data-id="message-form"> -->
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="message"
										placeholder="Enter your message here." autocomplete="off"> <span
										class="input-group-btn">
										<button class="btn btn-primary btn-sm" id="send-message" type="button">Send</button>
									</span>
								</div>
							<!-- </form> -->
						</div>
					</div>
					<%
					}	// session else closed
					%>
					<!-- end chat box -->
				</div>
                <!-- end col-6 -->
                
            </div>
            <!-- end row -->
		</div>
		<!-- end #content -->
		
       <!-- profile modal -->
				<div class="modal fade" id="profile-modal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Modal Dialog</h4>
							</div>
							<div class="modal-body"></div>
							<div class="modal-footer">
								<a href="javascript:;" class="btn btn-sm btn-white"
									data-dismiss="modal">Close</a> <a href="javascript:;"
									class="btn btn-sm btn-success">Action</a>
							</div>
						</div>
					</div>
				</div>


				<!-- accept request model -->
				<div class="modal fade" id="modal-alert">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header success">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Accept Request</h4>
							</div>
							<div class="modal-body">
								<div class="alert m-b-0">
									<input type="hidden" id="applianceId" />
									<p>
										Are you sure you want to accept loan request of <i
											id="customerId"> customer </i> ?
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
				<!-- accept request model end -->



				<!-- delete request model -->
				<div class="modal fade" id="modal-alert-delete">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header danger">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title">Suggest / Delete Request</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<h4 class="m-b-0 p-b-15 m-t-0 underline">Appliance</h4>
									<div class="p-15">
										<label class="checkbox-inline"> <input type="checkbox"
											name="appliance" value="6"
											onchange="(getCheki('appliance'));"> 6 Watt
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="appliance" value="30"
											onchange="(getCheki('appliance'));"> 30 Watt
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="appliance" value="60"
											onchange="(getCheki('appliance'));"> 60 Watt
										</label>
									</div>


									<!--                                    <br/> -->
									<!--                                    <br/> -->
									<!--                                     <select class="form-control"> -->
									<!--                                             <option value="" selected="" >-- Select Loan Scheme --</option> -->
									<!--                                             <option value="12">Scheme 12</option> -->
									<!--                                             <option value="18">Scheme 18</option> -->
									<!--                                             <option value="24">Scheme 24</option> -->
									<!--                                         </select> -->

								</div>
							</div>
							<div class="modal-footer">
								<a href="javascript:;" class="btn btn-sm btn-white"
									data-dismiss="modal">Close</a> <a id="updateURLsuggest"
									class="btn btn-sm btn-success">Suggest</a> <a
									id="updateURLreject" class="btn btn-sm btn-danger">Reject</a>
							</div>
						</div>
					</div>
				</div>
				<!-- delete request model end -->
       
		
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
	<script type="text/javascript" src="assets/js/SaDoChat.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script>
	
	jQuery(document).ready(function($) {

		  if (window.history && window.history.pushState) {

// 			  window.history.pushState(null, null, 'Request');

		    $(window).on('popstate', function() {
// 		      alert('Back button was pressed.');
			  
		      window.location.href = 'Request';
		    });

		  }
		});
	
	
		$(document).ready(function() {
			App.init();
			// 			TableManageColVis.init();
			
		});
	</script>
	
    
</body>


</html>
