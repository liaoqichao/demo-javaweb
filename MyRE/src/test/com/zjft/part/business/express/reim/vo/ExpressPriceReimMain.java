package test.com.zjft.part.business.express.reim.vo;

import java.math.BigDecimal;

public class ExpressPriceReimMain{

	private long expressreimmainid;	//����
	private BigDecimal transportdays;	//��������
	private String applyer;	//�����
	private String fromcity;	//��������
	private String costnote;	//����˵��
	private String fromdepot;	//��������վ
	private String expressno;	//��ݵ���
	private BigDecimal actualcost;	//ʵ�ʷ���
	private long expresscorp;	//��ݹ�˾id
	private BigDecimal weightafterpack;	//������װ��������
	private int expresstype;	//��ݷ�ʽ��10=������20=�����30=����
	private String frompeople;	//������
	private int totalboxs;	//������
	private String todepot;	//�ջ�����վ
	private String topeople;	//�ջ���
	private String linkconsignid;	//����������
	private String receivedate;	//�ջ�����
	private BigDecimal netweight;	//����������
	private String senddate;	//��������
	private int applystatus;	//����״̬��10=δ����20=�����У�30=�������
	private String tocity;	//�ջ�����
	private int applytype;	//�ʼ����ͣ�10=������20=�ļ���30=����

	/**
	 * ����
	 */
	public long getExpressreimmainid() {
		return this.expressreimmainid;
	}

	public void setExpressreimmainid(long expressreimmainid) {
		this.expressreimmainid = expressreimmainid;
	}

	/**
	 * ��������
	 */
	public BigDecimal getTransportdays() {
		return this.transportdays == null ? new BigDecimal("0") : this.transportdays;
	}

	public void setTransportdays(BigDecimal transportdays) {
		this.transportdays = transportdays;
	}

	/**
	 * �����
	 */
	public String getApplyer() {
		return this.applyer == null ? "" : this.applyer.trim();
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	/**
	 * ��������
	 */
	public String getFromcity() {
		return this.fromcity == null ? "" : this.fromcity.trim();
	}

	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}

	/**
	 * ����˵��
	 */
	public String getCostnote() {
		return this.costnote == null ? "" : this.costnote.trim();
	}

	public void setCostnote(String costnote) {
		this.costnote = costnote;
	}

	/**
	 * ��������վ
	 */
	public String getFromdepot() {
		return this.fromdepot == null ? "" : this.fromdepot.trim();
	}

	public void setFromdepot(String fromdepot) {
		this.fromdepot = fromdepot;
	}

	/**
	 * ��ݵ���
	 */
	public String getExpressno() {
		return this.expressno == null ? "" : this.expressno.trim();
	}

	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}

	/**
	 * ʵ�ʷ���
	 */
	public BigDecimal getActualcost() {
		return this.actualcost == null ? new BigDecimal("0") : this.actualcost;
	}

	public void setActualcost(BigDecimal actualcost) {
		this.actualcost = actualcost;
	}

	/**
	 * ��ݹ�˾id
	 */
	public long getExpresscorp() {
		return this.expresscorp;
	}

	public void setExpresscorp(long expresscorp) {
		this.expresscorp = expresscorp;
	}

	/**
	 * ������װ��������
	 */
	public BigDecimal getWeightafterpack() {
		return this.weightafterpack == null ? new BigDecimal("0") : this.weightafterpack;
	}

	public void setWeightafterpack(BigDecimal weightafterpack) {
		this.weightafterpack = weightafterpack;
	}

	/**
	 * ��ݷ�ʽ��10=������20=�����30=����
	 */
	public int getExpresstype() {
		return this.expresstype;
	}

	public void setExpresstype(int expresstype) {
		this.expresstype = expresstype;
	}

	/**
	 * ������
	 */
	public String getFrompeople() {
		return this.frompeople == null ? "" : this.frompeople.trim();
	}

	public void setFrompeople(String frompeople) {
		this.frompeople = frompeople;
	}

	/**
	 * ������
	 */
	public int getTotalboxs() {
		return this.totalboxs;
	}

	public void setTotalboxs(int totalboxs) {
		this.totalboxs = totalboxs;
	}

	/**
	 * �ջ�����վ
	 */
	public String getTodepot() {
		return this.todepot == null ? "" : this.todepot.trim();
	}

	public void setTodepot(String todepot) {
		this.todepot = todepot;
	}

	/**
	 * �ջ���
	 */
	public String getTopeople() {
		return this.topeople == null ? "" : this.topeople.trim();
	}

	public void setTopeople(String topeople) {
		this.topeople = topeople;
	}

	/**
	 * ����������
	 */
	public String getLinkconsignid() {
		return this.linkconsignid == null ? "" : this.linkconsignid.trim();
	}

	public void setLinkconsignid(String linkconsignid) {
		this.linkconsignid = linkconsignid;
	}

	/**
	 * �ջ�����
	 */
	public String getReceivedate() {
		return this.receivedate == null ? "" : this.receivedate.trim();
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	/**
	 * ����������
	 */
	public BigDecimal getNetweight() {
		return this.netweight == null ? new BigDecimal("0") : this.netweight;
	}

	public void setNetweight(BigDecimal netweight) {
		this.netweight = netweight;
	}

	/**
	 * ��������
	 */
	public String getSenddate() {
		return this.senddate == null ? "" : this.senddate.trim();
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	/**
	 * ����״̬��10=δ����20=�����У�30=�������
	 */
	public int getApplystatus() {
		return this.applystatus;
	}

	public void setApplystatus(int applystatus) {
		this.applystatus = applystatus;
	}

	/**
	 * �ջ�����
	 */
	public String getTocity() {
		return this.tocity == null ? "" : this.tocity.trim();
	}

	public void setTocity(String tocity) {
		this.tocity = tocity;
	}

	/**
	 * �ʼ����ͣ�10=������20=�ļ���30=����
	 */
	public int getApplytype() {
		return this.applytype;
	}

	public void setApplytype(int applytype) {
		this.applytype = applytype;
	}

}
