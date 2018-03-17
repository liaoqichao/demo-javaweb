package update.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



/**
 * 实体类：用于封装版本更新的所有信息
 * VerUpdate 和 UpdateFile 为一对多关系，双向关联。
 * VerUpdate 和 ClientBean 为一对多关系，双向关联。
 */
//@Entity
//@Table(name="verupdate")
public class VerUpdate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue
	private Integer id;	//主键，AUTO增长策略。使用高地位算法？
//	@OneToOne(cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
//	@JoinColumn()
	private Version _version;
//	private String version;//版本号
//	@OneToMany(mappedBy="verUpdate",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	//指明谁维护关系，防止生成中间表。EAGER表示立即加载，LAZY表示懒加载。全部删除，删除VerUpdate时会删除相关的UpdateFile
	//级联使用情况：一般是一对多和一对一使用级联，多对多和多对一不使用级联。
	/*
	 * CascadeType. PERSIST 级联持久化 ( 保存 ) 操作 
	 * CascadeType. MERGE 级联更新 ( 合并 ) 操作 
	 * CascadeType. REFRESH 级联刷新操作，只会查询获取操作 
	 * CascadeType. REMOVE 级联删除操作 
	 * CascadeType. ALL 级联以上全部操作
	 */
	private Set<UpdateFile> updateFileSet = new HashSet<UpdateFile>();//更新的文件和要被存放的路径
//	@OneToMany(mappedBy="verUpdate",fetch=FetchType.EAGER)
//	private Set<ClientBean> clientBeanSet = new HashSet<ClientBean>();//双向关联
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Set<ClientBean> getClientBeanSet() {
//		return clientBeanSet;
//	}
//	public void setClientBeanSet(Set<ClientBean> clientBeanSet) {
//		this.clientBeanSet = clientBeanSet;
//	}
	public Set<UpdateFile> getUpdateFileSet() {
		return updateFileSet;
	}
	public void setUpdateFileSet(Set<UpdateFile> updateFileSet) {
		this.updateFileSet = updateFileSet;
	}

	
	public Version get_version() {
		return _version;
	}
	public void set_version(Version _version) {
		this._version = _version;
	}
	
	
	
	@Override
	public String toString() {
		return "VerUpdate [id=" + id + ", _version=" + _version + "]";
	}
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
		VerUpdate other = (VerUpdate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
