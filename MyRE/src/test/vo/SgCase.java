package test.vo;

import java.math.BigDecimal;

public class SgCase{

	private String caseid;	//CASE号
	private String note;	//备注
	private String receivebanksign;	//卡接受银行签字
	private String swallowdeal;	//吞卡情况处理
	private String swallowdealtime;	//吞卡处理时间
	private String devicebrand;	//机器品牌
	private String ismoneyblock;	//是否卡钞
	private String arrivetime;	//到达时间
	private String subcode;	//支行
	private String starttime;	//出发时间
	private String areacode;	//所属组
	private String devicetype;	//机器型号
	private BigDecimal trafficcost;	//交通费用
	private String finishtime;	//完成时间
	private String ismonitornormal;	//监控录像是否正常
	private String othercontent;	//其它内容
	private String devicecode;	//机器制造号
	private String isswallowcard;	//是否吞卡
	private String bxtel;	//报修电话
	private String atmcode;	//终端号
	private int waterpaper;	//1:正常;2：更换
	private String reportpeople;	//反馈人
	private String bankcode;	//银行
	private String branchname;	//网点名称
	private String agentid;	//卡客户代理人身份证
	private String bxpeople;	//应急报修人
	private String agentsign;	//卡客户代理人签字
	private String ismoneynotout;	//是否取款未吐
	private String ownersign;	//卡客户本人签字
	private String ownerid;	//卡主身份证号码
	private String cardno;	//卡号
	private String contacttel;	//联系电话
	private String reporttime;	//反馈日期
	private String findproblem;	//发现问题
	private String bxtime;	//报修时间
	private String isgzhrecordnormal;	//冠字号记录是否正常
	private int idtype;	//证件类型 1：身份证 2 ：户口本 3:护照 4：其它
	private String cardbankcode;	//卡所属行
	private int casetype;	//10:巡检 20:应急 30:晨启 40:其他
	private String maintenancedate;	//维护日期
	private String receivebank;	//卡接受银行
	private String trafficid;	//交通工具 traffic_t 主键

	/**
	 * CASE号
	 */
	public String getCaseid() {
		return this.caseid == null ? "" : this.caseid.trim();
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	/**
	 * 备注
	 */
	public String getNote() {
		return this.note == null ? "" : this.note.trim();
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 卡接受银行签字
	 */
	public String getReceivebanksign() {
		return this.receivebanksign == null ? "" : this.receivebanksign.trim();
	}

	public void setReceivebanksign(String receivebanksign) {
		this.receivebanksign = receivebanksign;
	}

	/**
	 * 吞卡情况处理
	 */
	public String getSwallowdeal() {
		return this.swallowdeal == null ? "" : this.swallowdeal.trim();
	}

	public void setSwallowdeal(String swallowdeal) {
		this.swallowdeal = swallowdeal;
	}

	/**
	 * 吞卡处理时间
	 */
	public String getSwallowdealtime() {
		return this.swallowdealtime == null ? "" : this.swallowdealtime.trim();
	}

	public void setSwallowdealtime(String swallowdealtime) {
		this.swallowdealtime = swallowdealtime;
	}

	/**
	 * 机器品牌
	 */
	public String getDevicebrand() {
		return this.devicebrand == null ? "" : this.devicebrand.trim();
	}

	public void setDevicebrand(String devicebrand) {
		this.devicebrand = devicebrand;
	}

	/**
	 * 是否卡钞
	 */
	public String getIsmoneyblock() {
		return this.ismoneyblock == null ? "" : this.ismoneyblock.trim();
	}

	public void setIsmoneyblock(String ismoneyblock) {
		this.ismoneyblock = ismoneyblock;
	}

	/**
	 * 到达时间
	 */
	public String getArrivetime() {
		return this.arrivetime == null ? "" : this.arrivetime.trim();
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	/**
	 * 支行
	 */
	public String getSubcode() {
		return this.subcode == null ? "" : this.subcode.trim();
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	/**
	 * 出发时间
	 */
	public String getStarttime() {
		return this.starttime == null ? "" : this.starttime.trim();
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * 所属组
	 */
	public String getAreacode() {
		return this.areacode == null ? "" : this.areacode.trim();
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * 机器型号
	 */
	public String getDevicetype() {
		return this.devicetype == null ? "" : this.devicetype.trim();
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	/**
	 * 交通费用
	 */
	public BigDecimal getTrafficcost() {
		return this.trafficcost == null ? new BigDecimal("0") : this.trafficcost;
	}

	public void setTrafficcost(BigDecimal trafficcost) {
		this.trafficcost = trafficcost;
	}

	/**
	 * 完成时间
	 */
	public String getFinishtime() {
		return this.finishtime == null ? "" : this.finishtime.trim();
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	/**
	 * 监控录像是否正常
	 */
	public String getIsmonitornormal() {
		return this.ismonitornormal == null ? "" : this.ismonitornormal.trim();
	}

	public void setIsmonitornormal(String ismonitornormal) {
		this.ismonitornormal = ismonitornormal;
	}

	/**
	 * 其它内容
	 */
	public String getOthercontent() {
		return this.othercontent == null ? "" : this.othercontent.trim();
	}

	public void setOthercontent(String othercontent) {
		this.othercontent = othercontent;
	}

	/**
	 * 机器制造号
	 */
	public String getDevicecode() {
		return this.devicecode == null ? "" : this.devicecode.trim();
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	/**
	 * 是否吞卡
	 */
	public String getIsswallowcard() {
		return this.isswallowcard == null ? "" : this.isswallowcard.trim();
	}

	public void setIsswallowcard(String isswallowcard) {
		this.isswallowcard = isswallowcard;
	}

	/**
	 * 报修电话
	 */
	public String getBxtel() {
		return this.bxtel == null ? "" : this.bxtel.trim();
	}

	public void setBxtel(String bxtel) {
		this.bxtel = bxtel;
	}

	/**
	 * 终端号
	 */
	public String getAtmcode() {
		return this.atmcode == null ? "" : this.atmcode.trim();
	}

	public void setAtmcode(String atmcode) {
		this.atmcode = atmcode;
	}

	/**
	 * 1:正常;2：更换
	 */
	public int getWaterpaper() {
		return this.waterpaper;
	}

	public void setWaterpaper(int waterpaper) {
		this.waterpaper = waterpaper;
	}

	/**
	 * 反馈人
	 */
	public String getReportpeople() {
		return this.reportpeople == null ? "" : this.reportpeople.trim();
	}

	public void setReportpeople(String reportpeople) {
		this.reportpeople = reportpeople;
	}

	/**
	 * 银行
	 */
	public String getBankcode() {
		return this.bankcode == null ? "" : this.bankcode.trim();
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	/**
	 * 网点名称
	 */
	public String getBranchname() {
		return this.branchname == null ? "" : this.branchname.trim();
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	/**
	 * 卡客户代理人身份证
	 */
	public String getAgentid() {
		return this.agentid == null ? "" : this.agentid.trim();
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	/**
	 * 应急报修人
	 */
	public String getBxpeople() {
		return this.bxpeople == null ? "" : this.bxpeople.trim();
	}

	public void setBxpeople(String bxpeople) {
		this.bxpeople = bxpeople;
	}

	/**
	 * 卡客户代理人签字
	 */
	public String getAgentsign() {
		return this.agentsign == null ? "" : this.agentsign.trim();
	}

	public void setAgentsign(String agentsign) {
		this.agentsign = agentsign;
	}

	/**
	 * 是否取款未吐
	 */
	public String getIsmoneynotout() {
		return this.ismoneynotout == null ? "" : this.ismoneynotout.trim();
	}

	public void setIsmoneynotout(String ismoneynotout) {
		this.ismoneynotout = ismoneynotout;
	}

	/**
	 * 卡客户本人签字
	 */
	public String getOwnersign() {
		return this.ownersign == null ? "" : this.ownersign.trim();
	}

	public void setOwnersign(String ownersign) {
		this.ownersign = ownersign;
	}

	/**
	 * 卡主身份证号码
	 */
	public String getOwnerid() {
		return this.ownerid == null ? "" : this.ownerid.trim();
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	/**
	 * 卡号
	 */
	public String getCardno() {
		return this.cardno == null ? "" : this.cardno.trim();
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	/**
	 * 联系电话
	 */
	public String getContacttel() {
		return this.contacttel == null ? "" : this.contacttel.trim();
	}

	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}

	/**
	 * 反馈日期
	 */
	public String getReporttime() {
		return this.reporttime == null ? "" : this.reporttime.trim();
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	/**
	 * 发现问题
	 */
	public String getFindproblem() {
		return this.findproblem == null ? "" : this.findproblem.trim();
	}

	public void setFindproblem(String findproblem) {
		this.findproblem = findproblem;
	}

	/**
	 * 报修时间
	 */
	public String getBxtime() {
		return this.bxtime == null ? "" : this.bxtime.trim();
	}

	public void setBxtime(String bxtime) {
		this.bxtime = bxtime;
	}

	/**
	 * 冠字号记录是否正常
	 */
	public String getIsgzhrecordnormal() {
		return this.isgzhrecordnormal == null ? "" : this.isgzhrecordnormal.trim();
	}

	public void setIsgzhrecordnormal(String isgzhrecordnormal) {
		this.isgzhrecordnormal = isgzhrecordnormal;
	}

	/**
	 * 证件类型 1：身份证 2 ：户口本 3:护照 4：其它
	 */
	public int getIdtype() {
		return this.idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	/**
	 * 卡所属行
	 */
	public String getCardbankcode() {
		return this.cardbankcode == null ? "" : this.cardbankcode.trim();
	}

	public void setCardbankcode(String cardbankcode) {
		this.cardbankcode = cardbankcode;
	}

	/**
	 * 10:巡检 20:应急 30:晨启 40:其他
	 */
	public int getCasetype() {
		return this.casetype;
	}

	public void setCasetype(int casetype) {
		this.casetype = casetype;
	}

	/**
	 * 维护日期
	 */
	public String getMaintenancedate() {
		return this.maintenancedate == null ? "" : this.maintenancedate.trim();
	}

	public void setMaintenancedate(String maintenancedate) {
		this.maintenancedate = maintenancedate;
	}

	/**
	 * 卡接受银行
	 */
	public String getReceivebank() {
		return this.receivebank == null ? "" : this.receivebank.trim();
	}

	public void setReceivebank(String receivebank) {
		this.receivebank = receivebank;
	}

	/**
	 * 交通工具 traffic_t 主键
	 */
	public String getTrafficid() {
		return this.trafficid == null ? "" : this.trafficid.trim();
	}

	public void setTrafficid(String trafficid) {
		this.trafficid = trafficid;
	}

}
