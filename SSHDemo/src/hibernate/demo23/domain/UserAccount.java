package hibernate.demo23.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 	”√ªß’À∫≈
 */
public class UserAccount {
	
	private Long id;
	private String loginName;
	private String password;
	private Employee employee;
	private Set<Privilege> privilegeSet = new HashSet<Privilege>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Set<Privilege> getPrivilegeSet() {
		return privilegeSet;
	}
	public void setPrivilegeSet(Set<Privilege> privilegeSet) {
		this.privilegeSet = privilegeSet;
	}
	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", loginName=" + loginName + ", password=" + password + "]";
	}
	
}
