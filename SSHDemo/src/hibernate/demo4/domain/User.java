package hibernate.demo4.domain;

public class User {

//	private Integer id;//建议使用包装类型
	private String id;//使用UUID的主键生成策略。
	private String name;
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
}
