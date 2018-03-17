<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="domain.Demo26_JBNavigator_Address,domain.Demo26_JBNavigator_Employee"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		1.导包  import="domain.Demo26_JBNavigator_Address,domain.Demo26_JBNavigator_Employee" 
		2.设置Address对象 
		3.创建Employee对象
	--%>
	<% 
		Demo26_JBNavigator_Address addr = new Demo26_JBNavigator_Address();
		addr.setCity("北京");
		addr.setStreet("西三旗");
		
		Demo26_JBNavigator_Employee employee = new Demo26_JBNavigator_Employee();
		employee.setName("李小四");
		employee.setSalary(12345);
		employee.setAddress(addr);
		
		request.setAttribute("employee", employee);
	%>
	
	<h3>使用el获取request域的employee</h3>
	<%--获取李小四的住址的街道。使用JavaBean,
		而不是使用函数调用request.getAttribute("employee").getAddress().getStreet();--%>
	${requestScope.employee.address.street}
</body>
</html>