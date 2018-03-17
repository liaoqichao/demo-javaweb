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
	<H1>注册</H1>
	<form method="post" action="<c:url value='/BServlet'/>" enctype="multipart/form-data">
		用户名：<input type="text" name="username"/><BR/>
		简　历：<input type="file" name="resume"/><BR/>
		<input type="submit" value="注册"><BR/>
	</form>
</body>
</html>