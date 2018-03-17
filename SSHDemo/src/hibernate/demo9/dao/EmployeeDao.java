package hibernate.demo9.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo9.domain.Department;
import hibernate.demo9.domain.Employee;
import lqcUtils.HibernateUtils;

public class EmployeeDao {
	
	
	/**
	 * 保存
	 * @param employee
	 */
	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			
			//---------核心代码----------------
			Department department = new Department();
			department.setName("服部");
			
			Employee employee1 = new Employee();
			employee1.setName("平次");
			
			Employee employee2 = new Employee();
			employee2.setName("平藏");
			
			//建立关联关系
			employee1.setDepartment(department);
			employee2.setDepartment(department);
			department.getEmployeeSet().add(employee1);
			department.getEmployeeSet().add(employee2);
			
			/*
			 * department中的set设置了cascade=save-update
			 * 只保存部门，不保存员工。保存部门后，发下employee1和employee2都引用了这个部门，所以自动的也把employee1
			 * 和employee2保存到数据库中。
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
			
			//----核心代码------
			//获取一方
			Department department = session.get(Department.class, 1);//这句出现栈溢出异常。
			System.out.println(department);			
			//显示另一方信息
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
	 * //解除关联关系（员工离开部门）就是改变外键的值（变成其他值或者为空）
	 */
//	@Test
	public void testRemoveRelation(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//-----核心代码------------
			
			//从员工方解除关联关系
//			Employee employee = session.get(Employee.class, 1);
//			employee.setDepartment(null);
			
			//从部门方解除关联关系
			//结果没有想过，因为这一方没有维护，<set inverse="true">,改为false才会维护
			Department department = session.get(Department.class, 3);
			department.getEmployeeSet().clear();//清空这个部门所有的员工
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
	 * 删除对象，对关联的对象的影响。
	 * @param employee
	 */
//	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//-----核心代码------------
			
			//删除员工方,对部门没有影响
//			Employee employee = session.get(Employee.class, 7);
//			session.delete(employee);
			
			/*
			 * 删除部门方，部门没有员工可以直接删除。
			 * 如果部门有员工，先把这个部门所有的员工的外键都设置为null，然后再删除部门。
			 * 因为这里有部门表修改员工表的外键为null。所以inverse属性为true则表示不维护关系(不修改另外一张表的外键)
			 * 这样的话有关联关系就不能删除，而不是先变成空再删除(抛出异常)。
			 * a.没有关联员工：能删除
			 * b.有关联员工，inverse=true。由于不能维护关联关系，所以一删除就会出现异常。
			 * c.有关联员工，inverse=false。由于可以维护关联关系，所以可以先把员工的外键列设为空，然后再删除部门。
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
