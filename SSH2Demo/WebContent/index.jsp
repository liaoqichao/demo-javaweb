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
	<a href="<c:url value='/add.jsp'/>">增加员工</a><br/>
	<a href="<c:url value='/delete.jsp'/>">删除员工</a><br/>
	<a href="<c:url value='/update.jsp'/>">修改员工信息</a><br/>
	<a href="<c:url value='/show.jsp'/>">查询员工</a><br/>
</body>
</html>