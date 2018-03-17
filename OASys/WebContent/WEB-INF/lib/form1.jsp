<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>上传1</h1>
	<h3 style="color: red">${msg }</h3>
	<form action="<c:url value='/Upload1Servlet'/>"
		encType="multipart/form-data" method="POST">
		用户名：<input type="text" name="username" /><br /> 照 片：<input type="file"
			name="photo" /><br /> <input type="submit" value="上传" />
	</form>
</body>
</html>