<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>演示&lt;jsp:include&gt;</h1>
	<h2>&lt;jsp:forward&gt;留头不留体</h2>
	<%--动态包含 --%>
	<%
		out.print("这里是jspinclude_a.jsp");
	%>
	<br/>
	<jsp:include page="demo23_jspinclude_b.jsp">
		<jsp:param value="zhangsan" name="username"/>
		<jsp:param value="123" name="psw"/>
	</jsp:include>
</body>
</html>