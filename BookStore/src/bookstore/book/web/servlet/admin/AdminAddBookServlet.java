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
 * 添加图书的servlet
 * 为什么要这个Servlet，因为这里需要用到上传，上传不能使用BaseServlet
 */
@WebServlet("/admin/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();

	/**
	 * 上传，enctype=multipart/form-data 所以不能使用BaseServlet
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 上传文件问题：
		 * 上传的文件最好不要保存到项目的目录下，不然重启服务器上传的文件就会丢失。因为文件是上传到服务器，保存在本地硬盘，而不是上传到本地硬盘。
		 * 所以重启服务器的时候，就会把项目里面的文件还原。但是为什么又可以从数据库中获取文件(数据库存储的是文件路径,通过路径找到文件).
		 * 所以使用eclipse的workspace作为目录重启之后不会删除。
		 * E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\webapps\BookStore\book_img
		 */
		try {
			/*
			 * 0.解决编码问题
			 */
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			/*
			 * 1.把表单数据封装到book对象中
			 * 	> 上传三步
			 */
			//创建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory(30*1024,new File("E:/Eclipse/temp"));//第一步
			//第一个参数，缓存大小，第二个参数临时目录(超过缓存到就保存到临时目录)
			//得到解析器
			ServletFileUpload sfu = new ServletFileUpload(factory);//第二步
			sfu.setFileSizeMax(30*1024);//设置单个文件大小限制为15KB
			//使用解析器，解析request对象，得到List<FileItem>,每个FileItem对应一个表单字段
			@SuppressWarnings("unchecked")
			List<FileItem> fileItemList = sfu.parseRequest(request);//第三步
			Map<String,String> bookMap = new HashMap<String,String>();
			for (FileItem fileItem : fileItemList) {
				if(fileItem.isFormField()){	//如果这个表单字段是普通表单项，而不是文件表单项
					//把普通表单项放入map中，键为普通表单项的name，值为普通表单项的value
					bookMap.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}

			//现在就差book.image。book.image是路径，暂时还没产生
			/*
			 * 2.把fileItem中的数据封装到Book对象中
			 * 	> 把所有普通表单字段，先封装到Map中
			 * 	> 再把Map中的数据封装到Book对象中
			 *  补全第一个：补主键
			 * 	补全第二个：给book的image赋值
			 * 		* 保存上传的文件(图书图片)
			 * 			> 保存的路径
			 * 			> 保存的文件名称(要加UUID前缀)
			 * 		* 获取保存文件的路径
			 * 		* 赋值给book.image(开头不要斜杠)
			 * 	补全第三个：根据cid查找到category，赋值给book
			 */
			Book book = CommonUtils.toBean(bookMap, Book.class);//这样就把表单中除了图片都封装到book中
			//补全第一个：
			book.setBid(CommonUtils.uuid());
			//补全第二个：
			//得到根目录
			String savePath = this.getServletContext().getRealPath("/book_img");
			//得到目的文件
			String filename = CommonUtils.uuid()+"_"+fileItemList.get(1).getName();//添加UUID
			//校验文件的扩展名,需要在service.add方法之前进行校验。
			if(!filename.toLowerCase().endsWith("jpg")){
				request.setAttribute("msg", "您上传的图片不是jpg扩展名");
				request.setAttribute("categoryList", categoryService.findAll());//不然转发的页面下拉菜单没有分类
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			File destFile = new File(savePath, filename);
			//保存上传文件到目标文件
			fileItemList.get(1).write(destFile);
			//图片尺寸的校验，需要在文件创建后校验。Image是抽象类，new ImageIcon(文件路径)有getImage方法得到image。
//			得到image后就可以得到图片的宽和高
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getHeight(null) > 200 || image.getHeight(null) > 200){
				destFile.delete();//删除已经创建的文件(但尺寸不符合)
				request.setAttribute("msg", "您上传的图片的尺寸超出200*200");
				request.setAttribute("categoryList", categoryService.findAll());//不然转发的页面下拉菜单没有分类
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			//设置Book对象的image，即把图片的路径设置给Book的image
			book.setImage("book_img/" + filename);//只能手动设置。savaPath是带盘符的
			//补全第三个： 需要把map中的cid封装到category对象中，再把category赋值给book
			Category category = CommonUtils.toBean(bookMap, Category.class);
			book.setCategory(category);
			/*
			 * 3..使用BookService完成保存
			 */
			bookService.add(book);
			/*
			 * 4.返回到图书列表.没有BaseServlet，只能用原始方法转发
			 */
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);;
		} catch (Exception e) {
			/*
			 * 如果图片超出大小，需要转换.
			 * 需要判断异常类型，如果是FileUploadBase.FileSizeLimitExceededException说明是文件大小超出。这个异常查找
			 * 文档可以知道，文档在common-fileupload.jar包一起下载的文件夹里面有。
			 */
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				request.setAttribute("msg", "您上传的文件超出了30KB");
				request.setAttribute("categoryList", categoryService.findAll());//不然转发的页面下拉菜单没有分类
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
			throw new RuntimeException(e);
		}
	}
		
}
