<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<title>版本更新系统-指定更新</title>
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
	var versionSele = document.getElementById("versionSele");
	var clientSele = document.getElementById("clientSele");
	// 获取客户端列表
	//1.创建XMLHttpRequest
	var xmlHttp = createXMLHttpRequest();
	//2.xmlHttp.open
	xmlHttp.open("GET","<c:url value='/client/clientAction_list2.action'/>","true");
	//3.xmlHttp.send。
	xmlHttp.send(null);
	//4.给异步对象xmlHttp的onreadystatechange事件注册监听器
	xmlHttp.onreadystatechange = function(){
		//双重判断
		if(xmlHttp.readyState ==4 && xmlHttp.status == 200){
			//获取响应文本
			var data = xmlHttp.responseText;
// 			action不提供ver的setter，只返回version对象的json字符串
			var jsonObj = eval("("+ data +")");
// 			var clientSele = document.getElementById("clientSele");
			var option = document.createElement("option");//创建option标签
			for(var i=0 ; i< jsonObj.length ; i++){
				var option = document.createElement("option");//创建option标签
				option.value = jsonObj[i].id;//设置option的真实值
				var textNode = document.createTextNode(jsonObj[i].clientname);//创建显示值的节点
				option.appendChild(textNode);
				clientSele.appendChild(option);	//添加到select中
			}
		}
	};
	
	var xmlHttp1 = createXMLHttpRequest();
	//2.xmlHttp.open
	xmlHttp1.open("GET","<c:url value='/version/versionAction_listc.action'/>","true");
	//3.xmlHttp.send。
	xmlHttp1.send(null);
	//4.给异步对象xmlHttp的onreadystatechange事件注册监听器
	xmlHttp1.onreadystatechange = function(){
		//双重判断
		if(xmlHttp1.readyState ==4 && xmlHttp1.status == 200){
			//获取响应文本
			var data = xmlHttp1.responseText;
// 			action不提供ver的setter，只返回version对象的json字符串
			var jsonObj = eval("("+ data +")");
			// jsonObj有versionList和message
			var option = document.createElement("option");//创建option标签
			for(var i=0 ; i< jsonObj[0].length ; i++){
				var option = document.createElement("option");//创建option标签
				option.value = jsonObj[0][i].id;//设置option的真实值
				var textNode = document.createTextNode(jsonObj[0][i].ver);//创建显示值的节点
				option.appendChild(textNode);
				versionSele.appendChild(option);	//添加到select中
			}
		}
	};

	var btn = document.getElementById("btn");
	btn.onclick = function(){
		
		var session_user = document.getElementById("session_user");
		if(session_user.value.trim() == ""){
			alert("没有登录，请登录后再操作！");
		} else{
			if(($('#session_user_permissions').val()&4) == 0){
				alert("您没有权限选择客户端使用版本");
			} else{
				if(versionSele.value == "default" || clientSele.value == "default"){
					alert("请选择客户端或版本号！");
				} else{
					var xmlHttp2 = createXMLHttpRequest();
					//2.xmlHttp.open
					xmlHttp2.open("POST","<c:url value='/version/versionAction_specificUseVersion.action'/>","true");
					//3.添加新一步,设置Content-Type
					xmlHttp2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					//4.xmlHttp.send。
					xmlHttp2.send("vid="+versionSele.value+"&cid="+clientSele.value);
					//5.给异步对象xmlHttp的onreadystatechange事件注册监听器
					xmlHttp2.onreadystatechange = function(){
						//双重判断
						if(xmlHttp2.readyState ==4 && xmlHttp2.status == 200){
							//获取响应文本
							var data = xmlHttp2.responseText;
							var jsonObj = eval("("+ data +")");
							alert(jsonObj[1]);
						}
					};
				}
			}
		}
	}
};
</script>
</head>
<body>
	<div class="title" align="center">
		<h1>指定更新客户端主系统版本</h1>
	</div>
<input id="session_user_str" type="hidden" value="${sessionScope.session_user}">
<input id="session_user_permissions" type="hidden" value="${sessionScope.session_user.permissions}">
	<div align="right">
		<input id="session_user" type="hidden" name="session_user" value="${session_user.username }"/>
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
		选择客户端，再选择版本号，服务器就会记录下该客户端要更新到的版本号，然后客户端会自己更新。
	</div>
	<br/>
	<div id="operation" align="center">
		<span>请选择客户端：</span>
		<select id="clientSele" name="cid">
			<option value="default">---请选择---</option>
		</select>
		<br/>
		<br/>
		<span>请选择版本　：</span>
		<select id="versionSele" name="vid">
			<option value="default">---请选择---</option>
		</select>
		<br/>
		<br/>
		<button id="btn" name="btn">确认</button>
		<br/>
		<br/>
	</div>	

</body>
</html>