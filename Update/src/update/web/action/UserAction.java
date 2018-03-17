package update.web.action;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import update.domain.User;
import update.service.UserException;
import update.service.UserService;

@Transactional
public class UserAction {

	@Resource
	private UserService userService;
	private User user;
//	private String vcode; //验证码
//	private String vcodeErrors;// 验证码错误信息
	private String error;
	private String new_password;
	private String rpt_new_password;
	private String modifyPswMsg;
	
	
	
//	public String getVcodeErrors() {
//		return vcodeErrors;
//	}
//
//	public void setVcodeErrors(String vcodeErrors) {
//		this.vcodeErrors = vcodeErrors;
//	}
//
//	public String getVcode() {
//		return vcode;
//	}
//
//	public void setVcode(String vcode) {
//		this.vcode = vcode;
//	}

	public String getRpt_new_password() {
		return rpt_new_password;
	}

	public void setRpt_new_password(String rpt_new_password) {
		this.rpt_new_password = rpt_new_password;
	}

	public String getModifyPswMsg() {
		return modifyPswMsg;
	}

	public void setModifyPswMsg(String modifyPswMsg) {
		this.modifyPswMsg = modifyPswMsg;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String login(){
		try {
			if(user == null){
				return "login";
			}
			if(user.getUsername().trim().isEmpty() || user.getPsw().trim().isEmpty()){
				this.error = "用户名和密码不能为空！";
				return "login";
			}
			User user_db = userService.login(user);
			ActionContext actionContext = ActionContext.getContext();
			actionContext.getSession().put("session_user", user_db); //登录成功把用户放到session域
			return "index";
		} catch (UserException e) {
			this.error = e.getMessage();
			return "login";
		}
	}
	
//	public String login1(){
//		try {
//			String verifyCode = (String)ActionContext.getContext().getSession().get("session_vcode");
//			if(!verifyCode.equalsIgnoreCase(this.vcode)){
//				this.vcodeErrors = "验证码错误!";
//				return "login";
//			} else{
//				this.vcodeErrors = "";
//				if(user.getUsername().trim().isEmpty() || user.getPsw().trim().isEmpty()){
//					this.error = "用户名和密码不能为空！";
//					return "login";
//				}
//				User user_db = userService.login(user);
//				ActionContext actionContext = ActionContext.getContext();
//				actionContext.getSession().put("session_user", user_db); //登录成功把用户放到session域
//				return "index";
//			}
//		} catch (UserException e) {
//			this.error = e.getMessage();
//			return "login";
//		}
//	}
	
	public String logout(){
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getSession().remove("session_user");
		return "index";
	}
	
	public String modifyPsw(){
		// 1. 判断2次输入新密码是否相同
		if(!this.new_password.equals(this.rpt_new_password)){
			this.modifyPswMsg="新密码两次输入不同，请重新登录";
			return "modifyPsw";
		}
		/*
		 *  2. 修改密码的状态
		 */
		try {
			userService.modifyPsw(user,new_password);
			this.modifyPswMsg = "密码修改成功!请重新登录";
			ActionContext actionContext = ActionContext.getContext();
			actionContext.getSession().remove("session_user");
		} catch (UserException e) {
			this.modifyPswMsg = e.getMessage();
		}
		return "modifyPsw";
	}
	
	
}
