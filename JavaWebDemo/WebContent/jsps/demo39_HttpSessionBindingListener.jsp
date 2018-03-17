<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 演示 HttpSessionBindingListener监听Demo39_User是否在session域中存在--%>
	<%
		domain.Demo39_User user = new domain.Demo39_User();
		session.setAttribute("user", user);
		session.removeAttribute("user");
	%>
</body>
</html>