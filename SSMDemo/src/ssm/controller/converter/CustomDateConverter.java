package ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义类型转换器，需要实现Converter接口
 * Converter接口的两个泛型，第一个表示原始类型，第二个是被转换成的类型
 * @author Liaoqichao
 * @date 2016年5月2日
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		/*
		 * 实现日期字符串(yyyy-MM-dd HH:mm:ss)转换成日期类型
		 * 但是从数据库中获取空的timestamp会报错，解决办法在jdbc连接url中添加参数zeroDateTimeBehavior=convertToNull
		 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 转换成功直接返回
//			if(source.trim().equals(""))
//				return null;
			return formatter.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			if(!source.trim().equals("")){
				e.printStackTrace();
				System.out.println("日期格式错误");
			} else{
				System.out.println("没有输入日期");
			}
		}
		// 参数绑定失败返回null
		return null;
	}

}
