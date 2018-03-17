package demo;

import org.junit.Test;

import cn.itcast.beanfactory.BeanFactory;
import demo.dao.StudentDao;
import demo.domain.Student;
import demo.service.StudentService;

public class Demo1 {

	public void fun1(){
		/*
		 * 1.创建工厂,创建时需要给工厂指定配置文件
		 * 2.从工厂中获取bean对象
		 * 
		 * 以后不会在xml配置student。但是会配置Action（MVC模式下充当控制层的类，相当于servlet）、Service、Dao
		 */
		BeanFactory bf = new BeanFactory("beans.xml");
		Student stu1 = (Student) bf.getBean("stu1");
		Student stu2 = (Student) bf.getBean("stu1");
//		System.out.println(stu1);
		//Student [number=ITCAST_1001, name=zhangSan, age=29, sex=male]
		System.out.println(stu1 == stu2);//true工厂的实例是单例的
		/*
		 * beanFactory高级的<bean id="stu1" className="demo.domain.Student" scope="singleton">
		 * 会有scope属性，singleton表示单例，prototype表示不是单例（原型）。
		 */
	}
	public void fun2(){
		/*
		 * 1.创建工厂,创建时需要给工厂指定配置文件
		 * 2.从工厂中获取bean对象
		 * 
		 * 以后不会在xml配置student。但是会配置Action（MVC模式下充当控制层的类，相当于servlet）、Service、Dao
		 */
		BeanFactory bf = new BeanFactory("beans.xml");
		Student stu1 = (Student) bf.getBean("stu1");
		Student stu2 = (Student) bf.getBean("stu2");
//		System.out.println(stu1.getTeacher());
		//Teacher [tid=TEACHER_2001, name=liSi, salary=123.456]
		System.out.println(stu1.getTeacher()==stu2.getTeacher());//true。同一个id就是同一个老师
	}
	
	public void fun3(){
		BeanFactory bf = new BeanFactory("beans.xml");
		Student stu1 = (Student) bf.getBean("stu1");
		StudentDao stuDao = (StudentDao) bf.getBean("stuDao");//转换成接口类型就可以了，转换成具体类型面向接口就没有意义了。
		/*
		 * 现在修改配置文件的className，输出结果就改变，不需要改代码
		 */
		stuDao.add(stu1);//studentDaoImpl#add	，studentDaoImpl2#add222
		stuDao.update(null);//studentDaoImpl#update	，studentDaoImpl2#update222
	}
	
	@Test
	public void fun4(){
		BeanFactory bf = new BeanFactory("beans.xml");
		StudentService studentService = (StudentService) bf.getBean("stuService");
		studentService.login();
//		studentDaoImpl2#add222
//		studentDaoImpl2#update222
	}
}
