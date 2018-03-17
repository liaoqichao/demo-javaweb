<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%--导入css --%>
<link rel="stylesheet" href="../css/noPoint.css" type="text/css"/><%--ul,ol,li前面都没有点 --%>
<link rel="stylesheet" href="../css/a.css" type="text/css"/><%--伪类选择器去使超链接的4个状态颜色相同 --%>
</head>
<body>
	<div align="center">
		<H1>预约系统主页面</H1>
		<h1>深圳市房地产网上预约大厅</h1>
		<div>
			<a href="<c:url value='/jsps/selectItem.jsp'/>">网上预约</a>　　
			<a href="<c:url value='/jsps/query.jsp'/>">预约查询</a>　　
			<a href="<c:url value='/jsps/cancel.jsp'/>">取消预约</a>　　
		</div>
		<br/>
		<div>
			<div><b>网上预约流程</b></div>
			<ul>
				<li>
					<img width="330px" src="../images/flow.png"/>
				</li>
				<li style="width: 1000px;">
					<br/>
					<span style="color:red">温馨提示：</span>
					预约成功后，系统会将预约流水号以短信方式发送至您的手机，同时预约成功页面也提供了“打印回执”功能。如您未收到短信且没有打印回执，请通过“预约查询”功能找回流水号并补打回执。
				</li>
			</ul>
		</div>
	</div>
</body>
</html>