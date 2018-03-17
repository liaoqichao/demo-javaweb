<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		演示在这个页面的表单输入2个整数,传到/demo17的Servlet中,进行加法运算,结果在result.jsp中显示
	 -->
	 <form action="/JavaWebDemo/demo17" method="POST">
		整数1:<input type="text" name="int1"/><br/>
		整数2:<input type="text" name="int2"/><br/>
		<input type="submit" value="提交"/>	
	</form>
</body>
</html>