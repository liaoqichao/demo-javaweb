package hibernate.demo9.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo9.domain.Department;
import hibernate.demo9.domain.Employee;
import lqcUtils.HibernateUtils;

public class EmployeeDao {
	
	
	/**
	 * ����
	 * @param employee
	 */
	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			
			//---------���Ĵ���----------------
			Department department = new Department();
			department.setName("����");
			
			Employee employee1 = new Employee();
			employee1.setName("ƽ��");
			
			Employee employee2 = new Employee();
			employee2.setName("ƽ��");
			
			//����������ϵ
			employee1.setDepartment(department);
			employee2.setDepartment(department);
			department.getEmployeeSet().add(employee1);
			department.getEmployeeSet().add(employee2);
			
			/*
			 * department�е�set������cascade=save-update
			 * ֻ���沿�ţ�������Ա�������沿�ź󣬷���employee1��employee2��������������ţ������Զ���Ҳ��employee1
			 * ��employee2���浽���ݿ��С�
			 */
//			session.save(employee1);
//			session.save(employee2);
			session.save(department);
			
			//---------------------------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null) session.close();
		}
	}
	
//	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			//��ȡһ��
			Department department = session.get(Department.class, 1);//������ջ����쳣��
			System.out.println(department);			
			//��ʾ��һ����Ϣ
			System.out.println(department.getEmployeeSet());
			//-----------------
			
			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);
			System.out.println(employee.getDepartment());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null) session.close();
		}
	}

	/**
	 * //���������ϵ��Ա���뿪���ţ����Ǹı������ֵ���������ֵ����Ϊ�գ�
	 */
//	@Test
	public void testRemoveRelation(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//-----���Ĵ���------------
			
			//��Ա�������������ϵ
//			Employee employee = session.get(Employee.class, 1);
//			employee.setDepartment(null);
			
			//�Ӳ��ŷ����������ϵ
			//���û���������Ϊ��һ��û��ά����<set inverse="true">,��Ϊfalse�Ż�ά��
			Department department = session.get(Department.class, 3);
			department.getEmployeeSet().clear();//�������������е�Ա��
			//-----------------------
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null) session.close();
		}
	}
	/**
	 * ɾ�����󣬶Թ����Ķ����Ӱ�졣
	 * @param employee
	 */
//	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//-----���Ĵ���------------
			
			//ɾ��Ա����,�Բ���û��Ӱ��
//			Employee employee = session.get(Employee.class, 7);
//			session.delete(employee);
			
			/*
			 * ɾ�����ŷ�������û��Ա������ֱ��ɾ����
			 * ���������Ա�����Ȱ�����������е�Ա�������������Ϊnull��Ȼ����ɾ�����š�
			 * ��Ϊ�����в��ű��޸�Ա��������Ϊnull������inverse����Ϊtrue���ʾ��ά����ϵ(���޸�����һ�ű�����)
			 * �����Ļ��й�����ϵ�Ͳ���ɾ�����������ȱ�ɿ���ɾ��(�׳��쳣)��
			 * a.û�й���Ա������ɾ��
			 * b.�й���Ա����inverse=true�����ڲ���ά��������ϵ������һɾ���ͻ�����쳣��
			 * c.�й���Ա����inverse=false�����ڿ���ά��������ϵ�����Կ����Ȱ�Ա�����������Ϊ�գ�Ȼ����ɾ�����š�
			 */
			Department department = session.get(Department.class, 5);
			session.delete(department);
			
			//-----------------------
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null) session.close();
		}
	}
	
}
