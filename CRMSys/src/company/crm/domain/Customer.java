package company.crm.domain;

/**
 * �����
 * JavaBean:��������ݿ��t_customer��Ӧ
 * @author Administrator
 *
 */
public class Customer {
	
	/*
	 * ��Ӧ���ݿ��
	 */
	private String cid;			//����
	private String cname;		//�ͻ�����
	private String gender;		//�ͻ�����
	private String birthday;	//�ͻ�����
	private String cellphone;	//�ͻ��ֻ�
	private String email;		//�ͻ�����
	private String description;	//�ͻ�����
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
