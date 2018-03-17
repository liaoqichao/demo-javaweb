$(document).ready(function(){
  $("#searchbt").click(function(){
    $(this).hide();
  });
});




/*$(document).ready(function(){
	  $("#composebtn").click(function(){
		  this.hidden();
		 Employeer employeer=session.getAttribute("employeer");
	 if(employeer!=null&&employeer.isLogin()){
			 String employeecard=employeer.getEmployeecard();
		  	$.ajax(
					 {
						 //method:"get", 
						 type:"post",
	                     url:"./Checkauthority",
	                     data:{'employeecard':employeecard},
	                     success:function(data)
	                     {
	                    	 if(data==true)
	                         {
	                    		ArrayList employeerinfo=session.getAttribute("employeerinfo");
	                    		$("#name").value=employeerinfo.get(3);
	                         }
	                         else {
	                        	 window.location.href="../home.jsp";
	                         }
	                     }
	                 });
		 };
	  });
});*/
		 
	                     
	                 



	