package demo;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class Demo41_internationalization {
	/**
	 * 写两个配置文件(demo41Res_zh_CN.properties和demo41Res_en_US.properties)，一个文件中存放中文信息，一个文件存放英文信息
	 * 资源文件名称的格式：基本名称+Locale部分+.properties
	 * 基本名称：demo41Res(不包括下划线)
	 * Locale部分:zh_CN
	 * 条件：所有资源文件的名称的基本名称必须相同,不同之处就是locale部分
	 * 
	 * 使用ResourceBundle类，通过语言环境来识别加载哪个文件的信息。
	 * 语言环境：Locale类。
	 * zh_CN : 中文_中国
	 * en_US : 英文_美国
	 * new Locale("zh","CN");//创建语言环境对象，没有创建系统会获取Locale.getDefault();
	 * @throws UnsupportedEncodingException 
	 */
	
	@Test
	public void fun1() throws UnsupportedEncodingException{
		//1.得到locale
		Locale locale = Locale.CHINA  ;//Locale locale = request.getLocale();
		//中文出现乱码？要先转码
		//2.得到ResourceBundle。第一个参数是基本名称，第二个参数是local对象
		ResourceBundle rb = ResourceBundle.getBundle("demo41Res", locale);
//		-资源文件是UTF-8编码,rb获取后用ISO-8859-1解码，所以rb得到的值都需要ISO-8859-1编码在UTF-8解码 -
		//使用rb获取资源信息
		System.out.println(new String(rb.getString("username").getBytes("ISO-8859-1"),"UTF-8"));
		System.out.println(new String(rb.getString("password").getBytes("ISO-8859-1"),"UTF-8"));
		System.out.println(new String(rb.getString("login").getBytes("ISO-8859-1"),"UTF-8"));
	}
}
