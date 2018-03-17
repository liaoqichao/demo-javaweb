package test.vo;

import java.math.BigDecimal;

public class SgCase{

	private String caseid;	//CASE��
	private String note;	//��ע
	private String receivebanksign;	//����������ǩ��
	private String swallowdeal;	//�̿��������
	private String swallowdealtime;	//�̿�����ʱ��
	private String devicebrand;	//����Ʒ��
	private String ismoneyblock;	//�Ƿ񿨳�
	private String arrivetime;	//����ʱ��
	private String subcode;	//֧��
	private String starttime;	//����ʱ��
	private String areacode;	//������
	private String devicetype;	//�����ͺ�
	private BigDecimal trafficcost;	//��ͨ����
	private String finishtime;	//���ʱ��
	private String ismonitornormal;	//���¼���Ƿ�����
	private String othercontent;	//��������
	private String devicecode;	//���������
	private String isswallowcard;	//�Ƿ��̿�
	private String bxtel;	//���޵绰
	private String atmcode;	//�ն˺�
	private int waterpaper;	//1:����;2������
	private String reportpeople;	//������
	private String bankcode;	//����
	private String branchname;	//��������
	private String agentid;	//���ͻ����������֤
	private String bxpeople;	//Ӧ��������
	private String agentsign;	//���ͻ�������ǩ��
	private String ismoneynotout;	//�Ƿ�ȡ��δ��
	private String ownersign;	//���ͻ�����ǩ��
	private String ownerid;	//�������֤����
	private String cardno;	//����
	private String contacttel;	//��ϵ�绰
	private String reporttime;	//��������
	private String findproblem;	//��������
	private String bxtime;	//����ʱ��
	private String isgzhrecordnormal;	//���ֺż�¼�Ƿ�����
	private int idtype;	//֤������ 1�����֤ 2 �����ڱ� 3:���� 4������
	private String cardbankcode;	//��������
	private int casetype;	//10:Ѳ�� 20:Ӧ�� 30:���� 40:����
	private String maintenancedate;	//ά������
	private String receivebank;	//����������
	private String trafficid;	//��ͨ���� traffic_t ����

	/**
	 * CASE��
	 */
	public String getCaseid() {
		return this.caseid == null ? "" : this.caseid.trim();
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	/**
	 * ��ע
	 */
	public String getNote() {
		return this.note == null ? "" : this.note.trim();
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * ����������ǩ��
	 */
	public String getReceivebanksign() {
		return this.receivebanksign == null ? "" : this.receivebanksign.trim();
	}

	public void setReceivebanksign(String receivebanksign) {
		this.receivebanksign = receivebanksign;
	}

	/**
	 * �̿��������
	 */
	public String getSwallowdeal() {
		return this.swallowdeal == null ? "" : this.swallowdeal.trim();
	}

	public void setSwallowdeal(String swallowdeal) {
		this.swallowdeal = swallowdeal;
	}

	/**
	 * �̿�����ʱ��
	 */
	public String getSwallowdealtime() {
		return this.swallowdealtime == null ? "" : this.swallowdealtime.trim();
	}

	public void setSwallowdealtime(String swallowdealtime) {
		this.swallowdealtime = swallowdealtime;
	}

	/**
	 * ����Ʒ��
	 */
	public String getDevicebrand() {
		return this.devicebrand == null ? "" : this.devicebrand.trim();
	}

	public void setDevicebrand(String devicebrand) {
		this.devicebrand = devicebrand;
	}

	/**
	 * �Ƿ񿨳�
	 */
	public String getIsmoneyblock() {
		return this.ismoneyblock == null ? "" : this.ismoneyblock.trim();
	}

	public void setIsmoneyblock(String ismoneyblock) {
		this.ismoneyblock = ismoneyblock;
	}

	/**
	 * ����ʱ��
	 */
	public String getArrivetime() {
		return this.arrivetime == null ? "" : this.arrivetime.trim();
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	/**
	 * ֧��
	 */
	public String getSubcode() {
		return this.subcode == null ? "" : this.subcode.trim();
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	/**
	 * ����ʱ��
	 */
	public String getStarttime() {
		return this.starttime == null ? "" : this.starttime.trim();
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * ������
	 */
	public String getAreacode() {
		return this.areacode == null ? "" : this.areacode.trim();
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * �����ͺ�
	 */
	public String getDevicetype() {
		return this.devicetype == null ? "" : this.devicetype.trim();
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	/**
	 * ��ͨ����
	 */
	public BigDecimal getTrafficcost() {
		return this.trafficcost == null ? new BigDecimal("0") : this.trafficcost;
	}

	public void setTrafficcost(BigDecimal trafficcost) {
		this.trafficcost = trafficcost;
	}

	/**
	 * ���ʱ��
	 */
	public String getFinishtime() {
		return this.finishtime == null ? "" : this.finishtime.trim();
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	/**
	 * ���¼���Ƿ�����
	 */
	public String getIsmonitornormal() {
		return this.ismonitornormal == null ? "" : this.ismonitornormal.trim();
	}

	public void setIsmonitornormal(String ismonitornormal) {
		this.ismonitornormal = ismonitornormal;
	}

	/**
	 * ��������
	 */
	public String getOthercontent() {
		return this.othercontent == null ? "" : this.othercontent.trim();
	}

	public void setOthercontent(String othercontent) {
		this.othercontent = othercontent;
	}

	/**
	 * ���������
	 */
	public String getDevicecode() {
		return this.devicecode == null ? "" : this.devicecode.trim();
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	/**
	 * �Ƿ��̿�
	 */
	public String getIsswallowcard() {
		return this.isswallowcard == null ? "" : this.isswallowcard.trim();
	}

	public void setIsswallowcard(String isswallowcard) {
		this.isswallowcard = isswallowcard;
	}

	/**
	 * ���޵绰
	 */
	public String getBxtel() {
		return this.bxtel == null ? "" : this.bxtel.trim();
	}

	public void setBxtel(String bxtel) {
		this.bxtel = bxtel;
	}

	/**
	 * �ն˺�
	 */
	public String getAtmcode() {
		return this.atmcode == null ? "" : this.atmcode.trim();
	}

	public void setAtmcode(String atmcode) {
		this.atmcode = atmcode;
	}

	/**
	 * 1:����;2������
	 */
	public int getWaterpaper() {
		return this.waterpaper;
	}

	public void setWaterpaper(int waterpaper) {
		this.waterpaper = waterpaper;
	}

	/**
	 * ������
	 */
	public String getReportpeople() {
		return this.reportpeople == null ? "" : this.reportpeople.trim();
	}

	public void setReportpeople(String reportpeople) {
		this.reportpeople = reportpeople;
	}

	/**
	 * ����
	 */
	public String getBankcode() {
		return this.bankcode == null ? "" : this.bankcode.trim();
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	/**
	 * ��������
	 */
	public String getBranchname() {
		return this.branchname == null ? "" : this.branchname.trim();
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	/**
	 * ���ͻ����������֤
	 */
	public String getAgentid() {
		return this.agentid == null ? "" : this.agentid.trim();
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	/**
	 * Ӧ��������
	 */
	public String getBxpeople() {
		return this.bxpeople == null ? "" : this.bxpeople.trim();
	}

	public void setBxpeople(String bxpeople) {
		this.bxpeople = bxpeople;
	}

	/**
	 * ���ͻ�������ǩ��
	 */
	public String getAgentsign() {
		return this.agentsign == null ? "" : this.agentsign.trim();
	}

	public void setAgentsign(String agentsign) {
		this.agentsign = agentsign;
	}

	/**
	 * �Ƿ�ȡ��δ��
	 */
	public String getIsmoneynotout() {
		return this.ismoneynotout == null ? "" : this.ismoneynotout.trim();
	}

	public void setIsmoneynotout(String ismoneynotout) {
		this.ismoneynotout = ismoneynotout;
	}

	/**
	 * ���ͻ�����ǩ��
	 */
	public String getOwnersign() {
		return this.ownersign == null ? "" : this.ownersign.trim();
	}

	public void setOwnersign(String ownersign) {
		this.ownersign = ownersign;
	}

	/**
	 * �������֤����
	 */
	public String getOwnerid() {
		return this.ownerid == null ? "" : this.ownerid.trim();
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	/**
	 * ����
	 */
	public String getCardno() {
		return this.cardno == null ? "" : this.cardno.trim();
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	/**
	 * ��ϵ�绰
	 */
	public String getContacttel() {
		return this.contacttel == null ? "" : this.contacttel.trim();
	}

	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}

	/**
	 * ��������
	 */
	public String getReporttime() {
		return this.reporttime == null ? "" : this.reporttime.trim();
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	/**
	 * ��������
	 */
	public String getFindproblem() {
		return this.findproblem == null ? "" : this.findproblem.trim();
	}

	public void setFindproblem(String findproblem) {
		this.findproblem = findproblem;
	}

	/**
	 * ����ʱ��
	 */
	public String getBxtime() {
		return this.bxtime == null ? "" : this.bxtime.trim();
	}

	public void setBxtime(String bxtime) {
		this.bxtime = bxtime;
	}

	/**
	 * ���ֺż�¼�Ƿ�����
	 */
	public String getIsgzhrecordnormal() {
		return this.isgzhrecordnormal == null ? "" : this.isgzhrecordnormal.trim();
	}

	public void setIsgzhrecordnormal(String isgzhrecordnormal) {
		this.isgzhrecordnormal = isgzhrecordnormal;
	}

	/**
	 * ֤������ 1�����֤ 2 �����ڱ� 3:���� 4������
	 */
	public int getIdtype() {
		return this.idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	/**
	 * ��������
	 */
	public String getCardbankcode() {
		return this.cardbankcode == null ? "" : this.cardbankcode.trim();
	}

	public void setCardbankcode(String cardbankcode) {
		this.cardbankcode = cardbankcode;
	}

	/**
	 * 10:Ѳ�� 20:Ӧ�� 30:���� 40:����
	 */
	public int getCasetype() {
		return this.casetype;
	}

	public void setCasetype(int casetype) {
		this.casetype = casetype;
	}

	/**
	 * ά������
	 */
	public String getMaintenancedate() {
		return this.maintenancedate == null ? "" : this.maintenancedate.trim();
	}

	public void setMaintenancedate(String maintenancedate) {
		this.maintenancedate = maintenancedate;
	}

	/**
	 * ����������
	 */
	public String getReceivebank() {
		return this.receivebank == null ? "" : this.receivebank.trim();
	}

	public void setReceivebank(String receivebank) {
		this.receivebank = receivebank;
	}

	/**
	 * ��ͨ���� traffic_t ����
	 */
	public String getTrafficid() {
		return this.trafficid == null ? "" : this.trafficid.trim();
	}

	public void setTrafficid(String trafficid) {
		this.trafficid = trafficid;
	}

}
