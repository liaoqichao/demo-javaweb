package hibernate.demo23.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *	Ȩ��
 */
public class Privilege {

	private Long id;
	private String action;//��������Ȩ��
	private Set<UserAccount> userAccountSet = new HashSet<UserAccount>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public Set<UserAccount> getUserAccountSet() {
		return userAccountSet;
	}
	public void setUserAccountSet(Set<UserAccount> userAccountSet) {
		this.userAccountSet = userAccountSet;
	}
	@Override
	public String toString() {
		return "Privilege [id=" + id + ", action=" + action + "]";
	}
	
	
}
