package hibernate.demo19.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import hibernate.demo19.domain.Department;
import hibernate.demo19.domain.Employee;
import lqcUtils.HibernateUtils;

/**
 * Criteria
 */
public class EmployeeDao {

	/**
	 * ׼������������
	 * 
	 * @param employee
	 */
//	 @Test
	public void testSave() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ---------���Ĵ���----------------

			for (int i = 1; i <= 10; i++) {

				Department department = new Department();
				department.setName("dept_" + i);
				session.save(department);
			}
			for (int i = 1; i <= 20; i++) {

				Employee employee = new Employee();
				employee.setName("emp_" + i);
				session.save(employee);
			}

			// ---------------------------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * 
	 */
	 @Test
	public void testQuery1() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ----���Ĵ���------

			//����criteria����
			Criteria criteria = session.createCriteria(Employee.class);
			
			/*
			 * ���ӹ�������
			 * Restrictions����һ�Ѿ�̬�������ṩ����������Restrictions.lt����<,
			 * Restrictions.le����<= ��Щ�ؼ��ֺ�el���ʽ���ơ� 
			 */
			criteria.add(Restrictions.ge("id",1));
			criteria.add(Restrictions.le("id",5));

			/*
			 * ������������
			 * Order����ľ�̬�����ṩ����ʽ.
			 * ���2��addOrder���ȵ���add��������Ϊ�����ȱȽ��У��ڶ���Ϊ�����ȱȽ��бȽ���ȵ�ʱ����á�
			 */
			criteria.addOrder(Order.desc("id"));
			criteria.addOrder(Order.asc("name"));
			
			//ִ�в�ѯ
			/*
			 * ��ҳ��ѯ
			 */
			criteria.setFirstResult(0);
			criteria.setMaxResults(10);
			List<Employee> employeeList = criteria.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			
			// -------------

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null)
				session.close();
		}
	}

}
