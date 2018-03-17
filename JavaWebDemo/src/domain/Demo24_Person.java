package domain;
/**
 * 这就是一个JavaBean。
 * 1.JavaBean必须提供get/set方法(只提供一个也可以)只有 get就是只读属性。
 * 2.必须有一个默认构造器(无参数)
 * 3.一般对具有get/set方法的成员变量称之为属性。
 * 4.就算没有成员变量,但是只要也对应的get/set方法也可以称之为属性。
 * 5.getName(){return name;}  getAge(){return maxAge;}对应的属性的名称是name,age(不是maxAge)
 */
public class Demo24_Person {
	private String name;
	private int age;
	private String gender;//性别
	private boolean bb;
	public boolean isBb() {
		return bb;
	}
	public void setBb(boolean bb) {
		this.bb = bb;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Demo24_Person [name=" + name + ", age=" + age + ", gender=" + gender + ", bb=" + bb + "]";
	}
	
}
