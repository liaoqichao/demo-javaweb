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
	 * 创建测试数据
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

			// ---------核心代码----------------

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
	 * 测试懒加载
	 */
	@Test
	public void testLazy() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ---------核心代码----------------

			Department department = session.get(Department.class, 1);
			// Department department = session.get(Department.class, 1);//执行n次都只查询1次，立刻查询

			System.out.println(department.getName());

			// Hibernate: select employeese0_.departmentId as departme3_1_0_, employeese0_.id as id1_1_0_, employeese0_.id as id1_1_1_, employeese0_.name as name2_1_1_,
			// employeese0_.departmentId as departme3_1_1_
			// from demo20_employee employeese0_ where employeese0_.departmentId=?
			
			/*
			 *  如果没有下面输出语句，就不会执行上面的SQL语句。所以集合的信息是懒加载的.
			 *  可以在<set>标签下添加lazy属性。默认为true
			 *  这里的lazy属性有3个值，true，false，extra
			 *  true：第一次使用时加载
			 *  false：立即加载，在get获取本对象时就加载集合的所有元素。
			 *  extra：增强的懒加载策略。
			 */
			
			//如果lazy="true"，只是查询集合大小，就要查询出所有元素有点太浪费。最好只查询元素总数量，等真正需要的时候才查询。
			//把set中的lazy="extra"后就可以实现这个效果，查询集合的大小，没有查询集合元素的所有信息。等要查询元素的时候
			//才会执行查询元素所有信息的语句。
			
			
			//lazy=extra的SQL语句
//			Hibernate: select count(id) from demo20_employee where departmentId =?
//			System.out.println(department.getEmployeeSet().size());

			/*
			 * 马上加载EmployeeSet，防止出现懒加载异常。
			 * 懒加载异常：在session.close()后调用需要查询数据库的方法。这时候要加载的属性还没被使用过，懒加载还没加载过
			 * 懒加载就会加载，但是session已经关闭，所以抛出懒加载异常。
			 * 解决办法：
			 * 1.关闭懒加载
			 * 2.把session.close()放到需要加载的代码之前
			 * 3.在session.close()之前调用Hibernate.initialize("要加载的内容")。提前加载内容。
			 * 	 即使session.close()后，还是可以通过对象获取到这个属性。(department.getEmployeeSet())
			 */
			Hibernate.initialize(department.getEmployeeSet());//强制加载
			
			//session.close()后调用也可以查询到数据
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
