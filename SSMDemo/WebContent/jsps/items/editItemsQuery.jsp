<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<title>批量修改商品列表</title>
</head>
<body> 
<script type="text/javascript">
$('document').ready(function(){
	$('#btn_update').click(function(){ // 批量修改提交按钮
		$('#form').attr("action","<c:url value='/items/editItemsAllSubmit.action'/>");
		$('#form').submit();
	});
	$('#btn_search').click(function(){  // 查询按钮
		$('#form').attr("action","<c:url value='/items/queryItems.action'/>");
		$('#form').submit();
	});
});

</script>
<form id="form" action="${pageContext.request.contextPath }/items/queryItems.action" method="post">
查询条件：
<!-- 由于ItemsMapperCustom.xml中的查询条件只有价格和商品名称。所以页面只有两个查询条件。 -->
<table width="100%" border=1>
<tr>
<td>商品名称：<input type="text" name="itemsCustom.name"/></td>
<td>商品价格：<input type="text" name="itemsCustom.price"/></td>
<td><input id="btn_search" type="button" value="查询"/>&nbsp;
<input id="btn_update" type="button" value="批量修改提交"/></td>
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
<c:forEach items="${itemsCustomList }" var="itemsCustom" varStatus="status">
<tr>
	<td><input type="checkbox" name="items_id" value="${itemsCustom.id}"/></td>
	<td><input type="text" name="itemsCustomList[${status.index }].name" value="${itemsCustom.name }"/></td>
	<td><input type="text" name="itemsCustomList[${status.index }].price" value="${itemsCustom.price }"/></td>
	<td><input type="text" name="itemsCustomList[${status.index }].createtime" value="<fmt:formatDate value='${itemsCustom.createtime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/></td>
	<td><input type="text" name="itemsCustomList[${status.index }].detail" value="${itemsCustom.detail }"/></td>
	
</tr>
</c:forEach>

</table>
</form>
</body>

</html>