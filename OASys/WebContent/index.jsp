<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBliC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主界面</title>
 <% 
String path = request.getContextPath(); 
 String basePath = request.getScheme() + "://" 
 + request.getServerName() + ":" + request.getServerPort() 
 + path + "/"; 
 %> 
 <base href="<%=basePath%>"> 
<script src="js/jquery-1.11.2.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<liNK rel=stylesheet type=text/css href="css/common.css">
<liNK rel=stylesheet type=text/css href="css/getcss.css">
<script src="js/flashIndex.js"></script>
<script src="js/personalsearch.js"></script>
<script src="js/menuControl.js"></script>
<STYLE type=text/css>
HTML {
	OVERFLOW: auto;
	OVERFLOW-X: hidden;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
	PADDING-LEFT: 0px;
	MARGIN: 0px;
	PADDING-RIGHT: 0px
}

BODY {
	OVERFLOW: auto;
	OVERFLOW-X: hidden;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
	PADDING-LEFT: 0px;
	MARGIN: 0px;
	PADDING-RIGHT: 0px
}

HTML {
	HEIGHT: 100%
}

#mainFrame {
	POSITION: absolute;
	_position: relative
}
</STYLE>
</head>
<body>
<input type="hidden" id=info name=info>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">OASys</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">工作台 <span class="sr-only">(current)</span></a></li>
        <li><a href="#"></a></li>
        
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">退出</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<BODY class=frame_class>
	
	<div id=resize style="HEIGHT: 90%; WIDTH: 100%; ">
		<div id=backsend_container class=backsendsdiv>
			<SPAN id=backsend_info class=backsendstatus style="DISPLAY: none">
			</SPAN>
			<div id=backsend_panel style="DISPLAY: none"></div>
		</div>

		<div id=seplineTd class=topline_height>
			<div class=topline></div>
		</div>

		<div class="fdbody bodybgbt"></div>
		<div id=leftPanel class=newskinbody>
			<div id=navBardiv></div>
			<div class="lanrenzhijia">
				<div id=searchNum></div>
				<ul>
					<li><A id=1 accessKey=c
						href="staffmanage/staffmanage.jsp" target=mainFrame>用户管理</A>
					</li>
					<li><A id=2 accessKey=c href="issue/issue.jsp"
						target=mainFrame>签发管理</A>
					</li>
					<li><A id=3 accessKey=c href="draft/draft.jsp"
						target=mainFrame>公文起草</A>
					</li>
					<li><A id=4 accessKey=c href="not_receive/search_not_receive.jsp"
						target=mainFrame>未接收</A>
					</li>
					<li><A id=5 accessKey=c href="received/search_received.jsp"
						target=mainFrame>已接收</A>
					</li>
					<li><A id=5 accessKey=c href="send/search_send.jsp"
						target=mainFrame>已发送</A>
					</li>
					<li><A id=6 accessKey=c href="search_state/search_state.jsp"
						target=mainFrame>公文状态</A>
					</li>
					<li><A id=7 accessKey=c href="message/search_message.jsp"
						target=mainFrame>消息</A>
					</li>
					<li><div class="alert alert-warning" role="alert">
							<textarea rows="4" cols="5" id=message name=message style="width:150px;"></textarea>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<script>
$(function(){
	var lanrenzhijia = 1; // 默认值为0，二级菜单向下滑动显示；值为1，则二级菜单向上滑动显示
	if(lanrenzhijia ==0){
		$('.lanrenzhijia li').hover(function(){
			$('.second',this).css('top','30px').show();
		},function(){
			$('.second',this).hide();
		});
	}else if(lanrenzhijia ==1){
		$('.lanrenzhijia li').hover(function(){
			$('.second',this).css('bottom','30px').show();
		},function(){
			$('.second',this).hide();
		});
	}
});
</script>
		<div id=mainFrameContainer>
			<IFRAME id=mainFrame
				onload="setMainFrameScale();MarkTime('fm26',S('mainFrame').contentWindow,'fm25');"
				src="files/ajax_proxy.htm" name=mainFrame> </IFRAME>
		</div>
	</div>
<script language="javascript" for="window" event="onload"> 
if(document.readyState=="complete"){ 
	getMessage();
} </script>
</BODY>
</body>
</html>