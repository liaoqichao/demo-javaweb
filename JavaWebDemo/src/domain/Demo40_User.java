package domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * ��ʾsession�Ķۻ��ͻ��
 * û��ʵ�����л��ӿ�Serializable�Ļ��޷����浽Ӳ��
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
	 * �ʱ������
	 */
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�Һ�session�ֻ�����");
	}

	@Override
	/**
	 * �ۻ�ʱ������
	 */
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//Ϊʲô����������ʱ��Ϊ1���ӣ�Ȼ��30�����Ҿ����С�
		System.out.println("���Ѿ���sessionһ�𱣴浽Ӳ����");
	}

}
