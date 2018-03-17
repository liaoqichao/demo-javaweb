package hibernate.demo21.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo21.domain.Department;
import hibernate.demo21.domain.Employee;
import lqcUtils.HibernateUtils;

public class EmployeeDao {

	/**
	 * ������������
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
			
			for (int i = 1; i <= 20; i++) {
				Employee employee = new Employee();
				employee.setName("cacheEmp_" + i);
				session.save(employee);
			}
			
			for (int i = 1; i <= 10; i++) {
				Department department = new Department();
				department.setName("cacheDept_" + i);
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
	 * ���Զ�������
	 * һ������ָ����session�Ļ���
	 * 	> ���һ�����������Ĳ���
	 *  > ͨ��һ������ʹ��һ��session
	 * ��������ָ����sessionFactory�Ļ���
	 * 	һ��session�ڶ�λ�ȡͬһ����¼ֻ��Ҫ����һ�����ݿ�
	 *  ��������sesion��ȡͬһ������¼�أ�
	 *  
	 *  ʵ����һ�������������Ϊ�˹�����󣬶�����������ò���Ϊ���ṩ���ܡ�
	 *  session�л���û��  --> �Ҷ�������(sessionFactory�Ļ���) --> ���û���������ݿ�
	 */
	
	@Test
	public void testSecondCache() {
		Session session = null;
		Transaction tx = null;
		/*
		 * 1. ���û�п����������棬����2��session��ִ��2��SQL��䡣��������������棬�����ҵ���ͬһ������
		 * ��ô�ڶ��λ��ڶ��������ң�����ֻ��һ��SQL���
		 * 2. ʹ�ü��ϻ���ʱ�����ϴ洢��ʵ��ҲҪ���õ��������棬��Ȼ���ϻ���û�á�
		 * 3. ����һ�����滹�Ƕ������涼�Ǹ���OID�ҵ������еĶ���
		 * 		�������HQL�����Ҷ��󣬶��󶼻ᱣ�浽һ������Ͷ������档
		 * 		���ǲ������в�ѯ����Ӷ��������л�ȡ��ֻ��get��load������Ӷ��������ȡ��
		 * 		����Query.list()����Ĭ�ϲ���ʹ�û��棬ʹ��Query.iterate()��Ӷ��������в�ѯ������
		 * 		���ص���iterator��������List
		 */
		/*
		 * ��һ��session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------���Ĵ���----------------
			
//			Employee employee = session.get(Employee.class, 1);
//			System.out.println(employee);
			
			/*
			 * ���usageΪread-only�Ļ����޸ľͻᱨ�쳣��
			 */
//			Department department = session.get(Department.class, 1);
//			department.setName("�з���");
			
			
			/*
			 * ֻ�����л��棬����û���棬��ѯ2��
			 * <collection-cache usage="read-write" 
			 * collection="hibernate.demo21.domain.Department.employeeSet"/>
			 * ���ü��ϻ������ִ��һ�λ���
			 */
//			System.out.println(department.getEmployeeSet());
			
			
			
			/*
			 * HQL����ѯ,ʹ��HQL��ѯ�Ľ����ŵ���������.Ҫ�ŵ������Ҫ����OID��ѯ������ǰ��ÿ����¼����Ӧ
			 * һ����ѯ��䡣
			 * iterate()������ �ⷽ������������ޣ���̫����
			 * �Ȳ�ѯid�ļ��ϣ�
			 * Ȼ����ÿ��id��ѯ�������ͻ���n+1�β�ѯ��
			 * ���磺
			 * e.id<8����7����ѯ���+1����ѯid<8�����
			 * �ڶ���session����9����ǰ��7���ӻ����ã�����2���в�ѯ���
			 * 
			 * ���շ���������query.setCacheable(true)��
			 * ǰ��Ҫ���������ļ�����
			 * <property name="hibernate.cache.use_query_cache">true</property>
			 */
			String hql ="FROM Employee e WHERE e.id<18";
			
			List<Employee> employeeList = session.createQuery(hql)//
					.setCacheable(true)//�Ƿ�ʹ�ò�ѯ���棬ǰ�������ͬ������
					.list();
			System.out.println(employeeList);
//			Iterator<Employee> employeeIterator = session.createQuery(hql).iterate();
//			for (; employeeIterator.hasNext();) {
//				Employee employee = employeeIterator.next();
//				System.out.println(employee);
//			}
			
//			System.out.println(session.get(Employee.class, 5));
			// ---------------------------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null)
				session.close();
		}
		
		/*
		 * �ڶ���session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------���Ĵ���----------------
//			Employee employee = session.get(Employee.class, 1);
//			System.out.println(employee);
			
//			Department department =  session.get(Department.class, 1);
//			System.out.println(department.getEmployeeSet());
			
			String hql ="FROM Employee e WHERE e.id<18";
			List<Employee> employeeList = session.createQuery(hql)//
					.setCacheable(true)//�Ƿ�ʹ�ò�ѯ���棬ǰ�������ͬ������
					.list();
			System.out.println(employeeList);
			
//			Iterator<Employee> employeeIterator = session.createQuery(hql).iterate();
//			for(; employeeIterator.hasNext();){
//				Employee employee = employeeIterator.next();
//				System.out.println(employee);
//			}
//			System.out.println(session.get(Employee.class, 5));
			
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
	 * ����ʱ������档
	 * ����UPDATE,DELETE��ʽ��HQL���Զ��������Ӱ�졣
	 * ��һ��sessionʹ��HQL���и��£��ڶ���session��ȡ������󣬻�ȡ�Ķ���������Ǹ��º�ġ�
	 * ִ��HQL���󣬶��������ϳ�ԭ�ȵĻ��棬����Ҫ��ʱ���������ݿ����²�ѯ��
	 */
//	@Test
	public void testUpdateTimestampCache() {
		Session session = null;
		Transaction tx = null;
	
		/*
		 * ��һ��session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------���Ĵ���----------------
			
			//�Ȼ�ȡ
			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);
			//ֱ�Ӹ��µ�DB
			String hql = "UPDATE Employee e SET e.name=:name WHERE e.id=:id";
			session.createQuery(hql)//
				.setParameter("id", 1)//
				.setParameter("name", "����")//
				.executeUpdate();
			System.out.println(employee.getName());//���ݿ���ˣ�����session���滹�ڣ������������Ǿɽ��
			

			// ---------------------------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (session != null)
				session.close();
		}
		
		/*
		 * �ڶ���session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------���Ĵ���----------------

			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);//�������
			
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
