<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>채팅</title>
    <link rel="stylesheet" type="text/css" href="../css/chat.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
    	$(document).ready(function() {
        	var autoScroll = true;

        	// 메시지 새로 고침
        	function fetchMessages() {
            	$.ajax({
                	url: '/semiProject/chat/messages',
                	method: 'GET',
                	data: { chatRoomId: '${chatRoomId}' },
                	dataType: 'html',
                	success: function(response) {
                    	$('#messages').html(response);
                    	
                    	if (autoScroll) {
                            scrollToBottom(); // 자동 스크롤이 활성화된 경우, 스크롤을 맨 아래로 이동
                        }
        	        }
            	});
        	}

        	// 메시지 보낼 때 스크롤 위치 맨 아래로
        	function scrollToBottom() {
        		var messagesContainer = $('#messages');
        		messagesContainer.scrollTop(messagesContainer[0].scrollHeight);
        	}

    		// 메시지 전송시 스크롤 맨 아래로 이동
    		$('#chatForm').on('submit', function() {
        		scrollToBottom();
    		});

        	// 5초마다 새로고침(시간초는 수정가능)
        	setInterval(fetchMessages, 5000);
        	
        	// 스크롤 위치 고정
        	$('#messages').on('scroll', function() {
                var messagesContainer = $(this);
                var isScrolledToBottom = messagesContainer[0].scrollHeight - messagesContainer.scrollTop() === messagesContainer.height();
                autoScroll = isScrolledToBottom;
            });
        	
        	// 메시지 새로고침
        	fetchMessages();
  	  	});
	</script>
</head>
<body>
    <div class="chat-container">
    	<div class="back-button-container">
        	<a href="/semiProject/chatrooms" class="back-button">뒤로가기</a>
    	</div>
    	<div class="messages" id="messages"></div>
    
    	<div class="input-container">
        	<form action="/semiProject/chat" method="post">
            	<input type="hidden" name="chatRoomId" value="${chatRoomId}" />
            	<input type="hidden" name="senderId" value="${userId}" />
            	<input type="text" name="message" placeholder="메시지를 입력하세요" required />
            	<button type="submit">전송</button>
        	</form>
    	</div>
	</div>
</body>
</html>
