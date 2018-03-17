function checkInput(){
		var acc=document.getElementById("LoginAcc");	
		var pwd=document.getElementById("LoginPwd");	
		if(acc.value=="")	{	
			var msg=document.getElementById("msg");
			msg.className="login_box_msg_display";
			msg.innerHTML="您还没输入帐号";
			document.forms.loginform.LoginAcc.focus();
			return false;	
			}	
		else if(pwd.value==""){		
			var msg=document.getElementById("msg");
			msg.innerHTML="您还没有输入密码";
			document.forms.loginform.LoginPwd.focus();
			return false;	
			}
		else{
			 $.ajax(
					 {
						 //method:"get", 
						 type:"post",
	                     url:"./LoginServlet",
	                     data:{'LoginAcc':$("#LoginAcc").val(),'LoginPwd':$("#LoginPwd").val()},
	                     success:function(data)
	                     {
	                    	 if(data==-1)
	                         {
	                    		document.getElementById("LoginAcc").value="";
	                    		document.getElementById("LoginPwd").value="";
	                    		document.forms.loginform.LoginAcc.focus();
	                         	document.getElementById("msg").innerHTML="帐号不存在";
	                         }
	                         else if(data==0)
	                         {
	                        	 document.getElementById("LoginPwd").value="";
	                        	 document.forms.loginform.LoginPwd.focus();
	                        	 document.getElementById("msg").innerHTML="密码错误";
	                         }
	                         else{	
	                        	document.getElementById("msg").innerHTML="";
		                    	document.getElementById("LoginPwd").value="";
		                    	 document.getElementById("msg").innerHTML="正确";
		                    	 window.location.href="../index.jsp";
	                         }
	                     }
	                 });
	        return false;
		};
		return true;
	};
	
