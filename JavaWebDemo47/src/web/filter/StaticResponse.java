package web.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * װ�����ģʽ������getWriter��������ԭ������Ϣ�����show.jsp��������html��
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
	 * @param path ָ��html�ļ���·��
	 */
	public StaticResponse(HttpServletResponse response ,String path) {
		super(response);
		this.response = response;
		try {
			//������html����һ���������
			pw = new PrintWriter(path,"UTF-8");//������·�������롣������췽������Ӳ�����������ļ���
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��Ҫ��ǿ�Ĳ���
	 */
	@Override
	public PrintWriter getWriter(){
		//����һ����html�󶨵�printWriter
		return pw;
	}
}
