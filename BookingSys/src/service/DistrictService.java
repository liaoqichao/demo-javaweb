package service;

import java.util.List;

import dao.DistrictDao;
import domain.District;

/**
 *	û��ҵ��
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
