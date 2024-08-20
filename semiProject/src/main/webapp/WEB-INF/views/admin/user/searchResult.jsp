<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>회원 검색 결과</h1>
	<table class="admin-board">
		<c:if test="${not empty keyword and not empty type }">
			<caption>
				<c:choose>
					<c:when test="${type eq 'userId' }">회원 번호 </c:when>
					<c:when test="${type eq 'userName' }">닉네임 </c:when>
					<c:when test="${type eq 'userEmail' }">이메일 </c:when>
				</c:choose>
				<b>'${keyword }'</b> 에 대한 검색 결과입니다.
			</caption>
		</c:if>
		<thead>
			<tr>
				<th>회원 번호</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>상세 보기</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4">검색 결과가 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.userId }</td>
					<td>${item.userName }</td>
					<td>${item.userEmail }</td>
					<td><button type="button" class="admin-userinfo-btn"
							data-id="${item.userId }">상세보기</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="admin-board-page">
		<c:if test="${page > 1 }">
			<li><a
				href="search?keyword=${keyword }&type=${type }&page=${page-1 }">이전</a></li>
		</c:if>
		<c:forEach var="i" begin="1" end="${maxPage }">
			<li><a
				href="search?keyword=${keyword }&type=${type }&page=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${page < maxPage}">
			<li><a
				href="search?keyword=${keyword }&type=${type }&page=${page+1 }">다음</a></li>
		</c:if>
	</ul>
	<form action="search" class="admin-search">
		<select name="type">
			<option selected value="userId">회원 번호</option>
			<option value="userName">닉네임</option>
			<option value="userEmail">이메일</option>
		</select> <input type="number" name="keyword" id="keyword" required> <input
			type="submit" value="검색">
	</form>
</div>
