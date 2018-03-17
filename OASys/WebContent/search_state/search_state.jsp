<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看公文状态</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/search_state.js"></script>
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type=text/css href="bootstrap/css/bootstrap.css.map">
</head>
<body>
	<div class="container-fluid">
	<br>
	<input type="text" id=documentNo name=documentNo  style="display:none;">
	<div class="row-fluid" style="display:inline;">
		<div class="panel panel-default" style="float:left;width:70%;min-width:350px;min-height:300px;max-height:500px;margin-left:50px">
  		<!-- Default panel contents -->
  			<div class="panel-heading">公文状态</div>
  			<div class="panel-body">
   				 <!-- 带下拉的输入框 -->
   				 <div class="col-lg-6">
    				<div class="input-group">
      					<input type="text" class="form-control"  id=documentHeader name=documentHeader placeholder="输入公文标题查找">
      				<span class="input-group-btn">
        				<button class="btn btn-default" type="button" onclick="getState();">Go!</button>
      				</span>
    				</div><!-- /input-group -->
  				</div><!-- /.col-lg-6 -->
			</div><!-- /.row -->
  			<!-- Table -->
  			<table class="table" id=state_table >
						<thead>
							<tr>
							<th><input type="checkbox" id=selA onclick=" selAll();" value="1"></th>
								<th>公文标题</th>
								<th>卡号</th>
								<th>接收人</th>
								<th>部门</th>
								<th>职位</th>
								<th>发送人</th>
								<th>部门</th>
								<th>职位</th>
								<th>状态</th>
								<th>更新时间</th>
							</tr>
						</thead>
						<tbody id=state_tablebody>
							<tr>
								
							</tr>
						</tbody>
					</table>

		</div>
		<div class="panel panel-default" style="float:left;width:70%;min-width:350px;min-height:70px;max-height:500px;margin-left:50px">
  			<div class="panel-body">
    			<div class="btn-group btn-group-justified" role="group" aria-label="...">
					<div class="btn-group" role="group">
						<input type="hidden" id=addStr name=addStr >
    					<button type="button" class="btn btn-default"  style="width:150px;" onclick="add_state();">催办</button>
  					</div>
				</div>
  			</div>
		</div>
	</div>
</div>
<script language="javascript" for="window" event="onload"> 
if(document.readyState=="complete"){ 
	getState();
} </script>
</body>
</html>