/**
 * 
 */
var document_table;
var _row;			//行数
function search_send(){
	var tab=document.getElementById("document_tablebody");
	tab.innerHTML="";
	var searchHeader=$("#Header").val();
	var state=$("#state").val();
	$.ajax({
		type:"post",
		url:"./SearchSendState",
		data:{},
		success:function(data) //把数据保存为json
		{
			callBack(data);
		}
	});
}
function callBack(data){
	document_table=eval(data);
	var tab=document.getElementById("document_tablebody");
	var str="";
	
	for(var rowindex=0;rowindex<document_table.length;rowindex++){
		str=rowindex.toString();
		var tr=tab.insertRow(tab.rows.length-1);
		
		tr.id=str;
		var td1=tr.insertCell(-1);
		td1.innerHTML=fill_document(rowindex,"documentHeader");
		
		var td2=tr.insertCell(-1);
		var img="<img src='/OASys/images/icon/file.ico' onclick='operate("+rowindex+")'>";
		td2.id="tr"+str;
		td2.innerHTML=img;
		
		var td3=tr.insertCell(-1);
		td3.innerHTML=fill_document(rowindex,"receiver_name");
		
		var td4=tr.insertCell(-1);
		td4.innerHTML=fill_document(rowindex,"receiver_department");
		
		var td5=tr.insertCell(-1);
		td5.innerHTML=fill_document(rowindex,"receiver_position");
		
		
		var td6=tr.insertCell(-1);
		var temp="";
		if(fill_document(rowindex,"state")=="0"){
			temp="未接收";
		}
		else if(fill_document(rowindex,"state")=="1"){
			temp="已接收";
		}
		else if(fill_document(rowindex,"state")=="2"){
			temp="已处理";
		}
		else if(fill_document(rowindex,"state")=="5"){
			temp="需签发";
		}
		else if(fill_document(rowindex,"state")=="6"){
			temp="已签发";
		}
		else {
			temp="未通过";
		}
		td6.innerHTML=temp;	
		
		var td7=tr.insertCell(-1);
		var time=fill_document(rowindex,"time");
		var month=time.month+1;
		var year=time.year+1900;
		td7.innerHTML=year+"-"+month+"-"+time.date+" "+time.hours+":"+time.minutes+":"+time.seconds;
	}
	_row=-1;
}

function operate(rowindex){
	document.getElementById("documentNo").value=fill_document(rowindex,"documentNo");
	if(_row==rowindex){
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

function del(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	document.getElementById("documentNo").value=document.getElementById("documentNo").value;
	$.ajax({
		type:"post",
		url:"./DelDocument",
		data:{"documentNo":$("#documentNo").val(),},
		success:function(data) //把数据保存为json
		{
			if(data==1){
				alert("删除成功");
				var row=document.getElementById("tr"+_row);
				var rowNow =row.parentNode; 
				rowNow.parentNode.removeChild(rowNow); 
				document.getElementById("operate").style.display="none";
			}
			else{
				alert("删除失败了");
			}
		}
	});
}

function writeRemarks(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	document.getElementById("stateNo").value=fill_document(_row,"stateNo");
	var stateNo=document.getElementById("stateNo");
	if(fill_document(_row,"stateNo")=="0"){//如果是未接收，改变状态为已接收
		$.ajax({
			type:"post",
			url:"./ChangeState",
			data:{"stateNo":$("#stateNo").val(),"state":"1",},
			success:function(data) //把数据保存为json
			{
				if(data==1){
					alert("改变成功");
					var row=document.getElementById("tr"+_row);
					var rowNow =row.parentNode; 
					rowNow.parentNode.removeChild(rowNow); 
					document.getElementById("operate").style.display="none";
				}
				else{
					alert("改变失败了");
				}
				
			}
		});
	}
	
	window.location.href="process.jsp?documentNo="+fill_document(_row,"documentNo")+"&documentHeader="+fill_document(_row,"documentHeader");
}

function fill_document(rowindex,str){
	if(document_table[rowindex].hasOwnProperty(str)){
		return document_table[rowindex][str];
	}
	else{
		return null;
	}
}
