<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

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

<script>
	fuction checkValueAndSumit() {
	const userEmail = document.getElementById("email");
	const emailValue = userEmail.value;
	
	const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;
	
	if(emailValue=="") {
		alert("아이디를 입력해주세요.");
		userEmail.focus();
		return;
	}
	const userPw = document.getElementById("password");
	const userPwConfirm = document.getElementById("confirmPassword");
	
	if(userPw.value != userPwConfirm.value) {
		alert("비밀번호가 서로 맞지 않습니다.");
		userPw.value="";
		userPwConfirm.value="";
		userPw.focus();
		return;
	}
	
	const pwRegex = /^(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;
	
	
	if (!pwRegex.test(userPw.value)) {
		alert("비밀번호는 8자이상 15자이하 대소문자포함 하셔야 합니다.");
		userPw.value="";
		userPwConfirm.value="";
		
		userPw.focus();
		return;
	}
	
	const frm = document.getElementById("frm");
	frm.submit();
}
</script>

</head>
<body>
<h2>회원가입</h2>
	<form  id="frm" action="RegisterProcess" method="post" >
	<label for="email">이메일:</label>
	<input type="email" id="email" name="email" required><br><br>
	<label for="password">비밀번호:</label>
	<input type="password" id="password" name="password" required><br><br>
	<label for="confirmPassword">비밀번호 확인:</label>
	<input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
	<input type="submit" value="회원가입하기" onclick="checkValueAndSubmit">
	</form>
</body>
</html>