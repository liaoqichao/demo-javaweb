package struts2.demo8.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/**
 * 自定义类型转换器。
 * 类型转换器能实现双向转换：(String)20160321 --> (Date)Mon,Mar 21 2016 ...Date的输出格式
 * 					(Date)类型  --> 字符串类型20160321   
 * 定义万类型转换器后还需要注册。这是局部类型转换器按照局部类型转换器的格式注册
 * 要求:能接收字符串"20160321"，并变成Date类型，给Struts2接收
 */
public class DateTypeConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType) {
		/*
		 * context：和ognl表达式有关，以后说。
		 * value：接收到的值。这里是20160321
		 * toType：要转换成的类型
		 * 
		 * 这里要实现双向转换。
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");//请求参数的格式。
		try {
			if(toType == Date.class){//当字符串类型转换到Date类型时
				/*
				 * 为什么要把value转换成String数组呢？因为id=123这样是一个id对应一个值，但是有可能会是一个参数对应一组值，
				 * struts2为了考虑到这种情况，所以把参数的值用数组来存储。
				 * Struts调用的是request.getParameterValues(String arg0)的方式获取某个参数对应的一个或多个值。
				 */
				String[] params = (String[])value;	//把value转换成String数组
				return format.parse(params[0]);//取数组下标为0的元素，其实数组一共就1个元素。
			} else if(toType == String.class){//当Date类型转换到String类型时。使用struts2标签在页面回显参数的时候。
				Date date = (Date)value;
				return format.format(date);//Date变成yyyyMMdd格式字符串
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	
}
