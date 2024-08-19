<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty result}">
	<script>
		<c:choose>
		<c:when test="${result eq true }">
		alert('회원이 활동 정지 상태가 되었습니다.');
		</c:when>
		<c:otherwise>
		alert('회원이 활동 정지 상태가 되지 않았습니다.');
		</c:otherwise>
		</c:choose>
	</script>
</c:if>
<div id="right">
	<h2>회원 정보</h2>
	<table class="admin-board">
		<tr>
			<th>회원 번호</th>
			<td>${userInfo.userId }</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${userInfo.userName }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${userInfo.userEmail }</td>
		</tr>
		<tr>
			<th>직무</th>
			<td>${userInfo.userJob }</td>
		</tr>
		<tr>
			<th>상태</th>
			<td>${userInfo.userStatus}</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${userInfo.userJoinDate }</td>
		</tr>
		<tr>
			<th>신고 누적 횟수</th>
			<td><button>상세보기</button></td>
		</tr>
	</table>
	<hr>
	<h2>작성 게시글 목록</h2>
	<table class="admin-board">
		<thead>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty userPosts }">
					<tr>
						<td colspan="3">작성된 게시글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${userPosts }" var="post" varStatus="status">
						<tr>
							<td>${post.postId }</td>
							<td><a href="#">${post.postTitle }</a></td>
							<td>${post.postComments }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<ul class="admin-board-page">
		<li>이전</li>
		<li>1</li>
		<li>2</li>
		<li>3</li>
		<li>다음</li>
	</ul>
	<hr>
	<h2>작성 댓글 목록</h2>
	<table class="admin-board">
		<thead>
			<tr>
				<th>댓글 번호</th>
				<th>내용</th>
				<th>원게시글</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty userComments }">
					<tr>
						<td colspan="3">작성된 댓글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${userComments }" var="comment"
						varStatus="status">
						<tr>
							<td>${comment.commentId }</td>
							<td>${comment.commentText }</td>
							<td><a href="#">${comment.postTitle }</a></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<ul class="admin-board-page">
		<li>이전</li>
		<li>1</li>
		<li>2</li>
		<li>3</li>
		<li>다음</li>
	</ul>
	<hr>
	<div>
		<button class="historyBack">이전 페이지로 돌아가기</button>
		<button class="inactivateBtn" data-id="${userInfo.userId }">회원
			정지</button>
	</div>
</div>