<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.DefaultCustomer"%>
<%@page import="bean.UpComingRevocoveries"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="bal.TargetsBAL"%>
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
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nizam Energy</title>

<link rel="shortcut icon" href="assets/icons/favicon.png" />


<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>



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

<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />

<!-- Load jQuery from Google's CDN -->
<!-- Load jQuery UI CSS  -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="DateTimepicker/jquery-ui-timepicker-addon.css">
<link rel="stylesheet"
	href="DateTimepicker/jquery-ui-timepicker-addon.min.css">

<!-- ================== END BASE CSS STYLE ================== -->



<link
	href="assets/plugins/bootstrap-combobox/css/bootstrap-combobox.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css"
	rel="stylesheet" />
<link href="assets/plugins/select2/dist/css/select2.min.css"
	rel="stylesheet" />


<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<link href="assets/plugins/icheck/skins/line/_all.css" rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<!-- ================== BEGIN BASE JS ================== -->
<!-- <script src="assets/plugins/pace/pace.min.js"></script -->

<!-- ================== END BASE JS ================== -->


<Script>


			<%SalesManBean salesman = (SalesManBean) request
					.getAttribute("salesman");
			System.out
					.print("++++++++++++++++ .......... salesmna " + salesman);

			String salesmanNumber = UserBAL.getFormattedPhoneNumber(salesman
					.getPhone_number());
			System.out.print(salesmanNumber);
			String salesmanSalary = NumberFormat.getNumberInstance(Locale.US)
					.format((salesman.getSallery()));

			System.out.println("++++++++++++++++" + salesmanSalary);
			List<String> endTarget = TargetsBAL.getTargetsDateForSales(salesman
					.getSalesmanId());%>
			System.out.println("++++++++++++++++ end tarfget " + endTarget);

function myFunction() {
    location.reload();
}

function updateDueDate()
{
	var appid=document.getElementById("appID").value;
	
	var id=$('[data-id]').data('id');
	$.ajax({
		url:'UpdateDate',
		method:'Post',
		dataType:'json',
		data:{
			Appid:id
		},
		success:function(data)
		{
			
		}
	});
}	

	
         
	             
	

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
			var row = $(links[e]).data('link');
			console.info(row);
			rrow = row.replace(/[0-9]{4}-?[0-9]{2}-[0-9]{2} to [0-9]{4}-?[0-9]{2}-[0-9]{2}/, targetDate);
// 			console.error(rrow)
			$(links[e]).data('link',rrow);
			
		});

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
				
// 				console.log(data[9].TARGETDATE);
// 				console.log(dataProviders);
				
// 				dbGraph = data[9].TARGETDATE;
				
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
				
				
					
// 					graphFunction(dbGraph);
					
					
	            
				
			}
		
		});
	
	}  
	
	
	
	
	
	
	
	
	
	
	
	function getSalesmanGraph(salesmanId ,bt, ot , at){
		var dbGraph;
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
			url : 'CommissionFilter',
			dataType : 'json',
			method : 'POST',
			data : {
				salesmanId : salesmanId,
				beforeTime : bbt,
				onTime : oot,
				afterTime : aat
				
			},
			success : function(data){
				console.log("=================");
				console.log(data[9].TARGETDATE);
				
				dbGraph = data[9].TARGETDATE;
				
				
					
// 					graphFunction(dbGraph);
					
				// SERIAL CHART
			    chart = new AmCharts.AmSerialChart();
			    chart.dataProvider = dbGraph;
			    chart.categoryField = "date";
			    chart.dataDateFormat = "YYYY-MM-DD";
			    // AXES
			    // category
			    var categoryAxis = chart.categoryAxis;
			    categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
			    categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
			    categoryAxis.dashLength = 1;
			    categoryAxis.gridAlpha = 0.15;
			    categoryAxis.equalSpacing = true;
// 			    categoryAxis.axisColor = "#DADADA";
				categoryAxis.gridPosition = "start";

			    // value
			    var valueAxis = new AmCharts.ValueAxis();
			    valueAxis.axisColor = "#DADADA";
			    valueAxis.dashLength = 1;
// 			     valueAxis.logarithmic = true; // this line makes axis logarithmic
			    chart.addValueAxis(valueAxis);

			       
			    // GRAPH
			    var graph = new AmCharts.AmGraph();
			    graph.type = "smoothedLine";
			    graph.bullet = "round";
			    graph.bulletColor = "#FFFFFF";
			    graph.useLineColorForBulletBorder = true;
			    graph.bulletBorderAlpha = 1;
			    graph.bulletBorderThickness = 2;
			    graph.bulletSize = 7;
			    graph.title = "On Time";
			    graph.valueField = "onTime";
			    graph.balloonText = "<span style='font-size:11px'>Rs:<b>[[onTime]]</b> <br>Rec: <b>[[ot_recovery]]</b></span>";
			    graph.lineThickness = 2;
			    graph.lineColor = "#00BBCC";
			    
			    chart.addGraph(graph);
			    
			    
			    
			    
			    
			    var graph2 = new AmCharts.AmGraph();
			    graph2.type = "smoothedLine";
			    graph2.bullet = "round";
			    graph2.bulletColor = "#FFFFFF";
			    graph2.useLineColorForBulletBorder = true;
			    graph2.bulletBorderAlpha = 1;
			    graph2.bulletBorderThickness = 2;
			    graph2.bulletSize = 7;
			    graph2.title = "Before Time";
			    graph2.valueField = "amount";
			    graph2.lineThickness = 2;
			    graph2.balloonText = "<span style='font-size:11px'>Rs:<b>[[amount]]</b> <br>Rec: <b>[[bt_recovery]]</b></span>";
			    graph2.lineColor = "#08C";
			    chart.addGraph(graph2);
			    
			    
			    
			    
			    
			    var graph3 = new AmCharts.AmGraph();
			    graph3.type = "smoothedLine";
			    graph3.bullet = "round";
			    graph3.bulletColor = "#FFFFFF";
			    graph3.useLineColorForBulletBorder = true;
			    graph3.bulletBorderAlpha = 1;
			    graph3.bulletBorderThickness = 2;
			    graph3.bulletSize = 7;
			    graph3.title = "After Time";
			    graph3.valueField = "afterTime";
			    graph3.balloonText = "<span style='font-size:11px'>Rs:<b>[[afterTime]]</b> <br>Rec: <b>[[at_recovery]]</b></span>";
// 			    graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br>"
// 			    +"<span style='font-size:11px'>BT:<b>[[amount]]</b> BTR: <b>[[bt_recovery]]</b></span><br>"
// 			    +"<span style='font-size:11px'>OT:<b>[[onTime]]</b> OTR: <b>[[ot_recovery]]</b></span><br>"
// 			    +"<span style='font-size:11px'>AT:<b>[[afterTime]]</b> ATR: <b>[[at_recovery]]</b></span>";
			    graph3.lineThickness = 2;
			    graph3.lineColor = "#000000";
			    chart.addGraph(graph3);
			    
			    
			    

			    // CURSOR
			    var chartCursor = new AmCharts.ChartCursor();
			    chartCursor.cursorPosition = "mouse";
			    chart.addChartCursor(chartCursor);

			    // SCROLLBAR
			    var chartScrollbar = new AmCharts.ChartScrollbar();
			    chartScrollbar.graph = graph;
			    chartScrollbar.scrollbarHeight = 30;
			    chart.addChartScrollbar(chartScrollbar);

			    chart.creditsPosition = "bottom-right";

			    // WRITE
			    chart.write("chartdiv");
					
	            
				
			}
		
		});
	
	} 
	
	
	
	
	
	
	
	
	
	
	
	function getCustomerOfSalesmanByDate(salesmanId){
		var targetDate = document.getElementById("targetDateForSales").value;
		var d = targetDate.split(" to ");
		if(d[2]=='0'){
			document.getElementById("isSaleLock").innerHTML = "Sales : Lock";
		}else{
			document.getElementById("isSaleLock").innerHTML = "Sales : Unlock";
		}
		document.getElementById("filtercustomers").innerHTML = "";
		document.getElementById("Rs").innerHTML = "Rs : 0";
		document.getElementById("allowSales").innerHTML = "Sales Allowed : 0";
		document.getElementById("salesAchive").innerHTML = "Sales Achived : 0";
		document.getElementById("assignUnits").disabled = true;
	
		$.ajax({
			url : 'SalesFilter',
			dataType : 'json',
			method : 'POST',
			data : {
				salesmanId : salesmanId,
				targetStartDate : d[0],
				targetEndDate : d[1]
				
			},
			success : function(data){
				
				if(data[0].per>=98){
					document.getElementById("assignUnits").disabled = false;
				}
				
				document.getElementById("allowSales").innerHTML = "Sales Allowed : "+data[0].allowSales;
				document.getElementById("Rs").innerHTML = "Rs : "+data[0].rs;
				document.getElementById("salesAchive").innerHTML = "Sales Achived : "+data[0].salesAchive;
				for(var i=1; i<data.length; i++){
					var status = "";
					var a = "";
					if(data[i].applianceStatus=='1'){
						status = " class=\"hover\"	data-target=\"#payingModal\" data-toggle=\"modal\" ";
					}
					
					if(data[i].applianceStatus=='0'){
						a = "<span	class=\"label label-danger\">Deactivated</span>";
					}else{
						a = "<span class=\"label label-success\">Activated</span>";
					}
					
					
					document.getElementById("filtercustomers").innerHTML += "<tr data-astatus=\"customers[i][7]\" "+status+
						"data-applianceid="+data[i].applianceId+" >"+
						"<td>"+data[i].customerName+"</td>"+
						"<td>"+data[i].customerCnic+"</td>"+
						"<td>"+data[i].customerPhone+"</td>"+
						"<td>"+data[i].applianceName+"</td>"+
						"<td>"+data[i].applianceGSMno+"</td>"+
						"<td>"+a+"</td>"+
						"<td>"+data[i].cityName+"</td>"+
					"</tr>";
				}
			}
		});
			
		
	}
	
	
	function updateAssignUnits(salesmanId,targetId){
		var units = document.getElementById('units').value;
		$.ajax({
			url:"UpdateTarget",
			method:"POST",
			data : {
				salesmanId : salesmanId,
				targetId : targetId,
				units : units
			},
			success : function(data){
				alert(data);
			}
		});
	}
	

	 function setMoodValue(ndId, nd_name) {
	  	var id = document.getElementById("ndId").innerHTML = ndId;
	  	var name = document.getElementById("ndId").innerHTML = nd_name;
	   	document.getElementById('updateURL').onclick = function() {
		$.ajax({
			url : 'FoRemovingController',
			method : 'POST',
			dataType : 'json',
			data : {
				click : "removeNd",
				ndId : id
			},
			success : function(data) {
				$('#hello').text(data.Have_Network)
				$("#modal-remove").modal('hide');	
				$("#modal-list").modal('show');	
				$("#ndIdd").text(data.ndName)
				if(data.bulkNd == "null"){
					$("#bulk").text('Alert! there is no Bulk Nizamdost of his District so you can not convert or remove this ND. Kindly create Bulk ND');
				}else {
					$("#district").text(data.bulkNd)
				}
				}
		})
	 }
	   	document.getElementById('update').onclick = function() {
		   helloWorld(ndId);
		}
	}
	 
	function helloWorld(ndId) {
		var link = "FoRemovingController?click=removNd&ndId="+ndId;
		document.getElementById('update').href = link;
	}

</Script>



</head>
<!-- <body onload="add()"> -->
<body onload="initialize()">

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
							Are you sure you would like to remove Nizam Dost <b><i
								id="ndId"> </i></b> ?
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
							transfer Nizam Dost <b><i id="ndIdd"> </i></b>'s network to <b><i
								id="district"> </i></b>?
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
							<%-- 							<%=userbean.getUserName()%> --%>
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
					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Reports</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="reportAnalytics.jsp">Late/Defaulter Report</a></li>
							<li><a href="futureLoanBooks.jsp">Future loan book</a></li>
							<li><a href="SaReports.jsp">Reports</a></li>
						</ul></li>
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
				<li><a href="Salesmen">Salesmen</a></li>
				<li class="active">Nizam Dost Profile</li>
			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header"><%=salesman.getName()%></h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<div class="col-md-4">

					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Nizam Dost Details</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">

									<tbody>
										<tr>
											<th>Field Officer</th>
											<td><a
												href="fieldOfficerProfile.jsp?fo_id=<%=salesman.getFoid()%>"><%=salesman.getFoname()%></a>
											</td>
										</tr>

										<tr>
											<th>District Officer</th>
											<td><a
												href="DistrictOfficer?do_id=<%=salesman.getDoid()%>"><%=salesman.getDoname()%></a></td>
										</tr>

										<tr>
											<th>Nizam Dost CNIC</th>
											<td><%=salesman.getCnic()%></td>
										</tr>
										<tr>
											<th>Phone No</th>
											<td><%=salesmanNumber%></td>
										</tr>
										<tr>
											<th>Total Customers</th>
											<%
												String[][] customers = CustomerBAL.getCustomerBySalesmanId(salesman
														.getSalesmanId());
											%>
											<td><%=customers.length%></td>

										</tr>


										<tr>
											<th>Tehsil</th>
											<td><%=salesman.getTahsel()%></td>

										</tr>

										<%-- <tr>
											<th>Basic Salary</th>
											<td><%=salesmanSalary%></td>

										</tr> --%>
										<tr>
											<th>Date Of Joining</th>
											<td><%=salesman.getDatejoin()%></td>

										</tr>

										<tr>
											<th>Date Of Birth</th>
											<td><%=salesman.getDate_of_birth()%></td>

										</tr>

										<tr>
											<th>Address</th>
											<td><%=salesman.getAddress()%></td>

										</tr>

										<tr>
											<th>Monthly Recovery Commission</th>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
					Math.round(salesman.getOnTime()))%>
												%</td>
										</tr>


										<tr>
											<th>Per Sale Commission</th>
											<%
												if (salesman.getPer_sale() == 0) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(salesman.getPer_sale()))%><b>
													PKR</b></td>

											<%
												}
											%>

										</tr>

										<tr>
											<th>Nizam Dost Account Number</th>

											<%
												if (salesman.getVle_acount_no() == ""
														|| salesman.getVle_acount_no() == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=salesman.getVle_acount_no()%></td>
											<%
												}
											%>
										</tr>

										<tr>
											<th>Update Profile</th>
											<td><a
												href="updatevle.jsp?vleId=<%=salesman.getSalesmanId()%>"
												class="label"
												style="background-color: #3449ee; color: white; font-weight: bold; width: 100%">Edit</a></td>
										</tr>

										<tr>
											<td>
												<button type="button" class="btn btn-primary pull-left"
													data-toggle="modal" data-target="#modal-remove" id="foId"
													onclick="setMoodValue('<%=salesman.getSalesmanId()%>','<%=salesman.getName()%>')">Remove
													Nizam Dost</button>

											</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>







				<div class="col-md-8">
					<%
						int customerleng = 0;
					%>
					<div class="panel panel-default panel-with-tabs"
						data-sortable-id="ui-widget-9">
						<div class="panel-heading">
							<ul id="myTab" class="nav nav-tabs pull-right">
								<li class="active"><a href="#defaultprofile"
									data-toggle="tab" aria-expanded="true"><i
										class="fa fa-user"></i> <span class="hidden-xs">Default
											Customers</span></a></li>
								<li class=""><a href="#profile" data-toggle="tab"
									aria-expanded="true"><i class="fa fa-user"></i> <span
										class="hidden-xs">Customers (<%=customers.length%>)
									</span></a></li>


								<li class=""><a href="#recoveries" data-toggle="tab"
									aria-expanded="false"><i class="fa fa-home"></i> <span
										class="hidden-xs">Recoveries </span></a></li>

							</ul>
							<h2 class="panel-title">Panel with Tabs</h2>
						</div>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade" id="home" style="margin: -15px">

							</div>
							<div class="tab-pane fade active in" id="defaultprofile"
								style="margin: -15px">

								<div class="panel panel-inverse">

									<div class="panel-body">


										<div class="table-responsive" style="font-size: 13px;">



											<div id="" style="padding-top: 12px;">
												<div id="data-table_wrapper"
													class="dataTables_wrapper no-footer">
													<table id="data-table"
														class="table table-hover table-bordered">
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
																// 															ArrayList<DefaultCustomer> defCustomerlist  =  (ArrayList<DefaultCustomer>)request.getAttribute("defaultcutomer");
																ArrayList<HashMap<String, String>> defCustomerlist = SalesmanBAL
																		.getDefaultCustomer(salesman.getSalesmanId());
																if (defCustomerlist.size() != 0) {
																	for (int i = 0; i < defCustomerlist.size(); i++) {
																		StringBuilder smancell = new StringBuilder(defCustomerlist
																				.get(i).get("customer_phone").replace("92", ""));
																		smancell = smancell.insert(3, "-");
															%>
															<tr>
																<td><%=defCustomerlist.get(i).get("customer_name")%></td>
																<td><%=defCustomerlist.get(i).get("customer_cnic")%></td>
																<td><span>(+92) </span><%=smancell%></td>

																<td><%=defCustomerlist.get(i).get("appliance_name")%></td>
																<td><%=defCustomerlist.get(i).get("appliance_GSMno")%></td>
																<td><%=defCustomerlist.get(i).get("due_date")%></td>

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
							<div class="tab-pane fade in" id="profile" style="margin: -15px">

								<div class="panel panel-inverse">

									<div class="panel-body">


										<div class="table-responsive" style="font-size: 13px;">



											<div id="msgid" style="padding-top: 12px;">
												<div id="data-table_wrapper"
													class="dataTables_wrapper no-footer">

													<table id="data-table"
														class="table table-hover table-bordered">
														<thead>
															<tr>
																<th>Name</th>
																<th>CNIC</th>
																<th>Phone</th>

																<th>Appliance</th>
																<th>Appliance IMEI</th>
																<th>Charging Status</th>
																<th>City/Town</th>
															</tr>
														</thead>
														<tbody>
															<%
																if (customers.length > 0) {
																	customerleng = customers.length;
																	System.err.print("=======555==============" + customers.length);
																	for (int i = 0; i < customers.length; i++) {
															%>
															<tr data-astatus="<%=customers[i][7]%>"
																<%-- <%if (customers[i][7].equals("1")) {%> --%> class="hover"
																data-target="#payingModal" data-toggle="modal"
																<%-- <%}%> --%>
																data-applianceid=<%=customers[i][9]%>>
																<td><%=customers[i][1]%></td>
																<td><%=customers[i][2]%></td>
																<td>
																	<%
																		String numbervalue = UserBAL
																						.getFormattedPhoneNumber(customers[i][3]);
																	%> <%=numbervalue%>

																</td>

																<td><%=customers[i][5]%></td>
																<td>
																	<%
																		String Appliancenumber = customers[i][6];
																	%> <%=Appliancenumber%>


																</td>
																<td>
																	<%
																		if (customers[i][7].equals("0")) {
																	%> <span class="label"
																	style="background-color: Red; color: white; font-weight: bold;">Inactivated</span>
																	<%
																		} else if (customers[i][7].equals("1")) {
																	%> <span class="label"
																	style="background-color: #16a085; color: white; font-weight: bold;">Activated</span>
																	<%
																		}
																	%>
																
																<td><%=customers[i][8]%></td>

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
							<div class="tab-pane fade" id="recoveries" style="margin: -15px">

								<div class="panel panel-inverse">

									<div class="panel-body">

										<div class="table-responsive" style="font-size: 13px;">
											<table class="table">
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
																.upComingRevocoveriesBySalesmanId(salesman.getSalesmanId());

														for (int i = 0; i < upComingRevocoveries.size(); i++) {
															String color = "white";

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
															} else if (Integer.parseInt(upComingRevocoveries.get(i)
																	.getRemainingDays()) >= 11
																	&& Integer.parseInt(upComingRevocoveries.get(i)
																			.getRemainingDays()) <= 29) {
																color = "GreenYellow";
															}
													%>
													<tr
														style="background-color:<%=color%>; <%if (Integer.parseInt(upComingRevocoveries.get(i)
						.getRemainingDays()) == 0) {%> color:white;<%}%> ">
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

				</div>



			</div>
			<!-- end row -->









			<!-- tab panel row starts -->




			<div class="row">
				<div class="panel-body">
					<div class="table-responsive" style="font-size: 13px;">

						<!-- 								<div class="row"> -->
						<div class="col-md-4"></div>

					</div>

				</div>













			</div>
			<!-- tab panel row end -->


		</div>
		<!-- end #content -->
		<!-- #modal-dialog -->
		<div class="modal fade" id="payingModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Pay Loan</h4>
					</div>

					<div class="modal-body">
						<form onsubmit="return false;" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<label for="#amount" class="col-sm-3 control-label">Amount</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="amount"
											placeholder="Enter Amount" />
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<div class="col-sm-8">
							<a href="javascript:;" class="btn btn-block btn-success"
								id="payAmount">Pay</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:;" class="btn btn-block btn-default"
								data-dismiss="modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</div>



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


	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<!-- Load jQuery UI Main JS  -->
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script src="DateTimepicker/jquery-ui-timepicker-addon.js"></script>
	<script src="DateTimepicker/jquery-ui-timepicker-addon.min.js"></script>

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
	<script src="assets/plugins/chart-js/chart.js"></script>
	<!-- ================== END BASE JS ================== -->






	<script src="assets/plugins/flot/jquery.flot.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.time.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.stack.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.crosshair.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.categories.js"></script>
	<script src="assets/js/chart-flot.demo.min.js"></script>
	<!-- 	<script src="assets/js/apps.min.js"></script> -->




	<!-- ================== BEGIN PAGE LEVEL JS ================== -->

	<!-- 		<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> -->

	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>

	<script
		src="assets/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script>
	<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>

	<script src="assets/plugins/select2/dist/js/select2.min.js"></script>
	<script src="assets/js/form-plugins.demo.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- 	<script src="assets/js/chart-js.demo.min.js"></script> -->
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script src="assets/plugins/icheck/icheck.js"></script>
	<script src="assets/js/myscript.js"></script>

	<script type="text/javascript">
$(function(){
		var applianceId;
		var amount;
		$('tr[data-applianceid]').click(function(){
			applianceId = $(this).data('applianceid');
		})
		$('#payingModal').on('show.bs.modal', function(){
				$('.alert').alert('close')
				$('#amount').val("")
			})
		$('#payAmount').click(function(){
// url : '/NizamEnergyProject/LoanPaymentController',
			console.log("Hell");
			amount = $('#amount').val();
			if(amount){
				$.ajax({
					url : 'LoanPaymentController',
					method : 'POST',
					data : {
						click : "payingLoan",
						applianceId : applianceId,
						amount : amount
					},
					success : function(data){
						result = JSON.parse(data);
						console.log(result.status);
						
						if(result.status == 0){
							/* document.getElementById(""+applianceId).innerHTML="<span class=\"label label-success\">Activated</span>"; */ 
							alert = '<div class="alert alert-success fade in">'
								+'<span class="close" data-dismiss="alert">×</span>'
								+'<i class="fa fa-check fa-2x pull-left"></i>'
								+'<p>Paid</p>'
				            	+'</div>';
				            	$('#payingModal .modal-header').after(alert);
				            	$('#amount').val("");
						}
						else if(result.status == 1){
							alert = '<div class="alert alert-danger fade in">'
								+'<span class="close" data-dismiss="alert">×</span>'
								+'<i class="fa fa-check fa-2x pull-left"></i>'
								+'<p>Paying Large Amount</p>'
				            	+'</div>';
				            	$('#payingModal .modal-header').after(alert);
				            	$('#amount').val("");
						}
						else if(result.status == 3){
							alert = '<div class="alert alert-danger fade in">'
								+'<span class="close" data-dismiss="alert">×</span>'
								+'<i class="fa fa-check fa-2x pull-left"></i>'
								+'<p>Defaulted Customer</p>'
				            	+'</div>';
				            	$('#payingModal .modal-header').after(alert);
				            	$('#amount').val("");
						}
						else if(result.status == -1){
							alert = '<div class="alert alert-danger fade in">'
								+'<span class="close" data-dismiss="alert">×</span>'
								+'<i class="fa fa-check fa-2x pull-left"></i>'
								+'<p>Error Occured</p>'
				            	+'</div>';
				            	$('#payingModal .modal-header').after(alert);
				            	$('#amount').val("");
						}

					}
				
				})
			
				$('.alert').alert('close')
			}else{
				error = '<div class="alert alert-danger fade in">'
				+'<span class="close" data-dismiss="alert">×</span>'
				+'<i class="fa fa-check fa-2x pull-left"></i>'
				+'<p>Error : Enter Amount</p>'
            	+'</div>'
            	$('#payingModal .modal-header').after(error)
			}
			
			
		})
	})
 	$(document).ready(function(){
		$('tr[data-applianceid]').click(function() {
			$('#payingModal #applianceId').html($(this).data('applianceid'));
		})	
	})
 	</script>
	<script>

		var active;
		var inactive;
		$(document)
				.ready(
						function() {
							$('input.filter')
									.each(
											function() {
												var self = $(this), label = self
														.next(), label_text = label
														.text();
												var rClass;
												if ($(this).attr('id') == "active") {
													rClass = 'iradio_line-blue';
												} else if ($(this).attr('id') == "inactive") {
													rClass = 'iradio_line-red';
												}

												label.remove();
												self
														.iCheck({
															radioClass : rClass,
															insert : '<div class="icheck_line-icon"></div>'
																	+ label_text
														});
											});
							// 			on click filter radio button
							$('input.filter')
									.on(
											'ifClicked',
											function() {
												if ($(this).is(":checked")) {
													console.log("clicked")
													$(this).iCheck('uncheck')
													active = false;
													inactive = false;
												} else {
													if ($(this).attr('id') == "active") {
														console.log("active")
														active = true;
														inactive = false;
													} else if ($(this).attr(
															'id') == "inactive") {
														console.log("inactive")
														active = false;
														inactive = true;
													}
												}
												filter()

											});

							// 			$('tr[data-astatu]'
getSalesmanGraph('<%=salesman.getSalesmanId()%>','<%=salesman.getBeforeTime()%>','<%=salesman.getOnTime()%>','<%=salesman.getAfterTime()%>');
							
							App.init();
						//	ChartJs.init();
						Chart.init();
							TableManageColVis.init();
							// 			FormPlugins.init();

						});
		// 			filteration function
		var filter = function() {
			if (active) {
				$('tr[data-astatus = "1"]').show()
				$('tr[data-astatus = "0"]').hide()
			} else if (inactive) {
				$('tr[data-astatus = "1"]').hide()
				$('tr[data-astatus = "0"]').show()
			} else {
				$('tr[data-astatus = "1"]').show()
				$('tr[data-astatus = "0"]').show()
			}
		}
	</script>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'https://www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-53034621-1', 'auto');
		ga('send', 'pageview');
	</script>
	<script>
    $(document).ready(
    		  
    		  /* This is the function that will get executed after the DOM is fully loaded */
    		  function () {
    		    $( ".datepicker" ).datetimepicker({
    		      changeMonth: true,//this option for allowing user to select month
    		      dateFormat: 'dd-mm-yy',
    		      showSecond: true,
    		      
    		      // changeYear: true //this option for allowing user to select from year range
    		    });
    		  }

    		);
    </script>
</body>


</html>
