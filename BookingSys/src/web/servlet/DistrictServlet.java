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
 * form.jsp的window.onload就请求这个Servlet，返回所有区的名字
 */
@WebServlet("/DistrictServlet")
public class DistrictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.解决编码问题
		response.setContentType("text/html;charset=utf-8");
		//1.得到服务对象
		DistrictService service = new DistrictService();
				
		//2.得到区列表
		List<District> district_list = service.queryAll(); 
		
		String jsonStr = JSONArray.fromObject(district_list).toString();
		//3.给form.jsp
		response.getWriter().print(jsonStr);
	}



}
