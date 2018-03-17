<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- EL表达式的11个内置对象(除了JSP4大域) --%>
	<%--http://localhost:8080/JavaWebDemo/jsps/demo27_EL_param.jsp?username=zhangsan --%>
	${param.username}
	<%--http://localhost:8080/JavaWebDemo/jsps/demo27_EL_param.jsp?hobby=bed&&hobby=food --%>
	${paramValues.hobby[0] }
	${paramValues.hobby[1] }
	<%--
		不能${header.User-Agent} 因为-会被认为减号		
	 --%>
	 <br/>
	 ${header['User-Agent']}
	 <br/>
	 <%--得到sessionID的值 --%>
	 ${cookie.JSESSIONID.name }<%--JSESSIONID --%>	
	 ${cookie.JSESSIONID.value }<%-- 576E0EBACD779D0E698FF34BF46609DD --%>
	 <br/>
	 <%-- pageContext获取项目名(动态)
	 	以后这么写
	 	<a href="${pageContext.request.contextPath}/servlet/AServlet">点击这里</a>
	 	<form action="${pageContext.request.contextPath}/servlet/AServlet" method="POST">
	 	</form>
	 --%>
	 ${pageContext.request.contextPath}<%--http --%>
	 <br/>
	 <a href="${pageContext.request.contextPath}/jsps/demo26_EL_JBNavigator.jsp">点击这里</a>
	 
	 
</body>
</html>