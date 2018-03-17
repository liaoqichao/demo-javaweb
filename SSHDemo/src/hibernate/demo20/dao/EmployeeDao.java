package hibernate.demo20.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo20.domain.Department;
import hibernate.demo20.domain.Employee;
import lqcUtils.HibernateUtils;

public class EmployeeDao {

	/**
	 * ������������
	 * 
	 * @param employee
	 */
	// @Test
	public void testSave() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ---------���Ĵ���----------------

			for (int i = 1; i <= 20; i++) {
				Employee employee = new Employee();
				employee.setName("lazyEmp_" + i);
				session.save(employee);
			}

			for (int i = 1; i <= 10; i++) {
				Department department = new Department();
				department.setName("lazyDept_" + i);
				session.save(department);
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
	 * ����������
	 */
	@Test
	public void testLazy() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ---------���Ĵ���----------------

			Department department = session.get(Department.class, 1);
			// Department department = session.get(Department.class, 1);//ִ��n�ζ�ֻ��ѯ1�Σ����̲�ѯ

			System.out.println(department.getName());

			// Hibernate: select employeese0_.departmentId as departme3_1_0_, employeese0_.id as id1_1_0_, employeese0_.id as id1_1_1_, employeese0_.name as name2_1_1_,
			// employeese0_.departmentId as departme3_1_1_
			// from demo20_employee employeese0_ where employeese0_.departmentId=?
			
			/*
			 *  ���û�����������䣬�Ͳ���ִ�������SQL��䡣���Լ��ϵ���Ϣ�������ص�.
			 *  ������<set>��ǩ�����lazy���ԡ�Ĭ��Ϊtrue
			 *  �����lazy������3��ֵ��true��false��extra
			 *  true����һ��ʹ��ʱ����
			 *  false���������أ���get��ȡ������ʱ�ͼ��ؼ��ϵ�����Ԫ�ء�
			 *  extra����ǿ�������ز��ԡ�
			 */
			
			//���lazy="true"��ֻ�ǲ�ѯ���ϴ�С����Ҫ��ѯ������Ԫ���е�̫�˷ѡ����ֻ��ѯԪ������������������Ҫ��ʱ��Ų�ѯ��
			//��set�е�lazy="extra"��Ϳ���ʵ�����Ч������ѯ���ϵĴ�С��û�в�ѯ����Ԫ�ص�������Ϣ����Ҫ��ѯԪ�ص�ʱ��
			//�Ż�ִ�в�ѯԪ��������Ϣ����䡣
			
			
			//lazy=extra��SQL���
//			Hibernate: select count(id) from demo20_employee where departmentId =?
//			System.out.println(department.getEmployeeSet().size());

			/*
			 * ���ϼ���EmployeeSet����ֹ�����������쳣��
			 * �������쳣����session.close()�������Ҫ��ѯ���ݿ�ķ�������ʱ��Ҫ���ص����Ի�û��ʹ�ù��������ػ�û���ع�
			 * �����ؾͻ���أ�����session�Ѿ��رգ������׳��������쳣��
			 * ����취��
			 * 1.�ر�������
			 * 2.��session.close()�ŵ���Ҫ���صĴ���֮ǰ
			 * 3.��session.close()֮ǰ����Hibernate.initialize("Ҫ���ص�����")����ǰ�������ݡ�
			 * 	 ��ʹsession.close()�󣬻��ǿ���ͨ�������ȡ��������ԡ�(department.getEmployeeSet())
			 */
			Hibernate.initialize(department.getEmployeeSet());//ǿ�Ƽ���
			
			//session.close()�����Ҳ���Բ�ѯ������
			System.out.println(department.getEmployeeSet());
			
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

}
