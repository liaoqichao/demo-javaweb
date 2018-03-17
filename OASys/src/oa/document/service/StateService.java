package oa.document.service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import oa.document.dao.DocumentDao;
import oa.document.dao.StateDao;
import oa.document.domain.Document;
import oa.document.domain.State;
import oa.staff.dao.StaffDao;
import oa.staff.domain.Staff;

public class StateService {

	private StateDao stateDao = new StateDao();
	private DocumentDao documentDao = new DocumentDao();
	private StaffDao staffDao=new StaffDao();
	/**
	 * 根据staff卡号查询跟他有关的全部文档列表
	 * 
	 * @param cardNo
	 * @return
	 */
	public List<State> findByStaffCardNo(String cardNo) {
		return stateDao.findByStaffCardNo(cardNo);
	}

	/**
	 * 查询该用户cardNo查询他还未接受，接收但未处理，已经处理的文档。
	 * 
	 * @param flag
	 *            未接受:unreceived，接收但为处理：unchecked，已经处理的文档：pass
	 * @param cardNo
	 * @return List<State>不是List<Document>
	 */
	public List<State> findByState(String flag, String cardNo) {
		return stateDao.findByState(flag, cardNo);
	}

	public State findByStateNo(String stateNo) {
		return stateDao.findByStateNo(stateNo);
	}

	public State findByHeaderCardNo(String header, String cardNo) {
		return stateDao.findByHeaderCardNo(header, cardNo);
	}

	public void update(State state) {
		stateDao.update(state);
	}

	public void save(State state) {
		stateDao.save(state);
	}
	
	public void delete(State state){
		stateDao.delete(state);
	}

	public List<State> findByDocumentHeader(String cardNo, String documentHeader) throws StateException {
		List<State> stateList = stateDao.findByDocumentHeader(cardNo, documentHeader);

		if (stateList.size() == 0) { // 列表为空
			throw new StateException("你无权限查看此文档");
		} else {
			return stateList;
		}
	}

	public List<State> findByDocumentHeader(String documentHeader) throws StateException {
		return stateDao.findByDocumentHeader(documentHeader);
	}
	/**
	 * 根据卡号查询1,2,6,7状态的公文
	 * @param cardNo
	 * @return
	 */
	public List<State> findByStateWithout05(String cardNo){
		List<State> stateList = new ArrayList<State>();
		List<State> state1 = stateDao.findByState("1", cardNo);
		List<State> state2 = stateDao.findByState("2", cardNo);
		List<State> state6 = stateDao.findByState("6", cardNo);
		List<State> state7 = stateDao.findByState("7", cardNo);
		stateList.addAll(state1);
		stateList.addAll(state2);
		stateList.addAll(state6);
		stateList.addAll(state7);
		return stateList;
	}
	
	public List<Map<String,Object>> findBySendCardNo(String sendCardNo){
		List<State> stateList = stateDao.findBySendCardNo(sendCardNo);
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Staff form=new Staff();
		Staff receive=null;
		Staff send=null;
		for (State state  : stateList) {
			Map<String,Object> map = new HashMap<String,Object>();
			
			form.setCardNo(state.getSendCardNo());
			send=staffDao.findById(form);
			map.put("send",send);		//发送人
			
			form.setCardNo(state.getCardNo());
			receive=staffDao.findById(form);
			map.put("receive",receive);				//接收人

			map.put("state",state);	//state
			
			Document document = documentDao.findByDocumentHeader(state.getDocumentHeader());//公文
			map.put("document", document);
			
			mapList.add(map);
			
		}
		for(Map<String,Object> mapt  : mapList){
			Staff test=(Staff)mapt.get("receive");
			System.out.println(test);
		}
		return mapList;
	}
}
