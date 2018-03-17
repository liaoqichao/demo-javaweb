package springmvcDemo.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Liaoqichao
 * @date 2016��3��8��
 */
public class User implements Serializable{
	/**
	 * ʵ�����л��ӿ���Ϊ�˷����л���������Ϊ��������Ĵ洢���ʲ�һ�����ڴ档 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// getter �� setter Ҫ�����ݿ���ֶ�����Ӧ����Ա����������ʲôû�й�ϵ
	private Integer id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;
	private List<Orders> ordersList = new ArrayList<Orders>();
	
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthday=" + birthday + ", sex=" + sex + ", address=" + address + "]";
	}
	
}
