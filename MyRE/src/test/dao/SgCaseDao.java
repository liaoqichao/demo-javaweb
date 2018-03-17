package test.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import com.zjft.common.dao.CommonDao;
import com.zjft.common.service.Tool;
import test.vo.SgCase;

public class SgCaseDao extends CommonDao<String,SgCase> {
	public SgCaseDao(Connection connection){
		super(connection);
	}

	public SgCase createObject(ResultSet rs) {
		SgCase sgcase = new SgCase();
		sgcase.setCaseid(rs.getString("caseid"));
		sgcase.setNote(rs.getString("note"));
		sgcase.setReceivebanksign(rs.getString("receivebanksign"));
		sgcase.setSwallowdeal(rs.getString("swallowdeal"));
		sgcase.setSwallowdealtime(rs.getString("swallowdealtime"));
		sgcase.setDevicebrand(rs.getString("devicebrand"));
		sgcase.setIsmoneyblock(rs.getString("ismoneyblock"));
		sgcase.setArrivetime(rs.getString("arrivetime"));
		sgcase.setSubcode(rs.getString("subcode"));
		sgcase.setStarttime(rs.getString("starttime"));
		sgcase.setAreacode(rs.getString("areacode"));
		sgcase.setDevicetype(rs.getString("devicetype"));
		sgcase.setTrafficcost(rs.getBigDecimal("trafficcost"));
		sgcase.setFinishtime(rs.getString("finishtime"));
		sgcase.setIsmonitornormal(rs.getString("ismonitornormal"));
		sgcase.setOthercontent(rs.getString("othercontent"));
		sgcase.setDevicecode(rs.getString("devicecode"));
		sgcase.setIsswallowcard(rs.getString("isswallowcard"));
		sgcase.setBxtel(rs.getString("bxtel"));
		sgcase.setAtmcode(rs.getString("atmcode"));
		sgcase.setWaterpaper(rs.getInt("waterpaper"));
		sgcase.setReportpeople(rs.getString("reportpeople"));
		sgcase.setBankcode(rs.getString("bankcode"));
		sgcase.setBranchname(rs.getString("branchname"));
		sgcase.setAgentid(rs.getString("agentid"));
		sgcase.setBxpeople(rs.getString("bxpeople"));
		sgcase.setAgentsign(rs.getString("agentsign"));
		sgcase.setIsmoneynotout(rs.getString("ismoneynotout"));
		sgcase.setOwnersign(rs.getString("ownersign"));
		sgcase.setOwnerid(rs.getString("ownerid"));
		sgcase.setCardno(rs.getString("cardno"));
		sgcase.setContacttel(rs.getString("contacttel"));
		sgcase.setReporttime(rs.getString("reporttime"));
		sgcase.setFindproblem(rs.getString("findproblem"));
		sgcase.setBxtime(rs.getString("bxtime"));
		sgcase.setIsgzhrecordnormal(rs.getString("isgzhrecordnormal"));
		sgcase.setIdtype(rs.getInt("idtype"));
		sgcase.setCardbankcode(rs.getString("cardbankcode"));
		sgcase.setCasetype(rs.getInt("casetype"));
		sgcase.setMaintenancedate(rs.getString("maintenancedate"));
		sgcase.setReceivebank(rs.getString("receivebank"));
		sgcase.setTrafficid(rs.getString("trafficid"));

		return sgcase;
	}

	public String getIdColumn() {
		return "caseid";
	}

	public String getTableName() {
		return "sg_case";
	}

}
