<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/body.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/titile.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/js/uploadify/uploadify.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/uploadify/jquery.uploadify.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/swfobject.js'/>"></script>
<title>版本更新系统-发布更新</title>
</head>
<body>
<script type="text/javascript">
var i = 0; //用于记录当前上传第几个文件

$(document).ready(function(){
	$('.uploadfile').uploadify({
		'swf' : "<c:url value='/js/uploadify/uploadify.swf'/>", 
		// 要在url设置file（上传文件），fileFileName（上传文件的名字）
		'uploader'  : "<c:url value='/UploadifyServlet'/>", 
		'cancelImg' : "<c:url value='/js/uploadify/uploadify-cancel.png'/>", 
		'fileObjName' : 'file',
		'fileSizeLimit' : 0,
		'multi' : true,
		'queueSizeLimit' : 5,
		'removeCompleted' : true,
		'uploadLimit' : 999,
		'processData' : 'percentage',
		'auto' : false,
		'height' : 20,
		// 只能传静态参数
// 		'formData' : 	{	"version.ver":ver,
// 							"version.description":description, 
// 							"path":path
// 						},
		'buttonText' : '添加上传文件',
		'onQueueComplete': function () {
			alert("上传完成");
			$('#uploadfile').uploadify('cancel'); //删除队列第一个任务
            $('#fileQueue').attr('style', 'visibility :hidden');
        	$('#first-input').val("");
        	var thirdBr = $('#first-input').next().next().next();
        	$(thirdBr).nextAll().remove();
// 			if($('.path').length >1 ){
// 				var lastInput = $('.path')[$('.path').length-1]; // DOM对象input标签
// 				var lastSpan = $('.path-tip')[$('.path-tip').length-1]; // DOM对象span标签
				
// 				$(lastInput).nextAll().remove(); //删除3个空格！
// 				$(lastInput).remove();//删除该元素
// 				$(lastSpan).remove();
// 			} else{
// 				$('.path').val(""); // 设置为空字符串
// 			}

        },
		'onUploadSuccess' : function(file, data, response){
			i++;
			 $("#uploadfile").uploadify('settings', 'formData', 
	            		{ 'ver': $('#vid').val(),'description': $('#description').val(),
//	             			'path':$('.path').val()
	            			'path':$('.path').map(function(){
	            				return this.value;
	            			}).get(i)
	            		}
	            );  
		},
        onUploadError: function (file, errorCode, errorMsg, errorString) {
            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        },
        'onUploadStart': function (file) {  
        	$('#fileQueue').attr('style', 'top:200px;left:400px;width:400px;height :400px;visibility :visible');
        	// 动态绑定 其他参数
            $("#uploadfile").uploadify('settings', 'formData', 
            		{ 'ver': $('#vid').val(),'description': $('#description').val(),
//             			'path':$('.path').val()
            			'path':$('.path').map(function(){
            				return this.value;
            			}).get(i)
            		}
            );  
            
            //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
        }
//         'onCancel' : function(file){
        	
//         },
//         'onClearQueue' : function(queueItemCount){
        	
//         }
	});
	
	$('#btn-addPath').click(function(){
		var $add = $('<span class="path-tip">目标路径：</span><input type="text" class="path" name="path"/><br/><br/><br/>');
		$('#td-path').append($add);
	});
	
	$('#btn-test').click(function(){
// 		var arr = $('.path').map(function(){
// 			return this.value;
// 		}).get();

// 		alert($('.path').map(function(){
// 			return this.value;
// 		}).get(2));  // 下标从0开始

// 		$.each(arr,function(){
// 			alert($(this).val());
// 		});
// 		if($('.path').length >1 ){
// 			var lastInput = $('.path')[$('.path').length-1]; // DOM对象input标签
// 			var lastSpan = $('.path-tip')[$('.path-tip').length-1]; // DOM对象span标签
				
// 			$(lastInput).nextAll().remove(); //删除3个空格！
// 			$(lastInput).remove();//删除该元素
// 			$(lastSpan).remove();
// 		} else{
// 			$('.path').val(""); // 设置为空字符串
// 		}
	$('#first-input').val("");
	var thirdBr = $('#first-input').next().next().next();
	$(thirdBr).nextAll().remove();
	});
	
	$('#btn-submit').click(function(){
		isLogin();
	});
	
	function isLogin(){
		var session_user = document.getElementById("session_user_str");
		if(session_user.value.trim() == ""){
			alert("没有登录，请登录后再操作！");
		} else{
			if(($('#session_user_permissions').val()&2) == 0){
				alert("您没有权限发布新版本");
			} else{
				$('#uploadfile').uploadify('upload','*');
			}
		}
	}
});

</script>
<div>
	<h1 class="title" align="center">发布更新版本</h1>
</div>
<input id="session_user_str" type="hidden" value="${sessionScope.session_user}">
<input id="session_user_permissions" type="hidden" value="${sessionScope.session_user.permissions}">
	<div align="right">
		<a href="<c:url value='/index.jsp'/>">返回主页面</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<c:choose>
			<c:when test="${empty sessionScope.session_user }">
				<a href="<c:url value='/jsps/user/login.jsp'/>">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				用户：${sessionScope.session_user.username }&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<c:url value='/user/userAction_logout.action'/>">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
	</div>
	<br/>
	填写版本号，如果版本号不存在，则新建版本，如果版本号存在，则在原来的版本上追加新的更新文件。查看版本信息的时候发布时间为最新更新的时间。更新描述为所有更新描述（没有换行）。
	更新文件顺序要和输入目标路径匹配。
	<br/>
	<div align="center" id="div-main">
		<table id="table">
			<tr>
				<td>版本号：</td>
				<td><input id="vid" name="version.ver"/></td>
			</tr>
			<tr>
				<td>版本描述：</td>
				<td><textarea rows="5" cols="50" id="description" name="version.description"></textarea></td>
			</tr>
			<tr>
				<td><input id="uploadfile" class="uploadfile" type="file" name="file"/></td>
				<td id="td-path" class="td-path">
					<br/>
					<br/>
					<span class="path-tip">目标路径：</span><input id="first-input" type="text" class="path" name="path"/><br/><br/><br/>
				</td>
			</tr>
			<tr id="tr-btn">
				<td colspan="2" align="center">
					<input id="btn-cancelFist" type="button" value="取消第一个上传文件" 
						onclick="javascript:$('#uploadfile').uploadify('cancel')"/>
					<input id="btn-cancelAll" type="button" value="清空队列" 
						onclick="javascript:$('#uploadfile').uploadify('cancel','*')"/>
						
					<input id="btn-addPath" type="button" value="添加更新文件的目标路径" />
					<input id="btn-submit" type="button" value="发布"/>
<!-- 						onclick="javaScript:$('#uploadfile').uploadify('upload','*')"/> -->
<!-- 					<input id="btn-test" type="button" value="测试"/> -->
				</td>
			</tr>
		</table>
	</div>
</body>
</html>