<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公文起草</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type=text/css href="bootstrap/css/bootstrap.css.map">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/draft.js"></script>
<style>
img:hover{
	background-color:yellow;
}
</style>
</head>
<body>
<input type="text" id="documentNo" name="documentNo" style="display:none;">
<input type="text" id="documentHeader" name="documentHeader" style="display:none;">
<input type="text" id="state" name="state" style="display:none;">
<input type="hidden" id="url_save" name=url_save value="${pageContext.request.contextPath }">
<div class="list-group" id="operate"  style="position:absolute;max-width:100px;display:none ;">
  <button type="button" class="list-group-item" onclick="openDocument();">打开</button>
  <button type="button" class="list-group-item" onclick="add_issue();">签发</button>
  <button type="button" class="list-group-item" onclick="add_receive();">发送</button>
  <button type="button" class="list-group-item"  id=remark name=remark onclick="search_remark()">备注</button>
  <button type="button" class="list-group-item" onclick="del()">删除</button>
</div>

	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-center">
				公文起草
			</h3>
		</div>
	</div>
	<div class="row-fluid" style="display:inline;">
		
		<div class="span6" style="float:left;width:50%;min-width:350px;">
			<form class="form-inline" style="align:center"  action="<c:url value='/UploadDraft'/>"  encType="multipart/form-data" 	method="post" >
					<table>
						<tr>
							<TH width="10%"style="white-space: nowrap; text-align: center; color: green"><SPAN>${message}</SPAN></TH>
						</tr>
					</table>
				<fieldset  style="margin-left:50px;">
					<legend>上传公文</legend>
  						<button class="btn" type="submit" style="font-size:15px;" onclick="searchDocument();">上传</button>
					<input type="file" id="document" name="document"  style="min-width:250px;min-height:28px;"/>
				</fieldset>
			</form>
		</div>
		<div class="span6"  style="float:left;width:50%; min-width:350px;">
			<form class="form-search" method="post" target="_self">
			<fieldset style="margin-left:50px;">
				 <legend>查找公文</legend> <br>
				  <button class="btn" type="button" onclick="searchDocument();"  style="font-size:15px;">查找</button>
				 <input class="input-medium search-query" id="Header"  name="Header" 
				 	type="text" style="min-width:250px;min-height:28px;margin-top:22px" />	<br> 
			</fieldset>
			</form>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<table class="table" id="document_table" style="font-size:15px;" >
				<thead>
					<tr>
						<th>公文标题</th>
						<th>公文</th>
						<th>起草人</th>
						<th>签发人</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody id="document_tablebody" >
				<tr ></tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script language="javascript" for="window" event="onload"> 
if(document.readyState=="complete"){ 
	searchDocument();
} </script>
<script>
function search_remark(){
	var loc="<%=basePath%>draft/process.jsp?";
	loc=loc+"documentNo="+document.getElementById("documentNo").value;
	loc=loc+"&documentHeader="+document.getElementById("documentHeader").value;
	window.location.href=loc;
}
</script>
</body>
</html>