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
		try{
			return new XMLHttpRequest();	//支持大多数浏览器
		}catch (e){
			try {
				return new ActiveXObject("Msxml2,XMLHTTP");	//支持IE6.0
			} catch (e) {
				try {
					return new ActiveXObject("Microsoft,XMLHTTP"); //支持IE5.5及以下
				} catch (e) {
					alert("你用什么浏览器???");
					throw e;
				}
			}
		}
	}
	<%-- 给按钮添加监听器 --%>
	window.onload = function(){ //文档加载完毕后执行
		var btn = document.getElementById("btn");
		btn.onclick = function(){	//给按钮的点击事件注册监听器
			/*
				四部操作，得到服务器响应，把响应结果显示到h1元素中
			*/
			//1.得到一步对象
			var xmlHttp = createXMLHttpRequest();
			//2.打开与服务器的连接
			xmlHttp.open("GET","<c:url value='/AServlet'/>","true");//请求方式,请求url，是否为异步请求
			//3.发送请求
			xmlHttp.send(null);//get请求没有请求体但也要给出null，不然firefox可能不能发送
			//4.给异步对象的onreadystatechange事件注册监听器。
			xmlHttp.onreadystatechange = function(){	//当xmlHttp状态变化时执行
				if(xmlHttp.readyState ==4 && xmlHttp.status == 200){	
					//双重判断。xmlhttp状态为4表示服务器响应结束，服务器响应的状态码为200
					//获取响应的文本(数据)
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