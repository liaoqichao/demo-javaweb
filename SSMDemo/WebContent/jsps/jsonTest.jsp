<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
</head>
<body>
<script type="text/javascript">
$('document').ready(function(){
	$('#btn-requestJSON').click(function(){ // 请求json、响应json
		$.ajax({
			type:'post',
			url:'<c:url value="/requestJSON.action"/>',
// 			url:'<c:url value="/editItemSubmit_RequestJson.action"/>',
			contentType:'application/json;charset=utf-8',//指定请求数据的类型
			dataType:'json', // 返回格式为json
			// 数据格式是json串
			data:'{"name":"手机","price":"999"}',
			success:function(data){ // 返回json结果
				alert(data.price);
			}
		});
	});
	
	$('#btn-responseJSON').click(function(){ // 请求key/value、响应json
		$.ajax({
			// 不需要指定gcontentType
			type:'post',
			url:'<c:url value="/responseJSON.action"/>',
			data:'name=手机&price=999',
			dataType:'json',
			success:function(data){
				alert(data.name);
			}
		});
	});
	
});

</script>
<input type="button" id="btn-requestJSON" value="请求json、输出json"><!-- 请求json数据、处理响应的json数据 -->
<input type="button" id="btn-responseJSON" value="请求key/value、输出json"><!-- 请求key/value数据、处理响应的json数据 -->
</body>
</html>