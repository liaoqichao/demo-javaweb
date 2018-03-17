<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>获取session中的数据</h3>
	<%-- 
		session域存的数据浏览器保持不关闭，服务器关闭再开启后，仍然能读出session域中的数据，因为硬盘存储了sessions.ser的文件 
		在CATALINAHOME/work/Catalina/localhost/项目名/sessions.ser。
		但是eclipse没有。ser是serializable的简称。serializable：序列化
	--%>
	<%
		out.println(session.getAttribute("xxx"));
	%>
</body>
</html>