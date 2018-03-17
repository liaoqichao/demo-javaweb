package domain;

/**
 * 用来储存房地产信息的bean
 */
public class Estate {

	private String eid;		//房地产权属编码
	private String ename;	//房地产名称
	private String district;//房地产所在地区
	private String etype;	//房地产类型?，只有一个选项：就是不动产登记证明或房地产证
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
