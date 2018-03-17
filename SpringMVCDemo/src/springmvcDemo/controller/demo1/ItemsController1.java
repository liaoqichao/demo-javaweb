package springmvcDemo.controller.demo1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import springmvcDemo.pojo.Items;

/**
 * 实现Controller接口的处理器
 * @author Liaoqichao
 * @date 2016年4月14日
 */
public class ItemsController1 implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// 调用service层方法，service层调用mapper查询数据库，查询商品列表
		// 这里使用静态数据模拟。
		
		// 1. 向list中填充静态数据
		List<Items> itemsList = new ArrayList<Items>();
		
		Items items_1 = new Items();
		items_1.setName("联想笔记本");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");
		
		Items items_2 = new Items();
		items_2.setName("苹果手机");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6苹果手机！");
		
		itemsList.add(items_1);
		itemsList.add(items_2);

		
		// 2. 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 这个方法相当于request.setAttribute方法。在jsp页面中通过itemsList取数据。
		modelAndView.addObject("itemsList", itemsList);
		
		
		// 3. 指定视图
		modelAndView.setViewName("/jsps/items/itemsList.jsp");
		
		// 4. 返回ModelAndView
		return modelAndView;
	}

}
