package update.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
 * clientBean关联version，version不关联clientBean
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) // 更新不频繁用
@Table(name="_version")
public class Version implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String ver;
	
	@Column
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date time;
	
	// 单项关联
//	@OneToMany(mappedBy="_version",fetch=FetchType.EAGER)
//	private Set<ClientBean> clientBeanSet = new HashSet<ClientBean>();
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Set<ClientBean> getClientBeanSet() {
//		return clientBeanSet;
//	}
//
//	public void setClientBeanSet(Set<ClientBean> clientBeanSet) {
//		this.clientBeanSet = clientBeanSet;
//	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", ver=" + ver + ", description=" + description + ", time=" + time + "]";
	}

}
