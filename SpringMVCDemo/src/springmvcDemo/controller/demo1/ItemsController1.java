package springmvcDemo.controller.demo1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import springmvcDemo.pojo.Items;

/**
 * ʵ��Controller�ӿڵĴ�����
 * @author Liaoqichao
 * @date 2016��4��14��
 */
public class ItemsController1 implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// ����service�㷽����service�����mapper��ѯ���ݿ⣬��ѯ��Ʒ�б�
		// ����ʹ�þ�̬����ģ�⡣
		
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

		
		// 2. ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// ��������൱��request.setAttribute��������jspҳ����ͨ��itemsListȡ���ݡ�
		modelAndView.addObject("itemsList", itemsList);
		
		
		// 3. ָ����ͼ
		modelAndView.setViewName("/jsps/items/itemsList.jsp");
		
		// 4. ����ModelAndView
		return modelAndView;
	}

}
