package web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.��ʾHttpServletResponse�ķ��ʹ���״̬��sendError(int sc,String msg)�����óɹ�״̬setStatus(int sc)
 * 2.��ʾHttpServletResponse������Ӧͷ
 * 3.��ʾHttpServletResponse������Ӧ��
 * 
 * ����������302�ض���,������Locationͷ:demo10_a,demo10_b;
 * Refreshͷ���������Ϊ��ʱ�ض���
 * 
 * Servlet implementation class Demo9_HttpServletResponse
 */
@WebServlet("/demo9")
public class Demo9_HttpServletResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo9_HttpServletResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * ����״̬��
		 */
//		response.sendError(404,"����ʵ���Դ����,�Ͳ����㿴��");//ֱ�ӻ�����ҳ��ʾ404
		
		/**
		 * ������Ӧͷ
		 */
		/*
		 * 	setHeader(String name,String value);	//��set���������ڵ�ֵ����Ӧͷ������������*****
		 *	addHeader(String name,String value);	//��add���������ڶ�ֵ����Ӧͷ
		 *	setIntHeader(String name,int value);	//int���͵���Ӧͷ��������Ӧ����,�����Ҫ֪���������������Ķ����ж��ٸ��ֽ�
		 *	addIntHeader(String name,int value);
		 *	setDateHeader(String name,long value);//�������͵���Ӧͷ
		 *	addDateHeader(String name,long value);
		 */
//		response.setIntHeader("Content-Length", 888);	//�����������Ӧ��ĳ�����888�ֽ�
//		response.setDateHeader("expires", 1000*60*60*24);	//ҳ�����ʱ��Ϊ24Сʱ�������׶���Ҫ������ҳ��,���Ժ�����Ҫ������������û���
		//ҳ�����ʱ�䣺�����ʱ��������ٴη��ʸ���ҳ,������ʷ�����,ֱ���ڻ����������ҳ�档���ʱ���ֻ�ܷ��ʷ�����Ҫҳ��
		
		/**
		 * ��ʾ��ʱˢ��
		 * 1.����Refreshͷ����ʾ��ʱˢ��
		 */
		/*
		 * ����������������Ӧ��
		 */
//		PrintWriter writer = response.getWriter();
//		writer.print("��ӭxxx��¼��3���Ӻ���Զ���ת����ҳ,�㿴����һ��������");
//		/*
//		 * ����Refresh����Ӧͷ
//		 */
//		response.setHeader("refresh", "3;URL=/JavaWebDemo/demo10_a");
		
		/**
		 * �������������
		 * 3����һ��
		 * ������jsp����http��<head></head>�����
		 * <meta http-equiv="pragma" context="no-cache"/>	<!--�����ִ�Сд-->
		 * <meta http-equiv="cache-ontrol" context="no-cache"/>
		 * <meta http-equiv="expires" context="-1"/>
		 * ������Ӧͷ
		 * <meta http-equiv="��Ӧͷ�ļ�" context="��Ӧͷ��ֵ"/>
		 */
//		response.setHeader("Cache-Control", "no-cache");
//		response.setHeader("pragma", "no-cache");
//		response.setDateHeader("expires", -1);
		
		/**
		 * ������Ӧ��
		 * response��2����
		 * PrintWriter out = response.getWriter();					//�ַ���
		 * ServletOutputStream out = response.getOutputStream();	//�ֽ���
		 * ��2����������ͬʱʹ��,��Ȼ���׳�IllegalStateException ���̳�RuntimeException��
		 * **�����ȵõ�һ����,Ȼ��ر�,�ٵ�����һ����
		 */
//		PrintWriter out = response.getWriter();
//		out.write("Hello world! demo9");
//		out.close(); ���ù�,response������
		ServletOutputStream out = response.getOutputStream();
		File file = new File("E:\\Eclipse\\IO\\ByteStream\\demo2.jpg");
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = fis.read(buf)) != -1){
			out.write(buf, 0, len);
		}
		fis.close();
	}

}
