package update.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import lqcUtils.DSTransfer;
import lqcUtils.file.SuffixFilter;
import update.domain.UpdateFile;
import update.domain.VerUpdate;
import update.domain.Version;
import update.service.UpdateFileService;
import update.service.VerUpdateService;
import update.service.VersionService;

@Transactional
public class VerUpdateAction {

	@Resource
	private VerUpdateService verUpdateService;
	@Resource
	private VersionService versionService ;
	@Resource
	private UpdateFileService updateFileService ;
	
	private String message;
	private Version version;
	private File[] file;
	private String[] fileFileName;
	private String[] fileContentType;
	private String[] path;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Version getVersion() {
		return version;
	}
	public void setVersion(Version version) {
		this.version = version;
	}
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String[] getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String[] getPath() {
		return path;
	}
	public void setPath(String[] path) {
		this.path = path;
	}
	public String release(){
		Version db_version = versionService.fingByVer(version.getVer());
//		if(versionService.isExist(version.getVer())){
		if(db_version != null){
			
			//  --- 如果存在则追加 ---
			/*
			 *  1. 根据version获得持久化对象 verUpdate
			 *  2. 调用toUpdateFileSet(verUpdate);
			 *  3. 设置信息
			 *  4. 返回视图
			 */
			// 1.
			VerUpdate verUpdate =  verUpdateService.findByVersion(db_version.getVer());
			// 2.
			verUpdate.get_version().setTime(new Date());
			verUpdate.get_version().setDescription(verUpdate.get_version().getDescription()
					+ System.getProperty("line.separator")
					+ this.version.getDescription());
			Set<UpdateFile> added_updateFileSet = toUpdateFileSet(verUpdate);
//			Set<UpdateFile> old_updateFileSet = verUpdate.getUpdateFileSet();
//			old_updateFileSet.addAll(added_updateFileSet);
			verUpdate.getUpdateFileSet().addAll(added_updateFileSet);
			// 3.
			this.message = "该版本号已经存在,追加新更新文件成功!";
			// 4.
			return "release";
		} else{
			VerUpdate verUpdate = new VerUpdate();
			version.setTime(new Date());
			verUpdate.set_version(version);
			verUpdate.setUpdateFileSet(toUpdateFileSet(verUpdate));
			verUpdateService.save(verUpdate);
			//  -- 到此已经正确的向verupdate,_version,updatefile表插入了记录 --
			//  -- 下面是调用向更新系统服务器发送信息，更改更新系统服务器的配置文件信息 --
			//  -- 只是放到数据库，单独弄个按钮来选择版本
			this.message = "发布成功";
			return "release";
		}
		
	}
	
//	public String testRelease(){
//		System.out.println("a");
//		System.out.println(file.length);
//		return "release";
//	}
	

	/**
	 *  封装成updateFile，并且与verUpdate产生关联关系
	 */
	private Set<UpdateFile> toUpdateFileSet(VerUpdate verUpdate){
		Set<UpdateFile> updateFileSet = new HashSet<UpdateFile>();
		for(int i = 0 ; i< this.file.length ; i++){
			UpdateFile updateFile = new UpdateFile();
			updateFile.setPath(this.path[i]);
			//
			updateFile.setFile(DSTransfer.fileToByteArray(this.file[i]));
//			updateFile.setFile(IOUtils.toByteArray(this.file[i]));
			updateFile.setVerUpdate(verUpdate);
			updateFileSet.add(updateFile);
		}
		return updateFileSet;
	}
	
	/**
	 * 暂时没用
	 * @return
	 * @throws Exception
	 */
	public String addLargeFile() throws Exception{
		/*
		 * 1.按版本号查询版本，返回true进行下一步，否则发送message，版本不存在
		 * 2.接收第一个File对象（properties文件）
		 * 	> 文件夹名称为分割前文件的名称。即prop.getProperty(filename);
		 *  > 把第一个File对象保存到本地（指定文件夹）//改成最后一个File对象
		 * 3.获取本地的分割文件的properties文件（就是上面保存的）。
		 * 4.如果配置信息的partcount等于后面的file对象的个数加上指定文件夹后缀为.part的个数（即一次上传全部分割文件）
		 *  > 获取path（终端更新的路径）
		 * 	> 进行合并成一个File对象，然后删除本地文件
		 *  > 封装path和File成一个UpdateFile
		 *  > 根据版本号获取VerUpdate对象，
		 *  > UpdateFile绑定VerUpdate对象
		 *  > save(UpdateFile)
		 *  > message="上传成功"
		 * 5.如果配置信息的partcount大于后面file对象的个数加上指定文件夹后缀为.part的个数（分割文件不全）
		 * 	> 保存分割文件到本地。
		 *  > message = "分割文件个数小于配置文件中的partcount，分割文件保存，请继续完成上传"
		 * 6.如果配置信息的partcount小于后面的file对象的个数加上指定文件夹后缀为.part的个数。
		 * 	> message="分割文件大于配置信息的分割文件个数"
		 */
		//1.判断版本是否存在，存在进入下一步
		Version version = versionService.fingByVer(this.version.getVer());
		if(version == null){
			this.message = "该版本不存在";
			return "addLargeFile";
		}
		//2.创建文件夹存放分割文件
//		File propFile = this.file[this.file.length-1];
		File propFile = this.file[0];
//		if(!propFile.getName().endsWith("properties")){
//			this.message = "最后一个文件要为properties文件";
//			System.out.println(propFile.getName());//upload_b71fee4f_6735_4c05_9abb_2e90243ca5e6_00000002.tmp
//			return "addLargeFile";
//		}
		// ---
//		FileInputStream in = new FileInputStream(propFile);
//		InputStreamReader reader = new InputStreamReader(in,"ISO-5589-1");
		// ---
		Properties fileProp = updateFileService.toProperties(propFile);	//分割文件的配置信息
		File dir = updateFileService.createDir(fileProp);
		File[] diskSplitFile = dir.listFiles(new SuffixFilter("part")); //获取该目录下.part后缀的文件
		
		//3. 如果上传的文件数-1等于partcount+文件夹里面后缀为.part的文件个数
		int partcount = Integer.parseInt(fileProp.getProperty("partcount"));
		if(diskSplitFile.length+this.file.length-1 == partcount){
			// ...
			System.out.println("3");
		} else if(diskSplitFile.length+this.file.length-1 < partcount){
			//4. 如果上传文件数大于partcount+文件夹里面后缀为.part的文件个数
			// ...
			System.out.println("4");
		} else{
			//5. 如果上传文件数小于partcount+文件夹里面后缀为.part的文件个数
			// ...
			System.out.println(5);
		}
		
		return "addLargeFile";
	}
	
}
