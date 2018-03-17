package ss.web.action;

public class UserAction{
	
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
	
	public String login(){
		System.out.println(this.username);
		System.out.println(this.password);
		return "msg";
	}

}
