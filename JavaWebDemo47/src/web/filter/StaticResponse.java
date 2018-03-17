package web.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 装饰设计模式，掉包getWriter方法。让原来的信息输出到show.jsp变成输出到html中
 * @author Administrator
 *
 */
public class StaticResponse extends HttpServletResponseWrapper {

	@SuppressWarnings("unused")
	private HttpServletResponse response;
	private PrintWriter pw ;
	/**
	 * 
	 * @param response
	 * @param path 指向html文件的路径
	 */
	public StaticResponse(HttpServletResponse response ,String path) {
		super(response);
		this.response = response;
		try {
			//创建与html绑定在一起的流对象
			pw = new PrintWriter(path,"UTF-8");//参数：路径，编码。这个构造方法会在硬盘里面生成文件。
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 需要增强的部分
	 */
	@Override
	public PrintWriter getWriter(){
		//返回一个与html绑定的printWriter
		return pw;
	}
}
