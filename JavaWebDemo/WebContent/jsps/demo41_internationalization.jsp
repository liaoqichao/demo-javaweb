<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--演示国际化 --%>
<%--把与语言相关的的所有字符串都写成变量 --%>
<%
	/*
	1.获取Locale，这是有客户端的浏览器提供
	2.创建ResouceBundle
	3.把所有语言信息使用rb.getString("xxx")来替换
	*/
	Locale locale = request.getLocale();
	ResourceBundle rb = ResourceBundle.getBundle("demo41Res",locale);
%>
	<%--资源文件是UTF-8编码,rb获取后用ISO-8859-1解码，所以rb得到的值都需要ISO-8859-1编码在UTF-8解码 --%>
	<h1><%=new String(rb.getString("login").getBytes("ISO-8859-1"),"UTF-8") %></h1>
	<form action="" method="post">
		<%=new String(rb.getString("username").getBytes("ISO-8859-1"),"UTF-8") %>：<input type="text" name="username"/><br/>
		<%=new String(rb.getString("password").getBytes("ISO-8859-1"),"UTF-8") %>：<input type="password" name="password"/><br/>
		<input type="submit" value="<%= new String(rb.getString("login").getBytes("ISO-8859-1"),"UTF-8") %>"/>
	</form>
</body>
</html>