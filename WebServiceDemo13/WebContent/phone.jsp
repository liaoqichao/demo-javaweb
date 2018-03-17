<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	$('#find').click(function(){
		$.ajax({
			url:'<c:url value="/PhoneServlet"/>',
			type:'post',
			data:{num:$('#num').val()},
			success:function(data,status,xmlHttpRequest){
				$('#show').text(data);
			},
			error:function(){
				alert("error");
			}
			
		});
	});
});

</script>
	<H1>获取手机号码归属地</H1>
	手机号码:<input type="text" id="num"/>&nbsp;至少前7为数字<br/>
	<button id="find">查询</button>
	<div id="show"></div>
</body>
</html>