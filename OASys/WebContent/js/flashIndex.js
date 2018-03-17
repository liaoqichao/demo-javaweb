/**
 * 
 */
function getMessage(){
	var info="";
	//第一个查询有无新消息
	$.ajax({
		type:"post",
		url:"./SearchMessageNo",
		data:{"name":"","messageNo":"-1","state":"1"},
		success:function(data) //把数据保存为json
		{
			info="有"+data+"个新消息,";
			document.getElementById("info").value=info;
		}
	});
	//第二个查询有无新未接收公文
	$.ajax({
		type:"post",
		url:"./SearchDocumentNo",
		data:{"Header":"","state":'0',},
		success:function(data) //把数据保存为json
		{
			info=info+data+"个新文件";
			document.getElementById("info").value=info;
			document.getElementById("message").value=document.getElementById("info").value;
		}
	});
	setTimeout("getMessage()",1000); 
//	alert(document.getElementById("info").value);
}
function getNewMessage(){
	$.ajax({
		type:"post",
		url:"./SearchMessageNo",
		data:{"name":"","messageNo":"-1","state":"1"},
		success:function(data) //把数据保存为json
		{
			messageNo=data;
		}
	});
}
function getNotReceiveFile(){
	$.ajax({
		type:"post",
		url:"./SearchDocumentNo",
		data:{"Header":"","state":'0',},
		success:function(data) //把数据保存为json
		{
			fileNo=data;
		}
	});
}