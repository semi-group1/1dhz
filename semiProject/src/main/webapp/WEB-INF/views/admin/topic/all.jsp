<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>전체 게시글 조회</h1>
	<table class="admin-board">
		<thead>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>비활성화</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4">결과가 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.postId }</td>
					<td><a href="#" target="_blank">${item.postTitle }</a></td>
					<td>${item.postUserName }</td>
					<td><button type="button" class="inactivateBtn post"
							data-id="${item.postId }">비활성화</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="admin-board-page">
		<c:if test="${page > 1 }">
			<li><a href="listAll?page=${page-1 }">이전</a></li>
		</c:if>
		<c:forEach var="i" begin="1" end="${maxPage }">
			<li><a href="listAll?page=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${page < maxPage}">
			<li><a href="listAll?page=${page+1 }">다음</a></li>
		</c:if>
	</ul>
</div>
