package domain;

/**
 * �����洢ԤԼ���������bean
 */
public class BookingItem {
	private int bid;		//ԤԼ��������ID
	private String bname;	//ԤԼ������������
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
