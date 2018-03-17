package oa.document.domain;

import java.util.Date;

/**
 * 公文修改记录
 */
public class ModifyDocument {

	private String modifyDocumentRecordNo;
	private String documentNo;
	private String documentHeader;
	private String modifyerCardNo;
	private Date modifyTime;
	private String remarks;

	public String getModifyDocumentRecordNo() {
		return modifyDocumentRecordNo;
	}

	public void setModifyDocumentRecordNo(String modifyDocumentRecordNo) {
		this.modifyDocumentRecordNo = modifyDocumentRecordNo;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDocumentHeader() {
		return documentHeader;
	}

	public void setDocumentHeader(String documentHeader) {
		this.documentHeader = documentHeader;
	}

	public String getModifyerCardNo() {
		return modifyerCardNo;
	}

	public void setModifyerCardNo(String modifyerCardNo) {
		this.modifyerCardNo = modifyerCardNo;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "ModifyDocument [modifyDocumentRecordNo=" + modifyDocumentRecordNo + ", documentNo=" + documentNo
				+ ", documentHeader=" + documentHeader + ", modifyerCardNo=" + modifyerCardNo + ", modifyTime="
				+ modifyTime + ", remarks=" + remarks + "]";
	}

}
