package springmvcDemo.controller.demo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springmvcDemo.pojo.Items;

/**
 * 使用注解方式开发Handler
 * springmvc.xml配置了<mvc:annotation-driven/>
 * @author Liaoqichao
 * @date 2016年4月15日
 */
//使用Controller注解标记为控制器
@Controller
public class ItemsController {

	
	// 定义启发方法...,商品添加,商品修改
	
	// 商品查询列表
	@RequestMapping("/queryItems") // 一般建议和方法名一样，url后面可以加.action也可以不加
	public ModelAndView queryItems() throws Exception{
		
		System.out.println("demo3...");
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
		
		//2. 创建ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//3. 向request域添加属性
		modelAndView.addObject("itemsList",itemsList);
		//4. 指定返回视图
		/*
		 * 	前缀：/jsps/
		 *  后缀：.jsp
		 *  下面路径如果在视图解析器中配置了jsp前缀和后缀，修改为items/itemsList
		 */
		modelAndView.setViewName("items/itemsList");
//		modelAndView.setViewName("/jsps/items/itemsList.jsp");
		
		//5. 返回ModelAndView
		return modelAndView;
	}
}
