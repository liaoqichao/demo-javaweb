package struts2.demo12.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 输入校验：对所有action，对指定action。采用代码方式实现，采用XML配置方式实现。
 */
public class AAction extends ActionSupport{
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
		System.out.println("update is running...");
		ActionContext.getContext().put("msg", "信息更新成功");
		return "msg";
	}
	public String save(){
		System.out.println("save is running...");
		ActionContext.getContext().put("msg","信息保存成功");
		return "msg";
	}
	/**
	 * 继承ActionSupport，重写validate方法。来对action中的所有方法进行输入校验。
	 */
//	@Override
//	public void validate() {
//		if(username == null || username.trim().isEmpty()){
//			addFieldError("username", "用户名不能为空");//第一个参数，表单字段名称，第二个参数，错误信息。
//		}
//		if(mobile == null){
//			addFieldError(mobile, "手机号不能为空");
//		} else if(!mobile.matches("^1[358]\\d{9}$")){
////			Pattern.compile("^1[358]\\d{9}$").matcher(this.mobile).matches();//返回boolean
//			/*
//			 * ^1表示开头必须为1
//			 * d{9}$必须9个数字，然后就结束。^表示开头,$表示结尾。
//			 */
//			addFieldError(mobile, "手机格式不正确");
//		}
//	}
	
	/**
	 * validateXxx方法，只对xxx方法进行输入校验。这个方法不是重写的。
	 */
	public void validateUpdate(){
		if(username == null || username.trim().isEmpty()){
			addFieldError("username", "用户名不能为空");//第一个参数，表单字段名称，第二个参数，错误信息。
		}
		if(mobile == null){
			addFieldError(mobile, "手机号不能为空");
		} else if(!mobile.matches("^1[358]\\d{9}$")){
//			Pattern.compile("^1[358]\\d{9}$").matcher(this.mobile).matches();//返回boolean
			/*
			 * ^1表示开头必须为1
			 * d{9}$必须9个数字，然后就结束。^表示开头,$表示结尾。
			 */
			addFieldError(mobile, "手机格式不正确");
		}
	}
	
	
}
