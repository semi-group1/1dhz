<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
<h2>로그인</h2>
	
	<div class="container">
	<form action="loginProcess.jsp" method="post">
	<label for="email">이메일:</label>
	<input type="email" id="email" name="email" required><br><br>
	<label for="password">비밀번호:</label>
	<input type="password" id="password" name="password" required><br><br>
	<input type="submit" value="로그인"> </form> <br>
	<a href="register.jsp">회원가입</a>
	</div>
</body>
</html>