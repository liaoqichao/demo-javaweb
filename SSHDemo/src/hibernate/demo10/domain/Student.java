package hibernate.demo10.domain;

import java.util.HashSet;
import java.util.Set;

public class Student {

	private Long id;
	private String name;
	private Set<Teacher> teacherSet = new HashSet<Teacher>();//关联的老师们。
	public Set<Teacher> getTeacherSet() {
		return teacherSet;
	}
	public void setTeacherSet(Set<Teacher> teacherSet) {
		this.teacherSet = teacherSet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
}
