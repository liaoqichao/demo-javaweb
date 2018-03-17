<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
<%-- 参数bar1： 和声明bar1必须相同
ITCAST网络图书商城：大标题
--%>
var bar1 = new Q6MenuBar("bar1", "爪哇网络图书商城");
function load() {
	bar1.colorStyle = 2;//设置配色方案。配色方案一共4种
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";//指定图片目录，就是图书管理、分类管理、订单管理前面的加号和减号，背景色白色，中间的加和减是透明色
	bar1.config.radioButton=false;//菜单之间是否相互排斥。排斥：图书管理、分类管理、订单管理只能有一个菜单可以打开。
	/*
	分类管理:指定要添加的菜单的名称（如果这个名称的菜单已经存在，不会重复添加）
	查看分类：指定要添加的菜单项名称(菜单项：打开菜单后显示的选项)
	<c:url value='/adminjsps/admin/category/list.jsp'/>：点击菜单项时要请求的地址。
	body：结果显示的框架页名称。就是body.jsp
	*/
	bar1.add("分类管理", "查看分类", "<c:url value='/admin/AdminCategoryServlet?method=findAll'/>", "body");
	bar1.add("分类管理", "添加分类", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	bar1.add("图书管理", "查看图书", "<c:url value='/admin/AdminBookServlet?method=findAll'/>", "body");
	bar1.add("图书管理", "添加图书", "<c:url value='/admin/AdminBookServlet?method=addPre'/>", "body");

	bar1.add("订单管理", "所有订单", "<c:url value='/admin/AdminOrderServlet?method=findAll'/>", "body");
	bar1.add("订单管理", "未付款订单", "<c:url value='/admin/AdminOrderServlet?method=findByState&state=1'/>", "body");
	bar1.add("订单管理", "已付款订单", "<c:url value='/admin/AdminOrderServlet?method=findByState&state=2'/>", "body");
	bar1.add("订单管理", "未收货订单", "<c:url value='/admin/AdminOrderServlet?method=findByState&state=3'/>", "body");
	bar1.add("订单管理", "已完成订单", "<c:url value='/admin/AdminOrderServlet?method=findByState&state=4'/>", "body");

	var d = document.getElementById("menu");//获取div元素
	d.innerHTML = bar1.toString();//把菜单对象转换成字符串，赋值给div元素的内容
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
