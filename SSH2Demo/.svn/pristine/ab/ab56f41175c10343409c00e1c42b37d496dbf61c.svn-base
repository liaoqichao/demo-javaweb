<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function findAll(){
	var action_list = document.getElementById("contextPath");
	action_list.click();
}

</script>
	<a id="contextPath" href="${pageContext.request.contextPath}/employee/action_getList"></a>
	<H1>查</H1>
<%-- 	<s:form action="action_get" namespace="/employee"> --%>
<%-- 		员工ID：<s:textfield name="employee.id"></s:textfield><br/> --%>
<!-- 		<input type="submit" value="查询"/> -->
<%-- 	</s:form> --%>
	<form action="<c:url value='/employee/action_getOne'/>" method="post">
		员工ID:<input type="text" name="employee.id"/>
		<input type="submit" value="查询"/>
	</form>
	<s:property value="employee"/>
	<br/>
	<button style="height:20px;width:100px" onclick="javaScript:findAll()">查询所有员工</button>
	<br/>
<%-- 	<s:iterator value="employeeList" var="employee"> --%>
<%-- 		id=<s:property value="id"/>, name=<s:property value="name"/><br/> --%>
<%-- 	</s:iterator> --%>
	<c:forEach items="${employeeList }" var="employee">
		id=${employee.id }, name=${employee.name }<br/>
	</c:forEach>
	<br/>
	<a href="<c:url value='/index.jsp'/>">返回主页</a>
</body>
</html>