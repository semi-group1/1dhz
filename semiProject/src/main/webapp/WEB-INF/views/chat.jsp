<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" type="text/css" href="css/chat.css" />
</head>
<body>
    <div class="chat-container">
        <div class="messages">
            <c:forEach var="msg" items="${messages}">
                <div class="message ${msg.user == '홍길동' ? 'user1' : 'user2'}">
                    <strong>${msg.user}:</strong> ${msg.message}
                </div>
            </c:forEach>
        </div>
        <div class="input-container">
            <form action="${pageContext.request.contextPath}/chat" method="post">
                <input type="text" name="user" placeholder="Enter your name" required />
                <input type="text" name="message" placeholder="Enter your message" required />
                <button type="submit">Send</button>
            </form>
        </div>
    </div>
</body>
</html>
