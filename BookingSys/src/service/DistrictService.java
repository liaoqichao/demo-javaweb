package service;

import java.util.List;

import dao.DistrictDao;
import domain.District;

/**
 *	没有业务
 */
public class DistrictService {
	private DistrictDao districtDao = new DistrictDao();
	
	public District queryByName(int did){
		return districtDao.queryByDid(did);
	}
	public List<District> queryAll(){
		List<District> list = districtDao.queryAll();
		return list;
	}

	

	
}
