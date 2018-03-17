package bookstore.test;

@Table("tb_user")//这个类对应数据库的tb_user表
public class User {
	@ID("id")//这个对应数据库的id字段（主键）
	private String id;
	@Column("username")//name对应数据库表中的username字段
	private String name;
	@Column("psw")//password对应数据库表中的psw
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
