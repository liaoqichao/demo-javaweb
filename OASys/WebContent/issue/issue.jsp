<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人查询</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<liNK rel=stylesheet type=text/css href="css/table.css">
<script src="js/jquery-1.11.2.js"></script>
<script src="js/tablepackage.js"></script>
<script src="js/issue_inquiry.js"></script>
<style>
*{margin:0;padding:0;}
body{font-size:16px;font-family:"Microsoft YaHei";}
ul,li{list-style:none;}

#tab{position:relative;}
#tab .tabList ul li{
	float:left;
	background:#fefefe;
	background:-moz-linear-gradient(top, #fefefe, #ededed);	
	background:-o-linear-gradient(left top,left bottom, from(#fefefe), to(#ededed));
	background:-webkit-gradient(linear,left top,left bottom, from(#fefefe), to(#ededed));
	border:1px solid #ccc;
	padding:5px 0;
	width:300px;
	text-align:center;
	margin-left:-1px;
	position:relative;
	cursor:pointer;
}
#tab .tabCon{
	position:absolute;
	left:-1px;
	top:32px;
	border:1px solid #ccc;
	border-top:none;
	width:904px;
	height:400px;
	overflow:scroll;
}
#tab .tabCon div{
	padding:10px;
	position:absolute;
	opacity:0;
	filter:alpha(opacity=0);
}
#tab .tabList li.cur{
	border-bottom:none;
	background:#fff;
}
#tab .tabCon div.cur{
	opacity:1;
	filter:alpha(opacity=100);
}
.comfirm_submit {
	position: relative;
	float: left;
	width: 100%;
	padding-bottom: 0px;
	clear: both;
	font-size: 14px;
}

.comfirm_button {
	background: rgb(90, 152, 222);
	padding: 0px 20px;
	border-radius: 3px;
	border: currentColor;
	border-image: none;
	width: 185px;
	height: 40px;
	text-align: center;
	color: rgb(255, 255, 255);
	line-height: 38px;
	font-size: 16px;
	font-weight: normal;
	margin-top: 10px;
	margin-left: 35px;
	margin-bottom: 0px;
	display: inline-block;
	cursor: pointer;
	box-sizing: border-box;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

.comfirm_button:hover {
	background: rgb(106, 162, 224);
	text-decoration: none;
}

.comfirm_button:focus {
	background: rgb(106, 162, 224);
	text-decoration: none;
}

.comfirm_button:active {
	background: rgb(90, 152, 222);
}
</style>
</head>
<body>
<input id=delStr style="display:none">
<input id=Login style="display:none">
<!-- 代码 begin -->
<div id="tab" style="margin-left:20px;margin-top:20px">
  <div class="tabList" >
	<ul >
		<li  style="width:904px;">签发权限管理</li>
	</ul>
  </div>
  <div class="tabCon">
	<div class="cur">
		<table cellpadding="0" cellspacing="0" border="0" id="issue_table" class="sortable" style="font-size:10px;width:880px;">
				<thead>
					<tr>
						<th><input type="checkbox" id=selA onclick=" selAll();" value="1"></th>
						<th><h3>卡号</h3></th>
						<th><h3>姓名</h3>
						<th><h3>部门</h3></th>
						<th><h3>职位</h3></th>
					</tr>
				</thead>
				<tbody id=issue_tablebody>
					<tr></tr>
				</tbody>
			</table>
		</div>
  </div>
</div>
<div class="comfirm_submit" id=comfirm style="margin:400px 0px 0px 30px;">
	<input class="comfirm_button" id="delete_issue" onclick="delete_issue();"
		value="删除" type="button" tabindex="5" style="margin:0px 0px 0px 100px;"/>
	<input class="comfirm_button" id="add_issue" onclick="add_issue();"
		value="添加" type="button" tabindex="5"  style="margin:0px 0px 0px 200px;"/>
</div>
<script language="javascript" for="window" event="onload"> 
function openThePageCtrol() { 
	load_issue(); 
};
if(document.readyState=="complete"){ 
	    var oDiv = document.getElementById("tab");
	    var oLi = oDiv.getElementsByTagName("div")[0].getElementsByTagName("li");
	    var aCon = oDiv.getElementsByTagName("div")[1].getElementsByTagName("div");
	    document.getElementById("Login").value=true;
	   
	    var timer = null;
	    for (var i = 0; i < oLi.length; i++) {
	        oLi[i].index = i;
	        oLi[i].onmouseover = function() {
	            show(this.index);
	        };
	    }
	    function show(a) {
	        index = a;
	        var alpha = 0;
	        for (var j = 0; j < oLi.length; j++) {
	            oLi[j].className = "";
	            aCon[j].className = "";
	            aCon[j].style.opacity = 0;
	            aCon[j].style.filter = "alpha(opacity=0)";
	        }
	        oLi[index].className = "cur";
	        clearInterval(timer);
	        timer = setInterval(function() {
	            alpha += 2;
	            alpha > 100 && (alpha = 100);
	            aCon[index].style.opacity = alpha / 100;
	            aCon[index].style.filter = "alpha(opacity=" + alpha + ")";
	            alpha == 100 && clearInterval(timer);
	        },
	        5);
	    }
	    openThePageCtrol(); 
} 
</script>
</body>
</html>