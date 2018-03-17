package demo6;

import java.util.List;

/**
 * ͨ��WebService��ȡ����Ԥ��
 * http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl
 */
public class App {
	public static void main(String[] args) {
		// http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx
		WeatherWS weatherWS = new WeatherWS();
		WeatherWSSoap soap = weatherWS.getWeatherWSSoap();
		//��һ���������������֣��ڶ�����������ID��javadoc��д
		// ����getWeather�����һ�ε��ñ�503���󣬵ڶ��ε��óɹ���
		ArrayOfString weather = soap.getWeather("����", null);
		List<String> details = weather.string;
		for (String string : details) {
			System.out.println(string);
		}
		/*
			�㶫 ����
			����
			2419
			2016/02/28 13:04:22
			��������ʵ�������£�20�棻����/���������Ϸ� 3����ʪ�ȣ�55%
			������ǿ�ȣ����������������С�
			������ָ�����������������Ϳ��SPF12-15��PA+����Ʒ��
			��ðָ�������׷���������������·���ע�������
			����ָ���������ʣ����鴩�����׻�ţ�п�ȷ�װ��
			ϴ��ָ���������ˣ������ҷ�����С���ױ������ȡ�
			��ָͨ�������ã������������ã���������������ʻ��
			������Ⱦָ�����У��׸���ȺӦ�ʵ�����������
			
			2��28�� ����
			14��/21��
			�޳�������΢��
			1.gif
			1.gif
			2��29�� ��
			14��/23��
			������3-4��
			0.gif
			0.gif
			3��1�� ��
			13��/20��
			������3-4��
			0.gif
			0.gif
			3��2�� ����ת��
			14��/21��
			�޳�������΢��
			1.gif
			2.gif
			3��3�� ��ת����
			15��/20��
			�޳�������΢��
			2.gif
			1.gif
		   
		 */
	}
}
