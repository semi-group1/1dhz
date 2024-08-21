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
	<a href="${pageContext.request.contextPath}/admin/user/list">전체 회원 조회</a>
	<a href="${pageContext.request.contextPath}/admin/topic/all">전체 게시글 조회</a>
	<a href="${pageContext.request.contextPath}/admin/user/inactivate?userId=0">회원 정지</a>
</body>
</html>