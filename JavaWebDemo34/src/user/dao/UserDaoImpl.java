package user.dao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import user.domain.User;

/**
 * ������
 * @author Administrator
 * �޸ģ�	1.��UserDao->UserDaoImpl (implements UserDao)
 * 	   	2.UserService��private UserDao userDao = DaoFactory.getUserDao();
 * 			ԭ����private UserDao userDao = new UserDao();
 *
 */
public class UserDaoImpl implements UserDao {
	private String path = "E:\\Eclipse\\db\\JavaWebDemo34\\users.xml";//����xml��·��
	/**
	 * ���û�����ѯ
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		//����Ҫ��dom4j����
		/*
		 * 1.�õ�document
		 * 2.xpath��ѯ
		 * 3.У���ѯ����Ƿ�Ϊnull,���Ϊnull������null
		 * 4.�����Ϊnull����Ҫ��Element��װ��user������
		 */
		//1.����������
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(path);
			//2.ͨ��xpath�õ�element
			//��ѯ��ǩ��Ϊuser,������Ϊusername,����ֵΪusername(����)�Ľڵ�(node),ǿתΪԪ�ء�
			Element ele = (Element)document.selectSingleNode("//user[@username='" +username+ "']");//˫б�ܱ�ʾû��Ҫ��������ѯ
			//3.
			if(ele == null){
				return null;
			}
			//4.
			User user = new User();
			String attUsername = ele.attributeValue("username");//�õ�ele(user��ǩ)�µ�username��ֵ
			user.setUsername(attUsername);
			String password = ele.attributeValue("password");
			user.setPassword(password);
			String attStatus = ele.attributeValue("status");
			user.setStatus(attStatus);
			return user;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * ����û�
	 * @param user
	 */
	public void addUser(User user){
		//Ҳ��dom4j����
		/*
		 * 1.�õ�document
		 * 2.ͨ��document�õ�rootԪ��<users>
		 * 3.��root�����Ԫ��
		 * 4.ΪuserEle��������(����ֵ��user��)
		 * 5.����document(��дXML)
		 */
		
		//1.�õ�SAXReader,�õ�document,����root
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(path);
			//2.
			Element root = document.getRootElement();
			
			//3.ͨ����Ԫ�ش�����Ԫ��
			Element userEle = root.addElement("user");
			//4.ΪuserEle��������
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			userEle.addAttribute("status", user.getStatus());
			
			//5.��дXML
//			//��ʽ
//			OutputFormat format = new OutputFormat("\t",true);//�Ƿ�ʹ������,�Ƿ���
//			format.setTrimText(true);//���ԭ�и�ʽ(���к�����)
			//�����ĵ�
			XMLWriter writer;
			//��Ϊֱ����FileWriter����ϵͳĬ�ϱ���(GBK),����Ҫͨ��OutpuStreamWriter�����ñ���
			writer = new XMLWriter(
					new OutputStreamWriter(new FileOutputStream(path),"UTF-8"), 
					OutputFormat.createPrettyPrint());
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
