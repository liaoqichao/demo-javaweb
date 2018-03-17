<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新职工</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<link rel=stylesheet  type=text/css href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery-1.11.2.js"></script>
<script src="js/add_Staff.js"></script>
</head>
<body>
<div class="page-header">
  <h2 style="align:center;margin-left:250px;margin-bottom:25px;">添加新职员 </h2>
<!--  添加数据员工数据 -->
<form	action="<c:url value='/AddStaff'/>" method="post"  style="margin-left:250px;margin-top:50px;">
	<table>
		<tr>
		<TH width="10%"style="white-space: nowrap; text-align: left; color: green" ><SPAN>${message}</SPAN></TH>
		</tr>
	</table>
	<div class="input-group">
  	<span class="input-group-addon" >@姓名：</span>
  	<input type="text" class="form-control" id="nam" name="nam" style="width:350px;" placeholder="Username" aria-describedby="sizing-addon2">
</div>
	<div class="input-group">
  		<span class="input-group-addon" >@卡号：</span>
  		<input type="text" class="form-control" id="cardNo" name="cardNo" style="width:350px;" placeholder="card" aria-describedby="sizing-addon2">
	</div>
	<div class="input-group">
  		<span class="input-group-addon" >@ID　：  </span>
  		<input type="text" class="form-control" id="ID" name="ID" style="width:350px;" placeholder="ID number" aria-describedby="sizing-addon2">
	</div>
	<div class="input-group">
  		<span class="input-group-addon" >@部门：</span>
  		<select name="department"  id="department" style="width:350px;height:32px;">
			<option value="软件部">软件部</option>
			<option value="企划部">企划部</option>
			<option value="策划部">策划部</option>
			<option value="财务部">财务部</option>
		</select>
	</div>
 	<div class="input-group">
  		<span class="input-group-addon" >@职位： </span>
 		<input type="text" class="form-control" id="position" name="position"  style="width:350px;" placeholder="position" aria-describedby="sizing-addon2">
	</div>
	<div class="btn-group" role="group" aria-label="...">
  		<input type="submit" class="btn btn-default" style="margin-left:75px;margin-top:8px;" value="添加"/>
  		<button  type="button" class="btn btn-default" onclick="goBack()" style="margin-left:25px;margin-top:8px;" >返回<br/></button>
  	</div>
</form>
</div>
<script>
function  goBack(){
	window.location.href="<%=basePath%>/staffmanage/staffmanage.jsp";
}
</script>
</body>
</html>