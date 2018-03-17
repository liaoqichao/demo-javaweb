/**
 * 
 */
function T$(i){
	return document.getElementById(i);
}
function T$$(e,p){
	return p.getElementsByTagName(e);
}
var state_table;
var _row;
function getState(){
	var header=document.getElementById("documentHeader").value;
	$.ajax({
		type:"post",
		url:"./SearchState",
		data:{"documentHeader":header},
		success:function(data) //把数据保存为json
		{
			state_table=eval(data);
			callBack(data);
		}
	});
}
function callBack(data){
	var tab=document.getElementById("state_tablebody");
	tab.innerHTML="";
	var str="";
	
	for(var rowindex=0;rowindex<state_table.length;rowindex++){
		str=rowindex.toString();
		var tr=tab.insertRow(tab.rows.length-1);
		
		tr.id=str;
		var td1 = tr.insertCell(-1); 
		td1.innerHTML = "<input type='checkbox'/>"; 
		
		var td2=tr.insertCell(-1);
		td2.innerHTML=fill_state(rowindex,"documentHeader");
			
		var td3=tr.insertCell(-1);
		td3.innerHTML=fill_state(rowindex,"receiver_cardNo");
		td3.id="tr"+str;
		
		var td4=tr.insertCell(-1);
		td4.innerHTML=fill_state(rowindex,"receiver_name");
		
		
		var td5=tr.insertCell(-1);
		td5.innerHTML=fill_state(rowindex,"receiver_department");
		
		var td6=tr.insertCell(-1);
		td6.innerHTML=fill_state(rowindex,"receiver_position");
		
		
		var td7=tr.insertCell(-1);
		td7.innerHTML=fill_state(rowindex,"send_name");
		
		
		var td8=tr.insertCell(-1);
		td8.innerHTML=fill_state(rowindex,"send_department");
		
		var td9=tr.insertCell(-1);
		td9.innerHTML=fill_state(rowindex,"send_position");
		
		var td10=tr.insertCell(-1);
		var state=fill_state(rowindex,"state");
		var temp="";
		if(state=="0"){
			temp="未接收";
		}
		else if(state="1"){
			temp="已接收";
		}
		else if(state="2"){
			temp="有备注";
		}
		else if(state="5"){
			temp="未签发";
		}
		else if(state="6"){
			temp="已签发"
		}
		else if(state="7"){
			temp="未通过";
		}
		else {
			temp="未接收";
		}
		td10.innerHTML=temp;
		
		var td11=tr.insertCell(-1);
		var time=fill_state(rowindex,"time");
		var year=time.year+1900;
		td11.innerHTML=year+"-"+time.month+"-"+time.day+" "+time.hours+":"+time.minutes+":"+time.seconds;
	}
	_row=-1;
}


function add_state(){
	//遍历所有的input控件即可(除了最后一个全选用的checkbox) 
	var chks = document.getElementsByTagName('input'); 
	var addStr="",temp="",name="";
	for (var i = chks.length - 1; i >=0; i--) { 
		var chk = chks[i]; 
		if (chk.checked==true) { 
			//选中行删除处理 
			if(chk.value=="1"){
				continue;
			}
			var rowNow = chk.parentNode.parentNode; 
			temp="tr"+rowNow.id;
			if(addStr!=""){
				addStr=addStr+",";
				name=name+",";
			}
			addStr=addStr+document.getElementById(temp).innerHTML;
			name=name+fill_state(rowNow.id,"name");
			rowNow.parentNode.removeChild(rowNow); 
		} 
	} 
	
	if(addStr==""){
		alert("请选中催办对象");
	}
	else{
		document.getElementById("addStr").value=addStr;
		var header=$("#documentHeader").val();
		$.ajax(
				{
					type:"post",
					url:"./PressureDocument",
					data:{'addStr':$("#addStr").val(),'Header':header,'name':name},
					success:function(data){
						if(data=="0"){
							alert("帐号不存在！");
						}
						else{
							alert("发送成功");
						}
					}
				});
	}
}

function selAll() { 
	if (document.getElementById("selA").checked == true) { 
		seleAll(document.getElementById("state_table"), true); 
	} else { 
		seleAll(document.getElementById("state_table"), false); 
	} 
}; 
function seleAll(mailTab, isSel) { 
	for (var i = 0; i < mailTab.rows.length; i++) { 
	var tr = mailTab.rows[i]; 
		tr.cells[0].childNodes[0].checked = isSel; 
	} 
} 

function fill_state(rowindex,str){
	if(state_table[rowindex].hasOwnProperty(str)){
		return state_table[rowindex][str];
	}
	else{
		return null;
	}
}