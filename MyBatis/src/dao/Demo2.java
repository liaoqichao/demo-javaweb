package dao;

import java.io.IOException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016��3��9��
 */
public class Demo2 {

	/**
	 * ��
	 * @throws IOException
	 */
//	@Test
	public void testInsertUser() throws IOException{
		/*
		 * 1. �����������ļ�����Sqlsession����
		 * 2. ���ݹ����õ�sqlSession
		 * 3. ͨ��sqlSession�������ݿ�
		 * 		- insert
		 *  	- commit����//Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@4d95d2a2]
		 *  		�����Զ��ύ��Ҫ�ֶ��ύ��
		 * 4. �ͷ���Դ
		 */
		
		// 1. 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3.
		User user = new User();
		user.setUsername("�������");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("��������");
		sqlSession.insert("test.insertUser", user);
		
		// �ύ
		sqlSession.commit();
		
		// ��Ϊ���������������Բ�֪������������Ҫ��ȡ������
		System.out.println(user.getId());
		// 4. 
		sqlSession.close();
	}
	
	/**
	 * ɾ
	 * @throws IOException
	 */
//	@Test
	public void testDeleteUserById() throws IOException{
		/*
		 * 1. �õ�sqlSessionFactory
		 * 2. �õ�sqlSession
		 * 3. ͨ��sqlSession�������ݿ�
		 * 4. �ύ����
		 * 5. �ͷ���Դ
		 */
		
		// 1. 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. 
		sqlSession.delete("test.deleteUserById", 22); //ɾ�� 22 ��С��
		
		// 4.
		sqlSession.commit();
		
		// 5. 
		sqlSession.close();
	}
	
	/**
	 * ����
	 * @throws IOException 
	 */
	@Test
	public void updateUser() throws IOException{
		/*
		 * 1. �õ�SqlSessionFactory
		 * 2. �õ�SqlSession
		 * 3. ͨ��sqlSession�������ݿ�
		 * 4. �ύ����
		 * 5. �ͷ���Դ
		 */
		
		// 1. 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. 
		User user = sqlSession.selectOne("test.findUserById", 26); //�޸�idΪ26�ģ�username=���壬�������Զ�Ϊnull�ļ�¼
		System.out.println(user);
		user.setUsername("�Ҹ���ǰ������");
		user.setSex("1");
		user.setBirthday(new Date());
		user.setAddress("����");
		
		sqlSession.update("test.updateUser", user);
		
		// 4. 
		sqlSession.commit();
		
		// 5. 
		sqlSession.close();
	}
}
