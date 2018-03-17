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
	<H1>国际化</H1>
	<s:text name="welcome"><!-- 输出全局资源文件键为welcome的内容，输出结果为乱码！不成功properties文件已经是UTF-8编码-->
		<s:param>张三</s:param>
		<s:param>鞋习！</s:param>
	</s:text><!-- 张三,æ¬¢è¿æ¥å°ä¼ æºæ­å®¢鞋习 -->
	<br/>
	<s:i18n name="struts2/demo13/action/package">
		<s:text name="welcome">
			<s:param>王五</s:param>
			<s:param>玩</s:param>
		</s:text>
	</s:i18n>
	<br/>
	<s:i18n name="struts2/demo13/action/AAction">
		<s:text name="welcome">
			<s:param>李四</s:param>
			<s:param>捣乱</s:param>
		</s:text>
	</s:i18n>
</body>
</html>