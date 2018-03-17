<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>演示Cookie的maxAge属性</h1><br/>
	<h2>服务器保存Cookie到浏览器</h2>
	<%
		Cookie cookie1 = new Cookie("aaa","AAA");
// 		cookie1.setMaxAge(60*60*24);
// 		没设置maxAge时导出的Cookies文件看不到这个Cookie信息??
		cookie1.setMaxAge(0);//瞬间杀死
		response.addCookie(cookie1);
	%>
</body>
</html>