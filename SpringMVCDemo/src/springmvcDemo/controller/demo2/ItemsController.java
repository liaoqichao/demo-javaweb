package springmvcDemo.controller.demo2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import springmvcDemo.pojo.Items;

/**
 * 使用HttpRequestHandlerAdapter适配器
 * @author Liaoqichao
 * @date 2016年4月14日
 */
public class ItemsController implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

		
		// 2.设置模型数据。把数据保存到reques域
		request.setAttribute("itemsList", itemsList);
		
		// 3.设置转发的视图
		// 导入jsonObject包，返回JSON格式
//		response.getWriter.out(JSONObject.fromBean(itemsList).toString());
		request.getRequestDispatcher("/jsps/items/itemsList.jsp").forward(request, response);
	}

}
