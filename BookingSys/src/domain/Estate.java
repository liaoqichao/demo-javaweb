package domain;

/**
 * �������淿�ز���Ϣ��bean
 */
public class Estate {

	private String eid;		//���ز�Ȩ������
	private String ename;	//���ز�����
	private String district;//���ز����ڵ���
	private String etype;	//���ز�����?��ֻ��һ��ѡ����ǲ������Ǽ�֤���򷿵ز�֤
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	@Override
	public String toString() {
		return "Estate [eid=" + eid + ", ename=" + ename + ", district=" + district + ", etype=" + etype + "]";
	}
	
	
}
