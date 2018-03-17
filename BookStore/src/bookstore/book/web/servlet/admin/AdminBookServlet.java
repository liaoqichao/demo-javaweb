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
	 * ��ѯ����ͼ��
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.����service����
		 * 2.����List<Book>��request��
		 * 3.ת����/adminjsps/admin/book/list.jsp
		 */
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * �鿴ͼ����ϸ(����ͼ��)
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡ����bid
		 * 2.����service����������book,���浽request����
		 * 3.��ѯ���з��࣬���浽request����(�����˵���Ҫ)
		 * 4.ת����/adminjsps/admin/book/desc.jsp
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
	 * ���ͼ��֮��add.jsp���������˵�ѡ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ѯ���з��࣬���浽request��ת����add.jsp
		 * add.jsp�а����з����������б����
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}

	/**
	 * ɾ��ͼ�顣
	 * ��ν��ɾ��ͼ�鲻�������ݿ�ɾ����¼�������޸ĸü�¼�������޷�����ѯ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡbid
		 * 2.����service#del(bid)
		 * 3.����findAll
		 */
		String bid = request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request,response);
	}
	
	/**
	 * �޸�ͼ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��װ�����ݣ�������ҳ�汣֤��image���ݹ�������Ϊ��û���޸�image������Ҫ��������ֶ�image��
		 * 2.����service����
		 * 3.���ص��б�ҳ��(����findAll)
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
