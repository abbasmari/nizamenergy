<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="bal.FieldOfficerBAL"%>
<%@page import="bean.DistrictBean"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="bal.DistrictOfficerBAL"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.CityBean"%>
<%@page import="bean.OccupationBean"%>
<%@page import="bean.SalaryBean"%>
<%@page import="bean.EligibilityExistCustomer"%>
<%@page import="bean.CustomerSearchBean"%>
<%@page import="bean.AlertsForNumber"%>
<%@page import="bean.NumberOfMsgFrom"%>
<%@page import="bean.ShowMsgAdminBean"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SaDoChatBAL"%>
<%@page import="bal.RecoveryGraphBAL"%>
<%@page import="bal.RecoveryBAL"%>
<%@page import="java.io.File"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.InputStream"%>
<%@page import="connection.Connect"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bean.Suggestion"%>
<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bean.CustomerMessageBean"%>
<%@page import="bean.UserBean"%>
<%@page import="bal.Payment_loanNewBAL"%>
<%@page import="bal.CustomerBAL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CustomerInfoBean"%>
<%@page import="bal.SalesmanBAL"%>
<%@page import="bal.EligibilityBAL"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bal.ApplianceBAL"%>
<%@page import="bean.ApplianceBean"%>
<%@page import="bal.SoldtoBAL"%>
<%@page import="bean.SoldToBean"%>
<%@page import="bean.CustomerBean"%>
<%@page import="bean.RecoveryGraphBean"%>
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


<script>
	$(document).ready(
			function() {
				$('input').on(
						'keydown',
						function(event) {
							if (this.selectionStart == 0 && event.keyCode >= 65
									&& event.keyCode <= 90 && !(event.shiftKey)
									&& !(event.ctrlKey) && !(event.metaKey)
									&& !(event.altKey)) {
								var $t = $(this);
								event.preventDefault();
								var char = String.fromCharCode(event.keyCode);
								$t
										.val(char
												+ $t.val().slice(
														this.selectionEnd));
								this.setSelectionRange(1, 1);
							}
						});

				$('#member').change(function() {
					$(this).find("option:selected").each(function() {
						if ($(this).attr("value") == "vle") {

							$(".box").not(".vle").hide();
							$(".vle").show();
						} else if ($(this).attr("value") == "Field") {

							$(".box").not(".feildofficer").hide();
							$(".feildofficer").show();
						}

						else {
							$(".box").hide();
						}
					});
				}).change();

			});

	webshims.setOptions('forms-ext', {
		replaceUI : 'auto',
		types : 'number'
	});
	webshims.polyfill('forms forms-ext');
</script>


<script>
	var request;
	var requestgsm;
	var requestproduct;
	var requestcnic;
	function sendInfo() {
		// 		alert("mobile called");

		var v = document.vinform1.customerMobile.value;

		var url = "table.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
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

	$('.mask').each(function(e) {
		console.log($(this).data('mask'))
		$(this).mask('' + $(this).data('mask'), {
			placeholder : ''
		})
	})

	function sendInfoCustomerGsm() {
		var v = document.vinform1.gsm.value;
		var url = "tableCustomerGsm.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfoCustomerGsm;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfoCustomerGsm() {

		if (request.readyState == 4) {

			var val = request.responseText;
			// 			alert(val);
			if (val >= 1) {

				$('#messagephoneGsm').show();
				// 				$('#messagephoneGsm').delay(5000).fadeOut('slow');
				// 				$('#click').attr('disabled', 'disabled');
				// 				$('#click').prop('disabled', true);

			} else {
				$('#messagephoneGsm').hide();
				// 				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function append_payment_type() {
		var v = document.vinform1.customerpayment_type.value;
		if (v == "1") {
			$(function() {

				// 			document.getElementById('downPaymentCash').style.visibility = "hidden";
				// 			document.getElementById('weeklyPaymentCash').style.visibility = "hidden";
				// 			document.getElementById('schemeCash').style.visibility = "hidden";

				$("#downPaymentCash").fadeOut();
				$("#weeklyPaymentCash").fadeOut();
				$("#schemeCash").fadeOut();

				$('#click').attr('id', 'cashAdd');
				$('#cashAdd').attr('id', 'cashAdd');

				// 			  document.getElementById('click').id = 'cashAdd';
				// 			  document.getElementById('cashAdd').id = 'cashAdd';

			});
		} else if (v == "2") {
			$(function() {
				// 			div.style.visibility = "hidden";
				// 			document.getElementById('downPaymentCash').style.visibility = "visible";
				// 			document.getElementById('weeklyPaymentCash').style.visibility = "visible";
				// 			document.getElementById('schemeCash').style.visibility = "visible";

				$("#downPaymentCash").fadeIn();
				$("#weeklyPaymentCash").fadeIn();
				$("#schemeCash").fadeIn();

				$('#cashAdd').attr('id', 'click');
				$('#click').attr('id', 'click');

				// 			  document.getElementById('cashAdd').id = 'click';
				// 			  document.getElementById('click').id = 'click';
			});
		}

	}

	function append_weekly() {
		var v = document.vinform1.appname.value;
		if (v == "50 W") {
			$(function() {
				$('#downPayment').val("8000");
				$('#customerWeeklyPayment').val("1000");
			});
		} else if (v == "80 W") {
			$(function() {
				$('#downPayment').val("10000");
				$('#customerWeeklyPayment').val("1500");
			});
		} else if (v == "100 W") {
			$(function() {
				$('#downPayment').val("12000");
				$('#customerWeeklyPayment').val("2000");
			});
		}

	}

	function appendexee_weekly() {

		var exev = document.existing_customer.exappname2.value;

		if (exev == "50 W") {
			$(function() {

				$('#exe2downPayment').val("8000");
				$('#exe2customerWeeklyPayment').val("1000");
				// 			  $("#execustomer_btn").show();

			});
		} else if (exev == "80 W") {
			$(function() {

				$('#exe2downPayment').val("10000");
				$('#exe2customerWeeklyPayment').val("1500");
				// 			  $("#execustomer_btn").show();
			});
		} else if (exev == "100 W") {
			$(function() {

				$('#exe2downPayment').val("12000");
				$('#exe2customerWeeklyPayment').val("2000");
				// 			  $("#execustomer_btn").show();
			});
		} else {
			$(function() {

				// 			  $("#execustomer_btn").hide();
			});
		}

	}

	function appendexe_weekly() {
		// 		alert('clled appendexe_weekly');
		var exev = document.existingcustomer_form.exappname.value;
		// 		alert('get value from '+v);
		if (exev == "50 W") {
			$(function() {

				$('#exedownPayment').val("8000");
				$('#execustomerWeeklyPayment').val("1000");
				$("#execustomerid").show();

			});
		} else if (exev == "80 W") {
			$(function() {

				$('#exedownPayment').val("10000");
				$('#execustomerWeeklyPayment').val("1500");
				$("#execustomerid").show();
			});
		} else if (exev == "100 W") {
			$(function() {

				$('#exedownPayment').val("12000");
				$('#execustomerWeeklyPayment').val("2000");
				$("#execustomerid").show();
			});
		} else {
			$(function() {

				$("#execustomerid").hide();
			});
		}

	}

	function append_salesman() {
		var salesman = document.vinform1.gsalesman3.value;

		$('#salesmandetail').val(salesman);

	}

	function getInfo() {

		if (request.readyState == 4) {

			var val = request.responseText;
			// 			alert(val);
			if (val >= 1) {

				$('#messagephone').show();
				// 				$('#messagephone').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messagephone').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function sendInfogsm() {

		var v = document.vinform1.customerappgsm.value;

		var url = "gsm_validation.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestgsm = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestgsm = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestgsm.onreadystatechange = getInfogsm;
			requestgsm.open("GET", url, true);
			requestgsm.send();
		} catch (e) {
			alert("Unable to connect to server");
		}

	}
	function getInfogsm() {

		if (requestgsm.readyState == 4) {

			var val = requestgsm.responseText;

			if (val >= 1) {
				$('#messagegsm').show();
				// 				$('#messagegsm').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messagegsm').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function sendInfoproductid() {

		var v = document.vinform1.customerappproId.value;

		var url = "product_id.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestproduct = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestproduct = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestproduct.onreadystatechange = getInfoproductid;
			requestproduct.open("GET", url, true);
			requestproduct.send();
		} catch (e) {
			alert("Unable to connect to server");
		}

	}
	function getInfoproductid() {

		if (requestproduct.readyState == 4) {

			var val = requestproduct.responseText;

			if (val >= 1) {
				$('#messageproduct').show();
				// 				$('#messageproduct').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messageproduct').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	var request;

	function checkphone() {

		var v = document.salesman.salesphone.value;
		var url = "salesmanphonecheck.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
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

				// 				$('#messagephonevalid').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
			} else {
				$('#messagephonevalid').hide();
			}

			// document.getElementById('amit').innerHTML = val;
		}
	}

	function cniccheck() {

		var v = document.salesman.salescnic.value;
		var url = "cniccheck.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfocnic;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfocnic() {

		if (request.readyState == 4) {

			var val = request.responseText;

			if (val >= 1) {
				$('#messagenic').show();
				// 				$('#messagenic').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
			} else {
				$('#messagenic').hide();
			}

			// document.getElementById('amit').innerHTML = val;
		}
	}

	function emailphone_tech() {

		var v = document.tech_form.phone.value;
		var url = "phone_check_tech.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfophone_tech;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfophone_tech() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#tech_phone').show();
				// 				$('#tech_phone').delay(5000).fadeOut('slow');

				$('#click_tech').prop('disabled', true);

			} else {
				$('#tech_phone').hide();
				$('#click_tech').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function chechcnic_tech() {

		var v = document.tech_form.cnic.value;
		var url = "cnic_chec_tech.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfocnic_tech;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfocnic_tech() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#tech_cnic').show();
				// 				$('#tech_cnic').delay(5000).fadeOut('slow');

				$('#click_tech').prop('disabled', true);

			} else {
				$('#tech_cnic').hide();
				$('#click_tech').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function check_customer_cnic() {

		var v = document.vinform1.customercnic.value;

		var url = "check_customer_cnic.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestcnic = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestcnic = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestcnic.onreadystatechange = getInfo_customer12;
			requestcnic.open("GET", url, true);
			requestcnic.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfo_customer12() {

		if (requestcnic.readyState == 4) {

			var val = requestcnic.responseText;

			if (val >= 1) {

				$('#messagecnic').show();
				// 				$('#messagecnic').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messagecnic').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function checkphone() {

		var v = document.salesman.salesphone.value;
		var size = document.salesman.salesphone.value.length;
		if (size < 11) {
			$('#salesphonnumber').show();
			$('#salesphonnumber').delay(5000).fadeOut('slow');
			$('#click').attr('disabled', 'disabled');

		}
		var url = "salesmanphonecheck.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
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
			// 			alert(val);
			if (val >= 1) {
				$('#messagephonevalid').show();

				//                 $('#messagephonevalid').delay(5000).fadeOut('slow');
				$('#click_Salesman').attr('disabled', 'disabled');
			} else {
				$('#messagephonevalid').hide();
				$('#click_Salesman').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	webshims.setOptions('forms-ext', {
		replaceUI : 'auto',
		types : 'number'
	});
	webshims.polyfill('forms forms-ext');
</script>
<script>
	//danish
	function loan(a) {
		//alert('sdfkdf'+a);
		if (a == 'ON') {
			document.getElementById("loans0").style.display = 'none';
			document.getElementById("loans1").style.display = 'block';
		} else if (a == 'OFF') {
			document.getElementById("loans0").style.display = 'block';
			document.getElementById("loans1").style.display = 'none';
		}
	}

	function bank(a) {
		//alert('sdfkdf'+a);
		if (a == 'ON') {
			document.getElementById("bank0").style.display = 'none';
			document.getElementById("bank1").style.display = 'block';
		} else if (a == 'OFF') {
			document.getElementById("bank0").style.display = 'block';
			document.getElementById("bank1").style.display = 'none';
		}
	}
</Script>

<script>
	function getFoResults(val) {
		if (val.checked && val.value == "Car") {
			$('#slicence').show();
			$('#slabel').show();
		} else if (val.checked && val.value == "Bike") {
			$('#slicence').show();
			$('#slabel').show();
		} else if (val.checked && val.value == "None") {
			$('#slicence').hide();
			$('#slabel').hide();
		}
	};

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
		//     	    elem.checked && elem.value == "Show" ? $('#licence').show() : $('#label').show();
		if (val.checked && val.value == "Educated") {
			$('#college').show();
			$('#degree').show();
			$('#dateOfStart').show();
			$('#dateOfEnd').show();
			$('#percentage').show();
			$('#collegeResult').show();
			$('#degreeResult').show();
			$('#percentageResult').show();
			$('#a').show();
			$('#b').show();
			$('#c').show();
			$('#d').show();
			$('#e').show();

		} else {
			$('#college').hide();
			$('#degree').hide();
			$('#dateOfStart').hide();
			$('#dateOfEnd').hide();
			$('#percentage').hide();
			$('#collegeResult').hide();
			$('#degreeResult').hide();
			$('#percentageResult').hide();
			$('#a').hide();
			$('#b').hide();
			$('#c').hide();
			$('#d').hide();
			$('#e').hide();
		}
	};

	function getFoEducation(val) {
		//     	    elem.checked && elem.value == "Show" ? $('#licence').show() : $('#label').show();
		if (val.checked && val.value == "true") {
			$('#sCollege').show();
			$('#sDegree').show();
			$('#sDateOfStart').show();
			$('#sDateOfEnd').show();
			$('#sPercentage').show();
			$('#sCollegeResult').show();
			$('#sDegreeResult').show();
			$('#sPercentageResult').show();
			$('#sa').show();
			$('#sb').show();
			$('#sc').show();
			$('#sd').show();
			$('#se').show();

		} else {
			$('#sCollege').hide();
			$('#sDegree').hide();
			$('#sDateOfStart').hide();
			$('#sDateOfEnd').hide();
			$('#sPercentage').hide();
			$('#sCollegeResult').hide();
			$('#sDegreeResult').hide();
			$('#sPercentageResult').hide();
			$('#sa').hide();
			$('#sb').hide();
			$('#sc').hide();
			$('#sd').hide();
			$('#se').hide();
		}
	};

	function getMarritalStatus(val) {
		if (val.value == "Married") {
			$('#wifephone').show();
			$('#wifename').show();
			$('#wifeCnic').show();
			$('#wifedateOfBirth').show();
			$('#w').show();
			$('#x').show();
			$('#y').show();
			$('#z').show();
		} else {
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

	function family() {
		var test = document.salesman.customerfamilymember.value;
		document.getElementById("loop").innerHTML = " ";
		for (var i = 1; i <= test; i++) {

			document.getElementById("loop").innerHTML +=

			"<div class=\"container\"><div class=\"col-sm-offset-1 col-sm-9 well\"><b>Family Member: "
					+ i
					+ "</b><legend></legend>"
					+
					//===================div for name=======================
					"<div class=\"form-group\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Name*:</label>"
					+ "<div class=\"col-sm-6\" >"
					+ "<input type=\"text\"  pattern=\"^[A-z ]+$\" id=\"member"+i+"\" name=\"membername"+i+"\" class=\"form-control\" data-parsley-group=\"wizard-step-2\" required=\"required\">"
					+ "</div>"
					+ "</div>"
					+
					//===================div for relation====================	
					"<div class=\"form-group\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Relation*:</label>"
					+ "<div class=\"col-sm-6\" >"
					+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" pattern=\"^[A-z ]+$\" id=\"member"+i+"\" name=\"relation"+i+"\" class=\"form-control\" required=\"required\" >"
					+ "</div>"
					+ "</div>"
					+
					//====================div for Mobile=====================
					"<div class=\"form-group\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Mobile*:</label>"
					+ "<div class=\"col-sm-1\">"
					+ "<input  type=\"text\" class=\"form-control\" value=\"92\" style=\"width:\" readonly />"
					+ "</div>"
					+ "<div class=\"col-sm-5\" >"
					+ "<input type=\"text\"  maxlength=\"11\" id=\"memberphone"
					+ i
					+ "\" name=\"membermobile"
					+ i
					+ "\"  class=\"form-control\" onkeypress = \"setMobileDash(event, 'memberphone"
					+ i
					+ "')\" onchange=\"mobileNumberValidation('memberphone"
					+ i
					+ "','memberResult"
					+ i
					+ "')\" required >"
					+ "<span style=\"color: red; font-size: 12px;\"	id=\"memberResult"+i+"\"></span>"
					+ "</div>"
					+ "</div>"
					+

					//====================div for Cnic=====================
					"<div class=\"form-group\" id=\"CHILD_CNIC_ID"+i+"\" hidden=\"true\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Cnic*:</label>"
					+ "<div class=\"col-sm-6\" >"
					+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" maxlength=\"15\" id=\"member_cnic"
					+ i
					+ "\" name=\"membercnic"
					+ i
					+ "\" class=\"form-control\" oninvalid=\"this.setCustomValidity('Please enter the cnic number')\" onchange=\"cnicValidation('customercnic','customerCnicResult') , check_customer_cnic()\" onkeypress = \"setNicDash(event,'customercnic')\" required=\"required\" value=\"45301-5569855-5\">"
					+ "</div>" + "</div>" + "</div>" + "</div></div>";
			//					===================div end for complete family member ===========

			// 				"<div> <label for=\"inputEmail3\" class=\"control-label col-sm-3\">"+
			// 				"<b>Family Members: "+i+"</b></label></br> </div>"+
			// 				"<div class=\"col-sm-6\" >"+
			// 				"Name: <input type=\"text\" pattern=\"^[A-z ]+$\" id=\"member"+i+"\" name=\"membername"+i+"\" class=\"form-control\" required=\"required\" value=\"Ali\">"+
			// 				"Relation With Employee: <input type=\"text\" pattern=\"^[A-z ]+$\" id=\"member"+i+"\" name=\"relation"+i+"\" class=\"form-control\" required=\"required\" value=\"Father\\Mother\\Brother\\Sister\">"+				

			// 				"Mobile: <input type=\"text\" maxlength=\"12\" id=\"member"+i+"\" name=\"membermobile"+i+"\" class=\"form-control\ onchange=\"mobileNumberValidation('customerMobile','customerMobileResult') , sendInfo();\" required=\"required\" value=\"923122020235\">"+

			// 				"Cnic: <input type=\"text\" maxlength=\"15\" id=\"member_cnic"+i+"\" name=\"membercnic"+i+"\" class=\"form-control\" onchange=\"check_child_ageD('child_dob"+i+"','CHILD_CNIC_ID"+i+"')\" required=\"required\" oninvalid=\"this.setCustomValidity('Please select the date of birth')\" >"+

			// 				"</div>";										
		}
	}

	$(function() {

		$("input[type='submit']").click(function() {
			$(this).attr("disabled", false);
			disVLE();
		});
	});

	function disVLE() {
		$("#foClick").hide();
		$("#click").hide();
	}

	function sfamily() {
		var test = document.salesman.customerfamilymember.value;
		document.getElementById("sloop").innerHTML = " ";
		for (var i = 1; i <= test; i++) {

			document.getElementById("sloop").innerHTML +=

			"<div class=\"container\"><div class=\"col-sm-offset-1 col-sm-9 well\"><b>Family Member: "
					+ i
					+ "</b><legend></legend>"
					+
					//===================div for name=======================
					"<div class=\"form-group\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Name*:</label>"
					+ "<div class=\"col-sm-6\" >"
					+ "<input type=\"text\"  pattern=\"^[A-z ]+$\" id=\"smember"+i+"\" name=\"smembername"+i+"\" class=\"form-control\" data-parsley-group=\"wizard-step-2\" required=\"required\">"
					+ "</div>"
					+ "</div>"
					+
					//===================div for relation====================	
					"<div class=\"form-group\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Relation*:</label>"
					+ "<div class=\"col-sm-6\" >"
					+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" pattern=\"^[A-z ]+$\" id=\"smember"+i+"\" name=\"srelation"+i+"\" class=\"form-control\" required=\"required\" >"
					+ "</div>"
					+ "</div>"
					+
					//====================div for Mobile=====================
					"<div class=\"form-group\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Mobile*:</label>"
					+ "<div class=\"col-sm-1\">"
					+ "<input  type=\"text\" class=\"form-control\" value=\"92\" style=\"width:\" readonly />"
					+ "</div>"
					+ "<div class=\"col-sm-5\" >"
					+ "<input type=\"text\"  maxlength=\"10\" id=\"smemberphone"
					+ i
					+ "\" name=\"smembermobile"
					+ i
					+ "\"  class=\"form-control\" onchange=\"mobileNumberValidation('smemberphone"
					+ i
					+ "','smemberResult"
					+ i
					+ "')\" required >"
					+ "<span style=\"color: red; font-size: 12px;\"	id=\"smemberResult"+i+"\"></span>"
					+ "</div>"
					+ "</div>"
					+

					//====================div for Cnic=====================
					"<div class=\"form-group\" id=\"CHILD_CNIC_ID"+i+"\" hidden=\"true\">"
					+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Cnic*:</label>"
					+ "<div class=\"col-sm-6\" >"
					+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" maxlength=\"15\" id=\"smember_cnic"
					+ i
					+ "\" name=\"smembercnic"
					+ i
					+ "\" class=\"form-control\" oninvalid=\"this.setCustomValidity('Please enter the cnic number')\" onchange=\"cnicValidation('scustomercnic','scustomerCnicResult') , check_customer_cnic()\" onkeypress = \"setNicDash(event,'scustomercnic')\" required=\"required\" value=\"45301-5569855-5\">"
					+ "</div>" + "</div>" + "</div>" + "</div></div>";
			//					===================div end for complete family member ===========

			// 				"<div> <label for=\"inputEmail3\" class=\"control-label col-sm-3\">"+
			// 				"<b>Family Members: "+i+"</b></label></br> </div>"+
			// 				"<div class=\"col-sm-6\" >"+
			// 				"Name: <input type=\"text\" pattern=\"^[A-z ]+$\" id=\"member"+i+"\" name=\"membername"+i+"\" class=\"form-control\" required=\"required\" value=\"Ali\">"+
			// 				"Relation With Employee: <input type=\"text\" pattern=\"^[A-z ]+$\" id=\"member"+i+"\" name=\"relation"+i+"\" class=\"form-control\" required=\"required\" value=\"Father\\Mother\\Brother\\Sister\">"+				

			// 				"Mobile: <input type=\"text\" maxlength=\"12\" id=\"member"+i+"\" name=\"membermobile"+i+"\" class=\"form-control\ onchange=\"mobileNumberValidation('customerMobile','customerMobileResult') , sendInfo();\" required=\"required\" value=\"923122020235\">"+

			// 				"Cnic: <input type=\"text\" maxlength=\"15\" id=\"member_cnic"+i+"\" name=\"membercnic"+i+"\" class=\"form-control\" onchange=\"check_child_ageD('child_dob"+i+"','CHILD_CNIC_ID"+i+"')\" required=\"required\" oninvalid=\"this.setCustomValidity('Please select the date of birth')\" >"+

			// 				"</div>";										
		}
	}
</script>

<script>
	$(document).ready(function() {
		/*  $('#licence').hide();
		$('#label').hide(); */
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
		$('#doCnic').hide();
		$('#cnicLabel').hide();

		$('#sCollege').hide();
		$('#sDegree').hide();
		$('#sDateOfStart').hide();
		$('#sDateOfEnd').hide();
		$('#sPercentage').hide();
		$('#sa').hide();
		$('#sb').hide();
		$('#sc').hide();
		$('#sd').hide();
		$('#se').hide();

		$('#sdoCnic').hide();
		$('#cnicFoLabel').hide();

		$('#slicence').hide();
		$('#slabel').hide();

		$('#licence').hide();
		$('#label').hide();

	});
</script>

<script>
	$(document).ready(function() {
		$("#butn").click(function() {
			/* Single line Reset function executes on click of Reset Button */
			$("#salesman")[0].reset();
		});
	});
</script>
</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		ArrayList<InventoryBean> inventory = InventoryBAL.getAppliances();
		ArrayList<CityBean> cities = DistrictOfficerBAL.getDistrictCities(bean.getUser_district());
		ArrayList<HashMap<String, String>> folist = FieldOfficerBAL.getFieldOfficers(bean.getUserId());
		System.err.print("333333333333333333333333333333 : " + folist.size());
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


						<div class="panel-heading">
							<h4 class="panel-title">Add Team</h4>
						</div>
						<div class="panel-body">
							<div>
								<select id="member" class="form-control">
									<option selected disabled>--Select member--</option>
									<option value="vle">Nizam Dost</option>
									<option value="Field">Field officer</option>
								</select>
							</div>
							<div class="box vle" style="margin-top: 2%">
								<fieldset>
									<legend class="pull-left width-full">Add Nizam Dost</legend>
									<!-- begin row -->
									<div class="row">
										<!-- begin col-6 -->
										<form class="form-horizontal" action="AddSalesman"
											data-parsley-validate="true" method="post" id="salesman"
											name="salesman" enctype="multipart/form-data">
											<center style="margin: 10px;">
												<span style="color: red;">Fields marked with * are
													required</span>
											</center>
											<legend>
												<center>Personal information</Center>
											</legend>


											<!-- <legend><center>Field Officer</Center></legend> -->

											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Field Officer:</label>
												<div class="col-sm-6">
													<select class="form-control" id="fo" name="fo"
														required="required"
														oninvalid="this.setCustomValidity('Please select field officer')"
														onchange="this.setCustomValidity('')">
														<%
															for (HashMap<String, String> fo : folist) {
																String fo_id = fo.get("foid");
														%>
														<option value="<%=fo.get("foid")%>">
															<%=fo.get("foName")%></option>
														<%
															}
														%>
													</select>

												</div>
											</div>

											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Tehsil:</label>
												<div class="col-sm-6">
													<select class="form-control" id="city" name="city"
														required="required"
														oninvalid="this.setCustomValidity('Please enter the valid appliance name')"
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
													style="color: red;">*</i> Full Name:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="firstname"
														data-parsley-required="true" name="firstname"
														placeholder=""
														onchange="nameValidation('firstname','doFirstNameResult','Invalid name'); "
														onkeypress="nameCharahterValidation('firstname')" /> <span
														style="color: red; font-size: 12px;"
														id="doFirstNameResult"></span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Date of Joining:</label>
												<div class="col-sm-6">

													<input type="date" class="form-control" id="joiningDate"
														required name="joiningDate"
														oninvalid="this.setCustomValidity('Please enter the date of joining')"
														onchange="this.setCustomValidity('')">

												</div>
											</div>



											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Date of Birth:</label>
												<div class="col-sm-6">

													<input type="date" class="form-control" id="dateOfBirth"
														name="dateOfBirth"
														onChange="ageValidationNewVLE(this, 'dateOfBirthResult','doCnic','cnicLabel')"
														required="required"
														oninvalid="this.setCustomValidity('Please enter the Date of Birth')">
													<!-- 															onchange="this.setCustomValidity('')"> -->
													<span style="color: red; font-size: 12px;"
														id="dateOfBirthResult"></span>
												</div>
											</div>

											<div class="form-group" id="cnic">
												<div id="messagenicvalid" name="messagenicvalid"
													style="display: none; color: red">CNIC exist already</div>
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="cnicLabel"><i style="color: red;">*</i> CNIC:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="doCnic"
														data-table="salesman" data-column="salesman_cnic"
														placeholder="11111-1111111-1" name="cnic" maxlength="15"
														onchange="cnicValidation('doCnic','cnicMsg'),existingMobileNumber('doCnic','cnicMsg',this)"
														onkeypress="setNicDash(event,'doCnic')" /> <span
														style="color: red; font-size: 12px;" id="cnicMsg"></span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Gender:</label>
												<div class="col-sm-6">
													Male <input type="radio" name="gender" value="Male" />
													Female <input type="radio" name="gender" value="Female" />
												</div>
											</div>



											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Marital
													status:</label>
												<div class="col-sm-6">
													Single <input type="radio" name="marritalStatus"
														id="marritalStatus" value="Single" /> Married <input
														type="radio" name="marritalStatus" id="marritalStatus"
														value="Married" />
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Blood
													Group:</label>
												<div class="col-sm-6">
													<select class="form-control" id="bloodGroup"
														name="bloodGroup"
														oninvalid="this.setCustomValidity('Please choose Marrital Status')"
														onchange="this.setCustomValidity('')">
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
													style="display: none; color: red; margin-left: 385px;">Mobile
													number already exist</div>
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Primary Mobile Number:</label>
												<div class="col-sm-1">
													<span class="form-control"
														style="width: 65%; background-color: #e5e9ed;">92</span>
												</div>
												<div class="col-sm-5">
													<input type="text" class="form-control phone mask"
														id="dophone1" data-mask="9999999999" data-table="salesman"
														data-column="salesman_phone_no" data-type="phone"
														name="phone1" maxlength="11" required="required"
														onkeypress="setMobileDash(event, 'dophone1')"
														onkeyup="mobileNumberValidation('dophone1','dophoneResult1'); existingData('dophone1','dophoneResult1',this);"
														placeholder="333-3333333"> <span
														style="color: red; font-size: 12px;" id="dophoneResult1"></span>
												</div>

											</div>

											<div class="form-group">
												<div id="messagephonevalid" name="messagephonevalid"
													style="display: none; color: red">Mobile number
													already exist</div>
												<label for="inputPassword3" class="col-sm-3 control-label">
													Secondary Mobile Number</label>
												<div class="col-sm-1">
													<span class="form-control"
														style="width: 65%; background-color: #e5e9ed;">92</span>
												</div>
												<div class="col-sm-5">
													<input type="text" class="form-control" id="dophone2"
														data-type="phone" data-table="salesman"
														data-column="mobile_no" placeholder="333-3333333"
														maxlength="11"
														onkeypress="setMobileDash(event, 'dophone2')"
														name="phone2" maxlength="10"
														onchange="mobileNumberValidation('dophone2','dophoneResult2'),existingData('dophone2','dophoneResult2',this);">
													<span style="color: red; font-size: 12px;"
														id="dophoneResult2"></span>
												</div>
											</div>


											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">
													<!-- <i style="color: red;">*</i> --> Account Number
												</label>
												<div class="col-sm-6">
													<input type="text" id="accnumber" class="form-control"
														name="accnumber"
														oninvalid="this.setCustomValidity('Please Enter account number')"
														onblur="numericValidationAT('accnumber','accnumberResult','Invalid Number'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="accnumberResult"></span>
												</div>
											</div>


											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Vehicle:</label>
												<div class="col-sm-8" style="margin-top: 1%">
													<input type="radio" class="" name="toggle" value="Car"
														onClick="getResults(this)"> Car
													&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" class=""
														name="toggle" value="Bike" onClick="getResults(this)">
													Bike &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" class=""
														name="toggle" value="None" onClick="getResults(this)">
													None &nbsp;&nbsp;&nbsp;&nbsp;
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="label">Driving Licence:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="licence"
														name="licence" />
													<!-- onchange="numericValidation('licence','licenceMsg','Invalid Licence Number')" /> -->
													<span style="color: red; font-size: 12px;" id="licenceMsg"></span>
												</div>
											</div>



											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Address:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="address"
														name="address" required="required"
														oninvalid="this.setCustomValidity('Please Enter Address')"
														onchange="addressValidation('address','addressResult'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="addressResult"></span>
												</div>
											</div>

											<legend>
												<center>Qualification</Center>
											</legend>
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Education:</label>
												<div class="col-sm-6" style="margin-top: 1%">
													<input type="radio" name="education" value="Educated"
														onClick="getEducation(this)"> Educated
													&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
														name="education" value="Uneducated"
														onClick="getEducation(this)"> Uneducated
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="a">College/University:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="college"
														name="college"
														oninvalid="this.setCustomValidity('Please Enter College/University')"
														onchange="nameValidation('college','collegeResult','Invalid College/University/Institute'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="collegeResult"></span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="b">Certificate/Degree:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="degree"
														name="degree"
														oninvalid="this.setCustomValidity('Please Enter degree')"
														onchange="nameValidation('degree','degreeResult','Invalid Degree/Certificate/Diploma'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="degreeResult"></span>
												</div>
											</div>


											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"
													id="c">Start Date: </label>
												<div class="col-sm-6">

													<input type="date" class="form-control" id="dateOfStart"
														name="dateOfStart"
														oninvalid="this.setCustomValidity('Please enter the Date of Start')"
														onchange="this.setCustomValidity('')">

												</div>
											</div>




											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"
													id="d">End Date: </label>
												<div class="col-sm-6">
													<input type="date" class="form-control" id="dateOfEnd"
														name="dateOfEnd"
														oninvalid="this.setCustomValidity('Please enter the Date of End')"
														onchange="this.setCustomValidity('')">

												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="e">Percentage:</label>
												<div class="col-sm-6">
													<input type="number" class="form-control" id="percentage"
														name="percentage"
														oninvalid="this.setCustomValidity('Please Enter percentage')">
													<span style="color: red; font-size: 12px;"
														id="percentageResult"></span>
												</div>
											</div>


											<div class="form-group" id="loop"></div>
											<legend>
												<center>Commission information</Center>
											</legend>
											<!-- 												 <div class="form-group"> -->
											<!-- 													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Basic Salary:</label> -->
											<!-- 													<div class="col-sm-6"> -->
											<!-- 														<input type="number" id="salary" class="form-control" -->
											<!-- 															name="salary" required="required" -->
											<!-- 															oninvalid="this.setCustomValidity('Please Enter Basic Salary')" -->
											<!-- 															onblur="numericValidation('salary','salaryResult','Invalid Number'), this.setCustomValidity('')"> -->
											<!-- 															<span style="color: red; font-size: 12px;" -->
											<!-- 															id="salaryResult"></span> -->
											<!-- 													</div> -->
											<!-- 												</div>  -->
											<!-- <div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Before Time</label>
													<div class="col-sm-6">
														<input type="number" id="beforeTime" class="form-control"
															name="beforeTime" required="required"
															oninvalid="this.setCustomValidity('Please Enter Before Time Recovery')"
															onblur="numericValidationBT('beforeTime','beforeTimeResult','Invalid Number'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
															id="beforeTimeResult"></span>
													</div>
												</div> -->

											<div class="form-group" style="width: 98%; margin-left: 1%">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">* </i> Recovery Commission</label>
												<div class="col-md-offset-3 col-sm-6 input-group"
													data-parsley-trigger="change">
													</span> <input type="number" id="onTime" class="form-control"
														name="onTime" required="required"
														oninvalid="this.setCustomValidity('Please Enter On Time Recovery')"
														onblur="numericValidationOT('onTime','onTimeResult','Invalid Number'), this.setCustomValidity('')"
														maxlength="15" /> <span class="input-group-addon">%</span>
													<span
														style="color: red; font-size: 12px; position: absolute;"
														id="onTimeResult"></span>
												</div>
											</div>
											<!-- <div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> After Time</label>
													<div class="col-sm-6">
														<input type="number" id="afterTime" class="form-control"
															name="afterTime" required="required"
															oninvalid="this.setCustomValidity('Please Enter After Time Recovery')"
															onblur="numericValidationAT('afterTime','afterTimeResult','Invalid Number'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
															id="afterTimeResult"></span>
													</div>
												</div>	 -->

											<div class="form-group" style="width: 98%; margin-left: 1%">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">* </i> Per Sell Commission</label>
												<div class="col-md-offset-3 col-sm-6 input-group"
													data-parsley-trigger="change">
													<span class="input-group-addon">Rs:</span><input
														type="number" id="percellcomm" class="form-control"
														name="percellcomm" required="required"
														oninvalid="this.setCustomValidity('Please Enter percellcomm')"
														onblur="numericValidationAT('percellcomm','percellcommTimeResult','Invalid Number'), this.setCustomValidity('')"
														maxlength="15"> <span
														style="color: red; font-size: 12px; position: absolute;"
														id="percellcommTimeResult"></span>
												</div>
											</div>





											<!-- <div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3">Upload
														image</label>
													<div class="col-sm-6">
														<input type="file" name="photo" class="form-control"
															id="doImages" placeholder="upload image">
														<input type="hidden" id="imageId" name = "imageId" />
														<div id="show1"></div>	
													</div>
												</div> -->
											<input type="hidden" name="district"
												value="<%=bean.getUser_district()%>"> <input
												type="hidden" name="doId" value="<%=bean.getUserId()%>">

											<div class="form-group">
												<div class="col-sm-offset-3 col-sm-6">
													<input type="submit" name="click" id="click" value="Submit"
														class="btn btn-block btn-info ">
												</div>
											</div>


										</form>
										<!-- end col-6 -->
									</div>
									<!-- end row -->
								</fieldset>
							</div>
							<!-- end wizard step-2 -->






							<!-- 								begin wizard step-3 -->
							<!-- 								<div> -->
							<!-- 									<fieldset> -->
							<!-- 										<legend class="pull-left width-full">Add Technician</legend> -->
							<!-- 										begin row -->
							<!-- 										<div class="row"> -->
							<!-- 											begin col-4 -->
							<!-- 											<form class="form-horizontal" action='Technician' -->
							<!-- 												method='post' name="tech_form" id="tech_form"> -->
							<!-- 												<div class="form-group"> -->
							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">Full -->
							<!-- 														Name</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" -->
							<!-- 															id="tachnicianname" name="firstname" pattern="^[A-z ]+$" -->

							<!-- 															oninvalid="this.setCustomValidity('Please enter the full name')" -->
							<!-- 															onchange="nameValidation('tachnicianname','technicianNameResult','Invalid name') , this.setCustomValidity('')" onkeypress = "nameCharahterValidation('tachnicianname')"/> -->
							<!-- 														<span style="color: red; font-size: 12px;" -->
							<!-- 														id="technicianNameResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->

							<!-- 												<div class="form-group"> -->

							<!-- 												<span id="tech_phone" name="tech_phone" -->
							<!-- 														style="display: none; color: red; float: right; width: 73%;">This mobile number -->
							<!-- 														already exist in our system</span> -->

							<!-- 												</div> -->

							<!-- 												<div class="form-group"> -->


							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">Mobile -->
							<!-- 														Number</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" id="technicianMobile" -->
							<!-- 															name="phone" placeholder="3123040389" maxlength="10" -->
							<!-- 															onchange="mobileNumberValidation('technicianMobile','technicianMobileResult') , emailphone_tech()"  > -->
							<!-- 															<span style="color: red; font-size: 12px;" -->
							<!-- 														id="technicianMobileResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->


							<!-- 												<div class="form-group"> -->
							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">District</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" id="tecnicianDistrict" onchange="nameValidation('tecnicianDistrict','tecnicianDistrictResult','Invalid district')"> -->
							<!-- 														<span style="color: red; font-size: 12px;" -->
							<!-- 														id="tecnicianDistrictResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->


							<!-- 												<div class="form-group"> -->
							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">City</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" id="technicianCity" onchange="nameValidation('technicianCity','technicianCityResult','Invalid city')"> -->
							<!-- 														<span style="color: red; font-size: 12px;" -->
							<!-- 														id="technicianCityResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->


							<!-- 												<div class="form-group"> -->
							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">Address</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" id="technicianAddress" -->
							<!-- 															name="address"   -->
							<!-- 															oninvalid="this.setCustomValidity('Please Enter Address')" -->
							<!-- 															onchange="addressValidation('technicianAddress','technicianAddressResult')"> -->
							<!-- 															<span style="color: red; font-size: 12px;" -->
							<!-- 														id="technicianAddressResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->


							<!-- 												<div class="form-group"> -->
							<!-- 													<span id="tech_cnic" name="tech_cnic" -->
							<!-- 														style="display: none; color: red; float: right; width: 73%;">This CNIC already exist in our system</span> -->

							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">CNIC</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" id="technicianCnic" -->
							<!-- 															name="cnic"   -->
							<!-- 															oninvalid="this.setCustomValidity('Please Enter Cnic')" maxlength="15" -->
							<!-- 															onchange="cnicValidation('technicianCnic','technicianCnicResult') , chechcnic_tech()" onkeypress = "setNicDash(event,'technicianCnic')"/> -->
							<!-- 															<span style="color: red; font-size: 12px;" -->
							<!-- 														id="technicianCnicResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->


							<!-- 												<div class="form-group"> -->
							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">Salary</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="text" class="form-control" -->
							<!-- 															id="techbasicsalary" name="basicsalary" -->

							<!-- 															oninvalid="this.setCustomValidity('Please Enter Salary')" -->
							<!-- 															onchange="numericValidation('techbasicsalary', 'tecnicianSalaryResult','Invalid salary') , this.setCustomValidity('')"> -->
							<!-- 															<span style="color: red; font-size: 12px;" -->
							<!-- 														id="tecnicianSalaryResult"></span> -->
							<!-- 													</div> -->
							<!-- 												</div> -->
							<!-- 												<div class="form-group"> -->
							<!-- 													<label for="inputEmail3" class="control-label col-sm-3">Joining -->
							<!-- 														Date</label> -->
							<!-- 													<div class="col-sm-6"> -->
							<!-- 														<input type="date" class="form-control" id="techjoindate" -->
							<!-- 															name=""   -->
							<!-- 															oninvalid="this.setCustomValidity('Please Enter Date')" -->
							<!-- 															onchange="this.setCustomValidity('')"> -->
							<!-- 													</div> -->
							<!-- 												</div> -->
							<!-- 												<div class="form-group"> -->

							<!-- 													<div class="col-sm-6 col-sm-offset-3"> -->
							<!-- 														<input type="hidden" name="district" -->
							<%-- 															value="<%=bean.getUserId()%>"> <input --%>
							<!-- 															type="submit" name="click" id="click_tech" value="Add" -->
							<!-- 															disabled="disabled" class="btn btn-info btn-block"> -->

							<!-- 													</div> -->
							<!-- 												</div> -->
							<!-- 											</form> -->
							<!-- 											end col-6 -->

							<!-- 										</div> -->
							<!-- 										end row -->
							<!-- 									</fieldset> -->
							<!-- 								</div> -->
							<!-- 								end wizard step-3 -->









							<!-- begin wizard step 4 -->
							<div class="box feildofficer">
								<fieldset>
									<legend class="pull-left width-full">Add Field Officer</legend>
									<!-- begin row -->
									<div class="row">
										<!-- begin col-6 -->
										<form class="form-horizontal" action="AddSalesman"
											data-parsley-validate="true" method="post" id="fieldofficer"
											name="fieldofficer" enctype="multipart/form-data">
											<center style="margin: 10px;">
												<span style="color: red;">Fields marked with * are
													required</span>
											</center>
											<legend>
												<center>Personal information</Center>
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
													style="color: red;">*</i> Full Name:</label>
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

											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Date of Joining:</label>
												<div class="col-sm-6">

													<input type="date" class="form-control" id="sjoiningDate"
														required name="sjoiningDate"
														oninvalid="this.setCustomValidity('Please enter the date of joining')"
														onchange="this.setCustomValidity('')">

												</div>
											</div>



											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"><i
													style="color: red;">*</i> Date of Birth:</label>
												<div class="col-sm-6">

													<input type="date" class="form-control" id="sdateOfBirth"
														name="sdateOfBirth"
														onChange="ageValidationNewFO(this, 'sdateOfBirthResult','sdoCnic','cnicFoLabel')"
														required="required"
														oninvalid="this.setCustomValidity('Please enter the Date of Birth')">
													<!-- 															onchange="this.setCustomValidity('')"> -->
													<span style="color: red; font-size: 12px;"
														id="sdateOfBirthResult"></span>

												</div>
											</div>

											<div class="form-group" id="cnic">
												<div id="messagenicvalid" name="messagenicvalid"
													style="display: none; color: red">CNIC exist already</div>
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="cnicFoLabel"><i style="color: red;">*</i> CNIC:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="sdoCnic"
														placeholder="11111-1111111-1" name="scnic" maxlength="15"
														data-table="field_officer" data-column="fo_cnic"
														onchange="cnicValidationforFo('sdoCnic','scnicMsg'),existingMobileNumberforFo('sdoCnic','scnicMsg',this)"
														onkeypress="setNicDash(event,'sdoCnic')" /> <span
														style="color: red; font-size: 12px;" id="scnicMsg"></span>
												</div>
											</div>


											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Gender:</label>
												<div class="col-sm-6">
													Male <input type="radio" name="sgender" value="Male" />
													Female <input type="radio" name="sgender" value="Female" />
												</div>
											</div>



											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Marital
													Status:</label>
												<div class="col-sm-6">
													Single <input type="radio" name="smarritalStatus"
														id="smarritalStatus" value="Single" /> Married <input
														type="radio" name="smarritalStatus" id="smarritalStatus"
														value="Married" />
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Blood
													Group:</label>
												<div class="col-sm-6">
													<select class="form-control" id="sbloodGroup"
														name="sbloodGroup"
														oninvalid="this.setCustomValidity('Please choose Blood Group')"
														onchange="this.setCustomValidity('')">
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
													style="display: none; color: red; margin-left: 385px;">Mobile
													number already exist</div>
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Primary Phone :</label>
												<div class="col-sm-1">
													<span class="form-control"
														style="width: 65%; background-color: #e5e9ed;">92</span>
												</div>
												<div class="col-sm-5">
													<input type="text" class="form-control" id="sdophone1"
														placeholder="333-3333333" data-table="field_officer"
														data-column="fo_priamary_phone" name="sphone1"
														data-type="phone" maxlength="11" required="required"
														onkeypress="setMobileDash(event, 'sdophone1')"
														onkeyup="mobileNumberValidationforFo('sdophone1','sdophoneResult1'),existingData('sdophone1','sdophoneResult1',this);">
													<span style="color: red; font-size: 12px;"
														id="sdophoneResult1"></span>
												</div>
											</div>

											<div class="form-group">
												<div id="messagephonevalid" name="messagephonevalid"
													style="display: none; color: red">Mobile number
													already exist</div>
												<label for="inputPassword3" class="col-sm-3 control-label">
													Secondary Phone</label>
												<div class="col-sm-1">
													<span class="form-control"
														style="width: 65%; background-color: #e5e9ed;">92</span>
												</div>
												<div class="col-sm-5">
													<input type="text" class="form-control" id="sdophone2"
														data-type="phone" data-table="field_officer"
														data-column="fo_secondary_phone" placeholder="333-3333333"
														name="sphone2" maxlength="11"existingData('sdophone2','sdophoneResult2',this);">
													<span style="color: red; font-size: 12px;"
														id="sdophoneResult2"></span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">
													<!-- <i style="color: red;">*</i> --> Account Number
												</label>
												<div class="col-sm-6">
													<input type="number" id="accnumber" class="form-control"
														name="accnumber"
														oninvalid="this.setCustomValidity('Please Enter account number')"
														onblur="numericValidationAT('accnumber','accnumberResult','Invalid Number'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="accnumberResult"></span>
												</div>
											</div>

											<!-- 												<div class="form-group"> -->
											<!-- 													<div id="messageemailvalid" name="messageemailvalid" -->
											<!-- 														style="display: none; color: red">Please Enter -->
											<!-- 														Email already Exist on our system</div> -->
											<!-- 													<label for="inputPassword3" class="col-sm-3 control-label">Email</label> -->
											<!-- 													<div class="col-sm-6"> -->
											<!-- 														<input type="email" class="form-control" id="doemail" -->
											<!-- 															name="email" placeholder="" -->
											<!-- 															onchange="emailValidation('doemail','doemailResult')"> <span -->
											<!-- 															style="color: red; font-size: 12px;" id="doemailResult"></span> -->
											<!-- 													</div> -->
											<!-- 												</div> -->

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label">Vehicle:</label>
												<div class="col-sm-8" style="margin-top: 1%">
													<input type="radio" class="" name="stoggle" value="Car"
														onClick="getFoResults(this)"> Car
													&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" class=""
														name="stoggle" value="Bike" onClick="getFoResults(this)">
													Bike &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" class=""
														name="stoggle" value="None" onClick="getFoResults(this)">
													None &nbsp;&nbsp;&nbsp;&nbsp;
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="slabel">Driving Licence:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="slicence"
														name="slicence" />
													<!-- onchange="numericValidation('slicence','slicenceMsg','Invalid Licence Number')" /> -->
													<span style="color: red; font-size: 12px;" id="slicenceMsg"></span>
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




											<!-- 										<div class="form-group"> -->
											<!-- 											<label for="inputPassword3" class="col-sm-3 control-label"> Qualification </label> -->
											<!-- 										</div> -->
											<legend>
												<center>Qualification</Center>
											</legend>
											<div class="form-group " id="edu">
												<label for="inputPassword3" class="col-sm-3 control-label">Education:</label>
												<div class="col-sm-6" style="margin-top: 1%">
													<input type="radio" name="seducation" value="true"
														onClick="getFoEducation(this)"> Educated
													&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
														name="seducation" value="false"
														onClick="getFoEducation(this)"> Uneducated
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="sa">College/University:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="sCollege"
														name="sCollege"
														oninvalid="this.setCustomValidity('Please Enter College/University')"
														onchange="nameValidation('sCollege','sCollegeResult','Invalid College/University/Institute'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="sCollegeResult"></span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="sb">Certificate/Degree:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="sDegree"
														name="sDegree"
														oninvalid="this.setCustomValidity('Please Enter degree')"
														onchange="nameValidation('sDegree','sDegreeResult','Invalid Degree/Certificate/Diploma'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="sDegreeResult"></span>
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
												<label for="inputEmail3" class="control-label col-sm-3"
													id="sc">Start Date: </label>
												<div class="col-sm-6">

													<input type="date" class="form-control" id="sDateOfStart"
														name="sDateOfStart"
														oninvalid="this.setCustomValidity('Please enter the Date of Start')"
														onchange="this.setCustomValidity('')">

												</div>
											</div>




											<div class="form-group">
												<label for="inputEmail3" class="control-label col-sm-3"
													id="sd">End Date: </label>
												<div class="col-sm-6">
													<input type="date" class="form-control" id="sDateOfEnd"
														name="sDateOfEnd"
														oninvalid="this.setCustomValidity('Please enter the Date of End')"
														onchange="this.setCustomValidity('')">

												</div>
											</div>

											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"
													id="se">Percentage:</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" id="sPercentage"
														name="sPercentage"
														oninvalid="this.setCustomValidity('Please Enter percentage')">
													<span style="color: red; font-size: 12px;"
														id="sPercentageResult"></span>
												</div>
											</div>

											<!-- <legend><center>Family information</Center></legend> -->
											<!-- 										<div class="form-group"> -->
											<!-- 											<label for="inputPassword3" class="col-sm-3 control-label"> Family*: </label> -->
											<!-- 										</div>		 -->

											<!-- <div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3">
													Family Members*:</label>
													<div class="col-sm-6">
														<input required="required" type="text" class="form-control" value="0"
															id="scustomerfamily" name="scustomerfamilymember"  
															oninvalid="this.setCustomValidity('Please enter the family members')"
															onkeyup="sfamily();"
															onchange="numericValidation('scustomerfamily', 'scustomerfamilyResult','Invalid family member') , this.setCustomValidity('')"/>
															<span style="color: red; font-size: 12px;" id="scustomerfamilyResult"></span>
													</div>
											</div> -->


											<div class="form-group" id="sloop"></div>
											<legend>
												<center>Commission information</Center>
											</legend>
											<div class="form-group">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Basic Salary:</label>
												<div class="col-sm-6">
													<input type="number" id="ssalary" class="form-control"
														name="ssalary" required="required"
														oninvalid="this.setCustomValidity('Please Enter Basic Salary')"
														onblur="numericValidation('ssalary','ssalaryResult','Invalid Number'), this.setCustomValidity('')">
													<span style="color: red; font-size: 12px;"
														id="ssalaryResult"></span>
												</div>
											</div>
											<!-- <div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> Before Time</label>
													<div class="col-sm-6">
														<input type="number" id="sbeforeTime" class="form-control"
															name="sbeforeTime" required="required"
															oninvalid="this.setCustomValidity('Please Enter Before Time Recovery')"
															onblur="numericValidationBT('sbeforeTime','sbeforeTimeResult','Invalid Number'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
															id="sbeforeTimeResult"></span>
													</div>
												</div> -->
											<div class="form-group" style="width: 98%; margin-left: 1%">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Recovery Commission</label>
												<div class="col-md-offset-3 col-sm-6 input-group"
													data-parsley-trigger="change"">
													<input type="number" id="sonTime" class="form-control"
														name="sonTime" required="required"
														oninvalid="this.setCustomValidity('Please Enter On Time Recovery')"
														onblur="numericValidationOT('sonTime','sonTimeResult','Invalid Number'), this.setCustomValidity('')">
													<span class="input-group-addon">%</span> <span
														style="color: red; font-size: 12px; position: absolute;"
														id="sonTimeResult"></span>
												</div>
											</div>

											<div class="form-group" style="width: 98%; margin-left: 1%">
												<label for="inputPassword3" class="col-sm-3 control-label"><i
													style="color: red;">*</i> Per Sell Commission</label>
												<div class="col-md-offset-3 col-sm-6 input-group"
													data-parsley-trigger="change"">
													<span class="input-group-addon">Rs:</span> <input
														type="number" id="percellcomm" class="form-control"
														name="percellcomm" required="required"
														oninvalid="this.setCustomValidity('Please Enter percellcomm')"
														onblur="numericValidationAT('percellcomm','percellcommTimeResult','Invalid Number'), this.setCustomValidity('')">
													<span
														style="color: red; font-size: 12px; position: absolute;"
														id="percellcommTimeResult"></span>
												</div>
											</div>
											<!-- <div class="form-group">
													<label for="inputPassword3" class="col-sm-3 control-label"><i style="color: red;">*</i> After Time</label>
													<div class="col-sm-6">
														<input type="number" id="safterTime" class="form-control"
															name="safterTime" required="required"
															oninvalid="this.setCustomValidity('Please Enter After Time Recovery')"
															onblur="numericValidationAT('safterTime','safterTimeResult','Invalid Number'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
															id="safterTimeResult"></span>
													</div>
												</div>	 -->





											<!-- <div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-3">Upload
														image</label>
													<div class="col-sm-6">
														<input type="file" name="sphoto" class="form-control"
															id="sdoImage" placeholder="upload image">
														<input type="hidden" id="imageId2" name="imageIdfo">	
														<div id="show"></div>
													</div>
												</div> -->
											<input type="hidden" name="sdistrict"
												value="<%=bean.getUser_district()%>"> <input
												type="hidden" name="sdoId" value="<%=bean.getUserId()%>">

											<div class="form-group">
												<div class="col-sm-offset-3 col-sm-6">
													<input type="submit" id="foClick" name="click"
														value="Add Field officer" class="btn btn-block btn-info">
												</div>
											</div>

										</form>
										<!-- end col-6 -->
									</div>
									<!-- end row -->
								</fieldset>
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
		$(document)
				.ready(
						function() {
							App.init();
							FormWizard.init();
							FormPlugins.init();
							FOformSubmit();
							VLEformSubmit();
							$(
									'#scity, #sfirstname, #sjoiningDate, #sdateOfBirth, #sdoCnic, #sdophone1, #saddress,#sonTime')
									.change(FOformSubmit);
							$('#ssalary, #sbeforeTime, #safterTime').blur(
									FOformSubmit);
							$(
									'#city, #firstname, #joiningDate, #dateOfBirth, #doCnic, #dophone1, #address, #salary, #beforeTime, #onTime, #afterTime, #fo')
									.change(VLEformSubmit);
							$('#salary, #beforeTime, #onTime, #afterTime')
									.blur(VLEformSubmit);

						});
	</script>
	<script>
		$('#doImages')
				.fileupload(
						{
							url : 'InsertImageController',
							method : 'POST',
							enctype : 'multipart/form-data',
							beforeSend : function() {
								$('#show1')
										.append(
												'<span class = "fa fa-circle-o-notch fa-spin"></span>')
							},
							add : function(e, data) {
								data
										.submit()
										.success(
												function(result, textStatus,
														jqHXR) {
													var json = $
															.parseJSON(result);
													console.log(json)
													var img = document
															.createElement('img');
													var p = document
															.createElement('p');
													$(img).attr('width', '200')
													$(img).attr('height',
															'auto')
													$(img)
															.attr(
																	'src',
																	"ShowImage?id="
																			+ json.imageId)
													$('#imageId').attr('value',
															json.imageId)

													$(p).text(json.filename)
													$('#show1').append(img)
													$('#show1').append(p)
													$('#show1 span').remove();

												})
							}
						});
	</script>
	<script>
	$('#sdoImage').fileupload({
		  url : 'InsertImageController',
		method : 'POST',
		  enctype : 'multipart/form-data',
		  beforeSend : function(){
				$('#show').append('<span class = "fa fa-circle-o-notch fa-spin"></span>')
			},
		  add : function(e, data){
		   data.submit().success(function(result, textStatus, jqHXR){
			   var json = $.parseJSON(result);
		    console.log(json)
		    alert(json);
		    var img = document.createElement('img');
		    var p = document.createElement('p');
		    $(img).attr('width', '200')
		    $(img).attr('height', 'auto')
		    $(img).attr('src', "ShowImage?id="+json.imageId)
		    $('#imageId2').attr('value', json.imageId)
		  //  alert($('#imageId').val());
		    //alert("Image id"+json.imageId)
		    $(p).text(json.filename)
		    $('#show').append(img)
		    $('#show').append(p);
		    $('#show span').remove()
		   })
		  }
		 });
		 
	$("input[name='onTime'], input[name='percellcomm']").on('keyup keypress blur change', function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		return false;
		}else{
			if( $(this).val().length >= parseInt($(this).attr('maxlength')) && (e.which != 8 && e.which != 0)){
				return false;
			}
		}
	});
	</script>
</body>


</html>
<!-- enctype="multipart/form-data" -->