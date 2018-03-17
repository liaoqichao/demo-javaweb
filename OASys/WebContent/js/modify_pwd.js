var staff;
function modify_pwd(){
	var npwd=document.getElementById("newPwd");
	var cpwd=document.getElementById("confirmPwd");
	var pwd=document.getElementById("pwd");
	var msg=document.getElementById("msg");
	if(pwd.value=="")	{	
		msg.innerHTML="您还没输入原密码";
		document.forms.pwd_form.pwd.focus();
		}	
	else if(npwd.value==""){		
		msg.innerHTML="您还没有输入新密码";
		document.forms.pwd_form.newPwd.focus();
	}
	else if(cpwd.value==""){		
		msg.innerHTML="请第二次输入新密码";
		document.forms.pwd_form.confirmPwd.focus();
	}
	else if(pwd.value==npwd.value){
		msg.innerHTML="原密码和新密码一致";
		nPwd.value="";
		pwd.value="";
	}
	else if(npwd.value==cpwd.value){
		 $.ajax(
				 {
					 //method:"get", 
					 type:"post",
	                 url:"./Modifypwd",
	                 data:{"pwd":pwd.value,"newPwd":npwd.value},
	                 success:function(data) //把数据保存为json
	                 { 
	                	 alert(data);
	                 }
	            });
		
		}
		else{
			msg.innerHTML="俩次输入的密码不一致";
			npwd.value="";
			cpwd.value="";
		}
	}
function get_info(){
	 $.ajax(
			 {
				 //method:"get", 
				 type:"post",
                 url:"./GetStaff",
                 data:{"searchCardNo":"-1"},
                 success:function(data) //把数据保存为json
                 { 
                	 if(data=="0"){
							alert("帐号不存在！");
						}
						else{
							staff=eval(data);
							document.getElementById("nam").innerHTML=fill_staff(0,"department")+"的"+fill_staff(0,"name")+"正在修改密码...";
						}
				}    
            });
}
function fill_staff(rowindex,str){	
	if(staff[rowindex].hasOwnProperty(str)){
		return  staff[rowindex][str];
	}
	else{
		return "";
	}
}
