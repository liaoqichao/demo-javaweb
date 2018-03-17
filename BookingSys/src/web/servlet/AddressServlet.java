package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Address;
import lqcUtils.servlet.BaseServlet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AddressService;

/**
 * 在form.jsp中使用ajax，选择地区就自动显示办理登记点和登记点地址。
 * 带参数(地区)，所以用post请求
 */
@WebServlet("/AddressServlet")
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void getDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.解决编码问题,BaseServlet解决了
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		//1.获取参数
		int did = Integer.valueOf(request.getParameter("did"));//获取参数区的名字：dname
		//2.创建业务类对象
		AddressService service = new AddressService();
		//3.调用业务对象方法，得到返回值。
		List<Address> list = service.queryByDistrict(did);
		//4.把返回值变成JSON串
		String jsonStr = JSONArray.fromObject(list).toString();
		//5.响应回去
		response.getWriter().print(jsonStr);
	}
	
	public void getLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.post请求，BaseServlet解决了编码问题
		//1.获取参数
		int aid = Integer.valueOf(request.getParameter("aid"));
		//2.创建业务类对象
		AddressService service = new AddressService();
		//3.调用业务对象方法，得到返回值,
		Address address = service.queryByAid(aid);
		//4.把返回值变成JSON串
		String jsonStr = JSONObject.fromObject(address).toString();
		//5.响应回去
		response.getWriter().print(jsonStr);
	}
}
