<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/jsps/demo22_isErrorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>这是个错误页面</h1>
	<%--errorPage="/jsps/demo22_isErrorPage.jsp"是转发不是重定向 --%>
	<%
		int a = 10 / 0;
	%>
</body>
</html>