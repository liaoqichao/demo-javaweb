package web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * ʡ����������ҳ��������Servlet��������
 */
@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ��Ϊû�в�����������GET����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ��china.xml�л�ȡ����ʡ�����ơ�����xml��dom4j
		 * ��Ӧ����ʡ�����ƣ�ʹ�ö��Ÿ���
		 */
		/*
		 * dom4j�淨
		 * 1.�õ�Document����
		 * 	* ��������������
		 * 	* ���ý������Ķ�����������һ�������󣬵õ�Document
		 */
//		0.������룬������Ӧ����
		response.setContentType("text/html;charset=utf-8");
		try {
			SAXReader reader = new SAXReader();
			InputStream is = this.getClass().getResourceAsStream("/china.xml");
			Document document = reader.read(is);
			
			/*
			 * ʹ��XPath��ѯ����province��name���ԣ��õ�һ�����Զ���
			 * ѭ������������������ֵ���ӳ��ַ��������͸��ͻ���
			 */
			//Attribute ��dom4j�Ľӿ�
			@SuppressWarnings("unchecked")
			List<Attribute> arrList = document.selectNodes("//province/@name");//һֱ�����provinceԪ�ص�name����
			//ʹ��XPath��Ҫ����jaxen-1.1-beta-6.jar
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < arrList.size() ; i++){
				sb.append(arrList.get(i).getValue());
				if(i<arrList.size()-1){//���һ�����Ӷ���
					sb.append(",");
				}
			}
			response.getWriter().print(sb);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
