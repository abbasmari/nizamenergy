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
				
							<!-- ---------------------------------------------------------------------------------- -->
							<div class="form-group has-feedback" id="customer_other_appliance_">
								<h4></h4>
									<div class="col-md-offset-1 col-md-2 m-b-5">
									<select class="form-control " 
									name="customer_appliance_name" id="appliance_name_2" data-style="btn-white"
									onchange="changeappliance();changeScheme()">
									<option value="" >Nothing Selected</option>
									<option value="TV">TV</option>
									<option value="FAN">FAN</option>
									<!-- <option value="B1 Basic1">B1 - Basic 1</option>
									<option value="B1 Basic2">B1 - Basic 2</option>
									<option value="B1 Roshni">B1 - Roshni</option>
									<option value="B1 PowerPC">B1 - Power PC</option>
									<option value="B1 PowerP">B1 - Power P</option>
									<option value="B1 PowerC">B1 - Power C</option>
									<option value="B1 Other">B1 - Other</option> -->
									</select>
									</div>

									<div class="col-md-2 m-b-5 " id="customer_appliance_scheme_new_" style="display: show;">
									<select class="form-control "
									id="scheme_" name="customer_appliance_scheme" data-style="btn-white"
									onchange="changefunction(); changeScheme();">
									<option value="" >Nothing Selected</option>

									<!-- <option value="12">12</option>
									<option value="16">16</option>
									<option value="18">18</option>
									<option value="24">24</option>
									<option value="36">36</option> -->
									<option value="1">Other</option>
									</select>
									</div>

									<div class="col-md-2 m-b-5" style="display: none;" id="customer_other_scheme_">
									  <input class="form-control " name="customer_appliance_scheme_other" id="oscheme_"
									   placeholder="Other Scheme" data-mask="9?99999" onkeyup="myScheme()"  value="">

									<span class="btn fa fa-spinner"
									style="background-color: #00ACAC; color: white; font-weight: bold; width:50%; margin-left:103px; margin-top: 5px;"
									id = "cncl" onclick="dynCancelScheme()" > re-fresh</span>
									</div>
									
									 <div class="col-md-2 m-b-5">
									  <input class="form-control " name="customer_advance_payment" id="customer_advance_payment_"
									   placeholder="Advance Payment" data-mask="999999" onkeyup="doScheme()" value="">
									</div> 

									<div class="col-md-2 m-b-5">
									  <input class="form-control " name="customer_monthly_payment" id="customer_monthly_payment_"
									   placeholder="Monthly Payment" data-mask="999999" onkeyup="doScheme()"  value="">
									</div>

									<div class="col-md-2 m-b-5">
									  <input class="form-control " name="customer_total_payment" id="customer_total_payment_" 
									  placeholder="Total Payment" data-mask="999999"  value="">
									 
									<input type="hidden" name="customerId" value="<%=customerId%>"> 
									<input type="hidden" name="ApplianceId" value="<%=ApplianceId%>">
									</div>
									
									<div class="col-md-1 m-b-5">
									 <button type="submit" name = "action" value="add_own_appliance" class="btn btn-primary pull-left" 
									 style= "margin-right: 161px;" id="add_own_appliance">Submit</button>
									</div>
									<!-- <div class="col-md-2 m-b-5">
										
									</div> -->
							</div>
									
						</form>
						
						
		<script type="application/javascript"  >
		
			$('#scheme_').attr("disabled", true);
			
			$('#customer_advance_payment_')
				.attr("disabled", true);
			
			$('#customer_monthly_payment_')
				.attr("disabled", true);
			
			$('#customer_total_payment_')
				.attr("disabled", true);
			
				function dynCancelScheme() {
					$("#customer_appliance_scheme_new_").css("display", "block");
					$("#customer_other_scheme_").css("display", "none");

					document.getElementById('customer_advance_payment_').value = '';
					document.getElementById('customer_monthly_payment_').value = '';
					document.getElementById('customer_total_payment_').value = '';

					$('#customer_advance_payment_').prop('disabled', true);
					$('#customer_monthly_payment_').prop('disabled', true);
				}
			
			
			
				function myScheme() {

					$('#customer_advance_payment_').prop('readonly', false);
					$('#customer_monthly_payment_').prop('readonly', false);
					$('#customer_total_payment_').prop('readonly', false);

					////
					$('#customer_advance_payment_').attr("disabled", false);
					$('#customer_monthly_payment_').attr("disabled", false);
					$('#customer_total_payment_').attr("disabled", false);

					document.getElementById('customer_advance_payment_').value = '';
					document.getElementById('customer_monthly_payment_').value = '';
					document.getElementById('customer_total_payment_').value = '';
				}

				function changeappliance() {
					$('#scheme_').attr("disabled", false);
					console.log('changeappliance')
				}

				function changefunction() {
					console.log('changefunction')
					$('#customer_advance_payment_').prop('readonly', true);
					$('#customer_monthly_payment_').prop('readonly', true);
					$('#customer_total_payment_').prop('readonly', true);

					if ($('#scheme_ option:selected').val() == '1') {
						console.log("before other dynamic..");

						$("#customer_appliance_scheme_new_").css("display", "none");
						$("#customer_other_scheme_").css("display", "block");

						$('#customer_advance_payment_').prop('readonly', false);
						$('#customer_monthly_payment_').prop('readonly', false);
						$('#customer_total_payment_').prop('readonly', false);

						document.getElementById('customer_advance_payment_').value = '';
						document.getElementById('customer_monthly_payment_').value = '';
						document.getElementById('customer_total_payment_').value = '';
					}

				}

				function changeScheme() {
					console.log('changeScheme')
					var appliance = $('#appliance_name_2').val();
					var scheme = $('#scheme_').val();
							console.log('appliance '+appliance)
							console.log('sheme '+scheme)

					if (appliance == "60 W" && scheme == "12") {
									console.log("called if")
						var price = (scheme * 2700) + 0
						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2700');
						$("#customer_total_payment_").val(price);

					} else if (appliance == "7 W" && scheme == "12") {
						var price = (scheme * 2700) + 0
						// 			console.log("called if")

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2700');
						$("#customer_total_payment_").val(price);

					} else if (appliance == "60 W" && scheme == "18") {
						var price = (scheme * 2100) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2100');
						$("#customer_total_payment_").val(price);

					} else if (appliance == "7 W" && scheme == "18") {
						var price = (scheme * 2000) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2000');
						$("#customer_total_payment_").val(price);

					} else if (appliance == "60 W" && scheme == "24") {
						var price = (scheme * 1800) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('1800');
						$("#customer_total_payment_").val(price);

					} else if (appliance == "7 W" && scheme == "24") {
						var price = (scheme * 1700) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('1700');
						$("#customer_total_payment_").val(price);

					}

					else if (appliance == "60 W" && scheme == "36") {
						var price = (scheme * 1500) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('1500');
						$("#customer_total_payment_").val(price);

					}

					else if (appliance == "7 W" && scheme == "36") {
						var price = (scheme * 1400) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('1400');
						$("#customer_total_payment_").val(price);

					}else if (appliance == "B1 Basic1" && scheme == "16") {
						var price = (scheme * 2700) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2700');
						$("#customer_total_payment_").val(price);
						console.log('price'+price)
						console.log('price'+price)
					}else if (appliance == "B1 Basic2" && scheme == "16") {
						var price = (scheme * 2800) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2800');
						$("#customer_total_payment_").val(price);
					}else if (appliance == "B1 Roshni" && scheme == "16") {
						var price = (scheme * 2700) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('2700');
						$("#customer_total_payment_").val(price);
					}else if (appliance == "B1 PowerP" && scheme == "16") {
						var price = (scheme * 3000) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('3000');
						$("#customer_total_payment_").val(price);
					}else if (appliance == "B1 PowerC" && scheme == "16") {
						var price = (scheme * 3100) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('3100');
						$("#customer_total_payment_").val(price);
					}else{
						var price = (scheme * 0) + 0

						$("#customer_advance_payment_").val('0');
						$("#customer_monthly_payment_").val('0');
						$("#customer_total_payment_").val(price);
					}

				} //  end of function 

				function doScheme() {

// 			 		document.getElementById('customer_advance_payment_'+a).value='';
// 			    	document.getElementById('customer_monthly_payment_'+a).value='';
					document.getElementById('customer_total_payment_').value = '';

					$('#customer_total_payment_').prop('readonly', true);
// 			 		$('#customer_advance_payment_'+a).prop('readonly',true);

					var scheme = $('#oscheme_').val();
					var advance = $('#customer_advance_payment_').val();
					console.log('advance e'+advance+'e');
					var monthly = monthly = $('#customer_monthly_payment_').val();

					// not working correctly
					var total = (scheme * (monthly));

					total = parseInt(total, 10) + parseInt(advance, 10);

					console.log('scheme : ' + scheme);
					console.log('monthly : ' + monthly);
					console.log('advance : ' + advance);
					console.log('total : ' + total);

					$('#customer_total_payment_').val(total);
				}
			
			
				</script>
				
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
	
</body>
</html>