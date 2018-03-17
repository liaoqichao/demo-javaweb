package hibernate.demo11.domain;

/**
 * hibernate中java对象的四种状态：临时状态，持久化状态，游离状态，删除状态
 */
public class User {

	private Integer id;
	private String name;
	
	private byte[] bytes = new byte[1024 * 1024 * 10]; //一个对象至少10MB,JVM默认内存大小为物理内存的1/64
	//4G = 2^32, 一个User至少10*2^20,JVM内存为2^32/2^8 = 2^24，最多只能存储3个User，JVM内存就会溢出。
	//实际是存了86个
	//这个只是为了让User更占内存，好测试session中存入大量对象，内存溢出的问题。可以不用提供get/set
	
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
