package ssh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ssh.dao.EmployeeDao;
import ssh.domain.Employee;

//@Repository("employeeDao")
public class EmployeeDaoBean implements EmployeeDao {

	@Resource
	private SessionFactory sessionFactory;
	public void save(Employee employee){
		/*
		 * ����ʹ��opensession����Ϊ�´�����session����spring��������Ҫ��getCurrentSession
		 */
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
	}
	
	public void delete(Integer id){
		Session session = sessionFactory.getCurrentSession();
		Employee employee = session.get(Employee.class, id);
		session.delete(employee);
	}
	
	public void update(Employee employee){
		Session session = sessionFactory.getCurrentSession();
		session.update(employee);
	}
	
	public Employee getEmployee(Integer id){
		Session session = sessionFactory.getCurrentSession();
		//ʹ��������
		return session.get(Employee.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Employee";
		return session.createQuery(hql).setCacheable(true)//ʹ�ò�ѯ����
				.list();
	}
}
