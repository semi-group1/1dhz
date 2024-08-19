<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>일반 토픽별 조회</h1>
	<table class="admin-board">
		<thead>
			<tr>
				<th>게시글 번호</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>작성자</th>
				<th>비활성화</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.postId }</td>
					<td>${item.postCategoryName }</td>
					<td><a href="#" target="_blank">${item.postTitle }</a></td>
					<td>${item.postUserName }</td>
					<td><button type="button" class="admin-setTopicInactive-btn"
							data-id="${item.postId }">비활성화</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="admin-board-page">
		<li>이전</li>
		<li>1</li>
		<li>2</li>
		<li>3</li>
		<li>다음</li>
	</ul>
</div>
