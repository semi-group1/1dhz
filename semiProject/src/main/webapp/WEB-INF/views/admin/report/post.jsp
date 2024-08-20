<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>게시글 접수 내역</h1>
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
			<c:if test="${empty list }">
				<tr>
					<td colspan="6">결과가 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.reportTargetId }</td>
					<td><a href="#" target="_blank">${item.reportedTitle }</a></td>
					<td>${item.reportedUsername }</td>
					<td>${item.reportUserName }</td>
					<td>${item.reportDesc }</td>
					<c:choose>
						<c:when test="${item.reportStatus eq 'inProgress'}">
							<td><form method="GET" action="changeStatus">
									<input type="hidden" name="reportId" value="${item.reportId }">
									<input type="hidden" name="reportType" value="post"> <select
										name="status">
										<option selected value="inProgress">처리 중</option>
										<option value="completed">처리 완료</option>
										<option value="denied">거부됨</option>
									</select> <input type="submit" value="처리">
								</form></td>
						</c:when>
						<c:otherwise>
							<td><c:choose>
									<c:when test="${item.reportStatus eq 'completed'}">처리완료</c:when>
									<c:otherwise>거부됨</c:otherwise>
								</c:choose></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>