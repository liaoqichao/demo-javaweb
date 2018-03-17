package domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class Demo25_beanUtils {

	/**
	 * ͨ�������õ������
	 */
	@Test
	public void func1() throws Exception{
		String className = "domain.Demo24_Person";
		Class clazz = Class.forName(className);
		Object bean = clazz.newInstance();
		
		BeanUtils.setProperty(bean, "name", "����");
		BeanUtils.setProperty(bean, "age", "23");//�Զ����ַ���23�������23
		BeanUtils.setProperty(bean, "bb", "true");
		BeanUtils.setProperty(bean, "xxx", "XXX");//û��xxx���ԾͲ���ֵҲ�������쳣��

		System.out.println(bean.toString());
		//Demo24_Person [name=����, age=23, gender=null, bb=true]
		
		int age = Integer.parseInt(BeanUtils.getProperty(bean, "age"));
		System.out.println("age = "+age);
	}
	
	/**
	 * ��map������ֱ�ӷ�װ��һ��bean��
	 * ǰ����map�ļ���bean����������ͬ��
	 * @throws Exception 
	 */
	@Test
	public void func2() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", "����");
		map.put("password", "123");
		
		Demo25_example_User user = new Demo25_example_User();
		BeanUtils.populate(user, map);//populate��ס��; ������; ������; �仧��;
		
		System.out.println(user);

	}
}
