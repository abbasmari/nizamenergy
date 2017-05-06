<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<%@page import="java.util.List"%>
<%@page import="bal.TargetsBAL"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>

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

<link href="assets/plugins/flag-icon/css/flag-icon.css" rel="stylesheet">
<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->
<!-- <script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script> -->
<!-- <script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script> -->
<!-- <script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script> -->


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

	function append_salesman() {
		var salesman = document.getElementById("gsalesman33").value;

		$('#salesmandetail').val(salesman);

	}

	function append_weekly() {
		var v = document.getElementById("appname").value;
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
</Script>

<%
	String number1 = request.getParameter("number");
	String number = number1.substring(2, number1.length());
	int customerId = Integer.parseInt(request.getParameter("customerid"));
	request.setAttribute("number", "92" + number1);

	request.setAttribute("id", customerId);
%>
<script type="text/javascript">
	$(document).ready(function() {
		$("select").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "Employ") {
					$(".box1").not(".Employ").hide();
					$(".Employ").show();
				} else if ($(this).attr("value") == "Business") {
					$(".box1").not(".Business").hide();
					$(".Business").show();
				} else if ($(this).attr("value") == "Both") {
					$(".box1").not(".Both").hide();
					$(".Both").show();
				} else {
					$(".box1").hide();
				}
			});
		}).change();
	});
</script>








<script type="text/javascript">
	function searchByNumber_() {
		//alert('hello');
		var search_element =
<%=number%>
	var customer =
<%=customerId%>
	//alert(search_element);
		//alert(customer+' '+search_element);
		$
				.ajax({
					url : 'SearchCustomer',
					dataType : 'json',
					method : 'POST',
					data : {
						searchelement : search_element,
						CustomerId : customer
					},

					// 												beforeSend : function(){
					// 													$('#searchbyphone').parent().append('<span class = "fa fa-circle-o-notch fa-spin"></span>')
					// 												},
					success : function(data) {

						//alert(data)
						for (var iii = 0; iii < data.length; iii++) {
							// 														alert(data);
							var exeid = data[iii].exeid;
							var salesmanid = data[iii].salesmanid;
							var name = data[iii].name;
							var fathername = data[iii].fathername;
							var mothername = data[iii].mothername;
							var gender = data[iii].gender;
							$('#gender').val(gender);
							// 														String disabled;
							// 														if(gender == "male"){

							// 															$('#female').prop('readonly',true);
							// // 															disabled="disabled";
							// 															document.getElementById("gender").disabled = true;
							// 														}else{
							// 															$('#gender').val(gender);
							// 															$('#male').prop('readonly',false);
							// 														}
							var gsm = data[iii].gsm;
							// 														alert(gsm+" Primary Number");
							var gsm2 = data[iii].gsm2;
							var dob = data[iii].dob;
							var email = data[iii].email;
							var relationstatus = data[iii].relationstatus;
							var educated = data[iii].educated;
							var education = data[iii].education;
							var adress = data[iii].adress;

							var mincome = data[iii].mincome;
							var cnic = data[iii].cnic;
							var city = data[iii].city;
							var installement = data[iii].installement;
							var installment_scheme = data[iii].installment_scheme;
							var down_payment = data[iii].down_payment;
							var date = data[iii].date;

							var guardian = data[iii].guardian;
							var guardiancell = data[iii].guardiancell;
							var guardianchild = data[iii].guardianchild;

							var isLoanTaken = data[iii].isLoanTaken;
							var donnerName = data[iii].donnerName;
							var loanPayment = data[iii].loanPayment;
							var loanperiod = data[iii].loanperiod;
							var installment = data[iii].installment;
							var remaining = data[iii].remaining;

							var isBankAccount = data[iii].isBankAccount;
							var bankName = data[iii].bankName;

							var ups = data[iii].ups;
							var electricity = data[iii].electricity;
							var generator = data[iii].generator;
							var solar = data[iii].solar;
							var otherelusage = data[iii].otherelusage;

							// 														alert(ups);
							if (ups != null) {
								// 														$('#ups').val(ups);
								// 														$('#ups').prop('readonly',true);
								document.getElementById('ups').checked = true;
								// 														document.getElementById('UPS').disabled = true;
							}

							// 														alert(electricity);
							if (electricity != null) {
								// 														$('#electricity').val(electricity);
								// 														$('#electricity').prop('readonly',true);
								document.getElementById('electricity').checked = true;
							}

							// 														alert(generator);
							if (generator != null) {
								// 														$('#generator').val(generator);
								// 														$('#generator').prop('readonly',true);
								document.getElementById('generator').checked = true;
							}

							// 														alert(solar);
							if (solar != null) {
								// 														$('#solar').val(solar);
								// 														$('#solar').prop('readonly',true);
								document.getElementById('solar').checked = true;
							}

							// 														alert(otherusage);
							if (otherelusage != null) {
								// 														$('#otherelusage').val(otherelusage);
								// 														$('#otherelusage').prop('readonly',true);
								document.getElementById('otherelusage').checked = true;
							}

							var electricityusage = data[iii].electricityusage;
							var electricityexpense = data[iii].electricityexpense;
							var majorexpensedescription = data[iii].majorexpensedescription;
							var majorexpenseamount = data[iii].majorexpenseamount;
							var totalexpense = data[iii].totalexpense;
							var other = data[iii].other;

							var home = data[iii].home;
							var car = data[iii].car;
							var bike = data[iii].bike;
							var washingMachine = data[iii].washingMachine;
							var fridge = data[iii].fridge;
							var tv = data[iii].tv;

							// 														alert(home+"---home");
							if (home == true) {
								// 														$('#home').val(home);
								// 														$('#home').prop('readonly',true);
								document.getElementById('home').checked = true;
							}

							// 														alert(car);
							if (car == true) {
								// 														$('#car').val(car);
								// 														$('#car').prop('readonly',true);
								document.getElementById('car').checked = true;
							} else {
								document.getElementById('car').checked = false;
							}

							if (bike == true) {
								// 														$('#bike').val(bike);
								// 														$('#bike').prop('readonly',true);
								document.getElementById('bike').checked = true;
							}

							if (washingMachine == true) {
								// 														$('#washingMachine').val(washingMachine);
								// 														$('#washingMachine').prop('readonly',true);
								document.getElementById('washingMachine').checked = true;
							}

							if (fridge == true) {
								// 														$('#fridge').val(fridge);
								// 														$('#fridge').prop('readonly',true);
								document.getElementById('fridge').checked = true;
							}

							if (tv == true) {
								// 														$('#tv').val(tv);
								// 														$('#tv').prop('readonly',true);
								document.getElementById('tv').checked = true;
							}

							var g1guarantorName = data[iii].g1guarantorName;
							var g1guarantorFather = data[iii].g1guarantorFather;
							var g1guarantorDob = data[iii].g1guarantorDob;
							var g1guarantorCnic = data[iii].g1guarantorCnic;
							var g1RelationToCustomer = data[iii].g1RelationToCustomer;
							var g1marritalStatus = data[iii].g1marritalStatus;
							// 														alert(g1marritalStatus);
							var g1guarantorPhone = data[iii].g1guarantorPhone;
							// 														alert("GSM 1"+g1guarantorPhone);
							var g1guarantorIncome = data[iii].g1guarantorIncome;
							var g1CompanyName = data[iii].g1CompanyName;
							var g1Desidition = data[iii].g1Desidition;
							var g1PhoneNumber = data[iii].g1PhoneNumber;
							var g1CompanyAddess = data[iii].g1CompanyAddess;
							var g1BusinessName = data[iii].g1BusinessName;
							var g1BusnessType = data[iii].g1BusnessType;
							var g1BusnessNumber = data[iii].g1BusnessNumber;
							var g1BusinessAddress = data[iii].g1BusinessAddress;

							$('#g1guarantorName').val(g1guarantorName);
							//$('#g1guarantorName').prop('readonly',true);

							$('#g1guarantorFather').val(g1guarantorFather);
							//$('#g1guarantorFather').prop('readonly',true);

							$('#g1guarantorDob').val(g1guarantorDob);
							//$('#g1guarantorDob').prop('readonly',true);

							$('#g1guarantorCnic').val(g1guarantorCnic);
							//$('#g1guarantorCnic').prop('readonly',true);

							$('#g1RelationToCustomer')
									.val(g1RelationToCustomer);
							//$('#g1RelationToCustomer').prop('readonly',true);

							if (g1marritalStatus == "Married") {
								// 														$('#g1marritalStatus1').val(marritalStatus);
								// 														$('#g1marritalStatus1').prop('readonly',true);
								document.getElementById('g1marritalStatus1').checked = true;
							} else {
								// 															$('#g1marritalStatus1').val(marritalStatus);
								//	 														$('#g1marritalStatus1').prop('readonly',true);
								document.getElementById('g1marritalStatus2').checked = false;
							}

							$('#g1guarantorPhone').val(g1guarantorPhone);
							//$('#g1guarantorPhone').prop('readonly',true);

							$('#g1guarantorIncome').val(g1guarantorIncome);
							//$('#g1guarantorIncome').prop('readonly',true);

							$('#g1CompanyName').val(g1CompanyName);
							//$('#g1CompanyName').prop('readonly',true);

							$('#g1Desidition').val(g1Desidition);
							//$('#g1Desidition').prop('readonly',true);

							$('#g1PhoneNumber').val(g1PhoneNumber);
							//$('#g1PhoneNumber').prop('readonly',true);

							$('#g1CompanyAddess').val(g1CompanyAddess);
							//$('#g1CompanyAddess').prop('readonly',true);

							$('#g1BusinessName').val(g1BusinessName);
							//$('#g1BusinessName').prop('readonly',true);

							$('#g1BusnessType').val(g1BusnessType);
							//$('#g1BusnessType').prop('readonly',true);

							$('#g1BusnessNumber').val(g1BusnessNumber);
							//$('#g1BusnessNumber').prop('readonly',true);

							$('#g1BusinessAddress').val(g1BusinessAddress);
							//$('#g1BusinessAddress').prop('readonly',true);

							var g2guarantorName = data[iii].g2guarantorName;
							var g2guarantorFather = data[iii].g2guarantorFather;
							var g2guarantorDob = data[iii].g2guarantorDob;
							var g2guarantorCnic = data[iii].g2guarantorCnic;
							var g2RelationToCustomer = data[iii].g2RelationToCustomer;
							var g2marritalStatus = data[iii].g2marritalStatus;
							// 														alert(g2marritalStatus+"===g2");
							var g2guarantorPhone = data[iii].g2guarantorPhone;
							// 														alert("GSM 2"+g2guarantorPhone);
							var g2guarantorIncome = data[iii].g2guarantorIncome;
							var g2CompanyName = data[iii].g2CompanyName;
							var g2Designation = data[iii].g2Designation;
							var g2PhoneNumber = data[iii].g2PhoneNumber;
							var g2CompanyAddress = data[iii].g2CompanyAddress;
							var g2BusinessName = data[iii].g2BusinessName;
							var g2BusinessType = data[iii].g2BusinessType;
							var g2BusinessNumber = data[iii].g2BusinessNumber;
							var g2BusinessAddress = data[iii].g2BusinessAddress;

							$('#g2guarantorName').val(g2guarantorName);
							//$('#g2guarantorName').prop('readonly',true);

							$('#g2guarantorFather').val(g2guarantorFather);
							//$('#g2guarantorFather').prop('readonly',true);

							$('#g2guarantorDob').val(g2guarantorDob);
							//$('#g2guarantorDob').prop('readonly',true);

							$('#g2guarantorCnic').val(g2guarantorCnic);
							//$('#g2guarantorCnic').prop('readonly',true);

							$('#g2RelationToCustomer')
									.val(g2RelationToCustomer);
							//$('#g2RelationToCustomer').prop('readonly',true);

							if (g2marritalStatus == "Married") {
								//	 														$('#g1marritalStatus1').val(marritalStatus);
								//	 														$('#g1marritalStatus1').prop('readonly',true);
								document.getElementById('g2marritalStatus1').checked = true;
							} else {
								//	 															$('#g1marritalStatus1').val(marritalStatus);
								//		 														$('#g1marritalStatus1').prop('readonly',true);
								document.getElementById('g2marritalStatus2').checked = true;
							}

							$('#g2guarantorPhone').val(g2guarantorPhone);
							//$('#g2guarantorPhone').prop('readonly',true);

							$('#g2guarantorIncome').val(g2guarantorIncome);
							//$('#g2guarantorIncome').prop('readonly',true);

							$('#g2CompanyName').val(g2CompanyName);
							//$('#g2CompanyName').prop('readonly',true);

							$('#g2PhoneNumber').val(g2PhoneNumber);
							//$('#g2PhoneNumber').prop('readonly',true);

							$('#g2Designation').val(g2Designation);
							//$('#g2Designation').prop('readonly',true);

							$('#g2CompanyAddress').val(g2CompanyAddress);
							//$('#g2CompanyAddress').prop('readonly',true);

							$('#g2BusinessName').val(g2BusinessName);
							//	$('#g2BusinessName').prop('readonly',true);

							$('#g2BusinessType').val(g2BusinessType);
							//$('#g2BusinessType').prop('readonly',true);

							$('#g2BusinessNumber').val(g2BusinessNumber);
							//$('#g2BusinessNumber').prop('readonly',true);

							$('#g2BusinessAddress').val(g2BusinessAddress);
							//$('#g2BusinessAddress').prop('readonly',true);

							var income = data[iii].income;
							var fincome = data[iii].fincome;
							var source = data[iii].source;

							$('#income').val(income);
							//$('#income').prop('readonly',true);

							$('#fincome').val(fincome);
							//$('#fincome').prop('readonly',true);

							$('#source').val(source);
							//$('#source').prop('readonly',true);

							var businessName = data[iii].businessName;
							var businessType = data[iii].businessType;
							var owner = data[iii].owner;
							var businessTime = data[iii].businessTime;
							var worker = data[iii].worker;
							var businessPlace = data[iii].businessPlace;
							var businessPhone = data[iii].businessPhone;
							var orgName = data[iii].orgName;
							var position = data[iii].position;
							var period = data[iii].period;
							var orgPhone = data[iii].orgPhone;
							var salary = data[iii].salary;
							var orgAddress = data[iii].orgAddress;

							$('#businessName').val(businessName);
							//$('#businessName').prop('readonly',true);

							$('#businessType').val(businessType);
							//$('#businessType').prop('readonly',true);

							$('#owner').val(owner);
							//$('#owner').prop('readonly',true);

							$('#businessTime').val(businessTime);
							//$('#businessTime').prop('readonly',true);

							$('#worker').val(worker);
							//$('#worker').prop('readonly',true);

							$('#businessPlace').val(businessPlace);
							//$('#businessPlace').prop('readonly',true);

							$('#businessPhone').val(businessPhone);
							//$('#businessPhone').prop('readonly',true);

							$('#orgName').val(orgName);
							//$('#orgName').prop('readonly',true);

							$('#position').val(position);
							//$('#position').prop('readonly',true);

							$('#period').val(period);
							//$('#period').prop('readonly',true);

							$('#orgPhone').val(orgPhone);
							//$('#orgPhone').prop('readonly',true);

							$('#orgAddress').val(orgAddress);
							//$('#orgAddress').prop('readonly',true);

							$('#salary').val(salary);
							//$('#salary').prop('readonly',true);

							var childname = data[iii].childname;
							var childrelation = data[iii].childrelation;
							var childmobile = data[iii].childmobile;
							var child_dob = data[iii].child_dob;
							var childcnic = data[iii].childcnic;

							$('#childname').val(childname);
							//	$('#childname').prop('readonly',true);

							$('#childrelation').val(childrelation);
							//$('#childrelation').prop('readonly',true);

							$('#childmobile').val(childmobile);
							//$('#childmobile').prop('readonly',true);

							$('#child_dob').val(child_dob);
							//$('#child_dob').prop('readonly',true);

							$('#childcnic').val(childcnic);
							//$('#childcnic').prop('readonly',true);

							//var applist = data[iii].applist;
							// 														var myJson = JSON.stringify(data[iii].applist);
							// 														alert(exeid);

							var salesman_name = data[iii].salesman_name;
							// 														alert(salesman_name);
							$('#exe_gsm').val(gsm);
							$('#exe_customerid').val(exeid);
							$('#exe_salesmanid').val(salesmanid);
							$('#gsalesman33').val(salesman_name);

							$('#fullname').val(name);
							//$('#fullname').prop('readonly',true);

							$('#fathername').val(fathername);
							//$('#fathername').prop('readonly',true);

							$('#mothername').val(mothername);
							//$('#mothername').prop('readonly',true);

							// 														if(gender == true){
							// // 															$('#gender').val(gender);
							// // 															$('#gender').prop('readonly',true);
							// 															document.getElementById('gender').checked = true;
							// 															document.getElementById("gender").disabled = true;
							// 														}else{
							// 															document.getElementById('gender').checked = true;
							// 															document.getElementById("gender").disabled = true;
							// 														}

							// 														alert(gsm+"-gsm1-----");
							$('#phone').val(gsm);
							//$('#phone').prop('readonly',true);

							$('#phone2').val(gsm2);
							//$('#phone2').prop('readonly',true);

							$('#email').val(email);
							//$('#email').prop('readonly',true);

							$('#status').val(relationstatus);
							//$('#status').prop('readonly',true);
							document.getElementById('status').checked = true;

							if (educated == true) {
								// 															$('#educated').val(educated);
								// 															$('#educated').prop('readonly',true);
								document.getElementById('educated').checked = true;
							} else {
								document.getElementById('Uneducated').checked = true;
							}

							$('#education').val(education);
							//$('#education').prop('readonly',true);

							$('#dob').val(dob);
							//$('#dob').prop('readonly',true);

							$('#address').val(adress);
							//$('#address').prop('readonly',true);

							$('#cnic').val(cnic);
							//$('#cnic').prop('readonly',true);

							// 														String disabled;
							if (city != " ") {
								$('#city').val(city);
								//$('#city').prop('readonly',true);
								//document.getElementById("city").disabled = true;
								// 														disabled="disabled";
							}

							$('#guardian').val(guardian);
							//$('#guardian').prop('readonly',true);

							$('#guardianPhone').val(guardiancell);
							//$('#guardianPhone').prop('readonly',true);

							$('#customerfamily').val(guardianchild);
							//$('#customerfamily').prop('readonly',true);

							// 														alert(isLoanTaken+"----isLoanTaken");
							if (isLoanTaken == true) {
								// 															$('#LoanTaken').val(isLoanTaken);
								// 															$('#LoanTaken').prop('readonly',true);
								document.getElementById('LoanTaken').checked = true;
								//document.getElementById("LoanTaken").disabled=true;
							} else {
								// 															$('#NoLoanTaken').val(isLoanTaken);
								// 															$('#NoLoanTaken').prop('readonly',true);
								document.getElementById('NoLoanTaken').checked = true;
							}

							$('#donnerName').val(donnerName);
							//$('#donnerName').prop('readonly',true);

							$('#loanPayment').val(loanPayment);
							//$('#loanPayment').prop('readonly',true);

							$('#loanperiod').val(loanperiod);
							//$('#loanperiod').prop('readonly',true);

							$('#installment').val(installment);
							//$('#installment').prop('readonly',true);

							$('#remaining').val(remaining);
							//$('#remaining').prop('readonly',true);
							// 														alert(isBankAccount+"====isBankAccount");
							// 														if(isBankAccount == true){
							// // 															$('#HaveAccount').val(isBankAccount);
							// // 															$('#HaveAccount').prop('readonly',true);
							// 															document.getElementById('HaveAccount').checked = true;
							// 															document.getElementById('HaveAccount').disabled = true;
							// 														}else{
							// // 															$('#NoHaveAccount').val(isBankAccount);
							// // 															$('#NoHaveAccount').prop('readonly',true);
							// 															document.getElementById('NoHaveAccount').checked = true;
							// 														}

							$('#bankName').val(bankName);
							//	$('#bankName').prop('readonly',true);

							$('#electricityusage').val(electricityusage);
							//	$('#electricityusage').prop('readonly',true);

							$('#electricityexpense').val(electricityexpense);
							//$('#electricityexpense').prop('readonly',true);

							$('#majorexpensedescription').val(
									majorexpensedescription);
							//$('#majorexpensedescription').prop('readonly',true);

							$('#majorexpenseamount').val(majorexpenseamount);
							//$('#majorexpenseamount').prop('readonly',true);

							$('#totalexpense').val(totalexpense);
							//$('#totalexpense').prop('readonly',true);

							$('#other').val(other);
							//$('#other').prop('readonly',true);

							// 														alert(myJson);
							document.getElementById("appliance_loop").innerHTML = " ";
							for (var i = 0; i < JSON
									.stringify(data[iii].applist).length; i++) {
								if ((JSON
										.stringify(data[iii].applist[i].applianceName)) != "") {
									// 															 alert(JSON.stringify(data[iii].applist[i].applianceName));
									var applist = JSON
											.stringify(data[iii].applist[i].applianceName);
								}
								$('#applianc').val(applist);
								document.getElementById("appliance_loop").innerHTML += "<table class=\"table table-bordered\">"
										+

										"<tr>"
										+ "<th>Appliance Name</th>"
										+ "<td id=\"applianc\">"
										+ applist
										+ "</td>" + "</tr>" + "</table>";
							}
							alert('after appliance');

							document.getElementById("child_loop").innerHTML = " ";
							// 														alert(JSON.stringify(data[iii].childlist).length);
							for (var j = 0; j < JSON
									.stringify(data[iii].childlist).length; j++) {
								// 															 alert(JSON.stringify(data[iii].childlist[j].child_name));
								var child = JSON
										.stringify(data[iii].childlist[j].child_name);

								$('#c').val(child);
								document.getElementById("child_loop").innerHTML += "<table class=\"table table-bordered\">"
										+

										"<tr>"
										+ "<th>Child Name</th>"
										+ "<td id=\"c\">"
										+ child
										+ "</td>"
										+ "</tr>" + "</table>";
							}

							// Child Triverses Loop
							// 														document.getElementById("child_loop").innerHTML = " ";
							// 														alert(JSON.stringify(data[iii].childlist).length);
							// 														for(i = 0; i<JSON.stringify(data[iii].childlist).length; i++){
							// 															 alert(JSON.stringify(data[iii].childlist[i].child_name));
							// 															 alert(data[iii].childlist[i]);
							// 															 var child_name = JSON.stringify(data[iii].childlist[i].child_name);
							// 															 var child_relation = JSON.stringify(data[iii].childlist[i].child_relation);
							// 															 var child_mobile = JSON.stringify(data[iii].childlist[i].child_mobile);
							// 															 var child_dob = JSON.stringify(data[iii].childlist[i].child_dob);
							// 															 var child_cnic = JSON.stringify(data[iii].childlist[i].child_cnic);

							// 															 $('#cname').val(child_name);
							// 															 $('#crelation').val(child_relation);
							// 															 $('#cmobile').val(child_mobile);
							// 															 $('#cdob').val(child_dob);
							// 															 $('#ccnic').val(child_cnic);
							// 															 document.getElementById("child_loop").innerHTML+= "<table class=\"table\">"+

							// 																"<tr>"+
							// 		                                						"<th>Child Name</th>"+
							// 		                                 						"<td id=\"cname\">"+child_name+"</td>"+
							// 		                               							"</tr>"+
							// 																"<tr>"+
							// 		                                						"<th>Child Relation</th>"+
							// 		                                 						"<td id=\"crelation\">"+child_relation+"</td>"+
							// 		                               							"</tr>"+
							// 																"<tr>"+
							// 		                                						"<th>Child Mobile</th>"+
							// 		                                 						"<td id=\"cmobile\">"+child_mobile+"</td>"+
							// 		                               							"</tr>"+
							// 																"<tr>"+
							// 		                                						"<th>Child Date Of Birth</th>"+
							// 		                                 						"<td id=\"cdob\">"+child_dob+"</td>"+
							// 		                               							"</tr>"+
							// 																"<tr>"+
							// 		                                						"<th>Child CNIC</th>"+
							// 		                                 						"<td id=\"ccnic\">"+child_cnic+"</td>"+
							// 		                               							"</tr>"+
							// 																"</table>";
							// 														}

							// 														alert(gsm+""+name+""+cell+""+status+""+mincome+""+cnic+""+dob+""+adress+""+city+""+installement+""+installment_scheme+""+down_payment+""+date);

							// 														document.getElementById("name").innerHTML = name;

							// 														document.getElementById("gsm").innerHTML = gsm;
							// 														document.getElementById("status").innerHTML = status;
							// 														document.getElementById("mincome").innerHTML = mincome;
							// 														document.getElementById("cnic_2").innerHTML = cnic;
							// 														document.getElementById("dob_2").innerHTML = dob;
							// 														document.getElementById("adress").innerHTML = adress;
							// 														document.getElementById("city_2").innerHTML = city;
							// 														document.getElementById("installement").innerHTML = installement;
							// 														document.getElementById("installment_scheme").innerHTML = installment_scheme;
							// 														document.getElementById("down_payment").innerHTML = down_payment;
							// 														document.getElementById("date").innerHTML = date;

							// 														if(data[iii].gsm==search_element)
							// 														{
							// 															alert("this is alredy a customer");
							// 														}
							// 														else
							// 														{
							// 															alert(false);
							// 														}

						}

					}//danish

				});
	}
</script>

</head>
<body onload="searchByNumber_();">

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		ArrayList<InventoryBean> inventory = InventoryBAL.getAppliances();
		ArrayList<CityBean> cities = DistrictOfficerBAL.getDistrictCities(bean.getUser_district());
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
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar">
		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
		<!-- end #header -->


		<!-- begin #sidebar -->

		<!-- end #sidebar -->


		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<!-- <ol class="breadcrumb pull-right">
				<li><a href="doDashboard.jsp">Home</a></li>
				<li><a href="javascript:;">Customer Form</a></li>

			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header"></h1>
			<!-- end page-header -->




			<script>
				function myFunction() {
					location.reload();
				}
				$(document).ready(function() {
					$("#butn").click(function() {
						/* Single line Reset function executes on click of Reset Button */
						$("#vinform1")[0].reset();

					});
				});
			</script>

			<div class="row" style="margin-top: 6%; margin-left: -21%;">
				<!-- begin col-12 -->
				<div class="col-md-12">

					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload" id="butn" onclick="myFunction();"><i
									class="fa fa-repeat"></i></a> <a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Form Wizards</h4>
						</div>
						<div class="panel-body">
							<form action="UpdateCustomerInfo" enctype="multipart/form-data"
								method="get" id="vinform1" class="form-horizontal" method="Post"
								data-parsley-validate="true" name="form-wizard">
								<div id="wizard">
									<ol>
										<li>Personal Information </li>
										<li>Family Details</li>
										<li>General Information</li>
										<li>Guarantors Information</li>

										<li>Income Details</li>
										<li>Appliance Details</li>
									</ol>
									<!-- begin wizard step-1 -->
									<!-- =============================Personal Information ==================== -->
									<div class="wizard-step-1">
										<fieldset>
											<legend class="pull-left width-full">Personal
												Information</legend>
											<!-- begin row -->
											<div class="row">
												<!-- begin col-4 -->

												<input type="hidden" name="CustomerId"
													value="<%=customerId%>">

												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Full Name*</label>
													<div class="col-sm-6">
														<input type="text" data-parsley-group="wizard-step-1"
															class="form-control" id="fullname" name="fullname"
															required="required"
															oninvalid="this.setCustomValidity('Please enter the full name')"
															onchange="nameValidation('fullname','customerNameResult','Invalid name') , this.setCustomValidity('')"
															onkeypress="nameCharahterValidation('fullname')" /> <span
															style="color: red; font-size: 12px;"
															id="customerNameResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="fathername" class="control-label col-sm-4">Father
														Name* </label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															data-parsley-group="wizard-step-1" id="fathername"
															required="required" name="fathername" pattern="^[A-z ]+$"
															oninvalid="this.setCustomValidity('Please enter the full name')"
															onchange="nameValidation('fathername','customerNameResult','Invalid name') , this.setCustomValidity('')"
															onkeypress="nameCharahterValidation('fathername')" /> <span
															style="color: red; font-size: 12px;"
															id="customerNameResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="mothername" class="control-label col-sm-4">Mother
														Name* </label>
													<div class="col-sm-6">
														<input required="required" type="text"
															data-parsley-group="wizard-step-1" class="form-control"
															id="mothername" name="mothername" pattern="^[A-z ]+$"
															oninvalid="this.setCustomValidity('Please enter the full name')"
															onchange="nameValidation('mothername','mothernameNameResult','Invalid name') , this.setCustomValidity('')"
															onkeypress="nameCharahterValidation('mothername')" /> <span
															style="color: red; font-size: 12px;"
															id="mothernameNameResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">Gender*</label>
													<div class="col-sm-6">
														<select class="form-control" id="gender"
															data-parsley-group="wizard-step-1" name="gender"
															required="required">
															<!-- 															oninvalid="this.setCustomValidity('Please enter the valid appliance name')" -->
															<!-- 															onchange="this.setCustomValidity('')" -->
															<option disabled selected>---Select Gender---</option>
															<option id="male" value="Male">Male</option>
															<option id="female" value="Female">Female</option>

														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="dob" class="control-label col-sm-4">Date
														of Birth*</label>
													<div class="col-sm-6">

														<input required="required" type="date" maxlength="10"
															data-parsley-group="wizard-step-1" class="form-control"
															id="dob" name="dob" max="2015-12-31" min="1905-01-01"
															oninvalid="this.setCustomValidity('Please enter the date of birth')"
															onchange="this.setCustomValidity(''), check_age('age','ageResult')" />

													</div>
												</div>

												<div class="form-group" id="CNIC_ID" hidden="true">


													<label for="cnic" class="control-label col-sm-4">CNIC*</label>
													<div class="col-sm-6">
														<input required="required" type="text"
															data-parsley-group="wizard-step-1" class="form-control"
															id="cnic" name="cnic" maxlength="15"
															data-column="customer_cnic" data-table="customer"
															oninvalid="this.setCustomValidity('Please enter the cnic number')"
															onchange="cnicValidation('cnic','customerCnicResult') , existingMobileNumber('cnic','customerCnicResult',this);"
															onkeypress="setNicDash(event,'cnic')" /> <span
															style="color: red; font-size: 12px;"
															id="customerCnicResult"></span>
													</div>
												</div>


												<div class="form-group">
													<label for="phone" class="control-label col-sm-4">
														Primary Mobile Number*:</label>
													<div class="col-sm-1">
														<input type="text" class="form-control" value="92"
															style="width: 65%" readonly />
													</div>
													<div class="col-sm-5" style="margin-left:">
														<input required="required" type="text"
															class="form-control" data-parsley-group="wizard-step-1"
															id="phone" data-column="customer_phone"
															data-table="customer" name="phone" required="required"
															maxlength="10"
															onchange="mobileNumberValidation('phone','phoneResult'), existingMobileNumber('phone','phoneResult',this);" />
														<span style="color: red; font-size: 12px;"
															id="phoneResult"></span>
													</div>
												</div>





												<div class="form-group">
													<label for="phone2" class="control-label col-sm-4">
														Secondary Mobile Number</label>
													<div class="col-sm-1" style="margin-left:">
														<input type="text" class="form-control" value="92"
															style="width: 65%" readonly="" /> <span
															style="color: red; font-size: 12px;" id="phoneResult"></span>
													</div>
													<div class="col-sm-5" style="margin-left:">
														<input type="text" class="form-control" id="phone2"
															name="phone2"
															onchange="existingMobileNumber('phone2', this)"
															maxlength="10"
															onchange="mobileNumberValidation('phone2','customerMobileResult') " />
														<span style="color: red; font-size: 12px;"
															id="customerMobileResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="email" class="control-label col-sm-4">Email
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="email"
															name="email" placeholder="email is optional"
															oninvalid="this.setCustomValidity('Please enter the full name')"
															data-column="email" data-table="customer"
															onchange="emailValidation('email','emailResult'),existingMobileNumber('email','emailResult',this); "
															onkeypress="emailCharahterValidation('')" /> <span
															style="color: red; font-size: 12px;" id="emailResult"></span>
													</div>
												</div>


												<div class="form-group">
													<label for="status" class="control-label col-sm-4">
														Relationship Status*: </label>
													<div class="col-sm-6">
														<div class="checkbox">
															<label class="checkbox-inline"> <input
																type="radio" name="status"
																data-parsley-group="wizard-step-1" id="status"
																value="Married" required="required" /> Married
															</label> <label class="checkbox-inline"> <input
																type="radio" name="status" id="status" value="Unmarried" />
																Unmarried
															</label> <br> <label class="checkbox-inline"> <input
																type="radio" name="status" id="status" value="widowed" />
																Widowed
															</label>
														</div>
													</div>
												</div>
												<script type="text/javascript">
													$(document)
															.ready(
																	function() {
																		$(
																				'input[type="radio"]')
																				.click(
																						function() {
																							if ($(
																									this)
																									.attr(
																											"value") == "educated") {
																								$(
																										".box2")
																										.not(
																												".educated")
																										.hide();
																								$(
																										".educated")
																										.show();

																							}
																							if ($(
																									this)
																									.attr(
																											"value") == "Uneducated") {
																								$(
																										".box2")
																										.not(
																												".Uneducated")
																										.hide();
																								$(
																										".Uneducated")
																										.show();
																							}

																						});
																	});
												</script>
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Education*: </label>
													<div class="col-sm-6">
														<div class="checkbox">
															<label class="checkbox-inline"> <input
																type="radio" name="educated" id="educated"
																data-parsley-group="wizard-step-1" value="educated"
																required="required" /> Educated
															</label> <label class="checkbox-inline"> <input
																type="radio" name="educated" id="Uneducated"
																value="Uneducated" /> Uneducated
															</label>
														</div>
													</div>
												</div>
												<div class="form-group educated box2" style="display: none;">
													<label for="education" class="control-label col-sm-4">Education*
													</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="education"
															name="education" pattern="^[A-z ]+$"
															oninvalid="this.setCustomValidity('Please enter the full name')"
															onchange="nameValidation('education','educationResult','Invalid name') , this.setCustomValidity('')"
															onkeypress="nameCharahterValidation('education')" /> <span
															style="color: red; font-size: 12px;" id="educationResult"></span>
													</div>
												</div>

												
												<div class="form-group">
													<label for="city" class="control-label col-sm-4">City*</label>
													<div class="col-sm-6">
														<select class="form-control" id="city"
															data-parsley-group="wizard-step-1" required name="city"
															oninvalid="this.setCustomValidity('Please enter the valid appliance name')"
															onchange="this.setCustomValidity('')">
															<option selected disabled>---Select city---</option>
															<%
																for (int m = 0; m < cities.size(); m++) {
																	int cities_id = cities.get(m).getCity_id();
															%>
															<option value="<%=cities_id%>">
																<%=cities.get(m).getCity_name()%></option>
															<%
																}
															%>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Home Address*</label>
													<div class="col-sm-6">
														<input required="required"
															data-parsley-group="wizard-step-1" type="text"
															class="form-control" id="address" name="address"
															oninvalid="this.setCustomValidity('Please enter the valid address')"
															onchange="addressValidation('address','addressResult') , this.setCustomValidity('')" />
														<span style="color: red; font-size: 12px;"
															id="addressResult"></span>
													</div>
												</div>
												<input type="hidden" name="customerid" id="<%=customerId%>">
												<div class="form-group" style="visibility: hidden;">
													<label for="age" class="control-label col-sm-4">Age*:
													</label>
													<div class="col-sm-6">

														<input class="form-control" id="age" name="age"
															readonly="readonly" "/> <span
															style="color: red; font-size: 12px;" id="ageResult"></span>

													</div>
												</div>
												<!-- end col-4 -->
											</div>
											<!-- end row -->
										</fieldset>
									</div>
									<!-- =============================Personal Information END ==================== -->
									<!-- end wizard step-1 -->

									<!-- ======================Family Details starts =================================== -->
									<!-- begin wizard step-2 -->
									<div class="wizard-step-2">
										<fieldset>
											<legend class="pull-left width-full">Family Details</legend>
											<!-- begin row -->
											<div class="row">
												<!-- begin col-6 -->
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Guardian Name*:</label>
													<div class="col-sm-6">
														<input required="required"
															data-parsley-group="wizard-step-2" type="text"
															class="form-control" id="guardian" name="guardian"
															oninvalid="this.setCustomValidity('Please enter the valid address')"
															onchange="nameValidation('guardian','guardianResult') , this.setCustomValidity('')" />
														<span style="color: red; font-size: 12px;"
															id="guardianResult"></span>
													</div>
												</div>

												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Guardian Mobile Number*: </label>

													<div class="col-sm-1" style="margin-left:">
														<input type="text" class="form-control" value="92"
															style="width: 65%" readonly="" /> <span
															style="color: red; font-size: 12px;" id="phoneResult"></span>
													</div>
													<div class="col-sm-5" style="margin-left:">
														<input required="required"
															data-parsley-group="wizard-step-2" type="text"
															class="form-control" id="guardianPhone"
															name="guardianPhone" maxlength="10"
															onchange="mobileNumberValidation('guardianPhone','guardianPhoneResult') , sendInfo();" />
														<span style="color: red; font-size: 12px;"
															id="guardianPhoneResult"></span>
													</div>
												</div>

												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Family Members*:</label>
													<div class="col-sm-6">
														<input required="required"
															data-parsley-group="wizard-step-2" type="text"
															class="form-control" id="customerfamily"
															name="customerfamily"
															oninvalid="this.setCustomValidity('Please enter the family members')"
															onkeyup="forms_family()"
															onchange="numericValidation('customerfamily', 'customerfamilyResult','Invalid family member') , this.setCustomValidity('')" />

														<span style="color: red; font-size: 12px;"
															id="customerfamilyResult"></span>
													</div>
												</div>
												<div class="form-group" id="forms_loop"></div>


												<div class="form-group" id="child_loop"></div>





												<script type="text/javascript">
													function forms_family() {
														var test = document
																.getElementById("customerfamily").value;
														document
																.getElementById("forms_loop").innerHTML = " ";
														for (var i = 1; i <= test; i++) {
															document
																	.getElementById("forms_loop").innerHTML +=

															"<div class=\"container\"><div class=\" col-sm-12 well\"><b>Family Member: "
																	+ i
																	+ "</b><legend></legend>"
																	+
																	//===================div for name=======================
																	"<div class=\"form-group\">"
																	+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Name*:</label>"
																	+ "<div class=\"col-sm-6\" >"
																	+ "<input type=\"text\"  pattern=\"^[A-z ]+$\" id=\"childname"+i+"\" name=\"childname"+i+"\" class=\"form-control\" data-parsley-group=\"wizard-step-2\" required=\"required\">"
																	+ "</div>"
																	+ "</div>"
																	+
																	//===================div for relation====================	
																	"<div class=\"form-group\">"
																	+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Relation*:</label>"
																	+ "<div class=\"col-sm-6\" >"
																	+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" pattern=\"^[A-z ]+$\" id=\"childrelation"+i+"\" name=\"childrelation"+i+"\" class=\"form-control\" required=\"required\" >"
																	+ "</div>"
																	+ "</div>"
																	+
																	//====================div for Mobile=====================
																	"<div class=\"form-group\">"
																	+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Mobile*:</label>"
																	+ "<div class=\"col-sm-1\">"
																	+ "<input  type=\"text\" class=\"form-control\" value=\"92\" style=\"width:65%\" readonly />"
																	+ "</div>"
																	+ "<div class=\"col-sm-5\" >"
																	+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" maxlength=\"10\" id=\"childmobile"
																	+ i
																	+ "\" name=\"childmobile"
																	+ i
																	+ "\" class=\"form-control\" onchange=\"mobileNumberValidation('childmobile"
																	+ i
																	+ "','childMobileResult"
																	+ i
																	+ "')\" required=\"required\" >"
																	+ "<span style=\"color: red; font-size: 12px;\"	id=\"childMobileResult"+i+"\"></span>"
																	+ "</div>"
																	+ "</div>"
																	+
																	//====================div for Date of Birth=====================
																	"<div class=\"form-group\">"
																	+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Date of birth*:</label>"
																	+ "<div class=\"col-sm-6\" >"
																	+ "<input type=\"date\" data-parsley-group=\"wizard-step-2\" maxlength=\"12\" id=\"child_dob"
																	+ i
																	+ "\" name=\"child_dob"
																	+ i
																	+ "\" class=\"form-control\" onchange=\"check_child_ageD('child_dob"
																	+ i
																	+ "','CHILD_CNIC_ID"
																	+ i
																	+ "')\" required=\"required\" oninvalid=\"this.setCustomValidity('Please select the date of birth')\" >"
																	+ "</div>"
																	+ "</div>"
																	+
																	//====================div for Cnic=====================
																	"<div class=\"form-group\" id=\"CHILD_CNIC_ID"+i+"\" hidden=\"true\">"
																	+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Cnic*:</label>"
																	+ "<div class=\"col-sm-6\" >"
																	+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" maxlength=\"15\" id=\"childcnic"
																	+ i
																	+ "\" name=\"childcnic"
																	+ i
																	+ "\" class=\"form-control\" oninvalid=\"this.setCustomValidity('Please enter the cnic number')\" onchange=\"cnicValidation('customercnic','customerCnicResult') , check_customer_cnic()\" onkeypress = \"setNicDash(event,'customercnic')\" required=\"required\" value=\"45301-5569855-5\">"
																	+ "</div>"
																	+ "</div>"
																	+ "</div>"
																	+

																	//====================div for Monthly income=====================
																	"<div class=\"form-group\" id=\"CHILD_CNIC_ID"+i+"\" hidden=\"true\">"
																	+ "<label for=\"inputEmail3\" class=\"control-label col-sm-4\">Monthly income*:</label>"
																	+ "<div class=\"col-sm-6\" >"
																	+ "<input type=\"text\" data-parsley-group=\"wizard-step-2\" id=\"member"+i+"\" name=\"childmonthlyincome"+i+"\" class=\"form-control\ required=\"required\" value=\"20000\">";
																	"</div>"
																	+ "</div>"
																	+ "</div></div>";
																										
														}

													}

													function child_change() {
														$(document)
																.ready(
																		function() {
																			// 													alert('hello11');
																			$(
																					"select")
																					.change(
																							function() {
																								// 												    	alert('hello12');
																								$(
																										this)
																										.find(
																												"option:selected")
																										.each(
																												function() {
																													// 												        	alert('hello13');
																													if ($(
																															this)
																															.attr(
																																	"value") == "child_Employ") {
																														// 												            	alert('hello14');
																														$(
																																".box4")
																																.not(
																																		".child_Employ")
																																.hide();
																														$(
																																".child_Employ")
																																.show();
																													} else if ($(
																															this)
																															.attr(
																																	"value") == "child_Business") {
																														$(
																																".box4")
																																.not(
																																		".child_Business")
																																.hide();
																														$(
																																".child_Business")
																																.show();
																													}
																													// 												            else if($(this).attr("value")=="Both"){
																													// 												                $(".box3").not(".Both").hide();
																													// 												                $(".Both").show();
																													// 												            }
																													// 												            else{
																													// 												                $(".box3").hide();
																													// 												            }
																												});
																							})
																					.change();
																		});

													}
												</script>

												<!-- end col-6 -->
											</div>
											<!-- end row -->
										</fieldset>
									</div>
									<!-- =============================Family Details END ==================== -->
									<!-- end wizard step-2 -->

									<!-- =============================General Information Starts ==================== -->
									<!-- begin wizard step-3 -->
									<div class="wizard-step-3">
										<fieldset>
											<legend class="pull-left width-full">General
												Information</legend>
											<!-- begin row -->
											<div class="row">
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">Have
														you got any loan from us or any bank or any other org? </label>

													<div class="col-sm-6" style="margin-left: -2%">
														<div class="checkbox">
															<label class="checkbox-inline"> <input
																type="radio" name="isLoanTaken" id="LoanTaken"
																onclick="loan('ON')" /> Yes
															</label> <label class="checkbox-inline"> <input
																type="radio" name="isLoanTaken" id="NoLoanTaken"
																onclick="loan('OFF')" /> No
															</label>
														</div>
													</div>
												</div>

												<div class="form-group box oloans1" id="loans1"
													style="display: none;">
													<div class="form-group">
														<label for="donnerName" class="control-label col-sm-4">Bank/Org
															Name </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="donnerName"
																name="donnerName"
																oninvalid="this.setCustomValidity('Please enter the donnerName')"
																onchange="nameValidation('donnerName','edonnerNameResult','Invalid name') , this.setCustomValidity('')"
																onkeypress="nameCharahterValidation('donnerName')" /> <span
																style="color: red; font-size: 12px;"
																id="edonnerNameResult"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="control-label col-sm-4">
															Total Loan </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="loanPayment"
																name="loanPayment" onchange="this.setCustomValidity('')" />
														</div>
													</div>

													<div class="form-group">
														<label for="loanperiod" class="control-label col-sm-4">Loan
															Period </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="loanperiod"
																name="loanperiod"
																oninvalid="this.setCustomValidity('Please enter the period')"
																onchange="numberValidation('loanperiod','eloanperiodResult','Invalid name') , this.setCustomValidity('')"
																onkeypress="nameCharahterValidation('loanperiod')" /> <span
																style="color: red; font-size: 12px;"
																id="eloanperiodResult"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="installment" class="control-label col-sm-4">
															Monthly Installment </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="installment"
																name="otherInstallment"
																onchange="numberValidation('installment','installmentResult','Invalid name'), this.setCustomValidity('')" />
														</div>
														<span style="color: red; font-size: 12px;"
															id="installmentResult"></span>
													</div>

													<div class="form-group">
														<label for="remaining" class="control-label col-sm-4">
															Remaining Payment </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="remaining"
																name="remaining"
																onchange="numberValidation('remaining','remainingResult','Invalid name'), this.setCustomValidity('')" />
															<span style="color: red; font-size: 12px;"
																id="remainingResult"></span>
														</div>
													</div>
												</div>
												<div class="form-group box oloans0" id="loans0"
													style="display: none;">
													<!-- 											 non of fields -->

												</div>
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">Have
														you any bank account? </label>
													<div class="col-sm-6" style="margin-left: -2%">
														<div class="checkbox">
															<label class="checkbox-inline"> <input
																type="radio" name="isBankAccount" id="HaveAccount"
																onclick="bank('ON')" /> Yes
															</label> <label class="checkbox-inline"> <input
																type="radio" name="isBankAccount" id="NoHaveAccount"
																onclick="bank('OFF')" /> No
															</label>
														</div>
													</div>
												</div>
												<div class="form-group box bank1" id="bank1"
													style="display: none;">
													<div class="form-group">
														<label for="bankName" class="control-label col-sm-4">
															Bank Name </label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="bankName"
																name="bankName"
																oninvalid="this.setCustomValidity('Please enter the Bank Name')"
																onchange="nameValidation('bankName','ebankNameResult','Invalid name') , this.setCustomValidity('')"
																onkeypress="nameCharahterValidation('bankName')" /> <span
																style="color: red; font-size: 12px;"
																id="ebankNameResult"></span>
														</div>
													</div>
												</div>
												<div class="form-group box bank0" id="bank0"
													style="display: none;">
													<!-- 											 non of fields -->
													<!-- 												<div class="form-group"> -->
													<!-- 													<label for="bankName" class="control-label col-sm-4">Bank Name -->
													<!-- 														</label> -->
													<!-- 													<div class="col-sm-6"> -->
													<!-- 														<input  type="text" class="form-control" id="bankName" disabled="disabled" -->
													<!-- 															name="bankName"  value="Noklhkjhkjh" -->
													<!-- 															oninvalid="this.setCustomValidity('Please enter the Bank Name')" -->
													<!-- 															onchange="nameValidation('bankName','ebankNameResult','Invalid name') , this.setCustomValidity('')" onkeypress = "nameCharahterValidation('bankName')"/>  -->
													<!-- 															<span style="color: red; font-size: 12px;" -->
													<!-- 															id="ebankNameResult"></span> -->
													<!-- 													</div> -->
													<!-- 												</div> -->

												</div>

												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Electricity Usage </label>
													<div class="col-sm-6">
														<div class="checkbox">
															<label class="checkbox-inline"> <input
																type="checkbox" name="ups" id="ups" value="UPS" /> UPS
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="electricity" id="electricity"
																value="Electric Supply" /> Electric Supply
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="generator" id="generator"
																value="Generator" /> Generator
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="solar" id="solar" value="Solar" />
																Solar System
															</label><label class="checkbox-inline"> <input
																type="checkbox" name="otherelusage" id="otherelusage"
																value="otherelusage" /> Other
															</label>
														</div>
													</div>
												</div>

												<script type="text/javascript">
													$(document)
															.ready(
																	function() {
																		$(
																				'input[type="checkbox"]')
																				.click(
																						function() {
																							if ($(
																									this)
																									.attr(
																											"value") == "otherelusage") {
																								$(
																										".otherelusage")
																										.toggle();
																							}

																						});
																	});
													// 												$("#other").click(function () {
													// 													if ($(this).prop('checked') === true) {
													// 													    $('#electricityusage').show();
													// 													    $('input[name="electricityusage"]').prop('required',true);

													// 													} else {
													// 													    $('#electricityusage').hide();
													// 													    $('input[name="electricityusage"]').prop('required',false);

													// 													}
													// 													});
												</script>


												<div class="form-group box otherelusage"
													style="display: none;">
													<label for="electricityusage"
														class="control-label col-sm-4">Other Electricity
														Usage </label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="electricityusage" name="electricityusage"
															placeholder="Type Other Electricity Usage"
															oninvalid="this.setCustomValidity('Please enter the Other Electricity Usage')"
															onchange="nameValidation('electricityusage','electricityusageResult','Please enter the Other Electricity Usage') , this.setCustomValidity('')"
															onkeypress="nameCharahterValidation('electricityusage')" />
														<span style="color: red; font-size: 12px;"
															id="electricityusageResult"></span>
													</div>
												</div>

												<div class="form-group">
													<label for="electricityexpense"
														class="control-label col-sm-4">Electricity
														Expenses </label>
													<div class="col-sm-6">
														<input required="required"
															data-parsley-group="wizard-step-3" type="text"
															class="form-control" id="electricityexpense"
															name="electricityexpense"
															oninvalid="this.setCustomValidity('Please enter the Other Electricity Usage')"
															onchange="numericValidation('electricityexpense','electricityexpenseResult','Please enter the Other Electricity Usage') ,this.setCustomValidity('')" />
														<span style="color: red; font-size: 12px;"
															id="electricityexpenseResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="majorexpensedescription"
														class="control-label col-sm-4">Major Expenses
														Description </label>
													<div class="col-sm-6">
														<input required="required"
															data-parsley-group="wizard-step-3" type="text"
															class="form-control" id="majorexpensedescription"
															name="majorexpensedescription"
															onchange="nameValidation('majorexpensedescription','majorexpensedescriptionResult','Please Enter Major Expenses Description') , this.setCustomValidity('')"
															onkeypress="nameCharahterValidation('majorexpensedescription')" />
														<span style="color: red; font-size: 12px;"
															id="majorexpensedescriptionResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="majorexpenseamount"
														class="control-label col-sm-4"> Major Expenses
														Amount </label>
													<div class="col-sm-6">
														<input required="required" type="text"
															data-parsley-group="wizard-step-3" class="form-control"
															id="majorexpenseamount" name="majorexpenseamount"
															onchange="numericValidation('majorexpenseamount','majorexpenseamountResult','Enter Major Expenses Amount') ,this.setCustomValidity('')" />
														<span style="color: red; font-size: 12px;"
															id="majorexpenseamountResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="totalexpense" class="control-label col-sm-4">
														Total Expenses </label>
													<div class="col-sm-6">
														<input required="required" type="text"
															class="form-control" id="totalexpense"
															name="totalexpense" data-parsley-group="wizard-step-3"
															onchange="numericValidation('majorexpenseamount','majorexpenseamountResult','Enter Total Expenses') ,this.setCustomValidity('')" />
														<span style="color: red; font-size: 12px;"
															id="majorexpenseamountResult"></span>
													</div>
												</div>
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														House Hold Asset </label>
													<div class="col-sm-6">
														<div class="checkbox">
															<label class="checkbox-inline"> <input
																type="checkbox" name="home" id="home" value="1" /> Home
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="car" id="car" value="1" /> Car
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="bike" id="bike" value="1" />
																Motorcycle
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="washingMachine"
																id="washingMachine" value="1" /> Washing Machine
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="fridge" id="fridge" value="1" />
																Fridge
															</label> <label class="checkbox-inline"> <input
																type="checkbox" name="tv" id="tv" value="1" />
																TV/Computer
															</label>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">
														Other Assets</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="other"
															name="other"
															oninvalid="this.setCustomValidity('Please enter the valid Other Assets')"
															onchange="nameValidation('other','otherResult','Please enter the valid Other Assets') , this.setCustomValidity('')" />
														<span style="color: red; font-size: 12px;"
															id="otherResult"></span>
													</div>
												</div>

											</div>

											<!-- end row -->
										</fieldset>
									</div>

									<!-- =============================General Information End ==================== -->
									<!-- end wizard step-3 -->
									<!-- begin wizard step-4 -->
									<!-- =============================Gaurontor Information Starts  ==================== -->
									<div class="wizard-step-4">
										<fieldset>
											<legend class="pull-left width-full">Gaurantor
												Details</legend>

											<div class="row">
												<div class="container">
													<div class="row well " style="margin-top: 4%;">
														<legend>Family Gaurantor</legend>
														<div class="form-group">
															<label for="g1guarantorName"
																class="control-label col-sm-4"> Name </label>
															<div class="col-sm-6">
																<input type="text" class="form-control"
																	id="g1guarantorName" data-parsley-group="wizard-step-4"
																	required="required" name="g1guarantorName"
																	oninvalid="this.setCustomValidity('Please enter the orgName')"
																	onchange="nameValidation('g1guarantorName','g1guarantorNameResult','Please enter the name') , this.setCustomValidity('')"
																	onkeypress="nameCharahterValidation('g1guarantorName')" />
																<span style="color: red; font-size: 12px;"
																	id="g1guarantorNameResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="g1guarantorFather"
																class="control-label col-sm-4">Father </label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g1guarantorFather"
																	data-parsley-group="wizard-step-4"
																	name="g1guarantorFather"
																	oninvalid="this.setCustomValidity('Please enter Father Name')"
																	onchange="nameValidation('g1guarantorFather','g1guarantorFatherResult','Please enter Father Name') , this.setCustomValidity('')"
																	onkeypress="nameCharahterValidation('g1guarantorFather')" />
																<span style="color: red; font-size: 12px;"
																	id="g1guarantorFatherResult"></span>
															</div>
														</div>
														<div class="form-group">
															<label for="g1guarantorDob"
																class="control-label col-sm-4">Guarantor Date of
																Birth</label>
															<div class="col-sm-6">

																<input required="required" type="date"
																	class="form-control" id="g1guarantorDob"
																	data-parsley-group="wizard-step-4"
																	name="g1guarantorDob"
																	oninvalid="this.setCustomValidity('Please enter the date of birth')"
																	onchange="this.setCustomValidity(''), check_g1age('g1guarantorDob','g1guarantorDobResult')" />
																<span style="color: red; font-size: 12px;"
																	id="g1guarantorDobResult"></span>

															</div>
														</div>
														<div class="form-group" hidden="true" id="G1_CNIC_ID_SPAN">

															<span id="messagecnic" name="messagecnic"
																style="display: none; color: red; float: right; width: 73%;">This
																CNIC already exist in our system</span>

														</div>

														<div class="form-group" id="G1_CNIC_ID" hidden="true">


															<label for="g1guarantorCnic"
																class="control-label col-sm-4">CNIC</label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g1guarantorCnic"
																	name="g1guarantorCnic" maxlength="15"
																	onchange="cnicValidation('g1guarantorCnic','g1guarantorCnicCnicResult') , check_customer_cnic()"
																	onkeypress="setNicDash(event,'g1guarantorCnic')" /> <span
																	style="color: red; font-size: 12px;"
																	id="g1guarantorCnicCnicResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="g1RelationToCustomer"
																class="control-label col-sm-4">Relation to
																Customer*: </label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g1RelationToCustomer"
																	name="g1RelationToCustomer"
																	oninvalid="this.setCustomValidity('Please enter the Relation to Customer')"
																	onchange="nameValidation('g1RelationToCustomer','g1RelationToCustomerResult','Please enter the Relation to Customer') , this.setCustomValidity('')"
																	onkeypress="nameCharahterValidation('g1RelationToCustomer')" />
																<span style="color: red; font-size: 12px;"
																	id="g1RelationToCustomerResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="" class="control-label col-sm-4">
																Relationship Status*: </label>
															<div class="col-sm-6" style="margin-left: -2%">
																<div class="checkbox">
																	<label class="checkbox-inline"> <input
																		type="radio" name="g1marritalStatus"
																		id="g1marritalStatus1" value="Married" /> Married
																	</label> <label class="checkbox-inline"> <input
																		type="radio" name="g1marritalStatus"
																		id="g1marritalStatus2" value="Unmarried" /> Unmarried
																	</label>
																</div>
															</div>
														</div>

														<div class="form-group">
															<span id="messagephone" name="messagephone"
																style="display: none; color: red; float: right; width: 73%;">
																This number already exist in our System</span>
														</div>

														<div class="form-group">

															<div id="messagephoneNumber" name="messagephoneNumber"
																style="display: none; color: red">Please enter
																valid number</div>


															<label for="g1guarantorPhone"
																class="control-label col-sm-4">Guarantor Cell
																Number*: </label>
															<div class="col-sm-1">
																<input type="text" class="form-control" value="92"
																	style="width: 65%" readonly />
															</div>
															<div class="col-sm-5" style="margin-left:">
																<input required="required" type="text"
																	class="form-control" id="g1guarantorPhone"
																	name="g1guarantorPhone" maxlength="10"
																	onchange="mobileNumberValidation('g1guarantorPhone','g1guarantorPhoneResult') , sendInfo();" />
																<span style="color: red; font-size: 12px;"
																	id="g1guarantorPhoneResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="income" class="control-label col-sm-4">Monthly
																Income*:</label>
															<div class="col-sm-6">
																<select class="form-control" id="g1guarantorIncome"
																	required data-parsley-group="wizard-step-4"
																	name="g1guarantorIncome">
																	<option selected disabled>---Select monthly
																		income---</option>
																	<%
																		for (int m = 0; m < salary.size(); m++) {
																			int salary_id = salary.get(m).getSalary_id();
																	%>
																	<option value="<%=salary_id%>">Rs:
																		<%=salary.get(m).getSalary_range()%></option>
																	<%
																		}
																	%>
																</select>
															</div>
														</div>


														<script type="text/javascript">
															// 											function change_g1BusinessType(){
															// 												var g1 = document.getElementById(g1BusinessType).value;
															// 												alert(g1);
															// 												alert('ali');
															// 											}									

															$(document)
																	.ready(
																			function() {
																				$(
																						"select")
																						.change(
																								function() {
																									$(
																											this)
																											.find(
																													"option:selected")
																											.each(
																													function() {
																														if ($(
																																this)
																																.attr(
																																		"value") == "g1Employ") {
																															$(
																																	".box3")
																																	.not(
																																			".g1Employ")
																																	.hide();
																															$(
																																	".g1Employ")
																																	.show();
																														} else if ($(
																																this)
																																.attr(
																																		"value") == "g1Business") {
																															$(
																																	".box3")
																																	.not(
																																			".g1Business")
																																	.hide();
																															$(
																																	".g1Business")
																																	.show();
																														} else if ($(
																																this)
																																.attr(
																																		"value") == "g1Both") {
																															// 												                $(".box3").not(".g1Both").hide();
																															$(
																																	".g1Both")
																																	.show();
																														}
																														// 												            else{
																														// 												                $(".box3").hide();
																														// 												            }
																													});
																								})
																						.change();
																			});
														</script>


														<div class="form-group">
															<label for="" class="control-label col-sm-4"> Job
																/Business Type </label>
															<div class="col-sm-6">
																<select required="required" class="form-control"
																	id="g1BusinessType" name="g1BusinessType"
																	oninvalid="this.setCustomValidity('Please enter the period')"
																	onchange=" this.setCustomValidity('')">

																	<option>Select Source Of Income</option>
																	<option value="g1Employ">Employ</option>
																	<option value="g1Business">Business</option>
																	<option value="g1Both">Employ and Business</option>
																</select> <span style="color: red; font-size: 12px;"
																	id="periodResult"></span>
															</div>
														</div>

														<div class="form-group g1Employ g1Both box3" id=""
															style="display: none;">


															<div class="form-group">
																<label for="g1CompanyName"
																	class="control-label col-sm-4"> Company Name </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g1CompanyName"
																		name="g1CompanyName" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the Business/Org Name')"
																		onchange="nameValidation('g1CompanyName','g1CompanyNameNameResult','Invalid Company Name') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="g1CompanyNameNameResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="g1Desidition" class="control-label col-sm-4">
																	Designation </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g1Desidition"
																		name="g1Desidition" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the Designation')"
																		onchange="nameValidation('g1Desidition','g1DesiditionResult','Invalid Designation name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('g1Desidition')" />
																	<span style="color: red; font-size: 12px;"
																		id="g1DesiditionResult"></span>
																</div>
															</div>


															<div class="form-group">
																<span id="messagephone" name="messagephone"
																	style="display: none; color: red; float: right; width: 73%;">
																	This number already exist in our System</span>
															</div>

															<div class="form-group">

																<div id="messagephoneNumber" name="messagephoneNumber"
																	style="display: none; color: red">Please enter
																	valid number</div>


																<label for="inputEmail3" class="control-label col-sm-4">Company
																	number </label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" value="92"
																		style="width: 65%" readonly />
																</div>
																<div class="col-sm-5">
																	<input required="required" type="text"
																		class="form-control" id="g1PhoneNumber"
																		name="g1PhoneNumber" maxlength="10"
																		onchange="mobileNumberValidation('g1PhoneNumber','g1PhoneNumberResult') , sendInfo();" />
																	<span style="color: red; font-size: 12px;"
																		id="g1PhoneNumberResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="g1CompanyAddess"
																	class="control-label col-sm-4"> company address</label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g1CompanyAddess"
																		name="g1CompanyAddess" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the Guardian Address')"
																		onchange="addressValidation('g1CompanyAddess','g1CompanyAddessResult') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="g1CompanyAddessResult"></span>
																</div>
															</div>

														</div>


														<div class="form-group g1Business g1Both box3" id=""
															style="display: none;">


															<div class="form-group">
																<label for="g1BusinessName"
																	class="control-label col-sm-4"> Business name </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g1BusinessName"
																		name="g1BusinessName" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the Business/Org Name')"
																		onchange="nameValidation('g1BusinessName','g1BusinessNameNameResult','Please enter the Business/Org Name') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="g1BusinessNameNameResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="g1BusnessType"
																	class="control-label col-sm-4"> Business type </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g1BusnessType"
																		name="g1BusnessType" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the period')"
																		onchange="nameValidation('g1BusnessType','g1BusnessTypeResult','Please enter the Business type') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('g1BusnessType')" />
																	<span style="color: red; font-size: 12px;"
																		id="g1BusnessTypeResult"></span>
																</div>
															</div>


															<div class="form-group">
																<span id="messagephone" name="messagephone"
																	style="display: none; color: red; float: right; width: 73%;">
																	This number already exist in our System</span>
															</div>

															<div class="form-group">

																<div id="messagephoneNumber" name="messagephoneNumber"
																	style="display: none; color: red">Please enter
																	valid number</div>


																<label for="g1BusnessNumber"
																	class="control-label col-sm-4">Business Number</label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" value="92"
																		style="width: 65%" readonly />
																</div>
																<div class="col-sm-5" style="margin-left:">
																	<input required="required" type="text"
																		class="form-control" id="g1BusnessNumber"
																		name="g1BusnessNumber" value="Noklhkjhkjh"
																		maxlength="10"
																		onchange="mobileNumberValidation('g1BusnessNumber','g1BusnessNumberResult') , sendInfo();" />
																	<span style="color: red; font-size: 12px;"
																		id="g1BusnessNumberResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">
																	Business Address</label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g1BusinessAddress"
																		name="g1BusinessAddress" value="Noklhkjhkjh"
																		onchange="addressValidation('g1BusinessAddress','g1BusinessAddressResult','Enter Business Address') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="g1BusinessAddressResult"></span>
																</div>
															</div>

														</div>
													</div>


													<!-- =======Outsider Gaurontior ======== -->

													<div class="row well" style="margin-top: 4%">
														<legend>Outsider Gaurantor</legend>
														<div class="form-group">
															<label for="g2guarantorName"
																class="control-label col-sm-4"> Name </label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g2guarantorName"
																	name="g2guarantorName" value="Noklhkjhkjh"
																	oninvalid="this.setCustomValidity('Please enter the Name')"
																	onchange="nameValidation('g2guarantorName','g2guarantorNameResult','Please enter the  name') , this.setCustomValidity('')"
																	onkeypress="nameCharahterValidation('g2guarantorName')" />
																<span style="color: red; font-size: 12px;"
																	id="g2guarantorNameResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="g2guarantorFather"
																class="control-label col-sm-4">Father </label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g2guarantorFather"
																	name="g2guarantorFather" value="Noklhkjhkjh"
																	oninvalid="this.setCustomValidity('Please enter the position')"
																	onchange="nameValidation('g2guarantorFather','g2guarantorFatherResult','Please enter the Fahter name') , this.setCustomValidity('')"
																	onkeypress="nameCharahterValidation('g2guarantorFather')" />
																<span style="color: red; font-size: 12px;"
																	id="g2guarantorFatherResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="g2guarantorDob"
																class="control-label col-sm-4">Guarantor Date of
																Birth</label>
															<div class="col-sm-6">

																<input required="required" type="date"
																	class="form-control" id="g2guarantorDob"
																	name="g2guarantorDob"
																	oninvalid="this.setCustomValidity('Please enter the date of birth')"
																	onchange="this.setCustomValidity(''), check_g2age('g2guarantorDob','g2guarantorDobResult')" />
																<span style="color: red; font-size: 12px;"
																	id="g2guarantorDobResult"></span>

															</div>
														</div>

														<div class="form-group" id="G2_CNIC_ID" hidden="true">


															<label for="g2guarantorCnic"
																class="control-label col-sm-4">CNIC</label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g2guarantorCnic"
																	name="g2guarantorCnic" maxlength="15"
																	value="45301-5555555-6"
																	onchange="cnicValidation('g2guarantorCnic','g2guarantorCnicResult') , check_customer_cnic()"
																	onkeypress="setNicDash(event,'g2guarantorCnic')" /> <span
																	style="color: red; font-size: 12px;"
																	id="g2guarantorCnicResult"></span>
															</div>
														</div>



														<div class="form-group">
															<label for="g2RelationToCustomer"
																class="control-label col-sm-4">Relation to
																Customer </label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g2RelationToCustomer"
																	name="g2RelationToCustomer" value="Noklhkjhkjh"
																	oninvalid="this.setCustomValidity('Please enter the period')"
																	onchange="nameValidation('g2RelationToCustomer','g2RelationToCustomerResult','Please enter Relation to Customer') , this.setCustomValidity('')"
																	onkeypress="nameCharahterValidation('g2RelationToCustomer')" />
																<span style="color: red; font-size: 12px;"
																	id="g2RelationToCustomerResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="" class="control-label col-sm-4">
																Relationship Status </label>
															<div class="col-sm-6">
																<div class="checkbox">
																	<label class="checkbox-inline"> <input
																		type="radio" name="g2marritalStatus"
																		id="g2marritalStatus1" value="Married" /> Married
																	</label> <label class="checkbox-inline"> <input
																		type="radio" name="g2marritalStatus"
																		id="g2marritalStatus2" value="Unmarried" /> Unmarried
																	</label>
																</div>
															</div>
														</div>
														<div class="form-group">
															<span id="messagephone" name="messagephone"
																style="display: none; color: red; float: right; width: 73%;">
																This number already exist in our System</span>
														</div>

														<div class="form-group">

															<div id="messagephoneNumber" name="messagephoneNumber"
																style="display: none; color: red">Please enter
																valid number</div>


															<label for="g2guarantorPhone"
																class="control-label col-sm-4"> Guarantor Cell
																Number*: </label>
															<div class="col-sm-1">
																<input type="text" class="form-control" value="92"
																	style="width: 65%" readonly />
															</div>
															<div class="col-sm-5" style="margin-left:">
																<input required="required" type="text"
																	class="form-control" id="g2guarantorPhone"
																	name="g2guarantorPhone" maxlength="10"
																	onchange="mobileNumberValidation('g2guarantorPhone','g2guarantorPhoneResult') , sendInfo();" />
																<span style="color: red; font-size: 12px;"
																	id="g2guarantorPhoneResult"></span>
															</div>
														</div>

														<div class="form-group">
															<label for="g2guarantorIncome"
																class="control-label col-sm-4"> Monthly Income </label>
															<div class="col-sm-6">
																<input required="required" type="text"
																	class="form-control" id="g2guarantorIncome"
																	name="g2guarantorIncome" value="2000"
																	onchange="numericValidation('g2guarantorIncome','g2guarantorIncomeResult','Enter Monthly Income') ,this.setCustomValidity('')" />
																<span style="color: red; font-size: 12px;"
																	id="g2guarantorIncomeResult"></span>
															</div>
														</div>

														<script type="text/javascript">
															$(document)
																	.ready(
																			function() {
																				$(
																						"#gBusinessType")
																						.change(
																								function() {
																									$(
																											this)
																											.find(
																													"option:selected")
																											.each(
																													function() {

																														if ($(
																																this)
																																.attr(
																																		"value") == "g2Employ") {

																															$(
																																	".box5")
																																	.not(
																																			".g2Employ")
																																	.hide();
																															$(
																																	".g2Employ")
																																	.show();
																														} else if ($(
																																this)
																																.attr(
																																		"value") == "g2Business") {
																															$(
																																	".box5")
																																	.not(
																																			".g2Business")
																																	.hide();
																															$(
																																	".g2Business")
																																	.show();
																														} else if ($(
																																this)
																																.attr(
																																		"value") == "g2Both") {
																															// 												                $(".box3").not(".g1Both").hide();
																															$(
																																	".g2Both")
																																	.show();
																														}
																														// 												            else{
																														// 												                $(".box3").hide();
																														// 												            }
																													});
																								})
																						.change();
																			});

															// 												$(document).ready(function(){
															// 												    $("select").change(function(){
															// 												        $(this).find("option:selected").each(function(){
															// 												            if($(this).attr("value")=="g2Employ"){
															// 												            $(".box5").not(".g2Employ").hide();
															// 												                $(".g2Employ").show();
															// 												            }
															// 												            else if($(this).attr("value")=="g2Business"){
															// 												              $(".box5").not(".g2Business").hide();
															// 												                $(".g2Business").show();
															// 												            }
															// 												            else if($(this).attr("value")=="g2Both"){
															// 											                //$(".box5").not(".g2Both").hide();
															// 												                $(".g2Both").show();
															// 												            }
															// //  												            else{
															// // 											                $(".box5").hide();
															// // 												            }
															// 												        });
															// 												    }).change();
															// 												});
														</script>

														<div class="form-group">
															<label for="gBusinessType" class="control-label col-sm-4">
																Job/Business Type </label>
															<div class="col-sm-6">
																<select class="form-control" id="gBusinessType"
																	name="gBusinessType"
																	oninvalid="this.setCustomValidity('Please enter the period')"
																	onchange="this.setCustomValidity('') ">

																	<option disabled selected>---Select Source Of
																		Income---</option>
																	<option value="g2Employ">Employ</option>
																	<option value="g2Business">Business</option>
																	<option value="g2Both">Employ and Business</option>
																</select> <span style="color: red; font-size: 12px;"
																	id="g2periodResult"></span>
															</div>
														</div>


														<div class="g2Employ g2Both box5" id=""
															style="display: none;">

															<div class="form-group">
																<label for="g2CompanyName"
																	class="control-label col-sm-4"> Company Name </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g2CompanyName"
																		name="g2CompanyName" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the period')"
																		onchange="nameValidation('g2CompanyName','g2CompanyNameResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('g2CompanyName')" />
																	<span style="color: red; font-size: 12px;"
																		id="g2CompanyNameResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="g2Designation"
																	class="control-label col-sm-4"> Designation </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g2Designation"
																		name="g2Designation" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the period')"
																		onchange="nameValidation('g2Designation','g2DesignationResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('g2Designation')" />
																	<span style="color: red; font-size: 12px;"
																		id="g2DesignationResult"></span>
																</div>
															</div>

															<div class="form-group">
																<span id="messagephone" name="messagephone"
																	style="display: none; color: red; float: right; width: 73%;">
																	This number already exist in our System</span>
															</div>

															<div class="form-group">

																<div id="messagephoneNumber" name="messagephoneNumber"
																	style="display: none; color: red">Please enter
																	valid number</div>


																<label for="g2PhoneNumber"
																	class="control-label col-sm-4">Company Number </label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" value="92"
																		style="width: 65%" readonly />
																</div>
																<div class="col-sm-5">
																	<input required="required" type="text"
																		class="form-control" id="g2PhoneNumber"
																		name="g2PhoneNumber" maxlength="10"
																		onchange="mobileNumberValidation('g2PhoneNumber','g2PhoneNumberResult') , sendInfo();" />
																	<span style="color: red; font-size: 12px;"
																		id="g2PhoneNumberResult"></span>
																</div>
															</div>


															<div class="form-group">
																<label for="g2CompanyAddress"
																	class="control-label col-sm-4"> Company Address</label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g2CompanyAddress"
																		name="g2CompanyAddress" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the Guardian Address')"
																		onchange="addressValidation('g2CompanyAddress','g2CompanyAddressResult') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="g2CompanyAddressResult"></span>
																</div>
															</div>



														</div>


														<div class=" g2Business g2Both box5" id=""
															style="display: none;">

															<div class="form-group">
																<label for="g2BusinessName"
																	class="control-label col-sm-4"> Business Name </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g2BusinessName"
																		name="g2BusinessName" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the period')"
																		onchange="nameValidation('g2BusinessName','g2BusinessNameResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('g2BusinessName')" />
																	<span style="color: red; font-size: 12px;"
																		id="g2BusinessNameResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="g2BusinessType"
																	class="control-label col-sm-4">Business Type </label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g2BusinessType"
																		name="g2BusinessType" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the period')"
																		onchange="nameValidation('property1','g2BusinessTypeResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('g2BusinessType')" />
																	<span style="color: red; font-size: 12px;"
																		id="g2BusinessTypeResult"></span>
																</div>
															</div>

															<div class="form-group">
																<span id="messagephone" name="messagephone"
																	style="display: none; color: red; float: right; width: 73%;">
																	This number already exist in our System</span>
															</div>

															<div class="form-group">

																<div id="messagephoneNumber" name="messagephoneNumber"
																	style="display: none; color: red">Please enter
																	valid number</div>


																<label for=g2BusinessNumber
																	class="control-label col-sm-4">Business Number</label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" value="92"
																		style="width: 65%" readonly />
																</div>
																<div class="col-sm-5">
																	<input required="required" type="text"
																		class="form-control" id="g2BusinessNumber"
																		name="g2BusinessNumber" value="923123636369"
																		maxlength="10"
																		onchange="mobileNumberValidation('g2BusinessNumber','g2BusinessNumberResult') , sendInfo();" />
																	<span style="color: red; font-size: 12px;"
																		id="g2BusinessNumberResult"></span>
																</div>
															</div>


															<div class="form-group">
																<label for="g2BusinessAddress"
																	class="control-label col-sm-4">Business Address</label>
																<div class="col-sm-6">
																	<input required="required" type="text"
																		class="form-control" id="g2BusinessAddress"
																		name="g2BusinessAddress" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the Guardian Address')"
																		onchange="addressValidation('g2BusinessAddress','g2BusinessAddressResult', 'Please enter the Guardian Address') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="g2BusinessAddressResult"></span>
																</div>
															</div>



														</div>
													</div>


													<!-- ===Salesman Gaurontor===== -->
													<div class="row well" style="margin-top: 4%">
														<legend>Salesman Gaurantor</legend>
														<div class="form-group">
															<label for="inputEmail3" class="control-label col-sm-4">Salesman</label>
															<div class="col-sm-6">
																<select class="form-control" id="gsalesman33"
																	name="gsalesman3"
																	oninvalid="this.setCustomValidity('Please select the Salesman name')"
																	onchange="this.setCustomValidity(''), append_salesman()"
																	required="required">


																	<option value="0" selected disabled>--Please
																		Select Salesman--</option>
																	<%
																		// 															abbas
																		for (int m = 0; m < salesman_list.size(); m++) {
																			String phone_number = salesman_list.get(m).getPhone_number();
																			String sales_name = salesman_list.get(m).getName();
																			// 															    System.err.print(bean.getUserId()+"##########");
																	%>
																	<option
																		value="<%=salesman_list.get(m).getSalesmanId()%>">
																		<%=salesman_list.get(m).getName()%></option>
																	<%
																		}
																	%>
																</select>
															</div>
														</div>

													</div>
												</div>
											</div>
										</fieldset>

									</div>
									<!-- =============================Gaurontor Information End  ==================== -->
									<!-- =============================Source of Income Starts  ==================== -->

									<div class="wizard-step-5">
										<fieldset>
											<legend>Income Details</legend>
											<div class="row">
												<div class="form-group">
													<label for="income" class="control-label col-sm-4">Monthly
														Income*:</label>
													<div class="col-sm-6">
														<select class="form-control" id="income" required
															data-parsley-group="wizard-step-5" name="income">
															<option selected disabled>---Select monthly
																income---</option>
															<%
																for (int m = 0; m < salary.size(); m++) {
																	int salary_id = salary.get(m).getSalary_id();
															%>
															<option value="<%=salary_id%>">Rs:
																<%=salary.get(m).getSalary_range()%></option>
															<%
																}
															%>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="fincome" class="control-label col-sm-4">Monthly
														Family Income*:</label>
													<div class="col-sm-6">
														<select class="form-control" id="fincome" required
															name="fincome">
															<option selected disabled>---Select family
																incom---</option>
															<%
																for (int m = 0; m < salary.size(); m++) {
																	int salary_id = salary.get(m).getSalary_id();
															%>
															<option value="<%=salary_id%>">Rs:
																<%=salary.get(m).getSalary_range()%></option>
															<%
																}
															%>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label for="source" class="control-label col-sm-4">Source
														Of Income</label>
													<div class="col-sm-6">
														<select class="form-control" id="source" name="source"
															required="required"
															oninvalid="this.setCustomValidity('Please select the Source Of Income')"
															onchange="this.setCustomValidity('')">

															<option selected disabled>---Select source of
																income---</option>
															<option value="Employ">Employ</option>
															<option value="Business">Business</option>
															<option value="Both">Employ and Business</option>

														</select> <span style="color: red; font-size: 12px;"
															id="sourceResult"></span>
													</div>
												</div>
												<!-- ===========Business Detail of Customer starts================== -->
												<div class="form-group Business Both box1" id="Business"
													style="display: none;">
													<!-- 											<div class="form-group"> -->
													<!-- 												<label for="inputEmail3" class="control-label col-sm-3"><b> Business Details</b> </label> -->
													<!-- 											</div> -->
													<div class="container">
														<div class="row well" style="margin-top: 8%">
															<legend>Business Details</legend>
															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Business
																	Name </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control"
																		id="businessName" name="businessName"
																		value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the businessName')"
																		onchange="nameValidation('businessName','businessNameResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('businessName')" />
																	<span style="color: red; font-size: 12px;"
																		id="businessNameResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Business
																	Type </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control"
																		id="businessType" name="businessType"
																		value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the businessType')"
																		onchange="nameValidation('businessType','businessTypeResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('businessType')" />
																	<span style="color: red; font-size: 12px;"
																		id="businessTypeResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Are
																	you owner of Business ? </label>
																<div class="col-sm-6">
																	<select class="form-control" id="owner" name="owner">
																		<!-- 															oninvalid="this.setCustomValidity('Please enter the valid appliance name')" -->
																		<!-- 															onchange="this.setCustomValidity('')" -->

																		<option value="Owner">Owner</option>
																		<option value="PartnerShip">Partnership</option>

																	</select>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Business
																	Time Period </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control"
																		id="businessTime" name="businessTime" value="10"
																		oninvalid="this.setCustomValidity('Please enter the businessTime')"
																		onchange="numberValidation('businessTime','businessTimeResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('businessTime')" />
																	<span style="color: red; font-size: 12px;"
																		id="businessTimeResult"></span>
																</div>
															</div>



															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Employees
																</label>
																<div class="col-sm-6">

																	<input type="text" class="form-control" id="worker"
																		name="worker" value="10"
																		onchange="numberValidation('worker','workerResult','Invalid name'), this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="workerResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Business
																	Place </label>
																<div class="col-sm-6">
																	<select class="form-control" id="businessPlace"
																		name="businessPlace">
																		<!-- 															oninvalid="this.setCustomValidity('Please enter the valid appliance name')" -->
																		<!-- 															onchange="this.setCustomValidity('')" -->

																		<option value="Owned">Owned</option>
																		<option value="Rented">Rented</option>


																	</select>
																</div>
															</div>

															<div class="form-group">
																<span id="messagephone" name="messagephone"
																	style="display: none; color: red; float: right; width: 73%;">
																	This number already exist in our System</span>
															</div>

															<div class="form-group">

																<div id="messagephoneNumber" name="messagephoneNumber"
																	style="display: none; color: red">Please enter
																	valid number</div>


																<label for="businessPhone"
																	class="control-label col-sm-4"> Business
																	Telephone*: </label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" value="92"
																		style="width: 65%" readonly />
																</div>
																<div class="col-sm-5" style="margin-left:">
																	<input type="text" class="form-control"
																		id="businessPhone" name="businessPhone" maxlength="10"
																		onchange="mobileNumberValidation('businessPhone','businessPhoneResult') , sendInfo();" />
																	<span style="color: red; font-size: 12px;"
																		id="businessPhoneResult"></span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- ===========Business Detail of Customer end================== -->

												<!-- ===========Employement Detail of Customer starts================== -->
												<div class="form-group Employ Both box1" id="Employ"
													style="display: none;">
													<!-- 											<div class="form-group"> -->
													<!-- 												<label for="inputEmail3" class="control-label col-sm-3"> <b>Employment Details</b> </label> -->
													<!-- 											</div> -->
													<div class="container">
														<div class="row well" style="margin-top: 8%">
															<legend>Employment Details</legend>
															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Company
																	Name </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="orgName"
																		name="orgName" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the orgName')"
																		onchange="nameValidation('orgName','orgNameResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('orgName')" /> <span
																		style="color: red; font-size: 12px;"
																		id="orgNameResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">Job
																	Position </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="position"
																		name="position" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the position')"
																		onchange="nameValidation('position','epositionResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('position')" /> <span
																		style="color: red; font-size: 12px;"
																		id="epositionResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="period" class="control-label col-sm-4">Job
																	Period </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="period"
																		name="period" value="10"
																		oninvalid="this.setCustomValidity('Please enter the period')"
																		onchange="numberValidation('period','jperiodResult','Invalid name') , this.setCustomValidity('')"
																		onkeypress="nameCharahterValidation('period')" /> <span
																		style="color: red; font-size: 12px;"
																		id="jperiodResult"></span>
																</div>
															</div>

															<div class="form-group">
																<span id="messagephone" name="messagephone"
																	style="display: none; color: red; float: right; width: 73%;">
																	This number already exist in our System</span>
															</div>

															<div class="form-group">

																<div id="messagephoneNumber" name="messagephoneNumber"
																	style="display: none; color: red">Please enter
																	valid number</div>


																<label for="orgPhone" class="control-label col-sm-4">Company
																	Phone*: </label>
																<div class="col-sm-1">
																	<input type="text" class="form-control" value="92"
																		style="width: 65%" readonly />
																</div>
																<div class="col-sm-5" style="margin-left:">
																	<input type="text" class="form-control" id="orgPhone"
																		name="orgPhone" maxlength="10"
																		onchange="mobileNumberValidation('orgPhone','eorgPhoneResult') , sendInfo();" />
																	<span style="color: red; font-size: 12px;"
																		id="eorgPhoneResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="salary" class="control-label col-sm-4">
																	Salary </label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="salary"
																		name="salary" value="1000"
																		onchange="numberValidation('salary','esalaryResult') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="esalaryResult"></span>
																</div>
															</div>

															<div class="form-group">
																<label for="inputEmail3" class="control-label col-sm-4">
																	Org Address</label>
																<div class="col-sm-6">
																	<input type="text" class="form-control" id="orgAddress"
																		name="orgAddress" value="Noklhkjhkjh"
																		oninvalid="this.setCustomValidity('Please enter the orgAddress')"
																		onchange="addressValidation('orgAddress','eorgAddressResult') , this.setCustomValidity('')" />
																	<span style="color: red; font-size: 12px;"
																		id="eorgAddressResult"></span>
																</div>
															</div>


														</div>
													</div>
												</div>
												<!-- ===========Employement Detail of Customer end================== -->
											</div>
										</fieldset>
									</div>
									<!-- =============================Source of Income end  ==================== -->
									<!-- =============================Appliance Details Starts  ==================== -->
									<div class="wizard-step-6">
										<fieldset>
											<legend class="pull-left width-full">Appliance
												Details</legend>

											<!-- begin panel -->




											<div class="row">

												<div class="form-group">
													<div class="col-sm-offset-1 col-sm-4">

														<div class="table table-bordered table responsive"
															id="appliance_loop" style="margin-left: 90%"></div>


													</div>
												</div>


												<div class="form-group" style="margin-top: 2%">
													<label for="inputEmail3" class="control-label col-sm-4">Appliance
														Name</label>
													<div class="col-sm-6">
														<select class="form-control" id="appname" name="appname"
															onchange="append_weekly()">
															<option selected disabled>---Please Select
																Appliance---</option>
															<%
																for (int m = 0; m < inventory.size(); m++) {
																	String name = inventory.get(m).getName();
															%>
															<option value="<%=name%>">
																<%=inventory.get(m).getName()%></option>
															<%
																}
															%>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">Salesman</label>
													<div class="col-sm-6">
														<input type="text" class="form-control"
															id="salesmandetail" name="salesmandetail" readonly /> <span
															style="color: red; font-size: 12px;"
															id="salesmandetailResult"></span>

													</div>
												</div>

												<div class="form-group" id="schemeCash">
													<label for="inputEmail3" class="control-label col-sm-4">Scheme</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" name="scheme"
															id="scheme" value="36" class="field left" value="36"
															readonly /> <span style="color: red; font-size: 12px;"
															id="customerSchemeResult"></span>
													</div>
												</div>



												<div class="form-group" id="downPaymentCash">
													<label for="inputEmail3" class="control-label col-sm-4">
														Down Payment</label>
													<div class="col-sm-6">
														<input type="text" id="downPayment" name="downPayment"
															class="form-control" readonly /> <span
															style="color: red; font-size: 12px;"
															id="customerDownPaymentResult"></span>
													</div>
												</div>



												<div class="form-group" id="weeklyPaymentCash">
													<label for="inputEmail3" class="control-label col-sm-4">Monthly
														Installment</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" name="installment"
															id="customerWeeklyPayment" readonly /> <span
															style="color: red; font-size: 12px;"
															id="customerWeeklyPaymentResult"></span>
													</div>
												</div>


												<!--												<div class="form-group" id="execustomer_id">
 																<label for="exe_customerid" class="control-label col-sm-3">Cell Number</label> 
																<div class="col-sm-6">-->
												<input type="hidden" class="form-control" name="exe_gsm"
													id="exe_gsm" readonly />
												<!-- </div>
															</div> -->


												<!--															<div class="form-group" id="execustomer_id">
 																<label for="exe_customerid" class="control-label col-sm-3">Exe ID</label>
																<div class="col-sm-6"> -->
												<input type="hidden" class="form-control"
													name="exe_customerid" id="exe_customerid" readonly />
												<!-- </div>
															</div> -->

												<!--															<div class="form-group" id="exesalesman_id">
 																<label for="exe_salesmanid" class="control-label col-sm-3">Salesman ID</label> 
																<div class="col-sm-6">-->
												<input type="hidden" class="form-control"
													name="exe_salesmanid" id="exe_salesmanid" readonly />
												<!-- </div>
															</div> -->


												<div class="form-group">
													<label for="inputEmail3" class="control-label col-sm-4">Upload
														image</label>
													<div class="col-sm-6">
														<input type="file" name="photo" class="form-control"
															id="customerImage" placeholder="upload image" />
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-4">Attachment</label>
													<div class="col-sm-6">
														<input type="file" name="attachment1" /> <input
															type="file" name="attachment2" /> <input type="file"
															name="attachment3" /> <input type="file"
															name="attachment4" />
													</div>
												</div>
												<div class="form-group" id="addcustomer_btn">

													<div class="col-sm-offset-4 col-sm-6">
														<input name="click" type="submit"
															class="btn btn-info  btn block col-sm-12" value="update">
														<!-- disabled="disabled" -->
													</div>
												</div>


											</div>

										</fieldset>

									</div>
									<!-- =============================Appliance Details End  ==================== -->
									<!-- end wizard step-4 -->

								</div>
							</form>
						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->

		<!-- begin theme-panel -->

		<!-- end theme-panel -->

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

	<script src="assets/plugins/parsley/dist/parsley.js"></script>
	<script src="assets/plugins/bootstrap-wizard/js/bwizard.js"></script>

	<script src="assets/js/form-wizards-validation.demo.min.js"></script>

	<script src="assets/js/apps.min.js"></script>
	<script src="js/validation.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		$(document).ready(function() {
			App.init();
			FormWizardValidation.init();
			$('input[name=filename]').change(function() {
				alert($(this).val());
			});
		});
	</script>


</body>
</html>