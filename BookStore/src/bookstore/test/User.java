package bookstore.test;

@Table("tb_user")//������Ӧ���ݿ��tb_user��
public class User {
	@ID("id")//�����Ӧ���ݿ��id�ֶΣ�������
	private String id;
	@Column("username")//name��Ӧ���ݿ���е�username�ֶ�
	private String name;
	@Column("psw")//password��Ӧ���ݿ���е�psw
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
