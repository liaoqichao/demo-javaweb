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
	<H1>防止表单重复提交</H1>
	<!-- struts的表单标签就可以不用jstl标签来补全上下文路径（项目名），直接指定struts.xml中的action就可以，namespace是用来指定
		是在哪个包的。
	 -->
	<s:form action="aaction_test1" namespace="/demo15" method="post">
		用户名：<s:textfield name="username"/><s:token/><br/>
		密　码：<s:password name="password"/><br/>
		<s:token/><!-- 一个表单一个token标签就够 -->
<%-- 		<s:submit></s:submit> 这个标签要在服务器端解析，会影响性能，所以没必要用 --%>
		<input type="submit" value="提交"><br/>
	</s:form>
</body>
</html>