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
@Transactional//有这个注解，类里面的所有方法都会在开始前打开事务，方法结束后提交或回滚事务
public class PersonDaoBean implements PersonDao{

//	@Resource
//	private DataSource dataSource ;	//使用JdbcTemplate，数据源做参数就可以了，不需要做成员变量
	
	@Resource
	private JdbcTemplate jdbcTemplate;	//不建议直接操作dataSource，通过这个类来操作。
	
// 这个方法也不用
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
		int[] argTypes = {java.sql.Types.VARCHAR};//username对应数据库的varchar类型
		/*
		 * 第一个参数，SQL模板
		 * 第二个参数，模板中?的值
		 * 第三个参数，模板中?对应数据库中的类型
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
		 * 查询使用queryForObject，queryForList等queryForXX方法
		 * queryForObject:
		 * 	第一个参数：SQL模板
		 * 	第二个参数：模板的参数
		 * 	第三个参数：RowMapper
		 * 		第一个实现RowMapper方法：new BeanPropertyRowMapper<T>(T.calss),自动封装成bean
		 * 		第二个实现RowMapper方法：创建一个类，实现RowMapper接口，写mapRow(rs,index)方法
		 * 			然后就new Person(), 
		 * 			person.setId(rs.getInt(1));person.setUserName(rs.getString(2))
		 * 			return person.
		 * 			注意：这里不用rs.next()，因为外部调用会执行这个方法
		 */
		return jdbcTemplate.queryForObject(sql, params
				, new BeanPropertyRowMapper<Person>(Person.class));
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)//修改为不支持事务，因为查询本身不需要事务，用事务影响性能
	@Override
	public List<Person> getPersonList() {
		/*
		 * query(sql,Object[] args, RowMapper rowMapper)
		 * 第一个参数：sql模板
		 * 第二个参数：new BeanPropertyRowMapper<T>(T.class);
		 * 如果有参数
		 * jdbcTemplate.query(sql, args, argTypes, rowMapper)
		 * 第二个参数：模板参数
		 * 第三个参数：模板参数的SQL类型
		 */
//		String sql ="SELECT * FROM demo12 WHERE id=?";
//		Object[] params = {5};
//		int[] argTypes={java.sql.Types.INTEGER};
//		return jdbcTemplate.query(sql, params,argTypes,new BeanPropertyRowMapper<Person>(Person.class));
		
		String sql ="SELECT * FROM demo12";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class));
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)//指定异常和这个异常的子类都会回滚
	//noRollbackFor是指定不会回滚
	public void delete(Integer id) throws Exception {
		String sql = "DELETE FROM demo12 WHERE id=?";
		Object[] params = {id};
		int[] argTypes = {java.sql.Types.INTEGER};
		
		jdbcTemplate.update(sql, params, argTypes);
//		throw new RuntimeException("运行期异常,事务回滚");
		throw new Exception("Exception,默认事务不会滚，但是我加了@Transactional(rollbackFor=Exception.class)，所以会回滚");
		
	}

}
