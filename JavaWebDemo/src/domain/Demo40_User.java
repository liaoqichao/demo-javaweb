package domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 演示session的钝化和活化。
 * 没有实现序列化接口Serializable的话无法保存到硬盘
 * @author Administrator
 *
 */
public class Demo40_User implements HttpSessionActivationListener ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5618807329433333639L;
	private String username; 
	private String password; 
	
	@Override
	public String toString() {
		return "Demo40_User [username=" + username + ", password=" + password + "]";
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

	@Override
	/**
	 * 活化时被调用
	 */
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("我和session又回来了");
	}

	@Override
	/**
	 * 钝化时被调用
	 */
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//为什么配置最大空闲时间为1分钟，然而30秒左右就运行。
		System.out.println("我已经和session一起保存到硬盘了");
	}

}
