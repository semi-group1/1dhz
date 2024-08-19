<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>직장인 커뮤니티 _ 벌집</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- CSS -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <header class="bg-dark text-white p-3 d-flex justify-content-between align-items-center">
        <div class="logo">
            <a href="#" id="logo-link">
                <img src="images/logo_home.png" alt="로고" class="img-fluid" style="width: 100px;">
            </a>
        </div>
        <div>
            <button class="btn btn-primary" id="write-btn">글쓰기</button>
            <button class="btn btn-secondary" id="login-btn">로그인</button>
        </div>
    </header>
    
    <main id="main-content" class="container my-4">

        <div class="search-bar mb-4">
            <input type="text" class="form-control rounded-pill search-input" placeholder="게시판 주제 검색">
        </div>
     
        <div id="board-content" class="row">
            <!-- 게시판 내용 추가-->
        </div>
    </main>
    
    <footer class="bg-dark text-white text-center p-3">
        <div class="footer-logo mb-2">
            <img src="images/logo_bottom.png" alt="일단하조 로고" style="width: 100px;">
        </div>
        <p>© 2024 일단하조</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom JS -->
    <script src="scripts/main.js"></script>
</body>
</html>
	