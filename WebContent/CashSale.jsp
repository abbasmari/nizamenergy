<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.doBeansalesman"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.FinanceBAL"%>
<%@page import="bean.FinanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

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

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");

		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {

			ArrayList<FinanceBean> list = FinanceBAL.getPayments();
	%>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
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
					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Reports</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="reportAnalytics.jsp">Late/Defaulter Report</a></li>
							<li><a href="futureLoanBooks.jsp">Future loan book</a></li>
							<li><a href="SaReports.jsp">Reports</a></li>
						</ul></li>
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

			<h1 class="page-header" id="">Payments</h1>
			<h4 class="" id="curdate"></h4>
			<!-- 		<h1 id="curdate"></h1> -->
			<!-- begin row -->


			<div class="row" style="padding-top: 2%">

				<!-- begin col-6 -->
				<div class="col-md-5">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Payment Highlights</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="" class="table table-hover table-bordered">
									<thead>
										<tr>
											<th>Total Amount</th>
											<td id="total_amount" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total Cash Sale</th>
											<td id="total_sale" style="padding: 12px 50px">0</td>
										</tr>

										<tr>
											<th style="color: red">Total Net Income</th>
											<td id="total_net_income"
												style="padding: 12px 50px; color: red">0</td>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>
							</div>
						</div>
					</div>

					<!-- end panel -->
				</div>
				<div class="col-md-5">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Commission Payout Highlights</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="" class="table table-hover table-bordered">
									<thead>
										<tr>
											<th>Total FO payout</th>
											<td id="total_fo_amount" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total ND payout</th>
											<td id="total_nd_amount" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total payout</th>
											<td id="total_commission" style="padding: 12px 50px">0</td>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>
							</div>
						</div>
					</div>

					<!-- end panel -->
				</div>
				<!-- end col-10 -->
			</div>
			<!-- end row -->

			<!-- begin row -->


			<div class="row" style="padding-top: 2%">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Cash Sale</h4>
						</div>

						<div class="panel-body">
							<div id="datepick" class="table-responsive"
								style="font-size: 13px">

								<ul class="nav nav-pills f-s-15 inline bordered round-corner">
									<li>
										<div class="row">

											<%
												Date myDate = new Date();
													SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

													System.out.println("Current Date: " + ft.format(myDate));
											%>
											<div class="col-xs-5">
												<label for="ex1">From:</label> <input class="form-control"
													type="date" name="from" id="from" onchange="checkDate();">
											</div>

											<div class="col-xs-5 input-control text"
												data-role="datepicker">
												<label for="ex1">To:</label> <input class="form-control too"
													type="date" name="to" id="to" onchange="checkDateTo();">

											</div>

											<div class="col-xs-5 input-control text">
												<p id="date_message" style="display: none; color: red">
													Select date Less than today's date</p>

											</div>

											<input class="btn btn-default" type="button"
												style="background-color: #AFB1A2; margin-top: 26px; border-color: greenyellow;"
												class="btn btn-sm btn-success" id="filterid" value="Filter"
												onclick="filter()">
										</div>

									</li>


								</ul>
								<table id="fiance_table"
									class="table table-hover table-bordered">
									<thead>
										<tr>

											<th style="background: none">Customer Name</th>
											<th>District</th>

											<!-- 						                    <th>Customer Phone</th> -->
											<th>Appliance IMEI</th>

											<!-- 						                    <th>Loan ID</th> -->

											<th>Amount</th>
											<th>Transaction ID</th>

											<th>Bank Name</th>
											<th>FO Name</th>
											<th>FO Commission</th>
											<th>ND Name</th>
											<th>ND Commission</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>

						</div>
					</div>

					<!-- end panel -->
				</div>
				<!-- end col-10 -->
			</div>
		</div>
	</div>
	<!-- end row -->
	<%
		} //end for
			// session else closed
	%>






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
	<!-- <script src="assets/js/jquery-ui-1.8.18.custom.js"></script> -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script>
		function formatDollar(num) {
			//var p = num.toFixed(2).split(".");
			//return p[0].split("").reverse().reduce(function(acc, num, i, orig) {
			return num;
			/*  + "." + p[1] */;
		}

		function checkDate() {
			var EnteredDate = document.getElementById("from").value; //for javascript

			var EnteredDate = $("#from").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);

			var myDate = new Date(endYear, endMonth - 1, endDate);

			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}

			else {
				$("#date_message").css("display", "none");
			}
		}

		function checkDateTo() {
			var EnteredDate = document.getElementById("to").value; //for javascript

			var EnteredDate = $("#to").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);

			var myDate = new Date(endYear, endMonth - 1, endDate);

			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}

			else {
				$("#date_message").css("display", "none");
			}

		}

		function filter() {

			var table1 = $('#fiance_table').html();

			var to = document.getElementById("to").value;
			var from = document.getElementById("from").value;

			customertable = $("#fiance_table")
					.DataTable(
							{
								"destroy" : true,
								"order" : [ [ 0, "asc" ] ],
								"processing" : true,
								"serverSide" : true,

								"ajax" : {
									"url" : "finainceController",
									"type" : "get",
									"data" : function(d) {
										d.action = "cash_customer", d.to = to,
												d.from = from
									},
									"dataSrc" : function(json) {
										console
												.log('**********dataSrc2*******');
										console.log(json);
										console.log(json.data);

										$
												.each(
														json.data,
														function(e) {

															json.data[e].select = '<span class="label label-info" style="font-size:85%">  <span class="fa fa-plus-circle fa-lg"'+json.data[e].Customer_id+'"> '
																	+ json.data[e].Customer_name
																	+ '</span>'

														});
										$('#curdate').html(json.curdate);
										$("#total_amount").html(
												formatDollar(json.total_Amount)
														+ "<b> PKR</b>");
										$("#total_sale").html(
												formatDollar(json.total_sale)
														+ "<b> PKR</b>");
										total_commission

										$("#total_net_income")
												.html(
														formatDollar(json.total_Amount
																- (json.nd_commission + json.fo_commission))
																+ "<b> PKR</b>");
										$("#total_fo_amount")
												.html(
														formatDollar(json.fo_commission)
																+ "<b> PKR</b>");
										$("#total_nd_amount")
												.html(
														formatDollar(json.nd_commission)
																+ "<b> PKR</b>");
										$("#total_commission")
												.html(
														formatDollar(json.total_commission)
																+ "<b> PKR</b>");

										//console.log('after alteration1');
										//console.log(json);
										//console.log(json.data);
										return json.data;
									},
								},

								"rowCallback" : function(row, data, index) {

									$(row).addClass('link-first-child')

								},
								"columns" : [ {
									"data" : "select",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "District_name"

								}, {
									"data" : "Imei_number"

								}, {
									"data" : "Installment_paid"

								}, {
									"data" : "Transaction_id"

								}, {
									"data" : "Bank_name"

								}, {
									"data" : "Fo_name"

								}, {
									"data" : "FOcommission_persime"

								}, {
									"data" : "Nd_name"

								}, {
									"data" : "NDcommission_persime"

								} ],
								"columnsDefs" : []

							});
			//console.log('Before   CUstomer    ' + JSON.stringify(customertable));

		}

		$("#fiance_table tbody").on('click', 'tr>td', function(event) {

			// 		link = $(this).parent().data('link');
			//console.log($(this).text())
			if ($(this).eq(0).html() != "") {
				// 			if (link != undefined) {
				// 				window.location=link;
				// 			}			
			}
		});

		$("#fiance_table tbody").on('click', 'tr>td>span>span.fa',
				function(event) {

					var span = $(this)
					console.log('CSSS POSITION    ' + JSON.stringify(span));
					var tr = $(this).closest('tr');
					console.log('Row Id     ' + JSON.stringify(tr));
					//console.log('Row Number  '+JSON.stringify(customertable));
					var row = customertable.row(tr);
					//console.log('ROW VALUE    '+JSON.stringify(row));
					console.log("tr : ");
					console.log(tr);
					console.log("Row : ");
					console.log(row)
					console.log("Row Data : ");
					console.log("ID " + row.data())
					if (row.child.isShown()) {
						row.child.hide();
						tr.removeClass('shown');
						$(span).addClass('fa-plus-circle')
						$(span).removeClass('fa-minus-circle')
					} else {
						//console.log('sddddd')
						//console.log(row.data())
						//console.log('dfdfdfdfdf')

						row.child(format(row.data())).show();
						//alert('dsfds')
						//alert('wo  '+JSON.stringify(row.data()))
						tr.addClass('shown');
						$(span).addClass('fa-minus-circle')
						$(span).removeClass('fa-plus-circle')
					}
				});

		function format(d) {
			var id = "inner_table_" + d.Customer_id
			$('#' + id).remove();
			console.log('# ID     ' + JSON.stringify($('#' + id).remove()))
			var innerTable = "<table id='"+id+"'>";
			console.log('ID TABLE     ' + innerTable);
			$
					.getJSON(
							"GetCustomer?click=getCashCustomer&id="
									+ d.Customer_id,
							{},
							function(result, response) {
								$
										.each(
												result,
												function(key, value) {
													console.log(result)
													console.log(result)
													console
															.log('Actual Value  '
																	+ value);
													console.log('Lenght     '
															+ value.length);
													if (value.length <= 0) {
														$('#' + id)
																.append(
																		"Record Not Found");
													} else {
														console.log('In    '
																+ value.length);
														$('#' + id)
																.append(
																		'<th class="p-10">Appliance Name</th>'
																				+ '<th class="p-10">Customer CNIC</th>'
																				+ '<th class="p-10">Customer Address</th>'
																				+ '<th class="p-10">Customer Phone</th>'
																				+ '<th class="p-10">ND Phone</th>'
																				+ '<th class="p-10">FO Phone</th>'
																				+ '<th class="p-10">Paid Date</th>')
														for ( var i = 0; i < value.length; ++i) {
															console
																	.log(value[i].applianceName)
															var row;
															row += '<tr>'
																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].applianceName
																	+ '</td>'
																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].cnicNo
																	+ '</td>'
																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].address
																	+ '</td>'
																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].phoneNo
																	+ '</td>'
																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].salesmanName
																	+ '</td>'
																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].foName
																	+ '</td>'

																	+ '<td class="p-r-10 p-l-10 p-b-5">'
																	+ value[i].handoverAt
																	+ '</td>'

															;

															row += '</tr>' + '';

														}
														$('#' + id).append(row)
													}
												})

							})

			return innerTable + "</table>";
		}

		function table_load() {

			var currentDt = new Date();
			var mm = currentDt.getMonth() + 1;
			mm = (mm < 10) ? '0' + mm : mm;
			var dd = currentDt.getDate();
			var yyyy = currentDt.getFullYear();
			var date = yyyy + '-' + mm + '-' + dd;
			var to = date;
			var from = date;
			//console.log('table_load');
			customertable = $("#fiance_table")
					.DataTable(
							{
								"destroy" : true,
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "finainceController",
									"type" : "get",
									"data" : function(d) {
										d.action = "cash_customer", d.to = to,
												d.from = from
									},
									"dataSrc" : function(json) {
										console
												.log('**********dataSrc1*******');

										console.log(json);
										console.log(json.data);
										console.log('Thats good');
										$
												.each(
														json.data,
														function(e) {
															json.data[e].select = '<span class="label label-info" style="font-size:85%">  <span class="fa fa-plus-circle fa-lg"'+json.data[e].Customer_id+'"> '
																	+ json.data[e].Customer_name
																	+ '</span>'
														});
										$('#curdate').html(json.curdate);
										$("#total_amount").html(
												formatDollar(json.total_Amount)
														+ "<b> PKR</b>");
										$("#total_sale").html(
												formatDollar(json.total_sale)
														+ "<b> PKR</b>");
										total_commission

										$("#total_net_income")
												.html(
														formatDollar(json.total_Amount
																- (json.nd_commission + json.fo_commission))
																+ "<b> PKR</b>");
										$("#total_fo_amount")
												.html(
														formatDollar(json.fo_commission)
																+ "<b> PKR</b>");
										$("#total_nd_amount")
												.html(
														formatDollar(json.nd_commission)
																+ "<b> PKR</b>");
										$("#total_commission")
												.html(
														formatDollar(json.total_commission)
																+ "<b> PKR</b>");

										return json.data;
									},
								},

								"rowCallback" : function(row, data) {
									// 			if($.inArray(data.DT_RowId, selected) !== -1){
									//	$(row).data('link','ViewServlet?click=view&cnic='+ data.customerCnic+ '&applianceId='	+ data.applianceId);
									//$(row).addClass('link-first-child');
								},
								"columns" : [ {
									"data" : "select",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "District_name"

								}, {
									"data" : "Imei_number"

								}, {
									"data" : "Installment_paid"

								}, {
									"data" : "Transaction_id"

								}, {
									"data" : "Bank_name"

								}, {
									"data" : "Fo_name"

								}, {
									"data" : "FOcommission_persime"

								}, {
									"data" : "Nd_name"

								}, {
									"data" : "NDcommission_persime"

								} ],
								"columnsDefs" : []
							});

		}
	</script>


	<script>
		function valueChecker() {
			$("#content").on("change", "#to, #from", function() {
				var toValue = $("#to").val();
				var fromValue = $("#from").val();
				if (toValue != "" && fromValue != "") {
					$('#filterid').css("background-color", "Blue");
					$('#filterid').prop("disabled", false);
				}
			});
			$('#filterid').prop("disabled", true);
		}
	</script>


	<script>
		var customertable = $('#fiance_table').DataTable({});
		$(document).ready(function() {
			App.init();
			valueChecker();
			table_load();
		});
	</script>

</body>


</html>
