<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>demo16 演示JSP中JAVA代码的使用</title>
</head>
<body>
	<table border="1px" cellspacing="0" align="center" width="60%">
		<tr>
			<td>姓名</td>
			<td>年龄</td>
		</tr>
		<% for(int i = 0 ; i < 10 ; i++){ %>
<!-- 			下面这些内容就变成循环体了 -->
			<tr>
				<td>张三</td>
				<td>29</td>
			</tr>
		<% } %>
	</table>
</body>
</html>