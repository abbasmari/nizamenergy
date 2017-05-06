<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="bal.WottageGraphBAL"%>
<%@page import="bean.WottageGraphBean"%>
<%@page import="bal.AlertGraphBAL"%>
<%@page import="bean.AlertGraphBean"%>
<%@page import="bal.CustomerGraphBAL"%>
<%@page import="bean.CustomerGraphBean"%>
<%@page import="bal.LoanPaymentGraphBAL"%>
<%@page import="bean.LoanPaymentGraphBean"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.MobileMoneyGraphBAL"%>
<%@page import="bean.MobileMoneyGraphBean"%>
<%@page import="bean.soldApplianceGraphBean"%>
<%@page import="bal.soldApplianceGraphBAL"%>
<%@page import="bean.ActiveInActiveGraph"%>
<%@page import="bal.ActiveInActiveGraphBAL"%>
<%@page import="bal.UserBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.mapBAL"%>
<%@page import="bal.DistrictBAL"%>
<%@page import="bean.MapBean"%>
<%@page import="bean.DistrictBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.doBeansalesman"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<script src="assets/plugins/parsley/dist/parsley.js"></script>
<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->


<!-- ================== BEGIN BASE JS ================== -->
 
<!-- ================== END BASE JS ================== -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script> -->
<!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->

<!-- ================== BEGIN BASE JS ================== -->



<!-- <script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script> -->


<script src="js/validation.js"></script>

<script>
		
		$(document).ready(function() {
		    $('input').on('keydown', function(event) {
		        if (this.selectionStart == 0 && event.keyCode >= 65 && event.keyCode <= 90 && !(event.shiftKey) && !(event.ctrlKey) && !(event.metaKey) && !(event.altKey)) {
		           var $t = $(this);
		           event.preventDefault();
		           var char = String.fromCharCode(event.keyCode);
		           $t.val(char + $t.val().slice(this.selectionEnd));
		           this.setSelectionRange(1,1);
		        }
		    });
		});
		
		
		
		function contactCall(){
            
            var mob=document.getElementById("salesphone");
		var rmob=document.getElementById("salesphoneResult");
		var patt4=/^[0-9]{11}$/;
		if(!patt4.test(mob.value)){
			rmob.innerHTML="Invalid number";
                            ContactChecker = 0;
		} else{ 
                        rmob.innerHTML="";
                        ContactChecker = 1;
                    }
		
        }
		
		
		function doContactCall(){
            
            var mob=document.getElementById("dophone");
		var rmob=document.getElementById("dophoneResult");
		var patt4=/^[0-9]{11}$/;
		if(!patt4.test(mob.value)){
			rmob.innerHTML="Invalid number";
                            ContactChecker = 0;
		} else{ 
                        rmob.innerHTML="";
                        ContactChecker = 1;
                    }
		
        }
		
		
		
		function doFirstNameCall(){
            
            var mob=document.getElementById("firstname");
			var rmob=document.getElementById("doFirstNameResult");
			var patt4=/^[a-z A-Z]+$/;
			if(!patt4.test(mob.value)){
				rmob.innerHTML="Invalid name";
	       	} else{ 
	            rmob.innerHTML="";
            }
		
        }
		
		
		function doAddressCall(){
            alert("doAddressCall");
            var mob=document.getElementById("address");
			var rmob=document.getElementById("addressResult");
			//var patt4=/^[a-z A-Z]+$/;
			alert(mob.value);
			if(mob.value=="" || mob.value.length<5  ){
				alert("InValid");
				rmob.innerHTML="Invalid Address";
		    } else{ 
		    	alert("valid");
                 rmob.innerHTML="";
            }
		
        }
		
		
		function doEmailCall(){
            
            var mob=document.getElementById("doemail");
		var rmob=document.getElementById("doemailResult");
		var patt4= /^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if(!patt4.test(mob.value)){
			rmob.innerHTML="Invalid email";
                            ContactChecker = 0;
		} else{ 
                        rmob.innerHTML="";
                        ContactChecker = 1;
                    }
		
        }
		
		
		
		</script>




<script>
       
        
        
        
        var request;
        var requestphone;
        function emailcheck()
        {
            
            var v = document.DO_form.doemail.value;
            var url = "checkemail.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                request = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                request.onreadystatechange = getInfo;
                request.open("GET", url, true);
                request.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfo() {
           
            if (request.readyState == 4) {
                
                var val =request.responseText;
                
                if(val>=1){
                $('#messageemailvalid').show();
        $('#messageemailvalid').delay(5000).fadeOut('slow');
    $('#submit').prop('disabled', true);
      
        } 
               else {  $('#submit').prop('disabled', false);}
               // document.getElementById('amit').innerHTML = val;
            }
    }
        
        function emailphone()
        {
            
            
            var v = document.DO_form.dophone.value;
            var url = "checkphone.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                requestphone = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                requestphone = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                requestphone.onreadystatechange = getInfophone;
                requestphone.open("GET", url, true);
                requestphone.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfophone() {
           
            if (requestphone.readyState == 4) {
                
                var val =requestphone.responseText;
                
                if(val>=1){
                $('#messagephonevalid').show();
        $('#messagephonevalid').delay(5000).fadeOut('slow');
   
    $('#submit').prop('disabled', true);
      
        } 
               else {  $('#submit').prop('disabled', false);}
               // document.getElementById('amit').innerHTML = val;
            }
    }
        
        
  function chechcnic()
        {
            
            
            var v = document.DO_form.docnic.value;
            var url = "chechcnicDo.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                requestphone = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                requestphone = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                requestphone.onreadystatechange = getInfocnic;
                requestphone.open("GET", url, true);
                requestphone.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfocnic() {
             
            if (requestphone.readyState == 4) {
                
                var val =requestphone.responseText;
               
                if(val>=1){
	                $('#messagenicvalid').show();
			        $('#messagenicvalid').delay(5000).fadeOut('slow');
			  
			    	$('#submit').prop('disabled', true);
			      
			    } 
               else {  $('#submit').prop('disabled', false);}
               // document.getElementById('amit').innerHTML = val;
            }
    }
        
        
        
        
        //SALESMAN SCRIPT
        
        function emailchecknoc()
        {
            
            var v = document.Salesman_form.email_noc.value;
            var url = "checkemail.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                request = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                request.onreadystatechange = getInfonoc;
                request.open("GET", url, true);
                request.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfonoc() {
           
            if (request.readyState == 4) {
                
                var val =request.responseText;
                
                if(val>=1){
                $('#messageemailvalid_noc').show();
        $('#messageemailvalid_noc').delay(5000).fadeOut('slow');
    $('#submit').prop('disabled', true);
      
        } 
               else {  $('#submit').prop('disabled', false);}
               // document.getElementById('amit').innerHTML = val;
            }
    }
        
        function emailphonenoc()
        {
           
            
            var v = document.Salesman_form.phone_noc.value;
           
            var url = "checkphone.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                requestphone = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                requestphone = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                requestphone.onreadystatechange = getInfophonenoc;
                requestphone.open("GET", url, true);
                requestphone.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfophonenoc() {
           
            if (requestphone.readyState == 4) {
                
                var val =requestphone.responseText;
                
                if(val>=1){
                $('#messagephonevalid_noc').show();
        $('#messagephonevalid_noc').delay(5000).fadeOut('slow');
   
    $('#submit').prop('disabled', true);
      
        } 
               else {  $('#submit').prop('disabled', false);}
               // document.getElementById('amit').innerHTML = val;
            }
    }
        
        
  function chechcnicnoc()
        {
            
            
            var v = document.Salesman_form.cnic_noc.value;
            var url = "chechcnicDo.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                requestphone = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                requestphone = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                requestphone.onreadystatechange = getInfocnicnoc;
                requestphone.open("GET", url, true);
                requestphone.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfocnicnoc() {
             
            if (requestphone.readyState == 4) {
                
                var val =requestphone.responseText;
               
                if(val>=1){
                $('#messagenicvalid_noc').show();
        $('#messagenicvalid_noc').delay(5000).fadeOut('slow');
  
    $('#submit').prop('disabled', true);
      
        } 
               else {  $('#submit').prop('disabled', false);}
               // document.getElementById('amit').innerHTML = val;
            }
    }
        
        
        function checkphone()
        {

            var v = document.salesman.salesphone.value;
            var size=document.salesman.salesphone.value.length;
           if(size<11 ){ $('#salesphonnumber').show();
                    $('#salesphonnumber').delay(5000).fadeOut('slow');
                    $('#click').attr('disabled', 'disabled');
           
    
    
    }
            var url = "salesmanphonecheck.jsp?val=" + v;
            if (window.XMLHttpRequest) {
                request = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try {
                request.onreadystatechange = getInfophone;
                request.open("GET", url, true);
                request.send();
            } catch (e) {
                alert("Unable to connect to server");
            }
        }
        function getInfophone() {

            if (request.readyState == 4) {

                var val = request.responseText;

                if (val >= 1) {
                    $('#messagephonevalid').show();

                    $('#messagephonevalid').delay(5000).fadeOut('slow');
                    $('#click_Salesman').attr('disabled', 'disabled');
                }
                else {  $('#click_Salesman').prop('disabled', false);}
                // document.getElementById('amit').innerHTML = val;
            }
        }  
        
        
        function changecity(){
        	var district_id=$('#district_so').val();
        	
/*         	$.ajax({
    			type : 'GET',
    			url : 'getCitySo.jsp',
    			dataType: 'application/json',
    			data : ({
    				district_id : district_id
    			}),
    			success : function(data, event) {
    				alert(data);
    				
    			}

    		}); */
        	$.ajax({
        		type: "GET",
        		url:'getCitySo.jsp',
        		data:({
        			district_id : district_id
        		}),
        		success : function(data){
        		
        		
        			var myData = JSON.parse(data);

        			$(document).ready(function() {
        				var $grouplist = $('#city');
        			    $.each(myData, function() {
        			    	$('<option value='+this.City_id+'>' + this.City_name + '</option>').appendTo($grouplist);  
        			    });
        			});
        		
        		}
        	});
        	
        	
        }

	
        function getResults(val) {
    		if (val.checked && val.value == "Car") {
    			$('#licence').show();
    			$('#label').show();
    		} else if (val.checked && val.value == "Bike") {
    			$('#licence').show();
    			$('#label').show();
    		} else if (val.checked && val.value == "None") {
    			$('#licence').hide();
    			$('#label').hide();
    		}
    	};
	
	function getEducation(val) {
// 	    elem.checked && elem.value == "Show" ? $('#licence').show() : $('#label').show();
	    if(val.checked && val.value == "Educated") {
	    	$('#college').show();
	        $('#degree').show();
	        $('#dateOfStart').show();
	        $('#dateOfEnd').show();
	        $('#percentage').show();
	        $('#a').show();
	        $('#b').show();
	        $('#c').show();
	        $('#d').show();
	        $('#e').show();
	        
	    }else {
	    	$('#college').hide();
	        $('#degree').hide();
	        $('#dateOfStart').hide();
	        $('#dateOfEnd').hide();
	        $('#percentage').hide();
	        $('#a').hide();
	        $('#b').hide();
	        $('#c').hide();
	        $('#d').hide();
	        $('#e').hide();
	    }
	};
	
	function getMarritalStatus(val) {
	    if(val.value == "Married") {
	    	$('#wifephone').show();
	        $('#wifename').show();
	        $('#wifeCnic').show();
	        $('#wifedateOfBirth').show();
	        $('#w').show();
	        $('#x').show();
	        $('#y').show();
	        $('#z').show();
	    }else{
	    	$('#wifephone').hide();
	        $('#wifename').hide();
	        $('#wifeCnic').hide();
	        $('#wifedateOfBirth').hide();
	        $('#w').hide();
	        $('#x').hide();
	        $('#y').hide();
	        $('#z').hide();	   
	    }
	};
	
	
	
	function getUrlParameter(sParam) {
		   var sPageURL = decodeURIComponent(window.location.search
		     .substring(1)), sURLVariables = sPageURL.split('&'), sParameterName, i;

		   for (i = 0; i < sURLVariables.length; i++) {
		    sParameterName = sURLVariables[i].split('=');

		    if (sParameterName[0] === sParam) {
		     return sParameterName[1] === undefined ? true
		       : sParameterName[1];
		    }
		   }
		  };
	
	
	function getUploadedData(){
		
		var doId = getUrlParameter('doId');
		
		$.ajax({
			url : 'GetDataById',
			dataType : 'json',
			method : 'POST',
			data : {
				action : "doInfo",
				id : doId
			},
			success : function(data)
			{
				
				

				$('#firstname').val(data.doDetail.user_name);
				$('#joiningDate').val(data.doDetail.joining_date);
				$('#dateOfBirth').val(data.doDetail.date_of_birth)
				$('#doCnic').val(data.doDetail.user_cnic)
				
				
				RadionButtonSelectedValueSet('gender', data.doDetail.user_gender);
				RadionButtonSelectedValueSet('marritalStatus', data.doDetail.marrital_status);

				
				$('#bloodGroup').val(data.doDetail.blood_group);
				
// 				alert(data.doDetail.user_phone);
// 				alert(data.doDetail.user_mobile);
				
				
				if(data.doDetail.user_phone.length>2){
					data.doDetail.user_phone = data.doDetail.user_phone.substring(2);
					data.doDetail.user_phone = [data.doDetail.user_phone.slice(0, 3), '-', data.doDetail.user_phone.slice(3)].join('');
// 					alert(data.doDetail.user_phone);
					$('#dophone1').val(data.doDetail.user_phone);
				}
				
				if(data.doDetail.user_mobile.length>2){
					data.doDetail.user_mobile = data.doDetail.user_mobile.substring(2);
					data.doDetail.user_mobile = [data.doDetail.user_mobile.slice(0, 3), '-', data.doDetail.user_mobile.slice(3)].join('');
// 					alert(data.doDetail.user_mobile);
					$('#dophone2').val(data.doDetail.user_mobile);
				}
				
// 				if(data.doDetail.user_phone.length>2)$('#dophone1').val(data.doDetail.user_phone.substring(2));
				
				
// 				if(data.doDetail.user_mobile.length>2){
// 				//alert(data.doDetail.user_mobile);
// 				$('#dophone2').val(data.doDetail.user_mobile.substring(2));
// 				}
				
				$('#doemail').val(data.doDetail.user_email);
				
				
				RadionButtonSelectedValueSet('toggle', data.doDetail.vehicle);
				if(data.doDetail.vehicle == 'Car' || data.doDetail.vehicle == 'Bike' ){
					$('#licence').show();
					$('#label').show();
					$('#licence').val(data.doDetail.licence_no);
				}else{
					$('#licence').hide();
					$('#label').hide();
				}

				$('#address').val(data.doDetail.user_address);
				
				RadionButtonSelectedValueSet('education', data.doDetail.educated);

				
				if(data.doDetail.educated == 'Educated'){
					$('#college').val(data.doDetail.institute);
					$('#degree').val(data.doDetail.degree);
					$('#dateOfStart').val(data.doDetail.start_date);
					$('#dateOfEnd').val(data.doDetail.end_date);
					$('#percentage').val(data.doDetail.percentage);

					$('#a').show();
					$('#b').show();
					$('#c').show();
					$('#d').show();
					$('#e').show();
					
					$('#college').show();
					$('#degree').show();
					$('#dateOfStart').show();
					$('#dateOfEnd').show();
					$('#percentage').show();
				}
				 
				
				$('#salary').val(data.doDetail.user_basic_salary);
// 				$('#imageId').val(data.doDetail.image_id);
				//alert(data.doDetail.image_id);
				showImage(data.doDetail.image_id);
				
				$('<input>').attr({
					
					type : 'hidden',
					id : 'doId',
					name : 'doId',
					value : doId
				}).appendTo('#DO_form');
				
				}
		});
	}

	
	function RadionButtonSelectedValueSet(name, SelectdValue) {
		$('input[name="' + name + '"][value="' + SelectdValue + '"]').prop(
				'checked', true);
	}
	
    </script>


</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
	%>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container" class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp"%>
		<!-- end #header -->

		
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			
			<!-- end breadcrumb -->
			<!-- begin page-header -->
<!-- 			<h1 class="page-header"> -->
<!-- 				SuperAdmin Form -->
<!-- 			</h1> -->
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" style="margin-left: 7%; margin-right: 7%; margin-top: 3%;">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!-- <a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a>  <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a>  <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a> -->
							</div>
							
<%-- 								
// 					String err = (String)request.getAttribute("msg");
<%-- 					if(err != "" || err != null){ %> --%>
<%-- 						<span><%=err %></span> --%>
<%-- 						<%}else{ %> --%>
<%-- <%-- 						<span><%=err %></span> --%> 
<%-- 						<%} %> --%>
							
							
							<h4 class="panel-title">Super Admin Form</h4>
						</div>
						<div class="panel-body">

							<div id="wizard">
<!-- 								<ol style="padding-right: 5%;"> -->
<!-- 									<li>District Officer</li> -->
<!-- 									<li>Salesman</li> -->
<!-- 									<li>Service Operation Controller</li> -->

<!-- 								</ol> -->
								<!-- begin wizard step-1 -->

								<!--Add District Officer -->
								<div>
									<fieldset>
										
										<!-- begin row -->
										<div class="row">


											<form class="form-horizontal" action="AddUsersServlet" 
											method="post" id="DO_form" name="DO_form" enctype="multipart/form-data">
												<legend><span id="add">Update District Officer</span></legend>
												<center><span style="color: red;">Fields marked with * are required</span></center>
												<legend style="margin-top:2%"><center>Personal information</center></legend>
												<div class="form-group">
													<label for="inputEmail3" class="col-md-3 control-label"><i style="color: red;">*</i> Full Name:</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="firstname" onblur="getData();"
															name="firstname" placeholder="" required="required" 
															oninvalid="this.setCustomValidity('Please enter the full name')"
															onchange="nameValidation('firstname','doFirstNameResult','Invalid name'), this.setCustomValidity('');" onkeypress = "nameCharahterValidation('firstname')"/>
														<span style="color: red; font-size: 12px;"
															id="doFirstNameResult"></span>
													</div>
												</div>
											
												
												
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3"><i style="color: red;">*</i> Date of Joining:</label>
													<div class="col-sm-6">
													
														<input type="date" class="form-control" id="joiningDate"
															name="joiningDate" required="required"
															oninvalid="this.setCustomValidity('Please enter the date of joining')"
															onchange="this.setCustomValidity('')">

													</div>
												</div>
												
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3"><i style="color: red;">*</i> Date of Birth:</label>
													<div class="col-sm-6">
													
													<input type="date" class="form-control" id="dateOfBirth"
															name="dateOfBirth" onChange="ageValidationDO(this, 'dateOfBirthResult','doCnic','cnicLabel')" required="required"
															oninvalid="this.setCustomValidity('Please enter the Date of Birth')">
<!-- 															onchange="this.setCustomValidity('')"> -->
															 <span
															style="color: red; font-size: 12px;" id="dateOfBirthResult"></span>

													</div>
												</div>
												<div class="form-group">
													<div id="messagenicvalid" name="messagenicvalid"
														style="display: none; color: red">CNIC exist
														already</div>
													<label for="inputPassword3" class="col-sm-3 control-label" id="cnicLabel"><i style="color: red;">*</i> CNIC:</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="doCnic" data-table="user" data-column="user_cnic"
															name="cnic" maxlength="15" placeholder = "11111-1111111-1"
															onchange="cnicValidation('doCnic','cnicMsg'),existingMobileNumber('doCnic','cnicMsg',this)" onkeypress = "setNicDash(event,'doCnic')" />
															 <span
															style="color: red; font-size: 12px;" id="cnicMsg"></span>
													</div>
												</div>
												
<!-- 												<div class="form-group"> -->
<!-- 													<label for="inputPassword3" class="col-sm-3 control-label">Posting Location*:</label> -->
<!-- 													<div class="col-sm-6"> -->
<!-- 														<select class="form-control" id="district" name="district" -->
<!-- 															required="required" -->
<!-- 															oninvalid="this.setCustomValidity('Please Choose Posting Location')" -->
<!-- 															onchange="this.setCustomValidity('')"> -->
<!-- 															<option selected disabled>--Choose District--</option> -->
 															<% 
// 																//UserBean bean = (UserBean) session.getAttribute("email");
// 															    ArrayList<DistrictBean> list8 = DistrictBAL.getDistrict_names();
// 							                                    for (int z = 0; z < list8.size(); z++) {
// 								                                   int id = list8.get(z).getDistrict_id();
 															%> 
<%-- 															<option value=<%= id %>> --%>
<%-- 																<%=list8.get(z).getDistrict_name()%></option> --%>
															<% 
// 																}
															%> 
<!-- 														</select> -->
<!-- 													</div> -->
<!-- 												</div> -->
												
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label">Gender:</label>
													<div class="col-sm-6">
														
														Male <input type="radio" name="gender" id="male" 
															value="Male" /> 
														Female <input type="radio" name="gender" id="female" 
															value="Female" />
													</div>
												</div>
												
											
												
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label">Marital
														Status:</label>
													<div class="col-sm-6">
														Single <input type="radio" name="marritalStatus"
															id="single" value="Single" />
														Married <input type="radio" name="marritalStatus"
															id="married" value="Married" />
													</div>
												</div>
											
											<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label">Blood Group:</label>
													<div class="col-sm-6">
														<select class="form-control" id="bloodGroup" name="bloodGroup" required="required"
															oninvalid="this.setCustomValidity('Please choose Marital Status')"
															onchange="this.setCustomValidity('')" ">
															<option selected disabled>--Choose Blood Group--</option>
															<option value="A+ve">A+ve</option>
															<option value="A-ve">A-ve</option>
															<option value="B+ve">B+ve</option>
															<option value="B-ve">B-ve</option>
															<option value="AB+ve">AB+ve</option>
															<option value="AB-ve">AB-ve</option>
															<option value="O+ve">O+ve</option>
															<option value="O-ve">O-ve</option>
														</select> 
													</div>
												</div>
												
												
												
												<div class="form-group">
													<div id="messagephonevalid" name="messagephonevalid"
														style="display: none; color: red;margin-left: 385px;">Mobile number
														already exist</div>
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Primary Mobile
														Number:</label>
													<div class="col-sm-1">
														<input  type="text" class="form-control" value="92" style="width:65%" readonly />
													</div>
													<div class="col-sm-5">
														<input type="text" class="form-control" id="dophone1" data-table="user" data-column="user_phone" data-type="phone" placeholder = "333-333333333"
															name="phone1"  maxlength="11" required="required" onkeypress = "setMobileDash(event,'dophone1')" 
															onkeyup="mobileNumberValidation('dophone1','dophoneResult1'), existingData('dophone1','dophoneResult1',this)">
														<span style="color: red; font-size: 12px;"
															id="dophoneResult1"></span>
													</div>
												</div>
												
												<div class="form-group">
													<div id="messagephonevalid" name="messagephonevalid"
														style="display: none; color: red;margin-left: 385px;">Mobile number
														already exist</div>
													<label for="inputPassword3" class="col-sm-3 control-label">Secondary Mobile
														Number </label>
													<div class="col-sm-1">
														<input  type="text" class="form-control" value="92" style="width:65%" readonly />
													</div>
													<div class="col-sm-5">
														<input type="text" class="form-control" id="dophone2" data-table="user" data-column="user_phone" data-type="phone" placeholder = "333-333333333"
															name="phone2"  maxlength="11" onkeypress = "setMobileDash(event,'dophone2')"
															onchange="mobileNumberValidation('dophone2','dophoneResult2');existingData('dophone2','dophoneResult2',this);">
														<span style="color: red; font-size: 12px;"
															id="dophoneResult2"></span>
													</div>
												</div>
												
											

												<div class="form-group">
													<div id="messageemailvalid" name="messageemailvalid"
														style="display: none; color: red">Please Enter
														Email already Exist on our system</div>
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Email:</label>
													<div class="col-sm-6">
														<input type="email" class="form-control" id="doemail"
															name="email" data-table="user" data-column="user_email" data-type="email" placeholder=""
															onkeyup="emailValidation('doemail','doemailResult');existingData('doemail','doemailResult',this)"> <span
															style="color: red; font-size: 12px;" id="doemailResult"></span>
													</div>
												</div>
												
												<div class="form-group">
														<label for="inputPassword3" class="col-sm-3 control-label"> Vehicle:</label>
													<div class="col-sm-6" style="margin-top: 1%">
														<input type="radio" class="" 
															name="toggle" value="Car" onClick="getResults(this)">Car &nbsp;&nbsp;&nbsp;&nbsp;
														<input type="radio" class=""
															name="toggle" value="Bike" onClick="getResults(this)">Bike &nbsp;&nbsp;&nbsp;&nbsp; 
															<input type="radio" class=""
															name="toggle" value="None" onClick="getResults(this)">None &nbsp;&nbsp;&nbsp;&nbsp;
													</div>
												</div>
												
												<div class="form-group">
														<label for="inputPassword3" class="col-sm-3 control-label" id="label" >Driving Licence</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="licence" data-table="user" data-column="licence_no"
															name="licence"
															onchange="existingMobileNumber('licence','licenceMsg',this)" />
															<!-- numericValidation('licence','licenceMsg','Invalid Licence Number'), -->
															 <span
															style="color: red; font-size: 12px;" id="licenceMsg"></span>
													</div>
												</div>
												
											

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Address:</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="address"
															name="address" required="required"
															oninvalid="this.setCustomValidity('Please Enter Address')"
															onchange="addressValidation('address','addressResult'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;" id="addressResult"></span>
													</div>
												</div>
												
												
									
										<legend><center>Qualification</center></legend>		
										
												<div class="form-group">
														<label for="inputPassword3" class="col-sm-3 control-label">Education:</label>
													<div class="col-sm-6" style="margin-top:1%">
														<input type="radio" class="" 
															name="education" value="Educated" onClick="getEducation(this)">Educated &nbsp;&nbsp;&nbsp;&nbsp;
														<input type="radio" class=""
															name="education" value="Uneducated" onClick="getEducation(this)">Uneducated 
													</div>
												</div>
												
											<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label" id="a">College/University:</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="college"
															name="college" 
															oninvalid="this.setCustomValidity('Please Enter College/University')"
															onchange="nameValidation('college','collegeResult','Invalid College/University/Institute'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;" id="collegeResult"></span>
													</div>
												</div>
												
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label" id="b">Certificate/Degree:</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="degree"
															name="degree" 
															oninvalid="this.setCustomValidity('Please Enter degree')"
															onchange="nameValidation('degree','degreeResult','Invalid Degree/Certificate/Diploma'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;" id="degreeResult"></span>
													</div>
												</div>
												
<!-- 												<div class="form-group"> -->
<!-- 													<label for="inputPassword3" class="col-sm-3 control-label">Specialization</label> -->
<!-- 													<div class="col-sm-6"> -->
<!-- 														<input type="text" class="form-control" id="specialization" -->
<!-- 															name="specialization" required="required" -->
<!-- 															oninvalid="this.setCustomValidity('Please Enter specialization')" -->
<!-- 															onchange="nameValidation('specialization','specializationResult','Invalid Specialization'), this.setCustomValidity('')"> -->
<!-- 															<span style="color: red; font-size: 12px;" id="specializationResult"></span> -->
<!-- 													</div> -->
<!-- 												</div> -->
												
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3" id="c">Start Date:
														</label>
													<div class="col-sm-6">
													
													<input type="date" class="form-control" id="dateOfStart"
															name="dateOfStart" 
															oninvalid="this.setCustomValidity('Please enter the Date of Start')"
															onchange="this.setCustomValidity('')">

													</div>
												</div>
												
												
												
												
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3" id="d">End Date:
														</label>
													<div class="col-sm-6">
													<input type="date" class="form-control" id="dateOfEnd"
															name="dateOfEnd" 
															oninvalid="this.setCustomValidity('Please enter the Date of End')"
															onchange="this.setCustomValidity('')">

													</div>
												</div>
										
											<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label" id="e">Percentage:</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="percentage"
															name="percentage" 
															oninvalid="this.setCustomValidity('Please Enter percentage')" onchange="numericValidation('percentage', 'percentageResult','Invalid member') ; this.setCustomValidity('')"/>
														<span style="color: red; font-size: 12px;" id="percentageResult"></span>
													</div>
											</div>
											
											
												<div class="form-group" id="loop">
        										
    											</div>
									
<legend></legend>
												
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Salary:</label>
													<div class="col-sm-6">
														<input type="text" id="salary" class="form-control"
															name="salary" required="required"
															oninvalid="this.setCustomValidity('Please Enter Basic Salary')"
															onchange="numericValidation('salary','salaryResult','Invalid Number'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
															id="salaryResult"></span>
													</div>
												</div>	
												
												<!-- <div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3">Upload
														image</label>
													<div class="col-sm-6">
														<input type="file" name="photo" class="form-control" id="doImage" placeholder="upload image">
															<input type="hidden" name="imageId" id="imageId" >
															<div id="show"></div>
														
													</div>
												</div> -->
												
												<div class="form-group">
													<div class="col-sm-offset-3 col-sm-6">
														<input name="btn" type="submit" id="submit" value="Update"
															class="btn btn-block btn-info">
													</div>
												</div>
												
											</form>


										</div>
									</fieldset>
								</div>
								<!-- end wizard step-1 -->


								
							</div>

						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-12 -->

			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->





		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->


	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/plugins/morris/raphael.min.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/js/chart-morris.demo.min.js"></script>
<!-- 	<script src="assets/js/apps.min.js"></script> -->
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<!-- 	 -->
	<script src="assets/plugins/bootstrap-wizard/js/bwizard.js"></script>
	<script src="assets/js/form-wizards.demo.min.js"></script>
	
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script src="assets/plugins/parsley/dist/parsley.js"></script>


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<!-- 	<script src="assets/plugins/gritter/js/jquery.gritter.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.time.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.resize.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.pie.min.js"></script> -->
	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
	
	
	<!-- 	<script src="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script> -->
	<!-- 	<script src="assets/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script> -->
	<!-- 	<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> -->
	<script src="assets/js/dashboard.min.js"></script>
	<!-- 	<script src="assets/js/apps.min.js"></script> -->
	<script type="text/javascript" src="assets/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="assets/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

<script>

$('#doImage').fileupload({
	
	  url : 'InsertImageController',
	  method : 'POST',
	  enctype : 'multipart/form-data',
	  beforeSend : function(){
			$('#show').append('<span class = "fa fa-circle-o-notch fa-spin"></span>')
		},
	  add : function(e, data){
			
	   data.submit().success(function(result, textStatus, jqHXR){
		  
	    var json = $.parseJSON(result);
	    
	    console.log(json);
	    var img = document.createElement('img');
	    var p = document.createElement('p');
	    $(img).attr('width', '200')
	    $(img).attr('height', 'auto')
        $(img).attr('src', "ShowImage?id="+json.imageId)
	    $('#imageId').attr('value', json.imageId)
	  //  alert($('#imageId').val());
	    //alert("Image id"+json.imageId)
	    $(p).text(json.filename)
	    
	    $('#show').append(img)
	    $('#show').append(p)
	    $('#show span').remove()
	    
	   })
	  }
	 });
</script>	
	
	
	<script>
	function getData()
	{
// 		alert('hi')
		var firstname = $("#firstname").val();
		var joiningDate = $("#joiningDate").val();
		var dateOfBirth = $("#dateOfBirth").val();
		var doCnic = $('#doCnic').val();
		var dophone1 = $("#dophone1").val();
		var salary = $("#salary").val();
		var address = $("#address").val();
		if( (firstname.lenght != 0)  &&  (joiningDate.length != 0) && (dateOfBirth.length != 0) && (doCnic.lenght != 0) && (dophone1.length != 0) && (salary.length != 0) && (address.length != 0)){
		console.log(firstname.length+" : "+joiningDate.length+" : "+dateOfBirth.length+" : "+doCnic.length+" : "+dophone1.length+" : "+salary.length+" : "+address.length );
			$('#submit').prop("disabled", false);
// 		alert('if');
		var firstnameup = 1;
		var dateOfBirthup = 1;
		var doCnicup = 1;
		var dophone1up = 1;
		var doemailup = 1;
		var salaryup = 1;
		var addressup = 1;
	
		
// 			doFormUpdate(firstnameup,dateOfBirthup,doCnicup,dophone1up,doemailup,salaryup,addressup);
// 		doFormUpdate();
		}else{
			var firstnameup = 0;
			var dateOfBirthup = 0;
			var doCnicup = 0;
			var dophone1up = 0;
			var doemailup = 0;
			var salaryup = 0;
			var addressup = 0;
// 			alert('else')
			$('#submit').prop("disabled", true);
// 			doFormUpdate(firstnameup,dateOfBirthup,doCnicup,dophone1up,doemailup,salaryup,addressup);
		}
	}
	
	$(document).ready(function() {
			App.init();
			FormWizard.init();
			$('#firstnameup, #dateOfBirthup, #doCnicup, #dophone1up, #doemailup, #salaryup, #addressup').change(doFormUpdate);

			
		});
		
		
		$(document).ready(function() {
		    $('#licence').hide();
		    $('#label').hide();
		    $('#college').hide();
		    $('#degree').hide();
		    $('#dateOfStart').hide();
		    $('#dateOfEnd').hide();
		    $('#percentage').hide();
		    $('#a').hide();
		    $('#b').hide();
		    $('#c').hide();
		    $('#d').hide();
		    $('#e').hide();
		    $('#wifephone').hide();
		    $('#wifename').hide();
		    $('#wifeCnic').hide();
		    $('#wifedateOfBirth').hide();
		    $('#w').hide();
		    $('#x').hide();
		    $('#y').hide();
		    $('#z').hide();
		    $('#doCnic').show();
		    $('#cnicLabel').show();
		    

			});
		
		function showImage(imageId) {
// 			alert('ok' + imageId);
			if (imageId != 0 && imageId != null) {
// 				alert('if');
				$('#show').append(
						'<span class = "fa fa-circle-o-notch fa-spin"></span>');
				var img = document.createElement('img');
				$(img).attr('width', '200');
				$(img).attr('height', 'auto');
				$(img).attr('src', "ShowImage?id=" + imageId);
				$('#imageId').attr('value', imageId);
				$('#show').append(img);
				$('#show span').remove();
			}
		}
		
		
		
		window.onload = getUploadedData();
		
	</script>

</body>


</html>
