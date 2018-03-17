package bookstore.category.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.category.domain.Category;
import bookstore.category.service.CategoryException;
import bookstore.category.service.CategoryService;
import lqcUtils.CommonUtils;
import lqcUtils.servlet.BaseServlet;

/**
 * ��̨������ͼ������Servlet
 */
@WebServlet("/admin/AdminCategoryServlet")//��ǰ׺/admin
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryService();

	/**
	 * ��ѯ���з���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categoryList = categoryService.findAll();
		request.setAttribute("categoryList", categoryList);
		return "f:/adminjsps/admin/category/list.jsp";
	}

	/**
	 * ��ӷ���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��װ������
		 * 2.��ȫ��cid
		 * 3.����service����������ӹ���
		 * 4.����findAll(������������ѯ���з���ҳ��)
		 */
		//1.
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		//2.
		category.setCid(CommonUtils.uuid());
		//3.
		try {
			categoryService.add(category);
		} catch (CategoryException e) {
			//�����쳣��Ϣ��request��
			request.setAttribute("msg", e.getMessage());
			//ת����adminjsps/admin/msg.jsp
			return "f:/adminjsps/msg.jsp";
		}
		//4.
		return findAll(request,response);
	}

	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡcid����
		 * 2.����service����������cid
		 * 	> ��������쳣�������쳣��Ϣ��ת����msg.jsp
		 * 3.����findAll()���ص��鿴���з���ҳ��
		 */
		//1.
		String cid = request.getParameter("cid");
		//2.
		try {
			categoryService.delete(cid);
		} catch (CategoryException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
		return findAll(request,response);
	}
	
	/**
	 * �޸ķ���֮���ط��ࡣ
	 * �޸Ķ����Ȳ��ģ����������ͨ��cid��Ѱ������category����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("category", categoryService.load(cid));
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * �޸ķ���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡcid���ͱ��ı�����(cname),Ȼ��toBean(��װ������)
		 * 2.����service����������category
		 * 3.����findAll����
		 */
		//1.
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		//2.
		categoryService.modify(category);
		//3.
		return findAll(request,response);
	}
	
	
}
