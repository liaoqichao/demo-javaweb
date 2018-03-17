<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 		//往往是Servlet里面放数据,JSP取数据,所以JSP通常只用EL就够了。 --%>
<%-- 		//JSP页面尽量少的出现JAVA代码 --%>
	<%
		Date date = new Date();
		request.setAttribute("date", date);
	%>
	<fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss"/>
	<hr/>
	<%
		request.setAttribute("num", 3.1415926);
		//要小数点2位,四舍五入。如果位数不够,会自动补。3.1->3.10
	%>
	<fmt:formatNumber value="${requestScope.num }" pattern="0.00"/>
	<%--0.00 和 #.##的区别：
		0.00会自动补位：3.1->3.10
		#.##不会自动补位：3.1->3.1
	 --%>
</body>
</html>