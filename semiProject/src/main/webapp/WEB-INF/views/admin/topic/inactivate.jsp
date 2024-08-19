<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>회원 정지</h1>
	<form action="inactivate" method="POST">
		<input type="hidden" name="postId" value="${postId }">
		<table class="admin-board inactivate">
			<tr>
				<th>게시글 번호</th>
				<td><input type="number" value="${postId }" disabled></td>
			</tr>
			<tr>
				<th>블라인드 사유</th>
				<td><select name="type">
						<option selected value="커뮤니티 규칙 위반">커뮤니티 규칙 위반</option>
						<option value="부적절한 콘텐츠 게시">부적절한 콘텐츠 게시</option>
						<option value="저작권 침해">저작권 침해</option>
						<option value="허위사실 유포">허위사실 유포</option>
						<option value="타인에 대한 괴롭힘 또는 폭력">타인에 대한 괴롭힘 또는 폭력</option>
						<option value="스팸">스팸</option>
						<option value="개인 사생활 침해">개인 사생활 침해</option>
				</select></td>
			</tr>
			<tr>
				<th>설명</th>
				<td><textarea rows="" cols=""></textarea></td>
			</tr>
		</table>
		<input type="submit" value="제출">
	</form>
	<button class="historyBack">이전 페이지로 돌아가기</button>
</div>