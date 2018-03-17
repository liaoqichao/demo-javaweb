<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>Demo11</H1>
	<div>模拟用户登录。如果没有登录，不能访问action的所有方法，如果登录了可以访问action的方法。</div>
	<%
		request.getSession().setAttribute("user", "itcast");
	%>
	用户已经登录。
</body>
</html>