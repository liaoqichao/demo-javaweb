package update.domain;

import java.io.Serializable;

/**
 * 实体类：更新文件使用byte[]封装，和客户端的文件路径（相对主系统根目录）
 */
//@Entity
//@Table(name="updatefile")
public class UpdateFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue
	private Integer id;	//主键，native增长策略
//	@Column
	private byte[] file;
//	@Column
	private String path;// 客户端的文件路径（相对主系统根目录）
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="verid")
	private VerUpdate verUpdate;//双向关联
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public VerUpdate getVerUpdate() {
		return verUpdate;
	}
	public void setVerUpdate(VerUpdate verUpdate) {
		this.verUpdate = verUpdate;
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
		UpdateFile other = (UpdateFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateFile [id=" + id + ", path=" + path + "]";
	}
	
}