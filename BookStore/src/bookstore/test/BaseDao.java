package bookstore.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import lqcUtils.db.TxQueryRunner;

/**
 * hibernate主要思想BaseDAO
 * 1.父类(BaseDao)获取参数化类型(UserDao<User>)中的泛型(User.class)-------通过反射。
 * 2.通过已经得到的User.class，得到User.class上的类，成员变量(注解的作用目标)和注解。
 * 	通过注解的属性的值与注解的作用目标的对应关系，映射到表列名(注解的属性值)与类属性名(被注解的作用目标)------通过反射。
 * 当然hibernate还有更多的注解（例如一对多注解，多对一注解等）
 * @param <T>
 */
public class BaseDao<T>{
	private QueryRunner qr = new TxQueryRunner();
	
	private Class<T> beanClass;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao(){
		//父类获取子类的参数化类型的泛型。即得到A<String>中的String.class
		beanClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
	}
	public void add(T bean) throws SQLException{
		/*
		 * 如果表名和类名相同，这样就可以解决表明的问题。获取类的名字传递给父类。beanClass通过反射得到。
		 * 但是表名就是和类名不一样怎么办！！属性名也不同怎么办？？！！配置文件（xml）
		 * beanClass得到的具体步骤看ReflectDemo#demo6()。
		 * 既然已经得到beanClass，(User.class , Book.class)，是否可以得到它的属性个数. beanClass.getDeclaredFields()
		 * params的值就是几个成员变量的值，可以通过反射获取
		 * 
		 * 看了那么多是不是觉得很麻烦，不如用配置文件(xml)！！
		 * <bean class="bookstore.user.domain.User" , table="tb_user">
		 * 	<property name="username" column="uname"></property><!--name是类属性名，uname是表列名-->
		 * </bean>
		 * 是不是觉得写配置文件麻烦，注解可以代替配置文件！！
		 * 
		 * public @interface Table{String value();}
		 * 
		 * @Table("tb_user")
		 * class User{...}
		 * 当泛型类型是User，得到User.class后，就可以得到它的注解getAnnotation("Table");就可以知道表名。
		 * 同理cid,uid,bid,...都可以写个注解@ID("id")，这个就可以得到这个类的id，而且数据库表也不用分什么bid,cid,uid都用id就可以
		 * 
		 * 
		 */
		Field[] fields = beanClass.getDeclaredFields();//有declared的是本类声明的不是继承的。
		//平凑sql模板
		String sql = "INSERT INTO "+ beanClass.getSimpleName() +" VALUES(";
		for (int i = 0; i < fields.length; i++) {
			sql += "?";
			if(i < fields.length-1){
				sql += ", ";
			}
		}
		sql += ")";
		
		Object[] params = {/*参数值是什么?*/};
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
