<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/ajax-lib/ajaxutils.js'/>"></script>
<script type="text/javascript">
window.onload = function(){
	var btn = document.getElementById("btn");
	btn.onclick = function(){
		/*
		ajax
		*/
		ajax({
				url:"<c:url value='/JsonServlet'/>" , type:"json",
				callback:function(data){
				var h3 = document.getElementById("h3");
				h3.innerHTML = data.name+", "+data.age+", "+data.sex;
				}
			});
	};
};

</script>
</head>
<body>
	<H1>演示自己封装的小工具/ajax-lib/ajaxutils.js</H1>
		<%--点击按钮后把服务器响应的数据显示到h3元素 --%>
	<button id="btn">点击这里</button>
	<h3 id="h3"></h3>
</body>
</html>