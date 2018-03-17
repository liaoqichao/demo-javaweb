package test;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import generator.po.Items;
import generator.po.ItemsExample;
import mapper.ItemsMapper;

public class Demo18_Generator_ItemsMapperTest {

	private SqlSessionFactory sqlSessisonFactory;
	@Before
	public void setUp() throws Exception {
		this.sqlSessisonFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

//	@Test
	public void testDeleteByPrimaryKey() {
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		itemsMapper.deleteByPrimaryKey(4);
		sqlSession.commit();
		sqlSession.close();
	}

//	@Test
	public void testInsert() {
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		 // Ҫע��ǿ��ֶ�
		Items items = new Items();
		items.setName("�ֻ�");
		items.setPrice(999f);
		items.setCreatetime(new Date());
		itemsMapper.insert(items);
	
		
		sqlSession.commit();
		sqlSession.close();
	}

// ����������ѯ
//	@Test
	public void testSelectByPrimaryKey() {
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		/*
		 * Ϊʲô�������������� ��Ϊʵ���������ɹ��̺��Լ��Ĺ������������̣�����Ҫ�����ǵİ��ṹ��ͬ
		 * ����Items�����ͲŻ���ͬ��
		 * �����Ļ�mapper.xml��namespaceҲҪ�޸�
		 */
		generator.po.Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println("-------------------------------------------------");
		System.out.println(items.getName()); //̨ʽ��
		System.out.println("-------------------------------------------------");
		sqlSession.close();
	}

	// �Զ���������ѯ��ʵ���õ����
	@Test
	public void testSelectByExample(){
		
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		
		ItemsExample itemsExample = new ItemsExample();
		// �����ѯ����
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("�ʼǱ�"); // ��Ӳ�ѯ����name���Ե��ڱʼǱ����ַ���ƴ��  and name='�ʼǱ�'
		// ���ܷ��ض�����¼
		List<Items> itemsList = itemsMapper.selectByExample(itemsExample);
		
		System.out.println("-------------------------------------------------");
		for (Items items : itemsList) {
			System.out.println(items.getId()+" : "+items.getName());
		}
		System.out.println("-------------------------------------------------");
		sqlSession.close();
	}
	
//	@Test
	public void testUpdateByPrimaryKey() {
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		// �������ֶν��и���,��Ҫ�Ȳ�ѯ�����
		Items items = itemsMapper.selectByPrimaryKey(4);
		items.setCreatetime(new Date());
		itemsMapper.updateByPrimaryKey(items);
		
		// �Դ����ֶβ�Ϊ�ղŸ���,ͨ��������������ʹ�ô˷���������Ҫ�Ȳ�ѯ�ٸ���
//		itemsMapper.updateByPrimaryKeySelective(record);
		
		sqlSession.commit();
		sqlSession.close();
	}

}
