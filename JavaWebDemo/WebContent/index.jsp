<!-- 它是JSP指令,也是一种特殊额标签 -->
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello world!</title>
</head>
<body>
	<%
	Date date = new Date();
	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	%>
	Now is <%out.print(sdFormatter.format(date)); %>
	
	<!-- 这里的out.print()等于<%="显示带页面的内容" %> 。这句话会执行,但是被html注释掉,浏览器不显示-->
	<%-- <%="显示的内容"%> 这句话不执行！因为是用JSP的注释,不是用HTML的注释 --%>
	
	<%-- 
		这里是JSP注释,编译成.java文件不会有out.write("注释内容").而用html的注释会出现
		out.write("<!-- 注释内容-->");
	 --%>
	<!-- 下面演示JSP中3中使用JAVA代码的方法 -->
	<%
		int a = 10;
	%>
	<br/>
	<%= a 
// 			输出10;
	%>
	<br/>
	<% 
		out.println(a++);
// 		输出10
	%>
	<%!	
// 		定义成员,这种方式只学不用。！！！
		int a = 100;
		public void func(){
			System.out.println("你好");
// 			怎么解决乱码?charset=UTF-8
		}
	%>
	<%
		out.println(a++);
// 		局部变量,输出11;
		out.println(this.a++);
// 		输出的是成员变量a=100;第二次访问页面a=101..每次访问JSP的对象是同一个
// 		局部变量作用域完了就gc();
		func();
	%>
	
	<%--demo38 Listener --%>
	<%
		application.setAttribute("xxx", "abc");//调用attributeAdded
		application.setAttribute("xxx", "123");//调用attributeReplaced
		application.removeAttribute("xxx");
	%>
</body>
</html>