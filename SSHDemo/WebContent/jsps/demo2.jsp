<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>demo2</h1>
	<!-- 通过param.username得到URL上的参数%E5%BC%A0%E4%B8%89-->
<!-- 就是任性，我的tomcat的GET请求不用处理编码问题，我也没设置过默认编码 -->
<%
	String username = new String(request.getParameter("username"));
	username = URLDecoder.decode(username,"UTF-8");
%>
<%= username %><!-- 这里错误是误报 -->
<%-- 	${param.username } --%>
</body>
</html>