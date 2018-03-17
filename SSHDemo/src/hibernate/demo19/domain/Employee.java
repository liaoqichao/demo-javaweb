package hibernate.demo19.domain;

/**
 * 员工部门例子演示一对多/多对一关系
 */
public class Employee {

	private Integer id;
	private String name;
	private Department department;//多对一
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
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String name, Integer id) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}
