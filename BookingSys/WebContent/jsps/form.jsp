<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/ajaxutils.js"></script>
<script type="text/javascript">
window.onload = function(){
// 	使用ajax显示区
	ajax({
			url:"<c:url value='/DistrictServlet'/>" , type:"json",
			callback:function(data){	//deta是json数组
				var district = document.getElementById("district");//得到select id=districet元素
				for(var i=0 ; i<data.length; i++){
					var option = document.createElement("option");//创建option标签
					option.value = data[i].did;//设置option的真实值
					var textNode = document.createTextNode(data[i].dname);//创建显示值的节点
					option.appendChild(textNode);
					district.appendChild(option);	//添加到select中
				}
			}
	});

	//显示办理登记点
	var district = document.getElementById("district");
	district.onchange = function(){
		ajax({
			url:"<c:url value='/AddressServlet?method=getDept'/>" , type:"json",
			method:"POST" , params:"did="+this.value ,//市的名字，真实值和显示值相同,
// 			这里的this是district，因为是district的onchange事件触发的函数
			callback:function(data){	//data是List<Address>的json串
				//0.把之前已经有的option删掉
				//a.得到要删除option的select节点
				var department = document.getElementById("department");//得到select id=department
				//b.得到option节点列表
				var optionEleList = department.getElementsByTagName("option");//这样是把district当做根元素
				//c.
				while(optionEleList.length != 0){
					//下标为0的都删了，删了下面会顶上去
					department.removeChild(optionEleList[0]);
				}
				
				
				//**如果龙岗区有2个科的话，添加请选择，为后面根据科显示地址时，这样就有个onchange事件可以用
				if(data.length > 1){
					var option = document.createElement("option");
					option.value = "---请选择---";
					var textNode = document.createTextNode("---请选择---");
					option.appendChild(textNode);
					department.appendChild(option);
				}
				
				//1.添加option
				for(var i=0 ; i<data.length ; i++){
					////////办理登记地点
					//删除完后开始新建option和添加到select
					var option = document.createElement("option");//这里是办理登记地点
					option.value = data[i].aid;
					var textNode = document.createTextNode(data[i].aname);
					option.appendChild(textNode);	//option选项添加显示值
					department.appendChild(option);
					
				}
				//**该区办理地点只有一个，可以直接在这里显示地址,龙岗区有2个，另作打算
				if(data.length <= 1){
					var location = document.getElementById("location");
					location.innerHTML = data[0].alocation;
				}
			}
		})
	};
	
	//显示办理登记点的地址,龙岗区(有多个地址，有onchange事件)
	var department = document.getElementById("department");
	department.onchange = function(){
		ajax({
			url:"<c:url value='/AddressServlet?method=getLocation'/>" , type:"json",
			method:"POST" , params:"aid="+this.value,
			callback:function(data){
				var location = document.getElementById("location");
				location.innerHTML = data.alocation;
			}
		})
	};
};

</script>
</head>
<body>
<div>
	<h1>这是预约申请表页面</h1>
	<div>预约须知-->预约申请表</div>
	<div align="center"><b>预约申请表</b></div>
	<div>
	<form action="" method="POST"><!-- 没有附件，不用多部件 -->
		<table align="center">
			<tr>
				<td colspan="1"><b>业务信息</b></td>
			</tr>
			<tr>
				<td>业务事项</td>
				<td>[根据传过来的服务事项ID获取服务事项名字]</td>
				<td>受理单位</td>
				<td>深圳不动产登记中心</td>
			</tr>
			<tr>
				<td colspan="1"><b>房地产权利证明</b></td>
			</tr>
			<tr>
				<td>房地产所在区</td>
				<td>
					<select id="district">
					<!-- 用ajax，在window.onload向Servlet发送请求，Servlet返回区的信息，然后再循环生成option？ 
						 区信息用XML?bean变成json串?
					-->
						<option>请选择</option>	<!-- 这里的opton要有value，真实值和显示值 -->				
					</select>
				</td>
				<td>房地产名称</td>
				<td><input type="text" name="ename"/></td>
			</tr>
			<tr>
				<td>权属证明类型</td>
				<td><select>
					<option>请选择</option>
					<option >不动产登记证明或房产证</option>
				</select></td>
				<td>权属证明编号</td>
				<td><input type="text" name="eid"/></td>
			</tr>
			<tr>
				<td colspan="1"><b>预约人</b></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="cname"/></td>
				<td>手机</td>
				<td><input type="text" name="phone"/></td>
			</tr>
			<tr>
				<td>证件类型</td>
				<td><select><option>二代身份证</option></select></td>
				<td>证件号码</td>
				<td><input type="text" name="certificate"/></td>
			</tr>
			<tr>
				<td colspan="1"><b>办理登记点和预约时间信息</b></td>
			</tr>
			<tr>
				<td  >办理登记地点</td>
				<td colspan="3" >
				<select id="department" style="width:300px;"></select>
				</td>
			</tr>
			<tr>
				<td>登记地点地址</td>
				<td colspan="3" id="location"></td><!-- ajax,select中用onchange事件 -->
			</tr>
			<tr>
				<td>这里选择预约时段</td>
			</tr>
			<tr>
				<td colspan="1"><b>现场必须材料</b></td>
			</tr>
			<tr>
				<td colspan="1">
					<a href="<c:url value='/jsps/index.jsp'/>"/>详见《网上办事大厅房地产权登记业务办事指南》</a>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="button" value="返回" onclick="javascript:window.location.href='read.jsp'"/>　　<input type="button" value="提交"/></td>
			</tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>