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
 * 后台管理订单的Servlet
 */
@WebServlet("/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	/**
	 * 查询所有订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.调用service方法，返回List<Order>
		 * 2.把orderList封装到request域
		 * 3.转发到/adminjsps/admin/order/list.jsp
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
		 * 1.获取请求参数state
		 * 2.调用service#findByCategory
		 * 3.把orderList封装到request域
		 * 4.转发到/adminjsps/admin/order/list.jsp
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
	 * 后台之发货：订单状态从2变成3
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取oid
		 * 1.调用service#deliver(oid)方法
		 * 2.保存成功信息到request域，转发到/adminjsps/msg.jsp
		 */
		//1.
		String oid = request.getParameter("oid");
		//2.
		orderService.deliver(oid);
		//3.
		request.setAttribute("msg", "发货成功！");
		return "f:/adminjsps/msg.jsp";
	}

}
