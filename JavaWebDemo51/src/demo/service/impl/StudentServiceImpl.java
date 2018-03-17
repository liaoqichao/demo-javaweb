package demo.service.impl;

import demo.dao.StudentDao;
import demo.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao = null;//���ڲ�������ôд��,˭����StudentServiceImpl˭�������ļ��ṩStudentDaoʵ��

	public StudentDao getStudentDao() {
		return studentDao;
	}

	/*
	 * �Ժ�service��Ҫͨ��setDao������dao��ֻҪ�������ļ����beanFactory����һ����װ��
	 * ͬ��servletҲ��Ҫ��setService���������Ǵ������⣡֮ǰд�Ķ��ǰ��Լ��������ƽ���beanFactory������
	 * ����servlet��tomcat������!ͨ��struct��spring�������Ͽ�������beanFactory����servlet��
	 * ����������ע�붼�������ļ�����ɣ�
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public void login() {
		studentDao.add(null);
		studentDao.update(null);
	}
	

}
