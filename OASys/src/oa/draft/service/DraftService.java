package oa.draft.service;

import java.util.List;
import oa.document.domain.Document;
import oa.draft.dao.DraftDao;

/**
 * 公文起草业务层。
 */
public class DraftService {

	private DraftDao draftDao = new DraftDao();

	/**
	 * 公文起草，就是通过fileNo查找起草文件
	 * 
	 * @param document
	 */
	public Document findDocumentById(String documentNo) {
		return draftDao.findById(documentNo);
	}

	/**
	 * 公文起草，就是想documentatble插入记录
	 * 
	 * @param document
	 */
	public void draft(Document document) {
		draftDao.save(document);
	}

	/**
	 * 根据documentNo查找公文标题
	 * 
	 * @param documentNo
	 */
	public String findHeaderById(String documentNo) {
		return draftDao.findHeaderById(documentNo);
	}

	/**
	 * 删除刚起草的公文
	 * 
	 * @param document
	 */
	public void cancel(Document document) {
		draftDao.delete(document);
	}

	/**
	 * 更新公文
	 * 
	 * @param document
	 */
	public void update(Document document) {
		draftDao.update(document);
	}

	/**
	 * 根据公文标题模糊查询
	 * 
	 * @param documentHeader
	 * @return
	 */
	public List<Document> findByHeader(String documentHeader, String cardNo) {
		return draftDao.findByHeaderCardNo(documentHeader, cardNo);
	}

	public String findNoByHeader(String documentHeader, String cardNo) {
		return draftDao.findNoByHeader(documentHeader, cardNo);
	}
	
	/**
	 * 返回的document的document为null
	 * @param documentNo
	 * @return
	 */
	public Document findByDocumentNo(String documentNo){
		return draftDao.findByDocumentNo(documentNo);
	}
	
	public Document findByDocumentHeader(String documentHeader){
		return draftDao.findByDocumentHeader(documentHeader);
	}

	// @Test
	// public void testSave(){
	// File file = new File("E:/Eclipse/IO/ByteStream/demo2.txt");
	// Document document = new Document();
	// document.setDocumentNo(CommonUtils.uuid());
	// document.setDocumentHeader("测试模糊查询");
	// document.setDocument(file);
	// document.setDraftsmanCardNo("654321");
	// document.setIssuerCardNo("123456");
	// draft(document);
	// }

	// @Test
	// public void testDelete(){
	// Document document = new Document();
	// document.setDocumentNo("0D77368E9885484B8E6FB8B18F4A1972");
	// cancel(document);
	// }

	// @Test
	// public void testUpdate(){
	// File file = new File("E:/Eclipse/IO/ByteStream/demo2.txt");
	// Document document = new Document();
	// document.setDocumentNo("360E237329304C1B80197DAB61C76D21");
	// document.setDocumentHeader("测试更新");
	// document.setDocument(file);
	// document.setDraftsmanCardNo("88888888");
	// document.setIssuerCardNo("8888");
	// update(document);
	// }

	// @Test
	// public void testFindByHeader(){
	// List<Document> documentList = draftDao.findByHeader("有后");
	// System.out.println(documentList.size());
	// for (Document document : documentList) {
	// System.out.println(document.getDocumentHeader());
	// }
	//
	// }
}
