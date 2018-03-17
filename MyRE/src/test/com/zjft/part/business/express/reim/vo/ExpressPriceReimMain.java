package test.com.zjft.part.business.express.reim.vo;

import java.math.BigDecimal;

public class ExpressPriceReimMain{

	private long expressreimmainid;	//主键
	private BigDecimal transportdays;	//运输天数
	private String applyer;	//领款人
	private String fromcity;	//发货城市
	private String costnote;	//费用说明
	private String fromdepot;	//发货服务站
	private String expressno;	//快递单号
	private BigDecimal actualcost;	//实际费用
	private long expresscorp;	//快递公司id
	private BigDecimal weightafterpack;	//备件包装后总质量
	private int expresstype;	//快递方式，10=急件，20=快件，30=慢件
	private String frompeople;	//发货人
	private int totalboxs;	//总箱数
	private String todepot;	//收货服务站
	private String topeople;	//收货人
	private String linkconsignid;	//关联发货单
	private String receivedate;	//收货日期
	private BigDecimal netweight;	//备件净重量
	private String senddate;	//发货日期
	private int applystatus;	//报销状态，10=未发起，20=报销中，30=报销完成
	private String tocity;	//收货城市
	private int applytype;	//邮寄类型，10=备件，20=文件，30=其他

	/**
	 * 主键
	 */
	public long getExpressreimmainid() {
		return this.expressreimmainid;
	}

	public void setExpressreimmainid(long expressreimmainid) {
		this.expressreimmainid = expressreimmainid;
	}

	/**
	 * 运输天数
	 */
	public BigDecimal getTransportdays() {
		return this.transportdays == null ? new BigDecimal("0") : this.transportdays;
	}

	public void setTransportdays(BigDecimal transportdays) {
		this.transportdays = transportdays;
	}

	/**
	 * 领款人
	 */
	public String getApplyer() {
		return this.applyer == null ? "" : this.applyer.trim();
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	/**
	 * 发货城市
	 */
	public String getFromcity() {
		return this.fromcity == null ? "" : this.fromcity.trim();
	}

	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}

	/**
	 * 费用说明
	 */
	public String getCostnote() {
		return this.costnote == null ? "" : this.costnote.trim();
	}

	public void setCostnote(String costnote) {
		this.costnote = costnote;
	}

	/**
	 * 发货服务站
	 */
	public String getFromdepot() {
		return this.fromdepot == null ? "" : this.fromdepot.trim();
	}

	public void setFromdepot(String fromdepot) {
		this.fromdepot = fromdepot;
	}

	/**
	 * 快递单号
	 */
	public String getExpressno() {
		return this.expressno == null ? "" : this.expressno.trim();
	}

	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}

	/**
	 * 实际费用
	 */
	public BigDecimal getActualcost() {
		return this.actualcost == null ? new BigDecimal("0") : this.actualcost;
	}

	public void setActualcost(BigDecimal actualcost) {
		this.actualcost = actualcost;
	}

	/**
	 * 快递公司id
	 */
	public long getExpresscorp() {
		return this.expresscorp;
	}

	public void setExpresscorp(long expresscorp) {
		this.expresscorp = expresscorp;
	}

	/**
	 * 备件包装后总质量
	 */
	public BigDecimal getWeightafterpack() {
		return this.weightafterpack == null ? new BigDecimal("0") : this.weightafterpack;
	}

	public void setWeightafterpack(BigDecimal weightafterpack) {
		this.weightafterpack = weightafterpack;
	}

	/**
	 * 快递方式，10=急件，20=快件，30=慢件
	 */
	public int getExpresstype() {
		return this.expresstype;
	}

	public void setExpresstype(int expresstype) {
		this.expresstype = expresstype;
	}

	/**
	 * 发货人
	 */
	public String getFrompeople() {
		return this.frompeople == null ? "" : this.frompeople.trim();
	}

	public void setFrompeople(String frompeople) {
		this.frompeople = frompeople;
	}

	/**
	 * 总箱数
	 */
	public int getTotalboxs() {
		return this.totalboxs;
	}

	public void setTotalboxs(int totalboxs) {
		this.totalboxs = totalboxs;
	}

	/**
	 * 收货服务站
	 */
	public String getTodepot() {
		return this.todepot == null ? "" : this.todepot.trim();
	}

	public void setTodepot(String todepot) {
		this.todepot = todepot;
	}

	/**
	 * 收货人
	 */
	public String getTopeople() {
		return this.topeople == null ? "" : this.topeople.trim();
	}

	public void setTopeople(String topeople) {
		this.topeople = topeople;
	}

	/**
	 * 关联发货单
	 */
	public String getLinkconsignid() {
		return this.linkconsignid == null ? "" : this.linkconsignid.trim();
	}

	public void setLinkconsignid(String linkconsignid) {
		this.linkconsignid = linkconsignid;
	}

	/**
	 * 收货日期
	 */
	public String getReceivedate() {
		return this.receivedate == null ? "" : this.receivedate.trim();
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	/**
	 * 备件净重量
	 */
	public BigDecimal getNetweight() {
		return this.netweight == null ? new BigDecimal("0") : this.netweight;
	}

	public void setNetweight(BigDecimal netweight) {
		this.netweight = netweight;
	}

	/**
	 * 发货日期
	 */
	public String getSenddate() {
		return this.senddate == null ? "" : this.senddate.trim();
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	/**
	 * 报销状态，10=未发起，20=报销中，30=报销完成
	 */
	public int getApplystatus() {
		return this.applystatus;
	}

	public void setApplystatus(int applystatus) {
		this.applystatus = applystatus;
	}

	/**
	 * 收货城市
	 */
	public String getTocity() {
		return this.tocity == null ? "" : this.tocity.trim();
	}

	public void setTocity(String tocity) {
		this.tocity = tocity;
	}

	/**
	 * 邮寄类型，10=备件，20=文件，30=其他
	 */
	public int getApplytype() {
		return this.applytype;
	}

	public void setApplytype(int applytype) {
		this.applytype = applytype;
	}

}
