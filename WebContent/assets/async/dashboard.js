$(function() {
	getUnseenRequest();
	getRecovery();
	getParTen();
	getTotalInstallations();
	getPortfolio();
	getAnalyticsData();
	getPortfolioHealth();
	getDoWiseTopFiveNDs();
	getDoWiseTopFiveFos();
	getDoPerformance();
	getRecoveryDetails();
//	getCreditScore();
})

function updateDashboardWidget() {
	getUnseenRequest();
	getRecovery();
	getParTen();
	getTotalInstallations();
	getPortfolio();
	getAnalyticsData();
	getPortfolioHealth();
	getDoWiseTopFiveNDs();
	getDoWiseTopFiveFos();
	getDoPerformance();
	getRecoveryDetails();
//	getCreditScore();
}

function getRecovery() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getRecovery"
		},
		success : function(data) {
			console.log("ActiveInactiveGraph Success")
			console.log(data)
			$('#active_appliances_widget .progress-bar').width(
					data.recoveryRate)
			$('#active_appliances_widget .stats-desc').text(
					Math.round(data.recoveryRate) + "% Recovery rate")

			$('#active_appliances_widget .stats-number').text(
					formatNumber(parseInt(data.amount)) + " PKR")
		}
	})
}

function getParTen() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getParTen"
		},
		success : function(data) {
			$('#loan_payment_widget .stats-number').text(
					data.defaulters + " Defaults")
			$('#loan_payment_widget .progress-bar').width(data.parTen + " %")
			$('#loan_payment_widget .stats-desc').text(
					Math.round(data.parTen) + " % PAR-10")
		}
	})
}

function getTotalInstallations() {
	$.ajax({
		url : 'ApplianceController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "countApplianceAndSoldStatus"
		},
		success : function(data) {
			$('#mobile_money_widget .stats-number').text(
					parseInt(data.installed) + parseInt(data.installedNoSignal)
							+ " Homes")
			$('#mobile_money_widget .progress-bar').width(0)
			$('#mobile_money_widget .stats-desc').text("Total Deployement")
		}
	})
}

function getPortfolio() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getPortfolio"
		},
		success : function(data) {
			$('#sold_appliances_widget .stats-number').text(
					formatNumber(parseInt(data.amount)) + " PKR")
			$('#sold_appliances_widget .progress-bar').width(
					data.portfolio + "%")
			$('#sold_appliances_widget .stats-desc').text(
					Math.round(data.portfolio) + "% Total Portfolio")
		}
	})
}

function getAnalyticsData() {

	$
			.ajax({
				url : 'SuperAdminDashboardController',
				method : 'post',
				dataType : 'json',
				data : {
					action : "getTotalCustomersAndAppliances"
				},
				success : function(data) {

					$('#total-appliances').text(data.getAppliances)
					$('#total-customers').text(data.getCustomers)

					$('#countPendingCustomers')
							.text(data.countPendingCustomers)
					$('#countAcceptedCustomers').text(
							data.countAcceptedCustomers)
					$('#countVarifiedCustomers').text(
							data.countVarifiedCustomers)

					// Ready To Assign
					$('#ReadyToAssign')
							.text(
									data.countAllStatusAndApplianceStatus.readyToAssign)

					// Owned
					$('#AwaitingDownPayment')
							.text(
									data.countAllStatusAndApplianceStatus.awaitingForDownpayment)
					$('#RDP')
							.text(
									data.countAllStatusAndApplianceStatus.rdp)
					// installed
					$('#Installed')
							.text(
									parseInt(data.countAllStatusAndApplianceStatus.installed)
											+ parseInt(data.countAllStatusAndApplianceStatus.installedNoSignal))
					$('#returned').text(
							data.countAllStatusAndApplianceStatus.returned);
					$('#total_late').text(data.countLoanBookFilters.Late);
					$('#Maintain').text(data.countLoanBookFilters.Maintained);
					$('#Defaulter').text(data.countLoanBookFilters.Defaulter);
					$('#Owned').text(data.countLoanBookFilters.Owned);
					console.log(data.countPendingCustomers)

				}
			})

}

function getDoWiseTopFiveNDs() {
	console.log("hhhh")
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoTopFiveNDs"
		},
		success : function(data) {
			console.log(data)
			$('#super_top_five_nds').empty();
			if (data.length > 0) {
				$.each(data, function(e) {
					var tds = '<tr>' + '<td>' + data[e].nd_name + '</td>'
							+ '<td>' + data[e].district + '</td>' + '<td>'
							+ data[e].current_month_sale + '</td>' + '<td>'
							+ data[e].total_sale + '</td>' + '</tr>';
					$("#super_top_five_nds").append(tds);
				})

			}
		}
	})
}

function getDoWiseTopFiveFos() {
	console.log("hhhhh")
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoTopFiveFos"
		},
		success : function(data) {
			console.log(data)
			$('#super_top_five_fos').empty();
			if (data.length > 0) {
				$.each(data, function(e) {
					var tds = '<tr>' + '<td>' + data[e].fo_name + '</td>'
							+ '<td>' + data[e].district + '</td>' + '<td>'
							+ data[e].current_month_sale + '</td>' + '<td>'
							+ data[e].total_sale + '</td>' + '</tr>';
					$("#super_top_five_fos").append(tds);
				})

			}
		}
	})
}

function getDoPerformance() {
	console.log("hhhhh")
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoPerformance"
		},
		success : function(data) {
			console.log(data)
			$('#current_perf').empty();
			if (data.length > 0) {
				$.each(data, function(e) {
					var tds = '<tr>' 
							+ '<td>' + data[e].district + '</td>' 
							+ '<td>' + data[e].last_sale + '</td>' 
							+ '<td>' + data[e].current_month_sale + '</td>'
							+ '<td>' + data[e].average + '</td>'
							+ '<td>' + data[e].total_sale + '</td>' 
							+ '<td>' + data[e].district_wise_rating + '</td>'
							+ '<td>' + data[e].recovery + '</td>' 
							+ '</tr>';
					$("#current_perf").append(tds);
				})

			}
		}
	})
}

function getPortfolioHealth() {
	console.log("getPortfolioHealth")
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getPortfolioHealth"
		},
		success : function(data) {
			console.log(data)
			$('#BeforeTen').empty();
			$('#BeforeSixToTen').empty();
			$('#BeforeOneToFive').empty();
			$('#zero').empty();
			$('#OneToFive').empty();
			$('#SixtoTen').empty();
			$('#AfterTen').empty();
			$('#BeforeTen').text(data.BeforeTen + " %");
			$('#BeforeSixToTen').text(data.BeforeFive + " %")
			$('#BeforeOneToFive').text(data.BeforeDueDate + " %")
			$('#zero').text(data.dueDate + " %")
			$('#OneToFive').text(data.MinusOneToFive + " %")
			$('#SixtoTen').text(data.MinusSixToTen + " %")
			$('#AfterTen').text(data.AfterMinusTen + " %")
		}
	})
}

function getCreditScore() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getCreditScore"
		},
		success : function(data) {
			console.log(data)
			$('#credit_score').empty();
			if (data.length > 0) {
				$.each(data, function(e) {
					var tds = '<tr>' + '<td>' + data[e].district_name + '</td>'
							+ '<td>' + data[e].district_rating + " %" + '</td>'
							+ '</tr>';
					$("#credit_score").append(tds);
				})

			}
		}
	})
}

function getRecoveryDetails() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getRecoveryDetails"
		},
		success : function(data) {
			console.log(data)
			$('#get_recovery_details').empty();
			if (data.length > 0) {
				var totalAmount = 0;
				var recoveryAmount = 0;
				var lateRecoveryUnpaid = 0;
				var lateRecoveryPaid = 0;
				$.each(data, function(e) {
					console.log("hhhh "+parseInt(data[e].lateRecoveryUnpaid))
					totalAmount = totalAmount + parseInt(data[e].portfolio);
					recoveryAmount = recoveryAmount + parseInt(data[e].total);
					lateRecoveryUnpaid = lateRecoveryUnpaid + parseInt(data[e].previousRecoveryUnpaid);
					lateRecoveryPaid = lateRecoveryPaid + parseInt(data[e].previousRecoveryPaid);
					var tds = '<tr>' + '<td>' + data[e].district + '</td>'
							+ '<td>' + data[e].sales + '</td>' + '<td>'
							+ formatNumber(parseInt(data[e].portfolio))+" PKR"
							+ '</td>' + '<td>' + data[e].recovered + " %"
							+ '</td>' + '<td>' + data[e].expected + " %"
							+ '</td>' + '<td>' + data[e].late + " %"
							+ '</td>' + '<td>'
							+ formatNumber(parseInt(data[e].previousRecoveryUnpaid))+" PKR" + '</td>' + '<td>'
							+ formatNumber(parseInt(data[e].previousRecoveryPaid))+" PKR" + '</td>'
							+ '</tr>';
					$("#get_recovery_details").append(tds);
				})
				var a = '<span style="font-weight: bold"> <b>'
						+ formatNumber(totalAmount) + " PKR" + '</b></span>'
				var b = '<span style="font-weight: bold"> <b>'
						+ formatNumber(recoveryAmount) + " PKR" + '</b></span>'
				var c = '<span style="font-weight: bold"> <b>'
							+ formatNumber(lateRecoveryUnpaid) + " PKR" + '</b></span>'
				var d = '<span style="font-weight: bold"> <b>'
							+ formatNumber(lateRecoveryPaid) + " PKR" + '</b></span>'
				$('#total').append(a);
				$('#amount').append(b);
				$('#late_recovery').append(c);
				$('#late_recovery_paid').append(d);
			}
		}
	})
}

function formatNumber(number) {
	number = number.toFixed(2) + '';
	x = number.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1;
}
