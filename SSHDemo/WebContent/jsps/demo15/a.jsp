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
	<H1>Struts2常用标签</H1>
	<!-- iterator标签 -->
	<s:set name="list" value="{11111,22222,33333,44444,55555}"/>
	<s:iterator value="#list" status="st">
		<font color=<s:if test="#st.odd">red</s:if><s:else>blue</s:else>>
			<s:property/><br/>
		</font>
	</s:iterator><br/>
	<!-- 条件语句标签if,elseif,else -->
	<s:set name="age" value="23" scope="request"/><!-- 存在request域 -->
	<s:if test="#request.age == 23">23岁</s:if>
	<s:elseif test="#request.age ==21 ">21岁</s:elseif>
	<s:else>都不等</s:else>
	<br/>
	<!-- URL标签 ,只是构建路径，是否存在没关系-->
	<s:url action="aaction_test1" namespace="/demo15">
		<s:param name="age" value="#request.age"/>
	</s:url>
	<br/>
	<!-- value属性默认是字符串，需要用%{}让它变成ognl上下文的一个属性 -->
	<s:set name="myurl" value="'http://www.baidu.com'"/>
	<a href="<s:url value='%{#myurl}'/>">百度网址</a><br/><!-- 为什么不显示？因为set标签的value除了双引号还要单引号-->
	<br/>
	<!-- 表单标签 -->
	<form>
		<!-- 复选框 -->
		<!-- 集合为list -->
		<s:checkboxlist name="list" list="{'java', '.Net', 'RoR', 'PHP'}" 
				value="{'java','.Net'}"/>
		<br/>
		<!-- 集合为map -->
		<s:checkboxlist name="map" list="#{1:'java', 2:'.Net', 3:'RoR', 4:'PHP'}" 
					listKey="key" listValue="value" value="{1,2,3}"/>
		<br/>
		<!-- 
			这里的 "key"和"value"是栈顶对象的属性。栈顶对象是Map.Entry entry.
			这里map中的key{1,2,3,4}对应HTML代码中复选框的value。
		 -->
		<!-- 单选框 -->
		<s:radio name="map" list="#{1:'java', 2:'.Net', 3:'RoR', 4:'PHP'}" 
			listKey="key" listValue="value" value="2"/>
		<br/>
		<!-- 下拉列表 -->
		<s:select name="list" list="{'java', '.Net'}" value="java"/>
	</form>

</body>
</html>