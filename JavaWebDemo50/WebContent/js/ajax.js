/**
 * 
 */

/**
 * 创建异步对象XMLHttpRequest
 */
function createXMLHttpRequest(){
	try{
		return new XMLHttpRequest();	//支持大多数浏览器
	}catch (e){
		try {
			return new ActiveXObject("Msxml2,XMLHTTP");	//支持IE6.0
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft,XMLHTTP"); //支持IE5.5及以下
			} catch (e) {
				alert("你用什么浏览器???");
				throw e;
			}
		}
	}
}