package demo6;

import java.util.List;

/**
 * 通过WebService获取天气预报
 * http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl
 */
public class App {
	public static void main(String[] args) {
		// http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx
		WeatherWS weatherWS = new WeatherWS();
		WeatherWSSoap soap = weatherWS.getWeatherWSSoap();
		//第一个参数，城市名字，第二个参数城市ID，javadoc有写
		// 下面getWeather服务第一次调用报503错误，第二次调用成功！
		ArrayOfString weather = soap.getWeather("深圳", null);
		List<String> details = weather.string;
		for (String string : details) {
			System.out.println(string);
		}
		/*
			广东 深圳
			深圳
			2419
			2016/02/28 13:04:22
			今日天气实况：气温：20℃；风向/风力：西南风 3级；湿度：55%
			紫外线强度：弱。空气质量：中。
			紫外线指数：弱，辐射较弱，涂擦SPF12-15、PA+护肤品。
			感冒指数：较易发，天较凉，增加衣服，注意防护。
			穿衣指数：较舒适，建议穿薄外套或牛仔裤等服装。
			洗车指数：较适宜，无雨且风力较小，易保持清洁度。
			交通指数：良好，气象条件良好，车辆可以正常行驶。
			空气污染指数：中，易感人群应适当减少室外活动。
			
			2月28日 多云
			14℃/21℃
			无持续风向微风
			1.gif
			1.gif
			2月29日 晴
			14℃/23℃
			东北风3-4级
			0.gif
			0.gif
			3月1日 晴
			13℃/20℃
			东北风3-4级
			0.gif
			0.gif
			3月2日 多云转阴
			14℃/21℃
			无持续风向微风
			1.gif
			2.gif
			3月3日 阴转多云
			15℃/20℃
			无持续风向微风
			2.gif
			1.gif
		   
		 */
	}
}
