<%@ page language="java" contentType="text/html; charset=UTF-8"
    errorPage = "joinFail.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
// 서버 측에서 처리할 로직
String signupPageUrl = "signUpPage.jsp";
String buttonHtml = "<a href='" + signupPageUrl + "' class='signup-button'>처음오셨나요?</a>";
%>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="./layout/login.css"/>" rel="stylesheet" />
    <meta charset="UTF-8">
    <title>로그인 페이지</title>
</head>
<body>
    <div class="container">
        <div class="login-form">
            <h2>로그인</h2>
            <form action="LoginProcess" method = "POST">
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="login-button" onClick="">로그인</button>
            </form>
            <hr />
            <p><font color="red">{loginfailMsg}</p>
            
            <div class="signup-text">
                <hr />
                <!-- 여기에서 JSP 표현식을 사용하여 버튼 HTML을 출력 -->
                <!-- 처음오셨나요? 버튼 -->
                <%= buttonHtml %>
            </div>
        </div>
    </div>
</body>
</html>