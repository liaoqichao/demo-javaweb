<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	out.print("这里是jspinclude_b.jsp");
%>
<br/>
<%
	String username = (String)request.getParameter("username");
	String psw = (String)request.getParameter("psw");
	out.print(username+" : "+psw);
%>