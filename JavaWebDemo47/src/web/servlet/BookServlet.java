package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import lqcUtils.servlet.BaseServlet;
import service.BookService;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();


	/**
	 * ����������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.ֱ�ӵ���service����,�õ�List<Book>
		List<Book> list = bookService.findAll();
		//2.���б��浽request��
		request.setAttribute("list", list);
		//3.ת����show.jsp
		return "f:/show.jsp";
	}

	/**
	 * ����ĳ���������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.�õ�request���е�category
		int category = Integer.parseInt(request.getParameter("category"));
		//2.����service�������õ�List<Book>
		List<Book> list = bookService.findByCategory(category);
		//3.���б��浽reques��
		request.setAttribute("list", list);
		//4.
		return "f:/show.jsp";
	}

}
