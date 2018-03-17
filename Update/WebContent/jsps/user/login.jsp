<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<title>版本更新系统-登录</title>
</head>
<body>
	<div class="title" align="center">
		<h1>登录</h1>
	</div>
	<div align="right">
		<a href="<c:url value='/index.jsp'/>">返回主页面</a>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<br/>
	<div align="center">
		<form action="<c:url value='/user/userAction_login.action'/>" method="post">
			<font color="red">${loginMsg }</font><br/>
			<font color="red">${error }</font><br/><br/>
			管理员账户：<input type="text" name="user.username" value="${user.username }"/>
			<br/>
			<br/>
			密　　　码：<input type="password" name="user.psw"/>
			<br/>
			<br/>
			<input type="submit" value="登录"/>
		</form>
	</div>
	
</body>
</html>