package company.crm.service;

import company.crm.dao.CustomerDao;
import company.crm.domain.Customer;
import company.crm.domain.PageBean;

/**
 * 逻辑业务层
 * @author Administrator
 *
 */
public class CustomerService {

	//依赖dao
	private CustomerDao customerDao = new CustomerDao();
	
	/**
	 * 添加客户
	 * @param c 客户对象
	 */
	public void add(Customer c){
		customerDao.add(c);
	}
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public PageBean<Customer> findAll(int pc, int ps){

		//1.通过dao查询,并返回给CustomerServlet
		return customerDao.findAll(pc,ps);
	}
	
	/**
	 * 点击编辑加载用户
	 * @param cid
	 * @return
	 */
	public Customer load(String cid){
		
		return customerDao.findByCid(cid);
	}
	/**
	 * 修改用户信息
	 * @param c
	 */
	public void edit(Customer c){
		customerDao.eidt(c);
	}
	
	/**
	 * 删除用户
	 * @param cid
	 */
	public void delete(String cid){
		customerDao.delete(cid);
	}
	
	/**
	 * 根据封装到Cunstomer的条件查询，返回list
	 * @param condition
	 * @return 
	 */
	public PageBean<Customer> query(int pc,int ps,Customer criteria){
		
		return customerDao.query(pc,ps,criteria);
	}
}
