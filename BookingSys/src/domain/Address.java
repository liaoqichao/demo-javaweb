package domain;

/**
 * ��Ӧ���ݿ��t_address
 */
public class Address {
	private int aid;	//������ǿ�аѶ�Զ�Ĺ�����ʡ��
	private String aname;	//����Ǽǵ�
	private String alocation;//�Ǽǵ��ַ
	private District district;	//��������ID��������did��ֻ����District���������ֱ���ã���Ҫ�õ����ű�
							//��������Address a = qr.query(sql,new MapHandler(),params)
							//a.getDistrict = null;���������ݿ�ļ�¼��bean��Ӧ���������ᶪʧ���ݡ�hibernate�ܽ����
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAlocation() {
		return alocation;
	}
	public void setAlocation(String alocation) {
		this.alocation = alocation;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	@Override
	public String toString() {
		return "Address [aid=" + aid + ", aname=" + aname + ", alocation=" + alocation + ", district=" + district + "]";
	}

	
}
