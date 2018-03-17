package struts2.demo14.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import struts2.demo14.domain.Book;
import struts2.demo14.domain.User;

/**
 * ognl���ʽ
 */
public class AAction {

	/*
	 * ��JSP��������ʱ������ֵջ��ջ��Ԫ�ص����ԣ�����ֱ����${member}��Ҳ����<s:property name="member"/>
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
	 * ���������Ķ��������
	 */
	public String test1(){
		ActionContext.getContext().put("req_Name", "req_����");
		ActionContext.getContext().getSession().put("ses_Name", "ses_����");
		return "success";
	}

	/**
	 *  ����ֵջ��ջ����������ԣ�ջ���������action��
	 */
	public String test2(){
		this.member = "����";
		return "success";
	}
	
	/**
	 * ջ�����������Ϊһ��javabean
	 */
	public String test3(){
		User user = new User("����","123");
		this.user = user;
		return "success";
	}
	
	/**
	 * OGNL���ʽ��ͶӰ����
	 */
	public String test4(){
		//ʵ������Щ�����������ݿ�
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book("001","��һ����",80));
		bookList.add(new Book("002","�ڶ�����",60));
		bookList.add(new Book("003","��������",40));
		this.bookList = bookList;
		return "success";
	}
}
