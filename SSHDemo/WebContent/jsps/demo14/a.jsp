<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>OGNL表达式</H1>
	<!-- 访问上下文的属性 -->
	<s:property value="#request.req_Name"/><br/><!-- 用这个标签输出属性的值 -->
	<s:property value="#session['ses_Name']"/><br/>
	
	<!-- 访问值栈的属性 -->
	<s:property value="member"/><br/>
	${member }<br/><!-- 采用EL表达式只能访问ValueStack的属性，其他不能访问。 -->
	
	<!-- 访问栈值的复合属性（javabean） -->
	<s:property value="user.username"/>　　<!-- 这里有空格 -->
	<s:property value="user.password"/><br/>
	${user.username }　　<!-- 这里有空格 -->
	${user.password }<br/>
	
	<!-- 使用OGNL表达式创建List、Map -->
	<!-- 创建List。
		如果不指定scope，默认是在OGNL Context，和request，session的地位相同(存放在OGNL Context中)。 
	--> 
	<s:set var="list" value="{'张三三','李四四','王五五'}"/>
	<!-- 迭代时有一个特点：会把当前迭代的对象放在栈顶(root变量第一位)中 -->
	<s:iterator value="#list" >
		<!-- property如果没有提供value属性就默认输出ValueStack中root的第一个值（栈顶的值） -->
		<s:property/><br/>
	</s:iterator>
	<br/>
	<!-- 创建Map -->
	<s:set name="map" value="#{'key1':90, 'key2':100, 'key3':110 }"/>
	<s:iterator value="#map">
		<!-- 栈顶元素是Map.Entry entry,entry有getValue()和getKey()方法，所以直接写key和value -->
		<s:property value="key" /> = <s:property value="value"/><br/>
	</s:iterator>
	<br/>
	<!-- 判断元素是否在集合中 -->
	<s:if test="'d' in {'a','b','c'}">在</s:if>
	<s:else>不在</s:else><br/>
	=============================================<br/>
	<!-- OGNL表达式：集合的投影（获得满足规则的子集） -->
	<s:iterator value="bookList.{?#this.price >= 60}">
	<!-- 因为bookList是action的属性，这时栈顶元素是action，所以可以直接访问bookList -->
		<!-- 对筛选后的自己和进行迭代,还是一个List<Book>,栈顶元素就是Book -->
		<s:property value="bname"/> : <s:property value="price"/><br/>	
	</s:iterator>
</body>
</html>