<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String email = (String) session.getAttribute("email"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String loginBtn = "loginPage.jsp";
	String loginbutton = "<button class='login-button' onclick=\"window.location.href='" + loginBtn + "';\">로그인</button>";
%>

<!DOCTYPE html>
<html>
<head> <meta charset="UTF-8">
<title>메인 페이지</title>
<style>
        /* 메인 컨테이너 스타일 */
        .container {
            width: 1280px;  /* 너비를 1280px로 고정 */
            margin: 0 auto; /* 화면 가운데 정렬 */
            padding: 20px;  /* 내부 여백 */
            background-color: #f4f4f4; /* 배경 색상 */
            border: 1px solid #ccc; /* 외곽선 */
        }
</style>
</head>
<body>
<div class="container">
	<%c:if(email != null)
	{ %> <h2><%= email.split("@")[0] %>님, 어서오세요.</h2>
	<a href="logOut.jsp">로그아웃</a> <% }
	else { %> <h2>환영합니다</h2>
	<a href="${pageContext.request.contextPath}/login/loginPage">로그인</a> <% } %>
</div>
</body>
</html>