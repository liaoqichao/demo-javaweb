package domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class Demo25_beanUtils {

	/**
	 * 通过类名得到类对象
	 */
	@Test
	public void func1() throws Exception{
		String className = "domain.Demo24_Person";
		Class clazz = Class.forName(className);
		Object bean = clazz.newInstance();
		
		BeanUtils.setProperty(bean, "name", "张三");
		BeanUtils.setProperty(bean, "age", "23");//自动把字符串23变成整数23
		BeanUtils.setProperty(bean, "bb", "true");
		BeanUtils.setProperty(bean, "xxx", "XXX");//没有xxx属性就不赋值也不用抛异常。

		System.out.println(bean.toString());
		//Demo24_Person [name=张三, age=23, gender=null, bb=true]
		
		int age = Integer.parseInt(BeanUtils.getProperty(bean, "age"));
		System.out.println("age = "+age);
	}
	
	/**
	 * 把map的属性直接封装到一个bean中
	 * 前提是map的键与bean的属性名相同。
	 * @throws Exception 
	 */
	@Test
	public void func2() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", "张三");
		map.put("password", "123");
		
		Demo25_example_User user = new Demo25_example_User();
		BeanUtils.populate(user, map);//populate居住于; 生活于; 移民于; 落户于;
		
		System.out.println(user);

	}
}
