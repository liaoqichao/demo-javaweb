<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
	<%--循环遍历 显示的结果是乱序，因为数据库没有一列用来order by--%>
	<%--修改页面，要遍历的是pageBean的beanList集合 --%>
	<c:forEach items="${requestScope.pb.beanList }" var="customer">
		<tr>
			<td>${customer.cname }</td>
			<td>${customer.gender }</td>
			<td>${customer.birthday }</td>
			<td>${customer.cellphone }</td>
			<td>${customer.email }</td>
			<td>${customer.description }</td>
			<td>
				<a href="<c:url value='/CustomerServlet?method=beforeEdit&cid=${customer.cid }'/>">编辑</a>
				<a href="<c:url value='/CustomerServlet?method=delete&cid=${customer.cid }'/>">删除</a>
			</td>
		</tr>
	</c:forEach>

</table>
<br/>
<%--
	分页:给出分页相关的链接
 --%>
<center>
第${pb.pc }页/共${pb.tp }页
	<a href="${pb.url }&pc=1" >首页</a>
	<c:if test="${pb.pc >1 }">
	<%--也可以文字和超文本的切换 core标签库的哪个标签对应JAVA的else？ --%>
	<a href="${pb.url }&pc=${pb.pc-1 }" >上一页</a>
	</c:if>
	
	<%--计算begin和end --%>
	<c:choose>
		<%--如果总页数不足10页,那么把所有页数都显示出来 --%>
		<c:when test="${pb.tp <= 10 }">
			<c:set var="begin" value="1" /><%--还有scope属性，没给默认是page域 --%>
			<c:set var="end" value="${pb.tp }"/>
		</c:when>
		<c:otherwise>
			<%--总页数大于10时，通过公式计算 --%>
			<c:set var="begin" value="${pb.pc-5 }"/>
			<c:set var="end" value="${pb.pc+4 }"/>
			<%--头溢出 --%>
			<c:if test="${begin < 1 }">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="10"/>
			</c:if>
			<%--尾溢出 --%>
			<c:if test="${end > pb.tp }">
				<c:set var="end" value="${pb.tp }"/>
				<c:set var="begin" value="${pb.tp-9 }"/>
			</c:if>
		</c:otherwise>
	</c:choose>
	
	<%--循环显示页码列表 --%>
	<c:forEach var="i" begin="${begin }" end="${end }">
		<%-- 当前页不再显示超链接，而显示文本 --%>
		<c:choose>
			<c:when test="${i eq pb.pc }">
				[${i }]
			</c:when>
			<c:otherwise>
				<a href="${pb.url }&pc=${i}">[${i }]</a>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${pb.pc<pb.tp }">
	<a href="${pb.url }&pc=${pb.pc+1 }" >下一页</a>
	</c:if>
	<a href="${pb.url }&pc=${pb.tp }" >尾页</a>
</center>
  </body>
</html>
