package update.web.servlet;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import update.domain.UpdateFile;
import update.domain.VerUpdate;
import update.domain.Version;
import update.service.UpdateFileService;
import update.service.VerUpdateService;
import update.service.VersionService;

/**
 * Servlet implementation class UploadifyServlet
 */
@WebServlet("/UploadifyServlet")
@Transactional
public class UploadifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	private VerUpdateService verUpdateService = (VerUpdateService) ctx.getBean("verUpdateService"); 
	private VersionService versionService = (VersionService) ctx.getBean("versionService");
	private UpdateFileService updateFileService = (UpdateFileService) ctx.getBean("updateFileService");
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	
		try{
			// 0 .
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			// 设置缓存大小，单位为KB，超过则写到临时目录
			InputStream propIn = this.getClass().getClassLoader().getResourceAsStream("upload.properties");
			Properties uploadProp = new Properties();
			uploadProp.load(propIn);
			File cacheDir = new File(uploadProp.getProperty("cacheDir"));
			if(!cacheDir.exists()) cacheDir.mkdirs();
			// 设置文件存放目录
//			String savePath = "E:/Eclipse/fileTemp";
//			File fileDir = new File(savePath);
//			if(!fileDir.exists()) fileDir.mkdirs();
			
			// 1.创建DiskFileItemFactory
//			DiskFileItemFactory factory = new DiskFileItemFactory();
			DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,cacheDir);
			// 2.创建解析器
			ServletFileUpload sfu = new ServletFileUpload(factory);
			
			// 3. 设置上传限制
//			sfu.setFileSizeMax(1024 * 1024);//设置上传单个文件大小的限制为1M
//			sfu.setSizeMax(10 * 1024 *1024);//设置整个表单数据的大小为10M
			
			List<FileItem> fileItemList = null;
			Map<String,String> formDataMap = new HashMap<String,String>(); //存储普通表单项(除了path)
//			List<String> pathList = new ArrayList<String>(); // 存储name=path的普通表单项
//			List<byte[]> byteArrayList  = new ArrayList<byte[]>(); // 存储文件表单项
//			String path ; // 用于存储name=path的普通表单项
			byte[] file = null;
//			File file = null; // fileList 
			fileItemList = sfu.parseRequest(request);
			for (FileItem fileItem : fileItemList) { 
				if(!fileItem.isFormField()){ // 如果不是普通表单项（则是文件表单项？）
//					byteArrayList.add(fileItem.get()); // 
					file = fileItem.get();
				} else{ // 普通表单项
					/*
					 * ver
					 * description
					 * path
					 */
					formDataMap.put(fileItem.getFieldName(), new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8"));
//					if(fileItem.getFieldName().equals("path")){
//						pathList.add(new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8"));
//					} else{
//						formDataMap.put(fileItem.getFieldName(), new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8") );
//					}
				}
			}
			if(saveOrUpdate(formDataMap, file)){
				response.getWriter().print("发布成功");
			} else{
				response.getWriter().print("追加更新文件成功");
			}
//			if(saveOrUpdate(formDataMap,pathList, byteArrayList)){
//				response.getWriter().print("发布成功");
//			} else{
//				response.getWriter().print("追加更新文件成功");
//			}

		} catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 
	 * @param formDataMap
	 * @param file
	 * @return 返回true表示save，返回false表示update
	 */
	public boolean saveOrUpdate(Map<String,String> formDataMap,byte[] file){
		Version db_version = versionService.fingByVer(formDataMap.get("ver"));
//		Set<UpdateFile> add_updateFileSet = null;
		UpdateFile add_updateFile = null;
		if(db_version != null){
			// 获取持久化对象verUpdate?? 不是！ 为什么？ servlet没有被Spring托管？！
			VerUpdate verUpdate = verUpdateService.findByVersion(db_version.getVer());
			db_version.setTime(new Date());
			db_version.setDescription(
//					db_version.getDescription() 
//					+ System.getProperty("line.separator") 
//					+ 
					formDataMap.get("description"));
			versionService.update(db_version);
//			add_updateFileSet = toUpdateFileSet(verUpdate,formDataMap.get("path"),file);
			add_updateFile = toUpdateFile(verUpdate,formDataMap.get("path"),file);
//			saveUpdateFileSet(add_updateFileSet);
			saveUpdateFile(add_updateFile);
			return false;
		} else{
			// .封装version
			Version version = toVersion(formDataMap);
			
			// .封装verUpdate
			VerUpdate verUpdate = new VerUpdate();
			verUpdate.set_version(version);
//			verUpdate.getUpdateFileSet().add(updateFile);
			
			// . 封装updateFile
			add_updateFile = toUpdateFile(verUpdate,formDataMap.get("path"),file);
//			add_updateFileSet = toUpdateFileSet(verUpdate,pathList,byteList);
			
			// 临时状态 -> 持久化状态
			verUpdateService.save(verUpdate);
			saveUpdateFile(add_updateFile);
//			saveUpdateFileSet(add_updateFileSet);
			
			return true;
		}
	}
//	/**
//	 * 
//	 * @param formDataMap
//	 * @param file
//	 * @return 返回true表示save，返回false表示update
//	 */
//	public boolean saveOrUpdate(Map<String,String> formDataMap,List<String> pathList, List<byte[]> byteList){
//		Version db_version = versionService.fingByVer(formDataMap.get("ver"));
//		Set<UpdateFile> add_updateFileSet = null;
//		if(db_version != null){
//			// 获取持久化对象verUpdate?? 不是！ 为什么？ servlet没有被Spring托管？！
//			VerUpdate verUpdate = verUpdateService.findByVersion(db_version.getVer());
//			db_version.setTime(new Date());
//			db_version.setDescription(
//					db_version.getDescription() 
//					+ System.getProperty("line.separator") 
//					+ 
//					formDataMap.get("description"));
//			versionService.update(db_version);
//			add_updateFileSet = toUpdateFileSet(verUpdate,pathList,byteList);
//			saveUpdateFileSet(add_updateFileSet);
//			return false;
//		} else{
//			// .封装version
//			Version version = toVersion(formDataMap);
//			
//			// .封装verUpdate
//			VerUpdate verUpdate = new VerUpdate();
//			verUpdate.set_version(version);
////			verUpdate.getUpdateFileSet().add(updateFile);
//			
//			// . 封装updateFile
//			add_updateFileSet = toUpdateFileSet(verUpdate,pathList,byteList);
//			
//			// 临时状态 -> 持久化状态
//			verUpdateService.save(verUpdate);
//			saveUpdateFileSet(add_updateFileSet);
//			
//			return true;
//		}
//	}
//	public boolean saveOrUpdate(Map<String,String> formDataMap,List<String> pathList, List<byte[]> byteList){
//		Version db_version = versionService.fingByVer(formDataMap.get("ver"));
//		UpdateFile updateFile = null;
//		if(db_version != null){
//			// 获取持久化对象verUpdate?? 不是！ 为什么？ servlet没有被Spring托管？！
//			VerUpdate verUpdate = verUpdateService.findByVersion(db_version.getVer());
//			db_version.setTime(new Date());
//			db_version.setDescription(db_version.getDescription() 
//					+ System.getProperty("line.separator") 
//					+ formDataMap.get("description"));
//			versionService.update(db_version);
//			updateFile = new UpdateFile();
//			updateFile.setPath(formDataMap.get("path"));
//			updateFile.setFile(DSTransfer.fileToByteArray(file));
//			updateFile.setVerUpdate(verUpdate);
//			
//			updateFileService.save(updateFile);
//			verUpdate.getUpdateFileSet().add(updateFile);
//			
//			return false;
//		} else{
//			// .封装version
//			Version version = toVersion(formDataMap);
//			
//			// .封装verUpdate
//			VerUpdate verUpdate = new VerUpdate();
//			verUpdate.set_version(version);
//			verUpdate.getUpdateFileSet().add(updateFile);
//			
//			// . 封装updateFile
//			updateFile = new UpdateFile();
//			updateFile.setVerUpdate(verUpdate);
//			updateFile.setFile(DSTransfer.fileToByteArray(file));
//			updateFile.setPath(formDataMap.get("path"));
//			
//			// 临时状态 -> 持久化状态
//			verUpdateService.save(verUpdate);
//			updateFileService.save(updateFile);
//			
//			return true;
//		}
//	}
	
	private void saveUpdateFile(UpdateFile add_updateFile) {
		updateFileService.save(add_updateFile);
	}

	@SuppressWarnings("unused")
	private void saveUpdateFileSet(Set<UpdateFile> add_updateFileSet) {
		for (UpdateFile updateFile : add_updateFileSet) {
			updateFileService.save(updateFile);
		}
		
	}

	private UpdateFile toUpdateFile(VerUpdate verUpdate,String path,byte[] file) {
		UpdateFile updateFile = new UpdateFile();
		updateFile.setFile(file);
		updateFile.setPath(path);
		updateFile.setVerUpdate(verUpdate);
		return updateFile;
	}
//	private Set<UpdateFile> toUpdateFileSet(VerUpdate verUpdate,List<String> pathList,List<byte[]> byteList) {
//		Set<UpdateFile> updateFileSet = new HashSet<UpdateFile>();
//		for(int i=0 ; i<byteList.size() ; i++){ // 因为每次只提交一个文件
//			UpdateFile updateFile = new UpdateFile();
//			updateFile.setFile(byteList.get(i));
//			updateFile.setPath(pathList.get(i)); // 所以path始终是第一个
////			System.out.println("path"+i+":"+pathList.get(i));
//			updateFile.setVerUpdate(verUpdate);
////			System.out.println("updateFile"+i+":"+updateFile);
//			updateFileSet.add(updateFile);
//		}
//		return updateFileSet;
//	}

	private Version toVersion(Map<String,String> formDataMap){
		Version version = new Version();
		version.setVer(formDataMap.get("ver"));
		version.setDescription(formDataMap.get("description"));
		version.setTime(new Date());
		return version;
	}
}
