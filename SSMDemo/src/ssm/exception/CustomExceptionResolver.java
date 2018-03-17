package ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * �Զ���ȫ���쳣������
 * @author Liaoqichao
 * @date 2016��5��8��
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
		/*
		 *  Object arg2 �� ���Ǵ�����������Ҫִ�е�Handler�����������ֻ��һ��method������
		 *  Exception arg3 : ϵͳ�׳����쳣
		 */
		
		/*
		 * ȫ���쳣�������������յ����쳣�����ͣ�����쳣�����ǳ���Ա�Զ�����쳣�����ڴ���ҳ����ʾ�쳣��Ϣ������쳣���Ͳ��ǳ���Ա�Զ���
		 * 	���쳣(��ô������������ʱ�쳣)������һ���Զ����쳣�࣬�쳣��ϢΪ��δ֪���󡱡�
		 */
		
//		String message = null;
//		if(arg3 instanceof CustomException){ //ȫ���쳣�������������յ����쳣������
//			// ����쳣�����ǳ���Ա�Զ�����쳣�����ڴ���ҳ����ʾ�쳣��Ϣ
//			message = ((CustomException)arg3).getMessage();
//		} else{ //����쳣���Ͳ��ǳ���Ա�Զ�����쳣(��ô������������ʱ�쳣)������һ���Զ����쳣�࣬�쳣��ϢΪ��δ֪���󡱡�
//			
//		}
		
		CustomException customException = null;
		if(arg3 instanceof CustomException){
			customException = (CustomException)arg3;
		} else{
			customException = new CustomException("δ֪����");
		}
		
		// ������Ϣ
		String message = customException.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message); // ���������Ϣ��ҳ��
		modelAndView.setViewName("error");  // ������ͼ��ʡ�Ե�ǰ׺�ͺ�׺
		
		return modelAndView;
	}

	
}
