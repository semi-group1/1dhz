<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.regex.Pattern" %>
<% 
String email = request.getParameter("email");
String password = request.getParameter("password");
String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
String passwordRegex = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{1,15}$";
if (Pattern.matches(emailRegex, email) && Pattern.matches(passwordRegex, password)) {
	session.setAttribute("email", email);
	response.sendRedirect("main.jsp");
	} else { out.println("<script>alert('이메일 또는 비밀번호 형식이 올바르지 않습니다.'); history.back();</script>");
		}
%>
