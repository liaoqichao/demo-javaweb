<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加接收人</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/add_receive.js"></script>
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type=text/css href="bootstrap/css/bootstrap.css.map">
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-center">
				添加接收人
			</h3>
		</div>
	</div>
	<br>
	<input type="text" id=documentNo name=documentNo  style="display:none;" value=<%= request.getParameter("documentNo")%>>
	<div class="row-fluid" style="display:inline;">
		<div class="panel panel-default" style="float:left;width:70%;min-width:350px;min-height:300px;max-height:500px;margin-left:50px">
  		<!-- Default panel contents -->
  			<div class="panel-heading">查找职员</div>
  			<div class="panel-body">
   				 <!-- 带下拉的输入框 -->
   				 <div class="col-lg-6">
    				<div class="input-group">
      					<select  name="sdepartment"  id="sdepartment" style="width:85%;height:32px;margin-left:50px;">
							<option value="软件部">软件部</option>
							<option value="企划部">企划部</option>
							<option value="策划部">策划部</option>
							<option value="财务部">财务部</option>
						</select>
      					<span class="input-group-btn" style="margin-left:25px;">
        				<button class="btn btn-default" type="button" onclick="getStaff();">Go!</button>
      					</span>
    				</div><!-- /input-group -->
  				</div><!-- /.col-lg-6 -->
			</div><!-- /.row -->
  			<!-- Table -->
  			<table class="table" id=staff_table >
						<thead>
							<tr>
							<th><input type="checkbox" id=selA onclick=" selAll();" value="1"></th>
								<th>工作卡号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>职位</th>
							</tr>
						</thead>
						<tbody id=staff_tablebody>
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
    					<button type="button" class="btn btn-default"  style="width:150px;" onclick="add_staff();">发送</button>
    					<button type="button" class="btn btn-default" style="width:150px;" onclick="return_staff()">取消</button>
  					</div>
				</div>
  			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function return_staff(){
	window.location.href="<%=basePath%>/received/search_received.jsp";
}
</script>
	
</body>
</html>