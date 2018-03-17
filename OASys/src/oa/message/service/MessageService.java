package oa.message.service;

import java.util.List;

import oa.message.dao.MessageDao;
import oa.message.domain.Message;

public class MessageService {

	private MessageDao messageDao = new MessageDao();

	/**
	 * 添加message
	 * 
	 * @param message
	 */
	public void save(Message message) {
		messageDao.save(message);
	}

	/**
	 * 删除message
	 * 
	 * @param messageNo
	 */
	public void delete(String messageNo) {
		messageDao.delete(messageNo);
	}

	/**
	 * 修改message
	 * 
	 * @param message
	 */
	public void update(Message message) {
		messageDao.update(message);
	}

	/**
	 * 根据主键查询信息
	 * 
	 * @param messageNo
	 * @return 信息
	 */
	public Message findByMessageNo(String messageNo) {
		return messageDao.findByNo(messageNo);
	}

	/**
	 * 根据发送人查询所有信息
	 * 
	 * @param sendCardNo
	 * @return 信息列表
	 */
	public List<Message> findBySendCardNo(String sendCardNo) {
		return messageDao.findBySendCardNo(sendCardNo);
	}

	/**
	 * 根据接收人查询所有信息
	 * 
	 * @param receiveCardNo
	 * @return 信息列表
	 */
	public List<Message> findByReceiveCardNo(String receiveCardNo) {
		return messageDao.findByReceiveCardNo(receiveCardNo);
	}
	
	/**
	 * 根据接收人状态查询所有信息
	 * 
	 * @param receiveCardNo
	 * @return 信息列表
	 */
	public List<Message> findByReceiveCardNoState(String receiveCardNo ,int state) {
		return messageDao.findByReceiveCardNoState(receiveCardNo,state);
	}
	
	/**
	 * 根据接收人，发送人查询所有信息
	 * @param sendName
	 * @param receiveCardNo
	 * @return 信息列表
	 */
	public List<Message> findByReceiveSendCardNo(String name,String receiveCardNo) {
		return messageDao.findByReceiveSendCardNo(name, receiveCardNo);
	}
}
