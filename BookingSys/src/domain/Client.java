package domain;

/**
 * 用来储存预约人信息的bean
 */
public class Client {
	
	private int cid;		//主键
	private String cname;	//客户名称
	private String phone;	//客户电话
	private String certificate;	//客户省份证
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	@Override
	public String toString() {
		return "Client [cid=" + cid + ", cname=" + cname + ", phone=" + phone + ", certificate=" + certificate + "]";
	}
	
	

}
