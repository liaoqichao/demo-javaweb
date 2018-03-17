package ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义全局异常处理器
 * @author Liaoqichao
 * @date 2016年5月8日
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
		/*
		 *  Object arg2 ： 就是处理器适配器要执行的Handler对象，这个对象只有一个method方法。
		 *  Exception arg3 : 系统抛出的异常
		 */
		
		/*
		 * 全局异常处理器解析接收到的异常的类型，如果异常类型是程序员自定义的异常，则在错误页面显示异常信息；如果异常类型不是程序员自定义
		 * 	的异常(那么基本就是运行时异常)，构造一个自定义异常类，异常信息为“未知错误”。
		 */
		
//		String message = null;
//		if(arg3 instanceof CustomException){ //全局异常处理器解析接收到的异常的类型
//			// 如果异常类型是程序员自定义的异常，则在错误页面显示异常信息
//			message = ((CustomException)arg3).getMessage();
//		} else{ //如果异常类型不是程序员自定义的异常(那么基本就是运行时异常)，构造一个自定义异常类，异常信息为“未知错误”。
//			
//		}
		
		CustomException customException = null;
		if(arg3 instanceof CustomException){
			customException = (CustomException)arg3;
		} else{
			customException = new CustomException("未知错误");
		}
		
		// 错误信息
		String message = customException.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message); // 保存错误信息到页面
		modelAndView.setViewName("error");  // 设置视图，省略的前缀和后缀
		
		return modelAndView;
	}

	
}
