package hibernate.demo13.domain;

/**
 * 公民
 * @author Administrator
 *
 */
public class Person {
	
	private Integer id;
	private String name;
	private IdCard idCard;//这里不能new对象，一new就说明有关系
	
	public void setIdCard(IdCard idCard){
		this.idCard = idCard;
	}
	public IdCard getIdCard(){
		return idCard;
	}
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

}
