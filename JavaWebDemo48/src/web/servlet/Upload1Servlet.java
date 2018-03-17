package web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import lqcUtils.CommonUtils;

/**
 * --�ϴ�����ʹ��baseServlet����ΪencType="multipart/form-data"
 * �ϴ���Servlet,
 * �����ϴ��ļ���С���ƣ������ϴ��������Ĵ�С���ơ�
 * ���û����С����ʱĿ¼��
 * �����ϴ����⣺
 * 1.Ŀ¼��ɢ
 * 2.�ļ����ľ���·��
 * 3.�ļ�ͬ��
 */
@WebServlet("/Upload1Servlet")
public class Upload1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.�����������,���û���á���Ϊ��enctype="multipary/form-data"
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		//1.����DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//���ι���������һ������Ϊ�����С(��λ�ֽ�,Ĭ��10K)���ڶ�������Ϊ��ʱĿ¼�ļ�(��������Ͱ��ļ�д����ʱĿ¼)
//		DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,new File("E:/Eclipse/temp"));
		//2.����������
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		/*
		 * ��2�仰������sfu.parseRequest(request);֮ǰ���á�����ϴ����ļ��������ƣ���parseRequestִ��ʱ���׳��쳣��
		 */
		sfu.setFileSizeMax(1024 * 1024);//�����ϴ������ļ���С������Ϊ1M
		sfu.setSizeMax(10 * 1024 *1024);//�������������ݵĴ�СΪ10M
		//3.ͨ���������õ�List<FileItem>
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
			FileItem fi1 = fileItemList.get(0);//��ͨ����
			System.out.println(fi1.getFieldName()+" : "+fi1.getString("UTF-8"));//��ͨ�������ƣ�ֵ
			
			FileItem fi2 = fileItemList.get(1);//�ļ�����
			System.out.println("ContentType: " + fi2.getContentType()
			+ ", size: "+fi2.getSize()+" ,filename: "+fi2.getName());
			//4.�����ļ�
//			File file = new File("E:/Eclipse/IO/JavaWebDemo48-upload/1.jpg");
//			fi2.write(file);
			
			//Ŀ¼��ɢ��
			//1.�õ���·��/WEB-INF/files
			String root = this.getServletContext().getRealPath("/WEB-INF/files/");

			/*
			 * 2.��������Ŀ¼��
			 * a.�õ��ļ�����
			 * b.�õ���ϣֵ
			 * c.ת����16����
			 * d.��ȡǰ2����ĸ��������Ŀ¼
			 */
			//a.
			String filename = fi2.getName();
			//�����ļ����ľ���·�����⡣
			int index = filename.lastIndexOf("\\");
			if(index != -1){
				filename  = filename.substring(index+1);
			}
			//�����ļ�ͬ������
			String savename = CommonUtils.uuid() + "_" + filename; 
			//b.
			int hashCode = savename.hashCode();
			//c.
			String hex = Integer.toHexString(hashCode);
			//d.
			File dir = new File(root,hex.charAt(0)+"/"+hex.charAt(1));
			dir.mkdirs();//���򲻴�����û���򴴽���/A/B ���û��/AҲ�ᴴ��/A/B�� mkdir�Ļ����û��/A�Ͳ��ᴴ��/A/B
			
			//3.����Ŀ���ļ�
			File destFile = new File(dir,savename);
			//4.д�������ļ�
			fi2.write(destFile);
			//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo48\WEB-INF\files
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				request.setAttribute("msg", "���ϴ����ļ�����1M");
				request.getRequestDispatcher("/form1.jsp").forward(request, response);
			} else if( e instanceof FileUploadBase.SizeLimitExceededException){
				request.setAttribute("msg", "���ϴ��ı�����10M");
				request.getRequestDispatcher("/form1,jsp").forward(request, response);
			} else{
				throw new RuntimeException(e);
			}
		}
	}

}
