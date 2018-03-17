/**
 * 
 */
var message_table;
var _row;			//行数
function searchMessage(){
	var tab=document.getElementById("message_tablebody");
	var state="0";
	if(document.getElementById("state").checked==true){
		state=1;
	}
	tab.innerHTML="";
	var name=$("#nam").val();
	$.ajax({
		type:"post",
		url:"./SearchMessage",
		data:{"name":$("#nam").val(),"messageNo":"-1","state":state},
		success:function(data) //把数据保存为json
		{
			if(data=="0"){
				var message="没有来自"+name+"信息";
				alert(message);
			}
			else{
				callBack(data);
			}
		}
	});
}
function callBack(data){
	message_table=eval(data);
	var tab=document.getElementById("message_tablebody");
	var str="";
	
	for(var rowindex=0;rowindex<message_table.length;rowindex++){
		str=rowindex.toString();
		var tr=tab.insertRow(tab.rows.length-1);
		
		tr.id=str;
		var td1=tr.insertCell(-1);
		td1.innerHTML=fill_message(rowindex,"sendName");
		
		var td2=tr.insertCell(-1);
		td2.innerHTML=fill_message(rowindex,"sendDepartment");
		
		var td3=tr.insertCell(-1);
		var img="<img src='/OASys/images/icon/file.ico' onclick='operate("+rowindex+")'>";
		td3.id="tr"+str;
		td3.innerHTML=img;
		
		var td4=tr.insertCell(-1);
		var state="";
		if(fill_message(rowindex,"state")=="0"){
			state="未阅读";
		}
		else{
			state="已阅读";
		}
		td4.innerHTML=state;
		td4.id="state"+str;
	}
	_row=-1;
}

function displayMessage(){
	var tab=document.getElementById("message_tablebody");
	tab.innerHTML="";
	var messageNo=$("#messageNo").val();
	$.ajax({
		type:"post",
		url:"./SearchMessage",
		data:{"messageNo":$("#messageNo").val(),"name":"-1"},
		success:function(data) //把数据保存为json
		{
			callBack(data);
		}
	});
}
function operate(rowindex){
	if(_row==rowindex){
		var row=rowindex.toString();
		var temp="#tr"+row;
		var position=$(temp).offset();
		var left=position.left+43;
		var top=position.top+10;
		document.getElementById("operate").style.top=top+"px";
		document.getElementById("operate").style.left=left+"px";
		if(document.getElementById("operate").style.display=="block"){
			document.getElementById("operate").style.display="none";
		}
		else{
			document.getElementById("operate").style.display="block";
		}
	}
	else{
		_row=rowindex;
		var row=rowindex.toString();
		var temp="#tr"+row;
		var position=$(temp).offset();
		var left=position.left+43;
		var top=position.top+10;
		
		document.getElementById("operate").style.display="block";
		document.getElementById("operate").style.top=top+"px";
		document.getElementById("operate").style.left=left+"px";
	}
}

function openMessage(){
	document.getElementById("operate").style.display="none";
	document.getElementById("receiveMessage").value=fill_message(_row,"message");
	document.getElementById("sendCardNo").value=fill_message(_row,"sendCardNo");
	document.getElementById("messageNo").value=fill_message(_row,"messageNo");
	document.getElementById("sendName").value=fill_message(_row,"sendName");
	document.getElementById("sendCardNo").value=fill_message(_row,"sendCardNo");
	if(fill_message(_row,"state")=="0"){
		$.ajax({
			type:"post",
			url:"./ChangeMessageState",
			data:{"messageNo":$("#messageNo").val()},
			success:function(data) //把数据保存为json
			{
				message_table[_row]["state"]=data;
			}
		});
	}
	
	var str=_row.toString();
	document.getElementById("state"+_row).innerHTML="已阅读";
	
}

function sendMessage(){
//	document.getElementById("sendCardNo").value=fill_message(_row,"sendCardNo");
	var message=$("#sendMessage").val();
	var cardNo=$("#sendCardNo").val();
	if(message==""||cardNo==""){
		alert("请输入发送账号和发送内容");
	}
	else{
		$.ajax({
			type:"post",
			url:"./SendMessage",
			data:{"sendCardNo":$("#sendCardNo").val(),"message":$("#sendMessage").val()},
			success:function(data) //把数据保存为json
			{
				if(data==1){
					alert("发送成功了");
					var row=document.getElementById("tr"+_row);
					var rowNow =row.parentNode; 
					rowNow.parentNode.removeChild(rowNow); 
					document.getElementById("operate").style.display="none";
				}
				else{
					alert("发送失败了");
				}
			}
		});
	}
	
}

function fill_message(rowindex,str){
	if(message_table[rowindex].hasOwnProperty(str)){
		return message_table[rowindex][str];
	}
	else{
		return null;
	}
}
