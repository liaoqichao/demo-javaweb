package hibernate.demo3.domain;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private int age;
	private Date birthday;
	private String desc;//description����DECS����ؼ��ֳ�ͻ��<property name="desc" type="string" column="desc_"/>
//	Ҳ����ʹ�÷�����``<property name="desc" type="string" column="`desc`"/>�����鲻Ҫ�����á�
	private byte[] photo;//ͷ��ͼƬ
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	
}
