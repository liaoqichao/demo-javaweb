package hibernate.demo20.domain;

/**
 * Ա������������ʾһ�Զ�/���һ��ϵ
 */
public class Employee {

	private Integer id;
	private String name;
	private Department department;//���һ
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
}
