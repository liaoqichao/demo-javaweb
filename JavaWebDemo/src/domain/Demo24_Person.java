package domain;
/**
 * �����һ��JavaBean��
 * 1.JavaBean�����ṩget/set����(ֻ�ṩһ��Ҳ����)ֻ�� get����ֻ�����ԡ�
 * 2.������һ��Ĭ�Ϲ�����(�޲���)
 * 3.һ��Ծ���get/set�����ĳ�Ա������֮Ϊ���ԡ�
 * 4.����û�г�Ա����,����ֻҪҲ��Ӧ��get/set����Ҳ���Գ�֮Ϊ���ԡ�
 * 5.getName(){return name;}  getAge(){return maxAge;}��Ӧ�����Ե�������name,age(����maxAge)
 */
public class Demo24_Person {
	private String name;
	private int age;
	private String gender;//�Ա�
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
