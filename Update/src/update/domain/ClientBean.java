package update.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 实体类：用于封装客户端的信息 ClientBean 和 Version为多对一关系，双向关联
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)		// 更新频繁用
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) // 更新不频繁用
@Table(name = "clientbean")
public class ClientBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;// 主键
	@Column
	private String clientname;// 客户端的名字，用于识别客户端
	@Column
	private String ip;// 客户端的ip地址
	@ManyToOne(fetch = FetchType.EAGER) // 立即加载，后面看能不能换懒加载。
	@JoinColumn(name = "verid")
	private Version _version;// 更新版本号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Version get_version() {
		return _version;
	}

	public void set_version(Version _version) {
		this._version = _version;
	}

	@Override
	public String toString() {
		return "ClientBean [id=" + id + ", clientname=" + clientname + ", ip=" + ip + "]";
	}

}
