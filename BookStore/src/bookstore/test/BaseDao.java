package bookstore.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import lqcUtils.db.TxQueryRunner;

/**
 * hibernate��Ҫ˼��BaseDAO
 * 1.����(BaseDao)��ȡ����������(UserDao<User>)�еķ���(User.class)-------ͨ�����䡣
 * 2.ͨ���Ѿ��õ���User.class���õ�User.class�ϵ��࣬��Ա����(ע�������Ŀ��)��ע�⡣
 * 	ͨ��ע������Ե�ֵ��ע�������Ŀ��Ķ�Ӧ��ϵ��ӳ�䵽������(ע�������ֵ)����������(��ע�������Ŀ��)------ͨ�����䡣
 * ��Ȼhibernate���и����ע�⣨����һ�Զ�ע�⣬���һע��ȣ�
 * @param <T>
 */
public class BaseDao<T>{
	private QueryRunner qr = new TxQueryRunner();
	
	private Class<T> beanClass;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao(){
		//�����ȡ����Ĳ��������͵ķ��͡����õ�A<String>�е�String.class
		beanClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
	}
	public void add(T bean) throws SQLException{
		/*
		 * ���������������ͬ�������Ϳ��Խ�����������⡣��ȡ������ִ��ݸ����ࡣbeanClassͨ������õ���
		 * ���Ǳ������Ǻ�������һ����ô�죡��������Ҳ��ͬ��ô�죿�����������ļ���xml��
		 * beanClass�õ��ľ��岽�迴ReflectDemo#demo6()��
		 * ��Ȼ�Ѿ��õ�beanClass��(User.class , Book.class)���Ƿ���Եõ��������Ը���. beanClass.getDeclaredFields()
		 * params��ֵ���Ǽ�����Ա������ֵ������ͨ�������ȡ
		 * 
		 * ������ô���ǲ��Ǿ��ú��鷳�������������ļ�(xml)����
		 * <bean class="bookstore.user.domain.User" , table="tb_user">
		 * 	<property name="username" column="uname"></property><!--name������������uname�Ǳ�����-->
		 * </bean>
		 * �ǲ��Ǿ���д�����ļ��鷳��ע����Դ��������ļ�����
		 * 
		 * public @interface Table{String value();}
		 * 
		 * @Table("tb_user")
		 * class User{...}
		 * ������������User���õ�User.class�󣬾Ϳ��Եõ�����ע��getAnnotation("Table");�Ϳ���֪��������
		 * ͬ��cid,uid,bid,...������д��ע��@ID("id")������Ϳ��Եõ�������id���������ݿ��Ҳ���÷�ʲôbid,cid,uid����id�Ϳ���
		 * 
		 * 
		 */
		Field[] fields = beanClass.getDeclaredFields();//��declared���Ǳ��������Ĳ��Ǽ̳еġ�
		//ƽ��sqlģ��
		String sql = "INSERT INTO "+ beanClass.getSimpleName() +" VALUES(";
		for (int i = 0; i < fields.length; i++) {
			sql += "?";
			if(i < fields.length-1){
				sql += ", ";
			}
		}
		sql += ")";
		
		Object[] params = {/*����ֵ��ʲô?*/};
		qr.update(sql, params);
	}
	
	public void update(T bean){
		
	}
	
	public void delete(T bean){
		
	}
	public T load(String uuid){
		return null;
	}
	public List<T> findAll(){
		return null;
	}
}
