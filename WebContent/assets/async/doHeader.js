var min = 30;
var sec = 30;

var timer = setInterval(function() {
	if (sec <= 0) {
		if (min <= 0 && sec <= 0) {
			clearInterval(timer);
			// window.location="shsLogin.jsp";
		}
		if (min > 0) {
			sec = 60;
		}
		min--;
	}
	sec--;
}, 1000);

$("body").mousemove(function() {

	if (min <= 0 && sec <= 0) {

		window.location = "LoginServlet?click=logout";
	} else {
		min = 30;
		sec = 30;
	}
});
$(function() {
	$('.dropdown>a>span').hide();
	$('.dropdown-header>span').hide()

	getUnseenRequest();
	setInterval(updateHeaderNotification, 5000)

})

function updateHeaderNotification() {

	countUnseenMessagesFromAppliance();
	getUnseenMessagesFromAppliance()

}

// Begin Count Unseen Messages From Appliances
function countUnseenMessagesFromAppliance() {
	$.ajax({
		url : 'DoAlerts',
		method : 'POST',
		data : {
			action : 'countUnseenMessagesFromAppliance'
		},
		dataType : 'json',
		success : function(data) {
			if (data.countAlerts > 0) {
				$('#appliance_messages_do a span').text(data.countAlerts)
				$('#appliance_messages_do a span').show();
			} else {
				$('#appliance_messages_do a span').hide();
				$('#appliance_messages_do ul .dropdown-header span').hide()
			}
		}

	})
}

// End Count Unseen Messages from Appliances

var noMessageLi = '<li class="media"  id="no_message">'
		+ '<a href="javascript:;">' + '<div class="media-body">'
		+ '<h6 class="media-heading">No Alarms</h6>' + '</div>' + '</a>'
		+ '</li>'
// Begin Get Unseen Messages From Appliance
function getUnseenMessagesFromAppliance() {
	$
			.ajax({
				url : 'DoAlerts',
				method : 'POST',
				data : {
					action : 'getUnseenMessagesFromAppliance'
				},
				dataType : 'json',
				success : function(data) {
					// console.log('Get Unseen Messages From Appliance')
					// console.log(data)

					$('#appliance_messages_do ul li.data').remove()

					if (data.length > 0) {
						if ($('#appliance_messages_do ul #no_message')) {
							$('#appliance_messages_do ul #no_message').hide()
						}
						$
								.each(
										data,
										function(e) {
											var li = '<li class="media data">'
													+ '<a href="ViewServlet?click=viewDoAppliance&id='
													+ data[e].applianceId
													+ '">'
													+ '<div class="media-left"><i class="fa fa-bell media-object bg-blue"></i></div>'
													+ '<div class="media-body">'
													+ '<h6 class="media-heading">'
													+ data[e].messageFrom
													+ '</h6>'
													+ '<p class="text-muted">'
													+ data[e].messageDate
													+ '</p>' + '</div>'
													+ '</a>' + '</li>'
											$(
													'#appliance_messages_do ul .dropdown-footer')
													.before(li);
										})

						if (data.length > 5) {
							$('#appliance_messages_do ul li.dropdown-footer')
									.show()
						}
						// $('#appliance_messages ul').append(footer);
					} else {
						$('#appliance_messages_do ul li.dropdown-footer')
								.hide()
						$('#appliance_messages_do ul #no_message').show()
					}
				}

			})
}

// End Get Unseen Messages From Appliances

function getUnseenRequest() {
	$.ajax({
		url : 'DoDashboardController2',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getUnseenRequest"
		},
		success : function(data) {
			// console.log("UnseenRequest Success")
			// console.log(data)
			$('a #unseen_loan_request_count').empty()
			if (data.request > 0) {
				$('a #do_unseen_loan_request_count').text(data.request)
			} else {
				$('a #do_unseen_loan_request_count').empty()
			}
		}
	})
}