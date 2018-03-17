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
		url:"./SearchDocument",
		data:{"Header":$("#Header").val(),"state":'0',},
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
		if(fill_document(rowindex,"draftsman_position")=="部长"){
			td3.innerHTML=fill_document(rowindex,"draftsman_department")+"部长"+fill_document(rowindex,"draftsman_name");
		}
		else{
			td3.innerHTML=fill_document(rowindex,"draftsman_department")+"的"+fill_document(rowindex,"draftsman_name");
		}

		var td4=tr.insertCell(-1);
		if(fill_document(rowindex,"issue_name")=="-1"){
			td4.innerHTML="暂未签发";
		}
		else{
			if(fill_document(rowindex,"issue_department")=="部长"){
				td4.innerHTML=fill_document(rowindex,"issue_department")+"部长"+fill_document(rowindex,"issue_name");
			}
			else{
				td4.innerHTML=fill_document(rowindex,"issue_department")+"的"+fill_document(rowindex,"issue_name");
			}
		}
		
		
		var td5=tr.insertCell(-1);
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
		td5.innerHTML=temp;	
		
		var td6=tr.insertCell(-1);
		var time=fill_document(rowindex,"time");
		var month=time.month+1;
		var year=time.year+1900;
		td6.innerHTML=year+"-"+month+"-"+time.date+" "+time.hours+":"+time.minutes+":"+time.seconds;

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
}

function openDocument(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	document.getElementById("stateNo").value=fill_document(_row,"stateNo")
	var stateNo=document.getElementById("stateNo");
	if(fill_document(_row,"state")=="0"){
		$.ajax({
			type:"post",
			url:"./ChangeState",
			data:{"stateNo":$("#stateNo").val(),"state":"1",},
			success:function(data) //把数据保存为json
			{
				document_table[_row]["state"]="1";
			}
		});
	}
	
	window.location.href="downDocument.jsp?documentNo="+fill_document(_row,"documentNo");
}

function issueOP(){//完成签发
	window.location.href="complete_issue.jsp?documentNo="+fill_document(_row,"documentNo");
}

function add_receive(){
	document.getElementById("documentNo").value=fill_document(_row,"documentNo");
	window.location.href="add_receive.jsp?documentNo="+fill_document(_row,"documentNo");
}
function handle_issue(){
	window.location.href="handle_issue.jsp?documentNo="+fill_document(_row,"documentNo")+"&documentHeader="+fill_document(_row,"documentHeader");
}
function del(){
	if(document.getElementById("operate").style.display=="block"){
		document.getElementById("operate").style.display="none";
	}
	document.getElementById("documentNo").value=fill_document(_row,"documentNo");
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

function fill_document(rowindex,str){
	if(document_table[rowindex].hasOwnProperty(str)){
		return document_table[rowindex][str];
	}
	else{
		return null;
	}
}
