<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="bean.UserBean"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->


<head>
	
	<script>
	function methodCall(doName, userId) {
		
		document.getElementById('doName').innerHTML = doName;
		$("#chatBox").data('receiver', userId);
		var receiver = $("#chatBox").data('receiver');
		var sender = $("[data-sender]").data('sender');
		
		$("#closeChat").show(function(){
			$('#chatBox').empty()
			$.ajax({
				
				url : 'notificationChat',
					method : "post",
					data : {
						action : 'getChat',
						sender : sender,
						receiver : receiver
					},
					success : function(data){
						
						
						
						chatList = JSON.parse(data).chatList;
						
						$.each(chatList, function(e) {

							// $.each(value, function(key, value) {
							li = document.createElement('li');
							name = ""
							if (chatList[e].sender == sender) {
								$(li).addClass('right');
								
							} else {
								$(li).addClass('left');
								
							}
							$(li).append(
									'<span class="date-time">' + chatList[e].timestamp
											+ '</span>'
											+ '<a href="javascript:;" class="name">'
											+ chatList[e].senderName + '</a>' + ' <div class="message">'
											+ chatList[e].chat_text+ '</div>')
							$('#chatBox').append(li)
// 							scrolldown();
						})
					}
					
				
			})
		});
		
	}
		
		
		function sendMessageToDo() {
			
			var receiver = $("#chatBox").data('receiver');
			var sender = $("[data-sender]").data('sender');
			
			var messageGet = $('input[name="messageText"]').val();
			if(messageGet !== ""){
				
				$.ajax({
				
					url : 'notificationChat',
					method : "post",
					data : {
						
						action : "sendMessageToDo",
						message : messageGet,
						sender : sender,
						receiver : receiver
						
					},
					
					success : function(data) {
							
						chat = JSON.parse(data);
						li = document.createElement('li');
						$(li).addClass('right')
						name = chat.senderName;
						$(li).append('<span class="date-time">'
								+ chat.timestamp + '</span>'
								+ '<a href="javascript:;" class="name">'
								+ chat.senderName + '</a>' + ' <div class="message">'
								+ chat.chat_text + '</div>');
						
						$('#chatBox').append(li)
						$('input[name="messageText"]').val("")
// 						scrolldown();
						
					}
					
				})
				
			}
			
		}
	
	function closeBtn() {
		$("#closeChat").hide(function(){
			$('#chatBox').empty()
		});
	}
	</script>
	
	<style>
	
	#footer {
	    position: fixed;
	    bottom: -29px;
	    right: 0;
	    
}
	
	</style>
	
</head>
<body>
			<% UserBean sessionBeanGet = (UserBean) session.getAttribute("email"); %>
<div  class="profile-section" id="closeChat">
                    <!-- begin row -->
                    <div class="row">
                        <!-- begin col-4 -->
                        
                        <div class="col-md-4" id="footer" style=" margin-right: 40px; margin-bottom: 10px;">
                        
                        
                        
                        <div class="panel panel-info" >
						<div class="panel-heading">
						<div class="panel-heading-btn">
						<a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus fa-lg"></i></a>
									<button onclick="closeBtn()"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									><i class="fa fa-times"></i></button>
						</div>
							<h2 style="font-size: 23px;" class="panel-title" id="doName">Messages</h2>
						</div>
						<div class="panel-body bg-silver">
							<div data-scrollbar="true" data-height="225px">
								<ul class="chats" id="chatBox"
									 
									data-sender="<%= sessionBeanGet.getUserId()%>"
									
									
									>
								</ul>
							</div>
						</div>
						<div class="panel-footer" >
							<!-- <form name="send_message_form" data-id="message-form"> -->
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="messageText"
										placeholder="Enter your message here." autocomplete="off"/> <span
										class="input-group-btn">
										<button class="btn btn-primary btn-sm" id="send_messages" onclick="sendMessageToDo()" type="button">Send</button>
									</span>
								</div>
							<!-- </form> -->
						</div>
					</div>
                        
                        
                        <!-- <div class="panel panel-warning">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								 <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus fa-lg"></i></a>
									<button onclick="closeBtn()"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									><i class="fa fa-times"></i></button>
							</div>
							<h2 class="panel-title" id="doName">Messages</h2>
						</div>

						<div class="panel-body" >
                        
                        
                        
                            
                            begin scrollbar
                            
                            
                            <div data-scrollbar="true" data-height="280px">
                                begin chats
                                <ul class="chats">
                                    <li class="left">
                                        <span class="date-time">yesterday 11:23pm</span>
                                        <a href="javascript:;" class="name">Sowse Bawdy</a>
                                        <a href="javascript:;" class="image"><img alt="" src="assets/img/user-12.jpg"></a>
                                        <div class="message">
                                            Lorem ipsum dolor sit amet, consectetuer adipiscing elit volutpat. Praesent mattis interdum arcu eu feugiat.
                                        </div>
                                    </li>
                                    <li class="right">
                                        <span class="date-time">08:12am</span>
                                        <a href="#" class="name"><span class="label label-primary">ADMIN</span> Me</a>
                                        <a href="javascript:;" class="image"><img alt="" src="assets/img/user-13.jpg"></a>
                                        <div class="message">
                                            Nullam posuere, nisl a varius rhoncus, risus tellus hendrerit neque.
                                        </div>
                                    </li>
                                    <li class="left">
                                        <span class="date-time">09:20am</span>
                                        <a href="#" class="name">Neck Jolly</a>
                                        <a href="javascript:;" class="image"><img alt="" src="assets/img/user-10.jpg"></a>
                                        <div class="message">
                                            Euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
                                        </div>
                                    </li>
                                    <li class="left">
                                        <span class="date-time">11:15am</span>
                                        <a href="#" class="name">Shag Strap</a>
                                        <a href="javascript:;" class="image"><img alt="" src="assets/img/user-14.jpg"></a>
                                        <div class="message">
                                            Nullam iaculis pharetra pharetra. Proin sodales tristique sapien mattis placerat.
                                        </div>
                                    </li>
                                </ul>
                                end chats
                            </div>
                            <div class="panel-footer" style="margin-top: 5px;">
							<form name="send_message_form" data-id="message-form">
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="message"
										placeholder="Enter your message here." autocomplete="off"> <span
										class="input-group-btn">
										<button class="btn btn-primary btn-sm" id="send-message" type="button">Send</button>
									</span>
								</div>
							</form>
						</div>
                            
                            </div>
                            </div> -->
                            <!-- end scrollbar -->
                        </div>
                        
                        <!-- end col-4 -->
                        
                    </div>
                    <!-- end row -->
                </div>
                <!-- end profile-section -->

	

	
	<script>
		$(document).ready(function() {
			
			$("#closeChat").hide();
			
			

// 			App.init();
			
			
			
			
			   			
		});
	</script>
	
</body>
</html>