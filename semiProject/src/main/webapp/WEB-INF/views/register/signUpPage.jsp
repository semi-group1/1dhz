<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<%
// 서버 측에서 처리할 로직
request.setAttribute("actionUrl", "join_ok.jsp");
String signUpButtonHtml = "<button type='submit' class='signup-button'>가입하기</button>";
%>


<html>
<head>
<link href="<c:url value="./layout/signUp.css"/>" rel="stylesheet" />
<script type="text/javascript">
	function checkForm() {
		// 이메일 정규표현식 유효성 검사
		const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		const email = document.newMember.email.value;
		if (!emailPattern.test(email)) {
			alert("올바른 이메일 주소를 입력하세요.");
			return false;
		}

		// 비밀번호 정규표현식 유효성 검사 (8자 이상, 대문자, 소문자, 숫자 포함)
		const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
		const password = document.newMember.password.value;
		if (!passwordPattern.test(password)) {
			alert("비밀번호는 8자 이상이어야 하며, 대문자, 소문자, 숫자를 포함해야 합니다.");
			return false;
		}

		// 비밀번호와 비밀번호 확인 일치 여부 검사
		const confirmPassword = document.newMember['confirm-password'].value;
		if (password !== confirmPassword) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}

		// 모든 유효성 검사를 통과하면 폼을 제출
		return true;
	}
</script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<div class="container">
<div class="signUp-from">
    <h2>회원 가입</h2>
    
    <form action="signUpProcess" method="post" name="newMember" onsubmit="return checkForm();">
    <div class="form-group">
        <label for="email">이메일</label>
        <input type="email" id="email" name="email" placeholder="이메일" maxlength="50" required>
    </div>
    <div class="form-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" placeholder="비밀번호" required>
    </div>
    <div class="form-group">
        <label for="confirm-password">비밀번호 확인</label>
        <input type="password" id="confirm-password" name="confirm-password" placeholder="비밀번호 확인" required>
    </div>
    <%= signUpButtonHtml %>
	</form>
		</div>
	</div>
</body>
</html>