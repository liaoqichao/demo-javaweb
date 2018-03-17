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
 * ����������������������
 * �������������������⣬����serviceһ��������doGet,doPost��service�Ѳ���(request��response)��doGet,
 * ����doGet������
 * Servlet implementation class AServlet
 */
@WebServlet("/AServlet")
public class AServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ��Щ��������serviceһ�����ͺ�������ͬ���������ͣ������������쳣����ͬ��doGet��doPostҲ������
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
