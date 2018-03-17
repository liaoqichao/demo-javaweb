package test.com.zjft.part.business.express.reim.dao;

import java.sql.Connection;
import com.zjft.common.dao.CommonDao;
import java.sql.ResultSet;
import test.com.zjft.part.business.express.reim.vo.ExpressPriceReimMain;

public class ExpressPriceReimMainDao extends CommonDao<ExpressPriceReimMain,Long> {
	public ExpressPriceReimMainDao(Connection connection){
		super(connection);
	}

	public ExpressPriceReimMain createObject(ResultSet rs) throws Exception{
		ExpressPriceReimMain expresspricereimmain = new ExpressPriceReimMain();
		expresspricereimmain.setExpressreimmainid(rs.getLong("expressreimmainid"));
		expresspricereimmain.setTransportdays(rs.getBigDecimal("transportdays"));
		expresspricereimmain.setApplyer(rs.getString("applyer"));
		expresspricereimmain.setFromcity(rs.getString("fromcity"));
		expresspricereimmain.setCostnote(rs.getString("costnote"));
		expresspricereimmain.setFromdepot(rs.getString("fromdepot"));
		expresspricereimmain.setExpressno(rs.getString("expressno"));
		expresspricereimmain.setActualcost(rs.getBigDecimal("actualcost"));
		expresspricereimmain.setExpresscorp(rs.getLong("expresscorp"));
		expresspricereimmain.setWeightafterpack(rs.getBigDecimal("weightafterpack"));
		expresspricereimmain.setExpresstype(rs.getInt("expresstype"));
		expresspricereimmain.setFrompeople(rs.getString("frompeople"));
		expresspricereimmain.setTotalboxs(rs.getInt("totalboxs"));
		expresspricereimmain.setTodepot(rs.getString("todepot"));
		expresspricereimmain.setTopeople(rs.getString("topeople"));
		expresspricereimmain.setLinkconsignid(rs.getString("linkconsignid"));
		expresspricereimmain.setReceivedate(rs.getString("receivedate"));
		expresspricereimmain.setNetweight(rs.getBigDecimal("netweight"));
		expresspricereimmain.setSenddate(rs.getString("senddate"));
		expresspricereimmain.setApplystatus(rs.getInt("applystatus"));
		expresspricereimmain.setTocity(rs.getString("tocity"));
		expresspricereimmain.setApplytype(rs.getInt("applytype"));

		return expresspricereimmain;
	}

	public String getIdColumn() {
		return "expressreimmainid";
	}

	public String getTableName() {
		return "PART_EXPRESS_REIM_M";
	}

}
