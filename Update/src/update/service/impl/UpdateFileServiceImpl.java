package update.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;

import lqcUtils.LoadProperties;
import update.dao.UpdateFileDao;
import update.domain.UpdateFile;
import update.service.UpdateFileService;

/**
 * 暂时没用
 */
//@Transactional
public class UpdateFileServiceImpl implements UpdateFileService {

	@Resource
	private UpdateFileDao updateFileDao;
	
	
	/**
	 * 保存updateFile
	 * @param updateFile
	 */
	public void save(UpdateFile updateFile){
		updateFileDao.save(updateFile);
	}
	/**
	 * 
	 * @param file
	 * @return
	 */
	public Properties toProperties(File file){
		Properties prop = null;
		try{
			prop = new Properties();
			InputStream in = new FileInputStream(file);
//			Reader reader = new FileReader(file);
			prop.load(in);
//			prop.load(reader);
		} catch(IOException e){
			e.printStackTrace();
		}
		return prop;
	}

	@Override
	public File createDir(Properties fileProp) {
		// 加载就出问题，读不了unicode编码
		String filename = fileProp.getProperty("filename");	//获取文件夹的名字
		Properties saveSplitFileProp = new LoadProperties("saveSplitFile.properties")//
				.getProperties();
		String rootDirStr = saveSplitFileProp.getProperty("path");
		String dirStr = rootDirStr + filename;
		File dir = new File(dirStr);
		if(!dir.exists()){
			dir.mkdirs();	//创建文件夹
		}
		return dir;
	}
}
