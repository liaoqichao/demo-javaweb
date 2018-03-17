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
 * 省市联动。选择城市后向CityServlet发送请求
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 有参数(省的名称)，所以用POST请求。ajax中没有参数用get请求，有参数用post请求。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * 获取省份名称，加载该省对应的<province>元素
		 * 把元素转换成字符串，发送给客户端
		 */
		/*
		 * 1.获取省份名称
		 * 2.使用省份名称查找对应的province元素
		 * 3.把province元素转换成字符串，发送！
		 */
		/*
		 * dom4j
		 * 1.得到Document元素
		 * 	* 创建解析器对象
		 * 	* 获取一个流(china.xml)
		 *  * 调用read方法
		 */
		//0.解决编码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=utf-8");//这里响应的是MIME类型是xml不是html
		//1.获取参数：省份名称
		String pname = request.getParameter("pname");
		try {
			//1.得到Document
			SAXReader reader = new SAXReader();
			InputStream is = this.getClass().getResourceAsStream("/china.xml");
			Document document = reader.read(is);
			
			//2.得到Province元素,元素是节点的子类或者接口
			Element proEle = (Element) document.selectSingleNode("//province[@name='"+pname+"']");//[]表示条件
			//3.把元素转换成字符串
			String eleStr = proEle.asXML();
			//4.发送
			response.getWriter().println(eleStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
