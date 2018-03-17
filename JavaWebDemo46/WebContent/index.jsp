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
	<a href="<c:url value='/AServlet?username=张三'/>" >点击这里</a>
	<form action="<c:url value='/AServlet'/>" method="post">
		用户名:<input type="text" name="username"/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>