package struts2.demo8.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/**
 * �Զ�������ת������
 * ����ת������ʵ��˫��ת����(String)20160321 --> (Date)Mon,Mar 21 2016 ...Date�������ʽ
 * 					(Date)����  --> �ַ�������20160321   
 * ����������ת��������Ҫע�ᡣ���Ǿֲ�����ת�������վֲ�����ת�����ĸ�ʽע��
 * Ҫ��:�ܽ����ַ���"20160321"�������Date���ͣ���Struts2����
 */
public class DateTypeConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType) {
		/*
		 * context����ognl���ʽ�йأ��Ժ�˵��
		 * value�����յ���ֵ��������20160321
		 * toType��Ҫת���ɵ�����
		 * 
		 * ����Ҫʵ��˫��ת����
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");//��������ĸ�ʽ��
		try {
			if(toType == Date.class){//���ַ�������ת����Date����ʱ
				/*
				 * ΪʲôҪ��valueת����String�����أ���Ϊid=123������һ��id��Ӧһ��ֵ�������п��ܻ���һ��������Ӧһ��ֵ��
				 * struts2Ϊ�˿��ǵ�������������԰Ѳ�����ֵ���������洢��
				 * Struts���õ���request.getParameterValues(String arg0)�ķ�ʽ��ȡĳ��������Ӧ��һ������ֵ��
				 */
				String[] params = (String[])value;	//��valueת����String����
				return format.parse(params[0]);//ȡ�����±�Ϊ0��Ԫ�أ���ʵ����һ����1��Ԫ�ء�
			} else if(toType == String.class){//��Date����ת����String����ʱ��ʹ��struts2��ǩ��ҳ����Բ�����ʱ��
				Date date = (Date)value;
				return format.format(date);//Date���yyyyMMdd��ʽ�ַ���
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	
}
