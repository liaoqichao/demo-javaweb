package demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Person;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ��ʾjson-libС����
 */
public class JsonlibDemo {

	/*
	 * JSONObject��map����
	 */
	public void fun1(){
		JSONObject map = new JSONObject();
		map.put("name", "zhangSan");
		map.put("age", 23);
		map.put("sex", "male");
		String s = map.toString();
		System.out.println(s);
		//{"name":"zhangSan","age":23,"sex":"male"}
	}
	
	/*
	 * ����һ��javabeanʱ�����԰�javabean���json��
	 */
	public void fun2(){
		Person p = new Person("zhangSan",23,"male");
		JSONObject map = JSONObject.fromObject(p);//��javabean���JSONObject��Ҳ������map
		System.out.println(map.toString());//{"age":23,"name":"zhangSan","sex":"male"}
	}
	
	/*
	 * JSONArray��List����
	 * �����js�����е�����
	 */
	public void fun3(){
		
		Person p1 = new Person("zhangSan", 23, "male");
		Person p2 = new Person("liSi", 26, "female");
		JSONArray list = new JSONArray();
		list.add(p1);
		list.add(p2);
		
		System.out.println(list.toString());
		//[{"age":23,"name":"zhangSan","sex":"male"},{"age":26,"name":"liSi","sex":"female"}]
	}
	
	@Test
	/*
	 * �������и�List<Person>����Ҫת����JSONArray
	 */
	public void fun4(){
		List<Person> list = new ArrayList<Person>();
		Person p1 = new Person("zhangSan", 23, "male");
		Person p2 = new Person("liSi", 26, "female");
		list.add(p1);
		list.add(p2);
		
		System.out.println(JSONArray.fromObject(list).toString());
		//[{"age":23,"name":"zhangSan","sex":"male"},{"age":26,"name":"liSi","sex":"female"}]
	}
}
