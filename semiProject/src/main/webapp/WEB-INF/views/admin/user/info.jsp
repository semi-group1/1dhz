<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<c:forEach items="${userPosts }" var="post" varStatus="status">
				<tr>
					<td>${post.postId }</td>
					<td><a href="#">${post.postTitle }</a></td>
					<td>${post.postComments }</td>
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
			<tr>
				<td>1</td>
				<td>저도요.</td>
				<td><a href="">토픽>개발>스프링 너무 어렵네요.</a></td>
			</tr>
			<tr>
				<td>1</td>
				<td>저도요.</td>
				<td><a href="">토픽>개발>스프링 너무 어렵네요.</a></td>
			</tr>
			<tr>
				<td>1</td>
				<td>저도요.</td>
				<td><a href="">토픽>개발>스프링 너무 어렵네요.</a></td>
			</tr>
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
		<button id="historyBack">이전 페이지로 돌아가기</button>
		<button>회원 정지</button>
	</div>
</div>