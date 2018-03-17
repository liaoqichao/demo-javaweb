package demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import domain.City;
import domain.Province;

public class XStreamDemo {

	//����javabean����
	public List<Province> getProvinceList(){
		
		Province p1 = new Province();
		p1.setName("����");
		p1.addCity(new City("������","dongchengqu"));
		p1.addCity(new City("��ƽ��","changping"));
		
		Province p2 = new Province();
		p2.setName("����");
		p2.addCity(new City("����","shenyang"));
		p2.addCity(new City("��«��","huludao"));
		
		List<Province> provinceList = new ArrayList<Province>();
		provinceList.add(p1);
		provinceList.add(p2);
		
		return provinceList;
	}
	
	public void fun1(){
		//1.�õ�javabean����
		List<Province> provinceList = getProvinceList();
		//2.�õ�XStream����
		XStream xstream = new XStream();
		//3.����toXML�Ѽ���ת����xml�ַ���
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<list> 		��Ϊ���Ĳ�����List���ͣ����Ը�Ԫ��Ϊlist
//		  <domain.Province>		����
//		    <name>����</name>		javabean����������ֵ
//		    <cities>			javabean������
//		      <domain.City>		javabean������
//		        <name>������</name>	javabean����������ֵ
//		        <description>dongchengqu</description>javabean����������ֵ
//		      </domain.City>
//		      <domain.City>
//		        <name>��ƽ��</name>
//		        <description>changping</description>
//		      </domain.City>
//		    </cities>
//		  </domain.Province>
//		  <domain.Province>
//		    <name>����</name>
//		    <cities>
//		      <domain.City>
//		        <name>����</name>
//		        <description>shenyang</description>
//		      </domain.City>
//		      <domain.City>
//		        <name>��«��</name>
//		        <description>huludao</description>
//		      </domain.City>
//		    </cities>
//		  </domain.Province>
//		</list>

	}
	
	/*
	 * ����(alias)
	 * ϣ��:
	 * 	Ĭ��List���Ͷ�Ӧ<list>Ԫ�أ�ϣ����List��Ӧ<china>
	 * 	Ĭ��Province���Ͷ�Ӧ<domain.Province>Ԫ�أ�ϣ����Province���Ͷ�Ӧ<province>
	 * 	Ĭ��City���Ͷ�Ӧ<domain.City>Ԫ�أ�ϣ����City���Ͷ�Ӧ<city>
	 */
	public void fun2(){
		//1.�õ�javabean����
		List<Province> provinceList = getProvinceList();
		//2.�õ�XStream����
		XStream xstream = new XStream();
		
		///////////////////////////////////////////////
		/*
		 * ��ָ�����������
		 */
		xstream.alias("china", List.class);
		xstream.alias("province",Province.class);
		xstream.alias("city",City.class);
		/////////////////////////////////////////////////
		
		//3.����toXML�Ѽ���ת����xml�ַ���
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<china>
//		  <province>
//		    <name>����</name>
//		    <cities>
//		      <city>
//		        <name>������</name>
//		        <description>dongchengqu</description>
//		      </city>
//		      <city>
//		        <name>��ƽ��</name>
//		        <description>changping</description>
//		      </city>
//		    </cities>
//		  </province>
//		  <province>
//		    <name>����</name>
//		    <cities>
//		      <city>
//		        <name>����</name>
//		        <description>shenyang</description>
//		      </city>
//		      <city>
//		        <name>��«��</name>
//		        <description>huludao</description>
//		      </city>
//		    </cities>
//		  </province>
//		</china>
	}
	
	/*
	 * ����fun2�е�<cities>Ԫ�غ���ûʲô�ã�����ȥ����Province��nameӦ����Ϊ���ԣ���������Ϊ��Ԫ��
	 * <cities>����û�õ�Ԫ�أ���Ӧ����Collection������
	 */
	public void fun3(){
		//1.�õ�javabean����
		List<Province> provinceList = getProvinceList();
		//2.����XStream
		XStream xstream = new XStream();
		//3.�����
		xstream.alias("china", List.class);
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		
		//////////////////////////////////////////////////////
		
		//4.��JavaBean�е�Province���name���ԣ����<province>Ԫ���е�����
		//��Province�е�name���Ԫ��<province>�е����ԣ����û��ָ�����;Ͳ�֪����Province�е�name����City�е�name
		xstream.useAttributeFor(Province.class, "name");
		//5.ȥ��Collection��Ӧ�ı�ǩ����<cities>
		xstream.addImplicitCollection(Province.class, "cities");//ȥ��Province��Ϊcities��Collection������
		
		///////////////////////////////////////////////////////
		
		//6.ת��Ϊ�ַ���
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<china>
//		  <province name="����">
//		    <city>
//		      <name>������</name>
//		      <description>dongchengqu</description>
//		    </city>
//		    <city>
//		      <name>��ƽ��</name>
//		      <description>changping</description>
//		    </city>
//		  </province>
//		  <province name="����">
//		    <city>
//		      <name>����</name>
//		      <description>shenyang</description>
//		    </city>
//		    <city>
//		      <name>��«��</name>
//		      <description>huludao</description>
//		    </city>
//		  </province>
//		</china>

	}
	
	@Test
	/*
	 * ȥ������Ҫ��javabean���ԣ�������javabean��ĳЩ���Բ�����xmlԪ����
	 * ȥ��city��descriptionԪ��
	 */
	public void fun4(){
		
		//1.�õ�javabean����
		List<Province> provinceList = getProvinceList();
		//2.����XStream
		XStream xstream = new XStream();
		//3.�����
		xstream.alias("china", List.class);
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		//4.��JavaBean�е�Province���name���ԣ����<province>Ԫ���е�����
		//��Province�е�name���Ԫ��<province>�е����ԣ����û��ָ�����;Ͳ�֪����Province�е�name����City�е�name
		xstream.useAttributeFor(Province.class, "name");
		//5.ȥ��Collection��Ӧ�ı�ǩ����<cities>
		xstream.addImplicitCollection(Province.class, "cities");//ȥ��Province��Ϊcities��Collection������
		
		///////////////////////////////////////////////////////////
		//6.ȥ������Ҫ��javabean���ԣ�������javabean��ĳЩ���Բ�����xmlԪ����
		xstream.omitField(City.class, "description");
		///////////////////////////////////////////////////////////
		
		//7.ת��Ϊ�ַ���
		String xmlStr = xstream.toXML(provinceList);
		System.out.println(xmlStr);
		
//		<china>
//		  <province name="����">
//		    <city>
//		      <name>������</name>
//		    </city>
//		    <city>
//		      <name>��ƽ��</name>
//		    </city>
//		  </province>
//		  <province name="����">
//		    <city>
//		      <name>����</name>
//		    </city>
//		    <city>
//		      <name>��«��</name>
//		    </city>
//		  </province>
//		</china>
	}
}
