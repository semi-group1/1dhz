<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>활동 정지 회원 조회</h1>
	<table class="admin-board">
		<thead>
			<tr>
				<th>회원 번호</th>
				<th>닉네임</th>
				<th>활동 정지 기간</th>
				<th>상세 보기</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.userId }</td>
					<td>${item.userName }</td>
					<td>${item.userInactiveStartDate }-${item.userInactiveEndDate }</td>
					<td><button type="button" class="admin-userinfo-btn"
							data-id="${item.userId }">상세보기</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="admin-board-page">
		<c:if test="${page > 1 }">
			<li><a href="listInactive?page=${page-1 }">이전</a></li>
		</c:if>
		<c:forEach var="i" begin="1" end="${maxPage }">
			<li><a href="listInactive?page=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${page < maxPage}">
			<li><a href="listInactive?page=${page+1 }">다음</a></li>
		</c:if>
	</ul>
</div>
