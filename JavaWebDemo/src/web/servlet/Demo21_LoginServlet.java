package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 需求：
 * 1.从demo21_login.jsp中提交账号和密码到服务器。
 * 2.如果登录成功(账号不是itcast)则跳到demo21_succ1.jsp,session域保存账号和密码
 *   如果失败,则返回到demo21_login.jsp。并提示登录失败
 * 3.在demo21_succ1.jsp跳到demo21_succ2.jsp。如果session域中有账号和密码表示登录过,直接跳到demo21_succ2.jsp
 * 	 否则跳到demo21_login.jsp并提示没有登录。
 * 
 * Servlet implementation class Demo21_LoginServlet
 */
@WebServlet({ "/Demo21_LoginServlet", "/demo21" })
public class Demo21_LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo21_LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 0.获取session中的验证码,从表单获取用户填写的验证码。如果不相同发送错误信息到request域中,转发到login.jsp
		 * 1.处理中文问题后，获取表单数据
		 * 2.校验用户名和密码是否正确。
		 * 3.如果成功,保存用户信息(用户名)到Session中,然后重定向(不需要在同一请求)到succ1.jsp.
		 * 	 并且保存Cookie保存用户名信息,发送到浏览器。
		 * 4.如果失败,保存错误信息到request中,转发(同一个请求)到login.jsp
		 */
		
		//0.
		String sessionCode = (String)request.getSession().getAttribute("session_verifyCode");
		String userCode = (String)request.getParameter("verifyCode");//用户填写的验证码
		if(!sessionCode.equalsIgnoreCase(userCode)){
			request.setAttribute("msg", "验证码错误");
			//转发不需要项目名
			request.getRequestDispatcher("/jsps/demo21_login.jsp").forward(request, response);
			//不然还会向下执行。
			return;
		}
		//1.
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		@SuppressWarnings("unused")
		String psw = request.getParameter("psw");
		
		//用户名保存到Cookie中保存到浏览器,下一次login.jsp时,login会读取request中的cookie,把它显示到文本区域
		Cookie cookie = new Cookie("user",username);
		cookie.setMaxAge(60*60*24);//设置cookie命长1天
		response.addCookie(cookie);//保存cookie
		
		//2.
		if(!"itcast".equalsIgnoreCase(username)){//登录成功
			//3.
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			//重定向
			response.sendRedirect("/JavaWebDemo/jsps/demo21_succ1.jsp");
		} else{//登录失败
			
			//4.
			request.setAttribute("msg","用户名或密码错误!");
			RequestDispatcher rd = request.getRequestDispatcher("/jsps/demo21_login.jsp");//得到转发器
			rd.forward(request, response);
			
		}
	}

}
