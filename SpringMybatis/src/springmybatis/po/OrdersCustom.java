package springmybatis.po;

/**
 * 订单自定义po，包括订单信息、用户信息
 * 继承字段较多的实体类(其实多可以，只是代码写少些多而已)
 */
public class OrdersCustom extends Orders {

	private String username;// 用户名称
	private String sex; // 用户性别
	private String address;// 用户地址
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		return "OrdersCustom [username=" + username + ", sex=" + sex + ", address=" + address + ", getId()=" + getId() + ", getUserId()=" + getUserId() + ", getNumber()="
				+ getNumber() + ", getCreatetime()=" + getCreatetime() + ", getNote()=" + getNote() + "]";
	}

}
