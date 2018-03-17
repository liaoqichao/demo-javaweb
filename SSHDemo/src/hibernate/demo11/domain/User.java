package hibernate.demo11.domain;

/**
 * hibernate��java���������״̬����ʱ״̬���־û�״̬������״̬��ɾ��״̬
 */
public class User {

	private Integer id;
	private String name;
	
	private byte[] bytes = new byte[1024 * 1024 * 10]; //һ����������10MB,JVMĬ���ڴ��СΪ�����ڴ��1/64
	//4G = 2^32, һ��User����10*2^20,JVM�ڴ�Ϊ2^32/2^8 = 2^24�����ֻ�ܴ洢3��User��JVM�ڴ�ͻ������
	//ʵ���Ǵ���86��
	//���ֻ��Ϊ����User��ռ�ڴ棬�ò���session�д�����������ڴ���������⡣���Բ����ṩget/set
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
