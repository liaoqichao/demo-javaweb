<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>版本更新系统</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ul.css'/>"/>
</head>
<body>
	<div align="center" class="title">
		<h1>版本更新系统主页面</h1>
	</div>
	<div align="right">
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
	<div>
		<ul>
		<!-- 不用form表单上传，使用uploadify上传 -->
<!-- 			<li> -->
<%-- 				<a href="<c:url value='/jsps/release.jsp'/>">发布更新</a> ---做完<br/> --%>
<!-- 			</li> -->
			<li>
				<br/>
			</li>
			<li>
				<a href="<c:url value='/jsps/release_uploadify.jsp'/>">发布更新版本</a> <br/>
			</li>
			<li>
				<br/>
			</li>
			<li>
				<a href="<c:url value='/version/versionAction_list2.action'/>">选择所有客户端使用的主系统版本</a>  <br/>
			</li>
			<li>
				<br/>
			</li>
			<li>
				<a href="<c:url value='/jsps/searchClient.jsp'/>">查询客户端版本</a> <br/>
			</li>
			<li>
				<br/>
			</li>
			<li>
				<!-- 查看版本信息应该指向action，action返回的视图带versionList的信息 -->
				<a href="<c:url value='/version/versionAction_list.action'/>">查看版本信息</a> <br/>
			</li>
			<li>
				<br/>
			</li>
			<li>
				<a href="<c:url value='/jsps/specificUpdate.jsp'/>">指定更新客户端主系统版本</a> <br/>
				<br/>
			</li>
		</ul>
	</div>
</body>
</html>