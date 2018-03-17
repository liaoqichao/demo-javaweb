package domain;

/**
 * 封装预约记录的bean
 */
public class Booking {

	private String number;	//流水号
	private Client client;		//用户id(主键,不是身份证)
	private BookingItem bookingItem;		//预约服务事项id
	private Estate estate;		//房地产权属编号
	private String adate;	//申请预约日期
	private String pdate;	//办理业务日期	年月日星期几
	private String ptime;	//办理业务的时间	时分
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public BookingItem getBookingItem() {
		return bookingItem;
	}
	public void setBookingItem(BookingItem bookingItem) {
		this.bookingItem = bookingItem;
	}
	public Estate getEstate() {
		return estate;
	}
	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	

	
}
