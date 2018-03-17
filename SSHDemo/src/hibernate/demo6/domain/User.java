package hibernate.demo6.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

	private Integer id;
	private String name;
	private List<String> addressList = new ArrayList<String>();
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
	public List<String> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addressList=" + addressList + "]";
	}
	
}
