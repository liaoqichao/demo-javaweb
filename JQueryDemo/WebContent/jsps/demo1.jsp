<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<script type="text/javascript">
// 这个方法相当于window.onload
// $(document)是一个jQuery对象。document是一个dom对象。如果是jQuery对象，只能使用jQuery库提供的方法，如果是dom对象只能使用js本身的方法
$(document).ready(function() { 
	// put all your jQuery goodness in here. 
	alert("Hello jQuery!");
	}); 
</script>
</head>
<body>
	<h1>demo1</h1>
</body>
</html>