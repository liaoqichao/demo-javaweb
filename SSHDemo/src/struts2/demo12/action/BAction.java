package struts2.demo12.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 使用XML配置进行输入校验
 */
public class BAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String mobile;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String update(){
		System.out.println("XML update is running...");
		ActionContext.getContext().put("msg", "信息更新成功");
		return "msg";
	}
	public String save(){
		System.out.println("XML save is running...");
		ActionContext.getContext().put("msg","信息保存成功");
		return "msg";
	}

}
