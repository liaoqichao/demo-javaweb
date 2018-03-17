package demo;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class Demo41_internationalization {
	/**
	 * д���������ļ�(demo41Res_zh_CN.properties��demo41Res_en_US.properties)��һ���ļ��д��������Ϣ��һ���ļ����Ӣ����Ϣ
	 * ��Դ�ļ����Ƶĸ�ʽ����������+Locale����+.properties
	 * �������ƣ�demo41Res(�������»���)
	 * Locale����:zh_CN
	 * ������������Դ�ļ������ƵĻ������Ʊ�����ͬ,��֮ͬ������locale����
	 * 
	 * ʹ��ResourceBundle�࣬ͨ�����Ի�����ʶ������ĸ��ļ�����Ϣ��
	 * ���Ի�����Locale�ࡣ
	 * zh_CN : ����_�й�
	 * en_US : Ӣ��_����
	 * new Locale("zh","CN");//�������Ի�������û�д���ϵͳ���ȡLocale.getDefault();
	 * @throws UnsupportedEncodingException 
	 */
	
	@Test
	public void fun1() throws UnsupportedEncodingException{
		//1.�õ�locale
		Locale locale = Locale.CHINA  ;//Locale locale = request.getLocale();
		//���ĳ������룿Ҫ��ת��
		//2.�õ�ResourceBundle����һ�������ǻ������ƣ��ڶ���������local����
		ResourceBundle rb = ResourceBundle.getBundle("demo41Res", locale);
//		-��Դ�ļ���UTF-8����,rb��ȡ����ISO-8859-1���룬����rb�õ���ֵ����ҪISO-8859-1������UTF-8���� -
		//ʹ��rb��ȡ��Դ��Ϣ
		System.out.println(new String(rb.getString("username").getBytes("ISO-8859-1"),"UTF-8"));
		System.out.println(new String(rb.getString("password").getBytes("ISO-8859-1"),"UTF-8"));
		System.out.println(new String(rb.getString("login").getBytes("ISO-8859-1"),"UTF-8"));
	}
}
