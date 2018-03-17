<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>成功页面1</h1>

	<%
		String username = (String)session.getAttribute("username");	
		if(username !=null){
			out.print("欢迎"+username+"来到页面1!");
		}else{
			//向request域中保存错误信息,并转发到login.jsp
			request.setAttribute("msg", "您还没有登录");
			request.getRequestDispatcher("/jsps/demo21_login.jsp").forward(request, response);
		}
	%>
</body>
</html>