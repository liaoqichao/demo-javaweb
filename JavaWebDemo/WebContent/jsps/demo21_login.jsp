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
	function _change(){
// 		1.得到img元素
// 		2.修改src为/JavaWebDemo/demo21_verifyCode
		var oImage = document.getElementById("vcode");
		oImage.src = "/JavaWebDemo/demo21_verifyCode";
//			果点换一张图片没有变是因为浏览器缓存了这张图片,点换一张的时候就拿缓存的图片,所以没有换到。
// 			解决方法：在路径后面加参数,要求每次参数都不一样。浏览器看到参数不一样就不会读缓存的图片 
// 		Date date = new Date();
// 		oImage.src = "/JavaWebDemo/demo21_verifyCode" + date.getTime();
	}
</script>
	<%--
		需求：
		1.	在访问login.jsp时,里面的<img/>向VerifyCodeServlet发出请求。
			把验证码文本保存到session域中,并VerifyCodeServlet响应返回验证码图片。
		2.	输入账号密码验证码,点击提交向LoginServet发出请求。
		3.	LoginServlet在session域中获取到验证码文本,从表单中获取用户填写的验证码,进行比较。
			如果相同。向下运行,否则,保存错误信息到request域(msg,"验证码错误"),转发到login.jsp
		4.	LoginServlet判断账号密码是否正确,session域中添加用户名属性("user",username)正确则到succ1.JSP,
			不正确则转发到login.jsp,并在request域中添加(msg,"账号或密码错误");
		5.	打开succ2.jsp。如果session域中user==null,在request域中添加(msg,"没有登录"),并转发到login.jsp
			如果不为空则成功进入到succ2.jsp
	 --%>
	<h1>登录表单</h1>
	<%
		//读取名为user的cookie；如果不为空则显示到text区域,如果为空显示"";
		String user = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")){//这里的user是Cookie的键,不是上面的user字符串
					user = cookie.getValue();//获取这个cookie的值给user
				}
			}
		}
	%>
	<%--本页面提供登录表单,还要显示错误信息 --%>
	<%
		String message = "";//如果第一次打开页面的话msg是null。
		String msg = (String)request.getAttribute("msg");//登录失败就不为空 
		if(msg != null){
			message = msg;
		}
	%>
	<font color="red"><b><%= message %></b></font>
	<form action="/JavaWebDemo/demo21" method="POST">
		<%--把Cookie中的用户名显示到用户名文本框中 --%>
		用户名:<input type="text" name="username" value="<%=user%>" /><br/><%--这里误报错误 --%>
		密&nbsp;&nbsp;码:<input type="password" name="psw"/><br/>
		验证码:<input type="text" name="verifyCode" size=4/>
			<img id="vcode" src="/JavaWebDemo/demo21_verifyCode" >
			<a href="javascript:_change()">换一张</a><br/>
			<%--如果点换一张图片没有变是因为浏览器缓存了这张图片,点换一张的时候就拿缓存的图片,所以没有换到。 --%>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>