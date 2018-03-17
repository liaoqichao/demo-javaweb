package oa.issue.dao;

import java.util.List;

import org.junit.Test;

import oa.staff.domain.Staff;

public class IssueDaoTest {

	private IssueDao issueDao = new IssueDao();
	
	@Test
	public void testFindByDepartment(){
		List<Staff> staffList = issueDao.findByDepartment("财务部");
		for (Staff staff : staffList) {
			System.out.println(staff);
		}
	}
}
