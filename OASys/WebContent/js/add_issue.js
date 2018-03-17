/**
 * 
 */
var staff;
function getStaff(){
	var cardNo=$("#searchCardNo").val();
	if(cardNo!=""){
		$.ajax(
				{
					type:"post",
					url:"./GetStaff",
					data:{'searchCardNo':$("#searchCardNo").val(),},
					success:function(data){
						if(data=="0"){
							alert("帐号不存在！");
						}
						else{
							staff=eval(data);
							
							document.getElementById("cardNo").value=fill_staff(0,"cardNo");
							document.getElementById("name").value=fill_staff(0,"name");
							document.getElementById("department").value=fill_staff(0,"department");
							document.getElementById("position").value=fill_staff(0,"position");
							
						}
					}
				});
	}
	else{
		alert("请输入正确的卡号！");
	}
}
function fill_staff(rowindex,str){	
	if(staff[rowindex].hasOwnProperty(str)){
		return  staff[rowindex][str];
	}
	else{
		return "";
	}
}