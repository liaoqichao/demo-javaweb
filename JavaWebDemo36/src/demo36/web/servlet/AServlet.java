package demo36.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在这里给出多个请求处理方法。
 * 请求处理方法：除了名称外，都与service一样。例如doGet,doPost。service把参数(request，response)给doGet,
 * 调用doGet方法。
 * Servlet implementation class AServlet
 */
@WebServlet("/AServlet")
public class AServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 这些方法都和service一样，就函数名不同，返回类型，参数，声明异常都相同。doGet、doPost也是这样
	 */
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addUser");
	}
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("editUser");
		
	}
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("deleteUser");
	}
	public void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("findAllUser");
	}


}
