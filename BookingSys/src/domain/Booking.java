package domain;

/**
 * ��װԤԼ��¼��bean
 */
public class Booking {

	private String number;	//��ˮ��
	private Client client;		//�û�id(����,�������֤)
	private BookingItem bookingItem;		//ԤԼ��������id
	private Estate estate;		//���ز�Ȩ�����
	private String adate;	//����ԤԼ����
	private String pdate;	//����ҵ������	���������ڼ�
	private String ptime;	//����ҵ���ʱ��	ʱ��
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
