<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/admin.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin.js"></script>
</head>
<body>
	<div id="container">
		<div id="top">
			<span id="logo-admin">ADMIN PAGE</span>
		</div>
		<div id="bottom">
			<div id="left">
				<div class="admin-menu" id="members">
					<p class="admin-menu-title">MEMBERS</p>
					<p>
						<a href="${pageContext.request.contextPath}/admin/user/list">전체
							회원 조회</a>
					</p>
				</div>
				<div class="admin-menu" id="topics">
					<p class="admin-menu-title">TOPICS</p>
					<p>전체 게시판 조회</p>
					<p>일반 토픽별 조회</p>
					<p>직무별 조회</p>
				</div>
				<div class="admin-menu" id="reports">
					<p class="admin-menu-title">REPORTS</p>
					<p>게시글별 접수내역</p>
					<p>회원별 접수내역</p>
					<p>댓글별 접수내역</p>
				</div>
			</div>
			<c:choose>
				<c:when test='${command eq "userList" }'>
					<%@ include file="../user/list.jsp"%>
				</c:when>
				<c:when test='${command eq "userInfo" }'>
					<%@ include file="../user/info.jsp"%>
				</c:when>
			</c:choose>

		</div>
	</div>
</body>
</html>