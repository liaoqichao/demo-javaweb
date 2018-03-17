package update.dao;

import java.util.List;

import update.domain.ClientBean;

public interface ClientBeanDao {

	void save(ClientBean clientBean);

	void delete(Integer id);

	void update(ClientBean clientBean);

	ClientBean get(Integer id);

	List<ClientBean> getList();
	
	public ClientBean findByClientname(String clientname);

}