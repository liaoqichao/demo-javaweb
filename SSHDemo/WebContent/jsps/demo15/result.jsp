<%@ page language="java" import="java.util.Date" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>这里是显示结果的页面。</H1>
	<%= new Date() %><br/>
	<s:property value="username"/><br/><%--值栈顶的属性，不需要加#号，可以直接访问，也可以用el表达式 --%>
	<s:property value="password"/><br/>
</body>
</html>