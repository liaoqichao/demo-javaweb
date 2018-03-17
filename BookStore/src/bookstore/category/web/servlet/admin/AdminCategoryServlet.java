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
 * 后台：管理图书分类的Servlet
 */
@WebServlet("/admin/AdminCategoryServlet")//有前缀/admin
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryService();

	/**
	 * 查询所有分类
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
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.封装表单数据
		 * 2.补全：cid
		 * 3.调用service方法完整添加工作
		 * 4.调用findAll(添加完后跳到查询所有分类页面)
		 */
		//1.
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		//2.
		category.setCid(CommonUtils.uuid());
		//3.
		try {
			categoryService.add(category);
		} catch (CategoryException e) {
			//保存异常信息到request域
			request.setAttribute("msg", e.getMessage());
			//转发到adminjsps/admin/msg.jsp
			return "f:/adminjsps/msg.jsp";
		}
		//4.
		return findAll(request,response);
	}

	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取cid参数
		 * 2.调用service方法，传递cid
		 * 	> 如果出现异常，保存异常信息，转发到msg.jsp
		 * 3.调用findAll()返回到查看所有分类页面
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
	 * 修改分类之加载分类。
	 * 修改都是先查后改！，这里就是通过cid查寻，返回category对象
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
	 * 修改分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取cid，和表单文本内容(cname),然后toBean(封装表单数据)
		 * 2.调用service方法，传递category
		 * 3.调用findAll方法
		 */
		//1.
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		//2.
		categoryService.modify(category);
		//3.
		return findAll(request,response);
	}
	
	
}
