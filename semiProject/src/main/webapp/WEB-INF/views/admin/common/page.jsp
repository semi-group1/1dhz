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
			<span id="logo-admin"><a
				href="${pageContext.request.contextPath }/admin">ADMIN PAGE</a></span>
		</div>
		<div id="bottom">
			<div id="left">
				<div class="admin-menu" id="members">
					<div class="admin-menu-title">MEMBERS</div>
					<div class="admin-menu-element">
						<a href="${pageContext.request.contextPath}/admin/user/list">전체
							회원 조회</a>
					</div>
					<div class="admin-menu-element">
						<a
							href="${pageContext.request.contextPath}/admin/user/listInactive">활동
							정지 회원 조회</a>
					</div>
				</div>
				<div class="admin-menu" id="topics">
					<div class="admin-menu-title">TOPICS</div>
					<div class="admin-menu-element">
						<a href="${pageContext.request.contextPath }/admin/topic/listAll">전체
							게시판 조회</a>
					</div>
					<div class="admin-menu-element">
						<a
							href="${pageContext.request.contextPath }/admin/topic/listGeneral">일반
							토픽별 조회</a>
					</div>
					<div class="admin-menu-element">
						<a href="${pageContext.request.contextPath }/admin/topic/listJob">직무별
							조회</a>
					</div>
				</div>
				<div class="admin-menu" id="reports">
					<div class="admin-menu-title">REPORTS</div>
					<div class="admin-menu-element">
						<a href="${pageContext.request.contextPath }/admin/report/post">게시글
							접수내역</a>
					</div>
					<div class="admin-menu-element">
						<a href="${pageContext.request.contextPath }/admin/report/user">회원
							접수내역</a>
					</div>
					<div class="admin-menu-element">
						<a href="${pageContext.request.contextPath }/admin/report/comment">댓글
							접수내역</a>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test='${command eq "userList" }'>
					<%@ include file="../user/list.jsp"%>
				</c:when>
				<c:when test='${command eq "userListInactive" }'>
					<%@ include file="../user/listInactive.jsp"%>
				</c:when>
				<c:when test='${command eq "userSearchList" }'>
					<%@ include file="../user/searchResult.jsp"%>
				</c:when>
				<c:when
					test='${command eq "userInfo" or command eq "setUserInacitve" }'>
					<%@ include file="../user/info.jsp"%>
				</c:when>
				<c:when test='${command eq "inactivateUser" }'>
					<%@ include file="../user/inactivate.jsp"%>
				</c:when>
				<c:when test='${command eq "topicListAll" }'>
					<%@ include file="../topic/all.jsp"%>
				</c:when>
				<c:when test='${command eq "topicListGeneral" }'>
					<%@ include file="../topic/general.jsp"%>
				</c:when>
				<c:when test='${command eq "topicListJob" }'>
					<%@ include file="../topic/job.jsp"%>
				</c:when>
				<c:when test='${command eq "inactivatePost" }'>
					<%@ include file="../topic/inactivate.jsp"%>
				</c:when>
				<c:when test='${command eq "postReports" }'>
					<%@ include file="../report/post.jsp"%>
				</c:when>
				<c:when test='${command eq "userReports" }'>
					<%@ include file="../report/user.jsp"%>
				</c:when>
				<c:when test='${command eq "commentReports" }'>
					<%@ include file="../report/comment.jsp"%>
				</c:when>
			</c:choose>

		</div>
	</div>
</body>
</html>