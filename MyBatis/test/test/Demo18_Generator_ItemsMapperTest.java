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
		
		 // 要注意非空字段
		Items items = new Items();
		items.setName("手机");
		items.setPrice(999f);
		items.setCreatetime(new Date());
		itemsMapper.insert(items);
	
		
		sqlSession.commit();
		sqlSession.close();
	}

// 根据主键查询
//	@Test
	public void testSelectByPrimaryKey() {
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		/*
		 * 为什么会出现这种情况？ 因为实际上是生成工程和自己的工程是两个工程，而且要求它们的包结构相同
		 * 这样Items的类型才会相同。
		 * 这样的话mapper.xml的namespace也要修改
		 */
		generator.po.Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println("-------------------------------------------------");
		System.out.println(items.getName()); //台式机
		System.out.println("-------------------------------------------------");
		sqlSession.close();
	}

	// 自定义条件查询，实际用的最多
	@Test
	public void testSelectByExample(){
		
		SqlSession sqlSession = this.sqlSessisonFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		
		ItemsExample itemsExample = new ItemsExample();
		// 构造查询条件
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("笔记本"); // 添加查询条件name属性等于笔记本，字符串拼接  and name='笔记本'
		// 可能返回多条记录
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
		
		// 对所有字段进行更新,需要先查询后更新
		Items items = itemsMapper.selectByPrimaryKey(4);
		items.setCreatetime(new Date());
		itemsMapper.updateByPrimaryKey(items);
		
		// 对传入字段不为空才更新,通常在批量更新中使用此方法，不需要先查询再更新
//		itemsMapper.updateByPrimaryKeySelective(record);
		
		sqlSession.commit();
		sqlSession.close();
	}

}
