package service;

import java.util.List;

import dao.AddressDao;
import dao.DistrictDao;
import domain.Address;
import domain.District;

/**
 * 
 */
public class AddressService {
	private AddressDao addressDao = new AddressDao();
	private DistrictDao districtDao = new DistrictDao();
	
	public List<Address> queryByDistrict(int did){
		District district = districtDao.queryByDid(did);
		return addressDao.qureyByDistrict(district);
	}

	public Address queryByAid(int aid) {
		return addressDao.queryByAid(aid);
	}
}
