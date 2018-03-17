package oa.document.domain;

import java.io.File;

/**
 * 公文
 */
public class Document {

	private String documentNo;
	private String documentHeader;
	private File document;
	private String suffix;// 获取文件的后缀
	private String documentName;// 文件的名字
	
	public String getDocumentName() {
		documentName = document.getName();
		return documentName;
	}


	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}



	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}



	public String getSuffix() {
		String fileName = this.document.getName();
		suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		return suffix;
	}

	private String draftsmanCardNo;// 强行不面向对象！
	private String issuerCardNo;// 强行不面向对象！

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

	public File getDocument() {
		return document;
	}

	public void setDocument(File document) {
		this.document = document;
	}

	public String getDraftsmanCardNo() {
		return draftsmanCardNo;
	}

	public void setDraftsmanCardNo(String draftsmanCardNo) {
		this.draftsmanCardNo = draftsmanCardNo;
	}

	public String getIssuerCardNo() {
		return issuerCardNo;
	}

	public void setIssuerCardNo(String issuerCardNo) {
		this.issuerCardNo = issuerCardNo;
	}

	@Override
	public String toString() {
		return "Document [documentNo=" + documentNo + ", documentHeader=" + documentHeader + ", document=" + document
				+ ", suffix=" + suffix + ", documentName=" + documentName + ", draftsmanCardNo=" + draftsmanCardNo
				+ ", issuerCardNo=" + issuerCardNo + "]";
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(String documentNo, String documentHeader, File document, String draftsmanCardNo,
			String issuerCardNo) {
		super();
		this.documentNo = documentNo;
		this.documentHeader = documentHeader;
		this.document = document;
		this.draftsmanCardNo = draftsmanCardNo;
		this.issuerCardNo = issuerCardNo;
	}

}
