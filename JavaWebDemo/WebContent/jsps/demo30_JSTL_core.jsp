<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="code" value="<script>alert('hello')</script>" scope="request"/>
	<c:out value="${code }" escapeXml="true"/>
	<br/>
<%-- 	<c:remove var="code"/> --%>
<%-- 	没有指定域默认删除所有域 --%>

	 
	 <c:url var="servlet" value="/servlet/Demo1_Servlet" scope="request"/><br/>
	 <c:out value="${servlet }"/>
	 <hr/>
	 <a href="<c:url value='/index.jsp'>
	 			<c:param name='username' value='张三'/>
	 		</c:url>">点击这里回到主页</a>
	<%--输出 /JavaWebDemo/index.jsp?username=%e5%bc%a0%e4%b8%89 到页面
		自动进行URL编码
	 --%>
	 <%--正常是标签是不可以嵌套的,但是一个是动态标签(服务器端执行),一个是静态标签(浏览器执行),执行的时间不一样 --%>
	 <hr/>
	 
	 <c:if test="${not empty requestScope.code }">
	 <%-- 只能表示if,不能表示else。没有指定域则为全域
	 	String value = (String)request.getAttribute("code");//对象的话重写toString方法  
	 	if(obj!=null){
	 		request.getWriter().write(value);
	 	}
	 --%>
	 	<c:out value="${code }"/>
	 </c:if>
	 <hr/>
	 <c:set var="score" value="${param.score }"/><%--设置score变量,值为名为score的参数--%>
	 <c:choose>
	 	<c:when test="${score >100 || score<0 }">错误的分:${score }</c:when>
	 	<c:when test="${score >=60 && score<=100 }">及格</c:when>
	 	<c:when test="${score >=0 && score<60 }">不及格</c:when>
	 	<c:otherwise>你没给我分数</c:otherwise>
	 </c:choose>
	 <hr/>
	 <c:set var="sum" value="0"/>
	 <c:forEach var="i" begin="0" end="10" step="2"><%--step步长。i+=2 --%>
		<c:set var="sum" value="${sum + i}"/>
	 </c:forEach>
	 sum = <c:out value="${sum }"/>
	 <hr/>
	 <%
	 	//遍历数组或者集合
	 	String[] strs = {"one","two"};
	 	request.setAttribute("strs", strs);
	 %>	 
	 <c:forEach items="${requestScope.strs }" var="str">
	 	${str }<br/> 
	 </c:forEach>
	 <%--
	 	等同于 for(String str : strs){
	 			out.print(str);
	 		}
					**********调Bug
		value="${str }";//r和}之间可以有空格
		value="${str} ";//错误,}和"之间不能有空格 输出结果：[Ljava.lang.String;@48fa22d5
	  --%>
	  <hr/>
	  <%
	  	ArrayList<String> al = new ArrayList<String>();
	  	al.add("一");
	  	al.add("二");
	  	al.add("三");
	  	request.setAttribute("al",al);//没有加到域不能使用${al }
	  %>
	  <c:forEach items="${al }" var="ele" varStatus="vs">
	  	${vs.count }
	  	${vs.first }
	  	${vs.index }
	  	${vs.current }
	  	<br/>
	  </c:forEach>
</body>
</html>