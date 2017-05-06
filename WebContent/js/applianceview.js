var appId = document.getElementById("appID").value;
var customerId = document.getElementById("customer_id").value;
var curr = document.getElementById("current").value;
var solar = document.getElementById("SolarCurrent").value;
var GSM = document.getElementById("gsmNumber");

// datewise statics of appliance power usage graph
function staticsDateWise() {

	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var dbGraphs = [];
	var dbGraphSolar = [];
	var flg=0;
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates,
			flag : flg
		},
		success : function(data) {
			console.log(data)
			console.log(data[0].dateWise)
			dbGraph = data[0].dateWise
			console.log("+++++++++++++ solar data ++++++++++++++")
			console.log(data[0])
			dbGraphSolar = (data[0].solarLoad)
			console.log(dbGraphSolar)
			$('#powerproduced').text(data[0].power)
			$('#loadConsumed').text(data[0].loadConsumed)
			$('#hours').text(data[0].hours)
			$('#mins').text(data[0].mins)
			if(data[0].today==1){
			    $('#powertag').text("Previous Day Power Produced")
			    $('#loadtag').text("Previous Day Laod Consumed")
			    $('#chargingtag').text("Previous Day Charging Time:")
			   }else if(data[0].today==0){
			    $('#powertag').text("Power Produced")
			    $('#loadtag').text("Laod Consumed")
			     $('#chargingtag').text("Charging Time:")
			   }
			AmCharts.makeChart("chartdiv2", {
				"type" : "serial",
				"categoryField" : "datetime",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Load",
					"valueField" : "solarWatt",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Battery",
					"valueField" : "batteryWatt",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				}, {
					"id" : "AmGraph-3",
					"title" : "Solar",
					"valueField" : "lodWatt",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#f09609",
					"bullet" : "circle",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Power (watts)",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : dbGraph

			});// /// amChart finished

		}
	});// ///finished ajax

}

// mintwise statics of appliance power usage graph

function liveStatics() {
	// alert('hello')
	var now = $.datepicker.formatDate('yy-mm-dd', new Date())
	// alert(no);

	$('#dates').attr("max", now);
	var dbGraphs = [];
	// alert(appId);
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dates : now
		},
		success : function(data) {
			console.log(data)
			console.log(data[0].dateWise)
			dbGraph = data[0].dateWise
			AmCharts.makeChart("chartdiv2", {
				"type" : "serial",
				"categoryField" : "datetime",
				"dataDateFormat" : "YYYY-MM-DD HH:MM",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "square",
					"id" : "AmGraph-1",
					"title" : "Load watt",
					"valueField" : "solarWatt",
					"lineThickness" : 2
				}, {
					"bullet" : "square",
					"id" : "AmGraph-2",
					"title" : "Battery Watt",
					"valueField" : "batteryWatt",
					"lineThickness" : 2
				}, {
					"id" : "AmGraph-3",
					"title" : "Solar Watt",
					"valueField" : "lodWatt",
					"bullet" : "square",
					"lineThickness" : 2
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Power (watts)",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				"titles" : [ {
					"id" : "Title-1",
					"size" : 20,
					"text" : "Power Usage Graph"
				} ],
				// "dataProvider": AmCharts.loadJSON('data.jsp'),

				"dataProvider" : dbGraph

			});// /// amChart finished
		}
	});// ///finished ajax
}// liveStatics function closed
// });////document ready finished

function loadSoalrAmpere() {

	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var dbGraphLoad = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			dbGraphLoad = data[0].solarLoad
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].solarLoad)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv3", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Solar",
					"valueField" : "solar",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Load",
					"valueField" : "load",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Current (ampere)",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : dbGraphLoad

			});// /// amChart finished

		}
	});// ///finished ajax

}

// //////////////////////////////////////////////////////////////////////
// Alarm history table
$(function() {

	$('#alarmsTable')
			.DataTable(
					{

						"processing" : true,
						"serverSide" : true,
						"order" : [ [ 0, "asc" ] ],
						"ajax" : {
							"url" : "ApplianceAlarmsControl",
							"type" : "post",
							"data" : function(d) {
								d.action = "getAlarmsTable", d.appId = appId
							},
							"dataSrc" : function(json) {
								console
										.log("##################################");
								console.log(json);
								for ( var i = 0; i < json.data.length; i++) {
									json.data[i].alert = ""
									json.data[i].status = ""
									json.data[i].resTime = ""
									if (json.data[i].solar_power == "true") {
										json.data[i].alert += " Solar Power"
									} else if (json.data[i].battery == "true") {
										json.data[i].alert += " Battery "
									} else if (json.data[i].temprature == "true") {
										json.data[i].alert += " Temprature "
									} else if (json.data[i].current_load == "true") {
										json.data[i].alert += " Current Load "
									} else if (json.data[i].lid_connectivity == "true") {
										json.data[i].alert += "Lid Connectivity"
									} else if (json.data[i].gsmSignal == "1"
											|| json.data[i].gsmSignal == "true") {
										json.data[i].alert += "Signal Strength"
									} else if (json.data[i].gsm_suicide == "true") {
										json.data[i].alert += "GSM Suicide"
									} else if (json.data[i].common_fault == "true") {
										json.data[i].alert += "Common Fault"
									} else if (json.data[i].battery_overcharge == "true") {
										json.data[i].alert += "Battery Overcharge"
									}
									if (json.data[i].ticketStatus == "0") {
										json.data[i].status = "Pending"
									} else if (json.data[i].ticketStatus == "1") {
										json.data[i].status = "Resolved"
									}
									if (json.data[i].resolvedTime == "null"
											|| json.data[i].resolvedTime == null
											|| json.data[i].resolvedTime == 0) {
										json.data[i].resTime = "N/A"
									} else {
										json.data[i].resTime = json.data[i].resolvedTime
									}
								}
								return json.data;
							}

						},

						"columns" : [ {
							"data" : "alarmTime"
						}, {
							"data" : "alert"
						}, {
							"data" : "status"
						},
						// {
						// "data" : "ticketNo"
						// },
						{
							"data" : "resTime"
						}
						// , {
						// "data" : "userName"
						// }
						],

					});
});// function complete

$(document)
		.ready(
				function() {
					
					
					$('#editConsumer')
					.on('click',
							function() {

						$('input:text').removeAttr("disabled");
						$('input:text').removeAttr("style");
						$("#imeinumber")
								.attr("onChange",
										"existingData('imeinumber','imeiresult',this);");

						$('#editConsumer').hide();
						$('#cancelEditConsumer').show();
						$('#saveConsumer').show();
					});
			
			$('#cancelEditConsumer').on(
					'click',
					function() {

						$('input:text').attr("disabled", "disabled");
						$('input:text').attr("style",
								"border:none;background-color:white")
						$('#cancelEditConsumer').hide();
						$('#editConsumer').show();
						$('#saveConsumer').hide();
					});
			
			$('#saveConsumer')
			.on(
					'click',
					function() {
						//	alert('saveConsumer')
						$('input:text').attr("disabled",
								"disabled");
						$('input:text')
								.attr("style",
										"border:none;background-color:white")
						$('#cancelEditConsumer').hide();
						$('#editConsumer').show();
						$('#saveConsumer').hide();
						alert("action")
							$	.ajax({
									url : "ApplianceController",
									method : "post",
									type : "json",
									data : {
										
										action : "updateCredentials",
										appImei : $(
												"#imeinumber")
												.val(),
										appGsm : $("#gsm")
												.val(),
										consumer : $("#ConsumerNumber")
												.val(),		
										appId : appId,
										

									},
									success : function(data) {
										alert("action 1");
										console.log("data.data"+data.data)
										
										console.log()
										if (data.data > 1) {
											console.log(data)
											$(
													'#cloader span.fa-spin')
													.fadeOut(
															1000,
															'linear',
															function() {
																$(
																		'#cloader')
																		.append(
																				'<span class="fa fa-check" style="color:green;font-size:18px" ><b>Success</b></span>')
															});
											window.location = "http://localhost:8080/NizamEnergyProject/ViewServlet?click=viewAppliance&id="
													+ data.data;
										}
										if (data.data < 1) {
											$(
													'##cloader span.fa-spin')
													.fadeOut(
															10000,
															'linear',
															function() {
																$(
																		'##cloader')
																		.append(
																				'<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
															});
										}

									}

								});

					});
					
					
					
					

					$('#editApp')
							.on(
									'click',
									function() {

										$('input:text').removeAttr("disabled");
										$('input:text').removeAttr("style");
										$("#imeinumber")
												.attr("onChange",
														"existingData('imeinumber','imeiresult',this);");

										$('#editApp').hide();
										$('#cancelEdit').show();
										$('#saveApp').show();
									});
					$('#cancelEdit').on(
							'click',
							function() {

								$('input:text').attr("disabled", "disabled");
								$('input:text').attr("style",
										"border:none;background-color:white")
								$('#cancelEdit').hide();
								$('#editApp').show();
								$('#saveApp').hide();
							});

					$('#saveApp')
							.on(
									'click',
									function() {

										$('input:text').attr("disabled",
												"disabled");
										$('input:text')
												.attr("style",
														"border:none;background-color:white")
										$('#cancelEdit').hide();
										$('#editApp').show();
										$('#saveApp').hide();
										$
												.ajax({
													url : "UpdateAppliance",
													method : "post",
													type : "json",
													beforeSend : function() {
														$('#loader')
																.append(
																		'<span class="fa fa-circle-o-notch fa-spin"></span>')
													},
													data : {
														action : "updateAppliance",
														appImei : $(
																"#imeinumber")
																.val(),
														appGsm : $("#gsm")
																.val(),
														appId : appId,
														customerId : customerId

													},
													success : function(data) {
														console.log("data.data"
																+ data.data)
														console
																.log("customerId"
																		+ customerId)
														console.log()
														if (data.data > 1) {
															console.log(data)
															$(
																	'#loader span.fa-spin')
																	.fadeOut(
																			1000,
																			'linear',
																			function() {
																				$(
																						'#loader')
																						.append(
																								'<span class="fa fa-check" style="color:green;font-size:18px" ><b>Success</b></span>')
																			});
															window.location = "http://solarpaygo.com/ViewServlet?click=viewAppliance&id="
																	+ data.data;
														}
														if (data.data < 1) {
															$(
																	'##loader span.fa-spin')
																	.fadeOut(
																			10000,
																			'linear',
																			function() {
																				$(
																						'##loader')
																						.append(
																								'<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
																			});
														}

													}

												});

									});
				});

function loadBatteryAndSolarVoltage() {

	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var batteryGraphLoadVoltage = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			batteryGraphLoadVoltage = data[0].batteryPerformance
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].batteryPerformance)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv4", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Battery voltage",
					"valueField" : "batteryVoltage",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Solar voltage",
					"valueField" : "solarVoltage",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Voltage",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : batteryGraphLoadVoltage

			});// /// amChart finished

		}
	});// ///finished ajax

}

function loadBatteryAndSolarCurrent() {
	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var batteryGraphLoadCurrent = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			batteryGraphLoadCurrent = data[0].batteryPerformance
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].batteryPerformance)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv5", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Battery ampere",
					"valueField" : "batteryAmpere",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Solar ampere",
					"valueField" : "solarAmpere",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Ampere",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : batteryGraphLoadCurrent

			});// /// amChart finished

		}
	});// ///finished ajax
}

function loadApplianceTemperature() {
	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var applianceTemperature = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			applianceTemperature = data[0].batteryPerformance
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].batteryPerformance)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv6", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Temperature",
					"valueField" : "applianceTemperature",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Temperature",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : applianceTemperature

			});// /// amChart finished

		}
	});// ///finished ajax
}

function getDistrictSummary() {
	console.log("hhhhh")
	$.ajax({
		url : 'ApplianceController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "districtSummary",
			appId : appId
		},
		success : function(data) {
			$('#district_summary').empty();
			var tds = '<tr>' + '<td>' + data.name + '</td>' + '<td>'
					+ data.produced +' WH' +'</td>' + '<td>' + data.consumed+' WH'
					+ '</td>' + '<td>' + data.StdLoad + 'WH'+'</td>' + '<td>'
					+ data.StdProduced +'WH' +'</td>' + '</tr>';
			$("#district_summary").append(tds);

		}
	})
}





