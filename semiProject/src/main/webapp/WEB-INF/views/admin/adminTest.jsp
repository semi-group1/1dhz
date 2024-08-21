<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 테스트 페이지</title>
</head>
<body>
	<h1>관리자 테스트 페이지</h1>
	<a href="${pageContext.request.contextPath}/admin/user/list">관리자
		페이지로 이동</a>
	<a href="${pageContext.request.contextPath }/loginAdmin">관리자 상태로
		로그인하기</a>
	<a href="${pageContext.request.contextPath }/logoutAdmin">로그아웃 하기</a>
</body>
</html>