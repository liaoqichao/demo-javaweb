package user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myUtils.CommonUtils;
import user.domain.User;
import user.service.UserException;
import user.service.UserService;

/**
 * URL Mapping /RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//处理POST请求的编码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//依赖UserService
		UserService userService = new UserService();
		
		/*
		 * 0.使用JavaScript进行表单校验(客户端)
		 * 1.封装表单数据到user中
		 * 2.表单校验。在获取表单数据之后，验证码校验之前。然后校验验证码，必须在它们之后。
		 * 3.调用userService#regist(form)
		 * 4.得到异常,获取异常信息,封装到request域，转发到regist域中
		 * 5.没有异常，输出注册成功。
		 */
		//1.
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		//2.服务器端的表单校验
			/*
			 * a.创建一个Map装所有的错误信息。
			 * b.在校验过程中,如果失败，向map添加错误信息，key为表单字段。
			 * c.如果map长度大于0，说明有错。保存map到request中，保存form到request中。转发到regist.jsp
			 * d.如果map长度为0，没错。向下执行。
			 */
		//2.a
		Map<String,String> errors = new HashMap<String,String>();//装错误信息
		//2.b.对用户名进行校验
		String username = form.getUsername();//获取表单的username
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}
		if(username.length() < 3 || username.length()>15){
			errors.put("username", "用户名长度必须在3~15之间");
		}
		//对密码进行校验
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}
		if(password.length() < 3 || password.length()>15){
			errors.put("password", "密码长度必须在3~15之间");
		}
		//对status进行校验
		String status = form.getStatus();
		if(status.contains("abc"))
			errors.put("status", "状态不能包含字符串abc");
		//对验证码校验
		String vCode1 = (String)request.getSession().getAttribute("session_code");//正确的验证码
		String vCode2 = form.getVerifyCode();//用户输入的验证码
		if(vCode2 ==null || vCode2.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空");
		}
		else if(vCode2.length() != 4){
			errors.put("verifyCode", "验证码长度必须为4");
		}else if(!vCode1.equalsIgnoreCase(vCode2)){
			errors.put("verifyCode", "验证码错误");
		}
		//2.c
		if(errors != null && errors.size()!=0){
			/*
			 * 1.保存errors到request域
			 * 2.保存form到request域
			 * 3.转发到regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		try {
			//3.
			userService.regist(form);
			//5.
			response.getWriter().print("<h1>注册成功！</h1><br/>"
					+ "<a href='"+request.getContextPath()+"/user/login.jsp'>点击这里去登录</a>");
		} catch (UserException e) {
			//把form的数据保存到request域中,在<input input=text value="${requestScope.user.username}"/>
			//回显数据。
			request.setAttribute("user", form);
			//4.
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}

}
