<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<title>版本更新系统-查询客户端版本信息</title>
</head>
<body>
<script type="text/javascript">
function createXMLHttpRequest(){
	try {
		return new XMLHttpRequest();	//支持大部分浏览器
	} catch (e) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");	//支持IE6.0浏览器
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft,XMLHTTP");	//支持IE5.5及以下浏览器
			} catch (e) {
				throw e;
			}
		}
	}
}

window.onload = function(){
	var session_user = document.getElementById("session_user");
	//1.获取文本元素和按钮元素
	var clientname = document.getElementById("clientname");
	var btn = document.getElementById("btn");
	
	var show_clientname = document.getElementById("show_clientname");
	var show_ip = document.getElementById("show_ip");
	var show_version = document.getElementById("show_version");
	var session_user_str = document.getElementById("session_user").value;// String类型
	var session_user_str = document.getElementById("session_user_permissions").value;// String类型
	//2.
	btn.onclick = function(){
		if(clientname.value.trim() == ""){
			alert("请输入客户端名");
			show_clientname.innerHTML = "";
			show_ip.innerHTML = "";
			show_version.innerHTML = "";
		} else{
			if(session_user.value.trim() == ""){
				alert("没有登录，请登录后再操作！");
			} else{
				if((session_user_str&1)==0){
					alert("您没有权限查看客户端版本号和版本信息");
				} else{
					//1.创建XMLHttpRequest
					var xmlHttp = createXMLHttpRequest();
					//2.xmlHttp.open
					xmlHttp.open("POST","<c:url value='/client/clientAction_single2.action'/>","true");
					//3.添加新一步,设置Content-Type
					xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					//4.xmlHttp.send。POST请求有参数
					xmlHttp.send("cb.clientname="+clientname.value);
					//5.给异步对象xmlHttp的onreadystatechange事件注册监听器
					xmlHttp.onreadystatechange = function(){
						//双重判断
						if(xmlHttp.readyState ==4 && xmlHttp.status == 200){
							//获取响应文本
							var data = xmlHttp.responseText;
							var jsonObj = eval("("+ data +")");
							if(jsonObj == null){
								show_clientname.innerHTML = "";
								show_ip.innerHTML = "";
								show_version.innerHTML = "";
								alert("没有查询到结果！");
							} else{
								show_clientname.innerHTML = "客户端名称：　"+jsonObj.clientname;
								show_ip.innerHTML = "客户端IP：　"+jsonObj.ip;
								show_version.innerHTML = "客户端主系统版本：　"+jsonObj._version.ver;
							}
						}
					};
				}
			}
		}
	};
	
	$('#btn-showlist').click(function(){
		if(session_user.value.trim() == ""){
			alert("没有登录，请登录后再操作！");
		} else{
			if(($('#session_user_permissions').val()&1) == 0){
				alert("您没有权限查看客户端版本号和版本信息");
			} else{
				window.location.href = "<c:url value='/client/clientAction_list.action'/>";
			}
		}
	});
};

</script>
	<div class="title" align="center">
		<h1>查询客户端版本信息</h1>
	</div>
	<input id="session_user" type="hidden" value="${sessionScope.session_user}">
	<input id="session_user_permissions" type="hidden" value="${sessionScope.session_user.permissions}">
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
	<div class="_subtitle">
		<h2>查询所有客户端版本信息</h2>
	</div>
	<div>
		<!-- 查询所有客户端 -->
		<button id="btn-showlist">查询所有客户端版本信息</button><br/>
<%-- 		<a href="<c:url value='/client/clientAction_list.action'/>">查询所有客户端版本信息</a><br/> --%>
	</div>
	<br/>
	
	<!-- 根据终端名称查询终端信息 -->
	<div class="_subtitle">
		<h2>根据客户端名称查询客户端版本信息</h2>
	</div>
		客户端名称：<input id="clientname" type="text" name="cb.clientname"/>　
		
		<!-- 
			改了这里
		 -->
<!-- 		<button id="btn" onclick="javaScript:search()">查询</button><br/> -->
		<button id="btn">查询</button><br/>
	<br/>
	<div id="show" style="height : 100px;">
		<div id="show_clientname"></div><br/>
		<div id="show_ip"></div><br/>
		<div id="show_version"></div><br/>
	</div>
	<br/>

</body>
</html>