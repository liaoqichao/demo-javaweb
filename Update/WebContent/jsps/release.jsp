<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<title>版本更新系统-发布更新</title>
</head>
<body>
<script type="text/javascript">
function _addFile(){
	/*
	注意：table有隐藏标签tbody
	1.创建一个tr
	2.在tr创建两个td子标签
	3.第一个td创建input type=file子标签
	4.第二个td创建span子标签和input type=text子标签。span标签添加内容
	5.把tr标签添加到table标签
	*/
	//1. 创建tr
	var trEle = document.createElement("tr");
	//2. 创建td1
	var td1Ele = document.createElement("td"); // 存放input type=file
	var inputEle = document.createElement("input");
	inputEle.setAttribute("type","file");
	inputEle.setAttribute("name","file");
	//2-1 给td1添加字标签
	td1Ele.appendChild(inputEle);
	
	//3. 创建td2
	var td2Ele = document.createElement("td"); // 存在span和input type=text
	
	//3-1. 创建input
	var inputEle1 = document.createElement("input");
	inputEle1.setAttribute("type","text");
	inputEle1.setAttribute("name","path");
	//3-2. 创建span
	var spanEle = document.createElement("span");
	spanEle.innerHTML = "目标路径：";
	
	//3-3. 给td2添加子标签
	td2Ele.appendChild(spanEle);
	td2Ele.appendChild(inputEle1);
	
	//4. 给tr加入子标签td2和td1
	trEle.appendChild(td1Ele);
	trEle.appendChild(td2Ele);
	
	//5. 给table加入子标签tr
	var tabEle = document.getElementById("table");
	var tbodyEle = tabEle.children[0];
	tbodyEle.insertBefore(trEle,tbodyEle.children[3]);
	
}
window.onload = function(){
	var message = document.getElementById("message");
	if(message.value != ""){
		alert(message.value);
	}
};
</script>
<div>
	<h1 class="title" align="center">发布更新</h1>
</div>

	<div align="right">
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
	<div id="description">
		填写版本号，如果版本号不存在，则新建版本，如果版本号存在，则在原来的版本上追加新的更新文件。查看版本信息的时候发布时间为最新更新的时间。更新描述为所有更新描述（没有换行）。<br/>
	</div>
	<input type="hidden" id="message" value="${message }"/>
<%-- 	${message } --%>
	<form id="form" action="<c:url value='/verUpdate/verUpdateAction_release.action'/>" method="post" encType="multipart/form-data">
		<table align="center" id="table">
			<tr>
				<td><span>版本号　：</span></td>
				<td><input type="text" name="version.ver"/></td>
			</tr>
			<tr>
				<td><span>版本描述：</span></td>
				<td><textarea rows="5" cols="50" name="version.description"></textarea></td>
			</tr>
			<tr>
				<td><input id="file" type="file" name="file"/></td>
				<td><span>目标路径：</span><input type="text" name="path"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br/>
					<input type="button" value="添加上传文件" onclick="javaScript:_addFile()"/>
					<input type="submit" value="发布"/>
				</td>
			</tr>
		</table>
	</form>
	<br/>
</body>
</html>