<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签发公文</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/message.js"></script>
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type=text/css href="bootstrap/css/bootstrap.css.map">
<style type="text/css">
/* WebKit browsers */
::-webkit-input-placeholder {
	color: #777;
}
/* Mozilla Firefox 4 to 18 */
:-moz-placeholder {
	color: #777;
	opacity: 1;
}
/* Mozilla Firefox 19+ */
::-moz-placeholder {
	color: #777;
	opacity: 1;
}
/* Internet Explorer 10+ */
:-ms-input-placeholder {
	color: #777;
}
</style>
</head>
<body>
<input type="hidden" id="messageNo" name="messageNo">
<div class="list-group" id="operate"  style="position:absolute;max-width:100px;display:none ;">
  <button type="button" class="list-group-item" onclick="openMessage();">打开</button>
</div>
<div class="container-fluid" style="display:inline;">
<div class="panel panel-primary" style="float:left;width:45%;min-width:450px;min-height:500px;max-height:700px;margin-left:50px;margin-top:20px;">
  <!-- Default panel contents -->
  <div class="panel-heading">查找发送人</div>
    <div class="panel-body" >
    	
   				 <!-- 带下拉的输入框 -->
   				 <div class="col-lg-6" >
   				 <span><input type="checkbox" id=state name=state>未阅读</span>
    				<div class="input-group">
      					<input type="text" class="form-control"  id=nam name=nam placeholder="输入姓名查找" >
      				<span class="input-group-btn">
        				<button class="btn btn-default" type="button" onclick="searchMessage();">Go!</button>
      				</span>
    				</div><!-- /input-group -->
  				</div><!-- /.col-lg-6 -->
		</div><!-- /.row -->
		 <table class="table" id=message_table >
						<thead>
							<tr>
								<th>发送人</th>
								<th>部门</th>
								<th>消息</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody id=message_tablebody>
							<tr>
							</tr>
						</tbody>
			</table>
	</div>
	<div class="panel panel-primary" style="float:left;width:45%;min-width:450px;min-height:500px;max-height:700px;margin-left:50px;margin-top:20px;">
  <!-- Default panel contents -->
  <div class="panel-heading">显示信息</div>
    <div class="panel-body">
   				 <!-- 带下拉的输入框 -->
   				 <div class="input-group">
  					<span class="input-group-addon" id="sizing-addon2">@发送人</span>
  					<input type="text" class="form-control" placeholder="UserCardNo" aria-describedby="sizing-addon1" id="sendCardNo" name="sendCardNo" >
  					<input type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon1" id="sendName" name="sendName" >
				</div>

		</div><!-- /.row -->
			<!-- List group -->
  			<ul class="list-group">
  				<li class="list-group-item list-group-item-default"><textarea id=receiveMessage  name=receiveMessage cols="70" rows="8" placeholder="显示接收信息"  readonly="readonly"></textarea> </li>
  				<li class="list-group-item list-group-item-default"><textarea id=sendMessage  name=sendMessage placeholder="输入发送信息" cols="70" rows="4"></textarea> </li>
  			</ul>
  			<div class="btn-group btn-group-justified" role="group" aria-label="..." style="margin-top:12px;margin-bottom:12px;margin-left:50px;">
					<div class="btn-group" role="group">
						<input type="hidden" id=addStr name=addStr >
    					<button type="button" class="btn btn-default"  style="width:150px;" onclick="sendMessage();">发送</button>
  					</div>
				</div>
	</div>
</div>
<script language="javascript" for="window" event="onload">
if(document.readyState=="complete"){
	var messageNo=getUrlParam("messageNo");
	document.getElementById("messageNo").value=messageNo;
	document.getElementById("state").checked=true;
	searchMessage();
}
function getUrlParam(name) {  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");   
	var r = window.location.search.substr(1).match(reg);  
	if (r!=null)    
		return unescape(r[2]);         
	return null;        
}  
</script>
</body>
</html>