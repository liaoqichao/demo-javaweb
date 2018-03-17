package domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * HttpSessionBindingListener用来监听JavaBean是否在session域中存在
 */

public class Demo39_User implements HttpSessionBindingListener{
	
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
	@Override
	public String toString() {
		return "Demo39_User [username=" + username + ", password=" + password + "]";
	}
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("啊！Session添加了我");
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("哇~ 无情的session抛弃了我");
	}
	
	
}
