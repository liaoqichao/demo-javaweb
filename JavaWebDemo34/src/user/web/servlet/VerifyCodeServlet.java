package user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myUtils.VerifyCode;

/**
 * url mapping VerifyCodeServlet
 */
@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.创建验证码类
		 * 2.得到验证码图片
		 * 3.把图片上的文本保存到session中
		 * 4.把图片响应给客户端
		 */
		//1.
		VerifyCode vc = new VerifyCode();
		//2.
		BufferedImage bi = vc.getImage();
		//3.
		request.getSession().setAttribute("session_code", vc.getText());
		//4.
		vc.output(bi, response.getOutputStream());
	}

}
