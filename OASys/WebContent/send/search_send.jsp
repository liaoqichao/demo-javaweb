<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已发送文件</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/search_send.js"></script>
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type=text/css href="bootstrap/css/bootstrap.css.map">
</head>
<body>
<input type="text" id="documentNo" name="documentNo" style="display:none;">
<input type="text" id="stateNo" name="stateNo" style="display:none;">
<input type="hidden" id=state name=state value="1">
<input type="hidden" id="url_save" name=url_save value="${pageContext.request.contextPath }">
<div class="list-group" id="operate"  style="position:absolute;max-width:100px;display:none ;">
  <button type="button" class="list-group-item" onclick="openDocument();">打开</button>
  <button type="button" class="list-group-item" onclick="writeRemarks();">备注</button>
  <button type="button" class="list-group-item" onclick="add_receive();">转发</button>
</div>
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-center">
				查看已发送文件
			</h3>
		</div>
	</div>
	<br>
	<div class="row-fluid" style="display:inline;">
		<div class="panel panel-default" style="float:left;width:70%;min-width:350px;min-height:300px;max-height:500px;margin-left:50px">
  		<!-- Default panel contents -->
  			<div class="panel-heading">查看文件状态</div>
  			<div class="panel-body">
   				 <!-- 带下拉的输入框 -->
   				 <div class="col-lg-6">
    				<div class="input-group">
      					<input type="text" class="form-control"  id=Header name=Header placeholder="输入文件名查找">
      				<span class="input-group-btn">
        				<button class="btn btn-default" type="button" onclick="searchDocument();">Go!</button>
      				</span>
    				</div><!-- /input-group -->
  				</div><!-- /.col-lg-6 -->
			</div><!-- /.row -->
  			<!-- Table -->
  			<table class="table" id=document_table >
						<thead>
							<tr>
								<th>公文标题</th>
								<th>公文</th>
								<th>接收人</th>
								<th>部门</th>
								<th>职位</th>
								<th>状态</th>
								<th>更新时间</th>
							</tr>
						</thead>
						<tbody id=document_tablebody>
							<tr>
								
							</tr>
						</tbody>
					</table>

		</div>
	</div>
</div>
<script language="javascript" for="window" event="onload"> 
if(document.readyState=="complete"){ 
	search_send();
} </script>
<script>
function openDocument(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	if(fill_document(_row,"state")=="5"||state=="7"){
		window.location.href="<%=basePath%>/send/downDraft.jsp?documentNo="+document.getElementById("documentNo").value;
	}
	else{
		window.location.href="<%=basePath%>/send/downDocument.jsp?documentNo="+document.getElementById("documentNo").value;
	}
}
function add_receive(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	window.location.href="<%=basePath%>/send/add_receive.jsp?documentNo="+document.getElementById("documentNo").value;
}
</script>
</body>
</html>