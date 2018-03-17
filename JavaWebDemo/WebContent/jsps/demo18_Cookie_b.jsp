<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>浏览器归还Cookie到服务器</h1>
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				out.print(cookie.getName()+" = "+cookie.getValue() + "<br/>");
			}
		}
// 		浏览器输出：
// 		aaa = AAA
// 		bbb = BBB
// 		JSESSIONID = C2186D1F7CC67EA8E8D2C76B3F35DF34
	%>
</body>
</html>