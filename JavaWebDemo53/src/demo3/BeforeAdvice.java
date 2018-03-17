package demo3;

/**
 * 前置增强。想要要在被增强的内容之前调用的代码。
 * 想要增强的内容不能封装成对象，例如if语句，一个输出语句等，就用接口。实现这个接口的方法，方法里面写这些语句。
 */
public interface BeforeAdvice {
	public void before();
}
