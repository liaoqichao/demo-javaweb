package junit.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssh.dao.UserDao;
import ssh.domain.Employee;
import ssh.domain.User;
import ssh.service.EmployeeService;

public class EmployeeDaoTest {

	private static EmployeeService employeeService;
	private static UserDao userDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		employeeService = (EmployeeService)ctx.getBean("employeeService");
		userDao = (UserDao) ctx.getBean("userDao");
	}

	@Test
	public void testSave() {
//		Employee employee = new Employee("王八");
////		employeeDao.save(employee);
//		employeeService.save(employee);
		
		User user = new User();
		user.setUsername("张三");
		userDao.save(user);
	}

//	@Test
	public void testDelete() {
		employeeService.delete(4);
	}

//	@Test
	public void testUpdate() {
		Employee employee = employeeService.getEmployee(2);
		employee.setName("李四四");
		employeeService.update(employee);
	}

//	@Test
	public void testGetEmployee() {
		Employee employee = employeeService.getEmployee(2);
		System.out.println(employee);
	}

//	@Test
	public void testGetEmployeeList() {
		System.out.println(employeeService.getEmployeeList());
	}

}
