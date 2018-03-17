var staff;
function load_staff(){
	 $.ajax(
			 {
				 //method:"get", 
				 type:"post",
                 url:"./SearchStaff",
                 data:{},
                 success:function(data) //把数据保存为json
                 { 
                	 staff = eval(data); 
                	 var tab = document.getElementById("staff_tablebody"); 
                	 var str="";	 
                	
      
                	 for (var rowindex = 0; rowindex < staff.length; rowindex++) { 
                		 str=rowindex.toString();
                		 //var tr=document.getElementById(str);
                		 var tr =tab.insertRow(tab.rows.length - 1);//插入到末二行，最后一行要给全选那一行保留着
                		 
                		 tr.id=str;
                		 var td1 = tr.insertCell(-1); 
                		 td1.innerHTML = "<input type='checkbox'/>"; 
                		 
                		 
                		 var td2 = tr.insertCell(-1); 
                		 td2.innerHTML=fillstaff(rowindex,"cardNo"); 
                		 td2.id="tr"+str;
                		 
                		 var td3 = tr.insertCell(-1); 
                		 td3.innerHTML=fillstaff(rowindex,"name");    
                		 
                		 var td4 = tr.insertCell(-1); 
                		 td4.innerHTML=fillstaff(rowindex,"department"); 
                		 
                		 var td5 = tr.insertCell(-1); 
                		 td5.innerHTML=fillstaff(rowindex,"position"); 
                	 }
                 }
             });
}
function reloadstaff(){
	var staffTime=document.getElementById("staffTime");
	if(staffTime.value=="0"){
		staffTime.value="1";
	}
	else{
		staffTime.value="0";
	}
	var tab = document.getElementById("staff_tablebody"); 
	tab.innerHTML="";
	loadstaff();
}

function maintainmodify(){
	var employeecard=document.getElementById("kw").value;
	if(employeecard==""){
		alert("请输入条件");
	}
	else{
		var loc="modifyinfo.jsp?employeecard="+employeecard;
		window.location.href=loc;
	}
};
function selAll() { 
	if (document.getElementById("selA").checked == true) { 
		seleAll(T$("staff_table"), true); 
	} else { 
		seleAll(T$("staff_table"), false); 
	} 
}; 
function modify(){
	//遍历所有的input控件即可(除了最后一个全选用的checkbox) 
	var chks = document.getElementsByTagName('input'); 
	var employeecard="",temp="";
	for (var i = chks.length - 1; i >=0; i--) { 
		var chk = chks[i]; 
		if (chk.checked==true) { 
			//选中行删除处理 
			if(chk.value=="1"){
				continue;
			}
			var rowNow = chk.parentNode.parentNode;   //获得当前行tr
			temp="tr"+rowNow.id;     //获得tr的id。详细看loadTable()。document.getElementById(temp).innerHTML就是employeecard
			employeecard=document.getElementById(temp).innerHTML;
			var loc="presonalinfopage/modifyinfo.jsp?employeecard="+employeecard;
			window.location.href=loc;
		}
	}
}


//添加新职员的签发权限
function add_staff(){
	window.location.href="add_staff.jsp";
}
	//删除所有的选中的 
 function delete_staff() { 
	//遍历所有的input控件即可(除了最后一个全选用的checkbox) 
	var chks = document.getElementsByTagName('input'); 
	var delStr="",temp="";
	for (var i = chks.length - 1; i >=0; i--) { 
		var chk = chks[i]; 
		if (chk.checked==true) { 
			//选中行删除处理 
			if(chk.value=="1"){
				continue;
			}
			var rowNow = chk.parentNode.parentNode; 
			temp="tr"+rowNow.id;
			if(delStr!=""){
				delStr=delStr+",";
			}
			delStr=delStr+document.getElementById(temp).innerHTML;
			rowNow.parentNode.removeChild(rowNow); 
		} 
		
	} 
	document.getElementById("delStr").value=delStr;
	del();
}; 

function del(){
	 $.ajax(
			 {
				 //method:"get", 
				 type:"post",
                 url:"./DelStaff",
                 data:{"delStr":$("#delStr").val(),"department":fillstaff(0,"department") },
                 success:function(data) //把数据保存为json
                 { 
                	alert(data);
                 }
             });
}

function seleAll(mailTab, isSel) { 
	for (var i = 0; i < mailTab.rows.length; i++) { 
	var tr = mailTab.rows[i]; 
		tr.cells[0].childNodes[0].checked = isSel; 
	} 
} 

function fillstaff(rowindex,str){	
	if(staff[rowindex].hasOwnProperty(str)){
		return  staff[rowindex][str];
	}
	else{
		return "";
	}
}