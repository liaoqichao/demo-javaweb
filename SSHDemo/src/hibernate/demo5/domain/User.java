package hibernate.demo5.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * ӳ�伯������
 */
public class User {

	private Integer id;
	private String name;
	private Set<String> addressSet = new HashSet<String>();//�洢�����ַ

	
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
	public Set<String> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addressSet=" + addressSet + "]";
	}
	
	
	
}
