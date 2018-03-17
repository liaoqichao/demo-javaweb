package bookstore.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import lqcUtils.servlet.BaseServlet;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	

	/**
	 * 查询所有图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Book> bookList = bookService.findAll();
		request.setAttribute("bookList", bookList);
		return "f:/jsps/book/list.jsp";
	}


	/**
	 * 按分类查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		List<Book> bookList = bookService.findByCategory(cid);
		request.setAttribute("bookList", bookList);
		return "f:/jsps/book/list.jsp";
	}
	

	/**
	 * 点击书的图片或文字，查看数的详细信息。
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.得到参数bid
		 * 2.调用service查询得到book
		 * 3.保存，转发到desc.jsp
		 */
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		return "f:/jsps/book/desc.jsp";
	}
}
