package oa.document.service;

import java.util.List;

import oa.document.dao.DocumentDao;
import oa.document.domain.Document;

public class DocumentService {

	private DocumentDao documentDao = new DocumentDao();

	/**
	 * 根据documentNo从documentatble查找整个文档
	 * 
	 * @param document
	 */
	public Document findDocumentById(String documentNo) {
		return documentDao.findById(documentNo);
	}

	/**
	 * 根据documentNo从documenttable查找公文标题
	 * 
	 * @param document
	 */
	public String findHeaderById(String documentNo) {
		return documentDao.findHeaderById(documentNo);
	}

	/**
	 * 公文上传，就是想documentatble插入记录
	 * 
	 * @param document
	 */
	public void save(Document document) {
		documentDao.save(document);
	}

	/**
	 * 删除已上传的公文
	 * 
	 * @param document
	 */
	public void cancel(Document document) {
		documentDao.delete(document);
	}

	/**
	 * 更新公文
	 * 
	 * @param document
	 */
	public void update(Document document) {
		documentDao.update(document);
	}

	/**
	 * 根据公文标题模糊查询
	 * 
	 * @param documentHeader
	 * @return
	 */
	public List<Document> findByHeader(String documentHeader, String cardNo) {
		return documentDao.findByHeader(documentHeader, cardNo);
	}

	public String findNoByHeader(String documentHeader, String cardNo) {
		return documentDao.findNoByHeader(documentHeader, cardNo);
	}
	
	/**
	 * 返回的document的document为null
	 * @param documentNo
	 * @return
	 */
	public Document findByDocumentNo(String documentNo){
		return documentDao.findByDocumentNo(documentNo);
	}
	
	public Document findByDocumentHeader(String documentHeader){
		return documentDao.findByDocumentHeader(documentHeader);
	}

	// @Test
	// public void testFindById(){
	// Document document = findById("A332A38BB30B4DB89567B9A5EAF894F3");
	// System.out.println(document);
	// }
}
