<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트 페이지</title>
</head>
<body>
	<h1>테스트 페이지</h1>
	<ol>
		<li><a href="${pageContext.request.contextPath }/main">메인
				페이지로 이동하기</a></li>
		<li><a href="${pageContext.request.contextPath }/myPage/1">메인페이지로
				이동하기</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/user/list">관리자
				페이지로 이동</a></li>
		<li><a href="${pageContext.request.contextPath }/loginAdmin">관리자
				상태로 로그인하기</a></li>
		<li><a href="${pageContext.request.contextPath }/logoutAdmin">로그아웃
				하기</a></li>
	</ol>
</body>
</html>