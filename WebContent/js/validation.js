
var name = "0";
var cnicget = "0";
var mobileget = "0";
var mobileexist = "0";
var addressget = "0";
var salaryget = "0";
var bdageget = "0";
var emailSet = "0";
var emailSetUrl= "0";

var firstnameup = ""
var joiningDateup = "";
var dateOfBirthup = "";
var doCnicup = "";
var dophone1up = "";
var doemailup = "";
var addressup = "";
var salaryup = "";
var emailexist="";




var foNameGet = "";
var foCityGet = "";
var foJoiningDateGet = "";
var foBDget = "";
var foCnicGet = "";
var foPhoneGet = "";
var foAddressGet = "";
var foSalaryGet = "";
/*var foBeforeTime = "";
var foAfterTimeGet = "";*/
var foOnTimeGet = "";



var vlefirstname = "";
var vledateOfBirth = "";
var vledoCnic = "";
var vledophone1 = "";
var vleaddress = "";
/*var vlesalary = "";
var vlebeforeTime = "";
var vleafterTime = "";*/
var vleonTime = "";


/*var fromfilter = "0";
var tofilter = "0";*/

function cnicValidation(cnicfield, cnicMsg) {
	var cnic = document.getElementById(cnicfield).value;
	// var myRegExp = new RegExp(/\d[0-9+]{5}-\d[0-9+]{7}-\d[0-9+]{1}$/);
	var myRegExp = new RegExp(/^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$/);

	if (myRegExp.test(cnic)) {
		cnicget = "1";
		vledoCnic = "1";
		doCnicup = "1";
		
		document.getElementById(cnicMsg).innerHTML = '';
		document.getElementById(cnicfield).style.borderColor = "#ccd0d4";
	} else {
		cnicget = "0";
		vledoCnic = "0";
		
		document.getElementById(cnicMsg).innerHTML = 'Invalid CNIC';
		document.getElementById(cnicfield).style.borderColor = "red";
	}

}

function cnicValidationforFo(cnicfield, cnicMsg) {
	// var idToTest = '12345-1234567-1',
	var cnic = document.getElementById(cnicfield).value;
	// var myRegExp = new RegExp(/\d[0-9+]{5}-\d[0-9+]{7}-\d[0-9+]{1}$/);
	var myRegExp = new RegExp(/^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$/);

	if (myRegExp.test(cnic)) {
		foCnicGet = "1"; 
		
		document.getElementById(cnicMsg).innerHTML = '';
		document.getElementById(cnicfield).style.borderColor = "#ccd0d4";
	} else {
		foCnicGet = "0";
		
		document.getElementById(cnicMsg).innerHTML = 'Invalid CNIC';
		document.getElementById(cnicfield).style.borderColor = "red";
	}

}

function mobileNumberValidation(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	if (mobileRegExp.test(mobileNumber)) {
		mobileget = "1";
		vledophone1 = "1";
		dophone1up = "1";
		
		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";
		$('#submit').prop("disabled", false);
//		$('#submit').prop("disabled", false);
//		$('#click').prop("disabled", false);
		$('#vleClick').prop("disabled", false);
		$('#foClick').prop("disabled", false);
//		alert("false number : "+data[i].phone);
	} else {
		dophone1up = "0";
		mobileget = "0";
		vledophone1 = "0";
		
		$('#submit').prop("disabled", true);
//		$('#submit').prop("disabled", true);
//		$('#click').prop("disabled", true);
		$('#foClick').prop("disabled", true);
		$('#vleClick').prop("disabled", true);
		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
	}

}




function mobileNumberValidation2(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	if (mobileRegExp.test(mobileNumber)) {
	
		
		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";
		$('#submit').prop("disabled", false);
//		$('#submit').prop("disabled", false);
//		$('#click').prop("disabled", false);
		$('#vleClick').prop("disabled", false);
		$('#foClick').prop("disabled", false);
//		alert("false number : "+data[i].phone);
	} else {
		
		
		$('#submit').prop("disabled", true);
//		$('#submit').prop("disabled", true);
//		$('#click').prop("disabled", true);
		$('#foClick').prop("disabled", true);
		$('#vleClick').prop("disabled", true);
		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
//		alert("invalid number : "+data[i].phone);
	}

}

function mobileNumberValidationforFo(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	if (mobileRegExp.test(mobileNumber)) {
		foPhoneGet = "1";
		
		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";
		$('#vleClick').prop("disabled", false);
		$('#foClick').prop("disabled", false);

	} else {
		foPhoneGet = "0";
		$('#foClick').prop("disabled", true);
		$('#vleClick').prop("disabled", true);
		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
	}

}

function numericValidation(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
		salaryget = "1";
		foSalaryGet = "1";
//		vlesalary = "1";
		salaryup = "1";
		
		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		salaryup = "0";
		salaryget = "0";
		foSalaryGet = "0";
//		vlesalary = "0";
		
		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(numericField).style.borderColor = "red";
	}

}



function numericValidationBT(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
//		foBeforeTime = "1";
//		vlebeforeTime = "1";
		
		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
//		foBeforeTime = "0";
//		vlebeforeTime = "0";
		
		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(numericField).style.borderColor = "red";
	}

}


function numericValidationAT(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
//		foAfterTimeGet = "1";
//		vleafterTime = "1";
		document.getElementById(msg).innerHTML = '';
			
			document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
//		foAfterTimeGet = "0"
//		vleafterTime = "0";
		
		document.getElementById(msg).innerHTML = errorMsg
		document.getElementById(numericField).style.borderColor = "red";
	}

}


function numericValidationOT(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
		foOnTimeGet = "1";
		vleonTime = "1";
		
		document.getElementById(msg).innerHTML = ''; 
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		foOnTimeGet = "0";
		vleonTime = "0";
		
		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(numericField).style.borderColor = "red";
	}

}




function nameValidation(nameField, msg, errorMsg) {
	var mob = document.getElementById(nameField).value;
	var patt4 = /^([^0-9]*)$/;
	if (patt4.test(mob)) {
		name = "1";
		foNameGet = "1";
		vlefirstname = "1";
		firstnameup = "1";
		
		document.getElementById(msg).innerHTML = "";
		document.getElementById(nameField).style.borderColor = "#ccd0d4";
	} else {
		firstnameup = "0";
		name = "0";
		foNameGet = "0";
		vlefirstname = "0";
		
		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(nameField).style.borderColor = "red";
	}
}

function addressValidation(addressField, msg) {
	var address = document.getElementById(addressField);
	var message = document.getElementById(msg);
	// var patt4=/^[a-z A-Z]+$/;
	// alert(mob.value);
	if (address.value == "" || address.value.length < 5) {
		addressget = "0";
		foAddressGet = "0";
		vleaddress = "0";
		addressup = "0";
		
		document.getElementById(addressField).style.borderColor = "red";
		message.innerHTML = "Invalid Address";
//		alert('addressget  if'+addressget)
	} else {
		addressup = "1";
		addressget = "1";
		foAddressGet = "1";
		vleaddress = "1";
		
		message.innerHTML = "";
		document.getElementById(addressField).style.borderColor = "#ccd0d4";
//		alert('addressget  else'+addressget)
	}

}

function emailValidation(emailField, msg) {
	var email = document.getElementById(emailField).value;
	var emailMsg = document.getElementById(msg);
	var patt4 = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	if (!patt4.test(email)) {
		document.getElementById(emailField).style.borderColor = "red";
		emailMsg.innerHTML = "Invalid email";
		doemailup = "1";
		emailSet = "0"
//			alert("invalid "+emailSet)
		
	} else {
		emailSet = "1";
//		alert("valid "+emailSet)
		doemailup = "0";
		emailMsg.innerHTML = "";
		document.getElementById(emailField).style.borderColor = "#ccd0d4";
		
	}

}

function licenseValidation(licenseField, msg) {
	var license = document.getElementById(licenseField).value;
	var licensenum = document.getElementById(msg);
	var patt4 = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	if (!patt4.test(license)) {
		document.getElementById(licenseField).style.borderColor = "red";
		licensenum.innerHTML = "Invalid email";
		
	} else {
		licensenum.innerHTML = "";
		document.getElementById(licenseField).style.borderColor = "#ccd0d4";
		
	}

}

function setDateExpression(event, datefield) {
	var keycode = event.which || event.keyCode;
	if (keycode != 8) {
		var mystring = document.getElementById(datefield).value;
		if (mystring.split('').length == 2 || mystring.split('').length == 5) {
			mystring = mystring + "/";
		}
		document.getElementById(datefield).value = mystring;
	}
	return true;

}

function setNicDash(event, cnicfield) {
	var keycode = event.which || event.keyCode;
	if (keycode != 8) {
		var mystring = document.getElementById(cnicfield).value;
		if (mystring.split('').length == 5 || mystring.split('').length == 13) {
			mystring = mystring + "-";
		}
		document.getElementById(cnicfield).value = mystring;
	}
	return true;
}

function setMobileDash(event, cnicfield) {
	var keycode = event.which || event.keyCode;
	if (keycode != 8) {
		var mystring = document.getElementById(cnicfield).value;
		if (mystring.split('').length == 3) {
			mystring = mystring + "-";
		}
		document.getElementById(cnicfield).value = mystring;
	}
	return true;

}

function nameCharahterValidation(nameField) {
	var mystring = document.getElementById(nameField).value;
	var sp = mystring.split(' ');
	var f, r;
	var word = new Array();
	for ( var i = 0; i < sp.length; i++) {
		f = sp[i].substring(0, 1).toUpperCase();
		r = sp[i].substring(1);
		word[i] = f + r;
	}
	newstring = word.join(' ');
	document.getElementById(nameField).value = newstring;
	return true;
}

function emailCharahterValidation(nameField) {
	var mystring = document.getElementById(nameField).value;
	var sp = mystring.split(' ');
	var f, r;
	var word = new Array();
	for (i = 0; i < sp.length; i++) {
		f = sp[i].substring(0, 1).toLowerCase();
		r = sp[i].substring(1);
		word[i] = f + r;
	}
	newstring = word.join(' ');
	document.getElementById(nameField).value = newstring;
	return true;
}

function check_age(ageField, msg) {

	var currentdate = new Date();
	// var date = "Last Sync: " + currentdate.getDate() + "/"
	// + (currentdate.getMonth()+1) + "/"
	// + currentdate.getFullYear() ;

	// alert('current Date :'+currentdate.getFullYear());

	var dobb = document.getElementById("dob").value;
	var age = new Date(dobb);

	// alert('your Age: '+age.getFullYear());

	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// $('#age').val("Your Current Age is "+currentDate+" One year");
	$('#age').val(currentDate);

	ageValidation(ageField, msg);

}

function pointsValidation(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	// var reg = "[-+]?[0-9]*[.]?[0-9]*";
	var numericRegExp = new RegExp('([-+]*\d+\.\d+|[-+]*\d+)');
	if (numericRegExp.test(number)) {
		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		document.getElementById(numericField).style.borderColor = "red";
		document.getElementById(msg).innerHTML = errorMsg;
	}

}

function checkAgeForCnic(val) {

	var currentdate = new Date();
	// var val = document.salesman.dateOfBirth.value;
	// alert(val);
	var age = new Date(val.value);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	if (currentDate >= 18) {
		$("#cnicLabel").show();
		$("#doCnic").show();
	}
}

function checkFoAgeForCnic(val) {

	var currentdate = new Date();
	// var val = document.salesman.dateOfBirth.value;
	// alert(val);
	var age = new Date(val.value);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	if (currentDate >= 18) {
		$("#cnicFoLabel").show();
		$("#sdoCnic").show();
	}
}

// abbas
function ageValidation(ageField, msg) {
	var age = document.getElementById(ageField).value;
	var ageMsg = document.getElementById(msg);

	if (age >= 18) {
		$("#CNIC_ID").show();
		$("#CNIC_ID_SPAN").show();
		ageMsg.innerHTML = "";
		
	} else {
		$("#CNIC_ID").hide();
		$("#CNIC_ID_SPAN").hide();
		ageMsg.innerHTML = "Not applicable for CNIC";
		
	}
}
//abbas
/*function filterChackerOnPaymantTo(to){
	var tofilter = Date(to.value);
	if(tofilter>0){
		tofilter = "1";
	}
	alert(tofilter+" to")
}*/

/*function filterChackerOnPaymantFrom(from){
	var fromfilter = Date(from.value);
	if(fromfilter>0){
		fromfilter = "1";
	}
	alert(fromfilter+" from")
}*/

function ageValidationDO(ageField, msg, nicId, nicLabel) {
	var currentdate = new Date();
	var age = new Date(ageField.value);
	currentDate = currentdate.getFullYear() - age.getFullYear();
	var ageMsg = document.getElementById(msg);
	// alert(currentDate);
	// alert(nicLabel);
	if (currentDate >= 18) {
		$("#" + nicId).show();
		$("#" + nicLabel).show();
		// $("#CNIC_ID_SPAN").show();
		bdageget = "1";
		ageValidationNew = "1";
		foBDget = "1";
		vledateOfBirth = "1";
		dateOfBirthup = "1"
		
		ageMsg.innerHTML = "";
		document.getElementById('dateOfBirth').style.borderColor = "#ccd0d4";
		
	} else {
		$("#" + nicId).hide();
		$("#" + nicLabel).hide();
		// $("#CNIC_ID_SPAN").hide();
		bdageget = "0";
		ageValidationNew = "0";
		foBDget = "0"
		vledateOfBirth = "0";
		dateOfBirthup = "0"
		document.getElementById('dateOfBirth').style.borderColor = "red";
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}





function ageValidationNewFO(ageField, msg, nicId, nicLabel) {
	var currentdate = new Date();
	var age = new Date(ageField.value);
	currentDate = currentdate.getFullYear() - age.getFullYear();
	var ageMsg = document.getElementById(msg);
	// alert(currentDate);
	// alert(nicLabel);
	if (currentDate >= 18) {
		$("#" + nicId).show();
		$("#" + nicLabel).show();
		// $("#CNIC_ID_SPAN").show();
		bdageget = "1";
		ageValidationNew = "1";
		foBDget = "1";
		vledateOfBirth = "1";
		
		ageMsg.innerHTML = "";
		document.getElementById('sdateOfBirth').style.borderColor = "#ccd0d4";
		
	} else {
		$("#" + nicId).hide();
		$("#" + nicLabel).hide();
		// $("#CNIC_ID_SPAN").hide();
		bdageget = "0";
		ageValidationNew = "0";
		foBDget = "0"
		vledateOfBirth = "0";
		
		document.getElementById('sdateOfBirth').style.borderColor = "red";
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}






function ageValidationNewVLE(ageField, msg, nicId, nicLabel) {
	var currentdate = new Date();
	var age = new Date(ageField.value);
	currentDate = currentdate.getFullYear() - age.getFullYear();
	var ageMsg = document.getElementById(msg);
	// alert(currentDate);
	// alert(nicLabel);
	if (currentDate >= 18) {
		$("#" + nicId).show();
		$("#" + nicLabel).show();
		// $("#CNIC_ID_SPAN").show();
		bdageget = "1";
		ageValidationNew = "1";
		foBDget = "1";
		vledateOfBirth = "1";
		
		ageMsg.innerHTML = "";
		document.getElementById('dateOfBirth').style.borderColor = "#ccd0d4";
		
	} else {
		$("#" + nicId).hide();
		$("#" + nicLabel).hide();
		// $("#CNIC_ID_SPAN").hide();
		bdageget = "0";
		ageValidationNew = "0";
		foBDget = "0"
		vledateOfBirth = "0";
		
		document.getElementById('dateOfBirth').style.borderColor = "red";
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}



// G1

function check_g1age(g1ageField, msg) {
	var currentdate = new Date();
	var dob = document.getElementById("g1guarantorDob").value;
	var age = new Date(dob);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(currentDate);

	if (currentDate >= 18) {
		$("#G1_CNIC_ID").show();
		$("#G1_CNIC_ID_SPAN").show();
		$('[name="g1guarantorDob"]').next().text("");
	} else {
		$("#G1_CNIC_ID").hide();
		$("#G1_CNIC_ID_SPAN").hide();
		
		$('[name="g1guarantorDob"]').next().text("'Not applicable for CNIC'");
		// alert("else"+currentDate);
	}

	// ageValidation(currentDate, msg);
}

function g1_check_age(ageField, msg) {

	var currentdate = new Date();
	var dobb = document.vinform1.dob.value;
	var age = new Date(dobb);

	var currentDate = currentdate.getFullYear() - age.getFullYear();
	$('#age').val(currentDate);

	g1_ageValidation(ageField, msg);

}

function g1_ageValidation(ageField, msg) {
	var age = document.getElementById(ageField).value;
	var ageMsg = document.getElementById(msg);

	if (age >= 18) {
		$("#CNIC_ID").show();
		$("#CNIC_ID_SPAN").show();
		ageMsg.innerHTML = "";
	} else {
		$("#CNIC_ID").hide();
		$("#CNIC_ID_SPAN").hide();
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

// G2

function check_g2age(g2ageField, msg) {
	var currentdate = new Date();
	var dob = document.getElementById("g2guarantorDob").value;
	var age = new Date(dob);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(currentDate);

	// var age = document.getElementById(ageField).value;
	// var ageMsg = document.getElementById(msg);

	if (currentDate >= 18) {
		$("#G2_CNIC_ID").show();
		$("#G2_CNIC_ID_SPAN").show();
		$('[name="g2guarantorDob"]').next().text("");
	} else {
		$("#G2_CNIC_ID").hide();
		$("#G2_CNIC_ID_SPAN").hide();
		$('[name="g2guarantorDob"]').next().text("'Not applicable for CNIC'");
		// alert("else"+currentDate);
	}

	// ageValidation(currentDate, msg);
}

function g2_check_age(ageField, msg) {

	var currentdate = new Date();
	var dobb = document.vinform1.dob.value;
	var age = new Date(dobb);

	var currentDate = currentdate.getFullYear() - age.getFullYear();
	$('#age').val(currentDate);

	g2_ageValidation(ageField, msg);

}

function g2_ageValidation(ageField, msg) {
	var age = document.getElementById(ageField).value;
	var ageMsg = document.getElementById(msg);

	if (age >= 18) {
		$("#CNIC_ID").show();
		$("#CNIC_ID_SPAN").show();
		ageMsg.innerHTML = "";
	} else {
		$("#CNIC_ID").hide();
		$("#CNIC_ID_SPAN").hide();
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

function check_child_ageD(child_dobField, divField) {

	var currentdate = new Date();
	// alert("Hello1");
	var dob = document.getElementById(child_dobField).value;
	// alert("Hello2");
	var age = new Date(dob);
	// alert("Hello3");
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(child_dobField);
	// alert(divField);
	if (currentDate >= 18) {
		$("#" + divField).show();
		// document.getElementById(divField).childNodes[1].innerHTML.show();
	}
}

function check_child_age(child_dobField, msg) {
	var currentdate = new Date();
	var dob = document.vinform1.child_dob.value;
	var age = new Date(dob);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(currentDate);

	if (currentDate >= 18) {
		$("#CHILD_CNIC_ID").show();
		$("#G1_CNIC_ID_SPAN").show();
		$('[name="g1guarantorDob"]').next().text("");
	} else {
		$("#CHILD_CNIC_ID").hide();
		$("#G1_CNIC_ID_SPAN").hide();
		$('[name="g1guarantorDob"]').next().text("'Not applicable for CNIC'");
		// alert("else"+currentDate);
	}

	// ageValidation(currentDate, msg);
}


function existingData(id,result,e)
{
		
	var existingData=document.getElementById(id).value;
	var column=$(e).data('column');
	var table=$(e).data('table');
	var type=$(e).data('type');
	console.log(column)
	console.log(table)
	console.log(type)
	$.ajax({
				url : 'ValidationServlet',
				dataType : 'json',
				method : 'POST',
				async : false,
				data : {
					PhoneNumber : existingData,
					column : column,
					table : table,
					type:type
				},
				success : function(data){
					console.log(data)
					
					if(data.imei == true){
						$("#" + result).show();
						$("#" + result).text("imei number already exist");
					}else if(data.imei == false){
						$("#" + result).hide();
					}
					
					if(data.cnic==true)
					{
						document.getElementById(result).innerHTML += 'Cnic already exist';
					}
					if(data.email==true)
					{
						document.getElementById(result).innerHTML += 'email already exist';
						emailSetUrl = "0";
//						alert("email exist if"+emailSetUrl)
					}else if(data.email==false){
						emailSetUrl = "1";
						//alert("email exist else"+emailSetUrl)
					}
					if(data.phone==true)
					{
						document.getElementById(result).innerHTML += 'phone number already exist';
						mobileexist = "0";
						$('#submit').prop("disabled", true);
						$('#click').prop("disabled", true);
						$('#foClick').prop("disabled", true);
						$('#vleClick').prop("disabled", true);
					}else if(data.phone==false){
						
						mobileexist = "1";
						$('#submit').prop("disabled", false);
						$('#click').prop("disabled", false);
						$('#foClick').prop("disabled", false);
						$('#vleClick').prop("disabled", false);
						//alert('mobileexist  '+mobileexist)
					}
				}
	})
}


function existingMobileNumber(phone, result, e) {
	
	var existNumber = document.getElementById(phone).value;
//	alert('0000:  '+existNumber)
	console.log("++++++++++++++++++++++++++++++++");
	console.log(e);
	var column = $(e).data('column');
	var table = $(e).data('table');
	var type= $(e).data('type');
	$.ajax({
				url : 'ValidationServlet',
				dataType : 'json',
				method : 'POST',
				async : false,
				data : {
					PhoneNumber : existNumber,
					column : column,
					table : table
				},
				success : function(data) {
//					alert('44')
					for ( var i = 0; i < data.length; i++) {
//						alert("outer  if "+data[i].phone);
						if (data[i].cnic == true) {
							document.getElementById(result).innerHTML += 'Cnic already exist';
//							document.getElementById('click').disabled = true;
						} else if (data[i].phone == true) {
							document.getElementById(result).innerHTML += 'Phone number already exist';
//							document.getElementById('click').disabled = true;
							/*mobileexist = "0";*/
							$('#submit').prop("disabled", true);
							$('#click').prop("disabled", true);
							$('#foClick').prop("disabled", true);
							$('#vleClick').prop("disabled", true);
//							vledophone1 = "0";
//							alert("num exist  "+mobileexist);
							console.log(data[i].phone);
//							alert(data[i].phone);
						

						}else if (data[i].phone == undefined || data[i].phone == "undefined" || data[i].phone == null || data[i].phone == false) {
							
//								document.getElementById('click').disabled = true;
								/*mobileexist = "1";*/
//								vledophone1 = "0";
//								alert("num not exist  "+mobileexist);
								$('#submit').prop("disabled", false);
								$('#click').prop("disabled", false);
								$('#foClick').prop("disabled", false);
								$('#vleClick').prop("disabled", false);
//								alert("exist number : "+data[i].phone);
								console.log(data[i].phone);

							} else if (data[i].email == true) {
							document.getElementById(result).innerHTML += 'Email id already exist'
//							document.getElementById('click').disabled = true;

						} 
						else if (data[i].imei == true) {
						console.log(data[i].imei)
						document.getElementById(result).innerHTML += 'this imei already exist'
//						document.getElementById('foClick').disabled = true;

					}
//						else {
//							document.getElementById('click').disabled = false;
//
//						}
					}
				}
			});
	if ($('#imeiresult').length === $('#imeiresult:contains("Empty")').length) {
		
	}
	else {
		$('#saveApp').attr("disabled","disabled");
	}
	console.log('*/**/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/**/*/*/*/**/*/'+result)
	// alert(existCustomer, $('[data-column]').data('column'));
}

//function existingMobileNumberforFo(phone, result, e) {
//	var existNumber = document.getElementById(phone).value;
//
//	var column = $(e).data('column');
//	var table = $(e).data('table');
//	$
//			.ajax({
//
//				url : 'ValidationServlet',
//				dataType : 'json',
//				method : 'POST',
//				async : false,
//				data : {
//					PhoneNumber : existNumber,
//					column : column,
//					table : table
//				},
//				success : function(data) {
//					for ( var i = 0; i < data.length; i++) {
//						console.log(data);
//						if (data[i].cnic == true) {
//							document.getElementById(result).innerHTML += 'Cnic already exist'
////							document.getElementById('foClick').disabled = true;
//
//						} else if (data[i].phone == true) {
//							console.log(data[i].phone)
//							document.getElementById(result).innerHTML += 'Phone number already exist'
////							document.getElementById('foClick').disabled = true;
//
//						} else if (data[i].email == true) {
//							console.log(data[i].email)
//							document.getElementById(result).innerHTML += 'imei already exist'
////							document.getElementById('foClick').disabled = true;
//
//						} 
//						else if (data[i].imei == true) {
//							console.log(data[i].imei)
//							document.getElementById(result).innerHTML += 'this imei already exist'
////							document.getElementById('foClick').disabled = true;
//
//						} 
////						else {
////							document.getElementById('foClick').disabled = false;
////
////						}
//					}
//				}
//			});
//}

// created by Junaid Ali
function doFormSubmit(){
//alert('called')
	
	if(name == "1" && bdageget == "1" && emailSet == "1" && emailSetUrl == "1" && cnicget == "1" && mobileexist == "1" && addressget == "1" && salaryget == "1" && $('#district').val().length > 0 && $('#joiningDate').val().length > 0 ){
		$('#submit').prop("disabled", false);
//		alert("enabled: name: "+name+" :bdageget "+bdageget+" :emailSet "+emailSet+" :emailSetUrl "+emailSetUrl+" :cnicget "+cnicget+" :mobileget "+mobileexist+" :addressget "+addressget+" :salaryget "+salaryget+" :district "+$('#district').val()+" :joiningDate "+$('#joiningDate').val());
	}
	
	else{
		$('#submit').prop("disabled", true);
//		alert("disabled: name: "+name+" :bdageget "+bdageget+" :emailSet "+emailSet+" :emailSetUrl "+emailSetUrl+" :cnicget "+cnicget+" :mobileget "+mobileexist+" :addressget "+addressget+" :salaryget "+salaryget+" :district "+$('#district').val()+" :joiningDate "+$('#joiningDate').val());
	}
	
}
function doFormUpdate(firstnameup,dateOfBirthup,doCnicup,dophone1up,doemailup,salaryup,addressup){
	
	if(firstnameup == "1" && dateOfBirthup == "1" && doCnicup == "1" && dophone1up == "1" && doemailup == "1" && salaryup == "1" && addressup == "1" && mobileexist == "1" && $('#joiningDate').val().length > 0){
		$('#submit').prop("disabled", false);
//		alert("if:  name: "+firstnameup+" cnicget: "+dateOfBirthup+" mobileget: "+doCnicup+" mobileexist: "+dophone1up+" addressget: "+doemailup+" salaryget: "+salaryup+" bdageget: "+" : "+addressup+": "+mobileexist);
		
	}else{
//		alert("else:  name: "+firstnameup+" cnicget: "+dateOfBirthup+" mobileget: "+doCnicup+" mobileexist: "+dophone1up+" addressget: "+doemailup+" salaryget: "+salaryup+" bdageget: "+" : "+addressup+": "+mobileexist);
		$('#submit').prop("disabled", true);
	}
}

function FOformSubmit(){
	
	if(foNameGet == "1"	&& foBDget == "1" && foCnicGet == "1" && foPhoneGet == "1" && foAddressGet == "1" && foSalaryGet == "1" && /*foBeforeTime == "1" &&*/ foOnTimeGet == "1" /*&& foAfterTimeGet == "1"*/){
	
		
		$('#foClick').prop("disabled", false)
		
	}else{
		$('#foClick').prop("disabled", true)
		
	}
	
}

function VLEformSubmit(){
	
	if(vlefirstname == "1" &&
			  vledateOfBirth == "1" &&
			  vledoCnic == "1" &&
			  vledophone1 == "1" &&
			  vleaddress == "1" &&
//			  vlesalary == "1" &&
//			  vlebeforeTime == "1" &&
			  vleonTime == "1"
//			   && vleafterTime == "1"
				  ){
		
		$('#vleClick').prop("disabled", false)
	}else{
		$('#vleClick').prop("disabled", true)
	}
	
}

function PaymentFilterInit(){

	if(fromfilter == "1" && tofilter == "1"){
		$('#filterid').prop("disabled", false);
	}else{
		$('#filterid').prop("disabled", true);
	}
}

