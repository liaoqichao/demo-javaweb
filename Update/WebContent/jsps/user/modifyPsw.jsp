<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
</head>
<body>
	<H1 class="title" align="center">修改密码</H1>
	<br/>
	<div id="modifyMsg" align="center">
		<font color="red">${modifyPswMsg }</font><br/><br/>
	</div>
	<form action="<c:url value='/user/userAction_modifyPsw.action'/>" method="POST">
		<table align="center">
			<tr>
				<td>
					用户名：
				</td>
				<td>
<!-- 					<input type="text" name="user.username"/> -->
					<s:textfield required="true" name="user.username"/>
				</td>
			</tr>
			<tr>
				<td>
					旧密码：
				</td>
				<td>
<!-- 					<input type="password" name="user.psw"/> -->
					<s:password required="true" name="user.psw"/>
				</td>
			</tr>
			<tr>
				<td>
					新密码：
				</td>
				<td>
<!-- 					<input type="password" name="new_password"/> -->
					<s:password required="true" name="new_password"/>
				</td>
			</tr>
			<tr>
				<td>
					重新输入新密码：
				</td>
				<td>
<!-- 					<input type="password" name="rpt_new_password"/> -->
					<s:password required="true" name="rpt_new_password"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="提交"/>
				</td>
				<td>
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>