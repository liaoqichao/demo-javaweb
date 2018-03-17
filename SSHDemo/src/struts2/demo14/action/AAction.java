package struts2.demo14.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import struts2.demo14.domain.Book;
import struts2.demo14.domain.User;

/**
 * ognl表达式
 */
public class AAction {

	/*
	 * 但JSP访问它的时候，它是值栈的栈顶元素的属性，可以直接用${member}，也可以<s:property name="member"/>
	 */
	private String member;
	private User user;
	private List<Book> bookList;
	
	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getMember() {
		return member;
	}

	public void setMenber(String member) {
		this.member = member;
	}

	/**
	 * 访问上下文对象的属性
	 */
	public String test1(){
		ActionContext.getContext().put("req_Name", "req_张三");
		ActionContext.getContext().getSession().put("ses_Name", "ses_李四");
		return "success";
	}

	/**
	 *  访问值栈的栈顶对象的属性（栈顶对象就是action）
	 */
	public String test2(){
		this.member = "王五";
		return "success";
	}
	
	/**
	 * 栈顶对象的属性为一个javabean
	 */
	public String test3(){
		User user = new User("赵六","123");
		this.user = user;
		return "success";
	}
	
	/**
	 * OGNL表达式：投影功能
	 */
	public String test4(){
		//实际中这些数据来自数据库
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book("001","第一本书",80));
		bookList.add(new Book("002","第二本书",60));
		bookList.add(new Book("003","第三本书",40));
		this.bookList = bookList;
		return "success";
	}
}
