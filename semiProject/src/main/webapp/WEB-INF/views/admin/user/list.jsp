<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>전체 회원 조회</h1>
	<table class="admin-board">
		<thead>
			<tr>
				<th>회원 번호</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>상세 보기</th>
			</tr>
		</thead>
		<tbody>
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
		<li>이전</li>
		<li>1</li>
		<li>2</li>
		<li>3</li>
		<li>다음</li>
	</ul>
</div>
