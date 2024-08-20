<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="right">
	<h1>
		일반 토픽별 조회
		<c:forEach items="${categories }" var="category">
			<c:if test="${category.categoryId eq categoryId }"> > ${category.categoryName }
			</c:if>
		</c:forEach>
		<c:if test="${empty categoryId }"> > 전체 보기
			</c:if>
	</h1>
	<table class="admin-category">
		<thead>
			<tr>
				<th colspan="4">카테고리 목록</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="listGeneral">전체 보기</a></td>
				<c:forEach items="${categories }" var="category" varStatus="status">
					<c:choose>
						<c:when test="${status.count <3}">
							<c:if test="${status.count % 3 == 0 }">
			</tr>
			<tr>
				</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${status.count % 4 == 0 }">
			</tr>
			<tr>
				</c:if>
				</c:otherwise>
				</c:choose>
				<td><a href="selectCategory?categoryId=${category.categoryId }">${category.categoryName }</a></td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
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
			<c:if test="${empty list }">
				<tr>
					<td colspan="5">결과가 존재하지 않습니다.</td>
				</tr>
			</c:if>
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
		<c:choose>
			<c:when test="${empty categoryId }">
				<c:if test="${page > 1 }">
					<li><a href="listGeneral?page=${page-1 }">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="1" end="${maxPage }">
					<li><a href="listGeneral?page=${i }">${i }</a></li>
				</c:forEach>
				<c:if test="${page < maxPage}">
					<li><a href="listGeneral?page=${page+1 }">다음</a></li>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${page > 1 }">
					<li><a
						href="selectCategory?categoryId=${categoryId }&page=${page-1 }">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="1" end="${maxPage }">
					<li><a
						href="selectCategory?categoryId=${categoryId }&page=${i }">${i }</a></li>
				</c:forEach>
				<c:if test="${page < maxPage}">
					<li><a
						href="selectCategory?categoryId=${categoryId }&page=${page+1 }">다음</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
