<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>demo10</h1>
	<form action="<c:url value='/demo10/aaction_test1'/>" method="post" enctype="multipart/form-data">
		用户名：<input type="text" name="username"/><br/><!-- 这里action不设置其属性，就是不接受它的数据 -->
		图　片：<input type="file" name="uploadFile"/><br/><!-- action的属性名必须和这里的name的值一样 -->
		<input type="submit" value="提交"> 
	</form>
	<div>
		${requestScope.msg }
	</div>
</body>
</html>