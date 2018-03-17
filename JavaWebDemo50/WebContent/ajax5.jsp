<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/ajax.js"></script><%--src开头不要有斜杠 --%>
<script type="text/javascript">

/*
 * 1.在页面加载完毕时，得到所有省份名称，然后显示在select name=province中
 * 2.在选择了新的省份时，发送请求(含参数：省份名称)，得到xml文档。即<province>元素
 * 	解析xml文档，得到所有的city内容，即市名。使用市名得到option插入到select中
 */
window.onload = function(){
	/*
	ajax四步，请求ProvinceServlet，得到所有省份名称
	使用每个省份名称创建一个<option>元素，添加到<select name="province">中
	*/
	//1.得到异步对象XMLHttpRequest
	var xmlHttp = createXMLHttpRequest();
	//2.打开连接
	xmlHttp.open("GET","<c:url value='/ProvinceServlet'/>",true);//true表示发送异步请求
	//3.发送请求，GET请求为NULL,POST请求为参数
	xmlHttp.send(null);
	//4.在xmlHttp.onreadystatechange事件注册监听器
	xmlHttp.onreadystatechange = function(){
		//a.双重判断，获取服务器的response
		if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
			//b.获取响应文本
			var text = xmlHttp.responseText;
			//c.使用逗号分隔，得到数组
			var arr = text.split(","); 
			//d.循环遍历每个省份，每个名称生成一个option对象，添加到select name=province中
			for(var i = 0 ; i < arr.length ; i++){
				var option = document.createElement("option");//创建一个指定名称的元素
				option.value = arr[i];//设置op的实际值为当前的省份名称
				var textNode = document.createTextNode(arr[i]);//创建为本节点。文本本身也是一个节点。
				option.appendChild(textNode);//把文本子节点添加到option中，其实就是设置显示值
				document.getElementById("province").appendChild(option);//把option添加到<select>中
			}
		}
	};
	
	
	/*
		给<select name="province">添加改变监听器
		使用选择的省份名称，请求cityServlet，得到<province>元素。
		获取province元素中的所有city元素，遍历所有city元素，获取city的文本内容，即市名称。
		使用市名称创建option元素，添加到select name=city中。
	*/
	var proSelect = document.getElementById("province");
	proSelect.onchange = function(){
		//ajax四+1步
		//1.
		var xmlHttp = createXMLHttpRequest();
		//2.
		xmlHttp.open("POST","<c:url value='/CityServlet'/>",true);
		//3.
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//4.
		xmlHttp.send("pname="+proSelect.value);//下拉列表中选择的值就是option的值。发送给服务器。
		//5.
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState ==4 && xmlHttp.status ==200){
				//z.把select中所有的option移除，除了第一个"==请选择==";
				//获取select节点
				var citySelect = document.getElementById("city");
				//得到所有option元素
				var optionEleList = citySelect.getElementsByTagName("option");
				//遍历所有option元素，在citySelect移除
				while(optionEleList.length > 1){	//子元素的个数如果大于一就循环。
					citySelect.removeChild(optionEleList[1]);//全部1下标都删完了(总是删除第二个)，最后只剩0下标
				}
				
				//a.获取servlet的响应，响应格式为XML
				 var doc = xmlHttp.responseXML;	//得到<province>元素字符串，用XML来解析
				 //b.得到所有名为city的元素,前面已经获取
				 var cityEleList = doc.getElementsByTagName("city");
				 //c.遍历所有city，创建option元素，创建textNode，把textNode放在option内，把option放在select内
				 for(var i=0 ; i<cityEleList.length ; i++){
					 var cityEle = cityEleList[i];	//返回的是数组不是列表
					 var cityName;//获取city元素的内容，市名称
					 //处理浏览器差异
					 if(window.addEventListener){//大部分浏览器
						 cityName = cityEle.textContent;
					 } else{	//IE浏览器。IE9不行
						 cityName = cityEle.text;
					 }
					 
					 //使用市名称创建option元素，添加到select name=city
					 var option = document.createElement("option");
					 option.value = cityName;//实际值
// 					 alert(cityName);
					 var textNode = document.createTextNode(cityName);//显示值
					 option.appendChild(textNode);//把文本节点添加到option中
					 citySelect.appendChild(option);
					 
				 }
			}
		};
	};
 };
</script>
</head>
<body>
	<H1>省市联动</H1>
	<select name="province" id="province">
		<option>===请选择省===</option>
	</select>　　　
	<select name="city" id="city">
		<option>===请选择市===</option>
	</select>
</body>
</html>