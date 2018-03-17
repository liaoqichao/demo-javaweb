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
 * ���Ͳ����������ʼ�
 * ���ʹ��������ʼ�
 * @author Administrator
 *
 */
public class JavaMailDemo {
//	@Test
	/**
	 * ���Ͳ����������ʼ�
	 * 1.�õ�session��2.����MimeMesage��3.Transport.send(msg);
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void fun1() throws AddressException, MessagingException{
		//1.�õ�session
		//��props����2����ֵ��
		Properties props = new Properties();
		props.put("mail.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		//����authenticator��ʵ��PasswordAuthentication����
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("13670006805", "BTtCMjCMyx1994");//�û���������Ҫ��@163.com
			}
			
		};
		Session session = 	Session.getInstance(props, authenticator);
		
		//2.����MimeMessage
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("13670006805@163.com"));//���÷�����
		
		//setRecipients��s��ûs�Ļ��ڶ���������address[] ����address
		msg.setRecipients(RecipientType.TO, "lqcdayin@163.com");//�����ռ���
		msg.setRecipients(RecipientType.CC, "enjoy_the_game@163.com");//���ó���
		//���ó��ͣ�A���͸�B,����CΧ���ʼ����� -> A���͸�C,BҲ֪��C�յ��ʼ����ʼ���д�����ˣ��ռ��ˣ�������..
		
//		msg.setRecipients(RecipientType.BCC, "lqcdayin@163.com");
		//���ð��� A���͸�B������CΧ���ʼ�����,���Ǿ�ֻ��A��C֪��C�����ʼ��� -> A���͸�C������ʼ����Ƿ����Լ���������Ҳû�Լ����Լ����յ��ˣ����ǰ��͡�
		
		//��������
		msg.setSubject("JavaWebDemo49-JavaMail");
		
		//��������
		msg.setContent("�����JavaWebDemo49-JavaMail������", "text/html;charset=utf-8");//�����ı����ݺ�MIME����
		
		//3.Transport.send(msg)�����ʼ�
		Transport.send(msg);
		
		//�������lqcdayin@163.com��
//		JavaWebDemo49-JavaMail
//		�����ˣ�
//		13670006805<13670006805@163.com> +
//		�ռ��ˣ�
//		��<lqcdayin@163.com>
//		�����ˣ�
//		enjoy_the_game<enjoy_the_game@163.com> +
//		ʱ   �䣺
//		2015��11��09�� 10:35 (����һ)
	}
	
//	@Test
	/**
	 * ���ʹ��������ʼ�
	 * msg.setContent(mp):MimeMultipart:MimeBodyPart = 1:1:n
	 */
	public void fun2() throws AddressException, MessagingException, IOException{
		//1. �õ�session����׼��properties��authenticator
		Properties props = new Properties();
//		props.setProperty("mail.transport.protocol", "smtp"); // ����Э�飬���Բ�������
		props.put("mail.host", "smtp.163.com"); // ����SMTP����
		props.put("mail.smtp.auth", "true"); // �Ƿ���Ҫ���������֤
		
		// ʵ�ֵ�¼����ʱ���û������֤����
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("13670006805","BTtCMjCMyx1994");
			}
			
		};
		
		Session session = Session.getInstance(props, authenticator);
		
		//2.����MimeMessage
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("13670006805@163.com"));//���÷�����
		msg.setRecipients(RecipientType.TO, "lqcdayin@163.com");//�����ռ���
		msg.setSubject("JavaWebDemo49-JavaMail-���������ʼ�");//��������
		
		////////////////////////////////////////////////////////
		/*
		 * ���Ͱ����������ʼ�ʱ���ʼ����Ϊ�ಿ����ʽ��
		 * 1.����һ���ಿ�����ʼ����ݡ�MimeMultipart�ࡣMimeMultipar����һ�����ϣ�����װ�ض�����岿��
		 * 2.��Ҫ����2�����岿��(MimeBodyPart)��һ�����ı����ݵģ�һ���Ǹ����ġ�
		 * 3.��MimeMultipart���ø�MimeMessage������
		 */
		MimeMultipart multipart = new MimeMultipart();	//�����ಿ������
		MimeBodyPart part1 = new MimeBodyPart();		//����MimeBodyPoart����������ı�����
		part1.setContent("�����������ʼ����������ı�����", "text/html;charset=utf-8");//�������岿��part1������
		multipart.addBodyPart(part1);					//�����岿����ӵ�multipart��		
		msg.setContent(multipart);						//���ø��ʼ���Ϊ�ʼ�������
		
		MimeBodyPart part2 = new MimeBodyPart();		//�����ڶ���MimBodyPart
		part2.attachFile(new File("E:/Eclipse/IO/ByteStream/demo2.jpg"));//����MimeBodyPart����������Ϊ����
		part2.setFileName(MimeUtility.encodeText("����������"));			//���ø��������ƣ����룬��ҪMimeUtility.encodeText�����������
		multipart.addBodyPart(part2);
		////////////////////////////////////////////////////////

		//3.�����ʼ�
		Transport.send(msg);
	}
	
}
