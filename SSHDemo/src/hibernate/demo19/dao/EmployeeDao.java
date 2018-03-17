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
	 * 准备测试用数据
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

			// ---------核心代码----------------

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

			// ----核心代码------

			//创建criteria对象
			Criteria criteria = session.createCriteria(Employee.class);
			
			/*
			 * 增加过滤条件
			 * Restrictions类有一堆静态方法来提供条件。例如Restrictions.lt代表<,
			 * Restrictions.le代表<= 这些关键字和el表达式类似。 
			 */
			criteria.add(Restrictions.ge("id",1));
			criteria.add(Restrictions.le("id",5));

			/*
			 * 增加排序条件
			 * Order对象的静态方法提供排序方式.
			 * 如果2个addOrder，先调用add方法的作为最优先比较列，第二个为最优先比较列比较相等的时候才用。
			 */
			criteria.addOrder(Order.desc("id"));
			criteria.addOrder(Order.asc("name"));
			
			//执行查询
			/*
			 * 分页查询
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
