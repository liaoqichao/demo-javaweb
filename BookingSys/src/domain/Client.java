package domain;

/**
 * ��������ԤԼ����Ϣ��bean
 */
public class Client {
	
	private int cid;		//����
	private String cname;	//�ͻ�����
	private String phone;	//�ͻ��绰
	private String certificate;	//�ͻ�ʡ��֤
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
