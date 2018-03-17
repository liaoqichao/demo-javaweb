<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<title>查询商品列表</title>
</head>
<body> 
<script type="text/javascript">
$('document').ready(function(){
	$('#btn_delete').click(function(){ // 删除按钮
		$('#form').attr("action","<c:url value='/items/deleteItems.action'/>");
		$('#form').submit();
	});
	$('#btn_search').click(function(){  // 查询按钮
		$('#form').attr("action","<c:url value='/items/queryItems.action'/>");
		$('#form').submit();
	});
});

</script>
<c:choose>
<c:when test="${not empty sessionScope.session_username }">
当前用户:${sessionScope.session_username } <a href="<c:url value='/login/logout.action'/>">退出</a>
</c:when>
<c:otherwise>
	<a href="<c:url value='/login/login.action'/>">登录</a>
</c:otherwise>
</c:choose>
<form id="form" action="${pageContext.request.contextPath }/items/queryItems.action" method="post">
查询条件：
<!-- 由于ItemsMapperCustom.xml中的查询条件只有价格和商品名称。所以页面只有两个查询条件。 -->
<table width="100%" border=1>
<tr>
<td>商品名称：<input type="text" name="itemsCustom.name"/></td>
<td>
商品类型：
<select>
<c:forEach items="${itemsTypeMap}" var="itemsType">
		<option value="${itemsType.key}">${itemsType.value}</option>
</c:forEach>
</select>
</td>
<td>商品价格：<input type="text" name="itemsCustom.price"/></td>
<td><input id="btn_search" type="button" value="查询"/> <input id="btn_delete" type="button" value="批量删除"/></td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>选择</td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemsCustomList }" var="itemsCustom">
<tr>
	<td><input type="checkbox" name="items_id" value="${itemsCustom.id}"/></td>
	<td>${itemsCustom.name }</td>
	<td>${itemsCustom.price }</td>
	<td><fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${itemsCustom.detail }</td>
	
	<td><a href="${pageContext.request.contextPath }/items/editItems.action?id=${itemsCustom.id}">修改</a></td>

</tr>
</c:forEach>

</table>
</form>
</body>

</html>