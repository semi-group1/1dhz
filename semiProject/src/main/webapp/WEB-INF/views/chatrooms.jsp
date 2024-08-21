<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>채팅방 리스트</title>
    <link rel="stylesheet" type="text/css" href="../css/chatrooms.css" />
</head>
<body>
    <div class="chat-container">
        <h1>채팅방 리스트</h1>
        <div class="create-chatroom-button">
            <form action="/semiProject/createChatRoom" method="post">
                <button type="submit">채팅방 생성</button>
            </form>
        </div>
        <div class="chatroom-list">
            <ul>
                <c:forEach var="roomId" items="${chatRoomIds}">
                    <li>
                        <a href="/semiProject/chat?chatRoomId=${roomId}">채팅방 ${roomId}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>