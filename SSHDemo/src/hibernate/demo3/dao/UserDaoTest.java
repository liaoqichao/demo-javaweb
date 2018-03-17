package hibernate.demo3.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import hibernate.demo3.domain.User;
import lqcUtils.io.IOUtils;

public class UserDaoTest {

	private UserDao userDao = new UserDao();
//	@Test
	public void testSave(){
		//读取图片文件	
		File file = new File("E:/Eclipse/IO/HibernateDemo3/hibernate整体结构.jpg");
		byte[] photo = IOUtils.toByteArray(file);
		
		User user = new User();
		user.setName("赵日天");
		user.setAge(15);
		user.setBirthday(new Date());
		user.setDesc("我赵日天第一个不服~");
		user.setPhoto(photo);
		userDao.save(user);
	}
	
	@Test
	public void testGet() throws IOException{
		User user = userDao.get(3);
		byte[] photo = user.getPhoto();
		FileOutputStream fos = new FileOutputStream("E:/Eclipse/IO/HibernateDemo3/db-hibernate整体结构.jpg");
		fos.write(photo);
		fos.flush();
		fos.close();
	}
}
