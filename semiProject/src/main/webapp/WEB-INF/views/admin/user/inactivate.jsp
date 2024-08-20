<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>회원 정지</h1>
	<form action="inactivate" method="POST">
		<input type="hidden" name="userId" value="${userInfo.userId }">
		<table class="admin-board inactivate">
			<tr>
				<th>회원 번호</th>
				<td><input type="number" value="${userInfo.userId }" disabled>
				</td>
			</tr>
			<tr>
				<th>정지 사유</th>
				<td><select name="desc">
						<option selected value="커뮤니티 규칙 위반">커뮤니티 규칙 위반</option>
						<option value="부적절한 콘텐츠 게시">부적절한 콘텐츠 게시</option>
						<option value="타인에 대한 괴롭힘 또는 폭력">타인에 대한 괴롭힘 또는 폭력</option>
						<option value="계정 해킹 및 보안 위협">계정 해킹 및 보안 위협</option>
						<option value="개인 사생활 침해">개인 사생활 침해</option>
						<option value="허위 신고">허위 신고</option>
				</select></td>
			</tr>
			<tr>
				<th>정지 기간</th>
				<td><select name="length">
						<option selected value="1">1일</option>
						<option value="7">7일</option>
						<option value="14">14일</option>
						<option value="30">30일</option>
						<option value="90">90일</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="제출">
	</form>
	<button class="historyBack">이전 페이지로 돌아가기</button>
</div>