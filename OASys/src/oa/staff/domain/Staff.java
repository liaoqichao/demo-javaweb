package oa.staff.domain;

public class Staff {

	private String cardNo;
	private String name;
	private String password;
	private String ID;
	private String department;
	private String position;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Staff [cardNo=" + cardNo + ", name=" + name + ", password=" + password + ", ID=" + ID + ", department="
				+ department + ", positon=" + position + "]";
	}

}
