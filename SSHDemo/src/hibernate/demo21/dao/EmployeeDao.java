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
	 * 创建测试数据
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
	 * 测试二级缓存
	 * 一级缓存指的是session的缓存
	 * 	> 针对一次连接所做的操作
	 *  > 通常一个请求使用一次session
	 * 二级缓存指的是sessionFactory的缓存
	 * 	一个session内多次获取同一条记录只需要访问一次数据库
	 *  但是两个sesion获取同一个条记录呢？
	 *  
	 *  实际上一级缓存的作用是为了管理对象，二级缓存的作用才是为了提供性能。
	 *  session中缓存没有  --> 找二级缓存(sessionFactory的缓存) --> 如果没有再找数据库
	 */
	
	@Test
	public void testSecondCache() {
		Session session = null;
		Transaction tx = null;
		/*
		 * 1. 如果没有开启二级缓存，下面2个session会执行2次SQL语句。如果开启二级缓存，他们找的是同一个对象，
		 * 那么第二次会在二级缓存找，所以只有一条SQL语句
		 * 2. 使用集合缓存时，集合存储的实体也要配置到二级缓存，不然集合缓存没用。
		 * 3. 无论一级缓存还是二级缓存都是根据OID找到缓存中的对象。
		 * 		如果根据HQL语句查找对象，对象都会保存到一级缓存和二级缓存。
		 * 		但是不是所有查询都会从二级缓存中获取。只有get，load方法会从二级缓存获取。
		 * 		对于Query.list()方法默认不会使用缓存，使用Query.iterate()会从二级缓存中查询，但是
		 * 		返回的是iterator，而不是List
		 */
		/*
		 * 第一个session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------核心代码----------------
			
//			Employee employee = session.get(Employee.class, 1);
//			System.out.println(employee);
			
			/*
			 * 如果usage为read-only的话。修改就会报异常。
			 */
//			Department department = session.get(Department.class, 1);
//			department.setName("研发部");
			
			
			/*
			 * 只是类有缓存，集合没缓存，查询2次
			 * <collection-cache usage="read-write" 
			 * collection="hibernate.demo21.domain.Department.employeeSet"/>
			 * 配置集合缓存后置执行一次缓存
			 */
//			System.out.println(department.getEmployeeSet());
			
			
			
			/*
			 * HQL语句查询,使用HQL查询的结果会放到二级缓存.要放到缓存就要根据OID查询，所以前面每条记录都对应
			 * 一个查询语句。
			 * iterate()方法： 这方法性能提高有限，不太常用
			 * 先查询id的集合，
			 * 然后按照每个id查询。这样就会有n+1次查询。
			 * 例如：
			 * e.id<8就有7条查询语句+1条查询id<8的语句
			 * 第二个session，查9条，前面7条从缓存拿，后面2条有查询语句
			 * 
			 * 最终方法，调用query.setCacheable(true)。
			 * 前提要在主配置文件配置
			 * <property name="hibernate.cache.use_query_cache">true</property>
			 */
			String hql ="FROM Employee e WHERE e.id<18";
			
			List<Employee> employeeList = session.createQuery(hql)//
					.setCacheable(true)//是否使用查询缓存，前提必须是同样条件
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
		 * 第二个session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------核心代码----------------
//			Employee employee = session.get(Employee.class, 1);
//			System.out.println(employee);
			
//			Department department =  session.get(Department.class, 1);
//			System.out.println(department.getEmployeeSet());
			
			String hql ="FROM Employee e WHERE e.id<18";
			List<Employee> employeeList = session.createQuery(hql)//
					.setCacheable(true)//是否使用查询缓存，前提必须是同样条件
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
	 * 更新时间戳缓存。
	 * 测试UPDATE,DELETE格式的HQL语句对二级缓存的影响。
	 * 第一个session使用HQL进行更新，第二个session获取这个对象，获取的对象的数据是更新后的。
	 * 执行HQL语句后，二级缓存会废除原先的缓存，等需要的时候再向数据库重新查询。
	 */
//	@Test
	public void testUpdateTimestampCache() {
		Session session = null;
		Transaction tx = null;
	
		/*
		 * 第一个session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------核心代码----------------
			
			//先获取
			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);
			//直接更新到DB
			String hql = "UPDATE Employee e SET e.name=:name WHERE e.id=:id";
			session.createQuery(hql)//
				.setParameter("id", 1)//
				.setParameter("name", "测试")//
				.executeUpdate();
			System.out.println(employee.getName());//数据库变了，但是session缓存还在，所以这个输出是旧结果
			

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
		 * 第二个session
		 */
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			// ---------核心代码----------------

			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);//结果变了
			
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
