package company.crm.domain;

/**
 * 领域层
 * JavaBean:与表单和数据库的t_customer对应
 * @author Administrator
 *
 */
public class Customer {
	
	/*
	 * 对应数据库表
	 */
	private String cid;			//主键
	private String cname;		//客户名称
	private String gender;		//客户行呗
	private String birthday;	//客户生日
	private String cellphone;	//客户手机
	private String email;		//客户邮箱
	private String description;	//客户描述
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", gender=" + gender + ", birthday=" + birthday
				+ ", cellphone=" + cellphone + ", email=" + email + ", description=" + description + "]";
	}
	
	
}
