package oa.message.domain;

import java.util.Date;

public class Message {

	private String messageNo;
	private String sendCardNo;
	private String sendName;
	private String receiveCardNo;
	private String receiveName;
	private int state; // 0未接收，1已接受
	private String message;
	private Date time;

	public String getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(String messageNo) {
		this.messageNo = messageNo;
	}

	public String getSendCardNo() {
		return sendCardNo;
	}

	public void setSendCardNo(String sendCardNo) {
		this.sendCardNo = sendCardNo;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getReceiveCardNo() {
		return receiveCardNo;
	}

	public void setReceiveCardNo(String receiveCardNo) {
		this.receiveCardNo = receiveCardNo;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", sendCardNo=" + sendCardNo + ", sendName=" + sendName
				+ ", receiveCardNo=" + receiveCardNo + ", receiveName=" + receiveName + ", state=" + state
				+ ", message=" + message + ", time=" + time + "]";
	}

}
