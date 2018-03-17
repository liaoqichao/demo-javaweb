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
		//��ȡͼƬ�ļ�	
		File file = new File("E:/Eclipse/IO/HibernateDemo3/hibernate����ṹ.jpg");
		byte[] photo = IOUtils.toByteArray(file);
		
		User user = new User();
		user.setName("������");
		user.setAge(15);
		user.setBirthday(new Date());
		user.setDesc("���������һ������~");
		user.setPhoto(photo);
		userDao.save(user);
	}
	
	@Test
	public void testGet() throws IOException{
		User user = userDao.get(3);
		byte[] photo = user.getPhoto();
		FileOutputStream fos = new FileOutputStream("E:/Eclipse/IO/HibernateDemo3/db-hibernate����ṹ.jpg");
		fos.write(photo);
		fos.flush();
		fos.close();
	}
}
