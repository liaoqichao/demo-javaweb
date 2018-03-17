package update.service;

import java.util.List;

import update.domain.ClientBean;

public interface ClientBeanService {

	public List<ClientBean> list();
	public ClientBean findByClientname(String clientname);
	
}