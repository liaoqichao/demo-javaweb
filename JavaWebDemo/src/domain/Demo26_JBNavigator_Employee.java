package domain;

public class Demo26_JBNavigator_Employee {

	/**
	 * JavaBeanµ¼º½
	 */
	
	private String name;
	private double salary;
	private Demo26_JBNavigator_Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Demo26_JBNavigator_Address getAddress() {
		return address;
	}
	public void setAddress(Demo26_JBNavigator_Address address) {
		this.address = address;
	}
}
