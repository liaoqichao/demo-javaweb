package update.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.transaction.annotation.Transactional;

import update.domain.ClientBean;
import update.service.ClientBeanService;

@Transactional
public class ClientAction {

	@Resource
	private ClientBeanService clientBeanService;
	
	private List<ClientBean> clientBeanList;
	private ClientBean cb;
	
	@JSON
	public ClientBean getCb() {
		return cb;
	}
	public void setCb(ClientBean cb) {
		this.cb = cb;
	}
	@JSON
	public List<ClientBean> getClientBeanList() {
		return clientBeanList;
	}
	public void setClientBeanList(List<ClientBean> clientBeanList) {
		this.clientBeanList = clientBeanList;
	}
	
	public String list(){
		List<ClientBean> clientBeanList = clientBeanService.list();
		this.clientBeanList = clientBeanList;
		return "showclientlist";
	}
	public String list2(){
		List<ClientBean> clientBeanList = clientBeanService.list();
		this.clientBeanList = clientBeanList;
		return "specificUpdate";
	}
	
	public String single(){
		ClientBean cb = clientBeanService.findByClientname(this.cb.getClientname());
		this.cb = cb;
//		return "searchClient";
		return "showclient";
	}
	public String single2(){
		ClientBean cb = clientBeanService.findByClientname(this.cb.getClientname());
		this.cb = cb;
		return "searchClient";
//		return "showclient";
	}
}
