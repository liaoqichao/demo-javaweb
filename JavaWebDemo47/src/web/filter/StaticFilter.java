package web.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ҳ�澲̬��������BookServlet
 */
//@WebFilter(servletNames = {"BookServlet"})//û�й��ˣ�����֪������Ҫȫ�޶���
@WebFilter(urlPatterns="/BookServlet")
public class StaticFilter implements Filter {

	private ServletContext application ;//��Filter��Ҫ��request�õ�application,��init�Ĳ����õ�application
	public void destroy() {}

	public void init(FilterConfig fConfig) throws ServletException {
		
		this.application = fConfig.getServletContext();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*
		 * 1.��һ�η���ʱ�����Ҷ�Ӧ��html�Ƿ���ڣ���������ض���html
		 * 2.��������ڣ�����html�ļ�����Ҫװ��HttpServletResponse���޸�getWriter��
		 * 		pw = new PrintWriter(path,"UTF-8");pathΪhtml�ļ�·��
		 * 3.���У�response�ͻ᲻�ϵ�out.write("HTML����")�����ǲ��������show.jsp�����������HTML�ļ�(path)��
		 * 4.�ض���html��
		 */
		/*
		 * һ����ȡcategory����
		 * 	category�����ֿ��ܣ�
		 * 		null --> null.html
		 * 		1 --> 1.html
		 * 		2 --> 2.html
		 * 		3 --> 3.html
		 * html�ı���·����htmlsĿ¼��
		 */
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//null + ".html"  == "null.html" �õ���Ӧ���ļ�����
		String htmlPath = application.getRealPath("/htmls");//�õ��ļ��Ĵ��·�������̷�,���������ض���
		File destFile = new File(htmlPath,htmlPage);
//		System.out.println(destFile.getAbsolutePath());
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo47\htmls\1.html
		//1.
		if(destFile.exists()){//����ļ�����
//			�ض�������ļ�
			res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);//�� /htmls/
			return;
		} 
		/*
		 * 2.���html�����ڣ�����html
		 * 	> ���У����̻ᵽshow.jsp��������������ǲ�Ҫ�����show.jsp���������htmlҳ�档
		 * 		ֻ��Ҫװ��response��response.getWriter()�������˾Ϳ���
		 */
		//����HTML��ŵ�����·����pw = new PrintWriter(path,"UTF-8");�Ѿ���Ӳ���������ļ���
		StaticResponse sr = new StaticResponse(res, destFile.getAbsolutePath());//����Ҫres����Ϊsr�Ǽ̳�HttpServletResponse
		//3.���У���������Ӧ���󡣷��к�PrintWriter����html�з���out.write("html����");ҳ�����ݾ��Ѿ���������
		chain.doFilter(req, sr);
		//4.�ض���html��
		res.sendRedirect(req.getContextPath()+ "/htmls/" + htmlPage);
		//Ϊʲô��res������response����sr��������res��responseһ����res��srһ�������Զ�һ����
		
	}

}
