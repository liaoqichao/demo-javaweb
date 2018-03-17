package bookstore.user.domain;

/**
 * User����
 * 1.Ҫ��Ӧ��
 * 2.Ҫ��Ӧ���ݿ�
 * �ȸ�����Ӧ���ݿ��Ĳ���
 */
public class User {
	
	private String uid;//pk
	private String username;
	private String password;
	private String email;
	private String code;//������
	private boolean state;//״̬���Ѽ����δ����
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
