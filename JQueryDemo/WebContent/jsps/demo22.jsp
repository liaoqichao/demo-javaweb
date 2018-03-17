<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<title>Insert title here</title>
<style type="text/css">
		 	div,span{
			    width: 140px;
			    height: 140px;
			    margin: 20px;
			    background: #9999CC;
			    border: #000 1px solid;
				float:left;
			    font-size: 17px;
			    font-family:Roman;
			}
			
			div.mini{
			    width: 30px;
			    height: 30px;
			    background: #CC66FF;
			    border: #000 1px solid;
			    font-size: 12px;
			    font-family:Roman;
			}
			
			div.visible{
				display:none;
			}
	 </style>
</head>
<body>
	<H1>jQuery整合ajax</H1>
	<form action="" name="form1" id="form1">
			<input type="text" name="username" id="username" value="zhang"><br>
			<input type="text" name="psw" id="psw" value="99999"><br>
	        <input type="button" id="b1" value="登陆(返回数据格式：JSON)">
	        <input type="button" id="b2" value="登陆(返回数据格式：String)">
	        <input type="button" id="b3" value="登陆(返回数据格式：XML)">
	        <input type="button" id="b4" value="登陆(使用$.post()，get不写，类似，都是$.ajax()的简化，$.ajax()前面第一个按钮代码里面有)">
		</form>
	
		<div id="one">		
		</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#b1').click(function(){
		// jQuery对象.load,其中jQuery对象表示获取返回信息的对象
		/*
			第一个参数：请求URL
			第二个参数表示是否发送数据。null表示为不发送数据
		*/ 
// 		$('#one').load("<c:url value='/demo22/demo22Action_returnJSON'/>",
// 				{"username":$('#username').val(),"psw":$('#psw').val()},
// 				function(data,textStatus,xmlHttpRequest){
// 					var jsonObj = eval("("+data+")");
// 					alert("服务器返回:"+jsonObj);
// 		});
		
		// $.ajax(options)
		$.ajax({
			type:"POST",url:"<c:url value='/demo22/demo22Action_returnJSON'/>",
			data:{"username":$('#username').val(),"psw":$('#psw').val()},
			dataType:"XML",
			/*
				如果dataType:"text" 返回["用户名可用"]
				如果dataType:"JSON",返回用户名可用
			*/
			// json 或者字符串
			success:function(msg){
				alert("Date Received:" + msg);
			}
			// xml
// 			success:function(msg){
// 				alert("Date Received:" + $(msg).children().eq(0).text()); // 用户名可用
// 			}
		});
	});
	
	$('#b2').click(function(){
		$('#one').load("<c:url value='/demo22/demo22Action_returnString'/>",
				{"username":$('#username').val(),"psw":$('#psw').val()},
				function(data,textStatus,xmlHttpRequest){
					alert("服务器返回:"+data);
		});
	});
	$('#b3').click(function(){
		$('#one').load("<c:url value='/demo22/demo22Action_returnXml'/>",
				{"username":$('#username').val(),"psw":$('#psw').val()},
				function(data,textStatus,xmlHttpRequest){
					var xmlObjs = xmlHttpRequest.responseXML;
					var message = xmlObjs.getElementsByTagName("message");
					alert(message.innerHTML);
					
		});
	});
	
	$('#b4').click(function(){
		// $.post返回的是xmlHttpRequest对象
		var objXml = $.post("<c:url value='/demo22/demo22Action_returnJSON'/>",
				{"username":$('#username').val(),"psw":$('#psw').val()},function(data,textStatus){
					/*
						data是字符串
						var obj = eval("("+data+")");  是DOM对象
						$(obj) 是jQuery对象
					*/
					alert(data); // 返回的是文本
				}).responseXML;
		var message = objXml.getElementsByTagName("message")[0];
		alert(message.innerHTML);
	});
});

</script>
</html>