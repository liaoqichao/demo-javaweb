/**
 * 
 */
function T$(i){
	return document.getElementById(i);
}
function T$$(e,p){
	return p.getElementsByTagName(e);
}
var staff_table;
var _row;
function getStaff(){
	var sdepartment=document.getElementById("sdepartment").value;
	$.ajax({
		type:"post",
		url:"./SearchDepartment",
		data:{"sdepartment":$("#sdepartment").val()},
		success:function(data) //把数据保存为json
		{
			staff_table=eval(data);
			callBack(data);
		}
	});
}
function callBack(data){
	var tab=document.getElementById("staff_tablebody");
	tab.innerHTML="";
	var str="";
	
	for(var rowindex=0;rowindex<staff_table.length;rowindex++){
		str=rowindex.toString();
		var tr=tab.insertRow(tab.rows.length-1);
		
		tr.id=str;
		 var td5 = tr.insertCell(-1); 
		 td5.innerHTML = "<input type='checkbox'/>"; 
		
		var td1=tr.insertCell(-1);
		td1.innerHTML=fill_staff(rowindex,"cardNo");
		td1.id="tr"+str;
		
		var td2=tr.insertCell(-1);
		td2.innerHTML=fill_staff(rowindex,"name");
		
		
		var td3=tr.insertCell(-1);
		td3.innerHTML=fill_staff(rowindex,"department");
		
		var td4=tr.insertCell(-1);
		td4.innerHTML=fill_staff(rowindex,"position");
	}
	_row=-1;
}

function add_staff(){
	//遍历所有的input控件即可(除了最后一个全选用的checkbox) 
	var chks = document.getElementsByTagName('input'); 
	var addStr="",temp="";
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
			}
			addStr=addStr+document.getElementById(temp).innerHTML;
			rowNow.parentNode.removeChild(rowNow); 
		} 
	} 
	
	if(addStr==""){
		alert("请选中发送对象");
	}
	else{
		document.getElementById("addStr").value=addStr;
		$.ajax(
				{
					type:"post",
					url:"./SetSendState",
					data:{'addStr':$("#addStr").val(),'documentNo':$("#documentNo").val(),'state':'0'},
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
		seleAll(document.getElementById("staff_table"), true); 
	} else { 
		seleAll(document.getElementById("staff_table"), false); 
	} 
}; 
function seleAll(mailTab, isSel) { 
	for (var i = 0; i < mailTab.rows.length; i++) { 
	var tr = mailTab.rows[i]; 
		tr.cells[0].childNodes[0].checked = isSel; 
	} 
} 

function fill_staff(rowindex,str){
	if(staff_table[rowindex].hasOwnProperty(str)){
		return staff_table[rowindex][str];
	}
	else{
		return null;
	}
}