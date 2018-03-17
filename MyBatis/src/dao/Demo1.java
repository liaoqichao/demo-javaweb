package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016��3��8��
 */
public class Demo1 {
	
	/**
	 *  ����id��ѯ�û���Ϣ���õ�һ����¼�Ľ��
	 * @throws IOException
	 */
//	@Test
	public void testFindUserById() throws IOException{
		
		/*
		 * 1. �����Ự����
		 * 2. ͨ�������õ�SqlSession
		 * 3. ͨ��SqlSession�������ݿ�
		 * 4. �ͷ���Դ
		 */
		
		// 1. �����Ự����,�����������ļ�
		// mybatis�������ļ����·��
		String resource = "SqlMapConfig.xml";//��Ŀ¼��src?
		// ��ȡmybatis�������ļ���������
		InputStream inputStream = Resources.getResourceAsStream(resource); // Resources��mybatis�ṩ
		// �õ��Ự����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 2. ͨ�������õ�SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. ͨ��sqlSession�������ݿ�
		/*
		 * ��һ��������ӳ���ļ��е�statement��id�����������ռ�+statement��id // namespace.statementid
		 * �ڶ���������ָ����ӳ���ļ�����ƥ���parameterType���͵Ĳ���.�����ļ���int����������1�����������ַ���"1"Ҳû����
		 * ���ط������ͣ�����ӳ���ļ�����ƥ���resultType��Ӧ������
		 */
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		
		// 4. �ͷ���Դ
		sqlSession.close(); // ��finlly������ͷ�
	}
	
	/**
	 * �����û�����ģ����ѯ�û���Ϣ��ӳ���ļ�ʹ��ԭ����User.xml
	 * @throws IOException 
	 */
	@Test
	public void findUsersByName() throws IOException{
		/*
		 * 1. �����Ự����
		 * 2. ͨ�������õ��Ự
		 * 3. ͨ���Ự�������ݿ�
		 * 4. �ͷ���Դ
		 */
		// 1. 
		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. ��һ��������statement��id���ڶ���������sqlģ��Ĳ�����ģ����ѯҪ��Ӱٷֺ�
		// ʹ��#{}ռλ�������ɵ�sqlģ�����?ռλ��
//		List<User> userList = sqlSession.selectList("demo1.findUsersByName", "%С��%");
		// ʹ��${}ƴ�ӡ����ɵ�sqlģ��û��?ռλ��
		// ӳ���ļ���ʹ����'%${value}%'�������ǵ������ַ���ƴ�ӣ����'%С��%'���������ַ���ƴ�ӻ�����sqlע�룬������#{}��
		List<User> userList = sqlSession.selectList("test.findUsersByName", "С��");
		
		for (User user : userList) {
			System.out.println(user);
		}
		
		// 4. 
		sqlSession.close();
	}
}
