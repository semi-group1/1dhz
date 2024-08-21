<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp -->
<header class="bg-dark text-white p-3 d-flex justify-content-between align-items-center">
    <!-- 홈버튼 클릭시 화면전환 기능구현 -->
    <div class="logo">
        <a href="http://localhost:8080/semiProject/main" id="logo-link">
            <img src="images/logo_home.png" alt="로고" class="img-fluid" style="width: 100px;">
        </a>
    </div>
    <div>
        <!-- 글쓰기, 로그인버튼 클릭시 화면전환 기능구현 -->
        <a href="http://localhost:8080/semiProject/BoardList.jsp" class="btn btn-primary" id="write-btn">글쓰기</a>
        <a href="http://localhost:8080/semiProject/login/loginPage.jsp" class="btn btn-secondary" id="login-btn">로그인</a>
    </div>
</header>