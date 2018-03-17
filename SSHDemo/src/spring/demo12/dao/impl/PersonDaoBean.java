package spring.demo12.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.demo12.dao.PersonDao;
import spring.demo12.domain.Person;

//@Repository("demo12_personDao")
//@Component("demo12_personDao")
@Transactional//�����ע�⣬����������з��������ڿ�ʼǰ�����񣬷����������ύ��ع�����
public class PersonDaoBean implements PersonDao{

//	@Resource
//	private DataSource dataSource ;	//ʹ��JdbcTemplate������Դ�������Ϳ����ˣ�����Ҫ����Ա����
	
	@Resource
	private JdbcTemplate jdbcTemplate;	//������ֱ�Ӳ���dataSource��ͨ���������������
	
// �������Ҳ����
//	@Resource
//	public void setDataSource(DataSource dataSource){
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO demo12 (username) VALUES(?)";
		Object[] params = {person.getUsername()};
		int[] argTypes = {java.sql.Types.VARCHAR};//username��Ӧ���ݿ��varchar����
		/*
		 * ��һ��������SQLģ��
		 * �ڶ���������ģ����?��ֵ
		 * ������������ģ����?��Ӧ���ݿ��е�����
		 */
		jdbcTemplate.update(sql,params,argTypes);
	}

	@Override
	public void update(Person person) {
		String sql = "UPDATE demo12 SET username=? WHERE id=?";
		Object[] params ={person.getUsername(),person.getId()};
		int[] argTypes= {java.sql.Types.VARCHAR,java.sql.Types.INTEGER};
		jdbcTemplate.update(sql, params, argTypes);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public Person getPerson(Integer id) {
		String sql = "SELECT * FROM demo12 WHERE id=?";
		Object[] params = {id};
//		int[] argTypes = {java.sql.Types.INTEGER};
		/*
		 * ��ѯʹ��queryForObject��queryForList��queryForXX����
		 * queryForObject:
		 * 	��һ��������SQLģ��
		 * 	�ڶ���������ģ��Ĳ���
		 * 	������������RowMapper
		 * 		��һ��ʵ��RowMapper������new BeanPropertyRowMapper<T>(T.calss),�Զ���װ��bean
		 * 		�ڶ���ʵ��RowMapper����������һ���࣬ʵ��RowMapper�ӿڣ�дmapRow(rs,index)����
		 * 			Ȼ���new Person(), 
		 * 			person.setId(rs.getInt(1));person.setUserName(rs.getString(2))
		 * 			return person.
		 * 			ע�⣺���ﲻ��rs.next()����Ϊ�ⲿ���û�ִ���������
		 */
		return jdbcTemplate.queryForObject(sql, params
				, new BeanPropertyRowMapper<Person>(Person.class));
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)//�޸�Ϊ��֧��������Ϊ��ѯ������Ҫ����������Ӱ������
	@Override
	public List<Person> getPersonList() {
		/*
		 * query(sql,Object[] args, RowMapper rowMapper)
		 * ��һ��������sqlģ��
		 * �ڶ���������new BeanPropertyRowMapper<T>(T.class);
		 * ����в���
		 * jdbcTemplate.query(sql, args, argTypes, rowMapper)
		 * �ڶ���������ģ�����
		 * ������������ģ�������SQL����
		 */
//		String sql ="SELECT * FROM demo12 WHERE id=?";
//		Object[] params = {5};
//		int[] argTypes={java.sql.Types.INTEGER};
//		return jdbcTemplate.query(sql, params,argTypes,new BeanPropertyRowMapper<Person>(Person.class));
		
		String sql ="SELECT * FROM demo12";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class));
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)//ָ���쳣������쳣�����඼��ع�
	//noRollbackFor��ָ������ع�
	public void delete(Integer id) throws Exception {
		String sql = "DELETE FROM demo12 WHERE id=?";
		Object[] params = {id};
		int[] argTypes = {java.sql.Types.INTEGER};
		
		jdbcTemplate.update(sql, params, argTypes);
//		throw new RuntimeException("�������쳣,����ع�");
		throw new Exception("Exception,Ĭ�����񲻻���������Ҽ���@Transactional(rollbackFor=Exception.class)�����Ի�ع�");
		
	}

}
