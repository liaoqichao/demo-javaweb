package company.crm.service;

import company.crm.dao.CustomerDao;
import company.crm.domain.Customer;
import company.crm.domain.PageBean;

/**
 * �߼�ҵ���
 * @author Administrator
 *
 */
public class CustomerService {

	//����dao
	private CustomerDao customerDao = new CustomerDao();
	
	/**
	 * ��ӿͻ�
	 * @param c �ͻ�����
	 */
	public void add(Customer c){
		customerDao.add(c);
	}
	
	/**
	 * ��ѯ���пͻ�
	 * @return
	 */
	public PageBean<Customer> findAll(int pc, int ps){

		//1.ͨ��dao��ѯ,�����ظ�CustomerServlet
		return customerDao.findAll(pc,ps);
	}
	
	/**
	 * ����༭�����û�
	 * @param cid
	 * @return
	 */
	public Customer load(String cid){
		
		return customerDao.findByCid(cid);
	}
	/**
	 * �޸��û���Ϣ
	 * @param c
	 */
	public void edit(Customer c){
		customerDao.eidt(c);
	}
	
	/**
	 * ɾ���û�
	 * @param cid
	 */
	public void delete(String cid){
		customerDao.delete(cid);
	}
	
	/**
	 * ���ݷ�װ��Cunstomer��������ѯ������list
	 * @param condition
	 * @return 
	 */
	public PageBean<Customer> query(int pc,int ps,Customer criteria){
		
		return customerDao.query(pc,ps,criteria);
	}
}
