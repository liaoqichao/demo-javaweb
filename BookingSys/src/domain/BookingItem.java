package domain;

/**
 * 用来存储预约服务事项的bean
 */
public class BookingItem {
	private int bid;		//预约服务事项ID
	private String bname;	//预约服务事项名称
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	@Override
	public String toString() {
		return "BookingItem [bid=" + bid + ", bname=" + bname + "]";
	}
	
	
}
