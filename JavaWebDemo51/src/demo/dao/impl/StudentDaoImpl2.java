package demo.dao.impl;

import demo.dao.StudentDao;
import demo.domain.Student;

public class StudentDaoImpl2 implements StudentDao {

	@Override
	public void add(Student stu) {
		System.out.println("studentDaoImpl2#add222");
	}

	@Override
	public void update(Student stu) {
		System.out.println("studentDaoImpl2#update222");
	}

}
