package struts2.demo8.action;

import java.util.Date;

import struts2.demo8.domain.User;

public class AAction {
	private String username;
	private String password;
	private User user;
	private Date birthday;
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String test1(){
		System.out.println(username+", "+ password);
		return "success";
	}
	public String test2(){
		System.out.println(user);
		System.out.println(user.getUsername()+", "+user.getPassword());
		return "success";
	}
	
	public String test3(){
		System.out.println(birthday);
		//��ʽΪ2016-3-21������̨��ҳ���������Ϊ��Mon Mar 21 00:00:00 CST 2016
		/*
		 * ��ʽΪ20160321������̨���Ϊnull��ҳ��������Ϊ20160321����Ϊ����20160321�����ַ�������set�����Ĳ�����Date����.
		 * ������ó���������ת�����󡣵�����Ϊstruts2������������������˴���ʲô����?�����ԭʼ����ԭ�����������b.jspʹ��EL���ʽ
		 * �ܰ�ԭʼ�������������
		 */
		return "success";
	}
	
}
