package update.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import update.dao.VerUpdateDao;
import update.dao.VersionDao;
import update.domain.VerUpdate;
import update.service.VerUpdateService;

@Transactional
public class VerUpdateServiceImpl implements VerUpdateService {
	
	@Resource
	private VerUpdateDao verUpdateDao;
	@Resource
	private VersionDao versionDao;

	public void save(VerUpdate verUpdate) {
		verUpdateDao.save(verUpdate);
	}
	
	public VerUpdate findByVersion(String ver){
		return verUpdateDao.findByVersion(ver);
	}
	
}
