package demo33.domain;

/**
 * 把数据库查询到的结果保存到这个对象中。
 * @author Administrator
 *
 */
public class User {

	private String username;
	private String password;
	
	
	public User() {
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
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
}
