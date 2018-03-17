package bookstore.category.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.category.dao.CategoryDao;
import bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * ��ӷ��ࡣ�������������ͬ����ӷ���,�������ͬ�������׳��쳣
	 * @param category
	 * @throws CategoryException 
	 */
	public void add(Category category) throws CategoryException {
		if(categoryDao.findByCname(category.getCname()) == null){
			categoryDao.add(category);
		} else {
			throw new CategoryException("�÷����Ѵ��ڣ���������ӣ�");
		}
	}

	/**
	 * ����cidɾ������
	 * @param cid
	 * @throws CategoryException 
	 */
	public void delete(String cid) throws CategoryException {
		if(bookDao.getCountByCid(cid) == 0){
			categoryDao.delete(cid);
		} else{
			throw new CategoryException("�÷�������ͼ�飬��ɾ���÷��������ͼ����ɾ�����࣡");
		}
	}

	/**
	 * ͨ��cid��ѯCategory
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	/**
	 * �޸ķ��������
	 * @param category
	 */
	public void modify(Category category) {
		categoryDao.modify(category);
	}
}
