package update.service;

import java.io.File;
import java.util.Properties;

import update.domain.UpdateFile;

public interface UpdateFileService {
	
	/**
	 * 保存上传的UpdateFile对象
	 */
	public void save(UpdateFile updateFile);
	/**
	 * 读取上传过来的配置文件
	 * @param file
	 * @return 配置文件的Properties对象
	 */
	public Properties toProperties(File file);

	/**
	 * 创建文件夹存放分割文件
	 * @param propFile
	 * @return 返回创建目录的对象
	 */
	public File createDir(Properties fileProp);
}