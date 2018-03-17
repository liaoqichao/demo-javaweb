<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>输入校验</h1>
	<s:fielderror/>
	<!-- /demo12/aaction_save
		/demo12/aaction_update
		/demo12/baction_save
		/demo12/baction_update
	 -->
	<form action="<c:url value='/demo12/baction_save'/>" method="post">
		<!-- 要求用户名不能为空 -->
		用户名：<input type="text" name="username"/><br/>
		<!-- 要求手机号不能为空，第一位为1，第二位为3,5,8，后面加9位数字 -->
		手机号：<input type="text" name="mobile"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>