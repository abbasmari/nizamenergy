<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>

<%@page import="bean.ServiceOperationBean"%>
<%@page import="bean.DistrictOfficerList"%>
<%@page import="control.DistrictOfficer"%>
<%@page import="bean.DistrictOfficerBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.NumberOfApplianceStatusMessages"%>


<!DOCTYPE html >
<html>
<head>


<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet" />


</head>
<body>
	<%
		UserBean sessionBean = (UserBean) session.getAttribute("email");
		if (sessionBean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			String telenor_message = null;
			String mobilink_message = null;
			if (session.getAttribute("mobilink") != null) {
				boolean mobilink = (Boolean) session.getAttribute("mobilink");

				if (mobilink == true) {
					mobilink_message = "Connected";
				} else {
					mobilink_message = "Disconnected";
				}
			}

			if (session.getAttribute("telenor") != null) {
				boolean telenor = (Boolean) session.getAttribute("telenor");

				if (telenor == true) {
					telenor_message = "Connected";
				} else {
					telenor_message = "Disconnected";
				}
			}

			// 				System.out.println("User Name: "+sessionBean.getUserName());
			// 				int count = CustomerBAL.getUnseenCountAlertsForAdmin();
			// 				ArrayList<NumberOfApplianceStatusMessages> alertnum = CustomerBAL.getUnseenMessagesNumberApplianceStatus();
			// 				ArrayList<ShowMsgAdminBean> showmsg = SaDoChatBAL.getUnseenMessagesForAdmin();
			// 				ArrayList<NumberOfMsgFrom> msg_num = CustomerBAL.getUnseenMessagesNumberForAdmin();
			// 				int messagesNotifications = SaDoChatBAL.countDoChatNotifications();
			// 				int countmsg = CustomerBAL.getUnseenCountMessagesForAdmin(); 
			// 				ArrayList<DistrictOfficerList> districtOfficerList = CustomerBAL.getDistrictOfficerList();
			// 				ArrayList<ServiceOperationBean> socList = CustomerBAL.getSOCList();
			// 				int countOfUserNotification = SaDoChatBAL.doSocChatNotifications();
		}
	%>


	<div id="header" class="header navbar navbar-inverse navbar-fixed-top"
		data-userid="<%=sessionBean.getUserId()%>"
		data-districtid="<%=sessionBean.getUser_district()%>">
		<!-- begin container-fluid -->
		<div class="container-fluid">
			<!-- begin mobile sidebar expand / collapse button -->
			<div class="navbar-header navbar-header-without-bg">
				<a href="DistrictOfficerDashboard" class="navbar-brand"> <img
					src="assets/icons/logo.png"
					style="margin-top: -15px; margin-left: -8px;">
				</a>
				<button type="button" class="navbar-toggle"
					data-click="sidebar-toggled">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<!-- 			<span style="color:white;" id = "timer">00:00</span> -->
			<!-- end mobile sidebar expand / collapse button -->



			<!-- begin header navigation right -->
			<ul class="nav navbar-nav navbar-right">

				<!-- appliance alerts -->
				<li class="dropdown" id="appliance_messages_do"><a
					href="javascript:;" data-toggle="dropdown"
					class="dropdown-toggle f-s-14"> <i class="fa fa-bell-o"></i> <span
						class="label"></span>
				</a>
					<ul
						class="dropdown-menu media-list pull-right animated fadeInDown width-md">
						<li class="dropdown-header bg-silver">Appliance Alarms</li>
						<li class="media" id="no_message"><a href="javascript:;">
								<div class="media-body">
									<h6 class="media-heading">No Alarm</h6>
								</div>
						</a></li>

						<li class="dropdown-footer text-center"><a
							href="javascript:;">View more</a></li>
					</ul></li>

				<li class="dropdown navbar-user"><a href="javascript:;"
					data-toggle="dropdown" class="dropdown-toggle"> <i
						class="fa fa-bars"></i>
				</a>
					<ul class="dropdown-menu animated fadeInLeft">
						<li class="arrow"></li>
						<li><a href="DoForm">Add Team</a></li>
						<li><a href="ArchivedCustomers.jsp">Archived Customers <label
								class="label label-warning"></label></a></li>
						<li><a href="CustomerFormReEx.jsp">Add Customer</a></li>
						<li><a href="CashCustomer.jsp">Add Cash Customer</a></li>
						<li class="arrow">
						<li class="divider"></li>
						<li><a href="LoginServlet?click=logout">Log Out</a></li>
					</ul></li>
			</ul>

		</div>
		<!-- end container-fluid -->
	</div>

	<!--  	================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>

	<!-- ================== END BASE JS ================== -->

	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/async/doHeader.js"></script>
	<script src="assets/async/chat.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 	    	   updateData();
		});
	</script>



</body>
</html>




