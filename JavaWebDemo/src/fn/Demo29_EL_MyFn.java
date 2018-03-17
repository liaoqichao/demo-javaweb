package fn;

/**
 * 自定义EL函数库
 * 1.写一个类,有0~N个必须有返回值的静态成员方法。
 * 2.在WEB-INF(不希望用户看见)/tlds(自己建的)/XXX.tlds
 * @author Administrator
 *
 */
public class Demo29_EL_MyFn {
	public static String func(){
		return "我的自定义EL函数库";
	}
}
