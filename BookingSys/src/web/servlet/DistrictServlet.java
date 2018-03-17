package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.District;
import net.sf.json.JSONArray;
import service.DistrictService;

/**
 * form.jsp��window.onload���������Servlet������������������
 */
@WebServlet("/DistrictServlet")
public class DistrictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.�����������
		response.setContentType("text/html;charset=utf-8");
		//1.�õ��������
		DistrictService service = new DistrictService();
				
		//2.�õ����б�
		List<District> district_list = service.queryAll(); 
		
		String jsonStr = JSONArray.fromObject(district_list).toString();
		//3.��form.jsp
		response.getWriter().print(jsonStr);
	}



}
