package update.service;

import java.util.List;

import update.domain.Version;

public interface VersionService {

	/**
	 * 根据版本号判断该版本是否存在
	 * @param ver
	 * @return
	 */
	public boolean isExist(String ver);
	
	/**
	 * 根据版本号查找版本对象
	 * @param ver
	 * @return
	 */
	public Version fingByVer(String ver);

	public List<Version> list();

	/**
	 * 修改服务器配置文件的版本号，使终端都更新到那个版本
	 * @param ver 版本号字符串
	 * @return 返回服务器是否成功修改配置文件的版本号
	 */
	public boolean useVersion(String ver) throws Exception;

	/**
	 * 指定客户端，指定版本进行更新
	 * @param cid
	 * @param vid
	 * @return 返回服务器是否成功修改配置文件的版本号
	 */
	public boolean specificUseVersion(Integer cid, Integer vid) throws Exception;
	
	public void update(Version version);
}