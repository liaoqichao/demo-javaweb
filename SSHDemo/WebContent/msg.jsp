<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>这里是/msg.jsp</H1>
	${requestScope.savePath }<br/>
	${requestScope.message }<br/><!-- 内容 -->
	${requestScope.msg }<br/><!-- 正确或错误的信息 -->
</body>
</html>