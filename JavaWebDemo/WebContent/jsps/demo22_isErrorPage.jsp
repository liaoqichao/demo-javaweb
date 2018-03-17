<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>这是处理错误的页面,有isErrorPage="true",所以状态码是500,而不是200。而且可以使用九大内置对象中的exception</h2>
	<%
		exception.printStackTrace(response.getWriter());//输出到浏览器,而不是输出到控制台
	%>
</body>
</html>