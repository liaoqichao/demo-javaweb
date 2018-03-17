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
<title>版本更新系统-查看版本信息</title>
</head>
<body>
<script type="text/javascript">
window.onload = function(){
	
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
	function selectVersion(){
		var selEle = document.getElementById("idversion"); //得到select标签
		//1.创建XMLHttpRequest
		var xmlHttp = createXMLHttpRequest();
		//2.xmlHttp.open
		xmlHttp.open("POST","<c:url value='/versionjson/versionJSONAction_versionBean.action'/>","true");
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
				var jsonObj = eval("("+ data +")");
				//把响应文本输出到show元素
				// struts.xml的result配置<param name="root">version</param>
				// 那么返回的就只有version的JSON串，而不是JSON的对象数组串
				var verid = document.getElementById("verid");
	// 			verid.innerHTML = "版本号："+jsonObj.version.ver;
				verid.innerHTML = "版本号："+jsonObj.ver;
				var vertime = document.getElementById("vertime");
	// 			vertime.innerHTML = "发布时间："+jsonObj.version.time;
				vertime.innerHTML = "发布时间："+jsonObj.time;
				var verdescription = document.getElementById("verdescription");
	// 			verdescription.innerHTML = "更新描述："+jsonObj.version.description;
				verdescription.innerHTML = "更新描述："+jsonObj.description;
				//这里的data有clientbean。数据库关系内弄好
	//			返回的data包括action全部属性，包括没有JSON注释的
	// 			show.innerHTML = data.version+","+data.time+","+data.description
	// 			show.innerHTML = data.version.ver+","+data.version.time+","+data.version.description;
			}
		};
	}
	
	$('#idversion').change(function(){
		var session_user = document.getElementById("session_user");
		if(session_user.value.trim() == ""){
			alert("没有登录，请登录后再操作！");
		} else{
			if(($('#session_user_permissions').val()&1) == 0){
				alert("您没有权限查看客户端版本号和版本信息");
			} else{
				selectVersion();
			}
		}
	});
};

</script>
	<div class="title" align="center">
		<H1>查看版本信息</H1>
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
	<div id="description">
		请选择要查询的版本号。显示版本号，发布时间和更新描述。
	</div>
	<br/>
<!-- 	<select name="nameversion" id="idversion" onchange="javaScript:selectVersion()"> -->
	<select name="nameversion" id="idversion">
			<option>---请选择---</option>		
		<c:forEach items="${versionList }" var="version">
			<option value="${version.ver }">${version.ver }</option>		
		</c:forEach>
	</select>
	<br/>
	<br/>
	<div id="verid"></div><br/>
	<div id="vertime"></div><br/>
	<div id="verdescription"></div><br/><br/>
</body>
</html>