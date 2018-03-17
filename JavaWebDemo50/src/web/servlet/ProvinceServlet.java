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
 * 省市联动，打开页面就想这个Servlet发送请求。
 */
@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 因为没有参数，所以用GET请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 从china.xml中获取所有省份名称。解析xml用dom4j
		 * 响应所有省份名称，使用逗号隔开
		 */
		/*
		 * dom4j玩法
		 * 1.得到Document对象
		 * 	* 创建解析器对象
		 * 	* 调用解析器的读方法，传递一个流对象，得到Document
		 */
//		0.解决编码，设置响应编码
		response.setContentType("text/html;charset=utf-8");
		try {
			SAXReader reader = new SAXReader();
			InputStream is = this.getClass().getResourceAsStream("/china.xml");
			Document document = reader.read(is);
			
			/*
			 * 使用XPath查询所有province的name属性，得到一堆属性对象。
			 * 循环遍历，把所有属性值连接成字符串，发送给客户端
			 */
			//Attribute 是dom4j的接口
			@SuppressWarnings("unchecked")
			List<Attribute> arrList = document.selectNodes("//province/@name");//一直往深处找province元素的name属性
			//使用XPath需要导入jaxen-1.1-beta-6.jar
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < arrList.size() ; i++){
				sb.append(arrList.get(i).getValue());
				if(i<arrList.size()-1){//最后一个不加逗号
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
