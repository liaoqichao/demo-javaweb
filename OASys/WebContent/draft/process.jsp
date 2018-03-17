<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看备注</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/process.js"></script>
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type=text/css href="bootstrap/css/bootstrap.css.map">
</head>
<body>
<input type="text" id="documentNo" name="documentNo" style="display:none;">
<input type="text" id="stateNo" name="stateNo" style="display:none;">
<div class="list-group" id="operate"  style="position:absolute;max-width:100px;display:none ;">
  <button type="button" class="list-group-item" onclick="openDocument();">打开</button>
</div>
<div class="container-fluid" >
<div class="panel panel-primary" style="float:left;width:70%;min-width:550px;min-height:300px;max-height:700px;margin-left:50px">
  <!-- Default panel contents -->
  <div class="panel-heading">查看备注</div>
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
		
		
		 <table class="table" id=document_table >
						<thead>
							<tr>
								<th>公文标题</th>
								<th>公文</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody id=document_tablebody>
							<tr id=0>
							<td ><%=request.getParameter("documentHeader") %></td>
							<td id=tr0><img src="/OASys/images/icon/file.ico" onclick="operate(0);"></td>
							<td>处理...</td>
							</tr>
						</tbody>
			</table>
			<!-- List group -->
  			<ul class="list-group">
  				<li class="list-group-item list-group-item-default"><textarea id=remarks  name=remarks cols="100" rows="6" readonly="readonly"></textarea> </li>
  			</ul>
  			<div class="btn-group btn-group-justified" role="group" aria-label="..." style="margin-top:12px;margin-bottom:12px;margin-left:50px;">
					<div class="btn-group" role="group">
						<input type="hidden" id=addStr name=addStr >
    					 <button type="button" class="btn btn-default" style="width:150px;" onclick="return_unreceive();">返回</button>
  					</div>
				</div>
	</div>
</div>
<script language="javascript" for="window" event="onload">
if(document.readyState=="complete"){
	var header=getUrlParam("documentHeader");
	var documentNo=getUrlParam("documentNo");
	setTable(header,documentNo);
}
function getUrlParam(name) {  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");   
	var r = window.location.search.substr(1).match(reg);  
	if (r!=null)    
		return unescape(r[2]);         
	return null;        
}  
</script>
<script>
function return_unreceive(){
	window.location.href="<%=basePath%>/draft/draft.jsp";
}
</script>
</body>
</html>