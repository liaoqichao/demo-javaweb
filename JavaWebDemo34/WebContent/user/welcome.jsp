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
	<h1>欢迎登陆本系统</h1>
	<c:choose><%--每个页面都需要判断都需要这部分代码，使用过滤器后这个问题可以解决。 --%>
		<c:when test="${empty sessionScope.sessionUser }">滚！</c:when>
		<c:otherwise>
			${sessionScope.sessionUser }
		</c:otherwise>
	</c:choose>
</body>
</html>