<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//创建异步对象
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
	window.onload = function(){	//没事的话第一步都写它。
		/*
			给文本框的失去焦点事件注册监听器。
			1.得到文本框元素
			2.把这个元素的onblur注册监听器
		*/
		//1. 
		var userEle = document.getElementById("usernameEle");
		//2.
		userEle.onblur = function(){
			//a.得到异步对象
			var xmlHttp = createXMLHttpRequest();
			//b.打开连接
			xmlHttp.open("POST","<c:url value='/ValidateUsernameServlet'/>",true);
			//c.设置ContentType请求头
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//d.发送请求,给出请求体
			xmlHttp.send("username="+userEle.value);
			//e.给异步对象xmlHttp的onreadystatechange事件注册监听器
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200){//readyState=4,服务器响应结束状态
					//这里已经是服务器响应结束的状态,所以获取服务器的响应，
					var text = xmlHttp.responseText;
					//如果响应是1，获取span，添加内容，"用户名已经注册"
					var errorNameSpan = document.getElementById("errorNameSpan");
					if(text == "1"){
						errorNameSpan.innerHTML = "用户名已经被注册";
					} else{
						errorNameSpan.innerHTML = "";
					}
					//如果不是1，不操作
				}
			};
		};
		
	};


</script>
</head>
<body>
	<H1>演示用户名是否被注册</H1>
	<form action="" method="post" >
		用户名：<input type="text" name="username" id="usernameEle"/><span id="errorNameSpan"></span><br/>
		密　码：<input type="password" name="password"/><span id="errorPasswordSpan"></span><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>