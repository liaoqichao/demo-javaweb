package update.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import update.dao.ClientBeanDao;
import update.dao.VerUpdateDao;
import update.dao.VersionDao;
import update.domain.ClientBean;
import update.domain.ClientMessage;
import update.domain.VerUpdate;
import update.domain.Version;
import update.server.ServerException;
import update.service.ServerService;

@Transactional
public class ServerServiceImpl implements ServerService {

	@Resource
	private VerUpdateDao verUpdateDao;
	@Resource
	private ClientBeanDao clientBeanDao;
	@Resource
	private VersionDao versionDao;
	//查询不用开启事务
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public VerUpdate findVerUpdateByVersion(String version) throws ServerException{
		VerUpdate verUpdate = verUpdateDao.findByVersion(version);
		if(verUpdate == null){
			throw new ServerException("没有找到对应版本更新信息");
		}
		return verUpdate;
	}
	
	public ClientBean toClientBean(Socket socket, ClientMessage cm) throws ServerException{
		ClientBean cb = new ClientBean();
		cb.setClientname(cm.getClientname());
		cb.setIp(socket.getInetAddress().getHostAddress());
		Version version = findVersionByVer(cm.getVersion());
		cb.set_version(version);
		return cb;
	}
	
	public void saveOrUpdateClientBean(ClientBean cb){
		/*
		 * cb 客户端发送过来的对象
		 * clientbean 从数据库查询的对象
		 * 
		 * -- 增
		 * 1.得到Document
		 * 2.得到根元素
		 * 3.使用参数cb转换为Element对象
		 * 4.把Element添加到根元素中
		 * 5.保存到Document
		 * 
		 * -- 查
		 * 1.得到Document元素
		 * 2.XPath查询
		 * 3.如果存在把需要的Element对象转换到ClientBean对象中
		 */
		try{
			String path = "src/server.xml";
			SAXReader reader = new SAXReader();
			Document document = reader.read(path);
			Element root = document.getRootElement();
			ClientBean clientBean = clientBeanDao.findByClientname(cb.getClientname());
			
			// 为什么用clientname查找不用id查找。因为id为空时空字符串。就相当于查找XML中id=""的clientbean标签。不合理
			// 而clientname不是为空的。 -- 最终原因在于现在的cb还是临时状态，不一定有id（主键）。
			Element ele = (Element)document.selectSingleNode("//clientbean[@clientname='"+cb.getClientname()+"']");
			
//			Element ele = (Element)document.selectSingleNode("//clientbean[@id='"+cb.getId()+"']");
			if(clientBean == null && ele == null){		//不存在则插入
				System.out.println("不存在则插入");
				clientBeanDao.save(cb);
				// cb已经持久化
				// 在server.xml中保存clientBean。不能设置ID，空指针异常
				Element eleInsert = root.addElement("clientbean"); // 创建元素
				eleInsert.addAttribute("id", cb.getId().toString());	// 设置属性
				eleInsert.addAttribute("clientname", cb.getClientname());
				eleInsert.addAttribute("ip", cb.getIp());
				eleInsert.addAttribute("version", cb.get_version().getVer());
				/*
				 * 保存文档
				 */
				// 创建输出格式化器
				OutputFormat format = new OutputFormat("\t",true);//第一个参数表示用\t来缩进，第二个参数表示使用换行
				format.setTrimText(true);//清空原有格式（换行和缩进）
				// 创建XMLWriter，FileWriter没有编码问题，需要用OutputStreamWriter来设置编码
				XMLWriter writer = new XMLWriter(
						new OutputStreamWriter(
								new FileOutputStream(path),"UTF-8"),format);
				// 保存document对象
				writer.write(document);
				writer.close();
			} else{ //存在则更新
				System.out.println("存在则更新");
				//在事务内，也在session内，所以clientbean已经是持久化状态。
				clientBean.set_version(cb.get_version());
				clientBean.setIp(cb.getIp());
				
				// 在server.xml中修改clientBean
				
				/*
				 * 1.根据id获取元素
				 * 2.获取其属性对象
				 * 3.修改其属性值
				 * 4.保存文档
				 */
				// 这里getClientname用cb或者clientbean都可以，因为找出来了是一样的。
				Element eleModify = (Element) root.selectSingleNode("//clientbean[@clientname='"+clientBean.getClientname()+"']");
				Attribute ipAtt = eleModify.attribute("ip"); 
				ipAtt.setValue(clientBean.getIp());
				
				// 创建输出格式化器
				OutputFormat format = new OutputFormat("\t",true);//第一个参数表示用\t来缩进，第二个参数表示使用换行
				format.setTrimText(true);//清空原有格式（换行和缩进）
				// 创建XMLWriter，FileWriter没有编码问题，需要用OutputStreamWriter来设置编码
				XMLWriter writer = new XMLWriter(
						new OutputStreamWriter(
								new FileOutputStream(path),"UTF-8"),format);
				// 保存document对象
				writer.write(document);
				writer.close();
			}
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Version findVersionByVer(String ver) throws ServerException{
		Version version = versionDao.findByVersion(ver);
		if(version == null){
			throw new ServerException("没有找到对应版本号");
		}
		return version;
	}
	
	public String getServerVersion(ClientBean cb){
		String path = "src/server.xml";
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(path);
			Element ele = (Element) document.selectSingleNode("//clientbean[@clientname='"+cb.getClientname()+"']");
			Attribute versionAtt = ele.attribute("version");
			return versionAtt.getStringValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void modifyVersion(ClientBean clientBean, String version) {
		try {
			String path = "src/server.xml";
			SAXReader reader = new SAXReader();
			Document document = reader.read("src/server.xml");
			Element ele = (Element) document.selectSingleNode("//clientbean[@clientname='"+clientBean.getClientname()+"']");
			Attribute versionAtt = ele.attribute("version");
			versionAtt.setValue(version);
			
			OutputFormat format = new OutputFormat("\t",true);//第一个参数表示用\t来缩进，第二个参数表示使用换行
			format.setTrimText(true);//清空原有格式（换行和缩进）
			// 创建XMLWriter，FileWriter没有编码问题，需要用OutputStreamWriter来设置编码
			XMLWriter writer = new XMLWriter(
					new OutputStreamWriter(
							new FileOutputStream(path),"UTF-8"),format);
			// 保存document对象
			writer.write(document);
			writer.close();
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}

	/* (non-Javadoc)
	 * @see update.service.impl.ServerService#modifyVersion(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void modifyVersion(String version) {
		// .. 改成用XML存储配置信息
		 try {
			String path = "src/server.xml";
			SAXReader reader = new SAXReader();
			Document document = reader.read("src/server.xml");
			// 修改
			List<Node> nodeList = document.selectNodes("//clientbean");
			for (Node node : nodeList) {
				Element ele = (Element)node;
				Attribute versionAtt = ele.attribute("version");
				versionAtt.setValue(version);
			}
			
			// 保存
			OutputFormat format = new OutputFormat("\t",true);//第一个参数表示用\t来缩进，第二个参数表示使用换行
			format.setTrimText(true);//清空原有格式（换行和缩进）
			// 创建XMLWriter，FileWriter没有编码问题，需要用OutputStreamWriter来设置编码
			XMLWriter writer = new XMLWriter(
					new OutputStreamWriter(
							new FileOutputStream(path),"UTF-8"),format);
			// 保存document对象
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ClientMessage getClientMessage(InputStream in ){
		ClientMessage cm = null;
		try {
			ObjectInputStream objin = new ObjectInputStream(in);
			cm = (ClientMessage)objin.readObject();
//			System.out.println(cm);//ClientMessage [clientname=null, version=v1.3, manage=true]
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	/**
	 * 获取版本列表
	 * @return
	 */
	public List<Version> getVersionList(){
		return versionDao.getList();
	}

	@Override
	public Version getNextVersion(String ver) {
		/*
		 * 1. 获取版本列表
		 * 2. 获取传入版本号的下一个版本，没有则返回null
		 */
		// 1.
		List<Version> versionList = getVersionList();
		// 2.
		int index = 0;
		for (Version version : versionList) {
			if(version.getVer().equalsIgnoreCase(ver)){
				break;
			}
			index++;
		}
		if(index == versionList.size()-1){
			System.out.println("当前版本为最新版本");
			return null; 
		} else if(index < versionList.size()-1){
			return versionList.get(index+1); // 返回下一个Version
		} else{
			System.out.println("其他情况...");
			return null;
		}
	}
	
}
