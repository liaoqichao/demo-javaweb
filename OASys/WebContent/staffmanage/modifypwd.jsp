<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<style>
.comfirm_submit {
	position: relative;
	float: left;
	width: 100%;
	padding-bottom: 0px;
	clear: both;
	font-size: 14px;
}

.comfirm_button {
	background: rgb(90, 152, 222);
	padding: 0px 20px;
	border-radius: 3px;
	border: currentColor;
	border-image: none;
	width: 185px;
	height: 40px;
	text-align: center;
	color: rgb(255, 255, 255);
	line-height: 38px;
	font-size: 16px;
	font-weight: normal;
	margin-top: 10px;
	margin-left: 35px;
	margin-bottom: 0px;
	display: inline-block;
	cursor: pointer;
	box-sizing: border-box;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

.comfirm_button:hover {
	background: rgb(106, 162, 224);
	text-decoration: none;
}

.comfirm_button:focus {
	background: rgb(106, 162, 224);
	text-decoration: none;
}

.comfirm_button:active {
	background: rgb(90, 152, 222);
}
.login_box_msg {
	margin: 12px 25px -4px;
	height: 30px;
	text-align: left;
	font-size:12px;
	color: blue;
}
</style>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script src="js/jquery-1.11.2.js"></script>
<script src="js/modify_pwd.js"></script>
</head>
<body>
	<form id=pwd_form method=post >
	<fieldset>
		<label contenteditable="true" id=nam name=nam style="margin-top:25px;margin-left:30px;">表签名</label> 
		<div class="login_box_msg" id="box_msg"><p id="msg">请输入新旧密码</p></div>
		<div style="margin-bottom: 10px;">
		输入原密码： <input name="pwd" type="password" id="pwd" class="text" />
		</div>
		<div style="margin-bottom: 10px;">
		输入新密码： <input name="newPwd" type="password" id="newPwd" class="text" />
		</div>
		<div style="margin-bottom: 10px;">
		确认密码： <input name="confirmPwd" type="password" id="confirmPwd"
			class="text" style="margin-left: 16px;" />
		</div>
		<div class="comfirm_submit" id=comfirm>
			<input class="comfirm_button" id="out" onclick="modify_pwd();"
			value="确定" type="button" tabindex="5" />
		</div>
	</fieldset>
	</form>
	<script language="javascript" for="window" event="onload"> 
if(document.readyState=="complete"){ 
	get_info();
} </script>
</body>
</html>