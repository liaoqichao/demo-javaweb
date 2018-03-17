package ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * �Զ�������ת��������Ҫʵ��Converter�ӿ�
 * Converter�ӿڵ��������ͣ���һ����ʾԭʼ���ͣ��ڶ����Ǳ�ת���ɵ�����
 * @author Liaoqichao
 * @date 2016��5��2��
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		/*
		 * ʵ�������ַ���(yyyy-MM-dd HH:mm:ss)ת������������
		 * ���Ǵ����ݿ��л�ȡ�յ�timestamp�ᱨ������취��jdbc����url����Ӳ���zeroDateTimeBehavior=convertToNull
		 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// ת���ɹ�ֱ�ӷ���
//			if(source.trim().equals(""))
//				return null;
			return formatter.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			if(!source.trim().equals("")){
				e.printStackTrace();
				System.out.println("���ڸ�ʽ����");
			} else{
				System.out.println("û����������");
			}
		}
		// ������ʧ�ܷ���null
		return null;
	}

}
