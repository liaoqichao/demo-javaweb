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
 * UserService
 * URL Mapping : /LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//下载模板(插件?)，可以自动删掉多余注释和自动有对应GET/POST的修改编码,而且新建JSP会自动导入JSTL的core库
		//Eclipse怎么导入模板？MyEclipse方法不行
		request.setCharacterEncoding("utf-8");//请求编码POST
		response.setContentType("test/html;charset=utf-8");//响应编码
		
		//依赖UserService
		UserService userService = new UserService();
		
		/*
		 * 1.封装表单数据到User form中
		 * 	--->插入
		 * 	> 表单校验
		 * 		a.创建Map装错误信息
		 * 		b.判断用户名是否为空或符合规则(3~15)
		 * 		c.判断密码是否为空或符合规则(3~15)
		 * 		d.判断验证码是否为空或符合规则
		 * 		e.如果map长度不为0,把map保存到request域中，把form保存到request域中。并转发到login.jsp
		 * 2.调用service的login方法。
		 * 	> 如果抛出异常：获取异常信息，保存到request域中，保存form到request域中，转发到login.jsp
		 *  > 如果没有异常：保存返回值到session中,重定向到welcome.jsp
		 */
		
		//1.
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		//表单校验
		//a.
		Map<String,String> errors = new HashMap<String,String>();
		if(form.getUsername()==null || form.getUsername().trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}
		if(form.getUsername().length()<3 || form.getUsername().length()>15){
			errors.put("username", "用户名长度为3~15");
		}
		//b.
		if(form.getPassword()==null || form.getPassword().trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}
		if(form.getPassword().length()<3 || form.getPassword().length()>15){
			errors.put("password", "密码长度为3~15");
		}
		//c.
		String vCode1 = (String)request.getSession().getAttribute("session_code");
		String vCode2 = form.getVerifyCode();
		if(vCode2==null || form.getVerifyCode().trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空");
		}
		else if(vCode2.length()!=4){
			errors.put("verifyCode", "验证码长度为4");
		}else if(!vCode2.equalsIgnoreCase(vCode1)){
			errors.put("verifyCode", "验证码错误");
		}
		if(errors != null && errors.size()!=0){
			request.setAttribute("user", form);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			return;
		}
		
		//2.
		try{
			User user = userService.login(form);
			request.getSession().setAttribute("sessionUser", user);//在JSP中用来校验是否登陆过
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
	}

}
