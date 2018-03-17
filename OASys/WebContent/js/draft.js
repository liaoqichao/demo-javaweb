/**
 * 
 */
var document_table;
var _row;			//行数
function searchDocument(){
	var tab=document.getElementById("document_tablebody");
	tab.innerHTML="";
	var searchHeader=$("#Header").val();
	$.ajax({
		type:"post",
		url:"./SearchDraft",
		data:{"Header":$("#Header").val()},
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
		
		var td1=tr.insertCell(-1);
		td1.innerHTML=fill_document(rowindex,"documentHeader");
		
		var td2=tr.insertCell(-1);
		var img="<img src='/OASys/images/icon/file.ico' class='test' onclick='operate("+rowindex+")'>";
		td2.id="tr"+str;
		td2.innerHTML=img;
		
		var td3=tr.insertCell(-1);
		td3.innerHTML=fill_document(rowindex,"draftsmanCardNo");
		
		var td4=tr.insertCell(-1);
		td4.innerHTML=fill_document(rowindex,"issuerCardNo");
		
		var td5=tr.insertCell(-1);
		var state;
		if(fill_document(rowindex,"state")=="0"){
			state="未接收";
		}
		else if(fill_document(rowindex,"state")=="1"){
			state="已接收";
		}
		else if(fill_document(rowindex,"state")=="2"){
			state="已处理";
		}
		else if(fill_document(rowindex,"state")=="5"){
			state="需签发";
		}
		else if(fill_document(rowindex,"state")=="6"){
			state="已签发";
		}
		else {
			state="未通过";
		}
		td5.innerHTML=state;
	}
	_row=-1;
}

function operate(rowindex){
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
	if(fill_document(_row,"state")=="5"){
		document.getElementById("remark").style.display="none";
	}
	else{
		document.getElementById("remark").style.display="block";
	}
	document.getElementById("documentNo").value=fill_document(_row,"documentNo");
	document.getElementById("state").value=fill_document(_row,"state");
	document.getElementById("documentHeader").value=fill_document(_row,"documentHeader");
}

function add_issue(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	document.getElementById("documentNo").value=fill_document(_row,"documentNo");
	var url_save=document.getElementById("url_save").value
	if(fill_document(_row,"state")=="5"){
		window.location.href=url_save+"/draft/form_exam.jsp?documentNo="+fill_document(_row,"documentNo");
	}
	else{
		alert("该文件已通过");
	}
}
function add_receive(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	document.getElementById("documentNo").value=fill_document(_row,"documentNo");
	//判断是否通过了，不通过的不能发送
	if(fill_document(_row,"state")=="7"){
		alert("需要签发的文件，没有通过，不能发送给他人");
	}
	else{
		var url_save=document.getElementById("url_save").value
		window.location.href=url_save+"/draft/add_receive.jsp?documentNo="+fill_document(_row,"documentNo");
	}
}

function del(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	if(fill_document(_row,"state")=="6"){
		alert("通过签发的文件，不能删除");
	}
	else{
		document.getElementById("documentNo").value=fill_document(_row,"documentNo");
		$.ajax({
			type:"post",
			url:"./DelDraftFile",
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
}

function openDocument(){
	var state=document.getElementById("state").value;
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	var url_save=document.getElementById("url_save").value;
	if(state=="5"||state=="7"){
		window.location.href=url_save+"/draft/downDraft.jsp?documentNo="+document.getElementById("documentNo").value;
	}
	else{
		window.location.href=url_save+"/draft/downDocument.jsp?documentNo="+document.getElementById("documentNo").value;
	}
}

function fill_document(rowindex,str){
	if(document_table[rowindex].hasOwnProperty(str)){
		return document_table[rowindex][str];
	}
	else{
		return null;
	}
}
