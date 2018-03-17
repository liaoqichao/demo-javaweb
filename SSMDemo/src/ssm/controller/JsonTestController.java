package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.po.ItemsCustom;

/**
 * json�����Ĳ���
 * @author Liaoqichao
 * @date 2016��5��9��
 */
@Controller
public class JsonTestController {
	
	/**
	 * 1.ʹ��@RequestBody����json��������ת��Ϊjava����415����
	 * 2.ʹ��@ResponseBody��Ӧjson��ʽ���֣��޷�ת��ΪItemsCustom�Ĵ���
	 * 
	 * ����취����Ҫʹ��jackson-mapper-asl-1.9.11.jar��jackson-core-asl-1.9.11.jar��
	 * ʹ��jackson-annotations-2.2.3.jar��jackson-core-2.2.3.jar��jackson-databind-2.2.3.jar
	 * 
	 * ���ܵĴ���ԭ����Ҫjackson-databind.jar
	 */
	
	
	// ����json(��Ʒ��Ϣ)�����json(��Ʒ��Ϣ)
	@RequestMapping(value="/requestJSON", method={RequestMethod.POST})
	@ResponseBody 
	public ItemsCustom requestJSON(@RequestBody ItemsCustom itemsCustom) throws Exception{
		/*
		 * @RequestBodyע�⽫ҳ�洫�������json��������java����
		 * @ResponseBody��java����ת��JSON�����
		 */
		System.out.println(itemsCustom.getName());
		System.out.println(itemsCustom.getPrice());
		return itemsCustom;
	}
	
	// ����key/value�����json
	@RequestMapping(value="/responseJSON")
	@ResponseBody 
	public ItemsCustom responseJSON(ItemsCustom itemsCustom) throws Exception{
		System.out.println(itemsCustom.getName());
		System.out.println(itemsCustom.getPrice());
		return itemsCustom;
	}
	
}
