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
 * 数据类
 * @author Administrator
 * 修改：	1.把UserDao->UserDaoImpl (implements UserDao)
 * 	   	2.UserService中private UserDao userDao = DaoFactory.getUserDao();
 * 			原来是private UserDao userDao = new UserDao();
 *
 */
public class UserDaoImpl implements UserDao {
	private String path = "E:\\Eclipse\\db\\JavaWebDemo34\\users.xml";//访问xml的路径
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		//这里要用dom4j代码
		/*
		 * 1.得到document
		 * 2.xpath查询
		 * 3.校验查询结果是否为null,如果为null，返回null
		 * 4.如果不为null，需要把Element封装到user对象中
		 */
		//1.创建解析器
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(path);
			//2.通过xpath得到element
			//查询标签名为user,属性名为username,属性值为username(参数)的节点(node),强转为元素。
			Element ele = (Element)document.selectSingleNode("//user[@username='" +username+ "']");//双斜杠表示没有要求的深入查询
			//3.
			if(ele == null){
				return null;
			}
			//4.
			User user = new User();
			String attUsername = ele.attributeValue("username");//得到ele(user标签)下的username的值
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
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user){
		//也用dom4j代码
		/*
		 * 1.得到document
		 * 2.通过document得到root元素<users>
		 * 3.在root添加新元素
		 * 4.为userEle设置属性(属性值在user中)
		 * 5.保存document(回写XML)
		 */
		
		//1.得到SAXReader,得到document,都到root
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(path);
			//2.
			Element root = document.getRootElement();
			
			//3.通过根元素创建新元素
			Element userEle = root.addElement("user");
			//4.为userEle设置属性
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			userEle.addAttribute("status", user.getStatus());
			
			//5.回写XML
//			//格式
//			OutputFormat format = new OutputFormat("\t",true);//是否使用缩进,是否换行
//			format.setTrimText(true);//清空原有格式(换行和缩进)
			//保存文档
			XMLWriter writer;
			//因为直接用FileWriter绑定了系统默认编码(GBK),所以要通过OutpuStreamWriter来设置编码
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
