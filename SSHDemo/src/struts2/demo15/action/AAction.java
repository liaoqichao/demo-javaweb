package struts2.demo15.action;

/**
 * struts2的表单标签和防止表单重复提交。
 */
public class AAction {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String test1(){
		return "success";
	}
}
