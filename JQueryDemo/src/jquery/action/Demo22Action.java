package jquery.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

public class Demo22Action {

	private String username;
	private String psw;
	private String message;
	@JSON
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSON
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
	@JSON
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String returnJSON(){
		System.out.println(this.username +":" + this.psw);
		if(this.username.trim().equals("zhangSan")){
			this.message = "用户名不可用";
		} else{
			this.message = "用户名可用";
		}
		// 因为是ajax提交，所以需要服务器返回的只是局部页面而不是全部页面，而return视图是全部页面。
		// 所以需要用JSON，在视图定义root属性，指定要返回的字符串/实体类等数据，以JSON方式返回给页面
		return "success";
	}
	
	public String returnXml() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(this.username.trim().equals("zhangSan")){
			this.message = "<result><message>用户名不可用</message></result>";
//			this.message = "<message>用户名不可用</message>";
		} else{
			this.message = "<result><message>用户名可用</message></result>";
//			this.message = "<message>用户名可用</message>";
		}
		response.getWriter().print(this.message);
		return null;
	}
	
	public String returnString() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(this.username.trim().equals("zhangSan")){
			this.message = "用户名不可用";
		} else{
			this.message = "用户名可用";
		}
		response.getWriter().print(this.message);
		return null;
	}
	
	
}
