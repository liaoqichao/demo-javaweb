package hibernate.demo7.domain;

import java.util.HashMap;
import java.util.Map;

public class User {

	private Integer id;
	private String name;
	private Map<String,String> addressMap = new HashMap<String,String>();
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
	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addressMap=" + addressMap + "]";
	}
	
}
