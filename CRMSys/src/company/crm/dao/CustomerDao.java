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
 * �������ἰ�ṹ��չΪ�����ϵ����ʾ�㡢����/�н�㡢����㡢���ݳ־ò㡢����Դ��
 * ���ݳ־ò�
 */
public class CustomerDao {

	//����QueryRunner
	private QueryRunner qr = new TxQueryRunner();
	
	public void add(Customer c){
		
		try{
			//1.�ṩSQLģ��
			String sql = "INSERT INTO t_customer VALUES(?,?,?,?,?,?,?)";
			
			//2.׼������
			Object[] params= {c.getCid(),c.getCname(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription()};
			
			//3.ִ��SQL���
			qr.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	public PageBean<Customer> findAll(int pc, int ps){
//		List<Customer> list = null;
		try{
//			//1.׼��SQLģ��
//			String sql = "SELECT * FROM t_customer";
//			//2.׼������,û�в���
//			
//			//3.ִ��SQL���
//			list = qr.query(sql, new BeanListHandler<Customer>(Customer.class));
			
			//1.����PageBean����
			PageBean<Customer> pb = new PageBean<Customer>();
			
			//2.����pageBean��pc��ps
			pb.setPc(pc);
			pb.setPs(ps);
			
			//3.�õ�totalRecord�����õ�pb
			String sql = "SELECT COUNT(*) FROM t_customer";
			Number number = (Number)qr.query(sql, new ScalarHandler<Number>());
			int tr = number.intValue();
			pb.setTr(tr);
			
			//4.�õ�tp,totalPage.pageBean�����Լ���������ø�ֵ��Ҳû�ṩ��ֵ�ķ�����
			//5.�õ�beanList�����ø�pb
			sql = "SELECT * FROM t_customer ORDER BY cname limit ?,?";//limit��MySql�ķ���
			Object params[] ={(pc-1)*ps,ps};//��pcҳ�ǵ�(pc-1)*ps��ʼ��ps������
//			�����һҳ����1-1��*10,10��ʾ��0����¼����9����¼  ����5ҳ�ǵ�40����¼����49����¼
			List<Customer> beanList = qr.query(sql, new BeanListHandler<Customer>(Customer.class), params);
			pb.setBeanList(beanList);
			//6.���ظ�pb
			return pb;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
//		return list;
		
	}
	
	/**
	 * ����cid�ҵ�Customer
	 * @param cid
	 * @return Customer
	 */
	public Customer findByCid(String cid){
		
		try{
		//���н�������������������BeanHandler
		String sql = "SELECT * FROM t_customer WHERE cid=?";
		
		return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
 	}
	
	/**
	 * ���ݲ���Customer��cid�޸�Customer����������
	 * @param c
	 */
	public void eidt(Customer c){
		try {
			//1.׼��SQLģ��
			String sql = "UPDATE t_customer SET cname=?,gender=?,birthday=? ,"
					+ "cellphone=?,email=?,description=? WHERE cid=?";
			//2.׼������
			Object[] params = {c.getCname(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription(),c.getCid()};
			//3.ִ�����
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
		 * 1.SQLģ��,������������Ͻ���ܶ࣡
		 * 	a.����SQL���ǰ׺,�����1=1��Ϊ�˺���ƴ�յ�ʱ��ͬһ��AND XXX=? ������ĳһ��ΪWHERE
		 * 	b.�ж��������SQL�е�WHERE�־�
		 * 2.��������
		 * 	a.����ArrayListװ����ֵ����Ϊ�����ĸ������̶�
		 * 3.ʹ��BeanListHandler
		 * 
		 */
		try{
//			ûʹ�÷�ҳ
//			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1 ");
//			String cname = criteria.getCname();
//			String gender = criteria.getGender();
//			String cellphone = criteria.getCellphone();
//			String email = criteria.getEmail();
//			ArrayList<Object> al = new ArrayList<Object>();
//			if(cname != null && !cname.trim().isEmpty()){
//				//������Ҫģ����ѯ
//				sql.append("and cname like ? ");
//				al.add("%" + cname + "%");
//			}
//			if( gender != null && !gender.trim().isEmpty()){
//				sql.append("and gender=? ");
//				al.add(gender);
//			}
//			if( cellphone != null && !cellphone.trim().isEmpty()){
//				//�绰��Ҫģ����ѯ
//				sql.append("and cellphone like ? ");
//				al.add("%"+cellphone+"%");
//			}
//			if( email != null && !email.trim().isEmpty()){
//				//������Ҫģ����ѯ
//				sql.append("and email like ? ");
//				al.add("%"+email+"%");
//			}
//			
//			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), al.toArray());
			
//			ʹ�÷�ҳ
			//1.����pageBean����
			PageBean<Customer> pb = new PageBean<Customer>();
			
			//2.�����������ԣ�pc��ps
			pb.setPc(pc);
			pb.setPs(ps);
			
			//3.�õ�tr
			StringBuilder cntSql = new StringBuilder("SELECT COUNT(*) FROM t_customer ");
			StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");
			String cname = criteria.getCname();
			String gender = criteria.getGender();
			String cellphone = criteria.getCellphone();
			String email = criteria.getEmail();
			ArrayList<Object> al = new ArrayList<Object>();//params
			if(cname != null && !cname.trim().isEmpty()){
				//������Ҫģ����ѯ
				whereSql.append("and cname like ? ");
				al.add("%" + cname + "%");
			}
			if( gender != null && !gender.trim().isEmpty()){
				whereSql.append("and gender=? ");
				al.add(gender);
			}
			if( cellphone != null && !cellphone.trim().isEmpty()){
				//�绰��Ҫģ����ѯ
				whereSql.append("and cellphone like ? ");
				al.add("%"+cellphone+"%");
			}
			if( email != null && !email.trim().isEmpty()){
				//������Ҫģ����ѯ
				whereSql.append("and email like ? ");
				al.add("%"+email+"%");
			}
			Number number = (Number)qr.query(cntSql.append(whereSql).toString(), new ScalarHandler<Number>(), al.toArray());
			int tr = number.intValue();
			pb.setTr(tr);
			
			//4.�õ�beanList����2����ѯ���ֳ�cntSql,whereSql,sql,limitsql.��������whereSql
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer ");
			//��ѯbeanList����Ҫ����limit�Ӿ�
			StringBuilder limitSql = new StringBuilder(" LIMIT ?,? ");
			//al����Ҫ����limit��2���ʺŶ�Ӧ��ֵ
			al.add((pc-1)*ps);
			al.add(ps);
			List<Customer> beanList = qr.query(sql.append(whereSql).append(limitSql).toString(),
					new BeanListHandler<Customer>(Customer.class), al.toArray());
			pb.setBeanList(beanList);
			
			//5.����pageBean
			return pb;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
