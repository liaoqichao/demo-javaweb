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
	<a href="<c:url value='/demo8/aaction_test1?username=zhangSan&password=123'/>">点击这里</a><br/>
	<form action="<c:url value='/demo8/aaction_test1'/>" method="post">
		用户名：<input type="text" name="username"/><br/>
		密　码：<input type="password" name="password"/><br/>
		<input type="submit" value="提交"/><br/>
	</form>
	<br/>
	<a href="<c:url value='/demo8/aaction_test2?user.username=zhangSan&user.password=123'/>">点击这里</a><br/>
	<form action="<c:url value='/demo8/aaction_test2'/>" method="post">
		用户名：<input type="text" name="user.username"/><br/>
		密　码：<input type="password" name="user.password"/><br/>
		<input type="submit" value="提交"/><br/>
	</form>
	<br/>
	<a href="<c:url value='/demo8/aaction_test3?birthday=2016-3-21'/>">test3,2016-3-21</a><br/>
	<a href="<c:url value='/demo8/aaction_test3?birthday=20160321'/>">test3,20160321</a>
</body>
</html>