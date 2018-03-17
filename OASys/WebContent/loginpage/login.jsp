<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<!-- 
<base href="<c:url value=''/>"/>
 -->
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script charset="UTF-8"></script>
<link href="css/loginpage.css"  rel="stylesheet" type="text/css">
</head>
<body>
	<script src="js/jquery-1.11.2.js"></script>
	<script src="js/login.js"></script>
	<script src="js/cookieControl.js"></script>
	<div class="content">
		<div class="content_wrapper">

			<div class="login_pictures" id="login_pictures_box">
				<div class="login_pictures_picture" id="login_pic"></div>
				<div class="login_pictures_txt" id="login_pic_txt">
					<div class="login_pictures_title" id="login_pic_title"></div>
				</div>

			</div>
			<div class="login_container" id="login_container">
				<div class="login_box" id="login_box">
					<form id="loginform" method="post" name="loginform" target="_self">
						<div class="login_box_header">
							<h1>用户登录</h1>
						</div>

						<div class="login_box_msg" id="box_msg">
							<p id="msg"></p>
						</div>
						<font size=4>账号</font> <input name="LoginAcc" type="text"
							id="LoginAcc" class="text" value="" /><br> <font
							size=4>密码</font> <input name="LoginPwd" type="password"
							id="LoginPwd" class="text" value="" />
						<div class="login_operate" id="web_login">

							<div class="login_submit" style="">
								<input class="login_button" id="btlogin"
									onclick="return checkInput();" value="登录" type="submit"
									tabindex="5" />
							</div>
						</div>
					</form>
					<ul>
						<li>忘记密码？<a class="login_box_forgotpassword"
							href="loginpage/findPwd.jsp" target="_blank">找回密码</a>&nbsp;|&nbsp;
						</li>
						<li><a href="http://app.mail.qq.com/" target="_blank">联系我们</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<script>
function test(){
	window.location.href="../index.jsp";
}
</script>
</body>
</html>