package sh.domain;

/**
 * 实体类，使用Person.hbm.xml的方式配置
 */
public class Person {

	private Integer id;
	private String name;
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
		return "Person [id=" + id + ", name=" + name + "]";
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}
