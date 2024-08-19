<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // 로그인 검증 로직
    if("test@example.com".equals(email) && "password123".equals(password)) {
        // 로그인 성공 시 세션에 성공 상태 저장
        session.setAttribute("loginSuccess", true);
        response.sendRedirect("loginPage.jsp");
    } else {
        // 로그인 실패 시
        session.setAttribute("loginSuccess", false);
        response.sendRedirect("loginPage.jsp");
    }
%>