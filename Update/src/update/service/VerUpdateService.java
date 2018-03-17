package update.service;

import update.domain.VerUpdate;

public interface VerUpdateService {

	void save(VerUpdate verUpdate);

	public VerUpdate findByVersion(String ver);
	

}