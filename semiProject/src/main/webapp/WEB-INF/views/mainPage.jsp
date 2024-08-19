<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String loginBtn = "loginPage.jsp";
	String loginbutton = "<button class='login-button' onclick=\"window.location.href='" + loginBtn + "';\">로그인</button>";
%>
<html>
<head>
    <meta charset="UTF-8">
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
        
         /* 로그인 버튼 스타일 */
        .login-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }

        .login-button:hover {
            background-color: #0056b3; /* 호버 시 버튼 색상 */
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- 여기에 메인 화면의 내용을 추가하세요 -->
        <h1>Main Page</h1>
        <p>로그인</p>
        <%= loginbutton %>
    </div>
</body>
</html>