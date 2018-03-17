package oa.check.service;

import java.util.Date;

import lqcUtils.CommonUtils;
import oa.document.dao.ModifyDocumentDao;
import oa.document.dao.StateDao;
import oa.document.domain.Document;
import oa.document.domain.ModifyDocument;
import oa.document.domain.State;
import oa.staff.domain.Staff;

public class CheckService {

	private StateDao stateDao = new StateDao();
	private ModifyDocumentDao modifyDocumentDao = new ModifyDocumentDao();

	/**
	 * 
	 * @param document
	 *            被审阅的公文
	 * @param staff
	 *            审阅人
	 * @param flag
	 *            是否通过
	 */
	public void check(Document document, Staff staff, boolean flag) {
		/*
		 * 0,未接收；1，未审阅；2，审阅通过；3，未通过； 如果审阅通过，修改公文状态为checked，并在文档修改记录表中添加修改记录
		 * 如果审阅不通过，修改公文状态为fail，并在文档修改记录表中添加修改记录
		 */
		State state = stateDao.findByDocumentHeader(document.getDocumentHeader()).get(0);
		if (flag) {
			state.setState("checked");
			stateDao.update(state);
		} else {
			state.setState("fail");
			stateDao.update(state);
		}
		ModifyDocument modifyDocument = new ModifyDocument();
		modifyDocument.setModifyDocumentRecordNo(CommonUtils.uuid());
		modifyDocument.setDocumentNo(document.getDocumentNo());
		modifyDocument.setDocumentHeader(document.getDocumentHeader());
		modifyDocument.setModifyerCardNo(staff.getCardNo());
		modifyDocument.setModifyTime(new Date());
		modifyDocumentDao.save(modifyDocument);
	}

	/**
	 * 判断公文是否通过，返回1表示暂时没有审阅，返回2表示不通过，返回3表示通过
	 * 
	 * @param document
	 * @return
	 */
	public int isChecked(Document document) {
		State state = stateDao.findByDocumentHeader(document.getDocumentHeader()).get(0);
		if (state.getState().equalsIgnoreCase("qicao")) {// 表示暂时没有审阅
			return 1;
		} else if (state.getState().equalsIgnoreCase("fail")) {// 表示没有通过
			return 2;
		} else {
			return 3;
		}
	}

	// @Test
	// public void testCheck(){
	//
	// Document document = new DraftDao().findByHeader("header").get(0);
	// Staff staff = new Staff();
	// staff.setCardNo("123456");
	// check(document,staff,false);
	// }

	// @Test
	// public void testIsChecked(){
	// Document document = new DraftDao().findByHeader("header").get(0);
	// int a = isChecked(document);
	// System.out.println(a);//2，表示没有通过
	// }
}
