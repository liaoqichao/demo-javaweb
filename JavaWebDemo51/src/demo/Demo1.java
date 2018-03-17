package demo;

import org.junit.Test;

import cn.itcast.beanfactory.BeanFactory;
import demo.dao.StudentDao;
import demo.domain.Student;
import demo.service.StudentService;

public class Demo1 {

	public void fun1(){
		/*
		 * 1.��������,����ʱ��Ҫ������ָ�������ļ�
		 * 2.�ӹ����л�ȡbean����
		 * 
		 * �Ժ󲻻���xml����student�����ǻ�����Action��MVCģʽ�³䵱���Ʋ���࣬�൱��servlet����Service��Dao
		 */
		BeanFactory bf = new BeanFactory("beans.xml");
		Student stu1 = (Student) bf.getBean("stu1");
		Student stu2 = (Student) bf.getBean("stu1");
//		System.out.println(stu1);
		//Student [number=ITCAST_1001, name=zhangSan, age=29, sex=male]
		System.out.println(stu1 == stu2);//true������ʵ���ǵ�����
		/*
		 * beanFactory�߼���<bean id="stu1" className="demo.domain.Student" scope="singleton">
		 * ����scope���ԣ�singleton��ʾ������prototype��ʾ���ǵ�����ԭ�ͣ���
		 */
	}
	public void fun2(){
		/*
		 * 1.��������,����ʱ��Ҫ������ָ�������ļ�
		 * 2.�ӹ����л�ȡbean����
		 * 
		 * �Ժ󲻻���xml����student�����ǻ�����Action��MVCģʽ�³䵱���Ʋ���࣬�൱��servlet����Service��Dao
		 */
		BeanFactory bf = new BeanFactory("beans.xml");
		Student stu1 = (Student) bf.getBean("stu1");
		Student stu2 = (Student) bf.getBean("stu2");
//		System.out.println(stu1.getTeacher());
		//Teacher [tid=TEACHER_2001, name=liSi, salary=123.456]
		System.out.println(stu1.getTeacher()==stu2.getTeacher());//true��ͬһ��id����ͬһ����ʦ
	}
	
	public void fun3(){
		BeanFactory bf = new BeanFactory("beans.xml");
		Student stu1 = (Student) bf.getBean("stu1");
		StudentDao stuDao = (StudentDao) bf.getBean("stuDao");//ת���ɽӿ����;Ϳ����ˣ�ת���ɾ�����������ӿھ�û�������ˡ�
		/*
		 * �����޸������ļ���className���������͸ı䣬����Ҫ�Ĵ���
		 */
		stuDao.add(stu1);//studentDaoImpl#add	��studentDaoImpl2#add222
		stuDao.update(null);//studentDaoImpl#update	��studentDaoImpl2#update222
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
