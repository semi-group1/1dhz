<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>게시글별 접수 내역</h1>
	<table class="admin-board">
		<thead>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>신고자</th>
				<th>신고사유</th>
				<th>처리상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.reportTargetId }</td>
					<td><a href="#" target="_blank">${item.reportedTitle }</a></td>
					<td>${item.reportedUsername }</td>
					<td>${item.reportUserName }</td>
					<td>${item.reportDesc }</td>
					<td><form method="GET" action="/proceed">
							<input type="hidden" name="reportId" value="${item.reportId }">
							<select name="status">
								<option value="inProgress">처리 중</option>
								<option value="completed">처리 완료</option>
								<option value="denied">거부됨</option>
							</select> <input type="submit" value="처리">
						</form></td>
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
