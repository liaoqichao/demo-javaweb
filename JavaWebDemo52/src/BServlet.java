

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * servlet3.0������֮�ϴ���
 * ֮ǰ���ϴ�request.getParameter(..);����ʹ�ã����ڿ�����
 */
@WebServlet(urlPatterns = "/BServlet")
@MultipartConfig//�����ע���֧���ϴ����
public class BServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.getParameter����ʹ����
		 */
		String username = request.getParameter("username");	//����ʹ���ˣ�
		/*
		 * 2.��ȡ�ļ����ֶζ�Ӧ��part����
		 */
		Part part = request.getPart("resume");//resume���ļ����ֶε�name�����Ǻ�getParameterһ��
		/*
		 * 3.��part�л�ȡ��Ҫ������
		 */
		String mime = part.getContentType();//��ȡMIME����
		long size = part.getSize();//��ȡ�ϴ��ļ��Ĵ�С
		String name = part.getName();//��ȡ�ļ��ֶε����ƣ�����resume
		//��ȡ���ͷ��ֵ�����ͷ�������ϴ��ļ�������
		String contentDispositionHeader = part.getHeader("Content-Disposition");
		System.out.println(mime);//image/jpeg
//		System.out.println(size);/29414
		System.out.println(name);//resume
		System.out.println(contentDispositionHeader);//form-data; name="resume"; filename="demo2.jpg"
		System.out.println(getFilename(part));
		//�����ļ�
		part.write("E:/Eclipse/IO/JavaWebDemo52-servlet3.0-upload/JavaWebDemo52-2.jpg");
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @param Part �ļ����ֶ�
	 * @return Content-Disposition���е�filename��Ӧ��ֵ�����ļ�����
	 */
	private String getFilename(Part part){
		String contentDispositionHeader = part.getHeader("Content-Disposition");
		int start = contentDispositionHeader.lastIndexOf("filename=\"")+10;//lastIndexOf���ص���f��λ��
		int end = contentDispositionHeader.length()-1;
		String filename = contentDispositionHeader.substring(start, end);
		return filename;
	}

}
