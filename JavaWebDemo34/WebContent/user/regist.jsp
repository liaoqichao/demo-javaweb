<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
<%--如果一个表单项的name属相和<img>的ID属性的名字相同,那么
	var oImg = document.getElementById("vCode");得到第一个元素可能会是表单的name。
	IE会把表单的name和其他的ID弄混。
--%>
	function _change(){
		var oImg = document.getElementById("vCode");
		oImg.src = "${pageContext.request.contextPath }/VerifyCodeServlet";
// 		oImg.src = "${pageContext.request.contextPath }/VerifyCodeServlet?xxx="+new Date().getTime();;
		
	}

</script>
	<h1>注册</h1>
<!-- 	<h1><font color="red">注册功能没有完成功能：发送激活码到邮箱,邮箱点击超链接完成激活,才能登陆</font></h1> -->
	<p style="color: red;font-weight: 900">${msg }</p>
	<%--
		导包jstl-1.2.jar
		<%@ taglib prefix="c" url="..."/>
		<c:url value='/RegistServlet'/>  ==${pageContext.request.contextPath }/RegistServlet
	--%>
	<form action="${pageContext.request.contextPath }/RegistServlet" method="POST">
		用户名：<input type="text" name="username" value="${requestScope.user.username }"/>${errors.username }<br/>
		<%--El表达式特点,没有是空字符串,不是null --%>
		密　码：<input type="password" name="password" value="${requestScope.user.password }"/>${errors.password }<br/>
		状　态：<input type="text" name="status" value="${requestScope.user.status }"/>${errors.status }<br/>
		验证码：<input type="text" name="verifyCode" value="${requestScope.user.verifyCode }" size="3" border="1"/>
		<img id="vCode" src="${pageContext.request.contextPath }/VerifyCodeServlet"/>
		<a href="javaScript:_change()">看不清？换一张</a>${errors.verifyCode }<br/>
		<input type="submit" value="注册"/><br/>
	</form>
</body>
</html>