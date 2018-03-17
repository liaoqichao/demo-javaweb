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
	 * 添加分类。如果分类名称相同则不添加分类,如果有相同分类则抛出异常
	 * @param category
	 * @throws CategoryException 
	 */
	public void add(Category category) throws CategoryException {
		if(categoryDao.findByCname(category.getCname()) == null){
			categoryDao.add(category);
		} else {
			throw new CategoryException("该分类已存在，不用再添加！");
		}
	}

	/**
	 * 根据cid删除分类
	 * @param cid
	 * @throws CategoryException 
	 */
	public void delete(String cid) throws CategoryException {
		if(bookDao.getCountByCid(cid) == 0){
			categoryDao.delete(cid);
		} else{
			throw new CategoryException("该分类下有图书，请删除该分类的所有图书再删除该类！");
		}
	}

	/**
	 * 通过cid查询Category
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	/**
	 * 修改分类的名称
	 * @param category
	 */
	public void modify(Category category) {
		categoryDao.modify(category);
	}
}
