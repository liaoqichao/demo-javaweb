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
	 * 准备测试用数据
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
	 * 使用HQL语句查询
	 * 1.简单查询，带别名，AS可以省略
	 * 2.带条件，和SQL一样
	 * 3.ORDER BY 和SQL一样
	 * 4.查询指定列：与SQL不同，new对象把数据封装到指定列，需要实体类提供有参数构造方法。参数顺序按查询顺序。
	 * 5.查询结果：多条记录，一条记录，分页查询
	 */
	// @Test
	public void testQuery1() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();

			// ----核心代码------
			String hql = null;

			/*
			 * 1.简单查询 这里的User可以写简单类名，因为.hbm.xml中的<hibernate-mapping>标签 默认有auto-import="true" 表示这个标签下的类都自动导package="abc"包，这样HQL语句只用写简单类名就可以。 如果要用到另外一个包的同名类，只能写类全名。
			 * 
			 * 可以给类加别名 FROM Employee AS emp，其中AS关键字可以省略。刚列名有关键字的时候可以使用别名。 例如 FROM Employ emp WHERE emp.desc=? ;这样就不会认为desc是降序排序的关键字。
			 * 
			 * 建议以后写都带别名
			 */
			hql = "FROM Employee AS emp";// 这个Employee是类，通过类，和配置文件找到对应的数据库表。写成小写就会报错。
			Query query = session.createQuery(hql);
			List<Employee> employeeList = query.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 2.带上过滤条件（可以使用别名）：WHERE
			 */

			hql = "FROM Employee emp WHERE emp.id<10 AND emp.id>=5";
			employeeList = session.createQuery(hql).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}

			/*
			 * 3.带上排序条件的：ORDER BY 先按第一个排序，相等再按第二个排序。
			 */

			hql = "FROM Employee emp WHERE emp.id<10 AND emp.id>=5 " + "ORDER BY emp.name DESC, emp.id ASC ";
			employeeList = session.createQuery(hql).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}

			/*
			 * 4. > 指定SELECT字句，查询指定列（查询所有的时候不能使用SELECT *，要SELECT emp FROM Employee emp;） > 查询多个列，返回的是数组集合。但是数组不方便操作。所以可以使用new语法，指定把查询出的部分属性封装到对象中
			 */
			hql = "SELECT emp.name FROM Employee emp WHERE emp.id<=5 ORDER BY emp.name DESC";
			// 返回的是String类型(列的类型)，而不是整个对象。如果查询指定多个列，那么返回的不是String，而是数组。
			List<String> nameList = session.createQuery(hql).list();
			for (String name : nameList) {
				System.out.println(name);
			}

			// 查询多个列，返回的是数组。
			hql = "SELECT emp.name, emp.id FROM Employee emp " + "WHERE emp.id<=5 ORDER BY emp.name DESC";
			List<Object[]> arrayList = session.createQuery(hql).list();
			for (Object[] objects : arrayList) {
				System.out.println(Arrays.toString(objects));
			}

			// 查询多个列，返回的是对象（没有查询的列为空）,但是这样要在Employee里面提供指定列的构造方法，构造方法的参数顺序要和
			// 查询列的顺序一样
			hql = "SELECT new Employee(emp.name, emp.id) FROM Employee emp " + "WHERE emp.id<=5 ORDER BY emp.name DESC";
			employeeList = session.createQuery(hql).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 5.执行查询，获得结果（list、uniqueResult、分页） uniqueResulet查询不到返回为空，查询出多条记录会有异常。 方法链:把下面5句化简为1句
			 */
			hql = "FROM Employee emp";
			query = session.createQuery(hql);
			query.setFirstResult(0);// 第0条记录开始
			query.setMaxResults(10);// 设置每页的最大的记录数，pageSize
			employeeList = query.list();

			// 方法链
			employeeList = session.createQuery("FROM Employee emp")//
					.setFirstResult(0)//
					.setMaxResults(10)//
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}

			// 获取唯一结果
			hql = "FROM Employee emp WHERE emp.id=300";
			Employee emp = (Employee) session.createQuery(hql).uniqueResult();
			System.out.println(emp);
			// null，如果不用uniqueRusult用list()的话。list().get(0)会报数组下标越界

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
	 * 1.聚合函数
	 * 2.分组
	 * 3.连接查询/HQL是面向对象的查询
	 * 4.查询时使用参数
	 * 	> 方式一：使用"?"占位
	 *  > 方式二： 使用变量名
	 * 5.使用命名查询
	 * 6.update和delete，不会通知session缓存。
	 */
	@Test
	public void testQuery2() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			// ----核心代码----------
			
			String hql = null;
			/*
			 * 1.聚合函数
			 * 	count(),max(),min(),avg(),sum()
			 *  COUNT()返回的结果类型是LONG.反正都用Number就可以了
			 */
			hql = "SELECT COUNT(emp) FROM Employee emp";//这里可以使用COUNT(*),但是查询所有列的时候不能SELECT * FROM...
			Number cnt = (Number)session.createQuery(hql).uniqueResult();
			System.out.println(cnt.longValue());//20
			
			hql = "SELECT MAX(emp.id) FROM Employee emp";
			Number max = (Number)session.createQuery(hql).uniqueResult();
			System.out.println(max.intValue());//20
			
			/*
			 * 2.分组查询
			 * 	GROUP BY ... HAVING ...
			 */
			hql = "SELECT emp.name,COUNT(emp.id) AS cnt FROM Employee emp "
					+ "WHERE emp.id<=15 "
					+ "GROUP BY emp.name "
					+ "HAVING COUNT(emp.id)>=5 "	// HAVING中不可以使用列别名
					+ "ORDER BY cnt ASC";	// ORDER BY 中可以使用列别名
			List<Object[]> columnList = session.createQuery(hql)//
					.list();
			for (Object[] objects : columnList) {
				System.out.println(Arrays.toString(objects));
				// 没用HAVING过滤
				// [张YY, 4]
				// [李XX, 5]
				// [王AA, 6]
				
				// 使用HAVING过滤
				//[李XX, 5]
				//[王AA, 6]
			}
			
			/*
			 * 3.连接查询
			 * SQL语句:	SELECT e.id, e.name, d.name FROM employee e 
			 * 			JOIN department d ON e.departmentId = d.id;
			 * HQL语句：	SELECT e.id, e.name, d.name FROM Employee e JOIN e.department d
			 * 其中SQL和HQL一样的是：
			 * 	> 内连接：INNER JOIN 可以省略为JOIN
			 *  > 外链接：左外右外LEFT OUTER JOIN , RIGHT OUTER JOIN 中的OUTER都可以省略了
			 *  
			 *  SQL和HQL语句忘了？ 有更简单的方法：
			 *  HQL直接为 ： SELECT e.id, e.name, e.department.name FROM Employee e;
			 */
			hql = "SELECT e.id, e.name, d.name FROM Employee e INNER JOIN e.department d";
			hql = "SELECT e.id, e.name, d.name FROM Employee e LEFT JOIN e.department d";
			hql = "SELECT e.id, e.name, d.name FROM Employee e RIGHT JOIN e.department d";
			
			//更简单的方法，一直点下去，类似EL表达式
			hql = "SELECT e.id, e.name, e.department.name FROM Employee e";
			columnList = session.createQuery(hql)//
					.list();
			for (Object[] objects : columnList) {
				System.out.println(Arrays.toString(objects));
			}
			/*
			 * 4.查询的时候使用参数
			 * 	> "?"占位符
			 * 		setParameter(index,value),第一个参数表示HQL语句参数的位置，下标从0开始。第二个参数为值。
			 * 		如果HQL语句中参数很多，不推荐这种方法
			 *  > 变量名
			 *  	变量名格式：
			 *  		：xxx  冒号+字符
			 */
			// ?占位符
			hql = "FROM Employee e WHERE e.id BETWEEN ? AND ?"; //BETWEEEN包含边界
			List<Employee> employeeList = session.createQuery(hql)//
					.setParameter(0, 5)//第一个参数，参数的位置,从0开始，第二个参数，参数的值
					.setParameter(1, 15)
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			
			// 变量名
			hql = "FROM Employee e WHERE e.id BETWEEN :idMin AND :idMax"; //BETWEEEN包含边界
			employeeList = session.createQuery(hql)//
					.setParameter("idMax", 15)//第一个参数，参数的位置,从0开始，第二个参数，参数的值
					.setParameter("idMin", 5)
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			
			//当参数是集合时。要用setPrameterList("变量名",Object[])。IN(：idx)只能使用变量占位
			hql = "FROM Employee e WHERE e.id IN(:ids)";//不确定问号有几个，用一个问号代表一个集合。只能使用变量占位
			employeeList = session.createQuery(hql)//
					.setParameterList("ids", new Object[] {1,2,3,5,8,100})//100没有就不显示
					.list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 5.使用命名查询。HQL语句写在配置文件。
			 * session.getNamedQuery(queryName);
			 * HQL写在.hbm.xml文件中，使用<query>标签。一般查询哪个实体，就写在哪个实体对应的配置文件中。
			 * <query>标签是<hibernate-mapping>的直接子标签，不要写在<class>标签下
			 */
			Query query = session.getNamedQuery("queryByIdRange");
			employeeList = query.setParameter("idMin", 3).setParameter("idMax", 10).list();
			for (Employee employee : employeeList) {
				System.out.println(employee);
			}
			/*
			 * 6.update 与 delete，不会通知session缓存
			 * 	直接调用session.update()，把游离状态的对象转化为持久化对象，保存被修改的对象到session缓存中。
			 *  如果更新1W条记录呢？内存就会溢出。
			 *  通过session.create("UPDATE Employee e SET e.name=?")这样，就不会让对象从游离态转化
			 *  为持久化状态
			 *  
			 *  HQL:UPDATE Employee e SET e.name=? WHERE e.id>15
			 *  	DELETE Employee e WHERE e.id > 15
			 *  session.createQuery(hql).executeUpdate();
			 *  	
			 */
			//更新
			//把employee变成持久化对象，打印名称
			Employee employee = (Employee)session.get(Employee.class, 13);
			
			System.out.println(employee.getName());//王AA，无名氏
			hql = "UPDATE Employee e SET e.name=? WHERE e.id>10";
			int result = session.createQuery(hql).setParameter(0, "无名氏22")//
				.executeUpdate();//返回的int类型代表影响几行记录
			System.out.println(result);
			/*
			 * 执行完HQL语句的更新后，再次打印employee的名称。数据库改变了，但是session缓存没有改变
			 * 调用session.refresh()刷新session中对象的状态就可以了
			 */
			session.refresh(employee);
			System.out.println(employee.getName());//王AA，无名氏22
			//删除
			hql = "DELETE Employee e WHERE e.id > 15";//可以写DELETE 也可以写 DELETE FROM
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
