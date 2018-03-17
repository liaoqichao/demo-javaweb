<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>版本更新系统-选择使用版本</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
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

function isLogin(){
	var session_user = document.getElementById("session_user");
	if(session_user.value.trim() == ""){
		alert("没有登录，请登录后再操作！");
	} else{
		if(($('#session_user_permissions').val()&4) == 0){
			alert("您没有权限选择客户端使用版本");
		} else{
			isVersion();
		}
	}
}

function isVersion (){
	var selEle = document.getElementById("idversion");
	var error = document.getElementById("error");
	if(selEle.value != "---请选择---"){
		useVersion();
	} else{
		alert("请选择版本！");
	}
}
function useVersion(){
	/*
	 	一.获取idversion的值
	 	二.ajax提交到action
	 */
	//一.
	var selEle = document.getElementById("idversion"); //得到select标签
	//二.
	//1.创建XMLHttpRequest
	var xmlHttp = createXMLHttpRequest();
	//2.xmlHttp.open
	xmlHttp.open("POST","<c:url value='/versionjson/versionJSONAction_useVersion.action'/>","true");
	//3.添加新一步,设置Content-Type
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//4.xmlHttp.send。POST请求有参数
	xmlHttp.send("ver="+selEle.value);
	//5.给异步对象xmlHttp的onreadystatechange事件注册监听器
	xmlHttp.onreadystatechange = function(){
		//双重判断
		if(xmlHttp.readyState ==4 && xmlHttp.status == 200){
			//获取响应文本
			var data = xmlHttp.responseText;
			//执行json传.获得的是ver字符串和Version对象
			//action不提供ver的setter，只返回version对象的json字符串
			// <param name=root>""</param>表示全部都返回?
			var jsonObj = eval("("+ data +")");
			//把响应文本输出到show元素
			alert(jsonObj[0]);
		}
	};
}

function selectVersion(){
	var selEle = document.getElementById("idversion"); //得到select标签
	var verid = document.getElementById("verid");
	var vertime = document.getElementById("vertime");
	var verdescription = document.getElementById("verdescription");
	if(selEle.value=="default"){
		verid.innerHTML = "";
		vertime.innerHTML = "";
		verdescription.innerHTML = "";
	} else{
		//1.创建XMLHttpRequest
		var xmlHttp1 = createXMLHttpRequest();
		//2.xmlHttp.open
		xmlHttp1.open("POST","<c:url value='/versionjson/versionJSONAction_versionBean2.action'/>","true");
		//3.添加新一步,设置Content-Type
		xmlHttp1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//4.xmlHttp.send。POST请求有参数
		xmlHttp1.send("ver="+selEle.value);
		//5.给异步对象xmlHttp的onreadystatechange事件注册监听器
		xmlHttp1.onreadystatechange = function(){
			//双重判断
			if(xmlHttp1.readyState ==4 && xmlHttp1.status == 200){
				//获取响应文本
				var data = xmlHttp1.responseText;
				//执行json传.获得的是ver字符串和Version对象
				//action不提供ver的setter，只返回version对象的json字符串
				var jsonObj1 = eval("("+ data +")");
				//把响应文本输出到show元素
// 				var verid = document.getElementById("verid");
				verid.innerHTML = "版本号："+jsonObj1[1].ver;
// 				var vertime = document.getElementById("vertime");
				vertime.innerHTML = "发布时间："+jsonObj1[1].time;
// 				var verdescription = document.getElementById("verdescription");
				verdescription.innerHTML = "更新描述："+jsonObj1[1].description;
			}
		};
	}
}
</script>
<div class="title" align="center">
	<h1>选择所有客户端使用的主系统版本</h1>
</div>
<input id="session_user_str" type="hidden" value="${sessionScope.session_user}">
<input id="session_user_permissions" type="hidden" value="${sessionScope.session_user.permissions}">
<div align="right">
	<a href="<c:url value='/index.jsp'/>">返回主页面</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="session_user" type="hidden" name="session_user" value="${session_user.username }"/>
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
	<div id="description">选择使用版本后，会修改服务器的配置文件，最终所有客户端的主系统版本都会更新到指定版本。</div>
	<br/>
	<div align="center">
		<select name="nameversion" id="idversion" onchange="javaScript:selectVersion()">
			<option value="default">---请选择---</option>		
			<c:forEach items="${versionList }" var="version">
				<option value="${version.ver }">${version.ver }</option>		
			</c:forEach>
		</select>
		<input id="btn" type="button" name="btn" onclick="javaScript:isLogin()" value="确认使用"/>
		<span id="error"></span>
		<br/>
	</div>
	<br/>
	<div id="showVersion" align="center" style="height:350px;">
		<div id="verid" align="center"></div><br/>
		<div id="vertime" align="center"></div><br/>
		<div id="verdescription" align="center"></div><br/>
	</div>
</body>
</html>