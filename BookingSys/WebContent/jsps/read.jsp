<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function back(){
	window.location.href="<c:url value='/jsps/selectItem.jsp'/>";
}
function next(){
	//获取参数:选择服务的id
	var paramItemId = "";
	//访问表单页面
	var url = "<c:url value='/jsps/form.jsp?itemId='/>";
	window.location.href=url+paramItemId;
}

</script>
</head>
<body>
	<div align="center">
		<div>	<h1>这是阅读预约须知页面</h1>		</div>
		<div>
			<div align="left" style="margin-left: 100px;margin-right: 100px">预约须知</div>
			<br/>
			<div align="left" style="margin-left: 100px;margin-right: 100px">
				<table >
					<tr valign="bottom">
						<td >　一、</td>
						<td >预约人对现场办理时需提交申请材料和办事程序有疑问，可以点击<a href="">“网上办事大厅不动产登记业务办事指南”</a> 进行了解。</td>
					</tr>
					
					<tr valign="bottom">
						<td>　二、</td>
						<td>网上预约申请采用实名制，预约人须为不动产登记申请人或相关当事人，并填写《预约申请表》。网上预约信息与现场所提供的身份证明及不动产权利证明不符的，不予发预约号。<b>特别提示:境外人士到各登记点取当前时段现场预约号即可办理。</b></td>
					</tr>
					<tr valign="top">
						<td>　三、</td>
						<td>预约人可以预约当天的预约号。同一个身份证号码同一预约日期同一预约业务事项（除抵押权注销登记外），只能预约一次。同一不动产同一预约业务事项，只能预约一次。<b>特别提示:如同一不动产权利人，在同一天办理转移、抵押或变更多套不动产，只需预约一个号即可办理;预约“领取不动产权证书及登记证明”服务事项时，请务必留意受理通知书上的领证日期。</b></td>
					</tr>
					<tr valign="top">
						<td>　四、</td>
						<td>预约申请成功后，预约人须在预约时段开始前10分钟至结束前10分钟内凭预约回执或申请成功短信，并携带身份证明等相关资料到现场取号。如预约时段为9:00-10:00，取号时间为8:50-9:50。如需取消预约，应不晚于预约时段开始前1小时完成。</td>
					</tr>
					<tr valign="top">
						<td>　五、</td>
						<td>资料不符或逾期未取预约号者，视为失约。失约后三天内该预约人及权属证明编号无法再进行预约。</td>
					</tr>
					<tr valign="top">
						<td>　六、</td>
						<td>预约开放时间为7:00-23:00，其余时间停止服务。为防止恶意预约，系统已建立黑名单制度，并对权属证明编号的格式进行了规范。
如：1)深（宝）网预买字（2001）00001号，请输入200100001；2)深房地字2000541807,请输入2000541807；3)粤（2015）深圳市不动产证明第0000164号，请输入0000164。</td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<br/>
			<button onclick="javascript:next()">我接受(下一步)</button>
			<button onclick="javasrcipt:back()">我不接受(返回)</button>
			<br/>
		</div>
	</div>
</body>
</html>