package javaMail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 发送不带附件的邮件
 * 发送带附件的邮件
 * @author Administrator
 *
 */
public class JavaMailDemo {
//	@Test
	/**
	 * 发送不带附件的邮件
	 * 1.得到session、2.创建MimeMesage、3.Transport.send(msg);
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void fun1() throws AddressException, MessagingException{
		//1.得到session
		//得props设置2个键值对
		Properties props = new Properties();
		props.put("mail.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		//创建authenticator，实现PasswordAuthentication方法
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("13670006805", "BTtCMjCMyx1994");//用户名，不需要加@163.com
			}
			
		};
		Session session = 	Session.getInstance(props, authenticator);
		
		//2.创建MimeMessage
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("13670006805@163.com"));//设置发件人
		
		//setRecipients有s，没s的话第二个参数是address[] 或者address
		msg.setRecipients(RecipientType.TO, "lqcdayin@163.com");//设置收件人
		msg.setRecipients(RecipientType.CC, "enjoy_the_game@163.com");//设置抄送
		//设置抄送，A发送给B,允许C围观邮件内容 -> A抄送给C,B也知道C收到邮件。邮件有写发送人，收件人，抄送人..
		
//		msg.setRecipients(RecipientType.BCC, "lqcdayin@163.com");
		//设置暗送 A发送给B，允许C围观邮件内容,但是就只有A和C知道C收了邮件。 -> A暗送给C。如果邮件不是发给自己，抄送人也没自己，自己还收到了，就是暗送。
		
		//设置主题
		msg.setSubject("JavaWebDemo49-JavaMail");
		
		//设置内容
		msg.setContent("这就是JavaWebDemo49-JavaMail的内容", "text/html;charset=utf-8");//设置文本内容和MIME类型
		
		//3.Transport.send(msg)发送邮件
		Transport.send(msg);
		
		//结果，从lqcdayin@163.com看
//		JavaWebDemo49-JavaMail
//		发件人：
//		13670006805<13670006805@163.com> +
//		收件人：
//		我<lqcdayin@163.com>
//		抄送人：
//		enjoy_the_game<enjoy_the_game@163.com> +
//		时   间：
//		2015年11月09日 10:35 (星期一)
	}
	
//	@Test
	/**
	 * 发送带附件的邮件
	 * msg.setContent(mp):MimeMultipart:MimeBodyPart = 1:1:n
	 */
	public void fun2() throws AddressException, MessagingException, IOException{
		//1. 得到session，先准备properties和authenticator
		Properties props = new Properties();
//		props.setProperty("mail.transport.protocol", "smtp"); // 设置协议，可以不用设置
		props.put("mail.host", "smtp.163.com"); // 设置SMTP主机
		props.put("mail.smtp.auth", "true"); // 是否需要进行身份认证
		
		// 实现登录邮箱时的用户身份认证方法
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("13670006805","BTtCMjCMyx1994");
			}
			
		};
		
		Session session = Session.getInstance(props, authenticator);
		
		//2.创建MimeMessage
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("13670006805@163.com"));//设置发件人
		msg.setRecipients(RecipientType.TO, "lqcdayin@163.com");//设置收件人
		msg.setSubject("JavaWebDemo49-JavaMail-带附件的邮件");//设置主题
		
		////////////////////////////////////////////////////////
		/*
		 * 发送包含发件的邮件时，邮件体就为多部件形式！
		 * 1.创建一个多部件的邮件内容。MimeMultipart类。MimeMultipar就是一个集合，用来装载多个主体部件
		 * 2.需要创建2个主体部件(MimeBodyPart)，一个是文本内容的，一个是附件的。
		 * 3.把MimeMultipart设置给MimeMessage的内容
		 */
		MimeMultipart multipart = new MimeMultipart();	//创建多部件主体
		MimeBodyPart part1 = new MimeBodyPart();		//创建MimeBodyPoart，用来存放文本内容
		part1.setContent("包含附件的邮件，这里是文本内容", "text/html;charset=utf-8");//设置主体部件part1的内容
		multipart.addBodyPart(part1);					//把主体部件添加到multipart中		
		msg.setContent(multipart);						//设置给邮件作为邮件的内容
		
		MimeBodyPart part2 = new MimeBodyPart();		//创建第二个MimBodyPart
		part2.attachFile(new File("E:/Eclipse/IO/ByteStream/demo2.jpg"));//设置MimeBodyPart的主体内容为附件
		part2.setFileName(MimeUtility.encodeText("附件的名称"));			//设置附件的名称，乱码，需要MimeUtility.encodeText解决乱码问题
		multipart.addBodyPart(part2);
		////////////////////////////////////////////////////////

		//3.发送邮件
		Transport.send(msg);
	}
	
}
