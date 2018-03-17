<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	//获取btn元素
	var btn = document.getElementById("btn");
	//给btn添加点击事件
	btn.onclick = function(){
		//1.创建XMLHttpRequest
		var xmlHttp = createXMLHttpRequest();
		//2.xmlHttp.open
		xmlHttp.open("POST","<c:url value='/AServlet'/>","true");
		//3.添加新一步,设置Content-Type
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//4.xmlHttp.send。POST请求有参数
		xmlHttp.send("username=张三&password=123");
		//5.给异步对象xmlHttp的onreadystatechange事件注册监听器
		xmlHttp.onreadystatechange = function(){
			//双重判断
			if(xmlHttp.readyState ==4 && xmlHttp.status == 200){
				//获取响应文本
				var text = xmlHttp.responseText;
				//获取h1元素
				var h1 = document.getElementById("h1");
				//把响应文本输出到h1元素
				h1.innerHTML = text;
			}
		};
	};
};

</script>
</head>
<body>
	<button id="btn">点击这里 </button>
	<h1 id="h1"></h1>
</body>
</html>