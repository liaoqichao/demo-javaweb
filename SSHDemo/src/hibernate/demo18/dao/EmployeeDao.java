package hibernate.demo18.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo18.domain.Department;
import hibernate.demo18.domain.Employee;
import lqcUtils.HibernateUtils;

/**
 * HQL
 * 
 * @author Administrator
 *
 */
public class EmployeeDao {

	/**
	 * ׼������������
	 * 
	 * @param employee
	 */
	 @Test
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
	 * ʹ��HQL����ѯ
	 * 1.�򵥲�ѯ����������AS����ʡ��
	 * 2.����������SQLһ��
	 * 3.ORDER BY ��SQLһ��
	 * 4.��ѯָ���У���SQL��ͬ��new��������ݷ�װ��ָ���У���Ҫʵ�����ṩ�в������췽��������˳�򰴲�ѯ˳��
	 * 5.��ѯ�����������¼��һ����¼����ҳ��ѯ
	 */
	// @Test
	public void testQuery1() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ----���Ĵ���------
			String hql = null;

			/*
			 * 1.�򵥲�ѯ �����User����д����������Ϊ.hbm.xml�е�<hibernate-mapping>��ǩ Ĭ����auto-import="true" ��ʾ�����ǩ�µ��඼�Զ���package="abc"��������HQL���ֻ��д�������Ϳ��ԡ� ���Ҫ�õ�����һ������ͬ���ֻ࣬��д��ȫ����
			 * 
			 * ���Ը���ӱ��� FROM Employee AS emp������AS�ؼ��ֿ���ʡ�ԡ��������йؼ��ֵ�ʱ�����ʹ�ñ����� ���� FROM Employ emp WHERE emp.desc=? ;�����Ͳ�����Ϊdesc�ǽ�������Ĺؼ��֡�
			 * 
			 * �����Ժ�д��������
			 */
			hql = "FROM Employee AS emp";// ���Employee���࣬ͨ���࣬�������ļ��ҵ���Ӧ�����ݿ��д��Сд�ͻᱨ��
			Query query = session.createQuery(hql);
			List<Employee> employeeList = query.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 2.���Ϲ�������������ʹ�ñ�������WHERE
			 */

			hql = "FROM Employee emp WHERE emp.id<10 AND emp.id>=5";
			employeeList = session.createQuery(hql).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}

			/*
			 * 3.�������������ģ�ORDER BY �Ȱ���һ����������ٰ��ڶ�������
			 */

			hql = "FROM Employee emp WHERE emp.id<10 AND emp.id>=5 " + "ORDER BY emp.name DESC, emp.id ASC ";
			employeeList = session.createQuery(hql).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}

			/*
			 * 4. > ָ��SELECT�־䣬��ѯָ���У���ѯ���е�ʱ����ʹ��SELECT *��ҪSELECT emp FROM Employee emp;�� > ��ѯ����У����ص������鼯�ϡ��������鲻������������Կ���ʹ��new�﷨��ָ���Ѳ�ѯ���Ĳ������Է�װ��������
			 */
			hql = "SELECT emp.name FROM Employee emp WHERE emp.id<=5 ORDER BY emp.name DESC";
			// ���ص���String����(�е�����)���������������������ѯָ������У���ô���صĲ���String���������顣
			List<String> nameList = session.createQuery(hql).list();
			for (String name : nameList) {
				System.out.println(name);
			}

			// ��ѯ����У����ص������顣
			hql = "SELECT emp.name, emp.id FROM Employee emp " + "WHERE emp.id<=5 ORDER BY emp.name DESC";
			List<Object[]> arrayList = session.createQuery(hql).list();
			for (Object[] objects : arrayList) {
				System.out.println(Arrays.toString(objects));
			}

			// ��ѯ����У����ص��Ƕ���û�в�ѯ����Ϊ�գ�,��������Ҫ��Employee�����ṩָ���еĹ��췽�������췽���Ĳ���˳��Ҫ��
			// ��ѯ�е�˳��һ��
			hql = "SELECT new Employee(emp.name, emp.id) FROM Employee emp " + "WHERE emp.id<=5 ORDER BY emp.name DESC";
			employeeList = session.createQuery(hql).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 5.ִ�в�ѯ����ý����list��uniqueResult����ҳ�� uniqueResulet��ѯ��������Ϊ�գ���ѯ��������¼�����쳣�� ������:������5�仯��Ϊ1��
			 */
			hql = "FROM Employee emp";
			query = session.createQuery(hql);
			query.setFirstResult(0);// ��0����¼��ʼ
			query.setMaxResults(10);// ����ÿҳ�����ļ�¼����pageSize
			employeeList = query.list();

			// ������
			employeeList = session.createQuery("FROM Employee emp")//
					.setFirstResult(0)//
					.setMaxResults(10)//
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}

			// ��ȡΨһ���
			hql = "FROM Employee emp WHERE emp.id=300";
			Employee emp = (Employee) session.createQuery(hql).uniqueResult();
			System.out.println(emp);
			// null���������uniqueRusult��list()�Ļ���list().get(0)�ᱨ�����±�Խ��

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

	/**
	 * 1.�ۺϺ���
	 * 2.����
	 * 3.���Ӳ�ѯ/HQL���������Ĳ�ѯ
	 * 4.��ѯʱʹ�ò���
	 * 	> ��ʽһ��ʹ��"?"ռλ
	 *  > ��ʽ���� ʹ�ñ�����
	 * 5.ʹ��������ѯ
	 * 6.update��delete������֪ͨsession���档
	 */
	@Test
	public void testQuery2() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			// ----���Ĵ���----------
			
			String hql = null;
			/*
			 * 1.�ۺϺ���
			 * 	count(),max(),min(),avg(),sum()
			 *  COUNT()���صĽ��������LONG.��������Number�Ϳ�����
			 */
			hql = "SELECT COUNT(emp) FROM Employee emp";//�������ʹ��COUNT(*),���ǲ�ѯ�����е�ʱ����SELECT * FROM...
			Number cnt = (Number)session.createQuery(hql).uniqueResult();
			System.out.println(cnt.longValue());//20
			
			hql = "SELECT MAX(emp.id) FROM Employee emp";
			Number max = (Number)session.createQuery(hql).uniqueResult();
			System.out.println(max.intValue());//20
			
			/*
			 * 2.�����ѯ
			 * 	GROUP BY ... HAVING ...
			 */
			hql = "SELECT emp.name,COUNT(emp.id) AS cnt FROM Employee emp "
					+ "WHERE emp.id<=15 "
					+ "GROUP BY emp.name "
					+ "HAVING COUNT(emp.id)>=5 "	// HAVING�в�����ʹ���б���
					+ "ORDER BY cnt ASC";	// ORDER BY �п���ʹ���б���
			List<Object[]> columnList = session.createQuery(hql)//
					.list();
			for (Object[] objects : columnList) {
				System.out.println(Arrays.toString(objects));
				// û��HAVING����
				// [��YY, 4]
				// [��XX, 5]
				// [��AA, 6]
				
				// ʹ��HAVING����
				//[��XX, 5]
				//[��AA, 6]
			}
			
			/*
			 * 3.���Ӳ�ѯ
			 * SQL���:	SELECT e.id, e.name, d.name FROM employee e 
			 * 			JOIN department d ON e.departmentId = d.id;
			 * HQL��䣺	SELECT e.id, e.name, d.name FROM Employee e JOIN e.department d
			 * ����SQL��HQLһ�����ǣ�
			 * 	> �����ӣ�INNER JOIN ����ʡ��ΪJOIN
			 *  > �����ӣ���������LEFT OUTER JOIN , RIGHT OUTER JOIN �е�OUTER������ʡ����
			 *  
			 *  SQL��HQL������ˣ� �и��򵥵ķ�����
			 *  HQLֱ��Ϊ �� SELECT e.id, e.name, e.department.name FROM Employee e;
			 */
			hql = "SELECT e.id, e.name, d.name FROM Employee e INNER JOIN e.department d";
			hql = "SELECT e.id, e.name, d.name FROM Employee e LEFT JOIN e.department d";
			hql = "SELECT e.id, e.name, d.name FROM Employee e RIGHT JOIN e.department d";
			
			//���򵥵ķ�����һֱ����ȥ������EL���ʽ
			hql = "SELECT e.id, e.name, e.department.name FROM Employee e";
			columnList = session.createQuery(hql)//
					.list();
			for (Object[] objects : columnList) {
				System.out.println(Arrays.toString(objects));
			}
			/*
			 * 4.��ѯ��ʱ��ʹ�ò���
			 * 	> "?"ռλ��
			 * 		setParameter(index,value),��һ��������ʾHQL��������λ�ã��±��0��ʼ���ڶ�������Ϊֵ��
			 * 		���HQL����в����ܶ࣬���Ƽ����ַ���
			 *  > ������
			 *  	��������ʽ��
			 *  		��xxx  ð��+�ַ�
			 */
			// ?ռλ��
			hql = "FROM Employee e WHERE e.id BETWEEN ? AND ?"; //BETWEEEN�����߽�
			List<Employee> employeeList = session.createQuery(hql)//
					.setParameter(0, 5)//��һ��������������λ��,��0��ʼ���ڶ���������������ֵ
					.setParameter(1, 15)
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			
			// ������
			hql = "FROM Employee e WHERE e.id BETWEEN :idMin AND :idMax"; //BETWEEEN�����߽�
			employeeList = session.createQuery(hql)//
					.setParameter("idMax", 15)//��һ��������������λ��,��0��ʼ���ڶ���������������ֵ
					.setParameter("idMin", 5)
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			
			//�������Ǽ���ʱ��Ҫ��setPrameterList("������",Object[])��IN(��idx)ֻ��ʹ�ñ���ռλ
			hql = "FROM Employee e WHERE e.id IN(:ids)";//��ȷ���ʺ��м�������һ���ʺŴ���һ�����ϡ�ֻ��ʹ�ñ���ռλ
			employeeList = session.createQuery(hql)//
					.setParameterList("ids", new Object[] {1,2,3,5,8,100})//100û�оͲ���ʾ
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 5.ʹ��������ѯ��HQL���д�������ļ���
			 * session.getNamedQuery(queryName);
			 * HQLд��.hbm.xml�ļ��У�ʹ��<query>��ǩ��һ���ѯ�ĸ�ʵ�壬��д���ĸ�ʵ���Ӧ�������ļ��С�
			 * <query>��ǩ��<hibernate-mapping>��ֱ���ӱ�ǩ����Ҫд��<class>��ǩ��
			 */
			Query query = session.getNamedQuery("queryByIdRange");
			employeeList = query.setParameter("idMin", 3).setParameter("idMax", 10).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 6.update �� delete������֪ͨsession����
			 * 	ֱ�ӵ���session.update()��������״̬�Ķ���ת��Ϊ�־û����󣬱��汻�޸ĵĶ���session�����С�
			 *  �������1W����¼�أ��ڴ�ͻ������
			 *  ͨ��session.create("UPDATE Employee e SET e.name=?")�������Ͳ����ö��������̬ת��
			 *  Ϊ�־û�״̬
			 *  
			 *  HQL:UPDATE Employee e SET e.name=? WHERE e.id>15
			 *  	DELETE Employee e WHERE e.id > 15
			 *  session.createQuery(hql).executeUpdate();
			 *  	
			 */
			//����
			//��employee��ɳ־û����󣬴�ӡ����
			Employee employee = (Employee)session.get(Employee.class, 13);
			
			System.out.println(employee.getName());//��AA��������
			hql = "UPDATE Employee e SET e.name=? WHERE e.id>10";
			int result = session.createQuery(hql).setParameter(0, "������22")//
				.executeUpdate();//���ص�int���ʹ���Ӱ�켸�м�¼
			System.out.println(result);
			/*
			 * ִ����HQL���ĸ��º��ٴδ�ӡemployee�����ơ����ݿ�ı��ˣ�����session����û�иı�
			 * ����session.refresh()ˢ��session�ж����״̬�Ϳ�����
			 */
			session.refresh(employee);
			System.out.println(employee.getName());//��AA��������22
			//ɾ��
			hql = "DELETE Employee e WHERE e.id > 15";//����дDELETE Ҳ����д DELETE FROM
			result = session.createQuery(hql).executeUpdate();
			System.out.println(result);
			// -------------------
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
