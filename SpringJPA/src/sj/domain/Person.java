package sj.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//JPA规范，标明实体类.
@Table(name="demo14_person")//对应表，没有默认为类名
public class Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
// 实现序列化接口，重写hashCode和equals，根据主键来判断两个Person是否相等
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Id
	@GeneratedValue//使用默认生成策略，相当于hibernate的native。根据方言决定生成策略。
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=10,nullable=false)//没有属性，说明对应的数据库表的字段也是name,nullable=false表示为不能非空
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	
	
}
