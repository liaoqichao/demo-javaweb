package oa.document.service;

import java.util.List;

import oa.document.dao.ModifyDocumentDao;
import oa.document.domain.ModifyDocument;

public class ModifyDocumentService {

	private ModifyDocumentDao modifyDocumentDao = new ModifyDocumentDao();

	/**
	 * 从ModifyDocumentrecord里，根据用户ModifyCardNo查询他所有处理的文件列表，直接用 List
	 * <ModifyDocument>格式就行，别带文件类型。 本身就是没带文件类型的...
	 * 
	 * @param modifyerCardNo
	 * @return
	 */
	public List<ModifyDocument> findByModifyerCardNo(String modifyerCardNo) {

		return modifyDocumentDao.findByModifyerCardNo(modifyerCardNo);
	}

	public void save(ModifyDocument modifyDocument) {
		modifyDocumentDao.save(modifyDocument);
	}

	public void update(ModifyDocument modifyDocument) {
		modifyDocumentDao.update(modifyDocument);
	}

	public ModifyDocument findByDocumentCardNo(String cardNo, String documentNo) {
		return modifyDocumentDao.findByDocumentCardNo(cardNo, documentNo);
	}

	public ModifyDocument findByDocumentHeaderCardNo(String cardNo, String documentHeader) {
		return modifyDocumentDao.findByDocumentHeaderCardNo(cardNo, documentHeader);
	}
}
