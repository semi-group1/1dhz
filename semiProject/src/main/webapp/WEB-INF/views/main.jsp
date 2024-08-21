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
    <%@ include file="header.jsp" %>
    
    <main id="main-content" class="container my-4">
        <div class="search-bar mb-4">
            <input type="text" class="form-control rounded-pill search-input" placeholder="게시판 주제 검색">
        </div>
        
        <!-- 게시판 홈 연결 -->
 		<div class="main-banner">
	        <a href="http://localhost:8080/semiProject/main" id="main-banner">
	            <img src="images/main_banner.png" alt="메인배너" class="img-fluid">
	        </a>
   		 </div><br /><br />
        
        
       
        
        <div id="board-content" class="row">
            <!-- 게시판 내용 추가-->
        </div>
    </main>
    
  
     <!-- 메인 컨텐츠 -->
	    <div id="wrap">
	        <div id="container">
	            <div class="inner">
	                <h2>Welcome to the Board</h2>
	                <div id="board-section">
	                    <%@ include file="BoardList.jsp" %>
	                </div>
	            </div>
	        </div>
	    </div>
    
    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom JS -->
    <script src="scripts/main.js"></script>
</body>
</html>
