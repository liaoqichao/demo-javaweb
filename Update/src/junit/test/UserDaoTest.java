package junit.test;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import update.dao.ClientBeanDao;
import update.dao.UpdateFileDao;
import update.dao.UserDao;
import update.dao.VerUpdateDao;
import update.dao.VersionDao;
import update.domain.VerUpdate;
import update.domain.Version;

public class UserDaoTest {

	private static VerUpdateDao verUpdateDao;
	private static ClientBeanDao clientBeanDao;
	private static UpdateFileDao updateFileDao;
	private static VersionDao versionDao ;
	private static UserDao userDao;
	
	static{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		verUpdateDao = (VerUpdateDao) ctx.getBean("verUpdateDao");
		clientBeanDao = (ClientBeanDao) ctx.getBean("clientBeanDao");
		updateFileDao = (UpdateFileDao) ctx.getBean("updateFileDao");
		versionDao = (VersionDao) ctx.getBean("versionDao"); 
		userDao = (UserDao) ctx.getBean("userDao");
	}
	@Test
	public void testVerUpdateDao(){
		
		//----save  ---
		
//		VerUpdate verUpdate = new VerUpdate();
//		Version version = versionDao.get(231);
//		verUpdate.set_version(version);
//		verUpdateDao.save(verUpdate);
		
		//----
		
		//-----get------
//		VerUpdate verUpdate = verUpdateDao.get(52);
//		System.out.println(verUpdate);
//		//懒加载异常,先用Fetch=FetchType.EAGER使用立即加载。
//		System.out.println(verUpdate.get_version().getVer());
//		System.out.println(verUpdate.getUpdateFileSet().size());
		
		//--------------------
		
		//----getList-----
//		System.out.println(verUpdateDao.getList());
		//-----------------
		
		//---update------
//		VerUpdate verUpdate = verUpdateDao.get(11);
//		verUpdate.setVersion("v1.2");
//		verUpdateDao.update(verUpdate);
		//---------------
		
		//---delete-----
		
		//报错，因为这个记录有外键关系。
		//verUpdate和updateFile可以通过级联删除来删除
		//verUpdate和ClientBean需要先解除关联关系，再删除
//		verUpdateDao.delete(26);
		
		//--------------
	}
	@Test
	public void testClientBeanDao(){
		
		//-----save ---
		
//		ClientBean clientBean = new ClientBean();
//		clientBean.setClientname("yt233");
//		clientBean.setIp("127.0.0.1");
//		Version version = versionDao.get(219);
//		clientBean.set_version(version);
//		clientBeanDao.save(clientBean);
		
		//--------------------------
//		clientBeanDao.delete(20);
		//----get---------------
		
//		ClientBean cb = clientBeanDao.get(49);
//		System.out.println(cb);
//		System.out.println(cb.get_version());
		
		//-----------------
		
		//----getList-----
//		System.out.println(clientBeanDao.getList());
		
		//-----------------
		
		//---update------
//		ClientBean cb = clientBeanDao.get(14);
//		cb.setClientname("ns01");
//		clientBeanDao.update(cb);
		//---------------
		
		//---delete-----
		
		//--------------
	}
	
	@Test
	public void testUpdateFileDao() throws SQLException, Exception{
		
		//------------save--------------
		
		//文件太大 Packet for query is too large (62341138 > 20971520).
		//my.ini max_allowed_packet = 100M配置了这个最大上传100M
		//又改成了1G-_,-
		
//		UpdateFile updateFile = new UpdateFile();
////		File file = new File("E:\\Eclipse\\IO\\ByteStream\\倉木麻衣&NERDHEAD-どうして好きなんだろう ～Winter Ver.～.ape");
//		File file = new File("E:\\课程\\毕业设计\\最终要交\\开题报告.doc");
//		updateFile.setFile(DSTransfer.fileToByteArray(file));
//		System.out.println(updateFile.getFile().length);
////		updateFile.setPath("dir1/倉木麻衣&NERDHEAD-どうして好きなんだろう ～Winter Ver.～.ape");
//		updateFile.setPath("v1.0/开题报告.doc");
//		updateFile.setVerUpdate(verUpdateDao.get(219));//产生关联
//		updateFileDao.save(updateFile);
		
		//-------------------------------
		
		
		//-----------get--------------
		
//		UpdateFile uf = updateFileDao.get(62);
//		byte[] bytes = uf.getFile();
//		File file = new File("E:\\Eclipse\\IO\\ByteStream\\get-倉木麻衣&NERDHEAD-どうして好きなんだろう ～Winter Ver.～.ape");
//		if(!file.exists()){
//			file.createNewFile();
//		}
//		OutputStream out = new FileOutputStream(file);
//		BufferedOutputStream bos = new BufferedOutputStream(out);
//		bos.write(bytes);
//		bos.flush();
//		bos.close();
//		System.out.println(uf.getVerUpdate().getClientBeanSet());
		//---------------------------
		
		//----getList-----
//		System.out.println(updateFileDao.getList());
		//-----------------
		
		//---update------
//		UpdateFile uf = updateFileDao.get(15);
//		uf.setPath("IO\\ByteStream\\get-倉木麻衣-SAFEST PLACE.wma");
//		updateFileDao.update(uf);
		//---------------
		
		//---delete-----
//		updateFileDao.delete(21);
		//--------------
	}
	
	@Test
	public void TestVersion(){
		// ---  save  ----
//		Version version = new Version();
//		version.setVer("v1.1");
//		version.setTime(new Date());
//		version.setDescription("v1.1");
//		versionDao.save(version);
		
		//  --- delete  ----
		
		
		//  --- update ----
		
		//  --- get ----
//		Version version = versionDao.get(47);
//		System.out.println(version.getClientBeanSet());
		//  --- list ----
	}
	
	@Test
	public void  testUser(){
		
//		--- save ---
//		User user = new User();
//		user.setUsername("admin");
//		user.setPsw("123");
//		userDao.save(user);
		
//     --- delete ---
//		userDao.delete(107);
//     --- update ---
//		User user = userDao.get(107);
//		user.setUsername("list");
//		userDao.update(user);
//     --- get ---
//		System.out.println(userDao.get(107));
//     --- list ---
//		System.out.println(userDao.list());
//		--- findByUsername ---
//		System.out.println(userDao.findByUsername("admin"));
	}
}
