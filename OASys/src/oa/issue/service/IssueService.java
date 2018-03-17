package oa.issue.service;

import java.util.List;

import oa.issue.dao.IssueDao;
import oa.staff.dao.StaffDao;
import oa.staff.domain.Staff;

/**
 * 签发业务。这里不是签发的记录（因为签发表没有被签发的文件），而是管理签发权限。
 */
public class IssueService {

	private IssueDao issueDao = new IssueDao();
	private StaffDao staffDao = new StaffDao();

	/**
	 * 添加管理权限
	 * 
	 * @throws IssueException
	 */
	public void add(Staff form) throws IssueException {
		Staff po = issueDao.findById(form.getCardNo());
		if (po == null) { // 原本没有签发权限的
			Staff staff = staffDao.findById(form);// 职员表，所有字段都有的。
			issueDao.save(staff);
		} else { // 没有签发权限
			throw new IssueException("该用户已经拥有签发权限，请不要重复操作！");
		}
	}

	/**
	 * 删除管理权限
	 * 
	 * @throws Exception
	 */
	public void remove(Staff form) throws Exception {
		try {
			issueDao.delete(form);
		} catch (Exception e) {
			// throw new IssueException("删除失败！");
			throw e;
		} // 是通过主键查询，然后再删除的，所以不用staffDao。
	}

	/**
	 * 查询所有拥有权限的人
	 * 
	 * @return
	 */
	public List<Staff> findAll() {
		return issueDao.findAll();
	}

	/**
	 * 按cardNo查询
	 * 
	 * @param cardNo
	 * @return 返回空或者通过staff（通过staff表，信息完整）
	 */
	public Staff get(String cardNo) {
		return issueDao.findById(cardNo);
	}

	/**
	 * 按部门查询
	 * 
	 * @param 部门
	 * @return 返回staff列表
	 */
	public List<Staff> findByDepartment(String 部门) {
		return issueDao.findByDepartment(部门);
	}

}
