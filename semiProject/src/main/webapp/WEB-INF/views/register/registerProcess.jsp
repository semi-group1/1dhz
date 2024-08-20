<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.regex.Pattern" %>
<%
String email = request.getParameter("email");
String password = request.getParameter("password");
String confirmPassword = request.getParameter("confirmPassword");
String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	if (Pattern.matches(emailRegex, email) && password.equals(confirmPassword)) {
		session.setAttribute("email", email);
		out.println("<script>alert('회원가입이 완료되었습니다.'); location.href='main.jsp';</script>");
		} else {
			out.println("<script>alert('이메일 형식이 올바르지 않거나 비밀번호가 일치하지 않습니다.'); history.back();</script>");
			}
%>