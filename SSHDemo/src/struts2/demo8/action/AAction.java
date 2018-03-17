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
		//格式为2016-3-21，控制台和页面的输出结果为：Mon Mar 21 00:00:00 CST 2016
		/*
		 * 格式为20160321，控制台结果为null，页面输出结果为20160321。因为它把20160321当做字符串，而set方法的参数是Date类型.
		 * 这里出该出现了类型转换错误。但是因为struts2的拦截器，如果出现了错误（什么错误?）会把原始参数原样输出。所以b.jsp使用EL表达式
		 * 能把原始参数输出出来。
		 */
		return "success";
	}
	
}
