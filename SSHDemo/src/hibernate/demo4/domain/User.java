package hibernate.demo4.domain;

public class User {

//	private Integer id;//����ʹ�ð�װ����
	private String id;//ʹ��UUID���������ɲ��ԡ�
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
