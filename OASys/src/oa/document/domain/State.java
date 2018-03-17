package oa.document.domain;

import java.util.Date;

/**
 * 修改公文的状态
 */
public class State {
	private String stateNo; // 修改状态记录的主键
	private String documentHeader; // 被修改状态的公文的标题
	private String cardNo; // 修改人的卡号
	private String state; // 修改的状态
	private Date time; // 修改时间
	private String sendCardNo;//发送人卡号

	public String getSendCardNo() {
		return sendCardNo;
	}

	public void setSendCardNo(String sendCardNo) {
		this.sendCardNo = sendCardNo;
	}

	public String getStateNo() {
		return stateNo;
	}

	public void setStateNo(String stateNo) {
		this.stateNo = stateNo;
	}

	public String getDocumentHeader() {
		return documentHeader;
	}

	public void setDocumentHeader(String documentHeader) {
		this.documentHeader = documentHeader;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "State [stateNo=" + stateNo + ", documentHeader=" + documentHeader + ", cardNo=" + cardNo + ", state="
				+ state + ", time=" + time + ", sendCardNo=" + sendCardNo + "]";
	}

}
