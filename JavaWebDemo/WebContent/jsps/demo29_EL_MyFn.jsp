<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="demo29" uri="/WEB-INF/tlds/demo29_myELFn.tld" %>
<%--这里不写网址,写真实的地址 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--调用自定义标签库 --%>
	<h1>${demo29:func() }</h1> 
</body>
</html>