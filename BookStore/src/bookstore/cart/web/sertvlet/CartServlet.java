package bookstore.cart.web.sertvlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.cart.domain.Cart;
import bookstore.cart.domain.CartItem;
import lqcUtils.servlet.BaseServlet;

/**
 * 购物车Servlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 添加购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.得到购物车
		 * 2.得到条目（得到图书和数量）
		 * 3.把条目添加到购物车中
		 */
		//1.
		Cart cart = (Cart)request.getSession().getAttribute("session_cart");
		/*
		 * 表单传递的只有bid和数量
		 * 2.得到条目
		 * 	> 得到图书（得到bid，通过bid查询数据库得到图书）
		 * 	> 数量：表单中提交
		 */
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		//3.把条目添加到车中
		cart.add(cartItem);
		return "f:jsps/cart/list.jsp";//这些操作完之后都是到这个页面
	}


	/**
	 * 清空购物条目
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.得到车
		 * 2.调用车的clear
		 */
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		cart.clear();
		return "f:jsps/cart/list.jsp";
	}
	
	/**
	 * 删除指定购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.得到车
		 * 2.得到要删除的书的bid
		 * 3.调用cart.delete(bid)
		 */
		Cart cart = (Cart)request.getSession().getAttribute("session_cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:jsps/cart/list.jsp";
	}

}
