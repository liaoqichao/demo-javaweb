<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function(){
// 	var person = {"name":"zhangsan", "age":18, "sex":"male"};//然而给过来的是字符串
	var strPerson = "{\"name\":\"zhangsan\", \"age\":18, \"sex\":\"male\"}";
	var person = eval("(" +strPerson+ ")");//eval表示执行字符串里面的代码，两边的括号是必须加
	alert(person.name+", "+person.age+", "+person.sex);
};

</script>
</head>
<body>
	<H1>json的应用</H1>
</body>
</html>