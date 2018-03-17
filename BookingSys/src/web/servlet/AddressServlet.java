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
 * ��form.jsp��ʹ��ajax��ѡ��������Զ���ʾ����Ǽǵ�͵Ǽǵ��ַ��
 * ������(����)��������post����
 */
@WebServlet("/AddressServlet")
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void getDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.�����������,BaseServlet�����
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		//1.��ȡ����
		int did = Integer.valueOf(request.getParameter("did"));//��ȡ�����������֣�dname
		//2.����ҵ�������
		AddressService service = new AddressService();
		//3.����ҵ����󷽷����õ�����ֵ��
		List<Address> list = service.queryByDistrict(did);
		//4.�ѷ���ֵ���JSON��
		String jsonStr = JSONArray.fromObject(list).toString();
		//5.��Ӧ��ȥ
		response.getWriter().print(jsonStr);
	}
	
	public void getLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.post����BaseServlet����˱�������
		//1.��ȡ����
		int aid = Integer.valueOf(request.getParameter("aid"));
		//2.����ҵ�������
		AddressService service = new AddressService();
		//3.����ҵ����󷽷����õ�����ֵ,
		Address address = service.queryByAid(aid);
		//4.�ѷ���ֵ���JSON��
		String jsonStr = JSONObject.fromObject(address).toString();
		//5.��Ӧ��ȥ
		response.getWriter().print(jsonStr);
	}
}
