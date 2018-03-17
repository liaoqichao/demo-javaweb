<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载文件</title>
</head>
<body>

<form	action="<c:url value='/DownDraftFile'/>" method="post"  style="margin-left:250px;margin-top:25px;">
	<input type="text" id=documentNo name=documentNo  style="display:none;" value=<%= request.getParameter("documentNo")%>>
	<input type="submit" value="下载"/>
</form>
	
</body>
</html>