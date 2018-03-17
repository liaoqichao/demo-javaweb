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
 * ʹ��HttpRequestHandlerAdapter������
 * @author Liaoqichao
 * @date 2016��4��14��
 */
public class ItemsController implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

		
		// 2.����ģ�����ݡ������ݱ��浽reques��
		request.setAttribute("itemsList", itemsList);
		
		// 3.����ת������ͼ
		// ����jsonObject��������JSON��ʽ
//		response.getWriter.out(JSONObject.fromBean(itemsList).toString());
		request.getRequestDispatcher("/jsps/items/itemsList.jsp").forward(request, response);
	}

}
