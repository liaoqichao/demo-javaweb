<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<title>版本更新系统-查询所有客户端版本信息</title>
</head>
<body>
	<div class="title" align="center">
		<h1>客户端信息列表</h1>
	</div>
		<div align="right">
		<a href="<c:url value='/jsps/searchClient.jsp'/>">返回查询终端版本页面</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<c:url value='/index.jsp'/>">返回主页面</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${empty sessionScope.session_user }">
				<a href="<c:url value='/jsps/user/login.jsp'/>">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				用户：${sessionScope.session_user.username }&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<c:url value='/user/userAction_logout.action'/>">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
	</div>
	<br/>
	<br/>
	<table align="center" border="1px" cellspacing="0" width="700px">
	<c:if test="${fn:length(clientBeanList) ne 0 }">
		<tr >
			<td>客户端名称　　</td>
			<td>客户端IP　　　</td>
			<td>客户端主系统版本　　</td>
		</tr>
	</c:if>
		<c:forEach items="${clientBeanList }" var="clientBean">
			<tr>
				<td>${clientBean.clientname }</td>
				<td>${clientBean.ip }</td>
				<td>${clientBean._version.ver }</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<br/>
</body>
</html>