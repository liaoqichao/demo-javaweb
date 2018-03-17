package update.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String username;
	@Column
	private String psw;
	@Column
	private Integer permissions;
	public Integer getPermissions() {
		return permissions;
	}
	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", psw=" + psw + ", permissions=" + permissions + "]";
	}
	
}
