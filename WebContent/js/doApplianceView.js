/**
 * 
 */
var appId=document.getElementById("appID").value;



function doAlarmTable(){

	$('#doAlarmsTable').DataTable({
		
		"processing" : true,
		"serverSide" : true,
		"order" : [[0,"asc"]],
		"ajax" : {
			"url" : "ApplianceAlarmsControl",
			"type" : "Post",
			"data" : function(d){
				d.action = "getAlarmsTable",
				d.appId= appId
			},
			"dataSrc" : function(json){
				for(var i=0;i<json.data.length;i++)
				{
					json.data[i].alert = ""
					json.data[i].status=""	
					if(json.data[i].solar_power=="true")
					{
						json.data[i].alert += " Solar Power "
					}
					if(json.data[i].battery=="true")
					{
						json.data[i].alert += " Battery "
					}
					if(json.data[i].temprature=="true")
					{
						json.data[i].alert += " Temprature "
					}
					if(json.data[i].current_load=="true")
					{
						json.data[i].alert += " Current Load "
					}
					if(json.data[i].lid_connectivity=="true")
					{
						json.data[i].alert += "Lid Connectivity"
					}
					
					if(json.data[i].ticketStatus=="0")
					{
						json.data[i].status="Pending"
					}
					else if(json.data[i].ticketStatus=="1")
					{
						json.data[i].status="Resolved"
					}

					if(json.data[i].ticketNo=="null")
					{
						json.data[i].ticketNo="N/A"
					}
					if(json.data[i].foName=="null")
					{
						json.data[i].foName="N/A"
					}
					if(json.data[i].userName=="null")
					{
						json.data[i].userName="N/A"
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
//		{
//			"data" : "ticketNo"
//		}, 
		{
			"data" : "resolvedTime"
		}
//		, {
//			"data" : "userName"
//		}
		]
		
	})
	
}