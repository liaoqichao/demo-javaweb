<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>登录</H1>
	${msg }
	<form action="<c:url value='/LoginServlet?method=login'/>" method="post">
		用户名：<input type="text" name="username"/><br/>
		登　录：<input type="password" name="password"/><br/>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>