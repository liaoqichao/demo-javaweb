package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.po.ItemsCustom;

/**
 * json交互的测试
 * @author Liaoqichao
 * @date 2016年5月9日
 */
@Controller
public class JsonTestController {
	
	/**
	 * 1.使用@RequestBody接收json串参数并转化为java对象报415错误：
	 * 2.使用@ResponseBody响应json格式出现：无法转换为ItemsCustom的错误
	 * 
	 * 解决办法：不要使用jackson-mapper-asl-1.9.11.jar和jackson-core-asl-1.9.11.jar，
	 * 使用jackson-annotations-2.2.3.jar、jackson-core-2.2.3.jar、jackson-databind-2.2.3.jar
	 * 
	 * 可能的错误原因：需要jackson-databind.jar
	 */
	
	
	// 请求json(商品信息)、输出json(商品信息)
	@RequestMapping(value="/requestJSON", method={RequestMethod.POST})
	@ResponseBody 
	public ItemsCustom requestJSON(@RequestBody ItemsCustom itemsCustom) throws Exception{
		/*
		 * @RequestBody注解将页面传输过来的json串解析成java对象
		 * @ResponseBody将java对象转成JSON输出。
		 */
		System.out.println(itemsCustom.getName());
		System.out.println(itemsCustom.getPrice());
		return itemsCustom;
	}
	
	// 请求key/value、输出json
	@RequestMapping(value="/responseJSON")
	@ResponseBody 
	public ItemsCustom responseJSON(ItemsCustom itemsCustom) throws Exception{
		System.out.println(itemsCustom.getName());
		System.out.println(itemsCustom.getPrice());
		return itemsCustom;
	}
	
}
