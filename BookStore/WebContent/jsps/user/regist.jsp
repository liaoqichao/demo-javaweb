<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>注册</h1>
   <%--
	  1.显示errors -> 字段错误
	  2.显示异常错误 	-> 业务逻辑错误
	  3.回显
  --%>
<p style="color: red; font-weight: 900">${msg }</p>	<%-- 2.显示逻辑错误 --%>
<form action="<c:url value='/UserServlet'/>" method="post">
	<input type="hidden" name="method" value="regist"/>
	用户名：<input type="text" name="username" value="${requestScope.form.username }"/>
	<span style="color: red; font-weight: 900">${errors.username }</span>	<%-- 1.显示字段错误 --%>
	<br/>
	密　码：<input type="password" name="password" value="${requestScope.form.password }"/>
	<span style="color: red; font-weight: 900">${errors.password }</span>	<%-- 1.显示字段错误 --%>
	<br/>
	邮　箱：<input type="text" name="email" value="${requestScope.form.email }"/>
	<span style="color: red; font-weight: 900">${errors.email }</span>	<%-- 1.显示字段错误 --%>
	<br/>
	<input type="submit" value="注册"/>
</form>
  </body>
</html>
