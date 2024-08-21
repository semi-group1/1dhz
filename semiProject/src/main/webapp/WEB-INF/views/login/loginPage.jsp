<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
/* 메인 컨테이너 스타일 */
.container {
	width: 1280px; /* 너비를 1280px로 고정 */
	margin: 0 auto; /* 화면 가운데 정렬 */
	padding: 20px; /* 내부 여백 */
	background-color: #f4f4f4; /* 배경 색상 */
	border: 1px solid #ccc; /* 외곽선 */
}
</style>
<script type="text/javascript">
	function checkValueAndSubmit() {
		const userEmail = document.getElementById("email");
		const emailValue = userEmail.value;

		const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;

		if (emailValue == "") {
			alert("이메일을 입력해주세요.");
			userEmail.focus();
			return;
		}
		const userPw = document.getElementById("password");
		const pwValue = userPw.value;

		const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;

		if (pwValue == "") {
			alert("비밀번호를 입력해주세요.");
			userPw.focus();
			return;
		}

		//const frm = document.getElementById("frm");
		//frm.submit();

		return true; // 유효성 검사 통과 후 폼 제출

	}
</script>


</head>
<body>
	<h2>로그인</h2>

	<div class="container">
		<form id="frm"
			action="${pageContext.request.contextPath }/main/mainPage"
			method="get" onsubmit="return checkValueAndSubmit()">
			<label for="email">이메일:</label> <input type="email" id="email"
				name="email" placeholder="이메일을 쓰세요." required><br> <br>
			<label for="password">비밀번호:</label> <input type="password"
				id="password" name="password" required><br> <br> <input
				type="submit" value="로그인">
		</form>
		<br> <a
			href="${pageContext.request.contextPath}/register/registerPage">회원가입</a>
	</div>
</body>
</html>