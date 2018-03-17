package bookstore.book.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.category.domain.Category;
import bookstore.category.service.CategoryService;
import lqcUtils.CommonUtils;
import lqcUtils.servlet.BaseServlet;

/**
 * Servlet implementation class AdminBookServlet
 */
@WebServlet("/admin/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	/**
	 * 查询所有图书
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.调用service方法
		 * 2.保存List<Book>到request域
		 * 3.转发到/adminjsps/admin/book/list.jsp
		 */
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 查看图书详细(加载图书)
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取参数bid
		 * 2.调用service方法，返回book,保存到request域中
		 * 3.查询所有分类，保存到request域中(下拉菜单需要)
		 * 4.转发到/adminjsps/admin/book/desc.jsp
		 */
		//1.
		String bid = request.getParameter("bid");
		//2.
		request.setAttribute("book", bookService.load(bid));
		//3.
		request.setAttribute("categoryList", categoryService.findAll());
		//4.
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	
	/**
	 * 添加图书之在add.jsp加载下拉菜单选项
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.查询所有分类，保存到request，转发到add.jsp
		 * add.jsp中把所有分类用下拉列表电视
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}

	/**
	 * 删除图书。
	 * 所谓的删除图书不是在数据库删除记录，而是修改该记录，让它无法被查询。
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取bid
		 * 2.调用service#del(bid)
		 * 3.调用findAll
		 */
		String bid = request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request,response);
	}
	
	/**
	 * 修改图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.封装表单数据（必须让页面保证吧image传递过来，因为表单没有修改image，所以要添加隐藏字段image）
		 * 2.调用service方法
		 * 3.返回到列表页面(调用findAll)
		 */
		//1.
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		//2.
		bookService.modify(book);
		//3.
		return findAll(request,response);
	}
}
