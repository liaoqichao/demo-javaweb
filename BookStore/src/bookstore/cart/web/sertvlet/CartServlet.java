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
 * ���ﳵServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * ��ӹ�����Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.�õ����ﳵ
		 * 2.�õ���Ŀ���õ�ͼ���������
		 * 3.����Ŀ��ӵ����ﳵ��
		 */
		//1.
		Cart cart = (Cart)request.getSession().getAttribute("session_cart");
		/*
		 * �����ݵ�ֻ��bid������
		 * 2.�õ���Ŀ
		 * 	> �õ�ͼ�飨�õ�bid��ͨ��bid��ѯ���ݿ�õ�ͼ�飩
		 * 	> �����������ύ
		 */
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		//3.����Ŀ��ӵ�����
		cart.add(cartItem);
		return "f:jsps/cart/list.jsp";//��Щ������֮���ǵ����ҳ��
	}


	/**
	 * ��չ�����Ŀ
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.�õ���
		 * 2.���ó���clear
		 */
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		cart.clear();
		return "f:jsps/cart/list.jsp";
	}
	
	/**
	 * ɾ��ָ��������Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.�õ���
		 * 2.�õ�Ҫɾ�������bid
		 * 3.����cart.delete(bid)
		 */
		Cart cart = (Cart)request.getSession().getAttribute("session_cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:jsps/cart/list.jsp";
	}

}
