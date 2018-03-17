package springmvcDemo.controller.demo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springmvcDemo.pojo.Items;

/**
 * ʹ��ע�ⷽʽ����Handler
 * springmvc.xml������<mvc:annotation-driven/>
 * @author Liaoqichao
 * @date 2016��4��15��
 */
//ʹ��Controllerע����Ϊ������
@Controller
public class ItemsController {

	
	// ������������...,��Ʒ���,��Ʒ�޸�
	
	// ��Ʒ��ѯ�б�
	@RequestMapping("/queryItems") // һ�㽨��ͷ�����һ����url������Լ�.actionҲ���Բ���
	public ModelAndView queryItems() throws Exception{
		
		System.out.println("demo3...");
		// 1. ��list����侲̬����
		List<Items> itemsList = new ArrayList<Items>();
		
		Items items_1 = new Items();
		items_1.setName("����ʼǱ�");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 ����ʼǱ����ԣ�");
		
		Items items_2 = new Items();
		items_2.setName("ƻ���ֻ�");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6ƻ���ֻ���");
		
		itemsList.add(items_1);
		itemsList.add(items_2);
		
		//2. ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//3. ��request���������
		modelAndView.addObject("itemsList",itemsList);
		//4. ָ��������ͼ
		/*
		 * 	ǰ׺��/jsps/
		 *  ��׺��.jsp
		 *  ����·���������ͼ��������������jspǰ׺�ͺ�׺���޸�Ϊitems/itemsList
		 */
		modelAndView.setViewName("items/itemsList");
//		modelAndView.setViewName("/jsps/items/itemsList.jsp");
		
		//5. ����ModelAndView
		return modelAndView;
	}
}
