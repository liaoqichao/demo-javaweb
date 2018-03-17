package bookstore.user.domain;

/**
 * User领域
 * 1.要对应表单
 * 2.要对应数据库
 * 先给出对应数据库表的部分
 */
public class User {
	
	private String uid;//pk
	private String username;
	private String password;
	private String email;
	private String code;//激活码
	private boolean state;//状态：已激活和未激活
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", code="
				+ code + ", state=" + state + "]";
	}
	
	
}
