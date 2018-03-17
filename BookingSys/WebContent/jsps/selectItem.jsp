<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/noPoint.css"/>
<script type="text/javascript" src="../js/toIndex.js"></script>
<script type="text/javascript">
window.onload = function(){
	//AJAX 4步，用ajaxutils.js,获取json格式字符串，循环创建元素，给元素的textNode和a标签的href属性赋值
	/*
		那么，href要这样的呢？用javaScript:openWinInd("服务id")? 这个函数再打开新的页面?
		点击超链接时要把服务事项id传到Servlet，Servlet再转发到read.jsp,同时read也要包含服务事项id信息
		或者使用jsp:forward标签
	*/
};

</script>
</head>
<body>
	<div align="center">
		<h1>这是选择预约服务事项页面</h1>
		<h2>请选择预约服务事项</h2>
		<div>预约服务事项</div>
		<ul>
			<li>
				<input type="text" value="如：限制房地产权利登记" onfocus="if(value=='如：限制房地产权利登记'){value=''; this.style.color='#999'}"
				onblur="if(!value){value='如：限制房地产权利登记'};this.style.color='#999'"
				style="color:#999"/>　　<!-- 这里有2个全角的空格 --><!-- 获得焦点事件和失去焦点事件 -->
				<input type="button" value="查询" onclick=""/><!-- 点击事件 -->
			</li>
<%-- 			<c:forEach items="${requestScope.itemClasses}" var="itemClass"><!--循环遍历服务的类型 ,没有提交请求，可能要使用js--> --%>
<%-- 				<div>${itemClass.name}</div> --%>
<!-- 				<ul> -->
<%-- 					<c:forEach items="${itemClass.itemList }" var="item"> --%>
<%-- 						<li><a href="<c:url value='/AServlet?method=selectItem&itemid=${item.id }'/>">${item.name }</a></li> --%>
<%-- 					</c:forEach> --%>
<!-- 				</ul> -->
<%-- 			</c:forEach> --%>
		</ul>
		<div align="center">
			<input type="button" value="返回" onclick="javascript:toIndex()"/>
		</div>
	</div>
</body>
</html>