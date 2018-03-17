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
	<h1>${request.person }</h1>
	<form action="<c:url value='/PersonServlet'/>" method="post">
		<input type="hidden" name="method" value="getPerson">
		用户ID:<input type="text" name="personid"/>
		<input type="submit" value="查找"/>
	</form>
</body>
</html>