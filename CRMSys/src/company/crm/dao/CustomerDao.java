package company.crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import company.crm.domain.Customer;
import company.crm.domain.PageBean;
import lqcUtils.db.TxQueryRunner;

/**
 * 把三层提及结构扩展为五层体系：表示层、控制/中介层、领域层、数据持久层、数据源层
 * 数据持久层
 */
public class CustomerDao {

	//依赖QueryRunner
	private QueryRunner qr = new TxQueryRunner();
	
	public void add(Customer c){
		
		try{
			//1.提供SQL模板
			String sql = "INSERT INTO t_customer VALUES(?,?,?,?,?,?,?)";
			
			//2.准备参数
			Object[] params= {c.getCid(),c.getCname(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription()};
			
			//3.执行SQL语句
			qr.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	public PageBean<Customer> findAll(int pc, int ps){
//		List<Customer> list = null;
		try{
//			//1.准备SQL模板
//			String sql = "SELECT * FROM t_customer";
//			//2.准备参数,没有参数
//			
//			//3.执行SQL语句
//			list = qr.query(sql, new BeanListHandler<Customer>(Customer.class));
			
			//1.创建PageBean对象
			PageBean<Customer> pb = new PageBean<Customer>();
			
			//2.设置pageBean的pc和ps
			pb.setPc(pc);
			pb.setPs(ps);
			
			//3.得到totalRecord和设置到pb
			String sql = "SELECT COUNT(*) FROM t_customer";
			Number number = (Number)qr.query(sql, new ScalarHandler<Number>());
			int tr = number.intValue();
			pb.setTr(tr);
			
			//4.得到tp,totalPage.pageBean可以自己算出，不用赋值，也没提供赋值的方法。
			//5.得到beanList，设置给pb
			sql = "SELECT * FROM t_customer ORDER BY cname limit ?,?";//limit是MySql的方言
			Object params[] ={(pc-1)*ps,ps};//第pc页是第(pc-1)*ps开始，ps结束。
//			例如第一页：（1-1）*10,10表示第0条记录到第9条记录  。第5页是第40条记录到第49条记录
			List<Customer> beanList = qr.query(sql, new BeanListHandler<Customer>(Customer.class), params);
			pb.setBeanList(beanList);
			//6.返回给pb
			return pb;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
//		return list;
		
	}
	
	/**
	 * 根据cid找到Customer
	 * @param cid
	 * @return Customer
	 */
	public Customer findByCid(String cid){
		
		try{
		//单行结果集。结果集处理器用BeanHandler
		String sql = "SELECT * FROM t_customer WHERE cid=?";
		
		return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
 	}
	
	/**
	 * 根据参数Customer的cid修改Customer的其他数据
	 * @param c
	 */
	public void eidt(Customer c){
		try {
			//1.准备SQL模板
			String sql = "UPDATE t_customer SET cname=?,gender=?,birthday=? ,"
					+ "cellphone=?,email=?,description=? WHERE cid=?";
			//2.准备参数
			Object[] params = {c.getCname(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription(),c.getCid()};
			//3.执行语句
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete (String cid){
		try{
			
			//1.
			String sql = "DELETE FROM t_customer WHERE cid = ?";
			//2.
			Object param = cid;
			//3.
			qr.update(sql, param);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public PageBean<Customer> query(int pc,int ps,Customer criteria){
		/*
		 * 1.SQL模板,条件的排列组合结果很多！
		 * 	a.给出SQL语句前缀,这里的1=1是为了后面拼凑的时候同一用AND XXX=? 而不是某一个为WHERE
		 * 	b.判断条件完成SQL中的WHERE字句
		 * 2.给出参数
		 * 	a.创建ArrayList装参数值，因为参数的个数不固定
		 * 3.使用BeanListHandler
		 * 
		 */
		try{
//			没使用分页
//			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1 ");
//			String cname = criteria.getCname();
//			String gender = criteria.getGender();
//			String cellphone = criteria.getCellphone();
//			String email = criteria.getEmail();
//			ArrayList<Object> al = new ArrayList<Object>();
//			if(cname != null && !cname.trim().isEmpty()){
//				//名字需要模糊查询
//				sql.append("and cname like ? ");
//				al.add("%" + cname + "%");
//			}
//			if( gender != null && !gender.trim().isEmpty()){
//				sql.append("and gender=? ");
//				al.add(gender);
//			}
//			if( cellphone != null && !cellphone.trim().isEmpty()){
//				//电话需要模糊查询
//				sql.append("and cellphone like ? ");
//				al.add("%"+cellphone+"%");
//			}
//			if( email != null && !email.trim().isEmpty()){
//				//邮箱需要模糊查询
//				sql.append("and email like ? ");
//				al.add("%"+email+"%");
//			}
//			
//			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), al.toArray());
			
//			使用分页
			//1.创建pageBean对象
			PageBean<Customer> pb = new PageBean<Customer>();
			
			//2.设置已有属性，pc，ps
			pb.setPc(pc);
			pb.setPs(ps);
			
			//3.得到tr
			StringBuilder cntSql = new StringBuilder("SELECT COUNT(*) FROM t_customer ");
			StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");
			String cname = criteria.getCname();
			String gender = criteria.getGender();
			String cellphone = criteria.getCellphone();
			String email = criteria.getEmail();
			ArrayList<Object> al = new ArrayList<Object>();//params
			if(cname != null && !cname.trim().isEmpty()){
				//名字需要模糊查询
				whereSql.append("and cname like ? ");
				al.add("%" + cname + "%");
			}
			if( gender != null && !gender.trim().isEmpty()){
				whereSql.append("and gender=? ");
				al.add(gender);
			}
			if( cellphone != null && !cellphone.trim().isEmpty()){
				//电话需要模糊查询
				whereSql.append("and cellphone like ? ");
				al.add("%"+cellphone+"%");
			}
			if( email != null && !email.trim().isEmpty()){
				//邮箱需要模糊查询
				whereSql.append("and email like ? ");
				al.add("%"+email+"%");
			}
			Number number = (Number)qr.query(cntSql.append(whereSql).toString(), new ScalarHandler<Number>(), al.toArray());
			int tr = number.intValue();
			pb.setTr(tr);
			
			//4.得到beanList。把2条查询语句分成cntSql,whereSql,sql,limitsql.方便重用whereSql
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer ");
			//查询beanList还需要给出limit子句
			StringBuilder limitSql = new StringBuilder(" LIMIT ?,? ");
			//al中需要给出limit后2个问号对应的值
			al.add((pc-1)*ps);
			al.add(ps);
			List<Customer> beanList = qr.query(sql.append(whereSql).append(limitSql).toString(),
					new BeanListHandler<Customer>(Customer.class), al.toArray());
			pb.setBeanList(beanList);
			
			//5.返回pageBean
			return pb;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
