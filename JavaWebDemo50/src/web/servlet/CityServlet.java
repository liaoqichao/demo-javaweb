package web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ʡ��������ѡ����к���CityServlet��������
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * �в���(ʡ������)��������POST����ajax��û�в�����get�����в�����post����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * ��ȡʡ�����ƣ����ظ�ʡ��Ӧ��<province>Ԫ��
		 * ��Ԫ��ת�����ַ��������͸��ͻ���
		 */
		/*
		 * 1.��ȡʡ������
		 * 2.ʹ��ʡ�����Ʋ��Ҷ�Ӧ��provinceԪ��
		 * 3.��provinceԪ��ת�����ַ��������ͣ�
		 */
		/*
		 * dom4j
		 * 1.�õ�DocumentԪ��
		 * 	* ��������������
		 * 	* ��ȡһ����(china.xml)
		 *  * ����read����
		 */
		//0.�����������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=utf-8");//������Ӧ����MIME������xml����html
		//1.��ȡ������ʡ������
		String pname = request.getParameter("pname");
		try {
			//1.�õ�Document
			SAXReader reader = new SAXReader();
			InputStream is = this.getClass().getResourceAsStream("/china.xml");
			Document document = reader.read(is);
			
			//2.�õ�ProvinceԪ��,Ԫ���ǽڵ��������߽ӿ�
			Element proEle = (Element) document.selectSingleNode("//province[@name='"+pname+"']");//[]��ʾ����
			//3.��Ԫ��ת�����ַ���
			String eleStr = proEle.asXML();
			//4.����
			response.getWriter().println(eleStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
