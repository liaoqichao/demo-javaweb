var issue;
function load_issue(){
	 $.ajax(
			 {
				 //method:"get", 
				 type:"post",
                 url:"./IssueInquiry",
                 data:{'Login':$("#Login").val(),},
                 success:function(data) //把数据保存为json
                 { 
                	 issue = eval(data); 
                	 var tab = document.getElementById("issue_tablebody"); 
                	 var str="";	 
                	
      
                	 for (var rowindex = 0; rowindex < issue.length; rowindex++) { 
                		 str=rowindex.toString();
                		 //var tr=document.getElementById(str);
                		 var tr =tab.insertRow(tab.rows.length - 1);//插入到末二行，最后一行要给全选那一行保留着
                		 
                		 tr.id=str;
                		 var td1 = tr.insertCell(-1); 
                		 td1.innerHTML = "<input type='checkbox'/>"; 
                		 
                		 
                		 var td2 = tr.insertCell(-1); 
                		 td2.innerHTML=fillissue(rowindex,"cardNo"); 
                		 td2.id="tr"+str;
                		 
                		 var td3 = tr.insertCell(-1); 
                		 td3.innerHTML=fillissue(rowindex,"name");    
                		 
                		 var td4 = tr.insertCell(-1); 
                		 td4.innerHTML=fillissue(rowindex,"department"); 
                		 
                		 var td5 = tr.insertCell(-1); 
                		 td5.innerHTML=fillissue(rowindex,"position"); 
                	 }
                 }
             });
}
function reloadissue(){
	var issueTime=document.getElementById("issueTime");
	if(issueTime.value=="0"){
		issueTime.value="1";
	}
	else{
		issueTime.value="0";
	}
	var tab = document.getElementById("issue_tablebody"); 
	tab.innerHTML="";
	loadissue();
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
		seleAll(T$("issue_table"), true); 
	} else { 
		seleAll(T$("issue_table"), false); 
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
function add_issue(){
	window.location.href="add_issue.jsp";
}
	//删除所有的选中的 
 function delete_issue() { 
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
				delStr=delStr+" or ";
			}
			delStr=delStr+document.getElementById(temp).innerHTML;
			rowNow.parentNode.removeChild(rowNow); 
		} 
		
	} 
	document.getElementById("delStr").value=delStr;
	del();
}; 

function del(){
	
	//T$("kw").value=T$("delStr").value;
	 $.ajax(
			 {
				 //method:"get", 
				 type:"post",
                 url:"./DelIssue",
                 data:{'delStr':$("#delStr").val()},
                 success:function(data) //把数据保存为json
                 { 
                	 if(data==1){
                		 alert("删除成功！");
                	 }
                	 else {
                		 alert("删除失败！");
                	 }
                 }
             });
	}

function seleAll(mailTab, isSel) { 
	for (var i = 0; i < mailTab.rows.length; i++) { 
	var tr = mailTab.rows[i]; 
		tr.cells[0].childNodes[0].checked = isSel; 
	} 
} 

function fillissue(rowindex,str){	
	if(issue[rowindex].hasOwnProperty(str)){
		return  issue[rowindex][str];
	}
	else{
		return "";
	}
}