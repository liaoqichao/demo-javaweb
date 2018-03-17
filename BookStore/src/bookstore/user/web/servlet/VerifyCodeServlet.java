package bookstore.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lqcUtils.VerifyCode;

/**
 * 验证码的Servlet
 */
@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VerifyCode vcode = new VerifyCode();
		vcode.output(vcode.getImage(), response.getOutputStream());//把图片响应到请求页面。
		request.getSession().setAttribute("session_vcode", vcode.getText());//保存文本
		/*
		 * 如果把图片响应到页面请求和保存文本的顺序颠倒，会导致session保存的文本为为下一次正确的验证码文本。
		 * 没有调用vcode.getImage()方法，没有创建新图片，没有新的文字。
		 */
	}

}
