<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bal.FieldOfficerBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="bal.DistrictOfficerBAL"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bean.CityBean"%>
<%@page import="bean.OccupationBean"%>
<%@page import="bean.SalaryBean"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.SalesManBean"%>

<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>


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

<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<script src="js/validation.js"></script>

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
<link href="assets/plugins/ionRangeSlider/css/ion.rangeSlider.css"
	rel="stylesheet" />
<link
	href="assets/plugins/ionRangeSlider/css/ion.rangeSlider.skinNice.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"
	rel="stylesheet" />
<link
	href="assets/plugins/password-indicator/css/password-indicator.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-combobox/css/bootstrap-combobox.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css"
	rel="stylesheet" />
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />

<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<link href="assets/plugins/select2/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->


</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
	String msg=(String)request.getAttribute("msg");
	if(msg==null){msg="";}
		ArrayList<InventoryBean> inventory = InventoryBAL.getAppliances();
		ArrayList<CityBean> cities = DistrictOfficerBAL.getDistrictCities(bean.getUser_district());
		ArrayList<HashMap<String, String>> products=FieldOfficerBAL.getProductPrice();
		ArrayList<HashMap<String, String>> folist = FieldOfficerBAL.getFieldOfficers(bean.getUserId());
		ArrayList<DistrictBean> district = DistrictOfficerBAL.getDistrict();
		ArrayList<SalesManBean> salesman_list = SalesmanBAL.getSallesman(bean.getUserId());
		ArrayList<OccupationBean> occupation = CustomerBAL.getOccupations();
		ArrayList<SalaryBean> salary = CustomerBAL.Customer_salary_range();
	%>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
		<!-- begin #content -->
		<div id="content" class="content">


			<!-- begin row -->
			<div class="row">

				<div class="col-md-12">
					<div class="panel panel-inverse"
						style="margin-left: 7%; margin-right: 7%; margin-top: 3%;">


						



							<!-- begin wizard step 4 -->
							<div class="box feildofficer">
								
									
									<!-- begin row -->
									<div class="row">
										<!-- begin col-6 -->
										<form class="form-horizontal" action="AddCashCustomer"
											data-parsley-validate="true" method="post" 
											 >
											 
											 
											 
											 
											<center style="margin: 10px;">
												<span style="color: red;">Add Cash Customer</span>
												
											</center>
											<center style="background: greenyellow;margin: 10px; font-size: 22px;">
												
												<span id="msg_form_completion"><%=msg %></span>
												
											</center>
											<legend>
												<center>Customer Information</Center>
											</legend>

											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Tehsil:</label>
												<div class="col-sm-6">
													<select class="form-control" id="scity" name="scity"
														required="required"
														oninvalid="this.setCustomValidity('Please enter the valid city name')"
														onchange="this.setCustomValidity('')">
														<%
															for (int m = 0; m < cities.size(); m++) {
																int city_id = cities.get(m).getCity_id();
														%>
														<option value="<%=city_id%>">
															<%=cities.get(m).getCity_name()%></option>
														<%
															}
														%>
													</select>

												</div>
											</div>

											<div class="form-group">
												<label for="inputEmail3" class="col-md-3 control-label"><i
													style="color: red;">*</i> Customer Name:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="sfirstname"
														data-parsley-required="true" name="sfirstname"
														placeholder=""
														onchange="nameValidation('sfirstname','sfoFirstNameResult','Invalid name'); "
														onkeypress="nameCharahterValidation('sfirstname')" /> <span
														style="color: red; font-size: 12px;"
														id="sfoFirstNameResult"></span>
												</div>
											</div>
	

											<div class="form-group" id="cnic">
												
												<label for="inputPassword3" class="col-sm-3 control-label"
													><i style="color: red;">*</i> CNIC:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="customerNic" data-table="user" data-column="user_cnic" data-type="cnic"
															name="customerNic" maxlength="15" placeholder = "xxxxx-xxxxxxx-x"
														onchange="cnicValidation('customerNic','cnicMsg')" onkeypress="setNicDash(event,'customerNic')"
														 /> 
														<span
															style="color: red; font-size: 12px;" id="cnicMsg"></span>
												</div>
												
												
											</div>




											<div class="form-group">
													<div id="messagephonevalid" name="messagephonevalid"
														style="display: none; color: red;margin-left: 385px; " >Mobile number
														already exist</div>
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Primary Phone:</label>
													<div class="col-sm-1">
														<input  type="text" class="form-control" value="92" style="width:65%" readonly />
													</div>
													<div class="col-sm-5">
														<input type="text" class="form-control" id="phone1" data-type="phone" data-table="user" data-column="user_phone" placeholder = "xxx-xxxxxxx"
															name="phone1"  maxlength="11" required="required" onkeypress = "setMobileDash(event,'dophone1')"
															onchange="mobileNumberValidation('phone1','dophoneResult1'); ">
														<span style="color: red; font-size: 12px;"
															id="dophoneResult1"></span>
													</div>
												</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Address:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="saddress"
														name="saddress" required="required"
														oninvalid="this.setCustomValidity('Please Enter Address')"
														onchange="addressValidation('saddress','saddressResult'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="saddressResult"></span>
												</div>
											</div>


											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Field Officer:</label>
												<div class="col-sm-6">
													<select class="form-control" id="fo_id" name="fo_id"
														
														oninvalid="this.setCustomValidity('Please enter the valid city name')"
														onchange="this.setCustomValidity('')">
														<option value="0">Please Select FO</option>
														<%
															for (int m = 0; m < folist.size(); m++) {
																int fo_id =Integer.parseInt(folist.get(m).get("foid")) ;
														%>
														<option value="<%=fo_id%>">
															<%=folist.get(m).get("foName")%></option>
														<%
															}
														%>
													</select>

												</div>
											</div>

										<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Nizam Dost:</label>
												<div class="col-sm-6">
													<select class="form-control" id="nd_id" name="nd_id"
														
														oninvalid="this.setCustomValidity('Please enter the valid city name')"
														onchange="this.setCustomValidity('')">
														
														<option value="0">Please Select Nd</option>
														<%
															for (int m = 0; m < salesman_list.size(); m++) {
																int nd_id = salesman_list.get(m).getSalesmanId();
														%>
														<option value="<%=nd_id%>">
															<%=salesman_list.get(m).getName()%></option>
														<%
															}
														%>
													</select>

												</div>
											</div>
											
											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i>Price:</label>
												<div class="col-sm-6">
													<select class="form-control" id="product_id" name="product_id"
														required="required"
														oninvalid="this.setCustomValidity('Please enter the valid city name')"
														onchange="this.setCustomValidity('')">
														<%
															for (int m = 0; m < products.size(); m++) {
																int product_id =Integer.parseInt(products.get(m).get("product_id")) ;
														%>
														<option value="<%=product_id%>">
															<%=products.get(m).get("product_name")+" "+products.get(m).get("product_price")%></option>
														<%
															}
														%>
													</select>

												</div>
											</div>
											
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i>Consumer Number:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="consumer_no"
														name="consumer_no" required="required"
														oninvalid="this.setCustomValidity('Please Enter Address')"
														onchange="addressValidation('consumer_no','saddressResultconsumer_no'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="saddressResultconsumer_no"></span>
												</div>
											</div>
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i>GSM Number:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="gsm_no"
														name="gsm_no" required="required"
														oninvalid="this.setCustomValidity('Please Enter Address')"
														onchange="addressValidation('gsm_no','saddressResultgsm_no'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="saddressResultgsm_no"></span>
												</div>
											</div>
											
											<div class="form-group has-feedback"            id="customer_karyana">
              											<label class="col-md-3 control-label" class="radio"><i
													style="color: red;">*</i>Payment Method </label>
              
              												<div class="col-md-3">
             													<label class="radio-inline"> 
             													<input type="radio"     name="payment_method" value="Cash" checked="checked"> Cash </label>
             													<label class="radio-inline"> 
             													<input type="radio"  name="payment_method" value="Credit" >Mobile Payment </label>
            												</div>
             								</div>
											
											
											<input type="hidden" name="sdistrict"
												value="<%=bean.getUser_district()%>"> <input
												type="hidden" name="sdoId" value="<%=bean.getUserId()%>">

											<div class="form-group">
												<div class="col-sm-offset-3 col-sm-6">
													<input type="submit"  
														value="Add Cash Customer" class="btn btn-block btn-info" >
												</div>
											</div>

										</form>
										
									</div>
									
								
							</div>

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
	<!-- 	<script src="assets/plugins/gritter/js/jquery.gritter.js"></script> -->
	<script src="assets/plugins/flot/jquery.flot.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.time.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
	<script
		src="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="assets/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/plugins/bootstrap-daterangepicker/moment.js"></script>
	<script
		src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="assets/js/dashboard.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script
		src="assets/plugins/ionRangeSlider/js/ion-rangeSlider/ion.rangeSlider.min.js"></script>
	<script
		src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<script src="assets/plugins/masked-input/masked-input.min.js"></script>
	<script
		src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
	<script
		src="assets/plugins/password-indicator/js/password-indicator.js"></script>
	<script
		src="assets/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script>
	<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>
	<script
		src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
	<script
		src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput-typeahead.js"></script>
	<script src="assets/plugins/jquery-tag-it/js/tag-it.min.js"></script>
	<script src="assets/plugins/bootstrap-daterangepicker/moment.js"></script>
	<script
		src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="assets/plugins/select2/dist/js/select2.min.js"></script>
	<script src="assets/js/form-plugins.demo.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/bootstrap-wizard/js/bwizard.js"></script>
	<script src="assets/plugins/parsley/dist/parsley.js"></script>
	<script src="assets/js/form-wizards.demo.min.js"></script>

	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
	
	
	
		$(document).ready(function() {
			App.init();
			
		});
	</script>
	
	<script>


	 
	 

	 
</script>
</body>


</html>
<!-- enctype="multipart/form-data" -->