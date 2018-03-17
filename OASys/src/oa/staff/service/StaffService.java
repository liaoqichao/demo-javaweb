package oa.staff.service;

import java.util.List;

import oa.staff.dao.StaffDao;
import oa.staff.domain.Staff;

public class StaffService {

	private StaffDao staffDao = new StaffDao();

	public Staff findById(Staff form) {
		return staffDao.findById(form); // 到达这里staff肯定是null
	}

	public List<Staff> findByDepartment(String department) {
		return staffDao.findAllByDepartment(department);
	}

	public Staff validate(Staff form) {
		Staff staff = staffDao.findById(form);
		if (staff != null) {
			if (staff.getPassword().equals(form.getPassword())) {// password正确
				return staff;
			} else {// password错误
				return null;
			}
		}
		return staff; // 到达这里staff肯定是null
	}
	public void save(Staff staff){
		staffDao.save(staff);
	}
	
	public void delete(String cardNo){
		staffDao.delete(cardNo);
	}
	
	public void modifyPassword(String cardNo,String password){
		staffDao.modifyPassword(cardNo, password);
	}

	// @Test
	// public void fun1(){
	// Staff form = new Staff();
	// form.setcardNo(123456);
	// form.setpassword("1111112");
	// Staff staff = findById(form);
	// System.out.println(staff);
	// //null;
	// }
}
