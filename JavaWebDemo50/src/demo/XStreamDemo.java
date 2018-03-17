package demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import domain.City;
import domain.Province;

public class XStreamDemo {

	//返回javabean集合
	public List<Province> getProvinceList(){
		
		Province p1 = new Province();
		p1.setName("北京");
		p1.addCity(new City("东城区","dongchengqu"));
		p1.addCity(new City("昌平区","changping"));
		
		Province p2 = new Province();
		p2.setName("辽宁");
		p2.addCity(new City("沈阳","shenyang"));
		p2.addCity(new City("葫芦岛","huludao"));
		
		List<Province> provinceList = new ArrayList<Province>();
		provinceList.add(p1);
		provinceList.add(p2);
		
		return provinceList;
	}
	
	public void fun1(){
		//1.得到javabean集合
		List<Province> provinceList = getProvinceList();
		//2.得到XStream对象
		XStream xstream = new XStream();
		//3.调用toXML把集合转换成xml字符串
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<list> 		因为给的参数是List类型，所以根元素为list
//		  <domain.Province>		类名
//		    <name>北京</name>		javabean的属性名和值
//		    <cities>			javabean属性名
//		      <domain.City>		javabean的类名
//		        <name>东城区</name>	javabean的属性名和值
//		        <description>dongchengqu</description>javabean的属性名和值
//		      </domain.City>
//		      <domain.City>
//		        <name>昌平区</name>
//		        <description>changping</description>
//		      </domain.City>
//		    </cities>
//		  </domain.Province>
//		  <domain.Province>
//		    <name>辽宁</name>
//		    <cities>
//		      <domain.City>
//		        <name>沈阳</name>
//		        <description>shenyang</description>
//		      </domain.City>
//		      <domain.City>
//		        <name>葫芦岛</name>
//		        <description>huludao</description>
//		      </domain.City>
//		    </cities>
//		  </domain.Province>
//		</list>

	}
	
	/*
	 * 别名(alias)
	 * 希望:
	 * 	默认List类型对应<list>元素，希望让List对应<china>
	 * 	默认Province类型对应<domain.Province>元素，希望让Province类型对应<province>
	 * 	默认City类型对应<domain.City>元素，希望让City类型对应<city>
	 */
	public void fun2(){
		//1.得到javabean集合
		List<Province> provinceList = getProvinceList();
		//2.得到XStream对象
		XStream xstream = new XStream();
		
		///////////////////////////////////////////////
		/*
		 * 给指定类型起别名
		 */
		xstream.alias("china", List.class);
		xstream.alias("province",Province.class);
		xstream.alias("city",City.class);
		/////////////////////////////////////////////////
		
		//3.调用toXML把集合转换成xml字符串
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<china>
//		  <province>
//		    <name>北京</name>
//		    <cities>
//		      <city>
//		        <name>东城区</name>
//		        <description>dongchengqu</description>
//		      </city>
//		      <city>
//		        <name>昌平区</name>
//		        <description>changping</description>
//		      </city>
//		    </cities>
//		  </province>
//		  <province>
//		    <name>辽宁</name>
//		    <cities>
//		      <city>
//		        <name>沈阳</name>
//		        <description>shenyang</description>
//		      </city>
//		      <city>
//		        <name>葫芦岛</name>
//		        <description>huludao</description>
//		      </city>
//		    </cities>
//		  </province>
//		</china>
	}
	
	/*
	 * 但是fun2中的<cities>元素好像没什么用，可以去掉，Province的name应该作为属性，而不是作为子元素
	 * <cities>这类没用的元素，对应的是Collection的容器
	 */
	public void fun3(){
		//1.得到javabean集合
		List<Province> provinceList = getProvinceList();
		//2.创建XStream
		XStream xstream = new XStream();
		//3.起别名
		xstream.alias("china", List.class);
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		
		//////////////////////////////////////////////////////
		
		//4.把JavaBean中的Province类的name属性，变成<province>元素中的属性
		//把Province中的name变成元素<province>中的属性，如果没有指定类型就不知道是Province中的name还是City中的name
		xstream.useAttributeFor(Province.class, "name");
		//5.去掉Collection对应的标签例如<cities>
		xstream.addImplicitCollection(Province.class, "cities");//去掉Province名为cities的Collection容器。
		
		///////////////////////////////////////////////////////
		
		//6.转换为字符串
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<china>
//		  <province name="北京">
//		    <city>
//		      <name>东城区</name>
//		      <description>dongchengqu</description>
//		    </city>
//		    <city>
//		      <name>昌平区</name>
//		      <description>changping</description>
//		    </city>
//		  </province>
//		  <province name="辽宁">
//		    <city>
//		      <name>沈阳</name>
//		      <description>shenyang</description>
//		    </city>
//		    <city>
//		      <name>葫芦岛</name>
//		      <description>huludao</description>
//		    </city>
//		  </province>
//		</china>

	}
	
	@Test
	/*
	 * 去除不想要的javabean属性，就是让javabean的某些属性不生成xml元素中
	 * 去掉city的description元素
	 */
	public void fun4(){
		
		//1.得到javabean集合
		List<Province> provinceList = getProvinceList();
		//2.创建XStream
		XStream xstream = new XStream();
		//3.起别名
		xstream.alias("china", List.class);
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		//4.把JavaBean中的Province类的name属性，变成<province>元素中的属性
		//把Province中的name变成元素<province>中的属性，如果没有指定类型就不知道是Province中的name还是City中的name
		xstream.useAttributeFor(Province.class, "name");
		//5.去掉Collection对应的标签例如<cities>
		xstream.addImplicitCollection(Province.class, "cities");//去掉Province名为cities的Collection容器。
		
		///////////////////////////////////////////////////////////
		//6.去除不想要的javabean属性，就是让javabean的某些属性不生成xml元素中
		xstream.omitField(City.class, "description");
		///////////////////////////////////////////////////////////
		
		//7.转换为字符串
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<china>
//		  <province name="北京">
//		    <city>
//		      <name>东城区</name>
//		    </city>
//		    <city>
//		      <name>昌平区</name>
//		    </city>
//		  </province>
//		  <province name="辽宁">
//		    <city>
//		      <name>沈阳</name>
//		    </city>
//		    <city>
//		      <name>葫芦岛</name>
//		    </city>
//		  </province>
//		</china>
	}
}
