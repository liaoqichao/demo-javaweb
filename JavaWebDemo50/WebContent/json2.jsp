<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
window.onload =function(){
	//1.获取btn元素
	var btn = document.getElementById("btn");
	btn.onclick = function(){
		//ajax的4步
		//1.
		var xmlHttp = createXMLHttpRequest();
		//2.
		xmlHttp.open("GET","<c:url value='/JsonServlet'/>",true);
		//3.
		xmlHttp.send(null);
		//4.
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState ==4 && xmlHttp.status==200){
				var text = xmlHttp.responseText;//它是一个json串
				//执行json传
				var person = eval("("+ text +")");
				var s = person.name +", "+person.age+", "+person.sex;
				var h3 = document.getElementById("h3");
				h3.innerHTML = s;
			}
		};
	};
	
};

</script>
</head>
<body>
	<H1>json之hello world</H1>
	<%--点击按钮后把服务器响应的数据显示到h3元素 --%>
	<button id="btn">点击这里</button>
	<h3 id="h3"></h3>
</body>
</html>