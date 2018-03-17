package bookstore.order.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.order.domain.Order;
import bookstore.order.service.OrderService;
import lqcUtils.servlet.BaseServlet;

/**
 * ��̨��������Servlet
 */
@WebServlet("/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	/**
	 * ��ѯ���ж���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.����service����������List<Order>
		 * 2.��orderList��װ��request��
		 * 3.ת����/adminjsps/admin/order/list.jsp
		 */
		//1.
		List<Order> orderList = orderService.findAll();
		//2.
		request.setAttribute("orderList", orderList);
		//3.
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	public String findByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡ�������state
		 * 2.����service#findByCategory
		 * 3.��orderList��װ��request��
		 * 4.ת����/adminjsps/admin/order/list.jsp
		 */
		//1.
		int state = Integer.parseInt(request.getParameter("state"));
		//2.
		List<Order> orderList = orderService.findByState(state);
		//3.
		request.setAttribute("orderList", orderList);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	/**
	 * ��̨֮����������״̬��2���3
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡoid
		 * 1.����service#deliver(oid)����
		 * 2.����ɹ���Ϣ��request��ת����/adminjsps/msg.jsp
		 */
		//1.
		String oid = request.getParameter("oid");
		//2.
		orderService.deliver(oid);
		//3.
		request.setAttribute("msg", "�����ɹ���");
		return "f:/adminjsps/msg.jsp";
	}

}
