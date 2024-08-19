<%@ page language="java" contentType="text/html; charset=UTF-8"
    isErrorPage = "true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginFail</title>
</head>
<body>
	<h2>예외 발생!</h2>
	<hr />
	
	<%--<%= 버퍼의 내용을 기록한다. -> out.print --%>
	<%= exception %>
</body>
</html>