package domain;

/**
 * 对应数据库的t_address
 */
public class Address {
	private int aid;	//主键。强行把多对多的关联表省略
	private String aname;	//办理登记点
	private String alocation;//登记点地址
	private District district;	//所在区的ID，不能用did，只能用District。外键不能直接用，需要得到整张表
							//但是这样Address a = qr.query(sql,new MapHandler(),params)
							//a.getDistrict = null;　这样数据库的记录和bean对应不完整，会丢失数据。hibernate能解决。
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
