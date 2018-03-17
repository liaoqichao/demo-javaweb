function menudisplay(){
	
	$.ajax(
			 {
				 //method:"get", 
				type:"post",
                url:"./GetAuthority",
                data:{'employeecard':$("#searchNum").val()},
                success:function(data)
                {
                	var menutable= eval(data);
                	var length=menutable.length;
                	var temp;
                	i=1;
                	while(i<length){
                		temp=menutable[i];
                		if(temp>1205&&temp<1222){
                			if(temp<1211){
                				document.getElementById(1250).style.display="block";
                			}
                			else if(temp<1215){
                				document.getElementById(1260).style.display="block";
                			}
                			else if(temp<1219){
                				document.getElementById(1270).style.display="block";
                			}
                			else{
                				document.getElementById(1280).style.display="block";
                			}
                		}
                		document.getElementById(temp).style.display="block";
                		i++;
                	}
                }
            });
}