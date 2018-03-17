package company.crm.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.crm.domain.Customer;
import company.crm.domain.PageBean;
import company.crm.service.CustomerService;
import lqcUtils.CommonUtils;
import lqcUtils.servlet.BaseServlet;

/**
 * web层
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//依赖CustomerService
	private CustomerService customerService = new CustomerService();
	
	/**
	 * 添加客户
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//1.封装表单数据
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		//2.补全信息:cid，使用UUID
		c.setCid(CommonUtils.uuid());
		//3.使用service完成添加工作
		customerService.add(c);
		//4.向request域中保存成功信息
		request.setAttribute("msg", "恭喜,添加客户成功！");
		//5.转发到msg.jsp(通过BaseServlet)
		return "f:/msg.jsp";
	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		没有用分页
//		//1.调用service方法
//		List<Customer> list = customerService.findAll();
//		//2.把结果保存到request域中
//		request.setAttribute("customers", list);
//		//3.转发到list.jsp
//		return "f:/list.jsp";
		
//		使用分页
		//1.获取页面传递的pageCode,pc。如果pc不存在，pc=1，如果pc存在转换为int类型即可
		int pageCode = getPageCode(request);
		//2.给定pageSize,ps。每页10行记录
		int pageSize = 10;
		//3.使用pc和ps使用service，得到pageBean
		PageBean<Customer> pb = customerService.findAll(pageCode,pageSize);//传递pc，ps给service，得到pageBean
		//4.设置URL
		pb.setUrl(getUrl(request));
		//5.保存到request域
		request.setAttribute("pb", pb);
		//6.转发到list.jsp
		return "f:/list.jsp";
	}
	
	/**
	 * 获取pageCode,pc
	 */
	private int getPageCode(HttpServletRequest request){
		
		String  value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty()){
			return 1;
		} else{
			return Integer.parseInt(value);
		}
	}
	
	/**
	 * 加载：按主键查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String beforeEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.获取参数cid
		String cid = request.getParameter("cid");
		//2.调用service查询方法，通过cid得到Customer对象
		Customer c = customerService.load(cid);
		//3.保存到request域
		request.setAttribute("customer", c);
		//4.转发到edit.jsp
		return "f:/edit.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.封装表单数据到Customer对象中。request域中已经有cid，所以可以直接封装
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		//2.调用service完成编辑
		customerService.edit(c);
		//3.保存成功信息到request域
		request.setAttribute("msg", "恭喜,修改成功");
		//4.转发到msg.jsp显示成功
		return "f:/msg.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取request域中的cid
		String cid = request.getParameter("cid");
		//2.调用service完成删除
		customerService.delete(cid);
		//3.保存成功信息到request域
		request.setAttribute("msg", "恭喜,删除成功");
		//4.转发到msg.jsp显示成功
		return "f:/msg.jsp";
	}
	
	/**
	 * 多条件组合查询
	 */
	public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		没使用分页
//		//1.封装表单数据到Customer,只有4个属性，cname,gender,cellphone,email
//		//以后hibernate有Criteria接口。QBC query by criteria 基于条件查询
//		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		//2.调用service，把条件传递，返回list
//		List<Customer> list = customerService.query(criteria);
//		//3.把list保存到request域中
//		request.setAttribute("customers", list);
//		//4.转发到list.jsp
//		return "f:/list.jsp";
		
		/**
		 * 多条件查询如何处理分页？
		 * 1.同一请求。把query.list的表单改为GET请求，好在URL中显示搜索条件。表单的中文会自动进行URL编码
		 * 2.把URL的搜索条件保存到pageBean的URL中，其中pc不要，pc由list.jsp自己完成
		 * 3.分页的超链接都${pb.url}&pc=${i}获取超链接路径，这里的url保存了第一次提交表单的搜索条件
		 */
//		使用分页
//		System.out.println(getUrl(request));
		//0.把条件封装到Customer对象中
		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		/*
		 * 处理编码问题。GET请求只能手动处理。为什么不用处理？处理了反而错了BaseServlet没有处理
		 */
//		criteria = encoding(criteria);
		
		//1.得到pc
		int pc = getPageCode(request);
		
		//2.给定ps
		int ps = 10;
		
		//3.使用pc，ps和条件对象criteria调用service方法得到pageBean
		PageBean<Customer> pb = customerService.query(pc,ps,criteria);
		
		//4.得到URL保存到pb中
		pb.setUrl(getUrl(request));
		
		//5.把pageBean保存到request域中
		request.setAttribute("pb", pb);
		
		//6.转发到list.jsp
		return "f:/list.jsp";
	}
	
	/**
	 * GET请求为什么不用处理编码问题？处理了反而错了。一开始就是UTF-8
	 * 处理高级搜索功能的4样数据
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unused")
	private Customer encoding(Customer criteria) {
		
		try{
			String cname = criteria.getCname();
			String gender = criteria.getGender();
			String cellphone = criteria.getCellphone();
			String email = criteria.getEmail();
			
			if(cname != null && !cname.trim().isEmpty()){
				cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
				criteria.setCname(cname);
			}
			if(gender != null && !gender.trim().isEmpty()){
				System.out.println(gender);
//				gender = new String(gender.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println(gender);
				criteria.setGender(gender);
			}
			if(cellphone != null && !cellphone.trim().isEmpty()){
				cellphone = new String(cellphone.getBytes("ISO-8859-1"),"UTF-8");
				criteria.setCellphone(cellphone);
			}
			if(email != null && !email.trim().isEmpty()){
				email = new String(email.getBytes("ISO-8859-1"),"UTF-8");
				criteria.setEmail(email);
			}
			
			return criteria;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 截取url
	 * 	/项目名/Servlet路径?参数字符串
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request){
		String contextPath = request.getContextPath();//获取项目名
		String servletPath = request.getServletPath();//获取Servlet路径
		String queryString = request.getQueryString();//获取问号后的参数
		
		//queryString中可能存在pc，但不需要pc。由页面list.jsp控制就好。
		if(queryString.contains("&pc=")){
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		return contextPath + servletPath + "?" + queryString;
	}
}
