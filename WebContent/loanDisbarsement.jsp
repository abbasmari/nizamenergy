<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


		
 		<link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
		<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		
		<!-- <link href="assets/css/style.min.css" rel="stylesheet" /> -->
		

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->

</head>
<body>


		<% 
			int customerId = Integer.parseInt(request.getParameter("customerid"));
			Object ApplianceId = Integer.parseInt(request.getParameter("id"));
			/* out.print("customerId:"+customerId+"ApplianceId:"+ApplianceId); */
		%>
			
			
			<form  class="form-horizontal" action="DoDeployment" method="POST" 
			id="" data-parsley-validate="true" >
				
				<%-- <div class="form-group row has-feedback" id="other_appliances">
						<h4></h4>
						<div class="col-md-1">Fine For All</div>
						 <div class="col-md-2 m-b-5">
						 <input type="number" class="form-control " name="late_panality" id="late_panality" 
						  placeholder="Panality Amount" >
						</div>

						<div class="col-md-2 m-b-5">
						  <input type="number" class="form-control" name="late_days" id="late_days"
						  placeholder="Enter Late Days">
						</div>

						<input type="hidden" name="customerId" value="<%=customerId%>"> 
						<input type="hidden" name="ApplianceId" value="<%=ApplianceId%>">
						
						<div class="col-md-1 "
							style="margin-right: 10px;">
							<button type="submit" name="action" value="addOwnScheme" class="btn btn-block btn-info"
								id="action">Submit</button>
						</div>
				</div> --%>
				
				
				<div class="form-group row has-feedback" id="other_appliances">
						<h4></h4>
						<div class="col-md-1">Fine For particular Customers</div>
						 <div class="col-md-2 m-b-5">
						 <input type="number" class="form-control " name="fine" id="fine" 
						  placeholder="Enter Fine Amount" >
						</div>

						<div class="col-md-2 m-b-5">
						  <input type="text" class="form-control" name="fine_message" id="fine_message"
						  placeholder="Enter Fine Description">
						</div>
						
						<input type="hidden" name="customerId" value="<%=customerId%>"> 
						<input type="hidden" name="ApplianceId" value="<%=ApplianceId%>">
						
						<div class="col-md-1 "
							style="margin-right: 10px;">
							<button type="submit" name="action" value="fineOne" class="btn btn-block btn-info"
								id="action">Submit</button>
						</div>
				</div>
				
				
				<div class="form-group row has-feedback" id="other_appliances">
						<h4></h4>
						<div class="col-md-1">Discount for Particular Customers</div>
						 <div class="col-md-2 m-b-5">
						 <input type="number" class="form-control " name="discount" id="discount" 
						  placeholder="Enter Discount Amount" >
						</div>

						
						<div class="col-md-2 m-b-5">
						  <input type="text" class="form-control" name="discount_message" id="discount_message"
						  placeholder="Enter Discount Discription">
						</div>

						<input type="hidden" name="customerId" value="<%=customerId%>"> 
						<input type="hidden" name="ApplianceId" value="<%=ApplianceId%>">
						
						<div class="col-md-1 "
							style="margin-right: 10px;">
							<button type="submit" name="action" value="DiscountOne" class="btn btn-block btn-info"
								id="action">Submit</button>
						</div>
				</div>
									
			</form>
						
				
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
	
</body>
</html>