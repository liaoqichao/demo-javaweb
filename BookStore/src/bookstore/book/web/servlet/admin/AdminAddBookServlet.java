package bookstore.book.web.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.category.domain.Category;
import bookstore.category.service.CategoryService;
import lqcUtils.CommonUtils;

/**
 * ���ͼ���servlet
 * ΪʲôҪ���Servlet����Ϊ������Ҫ�õ��ϴ����ϴ�����ʹ��BaseServlet
 */
@WebServlet("/admin/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();

	/**
	 * �ϴ���enctype=multipart/form-data ���Բ���ʹ��BaseServlet
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * �ϴ��ļ����⣺
		 * �ϴ����ļ���ò�Ҫ���浽��Ŀ��Ŀ¼�£���Ȼ�����������ϴ����ļ��ͻᶪʧ����Ϊ�ļ����ϴ����������������ڱ���Ӳ�̣��������ϴ�������Ӳ�̡�
		 * ����������������ʱ�򣬾ͻ����Ŀ������ļ���ԭ������Ϊʲô�ֿ��Դ����ݿ��л�ȡ�ļ�(���ݿ�洢�����ļ�·��,ͨ��·���ҵ��ļ�).
		 * ����ʹ��eclipse��workspace��ΪĿ¼����֮�󲻻�ɾ����
		 * E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\webapps\BookStore\book_img
		 */
		try {
			/*
			 * 0.�����������
			 */
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			/*
			 * 1.�ѱ����ݷ�װ��book������
			 * 	> �ϴ�����
			 */
			//��������
			DiskFileItemFactory factory = new DiskFileItemFactory(30*1024,new File("E:/Eclipse/temp"));//��һ��
			//��һ�������������С���ڶ���������ʱĿ¼(�������浽�ͱ��浽��ʱĿ¼)
			//�õ�������
			ServletFileUpload sfu = new ServletFileUpload(factory);//�ڶ���
			sfu.setFileSizeMax(30*1024);//���õ����ļ���С����Ϊ15KB
			//ʹ�ý�����������request���󣬵õ�List<FileItem>,ÿ��FileItem��Ӧһ�����ֶ�
			@SuppressWarnings("unchecked")
			List<FileItem> fileItemList = sfu.parseRequest(request);//������
			Map<String,String> bookMap = new HashMap<String,String>();
			for (FileItem fileItem : fileItemList) {
				if(fileItem.isFormField()){	//���������ֶ�����ͨ����������ļ�����
					//����ͨ�������map�У���Ϊ��ͨ�����name��ֵΪ��ͨ�����value
					bookMap.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}

			//���ھͲ�book.image��book.image��·������ʱ��û����
			/*
			 * 2.��fileItem�е����ݷ�װ��Book������
			 * 	> ��������ͨ���ֶΣ��ȷ�װ��Map��
			 * 	> �ٰ�Map�е����ݷ�װ��Book������
			 *  ��ȫ��һ����������
			 * 	��ȫ�ڶ�������book��image��ֵ
			 * 		* �����ϴ����ļ�(ͼ��ͼƬ)
			 * 			> �����·��
			 * 			> ������ļ�����(Ҫ��UUIDǰ׺)
			 * 		* ��ȡ�����ļ���·��
			 * 		* ��ֵ��book.image(��ͷ��Ҫб��)
			 * 	��ȫ������������cid���ҵ�category����ֵ��book
			 */
			Book book = CommonUtils.toBean(bookMap, Book.class);//�����Ͱѱ��г���ͼƬ����װ��book��
			//��ȫ��һ����
			book.setBid(CommonUtils.uuid());
			//��ȫ�ڶ�����
			//�õ���Ŀ¼
			String savePath = this.getServletContext().getRealPath("/book_img");
			//�õ�Ŀ���ļ�
			String filename = CommonUtils.uuid()+"_"+fileItemList.get(1).getName();//���UUID
			//У���ļ�����չ��,��Ҫ��service.add����֮ǰ����У�顣
			if(!filename.toLowerCase().endsWith("jpg")){
				request.setAttribute("msg", "���ϴ���ͼƬ����jpg��չ��");
				request.setAttribute("categoryList", categoryService.findAll());//��Ȼת����ҳ�������˵�û�з���
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			File destFile = new File(savePath, filename);
			//�����ϴ��ļ���Ŀ���ļ�
			fileItemList.get(1).write(destFile);
			//ͼƬ�ߴ��У�飬��Ҫ���ļ�������У�顣Image�ǳ����࣬new ImageIcon(�ļ�·��)��getImage�����õ�image��
//			�õ�image��Ϳ��Եõ�ͼƬ�Ŀ�͸�
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getHeight(null) > 200 || image.getHeight(null) > 200){
				destFile.delete();//ɾ���Ѿ��������ļ�(���ߴ粻����)
				request.setAttribute("msg", "���ϴ���ͼƬ�ĳߴ糬��200*200");
				request.setAttribute("categoryList", categoryService.findAll());//��Ȼת����ҳ�������˵�û�з���
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			//����Book�����image������ͼƬ��·�����ø�Book��image
			book.setImage("book_img/" + filename);//ֻ���ֶ����á�savaPath�Ǵ��̷���
			//��ȫ�������� ��Ҫ��map�е�cid��װ��category�����У��ٰ�category��ֵ��book
			Category category = CommonUtils.toBean(bookMap, Category.class);
			book.setCategory(category);
			/*
			 * 3..ʹ��BookService��ɱ���
			 */
			bookService.add(book);
			/*
			 * 4.���ص�ͼ���б�.û��BaseServlet��ֻ����ԭʼ����ת��
			 */
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);;
		} catch (Exception e) {
			/*
			 * ���ͼƬ������С����Ҫת��.
			 * ��Ҫ�ж��쳣���ͣ������FileUploadBase.FileSizeLimitExceededException˵�����ļ���С����������쳣����
			 * �ĵ�����֪�����ĵ���common-fileupload.jar��һ�����ص��ļ��������С�
			 */
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				request.setAttribute("msg", "���ϴ����ļ�������30KB");
				request.setAttribute("categoryList", categoryService.findAll());//��Ȼת����ҳ�������˵�û�з���
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
			throw new RuntimeException(e);
		}
	}
		
}
