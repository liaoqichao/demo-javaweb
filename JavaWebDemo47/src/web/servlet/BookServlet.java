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
	 * 查找所有书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.直接调用service方法,得到List<Book>
		List<Book> list = bookService.findAll();
		//2.把列表保存到request域
		request.setAttribute("list", list);
		//3.转发到show.jsp
		return "f:/show.jsp";
	}

	/**
	 * 查找某个种类的书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到request域中的category
		int category = Integer.parseInt(request.getParameter("category"));
		//2.调用service方法，得到List<Book>
		List<Book> list = bookService.findByCategory(category);
		//3.把列表保存到reques域
		request.setAttribute("list", list);
		//4.
		return "f:/show.jsp";
	}

}
