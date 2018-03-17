package update.service.impl;

import java.util.List;

import javax.annotation.Resource;

import update.dao.ClientBeanDao;
import update.domain.ClientBean;
import update.service.ClientBeanService;

//@Transactional
public class ClientBeanServiceImpl implements ClientBeanService {

	@Resource
	private ClientBeanDao clientBeanDao;
	
	public List<ClientBean> list(){
		List<ClientBean> clientBeanList = clientBeanDao.getList();
		return clientBeanList;
	}
	
	public ClientBean findByClientname(String clientname){
		return clientBeanDao.findByClientname(clientname);
	}
}
